/**
 * Created Date: 9/7/10 11:44 AM
 * Class Name  : ExaminationSubjectsDTO.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>	<Author>	<Date>	<Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.dgmt.dto;

import com.dgmt.model.ExamPaper;

/**
 * Description:
 * 
 * @version 1.0 9/7/10 11:44 AM
 * @author
 * @since DGMT 1.0
 */
public class ExaminationSubjectsDTO
{
	/**
	 * DOCUMENT ME!
	 */
	public ExamPaper examPaper;

	/**
	 * DOCUMENT ME!
	 */
	public String subject;

	/**
	 * DOCUMENT ME!
	 */
	public String tiltle;

	/**
	 * DOCUMENT ME!
	 */
	public String timeSlot;

	/**
	 * DOCUMENT ME!
	 */
	public String timeDuration;

	/**
	 * DOCUMENT ME!
	 */
	public String allowed;

	/**
	 * DOCUMENT ME!
	 */
	public Long slotId;

	public String incomplete;
	
	/**
	 * @return
	 */
	public ExamPaper getExamPaper()
	{
		return examPaper;
	}

	/**
	 * @param examPaper
	 */
	public void setExamPaper(ExamPaper examPaper)
	{
		this.examPaper = examPaper;
	}

	/**
	 * @return
	 */
	public Long getSlotId()
	{
		return slotId;
	}

	/**
	 * @param slotId
	 */
	public void setSlotId(Long slotId)
	{
		this.slotId = slotId;
	}

	/**
	 * @return
	 */
	public String getAllowed()
	{
		return allowed;
	}

	/**
	 * @param allowed
	 */
	public void setAllowed(String allowed)
	{
		this.allowed = allowed;
	}

	/**
	 * @return
	 */
	public String getSubject()
	{
		return subject;
	}

	/**
	 * @param subject
	 */
	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	/**
	 * @return
	 */
	public String getTiltle()
	{
		return tiltle;
	}

	/**
	 * @param tiltle
	 */
	public void setTiltle(String tiltle)
	{
		this.tiltle = tiltle;
	}

	/**
	 * @return
	 */
	public String getTimeSlot()
	{
		return timeSlot;
	}

	/**
	 * @param timeSlot
	 */
	public void setTimeSlot(String timeSlot)
	{
		this.timeSlot = timeSlot;
	}

	/**
	 * @return
	 */
	public String getTimeDuration()
	{
		return timeDuration;
	}

	/**
	 * @param timeDuration
	 */
	public void setTimeDuration(String timeDuration)
	{
		this.timeDuration = timeDuration;
	}

	public String getIncomplete()
	{
		return incomplete;
	}

	public void setIncomplete(String incomplete)
	{
		this.incomplete = incomplete;
	}
	
	
}
