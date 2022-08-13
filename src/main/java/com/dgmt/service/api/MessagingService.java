/**
 * Created Date: 12/16/10 12:59 PM
 * Class Name  : MessagingService.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>        <Author>        <Date>        <Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package com.dgmt.service.api;

import com.dgmt.dto.StatusMessageDTO;
import com.dgmt.dto.TestCenterMessageDTO;
import com.dgmt.model.ExamWindow;

/**
 * Description:
 * 
 * @version 1.0 12/16/10 12:59 PM
 * @author CTE
 * @since DGMT 1.0
 */
public interface MessagingService
{
	/**
	 * @param tcMessageDTO
	 */
	public void saveExamData(TestCenterMessageDTO tcMessageDTO);

	/**
	 * @param correlationID
	 */
	public void sendStatusMessage(String correlationID);

	/**
         *
         */
	public void transferAnsweredPapers();

	/**
	 * @param messageDTO
	 */
	public void updateMessageReceipt(StatusMessageDTO messageDTO);

	/**
         *
         */
	public void transferExamStatus();

	/**
         *
         */
	public void transferMachineStatus();

	/**
	 * @param examId
	 */
	public void deleteExamData(String examId);

	/**
	 * @param examId
	 * @return
	 */
	public ExamWindow getActiveExamWindow(String examId);

	/**
	 * @param ewId
	 */
	public void sendDeleteNotification(String ewId);

	/**
         *
         */
	public void deleteTCData();
}
