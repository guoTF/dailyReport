package com.seda.dailyReport.control.dailyQuery;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.seda.dailyReport.model.vo.DailyPlanVo;
import com.seda.dailyReport.model.vo.DailyReportVo;
import com.seda.dailyReport.service.dailyQuery.DailyQueryService;

/**
 * 个人日志查询controller类
 * @author 郭腾飞 20180627
 *
 */
@Controller
@RequestMapping("/dailyQuery")
public class DailyQueryController {

	@Resource
	private DailyQueryService dailyQueryService;
	
	/**
	 * 工作计划查询
	 * @param planVo
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/queryPlanList")
	@ResponseBody
	public PageInfo<DailyPlanVo> queryPlanList(DailyPlanVo planVo, HttpServletRequest request){
		return this.dailyQueryService.queryPlanList(planVo, request);
	}
	
	/**
	 * 工作日志查询
	 * @param reportVo
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/queryReportList")
	@ResponseBody
	public PageInfo<DailyReportVo> queryReportList(DailyReportVo reportVo, HttpServletRequest request){
		return this.dailyQueryService.queryReportList(reportVo, request);
	}
}
