/**
 * Created Date: 4/15/10 7:26 PM
 * Class Name  : Candidate.java
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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:26 PM
 * @author
 * @since DGMT 1.0
 */
@Entity
@Table(name = "B_CANDIDATE_DETAILS")
public class Candidate implements Serializable
{
	/**
	 * DOCUMENT ME!
	 */
	@Id
	@Column(name = "CD_ID")
	Long id;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "CD_NAME", length = 35)
	String name;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "CD_RANK", length = 20)
	String rank;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "CD_ARM", length = 4)
	String arm;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "CD_COMM_TYPE", length = 50)
	String commType;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "CD_DATE_SENIORITY")
	@Temporal(TemporalType.DATE)
	Date seniorityDate;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "CD_COMM_DATE")
	@Temporal(TemporalType.DATE)
	Date commDate;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "CD_PERSONAL_NO", length = 12)
	String personalNo;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "CD_OLD_SS_NO", length = 12)
	String oldSSNo;

	/**
	 * DOCUMENT ME!
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "CD_DATE_OF_BIRTH")
	Date dateOfBirth;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "CD_ELIGIBLE_EXAM_CODE", length = 2)
	String eligibleExamId;

	/*@ManyToMany(targetEntity=OleSubject.class)
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@JoinColumn(name="CD_ID") 
	@Basic(fetch=FetchType.EAGER)*/
	@OneToMany(targetEntity=OleSubject.class)
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "B_CANDIDATE_SUBJECTS", joinColumns = { @JoinColumn(name = "CD_ID") }, inverseJoinColumns = { @JoinColumn(name = "SC_ID") })
	private List<OleSubject> oleSubject;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "CD_STATUS", length = 2)
	String status;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "CD_LOGIN_IP_ADDRESS", length = 20)
	String loginIPAddress;

	/**
	 * DOCUMENT ME!
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CD_CREATED_DATE")
	Date createdDate;

	/**
	 * DOCUMENT ME!
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CD_MODIFIED_DATE")
	Date modifiedDate;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "CD_CREATED_BY", length = 20)
	String createdBy;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "CD_MODIFIED_BY", length = 20)
	String modifiedBy;


	@ManyToOne
	@JoinColumn(name = "SQ_ID")
	private SecurityQuestion secQuest;
	
	@Column(name = "CD_SECURITY_ANSWER", length = 100)
	private String securityAnswer;
	
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
	public String getLoginIPAddress()
	{
		return loginIPAddress;
	}

	/**
	 * @param loginIPAddress
	 */
	public void setLoginIPAddress(String loginIPAddress)
	{
		this.loginIPAddress = loginIPAddress;
	}

	/**
	 * @return
	 */
	public List<OleSubject> getOleSubject()
	{
		return oleSubject;
	}

	/**
	 * @param subject
	 */
	public void setOleSubject(List<OleSubject> subject)
	{
		this.oleSubject = subject;
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
	public String getName()
	{
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return
	 */
	public String getRank()
	{
		return rank;
	}

	/**
	 * @param rank
	 */
	public void setRank(String rank)
	{
		this.rank = rank;
	}

	/**
	 * @return
	 */
	public String getArm()
	{
		return arm;
	}

	/**
	 * @param arm
	 */
	public void setArm(String arm)
	{
		this.arm = arm;
	}

	/**
	 * @return
	 */
	public String getCommType()
	{
		return commType;
	}

	/**
	 * @param commType
	 */
	public void setCommType(String commType)
	{
		this.commType = commType;
	}

	/**
	 * @return
	 */
	public Date getCommDate()
	{
		return commDate;
	}

	/**
	 * @param commDate
	 */
	public void setCommDate(Date commDate)
	{
		this.commDate = commDate;
	}

	/**
	 * @return
	 */
	public String getPersonalNo()
	{
		return personalNo;
	}

	/**
	 * @param personalNo
	 */
	public void setPersonalNo(String personalNo)
	{
		this.personalNo = personalNo;
	}

	/**
	 * @return
	 */
	public String getOldSSNo()
	{
		return oldSSNo;
	}

	/**
	 * @param oldSSNo
	 */
	public void setOldSSNo(String oldSSNo)
	{
		this.oldSSNo = oldSSNo;
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
	public Date getSeniorityDate()
	{
		return seniorityDate;
	}

	/**
	 * @param seniorityDate
	 */
	public void setSeniorityDate(Date seniorityDate)
	{
		this.seniorityDate = seniorityDate;
	}

	/**
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Candidate)
		{
			return (this.id == ((Candidate) obj).getId())
					&& this.personalNo
							.equals(((Candidate) obj).getPersonalNo());
		}

		return false;
	}

	/**
	 * @return
	 */
	@Override
	public int hashCode()
	{
		if (id != null)
		{
			return id.intValue();
		}

		return super.hashCode();
	}

	/**
	 * @return
	 */
	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 */
	public void setDateOfBirth(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return
	 */
	public String getEligibleExamId()
	{
		return eligibleExamId;
	}

	/**
	 * @param eligibleExamId
	 */
	public void setEligibleExamId(String eligibleExamId)
	{
		this.eligibleExamId = eligibleExamId;
	}
	

	public SecurityQuestion getSecQuest()
	{
		return secQuest;
	}

	public void setSecQuest(SecurityQuestion secQuest)
	{
		this.secQuest = secQuest;
	}

	public String getSecurityAnswer()
	{
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer)
	{
		this.securityAnswer = securityAnswer;
	}
	
	
}
