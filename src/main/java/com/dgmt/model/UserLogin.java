/**
 * Created Date: 4/23/10 10:51 AM
 * Class Name  : UserLogin.java
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
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Description:
 * 
 * @version 1.0 4/23/10 10:51 AM
 * @author
 * @since DGMT 1.0
 */
@Entity
@Table(name = "USER_LOGIN")
public class UserLogin implements Serializable{
	/**
	 * DOCUMENT ME!
	 */
	//@SequenceGenerator(name = "userloginSeq", sequenceName = "USER_LOGIN_SEQ")
	@Id
	//@GeneratedValue(generator = "userloginSeq")
	@Column(name = "UL_ID")
	public Long id;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "UL_NAME")
	public String name;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "UL_PASSWD")
	public String password;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "UL_STATUS")
	public Long status;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "UL_MODIFIED_BY")
	public String modifiedBy;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "UL_MODIFICATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	public Date modificationDate;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "UL_CREATED_BY")
	public String createdBy;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "UL_CREATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	public Date creationDate;
	
	/*@ManyToMany(cascade = CascadeType.ALL)	
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "UL_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	private Set<Role> userRole;*/

	/*@OneToMany
	  @JoinTable(name = "USER_ROLE",
	    joinColumns = {
	      @JoinColumn(name="UL_ID", unique = true)           
	    },
	    inverseJoinColumns = {
	      @JoinColumn(name="ROLE_ID")
	    }
	  )
	  @LazyCollection(LazyCollectionOption.FALSE)
	private Set<Role> userRole;
	
	*//**
	 * 
	 * 
	 * @return
	 *//*
	public Set<Role> getUserRole() {
		return userRole;
	}

	*//**
	 * 
	 * 
	 * @param userRole
	 *//*
	public void setUserRole(Set<Role> userRole) {
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
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public Long getStatus() {
		return status;
	}

	/**
	 * 
	 * 
	 * @param status
	 */
	public void setStatus(Long status) {
		this.status = status;
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
