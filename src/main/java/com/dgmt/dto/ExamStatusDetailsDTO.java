/**
 * Created Date: 1/20/11 4:04 PM
 * Class Name  : ExamStatusDetailsDTO.java
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
 * @version 1.0 1/20/11 4:04 PM
 * @author aloni
 * @since DGMT 1.0
 */
public class ExamStatusDetailsDTO implements Serializable
{
	private String personalNo;

	private String candidateName;

	private String subjectName;

	private String examPaperTitle;

	private String status;

	private Date startTime;

	private Date endTime;

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
	public String getCandidateName()
	{
		return candidateName;
	}

	/**
	 * @param candidateName
	 */
	public void setCandidateName(String candidateName)
	{
		this.candidateName = candidateName;
	}

	/**
	 * @return
	 */
	public String getSubjectName()
	{
		return subjectName;
	}

	/**
	 * @param subjectName
	 */
	public void setSubjectName(String subjectName)
	{
		this.subjectName = subjectName;
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

	/**
	 * @return
	 */
	public Date getStartTime()
	{
		return startTime;
	}

	/**
	 * @param startTime
	 */
	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}

	/**
	 * @return
	 */
	public Date getEndTime()
	{
		return endTime;
	}

	/**
	 * @param endTime
	 */
	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}

	public String getExamPaperTitle()
	{
		return examPaperTitle;
	}

	public void setExamPaperTitle(String examPaperTitle)
	{
		this.examPaperTitle = examPaperTitle;
	}
}
