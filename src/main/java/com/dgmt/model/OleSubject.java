/**
 * Created Date: 4/15/10 7:29 PM
 * Class Name  : Subject.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>        <Author>        <Date>        <Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package com.dgmt.model;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:29 PM
 * @author
 * @since DGMT 1.0
 */
@Entity
@Table(name = "B_SUBJECT_CODE")
public class OleSubject extends BaseSubject implements Serializable
{
	@Id
	@Column(name = "SC_ID")
	private Long id;

	@Column(name = "SC_NAME", unique = true)
	private String name;

	@Column(name = "SC_CODE", unique = true)
	private String code;

	@Column(name = "SC_DESC", nullable = true)
	private String desc;

	@Column(name = "SC_CAPTION", unique = true, length = 2)
	private String caption;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SC_MODIFIED_DATE", nullable = true)
	private Date modifiedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SC_CREATED_DATE", nullable = true)
	private Date createdDate;

	@Column(name = "SC_MODIFIED_BY", nullable = true)
	private String modifiedBy;

	@Column(name = "SC_CREATED_BY", nullable = true)
	private String createdBy;

	/**
	 * Creates a new Subject object.
	 */
	public OleSubject()
	{
	}

	/**
	 * Creates a new Subject object.
	 * 
	 * @param id
	 */
	public OleSubject(Long id)
	{
		this.id = id;
	}

	/**
	 * Creates a new OleSubject object.
	 * 
	 * @param caption
	 * @param code
	 * @param createdBy
	 * @param createdDate
	 * @param desc
	 * @param id
	 * @param modifiedBy
	 * @param modifiedDate
	 * @param name
	 */
	public OleSubject(String caption, String code, String createdBy,
			Date createdDate, String desc, Long id, String modifiedBy,
			Date modifiedDate, String name)
	{
		super();
		this.caption = caption;
		this.code = code;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.desc = desc;
		this.id = id;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.name = name;
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

	/**
	 * @return
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * @param code
	 */
	public void setCode(String code)
	{
		this.code = code;
	}

	/**
	 * @return
	 */
	public String getDesc()
	{
		return desc;
	}

	/**
	 * @param desc
	 */
	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	/**
	 * @return
	 */
	public String getCaption()
	{
		return caption;
	}

	/**
	 * @param caption
	 */
	public void setCaption(String caption)
	{
		this.caption = caption;
	}

	/**
	 * @return
	 */
	public Date getModifiedDate()
	{
		return modifiedDate;
	}

	/**
	 * @param modifiedDate
	 */
	public void setModifiedDate(Date modifiedDate)
	{
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return
	 */
	public Date getCreatedDate()
	{
		return createdDate;
	}

	/**
	 * @param createdDate
	 */
	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	/**
	 * @return
	 */
	public String getModifiedBy()
	{
		return modifiedBy;
	}

	/**
	 * @param modifiedBy
	 */
	public void setModifiedBy(String modifiedBy)
	{
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return
	 */
	public String getCreatedBy()
	{
		return createdBy;
	}

	/**
	 * @param createdBy
	 */
	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}
}
