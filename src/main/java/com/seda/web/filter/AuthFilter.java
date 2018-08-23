package com.seda.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.seda.dailyReport.model.dto.OperationDto;
import com.seda.dailyReport.util.AjaxUtils;

import net.sf.json.JSONObject;

public class AuthFilter implements Filter{

	private String[] allowUrls;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		allowUrls = filterConfig.getInitParameter("allowUrls").split(";");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resq = (HttpServletResponse) response;
		String requestUri = req.getRequestURI();
		String userID = (String) req.getSession().getAttribute("userID");
		for (String allowUrl : allowUrls) {
			if(requestUri.contains("/login/toIndex")){
				if(StringUtils.isBlank(userID)){
					resq.sendRedirect(req.getContextPath());
					return;
				}
			}
			if (requestUri.contains(allowUrl)) {
				chain.doFilter(req, resq);
				return;
			}
		}
		if(StringUtils.isBlank(userID)) {
			if (AjaxUtils.isAjaxRequest(req) || AjaxUtils.isAjaxUploadRequest(req)) {
				resq.setHeader("sessionstatus", "timeout");
				OperationDto od=new OperationDto();
				JSONObject json = JSONObject.fromObject(od);
				response.getWriter().print(json.toString());
				return;
			}
			chain.doFilter(req, resq);
			return;
		}
		chain.doFilter(req, resq);
		return;
	}

	@Override
	public void destroy() {
		
	}

}
