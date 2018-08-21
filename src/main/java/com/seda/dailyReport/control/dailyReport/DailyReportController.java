package com.seda.dailyReport.control.dailyReport;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seda.dailyReport.model.DailyReport;
import com.seda.dailyReport.model.dto.OperationDto;
import com.seda.dailyReport.service.dailyReport.DailyReportService;

/**
 * 工作日志 controller类
 * @author 郭腾飞 20180627
 *
 */
@Controller
@RequestMapping("/dailyReport")
public class DailyReportController {

	@Resource
	private DailyReportService dailyReportService;
	
	/**
	 * 点击填写按钮
	 * @param reportDay
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getReportList")
	@ResponseBody
	public List<DailyReport> getReportList(String reportDay, HttpServletRequest request){
		return this.dailyReportService.getReportList(reportDay, request);
	}
	
	/**
	 * 保存工作日志
	 * @param reportList
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/saveReport")
	@ResponseBody
	public OperationDto saveReport(String reportStr, HttpServletRequest request){
		return this.dailyReportService.saveReport(reportStr, request);
	}
	
	/**
	 * 删除一条工作日志
	 * @param reportId
	 * @return
	 */
	@RequestMapping(value="/deleteOneReport")
	@ResponseBody
	public OperationDto deleteOneReport(String reportId){
		return this.dailyReportService.deleteOneReport(reportId);
	}
	
}
