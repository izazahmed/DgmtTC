/**
 * Created Date: 4/23/10 10:51 AM
 * Class Name  : UserManagementService.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>        <Author>        <Date>        <Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package com.dgmt.service.api;

import java.util.List;

import com.dgmt.dto.CandidateDTO;
import com.dgmt.dto.CommonDTO;
import com.dgmt.model.Candidate;
import com.dgmt.model.Role;
import com.dgmt.model.UserLogin;

/**
 * Description:
 * 
 * @version 1.0 4/23/10 10:51 AM
 * @author
 * @since DGMT 1.0
 */
public interface UserManagementService
{
	/**
	 * @param userName
	 * @return
	 */
	public UserLogin getUserLoginDetails(String userName);

	/**
	 * @param candidate
	 * @param userLogin
	 * @return
	 */
	public boolean isCandidatePasswordChanged(Candidate candidate,
			UserLogin userLogin);

	/**
	 * @param username
	 * @return
	 */
	public Candidate getCandidateDetails(String username);

	/**
	 * @param candidateLoginId
	 * @param password
	 * @param updatedBy
	 */
	public void saveOrUpdateChangeCandidatePassword(String candidateLoginId,
			String password, String updatedBy);
}
