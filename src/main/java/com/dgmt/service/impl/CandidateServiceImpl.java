/**
 * Created Date: 4/15/10 7:31 PM
 * Class Name  : CandidateServiceImpl.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>        <Author>        <Date>        <Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package com.dgmt.service.impl;

import java.util.List;

import com.dgmt.dao.CandidateDAO;
import com.dgmt.model.Candidate;
import com.dgmt.model.OleSubject;
import com.dgmt.service.api.CandidateService;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:31 PM
 * @author
 * @since DGMT 1.0
 */
public class CandidateServiceImpl implements CandidateService
{
	private CandidateDAO candidateDAO;

	/**
	 * @param candidateDAO
	 */
	public void setCandidateDAO(CandidateDAO candidateDAO)
	{
		this.candidateDAO = candidateDAO;
	}

	/**
	 * @param name
	 * @return
	 */
	@Override
	public Candidate getCandidateId(String name)
	{
		return candidateDAO.getCandidateId(name);
	}

	/**
	 * @param candidate
	 */
	@Override
	public void updateCandidate(Candidate candidate)
	{
		candidateDAO.updateCandidate(candidate);
	}
	
	@Override
	public List<OleSubject> getCandidateSubjects(Long candidateId)
	{
		return candidateDAO.getCandidateSubjects(candidateId);
	}
}
