/**
 * Created Date: 4/15/10 7:34 PM
 * Class Name  : DGMTContextListener.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>	<Author>	<Date>	<Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.dgmt.web;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dgmt.model.OleExam;
import com.dgmt.service.api.OleExamService;
import com.dgmt.util.DGMTUtil;
import com.dgmt.util.Log;
import com.dgmt.util.LogFactory;
import com.dgmt.web.action.CandidateExaminationAction;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:34 PM
 * @author
 * @since DGMT 1.0
 */
public class DGMTContextListener implements ServletContextListener
{
	private Log logger = LogFactory.getLog(DGMTContextListener.class);
	
	/**
	 * @param arg0
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0)
	{
		//TODO: check if below is used or required
		// unregister your listeners   
		try
		{
			ServiceLocator.getInstance().getAnsPaperListener().stop();
			ServiceLocator.getInstance().getTCDataListener().stop();
		}
		catch (Exception e)
		{
			logger.error("Exception: " + DGMTUtil.getStackTrace(e));
		}
	}

	/**
	 * @param scEvent
	 */
	@Override
	public void contextInitialized(ServletContextEvent scEvent)
	{
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(scEvent.getServletContext());

		OleExamService oleExamService = (OleExamService) ctx
				.getBean("oleExamService");
		List<OleExam> oleExams = oleExamService.getExams();
		scEvent.getServletContext().setAttribute("oleExams", oleExams);

		// add shutdown hook to unregister active session when server is forced shut down
		Runtime.getRuntime().addShutdownHook(new Thread()
		{
			public void run()
			{
				ServiceLocator.getInstance().getAnsPaperListener().stop();
				ServiceLocator.getInstance().getTCDataListener().stop();
				while (true);
			}
		});

		Runtime.getRuntime().addShutdownHook(new Thread()
		{
			public void run()
			{
				try
				{
					Thread.sleep(10000);
				}
				catch (InterruptedException ex)
				{
				}
				// halt will bail out without calling further shutdown hooks or
				// finalizers

				Runtime.getRuntime().halt(1);
			}
		});

		// Start listeners explicitly as we are setting DMLC autoStartup false
		try
		{
			ServiceLocator.getInstance().getAnsPaperListener().start();
			ServiceLocator.getInstance().getTCDataListener().start();
		}
		catch (Exception e)
		{
			logger.error("Exception: " + DGMTUtil.getStackTrace(e));
		}
	}
}
