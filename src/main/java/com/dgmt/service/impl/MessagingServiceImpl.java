/**
 * Created Date: 11/16/10 2:32 PM
 * Class Name  : MessagingServiceImpl.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>        <Author>        <Date>        <Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package com.dgmt.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.Serializable;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.dgmt.dao.CandidateEnrollmentDAO;
import com.dgmt.dao.OleExamDAO;
import com.dgmt.dao.UserManagementDAO;
import com.dgmt.dto.ExamStatusDTO;
import com.dgmt.dto.ExamStatusDetailsDTO;
import com.dgmt.dto.MachineStatusDTO;
import com.dgmt.dto.MachineStatusDetailsDTO;
import com.dgmt.dto.StatusMessageDTO;
import com.dgmt.dto.TestCenterMessageDTO;
import com.dgmt.model.AnsweredPapersTransferStatus;
import com.dgmt.model.Candidate;
import com.dgmt.model.CandidateEnrollememt;
import com.dgmt.model.CandidateSubjects;
import com.dgmt.model.ExamWindow;
import com.dgmt.model.GeneratedQuePaper;
import com.dgmt.model.ProctorKey;
import com.dgmt.model.Resources;
import com.dgmt.model.Status;
import com.dgmt.model.TestCenter;
import com.dgmt.model.TimeSlot;
import com.dgmt.model.UserLogin;
import com.dgmt.model.UserRole;
import com.dgmt.service.api.MessagingService;
import com.dgmt.util.DGMTProperties;
import com.dgmt.util.DGMTUtil;
import com.dgmt.util.EditedImagefilter;
import com.dgmt.util.Log;
import com.dgmt.util.LogFactory;

import freemarker.log.Logger;

/**
 * Description:
 * 
 * @version 1.0 11/16/10 2:32 PM
 * @author CTE
 * @since DGMT 1.0
 */
public class MessagingServiceImpl implements MessagingService
{
	private Log logger = LogFactory.getLog(MessagingServiceImpl.class);

	private CandidateEnrollmentDAO enrollmentDAO;

	private OleExamDAO oleExamDAO;

	private UserManagementDAO userMgtDAO;

	private JmsTemplate jmsTemplate;

	/**
	 * @param enrollmentDAO
	 */
	public void setEnrollmentDAO(CandidateEnrollmentDAO enrollmentDAO)
	{
		this.enrollmentDAO = enrollmentDAO;
	}

	/**
	 * @param oleExamDAO
	 */
	public void setOleExamDAO(OleExamDAO oleExamDAO)
	{
		this.oleExamDAO = oleExamDAO;
	}

	/**
	 * @param userMgtDAO
	 */
	public void setUserMgtDAO(UserManagementDAO userMgtDAO)
	{
		this.userMgtDAO = userMgtDAO;
	}

	/**
	 * @param jmsTemplate
	 */
	public void setJmsTemplate(JmsTemplate jmsTemplate)
	{
		this.jmsTemplate = jmsTemplate;
	}

	/**
	 * Saves examination data received from central server.
	 * 
	 * @param tcMessageDTO
	 *            examination data
	 */
	@Override
	public void saveExamData(TestCenterMessageDTO tcMessageDTO)
	{
		logger.info("saveExamData starts");

		List<CandidateEnrollememt> enrollments = tcMessageDTO.getEnrollments();
		List<UserRole> userRoles = tcMessageDTO.getUserRoles();
		List<GeneratedQuePaper> papers = tcMessageDTO.getQuestionPapers();
		List<ProctorKey> proctorKeys = tcMessageDTO.getProctorKeys();
		List<CandidateSubjects> candSubjects = tcMessageDTO
				.getCandidateSubjects();

		if ((enrollments != null) && (enrollments.size() > 0))
		{
			enrollmentDAO.saveEnrollments(enrollments);
			logger.debug("Saved enrollments..");
		}

		if ((candSubjects != null) && (candSubjects.size() > 0))
		{
			enrollmentDAO.saveCandidateSubjects(candSubjects);
			logger.debug("Saved candidate subjects..");
		}

		if ((userRoles != null) && (userRoles.size() > 0))
		{
			userMgtDAO.saveUserRoles(userRoles);
			logger.debug("Saved user roles..");
		}

		if ((papers != null) && (papers.size() > 0))
		{
			oleExamDAO.saveQuestionPapers(papers);
			logger.debug("Saved question papers..");
		}

		if ((proctorKeys != null) && (proctorKeys.size() > 0))
		{
			/*
			 * ProctorKey pk = proctorKeys.get(0); // if regenerated proctor
			 * keys then delete old once if (pk.getStatus().equals("1")) {
			 * oleExamDAO.deleteProcterKeys(pk.getEw());
			 * logger.debug("Deleted old proctor keys.."); }
			 */
			oleExamDAO.saveProctorKeys(proctorKeys);
			logger.debug("Saved Proctor Keys..");
		}

		logger.info("saveExamData ends");
	}

	/**
	 * Sends message receipt notification.
	 * 
	 * @param correlationID
	 *            received message id
	 */
	@Override
	public void sendStatusMessage(final String correlationID)
	{
		logger.info("sendStatusMessage starts");

		StatusMessageDTO statusMsg = new StatusMessageDTO();

		statusMsg.setMessageID(correlationID);
		statusMsg.setReceivedTime(new Date());

		String topic = DGMTProperties.getProperties().getProperty(
				"jms.statusTopic");

		logger.debug("publishing status message to status topic..");
		jmsTemplate.send(topic, getMessage(statusMsg));

		logger.info("sendStatusMessage ends");
	}

	/**
	 * Creates object message for given object.
	 * 
	 * @param object
	 *            serializable object
	 * @return MessageCreator
	 */
	private MessageCreator getMessage(final Serializable object)
	{
		return new MessageCreator()
		{
			public Message createMessage(Session session) throws JMSException
			{
				Message message = session.createObjectMessage(object);

				return message;
			}
		};
	}

	/**
	 * Creates object message for given object.
	 * 
	 * @param object
	 *            serializable object
	 * @param msgId
	 *            message id
	 * @return MessageCreator
	 */
	private MessageCreator getMessage(final Serializable object,
			final String messageId)
	{
		return new MessageCreator()
		{
			public Message createMessage(Session session) throws JMSException
			{
				Message message = session.createObjectMessage(object);

				message.setJMSCorrelationID(messageId);

				return message;
			}
		};
	}

	/**
	 * Transfers answered question papers along with edited images to central
	 * server.
	 */
	@Override
	public void transferAnsweredPapers()
	{
		logger.info("transferAnsweredPapers starts");

		List<ExamWindow> examWindows = oleExamDAO.getAllActiveExamWindows();

		String answeredPaperIds = "";
		String topic = DGMTProperties.getProperties().getProperty(
				"jms.answerTopic");
		String messageId = "";
		List<GeneratedQuePaper> questionPapers = null;
		List<UserLogin> userLogin = null;
		String imageLoc = DGMTProperties.getProperties().getProperty(
				"TC_IMAGE_LOCATION");
		String testCenterID = DGMTProperties.getProperties().getProperty(
				"testcenter.id");

		AnsweredPapersTransferStatus answeredPapersTransferStatus = new AnsweredPapersTransferStatus();

		if ((examWindows != null) && (examWindows.size() == 1))
		{
			for (ExamWindow ew : examWindows)
			{
				if (isCurrentTimeBetweenSlots(ew))
				{
					TestCenterMessageDTO messageDTO = new TestCenterMessageDTO();

					questionPapers = oleExamDAO.getAnsweredQuestionPapers(ew);
					userLogin = userMgtDAO.getUpdatedPasswordUsers();
					messageDTO.setQuestionPapers(questionPapers);
					messageDTO.setTestCenterId(testCenterID);
					messageDTO.setUserLogin(userLogin);

					for (int q = 0; q < questionPapers.size(); q++)
					{
						answeredPaperIds += ("," + questionPapers.get(q)
								.getId());
					}

					if (StringUtils.isNotBlank(answeredPaperIds))
					{
						answeredPaperIds = answeredPaperIds.substring(1);
					}

					messageId = ""
							+ ew.getId().toString()
							+ "_"
							+ new SimpleDateFormat("ddMMyyhhmmsss")
									.format(new Date());

					if ((questionPapers != null) && (questionPapers.size() > 0))
					{
						// create QuestionPapers message
						MessageCreator mc = getMessage(messageDTO, messageId);

						try
						{
							// image transfer
							String[] paperIds = answeredPaperIds.split(",");

							File imageDir = new File(imageLoc);

							for (int i = 0; i < paperIds.length; i++)
							{
								FilenameFilter filter = new EditedImagefilter(
										paperIds[i]);
								String[] fileNames = imageDir.list(filter);

								for (int j = 0; j < fileNames.length; j++)
								{
									FileInputStream is = new FileInputStream(
											new File(imageLoc + fileNames[j]));
									byte[] data = IOUtils.toByteArray(is);

									// send message
									jmsTemplate.send(topic, getByteMessage(
											data, new SimpleDateFormat(
													"ddMMyyyyhhmmsss")
													.format(new Date()),
											fileNames[j]));
								}
							}


							// send message
							jmsTemplate.send(topic, mc);
							
							logger.debug(" published answered papers..");

							// update transfered flag in question papers
							for (GeneratedQuePaper qp : questionPapers)
							{
								qp.setTransfered("1");
							}
							
							for (UserLogin ul : userLogin)
							{
								ul.setStatus(Long.parseLong("1"));
							}

							oleExamDAO.updateTrasferredFlag(questionPapers);

							// update the status table.
							answeredPapersTransferStatus
									.setTransferedPaperCount(questionPapers
											.size());
							answeredPapersTransferStatus
									.setMessageId(messageId);
							answeredPapersTransferStatus
									.setTransferTime(new Date());
							answeredPapersTransferStatus
									.setAnsweredPaperIds(answeredPaperIds);
							answeredPapersTransferStatus.setStatus("1"); // success

							oleExamDAO
									.saveAnsweredPapersTransferStatus(answeredPapersTransferStatus);

							// transfer examination status once the papers are
							// transffered
							// transferExamStatus(true);
							logger.debug(" Saved status ..");
						}
						catch (Exception e)
						{
							answeredPapersTransferStatus
									.setTransferedPaperCount(questionPapers
											.size());
							answeredPapersTransferStatus
									.setMessageId(messageId);
							answeredPapersTransferStatus
									.setTransferTime(new Date());
							answeredPapersTransferStatus
									.setAnsweredPaperIds(answeredPaperIds);
							answeredPapersTransferStatus.setStatus("0"); // failed
							
							oleExamDAO
									.saveAnsweredPapersTransferStatus(answeredPapersTransferStatus);
							logger.error(" Exception while publishing.."
									+ DGMTUtil.getStackTrace(e));
						}
					}
				}
			}
		}

		logger.info("transferAnsweredPapers ends");
	}

	/**
	 * Updates answer paper receipt status.
	 * 
	 * @param messageDTO
	 */
	@Override
	public void updateMessageReceipt(StatusMessageDTO messageDTO)
	{
		logger.info("updateMessageReceipt starts");

		AnsweredPapersTransferStatus status = oleExamDAO
				.getPaperTransferStatus(messageDTO.getMessageID());

		logger.debug("Message id : " + messageDTO.getMessageID());

		if (status != null)
		{
			logger.debug("Status row found");

			status.setReceiptTime(messageDTO.getReceivedTime());
			oleExamDAO.saveAnsweredPapersTransferStatus(status);

			logger.debug("Updated received status and receipt time.");

			oleExamDAO.removeAnsweredQuestionPapers(status
					.getAnsweredPaperIds());

			logger.debug("Deleted transferred papers");

			// get the paper id and remove edited images from the paper
			String[] paperIds = status.getAnsweredPaperIds().split(",");

			String imageLoc = DGMTProperties.getProperties().getProperty(
					"TC_IMAGE_LOCATION");
			File imageDir = new File(imageLoc);

			for (int i = 0; i < paperIds.length; i++)
			{
				FilenameFilter filter = new EditedImagefilter(paperIds[i]);
				String[] fileNames = imageDir.list(filter);

				for (int j = 0; j < fileNames.length; j++)
				{
					File f = new File(imageLoc + fileNames[j]);

					if (f.delete())
					{
						logger.debug("Deleted image :" + imageLoc
								+ fileNames[j] + " Successfully.");
					}
					else
					{
						logger.debug("Failed to delete image :" + imageLoc
								+ fileNames[j]);
					}
				}
			}
		}

		transferExamStatus(true);

		logger.info("updateMessageReceipt ends");
	}

	/**
	 * Creates bytes message for the given byte data.
	 * 
	 * @param data
	 *            bytes data
	 * @param msgId
	 *            message id
	 * @param imageName
	 *            image name
	 * @return MessageCreator
	 */
	private MessageCreator getByteMessage(final byte[] data,
			final String msgId, final String imageName)
	{
		return new MessageCreator()
		{
			public Message createMessage(Session session) throws JMSException
			{
				BytesMessage message = session.createBytesMessage();

				message.setJMSCorrelationID(msgId);
				message.writeBytes(data);
				message.setIntProperty("size", data.length);
				message.setStringProperty("name", imageName);

				return message;
			}
		};
	}

	/**
        *
        */
	@Override
	public void transferExamStatus()
	{
		transferExamStatus(false);
	}

	/**
         *
         */
	private void transferExamStatus(boolean flag)
	{
		logger.info("transferAnsweredPapers starts");

		List<ExamWindow> examWindows = oleExamDAO.getAllActiveExamWindows();

		String topic = DGMTProperties.getProperties().getProperty(
				"jms.examStatusTopic");
		String testCenterID = DGMTProperties.getProperties().getProperty(
				"testcenter.id");

		ExamStatusDTO statusDTO;
		TestCenter tc = oleExamDAO.getTestCenter(Long.parseLong(testCenterID));
		List<GeneratedQuePaper> qps;
		List<ExamStatusDetailsDTO> detailsDTO;
		ExamStatusDetailsDTO dtl;
		Candidate candidate;
		Status st;
		String messageId;
		MessageCreator mc;

		for (ExamWindow ew : examWindows)
		{
			logger.debug("ExamWindow: " + ew.getId());

			if (isExamInProgress(ew) || flag)
			{
				statusDTO = new ExamStatusDTO();
				statusDTO.setExamWindow(ew);
				statusDTO.setTestCenter(tc);

				qps = oleExamDAO.getExamStatus(ew);

				if (((qps != null) && (qps.size() > 0)) || flag)
				{
					logger.debug("Question Papers: " + qps.size());

					detailsDTO = new ArrayList<ExamStatusDetailsDTO>();

					int ongoing = 0;
					int submitted = 0;

					if ((qps != null) && (qps.size() > 0))
					{
						for (GeneratedQuePaper qp : qps)
						{
							dtl = new ExamStatusDetailsDTO();
							candidate = qp.getCandidate();
							dtl.setCandidateName(candidate.getName());
							dtl.setStartTime(qp.getStartTime());
							dtl.setEndTime(qp.getEndTime());
							dtl.setPersonalNo(candidate.getPersonalNo());
							dtl.setSubjectName(qp.getExamPaper().getSubject()
									.getName());
							dtl.setExamPaperTitle(qp.getExamPaper().getTitle());
							st = qp.getStatus();
							dtl.setStatus(st.getStatus());

							if (st.getId() == 5)
							{
								ongoing += 1;
							}
							else
							{
								submitted += 1;
							}

							detailsDTO.add(dtl);
						}
					}

					logger.debug("OngoingCnt: " + ongoing);
					logger.debug("SubmittedCnt: " + submitted);

					statusDTO.setOngoingCnt(ongoing);
					statusDTO.setSubmittedCnt(submitted);
					statusDTO.setDetails(detailsDTO);
					statusDTO.setUpdatedTime(new Date());

					messageId = new SimpleDateFormat("ddMMyyhhmmsss")
							.format(new Date());
					mc = getMessage(statusDTO, messageId);

					// send message
					jmsTemplate.send(topic, mc);
				}
			}
		}

		logger.info("transferAnsweredPapers ends");
	}

	/**
	 * @param ew
	 * @return
	 */
	private boolean isExamInProgress(ExamWindow ew)
	{
		logger.info("isExamInProgress starts");

		Date todayDate = new Date();
		Calendar todayCal = Calendar.getInstance();

		todayCal.setTime(todayDate);
		todayCal.set(Calendar.HOUR_OF_DAY, 0);
		todayCal.set(Calendar.MINUTE, 0);
		todayCal.set(Calendar.SECOND, 0);
		todayCal.set(Calendar.MILLISECOND, 0);

		boolean tsOverlaps = false;

		// if current date falls between exam window
		if ((todayCal.getTime().compareTo(ew.getStartDate()) >= 0)
				&& (todayCal.getTime().compareTo(ew.getEndDate()) <= 0))
		{
			logger
					.debug("current date falls between exam window start date and end date");

			Calendar cal = Calendar.getInstance();
			Calendar startTime = Calendar.getInstance();
			Calendar endTime = Calendar.getInstance();
			Date today = new Date();

			cal.setTime(today);
			startTime.setTime(today);
			endTime.setTime(today);
			cal.set(Calendar.MILLISECOND, 0);

			TimeSlot t = null;
			Set<TimeSlot> ts = ew.getTimeSlots();
			Iterator<TimeSlot> it = ts.iterator();

			while (it.hasNext())
			{
				t = it.next();

				String[] st = t.getStartTime().split(":");
				String[] et = t.getEndTime().split(":");

				startTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(st[0]));
				startTime.set(Calendar.MINUTE, Integer.parseInt(st[1]));
				startTime.set(Calendar.SECOND, 0);
				startTime.set(Calendar.MILLISECOND, 0);

				endTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(et[0]));
				endTime.set(Calendar.MINUTE, Integer.parseInt(et[1]));
				endTime.set(Calendar.SECOND, 0);
				endTime.set(Calendar.MILLISECOND, 0);
				// TODO: read extra time from properties
				// this is to send exam status if exam is finished automatically
				// at the end time
				endTime.add(Calendar.MINUTE, Integer.parseInt("10"));

				if ((cal.compareTo(startTime) >= 0)
						&& (cal.compareTo(endTime) <= 0))
				{
					logger
							.debug("current time falls between exam window time slots");
					tsOverlaps = true;

					break;
				}
			}
		}

		logger.debug("Exam is in progress: " + tsOverlaps);

		logger.info("isExamInProgress ends");

		return tsOverlaps;
	}

	/**
	 * @param ew
	 * @return
	 */
	private boolean isCurrentTimeBetweenSlots(ExamWindow ew)
	{
		logger.info("isCurrentTimeBetweenSlots starts");

		Date todayDate = new Date();
		Calendar todayCal = Calendar.getInstance();

		todayCal.setTime(todayDate);
		todayCal.set(Calendar.HOUR_OF_DAY, 0);
		todayCal.set(Calendar.MINUTE, 0);
		todayCal.set(Calendar.SECOND, 0);
		todayCal.set(Calendar.MILLISECOND, 0);

		boolean dateOverlaps = false;
		boolean tsOverlaps = false;

		int bufferTime = Integer.parseInt(DGMTProperties.getProperties()
				.getProperty("MANUAL_SUBMIT_START_TIME_AFTER_EXAM", "30"));

		// if current date falls between exam window
		if ((todayCal.getTime().compareTo(ew.getStartDate()) >= 0)
				&& (todayCal.getTime().compareTo(ew.getEndDate()) <= 0))
		{
			dateOverlaps = true;

			logger
					.debug("current date falls between exam window start date and end date");

			Calendar cal = Calendar.getInstance();
			Calendar startTime = Calendar.getInstance();
			Calendar endTime = Calendar.getInstance();
			Date today = new Date();

			cal.setTime(today);
			startTime.setTime(today);
			endTime.setTime(today);
			cal.set(Calendar.MILLISECOND, 0);

			TimeSlot t = null;
			Set<TimeSlot> ts = ew.getTimeSlots();
			Iterator<TimeSlot> it = ts.iterator();

			while (it.hasNext())
			{
				t = it.next();

				String[] st = t.getStartTime().split(":");
				String[] et = t.getEndTime().split(":");

				startTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(st[0]));
				startTime.set(Calendar.MINUTE, Integer.parseInt(st[1]));
				startTime.set(Calendar.SECOND, 0);
				startTime.set(Calendar.MILLISECOND, 0);

				endTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(et[0]));
				endTime.set(Calendar.MINUTE, Integer.parseInt(et[1]));
				endTime.set(Calendar.SECOND, 0);
				endTime.set(Calendar.MILLISECOND, 0);

				endTime.add(Calendar.MINUTE, bufferTime);

				if ((cal.compareTo(startTime) >= 0)
						&& (cal.compareTo(endTime) <= 0))
				{
					logger
							.debug("current time falls between exam window time slots");
					tsOverlaps = true;

					break;
				}
			}
		}

		logger.debug("isCurrentTimeBetweenSlots : " + tsOverlaps);

		logger.info("isCurrentTimeBetweenSlots ends");

		return dateOverlaps && !tsOverlaps;
	}

	/**
         *
         */
	@Override
	public void transferMachineStatus()
	{
		List<ExamWindow> examWindows = oleExamDAO.getAllActiveExamWindows();

		for (ExamWindow ew : examWindows)
		{
			logger.debug("ExamWindow: " + ew.getId());

			if (isExamInProgress(ew))
			{
				String topic = DGMTProperties.getProperties().getProperty(
						"jms.machineStatusTopic");

				String testCenterID = DGMTProperties.getProperties()
						.getProperty("testcenter.id");

				TestCenter tc = oleExamDAO.getTestCenter(Long
						.parseLong(testCenterID));

				String fromIP = tc.getFromIpAddress();
				String toIP = tc.getToIpAddress();

				String first3PrtsIPAddres = fromIP.substring(0, fromIP
						.lastIndexOf("."));

				int lastStartHex = Integer.parseInt(fromIP.split("\\.")[3]);
				int lastEndHex = Integer.parseInt(toIP.split("\\.")[3]);

				MachineStatusDTO machineStatusDTO = new MachineStatusDTO();
				MachineStatusDetailsDTO machineStatusDetailsDTO;
				List<MachineStatusDetailsDTO> statusList = new ArrayList<MachineStatusDetailsDTO>();
				String ipAddress = "";
				int upCnt = 0;
				int downCnt = 0;
				String messageId;
				MessageCreator mc;

				for (int i = lastStartHex; i <= lastEndHex; i++)
				{
					ipAddress = first3PrtsIPAddres + "." + i;

					machineStatusDetailsDTO = new MachineStatusDetailsDTO();
					machineStatusDetailsDTO.setIpAddress(ipAddress);

					if (DGMTUtil.isIpAddressPinging(ipAddress))
					{
						machineStatusDetailsDTO.setStatus("1");
						upCnt++;
					}
					else
					{
						machineStatusDetailsDTO.setStatus("0");
						downCnt++;
					}

					statusList.add(machineStatusDetailsDTO);
				}

				machineStatusDTO.setDetails(statusList);
				machineStatusDTO.setTestCenter(tc);
				machineStatusDTO.setUpCnt(upCnt);
				machineStatusDTO.setDownCnt(downCnt);
				machineStatusDTO.setUpdatedTime(new Date());

				messageId = new SimpleDateFormat("ddMMyyhhmmsss")
						.format(new Date());
				mc = getMessage(machineStatusDTO, messageId);

				// send message
				jmsTemplate.send(topic, mc);
			}
		}
	}

	/**
	 * @param examId
	 */
	@Override
	public void deleteExamData(String examId)
	{
		// delete examination data
		oleExamDAO.deleteExamData(examId);

		// delete images from file system
		List<ExamWindow> examWindows = oleExamDAO.getAllExamWindows();

		if ((examWindows == null) || (examWindows.size() == 0))
		{
			List<Resources> res = oleExamDAO.getResources();

			if ((res != null) && (res.size() >= 0))
			{
				String imageLoc = DGMTProperties.getProperties().getProperty(
						"TC_IMAGE_LOCATION");

				File file = null;

				for (Resources r : res)
				{
					file = new File(imageLoc + r.getFile());

					if (file.exists())
					{
						file.delete();
					}
				}

				oleExamDAO.deleteResources();
			}
		}
	}

	/**
	 * @param examId
	 * @return
	 */
	@Override
	public ExamWindow getActiveExamWindow(String examId)
	{
		List<ExamWindow> examWindows = oleExamDAO.getActiveExamWindows(examId);

		if ((examWindows != null) && (examWindows.size() == 1))
		{
			return examWindows.get(0);
		}

		return null;
	}

	/**
	 * @param ewId
	 */
	@Override
	public void sendDeleteNotification(final String ewId)
	{
		String topic = DGMTProperties.getProperties().getProperty(
				"jms.statusTopic");

		final String testCenterID = DGMTProperties.getProperties().getProperty(
				"testcenter.id");

		MessageCreator mc = new MessageCreator()
		{
			@Override
			public Message createMessage(Session session) throws JMSException
			{
				TextMessage tm = session.createTextMessage("SUCCESS");

				tm.setStringProperty("tcId", testCenterID);
				tm.setStringProperty("ewId", ewId);

				return tm;
			}
		};

		logger.debug("publishing delete successful message to status topic..");
		jmsTemplate.send(topic, mc);
	}

	/**
      *
    */
	@Override
	public void deleteTCData()
	{
		logger.debug("deleteTCData method start");

		boolean paperCount = false;
		List<ExamWindow> examWindows = oleExamDAO.getAllActiveExamWindows();
		List<GeneratedQuePaper> ansPaperCount = oleExamDAO
				.getAnsweredPaperCount();

		logger.debug("ansPaperCount" + ansPaperCount.size());

		if (ansPaperCount.size() == 0)
		{
			paperCount = true;
		}

		for (ExamWindow ew : examWindows)
		{
			logger.debug("ExamWindow: " + ew.getId());

			if (isExamOver(ew) && paperCount)
			{
				try
				{
					deleteExamData(ew.getExam().getId().toString());
					sendDeleteNotification(ew.getId().toString());
				}
				catch (Exception e)
				{
					logger
							.error(" Exception while deleting the test center data"
									+ DGMTUtil.getStackTrace(e));
				}
			}
		}

		logger.debug("deleteTCData method end");
	}

	/**
	 * @param ew
	 * @return
	 */
	private boolean isExamOver(ExamWindow ew)
	{
		logger.info("isExamOver starts");

		Date todayDate = new Date();
		Calendar todayCal = Calendar.getInstance();

		todayCal.setTime(todayDate);
		todayCal.set(Calendar.HOUR_OF_DAY, 0);
		todayCal.set(Calendar.MINUTE, 0);
		todayCal.set(Calendar.SECOND, 0);
		todayCal.set(Calendar.MILLISECOND, 0);

		boolean tsOverlaps = false;

		// if current date falls between exam window
		if (todayCal.getTime().compareTo(ew.getEndDate()) >= 0)
		{
			logger.debug("current date is more than exam window end date");

			Calendar cal = Calendar.getInstance();
			Calendar startTime = Calendar.getInstance();
			Calendar endTime = Calendar.getInstance();
			Date today = new Date();

			cal.setTime(today);
			startTime.setTime(today);
			endTime.setTime(today);
			cal.set(Calendar.MILLISECOND, 0);

			TimeSlot t = null;
			Set<TimeSlot> ts = ew.getTimeSlots();
			Iterator<TimeSlot> it = ts.iterator();

			while (it.hasNext())
			{
				t = it.next();

				String[] st = t.getStartTime().split(":");
				String[] et = t.getEndTime().split(":");

				startTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(st[0]));
				startTime.set(Calendar.MINUTE, Integer.parseInt(st[1]));
				startTime.set(Calendar.SECOND, 0);
				startTime.set(Calendar.MILLISECOND, 0);

				endTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(et[0]));
				endTime.set(Calendar.MINUTE, Integer.parseInt(et[1]));
				endTime.set(Calendar.SECOND, 0);
				endTime.set(Calendar.MILLISECOND, 0);
				// TODO: read extra time from properties
				// this is to send exam status if exam is finished automatically
				// at the end time
				endTime.add(Calendar.MINUTE, Integer.parseInt("10"));

				if (cal.compareTo(endTime) >= 0)
				{
					logger
							.debug("current time is more than exam end time + buffer time");
					tsOverlaps = true;

					break;
				}
			}
		}

		logger.debug("isExamOver: " + tsOverlaps);

		logger.info("isExamOver ends");

		return tsOverlaps;
	}
}
