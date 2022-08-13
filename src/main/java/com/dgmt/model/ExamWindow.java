/**
 * Created Date: 4/15/10 7:28 PM
 * Class Name  : ExamWindow.java
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
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.OrderBy;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:28 PM
 * @author
 * @since DGMT 1.0
 */
@Entity
@Table(name = "B_EXAM_WINDOW")
public class ExamWindow extends BaseExamWindow implements Serializable
{
	/**
         *
         */
	private static final long serialVersionUID = 1L;

	/**
	 * DOCUMENT ME!
	 */
	@Id
	@Column(name = "EW_ID")
	Long id;

	/** Exam window start date. */
	@Column(name = "EW_START_DATE")
	@Temporal(TemporalType.DATE)
	Date startDate;

	/** Exam window end date. */
	@Column(name = "EW_END_DATE")
	@Temporal(TemporalType.DATE)
	Date endDate;

	/** Exam duration. */
	@Column(name = "EW_DURATION")
	float duration;

	/** Pass percentage for objective question paper. */
	@Column(name = "EW_PASS_PERCENT_OBJ")
	int passPctObj;

	/** Pass percentage for subjective question paper. */
	@Column(name = "EW_PASS_PERCENT_SUB")
	int passPctSub;

	/**  */
	@Column(name = "EW_RESULTS")
	// @Enumerated(EnumType.STRING) //@todo use enums
	int showResults;

	/** Exam window start date. */
	@Column(name = "EW_ENROLLW_START_DATE")
	@Temporal(TemporalType.DATE)
	Date enrollStartDate;

	/** Exam window start date. */
	@Column(name = "EW_ENROLLW_END_DATE")
	@Temporal(TemporalType.DATE)
	Date enrollEndDate;

	/**
	 * DOCUMENT ME!
	 */
	@ManyToOne
	@Cascade( { CascadeType.SAVE_UPDATE })
	@LazyToOne(LazyToOneOption.FALSE)
	@JoinColumn(name = "EC_ID")
	OleExam exam;

	/** Last modified date. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EW_MODIFIED_DATE")
	Date modifiedDate;

	/** Created date. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EW_CREATED_DATE")
	Date createdDate;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "EW_STATUS", length = 2)
	String status;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "EW_BLOCKED_DATES")
	String blockedDates;

	/**
	 * DOCUMENT ME!
	 */
	@OneToMany
	@Cascade( { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@CollectionOfElements(targetElement = com.dgmt.model.TimeSlot.class)
	@JoinColumn(name = "EW_ID", nullable = false)
	@LazyCollection(LazyCollectionOption.FALSE)
	@OrderBy(clause = "TS_ID")
	Set<TimeSlot> timeSlots = new LinkedHashSet<TimeSlot>(0);

	// @ManyToMany(cascade = CascadeType.ALL)
	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "EW_INSTRUCTIONS")
	String instructions;

	@Column(name = "EW_CREATED_BY", length = 20)
	private String createdBy;

	@Column(name = "EW_MODIFIED_BY", length = 20)
	private String modifiedBy;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "EW_ATTEMPTS_ALLOWED")
	int attemptsAllowed;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "EW_MAX_YEARS_PASS_SUBJECTS")
	int maxYears;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "EW_DAYS_BTW_ENEND_EXMSTRT")
	int daysBwEnrolEndExamStrtDate;

	/**
	 * @return
	 */
	public Set<TimeSlot> getTimeSlots()
	{
		return timeSlots;
	}

	/**
	 * @param timeSlots
	 */
	public void setTimeSlots(Set<TimeSlot> timeSlots)
	{
		this.timeSlots = timeSlots;
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
	public Date getStartDate()
	{
		return startDate;
	}

	/**
	 * @param startDate
	 */
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	/**
	 * @return
	 */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * @param endDate
	 */
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	/**
	 * @return
	 */
	public float getDuration()
	{
		return duration;
	}

	/**
	 * @param duration
	 */
	public void setDuration(float duration)
	{
		this.duration = duration;
	}

	/**
	 * @return
	 */
	public int getPassPctObj()
	{
		return passPctObj;
	}

	/**
	 * @param passPctObj
	 */
	public void setPassPctObj(int passPctObj)
	{
		this.passPctObj = passPctObj;
	}

	/**
	 * @return
	 */
	public int getPassPctSub()
	{
		return passPctSub;
	}

	/**
	 * @param passPctSub
	 */
	public void setPassPctSub(int passPctSub)
	{
		this.passPctSub = passPctSub;
	}

	/**
	 * @return
	 */
	public int getShowResults()
	{
		return showResults;
	}

	/**
	 * @param showResults
	 */
	public void setShowResults(int showResults)
	{
		this.showResults = showResults;
	}

	/**
	 * @return
	 */
	public Date getEnrollStartDate()
	{
		return enrollStartDate;
	}

	/**
	 * @param enrollStartDate
	 */
	public void setEnrollStartDate(Date enrollStartDate)
	{
		this.enrollStartDate = enrollStartDate;
	}

	/**
	 * @return
	 */
	public Date getEnrollEndDate()
	{
		return enrollEndDate;
	}

	/**
	 * @param enrollEndDate
	 */
	public void setEnrollEndDate(Date enrollEndDate)
	{
		this.enrollEndDate = enrollEndDate;
	}

	/**
	 * @return
	 */
	public OleExam getExam()
	{
		return exam;
	}

	/**
	 * @param exam
	 */
	public void setExam(OleExam exam)
	{
		this.exam = exam;
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
	public String getBlockedDates()
	{
		return blockedDates;
	}

	/**
	 * @param blockedDates
	 */
	public void setBlockedDates(String blockedDates)
	{
		this.blockedDates = blockedDates;
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
	public int getAttemptsAllowed()
	{
		return attemptsAllowed;
	}

	/**
	 * @param attemptsAllowed
	 */
	public void setAttemptsAllowed(int attemptsAllowed)
	{
		this.attemptsAllowed = attemptsAllowed;
	}

	/**
	 * @return
	 */
	public int getMaxYears()
	{
		return maxYears;
	}

	/**
	 * @param maxYears
	 */
	public void setMaxYears(int maxYears)
	{
		this.maxYears = maxYears;
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
	public int getDaysBwEnrolEndExamStrtDate()
	{
		return daysBwEnrolEndExamStrtDate;
	}

	/**
	 * @param daysBwEnrolEndExamStrtDate
	 */
	public void setDaysBwEnrolEndExamStrtDate(int daysBwEnrolEndExamStrtDate)
	{
		this.daysBwEnrolEndExamStrtDate = daysBwEnrolEndExamStrtDate;
	}
}
