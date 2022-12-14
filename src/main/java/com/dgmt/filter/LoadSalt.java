package com.dgmt.filter;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class LoadSalt implements Filter {
	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        // Assume its HTTP
        HttpServletRequest httpReq = (HttpServletRequest) request;

        HttpServletResponse httpResp = (HttpServletResponse) response;
        // Check the user session for the salt cache, if none is present we create one
        Cache<String, Boolean> csrfPreventionSaltCache = (Cache<String, Boolean>)
            httpReq.getSession().getAttribute("csrfPreventionSaltCache");

        if (csrfPreventionSaltCache == null){
            csrfPreventionSaltCache = CacheBuilder.newBuilder()
                .maximumSize(5000)
                .expireAfterWrite(20, TimeUnit.MINUTES)
                .build();

            httpReq.getSession().setAttribute("csrfPreventionSaltCache", csrfPreventionSaltCache);  
        }

        // Generate the salt and store it in the users cache
        String salt = RandomStringUtils.random(20, 0, 0, true, true, null, new SecureRandom());
        csrfPreventionSaltCache.put(salt, Boolean.TRUE);

        // Add the salt to the current request so it can be used
        // by the page rendered in this request
        httpReq.setAttribute("csrfPreventionSalt", salt);
        
        //httpResp.setContentType("application/json");
        //httpResp.setHeader("X-Content-Type-Options", "nosniff");
        //because of above two application properly not working in chrome
        
        httpResp.setHeader("X-Frame-Options", "SAMEORIGIN"); //ALLOW-FROM https://mt2onlineexam.army.mil:7002/
        httpResp.setHeader("X-XSS-Protection", "1; mode=block");
        
        chain.doFilter(request, response);
    }
	
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
