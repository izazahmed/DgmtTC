/**
 * Created Date: 4/15/10 7:34 PM
 * Class Name  : BaseAction.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>	<Author>	<Date>	<Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.dgmt.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dgmt.service.api.CandidateEnrollmentService;
import com.dgmt.service.api.CandidateExaminationService;
import com.dgmt.service.api.CandidateService;
import com.dgmt.service.api.OleExamService;
import com.dgmt.service.api.UserManagementService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:34 PM
 * @author
 * @since DGMT 1.0
 */
public class BaseAction extends ActionSupport implements ServletContextAware,
		ServletRequestAware, ServletResponseAware
{
	/**
	 * DOCUMENT ME!
	 */
	protected ApplicationContext ctx = null;

	/**
	 * DOCUMENT ME!
	 */
	protected ServletContext servletContext = null;

	/**
	 * DOCUMENT ME!
	 */
	protected HttpServletRequest request = null;

	/**
	 * DOCUMENT ME!
	 */
	protected HttpServletResponse response = null;

	/**
	 * @return
	 * @throws Exception
	 */
	@Override
	public String execute() throws Exception
	{
		return super.execute();
	}

	/**
	 * @param name
	 * @return
	 */
	public Object getBean(String name)
	{
		if (ctx == null)
		{
			ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext(servletContext);
		}

		return ctx.getBean(name);
	}

	/**
	 * @param context
	 */
	@Override
	public void setServletContext(ServletContext context)
	{
		this.servletContext = context;
	}
	
	/**
	 * @param context
	 */
	public ServletContext getServletContext()
	{
		return this.servletContext;
	}
	
	/**
	 * @param request
	 */
	@Override
	public void setServletRequest(HttpServletRequest request)
	{
		this.request = request;
	}

	/**
	 * @param response
	 */
	@Override
	public void setServletResponse(HttpServletResponse response)
	{
		this.response = response;
		this.response.setHeader("Cache-Control",
				"PUBLIC, max-age=1, must-revalidate");
		this.response.setHeader("Cache-Control", "no-store, no-cache");
		this.response.setHeader("Pragma", "no-cache");
		this.response.setDateHeader("Expires", 0);
	}

	/**
	 * @return
	 */
	protected HttpSession getSession()
	{
		return request.getSession();
	}

	/**
	 * @return
	 */
	protected OleExamService getOleExamService()
	{
		return (OleExamService) getBean("oleExamService");
	}

	/**
	 * @return
	 */
	protected CandidateService getCandidateService()
	{
		return (CandidateService) getBean("candidateService");
	}

	/**
	 * @return
	 */
	protected CandidateExaminationService getCandidateExaminationService()
	{
		return (CandidateExaminationService) getBean("candidateExaminationService");
	}

	/**
	 * Returns Candidate Enrollment Service.
	 * 
	 * @return
	 */
	protected CandidateEnrollmentService getCandidateEnrollmentService()
	{
		return (CandidateEnrollmentService) getBean("candidateEnrllmentService");
	}

	/**
	 * @return
	 */
	protected UserManagementService getUserManagementService()
	{
		return (UserManagementService) getBean("userManagementService");
	}

}
