package com.seda.dailyReport.dao;

import com.seda.dailyReport.model.PerformanceResult;
import com.seda.dailyReport.model.PerformanceResultExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PerformanceResultMapper {
    long countByExample(PerformanceResultExample example);

    int deleteByExample(PerformanceResultExample example);

    int deleteByPrimaryKey(String id);

    int insert(PerformanceResult record);

    int insertSelective(PerformanceResult record);

    List<PerformanceResult> selectByExample(PerformanceResultExample example);

    PerformanceResult selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PerformanceResult record, @Param("example") PerformanceResultExample example);

    int updateByExample(@Param("record") PerformanceResult record, @Param("example") PerformanceResultExample example);

    int updateByPrimaryKeySelective(PerformanceResult record);

    int updateByPrimaryKey(PerformanceResult record);
}