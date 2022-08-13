/**
 * Created Date: 4/15/10 7:22 PM
 * Class Name  : CandidateEnrollmentDAO.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>        <Author>        <Date>        <Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package com.dgmt.dao;

import java.util.List;

import com.dgmt.model.CandidateEnrollememt;
import com.dgmt.model.CandidateEnrollmentDetails;
import com.dgmt.model.CandidateSubjects;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:22 PM
 * @author CTE
 * @since DGMT 1.0
 */
public interface CandidateEnrollmentDAO
{
	/**
	 * Returns candidate enrollment for given examination and candidate.
	 * 
	 * @param ecId
	 *            examination id
	 * @param cdId
	 *            candidate id
	 * @return enrollment
	 */
	public CandidateEnrollememt getCandidateEnrollmentDetails(Long ecId,
			Long cdId);

	/**
	 * Gets enrollment for the given candidate id.
	 * 
	 * @param candidateId
	 *            candidate id
	 * @return
	 */
	public List<CandidateEnrollememt> checkCandidateEnrollment(long candidateId);

	/**
	 * Updates enrollment details.
	 * 
	 * @param canEnrollmentDetails
	 */
	public void updateCandidateEnrollmentDtls(
			CandidateEnrollmentDetails canEnrollmentDetails);

	/**
	 * Saves candidate enrollment.
	 * 
	 * @param candidateEnrollememt
	 */
	public void saveCandidateEnrollment(
			CandidateEnrollememt candidateEnrollememt);

	/**
	 * Saves enrollments.
	 * 
	 * @param enrollments
	 */
	public void saveEnrollments(List<CandidateEnrollememt> enrollments);

	/**
	 * Saves candidate subjects.
	 * 
	 * @param candSubs
	 */
	public void saveCandidateSubjects(List<CandidateSubjects> candSubs);
}
