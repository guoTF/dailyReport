package com.seda.dailyReport.service.util;

import java.util.List;
import java.util.Map;

import com.seda.dailyReport.model.vo.UserExcelVo;

/**
 * 公用service类
 * @author admin
 *
 */
public interface UtilService {

	/**
	 * 查询需要导出的user对象
	 * @param userExcelVo
	 * @return
	 */
	List<UserExcelVo> getUserExcelVo(UserExcelVo userExcelVo);

	/**
	 * 需要导出word模板的数据
	 * @param id
	 * @return
	 */
	Map<String, Object> getDataMap(Integer id);

}
