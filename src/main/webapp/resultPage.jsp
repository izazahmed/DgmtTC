<%@ page import="com.dgmt.dto.ResultDetails,com.dgmt.model.ExamPaperSection,java.util.*" %>
<link rel="stylesheet" href="common/css/default.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="common/css/common.css"  />

<style type="text/css">
.curved
{
border:1px solid black;
}

</style>
<script type="text/javascript">
<%
    String timeLeftInSecs = (String)request.getParameter("timeLeftInSecs");
%> 
function initializeTimer() {

	    var countDwn = '<%=timeLeftInSecs%>'; 
		var secs = 0;
		var mins = 0; 
		var hours = 0;
		

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
		
}

</script>

<% 
	String sesValue =  (String) request.getAttribute("sessionValue");

	ResultDetails resultDetails =  (ResultDetails) request.getAttribute("resultDetails"); 

	int noObjQuestions	 = Integer.parseInt(resultDetails.getNoObjQuestions());
	int noCorrectAnswers = Integer.parseInt(resultDetails.getNoCorrectAnswers());
	int totalMarks		 = Integer.parseInt(resultDetails.getTotalMarks());
	String subjDetails		 = resultDetails.getSubjectDetails();
	String personalInfo	 = resultDetails.getUserName();
	String startedAt		 = resultDetails.getStartedAt().split(" ")[1];

	int marksPerObjQuestion = 0;

	Set<ExamPaperSection> sections  = (Set<ExamPaperSection>)resultDetails.getSections();

	Iterator iter = sections.iterator();
    while (iter.hasNext())
	{
		ExamPaperSection sec = (ExamPaperSection)iter.next();
	  if (sec.getType().equalsIgnoreCase("0")) {
			marksPerObjQuestion = sec.getMarksPerQuestion(); 
			break;
	  }
    } 
	int showResults		 = 	resultDetails.getShowResults();
	int passPctObj		 = resultDetails.getPassPctObj();  

	String subjStatus;

	int objPercentage   = (100 * totalMarks) / (noObjQuestions * marksPerObjQuestion);

	if (objPercentage >= passPctObj) 
		subjStatus = "Pass";	
	else 
		subjStatus = "Fail";		
	
%>
<!-- oncontextmenu="return false;" -->

<body onload="initializeTimer()" oncontextmenu="return false;">
<form name="resultPage" action="#">
<input type="hidden" name="hidVal" id="hidVal">

<TABLE cellSpacing="3" cellPadding="0" width="100%" border="0"  class="sTable"  >

<TR>
		<TD width="50%">
			<img src="common/images/logo_DGMT.png" alt="Directorate General of Military Training" width="568" height="46" />
		</td>	
		<td width="20%" align="right">
				<table border="1" bgcolor="#00000">
				<tr>
					<td class="label" align="center">
						<span id="timerLabel" style="color:#00ff00">Time Left</span> &nbsp;<div id="cd" style="font:bold 20px;color:#00ff00;"></div>
					</td>
				</tr>  
				</table>
		</TD>
</tr>
</TABLE>
<!-- Examination details -->
<TABLE  cellPadding="0" width="100%" border="0"  class="sTable"  >
<TR><TD vAlign="top" align="center"> 
<DIV  style="width:950px;" align="center"  bgcolor="gray" class="curved">
<div id="HeaderData"  style="width:950px;" align="center" class="curved">
	<table border="0" width="100%"  align="center" bgcolor="white">
				<TR> 
					<td  align="left"><STRONG><font size="4px">Examination</font></STRONG> </td>
					<td  align="right"><STRONG><font size="2px">User Name  : <%=personalInfo%></font></STRONG></td>
				</TR>
				<TR>
					<td align="left"><STRONG><font size="2px"><%=subjDetails%></font></STRONG></td>
					<td align="right"><STRONG><font size="2px">Started At  : <%=startedAt%></font></STRONG></td>
				</TR>
	</table>  
</div>
</TD></TR>
</table>

<TABLE  cellPadding="0" width="100%" border="0"  class="sTable"  >
<TR>
<TD vAlign="top" align="center"> 
 <DIV  style="width:950px;" align="center"  bgcolor="gray" class="curved">
	<TABLE cellSpacing="0" cellPadding="0" width="50%" border="0">
 		<TR>
		<TD  align="center">
		<TABLE cellSpacing='2' cellPadding='0' width='100%' border='0' class='curved'>
		<TBODY><TR class='Title'><TD align='center' colspan='2'> Result </TD></TR>
		<TR><TD width='30%' noWrap><STRONG><font size='2px'>Total No. of Objective Questions</font></STRONG></TD><TD width='70%' align='center'>&nbsp;&nbsp;<%=resultDetails.getNoObjQuestions()%></TD></TR>		
		</TR>
		<TR><TD></TD></TR>

		<% if (showResults == 0) { %>
		<TR><TD><STRONG><font size='2px'>Total No. of Correct Answers</font></STRONG></TD><TD align='center'>&nbsp;&nbsp;<%=noCorrectAnswers%></TD></TR>
		<%} else if (showResults == 1) { 
			if (subjStatus.equalsIgnoreCase("Fail")) %>
				<TR><TD><STRONG><font size='2px'>Total No. of Correct Answers</font></STRONG></TD><TD align='center'>&nbsp;&nbsp;<%=noCorrectAnswers%></TD></TR>
			<% } else { %>
				<TR><TD><STRONG><font size='2px'>Total No. of Correct Answers</font></STRONG></TD><TD align='center'>&nbsp;&nbsp;<%=noCorrectAnswers%></TD></TR>
				<TR><TD><STRONG><font size='2px'> Status  </font></STRONG></TD><TD align='center'>&nbsp;&nbsp;<%=subjStatus%></TD></TR>
			<% 	} %> 
		<TR>
		<TD vAlign="top" align="center"> 
			<DIV id="logoutDiv" style="width:950px;" align="center">
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
  	</TABLE>
</DIV>

</TD></TR>
</table>

</form>
</body>

<script type="text/javascript">

function closeSession() {
	document.getElementById("hidVal").value="logoutAndClose";
	document.forms[0].action="logout.dgmt";
	document.forms[0].method ="post";
	document.forms[0].submit();  
}

</script>


