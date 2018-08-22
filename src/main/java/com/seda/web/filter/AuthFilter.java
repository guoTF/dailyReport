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

public class AuthFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resq = (HttpServletResponse) response;
		String requestURI = req.getRequestURI();
		String userID = (String) req.getSession().getAttribute("userID");
		if (!requestURI.contains("/login/toIndex")) {
			if (StringUtils.isBlank(userID)) {
				resq.sendRedirect("/index.jsp");
			}
		}
	}

	@Override
	public void destroy() {
		
	}

}
