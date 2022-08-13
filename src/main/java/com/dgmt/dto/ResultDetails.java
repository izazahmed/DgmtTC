/**
 * Created Date: 9/7/10 1:13 PM
 * Class Name  : ResultDetails.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>	<Author>	<Date>	<Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.dgmt.dto;

import java.util.Set;

import com.dgmt.model.ExamPaperSection;
import com.dgmt.model.GeneratedQuePaper;

/**
 * Description:
 * 
 * @version 1.0 9/7/10 1:13 PM
 * @author
 * @since DGMT 1.0
 */
public class ResultDetails
{
	private String subject;

	private String title;

	private String noObjQuestions;

	private String noCorrectAnswers;

	private String totalMarks;

	private GeneratedQuePaper generatedQuePaper;
	
	private String isResultsSubmitted;

	private Set<ExamPaperSection> sections; 

	private int showResults;
	
	private int passPctObj;
	
	private String subjectDetails;

	private String userName;

	private String startedAt;
	
	public int getShowResults() {
		return showResults;
	}

	public void setShowResults(int showResults) {
		this.showResults = showResults;
	}

	public int getPassPctObj() {
		return passPctObj;
	}

	public void setPassPctObj(int passPctObj) {
		this.passPctObj = passPctObj;
	}

	public Set<ExamPaperSection> getSections() {
		return sections;
	}

	public void setSections(Set<ExamPaperSection> sections) {
		this.sections = sections;
	}

	public String getIsResultsSubmitted() {
		return isResultsSubmitted;
	}

	public void setIsResultsSubmitted(String isResultsSubmitted) {
		this.isResultsSubmitted = isResultsSubmitted;
	}

	/**
	 * @return
	 */
	public GeneratedQuePaper getGeneratedQuePaper()
	{
		return generatedQuePaper;
	}

	/**
	 * @param generatedQuePaper
	 */
	public void setGeneratedQuePaper(GeneratedQuePaper generatedQuePaper)
	{
		this.generatedQuePaper = generatedQuePaper;
	}

	/**
	 * @return
	 */
	public String getTotalMarks()
	{
		return totalMarks;
	}

	/**
	 * @param totalMarks
	 */
	public void setTotalMarks(String totalMarks)
	{
		this.totalMarks = totalMarks;
	}

	/**
	 * @return
	 */
	public String getSubject()
	{
		return subject;
	}

	/**
	 * @param subject
	 */
	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	/**
	 * @return
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * @param title
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * @return
	 */
	public String getNoObjQuestions()
	{
		return noObjQuestions;
	}

	/**
	 * @param noObjQuestions
	 */
	public void setNoObjQuestions(String noObjQuestions)
	{
		this.noObjQuestions = noObjQuestions;
	}

	/**
	 * @return
	 */
	public String getNoCorrectAnswers()
	{
		return noCorrectAnswers;
	}

	/**
	 * @param noCorrectAnswers
	 */
	public void setNoCorrectAnswers(String noCorrectAnswers)
	{
		this.noCorrectAnswers = noCorrectAnswers;
	}

	public String getSubjectDetails()
	{
		return subjectDetails;
	}

	public void setSubjectDetails(String subjectDetails)
	{
		this.subjectDetails = subjectDetails;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getStartedAt()
	{
		return startedAt;
	}

	public void setStartedAt(String startedAt)
	{
		this.startedAt = startedAt;
	}
	
	
}
