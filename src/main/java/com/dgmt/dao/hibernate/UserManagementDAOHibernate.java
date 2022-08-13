/**
 * Created Date: 4/23/10 10:50 AM
 * Class Name  : UserManagementDAOHibernate.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>        <Author>        <Date>        <Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package com.dgmt.dao.hibernate;

import java.util.Date;
import java.util.List;

import com.dgmt.dao.AbstractSpringDao;
import com.dgmt.dao.UserManagementDAO;
import com.dgmt.model.Candidate;
import com.dgmt.model.ExamWindow;
import com.dgmt.model.UserLogin;
import com.dgmt.model.UserRole;
import com.dgmt.util.DGMTUtil;
//import com.dgmt.util.SHA1Converter;
import com.dgmt.util.SHA256Converter;

/**
 * Description:
 * 
 * @version 1.0 4/23/10 10:50 AM
 * @author CTE
 * @since DGMT 1.0
 */
public class UserManagementDAOHibernate extends AbstractSpringDao implements
		UserManagementDAO
{
	/**
	 * Gets login information for given user id.
	 * 
	 * @param userName
	 *            user id
	 * @return
	 */
	@Override
	public UserLogin getUserLoginDetails(String userName)
	{
		UserLogin userLogin = null;
		List<UserLogin> list = null;

		list = getHibernateTemplate().find(
				"from UserLogin ul where ul.name='" + userName + "'");

		if ((list != null) && (list.size() != 0))
		{
			userLogin = list.get(0);
		}

		return userLogin;
	}

	/**
	 * Saves user roles.
	 * 
	 * @param userLogins
	 */
	@Override
	public void saveUserRoles(List<UserRole> userLogins)
	{
		getHibernateTemplate().saveOrUpdateAll(userLogins);
	}

	/**
	 * @param username
	 * @return
	 */
	@Override
	public Candidate getCandidateDetails(String username)
	{
		Candidate candidate = null;
		List<Candidate> list = null;

		list = getHibernateTemplate().find(
				"from Candidate can where can.personalNo='" + username + "'");

		if ((list != null) && (list.size() != 0))
		{
			candidate = list.get(0);
		}

		return candidate;
	}

	/**
	 * @param candidateLoginId
	 * @param password
	 * @param changedBy
	 */
	@Override
	public void changeCandidatePassword(String candidateLoginId,
			String password, String changedBy)
	{
		List<UserLogin> uList = getHibernateTemplate().find(
				"from UserLogin ul where ul.name = ?", candidateLoginId);

		if ((uList != null) && (uList.size() == 1))
		{
			UserLogin ul = uList.get(0);

			ul.setModifiedBy(changedBy);
			ul.setModificationDate(new Date());
			// 12 status is to check that candidate changed the password.
			ul.setStatus(Long.parseLong("12"));

			try
			{
				//ul.setPassword("{SHA-1}" + SHA1Converter.SHA1(password));
				ul.setPassword("{SHA-256}" + SHA256Converter.SHA256(password));
			}
			catch (Exception e)
			{
				logger.error(DGMTUtil.getStackTrace(e));
			}

			getHibernateTemplate().update(ul);
		}
	}

	/**
	 * @return
	 */
	@Override
	public List<UserLogin> getUpdatedPasswordUsers()
	{
		return getHibernateTemplate().find(
				"from UserLogin ul where ul.status = 12");
	}
}
