package com.seda.dailyReport.service.login;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seda.dailyReport.model.Department;
import com.seda.dailyReport.model.LoginUser;
import com.seda.dailyReport.model.Post;
import com.seda.dailyReport.model.dto.OperationDto;

/**
 * 登录service
 * @author admin
 *
 */
public interface LoginService {

	/**
	 * 注册
	 * @param loginUser
	 * @param request
	 * @return
	 */
	OperationDto register(LoginUser loginUser, String password2, String identifyingCode, String mobileCode, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 登录
	 * @param userName
	 * @param password
	 * @param identifyingCode
	 * @param request
	 * @return
	 */
	OperationDto login(String userName, String password, String identifyingCode, HttpServletRequest request);

	/**
	 * 获取手机验证码
	 * @param phone
	 * @param session
	 * @return
	 */
	OperationDto sendMobileCode(String phone, HttpSession session);

	/**
	 * 邮件激活
	 * @param id
	 * @param checkCode
	 * @param response
	 * @return
	 */
	void activate(String id, String checkCode, HttpServletResponse response);

	/**
	 * 获取所有部门
	 */
	List<Department> getDepartment();

	/**
	 * 获取部门中所有的岗位
	 * @param depId
	 * @return
	 */
	List<Post> getPost(int depId);

}
