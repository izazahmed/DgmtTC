/**
 * Created Date: 4/15/10 7:35 PM
 * Class Name  : MenuAction.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>        <Author>        <Date>        <Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package com.dgmt.web.action;

import javax.servlet.http.HttpSession;

import com.dgmt.model.OleExam;
import com.dgmt.util.DGMTProperties;
import com.dgmt.util.DGMTUtil;
import com.dgmt.util.Log;
import com.dgmt.util.LogFactory;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:35 PM
 * @author CTE
 * @since DGMT 1.0
 */
public class MenuAction extends BaseAction
{
	private Log logger = LogFactory.getLog(MenuAction.class);

	private String examType;

	/**
	 * @return
	 */
	public String getExamType()
	{
		return examType;
	}

	/**
	 * @param examType
	 */
	public void setExamType(String examType)
	{
		this.examType = examType;
	}

	/**
	 * @return
	 */
	public String showExamination()
	{
		try
		{
			String examId = request.getParameter("examType");
			if (examId != null) {
				OleExam exam = getOleExamService().getExamById(
						Long.valueOf(examId));

				request.getSession().setAttribute("exam", exam);
				request.getSession().setAttribute("menuHeader",
						"candEnrollments");

				String validateProctorKey = DGMTProperties.getProperties()
						.getProperty("VALIDATE_PROCTOR_KEY", "true");
				request.getSession().setAttribute("validateProctorKey",
						validateProctorKey);
			} else return null;
		}
		catch (Exception e)
		{
			logger.error("Exception: " + DGMTUtil.getStackTrace(e));
		}

		return SUCCESS;
	}

	/**
	 * @return
	 */
	public String showOLECandidateHome()
	{
		try
		{
			HttpSession session = request.getSession();

			session.setAttribute("menuHeader", "candEnrollments");
			logger.debug("Exam Type " + examType);
		}
		catch (Exception e)
		{
			logger.error("Exception: " + DGMTUtil.getStackTrace(e));
		}

		return SUCCESS;
	}
}
