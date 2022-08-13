/**
 * Created Date: 4/23/10 10:51 AM
 * Class Name  : UserRole.java
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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

/**
 * Description:
 * 
 * @version 1.0 4/23/10 10:51 AM
 * @author
 * @since DGMT 1.0
 */
@Entity
@Table(name = "USER_ROLE",uniqueConstraints = @UniqueConstraint(columnNames = {
        "UL_ID", "ROLE_ID" }))
public class UserRole implements Serializable
{
	/**
	 * DOCUMENT ME!
	 */
//	@SequenceGenerator(name = "userRoleSeq", sequenceName = "USER_ROLE_SEQ")
	@Id
	//@GeneratedValue(generator = "userRoleSeq")
	@Column(name = "UR_ID", unique = true, nullable = false)
	public Long id;

	/**
	 * DOCUMENT ME!
	 */
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "UL_ID")
	@LazyToOne(LazyToOneOption.FALSE)
	public UserLogin userLogin;

	/**
	 * DOCUMENT ME!
	 */
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "ROLE_ID")
	@LazyToOne(LazyToOneOption.FALSE)
	public Role role;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "UR_MODIFIED_BY")
	public String modifiedBy;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "UR_MODIFICATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	public Date modificationDate;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "UR_CREATED_BY")
	public String createdBy;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "UR_CREATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	public Date creationDate;

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
	public UserLogin getUserLogin()
	{
		return userLogin;
	}

	/**
	 * @param userLogin
	 */
	public void setUserLogin(UserLogin userLogin)
	{
		this.userLogin = userLogin;
	}

	/**
	 * @return
	 */
	public Role getRole()
	{
		return role;
	}

	/**
	 * @param role
	 */
	public void setRole(Role role)
	{
		this.role = role;
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
	public Date getModificationDate()
	{
		return modificationDate;
	}

	/**
	 * @param modificationDate
	 */
	public void setModificationDate(Date modificationDate)
	{
		this.modificationDate = modificationDate;
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
	public Date getCreationDate()
	{
		return creationDate;
	}

	/**
	 * @param creationDate
	 */
	public void setCreationDate(Date creationDate)
	{
		this.creationDate = creationDate;
	}
}
