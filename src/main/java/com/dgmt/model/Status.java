/**
 * Created Date: 4/15/10 7:29 PM
 * Class Name  : Status.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>	<Author>	<Date>	<Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.dgmt.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:29 PM
 * @author
 * @since DGMT 1.0
 */
@Entity
@Table(name = "L_STATUS_DESCRIPTION")
public class Status implements Serializable
{
	@Id
	@Column(name = "SD_ID")
	private Long id;

	@Column(name = "SD_STATUS", unique = true, nullable = false)
	private String status;

	/**
	 * @return
	 */
	public Long getId()
	{
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Long id)
	{
		this.id = id;
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
