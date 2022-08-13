/**
 * Created Date: 6/22/10 2:14 PM
 * Class Name  : SessionTimeOutInterceptor.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>	<Author>	<Date>	<Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.dgmt.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.dgmt.util.DGMTProperties;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * Description:
 * 
 * @version 1.0 6/22/10 2:14 PM
 * @author CTE
 * @since DGMT 1.0
 */
public class SessionTimeOutInterceptor implements Interceptor
{
	/**
         *
         */
	@Override
	public void destroy()
	{
	}

	/**
         *
         */
	@Override
	public void init()
	{
	}

	/**
	 * @param arg0
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String isAjaxReq = request.getHeader("X-Requested-With");
		String isFpwd = request.getParameter("isFpwd");
		HttpSession session = request.getSession();

		String loginId = null;
		
		
		if(isFpwd!=null)
		{
			isFpwd=null;
		}
		
        String status = (String)request.getSession().getAttribute("statusval");
        
        if("invalidData".equalsIgnoreCase(status))
        {
        	request.getSession().removeAttribute("statusval");
        	return "vaidatorError";
        }
		if (!"XMLHttpRequest".equalsIgnoreCase(isAjaxReq)) {
			try {
				loginId = request.getUserPrincipal().getName();

			} catch (Exception e) {

				if(loginId == null && isFpwd != null )
				{
					session.setAttribute("sessionExp", "true");
					return "sessionExpired";
				} 
				else if((request.getParameter("j_username") == null || request.getParameter("j_password") == null) && request.getParameter("user") == null)
				{
					//session.removeAttribute("failed"); 
					return "sessionExpired";
				}
			}

		} else if ("XMLHttpRequest".equalsIgnoreCase(isAjaxReq)) {
			try {
				loginId = request.getUserPrincipal().getName();

			} catch (Exception e) {
				
				if(loginId == null && isFpwd != null)
				{
					session.setAttribute("sessionExp", "true");
					response.getWriter().write("SessionExpired");
					return null;
				}
			}

		}

		
		if (session.isNew())
		{
			if (!"XMLHttpRequest".equalsIgnoreCase(isAjaxReq))
			{
				session.setAttribute("sessionExp", "true");
				return "sessionExpired";
			}
			else if ("XMLHttpRequest".equalsIgnoreCase(isAjaxReq))
			{
				session.setAttribute("sessionExp", "true");
				response.getWriter().write("SessionExpired");
				return null;
			}

		}	
        String actHostname = "";
		
		if(DGMTProperties.getProperties().getProperty("hostname")==null)
		{
			actHostname = "";//request.getServerName();
		}
		else actHostname = DGMTProperties.getProperties().getProperty("hostname");
		
		String hostname = request.getServerName();
		
		if(!actHostname.equalsIgnoreCase(hostname))
    	{
			return "vaidatorError";
        }
		return invocation.invoke();
	}
}
