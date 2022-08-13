/**
 * Created Date: 5/6/10 11:35 AM
 * Class Name  : GeneratedQuePaperDtls.java
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
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.jasypt.hibernate.type.EncryptedStringType;

/**
 * Description:
 * 
 * @version 1.0 5/6/10 11:35 AM
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
@Table(name = "B_GENRAT_QUESTION_PAPER_DTLS")
public class GeneratedQuePaperDtls implements Serializable
{
	@Id
	@Column(name = "GQPD_ID")
	private Long id;

	@Column(name = "GQPD_QUESTION_SR_NO")
	private Long questSrNo;

	@Column(name = "GQPD_SECTION_NO")
	private Long sectionNo;

	@ManyToOne
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "QS_ID")
	private OleQuestion question;

	@Column(name = "GQPD_ANSWER", length = 5360)
	@Type(type="encryptedString")
	private String answer;

	@Column(name = "GQPD_MARKS")
	private Double marks;

	@Column(name = "GQPD_MAX_MARKS")
	private Double maxMarks;
	
	@Column(name = "GQPD_MARK_FOR_REVIEW")
	private String markForReview;

	@Column(name = "GQPD_MODIFIED_BY", length = 20)
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "GQPD_MODIFIED_DATE")
	private Date modifiedDate;

	@Column(name = "GQPD_CREATED_BY", length = 20)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "GQPD_CREATED_DATE")
	private Date createdDate;

	@Column(name = "GQPD_VISITED", length = 1)
	private String visited;

	
	public String getMarkForReview() 
	{
		return markForReview;
	}

	public void setMarkForReview(String markForReview) {
		this.markForReview = markForReview;
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
	public Long getQuestSrNo()
	{
		return questSrNo;
	}

	/**
	 * @param questSrNo
	 */
	public void setQuestSrNo(Long questSrNo)
	{
		this.questSrNo = questSrNo;
	}

	/**
	 * @return
	 */
	public Long getSectionNo()
	{
		return sectionNo;
	}

	/**
	 * @param sectionNo
	 */
	public void setSectionNo(Long sectionNo)
	{
		this.sectionNo = sectionNo;
	}

	/**
	 * @return
	 */
	public OleQuestion getQuestion()
	{
		return question;
	}

	/**
	 * @param question
	 */
	public void setQuestion(OleQuestion question)
	{
		this.question = question;
	}

	/**
	 * @return
	 */
	public String getAnswer()
	{
		return answer;
	}

	/**
	 * @param answer
	 */
	public void setAnswer(String answer)
	{
		this.answer = answer;
	}

	/**
	 * @return
	 */
	public Double getMarks()
	{
		return marks;
	}

	/**
	 * @param marks
	 */
	public void setMarks(Double marks)
	{
		this.marks = marks;
	}

	/**
	 * @return
	 */
	public Double getMaxMarks()
	{
		return maxMarks;
	}

	/**
	 * @param maxMarks
	 */
	public void setMaxMarks(Double maxMarks)
	{
		this.maxMarks = maxMarks;
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
	
	/**
	 * @param visited
	 */
	public void setVisited(String visited)
	{
		this.visited = visited;
	}
	
	/**
	 * @return
	 */
	public String getVisited()
	{
		return visited;
	}
}
