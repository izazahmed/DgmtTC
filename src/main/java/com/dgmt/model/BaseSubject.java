/**
 * Created Date: 4/15/10 7:25 PM
 * Class Name  : BaseSubject.java
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
 * @version 1.0 4/15/10 7:25 PM
 * @author
 * @since DGMT 1.0
 */
public class BaseSubject implements Serializable
{
	/**
	 * DOCUMENT ME!
	 */
	String subjectExames;
	
	/**
	 * @return
	 */
	public String getSubjectExames()
	{
		return subjectExames;
	}

	/**
	 * @param subjectExames
	 */
	public void setSubjectExames(String subjectExames)
	{
		this.subjectExames = subjectExames;
	}
}
