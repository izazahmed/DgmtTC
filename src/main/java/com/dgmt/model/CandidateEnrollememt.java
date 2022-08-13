/**
 * Created Date: 4/15/10 7:26 PM
 * Class Name  : CandidateEnrollememt.java
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:26 PM
 * @author CTE
 * @since DGMT 1.0
 */
@Entity
@Table(name = "B_CANDIDATE_ENROLLMENT", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"EW_ID", "CD_ID" }) })
public class CandidateEnrollememt implements Serializable
{
	/**
	 * DOCUMENT ME!
	 */
	@Id
	@Column(name = "CE_ID")
	Long id;

	/**
	 * DOCUMENT ME!
	 */
	@OneToOne
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "CD_ID")
	Candidate candidate;

	/**
	 * DOCUMENT ME!
	 */
	@ManyToOne
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "EW_ID")
	ExamWindow examWindow;

	/**
	 * DOCUMENT ME!
	 */
	@OneToMany(cascade = javax.persistence.CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "CE_ID", nullable = false)
	List<CandidateEnrollmentDetails> candidateEnrollmentDetails;

	/**
	 * DOCUMENT ME!
	 */
	@ManyToOne
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "TC_ID")
	TestCenter testCenter;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "CE_SECURE_KEY", length = 8)
	String secureKey;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "CE_APPROVING_OFFICER", length = 30)
	String approvingOfficer;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "CE_UNIT", length = 30)
	String unit;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "CE_IMMD_SUPR_FMN", length = 30)
	String immdSuprFmn;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "CE_COMD", length = 3)
	String comd;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "CE_EXAM_CORRES_ADDR", length = 100)
	String examCorresAddr;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "CE_APPOINTMENT", length = 30)
	String appointment;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "CE_ELIGIBLE", length = 2)
	String eligible;

	/**  */
	@Column(name = "CE_STATUS", length = 2)
	String status;

	/**  */
	@Column(name = "CE_CREATED_BY", length = 20)
	String createdBy;

	/**  */
	@Column(name = "CE_MODIFIED_BY", length = 20)
	String modifiedBy;

	/**  */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CE_CREATED_DATE")
	Date createdDate;

	/**  */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CE_MODIFIED_DATE")
	Date modifiedDate;
	
	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "CD_MOBILE_NO", length = 2)
	String mobileNo;

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
	public TestCenter getTestCenter()
	{
		return testCenter;
	}

	/**
	 * @param testCenter
	 */
	public void setTestCenter(TestCenter testCenter)
	{
		this.testCenter = testCenter;
	}

	/**
	 * @return
	 */
	public String getEligible()
	{
		return eligible;
	}

	/**
	 * @param eligible
	 */
	public void setEligible(String eligible)
	{
		this.eligible = eligible;
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
	public String getAppointment()
	{
		return appointment;
	}

	/**
	 * @param appointment
	 */
	public void setAppointment(String appointment)
	{
		this.appointment = appointment;
	}

	/**
	 * @return
	 */
	public String getSecureKey()
	{
		return secureKey;
	}

	/**
	 * @param secureKey
	 */
	public void setSecureKey(String secureKey)
	{
		this.secureKey = secureKey;
	}

	/**
	 * @return
	 */
	public String getApprovingOfficer()
	{
		return approvingOfficer;
	}

	/**
	 * @param approvingOfficer
	 */
	public void setApprovingOfficer(String approvingOfficer)
	{
		this.approvingOfficer = approvingOfficer;
	}

	/**
	 * @return
	 */
	public String getUnit()
	{
		return unit;
	}

	/**
	 * @param unit
	 */
	public void setUnit(String unit)
	{
		this.unit = unit;
	}

	/**
	 * @return
	 */
	public String getImmdSuprFmn()
	{
		return immdSuprFmn;
	}

	/**
	 * @param immdSuprFmn
	 */
	public void setImmdSuprFmn(String immdSuprFmn)
	{
		this.immdSuprFmn = immdSuprFmn;
	}

	/**
	 * @return
	 */
	public String getComd()
	{
		return comd;
	}

	/**
	 * @param comd
	 */
	public void setComd(String comd)
	{
		this.comd = comd;
	}

	/**
	 * @return
	 */
	public String getExamCorresAddr()
	{
		return examCorresAddr;
	}

	/**
	 * @param examCorresAddr
	 */
	public void setExamCorresAddr(String examCorresAddr)
	{
		this.examCorresAddr = examCorresAddr;
	}

	/**
	 * @return
	 */
	public List<CandidateEnrollmentDetails> getCandidateEnrollmentDetails()
	{
		return candidateEnrollmentDetails;
	}

	/**
	 * @param candidateEnrollmentDetails
	 */
	public void setCandidateEnrollmentDetails(
			List<CandidateEnrollmentDetails> candidateEnrollmentDetails)
	{
		this.candidateEnrollmentDetails = candidateEnrollmentDetails;
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
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * @param mobileNo
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
}
