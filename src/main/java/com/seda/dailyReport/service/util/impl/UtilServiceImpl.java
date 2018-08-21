package com.seda.dailyReport.service.util.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.seda.dailyReport.dao.UserMapper;
import com.seda.dailyReport.model.User;
import com.seda.dailyReport.model.vo.UserExcelVo;
import com.seda.dailyReport.service.util.UtilService;

/**
 * 公用service实现类
 * @author admin
 *
 */
@Service
public class UtilServiceImpl implements UtilService {
	
	@Resource
	private UserMapper userMapper;

	/**
	 * 查询需要导出的user对象
	 */
	@Override
	public List<UserExcelVo> getUserExcelVo(UserExcelVo userExcelVo) {
		List<UserExcelVo> uevList = this.userMapper.queryUserExcelVo(userExcelVo);
		return uevList;
	}

	/**
	 * 需要导出word模板的数据
	 */
	@Override
	public Map<String, Object> getDataMap(Integer id) {
		User user = this.userMapper.selectByPrimaryKey(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", user.getUserName());
		map.put("age", user.getAge());
		map.put("phone", user.getPhone());
		return map;
	}

}
