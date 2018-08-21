package com.seda.dailyReport.dao;

import com.seda.dailyReport.model.TaskClassification;
import com.seda.dailyReport.model.TaskClassificationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskClassificationMapper {
    long countByExample(TaskClassificationExample example);

    int deleteByExample(TaskClassificationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TaskClassification record);

    int insertSelective(TaskClassification record);

    List<TaskClassification> selectByExample(TaskClassificationExample example);

    TaskClassification selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TaskClassification record, @Param("example") TaskClassificationExample example);

    int updateByExample(@Param("record") TaskClassification record, @Param("example") TaskClassificationExample example);

    int updateByPrimaryKeySelective(TaskClassification record);

    int updateByPrimaryKey(TaskClassification record);
}