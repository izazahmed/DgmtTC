<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,com.isavvix.tools.*,java.util.Hashtable" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="common/js/utility.js"></script>

<html>
<head> 
<title>Exam</title>
<style type="text/css">
.curved
{
border:1px solid black;
}

</style>


<link rel="stylesheet" type="text/css" href="common/css/jquerycssmenu.css" />
<link rel="stylesheet" href="common/css/default.css" type="text/css" />
<link rel="stylesheet" href="common/css/jquery.wysiwyg.css" type="text/css" />
<link rel="stylesheet" type="text/css" media="all" href="common/css/calendar-win2k-cold-1.css" />
<link rel="stylesheet" type="text/css" href="common/css/default.css" />
<link rel="stylesheet" type="text/css" href="common/css/jquery.wysiwyg.css" />
<link rel="stylesheet" type="text/css" href="common/yui/paginator/assets/skins/sam/paginator.css"/>
<link rel="stylesheet" type="text/css" href="common/yui/datatable/assets/skins/sam/datatable.css"/>
<link rel="stylesheet" type="text/css" href="common/css/common.css"  />

<script type="text/javascript" src="common/yui/utilities/utilities.js"></script>
<script type="text/javascript" src="common/yui/datasource/datasource-min.js"></script>
<script type="text/javascript" src="common/yui/datatable/datatable-min.js"></script>
<script type="text/javascript" src="common/yui/paginator/paginator-min.js"></script>
<script type="text/javascript" src="common/yui/json/json-min.js"></script>

<script type="text/javascript" src="common/yui/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="common/yui/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="common/yui/event/event-min.js"></script>
<script type="text/javascript" src="common/yui/connection/connection-min.js"></script>

<script type="text/javascript" src="common/js/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="common/js/jquerycssmenu.js"></script>
<script type="text/javascript" src="common/js/jquery.wysiwyg.js"></script>
<script type="text/javascript" src="common/js/sortable.js" language="javascript"></script>
<script type="text/javascript" src="common/js/calendar.js"></script> 
<script type="text/javascript" src="common/js/calendar-en.js"></script> 
<script type="text/javascript" src="common/js/calendar-setup.js"></script>
<script type="text/javascript" src="common/js/common.js"></script>
<script type="text/javascript" src="common/js/header.js"></script>
<script type="text/javascript" src="common/js/custom.js"></script>
<script type='text/javascript' src='common/js/jquery.simplemodal.js'></script>
<script type="text/javascript" src="common/js/rowexpansion.js"></script>
<script type="text/javascript" src="common/js/nicEdit.js"></script>
<script type="text/javascript" src="common/js/timer.js"></script>


<script type="text/javascript" src="count.js"></script> 

<script type="text/javascript"><!--

if (typeof window.event != 'undefined') // IE 
            
document.onkeydown = function() // IE 
{ 
	var t = event.srcElement.type; 
	var kc = event.keyCode; 
	return ((kc != 8 && kc != 13) || (t == 'text' && kc != 13) || (t == 'textarea') || (t == 'submit' && kc == 13)) 
} 
        
else            
document.onkeypress = function(e)  // FireFox/Others  
{ 
                
	var t = e.target.type; 
	var kc = e.keyCode; 
	if ((kc != 8 && kc != 13) || (t == 'text' && kc != 13) ||    
	(t == 'textarea') || (t == 'submit' && kc == 13)) 
	return true
	else { 
		//alert('Sorry Backspace/Enter is not allowed here'); // Demo code 
		return false
	} 
            
} 

var browserName=navigator.appName; 

if (browserName=="Netscape" || browserName=="Microsoft Internet Explorer")
{ 
	// window.document.body.scroll = 'auto'; // show only if needed
}
var menu = 'examinationWindow';
var countDwn
var timerID = null
var timerRunning = false
var delay = 1000;

var netWorkDelayCount = 10;

var newTimer = new Timer(1000);

var sectionsArray = new Array();
sectionsArray [0] = "I";
sectionsArray[1] = "II";
sectionsArray[2] = "III";
sectionsArray[3] = "IV";
sectionsArray[4] = "V";
sectionsArray[5] = "VI";
sectionsArray[6] = "VII";
sectionsArray[7] = "VIII";
sectionsArray[8] = "IX";
sectionsArray[9] = "X";

var secs = 0;
var mins = 0; 
var hours = 0;

var logoutPress = false;

function limitText(limitField, limitCount, limitNum) {
	if (limitField.value.length > limitNum) {
		limitField.value = limitField.value.substring(0, limitNum);
	} else {
		limitCount.value = limitNum - limitField.value.length;
	}
}

function showResource()
{
    var message = '';
	var questionPaperId = document.getElementById("questionPaperId").value;
	var subjTimeSlotId = document.getElementById("subTimeSlotId").value;
	var subjectCodeId = document.getElementById("subjectCodeId").value;
	var questionNo = document.getElementById("questionNo").value;
	var elapsed = document.getElementById("elapsed").value;
	var message = document.getElementById("message").value;


	var AjaxObject = {
			handleSuccess:function(o){
				// This member handles the success response and passes the response object o to AjaxObject's processResult member.
				this.processResult(o);
			},
			handleFailure:function(o){
				// Failure handler
				
			},
			processResult:function(o){
				var msg = o.responseText;
				if(msg == "SessionExpired")
				{					
					//sessionExpired();
					sessionExpMsgDialog();
					return false;
				}
					var instr  = '';
				    var jsonString = eval ('('+o.responseText+')');
				//	document.getElementById("clearAnswer").checked = false;
				  	document.getElementById("timerInSeconds").value =  jsonString.timeLeftInSecs;   //jsonString.generatedQuePaper.examPaper.examWindow.duration;
					var elapsedTimeInSec  = elapsed; 
					countDwn = 0;
 					InitializeTimer(elapsedTimeInSec,message); //initialize the timer;
					updateReviewList(); // update the review list
  
				    populateHTML(jsonString); // populate the question;

					instr = instr + "<table width=100% border='0'><th> Instructions </th><tr><td>&nbsp;<td></tr><tr>";   	   
					  
					//if (jsonString.generatedQuePaper.examPaper.instructions!=null)   
					if (jsonString.instructions!=null)
					{
						instr = instr + "<td align='center'><textarea rows='20' cols='70' readonly>"+jsonString.instructions+"</textarea></br></br></td>";
					}
					else
					{ 
						instr = instr + "<td align='center'>&nbsp;</td>"; 
					}

					instr = instr + "<tr><td align='center'><input name='Submit241' type='button' class='button' value=' Close ' onClick='return closeInstructionsPopUp();'/> </td></tr></table>";		
					document.getElementById("instructionsDiv").innerHTML = instr;
					         
			}, 
			startRequest:function() {	
				
				var url ='getCandidateQuestion.dgmt?questionPaperId='+questionPaperId+"&action=first&subjTimeSlotId="+subjTimeSlotId+"&subjectCodeId="+subjectCodeId+"&questionNo="+questionNo+"&message=";// removed message as its is not required ... +message;  
				YAHOO.util.Connect.asyncRequest('POST', url, callback);  
			}   
		};

		// Define the callback object for success and failure handlers as well as object scope.
		var callback ={
			success:AjaxObject.handleSuccess,
			failure:AjaxObject.handleFailure,
			scope: AjaxObject
		};

		// Start the transaction.
		AjaxObject.startRequest(); 	
}
function populateHTML(jsonString){
	
	document.getElementById("divAutoSave").style.display="none";
	//if (jsonString.generatedQuePaper.status.id==6) {
	if (jsonString.statusId==6) {
	 
		$(document).ready(function() {     
			var id = $('#resultsSubmittedDiv'); 
			
			//Get the screen height and width
			var maskHeight = $(document).height();
			var maskWidth = $(window).width();

			//Set heigth and width to mask to fill up the whole screen
			$('#mask').css({'width':maskWidth,'height':maskHeight});
			
			//transition effect                           
			$('#mask').fadeIn(300); 
			$('#mask').fadeTo("fast",0.8);    

			//Get the window height and width
			var winH = $(window).height();
			var winW = $(window).width();

			//Set the popup window to center
			$(id).css('top',  winH/2-$(id).height()/2);
			$(id).css('left', winW/2-$(id).width()/2);

			//transition effect
			$(id).fadeIn(1000); 
			$('#mask').show();
			$("#resultsSubmittedDiv").show();
		 });		

	return false;

	} 
	 
	document.getElementById("subjectName").innerHTML='';
 	document.getElementById("instr").style.display="block"; 

	var question = jsonString.generatedQuePaperDtls.question;
	var answer	 = jsonString.generatedQuePaperDtls.answer;
 	var questionNo = jsonString.questionNo;
	var questionsCount = jsonString.questionsCount;
	var choiceSize = question.choices.length;
	var choiceId = ''; 
	var questionType = question.questionType;
	var matchingOptions = question.matchingOptions;
	var matchincorrectAns = question.matchingOptions.correctAns;
	var sectionNo = jsonString.generatedQuePaperDtls.sectionNo;
	var hint	  = question.hint;
	//var review  = jsonString.review
	var review  = jsonString.generatedQuePaperDtls.markForReview;

	var enableQuesSearch = jsonString.enableQuesSearch;

	if (enableQuesSearch!=null) {
		if (enableQuesSearch=="true"){
			document.getElementById("qSearch").style.display="block"	
		} else {
			document.getElementById("qSearch").style.display="none"	
		}
	}
	if (document.getElementById("message").value!="")  
	//countDwn = jsonString.generatedQuePaper.timeLeftInSecs;  
	countDwn = jsonString.timeLeftInSecs;  

//	alert(countDwn); 
//	populateUnAnsDiv(jsonString.unAnsQuestions);
	document.getElementById("reviewQuesCount").value = jsonString.reviewQuestionsCount;
	//alert("populate HTML , review count : "+jsonString.reviewQuestionsCount);
	/*if (review=="true"){
		document.getElementById("markForReview").checked = true;
	}
	else {
		document.getElementById("markForReview").checked = false;
	} */
	if (review=="1"){
		document.getElementById("markForReview").checked = true;
	}
	else {
		document.getElementById("markForReview").checked = false;
	} 
	var image;
	
	if (question.resources!=null)
	{
		image		= question.resources.file; 
	}
	if (questionNo==0) {
		document.getElementById("Prev").disabled="true";
		document.getElementById("Prev1").disabled="true";
	}  
	if ((parseInt(questionNo)+1)==parseInt(questionsCount)){
		document.getElementById("Next").disabled="true";
		document.getElementById("Next1").disabled="true";
	}

	document.getElementById("questionsCount").value = questionsCount;
	document.getElementById("questionType").value = questionType;  
	
	document.getElementById("choiceSize").value = choiceSize;
	document.getElementById("matchingOptionsSize").value = matchingOptions.length;
	
	// jsonString.generatedQuePaper.examPaper.title;  

	document.getElementById("subjectName").innerHTML =  " Subject  -   "+jsonString.generatedQuePaperDtls.question.subject.name+" :: "+" Title    - "+jsonString.examPaperTitle;  
	
	// jsonString.generatedQuePaper.candidate.personalNo;

	document.getElementById("examStartTime").innerHTML  = " User Name :   "+ jsonString.candidatePersonalNo;

	//jsonString.generatedQuePaper.startTime.split("T")[1]; 
	//	alert(jsonString.examStartTime);
	document.getElementById("icNo").innerHTML  = "Started on : "+ jsonString.examStartTime.split("T")[1]; 

	// var secArry  = jsonString.generatedQuePaper.examPaper.sections;

	var secArry  = jsonString.sections;

	
	var marksPerQues;
	var quesCountPerSec = 0;

	if (sectionNo==1){
		quesCountPerSec = secArry[sectionNo-1].questionCount;  
		marksPerQues = secArry[sectionNo-1].marksPerQuestion;
		document.getElementById("examPaperSecId").value = sectionNo;
	}
	else (sectionNo!=document.getElementById("examPaperSecId").value) 
	{
		//alert(secArry[sectionNo-1]);
		document.getElementById("examPaperSecId").value = sectionNo;
		if (secArry[sectionNo-1]!=null) {
			quesCountPerSec = secArry[sectionNo-1].questionCount;
			marksPerQues = secArry[sectionNo-1].marksPerQuestion;
		}
	} 
   
	if (answer==null)  
		answer = '';

	var TableString = '';
	var count = 0 ;
	for (var  k=0;k<choiceSize;k++)  
	count += question.choices[k].isAns;

	document.getElementById("count").value = count;
	var dashStr = "___________";

	var questionText  = (question.questionText).ReplaceAll('{}',dashStr);

	//var questionText = questionText.split("Questions:");

	//alert(questionText[0])
	//		alert(questionText[1])


	TableString = TableString+ "<table width='100%' border='0' colspan='3' cellpadding='1'><tr><td align='center'> <font size='2'>  <i> Total <b>"+questionsCount+"</b> questions. <b>Section  "+sectionsArray[sectionNo-1]+"</b> There are  <b> "+quesCountPerSec +"</b>  question(s) in this section.  </i></font> <font size='2'> <i> Each question carries  <b>"+marksPerQues+"</b> marks.</font></i>  </td></tr>";
	/*if (questionText.length==2) {
		TableString=TableString+'<tr><td bgcolor="#ffffff" style="color:gray"><font size="2" color="black"><b>'+questionText[0]+' </b></font> </td></tr>';
		TableString=TableString+'<tr><td bgcolor="#ffffff" style="color:gray"><font size="2" color="black"><b> Questions : </br>'+questionText[1]+'</b>  </td></tr>';
	} else {
		TableString=TableString+'<tr><td bgcolor="#ffffff" style="color:gray"><b>Q. '+(parseInt(jsonString.questionNo)+1)+"&nbsp; "+questionText[0]+" </b> </td></tr>";
	} */
	TableString=TableString+'<tr><td  bgcolor="#ffffff" style="color:gray"><b>Q. '+(parseInt(jsonString.questionNo)+1)+"&nbsp; "+questionText+"  </b></td></tr>";

	if (questionType==5)
		TableString = TableString+"<tr><td ><table width='100%' border='0' style='color:white'>";
	else
		TableString = TableString+"<tr><td ><table width='100%' bgcolor='#9B4013' border='0' style='color:white'>";

	var chk = 0
	if (choiceSize > 0 && questionType!=3) { 
	 /* var checkCommaAvailableOrNot = answer.indexOf(",");
	   if(checkCommaAvailableOrNot != -1){
	      answer = answer.split(",");
	    }	 */	
	     
	var checkSpecialSymbolAvailableOrNot = answer.indexOf("~");
		if(checkSpecialSymbolAvailableOrNot != -1){
	      answer = answer.split("~");
	    } 
		for (var j = 0 ;j < choiceSize ; j++) { 
			if (questionType==1) {
				var test = question.choices[j].optionText.replace(/'/g,"&#39;");
				question.choices[j].optionText = test;
				if (count > 1) {
					// multiple choice 
					var testval = question.choices[j].optionText.replace(/&#39;/g , "'");
					if (answer[j]!=undefined) {
						if (answer[j]==testval)					
							TableString = TableString+"<tr><td colspan='2' ><input type='checkbox' id=multichoice"+j+" name='multichoice' value='"+question.choices[j].optionText+"' checked />"+question.choices[j].optionText+"</td></tr>";
						else 
							TableString = TableString+"<tr><td colspan='2'><input type='checkbox' id=multichoice"+j+" name='multichoice' value='"+question.choices[j].optionText+"'  />"+question.choices[j].optionText+"</td></tr>";
					}
					else   
						TableString = TableString+"<tr><td colspan='2'><input type='checkbox' id=multichoice"+j+" name='multichoice' value='"+question.choices[j].optionText+"' />"+question.choices[j].optionText+"</td></tr>";
				} else  {
					// single choice
					var testval = question.choices[j].optionText.replace(/&#39;/g , "'");
					if (answer[j]==testval){
						TableString = TableString+"<tr><td colspan='2'><input type='radio' id=singleChoice"+j+" name='RadioSet' value='"+question.choices[j].optionText+"' checked />"+question.choices[j].optionText+"</td></tr>";
					}else{
						TableString = TableString+"<tr><td colspan='2'><input type='radio' id=singleChoice"+j+" name='RadioSet' value='"+question.choices[j].optionText+"'  />"+question.choices[j].optionText+"</td></tr>";
 					}
				}
			} else if (questionType==2) {  
				// true or false
				if (answer[j]==question.choices[j].optionText)
					TableString = TableString+"<tr><td colspan='2'><input type='radio' id=trueOrFalse"+j+" name='trueOrFalse' value='"+question.choices[j].optionText+"' checked />"+question.choices[j].optionText+"</td></tr>";
				else 
					TableString = TableString+"<tr><td colspan='2'><input type='radio' id=trueOrFalse"+j+" name='trueOrFalse' value='"+question.choices[j].optionText+"' />"+question.choices[j].optionText+"</td></tr>";

			}  else if (questionType==5) {
				// subjective type
				TableString = TableString+"<tr><td colspan='2'><textarea name='subjective' id='subjective' rows='12'  cols='100' onKeyUp='javascript:limitText(this,this.form.countdown,2980)' onKeyDown='javascript:limitText(this,this.form.countdown,2980)' 	 >"+answer+"</textarea>";
				
				TableString = TableString+"<tr><td colspan='2'> <font size='1' color='#FF0000'>(Maximum characters: 2980)<br>    <input readonly type='hidden' name='countdown' size='4' value='2980'> </font>"; 
				AutoSaveInit(10000,questionNo);
				document.getElementById("divAutoSave").style.display="block";
			}
		}
	} else if (matchingOptions.length > 0) {
			/* var checkCommaAvailableOrNot = answer.indexOf(",");
	   if(checkCommaAvailableOrNot != -1){
	      answer = answer.split(",");
	    }	 */	 
		var checkSpecialSymbolAvailableOrNot = answer.indexOf("~");
		if(checkSpecialSymbolAvailableOrNot != -1){
	      answer = answer.split("~");
	    } 
			TableString = TableString+ "<tr><td><table width='70%' border='0'>";
			TableString = TableString+"<tr><td width='25%'>Column1</td><td>Column2</td><td><b>Match with Column1</b></td></tr>"
		for (var k = 0 ;k < matchingOptions.length ; k++) { 
			
			TableString = TableString+"<tr><td width='25%'><input type='text'  readonly name='fillinblanks1' maxlength='50' value='"+matchingOptions[k].column1+"' /></td>"; 
			TableString = TableString+"<td width='25%'><input type='text' readonly  name='fillinblanks1' maxlength='50' value='"+matchingOptions[k].column2+"'  /></td>"; 
			if (answer[k]==undefined || answer[k]=='undefined')
			answer[k] = '';
			
			TableString = TableString+"<td width='25%'><select name='match"+k+"' id=mathcing"+k+" >"+matchingAns(answer[k],matchingOptions)+"</select></td></tr>";
		}
		TableString = TableString+ "</table></td></tr>";
	} else if (questionType==5) {
	//	alert(answer);
		TableString = TableString+"<tr><td><textarea name='subjective' id='subjective' onKeyUp='javascript:limitText(this,this.form.countdown,2980)' onKeyDown='javascript:limitText(this,this.form.countdown,2980)' 	 >"+answer+"</textarea>";
		
		TableString = TableString+"<tr><td> <font size='1' color='#FF0000'>(Maximum characters: 2980)<br>    <input readonly type='hidden' name='countdown' size='4' value='2980'> </font>"; 
		AutoSaveInit(10000,questionNo);
		document.getElementById("divAutoSave").style.display="block";
		//alert(TableString);
	} else if (questionType==3) {
		//fill in the blanks
			var noOfBracesArray = (question.questionText).split('{}');
			var noOfBraces = noOfBracesArray.length-1;
			document.getElementById("bracesCount").value = noOfBraces;
 			if (jsonString.generatedQuePaperDtls.answer!=null) {
					if (noOfBraces > 0 ) {
						var fillInBlankAnswer = (jsonString.generatedQuePaperDtls.answer).split(',');
						for (var b = 0; b < noOfBraces; b++) { 					
							if (fillInBlankAnswer[b]!="" && fillInBlankAnswer[b]!=undefined){
								TableString = TableString+"<tr><td><input type='text' id=fillinblanks"+b+" maxlength='50'   onkeypress='return handleEnter(this, event)' value='"+fillInBlankAnswer[b]+"'></td></tr>";
							}else {
								TableString = TableString+"<tr><td><input type='text'  id=fillinblanks"+b+"  maxlength='50' onkeypress='return handleEnter(this, event)' /></td></tr>";
							}
						}
					}
			} else {
				if (noOfBraces > 0 ) {
					for (var b = 0; b < noOfBraces; b++) { 
							TableString = TableString+"<tr><td><input type='text'  id=fillinblanks"+b+" maxlength='50' /></td></tr>";
						}
				}
			}
		//	alert(TableString);
	}
	if (image!=null) {
		//alert(image)
		//TableString = TableString+"<tr><td><img src='DGMTImages/"+image+"' width='200' height='300'/></td><td>";  
		var candQuestionImage = jsonString.candQuestionImage;
		var candQuestionImageAnnot = jsonString.candQuestionImageAnno;
		document.getElementById('imageName').value=candQuestionImage;
		TableString = TableString+"<tr><td id='tdapplet'><applet name='annotator' codebase='applet/' archive='imagemark.jar' code='Annotator.class' width='700' height='500' >";
		if(candQuestionImage !=null && candQuestionImage != "")
		{
			TableString = TableString+"<param name='picture' value='DGMTImages/"+candQuestionImage+"'>";
		}
		else
		{
			TableString = TableString+"<param name='picture' value='DGMTImages/"+image+"'>";
		}
		
		if(candQuestionImageAnnot !=null && candQuestionImageAnnot != "")
		{
			TableString = TableString+"<param name='annotation' value='DGMTImages/"+candQuestionImageAnnot+"'>";
		}

		TableString = TableString+"</applet></td><td>";   
	}
  
	if (hint!=null){
		TableString = TableString+"<tr><td> <I> Hint :"+hint+"</I></td><td>";  
	}
	TableString = TableString+"</table></td></tr></table>"

	document.getElementById("presentQuestionNo").value=jsonString.questionNo;
	document.getElementById("questionDiv").innerHTML=TableString;
	document.getElementById("urData").style.display="block";
	reviewedQuestion();
	if (questionType!=5) {
		AutoSaveForTimer(12000);
	}
}

function matchingAns(matchAns,matchOptions)
{
	var matchingAnsSelect = "<option value=''>Select</option>";

		for (var k = 0 ;k < matchOptions.length ; k++) { 
			if(matchAns == matchOptions[k].column2)
			{
				matchingAnsSelect += "<option value="+matchOptions[k].column2+" selected>"+ matchOptions[k].column2+"</option>";
			}
			else
			{
				matchingAnsSelect += "<option value="+matchOptions[k].column2+" >"+ matchOptions[k].column2+"</option>";
			}
		}
	return matchingAnsSelect;
}

function closeSession() {
	logoutPress = true;
	document.getElementById("hidVal").value="logoutAndClose";
	document.forms[0].action="logout.dgmt";
	document.forms[0].method ="post";
	document.forms[0].submit();  
}

function populateResultHTML(jsonString){

	if (jsonString.isResultsSubmitted!=null && jsonString.isResultsSubmitted=="true"){

		$(document).ready(function() {     
			var id = $('#resultsSubmittedDiv'); 
			
			//Get the screen height and width
			var maskHeight = $(document).height();
			var maskWidth = $(window).width();

			//Set heigth and width to mask to fill up the whole screen 
			$('#mask').css({'width':maskWidth,'height':maskHeight});
			
			//transition effect                           
			$('#mask').fadeIn(300); 
			$('#mask').fadeTo("fast",0.8);    

			//Get the window height and width
			var winH = $(window).height();
			var winW = $(window).width();

			//Set the popup window to center
			$(id).css('top',  winH/2-$(id).height()/2);
			$(id).css('left', winW/2-$(id).width()/2);

			//transition effect
			$(id).fadeIn(1000); 
			$('#mask').show();
			$("#resultsSubmittedDiv").show();
		 });		
		return false;
	}
	
	$('#mask').hide();
	$("#reviewQueDiv").hide();
	$("#showReviewQuesDialogDiv").hide();
	document.getElementById("reviewDivData").style.display="none";
	document.getElementById("markUnmark").style.display="none";
	document.getElementById("logoutDiv").style.display="block";
	

	var TableString = '';
	var subjects = jsonString.subject;
	var title = jsonString.title;
	var noObjQuestions =jsonString.noObjQuestions;
	var noCorrectAnswers = jsonString.noCorrectAnswers;
	var totalMarks = jsonString.totalMarks;

	//var sections  =  jsonString.generatedQuePaper.examPaper.sections;

	var sections  =  jsonString.sections;

	//var showResults = jsonString.generatedQuePaper.examPaper.examWindow.showResults;
	var showResults = jsonString.showResults;
	
	//var passPctObj =  jsonString.generatedQuePaper.examPaper.examWindow.passPctObj;

	var passPctObj =  jsonString.passPctObj;
	var subjStatus;
	var marksPerObjQuestion;
	for (var i = 0;i <sections.length; i++) {
			if (sections[i].type=="0")
				marksPerObjQuestion = sections[i].marksPerQuestion; 
	}
	var objPercentage   =  parseInt(100 * totalMarks) / parseInt(noObjQuestions * marksPerObjQuestion);

	if (objPercentage >= passPctObj) 
		subjStatus = "Pass";	
	else 
		subjStatus = "Fail";	
	
		StopTheClock();

		TableString  = TableString  +"	<TABLE cellSpacing='2' cellPadding='0' width='100%' border='0' class='curved'>";
		TableString  = TableString  +" 	<TBODY><TR class='Title'><TD align='center' colspan='2'> Result </TD></TR>";
		TableString  = TableString  +" 	<TR><TD width='30%' noWrap><STRONG><font size='2px'>Total No. of Objective Questions</font></STRONG></TD><TD width='70%' align='center'>&nbsp;&nbsp;"+noObjQuestions+"</TD></TR>";

	if (showResults == 0) {
		TableString  = TableString  +" 	<TR><TD><STRONG><font size='2px'>Total No. of Correct Answers</font></STRONG></TD><TD align='center'>&nbsp;&nbsp;"+noCorrectAnswers+"</TD></TR>";
	} else if (showResults == 1) {
		if (subjStatus=="Fail")
		TableString  = TableString  +" 	<TR><TD><STRONG><font size='2px'>Total No. of Correct Answers</font></STRONG></TD><TD align='center'>&nbsp;&nbsp;"+noCorrectAnswers+"</TD></TR>";
	} else {
		TableString  = TableString  +" 	<TR><TD><STRONG><font size='2px'>Total No. of Correct Answers</font></STRONG></TD><TD align='center'>&nbsp;&nbsp;"+noCorrectAnswers+"</TD></TR>";
		TableString  = TableString  +" 	<TR><TD><STRONG><font size='2px'> Status  </font></STRONG></TD><TD align='center'>&nbsp;&nbsp;"+subjStatus+"</TD></TR>";
	}
		TableString  = TableString  +" 	</TBODY>   ";
		TableString  = TableString  +"	</TABLE> ";

	document.getElementById("resultDiv").innerHTML=TableString;  
	document.getElementById("urData").style.display="none";
	document.getElementById("resultHeaderDiv").style.display="block"; 
 
}

function enableDisableButtons(flag,buttonFlag){

 
	if (buttonFlag=="first"){
		document.getElementById("Next").disabled=false;
		document.getElementById("Next1").disabled=false;
		document.getElementById("Prev").disabled=true;
		document.getElementById("Prev1").disabled=true;
	} else if (buttonFlag=="last"){
		document.getElementById("Next").disabled=true;
		document.getElementById("Next1").disabled=true;
		document.getElementById("Prev").disabled=false;
		document.getElementById("Prev1").disabled=false;
	} else if (flag=="true") {
		document.getElementById("Next").disabled=true;
		document.getElementById("Next1").disabled=true;
		document.getElementById("Prev").disabled=true;
		document.getElementById("Prev1").disabled=true;
	} else {
 		document.getElementById("Next").disabled=false;
		document.getElementById("Next1").disabled=false;
		document.getElementById("Prev").disabled=false;
		document.getElementById("Prev1").disabled=false;
	}
}

function getQuestion(buttonClicked,timeOver)
{
	enableDisableButtons("true",'');
	//alert(countDwn);
	netWorkDelayCount = 10;
 	var buttonFlag = '';
	var nextQuestion = 0;
	var answer = '';  
	var presentQuestion = parseInt(document.getElementById("presentQuestionNo").value);
	var questionPaperId = parseInt(document.getElementById("questionPaperId").value);
	var questionType = document.getElementById("questionType").value;
	var count		 = document.getElementById("count").value;
	var choiceSize	 = document.getElementById("choiceSize").value;
	var matOptSize   = document.getElementById("matchingOptionsSize").value;
	var imageName = document.getElementById('imageName').value;
	document.getElementById("PrvNxtReview").style.display="block";
	document.getElementById("PrvNxtReview1").style.display="block";
 	document.getElementById("markUnmark").style.display="block";
	document.getElementById("questionDiv").style.display="block";
	document.getElementById("instr").style.display="block";

	if ((buttonClicked!="" && buttonClicked!="fromReview" && buttonClicked!="finishFinal") || (buttonClicked=="finishExam")) {
		if (questionType==1){
			if (count > 1){
				for (var ch = 0; ch<choiceSize; ch++)
				{
					if (document.getElementById('multichoice'+ch).checked){						
						answer = answer +"~"+document.getElementById('multichoice'+ch).value;
					} else {
						answer = answer +"~";
					}  
				}
				if (answer!="")  
				answer = answer.substring(1,answer.length);
			} else {
				for (var ch = 0; ch<choiceSize; ch++)
				{   
 					if (document.getElementById('singleChoice'+ch).checked){
						answer = answer +"~"+document.getElementById('singleChoice'+ch).value;
					} else {
						answer = answer +"~";   
					}
				}
				if (answer!="")
				answer = answer.substring(1,answer.length);
 			}
		} else if (questionType==2){
				for (var ch = 0; ch<choiceSize; ch++)
				{
					if (document.getElementById('trueOrFalse'+ch).checked){
						answer = answer +"~"+document.getElementById('trueOrFalse'+ch).value;
					}  else {
						answer = answer +"~";
					}
				}
				if (answer!="")
				answer = answer.substring(1,answer.length);
		} else if (questionType==3){
				
				choiceSize = document.getElementById('bracesCount').value;
 
				for (var ch = 0; ch<choiceSize; ch++)
				{ 
					answer = answer +","+ (document.getElementById('fillinblanks'+ch).value).trim();
				}
				if (answer!="")
				answer = answer.substring(1,answer.length);
 
		} else if (questionType==4){ 
			for (var m = 0; m < matOptSize; m++) 
			{	
				answer = answer +","+ (document.getElementById('mathcing'+m).value).trim();
			} 
			if (answer!="")
			answer = answer.substring(1,answer.length);
 
		} else if (questionType==5){
			answer = document.getElementById('subjective').value;
		}
		document.getElementById('answer').value =  answer;
		//alert(answer);  
	}
  	if(buttonClicked == 'next')
	{
		document.getElementById("quesNumber").value="";
		var count = document.getElementById("questionsCount").value;
		nextQuestion = presentQuestion + 1;
		
		if (parseInt(nextQuestion)==(parseInt(count)-1))
		{	
			buttonFlag = 'last'
			document.getElementById("Next").disabled="true";
			document.getElementById("Next1").disabled="true";
			document.getElementById("Prev").disabled="false";
			document.getElementById("Prev1").disabled="false";
		} else {
			document.getElementById("Prev").style.display="block";
			document.getElementById("Prev1").style.display="block";
		}

		if (nextQuestion == count) 
			return false;
	} else if(buttonClicked == 'previous'){
		
		nextQuestion = presentQuestion - 1;
		if (nextQuestion == 0)
			buttonFlag = 'first'
		if (nextQuestion==-1){
			return false;
		} else {
			document.getElementById("Next").style.display="block";
			document.getElementById("Next1").style.display="block";
		}
		document.getElementById("quesNumber").value="";

	} else if (buttonClicked!='finish' && buttonClicked != 'displayQuestion') { 
		// This block executes while viewing the review question
		nextQuestion = presentQuestion;
		if ((parseInt(nextQuestion)+1)==parseInt(count)){
			
 			document.getElementById("Prev").style.display="block";
			document.getElementById("Prev1").style.display="block";
			document.getElementById("Next").style.display="none";
			document.getElementById("Next1").style.display="none";
		} else if (nextQuestion==0){
			
 			document.getElementById("Prev").disabled="true";
			document.getElementById("Prev1").disabled="true";
			document.getElementById("Next").style.display="block";
			document.getElementById("Next1").style.display="block";
		} else {
 			document.getElementById("Prev").style.display="block";
			document.getElementById("Prev1").style.display="block";
			document.getElementById("Next").style.display="block";
			document.getElementById("Next1").style.display="block";
		}
	} else if (buttonClicked == 'displayQuestion') {   // user clicked on the review question link
			nextQuestion = presentQuestion;
			var currentQuestionNo =  document.getElementById("currentQuestionNo").value;
 			var count  = document.getElementById("questionsCount").value;
		if ((parseInt(nextQuestion)+1)==parseInt(count)){
			buttonFlag = 'last'
			document.getElementById("Prev").disabled="false";
			document.getElementById("Prev1").disabled="false";
			document.getElementById("Next").disabled="true";
			document.getElementById("Next1").disabled="true";

		} else if (nextQuestion==0){
			buttonFlag = 'first'
 			document.getElementById("Prev").disabled="true";
			document.getElementById("Prev1").disabled="true";
			document.getElementById("Next").style.display="block";
			document.getElementById("Next1").style.display="block";
		} else {
			document.getElementById("Prev").style.display="block";
			document.getElementById("Prev1").style.display="block";
			document.getElementById("Next").style.display="block";
			document.getElementById("Next1").style.display="block";
		}
	}

	if (buttonClicked=='finish') {

		document.getElementById("nxtQues").value = nextQuestion;
		document.getElementById("answer").value = answer;
 	
		if (document.getElementById("reviewQuesCount").value!=0) {
			reviewConfirmWindow();
			return false;
		}
		else  {
			confirmationWindow();
			return false;
		}
		return false;
	} if (buttonClicked=='finishFinal') {
 		document.getElementById("nxtQues").value = nextQuestion;
 		answer = document.getElementById("answer").value;
 		finishExam();
		return false;
	} 
	if (timeOver=='true'){
		closeInstructionsPopUp();
		nextQuestion = presentQuestion;
		document.getElementById("nxtQues").value = nextQuestion;
		document.getElementById("actionPath").value = 'finishExam';
		//document.getElementById("timerLabel").style.display='none'
		//document.getElementById("cd").innerHTML = 'Time Over';
		document.getElementById("cd").innerHTML = hours+' : '+mins+' : '+secs;
		finishExam();
		return false;
	} 
	if(imageName != "" && imageName != null)
	{
		//document.questionPaperFrom.method = 'post';

		document.questionPaperFrom.answer.value = answer;
		var subjTimeSlotId = document.getElementById("subTimeSlotId").value;
		var subjectCodeId = document.getElementById("subjectCodeId").value;
// 				 alert(imageName);
		answer = answer.replace(/\r\n/g,"@@@@").replace(/\n/g,"@@@@");//DGMT on mobile (3468)
//alert(answer);
		var url  = "";
		if ( (buttonClicked!="toReview") || (buttonClicked!="displayQuestion") )
		//StopTheClock();
		//startNetWorkDelayTimer();
		newTimer.pause();
		YAHOO.util.Connect.setForm('questionPaperFrom', false); 
		if (buttonClicked=="displayQuestion"){
			url ='saveImage.jsp?questionNo='+nextQuestion+"&action="+buttonClicked+"&questionPaperId="+questionPaperId+"&subjTimeSlotId="+subjTimeSlotId+"&subjectCodeId="+subjectCodeId+"&currentQuestionNo="+currentQuestionNo+"&message=maxTime&timeLeftInSecs="+countDwn+"&imageName="+imageName+"&answer="+answer; 
		} else {
			url ='saveImage.jsp?questionNo='+nextQuestion+"&action="+buttonClicked+"&questionPaperId="+questionPaperId+"&subjTimeSlotId="+subjTimeSlotId+"&subjectCodeId="+subjectCodeId+"&message=maxTime&timeLeftInSecs="+countDwn+"&imageName="+imageName+"&answer="+answer; 
		}
	//	alert(url)
		document.applets['annotator'].submit(url);
		
	}
	else
	{
			var AjaxObject = {
					handleSuccess:function(o){
						this.processResult(o);
					},
					handleFailure:function(o){
						errorMsgDialog();
					},
					processResult:function(o){
						var msg = o.responseText;
						if(msg == "SessionExpired")
						{					
							//sessionExpired();
							sessionExpMsgDialog();
							return false;
						}
						  var jsonString; 

						   if (buttonClicked!="toReview"){
							  jsonString = eval ('('+o.responseText+')'); 
							  document.getElementById('imageName').value = "";
							  populateHTML(jsonString);  
							 // StartTheTimer();
							// stopNetWorkDelayTimer();
							 newTimer.resume();
							 enableDisableButtons("false",buttonFlag);
						  } else {
							// StartTheTimer();
							newTimer.resume();
							reviewedQuestion();
							 return false; 
						  }

					},
					startRequest:function() {	
						document.questionPaperFrom.answer.value = answer;
						var subjTimeSlotId = document.getElementById("subTimeSlotId").value;
						var subjectCodeId = document.getElementById("subjectCodeId").value;
		// 				 alert(imageName);
						var url  = "";
						if ( (buttonClicked!="toReview") || (buttonClicked!="displayQuestion") )
						//StopTheClock();
						//startNetWorkDelayTimer();
						newTimer.pause();
						YAHOO.util.Connect.setForm('questionPaperFrom', false); 
						if (buttonClicked=="displayQuestion"){
							url ='getCandidateQuestion.dgmt?questionNo='+nextQuestion+"&action="+buttonClicked+"&questionPaperId="+questionPaperId+"&subjTimeSlotId="+subjTimeSlotId+"&subjectCodeId="+subjectCodeId+"&currentQuestionNo="+currentQuestionNo+"&message=minTime&timeLeftInSecs="+countDwn+"&imageName="+imageName; 
						} else {
							url ='getCandidateQuestion.dgmt?questionNo='+nextQuestion+"&action="+buttonClicked+"&questionPaperId="+questionPaperId+"&subjTimeSlotId="+subjTimeSlotId+"&subjectCodeId="+subjectCodeId+"&message=minTime&timeLeftInSecs="+countDwn+"&imageName="+imageName; 
						}
						
						YAHOO.util.Connect.asyncRequest('POST', url, callback);  
					}
				};

				// Define the callback object for success and failure handlers as well as object scope.
				var callback ={
					success:AjaxObject.handleSuccess,
					failure:AjaxObject.handleFailure,
					scope: AjaxObject
				};

				// Start the transaction.
				AjaxObject.startRequest(); 
	}
	
}


function updateReviewList()
{
	var reviewChecked = document.getElementById("markForReview").checked;
	var questionPaperId = document.getElementById("questionPaperId").value;
	
	var presentQuestion = parseInt(document.getElementById("presentQuestionNo").value);
	var AjaxObject = {
			handleSuccess:function(o){
				// This member handles the success response and passes the response object o to AjaxObject's processResult member.
				this.processResult(o);
			},
			handleFailure:function(o){
				// Failure handler
			},
			processResult:function(o){
				var msg = o.responseText;
				if(msg == "SessionExpired")
				{					
					//sessionExpired();
					sessionExpMsgDialog();
					return false;
				}
				 var jsonString = o.responseText; 
 				 document.getElementById("reviewQuesCount").value = jsonString;
				 //alert(jsonString);
 				 reviewedQuestion();
				  
			},
			startRequest:function() {	
				var url = "reviewQuestion.dgmt?questionNo="+presentQuestion+"&addRemoveFlag="+reviewChecked+"&questionPaperId="+questionPaperId;
				YAHOO.util.Connect.asyncRequest('POST', url, callback);  
			}
		}; 

		// Define the callback object for success and failure handlers as well as object scope.
		var callback ={
			success:AjaxObject.handleSuccess, 
			failure:AjaxObject.handleFailure,
			scope: AjaxObject
		};

		// Start the transaction.
		AjaxObject.startRequest(); 	
}
function reviewedQuestion()
{
	var paperId = document.getElementById("questionPaperId").value;
 
			var AjaxObject = {
			handleSuccess:function(o){
				// This member handles the success response and passes the response object o to AjaxObject's processResult member.
				this.processResult(o);
			},
			handleFailure:function(o){
				// Failure handler
			},
			processResult:function(o){
			  var msg = o.responseText;
			  if(msg == "SessionExpired")
			  {					
					//sessionExpired();
					sessionExpMsgDialog();
					return false;
			  }
			  var jsonString; 
			  var str = "None of the questions are marked for review";
 			  if (o.responseText!=str) {
					jsonString = eval ('('+o.responseText+')');
					if (jsonString.keys!=null) {
						populateNewReviewDiv(jsonString); 
					} else  {
						populateNewReviewDiv(jsonString); 
						document.getElementById("questionTextDiv1").innerHTML ='';
					}
				  } else {
 					  populateReviewDiv(jsonString); 
 				  }   
			},
			startRequest:function() {	  
			 	YAHOO.util.Connect.setForm('questionPaperFrom', false); 
				var url = "showReviewQuestionsList.dgmt?paperId="+paperId;
				YAHOO.util.Connect.asyncRequest('POST', url, callback);  
			} 
		};    
		// Define the callback object for success and failure handlers as well as object scope.
		var callback ={
			success:AjaxObject.handleSuccess, 
			failure:AjaxObject.handleFailure,
			scope: AjaxObject
		};
		// Start the transaction.  
		AjaxObject.startRequest(); 	
}
function showQuestion(presentQuestionNo){
	document.getElementById("currentQuestionNo").value =  document.getElementById("presentQuestionNo").value;
	document.getElementById("presentQuestionNo").value = presentQuestionNo;
	getQuestion('displayQuestion','');
}
function showReviewQuesDialog() {
	

	 $(document).ready(function() {     
		var id = $('#showReviewQuesDialogDiv'); 
		
		//Get the screen height and width
		var maskHeight = $(document).height();
		var maskWidth = $(window).width();

		//Set heigth and width to mask to fill up the whole screen
		$('#mask').css({'width':maskWidth,'height':maskHeight});
		
		//transition effect                           
		$('#mask').fadeIn(300); 
		$('#mask').fadeTo("fast",0.8);    

		//Get the window height and width
		var winH = $(window).height();
		var winW = $(window).width();

		//Set the popup window to center
		$(id).css('top',  winH/2-$(id).height()/2);
		$(id).css('left', winW/2-$(id).width()/2);

		//transition effect
		$(id).fadeIn(1000); 
		$('#mask').show();
		$("#showReviewQuesDialogDiv").show();
	 });		
	
}
function showReviewQuesDialogDiv() {


if (document.getElementById("tdapplet")!=null)
		document.getElementById("tdapplet").style.display="block";

	$('#mask').hide();
	$("#showReviewQuesDialogDiv").hide();
	enableDisableButtons("false",'');
}

String.prototype.trim = function() {
a = this.replace(/^\s+/, '');
return a.replace(/\s+$/, '');
};


/**
 * Array.prototype.[method name] allows you to define/overwrite an objects method
 * needle is the item you are searching for
 * this is a special variable that refers to "this" instance of an Array.
 * returns true if needle is in the array, and false otherwise
 */
Array.prototype.contains = function ( needle ) {
   for (i in this) {
       if (this[i] == needle) return true;
   }
   return false;
}
function showQuestionText(id){
	document.getElementById("questionTextDiv1").style.display = "block";
	document.getElementById("qText"+id).style.display = "block";
	var qTextDes  = document.getElementById("qText"+id).innerHTML;
	if(qTextDes.length > 120)
	qTextDes =  qTextDes.substring(0,120)+"...";
	document.getElementById("questionTextDiv1").innerHTML = "<font size='2'>"+qTextDes.ReplaceAll('{}','_____________')+"</font>";
}
function clearQuestionText(){
 	document.getElementById("questionTextDiv1").innerHTML = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
}
 
function populateNewReviewDiv(jsonString) { 

	var reviewKeys = new Array();
	var htmlString ="";
	var qpDetails = 	jsonString.genQPaperDtls; 
	var keys = 	jsonString.keys;
	var question ;  
	var answer;
	//alert(keys)
 	/* code to show only reviewed questions */

	if (jsonString.reviewQuestionsCount!=null)
		document.getElementById("reviewQuesCount").value = jsonString.reviewQuestionsCount;
	else 
		document.getElementById("reviewQuesCount").value = 0;

	if (keys!=null) {
		reviewKeys = keys.split(",");
		reviewKeys.sort(sortit);
		var index;
//		htmlString += "<table border='0' cellspacing='0' cellpadding='0'><tr><td>Review Questions</td></tr></table>"
		htmlString += "<table><tr><td><table border='0' cellpadding='0' cellspacing='1'>"
 		htmlString += "<tr>"; 

		for(var i = 0; i<reviewKeys.length; i++){
				index = reviewKeys[i];
				question = qpDetails[index].question;
			//	answer = qpDetails[index].answer; 

				var subDiv = document.createElement('div');
				subDiv.setAttribute('id', 'qText'+reviewKeys[i]);
				subDiv.innerHTML =  question.questionText; 
				document.getElementById('questionTextDiv').appendChild(subDiv);
				document.getElementById("questionTextDiv").style.display = "none";
				htmlString+="<td><a href='#' onclick=showQuestion("+reviewKeys[i]+") onMouseOver=showQuestionText("+reviewKeys[i]+") onMouseOut=clearQuestionText() ><font size='2'>"+(parseInt(reviewKeys[i])+1)+"</font></a></td>";
		}
			htmlString+="</tr></table>";
			//</td></tr></table>";
			document.getElementById("reviewListDiv").innerHTML=htmlString;
			document.getElementById("reviewDivData").style.display="block"; 

	}
	 if (keys==null)
		// document.getElementById("reviewDivData").style.display="none";
		document.getElementById("reviewListDiv").innerHTML=htmlString;
		
}

function populateUnAnsDiv(jsonString) {  

 
	var unAnsKeys = new Array();
	var htmlString ="";
	var qpDetails = 	jsonString.genQPaperDtls;
	var keys = 	jsonString.keys;
	var question ;  
	var answer;
 	/* code to show only reviewed questions */
	if (keys!=null) {
		unAnsKeys = keys.split(",");
		unAnsKeys.sort(sortit);
		var index;
		htmlString += "<table border='0' cellspacing='0' cellpadding='0'><tr><td>Un answered questions</td></tr></table>"
		htmlString += "<table><tr><td><table border='0' cellpadding='0' cellspacing='5'>"
		htmlString += "<tr>"; 
		//alert(qpDetails);
		 for(var i = 0; i<unAnsKeys.length; i++){
				index = unAnsKeys[i];
				question = qpDetails[index].question;  
				answer = qpDetails[index].answer; 
				var subDiv = document.createElement('div');
				subDiv.setAttribute('id', 'qText'+reviewKeys[i]);
				subDiv.innerHTML =  question.questionText; 
				document.getElementById('questionTextDiv').appendChild(subDiv);
				document.getElementById("questionTextDiv").style.display = "none";
				htmlString+="<td><a href='#' onclick=showQuestion("+unAnsKeys[i]+") onMouseOver=showQuestionText("+reviewKeys[i]+") onMouseOut=clearQuestionText() >"+(parseInt(unAnsKeys[i])+1)+"</a></td>"; 
		} 
			htmlString+="</tr></table></td></tr></table>";
	}

 document.getElementById("unAnsListDiv").innerHTML=htmlString;

}




function populateReviewDiv(jsonString)
{
	$('#mask').hide();
	$("#reviewQueDiv").hide();

	document.getElementById("PrvNxtReview").style.display="none";
	document.getElementById("PrvNxtReview1").style.display="none";
//	document.getElementById("finishbtn").style.display="none";
	document.getElementById("markUnmark").style.display="none";

	document.getElementById("instr").style.display="none";


	var reviewKeys = new Array();
	var htmlString ="";
	var qpDetails = 	jsonString.genQPaperDtls;
	var keys = 	jsonString.keys;
	//alert(keys);
	var question ;  
	var answer;
	htmlString+="<table border='1'>";
	htmlString+="<th> Q No      </th>";
	htmlString+="<th> Marked    </th>";
	htmlString+="<th> Answered </th>";   
	htmlString+="<th> Question </th>";  

 
	/* code to show only reviewed questions */
	if (keys!=null) {
	reviewKeys = keys.split(",");
	reviewKeys.sort();
	var index;
		for(var i = 0; i<reviewKeys.length; i++){
			//alert(reviewKeys[i]);
			index = reviewKeys[i];
			question = qpDetails[index].question;  
			answer = qpDetails[index].answer; 
			htmlString+="<tr><td><a href='#' onclick=showQuestion("+reviewKeys[i]+")>"+(parseInt(reviewKeys[i])+1)+"</a></td>";
 			htmlString+="<td><img src='common/images/accept_icon.gif'/></td>";
			if (answer!="" && answer!=null)
			answer = answer.ReplaceAll(',','');

			if (answer!="" && answer!=null){
					htmlString+="<td><img src='common/images/accept_icon.gif'/></td>";
			}else {
				htmlString+="<td>&nbsp</td>";
			}
				htmlString+="<td>"+question.questionText+"</td>";
				htmlString+="</tr>";
		}
		htmlString+="</table>";
	}



	for(var i=0; i < jsonString.length;i++)
	{
		htmlString+=jsonString[i]+"<br>"
	//	alert(htmlString);
	}
	document.getElementById("reviewQuestionDiv").innerHTML=htmlString;

	document.getElementById("questionDiv").style.display="none";
	document.getElementById("reviewData").style.display="block";
	document.getElementById("count").value = qpDetails.length;
	//alert("count : "+document.getElementById("count").value)

}

String.prototype.ReplaceAll = function(stringToFind,stringToReplace){
	var temp = this;
	var index = temp.indexOf(stringToFind);
		while(index != -1){
			temp = temp.replace(stringToFind,stringToReplace);
			index = temp.indexOf(stringToFind);
		}
		return temp;
}

function isNumberKey(evt, id) {

		document.oncontextmenu = new Function("return false"); // It will not allow mouse right click also.
		var charCode = (evt.which) ? evt.which : event.keyCode
		return true;
}

function sortit(a,b){
	return(a-b)
}

newTimer.addCallback(StartTheTimer).start();

function InitializeTimer(elapsedTimeInSec,message)
{
	hours = 0
	mins = 0
	secs = 0;

    // Set the length of the timer, in seconds
	//var timerVal = 	document.getElementById("timerInSeconds").value;
	//alert(message +''+elapsedTimeInSec ) ;

      if (message=="maxTime"){
	  	countDwn = elapsedTimeInSec;          
	  } else {
   		//countDwn = parseInt(timerVal*3600) - elapsedTimeInSec;
		countDwn = elapsedTimeInSec;
		//countDwn = 120; 
		//countDwn = 2; //parseInt(timerVal*3600) - elapsedTimeInSec;
	  }
//    StopTheClock()
	StartTheTimer();
	//newTimer.resume();
} 

function StopTheClock()
{
    /*if(timerRunning)
        clearTimeout(timerID)
    timerRunning = false;  */
	 newTimer.clearCallbacks();
	 return;
} 

function StartTheTimer()
{
	
    if (countDwn==0)
    {
        StopTheClock();
		secs = "00"; 
		document.getElementById("cd").innerHTML = hours+' : '+mins+' : '+secs;
		getQuestion('finishExam','true');

    } else
    {
		  
		hours = Math.floor(countDwn / (60 * 60));
		
		var divisor_for_minutes = countDwn % (60 * 60);
		mins = Math.floor(divisor_for_minutes / 60);
		if (mins < 10) 
		mins = '0'+mins;

		var divisor_for_seconds = divisor_for_minutes % 60;
		secs = Math.ceil(divisor_for_seconds);
		if (secs < 10) 
		secs = '0'+secs; 

		document.getElementById("cd").innerHTML = hours+' : '+mins+' : '+secs;

        countDwn = countDwn - 1
      //  timerRunning = true
      //  timerID = self.setTimeout("StartTheTimer()", delay)
    }
}

function openInstructions(){


if (document.getElementById("tdapplet")!=null)
	document.getElementById("tdapplet").style.display="none";

	document.getElementById("instructionsDiv").style.dispaly="block";
	$(document).ready(function() {
		 
		var id = $('#instructionsDiv'); 
		
		//Get the screen height and width
		var maskHeight = $(document).height();
		var maskWidth = $(window).width();

		//Set heigth and width to mask to fill up the whole screen
		$('#mask').css({'width':maskWidth,'height':maskHeight});
		
		//transition effect                           
		$('#mask').fadeIn(300); 
		$('#mask').fadeTo("fast",0.8);    

		//Get the window height and width
		var winH = $(window).height();
		var winW = $(window).width();

		//Set the popup window to center
		$(id).css('top',  winH/2-$(id).height()/2);
		$(id).css('left', winW/2-$(id).width()/2);

		//transition effect
		$(id).fadeIn(1000); 
		$('#mask').show();
		$("#instructionsDiv").show();
	 });		
	

} 

window.history.forward(1); 

document.attachEvent("onkeydown", my_onkeydown_handler); 

function my_onkeydown_handler() 
{ 
	switch (event.keyCode) 
	{ 

		case 116 : // 'F5' 
		event.returnValue = false; 
		event.keyCode = 0; 
		window.status = "We have disabled F5"; 
		break; 

		case 18 : // 'Alt' Key
		event.returnValue = false; 
		event.keyCode = 0; 
		break; 

		case 9 : // 'Tab'  Key
		event.returnValue = false; 
		event.keyCode = 0; 
		break; 

		case 27 : // 'Esc'  Key
		event.returnValue = false; 
		event.keyCode = 0; 
		break; 

	} 
} 

function reviewConfirmWindow(){

	   if (document.getElementById("tdapplet")!=null)
		document.getElementById("tdapplet").style.display="none";

	   $(document).ready(function() {     
		var id = $('#reviewQueDiv'); 
		
		//Get the screen height and width
		var maskHeight = $(document).height();
		var maskWidth = $(window).width();

		//Set heigth and width to mask to fill up the whole screen
		$('#mask').css({'width':maskWidth,'height':maskHeight});
		
		//transition effect                           
		$('#mask').fadeIn(300); 
		$('#mask').fadeTo("fast",0.8);    

		//Get the window height and width
		var winH = $(window).height();
		var winW = $(window).width();

		//Set the popup window to center
		$(id).css('top',  winH/2-$(id).height()/2);
		$(id).css('left', winW/2-$(id).width()/2);

		//transition effect
		$(id).fadeIn(1000); 
		//$("#informationDiv").show();
		//$('#mask').show();
		$('#mask').show();
		$("#reviewQueDiv").show();
	 });		
	
}

function confirmationWindow(){

	if (document.getElementById("tdapplet")!=null)
		document.getElementById("tdapplet").style.display="none";

	   $(document).ready(function() {     
		var id = $('#informationDiv'); 
		//Get the screen height and width
		var maskHeight = $(document).height();
		var maskWidth = $(window).width();

		//Set heigth and width to mask to fill up the whole screen
		$('#mask').css({'width':maskWidth,'height':maskHeight});
		
		//transition effect                           
		$('#mask').fadeIn(300); 
		$('#mask').fadeTo("fast",0.8);    

		//Get the window height and width
		var winH = $(window).height();
		var winW = $(window).width();

		//Set the popup window to center
		$(id).css('top',  winH/2-$(id).height()/2);
		$(id).css('left', winW/2-$(id).width()/2);

		//transition effect
		$(id).fadeIn(1000); 
	//	$("#informationDiv").show();
	//	$('#mask').show();
		$('#mask').show();
		$("#informationDiv").show();
	 });		
	
}

function closePopUp(){

	if (document.getElementById("tdapplet")!=null)
		document.getElementById("tdapplet").style.display="block";


	$('#mask').hide();
	$("#informationDiv").hide();
	var presentQuestion = parseInt(document.getElementById("presentQuestionNo").value);
	var count  = document.getElementById("questionsCount").value;
	if (parseInt(presentQuestion)==0){
		enableDisableButtons("","first");	
	} else	if (parseInt(count)==parseInt(presentQuestion)+1){
		enableDisableButtons("","last");
	} else {
		enableDisableButtons("","");
	}
	return false;
}
function closeInstructionsPopUp(){

if (document.getElementById("tdapplet")!=null)
	document.getElementById("tdapplet").style.display="block";


	$('#mask').hide();
	$("#instructionsDiv").hide();

	var presentQuestion = parseInt(document.getElementById("presentQuestionNo").value);
	var count  = document.getElementById("questionsCount").value;

	if (parseInt(presentQuestion)==0){
		enableDisableButtons("","first");	}
	if (parseInt(presentQuestion)==parseInt(count)){
		enableDisableButtons("","last");
	}

}
function closeReviewPopUp(flag){
	$('#mask').hide();
	$("#reviewQueDiv").hide();
	if (flag=="no")
	confirmationWindow();

	if(flag=="yes")
	{
		if (document.getElementById("tdapplet")!=null)
		{
			document.getElementById("tdapplet").style.display="block";
		}
    }

	enableDisableButtons("false",'');
	var presentQuestion = parseInt(document.getElementById("presentQuestionNo").value);
	var count  = document.getElementById("questionsCount").value;

	if (parseInt(presentQuestion)==0){
		enableDisableButtons("","first");	}
	if (parseInt(presentQuestion)==parseInt(count)){
		enableDisableButtons("","last");
	}
}

function finishExam(){
	
var imageName = document.getElementById('imageName').value;
var subjTimeSlotId = document.getElementById("subTimeSlotId").value;

var  nextQuestion = document.getElementById("nxtQues").value;
var  answer = document.getElementById("answer").value;
var  questionPaperId = document.getElementById("questionPaperId").value;
var  actionPath = document.getElementById("actionPath").value;
document.getElementById("instr").style.display="none";
//alert("answer "+answer +" questionNo =  "+nextQuestion);
var  action;

/*if (actionPath=="fromReview") 
	action = "fromReview";     
else if (actionPath=="finishExam") 
	action = "finishExam";     
else 
	action = "finish";*/

	action = "finishExam";  
	var url  = "";

	if(imageName != "" && imageName != null)
	{
		
		//document.questionPaperFrom.method = 'post';
		var  presentQuestionNo = document.getElementById("presentQuestionNo").value;
		
		
		document.questionPaperFrom.answer.value = answer;
		var subjTimeSlotId = document.getElementById("subTimeSlotId").value;
		var subjectCodeId = document.getElementById("subjectCodeId").value;
		answer = answer.replace(/\r\n/g,"@@@@").replace(/\n/g,"@@@@");//DGMT on mobile (3468)
		newTimer.pause();
		YAHOO.util.Connect.setForm('questionPaperFrom', false); 
		url ='saveImage.jsp?questionNo='+presentQuestionNo+"&action="+action+"&questionPaperId="+questionPaperId+"&subjTimeSlotId="+subjTimeSlotId+"&subjectCodeId="+subjectCodeId+"&message=maxTime&timeLeftInSecs="+countDwn+"&imageName="+imageName+"&answer="+answer; 
		document.applets['annotator'].submit(url);
	}
	else
	{

		 var AjaxObject = {
			handleSuccess:function(o){
				// This member handles the success response and passes the response object o to AjaxObject's processResult member.
				this.processResult(o);
			},
			handleFailure:function(o){
				errorMsgDialog();
			},
			processResult:function(o){ 
				var msg = o.responseText;
				if(msg == "SessionExpired")
				{					
					//sessionExpired();
					sessionExpMsgDialog();
					return false;
				} 
				  //alert(o.responseText);
				  $('#mask').hide();
				  $("#informationDiv").hide(); 
				 
				  var jsonString = eval ('('+o.responseText+')');
				  if (countDwn==0) {
					  //alert("Examination time is over!!, answers are submitted, Click on ' Ok ' button to see the result page ");
					 alert(DGMT.Messages.CANDEXAMINATION.ExmTimeIsOver);	
					  populateResultHTML(jsonString);  
				  } else {
					  populateResultHTML(jsonString);  
				  }
				
			},
			startRequest:function() {	
			 document.questionPaperFrom.answer.value = answer;

			 YAHOO.util.Connect.setForm('questionPaperFrom', false); 
			 var subjTimeSlotId = document.getElementById("subTimeSlotId").value;
			 var subjectCodeId = document.getElementById("subjectCodeId").value;
			 var url ='getCandidateQuestion.dgmt?questionNo='+nextQuestion+"&action="+action+"&questionPaperId="+questionPaperId+"&subjTimeSlotId="+subjTimeSlotId+"&subjectCodeId="+subjectCodeId+"&message=finish";

				YAHOO.util.Connect.asyncRequest('POST', url, callback);  
			}
		};

		// Define the callback object for success and failure handlers as well as object scope.
		var callback ={
			success:AjaxObject.handleSuccess,
			failure:AjaxObject.handleFailure,
			scope: AjaxObject
		};

		// Start the transaction.
		AjaxObject.startRequest(); 	
	}
}
function finishExamination() {
	document.getElementById("actionPath").value = "fromReview";
	confirmationWindow();
}

function eraseAnswer() {

var questionType = 	document.getElementById("questionType").value ;
var choiceSize	 = document.getElementById("choiceSize").value;
var count =  	document.getElementById("count").value;
var matOptSize = document.getElementById("matchingOptionsSize").value;
var noOfBraces = document.getElementById("bracesCount").value;

var choiceArray = new Array();
choiceArray[0] = "multichoice";
choiceArray[1] = "singleChoice";
choiceArray[2] = "trueOrFalse";
choiceArray[3] = "fillinblanks";
choiceArray[4] = "mathcing";
choiceArray[5] = "subjective";
var ch;
		if (questionType==1){
			if (count > 1){
				for (ch = 0; ch<choiceSize; ch++)
					document.getElementById(choiceArray[parseInt(questionType)-1]+ch).checked=0;
			} else {
				for (ch = 0; ch<choiceSize; ch++)
					document.getElementById(choiceArray[parseInt(questionType)]+ch).checked=0;
 			}
		} else if (questionType==2){ 
			for (ch = 0; ch<choiceSize; ch++)
				document.getElementById(choiceArray[parseInt(questionType)]+ch).checked=0;
		} else if (questionType==3){ 
			for (ch = 0; ch<noOfBraces; ch++)
				document.getElementById(choiceArray[parseInt(questionType)]+ch).value="";
		} else if (questionType==5){ 
				document.getElementById(choiceArray[parseInt(questionType)]).value="";
		}else if (questionType==4){ 
			for (var m = 0; m < matOptSize; m++) 
				document.getElementById(choiceArray[parseInt(questionType)]+m).value="";
		}
}

window.onbeforeunload = confirmExit;
window.onunload = dontClose;

function confirmExit(event)
{

		if(document.getElementById("resultHeaderDiv")==null) {
			if (logoutPress != true)
			{
					  return "Closing the window will Submit the results?";   
			} 
		}
} 
function dontClose() 
{  
			if(document.getElementById("resultHeaderDiv")==null) {
				if (logoutPress != true)
				getQuestion('finishFinal','');  
				return false;
			}
}

function showUnansweredList()
{
	var AjaxObject = {
			handleSuccess:function(o){
				// This member handles the success response and passes the response object o to AjaxObject's processResult member.
				this.processResult(o);
			},
			handleFailure:function(o){
				// Failure handler
			},
			processResult:function(o){
				var msg = o.responseText;
				if(msg == "SessionExpired")
				{					
					//sessionExpired();
					sessionExpMsgDialog();
					return false;
				}
				  var jsonString = o.responseText; 
 				  document.getElementById("reviewQuesCount").value = jsonString;
				 // alert(jsonString);
				 reviewedQuestion();
				  
			},
			startRequest:function() {	
				
			//	alert("  answer :  "+answer);  

				var url = "showUnAnsQuestions.dgmt?questionNo="+presentQuestion+"&addRemoveFlag="+reviewChecked;
				YAHOO.util.Connect.asyncRequest('POST', url, callback);  
			}
		}; 

		// Define the callback object for success and failure handlers as well as object scope.
		var callback ={
			success:AjaxObject.handleSuccess, 
			failure:AjaxObject.handleFailure,
			scope: AjaxObject
		};

		// Start the transaction.
		AjaxObject.startRequest(); 	
}

function viewQuesNumber() {

	var quesNumber =  document.getElementById("quesNumber").value;
	var count  = document.getElementById("questionsCount").value;
	var flag = '';
	if (quesNumber!="" && quesNumber!=0 && !isNaN(parseFloat(quesNumber)) && parseInt(quesNumber)<=parseInt(count)) {
		showQuestion(parseInt(quesNumber)-1);
	}

}
function onlyNumbers(evt)
{
	var e = event || evt; // for trans-browser compatibility
	var charCode = e.which || e.keyCode;
	var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
	if (keyCode == 13) {
		return false;
	} 
	if (charCode > 31 && (charCode < 48 || charCode > 57))
		return false;
	
 	return true;

}
function stopNetWorkDelayTimer() {

	 if(timerRunning)
        clearTimeout(timerID)
	    timerRunning = false;  

}
function startNetWorkDelayTimer()
{ 
	if (netWorkDelayCount==0){
		//alert("Network Problem. Please contact the proctor");
		alert(DGMT.Messages.CANDEXAMINATION.ContactProctor);
		stopNetWorkDelayTimer();
	} else {
		netWorkDelayCount = netWorkDelayCount -1;
		 timerRunning = true;  
		timerID = self.setTimeout("startNetWorkDelayTimer()", delay)
	}
}

function errorMsgDialog() {
	
logoutPress = true;

	 $(document).ready(function() {     
		var id = $('#errorDiv'); 
		
		//Get the screen height and width
		var maskHeight = $(document).height();
		var maskWidth = $(window).width();

		//Set heigth and width to mask to fill up the whole screen
		$('#mask').css({'width':maskWidth,'height':maskHeight});
		
		//transition effect                           
		$('#mask').fadeIn(300); 
		$('#mask').fadeTo("fast",0.8);    

		//Get the window height and width
		var winH = $(window).height();
		var winW = $(window).width();

		//Set the popup window to center
		$(id).css('top',  winH/2-$(id).height()/2);
		$(id).css('left', winW/2-$(id).width()/2);

		//transition effect
		$(id).fadeIn(1000); 
		$('#mask').show();
		$("#errorDiv").show();
	 });		
	
}

function sessionExpMsgDialog() {
	
logoutPress = true;

	 $(document).ready(function() {     
		var id = $('#sessionExpDiv'); 
		
		//Get the screen height and width
		var maskHeight = $(document).height();
		var maskWidth = $(window).width();

		//Set heigth and width to mask to fill up the whole screen
		$('#mask').css({'width':maskWidth,'height':maskHeight});
		
		//transition effect                           
		$('#mask').fadeIn(300); 
		$('#mask').fadeTo("fast",0.8);    

		//Get the window height and width
		var winH = $(window).height();
		var winW = $(window).width();

		//Set the popup window to center
		$(id).css('top',  winH/2-$(id).height()/2);
		$(id).css('left', winW/2-$(id).width()/2);

		//transition effect
		$(id).fadeIn(1000); 
		$('#mask').show();
		$("#sessionExpDiv").show();
	 });		
	
}


function handleEnter (field, event) {
		var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
		if (keyCode == 13) {
			return false;
		} 
		else 
		return true;
	}  

// auto save code start 

  var mnAutoSaveMilliSeconds=0;
  var mnAutoSaveMilliSecondsExp=0;
  var mnAutoSaveInterval=30000;

  function AutoSaveInit(nMilliSeconds,questionNo)
  {
      try
     {
       var nMinutes=0;
       AutoSaveClearTimeOuts();

       nMinutes = ((nMilliSeconds / 1000) / 60); 
       mnAutoSaveMilliSeconds = nMilliSeconds; 
       mnAutoSaveMilliSecondsExp=0;

       oTimeOut = window.setTimeout("AutoSaveSubmit("+questionNo+","+nMilliSeconds+")",nMilliSeconds);
       oInterval = window.setInterval("AutoSaveCountDown()",mnAutoSaveInterval);
       document.getElementById("divAutoSave").innerHTML = "<b>Auto Save In " + nMinutes * 60 + " Seconds </b>";
		
      }
      catch (exception) 
      { 
        if (exception.description == null) { //alert("AutoSaveInit Error: " + exception.message); 
		}  
        else {  
			//alert("AutoSaveInit Error: " + exception.description); 
		}
      }
  }

  function AutoSaveCountDown()
  {

    var nMinutesLeft=0;
    var nMilliSecondsLeft=0;

    mnAutoSaveMilliSecondsExp =  mnAutoSaveMilliSecondsExp + mnAutoSaveInterval;

    if ( mnAutoSaveMilliSeconds > mnAutoSaveMilliSecondsExp)
    {
      nMilliSecondsLeft = mnAutoSaveMilliSeconds - mnAutoSaveMilliSecondsExp;
      nMinutes= AutoSaveRoundNumber(((nMilliSecondsLeft / 1000) / 60),2); 
      document.getElementById("divAutoSave").innerHTML = "<b>Auto Save In " + nMinutes/60 + " Seconds </b>";
    }

  }

  function AutoSaveBeforeSubmit()
  {
     document.getElementById("divAutoSave").innerHTML = '<b>Saving data...Please wait.</b>';
     return true;
  }


  function AutoSaveClearTimeOuts()
  {
    try
    {
      window.clearInterval(oInterval);
      window.clearTimeout(oTimeOut);
    }
    catch (exception) { }

  }

  function AutoSaveSubmit(questionNo,nMilliSeconds)
  {
     try
    {
      AutoSaveClearTimeOuts();
      AutoSaveBeforeSubmit();
	
      /*
         Call the form submittal code in your main page.
      */
      SubmitFormToBeSaved(questionNo,nMilliSeconds);
    
     }
    catch (exception) {}
  }

  function AutoSaveRoundNumber(number,X)
  {
	  
    var number2;
    var TmpNum;

     X=(!X ? 1:X);
	
     number2 = Math.round(number*Math.pow(10,X))/Math.pow(10,X);
     TmpNum = "" + number2;
     var TmpArray = TmpNum.split(".");
     if (TmpArray.length <2) { number2 = number2 + ".0"; }
	 
     return number2;
  }
  function  SubmitFormToBeSaved(questionNo,nMilliSeconds)
  {
			var AjaxObject = {
			handleSuccess:function(o){
				// This member handles the success response and passes the response object o to AjaxObject's processResult member.
				this.processResult(o);
			},
			handleFailure:function(o){
				// Failure handler
			},
			processResult:function(o){
				var msg = o.responseText;
				if(msg == "SessionExpired")
				{					
					//sessionExpired();
					sessionExpMsgDialog();
					return false;
				}
				  var jsonString = o.responseText; 
				  document.getElementById("divAutoSave").innerHTML = '<b>Auto Saved...in '+nMilliSeconds * 60 +' Seconds </b>';
				  AutoSaveInit(nMilliSeconds,questionNo);
			},
			startRequest:function() {	
 			 
			 document.questionPaperFrom.answer.value = document.getElementById("subjective").value;
 			 
			 YAHOO.util.Connect.setForm('questionPaperFrom', false); 

			 var url = "autoSave.dgmt?questionNo="+questionNo+"&timeLeftInSecs="+countDwn;
			 

			 YAHOO.util.Connect.asyncRequest('POST', url, callback);    
 
			}
		}; 

		// Define the callback object for success and failure handlers as well as object scope.
		var callback ={
			success:AjaxObject.handleSuccess, 
			failure:AjaxObject.handleFailure,
			scope: AjaxObject
		};

		// Start the transaction.
		AjaxObject.startRequest(); 	
  }
// auto save code end 
// AutoSaveForTimer code start 
	function AutoSaveForTimer(nMilliSeconds)
  {
      try
     {    
       var nMinutes=0;
       AutoSaveClearTimeOuts();

       nMinutes = ((nMilliSeconds / 1000) / 60); 
       mnAutoSaveMilliSeconds = nMilliSeconds; 
       mnAutoSaveMilliSecondsExp=0;

       oTimeOut = window.setTimeout("AutoSaveForSubmitTimer("+nMilliSeconds+")",nMilliSeconds);
       oInterval = window.setInterval("AutoSaveCountDown()",mnAutoSaveInterval);
      }
      catch (exception) 
      { 
        if (exception.description == null) { //alert("AutoSaveForTimer Error: " + exception.message); 
		}  
        else {  
			//alert("AutoSaveForTimer Error: " + exception.description); 
		}
      }
  }
	 function AutoSaveForSubmitTimer(nMilliSeconds)
	  {
	     try
	    {
	      AutoSaveClearTimeOuts();
	      AutoSaveBeforeSubmit();
		
	      /*
	         Call the form submittal code in your main page.
	      */
	      SubmitFormToBeSavedTimer(nMilliSeconds);
	    
	     }
	    catch (exception) {}
	  }
	 function  SubmitFormToBeSavedTimer(nMilliSeconds)
	  {
				var AjaxObject = {
				handleSuccess:function(o){
					// This member handles the success response and passes the response object o to AjaxObject's processResult member.
					this.processResult(o);
				},
				handleFailure:function(o){
					// Failure handler
				},
				processResult:function(o){
					var msg = o.responseText;
					if(msg == "SessionExpired")
					{					
						//sessionExpired();
						sessionExpMsgDialog();
						return false;
					}
					  var jsonString = o.responseText; 
					  //document.getElementById("divAutoSave").innerHTML = '<b>Auto Saved...in '+nMilliSeconds * 60 +' Seconds </b>';
					  AutoSaveForTimer(nMilliSeconds);
				},
				startRequest:function() {	
	 			  			 
				 YAHOO.util.Connect.setForm('questionPaperFrom', false); 
				 var url = "autoSaveToLastUpdateTime.dgmt?timeLeftInSecs="+countDwn;

				 YAHOO.util.Connect.asyncRequest('POST', url, callback);    
	 
				}
			}; 

			// Define the callback object for success and failure handlers as well as object scope.
			var callback ={
				success:AjaxObject.handleSuccess, 
				failure:AjaxObject.handleFailure,
				scope: AjaxObject
			};

			// Start the transaction.
			AjaxObject.startRequest(); 	
		}	  
// AutoSaveForTimer code end 
--></script>
</head>
<!-- oncontextmenu="return false;" -->
<body onload="showResource()" oncontextmenu="return false;">        
<s:form name="questionPaperFrom" action="#" enctype="multipart/form-data">
<TABLE cellSpacing="3" cellPadding="0" width="100%" border="0"  class="sTable" >

<TBODY> 
	<TR><TD vAlign="top" align="left"> 
		<TABLE class="sTable" cellSpacing="0" cellPadding="0" width="100%" border="0">
			<TBODY>
			<TR>
				<TD width="30%">
					<img src="common/images/logo_DGMT.png" alt="Directorate General of Military Training" width="568" height="46" /><TD class="label"  align="right" width="25%">
				</td>	
				<td  width="20%" align="center"><span class="label" id="instr"><img src="common/images/add-instructions.gif" alt="Instructions"  width="16" height="16" border="0" align="absmiddle" /> <a href="#" id="rules"  onclick="openInstructions()">Instructions</a></span></td>
				<td width="50%" align="right">
						<table border="1" bgcolor="#00000">
						<tr><td class="label" align="center">
							<span id="timerLabel" style="color:#00ff00">Time Left</span> &nbsp;<div id="cd" style="font:bold 20px;color:#00ff00;"></div>
						</td>
						</tr>  
						</table>
				</TD>
			</tr>
			<tr> 
			</tr>
			</TBODY>
		</TABLE>
	</TD></TR>
<!-- Examination details -->
<TR><TD vAlign="top" align="center"> 

<div id="HeaderData"  style="width:950px;" align="center" class="curved">
	<table border="0" width="100%"  align="center" bgcolor="white">
				<TR> 
					<td  align="left"><STRONG><font size="4px">Examination</font></STRONG> </td>
					<td  align="right"><STRONG><font size="2px"><div id="examStartTime"></font></STRONG></div> </td>
				</TR>
				<TR>
					<td align="left"><STRONG><font size="2px"><div id="subjectName"></font></STRONG></div></td>
					<td align="right"><STRONG><font size="2px"><div id="icNo"></font></STRONG></div></td>
				</TR>
	</table>  
</div>
</TD></TR>
<!-- Question details -->
<TR>
<TD vAlign="top" align="center"> 

<!-- review table details -->
<div id="reviewDivData"  style="width:950px;height:80px" align="center" class="curved">

<table width="100%" border="0" cellspacing="1" cellpadding="1" >
	<tr>
		<td align="left" width="15%"><font size='2'>Review Questions </font></td>
		<td align="left"><div id="reviewListDiv"></div></td>
	</tr>
	<tr>
	<td colspan="2" align="left"><div id="questionTextDiv1" style="width:900px;"></div></td>
	</tr>
	<tr>
	<td colspan="2" id="questionTextDiv" align="left"></td>
	</tr>
</table>
</div>
</td>
<TR>
<TD vAlign="top" align="center"> 
<div id="urData"  style="width:950px;" align="center" class="curved">
			 <!-- queston table details -->
			<table width="100%" border="0" cellspacing="1" cellpadding="1">
			<tr> 
				<td valign="top">
					<table id="PrvNxtReview" border="0"  width="100%" >
						<tr>
						<td width="100%" nowrap>
							<table cellpadding="0" cellspacing="0" border="0"><tr>
								<td ><INPUT TYPE="button" name="Previous" value="Previous" id="Prev" class="button"  onclick="getQuestion('previous','')"></td>
								<td ><INPUT TYPE="button" name="Next" value="Next"  id="Next" class="button"  onclick="getQuestion('next','')"></td>
								<td id="markUnmark" align="center" style="width:650px">
									<input TYPE="checkbox" id="markForReview" name="markForReview" onclick="updateReviewList()"/> Mark for review 
									&nbsp;<a href="#" id="clearAns" onclick="eraseAnswer()" />Erase Answer</a></td>
								<td id='qSearch' syle='display:none'>
								 &nbsp; Q.No &nbsp;<input type="text" id="quesNumber" size="2" maxlength="3"  onkeyup="return onlyNumbers();" onkeypress="return onlyNumbers();" onmousemove="return onlyNumbers();" onmouseout="return onlyNumbers();"  onkeydown="if (event.keyCode == 13) document.getElementById('goBtnId').click()"/>&nbsp;<input type="button" id="goBtnId" name="goBtnId" onclick="viewQuesNumber()"  value="Go"/> 
								 </td>
								<td align="right"><INPUT TYPE="button" name="button8" value="Finish Examination"  id="button8" class="button" onclick="getQuestion('finish','')"/></td>
							</tr>
							</table>
						</td>
						
						</tr>
					</table>  
				</td>  
			</tr> 
			<tr><td><div id="divAutoSave" name="divAutoSave"></div>
				<table width="100%">
					<tr>
					<td width="5%"></td>
					<td>
						<div  style="height:300px;" id="questionDiv"></div>
					</td>
					<td width="5%"></td>
					</tr>  
				</table>
			<BR> 
			 <%
				String questionPaperId = request.getParameter("questionPaperId") != null ? request.getParameter("questionPaperId") : (String)request.getAttribute("questionPaperId");

				String subjTimeSlotId = request.getParameter("subjTimeSlotId") != null ? request.getParameter("subjTimeSlotId") : (String)request.getAttribute("subjTimeSlotId");

				String subjectCodeId = request.getParameter("subjectCodeId") != null ? request.getParameter("subjectCodeId") : (String)request.getAttribute("subjectCodeId");

				String questionNo = request.getParameter("questionNo") != null ? request.getParameter("questionNo") : (String)request.getAttribute("questionNo");

				String elapsed = request.getParameter("elapsed") != null ? request.getParameter("elapsed") : (String)request.getAttribute("elapsed");

				String message = request.getParameter("message") != null ? request.getParameter("message") : (String)request.getAttribute("message");
				
			 %>
			 	<input type="hidden" name="hidVal" id="hidVal">
				<input TYPE="hidden" id="nxtQues"/>
				<input TYPE="hidden" id="presentQuestionNo"/>  
				<input TYPE="hidden" id="questionsCount"/>
				<input TYPE="hidden" id="questionType"/>
				<input TYPE="hidden" id="count"/>
				<input TYPE="hidden" id="choiceSize"/>
				<input TYPE="hidden" id="matchingOptionsSize"/>
				<input TYPE="hidden" id="examInstructions" value=""/>  
				<input TYPE="hidden" id="examPaperSecId"/>  
				<input TYPE="hidden" id="actionPath"/>  
				<input type='hidden'  id='imageName' />
				<input type="hidden" id="questionPaperId"  value="<%=questionPaperId%>" /> 
				<input type="hidden" id="subTimeSlotId"  value="<%=subjTimeSlotId%>" /> 
				<input type="hidden" id="subjectCodeId"  value="<%=subjectCodeId%>" /> 

				<input type="hidden" id="reviewQuesCount"  /> 
				<input type="hidden" id="timerInSeconds"  /> 
				<input type="hidden" id="bracesCount"  /> 
				<input type="hidden" id="currentQuestionNo"  /> 

				<input type="hidden" id="questionPaperId"  value="<%=(String)request.getAttribute("questionPaperId")%>" /> 
				<input type="hidden" id="subTimeSlotId"  value="<%=(String)request.getAttribute("subjTimeSlotId")%>" /> 
				<input type="hidden" id="subjectCodeId"  value="<%=(String)request.getAttribute("subjectCodeId")%>" /> 
				<input type="hidden" id="questionNo"  value="<%=questionNo%>" /> 
				<input type="hidden" id="elapsed"  value="<%=elapsed%>" /> 
				<input type="hidden" id="message"  value="<%=message%>" /> 
				<s:hidden name="answer" id="answer" />  
			  </td></tr>
			<tr  class="curved">
			<td valign="top" >
					<table id="PrvNxtReview1" border="0"  width="100%" >
						<tr>
						<td  width="100%" nowrap>
							<table width="100%"><tr>
								<td ><INPUT TYPE="button" name="Previous" value="Previous" id="Prev1" class="button"  onclick="getQuestion('previous','')"></td>
								<td ><INPUT TYPE="button" name="Next" value="Next"  id="Next1" class="button"  onclick="getQuestion('next','')"></td>
								<td align="center" style="width:650px">
									&nbsp;
								</td>
								<td align="right"><INPUT TYPE="button" name="button3"  class="button" id="button3" value="Finish Examination"  onclick="getQuestion('finish','')"/></td>
							</tr>
							</table>
						</td>
						</tr>
					</table>
				</td>  
			</tr>
			<tr>
			</tr>
			</table>
</div>
</TD></TR>

<TR>
<TD vAlign="top" align="center"> 
<DIV id="resultHeaderDiv" style="display:none;width:950px;" align="center"  bgcolor="gray" class="curved">
 
	<TABLE cellSpacing="0" cellPadding="0" width="50%" border="0">
 		<TR>
		<TD  align="center"><div id="resultDiv"></div></TD>
		</TR>
		<TR><TD></TD></TR>
  	</TABLE>

</DIV> 
</TD></TR>

<TR>
<TD vAlign="top" align="center"> 
	<DIV id="logoutDiv" style="display:none;width:950px;" align="center">
		<TABLE cellSpacing="0" cellPadding="0" width="100%" border="0">
 		<TR>
		<TD  align="right"><INPUT class='button' id='button2' onclick='closeSession();' type='button' name='button2' value='Logout & Close'/></TD>
		</TR>
		<TR><TD></TD></TR>
  	</TABLE>
		
	</DIV>
</TD>
</TR>


</TBODY>
</TABLE>

</DIV>
</DIV></DIV>


<div id="boxes">
	<div id="informationDiv" class="window" style="height:auto;width:50%">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableborder">
		<tr>
		<td align="center"  class="collapseboxcontent" style="padding:0px">
					<table width="100%" cellpadding="0" cellspacing="1">
					<tr> 
					<td colspan="11" align="center">

						<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr> 
						<td class="label" colspan="11" align="center">&nbsp;
						</td>
						</tr>
						<tr><td class="label" colspan="11" align="center">Are you sure want to finish the exam ?</td>
						</tr>
						<tr> 
						<td class="label" colspan="11" align="center">&nbsp;
						</td>
						</tr>  
						</table>
					</td>  
					</tr>
					<tr class="innertablefotter"> 
					<td colspan="11" align="center" style="padding:0px"> 
					<input name="Submit24" type="button" class="button" value="   Yes  " onClick="getQuestion('finishFinal','');" />
					<input name="Submit241" type="button" class="button" value="  No  " onClick="return closePopUp();" /> &nbsp;</td>
					</tr>
					</table>
		</td>
		</tr>
		</table>
	</div>
<div id="reviewQueDiv" class="window" style="height:auto;width:50%">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableborder">
		<tr>
		<td align="center"  class="collapseboxcontent" style="padding:0px">
					<table width="100%" cellpadding="0" cellspacing="1">
					<tr> 
					<td colspan="11" align="center">

						<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr> 
						<td class="label" colspan="11" align="center">&nbsp;
						</td>
						</tr>
						<tr><td class="label" colspan="11" align="center">Some of the questions are marked for review, Do you want to review ?</td>
						</tr>
						<tr> 
						<td class="label" colspan="11" align="center">&nbsp;
						</td>
						</tr>
						</table>
					</td>
					</tr>
					<tr class="innertablefotter"> 
					<td colspan="11" align="center" style="padding:0px"> <!-- onClick="getQuestion('toReview','');" -->
					<input name="Submit24" type="button" class="button" value="  Yes  " onClick="return closeReviewPopUp('yes');" />
					<input name="Submit241" type="button" class="button" value="  No  " onClick="return closeReviewPopUp('no');" /> &nbsp;</td>
					</tr>
					</table>
		</td>
		</tr>
		</table>
	</div>
<div id="instructionsDiv" class="window" style="height:auto;width:50%"></div>
<div id="showReviewQuesDialogDiv" class="window" style="height:auto;width:50%">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableborder">
		<tr>
		<td align="center"  class="collapseboxcontent" style="padding:0px">
					<table width="100%" cellpadding="0" cellspacing="1">
					<tr> 
					<td colspan="11" align="center">

						<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr> 
						<td class="label" colspan="11" align="center">&nbsp;
						</td>
						</tr>
						<tr><td class="label" colspan="11" align="center">None of the questions are marked for review</td>
						</tr>
						<tr> 
						<td class="label" colspan="11" align="center">&nbsp;
						</td>
						</tr>
						</table>
					</td>
					</tr>
					<tr class="innertablefotter"> 
					<td colspan="11" align="center" style="padding:0px"> 
					<input name="Submit24" type="button" class="button" value="  Close  " onClick="showReviewQuesDialogDiv();" />
					</tr>
					</table>
		</td>
		</tr>
		</table>
	</div>

 
<div id="errorDiv" class="window" style="height:auto;width:50%">
	<TABLE cellSpacing="0" cellPadding="0" width="100%" border="0">
 		<TR>
		<TD align="center" class="label"> Network error. Please contact the Proctor / Administrator</TD>
		</TR>
		<TR><TD  align="center"> &nbsp; </TR>
		<TR><TD  align="center">
		<INPUT class='button' id='button2' onclick='window.close()'  type='button' name='button2' value='Close'/></TD>
		</TR>
		<TR><TD></TD></TR>
  	</TABLE>
</DIV> 

<div id="sessionExpDiv" class="window" style="height:auto;width:50%">
	<TABLE cellSpacing="0" cellPadding="0" width="100%" border="0">
 		<TR>
		<TD align="center" class="label"> Your Session has expired. Please login again. </TD>
		</TR>
		<TR><TD  align="center"> &nbsp; </TR>
		<TR><TD  align="center">
		<INPUT class='button' id='button2' onclick='window.close()'  type='button' name='button2' value='OK'/></TD>
		</TR>
		<TR><TD></TD></TR>
  	</TABLE>
</DIV> 


<div id="resultsSubmittedDiv" class="window" style="height:auto;width:50%">
	<TABLE cellSpacing="0" cellPadding="0" width="100%" border="0">
 		<TR>
		<TD align="center" class="label"> You have already finished with the examination.  </TD>
		</TR>
		<TR><TD  align="center"> &nbsp; </TR>
		<TR><TD  align="center">
		<INPUT class='button' id='button5' onclick='closeSession();'  type='button' name='button5' value='Logout & Close' /></TD>
		</TR>
		<TR><TD></TD></TR>
  	</TABLE>
</DIV> 

<div id="mask"></div>
</div>

</s:form>
</body>
</html>

