package com.seda.dailyReport.service.dailyReport.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seda.dailyReport.dao.DailyPlanMapper;
import com.seda.dailyReport.dao.DailyReportMapper;
import com.seda.dailyReport.model.DailyPlan;
import com.seda.dailyReport.model.DailyPlanExample;
import com.seda.dailyReport.model.DailyReport;
import com.seda.dailyReport.model.DailyReportExample;
import com.seda.dailyReport.model.dto.OperationDto;
import com.seda.dailyReport.service.dailyReport.DailyReportService;
import com.seda.dailyReport.util.CreatePrimaryKeyUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 工作日志service实现类
 * 
 * @author 郭腾飞 20180627
 *
 */
@Service
public class DailyReportServiceImpl implements DailyReportService {

	@Resource
	private DailyPlanMapper dailyPlanMapper;

	@Resource
	private DailyReportMapper dailyReportMapper;

	/**
	 * 填写按钮
	 */
	@Override
	public List<DailyReport> getReportList(String reportDay, HttpServletRequest request) {
		ArrayList<DailyReport> reportList = new ArrayList<DailyReport>();
		String userId = (String) request.getSession().getAttribute("userID");
		DailyPlanExample planExample = new DailyPlanExample();
		planExample.createCriteria().andUserIdEqualTo(userId).andPlanDayEqualTo(reportDay);
		planExample.setOrderByClause(" plan_num ");
		List<DailyPlan> planList = this.dailyPlanMapper.selectByExample(planExample);
		if (CollectionUtils.isNotEmpty(planList)) {
			for (int i = 0; i < planList.size(); i++) {
				DailyReport report = new DailyReport();
				DailyPlan plan = planList.get(i);
				report.setReportNum(plan.getPlanNum());
				report.setReportProjectName(plan.getPlanProjectName());
				report.setReportContent(plan.getPlanContent());
				report.setReportGoal(plan.getPlanGoal());
				report.setReportTime(plan.getPlanTime());
				reportList.set(i, report);
			}
		}
		return reportList;
	}

	/**
	 * 保存工作日志
	 */
	@Override
	@Transactional
	public OperationDto saveReport(String reportStr, HttpServletRequest request) {
		OperationDto dto = new OperationDto();
		String userId = (String) request.getSession().getAttribute("userID");
		JSONArray jsonArray = JSONArray.fromObject(reportStr);
		if (CollectionUtils.isNotEmpty(jsonArray)) {
			String reportDay = jsonArray.getJSONObject(0).getString("reportDay");
			if (StringUtils.isBlank(reportDay)) {
				return dto.fail("0", "工作日为空");
			}
			DailyReportExample reportExample = new DailyReportExample();
			reportExample.createCriteria().andUserIdEqualTo(userId).andReportDayEqualTo(reportDay);
			List<DailyReport> oldReportList = this.dailyReportMapper.selectByExample(reportExample);
			if (CollectionUtils.isNotEmpty(oldReportList)) {
				int i = this.dailyReportMapper.deleteByExample(reportExample);
				if (i != oldReportList.size()) {
					return dto.fail("0", "保存失败");
				}
			}
			int count = 0;
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				String reportProjectName = jsonObject.getString("reportProjectName");
				String reportContent = jsonObject.getString("reportContent");
				Double reportTime = jsonObject.getDouble("reportTime");
				String reportTimestr = jsonObject.getString("reportTimestr");
				String reportTypeId = jsonObject.getString("reportTypeId");
				String reportGoal = jsonObject.getString("reportGoal");
				DailyReport report = new DailyReport();
				if (StringUtils.isBlank(reportProjectName)) {
					return dto.fail("0", "项目名称为空");
				}
				if (StringUtils.isBlank(reportContent)) {
					return dto.fail("0", "工作内容为空");
				}
				if (reportTime <= 0) {
					return dto.fail("0", "工作量填写错误");
				}
				if (StringUtils.isBlank(reportTimestr)) {
					return dto.fail("0", "工作时间为空");
				}
				if (StringUtils.isBlank(reportTypeId)) {
					return dto.fail("0", "工作类型为空");
				}
				if (StringUtils.isBlank(reportGoal)) {
					return dto.fail("0", "工作目标为空");
				}
				report.setReportNum(jsonObject.getInt("reportNum"));
				report.setReportDay(jsonObject.getString(reportDay));
				report.setReportProjectName(reportProjectName);
				report.setReportContent(reportContent);
				report.setReportTime(reportTime);
				report.setReportTimestr(reportTimestr);
				report.setReportTypeId(reportTypeId);
				report.setReportGoal(reportGoal);
				report.setId(CreatePrimaryKeyUtils.createPrimaryKey());
				report.setUserId(userId);
				report.setCreateDate(new Date());
				report.setUpdateDate(new Date());
				String remark = jsonObject.getString("remark");
				if (StringUtils.isNotBlank(remark)) {
					report.setRemark(remark);
				}
				int j = this.dailyReportMapper.insertSelective(report);
				if (j == 1) {
					count++;
				}
			}
			if (count == jsonArray.size()) {
				return dto.success("保存成功");
			}
		}
		return dto.fail("0", "保存失败");
	}

	/**
	 * 删除一条工作日志
	 */
	@Override
	public OperationDto deleteOneReport(String reportId) {
		OperationDto dto = new OperationDto();
		if (StringUtils.isBlank(reportId)) {
			return dto.fail("0", "获取删除信息失败");
		}
		int i = this.dailyReportMapper.deleteByPrimaryKey(reportId);
		if (i == 1) {
			return dto.success("删除成功");
		}
		return dto.fail("0", "删除失败");
	}

}
