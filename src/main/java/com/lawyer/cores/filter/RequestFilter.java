package com.lawyer.cores.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestFilter implements Filter {
	private static Logger logger = LoggerFactory.getLogger(RequestFilter.class);
    
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * 请求过滤
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
	    HttpServletRequest rqt = (HttpServletRequest) request;
	      
	    String requestURI=rqt.getRequestURI();
	    logger.info(rqt.getScheme()+"://"+rqt.getServerName()+":"+rqt.getServerPort()+requestURI);
	    
        chain.doFilter(request,response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
