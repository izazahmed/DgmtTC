/**
 * Created Date: 11/25/10 11:26 AM
 * Class Name  : TestCenterMessageDTO.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>        <Author>        <Date>        <Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package com.dgmt.dto;

import java.io.Serializable;

import java.util.List;

import com.dgmt.model.CandidateEnrollememt;
import com.dgmt.model.CandidateSubjects;
import com.dgmt.model.GeneratedQuePaper;
import com.dgmt.model.ProctorKey;
import com.dgmt.model.UserLogin;
import com.dgmt.model.UserRole;

/**
 * Description:
 * 
 * @version 1.0 11/25/10 11:26 AM
 * @author aloni
 * @since DGMT 1.0
 */
public class TestCenterMessageDTO implements Serializable
{
	private List<CandidateEnrollememt> enrollments;

	private List<UserRole> userRoles;

	private List<GeneratedQuePaper> questionPapers;

	private List<ProctorKey> proctorKeys;

	private List<CandidateSubjects> candidateSubjects;

	private String testCenterId;

	private List<UserLogin> userLogin;

	/**
	 * @return
	 */
	public List<CandidateEnrollememt> getEnrollments()
	{
		return enrollments;
	}

	/**
	 * @param enrollments
	 */
	public void setEnrollments(List<CandidateEnrollememt> enrollments)
	{
		this.enrollments = enrollments;
	}

	/**
	 * @return
	 */
	public List<UserRole> getUserRoles()
	{
		return userRoles;
	}

	/**
	 * @param userLogins
	 */
	public void setUserRoles(List<UserRole> userRoles)
	{
		this.userRoles = userRoles;
	}

	/**
	 * @return
	 */
	public List<GeneratedQuePaper> getQuestionPapers()
	{
		return questionPapers;
	}

	/**
	 * @param questionPapers
	 */
	public void setQuestionPapers(List<GeneratedQuePaper> questionPapers)
	{
		this.questionPapers = questionPapers;
	}

	/**
	 * @return
	 */
	public List<ProctorKey> getProctorKeys()
	{
		return proctorKeys;
	}

	/**
	 * @param proctorKeys
	 */
	public void setProctorKeys(List<ProctorKey> proctorKeys)
	{
		this.proctorKeys = proctorKeys;
	}

	/**
	 * @return
	 */
	public List<CandidateSubjects> getCandidateSubjects()
	{
		return candidateSubjects;
	}

	/**
	 * @param candidateSubjects
	 */
	public void setCandidateSubjects(List<CandidateSubjects> candidateSubjects)
	{
		this.candidateSubjects = candidateSubjects;
	}

	/**
	 * @param testCenterId
	 */
	public void setTestCenterId(String testCenterId)
	{
		this.testCenterId = testCenterId;
	}

	/**
	 * @return
	 */
	public String getTestCenterId()
	{
		return testCenterId;
	}

	/**
	 * @return
	 */
	public List<UserLogin> getUserLogin()
	{
		return userLogin;
	}

	/**
	 * @param userLogin
	 */
	public void setUserLogin(List<UserLogin> userLogin)
	{
		this.userLogin = userLogin;
	}
}
