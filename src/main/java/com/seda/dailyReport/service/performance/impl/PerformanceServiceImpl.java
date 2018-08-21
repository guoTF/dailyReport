package com.seda.dailyReport.service.performance.impl;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.seda.dailyReport.dao.LoginUserMapper;
import com.seda.dailyReport.dao.PerformanceAppraisalMapper;
import com.seda.dailyReport.dao.TaskClassificationMapper;
import com.seda.dailyReport.model.LoginUser;
import com.seda.dailyReport.model.LoginUserExample;
import com.seda.dailyReport.model.PerformanceAppraisal;
import com.seda.dailyReport.model.PerformanceAppraisalExample;
import com.seda.dailyReport.model.TaskClassification;
import com.seda.dailyReport.model.TaskClassificationExample;
import com.seda.dailyReport.model.dto.OperationDto;
import com.seda.dailyReport.model.vo.PerformanceVo;
import com.seda.dailyReport.service.performance.PerformanceService;
import com.seda.dailyReport.util.CreatePrimaryKeyUtils;
import com.seda.dailyReport.util.DateUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 绩效考核service实现类
 * 
 * @author 郭腾飞 20180629
 *
 */
@Service
public class PerformanceServiceImpl implements PerformanceService {

	private static final Logger logger = LoggerFactory.getLogger(PerformanceServiceImpl.class);

	@Resource
	private LoginUserMapper loginUserMapper;

	@Resource
	private PerformanceAppraisalMapper performanceAppraisalMapper;
	
	@Resource
	private TaskClassificationMapper taskClassificationMapper;

	@Value("${mail.smtp.auth}")
	private String auth;

	@Value("${mail.transport.protocol}")
	private String protocol;

	@Value("${mail.smtp.host}")
	private String host;

	@Value("${from.mail}")
	private String fromMail;

	@Value("${from.mail.pw}")
	private String fromMailPw;

	/**
	 * 跳转到新增绩效考核页面
	 */
	@Override
	public PerformanceVo newPerformancePage(HttpServletRequest request) {
		PerformanceVo pv = new PerformanceVo();
		String userId = (String) request.getSession().getAttribute("userID");
		LoginUser loginUser = this.loginUserMapper.selectByPrimaryKey(userId);
		pv.setLoginUser(loginUser);
		String format = DateUtils.format(new Date(), DateUtils.LONG_PURE_DIGITAL_PATTERN);
		String appraisalMonth = format.substring(0, 6);
		pv.setAppraisalMonth(appraisalMonth);
		// 如果存在该工作周期的内容时，为更新内容。
		PerformanceAppraisalExample example = new PerformanceAppraisalExample();
		example.createCriteria().andAppraisalMonthEqualTo(appraisalMonth).andUseridEqualTo(userId);
		List<PerformanceAppraisal> paList = this.performanceAppraisalMapper.selectByExample(example);
		pv.setPaList(paList);
		return pv;
	}

	/**
	 * 新增绩效考核内容
	 */
	@Override
	public OperationDto addPerformance(PerformanceVo pv, HttpServletRequest request) {
		OperationDto dto = new OperationDto();
		LoginUser loginUser = pv.getLoginUser();
		String userId = loginUser.getId();
		String appraisalMonth = pv.getAppraisalMonth();
		String performanceAppraisalStr = pv.getPerformanceAppraisalStr();
		JSONArray jsonArray = JSONArray.fromObject(performanceAppraisalStr);
		if (CollectionUtils.isEmpty(jsonArray)) {
			return dto.fail("0", "绩效考核内容不能为空");
		}
		Integer status = jsonArray.getJSONObject(0).getInt("status");
		if (status == 1) {
			return dto.fail("0", "邮件已发送给上级主管");
		}
		// 根据考核周期和用户id查询，是否之前已经保存过绩效考核内容
		PerformanceAppraisalExample example = new PerformanceAppraisalExample();
		example.createCriteria().andUseridEqualTo(userId).andAppraisalMonthEqualTo(appraisalMonth);
		List<PerformanceAppraisal> list = this.performanceAppraisalMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(list)) {
			int i = this.performanceAppraisalMapper.deleteByExample(example);
			if (i != list.size()) {
				return dto.fail("0", "保存失败1");
			}
		}
		int count = 0;
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			String concreteFunction = jsonObject.getString("concreteFunction");// 具体功能
			String manHour = jsonObject.getString("manHour");// 耗用工时
			String projectName = jsonObject.getString("projectName");// 项目名称
			String performance = jsonObject.getString("performance");// 完成情况（%）
			PerformanceAppraisal appraisal = new PerformanceAppraisal();
			if (StringUtils.isBlank(concreteFunction)) {
				return dto.fail("0", "具体功能为空");
			}
			if (StringUtils.isBlank(manHour)) {
				return dto.fail("0", "耗用工时为空");
			}
			if (StringUtils.isBlank(projectName)) {
				return dto.fail("0", "项目名称为空");
			}
			if (StringUtils.isBlank(performance)) {
				return dto.fail("0", "完成情况为空");
			}
			appraisal.setConcreteFunction(concreteFunction);
			appraisal.setManHour(manHour);
			appraisal.setProjectName(projectName);
			appraisal.setPerformance(performance);
			appraisal.setId(CreatePrimaryKeyUtils.createPrimaryKey());
			appraisal.setAppraisalMonth(appraisalMonth);
			appraisal.setCreateBy(userId);
			appraisal.setUserid(userId);
			appraisal.setTaskId(jsonObject.getString("taekId"));//该字段保存任务名称，不是id值
			appraisal.setOvertim(jsonObject.getString("overtim"));
			String format = DateUtils.format(new Date(), DateUtils.LONG_PURE_DIGITAL_PATTERN);
			appraisal.setCreateDate(format);
			appraisal.setUpdateBy(userId);
			appraisal.setUpdateDate(format);
			appraisal.setStatus(0);// 状态(0：未发邮件，1：已发邮件)
			int j = this.performanceAppraisalMapper.insertSelective(appraisal);
			if (j == 1) {
				count++;
			}
		}
		if (count == jsonArray.size()) {
			return dto.success("保存成功");
		}
		return dto.fail("0", "保存失败");
	}

	/**
	 * 发送邮件
	 */
	@Override
	public OperationDto sendMail(String toMail, String month, HttpServletRequest request) {
		OperationDto dto = new OperationDto();
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", auth);
		props.setProperty("mail.transport.protocol", protocol);
		props.put("mail.smtp.host", host);// smtp服务器地址

		Session session = Session.getInstance(props);
		session.setDebug(true);

		String userId = (String) request.getSession().getAttribute("userID");
		LoginUser loginUser = this.loginUserMapper.selectByPrimaryKey(userId);
		String userName = loginUser.getUserName();

		Message msg = new MimeMessage(session);
		try {
			msg.setSubject(userName + "在考核周期" + month + "的考核内容");
			msg.setText("公司主管：你好。本人" + userName + "在考核周期" + month + "的考核内容已填写完成，并提交到系统。请进行查阅。谢谢！");
			msg.setFrom(new InternetAddress(fromMail));// 发件人邮箱
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toMail)); // 收件人邮箱
			msg.saveChanges();

			Transport transport = session.getTransport();
			transport.connect(fromMail, fromMailPw);// 发件人邮箱,授权码(可以在邮箱设置中获取到授权码的信息)

			transport.sendMessage(msg, msg.getAllRecipients());

			System.out.println("邮件发送成功...");
			transport.close();
			// 邮件发送成功后，将绩效考核状态改为1
			PerformanceAppraisalExample example = new PerformanceAppraisalExample();
			example.createCriteria().andAppraisalMonthEqualTo(month).andUseridGreaterThan(userId).andStatusEqualTo(0);
			List<PerformanceAppraisal> paList = this.performanceAppraisalMapper.selectByExample(example);
			if (CollectionUtils.isNotEmpty(paList)) {
				int count = 0;
				for (int i = 0; i < paList.size(); i++) {
					PerformanceAppraisal appraisal = paList.get(i);
					appraisal.setStatus(1);
					int j = this.performanceAppraisalMapper.updateByPrimaryKeySelective(appraisal);
					if (j == 1) {
						count++;
					}
				}
				if (count != paList.size()) {
					return dto.fail("0", "邮件发送失败");
				}
			}
			return dto.success("邮件发送成功");
		} catch (Exception e) {
			logger.debug(e.toString());
			return dto.fail("0", "邮件发送失败");
		}
	}

	/**
	 * 查询
	 */
	@Override
	public OperationDto queryPerformance(String name, String month, HttpServletRequest request) {
		OperationDto dto = new OperationDto();
		if (StringUtils.isBlank(name)) {
			return dto.fail("", "考核人员名称为空");
		}
		if (StringUtils.isBlank(month)) {
			return dto.fail("", "考核周期为空");
		}
		LoginUserExample userExample = new LoginUserExample();
		userExample.createCriteria().andUserNameEqualTo(name).andStatusEqualTo(1);
		List<LoginUser> userList = this.loginUserMapper.selectByExample(userExample);
		if (CollectionUtils.isEmpty(userList)) {
			return dto.fail("", "考核人员信息为空");
		}
		PerformanceAppraisalExample example = new PerformanceAppraisalExample();
		example.createCriteria().andUseridEqualTo(userList.get(0).getId()).andAppraisalMonthEqualTo(month);
		List<PerformanceAppraisal> list = this.performanceAppraisalMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(list)) {
			return dto.success(list);
		}
		return dto.fail("", "该考核人员" + name + "还未填写" + month + "考核周期的考核考核内容！");
	}

	/**
	 * 获取所有的任务名称
	 */
	@Override
	public OperationDto getTaskName() {
		OperationDto dto = new OperationDto();
		TaskClassificationExample example = new TaskClassificationExample();
		example.createCriteria();
		List<TaskClassification> list = this.taskClassificationMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(list)) {
			return dto.success(list);
		}
		return dto.fail("0", "任务名称为空");
	}

}
