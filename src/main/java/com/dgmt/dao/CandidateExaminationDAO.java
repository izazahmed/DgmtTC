/**
 * Created Date: 7/22/10 1:15 PM
 * Class Name  : CandidateExaminationDAO.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>	<Author>	<Date>	<Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.dgmt.dao;

import java.util.List;

import com.dgmt.model.GeneratedQuePaper;

/**
 * Description:
 * 
 * @version 1.0 7/22/10 1:15 PM
 * @author CTE
 * @since DGMT 1.0
 */
public interface CandidateExaminationDAO
{

	/**
	 * @param questionPaperId
	 * @return
	 */
	public List<GeneratedQuePaper> getQuestionPaper(Long questionPaperId);

	public Long getQuestionPaperStatusId(String questionPaperId);
}
