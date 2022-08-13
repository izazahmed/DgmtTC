/**
 * Created Date: 4/15/10 7:24 PM
 * Class Name  : CandidateDAOHibernate.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>        <Author>        <Date>        <Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package com.dgmt.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import com.dgmt.dao.AbstractSpringDao;
import com.dgmt.dao.CandidateDAO;
import com.dgmt.model.Candidate;
import com.dgmt.model.CandidateSubjects;
import com.dgmt.model.OleSubject;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:24 PM
 * @author
 * @since DGMT 1.0
 */
public class CandidateDAOHibernate extends AbstractSpringDao implements
		CandidateDAO
{
	/**
	 * @param name
	 * @return
	 */
	@Override
	public Candidate getCandidateId(String name)
	{
		List<Candidate> candidates = getHibernateTemplate().find(
				"from Candidate c where c.personalNo = '" + name + "'");

		if ((candidates != null) && (candidates.size() > 0))
		{
			return candidates.get(0);
		}

		return null;
	}

	/**
	 * @param candidate
	 */
	@Override
	public void updateCandidate(Candidate candidate)
	{
		getHibernateTemplate().update(candidate);
	}
	
	@Override
	public List<OleSubject> getCandidateSubjects(Long candidateId)
	{
		List<OleSubject> subjects = null;
		
		List<CandidateSubjects> candSubs = getHibernateTemplate().find(
				"from CandidateSubjects cs where cs.candidate.id = ?", candidateId );

		if ((candSubs != null) && (candSubs.size() > 0))
		{
			subjects = new ArrayList<OleSubject>();
			for(CandidateSubjects cs : candSubs)
			{
				subjects.add(cs.getSubject());
			}
		}
		return subjects;
	}
}
