/**
 * Created Date: 9/24/10 10:37 AM
 * Class Name  : CandidateEnrollmentDetails.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises Ltd.,All rights reserved.
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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Description:
 * 
 * @version 1.0 9/24/10 10:37 AM
 * @author CTE
 * @since DGMT 1.0
 */
@Entity
@Table(name = "B_CANDIDATE_ENROLLMENT_DETAILS")
public class CandidateEnrollmentDetails implements Serializable
{
	/**
	 * DOCUMENT ME!
	 */
	@Id
	@Column(name = "CED_ID")
	Long id;

	/**  */
	@Column(name = "CED_EXAM_DATE")
	@Temporal(TemporalType.DATE)
	Date enrollmetExamDate;

	@ManyToOne
	@JoinColumn(name = "SC_ID")
	@LazyCollection(LazyCollectionOption.FALSE)
	private OleSubject subject;

	/**  */
	@Column(name = "SLOT_FOR_SUB_ID")
	Long slotUserForSubjectId;

	/**
	 * DOCUMENT ME!
	 */
	@ManyToOne
	@Cascade( { CascadeType.SAVE_UPDATE })
	@CollectionOfElements(targetElement = com.dgmt.model.TimeSlot.class)
	@JoinColumn(name = "TS_ID")
	@LazyCollection(LazyCollectionOption.FALSE)
	TimeSlot timeSlots;

	/**
	 * @return
	 */
	public Long getSlotUserForSubjectId()
	{
		return slotUserForSubjectId;
	}

	/**
	 * @param slotUserForSubjectId
	 */
	public void setSlotUserForSubjectId(Long slotUserForSubjectId)
	{
		this.slotUserForSubjectId = slotUserForSubjectId;
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
	public TimeSlot getTimeSlots()
	{
		return timeSlots;
	}

	/**
	 * @param timeSlots
	 */
	public void setTimeSlots(TimeSlot timeSlots)
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
	public Date getEnrollmetExamDate()
	{
		return enrollmetExamDate;
	}

	/**
	 * @param enrollmetExamDate
	 */
	public void setEnrollmetExamDate(Date enrollmetExamDate)
	{
		this.enrollmetExamDate = enrollmetExamDate;
	}
}
