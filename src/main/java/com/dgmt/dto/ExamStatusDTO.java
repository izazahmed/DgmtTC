/**
 * Created Date: 1/20/11 4:04 PM
 * Class Name  : ExamStatusDTO.java
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

import com.dgmt.model.ExamWindow;
import com.dgmt.model.TestCenter;

/**
 * Description:
 * 
 * @version 1.0 1/20/11 4:04 PM
 * @author aloni
 * @since DGMT 1.0
 */
public class ExamStatusDTO implements Serializable
{
	private ExamWindow examWindow;

	private TestCenter testCenter;

	private int ongoingCnt;

	private int submittedCnt;

	private List<ExamStatusDetailsDTO> details;

	private Date updatedTime;

	/**
	 * @return
	 */
	public ExamWindow getExamWindow()
	{
		return examWindow;
	}

	/**
	 * @param examWindow
	 */
	public void setExamWindow(ExamWindow examWindow)
	{
		this.examWindow = examWindow;
	}

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
	public int getOngoingCnt()
	{
		return ongoingCnt;
	}

	/**
	 * @param ongoingCnt
	 */
	public void setOngoingCnt(int ongoingCnt)
	{
		this.ongoingCnt = ongoingCnt;
	}

	/**
	 * @return
	 */
	public int getSubmittedCnt()
	{
		return submittedCnt;
	}

	/**
	 * @param submittedCnt
	 */
	public void setSubmittedCnt(int submittedCnt)
	{
		this.submittedCnt = submittedCnt;
	}

	/**
	 * @return
	 */
	public List<ExamStatusDetailsDTO> getDetails()
	{
		return details;
	}

	/**
	 * @param details
	 */
	public void setDetails(List<ExamStatusDetailsDTO> details)
	{
		this.details = details;
	}

	public void setUpdatedTime(Date updatedTime)
	{
		this.updatedTime = updatedTime;
	}

	public Date getUpdatedTime()
	{
		return updatedTime;
	}
}
