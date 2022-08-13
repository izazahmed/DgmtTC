/**
 * Created Date: 10/1/10 3:26 PM
 * Class Name  : CanidateExamsListAction.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>	<Author>	<Date>	<Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.dgmt.web.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import com.dgmt.dto.ExaminationSubjectsDTO;
import com.dgmt.model.Candidate;
import com.dgmt.model.CandidateEnrollememt;
import com.dgmt.model.CandidateEnrollmentDetails;
import com.dgmt.model.ExamPaper;
import com.dgmt.model.ExamWindow;
import com.dgmt.model.OleExam;
import com.dgmt.model.OleSubject;
import com.dgmt.model.TimeSlot;
import com.dgmt.util.DGMTProperties;
import com.dgmt.util.DGMTUtil;
import com.dgmt.util.Log;
import com.dgmt.util.LogFactory;

/**
 * Description:
 * 
 * @version 1.0 10/1/10 3:26 PM
 * @author CTE
 * @since DGMT 1.0
 */
@SuppressWarnings("serial")
public class CandidateExamsListAction extends BaseAction
{
	private Log logger = LogFactory.getLog(CandidateExamsListAction.class);

	private ArrayList<ExaminationSubjectsDTO> subjectDetailsList;

	/**
	 * @return
	 */
	public ArrayList<ExaminationSubjectsDTO> getSubjectDetailsList()
	{
		return subjectDetailsList;
	}

	/**
	 * @param subjectDetailsList
	 */
	public void setSubjectDetailsList(
			ArrayList<ExaminationSubjectsDTO> subjectDetailsList)
	{
		this.subjectDetailsList = subjectDetailsList;
	}

	/**
	 * @return
	 */
	public String viewCandidateExamsList()
	{
		try
		{
			DGMTProperties properties = DGMTProperties.getProperties();
			String beforeTime = properties.getProperty(
					"ENABLE_BEGIN_LINK_BEFORE_EXAM_START_TIME", "30");
			String afterTime = properties.getProperty(
					"ENABLE_BEGIN_LINK_AFTER_EXAM_START_TIME", "30");
			String name = request.getUserPrincipal().toString();
			DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			int size = 0;
			int i = 0;
			String subjectIds = "";
			String subjectsTakenList = "";
			String subjectsIncompList = "";

			OleExam exam = (OleExam) request.getSession().getAttribute("exam");

			ExaminationSubjectsDTO subjectDTO;
			CandidateEnrollememt candidateEnrollememt = null;
			CandidateEnrollmentDetails candidateEnrollmentDetails = null;
			ArrayList<ExaminationSubjectsDTO> timeSlots = new ArrayList<ExaminationSubjectsDTO>();

			subjectDetailsList = new ArrayList<ExaminationSubjectsDTO>();
			
			List<ExamWindow> examWindows = null;
			if(exam!=null){
				examWindows = getOleExamService().getActiveExamWindows(exam);
			}

			if ((examWindows != null) && (examWindows.size() == 1))
			{
				logger.debug("Active examination window found.");
				
				Candidate candidate = getCandidateService()
						.getCandidateId(name);

				candidateEnrollememt = (CandidateEnrollememt) getCandidateEnrollmentService()
						.getCandidateEnrollmentDetails(
								examWindows.get(0).getId(), candidate.getId());

				size = candidateEnrollememt.getCandidateEnrollmentDetails()
						.size();

				if (size > 0)
				{
					// iterate through the enrollment details

					for (i = 0; i < size; i++)
					{
						candidateEnrollmentDetails = candidateEnrollememt
								.getCandidateEnrollmentDetails().get(i);

						if ((candidateEnrollmentDetails.getSubject() != null)
								&& (candidateEnrollmentDetails
										.getSlotUserForSubjectId() != null))
						{
							subjectsTakenList += ("," + candidateEnrollmentDetails
									.getSubject().getId());
						}

						if ((candidateEnrollmentDetails.getSubject() == null)
								&& (candidateEnrollmentDetails
										.getSlotUserForSubjectId() != null))
						{
							subjectsIncompList += ("," + candidateEnrollmentDetails
									.getSlotUserForSubjectId());
						}
					}

					if (org.apache.commons.lang.StringUtils
							.isNotBlank(subjectsTakenList))
					{
						subjectsTakenList = subjectsTakenList.substring(1);
						logger.debug("Subjects taken :" + subjectsTakenList);
					}

					if (org.apache.commons.lang.StringUtils
							.isNotBlank(subjectsIncompList))
					{
						subjectsIncompList = subjectsIncompList.substring(1);
						logger.debug("Subjects incomplete :" + subjectsIncompList);
					}

					i = 0;

					for (i = 0; i < size; i++)
					{
						candidateEnrollmentDetails = candidateEnrollememt
								.getCandidateEnrollmentDetails().get(i);

						Date date = (Date) formatter1
								.parse(candidateEnrollmentDetails
										.getEnrollmetExamDate().toString());

						TimeSlot slot;
						List<ExamPaper> examPapers = null;
						boolean firstTimeLogin = false;

						if ((candidateEnrollmentDetails.getSubject() == null)
								&& (candidateEnrollmentDetails
										.getSlotUserForSubjectId() == null))
						{
							firstTimeLogin = true;
						}

						logger.debug("First time taking exam :"+ firstTimeLogin);
						
						slot = candidateEnrollmentDetails.getTimeSlots();

						String[] st = slot.getStartTime().split(":");
						String[] et = slot.getEndTime().split(":");
						String[] UpTo = slot.getAllowedUpto().split(":");

						Calendar startTimeCal = Calendar.getInstance();
						startTimeCal.setTime(date);
						startTimeCal.set(Calendar.HOUR_OF_DAY, Integer
								.parseInt(st[0]));
						startTimeCal.set(Calendar.MINUTE, Integer
								.parseInt(st[1])
								- Integer.parseInt(beforeTime));
						startTimeCal.set(Calendar.SECOND, 0);
						startTimeCal.set(Calendar.MILLISECOND, 0);

						Calendar endTimeCal = Calendar.getInstance();
						endTimeCal.setTime(date);
						endTimeCal.set(Calendar.HOUR_OF_DAY,
								firstTimeLogin ? Integer.parseInt(UpTo[0])
										: Integer.parseInt(et[0]));
						endTimeCal.set(Calendar.MINUTE,
								(firstTimeLogin ? Integer.parseInt(UpTo[1])
										: Integer.parseInt(et[1]))
										+ Integer.parseInt(afterTime));
						endTimeCal.set(Calendar.SECOND, 0);
						endTimeCal.set(Calendar.MILLISECOND, 0);

						Calendar currTimeCal = Calendar.getInstance();
						currTimeCal.setTime(new Date());
						currTimeCal.set(Calendar.SECOND, 0);
						currTimeCal.set(Calendar.MILLISECOND, 0);

						logger.debug("Current time :"+ currTimeCal.getTime());
						logger.debug("Exam Start time :"+ startTimeCal.getTime());
						logger.debug("Exam end time/ allowed upto time :"+ endTimeCal.getTime());
						
						if (currTimeCal.getTime().compareTo(
								startTimeCal.getTime()) >= 0
								&& currTimeCal.getTime().compareTo(
										endTimeCal.getTime()) <= 0)
						{
							
							if ((candidateEnrollmentDetails.getSubject() == null)
									&& (candidateEnrollmentDetails
											.getSlotUserForSubjectId() == null))
							{
								List<OleSubject> subs = getCandidateService()
										.getCandidateSubjects(
												candidateEnrollememt
														.getCandidate().getId());

								subjectIds = getEligibleSubjectIds(subs,
										subjectsTakenList, subjectsIncompList);
							}
							else if ((candidateEnrollmentDetails.getSubject() == null)
									&& (candidateEnrollmentDetails
											.getSlotUserForSubjectId() != null))
							{
								subjectIds = ""
										+ candidateEnrollmentDetails
												.getSlotUserForSubjectId()
												.longValue();
							}
							
							logger.debug("Get exam papers for subject Ids :"+ subjectIds);
							
							if(subjectIds!=null && !"".equalsIgnoreCase(subjectIds))
							{
								examPapers = getOleExamService()
										.getCandidateExamPapers(examWindows.get(0),
												subjectIds);
							}

							if (examPapers != null)
							{
								for (ExamPaper ep : examPapers)
								{
									subjectDTO = new ExaminationSubjectsDTO();
									slot = candidateEnrollmentDetails
											.getTimeSlots();

									subjectDTO.setTimeDuration(ep
											.getExamWindow().getDuration()
											+ "");

									subjectDTO.setSubject(ep.getSubject()
											.getName());
									subjectDTO.setExamPaper(ep);

									if ((candidateEnrollmentDetails
											.getSubject() == null)
											&& (candidateEnrollmentDetails
													.getSlotUserForSubjectId() != null))
									{
										subjectDTO.setIncomplete("1");
									}

									subjectDTO.setTimeSlot(slot.getStartTime()
											+ " - " + slot.getEndTime());
									subjectDTO.setSlotId(slot.getId());
									subjectDTO.setAllowed("true");
									timeSlots.add(subjectDTO);

									subjectDetailsList.add(subjectDTO);
								}
							}
							break;
						}						
					}
				}
			}
		}
		catch (Exception exception)
		{
			logger.error("Exception: " + DGMTUtil.getStackTrace(exception));
		}

		return SUCCESS;
	}

	private String getEligibleSubjectIds(List<OleSubject> subs,
			String subjectsTakenList, String subjectsIncompList)
	{
		String subjectIds = "", eligibleSubs = "";
		
		if (subjectsTakenList.length() != 0)
		{
			subjectIds = subjectsTakenList;
			if (subjectsIncompList.length() != 0)
			{
				subjectIds += "," + subjectsIncompList;
			}
		}
		else if (subjectsIncompList.length() != 0)
		{
			subjectIds = subjectsIncompList;
		}

		if (subjectIds.length() > 0)
		{
			String[] ids = subjectIds.split(",");

			HashSet<Long> idSet = new HashSet<Long>();
			for (String s : ids)
			{
				idSet.add(Long.parseLong(s));
			}

			for (OleSubject os : subs)
			{
				if (!idSet.contains(os.getId()))
				{
					eligibleSubs += "," + os.getId().intValue();
				}
			}
		}
		else
		{
			for (OleSubject os : subs)
			{
				eligibleSubs += "," + os.getId().intValue();
			}
		}

		if (eligibleSubs.length() > 0)
		{
			eligibleSubs = eligibleSubs.substring(1);
		}

		return eligibleSubs;
	}
}
