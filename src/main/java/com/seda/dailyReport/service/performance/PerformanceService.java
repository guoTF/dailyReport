package com.seda.dailyReport.service.performance;

import javax.servlet.http.HttpServletRequest;

import com.seda.dailyReport.model.dto.OperationDto;
import com.seda.dailyReport.model.vo.PerformanceVo;

/**
 * 绩效考核service类
 * @author 郭腾飞 20180629
 *
 */
public interface PerformanceService {

	/**
	 * 跳转到新增绩效考核页面
	 * @param request
	 * @return
	 */
	PerformanceVo newPerformancePage(HttpServletRequest request);
	
	/**
	 * 新增绩效考核内容
	 * @param pv
	 * @param request
	 * @return
	 */
	OperationDto addPerformance(PerformanceVo pv, HttpServletRequest request);

	/**
	 * 发送邮件
	 * @param toMail
	 * @param month 
	 * @param request
	 * @return
	 */
	OperationDto sendMail(String toMail, String month, HttpServletRequest request);

	/**
	 * 查询
	 * @param name
	 * @param month
	 * @param request
	 * @return
	 */
	OperationDto queryPerformance(String name, String month, HttpServletRequest request);

	/**
	 * 获取任务名称类表
	 * @return
	 */
	OperationDto getTaskName();


}
