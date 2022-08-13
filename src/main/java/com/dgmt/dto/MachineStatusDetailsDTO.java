/**
 * Created Date: 3/2/11 12:21 PM
 * Class Name  : MachineStatusDetailsDTO.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>	<Author>	<Date>	<Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.dgmt.dto;

import java.io.Serializable;

/**
 * Description:
 * 
 * @version 1.0 3/2/11 12:21 PM
 * @author CTE
 * @since DGMT 1.0
 */
public class MachineStatusDetailsDTO implements Serializable
{
	private String ipAddress;

	private String status;

	/**
	 * @return
	 */
	public String getIpAddress()
	{
		return ipAddress;
	}

	/**
	 * @param ipAddress
	 */
	public void setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
	}

	/**
	 * @return
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * @param status
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}
}
