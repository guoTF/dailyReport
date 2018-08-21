package com.seda.dailyReport.service.dailyReport;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.seda.dailyReport.model.DailyReport;
import com.seda.dailyReport.model.dto.OperationDto;

/**
 * 工作日志service类
 * @author 郭腾飞 20180627
 *
 */
public interface DailyReportService {

	/**
	 * 填写按钮
	 * @param reportDay
	 * @param request
	 * @return
	 */
	List<DailyReport> getReportList(String reportDay, HttpServletRequest request);

	/**
	 * 保存工作日志
	 * @param reportStr
	 * @param request
	 * @return 
	 */
	OperationDto saveReport(String reportStr, HttpServletRequest request);

	/**
	 * 删除一条工作日志
	 * @param reportId
	 * @return
	 */
	OperationDto deleteOneReport(String reportId);

}
