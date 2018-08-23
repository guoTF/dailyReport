/*
 *  Copyright 2009-2012 Evun Technology.
 *  This software is the confidential and proprietary information of Evun Technology.
 *   ("Confidential Information").  You shall not disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into with evun.cn.
 *
 */

package com.seda.dailyReport.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @Date 2018年2月26日
 */
public class AjaxUtils {

	public static boolean isAjaxRequest(HttpServletRequest request) {
		String requestedWith = request.getHeader("X-Requested-With");
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
	}

	public static boolean isAjaxUploadRequest(HttpServletRequest request) {
		if (request.getContentType() != null && request.getContentType().contains("multipart/form-data")) {
			return true;
		}
		return false;
	}

	public static void sessionOut(HttpServletRequest request, HttpServletResponse response) {
		    response.setHeader("sessionstatus", "timeout");//在响应头设置session状态
            try {
				response.getWriter().print("timeout");//打印一个返回值，没这一行，在tabs页中无法跳出（导航栏能跳出），具体原因不明
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	private AjaxUtils() {
	}

}
