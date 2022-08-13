/**
 * Created Date: 4/15/10 7:27 PM
 * Class Name  : Exam.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>	<Author>	<Date>	<Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.dgmt.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:27 PM
 * @author
 * @since DGMT 1.0
 */
@Entity
@Table(name = "B_EXAM_CODE")
public class OleExam implements Serializable
{
	@Id
	@Column(name = "EC_ID")
	private Long id;

	@Column(name = "EC_NAME")
	private String name;

	/**
	 * Creates a new Exam object.
	 */
	public OleExam()
	{
	}

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
