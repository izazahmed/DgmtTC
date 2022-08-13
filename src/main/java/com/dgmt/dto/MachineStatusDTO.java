/**
 * Created Date: 3/2/11 12:20 PM
 * Class Name  : MachineStatusDTO.java
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
import java.util.List;

import com.dgmt.model.TestCenter;

/**
 * Description:
 * 
 * @version 1.0 3/2/11 12:20 PM
 * @author CTE
 * @since DGMT 1.0
 */
public class MachineStatusDTO implements Serializable
{
	private TestCenter testCenter;

	private int upCnt;

	private int downCnt;

	private List<MachineStatusDetailsDTO> details;

	private Date updatedTime;

	/**
	 * @return
	 */
	public TestCenter getTestCenter()
	{
		return testCenter;
	}

	/**
	 * @param testCenter
	 */
	public void setTestCenter(TestCenter testCenter)
	{
		this.testCenter = testCenter;
	}

	/**
	 * @return
	 */
	public int getUpCnt()
	{
		return upCnt;
	}

	/**
	 * @param upCnt
	 */
	public void setUpCnt(int upCnt)
	{
		this.upCnt = upCnt;
	}

	/**
	 * @return
	 */
	public int getDownCnt()
	{
		return downCnt;
	}

	/**
	 * @param downCnt
	 */
	public void setDownCnt(int downCnt)
	{
		this.downCnt = downCnt;
	}

	/**
	 * @return
	 */
	public List<MachineStatusDetailsDTO> getDetails()
	{
		return details;
	}

	/**
	 * @param details
	 */
	public void setDetails(List<MachineStatusDetailsDTO> details)
	{
		this.details = details;
	}

	/**
	 * @return
	 */
	public Date getUpdatedTime()
	{
		return updatedTime;
	}

	/**
	 * @param updatedTime
	 */
	public void setUpdatedTime(Date updatedTime)
	{
		this.updatedTime = updatedTime;
	}
}
