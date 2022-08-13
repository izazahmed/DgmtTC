/**
 * Created Date: 4/15/10 7:22 PM
 * Class Name  : CandidateDAO.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>        <Author>        <Date>        <Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package com.dgmt.dao;

import java.util.List;

import com.dgmt.model.Candidate;
import com.dgmt.model.OleSubject;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:22 PM
 * @author
 * @since DGMT 1.0
 */
public interface CandidateDAO
{
	/**
	 * @param name
	 * @return
	 */
	public Candidate getCandidateId(String name);

	/**
	 * @param candidate
	 */
	public void updateCandidate(Candidate candidate);

	public List<OleSubject> getCandidateSubjects(Long candidateId);
}
