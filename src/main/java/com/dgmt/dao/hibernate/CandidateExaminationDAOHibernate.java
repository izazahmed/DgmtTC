/**
 * Created Date: 7/22/10 1:16 PM
 * Class Name  : CandidateExaminationDAOHibernate.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>	<Author>	<Date>	<Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.dgmt.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.dgmt.dao.AbstractSpringDao;
import com.dgmt.dao.CandidateExaminationDAO;
import com.dgmt.model.GeneratedQuePaper;

/**
 * Description:
 * 
 * @version 1.0 7/22/10 1:16 PM
 * @author
 * @since DGMT 1.0
 */
public class CandidateExaminationDAOHibernate extends AbstractSpringDao
		implements CandidateExaminationDAO
{

	/**
	 * @param questionPaperId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GeneratedQuePaper> getQuestionPaper(Long questionPaperId)
	{
		logger.info("getQuestionPaper starts");

		List questList = null;

		questList = getHibernateTemplate().find(
				"from GeneratedQuePaper where id = " + questionPaperId);

		if ((questList == null) || (questList.size() == 0))
		{
			return null;
		}

		logger.info("getQuestionPaper ends");

		return questList;
	}

	@Override
	public Long getQuestionPaperStatusId(final String questionPaperId) {
		// TODO Auto-generated method stub
		
		return getHibernateTemplate().execute(new HibernateCallback<Long>()
				{
					@Override
					public Long doInHibernate(Session session)
							throws HibernateException, SQLException
					{
						String sql = "select gqp.status.id from GeneratedQuePaper  gqp WHERE gqp.id =  "+questionPaperId;
						Long count = (Long) session.createQuery(sql).uniqueResult();

						return count;
					}
				});
	}
}
