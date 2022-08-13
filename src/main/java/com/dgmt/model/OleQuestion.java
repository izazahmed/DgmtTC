/**
 * Created Date: 4/15/10 7:28 PM
 * Class Name  : Question.java
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
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.jasypt.hibernate.type.EncryptedStringType;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:28 PM
 * @author
 * @since DGMT 1.0
 */
@TypeDef(
        name="encryptedString", 
        typeClass=EncryptedStringType.class, 
        parameters={@Parameter(name="encryptorRegisteredName",
                               value="strongHibernateStringEncryptor")}
    )

@Entity
@Table(name = "B_QUESTIONS")
public class OleQuestion implements Serializable
{
	/**
	 * DOCUMENT ME!
	 */
	@Id
	@Column(name = "QS_ID")
	Long questionId;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "QS_QUESTION", length = 4000, nullable = false)
	@Type(type="encryptedString")
	String questionText;
	

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "QS_TYPE", length = 1, nullable = false)
	int questionType;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "QS_SOURCE", columnDefinition = "number(1) default 0", nullable = false)
	int questionSource;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "QS_STATUS", columnDefinition = "number(1) default 0", nullable = false)
	int questionStatus;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "QS_HINT", length = 4000, nullable = true)
	@Type(type="encryptedString")
	String hint;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "QS_ORDER", columnDefinition = "number(1) default 0", nullable = false)
	int order = 0;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "QS_IMAGE", length = 50, nullable = true)
	String image;

	/**
	 * DOCUMENT ME!
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade( { CascadeType.SAVE_UPDATE })
	@JoinColumn(name = "CB_ID")
	OleContributor candidate;

	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade( { CascadeType.SAVE_UPDATE })
	@JoinColumn(name = "SC_ID")
	private OleSubject subject;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "QS_POOL_TYPE", length = 20, nullable = false)
	String poolType;

	/**
	 * DOCUMENT ME!
	 */
	@ManyToOne
	@Cascade( { CascadeType.SAVE_UPDATE })
	@JoinColumn(name = "QP_ID")
	OleQuestionPool questionPool;

	/**
	 * DOCUMENT ME!
	 */
	@OneToMany
	@Cascade( { CascadeType.SAVE_UPDATE })
	@LazyCollection(LazyCollectionOption.FALSE)
	@CollectionOfElements(targetElement = com.dgmt.model.OleChoice.class)
	@JoinColumn(name = "QS_ID", nullable = false)
	List<OleChoice> choices;

	/**
	 * DOCUMENT ME!
	 */
	@OneToMany
	@Cascade(CascadeType.SAVE_UPDATE)
	@LazyCollection(LazyCollectionOption.FALSE)
	@CollectionOfElements(targetElement = com.dgmt.model.OleMatching.class)
	@JoinColumn(name = "QS_ID", nullable = false)
	List<OleMatching> matchingOptions;

	/**
	 * DOCUMENT ME!
	 */
	@ManyToOne
	@Cascade( { CascadeType.SAVE_UPDATE })
	@JoinColumn(name = "RS_ID")
	private Resources resources;

	@Column(name = "QS_MODIFIED_BY", length = 20)
	private String modifiedBy;

	@Column(name = "QS_MODIFIED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@Column(name = "QS_CREATED_BY", length = 20)
	private String createdBy;

	@Column(name = "QS_CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	/**
	 * @return
	 */
	public int getOrder()
	{
		return order;
	}

	/**
	 * @return
	 */
	public Resources getResources()
	{
		return resources;
	}

	/**
	 * @param resources
	 */
	public void setResources(Resources resources)
	{
		this.resources = resources;
	}

	/**
	 * @return
	 */
	public List<OleMatching> getMatchingOptions()
	{
		return matchingOptions;
	}

	/**
	 * @param matchingOptions
	 */
	public void setMatchingOptions(List<OleMatching> matchingOptions)
	{
		this.matchingOptions = matchingOptions;
	}

	/**
	 * @return
	 */
	public List<OleChoice> getChoices()
	{
		return choices;
	}

	/**
	 * @param choices
	 */
	public void setChoices(List<OleChoice> choices)
	{
		this.choices = choices;
	}

	/**
	 * @return
	 */
	public OleQuestionPool getQuestionPool()
	{
		return questionPool;
	}

	/**
	 * @param questionPool
	 */
	public void setQuestionPool(OleQuestionPool questionPool)
	{
		this.questionPool = questionPool;
	}

	/**
	 * @return
	 */
	public OleContributor getCandidate()
	{
		return candidate;
	}

	/**
	 * @param candidate
	 */
	public void setCandidate(OleContributor candidate)
	{
		this.candidate = candidate;
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
	 * @param order
	 */
	public void setOrder(int order)
	{
		this.order = order;
	}

	/**
	 * @return
	 */
	public String getImage()
	{
		return image;
	}

	/**
	 * @param image
	 */
	public void setImage(String image)
	{
		this.image = image;
	}

	/**
	 * @return
	 */
	public int getQuestionStatus()
	{
		return questionStatus;
	}

	/**
	 * @param questionStatus
	 */
	public void setQuestionStatus(int questionStatus)
	{
		this.questionStatus = questionStatus;
	}

	/**
	 * @return
	 */
	public int getQuestionSource()
	{
		return questionSource;
	}

	/**
	 * @param questionSource
	 */
	public void setQuestionSource(int questionSource)
	{
		this.questionSource = questionSource;
	}

	/**
	 * @return
	 */
	public Long getQuestionId()
	{
		return questionId;
	}

	/**
	 * @param questionId
	 */
	public void setQuestionId(Long questionId)
	{
		this.questionId = questionId;
	}

	/**
	 * @return
	 */
	public String getQuestionText()
	{
		return questionText;
	}

	/**
	 * @param questionText
	 */
	public void setQuestionText(String questionText)
	{
		this.questionText = questionText;
	}

	/**
	 * @return
	 */
	public int getQuestionType()
	{
		return questionType;
	}

	/**
	 * @param questionType
	 */
	public void setQuestionType(int questionType)
	{
		this.questionType = questionType;
	}

	/**
	 * @return
	 */
	public String getHint()
	{
		return hint;
	}

	/**
	 * @param hint
	 */
	public void setHint(String hint)
	{
		this.hint = hint;
	}

	/**
	 * @return
	 */
	public String getPoolType()
	{
		return poolType;
	}

	/**
	 * @param poolType
	 */
	public void setPoolType(String poolType)
	{
		this.poolType = poolType;
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

	// public abstract List<Matching> getMatchingOptions();
	// public abstract List<Choice> getChoices();
}
