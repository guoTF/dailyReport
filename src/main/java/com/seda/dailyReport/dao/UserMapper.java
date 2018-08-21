package com.seda.dailyReport.dao;

import com.seda.dailyReport.model.User;
import com.seda.dailyReport.model.UserExample;
import com.seda.dailyReport.model.vo.UserExcelVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 查询需要导出的Excel对象
     * @param userExcelVo
     * @return
     */
	List<UserExcelVo> queryUserExcelVo(UserExcelVo userExcelVo);
}