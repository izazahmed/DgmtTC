/**
 * Created Date: 4/15/10 7:24 PM
 * Class Name  : ExamDAOHibernate.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>        <Author>        <Date>        <Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package com.dgmt.dao.hibernate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.dgmt.dao.AbstractSpringDao;
import com.dgmt.dao.OleExamDAO;
import com.dgmt.model.AnsweredPapersTransferStatus;
import com.dgmt.model.Candidate;
import com.dgmt.model.ExamPaper;
import com.dgmt.model.ExamWindow;
import com.dgmt.model.GeneratedQuePaper;
import com.dgmt.model.GeneratedQuePaperDtls;
import com.dgmt.model.OleExam;
import com.dgmt.model.OleSubject;
import com.dgmt.model.ProctorKey;
import com.dgmt.model.Resources;
import com.dgmt.model.SecurityQuestion;
import com.dgmt.model.TestCenter;
import com.dgmt.model.TimeSlot;
import com.dgmt.util.DGMTUtil;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:24 PM
 * @author
 * @since DGMT 1.0
 */
public class OleExamDAOHibernate extends AbstractSpringDao implements
		OleExamDAO
{
	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OleExam> getExams()
	{
		return findAll(OleExam.class);
	}

	/**
	 * @param id
	 * @return
	 */
	@Override
	public OleExam getExamById(Long id)
	{
		return (OleExam) findGet(OleExam.class, id);
	}

	/**
	 * @param name
	 * @return
	 */
	@Override
	public OleExam getExamByName(String name)
	{
		List<OleExam> exams = getHibernateTemplate().find(
				"from OleExam e where e.name='" + name + "'");

		if ((exams != null) && (exams.size() == 1))
		{
			return exams.get(0);
		}

		return null;
	}

	/**
	 * @param id
	 * @return
	 */
	@Override
	public OleSubject getSubject(Long id)
	{
		return (OleSubject) findGet(OleSubject.class, id);
	}

	/**
	 * @param exam
	 * @return
	 */
	@Override
	public List<ExamWindow> getActiveExamWindows(OleExam exam)
	{
		return getHibernateTemplate().find(
				"from ExamWindow ew where ew.exam = ? and ew.status = '1'",
				exam);
	}

	/**
	 * @param id
	 * @return
	 */
	@Override
	public ExamPaper getExamPapers(Long id)
	{
		return (ExamPaper) findGet(ExamPaper.class, id);
	}

	/**
	 * Get examination papers associated with given window and subjects.
	 * 
	 * @param examWindow
	 *            examination window
	 * @param oleSubject
	 *            list of subjects
	 * @return list of exam papers
	 */
	@Override
	public List<ExamPaper> getCandidateExamPapers(ExamWindow examWindow,
			String subjectIds)
	{
		List<ExamPaper> list = null;

		list = getHibernateTemplate().find(
				"from ExamPaper ep where ep.examWindow = ? and ep.subject.id in ("
						+ subjectIds
						+ ") and ep.status='2' order by ep.subject.name asc  ",
				examWindow);

		return list;
	}

	/**
	 * @param examPaper
	 * @return
	 */
	@Override
	public List<GeneratedQuePaper> getExamPaper(final ExamPaper examPaper)
	{
		return getHibernateTemplate().execute(
				new HibernateCallback<List<GeneratedQuePaper>>()
				{
					public List<GeneratedQuePaper> doInHibernate(
							org.hibernate.Session sess)
							throws org.hibernate.HibernateException,
							java.sql.SQLException
					{
						Criteria cri = sess
								.createCriteria(GeneratedQuePaper.class);

						cri.add(Restrictions.eq("examPaper", examPaper));
						cri.add(Restrictions.isNull("candidate"));
						cri.add(Restrictions.isNull("candidateEnrollememt"));

						cri.setMaxResults(1);

						List<GeneratedQuePaper> list = cri.list();

						// if (list==null)
						// return null;
						return list;
					};
				});
	}

	/**
	 * @param quePaperDtls
	 */
	@Override
	public void updateQuestionDtls(GeneratedQuePaperDtls quePaperDtls)
	{
		getHibernateTemplate().update(quePaperDtls);
	}
	
	/**
	 * Updates question paper.
	 * 
	 * @param generatedQuePaper
	 *            question paper to be updated
	 */
	@Override
	public void updateGeneratedQuestionPaper(GeneratedQuePaper generatedQuePaper)
	{
		getHibernateTemplate().merge(generatedQuePaper);
	}
	
	/**
	 * Updates question paper.
	 * 
	 * @param generatedQuePaper
	 *            question paper to be updated
	 */
	@Override
	public void updateTimeLeftInSecsForGQPaper(GeneratedQuePaper generatedQuePaper)
	{
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery("update GeneratedQuePaper set timeLeftInSecs = :timeinsecs,"
						+ " lastUpdatedQuestionTime =:lastAccesstime where id = :gqpId");
		query.setParameter("timeinsecs",generatedQuePaper.getTimeLeftInSecs() );
		query.setParameter("lastAccesstime",generatedQuePaper.getLastUpdatedQuestionTime());
		query.setParameter("gqpId",generatedQuePaper.getId());
	    query.executeUpdate();
	}

	/**
	 * @param examPaper
	 * @param candidate
	 * @return
	 */
	@Override
	public List<GeneratedQuePaper> getGQPIdForCandidate(
			final ExamPaper examPaper, final Candidate candidate)
	{
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(
				new HibernateCallback<List<GeneratedQuePaper>>()
				{
					public List<GeneratedQuePaper> doInHibernate(
							org.hibernate.Session sess)
							throws org.hibernate.HibernateException,
							java.sql.SQLException
					{
						Criteria cri = sess
								.createCriteria(GeneratedQuePaper.class);

						Calendar cal = Calendar.getInstance();
						Date today = new Date();

						cal.setTime(today);

						DateFormat formatter = new SimpleDateFormat(
								"dd-MMM-yy hh:mm:ss");

						try
						{
							// Date date = (Date)formatter.format(date);
							today = (Date) formatter.parse(formatter
									.format(today));
						}
						catch (ParseException e)
						{
						}

						cri.add(Restrictions.eq("examPaper", examPaper));
						cri.add(Restrictions.isNotNull("candidate"));
						cri.add(Restrictions.eq("candidate", candidate));
						cri.add(Restrictions.isNull("endTime"));
						cri.setMaxResults(1);

						List<GeneratedQuePaper> list = cri.list();

						// if (list==null)
						// return null;
						return list;
					};
				});
	}

	/**
	 * @param ew
	 * @return
	 */
	@Override
	public ProctorKey getTodaysProctorKey(ExamWindow ew)
	{
		ProctorKey pk = null;
		List<ProctorKey> pks = getHibernateTemplate().find(
				"from ProctorKey pk where pk.ew = ? and pk.date= ?", ew,
				new Date());

		if ((pks != null) && (pks.size() == 1))
		{
			pk = pks.get(0);
		}

		return pk;
	}

	/**
	 * @param papers
	 */
	@Override
	public void saveQuestionPapers(List<GeneratedQuePaper> papers)
	{
		getHibernateTemplate().saveOrUpdateAll(papers);
	}

	/**
	 * @param proctorKeys
	 */
	@Override
	public void saveProctorKeys(List<ProctorKey> proctorKeys)
	{
		ProctorKey pk = proctorKeys.get(0);
		// if regenerated proctor keys then delete old once
		if (pk.getStatus().equals("1"))
		{
			deleteProcterKeys(pk.getEw());
			getHibernateTemplate().setAlwaysUseNewSession(true);
			getHibernateTemplate().saveOrUpdateAll(proctorKeys);
			getHibernateTemplate().setAlwaysUseNewSession(false);
		}
		else
		{
			getHibernateTemplate().saveOrUpdateAll(proctorKeys);
		}
		
	}

	/**
	 * @return
	 */
	@Override
	public List<ExamWindow> getAllActiveExamWindows()
	{
		return getHibernateTemplate().find(
				"from ExamWindow ew where ew.status = '1'");
	}

	/**
	 * @param examWindow
	 * @return
	 */
	public List<GeneratedQuePaper> getAnsweredQuestionPapers(
			final ExamWindow examWindow)
	{
		return getHibernateTemplate().execute(
				new HibernateCallback<List<GeneratedQuePaper>>()
				{
					public List<GeneratedQuePaper> doInHibernate(
							org.hibernate.Session sess)
							throws org.hibernate.HibernateException,
							java.sql.SQLException
					{
						DateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
						Date date = new Date();
						String s = formatter.format(date);

						try {
							date = formatter.parse(s);
						} catch (ParseException e) {
							
							logger.error("Exception: " + DGMTUtil.getStackTrace(e));
						}

						List<ExamPaper> papers = getHibernateTemplate().find(
								"from ExamPaper ep where ep.examWindow = ?",
								examWindow);
						Query query = sess
								.createQuery("FROM GeneratedQuePaper gqp WHERE gqp.examPaper IN (:ids) and ( status.id = 5 or status.id = 6) and transfered is null");

						query.setParameterList("ids", papers);

						return query.list();
					};
				});
	}

	/**
	 * @param answeredPapersTransferStatus
	 */
	@Override
	public void saveAnsweredPapersTransferStatus(
			AnsweredPapersTransferStatus answeredPapersTransferStatus)
	{
		getHibernateTemplate().saveOrUpdate(answeredPapersTransferStatus);
	}
	
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																		
	/**
	 * @param messageId
	 * @return
	 */
	@Override
	public AnsweredPapersTransferStatus getPaperTransferStatus(String messageId)
	{
		List<AnsweredPapersTransferStatus> list = getHibernateTemplate().find(
				"from AnsweredPapersTransferStatus where messageId= '"
						+ messageId + "'");

		if ((list != null) && (list.size() == 1))
		{
			return list.get(0);
		}

		return null;
	}

	/**
	 * @param paperIds
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void removeAnsweredQuestionPapers(String paperIds)
	{
		getHibernateTemplate()
				.bulkUpdate(
						"DELETE FROM GeneratedQuePaper WHERE id IN ("
								+ paperIds + ") ");
	}

	/**
	 * @param questionPapers
	 */
	@Override
	public void updateTrasferredFlag(List<GeneratedQuePaper> questionPapers)
	{
		getHibernateTemplate().saveOrUpdateAll(questionPapers);
	}

	/**
	 * @param ew
	 */
	@Override
	public void deleteProcterKeys(ExamWindow ew)
	{
		getHibernateTemplate().deleteAll(
				getHibernateTemplate().find("from ProctorKey pk where pk.ew=?",
						ew));
	}

	/**
	 * @param ew
	 * @return
	 */
	@Override
	public List<GeneratedQuePaper> getExamStatus(final ExamWindow ew)
	{
		return getHibernateTemplate()
				.find(
						"from GeneratedQuePaper qp where qp.status.id in (5, 6) and qp.examPaper.examWindow = ? ",
						ew);		
	}

	/**
	 * @param testCenterId
	 * @return
	 */
	@Override
	public TestCenter getTestCenter(Long testCenterId)
	{
		return (TestCenter) findGet(TestCenter.class, testCenterId);
	}

	/**
	 * @return
	 */
	@Override
	public List<SecurityQuestion> getSecurityQuestions()
	{
		return findAll(SecurityQuestion.class);
	}
	
	/**
	 * 
	 */
	public void deleteExamData(final String examId)
	{		
		//getHibernateTemplate().bulkUpdate("delete ul from UserLogin ul inner join Candidate cd on cd.personalNo = ul.name where cd.eligibleExamId = '"+examId +"'");
		getHibernateTemplate().bulkUpdate("delete from ExamWindow where exam = ? ", findGet(OleExam.class, Long.valueOf(examId)));
		getHibernateTemplate().bulkUpdate("delete from Candidate where eligibleExamId =  '"+examId +"'");
		
		List<ExamWindow> examWindows = getHibernateTemplate().find("from ExamWindow");
		
		//if there are no exam windows then delete subjects, test center, resources and exam
		if(examWindows == null || examWindows.size() == 0)
		{
			getHibernateTemplate().bulkUpdate("delete from OleSubject");			
			getHibernateTemplate().bulkUpdate("delete from State");
			getHibernateTemplate().bulkUpdate("delete from OleExam");
			getHibernateTemplate().bulkUpdate("delete from AnsweredPapersTransferStatus");
			getHibernateTemplate().bulkUpdate("delete from OleContributor");
			
			// user logins will be deleted if there are no exam windows (not able to delete user login with joins) 
			getHibernateTemplate().bulkUpdate("delete from UserLogin");
			getHibernateTemplate().bulkUpdate("delete from Role");
		}		
	}
	
	@Override
	public void deleteResources()
	{
		getHibernateTemplate().bulkUpdate("delete from Resources");		
	}
	
	@Override
	public List<Resources> getResources()
	{		
		return getHibernateTemplate().find("from Resources");
	}
	
	/**
	 * @return
	 */
	@Override
	public List<ExamWindow> getAllExamWindows()
	{
		return getHibernateTemplate().find("from ExamWindow");
	}
	
	@Override
	public void mergeExamWindow(ExamWindow ew)
	{
		getHibernateTemplate().merge(ew);	
	}
	
	@Override
	public List<ExamWindow> getActiveExamWindows(String examId)
	{
		OleExam exam = (OleExam)findGet(OleExam.class, Long.valueOf(examId));
		if(exam != null)
		{
			return getActiveExamWindows(exam);
		}
		return null;
	}
	
	@Override
	public List<GeneratedQuePaper> getAnsweredPaperCount()
	{
		return getHibernateTemplate()
				.find("from GeneratedQuePaper qp where qp.status.id in (5, 6)"); 
	}
}
