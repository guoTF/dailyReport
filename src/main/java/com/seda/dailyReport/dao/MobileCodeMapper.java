package com.seda.dailyReport.dao;

import com.seda.dailyReport.model.MobileCode;
import com.seda.dailyReport.model.MobileCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MobileCodeMapper {
    long countByExample(MobileCodeExample example);

    int deleteByExample(MobileCodeExample example);

    int deleteByPrimaryKey(String id);

    int insert(MobileCode record);

    int insertSelective(MobileCode record);

    List<MobileCode> selectByExample(MobileCodeExample example);

    MobileCode selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MobileCode record, @Param("example") MobileCodeExample example);

    int updateByExample(@Param("record") MobileCode record, @Param("example") MobileCodeExample example);

    int updateByPrimaryKeySelective(MobileCode record);

    int updateByPrimaryKey(MobileCode record);
}