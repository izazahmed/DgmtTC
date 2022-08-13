/**
 * Created Date: 4/13/10 3:41 PM
 * Class Name  : ExamPaperSection.java
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
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OrderBy;

/**
 * Description:
 * 
 * @version 1.0 4/13/10 3:41 PM
 * @author aloni
 * @since DGMT 1.0
 */
@Entity
@Table(name = "B_EXAM_PAPER_SECTION")
public class ExamPaperSection implements Serializable
{
	@Id
	@Column(name = "EPS_ID")
	private Long id;

	@Column(name = "EPS_TITLE", length = 30)
	private String title;

	@Column(name = "EPS_TYPE", length = 10)
	private String type;

	@Column(name = "EPS_QUEST_COUNT")
	private int questionCount;

	@Column(name = "EPS_MARKS_PER_QUEST")
	private int marksPerQuestion;

	@Column(name = "EPS_TOTAL_MARKS")
	private int totalMarks;

	@OneToMany(/*fetch=FetchType.EAGER*/)
	@Cascade( { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@CollectionOfElements(targetElement = com.dgmt.model.SectionPoolSelection.class)
	@JoinColumn(name = "EPS_ID", nullable = false)
	@OrderBy(clause = "SP_ID")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<SectionPoolSelection> sectionPools = new LinkedHashSet<SectionPoolSelection>(
			0);

	@Column(name = "EPS_MODIFIED_BY", length = 20)
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EPS_MODIFIED_DATE")
	private Date modifiedDate;

	@Column(name = "EPS_CREATED_BY", length = 20)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EPS_CREATED_DATE")
	private Date createdDate;

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
	public int getQuestionCount()
	{
		return questionCount;
	}

	/**
	 * @param questionCount
	 */
	public void setQuestionCount(int questionCount)
	{
		this.questionCount = questionCount;
	}

	/**
	 * @return
	 */
	public int getMarksPerQuestion()
	{
		return marksPerQuestion;
	}

	/**
	 * @param marksPerQuestion
	 */
	public void setMarksPerQuestion(int marksPerQuestion)
	{
		this.marksPerQuestion = marksPerQuestion;
	}

	/**
	 * @return
	 */
	public int getTotalMarks()
	{
		return totalMarks;
	}

	/**
	 * @param totalMarks
	 */
	public void setTotalMarks(int totalMarks)
	{
		this.totalMarks = totalMarks;
	}

	/**
	 * @return Returns the sectionPools.
	 */
	public Set<SectionPoolSelection> getSectionPools()
	{
		return sectionPools;
	}

	/**
	 * @param sectionPools
	 *            The sectionPools to set.
	 */
	public void setSectionPools(Set<SectionPoolSelection> sectionPools)
	{
		this.sectionPools = sectionPools;
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
}
