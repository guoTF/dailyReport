package com.seda.dailyReport.util;

import java.util.UUID;

/**
 * 主键id生成工具类
 * @author 郭腾飞 20180626
 *
 */
public class CreatePrimaryKeyUtils {

	/**
	 * 生成主键id
	 * @return 前面使用UUID，后面12位使用yyyyMMddHHmmss
	 */
	public static String createPrimaryKey(){
		UUID uuid = UUID.randomUUID();
		String uuidStr = uuid.toString();
		String format = DateUtils.format(DateUtils.LONG_PURE_DIGITAL_PATTERN);
		String substring = uuidStr.substring(0, uuidStr.length()-12);
		String key = substring + format.substring(2);
		return key;
	}
	
	public static void main(String[] args) {
		UUID uuid = UUID.randomUUID();
		String uuidStr = uuid.toString();
		String format = DateUtils.format(DateUtils.LONG_PURE_DIGITAL_PATTERN);
		String substring = uuidStr.substring(0, uuidStr.length()-12);
		String key = substring + format.substring(2);
		System.out.println(key);
	}
}
