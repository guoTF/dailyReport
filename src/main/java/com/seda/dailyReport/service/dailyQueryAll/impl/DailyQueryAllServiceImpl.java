package com.seda.dailyReport.service.dailyQueryAll.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seda.dailyReport.dao.DailyPlanMapper;
import com.seda.dailyReport.dao.DailyReportMapper;
import com.seda.dailyReport.dao.LoginUserMapper;
import com.seda.dailyReport.model.LoginUser;
import com.seda.dailyReport.model.vo.DailyPlanVo;
import com.seda.dailyReport.model.vo.DailyReportVo;
import com.seda.dailyReport.service.dailyQueryAll.DailyQueryAllService;

/**
 * 查询项目组成员工作计划及日志service实现类
 * @author 郭腾飞 20180628
 *
 */
@Service
public class DailyQueryAllServiceImpl implements DailyQueryAllService {
	
	@Resource
	private DailyPlanMapper dailyPlanMapper;
	
	@Resource
	private DailyReportMapper dailyReportMapper;
	
	@Resource
	private LoginUserMapper loginUserMapper;

	/**
	 * 查询项目组工作日志
	 */
	@Override
	public PageInfo<DailyPlanVo> getPlanALLList(DailyPlanVo planVo, HttpServletRequest request) {
		PageHelper.startPage(planVo.getPageNo(), planVo.getPageSize());
		List<DailyPlanVo> planVoList = this.dailyPlanMapper.getPlanALLList(planVo);
		if (CollectionUtils.isNotEmpty(planVoList)) {
			for (int i = 0; i < planVoList.size(); i++) {
				DailyPlanVo vo = planVoList.get(i);
				String userId = vo.getUserId();
				LoginUser loginUser = this.loginUserMapper.selectByPrimaryKey(userId);
				String userName = loginUser.getUserName();
				vo.setUserName(userName);
				planVoList.set(i, vo);
			}
		}
		return new PageInfo<DailyPlanVo>(planVoList);
	}

	/**
	 * 查询项目组成员工作日志
	 */
	@Override
	public PageInfo<DailyReportVo> getReportALLList(DailyReportVo reportVo, HttpServletRequest request) {
		PageHelper.startPage(reportVo.getPageNo(), reportVo.getPageSize());
		List<DailyReportVo> reportVoList = this.dailyReportMapper.getReportALLList(reportVo);
		if (CollectionUtils.isNotEmpty(reportVoList)) {
			for (int i = 0; i < reportVoList.size(); i++) {
				DailyReportVo vo = reportVoList.get(i);
				String userId = vo.getUserId();
				LoginUser loginUser = this.loginUserMapper.selectByPrimaryKey(userId);
				String userName = loginUser.getUserName();
				vo.setUserName(userName);
				reportVoList.set(i, vo);
			}
		}
		return new PageInfo<DailyReportVo>(reportVoList);
	}

}
