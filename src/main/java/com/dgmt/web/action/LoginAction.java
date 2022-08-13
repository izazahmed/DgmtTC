/**
 * Created Date: 4/15/10 7:35 PM
 * Class Name  : LoginAction.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>        <Author>        <Date>        <Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package com.dgmt.web.action;

import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.dgmt.model.Candidate;
import com.dgmt.model.CandidateEnrollememt;
import com.dgmt.model.CandidateEnrollmentDetails;
import com.dgmt.model.OleExam;
import com.dgmt.model.SecurityQuestion;
import com.dgmt.model.UserLogin;
import com.dgmt.util.DGMTProperties;
import com.dgmt.util.DGMTUtil;
import com.dgmt.util.Log;
import com.dgmt.util.LogFactory;
import com.dgmt.util.SHA256Converter;
import com.dgmt.util.Validator;

import com.google.common.cache.Cache;
import com.googlecode.jsonplugin.JSONException;
import com.googlecode.jsonplugin.JSONUtil;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:35 PM
 * @author CTE
 * @since DGMT 1.0
 */
public class LoginAction extends BaseAction
{
	private Log logger = LogFactory.getLog(LoginAction.class);

	private UserLogin userLogin;

	private List<SecurityQuestion> securityQuestionList;

	private Candidate candidate;

	private String url;

	/**
	 * @return
	 */
	public String getUrl()
	{
		return url;
	}

	/**
	 * @param url
	 */
	public void setUrl(String url)
	{
		this.url = url;
	}

	/**
	 * @return
	 */
	public UserLogin getUserLogin()
	{
		return userLogin;
	}

	/**
	 * @param userLogin
	 */
	public void setUserLogin(UserLogin userLogin)
	{
		this.userLogin = userLogin;
	}

	/**
	 * @return
	 */
	public Candidate getCandidate()
	{
		return candidate;
	}

	/**
	 * @param candidate
	 */
	public void setCandidate(Candidate candidate)
	{
		this.candidate = candidate;
	}

	/**
	 * @return
	 */
	public List<SecurityQuestion> getSecurityQuestionList()
	{
		return securityQuestionList;
	}

	/**
	 * @param securityQuestionList
	 */
	public void setSecurityQuestionList(
			List<SecurityQuestion> securityQuestionList)
	{
		this.securityQuestionList = securityQuestionList;
	}

	/**
	 * @return
	 */
	public String logout()
	{
		// set login ip address to null
		logger.info("logout start");
		String val = "";
		try
		{
			logger.debug("logout in try block");
			
			Candidate candidate = getCandidateService().getCandidateId(
					request.getUserPrincipal().getName());

			logger.debug("logout candidate object" + candidate);
			candidate.setLoginIPAddress(null);
			getCandidateService().updateCandidate(candidate);
			
			if(request.getParameter("hidVal")!=null && !request.getParameter("hidVal").equals("")){
				val = request.getParameter("hidVal");
			}
			// Invalidate session object
			getSession().invalidate();
			logger.debug("logout end of try block");
		}
		catch (Exception exception)
		{
			logger.error(DGMTUtil.getStackTrace(exception));
		}

		logger.info("logout ends");
		
		if(val.equals("logoutAndClose")){
			return "autoClose";
		}else{
			return "logout";
		}
	}
	
	/**
	 * @return
	 */
	public String tokenCheck() {
		logger.info("tokenCheck start");
		String salt = (String) request.getParameter("csrfPreventionSalt");
		Cache<String, Boolean> csrfPreventionSaltCache = (Cache<String, Boolean>) request.getSession().getAttribute("csrfPreventionSaltCache");
		logger.info("salt-----" + salt);
		try {
			if (csrfPreventionSaltCache != null && csrfPreventionSaltCache.getIfPresent(salt)) {
				logger.info("status-----"+ csrfPreventionSaltCache.getIfPresent(salt));
				String loginId = request.getParameter("j_username");
				String pwd = request.getParameter("j_password");				
				userLogin = getUserManagementService().getUserLoginDetails(loginId);
				if(userLogin!=null){
					String encryptedPwd = "{SHA-256}"+ SHA256Converter.SHA256(pwd);
					if(encryptedPwd.equalsIgnoreCase(userLogin.getPassword())) {
						url = "j_security_check?j_username=" + loginId + "&j_password="+ pwd;
						return "tokenCheck";
					}else{
						getSession().setAttribute("failed", "true");					
						return LOGIN;
					} 					
				}else{
					getSession().setAttribute("failed", "true");					
					return LOGIN;
				}
			} else {
				return LOGIN;
			}
		} catch (Exception e) 
		{
			logger.error("Exception: " + DGMTUtil.getStackTrace(e));
			return LOGIN;
		}
	}

	/**
	 * @return
	 */
	public String login()
	{
		logger.info("login starts");
		
		if (request.isUserInRole("CANDIDATE"))
		{
			DGMTProperties properties = DGMTProperties.getProperties();
			String validateIPAddress = properties
					.getProperty("validateIPAddress");

			logger.info("login validateIPAddress" + validateIPAddress);

			List<CandidateEnrollmentDetails> list = null;

			// get eligible examination and put it into session
			// so that user can enroll only for the exam for which he is
			// eligible
			logger.info("login user is candidate");

			OleExam exam = null;
			List<CandidateEnrollememt> candidateEnrollememt = null;
			String loginId = request.getUserPrincipal().getName();

			Candidate candidate = getCandidateService().getCandidateId(loginId);
			
			logger.info("login user is candidate object"+candidate);

			String isMultipleLoginsAllowed = properties.getProperty(
					"MULTIPLE_LOGINS_ALLOWED", "false");

			logger.debug("isMultipleLoginsAllowed :" + isMultipleLoginsAllowed);

			if (isMultipleLoginsAllowed.equalsIgnoreCase("false"))
			{
				List<UserSessionInfo> userSessionList = null;
				Iterator<UserSessionInfo> it;

				userSessionList = (List<UserSessionInfo>) getServletContext()
						.getAttribute("userSessionList");

				if ((userSessionList != null) && (userSessionList.size() > 0))
				{
					it = userSessionList.iterator();

					UserSessionInfo uInfo = new UserSessionInfo();

					int counter = 0;

					while (it.hasNext())
					{
						uInfo = (UserSessionInfo) it.next();
						boolean isLoginIdSame = uInfo.getLoginid().equals(
								(String) loginId);
						
						boolean isSessionIdSame = getSession().getId().equals(
								uInfo.getSessionId());

						logger.debug("SessionId :" + getSession().getId());
						logger.debug("isSessionIdSame :" + isSessionIdSame);
						logger.debug("isLoginIdSame :" + isLoginIdSame);

						// Check different SessionId for same LoginId
						if ((isLoginIdSame) && (!isSessionIdSame))
						{
							if (counter == 0)
							{
								uInfo.getCurrentSession()
										.setMaxInactiveInterval(0);
								counter = 1;
							}
							it.remove();
						}
					}
				}
				else
				{
					userSessionList = new ArrayList<UserSessionInfo>();
				}

				HttpSession session = getSession();

				// Store the values of loginId, sessionId and session object in
				// UserSessionInfo each time.
				UserSessionInfo uInfo = new UserSessionInfo();

				uInfo.setLoginid(loginId);
				uInfo.setSessionId(session.getId());
				uInfo.setCurrentSession(session);
				userSessionList.add(uInfo);

				getServletContext().setAttribute("userSessionList",
						userSessionList);
			}

			try
			{
				userLogin = getUserManagementService().getUserLoginDetails(
						loginId);
			}
			catch (Exception e)
			{
				logger.error("Exception: " + DGMTUtil.getStackTrace(e));
			}

			if (candidate != null)
			{
				String examId = candidate.getEligibleExamId();

				logger.info("login examId" + examId);

				if (examId != null)
				{
					try
					{
						exam = getOleExamService().getExamById(
								Long.valueOf(examId));
					}
					catch (Exception e)
					{
						logger.error("Exception: " + DGMTUtil.getStackTrace(e));
					}
				}
			

				if (getSession().getAttribute("candidateEnrolled") != null)
				{
					getSession().removeAttribute("candidateEnrolled");
				}
	
				// chek the candidate is enrolled or not
				candidateEnrollememt = getCandidateEnrollmentService()
						.checkCandidateEnrollment(candidate.getId());
	
				logger.info(" RemoteAddr : " + request.getRemoteAddr());
				logger.info(" RemoteHost : " + request.getRemoteHost());
				logger.info(" RemoteUser : " + request.getRemoteUser());
	
				String toAddress = "";
				String fromAddress = "";
				String reqAddress = "";
	
				try
				{
					if (StringUtils.isNotBlank(validateIPAddress))
					{
						if (validateIPAddress.equalsIgnoreCase("true"))
						{
							logger.info("validate the login ip address  : "
									+ request.getRemoteAddr());
							fromAddress = candidateEnrollememt.get(0)
									.getTestCenter().getFromIpAddress();
							logger
									.info("validate the login ip address  fromAddress: "
											+ fromAddress);
							toAddress = candidateEnrollememt.get(0).getTestCenter()
									.getToIpAddress();
							logger
									.info("validate the login ip address  toAddress: "
											+ toAddress);
	
							String first3PrtsFromAddres = fromAddress.substring(0,
									fromAddress.lastIndexOf("."));
							String first3PrtsReqAddres = request.getRemoteAddr()
									.substring(
											0,
											request.getRemoteAddr()
													.lastIndexOf("."));
	
							if (StringUtils.isNotBlank(first3PrtsFromAddres)
									&& StringUtils.isNotBlank(first3PrtsReqAddres))
							{
								if ((fromAddress.split("\\.").length == 4)
										&& (toAddress.split("\\.").length == 4)
										&& (request.getRemoteAddr().split("\\.").length == 4))
								{
									if (fromAddress.split("\\.").length == 4)
									{
										fromAddress = fromAddress.split("\\.")[3];
									}
	
									if (toAddress.split("\\.").length == 4)
									{
										toAddress = toAddress.split("\\.")[3];
									}
	
									if (request.getRemoteAddr().split("\\.").length == 4)
									{
										reqAddress = request.getRemoteAddr().split(
												"\\.")[3];
									}
	
									if (first3PrtsFromAddres
											.equalsIgnoreCase(first3PrtsReqAddres))
									{
										if (StringUtils.isNotBlank(fromAddress)
												&& StringUtils
														.isNotBlank(toAddress)
												&& StringUtils
														.isNotBlank(reqAddress))
										{
											if ((Integer.parseInt(reqAddress) >= Integer
													.parseInt(fromAddress))
													&& (Integer
															.parseInt(reqAddress) <= Integer
															.parseInt(toAddress)))
											{
												logger.info("VALID IP  : "
														+ request.getRemoteAddr());
	
												/*
												 * candidate.setLoginIPAddress(request
												 * .getRemoteAddr());
												 * getCandidateService()
												 * .updateCandidate(candidate);
												 */
	
												// update login IP address....
											}
											else
											{
												// returnToLoginPage(
												// "IP address should be in range from : "
												// + candidateEnrollememt.get(0).
												// getTestCenter
												// ().getFromIpAddress()
												// +" To : "+candidateEnrollememt
												// .get
												//(0).getTestCenter().getToIpAddress
												// ());
												logger
														.info("IP address should be in range from : "
																+ candidateEnrollememt
																		.get(0)
																		.getTestCenter()
																		.getFromIpAddress()
																+ " To : "
																+ candidateEnrollememt
																		.get(0)
																		.getTestCenter()
																		.getToIpAddress());
												addActionError("IP address should be in range from : "
														+ candidateEnrollememt.get(
																0).getTestCenter()
																.getFromIpAddress()
														+ " To : "
														+ candidateEnrollememt.get(
																0).getTestCenter()
																.getToIpAddress());
												getSession().setAttribute(
														"errorMsg",
														"Invalid Login IP Address");
	
												return SUCCESS;
											}
										}
										else
										{
											// returnToLoginPage(
											// "IP address should be in range from : "
											// + candidateEnrollememt.get(0).
											// getTestCenter
											// ().getFromIpAddress()+" To : "
											// +candidateEnrollememt
											// .get(0).getTestCenter
											// ().getToIpAddress());
											logger
													.info("IP address should be in range from : "
															+ candidateEnrollememt
																	.get(0)
																	.getTestCenter()
																	.getFromIpAddress()
															+ " To : "
															+ candidateEnrollememt
																	.get(0)
																	.getTestCenter()
																	.getToIpAddress());
											addActionError("IP address should be in range from : "
													+ candidateEnrollememt.get(0)
															.getTestCenter()
															.getFromIpAddress()
													+ " To : "
													+ candidateEnrollememt.get(0)
															.getTestCenter()
															.getToIpAddress());
											getSession().setAttribute("errorMsg",
													"Invalid Login IP Address");
	
											return SUCCESS;
										}
									}
									else
									{
										logger
												.info("Test Center IP addresses are not matching");
										addActionError("IP address should be in range from : "
												+ candidateEnrollememt.get(0)
														.getTestCenter()
														.getFromIpAddress()
												+ " To : "
												+ candidateEnrollememt.get(0)
														.getTestCenter()
														.getToIpAddress());
										getSession().setAttribute("errorMsg",
												"Invalid Login IP Address");
	
										return SUCCESS;
									}
								}
								else
								{
									// returnToLoginPage("Invalid IP addresses "+
									// first3PrtsFromAddres+"  "
									// +first3PrtsReqAddres);
									logger.info("Invalid IP addresses "
											+ first3PrtsFromAddres + "  "
											+ first3PrtsReqAddres);
									addActionError("Invalid IP addresses "
											+ first3PrtsFromAddres + "  "
											+ first3PrtsReqAddres);
									getSession().setAttribute("errorMsg",
											"Invalid Login IP Address");
	
									return SUCCESS;
								}
							}
							else
							{
								// returnToLoginPage("Invalid IP addresses "+
								// first3PrtsFromAddres+"  " +first3PrtsReqAddres);
								logger.info("Invalid IP addresses "
										+ first3PrtsFromAddres + "  "
										+ first3PrtsReqAddres);
								addActionError("Invalid IP addresses "
										+ first3PrtsFromAddres + "  "
										+ first3PrtsReqAddres);
								getSession().setAttribute("errorMsg",
										"Invalid Login IP Address");
	
								return SUCCESS;
							}
						}
					}
				}
				catch (Exception e)
				{
					logger.error("Exception: " + DGMTUtil.getStackTrace(e));
				}
	
				if ((candidateEnrollememt != null)
						&& (candidateEnrollememt.size() > 0))
				{
					getSession().setAttribute("candidateEnrolled", "true");
				}
	
				getSession().setAttribute("candidateExam", exam);
	
				try
				{
					boolean isCandidatePasswordChanged = getUserManagementService()
							.isCandidatePasswordChanged(candidate, userLogin);
	
					logger.info("login isCandidatePasswordChanged"
							+ isCandidatePasswordChanged);
	
					if (!isCandidatePasswordChanged)
					{
						SimpleDateFormat dateFormat = new SimpleDateFormat(
								"ddMMyyyy");
	
						request.getSession().setAttribute("dob",
								dateFormat.format(candidate.getDateOfBirth()));
	
						//return "changecandidatepassword";
						return "changepassword";
					}
				}
				catch (Exception e)
				{
					
					logger.error("Exception: " + DGMTUtil.getStackTrace(e));
				}
			}

			logger.info("login ends");
			
			return "candidate";
			}		
			else
			{
				//getSession().setAttribute("failed", "true");
				//request.setAttribute("failed", "true");
				return LOGIN;
			}
	}
	
	/**
	 * @return
	 */
	public String checkSessionStatus()
	{
		logger.info("checkSessionStatus starts");

		HttpServletResponse response = ServletActionContext.getResponse();

		try
		{
			response.getWriter().write("Success");
		}
		catch (IOException e)
		{
			logger.error("Exception: " + DGMTUtil.getStackTrace(e));
			
		}

		logger.info("checkSessionStatus ends");

		return null;
	}

	/**
	 * @param message
	 * @return
	 */
	public String returnToLoginPage(String message)
	{
		logger.info("returnToLoginPage starts");

		try
		{
			logger.info(message);
			addActionError(message);
		}
		catch (Exception e)
		{
			logger.error("Exception: " + DGMTUtil.getStackTrace(e));
			
		}

		logger.info("returnToLoginPage ends");

		return SUCCESS;
	}

	/**
	 * @return
	 */
	public String forgotPassword()
	{
		logger.info("forgotPassword starts");

		try
		{
			DGMTProperties properties = DGMTProperties.getProperties();
			String strongpassword = properties.getProperty("STRONG_PASSWORD","false");
			
			logger.info("forgotPassword starts strongpassword"+strongpassword);
			
			getSession().setAttribute("strongpassword", strongpassword);
			
			populateSecurityQuestions();

			String username = request.getParameter("j_username");
			
			logger.info("forgotPassword starts username"+username);

            Boolean valid = Validator.validate(username);
			
			if(valid){	
				getSession().setAttribute("forgotPassUserName", username);
			}else{
				request.getSession().setAttribute("statusval", "invalidData");
			} 
			candidate = getUserManagementService().getCandidateDetails(username);
		}
		catch (Exception e)
		{
			logger.error("Exception: " + DGMTUtil.getStackTrace(e));
			
		}

		logger.info("forgotPassword ends");

		return "forgotPassword";
	}

	/**
	 * @return
	 */
	public String checkSecurityAns()
	{
		logger.info("checkSecurityAns starts");

		try
		{
			String username = (String) getSession().getAttribute(
					"forgotPassUserName");
			
			logger.info("checkSecurityAns username"+username);

			if (username != null)
			{
				candidate = getUserManagementService().getCandidateDetails(
						username);
				
				logger.info("checkSecurityAns candidate"+candidate);

				String answer = request.getParameter("securityAnswer");

				if (candidate.getSecurityAnswer().equals(answer))
				{
					UserLogin userLogin = getUserManagementService()
							.getUserLoginDetails(username);

					url = "j_security_check?j_username=" + username
							+ "&j_password=" + userLogin.getPassword();

					return SUCCESS;
				}
				else
				{
					getActionErrors().add(
							"Security answer do not match. Please try again.");

					return ERROR;
				}
			}
		}
		catch (Exception e)
		{
			logger.error("Exception: " + DGMTUtil.getStackTrace(e));
			
		}

		logger.info("checkSecurityAns ends");

		return ERROR;
	}

	/**
         *
         */
	public void populateSecurityQuestions()
	{
		logger.info("populateSecurityQuestion starts");

		securityQuestionList = (List<SecurityQuestion>) getSession()
				.getAttribute("securityQuestionList");
		logger
				.debug("populateSecurityQuestions security questions list size from session"
						+ ((securityQuestionList == null) ? "null"
								: securityQuestionList.size()));

		try
		{
			logger.debug("populateSecurityQuestions in try block");

			if ((securityQuestionList == null) || (securityQuestionList.size() == 0))
			{
				securityQuestionList = getOleExamService().getSecurityQuestions();
				
				logger.debug("populateSecurityQuestions.03.security questions list size from database "+ securityQuestionList);
				
				Boolean valid = Validator.validate(securityQuestionList);
				
				if(valid){	
					getSession().setAttribute("securityQuestionList",securityQuestionList);
				}else{
					request.getSession().setAttribute("statusval", "invalidData");
				} 
				
			}

			logger.debug("populateSecurityQuestions end of try block");
		}
		catch (Exception e)
		{
			logger.error("Exception: " + DGMTUtil.getStackTrace(e));
			
		}

		logger.info("populateSecurityQuestionse end");
	}

	/**
	 * @return
	 */
	public String saveOrUpdateChangeCandidatePassword() {
		logger.info("saveOrUpdateChangeCandidatePassword starts");
		String salt = (String) request.getParameter("csrfPreventionSalt");
		Cache<String, Boolean> csrfPreventionSaltCache = (Cache<String, Boolean>) request
				.getSession().getAttribute("csrfPreventionSaltCache");

		logger.info("salt-----" + salt);
		try {

			// String userName =
			// (String)getSession().getAttribute("forgotPassUserName");
			if (csrfPreventionSaltCache != null
					&& csrfPreventionSaltCache.getIfPresent(salt)) {

				logger.info("status-----"
						+ csrfPreventionSaltCache.getIfPresent(salt));
				String userName = request.getParameter("user");

				logger.info("saveOrUpdateChangeCandidatePassword userName "
						+ userName);

				String selSecurityQuest = request
						.getParameter("selSecurityQuest");

				logger.info("saveOrUpdateChangeCandidatePassword selSecurityQuest "
						+ selSecurityQuest);

				String securityAnswer = request.getParameter("securityAnswer");

				logger.info("saveOrUpdateChangeCandidatePassword securityAnswer "
						+ securityAnswer);

				candidate = getUserManagementService().getCandidateDetails(
						userName);

				logger.info("saveOrUpdateChangeCandidatePassword candidate "
						+ candidate);

				if (candidate == null) {
					logger.info("saveOrUpdateChangeCandidatePassword if candidate is null");
					populateSecurityQuestions();
					logger.info("saveOrUpdateChangeCandidatePassword No records found with given user name. Please enter valid user");
					addActionError(getText("unavailablerecord.user"));

					return ERROR;
				} else {
					if (!(selSecurityQuest.equalsIgnoreCase(candidate
							.getSecQuest().getId().toString()) && securityAnswer
							.equalsIgnoreCase(candidate.getSecurityAnswer()))) {
						logger.info("saveOrUpdateChangeCandidatePassword in else");
						logger.info("saveOrUpdateChangeCandidatePassword"
								+ selSecurityQuest.equalsIgnoreCase(candidate
										.getSecQuest().getId().toString()));
						logger.info("saveOrUpdateChangeCandidatePassword"
								+ securityAnswer.equalsIgnoreCase(candidate
										.getSecurityAnswer()));
						populateSecurityQuestions();
						addActionError(getText("securityquestion.mismatch"));

						return ERROR;
					}
				}

				logger.info("saveOrUpdateChangeCandidatePassword username:"
						+ userName);

				String newPassword = request.getParameter("newpassword");

				logger.info("saveOrUpdateChangeCandidatePassword newPassword:"
						+ newPassword);

				String updatedBy = userName;

				logger.info("saveOrUpdateChangeCandidatePassword updatedBy:"
						+ updatedBy);

				try {
					logger.debug("saveOrUpdateChangeCandidatePassword in try block");
					getUserManagementService()
							.saveOrUpdateChangeCandidatePassword(userName,
									newPassword, updatedBy);
					logger.debug("saveOrUpdateChangeCandidatePassword end of try block");
				} catch (Exception e) {
					
					logger.error("Exception: " + DGMTUtil.getStackTrace(e));
				}

				getSession().setAttribute("passwordChanged", "true");
				logger.info("saveOrUpdateChangeCandidatePassword ends");
				return SUCCESS;
			} else
				return ERROR;
		} catch (Exception e) {
			
			populateSecurityQuestions();
			logger.error("Exception: " + DGMTUtil.getStackTrace(e));
			return ERROR;
		}
	}

	/**
	 * @return
	 */
	public String checkSecurityDetails()
	{
		logger.info("checkSecurityDetails starts");

		String username = request.getParameter("user");

		logger.debug("checkSecurityDetails username:" + username);
		candidate = getUserManagementService().getCandidateDetails(username);
		logger.debug("checkSecurityDetails candidate object:" + candidate);

		try
		{
			logger.debug("checkSecurityDetails in try block");
			response.getWriter().write(JSONUtil.serialize(candidate));
			logger.debug("checkSecurityDetails end of try block");
		}
		catch (IOException e)
		{
			
			logger.error("Exception: " + DGMTUtil.getStackTrace(e));
		}
		catch (JSONException e)
		{
			
			logger.error("Exception: " + DGMTUtil.getStackTrace(e));
		}

		return null;
	}
	
	/**
	 * change candidate password.
	 * 
	 * @return
	 */
	public String saveOrUpdateChangePassword()
	{
		logger.info("saveOrUpdateChangePassword starts");

		try
		{
			String userName = request.getParameter("user");
			
			String newPassword = request.getParameter("newpassword");

			logger.info("saveOrUpdateChangePassword newPassword:"
					+ newPassword);

			String updatedBy = userName;

			logger.info("saveOrUpdateChangePassword updatedBy:"
					+ updatedBy);
			
			getUserManagementService().saveOrUpdateChangeCandidatePassword(userName, newPassword, updatedBy);
			
			getSession().setAttribute("passwordChanged", "true");
			
			logger.info("saveOrUpdateChangePassword ends");
		}
		catch (Exception e)
		{
			
			logger.error("Exception: " + DGMTUtil.getStackTrace(e));
		}

		logger.info("saveOrUpdateChangePassword ends");

		return SUCCESS;
	}
}
