/**
 * Created Date: 4/15/10 7:27 PM
 * Class Name  : ExamSubject.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>	<Author>	<Date>	<Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.dgmt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:27 PM
 * @author
 * @since DGMT 1.0
 */
@Entity
@Table(name = "B_EXAM_SUBJECT")
public class OleExamSubject implements java.io.Serializable
{
	private Long examSubjectId;

	private OleExam exam;

	private OleSubject subject;

	/**
	 * @return
	 */
	@Id
	@Column(name = "ESR_ID", unique = true, nullable = false)
	public Long getExamSubjectId()
	{
		return this.examSubjectId;
	}

	/**
	 * @return
	 */
	@ManyToOne/*(fetch = FetchType.EAGER)*/
	@LazyToOne(LazyToOneOption.FALSE)
	@JoinColumn(name = "EC_ID", nullable = false)
	public OleExam getExam()
	{
		return this.exam;
	}

	/**
	 * @return
	 */
	@ManyToOne/*(fetch = FetchType.EAGER)*/
	@LazyToOne(LazyToOneOption.FALSE)
	@JoinColumn(name = "SC_ID", nullable = false)
	public OleSubject getSubject()
	{
		return this.subject;
	}

	/**
	 * @param exam
	 */
	public void setExam(OleExam exam)
	{
		this.exam = exam;
	}

	/**
	 * @param examSubjectId
	 */
	public void setExamSubjectId(Long examSubjectId)
	{
		this.examSubjectId = examSubjectId;
	}

	/**
	 * @param subject
	 */
	public void setSubject(OleSubject subject)
	{
		this.subject = subject;
	}
}
