/**
 * Created Date: 4/15/10 7:30 PM
 * Class Name  : CandidateEnrollmentService.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>        <Author>        <Date>        <Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package com.dgmt.service.api;

import java.util.List;

import com.dgmt.model.CandidateEnrollememt;
import com.dgmt.model.CandidateEnrollmentDetails;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:30 PM
 * @author CTE
 * @since DGMT 1.0
 */
public interface CandidateEnrollmentService
{
	/**
	 * Returns candidate enrollment details.
	 * 
	 * @param ec_id
	 *            examination id
	 * @param cd_id
	 *            candidate id whose enrollment to be fetched
	 * @return enrollment details of the candidate
	 */
	public CandidateEnrollememt getCandidateEnrollmentDetails(Long ec_id,
			Long cd_id);

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
	 * Save candidate enrollment.
	 * 
	 * @param candidateEnrollememt
	 *            enrollment to be saved
	 */
	public void saveCandidateEnrollment(
			CandidateEnrollememt candidateEnrollememt);
}
