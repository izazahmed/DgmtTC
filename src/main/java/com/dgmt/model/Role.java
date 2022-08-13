/**
 * Created Date: 4/23/10 10:50 AM
 * Class Name  : Role.java
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
 * @version 1.0 4/23/10 10:50 AM
 * @author
 * @since DGMT 1.0
 */
@Entity
@Table(name = "ROLE")
public class Role implements Serializable{
	/**
	 * DOCUMENT ME!
	 */
	@Id
	@Column(name = "ROLE_ID")
	public Long id;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "ROLE_NAME")
	public String name;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "ROLE_MODIFIED_BY")
	public String modifiedBy;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "ROLE_MODIFICATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	public Date modificationDate;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "ROLE_CREATED_BY")
	public String createdBy;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "ROLE_CREATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	public Date creationDate;
	/*@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "ROLE_ID")
	private Set<UserRole> userRole = new HashSet<UserRole>(0);*/

	/**
	 * 
	 * 
	 * @return
	 */
/*	public Set<UserRole> getUserRole() {
		return userRole;
	}*/

	/**
	 * 
	 * 
	 * @param userRole
	 */
/*	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}*/

	/**
	 * 
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
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
	public Date getModificationDate() {
		return modificationDate;
	}

	/**
	 * 
	 * 
	 * @param modificationDate
	 */
	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

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
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * 
	 * 
	 * @param creationDate
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
}
