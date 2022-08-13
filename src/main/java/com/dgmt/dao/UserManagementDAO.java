/**
 * Created Date: 4/23/10 10:50 AM
 * Class Name  : UserManagementDAO.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>        <Author>        <Date>        <Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package com.dgmt.dao;

import java.util.List;

import com.dgmt.model.Candidate;
import com.dgmt.model.UserLogin;
import com.dgmt.model.UserRole;

/**
 * Description:
 * 
 * @version 1.0 4/23/10 10:50 AM
 * @author CTE
 * @since DGMT 1.0
 */
public interface UserManagementDAO
{
	/**
	 * Gets login information for given user id.
	 * 
	 * @param userName
	 *            user id
	 * @return
	 */
	public UserLogin getUserLoginDetails(String userName);

	/**
	 * Saves user roles.
	 * 
	 * @param userLogins
	 */
	public void saveUserRoles(List<UserRole> userLogins);

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
	public void changeCandidatePassword(String candidateLoginId,
			String password, String updatedBy);

	/**
	 * @return
	 */
	public List<UserLogin> getUpdatedPasswordUsers();
}
