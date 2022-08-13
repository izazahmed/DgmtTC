/**
 * Created Date: 4/15/10 7:26 PM
 * Class Name  : CandidateSubjects.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>        <Author>        <Date>        <Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package com.dgmt.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @version 1.0 4/15/10 7:26 PM
 * @author
 * @since DGMT 1.0
 */
@Entity
@Table(name = "B_CANDIDATE_SUBJECTS")
public class CandidateSubjects implements Serializable
{
	/**  */
	@Id
	//@SequenceGenerator(name = "candSubSeq", sequenceName = "CANDIDATE_SUB_SEQ")
	//@GeneratedValue(generator = "candSubSeq")
	@Column(name = "CS_ID")
	Long id;

	/**  */
	@ManyToOne
	/* (fetch = FetchType.EAGER) */
	@LazyToOne(LazyToOneOption.FALSE)
	//@JoinColumn(name = "CD_ID", nullable = false)
	@JoinColumn(name = "CD_ID")
	Candidate candidate;

	/**  */
	@ManyToOne
	/* (fetch = FetchType.EAGER) */
	@LazyToOne(LazyToOneOption.FALSE)
	@JoinColumn(name = "SC_ID", nullable = false)
	OleSubject subject;

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
}
