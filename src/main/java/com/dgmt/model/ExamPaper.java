/**
 * Created Date: 4/13/10 3:41 PM
 * Class Name  : ExamPaper.java
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "B_EXAM_PAPER")
public class ExamPaper implements Serializable
{
	@Id
	@Column(name = "EP_ID")
	private Long id;

	@Column(name = "EP_TITLE", length = 50 /* , unique = true */)
	private String title;

	@OneToOne
	@JoinColumn(name = "SC_ID")
	private OleSubject subject;

	@ManyToOne
	@JoinColumn(name = "EW_ID")
	private ExamWindow examWindow;

	@Column(name = "EP_INSTRUCTIONS", length = 250, nullable = true)
	private String instructions;

	@Column(name = "EP_STATUS", length = 2, nullable = true)
	private String status;

	@Column(name = "EP_TOTAL_QUESTIONS")
	private int totalQueCount;

	@Column(name = "EP_TOTAL_MARKS")
	private int totalMarks;

	@Column(name = "EP_ENROLLMENT_CNT")
	private int enrollmentCount;

	@OneToMany(cascade = { javax.persistence.CascadeType.ALL })
	@Cascade( { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@CollectionOfElements(targetElement = com.dgmt.model.ExamPaperSection.class)
	@JoinColumn(name = "EP_ID", nullable=false)
	@OrderBy(clause = "EPS_ID")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<ExamPaperSection> sections = new LinkedHashSet<ExamPaperSection>(
			0);

	@Column(name = "EP_MODIFIED_BY", length = 20)
	private String modifiedBy;

	/**
	 * DOCUMENT ME!
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EP_MODIFIED_DATE")
	private Date modifiedDate;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "EP_CREATED_BY", length = 20)
	private String createdBy;

	/**
	 * DOCUMENT ME!
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EP_CREATED_DATE")
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
	 * @return
	 */
	public ExamWindow getExamWindow()
	{
		return examWindow;
	}

	/**
	 * @param examWindow
	 */
	public void setExamWindow(ExamWindow examWindow)
	{
		this.examWindow = examWindow;
	}

	/**
	 * @return
	 */
	public String getInstructions()
	{
		return instructions;
	}

	/**
	 * @param instructions
	 */
	public void setInstructions(String instructions)
	{
		this.instructions = instructions;
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

	/**
	 * @return
	 */
	public int getTotalQueCount()
	{
		return totalQueCount;
	}

	/**
	 * @param totalQueCount
	 */
	public void setTotalQueCount(int totalQueCount)
	{
		this.totalQueCount = totalQueCount;
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
	 * @return Returns the sections.
	 */
	public Set<ExamPaperSection> getSections()
	{
		return sections;
	}

	/**
	 * @param sections
	 *            The sections to set.
	 */
	public void setSections(Set<ExamPaperSection> sections)
	{
		this.sections = sections;
	}

	/**
	 * @return the enrollmentCount
	 */
	public int getEnrollmentCount()
	{
		return enrollmentCount;
	}

	/**
	 * @param enrollmentCount
	 *            the enrollmentCount to set
	 */
	public void setEnrollmentCount(int enrollmentCount)
	{
		this.enrollmentCount = enrollmentCount;
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
