/**
 * Created Date: 5/6/10 11:09 AM
 * Class Name  : GeneratedQuePaper.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>        <Author>        <Date>        <Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package com.dgmt.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
import org.hibernate.annotations.OrderBy;

/**
 * Description:
 * 
 * @version 1.0 5/6/10 11:09 AM
 * @author
 * @since DGMT 1.0
 */
@Entity
@Table(name = "B_GENERATED_QUESTION_PAPER")
public class GeneratedQuePaper implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6778765151096106090L;

	@Id
	@Column(name = "GQP_ID")
	private Long id;

	@ManyToOne()
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "EP_ID")
	private ExamPaper examPaper;

	@ManyToOne
	@JoinColumn(name = "CD_ID")
	private Candidate candidate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "GQP_START_TIME")
	private Date startTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "GQP_END_TIME")
	private Date endTime;

	@Column(name = "GQP_MARKS")
	private Float marks;

	@ManyToOne
	@JoinColumn(name = "SD_ID")
	private Status status;

	@OneToMany(cascade = { javax.persistence.CascadeType.ALL })
	// @Cascade( { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "GQP_ID", nullable=false)
	@OrderBy(clause = "GQPD_ID,GQPD_QUESTION_SR_NO")
	@CollectionOfElements(targetElement = GeneratedQuePaperDtls.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<GeneratedQuePaperDtls> qpDetails = new ArrayList<GeneratedQuePaperDtls>(
			0);

	@ManyToOne
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinColumn(name = "CE_ID")
	private CandidateEnrollememt candidateEnrollememt;

	@Column(name = "GQP_OBJ_MARKS")
	private Float objMarks;

	@Column(name = "GQP_SUB_MARKS")
	private Float subMarks;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "GQP_LAST_UPDATED_QUESTION_TIME")
	private Date lastUpdatedQuestionTime;
	
	@Column(name = "GQP_LAST_ACCESS_QID")
	private String questionId;

	@Column(name = "GQP_MODIFIED_BY", length = 20)
	private String modifiedBy;
	
	@Column(name = "GQP_TRANSFERRED", length = 1)
	private String transfered;

	/**
	 * DOCUMENT ME!
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "GQP_MODIFIED_DATE")
	private Date modifiedDate;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "GQP_CREATED_BY", length = 20)
	private String createdBy;

	/**
	 * DOCUMENT ME!
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "GQP_CREATED_DATE")
	private Date createdDate;

	@Column(name = "GQP_CED_ID")
	private Long candEnrollDtlId;
	
	@Column(name = "GQP_TIME_LEFT_IN_SECS")
	private Long timeLeftInSecs;
	
	@Column(name = "GQP_NETWORK_FAIL_IN_SECS")
	private Long networkFailTimeInSec;
	
	public Long getNetworkFailTimeInSec()
	{
		return networkFailTimeInSec;
	}
	
	public void setNetworkFailTimeInSec(Long networkFailTimeInSec)
	{
		this.networkFailTimeInSec = networkFailTimeInSec;
	}
	
	public Long getTimeLeftInSecs() 
	{
		return timeLeftInSecs;
	}

	public void setTimeLeftInSecs(Long timeLeftInSecs) 
	{
		this.timeLeftInSecs = timeLeftInSecs;
	}
	
	public Long getCandEnrollDtlId() 
	{
		return candEnrollDtlId;
	}

	public void setCandEnrollDtlId(Long candEnrollDtlId) 
	{
		this.candEnrollDtlId = candEnrollDtlId;
	}
	/**
	 * @return
	 */
	public String getQuestionId()
	{
		return questionId;
	}

	/**
	 * @param questionId
	 */
	public void setQuestionId(String questionId)
	{
		this.questionId = questionId;
	}

	/**
	 * @return
	 */
	public Date getLastUpdatedQuestionTime()
	{
		return lastUpdatedQuestionTime;
	}

	/**
	 * @param lastUpdatedQuestionTime
	 */
	public void setLastUpdatedQuestionTime(Date lastUpdatedQuestionTime)
	{
		this.lastUpdatedQuestionTime = lastUpdatedQuestionTime;
	}

	/**
	 * @return
	 */
	public Float getObjMarks()
	{
		return objMarks;
	}

	/**
	 * @param objMarks
	 */
	public void setObjMarks(Float objMarks)
	{
		this.objMarks = objMarks;
	}

	/**
	 * @return
	 */
	public Float getSubMarks()
	{
		return subMarks;
	}

	/**
	 * @param subMarks
	 */
	public void setSubMarks(Float subMarks)
	{
		this.subMarks = subMarks;
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
	public ExamPaper getExamPaper()
	{
		return examPaper;
	}

	/**
	 * @param examPaper
	 */
	public void setExamPaper(ExamPaper examPaper)
	{
		this.examPaper = examPaper;
	}

	/**
	 * @return
	 */
	public Candidate getCandidate()
	{
		return candidate;
	}

	/**
	 * @param candidate
	 */
	public void setCandidate(Candidate candidate)
	{
		this.candidate = candidate;
	}

	/**
	 * @return
	 */
	public Date getStartTime()
	{
		return startTime;
	}

	/**
	 * @param startTime
	 */
	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}

	/**
	 * @return
	 */
	public Date getEndTime()
	{
		return endTime;
	}

	/**
	 * @param endTime
	 */
	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}

	/**
	 * @return
	 */
	public Float getMarks()
	{
		return marks;
	}

	/**
	 * @param marks
	 */
	public void setMarks(Float marks)
	{
		this.marks = marks;
	}

	/**
	 * @return
	 */
	public Status getStatus()
	{
		return status;
	}

	/**
	 * @param status
	 */
	public void setStatus(Status status)
	{
		this.status = status;
	}

	/**
	 * @return
	 */
	public List<GeneratedQuePaperDtls> getQpDetails()
	{
		return qpDetails;
	}

	/**
	 * @param qpDetails
	 */
	public void setQpDetails(List<GeneratedQuePaperDtls> qpDetails)
	{
		this.qpDetails = qpDetails;
	}

	/**
	 * @return
	 */
	public CandidateEnrollememt getCandidateEnrollememt()
	{
		return candidateEnrollememt;
	}

	/**
	 * @param candidateEnrollememt
	 */
	public void setCandidateEnrollememt(
			CandidateEnrollememt candidateEnrollememt)
	{
		this.candidateEnrollememt = candidateEnrollememt;
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
	
	public String getTransfered()
	{
		return transfered;
	}
	
	public void setTransfered(String transfered)
	{
		this.transfered = transfered;
	}
}
