/**
 * Created Date: 4/15/10 7:23 PM
 * Class Name  : ExamDAO.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>        <Author>        <Date>        <Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package com.dgmt.dao;

import java.util.List;

import com.dgmt.dto.ExamStatusDTO;
import com.dgmt.model.AnsweredPapersTransferStatus;
import com.dgmt.model.Candidate;
import com.dgmt.model.ExamPaper;
import com.dgmt.model.ExamWindow;
import com.dgmt.model.GeneratedQuePaper;
import com.dgmt.model.GeneratedQuePaperDtls;
import com.dgmt.model.OleExam;
import com.dgmt.model.OleSubject;
import com.dgmt.model.ProctorKey;
import com.dgmt.model.Resources;
import com.dgmt.model.SecurityQuestion;
import com.dgmt.model.TestCenter;
import com.dgmt.model.TimeSlot;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:23 PM
 * @author
 * @since DGMT 1.0
 */
public interface OleExamDAO
{
	/**
	 * @return
	 */
	public List<OleExam> getExams();

	/**
	 * @param id
	 * @return
	 */
	public OleExam getExamById(Long id);

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
	 * @param id
	 * @return
	 */
	public ExamPaper getExamPapers(Long id);

	/**
	 * Get examination papers associated with given window and subjects.
	 * 
	 * @param examWindow
	 *            examination window
	 * @param subjectIds
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
	 * @param examPaper
	 * @param candidate
	 * @return
	 */
	public List<GeneratedQuePaper> getGQPIdForCandidate(ExamPaper examPaper,
			Candidate candidate);

	/**
	 * @param ew
	 * @return
	 */
	public ProctorKey getTodaysProctorKey(ExamWindow ew);

	/**
	 * @param papers
	 */
	public void saveQuestionPapers(List<GeneratedQuePaper> papers);

	/**
	 * @param proctorKeys
	 * @param ew
	 */
	public void saveProctorKeys(List<ProctorKey> proctorKeys);

	/**
	 * Returns list of all active examination windows.
	 * 
	 * @return
	 */
	public List<ExamWindow> getAllActiveExamWindows();

	/**
	 * @param window
	 * @return
	 */
	public List<GeneratedQuePaper> getAnsweredQuestionPapers(ExamWindow window);

	/**
	 * @param answeredPapersTransferStatus
	 */
	public void saveAnsweredPapersTransferStatus(
			AnsweredPapersTransferStatus answeredPapersTransferStatus);

	/**
	 * @param string
	 */
	public void removeAnsweredQuestionPapers(String string);

	/**
	 * @param messageId
	 * @return
	 */
	public AnsweredPapersTransferStatus getPaperTransferStatus(String messageId);

	/**
	 * @param questionPapers
	 */
	public void updateTrasferredFlag(List<GeneratedQuePaper> questionPapers);

	// public List<ExamWindowTestCenter> getExamTestCenters(ExamWindow
	// examWindow);

	/**
	 * @param ew
	 */
	public void deleteProcterKeys(ExamWindow ew);

	/**
	 * @param ew
	 * @return
	 */
	public List<GeneratedQuePaper> getExamStatus(ExamWindow ew);

	/**
	 * @param testCenterId
	 * @return
	 */
	public TestCenter getTestCenter(Long testCenterId);

	/**
	 * @return
	 */
	public List<SecurityQuestion> getSecurityQuestions();

	/**
	 * @param examId
	 */
	public void deleteExamData(String examId);

	/**
	 * @return
	 */
	public List<Resources> getResources();

	/**
     *
    */
	public void deleteResources();

	/**
	 * @return
	 */
	public List<ExamWindow> getAllExamWindows();

	/**
	 * @param ew
	 */
	public void mergeExamWindow(ExamWindow ew);

	/**
	 * @param examId
	 * @return
	 */
	public List<ExamWindow> getActiveExamWindows(String examId);

	/**
	 * @return
	 */
	public List<GeneratedQuePaper> getAnsweredPaperCount();
}
