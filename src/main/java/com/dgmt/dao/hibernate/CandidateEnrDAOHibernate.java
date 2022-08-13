/**
 * Created Date: 4/15/10 7:24 PM
 * Class Name  : CandidateEnrDAOHibernate.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>        <Author>        <Date>        <Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package com.dgmt.dao.hibernate;

import java.util.List;

import com.dgmt.dao.AbstractSpringDao;
import com.dgmt.dao.CandidateEnrollmentDAO;
import com.dgmt.model.CandidateEnrollememt;
import com.dgmt.model.CandidateEnrollmentDetails;
import com.dgmt.model.CandidateSubjects;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:24 PM
 * @author CTE
 * @since DGMT 1.0
 */
public class CandidateEnrDAOHibernate extends AbstractSpringDao implements
		CandidateEnrollmentDAO
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
	@Override
	public CandidateEnrollememt getCandidateEnrollmentDetails(Long ewId,
			Long cdId)
	{
		logger.info("getCandidateEnrollmentDetails starts");

		List<CandidateEnrollememt> candiList = null;

		candiList = getHibernateTemplate().find(
				"from CandidateEnrollememt c where c.candidate =" + cdId
						+ " and c.examWindow = " + ewId);

		if ((candiList == null) || (candiList.size() == 0))
		{
			return null;
		}

		logger.info("getCandidateEnrollmentDetails ends");

		return candiList.get(0);
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
		return getHibernateTemplate().find(
				"from CandidateEnrollememt ce where ce.candidate.id = "
						+ candidateId);
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
		getHibernateTemplate().update(canEnrollmentDetails);
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
		getHibernateTemplate().save(candidateEnrollememt);
	}

	/**
	 * Saves enrollments.
	 * 
	 * @param enrollments
	 */
	@Override
	public void saveEnrollments(List<CandidateEnrollememt> enrollments)
	{
		logger.info("in saveEnrollments() of DAOHibernate enrollment id : "+enrollments.get(0).getId());
		getHibernateTemplate().saveOrUpdateAll(enrollments);
		getHibernateTemplate().flush();
	}

	/**
	 * Saves candidate subjects.
	 * 
	 * @param candSubs
	 */
	@Override
	public void saveCandidateSubjects(List<CandidateSubjects> candSubs)
	{
		logger.info("in saveCandidateSubjects() of DAOHibernate subject id : "+candSubs.get(0).getSubject().getId());
		logger.info("in saveCandidateSubjects() of DAOHibernate candidate id : "+candSubs.get(0).getCandidate().getId());
		getHibernateTemplate().saveOrUpdateAll(candSubs);
	}
}
