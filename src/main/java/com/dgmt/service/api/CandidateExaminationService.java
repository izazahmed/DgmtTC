/**
 * Created Date: 7/22/10 1:21 PM
 * Class Name  : CandidateExaminationService.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>	<Author>	<Date>	<Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.dgmt.service.api;

import java.util.List;

import com.dgmt.model.GeneratedQuePaper;

/**
 * Description:
 * 
 * @version 1.0 7/22/10 1:21 PM
 * @author
 * @since DGMT 1.0
 */
public interface CandidateExaminationService
{
	/**
	 * @param questionPaperId
	 * @return
	 */
	public List<GeneratedQuePaper> getQuestionPaper(Long questionPaperId);
	
	
	/**
	 * Returns status of question paper for a given gqpdid.
	 * @param gqpdId
	 * @return
	 */

	public Long getQuestionPaperStatusId(String questionPaperId);

}
