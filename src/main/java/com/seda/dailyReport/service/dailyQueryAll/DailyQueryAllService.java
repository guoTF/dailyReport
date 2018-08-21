package com.seda.dailyReport.service.dailyQueryAll;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageInfo;
import com.seda.dailyReport.model.vo.DailyPlanVo;
import com.seda.dailyReport.model.vo.DailyReportVo;

/**
 * 查询项目组成员工作计划及日志service类
 * @author 郭腾飞 20180628
 *
 */
public interface DailyQueryAllService {

	/**
	 * 查询项目组成员工作计划
	 * @param planVo
	 * @param request
	 * @return
	 */
	PageInfo<DailyPlanVo> getPlanALLList(DailyPlanVo planVo, HttpServletRequest request);

	/**
	 * 查询项目组成员工作日志
	 * @param reportVo
	 * @param request
	 * @return
	 */
	PageInfo<DailyReportVo> getReportALLList(DailyReportVo reportVo, HttpServletRequest request);

}
