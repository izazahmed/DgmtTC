package com.dgmt.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.dgmt.dto.QuestionDetails;
import com.dgmt.dto.ResultDetails;
import com.dgmt.model.Candidate;
import com.dgmt.model.CandidateEnrollememt;
import com.dgmt.model.CandidateEnrollmentDetails;
import com.dgmt.model.ExamPaper;
import com.dgmt.model.ExamPaperSection;
import com.dgmt.model.ExamWindow;
import com.dgmt.model.GeneratedQuePaper;
import com.dgmt.model.GeneratedQuePaperDtls;
import com.dgmt.model.OleChoice;
import com.dgmt.model.OleExam;
import com.dgmt.model.OleMatching;
import com.dgmt.model.OleSubject;
import com.dgmt.model.ProctorKey;
import com.dgmt.model.Status;
import com.dgmt.model.TimeSlot;
import com.dgmt.service.api.CandidateEnrollmentService;
import com.dgmt.util.DGMTProperties;
import com.dgmt.util.DGMTUtil;
import com.dgmt.util.IConstants.StatusCode;
import com.dgmt.util.Log;
import com.dgmt.util.LogFactory;
import com.googlecode.jsonplugin.JSONUtil;
 
public class CandidateExaminationAction extends BaseAction{
	
	private Log logger = LogFactory.getLog(CandidateExaminationAction.class);
	
	private Candidate candidate;
	
	private List<GeneratedQuePaperDtls>  generatedQuePaperDtls;

	private List<ExamPaper> examPapers;
	
	private List<GeneratedQuePaper> generatedQuePaper;
	
	private String prockerKey;
	
	private String subjectName;  
	private String subjectTitle; 
	private String subjectTimeSlot;
	private String subjectTimeDuration;
	private String answer;
	private String subjectTimeSlotId;
	private String questionCount;
	private String sectionCount;
	private String subjectCodeId;
	private long elapsedTime; 
	private String paperId;
	
	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

	public long getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public String getSubjectCodeId() {
		return subjectCodeId;
	}

	public void setSubjectCodeId(String subjectCodeId) {
		this.subjectCodeId = subjectCodeId;
	}

	public String getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(String questionCount) {
		this.questionCount = questionCount;
	}

	public String getSubjectTimeSlotId() {
		return subjectTimeSlotId;
	}

	public void setSubjectTimeSlotId(String subjectTimeSlotId) {
		this.subjectTimeSlotId = subjectTimeSlotId;
	}

	public String getSectionCount() {
		return sectionCount;
	}

	public void setSectionCount(String sectionCount) {
		this.sectionCount = sectionCount;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectTitle() {
		return subjectTitle;
	}

	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}

	public String getSubjectTimeSlot() {
		return subjectTimeSlot;
	}

	public void setSubjectTimeSlot(String subjectTimeSlot) {
		this.subjectTimeSlot = subjectTimeSlot;
	}

	public String getSubjectTimeDuration() {
		return subjectTimeDuration;
	}

	public void setSubjectTimeDuration(String subjectTimeDuration) {
		this.subjectTimeDuration = subjectTimeDuration;
	}

	public String getProckerKey() {
		return prockerKey;
	}

	public void setProckerKey(String prockerKey) {
		this.prockerKey = prockerKey;
	}

	public List<GeneratedQuePaper> getGeneratedQuePaper() {
		return generatedQuePaper;
	}

	public void setGeneratedQuePaper(List<GeneratedQuePaper> generatedQuePaper) {
		this.generatedQuePaper = generatedQuePaper;
	}

	public List<GeneratedQuePaperDtls> getGeneratedQuePaperDtls() {
		return generatedQuePaperDtls;
	}

	public void setGeneratedQuePaperDtls(
			List<GeneratedQuePaperDtls> generatedQuePaperDtls) {
		this.generatedQuePaperDtls = generatedQuePaperDtls;
	}


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	
	public List<ExamPaper> getExamPapers() {
		return examPapers;
	}

	public void setExamPapers(List<ExamPaper> examPapers) {
		this.examPapers = examPapers;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3087555727028848212L;

	public String viewExaminations()
	{
		/*try{
		String name = request.getUserPrincipal().toString();	
 		
		OleExam exam = (OleExam) getSession().getAttribute("exam");  
		
		List<ExamWindow> examWindows = getOleExamService().getActiveExamWindows(getOleExamService().getExamByName(exam.getName()));
		
		if(examWindows != null && examWindows.size() == 1)
		{
			candidate  = getCandidateService().getCandidateId(name);
			examPapers = getOleExamService().getCandidateExamPapers(examWindows.get(0), candidate.getOleSubject()); 
		}
		else			
		{
			//no active exam windows
		}
		} catch(Exception exception){
		}*/
		return SUCCESS;
	}
	
	public String isSecureKeyValid()
	{
		try{
				String name = request.getUserPrincipal().toString();  	 
 				candidate  = getCandidateService().getCandidateId(name);
				CandidateEnrollmentService cEnrollService  = (CandidateEnrollmentService) getBean("candidateEnrllmentService");
	
				logger.debug("Candidate Id :  "+candidate.getId());  
 				String examId = request.getParameter("examId"); 				
 				OleExam exam = getOleExamService().getExamById(Long.valueOf(examId));
	
				List<ExamWindow> examWindows = getOleExamService().getActiveExamWindows(getOleExamService().getExamByName(exam.getName()));
				
				if(examWindows != null && examWindows.size() == 1)
				{
					CandidateEnrollememt candiList = null;
					candiList =  (CandidateEnrollememt) cEnrollService.getCandidateEnrollmentDetails(examWindows.get(0).getId(),candidate.getId());
					if (!candiList.getSecureKey().equals(request.getParameter("secureKey"))) { 
						response.getWriter().write("invalid");
					}else{
						response.getWriter().write("valid");
					}
				} else {
					addActionError(getText("inactive.examwindow"));  
				}
				
			} catch(Exception exception){
				
			}
		return null;
	}
	
	public String isProcterKeyValid()
	{
		 
		try{
				OleExam exam =(OleExam)getSession().getAttribute("exam");
				String name = request.getUserPrincipal().toString();  	 
				candidate  = getCandidateService().getCandidateId(name);
				List<ExamWindow> examWindows = getOleExamService().getActiveExamWindows(getOleExamService().getExamByName(exam.getName()));
				if(examWindows != null && examWindows.size() == 1) 
				{
					prockerKey = request.getParameter("prockerKey");
					
					logger.debug("Proctor Key Entered : "+ prockerKey);
					
					ProctorKey  pKey = getOleExamService().getTodaysProctorKey(examWindows.get(0));
					
					if(pKey != null)
					{
						logger.debug("Proctor Key for today : "+ pKey.getKey());
						
						if (!pKey.getKey().equals(prockerKey)) 
						{    
							response.getWriter().write("invalid");
						}
						else
						{
							response.getWriter().write("valid");
						}
					}
					else
					{
						logger.debug("Proctor Key for today : "+ pKey);
						
						response.getWriter().write("invalid");
					}
				} 
				else 
				{
					logger.debug(" There are no active windows ...");
				}
				
			} catch(Exception exception){
				logger.error("exception  :" + DGMTUtil.getStackTrace(exception));
			}
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getQuestionPaper()
	{
		String name = "";
		String lastQuestionaccessed = "";
		Status status = new Status();

		List<CandidateEnrollmentDetails> canDetailsList;
		CandidateEnrollmentDetails canDetails;
		CandidateEnrollememt enrollment;
		// List<GeneratedQuePaper> list = null;

		TimeSlot slot;
		String startTime = "";
		String allowedTime = "";
		Long candEnrollDtlId = null;

		// String exStartTime = "";
		String exAllowedTime = "";
		String exEndTime = "";
		String endTime = "";

		String lastAccessTime = "";

		int delayTime = 0;

		Calendar cal = Calendar.getInstance();
		Calendar examStartTime = Calendar.getInstance();
		Calendar examEndTime = Calendar.getInstance();
		Calendar examAllowUpTo = Calendar.getInstance();

		Date today = new Date();
		cal.setTime(today);
		examStartTime.setTime(today);
		examEndTime.setTime(today);
		examAllowUpTo.setTime(today);

		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

		try
		{

			DGMTProperties properties = DGMTProperties.getProperties();
			String netWorkDelayTime = properties.getProperty("NETWORK_DELAY_TIME");

			if (getSession().getAttribute("QuestionPaperDtls") != null)
			{
				getSession().removeAttribute("QuestionPaperDtls");
			}
			if (getSession().getAttribute("generatedQuePaper") != null)
			{
				getSession().removeAttribute("generatedQuePaper");
			}

			//String name = request.getUserPrincipal().toString();
			Principal principal = request.getUserPrincipal();
			if(principal != null){
				name = principal.getName();
				candidate = getCandidateService().getCandidateId(name);
			}

			String exampaperId = request.getParameter("exampaperId");
			String subjectCodeId = request.getParameter("subjectCodeId");
			String subjTimeSlotId = request.getParameter("subjTimeSlotId");
			Long questionPaperId;
			ExamPaper examPaper = null;
			
			if( exampaperId !=null &&! "null".equalsIgnoreCase(exampaperId)) {
				
				examPaper = getOleExamService().getExamPapers(Long.valueOf(exampaperId));
			}
			
			// check if the candidate finishes the exam or not
			if( examPaper != null && candidate != null ) {
				
			  generatedQuePaper = getOleExamService().getGQPIdForCandidate(
					examPaper, candidate);

			}
			else return null;
			
			GeneratedQuePaper questionPaper = null;
			
			if (generatedQuePaper != null && generatedQuePaper.size() > 0)
			{
				questionPaper = generatedQuePaper.get(0);
				
				OleExam exam = (OleExam) getSession().getAttribute("exam");
				List<ExamWindow> examWindows = getOleExamService().getActiveExamWindows(getOleExamService()
								                        .getExamByName(exam.getName()));

				if (examWindows != null && examWindows.size() == 1)
				{

					enrollment = getCandidateEnrollmentService().getCandidateEnrollmentDetails(
									examWindows.get(0).getId(),candidate.getId());
					
					canDetailsList = enrollment.getCandidateEnrollmentDetails();

					// get the time slot - startTime & allowed of the exam
					for (int i = 0; i < canDetailsList.size(); i++)
					{
						slot = new TimeSlot();
						canDetails = canDetailsList.get(i);
						slot = canDetails.getTimeSlots();

						if (slot.getId().intValue() == Integer
								.parseInt(subjTimeSlotId))
						{
							startTime = slot.getStartTime();
							allowedTime = slot.getAllowedUpto();
							endTime = slot.getEndTime();
							
							break;
						}
					}

					String[] st = startTime.split(":");
					String[] end = endTime.split(":");
					String[] UpTo = allowedTime.split(":");

					examStartTime.set(Calendar.HOUR_OF_DAY, Integer
							.parseInt(st[0]));
					examStartTime.set(Calendar.MINUTE, Integer.parseInt(st[1]));
					examStartTime.set(Calendar.SECOND, 0);
					examStartTime.set(Calendar.MILLISECOND, 0);

					examEndTime.set(Calendar.HOUR_OF_DAY, Integer
							.parseInt(end[0]));
					examEndTime.set(Calendar.MINUTE, Integer.parseInt(end[1]));
					examEndTime.set(Calendar.SECOND, 0);
					examEndTime.set(Calendar.MILLISECOND, 0);

					examAllowUpTo.set(Calendar.HOUR_OF_DAY, Integer
							.parseInt(UpTo[0]));
					examAllowUpTo.set(Calendar.MINUTE, Integer
							.parseInt(UpTo[1]));
					examAllowUpTo.set(Calendar.SECOND, 0);
					examAllowUpTo.set(Calendar.MILLISECOND, 0);

					// exStartTime = df.format(examStartTime.getTime());
					exAllowedTime = df.format(examAllowUpTo.getTime());
					questionPaperId = questionPaper.getId();
					lastQuestionaccessed = questionPaper
							.getQuestionId() == null ? "0" : questionPaper.getQuestionId();
					// examEndTime.

					DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
					lastAccessTime = formatter.format(questionPaper
							.getLastUpdatedQuestionTime());
					Long lastAccessTimeInSecs = questionPaper
							.getTimeLeftInSecs();

					Date date1 = new Date();
					DateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");
					String currentTime = formatter2.format(date1);
					long elapsed = 0;

					if (lastAccessTimeInSecs != null)
					{
						elapsed = lastAccessTimeInSecs.longValue();
					}
					else
					{
						elapsed = 0;
					}

					if (StringUtils.isNotBlank(netWorkDelayTime))
					{
						delayTime = Integer.parseInt(netWorkDelayTime) * 60;
					}

					long totalNwDelay = questionPaper.getNetworkFailTimeInSec() == null ? 0L: questionPaper.getNetworkFailTimeInSec();
					
					long duration = (formatter.parse(currentTime).getTime() - formatter
							.parse(lastAccessTime).getTime() + totalNwDelay);
					
					long durationInMin = duration / 1000;

					// network delay time
					if (durationInMin >= 0 && durationInMin < delayTime)
					{
						questionPaper
								.setLastUpdatedQuestionTime(new java.util.Date());
						// update network delay time
						questionPaper.setNetworkFailTimeInSec(duration);
						
						getOleExamService().updateGeneratedQuestionPaper(
								questionPaper);
						
						/*List<GeneratedQuePaper> list = null;
						GeneratedQuePaper generatedQuePaper = null;
						List<GeneratedQuePaperDtls> questionPaperDtls = null;

						list = getCandidateExaminationService()
								.getQuestionPaper(questionPaperId);

						if (list != null && list.size() > 0)
						{
							generatedQuePaper = (GeneratedQuePaper) list.get(0);
							questionPaperDtls = generatedQuePaper
									.getQpDetails();*/
							getSession().setAttribute("generatedQuePaper",
									questionPaper);
							getSession().setAttribute("QuestionPaperDtls",
									questionPaper.getQpDetails());
							response.getWriter().write(
									questionPaperId + "##"
											+ lastQuestionaccessed + "##"
											+ elapsed + "##" + "maxTime");
							return null;
					/*	}
						else
						{
							response.getWriter().write(
									"Question Paper not availabale");
							return null;
						}*/
					}
					else
					{ // user re-logins after network delay time
						response.getWriter().write("reschedule");
						return null;
					}
				}
			}
			else
			{
				logger.debug("candidate taking exam in first attempt  " + candidate.getPersonalNo());
				/*synchronized (CandidateExaminationAction.class)
				{*/
					if (examPaper != null && candidate != null) {

						generatedQuePaper = getOleExamService()
								.getGQPIdForCandidate(examPaper, candidate);

					} 
					if (generatedQuePaper.size()==0 && generatedQuePaper.isEmpty()){
						generatedQuePaper = getOleExamService().getExamPaper(
								examPaper);
					}
					DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					OleExam exam = (OleExam) getSession().getAttribute("exam");
					List<ExamWindow> examWindows = getOleExamService()
							.getActiveExamWindows(
									getOleExamService().getExamByName(
											exam.getName()));

					if (examWindows != null && examWindows.size() == 1)
					{

						enrollment = getCandidateEnrollmentService()
								.getCandidateEnrollmentDetails(
										examWindows.get(0).getId(),
										candidate.getId());
						canDetailsList = enrollment
								.getCandidateEnrollmentDetails();
						Date todayDate = new Date();
						Calendar todayCal = Calendar.getInstance();
						todayCal.setTime(todayDate);
						todayCal.set(Calendar.HOUR_OF_DAY, 0);
						todayCal.set(Calendar.MINUTE, 0);
						todayCal.set(Calendar.SECOND, 0);
						todayCal.set(Calendar.MILLISECOND, 0);

						// get the time slot - startTime & allowed of the exam
						for (int i = 0; i < canDetailsList.size(); i++)
						{
							slot = new TimeSlot();
							canDetails = canDetailsList.get(i);
							slot = canDetails.getTimeSlots();

							Date date = (Date) formatter.parse(canDetails
									.getEnrollmetExamDate().toString());
							Calendar enrollDate = Calendar.getInstance();
							enrollDate.setTime(date);
							enrollDate.set(Calendar.HOUR_OF_DAY, 0);
							enrollDate.set(Calendar.MINUTE, 0);
							enrollDate.set(Calendar.SECOND, 0);
							enrollDate.set(Calendar.MILLISECOND, 0);
							if (todayCal.getTime().compareTo(
									enrollDate.getTime()) == 0)
							{

								if (slot.getId().intValue() == Integer
										.parseInt(subjTimeSlotId))
								{
									startTime = slot.getStartTime();
									allowedTime = slot.getAllowedUpto();
									endTime = slot.getEndTime();
									candEnrollDtlId = canDetails.getId();
									break;
								}
							}
						}
						String[] st = startTime.split(":");
						String[] end = endTime.split(":");
						String[] UpTo = allowedTime.split(":");

						examStartTime.set(Calendar.HOUR_OF_DAY, Integer
								.parseInt(st[0]));
						examStartTime.set(Calendar.MINUTE, Integer
								.parseInt(st[1]));
						examStartTime.set(Calendar.SECOND, 0);
						examStartTime.set(Calendar.MILLISECOND, 0);

						examEndTime.set(Calendar.HOUR_OF_DAY, Integer
								.parseInt(end[0]));
						examEndTime.set(Calendar.MINUTE, Integer
								.parseInt(end[1]));
						examEndTime.set(Calendar.SECOND, 0);
						examEndTime.set(Calendar.MILLISECOND, 0);

						examAllowUpTo.set(Calendar.HOUR_OF_DAY, Integer
								.parseInt(UpTo[0]));
						examAllowUpTo.set(Calendar.MINUTE, Integer
								.parseInt(UpTo[1]));
						examAllowUpTo.set(Calendar.SECOND, 0);
						examAllowUpTo.set(Calendar.MILLISECOND, 0);

						// exStartTime = df.format(examStartTime.getTime());
						exAllowedTime = df.format(examAllowUpTo.getTime());
						exEndTime = df.format(examEndTime.getTime());

						Date date1 = new Date();
						DateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");
						String currentTime = formatter2.format(date1);

						long elapsed = (df.parse(exEndTime).getTime() - df
								.parse(currentTime).getTime()) / 1000;

						long allowedTimeInSecs = (df.parse(exAllowedTime)
								.getTime() - (df.parse(currentTime)).getTime()) / 1000;
						// to check allowed time
						if (cal.compareTo(examStartTime) >= 0
								&& cal.compareTo(examEndTime) <= 0)
						{
							if (allowedTimeInSecs < 0)
							{
								response.getWriter().write("NotAllowedTime");
								return null;
							}
							// update the enrollment details
							updateEnrollmentDetails(subjTimeSlotId,
									subjectCodeId, true);
							// update generated question paper
							candidate = getCandidateService().getCandidateId(
									name);
							status.setId(Long.parseLong(StatusCode.ONGOING
									.code())); // ongoing;
							generatedQuePaper.get(0).setStartTime(
									new java.util.Date());
							generatedQuePaper.get(0).setCandidate(candidate);
							generatedQuePaper.get(0).setStatus(status);
							generatedQuePaper.get(0).setCandEnrollDtlId(
									candEnrollDtlId);
							generatedQuePaper.get(0)
									.setLastUpdatedQuestionTime(
											new java.util.Date());
							generatedQuePaper.get(0).setTimeLeftInSecs(elapsed);
							generatedQuePaper.get(0).setNetworkFailTimeInSec(0L);
							
							getOleExamService().updateGeneratedQuestionPaper(
									generatedQuePaper.get(0));
							getSession().setAttribute("genQPaper",
									generatedQuePaper.get(0));
							questionPaperId = generatedQuePaper.get(0).getId();

							List<GeneratedQuePaper> list = null;
							GeneratedQuePaper generatedQuePaper = null;
							List<GeneratedQuePaperDtls> questionPaperDtls = null;

							list = getCandidateExaminationService()
									.getQuestionPaper(questionPaperId);

							if (list != null && list.size() > 0)
							{
								generatedQuePaper = (GeneratedQuePaper) list
										.get(0);
								questionPaperDtls = generatedQuePaper
										.getQpDetails();
								getSession().setAttribute("generatedQuePaper",
										generatedQuePaper);
								getSession().setAttribute("QuestionPaperDtls",
										questionPaperDtls);
								response.getWriter().write(
										questionPaperId + "##" + "first" + "##"
												+ elapsed);
								return null;
							}
							else
							{
								response.getWriter().write(
										"Question Paper not availabale");
								return null;
							}
						}
						else
						{
							response.getWriter().write("NotAllowedTime");
							return null;
						}
					}
					else
					{
						response.getWriter().write(
								"Question Paper not availabale");
						return null;
					}
				//}
			}

		}
		catch (Exception e)
		{
			logger.error("Exception: " + DGMTUtil.getStackTrace(e));
		}

		return null;
	}
	

	public String getQuestion()
	{
		logger.info("getQuestion.01.Start");
		/* Validate the secureKey
		 * Get the Enrolled Examinations
		 * Select the Exam -> Open Window for entering  Proctor's Key (Ajax will with check the Proc Key)
		 * Display Examination Details and Instructions like
		 * Number of Questions in Exam Paper and Number of Sections from -- B_EXAM_PAPER_SECTION
		 * On clicking on the 'Start Examination' have to fetch the questions order by Section
		 * Maintain the Total Number of Questions to Display in Each Page or Section.(Example 1 of 40 )
		 * Start the Timer 
		 * */
		try{
			//Map<Integer,Object>  unAnsweredQuestions =  null; //new HashMap<Integer,Object>();
			String name = request.getUserPrincipal().toString();	 
			CandidateEnrollememt candidateEnrollememt=null;
			List<GeneratedQuePaperDtls> questionPaperDtls = (List<GeneratedQuePaperDtls>)getSession().getAttribute("QuestionPaperDtls");
			//Map<Integer,Object>  unAnsweredQuestions = (HashMap<Integer,Object>)getSession().getAttribute("unAnsweredQuestions");
			QuestionDetails questionDetails = new QuestionDetails();
			Status status = new Status();
			GeneratedQuePaperDtls  quePaperDtls= null;
			GeneratedQuePaper generatedQuePaper = null;
			List<GeneratedQuePaper>   list = null;
			int quesNo = 0;
			String questionNo = request.getParameter("questionNo");
			String questionPaperId = request.getParameter("questionPaperId");
			String currentQuestionNo = request.getParameter("currentQuestionNo");
			answer = request.getParameter("answer"); 
			String action = request.getParameter("action");  
			String subjTimeSlotId = request.getParameter("subjTimeSlotId");
			String subjectCodeId = request.getParameter("subjectCodeId");
			String message = request.getParameter("message");
			String timeLeftInSecs = request.getParameter("timeLeftInSecs");
			String imageDir = servletContext.getRealPath("/")+"/DGMTImages/";
			// For image saving
			String imageName = request.getParameter("imageName");
			generatedQuePaper = (GeneratedQuePaper)getSession().getAttribute("generatedQuePaper");
			Long statusId  = getCandidateExaminationService().getQuestionPaperStatusId(questionPaperId);

			String visited = "1";
			if(answer != null)
			{
				answer = answer.replaceAll("@@@@", "\r\n").replaceAll("@@@@","\n");
				if(answer.trim().replace("~", "").length() !=0)
				{
					visited = "2";
				}
			}
			logger.info("answer2 is :::"+answer);
			if (StringUtils.isNotBlank(action) && ( action.equals("finishExam") || action.equals("finish"))) {  
			
				if (StringUtils.isNotBlank(questionNo)){
	  				try {
		  				quePaperDtls = questionPaperDtls.get(Integer.parseInt(questionNo));
			 			quePaperDtls.setAnswer(answer);
						quePaperDtls.setMarks(null);
						quePaperDtls.setVisited(visited);
						getOleExamService().updateQuestionDtls(quePaperDtls);
	  				} catch(Exception e){
	  					DGMTUtil.getStackTrace(e);
	  				}
				}
			}
			if (action!=null && !action.equals("fromReview") && !action.equals("finishExam"))   {

				if(questionNo != null && !questionNo.equals("0")){   
					try {
						if (action!=null && action.equals("next")){ // user clicked on next button
							quesNo = Integer.parseInt(questionNo)-1;
							generatedQuePaper.setQuestionId((quesNo+1)+"");
						} else if (action!=null && action.equals("previous")){ // user clicked on next button
							quesNo = Integer.parseInt(questionNo)+1; 
							generatedQuePaper.setQuestionId((quesNo-1)+""); 
						} else  if  (action!=null && action.equals("displayQuestion")) { // user clicked on review question link
							quesNo = Integer.parseInt(currentQuestionNo);
							generatedQuePaper.setQuestionId(questionNo+"");
						}  else { 
							quesNo = Integer.parseInt(questionNo);
							generatedQuePaper.setQuestionId((quesNo+1)+"");
						}
						// user clicked on next/previous button
						// save the answer 
						quePaperDtls = questionPaperDtls.get(quesNo);
						if (message!=null && !message.equals("maxTime") && !message.equals("")){
 							quePaperDtls.setAnswer(answer);
 							quePaperDtls.setMarks(null);
 							quePaperDtls.setVisited(visited);
 							if (statusId!= null && statusId!= 6)
							getOleExamService().updateQuestionDtls(quePaperDtls);
						}
							// to identify network failure or power off scenario
						 if(timeLeftInSecs!=null)
							generatedQuePaper.setTimeLeftInSecs(Long.parseLong(timeLeftInSecs)); 
							generatedQuePaper.setLastUpdatedQuestionTime(new java.util.Date());
							if (statusId!= null && statusId!= 6)
		                    getOleExamService().updateGeneratedQuestionPaper(generatedQuePaper);

					} catch (Exception exception){
						
						logger.error("exception  :" + DGMTUtil.getStackTrace(exception));
					}
						//to display the question
					if (questionNo!=null && !questionNo.equals("-1"))  
						quePaperDtls = questionPaperDtls.get(Integer.parseInt(questionNo));
				 
            } else {
            		questionNo = "0";
            	
                // from 2nd question user clicked on previous button, to save the first question
                if (action!=null && (action.equals("previous") || action.equals("toReview") || action.equals("displayQuestion"))){
                	try{
    				
                	if (action.equals("toReview")){
                		quesNo = Integer.parseInt(questionNo);
                		generatedQuePaper.setQuestionId((quesNo+1)+"");
                	}else if (action.equals("displayQuestion")) {
                		quesNo = Integer.parseInt(currentQuestionNo);
                		generatedQuePaper.setQuestionId(questionNo+""); 
                	} else{
                		quesNo = Integer.parseInt(questionNo)+1;
                		generatedQuePaper.setQuestionId((quesNo-1)+""); 
                	}
                		
                	quePaperDtls = questionPaperDtls.get(quesNo);
                	if (message!=null && !message.equals("maxTime")){
						quePaperDtls.setAnswer(answer);
						quePaperDtls.setMarks(null);
						quePaperDtls.setVisited(visited);
						if (statusId!= null && statusId!= 6)
						getOleExamService().updateQuestionDtls(quePaperDtls);
                	}
					// to identify network failure or power off scenario 
					generatedQuePaper.setTimeLeftInSecs(Long.parseLong(timeLeftInSecs));
					//generatedQuePaper.setQuestionId(quesNo+"");  
					generatedQuePaper.setLastUpdatedQuestionTime(new java.util.Date());
					if (statusId!= null && statusId!= 6)
                    getOleExamService().updateGeneratedQuestionPaper(generatedQuePaper);

                    // To get the first question
                	quePaperDtls = questionPaperDtls.get(Integer.parseInt(questionNo));
                	} catch(Exception e){
                		
                		logger.error("Exception: " + DGMTUtil.getStackTrace(e));
                	}
                } else {
                	quePaperDtls = questionPaperDtls.get(Integer.parseInt(questionNo));
                	
                	// to save in questionPaperDtls when user starts the exam;
                	if (action!=null && !action.equals("finishExam")){  
                				candidate  = getCandidateService().getCandidateId(name);
                			if (getSession().getAttribute("generatedQuePaper")!=null) {
	    					//if (generatedQuePaper!=null) {
	    						generatedQuePaper = (GeneratedQuePaper)getSession().getAttribute("generatedQuePaper");
			                    OleExam exam = (OleExam)getSession().getAttribute("exam");
			                    List<ExamWindow> examWindows = getOleExamService().getActiveExamWindows(getOleExamService().getExamByName(exam.getName()));
			                  
			        			if(examWindows != null && examWindows.size() == 1)
			        			{
			        				candidateEnrollememt = getCandidateEnrollmentService().getCandidateEnrollmentDetails(examWindows.get(0).getId(), candidate.getId());
			        			}
			        			generatedQuePaper.setQuestionId(questionNo);
								generatedQuePaper.setLastUpdatedQuestionTime(new java.util.Date());
			                    generatedQuePaper.setCandidateEnrollememt(candidateEnrollememt);
			                    if (statusId!= null && statusId!= 6)
			                    getOleExamService().updateGeneratedQuestionPaper(generatedQuePaper);
	    					}
	            	  }	
                	}
            	}
			}
			if (StringUtils.isNotBlank(action) && (action.equals("finish")|| action.equals("fromReview") || (action.equals("finishExam")) )) {
				//forward to
				int answerCount = 0;
				String chAnswer = "";
				int objectiveCount = 0;
				//int marksPerQuestion = 0;
				int totalMarks = 0;
				int order  = 0;
				list  = getCandidateExaminationService().getQuestionPaper(new Long(questionPaperId));
			
				if (list!=null) {
					generatedQuePaper = (GeneratedQuePaper)list.get(0);
					
					if (generatedQuePaper.getStatus().getId()==Long.parseLong(StatusCode.SUBMITTED.code())) { 
						ResultDetails resultDetails = new ResultDetails();
						resultDetails.setIsResultsSubmitted("true");
						response.getWriter().write(JSONUtil.serialize(resultDetails));
						return null;
					}
					questionPaperDtls = generatedQuePaper.getQpDetails(); 
					
					// save end time of the exam.
					
					generatedQuePaper.setEndTime(new java.util.Date());
					status.setId(Long.parseLong(StatusCode.SUBMITTED.code())); // Submitted;
					generatedQuePaper.setStatus(status);
					
					//getOleExamService().updateGeneratedQuestionPaper(generatedQuePaper);
 					
					// on submitting the results update the candidate enrollement details with subject id;
					
					updateEnrollmentDetails(subjTimeSlotId,subjectCodeId,false);
					
					/*Set<ExamPaperSection> sections = generatedQuePaper.getExamPaper().getSections();
						Iterator<ExamPaperSection> it =  sections.iterator();
						while( it.hasNext() ){
							ExamPaperSection eps =  (ExamPaperSection)it.next() ;
							if (eps.getType().equalsIgnoreCase("0"))
								marksPerQuestion = eps.getMarksPerQuestion();
						}*/
					
					int size = questionPaperDtls.size();
					int i = 0;
					
					// iterate over the GeneratedQuePaper ,to find out the correct objective answers
					// quePaperDtls.getQuestion().getQuestionType()==4 //ignored because of subjective questions
					
					List<OleChoice> choices = null;
					OleChoice ch = null;
					int size1 = 0;
					String canArray[];
					
					int occurances = 0;
					int pipeCount = 0;
					String pipeArray[];
					String ansText = "";
					String questionText = "";
					String answer2 = "";
					OleMatching mt;
					
					ArrayList<String> canAnswer = new ArrayList<String>();
					
					for( i = 0 ;i < size; i++)
					{
						int count = 0;
						quePaperDtls = questionPaperDtls.get(i);
						
						// single choice or multiple  choice 
						
						if (quePaperDtls.getQuestion().getQuestionType()==1 )
						{	
							objectiveCount++;
							choices  =   quePaperDtls.getQuestion().getChoices();
							size1 = choices.size();
							chAnswer = "";
							for (int j = 0; j < size1 ;j++){
								ch = choices.get(j);
								if (ch.getIsAns()==1)
									chAnswer = chAnswer + "~"+ch.getOptionText();
								else 
									chAnswer = chAnswer + "~";
							}
							logger.debug("chAnswer :    "+chAnswer);
							chAnswer = chAnswer.substring(1,chAnswer.length());
							logger.debug(" user selected :    "+quePaperDtls.getAnswer());
							logger.debug(" actual answer :    "+chAnswer);
							
							if (StringUtils.isNotBlank(chAnswer) &&  chAnswer.equals(quePaperDtls.getAnswer())){
								answerCount = answerCount +1;
								totalMarks = (int) (totalMarks + (Double) (quePaperDtls.getMaxMarks())); 
								quePaperDtls.setMarks(new Double(quePaperDtls.getMaxMarks()));
								getOleExamService().updateQuestionDtls(quePaperDtls);
							}else{
								quePaperDtls.setMarks(new Double(0));
								getOleExamService().updateQuestionDtls(quePaperDtls);
							}
						}
						
						// // true or false
						if (quePaperDtls.getQuestion().getQuestionType()==2)
						{	
							objectiveCount++;
							choices  =   quePaperDtls.getQuestion().getChoices();
							size1 = choices.size();
							chAnswer = "";
							for (int j = 0; j < size1 ;j++){
								ch = choices.get(j);
								if (ch.getIsAns()==1)
									chAnswer = chAnswer + "~"+ch.getOptionText();
								else 
									chAnswer = chAnswer + "~";
							}
							logger.debug("chAnswer :    "+chAnswer);
							chAnswer = chAnswer.substring(1,chAnswer.length());
							logger.debug(" user selected :    "+quePaperDtls.getAnswer());
							logger.debug(" actual answer :    "+chAnswer);
							
							if (StringUtils.isNotBlank(chAnswer) &&  chAnswer.equals(quePaperDtls.getAnswer())){
								answerCount = answerCount +1;
								totalMarks = (int) (totalMarks + (Double) (quePaperDtls.getMaxMarks())); 
								quePaperDtls.setMarks(new Double(quePaperDtls.getMaxMarks()));
								getOleExamService().updateQuestionDtls(quePaperDtls);
							}else{
								quePaperDtls.setMarks(new Double(0));
								getOleExamService().updateQuestionDtls(quePaperDtls);
							}
						}
						// fill in the blanks
					
						if (quePaperDtls.getQuestion().getQuestionType()==3){   
							choices  =   quePaperDtls.getQuestion().getChoices();
							order = quePaperDtls.getQuestion().getOrder();
							objectiveCount++;
							 
							
							size1 = choices.size();
							if (order > 0) {
								// add the actual answer to array list 	
								for (int j = 0; j < size1 ;j++){
									ch = choices.get(j);
									if (ch.getIsAns()==1)
									canAnswer.add(ch.getOptionText());
								}
								// compare the actual answer with the 	
								// checking for any order question.
							
								if (canAnswer!=null && canAnswer.size() > 0) {
									if (quePaperDtls.getAnswer()!=null) {
										canArray = quePaperDtls.getAnswer().split(",");
										if (canArray!=null){
											for (int a = 0; a< canArray.length; a++ ){
												for (int c = 0 ; c<canAnswer.size(); c++){
													if (canAnswer.get(c).equalsIgnoreCase(canArray[a])){
														count++;
													}
												}
											}
										}
									}
								}
								if (count==canAnswer.size()) {
									answerCount = answerCount +1;
									totalMarks = (int) (totalMarks + (Double) (quePaperDtls.getMaxMarks())); 
									quePaperDtls.setMarks(new Double(quePaperDtls.getMaxMarks()));
									getOleExamService().updateQuestionDtls(quePaperDtls);
								}else{
									quePaperDtls.setMarks(new Double(0));
									getOleExamService().updateQuestionDtls(quePaperDtls);
								}
							}
							canAnswer.clear();
							// for | symbol answer .
							if (choices.size()==1){
								
								ch = choices.get(0);
								
								//int pipeIndex;
								ansText =ch.getOptionText();
								if (ansText!=null){
									pipeCount = org.springframework.util.StringUtils.countOccurrencesOf(ansText,"|");
									//pipeIndex = ansText.indexOf("|");
									
									if (pipeCount > 0 ){
										pipeArray = ansText.split("\\|");
										
										if (pipeCount > 0) {
											for (int j = 0; j < pipeArray.length ;j++){
												if (ch.getIsAns()==1)
												canAnswer.add(pipeArray[j]);
											}
										}
									}else {
										canAnswer.add(ch.getOptionText()); 
									}
								}
								
								 questionText =  quePaperDtls.getQuestion().getQuestionText();
								 occurances = org.springframework.util.StringUtils.countOccurrencesOf(questionText,"{}");
								if (occurances > 0) {
									
									
									if (canAnswer!=null && canAnswer.size() > 0) {
										if (quePaperDtls.getAnswer()!=null) {
											canArray = quePaperDtls.getAnswer().split(",");
											if (canArray!=null){
												for (int a = 0; a< canArray.length; a++ ){
													for (int c = 0 ; c<canAnswer.size(); c++){
														if (canAnswer.get(c).equalsIgnoreCase(canArray[a])){
															count++;
														}
													}
												}
											}
										}
									}
									if (count==occurances) {
										answerCount = answerCount +1;
										totalMarks = (int) (totalMarks + (Double) (quePaperDtls.getMaxMarks()));
										quePaperDtls.setMarks(new Double(quePaperDtls.getMaxMarks()));
										getOleExamService().updateQuestionDtls(quePaperDtls);
									}else{
										quePaperDtls.setMarks(new Double(0));
										getOleExamService().updateQuestionDtls(quePaperDtls);
									}	
								}
							}
							canAnswer.clear();
						}
						// match the following
						if (quePaperDtls.getQuestion().getQuestionType()==4){ 
							List<OleMatching> matching  =   quePaperDtls.getQuestion().getMatchingOptions();
							objectiveCount++;
							
							size1 = matching.size();
							for (int j = 0; j < size1 ;j++){ 
								mt = matching.get(j);
								answer2 = answer2 + ","+mt.getCorrectAns();
							}
							answer2 = answer2.substring(1,answer2.length());
							if (StringUtils.isNotBlank(answer2) && StringUtils.isNotBlank(quePaperDtls.getAnswer())  && quePaperDtls.getAnswer().trim().equalsIgnoreCase(answer2)){
								answerCount = answerCount +1;
								totalMarks = (int) (totalMarks + (Double) (quePaperDtls.getMaxMarks()));
								quePaperDtls.setMarks(new Double(quePaperDtls.getMaxMarks()));
								getOleExamService().updateQuestionDtls(quePaperDtls);
							}else{
								quePaperDtls.setMarks(new Double(0));
								getOleExamService().updateQuestionDtls(quePaperDtls);  
							}
							answer2 = "";
						}
					}
				}
				// update the objective marks and total marks 
				 
				generatedQuePaper.setMarks(new Float(totalMarks));
				generatedQuePaper.setObjMarks(new Float(totalMarks));
				
				getOleExamService().updateGeneratedQuestionPaper(generatedQuePaper);
				
				logger.debug(" Total No. of Objective Questions     "+objectiveCount);
				logger.debug(" Total No. of Correct Answers     "+answerCount);
				logger.debug(" Total No. of Marks     "+totalMarks);
				
				ResultDetails resultDetails = new ResultDetails();
				resultDetails.setSubject(quePaperDtls.getQuestion().getSubject().getName());
				resultDetails.setTitle(quePaperDtls.getQuestion().getSubject().getDesc());
				resultDetails.setNoObjQuestions(objectiveCount+"");
				resultDetails.setNoCorrectAnswers(answerCount+"");
				resultDetails.setTotalMarks(totalMarks+"");
				
				resultDetails.setSections(generatedQuePaper.getExamPaper().getSections());
				resultDetails.setShowResults(generatedQuePaper.getExamPaper().getExamWindow().getShowResults());
				resultDetails.setPassPctObj(generatedQuePaper.getExamPaper().getExamWindow().getPassPctObj());
				
				resultDetails.setSubjectDetails(generatedQuePaper.getQpDetails().get(0).getQuestion().getSubject().getName()+" - Title :: "+generatedQuePaper.getExamPaper().getTitle());
				resultDetails.setUserName(""+generatedQuePaper.getCandidate().getPersonalNo());
				resultDetails.setStartedAt((""+generatedQuePaper.getStartTime()).split("\\.")[0]);
				
				if(imageName != null && ! "".equalsIgnoreCase(imageName))
				{
	 				request.setAttribute("resultDetails",resultDetails);
	 				return "resultpage";
				}
				
				//resultDetails.setGeneratedQuePaper(generatedQuePaper);
				response.getWriter().write(JSONUtil.serialize(resultDetails));
				
				return null;
			}
			//Map <String,GeneratedQuePaperDtls> reviewQuestions;
			//Map <String,GeneratedQuePaperDtls> attribute = null;
			//getSession().setAttribute("unAnsweredQuestions",unAnsweredQuestions);
			//generatedQuePaper = (GeneratedQuePaper) getSession().getAttribute("generatedQuePaper");
			
			DGMTProperties properties = DGMTProperties.getProperties();
			String enableQuesSearch = properties.getProperty("ENABLE_GOTO_FUNCTIONALITY");  
			
			List<OleChoice> choices = quePaperDtls.getQuestion().getChoices();
			
			/*for (int ch = 0 ; ch < choices.size();  ch++) {
				OleChoice olech = choices.get(ch);
				if (olech.getIsAns()==1) {
					olech.setIsAns(0);
					choices.set(ch, olech); 
				}
			}*/
			quePaperDtls.getQuestion().setChoices(choices);
			
			questionDetails.setGeneratedQuePaperDtls(quePaperDtls);
			questionDetails.setQuestionNo(questionNo);
			questionDetails.setQuestionsCount(Integer.valueOf(questionPaperDtls.size()));
			//questionDetails.setGeneratedQuePaper(generatedQuePaper); 
			questionDetails.setEnableQuesSearch(enableQuesSearch);
			
			questionDetails.setInstructions(generatedQuePaper.getExamPaper().getInstructions());
			
			// generatedQuePaper.examPaper.instructions
			questionDetails.setStatusId(statusId);
			// generatedQuePaper.status.id

			// generatedQuePaper.timeLeftInSecs
			questionDetails.setTimeLeftInSecs(generatedQuePaper.getTimeLeftInSecs());
			
			// generatedQuePaper.examPaper.title
			questionDetails.setExamPaperTitle(generatedQuePaper.getExamPaper().getTitle());
			
			// generatedQuePaper.candidate.personalNo
			questionDetails.setCandidatePersonalNo(generatedQuePaper.getCandidate().getPersonalNo());
			
			// generatedQuePaper.startTime
			questionDetails.setExamStartTime(generatedQuePaper.getStartTime());
			
			// generatedQuePaper.examPaper.sections
			questionDetails.setSections(generatedQuePaper.getExamPaper().getSections());
			
			// generatedQuePaper.examPaper.examWindow.showResults
			questionDetails.setShowResults(generatedQuePaper.getExamPaper().getExamWindow().getShowResults());
			
			// generatedQuePaper.examPaper.examWindow.passPctObj
			questionDetails.setPassPctObj(generatedQuePaper.getExamPaper().getExamWindow().getPassPctObj()); 
			
			//for checking review questions
			int reviewCount = 0;
			if (generatedQuePaper!=null) {
				questionPaperDtls = generatedQuePaper.getQpDetails();
				
				quePaperDtls = questionPaperDtls.get(Integer.parseInt(questionNo));
				
				if (quePaperDtls.getMarkForReview()!=null && quePaperDtls.getMarkForReview().equalsIgnoreCase("1"))
					questionDetails.setReview("true");
				
				int i = 0;
				int size = questionPaperDtls.size();
				for ( i = 0; i< size; i++){
					if (StringUtils.isNotBlank(questionPaperDtls.get(i).getMarkForReview()) && questionPaperDtls.get(i).getMarkForReview().equalsIgnoreCase("1")){  
						reviewCount++;
					}
				}
			}
			
			// Checking if Question with image has already been editied by candidate
			if(quePaperDtls !=null && quePaperDtls.getQuestion() != null && quePaperDtls.getQuestion().getResources() != null)
			{
				
				String questImageName = quePaperDtls.getQuestion().getResources().getFile();
				String imgName[] = questImageName.split("\\.");
				//imageUniqueName = imgName[0]+"_"+questionPaperId+"_"+questionNo+
				String picture                = imgName[0]+"_"+questionPaperId+"_"+questionNo+"."+imgName[1];
				String annotation             = imgName[0]+"_"+questionPaperId+"_"+questionNo+"-pic.xml";
				if ((new File(imageDir,picture)).exists())
				{
					questionDetails.setCandQuestionImage(picture);
				}
				else
				{
					makeCopyOfFile(questImageName,picture);
					questionDetails.setCandQuestionImage(picture);  
				}
				
				if ((new File(imageDir,annotation)).exists())
				{
					questionDetails.setCandQuestionImageAnno(annotation);
				}
				 
			}
			/*if (getSession().getAttribute("reviewQuestions")!=null){
				attribute = (Map) getSession().getAttribute("reviewQuestions");
				reviewQuestions = attribute;
				if (reviewQuestions.containsKey(questionNo)){
					questionDetails.setReview("true");
				}
			}
 			if (attribute!=null && attribute.size()>0)  
 				questionDetails.setReviewQuestionsCount(attribute.size()+"");
 			else
 				questionDetails.setReviewQuestionsCount("0");*/
			
			
 			if (reviewCount > 0) 
 				questionDetails.setReviewQuestionsCount(reviewCount+"");
 			else 
 				questionDetails.setReviewQuestionsCount("0"); 
 				
 			if(getSession().getAttribute("QuestionPaperDtls") != null)
 				getSession().removeAttribute("QuestionPaperDtls");
 			
 			getSession().setAttribute("QuestionPaperDtls", questionPaperDtls);     
 			
 			//questionDetails = (QuestionDetails) questionDetails.clone();

 			
 			if(imageName != null && ! "".equalsIgnoreCase(imageName))
			{
 				request.setAttribute("questionPaperId",questionPaperId);
 				request.setAttribute("subjTimeSlotId",subjTimeSlotId);
 				request.setAttribute("subjectCodeId",subjectCodeId);
 				request.setAttribute("questionNo",""+questionNo);
 				request.setAttribute("elapsed",timeLeftInSecs);
 				request.setAttribute("message","maxTime");
 				
 				return SUCCESS;
			}
            
			response.getWriter().write(JSONUtil.serialize(questionDetails));
		
		} catch(Exception exception){
			DGMTUtil.getStackTrace(exception);
		}
		return null;
	}

	
	public String viewCandidateExams()
	{
		return SUCCESS;	
	}
	
	
	
	public String getOleGeneratedQuestionPaper()
	{
		
		/*
		 * Examination
		 * Subject Title Time Slot
		 * Military History  Military History Part B 9:00 AM - 1:00 PM
		 * Time Limit
		 * 2 Hrs
		 * Instructions */
		
		
		try{
				String exampaperId = request.getParameter("exampaperId");   
				String name = request.getUserPrincipal().toString();
				candidate  = getCandidateService().getCandidateId(name);
								
			//	synchronized (CandidateExaminationAction.class) { 
					ExamPaper examPaper = getOleExamService().getExamPapers(Long.valueOf(exampaperId)); 
					generatedQuePaper = getOleExamService().getGQPIdForCandidate(examPaper,candidate);    

					if (generatedQuePaper!=null && generatedQuePaper.size()>0) {
						return null;
					} else {
						generatedQuePaper =  getOleExamService().getExamPaper(examPaper);
						if (generatedQuePaper!=null && generatedQuePaper.size()>0) {
							return null;
						}else {
							response.getWriter().write("Question paper not available");
							return null;  
						}
					}
				
			//	}
			} catch(Exception exception){
				DGMTUtil.getStackTrace(exception);
			}
		return null;
	}
	
 	public String viewCandiateExamDetails()
	{
		
		/*
		 * Examination
		 * Subject Title Time Slot
		 * Military History  Military History Part B 9:00 AM - 1:00 PM
		 * Time Limit
		 * 2 Hrs
		 * Instructions */
		try { 
			
		
		List<CandidateEnrollmentDetails> canDetailsList;
		CandidateEnrollmentDetails canDetails; 
		CandidateEnrollememt enrollment;
		TimeSlot slot;
		String startTime = "";
		String allowedTime = "";
		String endTime = "";
 	   // String exAllowedTime = "";
 	    String exStartTime = "";

 		String name = request.getUserPrincipal().toString();
		candidate  = getCandidateService().getCandidateId(name);
		
		Calendar cal = Calendar.getInstance();
        Calendar examStartTime = Calendar.getInstance();
        Calendar examEndTime = Calendar.getInstance();
        Calendar examAllowUpTo = Calendar.getInstance();
       
        Date today = new Date();
        cal.setTime(today);
        examStartTime.setTime(today);
        examEndTime.setTime(today);
        examAllowUpTo.setTime(today);
		
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

		if (getSession().getAttribute("exampaperId")!=null)
			getSession().removeAttribute("exampaperId");
		
		subjectName = request.getParameter("subjectName"); 
		if(subjectName.contains("amp;")){
			subjectName = subjectName.replaceAll("amp;", "");
		}
		subjectTitle = request.getParameter("subjectTitle");
		if(subjectTitle.contains("amp;")){
			subjectTitle = subjectTitle.replaceAll("amp;", "");
		}
		subjectTimeSlot = request.getParameter("subjectTimeSlot");
		subjectTimeDuration = request.getParameter("subjectTimeDuration");  
		subjectTimeSlotId = request.getParameter("subjectTimeSlotId");
		subjectCodeId = request.getParameter("subjectCodeId");
		
		ExamPaper examPaper = getOleExamService().getExamPapers(Long.valueOf(request.getParameter("exampaperId")));
		
		request.setAttribute("subject", subjectName);
		request.setAttribute("title", subjectTitle);
		request.setAttribute("timeDuration",subjectTimeDuration);
		request.setAttribute("subjectTimeSlotId",subjectTimeSlotId);
		request.setAttribute("subjectCodeId",subjectCodeId);
		request.setAttribute("examPaper",examPaper);    
		
		
		OleExam exam = (OleExam)getSession().getAttribute("exam");
        List<ExamWindow> examWindows = getOleExamService().getActiveExamWindows(getOleExamService().getExamByName(exam.getName()));
      
		if(examWindows != null && examWindows.size() == 1)
			{
			 
			enrollment = getCandidateEnrollmentService().getCandidateEnrollmentDetails(examWindows.get(0).getId(), candidate.getId());
			canDetailsList = enrollment.getCandidateEnrollmentDetails();
				
			// get the time slot  -  startTime & allowed of the exam
				for (int i = 0; i < canDetailsList.size(); i++){
					slot = new TimeSlot();
					canDetails = canDetailsList.get(i); 
					slot = canDetails.getTimeSlots();
					
					if (slot.getId().intValue()==Integer.parseInt(subjectTimeSlotId)){
						startTime 	=  slot.getStartTime();
						allowedTime =  slot.getAllowedUpto();
						endTime		=  slot.getEndTime();
					}
				}
			 	String[] st = startTime.split(":");
			 	String[] end = endTime.split(":");
		        String[] UpTo = allowedTime.split(":");
		       
		        examStartTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(st[0]));
		        examStartTime.set(Calendar.MINUTE, Integer.parseInt(st[1]));	
		        examStartTime.set(Calendar.SECOND, 0);
		        examStartTime.set(Calendar.MILLISECOND, 0);
		        
		        examEndTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(end[0]));
		        examEndTime.set(Calendar.MINUTE, Integer.parseInt(end[1]));	
		        examEndTime.set(Calendar.SECOND, 0);
		        examEndTime.set(Calendar.MILLISECOND, 0);
		
		        examAllowUpTo.set(Calendar.HOUR_OF_DAY, Integer.parseInt(UpTo[0]));
		        examAllowUpTo.set(Calendar.MINUTE, Integer.parseInt(UpTo[1]));
		        examAllowUpTo.set(Calendar.SECOND, 0);
		        examAllowUpTo.set(Calendar.MILLISECOND, 0);
		        
 			   // exAllowedTime = df.format(examAllowUpTo.getTime());
			    
			    Date date1 = new Date();
			    DateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");  
			    String currentTime = formatter2.format(date1); 
		    
			    exStartTime = df.format(examStartTime.getTime());   
 			    
			    elapsedTime = (df.parse(exStartTime).getTime() -  df.parse(currentTime).getTime())/1000;
			    //elapsedTime = ( df.parse(exAllowedTime).getTime() - (df.parse(currentTime)).getTime())/1000 ;
		}
		getSession().setAttribute("exampaperId",request.getParameter("exampaperId"));
		request.setAttribute("elapsedTime",elapsedTime);    
 		} catch (Exception e){
			DGMTUtil.getStackTrace(e);
		}
	 
		return SUCCESS;
	}
	
	 @SuppressWarnings("unchecked")
     public String setReviewQuestion()
	 {
	 	List<GeneratedQuePaperDtls> questionPaperDtls = (List)getSession().getAttribute("QuestionPaperDtls");
        Map reviewQuestions = (HashMap)getSession().getAttribute("reviewQuestions");
        String questionNo;
        String addRemoveFlag;
        GeneratedQuePaperDtls dtls = null;
        questionNo = request.getParameter("questionNo");
        addRemoveFlag = request.getParameter("addRemoveFlag");
        
        int i = 0;
		int reviewCount = 0;
		int size = 0;
		
		if(reviewQuestions == null)
        reviewQuestions = new HashMap();
		
		if(questionNo == null)
	            return null;
        
        try
        {
        	if( addRemoveFlag != null && questionNo != null )
            if(addRemoveFlag.equalsIgnoreCase("true")) {
                reviewQuestions.put(questionNo, (GeneratedQuePaperDtls)questionPaperDtls.get(Integer.parseInt(questionNo)));
	                dtls = (GeneratedQuePaperDtls)questionPaperDtls.get(Integer.parseInt(questionNo));
		        	dtls.setMarkForReview("1");
	 	        	getOleExamService().updateQuestionDtls(dtls);
            } else {
            	if(addRemoveFlag.equalsIgnoreCase("false")) {
            		
	            	reviewQuestions.remove(questionNo);
	            	getSession().setAttribute("reviewQuestions", reviewQuestions);
	            	
	                // update in details
	            	
            		if (StringUtils.isNotBlank(questionNo)) {
		            	dtls = (GeneratedQuePaperDtls)questionPaperDtls.get(Integer.parseInt(questionNo));
			        	dtls.setMarkForReview("0");
		 	        	getOleExamService().updateQuestionDtls(dtls);
            		}
            	}
            }
            
            size = questionPaperDtls.size();
            
            for ( i = 0; i< size; i++){ 
				if (StringUtils.isNotBlank(questionPaperDtls.get(i).getMarkForReview()) && questionPaperDtls.get(i).getMarkForReview().equalsIgnoreCase("1")){  
					reviewCount++;
				}
			}
            
            if (reviewCount > 0)   
				response.getWriter().write(reviewCount+"");
			else
				response.getWriter().write("0"); 
            	
        }
        catch(Exception e)
        {
        	DGMTUtil.getStackTrace(e);
        }
        return null;
       }
	 
	 //	@SuppressWarnings("unchecked")
	 public String showReviewQuestions() {

		QuestionDetails details = new QuestionDetails();
		GeneratedQuePaper generatedQuePaper = null;
		List<GeneratedQuePaperDtls> generatedQuePaperDtls = null;
		GeneratedQuePaperDtls questionPaperDtls = null;
		List<GeneratedQuePaper> list = null;
		paperId = request.getParameter("paperId");

		List<OleChoice> choices = null;
		try {
			if (paperId != null)

				list = getCandidateExaminationService().getQuestionPaper(Long.parseLong(paperId));

			if (list != null && list.size() > 0) {

				generatedQuePaper = (GeneratedQuePaper) list.get(0);
				
				generatedQuePaperDtls = generatedQuePaper.getQpDetails();

				if (generatedQuePaperDtls != null && generatedQuePaperDtls.size() > 0) {

					int i = 0;
					String keys = "";
					int reviewCount = 0;
					int size = generatedQuePaperDtls.size();

					for (i = 0; i < size; i++) {

						questionPaperDtls = generatedQuePaperDtls.get(i);

						//choices = questionPaperDtls.getQuestion().getChoices();

						//questionPaperDtls.getQuestion().setChoices(choices);

						if (StringUtils.isNotBlank(questionPaperDtls.getMarkForReview())
								&& questionPaperDtls.getMarkForReview().equalsIgnoreCase("1")) {

							keys += ("," + i);
							reviewCount++;

						}
					}
					if (StringUtils.isNotBlank(keys)) {
						keys = keys.substring(1);
						details.setKeys(keys);
						details.setGenQPaperDtls(generatedQuePaperDtls);
						details.setReviewQuestionsCount(reviewCount + "");
					}
					response.getWriter().write(JSONUtil.serialize(details));

				}
			} else {

				if (getSession().getAttribute("generatedQuePaper") != null) {

					generatedQuePaper = (GeneratedQuePaper) getSession().getAttribute("generatedQuePaper");
					generatedQuePaperDtls = generatedQuePaper.getQpDetails();
					if (generatedQuePaperDtls != null && generatedQuePaperDtls.size() > 0) {

						int i = 0;
						String keys = "";
						int reviewCount = 0;
						int size = generatedQuePaperDtls.size();

						for (i = 0; i < size; i++) {

							questionPaperDtls = generatedQuePaperDtls.get(i);

							//choices = questionPaperDtls.getQuestion().getChoices();

							//questionPaperDtls.getQuestion().setChoices(choices);

							if (StringUtils.isNotBlank(questionPaperDtls.getMarkForReview())
									&& questionPaperDtls.getMarkForReview().equalsIgnoreCase("1")) {

								keys += ("," + i);
								reviewCount++;

							}
						}
						if (StringUtils.isNotBlank(keys)) {
							keys = keys.substring(1);
							details.setKeys(keys);
							details.setGenQPaperDtls(generatedQuePaperDtls);
							details.setReviewQuestionsCount(reviewCount + "");
						}
					}
				}

				response.getWriter().write(JSONUtil.serialize(details));
			}
		} catch (Exception e) {
			DGMTUtil.getStackTrace(e);
		}
		return null;
	}
	 	public String validateCandidate()
		{
			try{
					String name = request.getUserPrincipal().toString();  	 
					//CandidateService candidateService = (CandidateService) getBean("candidateService"); 
					candidate  = getCandidateService().getCandidateId(name);
					CandidateEnrollmentService cEnrollService  = (CandidateEnrollmentService) getBean("candidateEnrllmentService");
		
					logger.debug("Candidate Id :  "+candidate.getId());
					//Long examWindowId = new Long(1);
					String examId = request.getParameter("examId");
					OleExam exam = getOleExamService().getExamById(Long.valueOf(examId));
		
					List<ExamWindow> examWindows = getOleExamService().getActiveExamWindows(getOleExamService().getExamByName(exam.getName()));
					
					if(examWindows != null && examWindows.size() == 1)
					{
					
					CandidateEnrollememt candidateEnrollememt = null;
					
					candidateEnrollememt =  (CandidateEnrollememt) cEnrollService.getCandidateEnrollmentDetails(examWindows.get(0).getId(),candidate.getId());
					
					if (candidateEnrollememt!=null && ("0".equalsIgnoreCase(candidateEnrollememt.getStatus()) ))  
						response.getWriter().write("Debarred");
					else
						response.getWriter().write("valid");
					} else {
						response.getWriter().write("There are no active windows ...");
					}
				
				} catch(Exception exception){
					DGMTUtil.getStackTrace(exception);
				}
			return null;
		}
	 	
	 	public String updateGenQuePaper() {
	 		try{
		 		GeneratedQuePaper genQuePaper = (GeneratedQuePaper)getSession().getAttribute("genQPaper");
				candidate.setId(null);
				genQuePaper.setCandidate(candidate);
				getOleExamService().updateGeneratedQuestionPaper(genQuePaper);
	 		} catch(Exception e){
	 			DGMTUtil.getStackTrace(e);
	 		}
	 		
	 		return null;
	 	}
	 	public void updateEnrollmentDetails(String subjTimeSlotId,String subjectCodeId,boolean flag) {
	 			CandidateEnrollememt candidateEnrollememt=null;
	 			List<CandidateEnrollmentDetails> candidateEnrollmentDetails;
				CandidateEnrollmentDetails canEnrollmentDetails;
 			
	 		try{
	 			String name = request.getUserPrincipal().toString();  	 
				candidate  = getCandidateService().getCandidateId(name);
	 			OleExam exam = (OleExam)getSession().getAttribute("exam");
                List<ExamWindow> examWindows = getOleExamService().getActiveExamWindows(getOleExamService().getExamByName(exam.getName()));
            	Date todayDate = new Date();
                Calendar todayCal = Calendar.getInstance();
    			todayCal.setTime(todayDate);
    			todayCal.set(Calendar.HOUR_OF_DAY, 0);
    			todayCal.set(Calendar.MINUTE, 0);
    			todayCal.set(Calendar.SECOND, 0);
    			todayCal.set(Calendar.MILLISECOND, 0);
    			DateFormat formatter1  = new SimpleDateFormat("yyyy-MM-dd"); 
    			if(examWindows != null && examWindows.size() == 1)
    			{
    				TimeSlot slot = new TimeSlot();
    				slot.setId(Long.parseLong(subjTimeSlotId));
    				candidateEnrollememt = getCandidateEnrollmentService().getCandidateEnrollmentDetails(examWindows.get(0).getId(), candidate.getId());
    				candidateEnrollmentDetails = candidateEnrollememt.getCandidateEnrollmentDetails();
					
			        
    				for (int i = 0; i < candidateEnrollmentDetails.size(); i++){
    					canEnrollmentDetails = candidateEnrollmentDetails.get(i);
        				Date  date = (Date)formatter1.parse(canEnrollmentDetails.getEnrollmetExamDate().toString());
        				Calendar enrollDate = Calendar.getInstance();
    			        enrollDate.setTime(date); 
    			        enrollDate.set(Calendar.HOUR_OF_DAY, 0);
    			        enrollDate.set(Calendar.MINUTE, 0);
    			        enrollDate.set(Calendar.SECOND, 0);
    			        enrollDate.set(Calendar.MILLISECOND, 0);
    					if (todayCal.getTime().compareTo(enrollDate.getTime())== 0 ){  
	    					if (canEnrollmentDetails.getTimeSlots().getId().intValue()==slot.getId().intValue()){
	    							OleSubject oleSubject = getOleExamService().getSubject(Long.parseLong(subjectCodeId));
	    							if (flag) {
	    								canEnrollmentDetails.setSlotUserForSubjectId(oleSubject.getId());
	    							} else {
	    								canEnrollmentDetails.setSubject(oleSubject);
	    							}
	    							getCandidateEnrollmentService().updateCandidateEnrollmentDtls(canEnrollmentDetails);
	    							break;
	    					}
    				   }
    				}
    			}
    			
	 		} catch(Exception e){
	 			DGMTUtil.getStackTrace(e);
	 		}
	 		
	 	}
	 	
		public void updateEnrollmentDetails(String subjTimeSlotId,String subjectCodeId,boolean flag, GeneratedQuePaper qp) 
		{
 			CandidateEnrollememt candidateEnrollememt=null;
 			List<CandidateEnrollmentDetails> candidateEnrollmentDetails;
			CandidateEnrollmentDetails canEnrollmentDetails;
			
 		try{
 			String name = request.getUserPrincipal().toString();  	 
			candidate  = getCandidateService().getCandidateId(name);
 			OleExam exam = (OleExam)getSession().getAttribute("exam");
            List<ExamWindow> examWindows = getOleExamService().getActiveExamWindows(getOleExamService().getExamByName(exam.getName()));
        	Date todayDate = new Date();
            Calendar todayCal = Calendar.getInstance();
			todayCal.setTime(todayDate);
			todayCal.set(Calendar.HOUR_OF_DAY, 0);
			todayCal.set(Calendar.MINUTE, 0);
			todayCal.set(Calendar.SECOND, 0);
			todayCal.set(Calendar.MILLISECOND, 0);
			DateFormat formatter1  = new SimpleDateFormat("yyyy-MM-dd"); 
			if(examWindows != null && examWindows.size() == 1)
			{
				TimeSlot slot = new TimeSlot();
				slot.setId(Long.parseLong(subjTimeSlotId));
				candidateEnrollememt = qp.getCandidateEnrollememt();
				candidateEnrollmentDetails = candidateEnrollememt.getCandidateEnrollmentDetails();
				
		        List<CandidateEnrollememt> en = new ArrayList<CandidateEnrollememt>();
		        en.add(candidateEnrollememt);
				for (int i = 0; i < candidateEnrollmentDetails.size(); i++){
					canEnrollmentDetails = candidateEnrollmentDetails.get(i);
    				Date  date = (Date)formatter1.parse(canEnrollmentDetails.getEnrollmetExamDate().toString());
    				Calendar enrollDate = Calendar.getInstance();
			        enrollDate.setTime(date); 
			        enrollDate.set(Calendar.HOUR_OF_DAY, 0);
			        enrollDate.set(Calendar.MINUTE, 0);
			        enrollDate.set(Calendar.SECOND, 0);
			        enrollDate.set(Calendar.MILLISECOND, 0);
					if (todayCal.getTime().compareTo(enrollDate.getTime())== 0 ){  
    					if (canEnrollmentDetails.getTimeSlots().getId().intValue()==slot.getId().intValue()){
    							OleSubject oleSubject = getOleExamService().getSubject(Long.parseLong(subjectCodeId));
    							if (flag) {
    								canEnrollmentDetails.setSlotUserForSubjectId(oleSubject.getId());
    							} else {
    								canEnrollmentDetails.setSubject(oleSubject);
    							}
    							//getCandidateEnrollmentService().updateCandidateEnrollmentDtls(canEnrollmentDetails);
    							//getCandidateEnrollmentService().updateCandidateEnrollments(en);
    							//qp.setCandidateEnrollememt(candidateEnrollememt);  
    							break;
    					}
				   }
				}
			}
			
 		} catch(Exception e){
 			DGMTUtil.getStackTrace(e);
 		}
 		
 	}

	 	@SuppressWarnings("unchecked")
		public String showUnAnsQuestions()
	    {
	 		Map <Integer,Object> unAnsweredQuestions = (Map<Integer,Object>)getSession().getAttribute("unAnsweredQuestions");
	 		String keys = "";
	        try
	        {
	        	QuestionDetails details = new QuestionDetails();
	        	if (unAnsweredQuestions!=null && unAnsweredQuestions.size() >0) {   
		        	Iterator iterator = unAnsweredQuestions.keySet().iterator();
		        	while( iterator. hasNext() ){
		        		keys += ("," + iterator.next());
		        	}
		        	if (StringUtils.isNotBlank(keys)){ 
			        	keys = keys.substring(1);   
			        	details.setKeys(keys); 
						response.getWriter().write(JSONUtil.serialize(details));  
		        	} else {
		        		response.getWriter().write("None");
		        	}
	        	} else {    
	        		response.getWriter().write("None");  
	        	}
	        }
	        catch(Exception e)
	        {
	        	DGMTUtil.getStackTrace(e);
	        }
	        return null;
	    }
	 	
	 	public String showQuestionPaper() 
	 	{ 
	 		
	 		request.setAttribute("questionPaperId",request.getParameter("questionPaperId"));
	 		request.setAttribute("subjTimeSlotId", request.getParameter("subjTimeSlotId"));
	 		request.setAttribute("subjectCodeId", request.getParameter("subjectCodeId"));
	 		request.setAttribute("questionNo", request.getParameter("questionNo"));
	 		request.setAttribute("elapsed", request.getParameter("elapsed"));
	 		request.setAttribute("message", request.getParameter("message"));
	 		
			if (request.getParameter("testVal") != null)
			{
				request.setAttribute("testVal", request.getParameter("testVal"));
			}
			else
			{
				return "sessionExpired";
			}

	 		return SUCCESS;
	 	}


 	/**
 	 * Making a copy of image 
 	 * @param fromFileName
 	 * @param toFileName
 	 */
	 public void makeCopyOfFile(String fromFileName, String toFileName)
	 {
		 FileInputStream fis =null;
		 FileOutputStream fos =null;
		 try {
			 String realPath = servletContext.getRealPath("/");
			 
			 if (realPath==null) //WebLogic!
		      {
		         try 
		         {
		            URL resourcePath = servletContext.getResource("/");
		            if ((resourcePath != null) && (resourcePath.getProtocol().equals("file"))) 
		            {
		               realPath = resourcePath.getPath();
		            }
		            else
		            {
		               logger.warn("Unable to determine real path from servlet context for path does not exist.");
		            }
		         }
		         catch (MalformedURLException e) 
		         {
		            logger.warn("Unable to determine real path from servlet context for : " + "/");
		            logger.debug("Caused by MalformedURLException", e);
		         }

		      }
			    String imageDir = realPath+"/DGMTImages/";
			    File inputFile = new File(imageDir, fromFileName);
				
				String canonicalPath = inputFile.getCanonicalPath();
				
				if(!canonicalPath.equals(imageDir+fromFileName)) {
					  
						request.getSession().setAttribute("statusval", "invalidData"); 

				} else {
					
					File outputFile = new File(imageDir, toFileName);
					fis = new FileInputStream(inputFile);
					fos = new FileOutputStream(outputFile);
					int c;
		
					while ((c = fis.read()) != -1)
					{
						fos.write(c);
					}
		
					fis.close();
					fos.close();
				}
	        } catch (FileNotFoundException e) {
	            logger.error("Exception: " + DGMTUtil.getStackTrace(e));
	        } catch (IOException e) {
	        	logger.error("Exception: " + DGMTUtil.getStackTrace(e));
	        }
	 }

	@SuppressWarnings("unchecked")
	public String autoSaveSubjectiveQuestion() throws ParseException
	{
		List<GeneratedQuePaperDtls> questionPaperDtls = (List<GeneratedQuePaperDtls>)getSession().getAttribute("QuestionPaperDtls"); 
		GeneratedQuePaperDtls  quePaperDtls= null;
		String questionNo = request.getParameter("questionNo"); 
		answer = request.getParameter("answer"); 
		if (StringUtils.isNotBlank(questionNo))
		{
			try 
			{
				quePaperDtls = questionPaperDtls.get(Integer.parseInt(questionNo));
	 			quePaperDtls.setAnswer(answer);
				quePaperDtls.setMarks(null);
				getOleExamService().updateQuestionDtls(quePaperDtls);
				//to save timer time for every 10 seconds
				autoSaveToLastUpdateTime();
				response.getWriter().write("success");    
			} 
			catch (IOException e) 
			{
				logger.error(DGMTUtil.getStackTrace(e));
			} 
		}
	    return null;
	}
	
	public String autoSaveToLastUpdateTime() throws ParseException
	{
		GeneratedQuePaper generatedQuePaper = null;
		generatedQuePaper = (GeneratedQuePaper)getSession().getAttribute("generatedQuePaper");
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		if (generatedQuePaper!=null)
		{
			try 
			{
				generatedQuePaper.setLastUpdatedQuestionTime(new java.util.Date());
				
				String timeLeftInSecs = request.getParameter("timeLeftInSecs");
				
				if(timeLeftInSecs!=null && !"".equalsIgnoreCase(timeLeftInSecs))
				{
					generatedQuePaper.setTimeLeftInSecs(Long.parseLong(timeLeftInSecs));
				}
				//getOleExamService().updateGeneratedQuestionPaper(generatedQuePaper);
				getOleExamService().updateTimeLeftInSecsForGQPaper(generatedQuePaper);
				response.getWriter().write("success"); 
			} 
			catch (IOException e) 
			{
				logger.error(DGMTUtil.getStackTrace(e));
			} 
		}
	    return null;
	}
	 
	 public String viewImageMarks()
	 {
		 return SUCCESS;
	 }
}
