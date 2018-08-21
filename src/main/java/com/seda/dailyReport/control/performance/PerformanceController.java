package com.seda.dailyReport.control.performance;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seda.dailyReport.model.dto.OperationDto;
import com.seda.dailyReport.model.vo.PerformanceVo;
import com.seda.dailyReport.service.performance.PerformanceService;

/**
 * 绩效考核controller类
 * @author 郭腾飞 20180629
 *
 */
@Controller
@RequestMapping("/performance")
public class PerformanceController {

	@Resource
	private PerformanceService performanceService;
	
	/**
	 * 跳转到绩效考核页面/update
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/newPerformancePage")
	@ResponseBody
	public PerformanceVo newPerformancePage(HttpServletRequest request){
		return this.performanceService.newPerformancePage(request);
	}
	
	/**
	 * 新增绩效考核内容
	 * @param pv
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPerformance")
	@ResponseBody
	public OperationDto addPerformance(PerformanceVo pv, HttpServletRequest request){
		return this.performanceService.addPerformance(pv, request);
	}
	
	/**
	 * 发送邮件
	 * @param toMail
	 * @param request
	 * @return
	 */
	@RequestMapping("/sendMail")
	@ResponseBody
	public OperationDto sendMail(String toMail, String month, HttpServletRequest request){
		return this.performanceService.sendMail(toMail, month, request);
	}

	/**
	 * 获取任务名称列表
	 * @return
	 */
	@RequestMapping("/getTaskName")
	@ResponseBody
	public OperationDto getTaskName(){
		return this.performanceService.getTaskName();
	}
	
	/**
	 * 管理层查询某人某个考核周期的绩效考核内容
	 * @param name
	 * @param month
	 * @param request
	 * @return
	 */
	public OperationDto queryPerformance(String name, String month, HttpServletRequest request){
		return this.performanceService.queryPerformance(name, month, request);
	}
	
}
