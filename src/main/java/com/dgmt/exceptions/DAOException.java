/**
 * Created Date: 4/15/10 7:23 PM
 * Class Name  : DAOException.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>        <Author>        <Date>        <Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package com.dgmt.exceptions;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:23 PM
 * @author
 * @since DGMT 1.0
 */
public class DAOException extends Exception
{
	// ~ Constructors
	// ===========================================================

	/**
	 * Constructor for DAOException.
	 */
	public DAOException()
	{
		super();
	}

	/**
	 * Constructor for DAOException.
	 * 
	 * @param message
	 */
	public DAOException(String message)
	{
		super(message);
	}

	/**
	 * Constructor for DAOException.
	 * 
	 * @param message
	 * @param cause
	 */
	public DAOException(String message, Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * Constructor for DAOException.
	 * 
	 * @param cause
	 */
	public DAOException(Throwable cause)
	{
		super(cause);
	}
}
