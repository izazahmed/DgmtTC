/**
 * Created Date: 11/30/10 1:19 PM
 * Class Name  : StatusMessageDTO.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>	<Author>	<Date>	<Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.dgmt.dto;

import java.io.Serializable;

import java.util.Date;

/**
 * Description:
 * 
 * @version 1.0 11/30/10 1:19 PM
 * @author aloni
 * @since DGMT 1.0
 */
public class StatusMessageDTO implements Serializable
{
	private String messageID;

	private Date receivedTime;
	
	private String testCenterId;

	/**
	 * @return
	 */
	public String getMessageID()
	{
		return messageID;
	}

	/**
	 * @param messageID
	 */
	public void setMessageID(String messageID)
	{
		this.messageID = messageID;
	}

	/**
	 * @return
	 */
	public Date getReceivedTime()
	{
		return receivedTime;
	}

	/**
	 * @param receivedTime
	 */
	public void setReceivedTime(Date receivedTime)
	{
		this.receivedTime = receivedTime;
	}
	
	public void setTestCenterId(String testCenterId)
	{
		this.testCenterId = testCenterId;
	}
	
	public String getTestCenterId()
	{
		return testCenterId;
	}
}
