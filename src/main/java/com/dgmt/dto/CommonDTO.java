package com.dgmt.dto;

/**
 * Created Date: 9/13/10 12:03 PM
 * Class Name  : CommonDTO.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>        <Author>        <Date>        <Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 * 
 * @version 1.0 9/13/10 12:03 PM
 * @author
 * @since DGMT 1.0
 */
public class CommonDTO implements Serializable {
	private String createdBy;
	private String modifiedBy;
	private Date createdDate;
	private Date modifiedDate;

	/**
	 * 
	 * 
	 * @return
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * 
	 * 
	 * @param createdBy
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * 
	 * 
	 * @param modifiedBy
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * 
	 * 
	 * @param createdDate
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * 
	 * 
	 * @param modifiedDate
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}
