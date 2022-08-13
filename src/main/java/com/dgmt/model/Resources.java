/**
 * Created Date: 4/15/10 7:29 PM
 * Class Name  : Resources.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>	<Author>	<Date>	<Comments on Change>
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
@Table(name = "B_RESOURCES")
public class Resources implements Serializable
{
	@Id
	@Column(name = "RS_ID")
	private Long id;

	@Column(name = "RS_FILENAME", unique = true, nullable = false)
	private String file;

	@Column(name = "RS_UPLOADED_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Column(name = "RS_UPLOADED_BY", nullable = false)
	private String uploadedBy;

	/*
	 * @ManyToOne(fetch=FetchType.EAGER)
	 * @Cascade({CascadeType.SAVE_UPDATE})
	 * @JoinColumn(name="LS_ID") private Status status;
	 */
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
	public String getFile()
	{
		return file;
	}

	/**
	 * @param file
	 */
	public void setFile(String file)
	{
		this.file = file;
	}

	/**
	 * @return
	 */
	public Date getDate()
	{
		return date;
	}

	/**
	 * @param date
	 */
	public void setDate(Date date)
	{
		this.date = date;
	}

	/**
	 * @return
	 */
	public String getUploadedBy()
	{
		return uploadedBy;
	}

	/**
	 * @param uploadedBy
	 */
	public void setUploadedBy(String uploadedBy)
	{
		this.uploadedBy = uploadedBy;
	}

	/*
	 * public Status getStatus() { return status; } public void setStatus(Status
	 * status) { this.status = status; }
	 */
}
