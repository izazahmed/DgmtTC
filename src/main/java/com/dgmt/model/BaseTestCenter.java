/**
 * Created Date: 6/28/10 6:34 PM
 * Class Name  : BaseTestCenter.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>	<Author>	<Date>	<Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.dgmt.model;

import java.io.Serializable;

/**
 * Description:
 * 
 * @version 1.0 6/28/10 6:34 PM
 * @author
 * @since DGMT 1.0
 */
public class BaseTestCenter implements Serializable
{
	private String isTcAssociatedWithExamWindow;

	/**
	 * @return
	 */
	public String getIsTcAssociatedWithExamWindow()
	{
		return isTcAssociatedWithExamWindow;
	}

	/**
	 * @param isTcAssociatedWithExamWindow
	 */
	public void setIsTcAssociatedWithExamWindow(
			String isTcAssociatedWithExamWindow)
	{
		this.isTcAssociatedWithExamWindow = isTcAssociatedWithExamWindow;
	}
}
