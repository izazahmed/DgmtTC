/**
 * Created Date: 4/15/10 7:31 PM
 * Class Name  : CandidateEnrollmentServiceImpl.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>        <Author>        <Date>        <Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package com.dgmt.service.impl;

import java.util.List;

import com.dgmt.dao.CandidateEnrollmentDAO;
import com.dgmt.model.CandidateEnrollememt;
import com.dgmt.model.CandidateEnrollmentDetails;
import com.dgmt.service.api.CandidateEnrollmentService;
import com.dgmt.util.Log;
import com.dgmt.util.LogFactory;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:31 PM
 * @author CTE
 * @since DGMT 1.0
 */
public class CandidateEnrollmentServiceImpl implements
		CandidateEnrollmentService
{
	private Log logger = LogFactory
			.getLog(CandidateEnrollmentServiceImpl.class);

	private CandidateEnrollmentDAO candidateEnrollmentDAO;

	/**
	 * @return
	 */
	public CandidateEnrollmentDAO getCandidateEnrollmentDAO()
	{
		return candidateEnrollmentDAO;
	}

	/**
	 * @param candidateEnrollmentDAO
	 */
	public void setCandidateEnrollmentDAO(
			CandidateEnrollmentDAO candidateEnrollmentDAO)
	{
		this.candidateEnrollmentDAO = candidateEnrollmentDAO;
	}

	/**
	 * Returns candidate enrollment for given examination and candidate.
	 * 
	 * @param ecId
	 *            examination id
	 * @param cdId
	 *            candidate id
	 * @return enrollment
	 */
	@Override
	public CandidateEnrollememt getCandidateEnrollmentDetails(Long ecId,
			Long cdId)
	{
		return candidateEnrollmentDAO.getCandidateEnrollmentDetails(ecId, cdId);
	}

	/**
	 * Gets enrollment for the given candidate id.
	 * 
	 * @param candidateId
	 *            candidate id
	 * @return
	 */
	@Override
	public List<CandidateEnrollememt> checkCandidateEnrollment(long candidateId)
	{
		return candidateEnrollmentDAO.checkCandidateEnrollment(candidateId);
	}

	/**
	 * Updates enrollment details.
	 * 
	 * @param canEnrollmentDetails
	 */
	@Override
	public void updateCandidateEnrollmentDtls(
			CandidateEnrollmentDetails canEnrollmentDetails)
	{
		candidateEnrollmentDAO
				.updateCandidateEnrollmentDtls(canEnrollmentDetails);
	}

	/**
	 * Saves candidate enrollment.
	 * 
	 * @param candidateEnrollememt
	 */
	@Override
	public void saveCandidateEnrollment(
			CandidateEnrollememt candidateEnrollememt)
	{
		candidateEnrollmentDAO.saveCandidateEnrollment(candidateEnrollememt);
	}
}
