package com.seda.dailyReport.service.result;

import javax.servlet.http.HttpServletRequest;

import com.seda.dailyReport.model.dto.OperationDto;

public interface PerformanceResultService {

	/**
	 * 根据名字及考核周期查询考核内容
	 * @param userName
	 * @param month
	 * @return
	 */
	OperationDto findPerformanceByUserNameAndMonth(String userID, String month, HttpServletRequest request);

	/**
	 * 获取所在部门的成员的考核内容
	 * @param request
	 * @return
	 */
	OperationDto getAllLoginUser(HttpServletRequest request);

	/**
	 * 进行评分
	 * @param userID
	 * @param month
	 * @param result
	 * @param request
	 */
	OperationDto grade(String userID, String month, String result, HttpServletRequest request);

}
