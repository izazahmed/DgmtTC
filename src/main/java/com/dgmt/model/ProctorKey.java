/**
 * Created Date: 4/12/10 11:28 AM
 * Class Name  : ProctorKey.java
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.Type;

import org.hibernate.annotations.TypeDef;
import org.jasypt.hibernate.type.EncryptedStringType;
import org.hibernate.annotations.Parameter;

/**
 * Description:
 * 
 * @version 1.0 4/12/10 11:28 AM
 * @author aloni
 * @since DGMT 1.0
 */

@TypeDef(
	    name="encryptedString", 
	    typeClass=EncryptedStringType.class, 
	    parameters={@Parameter(name="encryptorRegisteredName",
	                           value="strongHibernateStringEncryptor")}
	)
@Entity
@Table(name = "B_PROCTOR_KEY")
public class ProctorKey implements Serializable
{
	@Id
	@Column(name = "PK_ID")
	private Long id;

	@ManyToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "EW_ID")
	@LazyToOne(LazyToOneOption.FALSE)
	private ExamWindow ew;

	@Column(name = "PK_DATE")
	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(name = "PK_CARD", length = 1)
	private String card;

	@Column(name = "PK_CELL", length = 2)
	private String cell;

	@Column(name = "PK_KEY", length = 100, nullable = false)
	@Type(type="encryptedString")
	private String key;

	@Column(name = "PK_CREATED_BY", length = 20)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PK_CREATED_DATE")
	private Date createdDate;

	@Column(name = "PK_STATUS", length = 1)
	private String status;
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
	public String getCard()
	{
		return card;
	}

	/**
	 * @param card
	 */
	public void setCard(String card)
	{
		this.card = card;
	}

	/**
	 * @return
	 */
	public String getCell()
	{
		return cell;
	}

	/**
	 * @param cell
	 */
	public void setCell(String cell)
	{
		this.cell = cell;
	}

	/**
	 * @return
	 */
	public String getKey()
	{
		return key;
	}

	/**
	 * @param key
	 */
	public void setKey(String key)
	{
		this.key = key;
	}

	/**
	 * @return
	 */
	public ExamWindow getEw()
	{
		return ew;
	}

	/**
	 * @param ew
	 */
	public void setEw(ExamWindow ew)
	{
		this.ew = ew;
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
	 * @param status
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	/**
	 * @return
	 */
	public String getStatus()
	{
		return status;
	}
}
