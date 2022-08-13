/**
 * Created Date: 4/15/10 7:26 PM
 * Class Name  : Candidate.java
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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:26 PM
 * @author
 * @since DGMT 1.0
 */
@Entity
@Table(name = "B_CONTRIBUTOR_DETAILS")
public class OleContributor implements Serializable
{
	/**
	 * DOCUMENT ME!
	 */
	@Id
	@Column(name = "CB_ID")
	Long id;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "CB_NAME")
	String name;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "CB_LOGIN_ID", nullable = false, unique = true)
	String loginId;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "CB_PASSWORD")
	String password;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "CB_CONTACT_NUMBER")
	String contactNumber;
	
	@Column(name = "CB_ADDRESS")
	private String address;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "CB_EMAIL")
	String email;

	/**
	 * DOCUMENT ME!
	 */
	@OneToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "SC_ID")
	OleSubject subject;

	/**
	 * DOCUMENT ME!
	 */
    @Temporal(TemporalType.DATE)
	@Column(name = "CB_ACCESS_START_DATE")
	Date accessStartDate;

	/**
	 * DOCUMENT ME!
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "CB_ACCESS_END_DATE")
	Date accessEndDate;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "CB_STATUS")
	String status;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "CB_MODIFIED_BY", length = 20)
	private String modifiedBy;

	/**
	 * DOCUMENT ME!
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CB_MODIFIED_DATE")
	private Date modifiedDate;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "CB_CREATED_BY", length = 20)
	private String createdBy;

	/**
	 * DOCUMENT ME!
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CB_CREATED_DATE")
	private Date createdDate;

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
	public String getLoginId()
	{
		return loginId;
	}

	/**
	 * @param loginId
	 */
	public void setLoginId(String loginId)
	{
		this.loginId = loginId;
	}

	/**
	 * @return
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * @return
	 */
	public String getContactNumber()
	{
		return contactNumber;
	}

	/**
	 * @param contactNumber
	 */
	public void setContactNumber(String contactNumber)
	{
		this.contactNumber = contactNumber;
	}
	
	

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	/**
	 * @return
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}
 
	
	
	public OleSubject getSubject()
	{
		return subject;
	}

	public void setSubject(OleSubject subject)
	{
		this.subject = subject;
	}

	/**
	 * @return
	 */
	public Date getAccessStartDate()
	{
		return accessStartDate;
	}

	/**
	 * @param accessStartDate
	 */
	public void setAccessStartDate(Date accessStartDate)
	{
		this.accessStartDate = accessStartDate;
	}

	/**
	 * @return
	 */
	public Date getAccessEndDate()
	{
		return accessEndDate;
	}

	/**
	 * @param accessEndDate
	 */
	public void setAccessEndDate(Date accessEndDate)
	{
		this.accessEndDate = accessEndDate;
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
