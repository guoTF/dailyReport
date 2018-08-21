package com.seda.dailyReport.service.dailyPlan;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.seda.dailyReport.model.DailyPlan;
import com.seda.dailyReport.model.dto.OperationDto;

/**
 * 日工作计划service类
 * @author 郭腾飞 20180626
 *
 */
public interface DailyPlanService {

	/**
	 * 保存日计划
	 * @param planList
	 * @param request
	 * @return
	 */
	OperationDto savePlan(String planStr, HttpServletRequest request);

	/**
	 * 获取日计划
	 * @param day
	 * @param request
	 * @return
	 */
	List<DailyPlan> getPlan(String day, HttpServletRequest request);

	/**
	 * 删除一条记录
	 * @param planId
	 * @return
	 */
	OperationDto deleteOnePlan(String planId);

}
