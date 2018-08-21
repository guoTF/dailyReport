package com.seda.dailyReport.service.result.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.seda.dailyReport.dao.LoginUserMapper;
import com.seda.dailyReport.dao.PerformanceAppraisalMapper;
import com.seda.dailyReport.dao.PerformanceResultMapper;
import com.seda.dailyReport.model.LoginUser;
import com.seda.dailyReport.model.LoginUserExample;
import com.seda.dailyReport.model.PerformanceAppraisal;
import com.seda.dailyReport.model.PerformanceAppraisalExample;
import com.seda.dailyReport.model.PerformanceResult;
import com.seda.dailyReport.model.PerformanceResultExample;
import com.seda.dailyReport.model.dto.OperationDto;
import com.seda.dailyReport.model.vo.PerformanceResultVo;
import com.seda.dailyReport.service.result.PerformanceResultService;
import com.seda.dailyReport.util.CreatePrimaryKeyUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class PerformanceResultServiceImpl implements PerformanceResultService {
	
	@Resource
	private LoginUserMapper loginUserMapper;
	
	@Resource
	private PerformanceAppraisalMapper performanceAppraisalMapper;
	
	@Resource
	private PerformanceResultMapper performanceResultMapper;

	/**
	 * 获取所在部门的成员的考核内容
	 */
	@Override
	public OperationDto getAllLoginUser(HttpServletRequest request) {
		OperationDto dto = new OperationDto();
		String userId = (String) request.getSession().getAttribute("userID");
		LoginUser loginUser = this.loginUserMapper.selectByPrimaryKey(userId);
		LoginUserExample userExample = new LoginUserExample();
		userExample.createCriteria().andActivatedEqualTo(1).andStatusEqualTo(1).andDepartmentEqualTo(loginUser.getDepartment());
		userExample.setOrderByClause(" user_name ");
		List<LoginUser> list = this.loginUserMapper.selectByExample(userExample);
		return dto.success(list);
	}
	
	/**
	 * 根据名字及考核周期查询考核内容
	 */
	@Override
	public OperationDto findPerformanceByUserNameAndMonth(String userID, String month, HttpServletRequest request) {
		OperationDto dto = new OperationDto();
		PerformanceResultVo resultVo = new PerformanceResultVo();
		if (StringUtils.isBlank(userID)) {
			return dto.fail("0", "考核人员名字为空");
		}
		if (StringUtils.isBlank(month)) {
			return dto.fail("0", "考核周期为空");
		}
		PerformanceAppraisalExample example = new PerformanceAppraisalExample();
		example.createCriteria().andUseridEqualTo(userID).andAppraisalMonthEqualTo(month);
		example.setOrderByClause(" item ");
		List<PerformanceAppraisal> paList = this.performanceAppraisalMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(paList)) {
			resultVo.setPerformanceAppraisalList(paList);
			String resultUserId = (String) request.getSession().getAttribute("userID");
			PerformanceResultExample resultExample = new PerformanceResultExample();
			resultExample.createCriteria().andResultUserIdEqualTo(resultUserId).andUserIdEqualTo(userID).andAppraisalMonthEqualTo(month);
			List<PerformanceResult> prList = this.performanceResultMapper.selectByExample(resultExample);
			if (CollectionUtils.isNotEmpty(prList)) {
				resultVo.setPerformanceResultList(prList);
			}
			return dto.success(resultVo);
		}
		return dto.fail("0", "未获取到该考核人员在该考核周期的内容");
	}

	/**
	 * 进行评分
	 */
	@Override
	public OperationDto grade(String userID, String month, String resultStr, HttpServletRequest request) {
		OperationDto dto = new OperationDto();
		if (StringUtils.isBlank(userID)) {
			return dto.fail("0", "考核人员名字为空");
		}
		if (StringUtils.isBlank(month)) {
			return dto.fail("0", "考核周期为空");
		}
		if (StringUtils.isBlank(resultStr)) {
			return dto.fail("0", "评分结果为空");
		}
		String resultUserId = (String) request.getSession().getAttribute("userID");
		PerformanceResultExample resultExample = new PerformanceResultExample();
		resultExample.createCriteria().andResultUserIdEqualTo(resultUserId).andUserIdEqualTo(userID).andAppraisalMonthEqualTo(month);
		List<PerformanceResult> list = this.performanceResultMapper.selectByExample(resultExample);
		if (CollectionUtils.isNotEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				PerformanceResult performanceResult = list.get(i);
				this.performanceResultMapper.deleteByPrimaryKey(performanceResult.getId());
			}
		}
		
		JSONArray jsonArray = JSONArray.fromObject(resultStr);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			String itemString = jsonObject.getString("item");
			String resultString = jsonObject.getString("result");
			if (StringUtils.isBlank(itemString)) {
				return dto.fail("0", "打分项序号为空");
			}
			if (StringUtils.isBlank(resultString)) {
				return dto.fail("", "打分未完成");
			}
			PerformanceResult result = new PerformanceResult();
			result.setId(CreatePrimaryKeyUtils.createPrimaryKey());
			result.setAppraisalMonth(month);
			result.setUserId(userID);
			result.setItem(Integer.valueOf(itemString));
			result.setResult(Integer.valueOf(resultStr));
			result.setResultUserId(resultUserId);
			int j = this.performanceResultMapper.insertSelective(result);
			if (j != 1) {
				return dto.fail("0", "保存失败");
			}
		}
		return dto.success("保存成功");
	}


}
