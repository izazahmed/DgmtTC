/**
 * Created Date: 4/15/10 7:32 PM
 * Class Name  : ExamServiceImpl.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>        <Author>        <Date>        <Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package com.dgmt.service.impl;

import java.util.List;

import com.dgmt.dao.OleExamDAO;
import com.dgmt.model.Candidate;
import com.dgmt.model.ExamPaper;
import com.dgmt.model.ExamWindow;
import com.dgmt.model.GeneratedQuePaper;
import com.dgmt.model.GeneratedQuePaperDtls;
import com.dgmt.model.OleExam;
import com.dgmt.model.OleSubject;
import com.dgmt.model.ProctorKey;
import com.dgmt.model.SecurityQuestion;
import com.dgmt.model.TimeSlot;
import com.dgmt.service.api.OleExamService;
import com.dgmt.util.Log;
import com.dgmt.util.LogFactory;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:32 PM
 * @author CTE
 * @since DGMT 1.0
 */
public class OleExamServiceImpl implements OleExamService
{
	private OleExamDAO oleExamDAO;

	private Log logger = LogFactory.getLog(OleExamServiceImpl.class);

	/**
	 * @param oleExamDAO
	 */
	public void setOleExamDAO(OleExamDAO oleExamDAO)
	{
		this.oleExamDAO = oleExamDAO;
	}

	/**
	 * @return
	 */
	@Override
	public List<OleExam> getExams()
	{
		return oleExamDAO.getExams();
	}

	/**
	 * @param id
	 * @return
	 */
	@Override
	public OleSubject getSubject(Long id)
	{
		return oleExamDAO.getSubject(id);
	}

	/**
	 * @param name
	 * @return
	 */
	@Override
	public OleExam getExamByName(String name)
	{
		return oleExamDAO.getExamByName(name);
	}

	/**
	 * @param exam
	 * @return
	 */
	@Override
	public List<ExamWindow> getActiveExamWindows(OleExam exam)
	{
		return oleExamDAO.getActiveExamWindows(exam);
	}

	/**
	 * @param id
	 * @return
	 */
	@Override
	public ExamPaper getExamPapers(Long id)
	{
		return oleExamDAO.getExamPapers(id);
	}

	/**
	 * Get examination papers associated with given window and subjects.
	 * 
	 * @param examWindow
	 *            examination window
	 * @param oleSubject
	 *            list of subjects
	 * @return list of exam papers
	 */
	@Override
	public List<ExamPaper> getCandidateExamPapers(ExamWindow examWindow,
			String subjectIds)
	{
		return oleExamDAO.getCandidateExamPapers(examWindow, subjectIds);
	}

	/**
	 * @param examPaper
	 * @return
	 */
	@Override
	public List<GeneratedQuePaper> getExamPaper(ExamPaper examPaper)
	{
		return oleExamDAO.getExamPaper(examPaper);
	}

	/**
	 * @param quePaperDtls
	 */
	@Override
	public void updateQuestionDtls(GeneratedQuePaperDtls quePaperDtls)
	{
		oleExamDAO.updateQuestionDtls(quePaperDtls);
	}
	
	/**
	 * @param generatedQuePaper
	 */
	@Override
	public void updateGeneratedQuestionPaper(GeneratedQuePaper generatedQuePaper)
	{
		oleExamDAO.updateGeneratedQuestionPaper(generatedQuePaper);
	}
	
	/**
	 * @param generatedQuePaper
	 */
	@Override
	public void updateTimeLeftInSecsForGQPaper(GeneratedQuePaper generatedQuePaper)
	{
		oleExamDAO.updateTimeLeftInSecsForGQPaper(generatedQuePaper);
	}

	/**
	 * Returns exam object for a given exam id
	 * 
	 * @param id
	 * @return Exam
	 */
	@Override
	public OleExam getExamById(Long id)
	{
		return oleExamDAO.getExamById(id);
	}

	/**
	 * @param examPaper
	 * @param candidate
	 * @return
	 */
	@Override
	public List<GeneratedQuePaper> getGQPIdForCandidate(ExamPaper examPaper,
			Candidate candidate)
	{
		return oleExamDAO.getGQPIdForCandidate(examPaper, candidate);
	}

	/**
	 * @param examWindow
	 * @return
	 */
	@Override
	public ProctorKey getTodaysProctorKey(ExamWindow examWindow)
	{
		return oleExamDAO.getTodaysProctorKey(examWindow);
	}

	/**
	 * @return
	 */
	@Override
	public List<SecurityQuestion> getSecurityQuestions()
	{
		return oleExamDAO.getSecurityQuestions();
	}
}
