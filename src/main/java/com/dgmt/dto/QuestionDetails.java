/**
 * Created Date: 9/7/10 1:11 PM
 * Class Name  : QuestionDetails.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>	<Author>	<Date>	<Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.dgmt.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dgmt.model.ExamPaperSection;
import com.dgmt.model.GeneratedQuePaper;
import com.dgmt.model.GeneratedQuePaperDtls;

/**
 * Description:
 * 
 * @version 1.0 9/7/10 1:11 PM
 * @author
 * @since DGMT 1.0
 */
public class QuestionDetails implements Cloneable
{
	private String questionNo;

	private GeneratedQuePaperDtls generatedQuePaperDtls;

	private GeneratedQuePaper generatedQuePaper;

	private int questionsCount;

	private String submittedOn;

	private String subject;

	private String title;

	private String name;

	private int marks;

	private String review;

	private String keys;

	private String reviewQuestionsCount;

	private long elapsedTimeInSecs;

	private Map<Integer, Object> unAnsQuestions;

	private List<GeneratedQuePaperDtls> genQPaperDtls;
	
	private String enableQuesSearch;
	
	private String instructions;
	
	private Long statusId;
	
	private Long timeLeftInSecs;
	
	private Map<Integer, String> reviewQuestions;
	
	private String candQuestionImage;
	
	private String candQuestionImageAnno;
	
	private int answerCount;
	
	public int getAnswerCount() {
		return answerCount;
	}

	public void setAnswerCount(int answerCount) {
		this.answerCount = answerCount;
	}

	public Map<Integer, String> getReviewQuestions() {
		return reviewQuestions;
	}

	public void setReviewQuestions(Map<Integer, String> reviewQuestions) {
		this.reviewQuestions = reviewQuestions;
	}

	public Object clone() {
		
		QuestionDetails obj = new QuestionDetails();
		obj.setQuestionNo(this.questionNo);
		obj.setGeneratedQuePaper(this.generatedQuePaper);
		obj.setQuestionsCount(this.questionsCount);
		obj.setSubmittedOn(this.submittedOn);
		obj.setSubject(this.subject);
		obj.setTitle(this.title);
		obj.setName(this.name);
		obj.setMarks(this.marks);
		obj.setReview(this.review);
		obj.setKeys(this.keys);
		obj.setReviewQuestionsCount(this.reviewQuestionsCount);
		obj.setElapsedTimeInSecs(this.elapsedTimeInSecs);
		obj.setGenQPaperDtls(this.genQPaperDtls);
		obj.setEnableQuesSearch(this.enableQuesSearch);
		obj.setInstructions(this.instructions);
		obj.setStatusId(this.statusId);
		obj.setTimeLeftInSecs(this.timeLeftInSecs);
		obj.setExamPaperTitle(this.examPaperTitle);
		obj.setExamStartTime(this.examStartTime);
		obj.setShowResults(this.showResults);
		obj.setPassPctObj(this.passPctObj);
		
	    return obj;
	}
	
	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	private String examPaperTitle;
	
	private String candidatePersonalNo;
	
	private Date examStartTime;
	
	private int showResults;
	
	private int passPctObj;
	
	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getExamPaperTitle() {
		return examPaperTitle;
	}

	public Long getTimeLeftInSecs() {
		return timeLeftInSecs;
	}

	public void setTimeLeftInSecs(Long timeLeftInSecs) {
		this.timeLeftInSecs = timeLeftInSecs;
	}

	public void setExamPaperTitle(String examPaperTitle) {
		this.examPaperTitle = examPaperTitle;
	}

	public String getCandidatePersonalNo() {
		return candidatePersonalNo;
	}

	public void setCandidatePersonalNo(String candidatePersonalNo) {
		this.candidatePersonalNo = candidatePersonalNo;
	}

	public Date getExamStartTime() {
		return examStartTime;
	}

	public void setExamStartTime(Date examStartTime) {
		this.examStartTime = examStartTime;
	}

	 
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

	private Set<ExamPaperSection> sections; 
	

	public String getEnableQuesSearch() {
		return enableQuesSearch;
	}

	public void setEnableQuesSearch(String enableQuesSearch) {
		this.enableQuesSearch = enableQuesSearch;
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
	public Map<Integer, Object> getUnAnsQuestions()
	{
		return unAnsQuestions;
	}

	/**
	 * @param unAnsQuestions
	 */
	public void setUnAnsQuestions(Map<Integer, Object> unAnsQuestions)
	{
		this.unAnsQuestions = unAnsQuestions;
	}

	/**
	 * @return
	 */
	public long getElapsedTimeInSecs()
	{
		return elapsedTimeInSecs;
	}

	/**
	 * @param elapsedTimeInSecs
	 */
	public void setElapsedTimeInSecs(long elapsedTimeInSecs)
	{
		this.elapsedTimeInSecs = elapsedTimeInSecs;
	}

	/**
	 * @return
	 */
	public String getReviewQuestionsCount()
	{
		return reviewQuestionsCount;
	}

	/**
	 * @param reviewQuestionsCount
	 */
	public void setReviewQuestionsCount(String reviewQuestionsCount)
	{
		this.reviewQuestionsCount = reviewQuestionsCount;
	}

	/**
	 * @return
	 */
	public List<GeneratedQuePaperDtls> getGenQPaperDtls()
	{
		return genQPaperDtls;
	}

	/**
	 * @param genQPaperDtls
	 */
	public void setGenQPaperDtls(List<GeneratedQuePaperDtls> genQPaperDtls)
	{
		this.genQPaperDtls = genQPaperDtls;
	}

	/**
	 * @return
	 */
	public String getKeys()
	{
		return keys;
	}

	/**
	 * @param keys
	 */
	public void setKeys(String keys)
	{
		this.keys = keys;
	}

	/**
	 * @return
	 */
	public String getReview()
	{
		return review;
	}

	/**
	 * @param review
	 */
	public void setReview(String review)
	{
		this.review = review;
	}

	/**
	 * @return
	 */
	public int getMarks()
	{
		return marks;
	}

	/**
	 * @param marks
	 */
	public void setMarks(int marks)
	{
		this.marks = marks;
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
	public String getName()
	{
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return
	 */
	public String getSubmittedOn()
	{
		return submittedOn;
	}

	/**
	 * @param submittedOn
	 */
	public void setSubmittedOn(String submittedOn)
	{
		this.submittedOn = submittedOn;
	}

	/**
	 * @return
	 */
	public String getQuestionNo()
	{
		return questionNo;
	}

	/**
	 * @param questionNo
	 */
	public void setQuestionNo(String questionNo)
	{
		this.questionNo = questionNo;
	}

	/**
	 * @return
	 */
	public GeneratedQuePaperDtls getGeneratedQuePaperDtls()
	{
		return generatedQuePaperDtls;
	}

	/**
	 * @param generatedQuePaperDtls
	 */
	public void setGeneratedQuePaperDtls(
			GeneratedQuePaperDtls generatedQuePaperDtls)
	{
		this.generatedQuePaperDtls = generatedQuePaperDtls;
	}

	/**
	 * @return
	 */
	public int getQuestionsCount()
	{
		return questionsCount;
	}

	/**
	 * @param questionsCount
	 */
	public void setQuestionsCount(int questionsCount)
	{
		this.questionsCount = questionsCount;
	}
	
	public String getCandQuestionImage()
	{
		return candQuestionImage;
	}

	public void setCandQuestionImage(String candQuestionImage)
	{
		this.candQuestionImage = candQuestionImage;
	}

	public String getCandQuestionImageAnno()
	{
		return candQuestionImageAnno;
	}

	public void setCandQuestionImageAnno(String candQuestionImageAnno)
	{
		this.candQuestionImageAnno = candQuestionImageAnno;
	}
}
