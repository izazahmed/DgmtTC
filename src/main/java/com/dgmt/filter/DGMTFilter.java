package com.dgmt.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

public class DGMTFilter extends StrutsPrepareAndExecuteFilter 
{
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;        

        try {
        	if(!"/saveImage.jsp".equalsIgnoreCase(request.getServletPath()))
        	{
	            super.doFilter(req, res, chain);
        	}
        	else
        	{
        		chain.doFilter(request, response);
        	}
        } finally {
        	//prepare.cleanupRequest(request);
        	//response.setHeader("X-Content-Type-Options", "nosniff");
        	//response.setHeader("X-Frame-Options", "DENY");
        	//response.setHeader("X-XSS-Protection", "1; mode=block");
        }
    }
    
}
