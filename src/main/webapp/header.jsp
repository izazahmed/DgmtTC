<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.List"%>
<%@page import="com.dgmt.model.OleExam"%>
<%
	ServletContext context = getServletConfig().getServletContext();
%>


<script>
function checkSessionStatus()
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
			
			 var message = o.responseText;
			 if(message == "SessionExpired")
			 {                             
				  sessionExpired();
				  return false;
			 }     			
		},
		startRequest:function() {				
			var url ='checkSessionStatus.dgmt';
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

</script>

<!--begin header -->

<div id="header">
<div id="logo">
<s:form action="enrollmentMenu" method="post" name="header"><img
	src="common/images/logo_DGMT.png"
	alt="Directorate General of Military Training" width="568" height="46" />
	<input type="hidden" name="csrfPreventionSalt" value="<%= request.getAttribute("csrfPreventionSalt") %>"/>
<div class="logout"><a href="javascript:void(0);"
	onclick="download('Candidate_Examination_Help.pdf')"><img
	src="common/images/help.png" alt="Help" border="0" /></a> <a href="#"
	onclick="forwardToAction('logout.dgmt')"><img
	src="common/images/logout.png" alt="Logout" border="0" /></a></div>
<input type="hidden" name="examType" /> <input type="hidden"
	name="examId" />
</s:form>
</div>
<div id="myjquerymenu" class="jquerycssmenu">

<ul>

	<%
		if (request.isUserInRole("CANDIDATE")) 
		{
			OleExam exam = (OleExam) session.getAttribute("candidateExam");
			String isCandidateEnrolled = (String) session
					.getAttribute("candidateEnrolled");
	%>

	<li><a href="#"
		onclick="forwardToAction('oleCandidateHomeMenu.dgmt')"
		id="candEnrollments">Home</a></li>

	<%
			if (isCandidateEnrolled != null
					&& isCandidateEnrolled.equalsIgnoreCase("true")) 
			{
	%>
	<li><a href="javascript:void(0)">Examinations</a>
	<ul>
		<%
				if (exam != null) 
				{
		%>
		<li><a href="javascript:void(0)"
			onclick="getCandExamination('<%=exam.getId()%>')"><%=exam.getName()%></a></li>
		<%
				}
		%>
	</ul>
	</li>
	<%
			}
		}
	%>

</ul>
<div class="date">Welcome<strong class="guest">&nbsp;&nbsp;
<%=request.getUserPrincipal()%> </strong> &nbsp;&nbsp; <script>var now = new Date();
var days = new Array(
'Sunday','Monday','Tuesday',
'Wednesday','Thursday','Friday','Saturday');
var months = new Array(
'Jan','Feb','March','Apr','May',
'Jun','Jul','Aug','Sep','Oct',
'Nov','Dec');
var date = ((now.getDate()<10) ? "0" : "")+ now.getDate();
function fourdigits(number) {
return (number < 1000) ? number + 1900 : number;}
today = days[now.getDay()] + ", " +
months[now.getMonth()] + " " +
date + ", " +
(fourdigits(now.getYear()));
document.write(today);</script></div>

</div>
</div>
<!-- end of header -->

<script>
var menuHeader = '<%=session.getAttribute("menuHeader")%>';

document.getElementById(menuHeader).className = "active";
</script>