package com.seda.dailyReport.control.dailyPlan;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.seda.dailyReport.model.DailyPlan;
import com.seda.dailyReport.model.dto.OperationDto;
import com.seda.dailyReport.service.dailyPlan.DailyPlanService;

/**
 * 日工作计划Controller
 * @author 郭腾飞 20180626
 *
 */
@Controller
@RequestMapping("/dailyPlan")
public class DailyPlanController {

	@Resource
	private DailyPlanService dailyPlanService;
	
	/**
	 * 列表显示所选计划日的计划
	 * @param day
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getPlan")
	@ResponseBody
	public List<DailyPlan> getPlan(String day, HttpServletRequest request){
		return this.dailyPlanService.getPlan(day, request);
	}
	
	/**
	 * 保存日工作计划
	 * @param planList
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/savePlan")
	@ResponseBody
	public OperationDto savePlan(String planStr, HttpServletRequest request){
		return this.dailyPlanService.savePlan(planStr, request);
	}
	
	/**
	 * 删除一条日计划
	 * @param planId
	 * @return
	 */
	@RequestMapping(value="/deleteOnePlan")
	@ResponseBody
	public OperationDto deleteOnePlan(String planId){
		return this.dailyPlanService.deleteOnePlan(planId);
	}
	 /**
     * 工作计划页面跳转
     * @return
     */
    @RequestMapping(value="/togetPlan")
    public ModelAndView togetPlan(){
    	ModelAndView mav=new ModelAndView();
    	mav.setViewName("/dailyMange/getPlan");
    	return mav;
    }
    /**
     * 工作日志填写
     * @return
     */
    @RequestMapping(value="/togetReportList")
    public ModelAndView togetReportList(){
    	ModelAndView mav=new ModelAndView();
    	mav.setViewName("/dailyMange/getReportList");
    	return mav;
    }
    /**
     * 项目成员日志查询
     * @return
     */
    @RequestMapping(value="/toqueryPlanALLList")
    public ModelAndView toqueryPlanALLList(){
    	ModelAndView mav=new ModelAndView();
    	mav.setViewName("/dailyMange/queryPlanALLList");
    	return mav;
    }
    /**
     * 个人日志查询
     * @return
     */
    @RequestMapping(value="/toqueryPlanList")
    public ModelAndView toqueryPlanList(){
    	ModelAndView mav=new ModelAndView();
    	mav.setViewName("/dailyMange/queryPlanList");
    	return mav;
    }
}
