/**
 * Created Date: 5/11/10 11:39 AM
 * Class Name  : InsufficientQuestionsException.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>	<Author>	<Date>	<Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.dgmt.exceptions;

/**
 * Description:
 * 
 * @version 1.0 5/11/10 11:39 AM
 * @author aloni
 * @since DGMT 1.0
 */
public class InsufficientQuestionsException extends Exception
{
	/**
	 * Creates a new InsufficientQuestionsException object.
	 * 
	 * @param message
	 */
	public InsufficientQuestionsException(String message)
	{
		super(message);
	}
}
