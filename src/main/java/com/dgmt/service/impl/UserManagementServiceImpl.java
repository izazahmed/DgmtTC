/**
 * Created Date: 4/23/10 10:52 AM
 * Class Name  : UserManagementServiceImpl.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>        <Author>        <Date>        <Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package com.dgmt.service.impl;

import java.text.SimpleDateFormat;

import com.dgmt.dao.UserManagementDAO;
import com.dgmt.model.Candidate;
import com.dgmt.model.UserLogin;
import com.dgmt.service.api.UserManagementService;
import com.dgmt.util.DGMTUtil;
import com.dgmt.util.Log;
import com.dgmt.util.LogFactory;
//import com.dgmt.util.SHA1Converter;
import com.dgmt.util.SHA256Converter;

/**
 * Description:
 * 
 * @version 1.0 4/23/10 10:52 AM
 * @author
 * @since DGMT 1.0
 */
public class UserManagementServiceImpl implements UserManagementService
{
	private UserManagementDAO userManagementDAO;

	private Log logger = LogFactory.getLog(UserManagementServiceImpl.class);
	
	/**
	 * @return
	 */
	public UserManagementDAO getUserManagementDAO()
	{
		return userManagementDAO;
	}

	/**
	 * @param userManagementDAO
	 */
	public void setUserManagementDAO(UserManagementDAO userManagementDAO)
	{
		this.userManagementDAO = userManagementDAO;
	}

	/**
	 * @param userName
	 * @return
	 */
	@Override
	public UserLogin getUserLoginDetails(String userName)
	{
		return userManagementDAO.getUserLoginDetails(userName);
	}
	
	/**
	 * @param candidate
	 * @param userLogin
	 * @return
	 */
	@Override
	public boolean isCandidatePasswordChanged(Candidate candidate,
			UserLogin userLogin)
	{
		String encryptedLoginId = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");

		try
		{
			//encryptedLoginId = "{SHA-1}"+ SHA1Converter.SHA1(dateFormat.format(candidate.getDateOfBirth()));
			encryptedLoginId = "{SHA-256}"+ SHA256Converter.SHA256(dateFormat.format(candidate.getDateOfBirth()));
		}
		catch (Exception e)
		{
			logger.error(DGMTUtil.getStackTrace(e));
		}

		return !(userLogin.getPassword().equalsIgnoreCase(encryptedLoginId));
	}

	/**
	 * @param username
	 * @return
	 */
	@Override
	public Candidate getCandidateDetails(String username)
	{
		return userManagementDAO.getCandidateDetails(username);
	}

	/**
	 * @param candidateLoginId
	 * @param password
	 * @param updatedBy
	 */
	@Override
	public void saveOrUpdateChangeCandidatePassword(String candidateLoginId,
			String password, String updatedBy)
	{
		userManagementDAO.changeCandidatePassword(candidateLoginId, password,
				updatedBy);
	}
}
