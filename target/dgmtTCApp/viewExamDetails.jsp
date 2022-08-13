<%@ page language="java" contentType="text/html; charset=UTF-8" 	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.Set,com.dgmt.dto.CandidateDTO,com.dgmt.model.GeneratedQuePaper,com.dgmt.model.ExamPaper,com.dgmt.model.ExamPaperSection" %>
<script type="text/javascript" src="common/js/utility.js"></script>
<script>
	var menu = 'examinationWindow';
</script>
<% 
 Set<ExamPaperSection> examPaperSection = null;
 ExamPaper examPaper = (ExamPaper) request.getAttribute("examPaper");
 if(examPaper!=null) {
	 examPaperSection = examPaper.getSections();
 }
%>

<script>

var countDwn
var timerID = null
var timerRunning = false
var delay = 1000

function DisableEnableLinks(xHow){  
  objLinks = document.links;
  for(i=0;i<objLinks.length;i++){
    objLinks[i].disabled = xHow;
    //link with onclick 
    if(objLinks[i].onclick && xHow){  
        objLinks[i].onclick = new Function("return false;" + objLinks[i].onclick.toString().getFuncBody());
    }
    //link without onclick
    else if(xHow){  
      objLinks[i].onclick = function(){return false;}
    }
    //remove return false with link without onclick
    else if(!xHow && objLinks[i].onclick.toString().indexOf("function(){return false;}") != -1){            
      objLinks[i].onclick = null;
    }
    //remove return false link with onclick
    else if(!xHow && objLinks[i].onclick.toString().indexOf("return false;") != -1){  
      strClick = objLinks[i].onclick.toString().getFuncBody().replace("return false;","")
      objLinks[i].onclick = new Function(strClick);
    }
  }
}

String.prototype.getFuncBody = function(){ 
  var str=this.toString(); 
  str=str.replace(/[^{]+{/,"");
  str=str.substring(0,str.length-1);   
  str = str.replace(/\n/gi,"");
  if(!str.match(/\(.*\)/gi))str += ")";
  return str; 
} 


function openExaminationWindow(examPaperId){

		var AjaxObject = {
			handleSuccess:function(o){
				// This member handles the success response and passes the response object o to AjaxObject's processResult member.
				this.processResult(o);
			},
			handleFailure:function(o){
				// Failure handler
			},
			processResult:function(o){
				  
				var jsonString = o.responseText;
				var elapsed = "";
				var  message = "";
				//alert(jsonString); 
				if (jsonString=="NotAllowedTime")	 {			
					//alert("Please click 'Start Examination' button before allowed time.");
					alert(DGMT.Messages.CANDEXAMINATION.StartExm);
					return false;
				} else if (jsonString=="Question Paper not availabale")	 {			
					//alert("Question Paper is not availabale");
					alert(DGMT.Messages.CANDEXAMINATION.QpaperUnavailable);
					return false; 
				} else if (jsonString=="reschedule") { 
					//alert("Please contact administrator for re-scheduling the examination");
					//alert("Please contact the proctor / administrator");
					alert(DGMT.Messages.CANDEXAMINATION.ContactProctor);
					return false;
				} else {  
					document.getElementById("startButton").disabled=true;
					DisableEnableLinks(true);
					var paperDetails = jsonString.split("##");
					//alert(paperDetails);
					//alert("length "+paperDetails.length);
					var questionPaperId =  paperDetails[0];
					var questionNo = paperDetails[1]; 
					elapsed =  paperDetails[2];
					
					if (questionNo=="first")
						questionNo = "0";   
					  
					if (elapsed=="")    
						elapsed = 0;
					if (paperDetails.length==4)
						message =  paperDetails[3];

				}

					//alert("message : "+message);
					//var url =  "oleQuestionPaper.jsp?questionPaperId="+questionPaperId+"&subjTimeSlotId="+document.getElementById("subjTimeSlotId").value+"&subjectCodeId="+document.getElementById("subjectCodeId").value+"&questionNo="+questionNo+"&elapsed="+elapsed+"&message="+message; 
					var url =  "showQuestionPaper.dgmt?questionPaperId="+questionPaperId+"&subjTimeSlotId="+document.getElementById("subjTimeSlotId").value+"&subjectCodeId="+document.getElementById("subjectCodeId").value+"&questionNo="+questionNo+"&elapsed="+elapsed+"&message="+message;   

					//window.open(url,'_blank',"fullscreen=yes"); 
					var w=800
					var h=600
					if (window.screen) {
					   w = window.screen.availWidth;
					   h = window.screen.availHeight;
					}
					// commented for bug #21384
					//window.open(url,'_blank',"fullscreen=yes"); 
					//window.open(url,'_blank','dialog=yes,maximize=no,scrollbars=yes,location=no,status=no,width='+w+',height='+h+',top=0,left=0'); 
					window.open("about:blank",'myExamWindow','dialog=yes,maximize=no,scrollbars=yes,location=no,status=no,width='+w+',height='+h+',top=0,left=0');

					document.examdetails.action = url;
					document.examdetails.testVal.value = document.getElementById('testVal').value;
					document.examdetails.submit();

					window.open('AutoCloseExample2.html','_self');
				 
 
			},  
			startRequest:function() {	
				var url =  "viewQuestionPaper.dgmt?exampaperId="+examPaperId+"&subjTimeSlotId="+document.getElementById("subjTimeSlotId").value+"&subjectCodeId="+document.getElementById("subjectCodeId").value;  
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
function viewCandidateExams() {
document.examdetails.action = "viewCandidateExams.dgmt";    
document.examdetails.method = "post";  
document.examdetails.submit();
 
}    
function InitializeTimer(){

	document.getElementById("startButton").disabled=true;
 	countDwn = document.getElementById("elapsedTime").value;
	
	if (countDwn > 0) {
	StartTheTimer();
	}else {
	document.getElementById("cd").innerHTML = '00:00:00';
	document.getElementById("startButton").disabled=false;
	}
  
}
function StartTheTimer()
{
 	//countDwn = document.getElementById("elapsedTime").value;

    if (countDwn==0)
    { 
        StopTheClock(); 
		secs = "00";
		document.getElementById("cd").innerHTML = hours+' : '+mins+' : '+secs;
		document.getElementById("startButton").disabled=false;
    } else {
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
        timerRunning = true
        timerID = self.setTimeout("StartTheTimer()", delay)
    }
}
function StopTheClock()
{
    if(timerRunning)
        clearTimeout(timerID)
    timerRunning = false
}

</script>     

<div id="breadcrumb"><strong>Home</strong> &gt; Examinations </div>  
<div id="wrapper" class="clearfix"> 

<%@include file="/candiateExaminationsLeftMenu.jsp"%>

<div id="maincol">
<div class="fleft" style="width:100%">
    <!--  begin bodycontent -->   


<body onload="InitializeTimer()">
<form name="examdetails" target="myExamWindow" method="post" id="examdetails" >

<input type="hidden" id="subjTimeSlotId" name="subjTimeSlotId" value='<s:property value="subjectTimeSlotId"/>' /> 
<input type="hidden" id="subjectCodeId" name="subjectCodeId" value='<s:property value="subjectCodeId"/>' /> 
<input type="hidden" id="questionCount" name="questionCount" value='<s:property value="questionCount"/>' /> 
<input type="hidden" id="elapsedTime" name="elapsedTime" value='<s:property value="elapsedTime"/>' />   
<input type="hidden" id="testVal" name="testVal" value='123' /> 
    
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td align="left" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="sTable">
              <tr class="Title">
                <td colspan="4"><strong>Examination</strong></td>
              </tr>
              <tr>
                <td width="33%" class="label">Subject</td>
                <td width="23%" class="label">Title</td>
                <td width="23%" class="label">Time Slot</td>
				<td width="20%" class="label">Time remaining to start the examination</td>
              </tr>
              <tr>
                <td><s:property value="subjectName"/></td>
                <td><s:property value="subjectTitle"/></td>
                <td><s:property value="subjectTimeSlot"/></td> 
				 <td><div id="cd"></div></td> 
              </tr>  
              <tr class="dotline">
                <td class="label">Time Limit</td>
                <td class="label">&nbsp;</td>
                <td class="label">&nbsp;</td>
				<td class="label">&nbsp;</td>
              </tr>
              <tr>
                <td><s:property value="subjectTimeDuration"/></td>
                <td>&nbsp;</td>   
                <td>&nbsp;</td>
              </tr>
              </table> 

              <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="30"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td></td>
                    </tr>
                    <tr>  
					 <!-- <Strong>Instructions </Strong> <br /> 
					 <% if (examPaperSection!=null) %>
					 There are <% if (examPaper!=null) %>
					 <%= examPaper.getTotalQueCount() %> questions and
					 <% if (examPaperSection!=null) %>
					 <%= examPaperSection.size()%> sections in this paper.<br /> 
					 Please read the instructions given in each section carefully. 
 -->
                      <td> 
						</td>
                    </tr> 
                    <tr>    
                      <td>&nbsp;</td>  
                    </tr>  
                    <tr class="tablefooter">
                      <td align="right"><input name="startButton" type="button" class="button" id="startButton" value="Start Examination" 
					  onclick='openExaminationWindow("<%=session.getAttribute("exampaperId")%>")' /></td>    
                    </tr>
                  </table></td>   
                </tr>
              </table></td>
          </tr>
        </table>

</div>
</div>	
    
</div>
 
</form>
</body>
