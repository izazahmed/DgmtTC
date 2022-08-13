/**
 * Created Date: 4/18/11 12:28 PM
 * Class Name  : UserSessionInfo.java
 *
 * © Copyright 2011 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>	<Author>	<Date>	<Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.dgmt.web.action;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Description: This file is used to store the Session related information for each session
 * 
 * @version 1.0 4/18/11 12:28 PM
 * @author CTE
 * @since DGMT 1.0
 */
public class UserSessionInfo
{
	private String loginid;

	private String sessionId;

	private HttpSession currentSession;

	/**
	 * @return
	 */
	public String getLoginid()
	{
		return loginid;
	}

	/**
	 * @param loginid
	 */
	public void setLoginid(String loginid)
	{
		this.loginid = loginid;
	}

	/**
	 * @return
	 */
	public String getSessionId()
	{
		return sessionId;
	}

	/**
	 * @param sessionId
	 */
	public void setSessionId(String sessionId)
	{
		this.sessionId = sessionId;
	}

	/**
	 * @return
	 */
	public HttpSession getCurrentSession()
	{
		return currentSession;
	}

	/**
	 * @param currentSession
	 */
	public void setCurrentSession(HttpSession currentSession)
	{
		this.currentSession = currentSession;
	}
}
