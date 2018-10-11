package com.seda.dailyReport.control.login;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.seda.dailyReport.model.Department;
import com.seda.dailyReport.model.LoginUser;
import com.seda.dailyReport.model.Post;
import com.seda.dailyReport.model.dto.OperationDto;
import com.seda.dailyReport.service.login.LoginService;

/**
 * 登录controller类
 * @author 郭腾飞 20180625
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	@Resource
	private LoginService loginService;
	
	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping(value="/toLoginIndex")
	public ModelAndView toIndex(){
		ModelAndView view = new ModelAndView();
		view.setViewName("index");
		return view;
	}
	
	 /**
     * 注册跳转
     * @return
     */
    @RequestMapping(value="/toRegister")
    public ModelAndView toBackLoginPage(){
    	ModelAndView mav=new ModelAndView();
    	mav.setViewName("register");
    	return mav;
    }

	/**
	 * 注册
	 * @param loginUser
	 * @param identifyingCode 验证码
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/register")
	@ResponseBody
	public OperationDto register(LoginUser loginUser, String password2, String identifyingCode, String mobileCode, HttpServletRequest request, HttpServletResponse response){
		return this.loginService.register(loginUser, password2, identifyingCode, mobileCode, request, response);
	}
	
	/**
	 * 手机获取验证码
	 * @param phone 手机号
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/sendMobileCode")
	@ResponseBody
	public OperationDto sendMobileCode(String phone, HttpSession session){
		return this.loginService.sendMobileCode(phone, session);
	}
	/**
	 * 登录
	 * @param userName
	 * @param password
	 * @param identifyingCode
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/login")
	@ResponseBody
	public OperationDto login(String userName, String password, String identifyingCode, HttpServletRequest request){
		return this.loginService.login(userName, password, identifyingCode,request);
	}
	/**
     * 跳转至首页
     * @param bizSource
     * @return
     */
	@RequestMapping(value="/toIndex")
	public ModelAndView toIndex(Integer bizSource){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("main");
		return mav;
	}
	/**
	 * 邮箱激活
	 * @param id
	 * @param checkCode
	 * @param response
	 */
	@RequestMapping("/activate")
	public void activate(String id,String checkCode,HttpServletResponse response) {
		this.loginService.activate(id, checkCode, response);
	}
	
	/**
	 * 获取所有部门
	 * @return
	 */
	@RequestMapping("/getDepartment")
	@ResponseBody
	public List<Department> getDepartment(){
		return this.loginService.getDepartment();
	}
	
	/**
	 * 获取岗位信息
	 * @param depId
	 * @return
	 */
	@RequestMapping("/getPost")
	@ResponseBody
	public List<Post> getPost(int depId){
		return this.loginService.getPost(depId);
	}
	 /**
     * 注册跳转
     * @return
     */
    @RequestMapping(value="/toMain")
    public ModelAndView toMain(){
    	ModelAndView mav=new ModelAndView();
    	mav.setViewName("/dailyMange/manage");
    	return mav;
    }
}
