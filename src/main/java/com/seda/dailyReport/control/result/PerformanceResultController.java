package com.seda.dailyReport.control.result;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seda.dailyReport.model.dto.OperationDto;
import com.seda.dailyReport.service.result.PerformanceResultService;

/**
 * 绩效考核评分controller
 * @author 郭腾飞 20180703
 *
 */
@Controller
@RequestMapping("/performanceResult")
public class PerformanceResultController {

	@Resource
	private PerformanceResultService performanceResultService;
	
	/**
	 * 获取所在部门的成员
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAllLoginUser")
	@ResponseBody
	public OperationDto getAllLoginUser(HttpServletRequest request){
		return this.performanceResultService.getAllLoginUser(request);
	}
	
	/**
	 * 根据名字及考核周期查询考核内容
	 * @param userName
	 * @param month
	 * @return
	 */
	@RequestMapping("/findPerformanceByUserNameAndMonth")
	@ResponseBody
	public OperationDto findPerformanceByUserNameAndMonth(String userID, String month, HttpServletRequest request){
		return this.performanceResultService.findPerformanceByUserNameAndMonth(userID, month, request);
	}
	
	/**
	 * 进行评分
	 * @param userID
	 * @param month
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping("/grade")
	@ResponseBody
	public OperationDto grade(String userID, String month, String result, HttpServletRequest request){
		return this.performanceResultService.grade(userID, month, result, request);
	}
}
