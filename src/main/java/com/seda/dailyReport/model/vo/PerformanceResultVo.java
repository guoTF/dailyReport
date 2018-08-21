package com.seda.dailyReport.model.vo;

import java.util.List;

import com.seda.dailyReport.model.PerformanceAppraisal;
import com.seda.dailyReport.model.PerformanceResult;

/**
 * 绩效考核评分页面Vo类
 * @author gtf
 *
 */
public class PerformanceResultVo {
	
	private List<PerformanceResult> performanceResultList;
	
	private List<PerformanceAppraisal> performanceAppraisalList;

	public List<PerformanceResult> getPerformanceResultList() {
		return performanceResultList;
	}

	public void setPerformanceResultList(List<PerformanceResult> performanceResultList) {
		this.performanceResultList = performanceResultList;
	}

	public List<PerformanceAppraisal> getPerformanceAppraisalList() {
		return performanceAppraisalList;
	}

	public void setPerformanceAppraisalList(List<PerformanceAppraisal> performanceAppraisalList) {
		this.performanceAppraisalList = performanceAppraisalList;
	}

}
