/*
 * Copyright 2009-2012 Evun Technology.
 *
 * This software is the confidential and proprietary information of
 * Evun Technology. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with evun.cn.
 */
package com.seda.dailyReport.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author 张纯奎
 * @created 2012-12-19 上午9:49:48
 * @since v1.3.1
 */
public class DateUtils {

	public static final String LONG_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public static final String CHINA_MINITE_DATE_PATTERN = "yyyy年MM月dd日  HH:mm";

	public static final String MAIL_CHINA_MINITE_DATE_PATTERN = "yyyy年MM月dd日 HH:mm (EEE)";

	public static final String SHORT_DATE_PATTERN = "yyyy-MM-dd";

	public static final String CHINA_MONTH_DAY_DATE_PATTERN = "MM月dd日";

	public static final String CHINA_MONTH_DATE_PATTERN = "MM月dd日  HH:mm";

	public static final String HOUR_MINITE_PATTERN = "HH:mm";

	public static final String SECOND_PATTERN = "HH:mm:ss";

	public static final String MINITE_DATE_PATTERN = "yyyy-MM-dd HH:mm";

	public static final String LONG_PURE_DIGITAL_PATTERN = "yyyyMMddHHmmss";

	public static final int MINITE_TYPE = 0;
	public static final int HOUR_TYPE = 1;
	public static final int DAY_TYPE = 2;
	public static final int MONTH_TYPE = 3;
	public static final int YEAR_TYPE = 4;

	private static SimpleDateFormat sdf = new SimpleDateFormat(SHORT_DATE_PATTERN);

	/**
     * <p>
     * Description: 长日期格式：2012-12-19 13:00:00
     * </p>
     *
     * @return String
     * @author 张纯奎
     * @created 2012-12-19 上午9:48:17
     * @since v1.3.1
     */
	public static String formatTime(Date date) {
		if (null == date) {
			return StringUtils.EMPTY_STRING;
		}
		DateTime dateTime = new DateTime(date);
		return dateTime.toString(LONG_DATE_PATTERN);
	}

	public static String formatTime1(Date date) {
		if (null == date) {
			return StringUtils.EMPTY_STRING;
		}
		DateTime dateTime = new DateTime(date);
		return dateTime.toString(SECOND_PATTERN);
	}

	/**
     * <p>
     * Description: 短日期格式：2012-12-19
     * </p>
     *
     * @return String
     * @author 张纯奎
     * @created 2012-12-19 上午9:48:53
     * @since v1.3.1
     */
	public static String formatDate(Date date) {
		if (null == date) {
			return StringUtils.EMPTY_STRING;
		}
		DateTime dateTime = new DateTime(date);
		return dateTime.toString(SHORT_DATE_PATTERN);
	}

	/**
     * <p>
     * Description: 指定日期模式格式化
     * </p>
     *
     * @return String
     * @author 张纯奎
     * @created 2012-12-19 上午9:48:17
     * @since v1.3.1
     */
	public static String format(Date date, String pattern) {
		if (null == date) {
			return "";
		}
		DateTime dateTime = new DateTime(date);
		return dateTime.toString(pattern);
	}

	/**
     * <p>
     * Description: 指定日期模式格式化
     * </p>
     *
     * @return String
     * @author 张纯奎
     * @created 2012-12-19 上午9:48:17
     * @since v1.3.1
     */
	public static String format(String pattern) {
		return format(new Date(), pattern);
	}

	public static String getWeek(Date date) {
		DateTime dateTime = new DateTime(date);
		return dateTime.dayOfWeek().getAsText();
	}

	public static int getYear(Date date) {
		DateTime dateTime = new DateTime(date);
		return dateTime.getYear();
	}

	/**
     * <p>
     * Description: 得到当前年数
     * </p>
     *
     * @author 张纯奎
     * @created 2013-1-7 下午5:47:18
     * @since v1.3.1
     */
	public static int getCurrentYear() {
		DateTime dateTime = new DateTime();
		return dateTime.getYear();
	}

	public static Date parseDate(String source) {
		DateTime dateTime = new DateTime(source);
		return dateTime.toDate();
	}

	public static Date parseDate(String source, String pattern) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
		return fmt.parseDateTime(source).toDate();
	}

	public static Date parseTime(String source) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern(LONG_DATE_PATTERN);
		return fmt.parseDateTime(source).toDate();
	}

	public static Date addMinutes(Date lockTime, Integer lockPeriod) {
		DateTime dateTime = new DateTime(lockTime);
		return dateTime.plusMinutes(lockPeriod).toDate();
	}

	public static Date addDays(Date pswBeainTime, Integer pswLife) {
		DateTime dateTime = new DateTime(pswBeainTime);
		return dateTime.plusDays(pswLife).toDate();
	}

	public static Date minusDays(Date pswBeainTime, Integer pswLife) {
		DateTime dateTime = new DateTime(pswBeainTime);
		return dateTime.minusDays(pswLife).toDate();
	}

	public static Date plusYears(Date pswBeainTime, Integer pswLife) {
		DateTime dateTime = new DateTime(pswBeainTime);
		return dateTime.plusYears(pswLife).toDate();
	}

	public static Date minusHours(Date pswBeainTime, Integer pswLife) {
		DateTime dateTime = new DateTime(pswBeainTime);
		return dateTime.minusHours(pswLife).toDate();
	}

	/**
     * 类似微博发布时间的时间处理方案
     */
	public static String converTime(long timeMillis) {
		long timeGap = (System.currentTimeMillis() - timeMillis) / 1000;// 与现在时间相差秒数
		String timeStr = null;
		if (timeGap > 365 * 24 * 60 * 60) {// 1年以上
			timeStr = format(new Date(timeMillis), CHINA_MINITE_DATE_PATTERN);
		} else if (timeGap > 30 * 24 * 60 * 60) {// 1月以上
			timeStr = format(new Date(timeMillis), CHINA_MONTH_DATE_PATTERN);
		} else if (timeGap > 24 * 60 * 60) {// 1天以上
			timeStr = timeGap / (24 * 60 * 60) + "天前";
		} else if (timeGap > 60 * 60) {// 1小时-24小时
			timeStr = timeGap / (60 * 60) + "小时前";
		} else if (timeGap > 60) {// 1分钟-59分钟
			timeStr = timeGap / 60 + "分钟前";
		} else {// 1秒钟-59秒钟
			timeStr = "刚刚";
		}
		return timeStr;
	}

	public static String converTime2(Date date){
		Calendar cal = parseDateTime(new Date());
		GregorianCalendar today = new GregorianCalendar(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH),0,0,0);
		GregorianCalendar yesterday = new GregorianCalendar(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)-1,0,0,0);

		String timeStr = null;
		String d = formatDate(date);
		String time = format(date, HOUR_MINITE_PATTERN);
		String todayStr = formatDate(today.getTime());
		String yesterdayStr = formatDate(yesterday.getTime());

		if(d.equals(todayStr)){
			timeStr = "今天 "+time;
		}else if(d.equals(yesterdayStr)){
			timeStr = "昨天 "+time;
		}else{
			timeStr = format(date, CHINA_MONTH_DATE_PATTERN);
		}
		return timeStr;
	}

	/**
     * <p>
     * Description: 上周的周日
     * </p>
     *
     * @return Date
     * @author 张纯奎
     * @created 2014-2-27 下午7:45:00
     * @since v1.3.1
     */
	@SuppressWarnings("deprecation")
	public static Date getPreviousWeekSunday() {
		int weeks = -1;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(5, mondayPlus + weeks);
		Date monday = currentDate.getTime();
		monday.setHours(23);
		monday.setMinutes(59);
		monday.setSeconds(59);
		return monday;
	}

	/**
     * <p>
     * Description: 上周的周一
     * </p>
     *
     * @return Date
     * @author 张纯奎
     * @created 2014-2-27 下午7:44:49
     * @since v1.3.1
     */
	@SuppressWarnings("deprecation")
	public static Date getPreviousWeekMonday() {
		int weeks = -1;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(5, mondayPlus + 7 * weeks);
		Date monday = currentDate.getTime();
		monday.setHours(0);
		monday.setMinutes(0);
		monday.setSeconds(0);
		return monday;
	}


	private static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		int dayOfWeek = cd.get(7) - 1;
		if (dayOfWeek == 1) {
			return 0;
		}
		return (1 - dayOfWeek);
	}

	/**
     * <p>
     * Description: 获取本周的日期
     * </p>
     *
     * @return Date
     * @author 张纯奎
     * @created 2014-6-10 下午4:31:43
     * @since v1.3.1
     */
	public static Date getCurrentWeekDay(int day) {
		return getWeekDay(new Date(),day);
	}

	/**
     * <p>
     * Description: 获取本周的日期
     * </p>
     *
     * @return Date
     * @author 张纯奎
     * @created 2014-6-10 下午4:31:43
     * @since v1.3.1
     */
	public static Date getWeekDay(Date d,int day) {
		Calendar cal = parseDateTime(d);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.set(Calendar.DAY_OF_WEEK,day+1);
		cal.set(Calendar.HOUR_OF_DAY,0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 1);
		return cal.getTime();
	}



	/**
     * <p>
     * Description: 获取天时间点
     * </p>
     *
     * @return Date
     * @author 张纯奎
     * @created 2014-6-10 上午10:27:43
     * @since v1.3.1
     */
	public static Date getDayTime(Date d,int hour,int minute,int second) {
		Calendar cal = parseDateTime(d);
		cal.set(Calendar.HOUR_OF_DAY,hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		return cal.getTime();
	}

	/**
     * <p>
     * Description: 获取当天时间点
     * </p>
     *
     * @return Date
     * @author 张纯奎
     * @created 2014-6-10 上午10:27:43
     * @since v1.3.1
     */
	public static Date getTodayTime(int hour,int minute,int second) {
		return getDayTime(new Date(),hour,minute,second);
	}

	/**
     * <p>
     * Description: 获取本月的第一天
     * </p>
     *
     * @return String
     * @author 张纯奎
     * @created 2014-6-4 上午9:07:15
     * @since v1.3.1
     */
	public static String getCurrentMonthFirstDay(Date date){
		return formatDate(getMonthFirstDay(date));
	}

	/**
     * <p>
     * Description: 获取当月最后一天
     * </p>
     *
     * @return String
     * @author 张纯奎
     * @created 2014-8-4 上午8:52:39
     * @since v1.3.1
     */
	public static String getCurrentMonthLastDay(Date date){
		return formatDate(getMonthLastDay(date));
	}

	/**
     * <p>
     * Description: 获取月第一天
     * </p>
     *
     * @return Date
     * @author 张纯奎
     * @created 2014-6-10 下午5:03:14
     * @since v1.3.1
     */
	public static Date getMonthFirstDay(Date date){
		Calendar cal = parseDateTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
	    cal.set(Calendar.HOUR_OF_DAY,0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 1);
		return cal.getTime();
	}

	/**
     * <p>
     * Description: 获取月最后一天
     * </p>
     *
     * @return Date
     * @author 张纯奎
     * @created 2014-6-10 下午5:04:08
     * @since v1.3.1
     */
	public static Date getMonthLastDay(Date date){
		Calendar cal = parseDateTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	    cal.set(Calendar.HOUR_OF_DAY,0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 1);
		return cal.getTime();
	}

	/**
     * <p>
     * Description: 获取月指定的日期
     * </p>
     *
     * @return Date
     * @author 张纯奎
     * @created 2014-6-10 下午4:56:53
     * @since v1.3.1
     */
	public static Date getMonthDay(Date date,int day){
		Calendar cal = parseDateTime(date);
		cal.set(Calendar.DAY_OF_MONTH,day);
		cal.set(Calendar.HOUR_OF_DAY,0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 1);
		return cal.getTime();
	}

	/**
     * <p>
     * Description: 获取月份
     * </p>
     *
     * @param type 1月初,2月末
     * @return Date
     * @author 张纯奎
     * @created 2014-6-10 下午5:19:11
     * @since v1.3.1
     */
	public static Date getMonth(Date date,int month,int type){
		Calendar cal = parseDateTime(date);
		cal.set(Calendar.MONTH,month-1);
		if(2 == type){
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		}else{
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		}
		cal.set(Calendar.HOUR_OF_DAY,0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 1);
		return cal.getTime();
	}


	/**
     * <p>
     * Description: 计算两个日期间的差值,需要指定日期格式来转化日期
     * </p>
     *
     * @return int
     * @author 张纯奎
     * @created 2014-5-28 上午11:08:00
     * @since v1.3.1
     */
	public static int dateCal(String startdate, String enddate, String format,
			int iType) {
		return dateCal(parseDate(startdate, format),
				parseDate(enddate, format), iType);
	}

	/**
     * <p>
     * Description: 计算两个日期间的差值
     * </p>
     *
     * @return int
     * @author 张纯奎
     * @created 2014-5-28 上午11:01:15
     * @since v1.3.1
     */
	public static int dateCal(Date startdate, Date enddate, int iType) {
		Calendar calBegin = parseDateTime(startdate);
		Calendar calEnd = parseDateTime(enddate);
		long lBegin = calBegin.getTimeInMillis();
		long lEnd = calEnd.getTimeInMillis();
		int ss = (int) ((lEnd - lBegin) / 1000L);
		int min = ss / 60;
		int hour = min / 60;
		int day = hour / 24;
		if (iType == MINITE_TYPE)
			return min;
		if (iType == HOUR_TYPE)
			return hour;
		if (iType == DAY_TYPE)
			return day;
		else
			return -1;
	}

	/**
     * <p>
     * Description: 计算两个日期间的差值(得到阶梯型数据)
     * startdate - enddate
     * </p>
     *
     * @return int
     * @author 张纯奎
     * @created 2014-5-28 上午11:01:15
     * @since v1.3.1
     */
	public static long dateCal2(Date startdate, Date enddate, int iType) {
		Calendar calBegin = parseDateTime(startdate);
		Calendar calEnd = parseDateTime(enddate);
		long lBegin = calBegin.getTimeInMillis();
		long lEnd = calEnd.getTimeInMillis();
		long ss = (long) ((lEnd - lBegin) / 1000L);
		long day = ss/(24*60*60);
		long hour = (ss/(60*60)-day*24);
		long min = ((ss/(60))-day*24*60-hour*60);
		if (iType == MINITE_TYPE)
			return min;
		if (iType == HOUR_TYPE)
			return hour;
		if (iType == DAY_TYPE)
			return day;
		else
			return -1;
	}

	public static Calendar parseDateTime(Date d) {

		Calendar cal = Calendar.getInstance();
		int yy = 0, mm = 0, dd = 0, hh = 0, mi = 0, ss = 0;
		cal.setTime(d);

		yy = cal.get(Calendar.YEAR);
		mm = cal.get(Calendar.MONTH);
		dd = cal.get(Calendar.DAY_OF_MONTH);
		hh = cal.get(Calendar.HOUR_OF_DAY);
		mi = cal.get(Calendar.MINUTE);
		ss = cal.get(Calendar.SECOND);

		cal.set(yy, mm, dd, hh, mi, ss);
		return cal;
	}

	public static Date getWeekStartDay(Date date){
		return getWeekDay(date,1);
	}

	public static Date getWeekEndDay(Date date){
		Calendar cal = parseDateTime(date);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.set(Calendar.HOUR_OF_DAY,23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return cal.getTime();
	}

	public static Date getDayStart(Date date){
		Calendar cal = parseDateTime(date);
		cal.set(Calendar.HOUR_OF_DAY,0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 1);
		return cal.getTime();
	}

	public static Date getDayEnd(Date date){
		Calendar cal = parseDateTime(date);
		cal.set(Calendar.HOUR_OF_DAY,23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}


	// 获取所在月份天数
	public static int getMonthDays(Date date){
		Calendar cal = parseDateTime(date);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
     * 获取昨日日期
     */
	public static String getYesterday() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) - 1);
		return sdf.format(cal.getTime());
	}

	public static void main(String[] args) {
		//System.out.println(parseDateTime(new Date()));

//		System.out.println(getMonth(new Date(),4,2));
//		System.out.println(DateUtils.minusHours(new Date(), 2));

//		System.out.println("11:10:10".substring(0, 2));
	}

}
