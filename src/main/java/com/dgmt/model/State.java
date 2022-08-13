/**
 * Created Date: 4/15/10 7:29 PM
 * Class Name  : State.java
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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "L_STATE")
public class State implements Serializable
{
	/**
	 * DOCUMENT ME!
	 */
	@Id
	@Column(name = "STATE_ID")
	Long id;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "STATE_NAME", nullable = false)
	String name;

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
}
