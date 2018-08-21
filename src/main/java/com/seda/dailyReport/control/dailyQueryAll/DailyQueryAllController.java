package com.seda.dailyReport.control.dailyQueryAll;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.seda.dailyReport.model.vo.DailyPlanVo;
import com.seda.dailyReport.model.vo.DailyReportVo;
import com.seda.dailyReport.service.dailyQueryAll.DailyQueryAllService;

/**
 * 查询项目组成员工作计划及日志controller类
 * @author 郭腾飞 20180628
 *
 */
@Controller
@RequestMapping("/dailyQueryAll")
public class DailyQueryAllController {

	@Resource
	private DailyQueryAllService dailyQueryAllService;
	
	/**
	 * 查询项目组成员工作计划
	 * @param reportVo
	 * @param request
	 * @return
	 */
	@RequestMapping("/getPlanALLList")
	@ResponseBody
	public PageInfo<DailyPlanVo> getPlanALLList(DailyPlanVo planVo, HttpServletRequest request){
		return this.dailyQueryAllService.getPlanALLList(planVo, request);
	}
	
	/**
	 * 查询项目组成员工作日志
	 * @param reportVo
	 * @param request
	 * @return
	 */
	@RequestMapping("/getReportALLList")
	@ResponseBody
	public PageInfo<DailyReportVo> getReportALLList(DailyReportVo reportVo, HttpServletRequest request){
		return this.dailyQueryAllService.getReportALLList(reportVo, request);
	}
}
