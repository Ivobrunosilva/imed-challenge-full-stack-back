package com.imed.challenge.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.imed.challenge.util.JwtUtil;

public class JwtFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String jwt = null;
		String content = req.getHeader(HttpHeaders.AUTHORIZATION);
		if (StringUtils.isNotBlank(content) && content.startsWith("Bearer ")) {
			jwt = content.substring(7);
		}
		if (StringUtils.isNotBlank(jwt) && JwtUtil.isValid(jwt)) {
			SecurityContextHolder.getContext().setAuthentication(JwtUtil.getAuth(jwt));
		}
		chain.doFilter(request, response);
	}

}
