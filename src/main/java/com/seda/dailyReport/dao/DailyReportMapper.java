package com.seda.dailyReport.dao;

import com.seda.dailyReport.model.DailyReport;
import com.seda.dailyReport.model.DailyReportExample;
import com.seda.dailyReport.model.vo.DailyReportVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DailyReportMapper {
    long countByExample(DailyReportExample example);

    int deleteByExample(DailyReportExample example);

    int deleteByPrimaryKey(String id);

    int insert(DailyReport record);

    int insertSelective(DailyReport record);

    List<DailyReport> selectByExample(DailyReportExample example);

    DailyReport selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DailyReport record, @Param("example") DailyReportExample example);

    int updateByExample(@Param("record") DailyReport record, @Param("example") DailyReportExample example);

    int updateByPrimaryKeySelective(DailyReport record);

    int updateByPrimaryKey(DailyReport record);

    /**
     * 查询个人日志列表信息
     * @param reportVo
     * @return
     */
	List<DailyReportVo> queryReportList(DailyReportVo reportVo);

	/**
	 * 查询项目组工作日志列表信息
	 * @param reportVo
	 * @return
	 */
	List<DailyReportVo> getReportALLList(DailyReportVo reportVo);
}