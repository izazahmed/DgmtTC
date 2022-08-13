<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.*, java.io.*,
				com.dgmt.model.CandidateEnrollememt,com.dgmt.model.CandidateEnrollmentDetails,com.dgmt.model.TimeSlot,
				com.dgmt.model.ExamPaper" %>
<script type="text/javascript" src="common/js/utility.js"></script>
<script>

//var menu = 'examinationWindow';

function openProcKeyWindow(id,questionPaperId) 
{
 	document.getElementById('questionPaperId').value  = questionPaperId;
 	document.getElementById("errorMsgArea1").style.display="none";
 	document.getElementById("errorMsgArea1").innerHTML = ''; 

 	$(document).ready(function() 
	{       
		var id = $('#procDialog'); 
		$('#myjquerymenu').hide();
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
		//$("#procDialog").show();
		$('#mask').show();
		$("#procDialog").show();
	 });	
	 	
	document.getElementById('procKey1').focus();
	document.getElementById('subject').innerHTML = document.getElementById('sub'+id).innerHTML;
	document.getElementById('examTitle').innerHTML = document.getElementById('subTitle'+id).innerHTML;
	document.getElementById('timelimit').innerHTML= document.getElementById('timeDuration'+id).innerHTML;
	document.getElementById('timeD').innerHTML= document.getElementById('tlimit'+id).innerHTML;
}

function closeProcterWindow()
{
	$('#mask').hide();
	$("#procDialog").hide();
	for(var i = 1; i<=7 ; i++)
	{
		document.getElementById('procKey'+i).value='';
	}  
}

function validateProcterKey()
{  		
	var examId = document.getElementById('examId').value;
	var exampaperId = document.getElementById("exampaperId").value;

	var i = 1;
	var pKey = "";
	for(i = 1; i<=7 ; i++)
	{
		if (document.getElementById('procKey'+i).value=='')
		{
			document.getElementById("errorMsgArea1").style.display="block";
			document.getElementById("errorMsgArea1").innerHTML = "<table align='center' border='0'><tr><td><font color='red'><li>Please enter Proctor Key</li></font></td></tr></table>";
			document.getElementById('procKey'+i).focus();
			return false;
		}
		pKey = pKey + document.getElementById('procKey'+i).value;
	}
		
	document.procKeyForm.prockerKey.value = pKey;
	      
	var AjaxObject = 
	{
		handleSuccess:function(o)
		{
			// This member handles the success response and passes the response object o to AjaxObject's processResult member.
			this.processResult(o);
		},   
		handleFailure:function(o)
		{
			// Failure handler
			
		},
		processResult:function(o)
		{  
			document.procKeyForm.subjectName.value = document.getElementById('subject').innerHTML;
			document.procKeyForm.subjectTitle.value = document.getElementById('examTitle').innerHTML;
			document.procKeyForm.subjectTimeSlot.value = document.getElementById('timeD').innerHTML;
			document.procKeyForm.subjectTimeDuration.value = document.getElementById('timelimit').innerHTML;
			document.procKeyForm.subjectTimeSlotId.value  = document.getElementById("subjectTimeSlotId").value;
			document.procKeyForm.subjectCodeId.value  = document.getElementById("subjectCodeId").value;
			
			var validKey = o.responseText;	

			if (validKey=="valid")
			{
				document.procKeyForm.action = "viewCandidateExamDetails.dgmt?exampaperId="+exampaperId;
				document.procKeyForm.method = "post";  
				document.procKeyForm.submit();
			}
			else 
			{
				document.getElementById("errorMsgArea1").style.display="block";
				document.getElementById("errorMsgArea1").innerHTML = "<table align='center' border='0'><tr><td><font color='red'><li>Invalid Proctor's Key</li></font></td></tr></table>"
				for(i = 1; i<=7 ; i++)
				document.getElementById('procKey'+i).value='';	 			
				return false;   
			}      
		},       
		startRequest:function() 
		{				   
			YAHOO.util.Connect.setForm('procKeyForm', false);  
			var url ='validateProcterKey.dgmt?&examId='+examId;   
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

function chkForQuestionPaper(id,exampaperId,subjectCodeId)
{
	document.getElementById('subjectCodeId').value = subjectCodeId;
	var examId = document.getElementById('examId').value;
	var AjaxObject = 
	{
		handleSuccess:function(o)
		{
			// This member handles the success response and passes the response object o to AjaxObject's processResult member.
			this.processResult(o);
		},
		handleFailure:function(o)
		{
			// Failure handler
		},
		processResult:function(o)
		{
			var msg = o.responseText;

			if (msg == "SessionExpired") 
			{
					sessionExpired();
					return false;
			}

			if (msg=="Question paper not available")
			{
				//alert("Question paper not available");
				alert(DGMT.Messages.CANDEXAMINATION.QpaperUnavailable);
				$('#mask').hide();
			}
			else 
			{
				//document.getElementById("questionPaperId").value = eval ('('+o.responseText+')');
				document.getElementById("exampaperId").value = exampaperId;
				if(document.getElementById("validateProctorKey").value == "true")
				{
					openProcKeyWindow(id,document.getElementById("questionPaperId").value);
				}
				else
				{
					document.procKeyForm.subjectName.value =document.getElementById('sub'+id).innerHTML;						
					document.procKeyForm.subjectTitle.value = document.getElementById('subTitle'+id).innerHTML
					document.procKeyForm.subjectTimeSlot.value = document.getElementById('tlimit'+id).innerHTML;
					document.procKeyForm.subjectTimeDuration.value = document.getElementById('timeDuration'+id).innerHTML;
					document.procKeyForm.subjectTimeSlotId.value  = document.getElementById("subjectTimeSlotId").value;
					document.procKeyForm.subjectCodeId.value  = document.getElementById("subjectCodeId").value;
					
					document.procKeyForm.action = "viewCandidateExamDetails.dgmt?exampaperId="+exampaperId;
					document.procKeyForm.method = "post";  
					document.procKeyForm.submit();
				}				
			}
		},
		startRequest:function() 
		{				
			var url ="oleGeneratedQuestionPaper.dgmt?exampaperId="+exampaperId+"&examId="+examId;
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
var isNN = (navigator.appName.indexOf("Netscape")!=-1);

var procKeyStr ="";

function autoTabSecureKey(input,len, e) 
{
	var keyCode = (isNN) ? e.which : e.keyCode; 
	var filter = (isNN) ? [0,8,9] : [0,8,9,16,17,18,37,38,39,40,46];
	if(input.value.length >= len && !containsElement(filter,keyCode)) 
	{
		input.value = input.value.slice(0, len);
		input.form[(getIndex(input)+1) % input.form.length].focus();
		input.form[(getIndex(input)+1) % input.form.length].select();
	}

	function containsElement(arr, ele) 
	{
		var found = false, index = 0;
		while(!found && index < arr.length)
		if(arr[index] == ele)
		found = true; 
		else
		index++;  
		return found;
	}

	function getIndex(input) 
	{
		var index = -1, i = 0, found = false;
		while (i < input.form.length && index == -1)
		if (input.form[i] == input)index = i;
		else i++;
		return index;
	}

	procKeyStr =procKeyStr+input.value;
	return true;
}//End of function autoTab()


var id = 0;
function FormatTitleCell(elCell, oRecord, oColumn, oData) 
{
	var flag = "false";
	id = id + 1;
	
	 var examPaperId = oRecord.getData("examPaper.id");	
	 document.getElementById("alertNote").style.display="none";
	 document.getElementById("subjectTimeSlotId").value =  oRecord.getData("slotId");
	
	 var url = "";
	 var begintext = oRecord.getData("incomplete") == "1" ? "Resume" : "Start";
	
	 if (oRecord.getData("allowed")!=null && oRecord.getData("allowed")!="false") 
	 {
		 document.getElementById("alertNote").style.display="none";
		 url = oRecord.getData("examPaper.title") +" &nbsp; " +"<a class='active' href='javascript:void(0)'  onclick=chkForQuestionPaper("+id+","+examPaperId+","+oRecord.getData("examPaper.subject.id")+")>"+begintext+"</a>";
	 } 
	 else  
	 {
		 flag = "true";
		 url = oRecord.getData("examPaper.title")+ "&nbsp;&nbsp; <img src='common/images/alert_icon.gif' height='14' width='14'/>";  
	 }   
	
	if (flag=="true") {
	 document.getElementById("alertNote").style.display="block";
	}
	var subDiv = document.createElement('div');
	subDiv.setAttribute('id', 'sub'+id);
	subDiv.innerHTML =  oRecord.getData("subject"); 

	var titleDiv = document.createElement('div'); 
	titleDiv.setAttribute('id','subTitle'+id);
	titleDiv.innerHTML =  oRecord.getData("examPaper.title"); 

	var dDiv = document.createElement('div'); 
	dDiv.setAttribute('id','timeDuration'+id);
	dDiv.innerHTML =  oRecord.getData("timeDuration") + " Hrs"; 

	var tlimitDiv = document.createElement('div'); 
	tlimitDiv.setAttribute('id','tlimit'+id);
	tlimitDiv.innerHTML =  oRecord.getData("timeSlot"); 

	
	document.getElementById('sDiv').appendChild(subDiv);
	document.getElementById('tDiv').appendChild(titleDiv);
	document.getElementById('dDiv').appendChild(dDiv);
	document.getElementById('tlDiv').appendChild(tlimitDiv);

	document.getElementById('sub'+id).style.visibility = 'hidden';
	document.getElementById('subTitle'+id).style.visibility = 'hidden';
	document.getElementById('timeDuration'+id).style.visibility = 'hidden';
	document.getElementById('tlimit'+id).style.visibility = 'hidden';

	 
	elCell.innerHTML = url;
}

function FormatTimeDurationCell(elCell, oRecord, oColumn, oData) 
{
	elCell.innerHTML = oRecord.getData("timeDuration")+" Hrs"; 	
}
 
function dataTableResultStatus() {
	
 		YAHOO.example.Basic = function() {    
			var myColumnDefs = [ 
			{
				key :"subject",
				label :"Subject",
				sortable :false,
				resizeable :true
			}, {  
				key :"examPaper.title",
				label :"Title",
				sortable :true,
				resizeable :true,
				formatter :FormatTitleCell
			},{  
				key :"timeSlot",
				label :"Time Slot",
				sortable :false,
				resizeable :true
			},{  
				key :"timeDuration",
				label :"Time Limit",
				sortable :false,
				resizeable :true,
				formatter :FormatTimeDurationCell
			}
			];  

			var myDataSource = new YAHOO.util.DataSource(
			"viewCandidateExamsList.dgmt");
 
			myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
			myDataSource.responseSchema = {
				resultsList :"subjectDetailsList",  
				fields :[ {
							key :"examPaper.id" 
						},{
							key :"subject"
						},{
							key :"examPaper.title"
						},{
							key :"timeSlot"
						},{
							key :"timeDuration" 
						},{
							key :"allowed" 
						},{
							key :"slotId" 
						},{
							key :"examPaper.subject.id" 
						},{
							key :"incomplete" 
						}]
 			};       

			// Set up the Paginator instance.  
			var myPaginator = new YAHOO.widget.Paginator(
					{
						containers : [ 'paging' ],  
						pageLinks :5,
						rowsPerPage :10,
						rowsPerPageOptions : [ 10, 20, 30 ],
						template :"<strong>{CurrentPageReport}</strong> {PreviousPageLink} {PageLinks} {NextPageLink} {RowsPerPageDropdown}"
					});  

			var myTableConfig = {
				//initialRequest :'?sort=title&dir=asc&startIndex=0&results=100',
				paginator :myPaginator
			};

			myDataTable = new YAHOO.widget.DataTable("dynamicData",
					myColumnDefs, myDataSource, myTableConfig);
  
			this.myDataTable.set("MSG_EMPTY","<b><font color='red'>You don't have any examinations scheduled at this time.</font></b>" );

			return {
				oDS :myDataSource,
				oDT :myDataTable 
			};
		}();
	}
</script>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">

<div id="breadcrumb"><strong>Home</strong> &gt; Examinations </div>

<div id="wrapper" class="clearfix"> 

<%@include file="/candiateExaminationsLeftMenu.jsp"%>

<DIV id="maincol"><DIV class="fleft" style="WIDTH: 100%">

<form name="procKeyForm">

<TABLE cellSpacing="0" cellPadding="0" width="100%" border="0">
<TBODY> 
	<TR><TD vAlign="top" align="left"> 

		<TABLE id="sortTable" cellSpacing="1" cellPadding="0" width="100%" border="0">
		<THEAD><TR>
		<TH class="mainHeader" colSpan="4">Examinations</TH>
		</TR>    
		</THEAD>
		</TABLE> 
	</td></tr>

<TR><TD vAlign="top" align="left"> 
	<div id="dynamicData"></div>
 </td></tr>
<TR><TD vAlign="top" align="left"> 
	<table>
	<tr>		
		<td>
			<div id="alertNote" style="display:none"><img src='common/images/alert_icon.gif'  height='14' width='14'/>&nbsp; means user not logged in the specified time slot or allowed time is over</div>
		</td>
	</tr>
	</table>
</td></tr>

<input type="hidden" id="examId" name="examId" value="<%=request.getParameter("examId")%>"/>
<input type="hidden" id="exampaperId" name="exampaperId"/>
<s:hidden id="prockerKey"  name="prockerKey" />
<s:hidden id="subjectName" name="subjectName" />
<s:hidden id="subjectTitle" name="subjectTitle" />
<s:hidden id="subjectTimeSlot" name="subjectTimeSlot" />
<s:hidden id="subjectTimeDuration" name="subjectTimeDuration" /> 
<s:hidden id="questionCount" name="questionCount" /> 
<s:hidden id="sectionCount" name="sectionCount" /> 
<s:hidden id="subjectTimeSlotId" name="subjectTimeSlotId" />  
<s:hidden id="subjectCodeId" name="subjectCodeId" />   
<input type="hidden" id="validateProctorKey" name="validateProctorKey" value="<%=(String)session.getAttribute("validateProctorKey")%>"/>
<input type="hidden" id="questionPaperId" name="questionPaperId"/>
</TD></TR></TBODY></TABLE>

<!-- Mask to cover the whole screen -->
<div id="mask"></div>
<div  id="sDiv"></div>
<div  id="tDiv"></div>
<div  id="dDiv"></div>
<div  id="tlDiv"></div>
<div  id="timeD" style="display:none"></div>


</DIV></DIV>
</DIV>     <!-- end bodycontent -->	 

<div id="boxes">
<div id="procDialog" class="window" style="height: auto; width: 70%">
<div id="errorMsgArea1"></div>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="sTable">
 <tr>
    <td width="33%" class="label">Subject</td>
    <td width="33%" class="label">Title</td>
    <td width="33%" class="label">Time Limit</td>
    </tr>
  <tr>
    <td><div id="subject"></div></td>
    <td><div id="examTitle"></div></td>
    <td><div id="timelimit"></div></td>
    </tr>
  <tr class="dotline">
    <td class="label">Proctor's Key</td>
    <td class="label">&nbsp;</td>
    <td class="label">&nbsp;</td>
  </tr>
  <tr>
    <td>  
	  <input id="procKey1" name="procKey1"  onKeyUp="return autoTabSecureKey(this, 1, event);" type="text" class="skey" size="2" maxlength="1" />
      <input id="procKey2" name="procKey2"  onKeyUp="return autoTabSecureKey(this, 1, event);" type="text" class="skey" size="2" maxlength="1"  />
      <input id="procKey3" name="procKey3"  onKeyUp="return autoTabSecureKey(this, 1, event);" type="text" class="skey" size="2" maxlength="1"  />
      <input id="procKey4" name="procKey4"  onKeyUp="return autoTabSecureKey(this, 1, event);" type="text" class="skey" size="2" maxlength="1"   />
      <input id="procKey5" name="procKey5"  onKeyUp="return autoTabSecureKey(this, 1, event);" type="text" class="skey" size="2" maxlength="1"   />
      <input id="procKey6" name="procKey6"  onKeyUp="return autoTabSecureKey(this, 1, event);" type="text" class="skey" size="2" maxlength="1"  />
      <input id="procKey7" name="procKey7"  type="text" class="skey" size="2" maxlength="1"   />       
	  </td> 
    <td>&nbsp;</td>
    <td>&nbsp;</td>       
  </tr>   
  <tr class="dotline">
  <td colspan='4' align='left'>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
		<tr colspan='5'>
			<td align='right'><input name="" type="button" value="Continue" class="button"  onclick="validateProcterKey()"/> </td>
			<td align='left'><input name="" type="button" value="Cancel" class="button close" onclick="closeProcterWindow()"/></td>
			<td align='right'>&nbsp; </td>
			<td align='right'>&nbsp; </td>
		</tr>
		</table>
  </td>
  </tr>

</table> 
</div>
</div>


</form>
 
<script>
dataTableResultStatus();
</script>
