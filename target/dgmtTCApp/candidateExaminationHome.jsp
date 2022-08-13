<%@ page import="com.dgmt.model.OleExam"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript" src="common/js/utility.js"></script>
<script>

function closeSecureWindow(){
	$('#mask').hide();
	$("#secureDialog").hide();
}  

function validateSecureKey(){

		var i = 1;
		var secureKey = "";
		for(i = 1; i<=8 ; i++){
				if (document.getElementById('secKey'+i).value==''){
					document.getElementById("errorMsgArea").style.display="block";
					document.getElementById("errorMsgArea").innerHTML = "<table align='center' border='0'><tr><td><font color='red'><li>Please enter secure key</li></font></td></tr></table>";
					document.getElementById('secKey'+i).focus();
					return false;
				}
				secureKey = secureKey + document.getElementById('secKey'+i).value;
		} 

		var examId = document.getElementById('examId').value;

		var AjaxObject = {
			handleSuccess:function(o){
				// This member handles the success response and passes the response object o to AjaxObject's processResult member.
				this.processResult(o);
			},
			handleFailure:function(o){
				// Failure handler
				
			},
			processResult:function(o){
				
				var validKey = o.responseText;
				
				if(validKey == "SessionExpired")
				{                             
					sessionExpired();
					return false;
				}     
				if (validKey=="valid"){
					document.header.action = "viewCandidateExams.dgmt?examId="+examId;    
					document.header.method = "post";  
					document.header.submit();
				} else { 
					//alert("invalid secure key") 
					document.getElementById("errorMsgArea").style.display="block";
					document.getElementById("errorMsgArea").innerHTML = "<table align='center'><tr><td><font color='red'><li>Invalid Secure Key</li></font></td></tr></table>"
					for(i = 1; i<=8 ; i++)
					document.getElementById('secKey'+i).value='';	  			
					return false;   
				}  
			},
			startRequest:function() {				
				//alert("secureKey : "+secureKey);
				var url ='validateSecureKey.dgmt?secureKey='+secureKey+"&examId="+examId;
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

var sKeyStr ="";

function autoTabSecureKey(input,len, e) 
{
	  var keyCode = (isNN) ? e.which : e.keyCode; 
	  var filter = (isNN) ? [0,8,9] : [0,8,9,16,17,18,37,38,39,40,46];
	  if(input.value.length >= len && !containsElement(filter,keyCode)) {
		input.value = input.value.slice(0, len);
		input.form[(getIndex(input)+1) % input.form.length].focus();
		input.form[(getIndex(input)+1) % input.form.length].select();
	  }

	  function containsElement(arr, ele) {
		var found = false, index = 0;
		while(!found && index < arr.length)
		if(arr[index] == ele)
		found = true;
		else
		index++;
		return found;
	  }

	  function getIndex(input) {
		var index = -1, i = 0, found = false;
		while (i < input.form.length && index == -1)
		if (input.form[i] == input)index = i;
		else i++;
		return index;
	  }

		sKeyStr =sKeyStr+input.value;
		return true;  

}//End of function autoTab()


function validateCandidate(examId){

document.getElementById("examId").value = examId;

 
var AjaxObject = {
			handleSuccess:function(o){
				// This member handles the success response and passes the response object o to AjaxObject's processResult member.
				this.processResult(o);
			},
			handleFailure:function(o){
				// Failure handler
				
			},
			processResult:function(o){
				
				var candidateStatus = o.responseText;	
				if(candidateStatus == "SessionExpired")
                {                             
                    sessionExpired();
					return false;
                }     
				if (candidateStatus=="Debarred"){
					//alert('Debarred. Please Contact the MT-2 ');
					alert(DGMT.Messages.CANDEXAMINATION.Debarred);
					return;
				} else {
					var i = 1;
					document.getElementById('errorMsgArea').innerHTML='';
					var keyVal='';
					for(i = 1; i<=8 ; i++) {
						document.getElementById('secKey'+i).value='';
					}
					for(i = 1; i<=8 ; i++) {
						keyVal = keyVal + document.getElementById('secKey'+i).value;
					}
									
					$(document).ready(function() {     
						var id = $('#dialog18'); 
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
						$('#mask').show();
						$("#dialog19").show();
					 });		
					document.getElementById('secKey1').focus();
				}
			},
			startRequest:function() {				
				//alert("examId : "+document.getElementById("examId").value);
				var url ='validateCandidate.dgmt?examId='+examId;  
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

	<% OleExam exam = (OleExam)session.getAttribute("exam"); %>

  <div id="breadcrumb"><strong>Home</strong></div>
  <div id="wrapper" class="clearfix">    
    <!--  begin bodycontent -->
   <div id="maincol" style="float:none;margin:auto">
    
   <div class="fleft" style="width:100%; font-size:18px; padding-top:250px;margin:auto" align="center">
      <h2>Welcome to the On-line Examination System</h2>
    </div>
    </div>	
  
     <!-- end bodycontent -->	 
</div>     
<body onload="validateCandidate(<%=exam.getId()%>)">
       
<form name="secForm">  

	<input type="hidden" name="examId" id="examId" value="<%=request.getParameter("examId")%>"/>
	<input type="hidden" name="debar"/>
<div id="boxes">
<div id="dialog18" class="window" style="height: auto; width: 35%">
<div id="errorMsgArea"></div>
<table width="100%" border="0" cellpadding="0" cellspacing="0">  
	<tr> 
		<td align="left" valign="top">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			 
				<tr>
					<td align="left"><p  style="font-weight:bold;text-align:center">Enter Secure Key</p></td>
				 
				</tr>
			 
	 
				<tr><td>&nbsp;</td></tr>
				<tr>
				<td align="left">
				<p style="text-align:center">
			<input id="secKey1"  onKeyUp="return autoTabSecureKey(this, 1, event);" size="1" maxlength="1" class="skey" value="1"> 
			<input id="secKey2"  onKeyUp="return autoTabSecureKey(this, 1, event);" size="1" maxlength="1" class="skey" value="2"> 
			<input id="secKey3"  onKeyUp="return autoTabSecureKey(this, 1, event);" size="1" maxlength="1" class="skey" value="3">
			<input id="secKey4"  onKeyUp="return autoTabSecureKey(this, 1, event);" size="1" maxlength="1" class="skey" value="4"> 
			<input id="secKey5"  onKeyUp="return autoTabSecureKey(this, 1, event);" size="1" maxlength="1" class="skey" value="5"> 
			<input id="secKey6"  onKeyUp="return autoTabSecureKey(this, 1, event);" size="1" maxlength="1" class="skey" value="6">
			<input id="secKey7"  onKeyUp="return autoTabSecureKey(this, 1, event);" size="1" maxlength="1" class="skey" value="7"> 
			<input id="secKey8"  size="1" maxlength="1" class="skey" value="8">   
				</p></td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td align="center">
					<input type="button" value="Continue" class="button"  onclick="validateSecureKey()" />  
					<input type="button" value="Cancel" class="button close"  onClick="closeSecureWindow();"/>
					</td>
				</tr>
			 
		</table>
		</td>  
	</tr>
</table>  
</div>
<input type="hidden" name="canSecKey"/>
</form>
<!-- Mask to cover the whole screen -->
<div id="mask"></div>
</div>
  <!-- end bodycontent -->	
