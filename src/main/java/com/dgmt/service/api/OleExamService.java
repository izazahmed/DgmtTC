/**
 * Created Date: 4/15/10 7:30 PM
 * Class Name  : ExamService.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>        <Author>        <Date>        <Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package com.dgmt.service.api;

import java.util.List;

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

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:30 PM
 * @author CTE
 * @since DGMT 1.0
 */
public interface OleExamService
{
	/**
	 * @return
	 */
	public List<OleExam> getExams();

	/**
	 * @param name
	 * @return
	 */
	public OleExam getExamByName(String name);

	/**
	 * @param id
	 * @return
	 */
	public OleSubject getSubject(Long id);

	/**
	 * @param exam
	 * @return
	 */
	public List<ExamWindow> getActiveExamWindows(OleExam exam);

	/**
	 * Get examination paper for given id.
	 * 
	 * @param id
	 */
	public ExamPaper getExamPapers(Long id);

	/**
	 * Get examination papers associated with given window and subjects.
	 * 
	 * @param examWindow
	 *            examination window
	 * @param flag
	 * @param oleSubject
	 *            list of subjects
	 * @return list of exam papers
	 */
	public List<ExamPaper> getCandidateExamPapers(ExamWindow examWindow,
			String subjectIds);

	/**
	 * @param examPaper
	 * @return
	 */
	public List<GeneratedQuePaper> getExamPaper(ExamPaper examPaper);

	/**
	 * @param quePaperDtls
	 */
	public void updateQuestionDtls(GeneratedQuePaperDtls quePaperDtls);
	
	/**
	 * @param generatedQuePaper
	 */
	public void updateGeneratedQuestionPaper(GeneratedQuePaper generatedQuePaper);
	
	/**
	 * @param generatedQuePaper
	 */
	public void updateTimeLeftInSecsForGQPaper(GeneratedQuePaper generatedQuePaper);

	/**
	 * Returns exam object for a given exam id
	 * 
	 * @param id
	 * @return Exam
	 */
	public OleExam getExamById(Long id);

	/**
	 * @param examPaper
	 * @param candidate
	 * @return
	 */
	public List<GeneratedQuePaper> getGQPIdForCandidate(ExamPaper examPaper,
			Candidate candidate);

	/**
	 * @param examWindow
	 * @return
	 */
	public ProctorKey getTodaysProctorKey(ExamWindow examWindow);

	/**
	 * @return
	 */
	public List<SecurityQuestion> getSecurityQuestions();
}
