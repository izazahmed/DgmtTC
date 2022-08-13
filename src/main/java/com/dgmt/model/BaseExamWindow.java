/**
 * Created Date: 6/30/10 10:49 AM
 * Class Name  : BaseExamWindow.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>	<Author>	<Date>	<Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.dgmt.model;

/**
 * Description:
 * 
 * @version 1.0 6/30/10 10:49 AM
 * @author
 * @since DGMT 1.0
 */
public class BaseExamWindow
{
	private String isExamWindowAssociatedWithEnrollment;

	/**
	 * @return
	 */
	public String getIsExamWindowAssociatedWithEnrollment()
	{
		return isExamWindowAssociatedWithEnrollment;
	}

	/**
	 * @param isExamWindowAssociatedWithEnrollment
	 */
	public void setIsExamWindowAssociatedWithEnrollment(
			String isExamWindowAssociatedWithEnrollment)
	{
		this.isExamWindowAssociatedWithEnrollment = isExamWindowAssociatedWithEnrollment;
	}
}
