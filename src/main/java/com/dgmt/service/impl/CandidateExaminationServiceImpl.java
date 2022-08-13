/**
 * Created Date: 7/22/10 1:22 PM
 * Class Name  : CandidateExaminationServiceImpl.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>	<Author>	<Date>	<Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.dgmt.service.impl;

import java.util.List;

import com.dgmt.dao.CandidateExaminationDAO;
import com.dgmt.model.GeneratedQuePaper;
import com.dgmt.service.api.CandidateExaminationService;

/**
 * Description:
 * 
 * @version 1.0 7/22/10 1:22 PM
 * @author
 * @since DGMT 1.0
 */
public class CandidateExaminationServiceImpl implements
		CandidateExaminationService
{
	private CandidateExaminationDAO candidateExaminationDAO;

	/**
	 * @return
	 */
	public CandidateExaminationDAO getCandidateExaminationDAO()
	{
		return candidateExaminationDAO;
	}

	/**
	 * @param candidateExaminationDAO
	 */
	public void setCandidateExaminationDAO(
			CandidateExaminationDAO candidateExaminationDAO)
	{
		this.candidateExaminationDAO = candidateExaminationDAO;
	}

	/**
	 * @param questionPaperId
	 * @return
	 */
	@Override
	public List<GeneratedQuePaper> getQuestionPaper(Long questionPaperId)
	{
		return candidateExaminationDAO.getQuestionPaper(questionPaperId);
	}

	@Override
	public Long getQuestionPaperStatusId(String questionPaperId) {
		// TODO Auto-generated method stub
		return candidateExaminationDAO.getQuestionPaperStatusId(questionPaperId);
	}
}
