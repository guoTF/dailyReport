package com.seda.dailyReport.dao;

import com.seda.dailyReport.model.PerformanceAppraisal;
import com.seda.dailyReport.model.PerformanceAppraisalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PerformanceAppraisalMapper {
    long countByExample(PerformanceAppraisalExample example);

    int deleteByExample(PerformanceAppraisalExample example);

    int deleteByPrimaryKey(String id);

    int insert(PerformanceAppraisal record);

    int insertSelective(PerformanceAppraisal record);

    List<PerformanceAppraisal> selectByExample(PerformanceAppraisalExample example);

    PerformanceAppraisal selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PerformanceAppraisal record, @Param("example") PerformanceAppraisalExample example);

    int updateByExample(@Param("record") PerformanceAppraisal record, @Param("example") PerformanceAppraisalExample example);

    int updateByPrimaryKeySelective(PerformanceAppraisal record);

    int updateByPrimaryKey(PerformanceAppraisal record);
}