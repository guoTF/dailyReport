package com.seda.dailyReport.service.dailyQuery.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seda.dailyReport.dao.DailyPlanMapper;
import com.seda.dailyReport.dao.DailyReportMapper;
import com.seda.dailyReport.model.vo.DailyPlanVo;
import com.seda.dailyReport.model.vo.DailyReportVo;
import com.seda.dailyReport.service.dailyQuery.DailyQueryService;

/**
 * 个人日志查询service实现类
 * @author 郭腾飞 20180627
 *
 */
@Service
public class DailyQueryServiceImpl implements DailyQueryService {
	
	@Resource
	private  DailyPlanMapper dailyPlanMapper;
	
	@Resource
	private DailyReportMapper dailyReportMapper;

	/**
	 * 个人工作计划查询
	 */
	@Override
	public PageInfo<DailyPlanVo> queryPlanList(DailyPlanVo planVo, HttpServletRequest request) {
		PageHelper.startPage(planVo.getPageNo(), planVo.getPageSize());
		String userId = (String) request.getSession().getAttribute("userID");
		planVo.setUserId(userId);
		List<DailyPlanVo> planVoList = this.dailyPlanMapper.queryPlanList(planVo);
		return new PageInfo<DailyPlanVo>(planVoList);
	}

	/**
	 * 个人工作日志查询
	 */
	@Override
	public PageInfo<DailyReportVo> queryReportList(DailyReportVo reportVo, HttpServletRequest request) {
		PageHelper.startPage(reportVo.getPageNo(), reportVo.getPageSize());
		String userId = (String) request.getSession().getAttribute("userID");
		reportVo.setUserId(userId);
		List<DailyReportVo> reportVoList = this.dailyReportMapper.queryReportList(reportVo);
		return new PageInfo<DailyReportVo>(reportVoList);
	}

}
