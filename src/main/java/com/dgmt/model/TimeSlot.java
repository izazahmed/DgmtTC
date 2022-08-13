/**
 * Created Date: 3/18/10 10:55 PM
 * Class Name  : TimeSlot.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises Ltd.,All rights reserved.
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
 * @version 1.0 3/18/10 10:55 PM
 * @author CTE
 * @since DGMT 1.0
 */
@Entity
@Table(name = "B_TIME_SLOT")
public class TimeSlot implements Serializable
{
	/**  */
	
	@Id
	@Column(name = "TS_ID")
	Long id;

	/**  */
	@Column(name = "TS_START_TIME", length = 10)
	String startTime;

	/**  */
	@Column(name = "TS_END_TIME", length = 10)
	String endTime;

	/**  */
	@Column(name = "TS_ALLOWED_UPTO", length = 10)
	String allowedUpto;

	/** Last modified date. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TS_MODIFIED_DATE")
	Date modifiedDate;

	/** Created date. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TS_CREATED_DATE")
	Date createdDate;

	@Column(name = "TS_CREATED_BY", length = 20)
	private String createdBy;

	@Column(name = "TS_MODIFIED_BY", length = 20)
	private String modifiedBy;

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
	public String getStartTime()
	{
		return startTime;
	}

	/**
	 * @param startTime
	 */
	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}

	/**
	 * @return
	 */
	public String getEndTime()
	{
		return endTime;
	}

	/**
	 * @param endTime
	 */
	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}

	/**
	 * @return
	 */
	public String getAllowedUpto()
	{
		return allowedUpto;
	}

	/**
	 * @param allowedUpto
	 */
	public void setAllowedUpto(String allowedUpto)
	{
		this.allowedUpto = allowedUpto;
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
}
