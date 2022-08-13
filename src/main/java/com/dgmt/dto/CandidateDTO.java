/**
 * Created Date: 5/4/10 5:23 PM
 * Class Name  : CandidateDTO.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>        <Author>        <Date>        <Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package com.dgmt.dto;

import java.io.Serializable;

/**
 * Description:
 * 
 * @version 1.0 5/4/10 5:23 PM
 * @author
 * @since DGMT 1.0
 */
public class CandidateDTO implements Serializable
{
	private String seniorityDateString;

	private String dateOfCommString;

	private String personalNo;

	private String name;

	private String rank;

	private String armServive;

	private String status;

	/**
	 * @return
	 */
	public String getDateOfCommString()
	{
		return dateOfCommString;
	}

	/**
	 * @param dateOfCommString
	 */
	public void setDateOfCommString(String dateOfCommString)
	{
		this.dateOfCommString = dateOfCommString;
	}

	/**
	 * @return
	 */
	public String getSeniorityDateString()
	{
		return seniorityDateString;
	}

	/**
	 * @param seniorityDateString
	 */
	public void setSeniorityDateString(String seniorityDateString)
	{
		this.seniorityDateString = seniorityDateString;
	}

	/**
	 * @return
	 */
	public String getPersonalNo()
	{
		return personalNo;
	}

	/**
	 * @param personalNo
	 */
	public void setPersonalNo(String personalNo)
	{
		this.personalNo = personalNo;
	}

	/**
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return
	 */
	public String getRank()
	{
		return rank;
	}

	/**
	 * @param rank
	 */
	public void setRank(String rank)
	{
		this.rank = rank;
	}

	/**
	 * @return
	 */
	public String getArmServive()
	{
		return armServive;
	}

	/**
	 * @param armServive
	 */
	public void setArmServive(String armServive)
	{
		this.armServive = armServive;
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
