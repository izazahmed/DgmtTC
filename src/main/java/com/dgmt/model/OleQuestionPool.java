/**
 * Created Date: 4/15/10 7:29 PM
 * Class Name  : QuestionPool.java
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:29 PM
 * @author
 * @since DGMT 1.0
 */
@Entity
@Table(name = "B_QUESTION_POOL")
public class OleQuestionPool implements Serializable
{
	@Id
	@Column(name = "QP_ID")
	private Long id;

	@Column(name = "QP_TITLE", length = 30, nullable = false)
	private String title;

	@Column(name = "QP_TYPE", length = 10, nullable = false)
	private String type;

	@Column(name = "QP_LEVEL", length = 8, nullable = false)
	private String level;

	@Column(name = "QP_DESC", length = 150, nullable = true)
	private String desc;

	@ManyToOne
	/* (fetch = FetchType.EAGER) */
	@LazyToOne(LazyToOneOption.FALSE)
	@Cascade( { CascadeType.SAVE_UPDATE })
	@JoinColumn(name = "SC_ID")
	private OleSubject subject;

	@Column(name = "QP_COUNT")
	private int questionCount;

	@Column(name = "QP_MODIFIED_BY")
	private String modifiedBy;

	@Column(name = "QP_CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QP_MODIFIED_DATE")
	private Date modifiedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QP_CREATED_DATE")
	private Date createdDate;

	/**
	 * Creates a new QuestionPool object.
	 */
	public OleQuestionPool()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * Creates a new QuestionPool object.
	 * 
	 * @param id
	 */
	public OleQuestionPool(Long id)
	{
		this.id = id;
	}

	/**
	 * Creates a new QuestionPool object.
	 * 
	 * @param desc
	 * @param id
	 * @param level
	 * @param subject
	 * @param title
	 * @param type
	 */
	public OleQuestionPool(String desc, Long id, String level,
			OleSubject subject, String title, String type, Date createdDate,
			String createdBy, Date modifiedDate, String modifiedBy)
	{
		super();
		this.desc = desc;
		this.id = id;
		this.level = level;
		this.subject = subject;
		this.title = title;
		this.type = type;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
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
	public String getTitle()
	{
		return title;
	}

	/**
	 * @param title
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * @return
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @param type
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * @return
	 */
	public String getLevel()
	{
		return level;
	}

	/**
	 * @param level
	 */
	public void setLevel(String level)
	{
		this.level = level;
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
	public OleSubject getSubject()
	{
		return subject;
	}

	/**
	 * @param subject
	 */
	public void setSubject(OleSubject subject)
	{
		this.subject = subject;
	}

	/**
	 * @return the questionCount
	 */
	public int getQuestionCount()
	{
		return questionCount;
	}

	/**
	 * @param questionCount
	 *            the questionCount to set
	 */
	public void setQuestionCount(int questionCount)
	{
		this.questionCount = questionCount;
	}
}
