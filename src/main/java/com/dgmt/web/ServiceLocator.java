package com.dgmt.web;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import com.dgmt.service.api.CandidateEnrollmentService;
import com.dgmt.service.api.MessagingService;

public class ServiceLocator implements ApplicationContextAware
{
	private static ServiceLocator locator = null; 
	
	/**
	 * DOCUMENT ME!
	 */
	protected static ApplicationContext ctx = null;
	
	/**
	 * @param context
	 */
	@Override
	public void setApplicationContext(ApplicationContext context)
	{
		this.ctx = context;
	}
	
	/**
	 * @param name
	 * @return
	 */
	public Object getBean(String name)
	{
		if (ctx == null)
		{
			ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		}

		return ctx.getBean(name);
	}
	
	/**
	 * @return
	 */
	public CandidateEnrollmentService getCandidateEnrollmentService()
	{
		return (CandidateEnrollmentService) getBean("candidateEnrllmentService");
	}
	
	/**
	 * @return
	 */
	public MessagingService getMessagingService()
	{
		return (MessagingService) getBean("messagingService");
	}
	
	/**
	 * @return
	 */
	public DefaultMessageListenerContainer getAnsPaperListener()
	{
		return (DefaultMessageListenerContainer) getBean("ansPaperStatusListener");
	}
	
	public DefaultMessageListenerContainer getTCDataListener()
	{
		return (DefaultMessageListenerContainer) getBean("tcDataListener");
	}
	
	/**
	 * 
	 * @return
	 */
	public static ServiceLocator getInstance()
	{
		if(locator == null )
		{
			locator = new ServiceLocator();
		}
		return locator;
	}
}
