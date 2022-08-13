<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" >
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Directorate General of Military Training</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<meta name="generator" content="" />
<link rel="stylesheet" type="text/css"
	href="common/css/jquerycssmenu.css" />
<link rel="stylesheet" href="common/css/default.css" type="text/css" />
<link rel="stylesheet" type="text/css" media="all"
	href="common/css/calendar-win2k-cold-1.css" />
<script type="text/javascript" src="common/js/common.js"></script>
<script type="text/javascript" src="common/yui/utilities/utilities.js"></script>
<script type="text/javascript" src="common/yui/datasource/datasource-min.js"></script>
<script type="text/javascript" src="common/yui/datatable/datatable-min.js"></script>
<script type="text/javascript" src="common/yui/paginator/paginator-min.js"></script>
<script type="text/javascript" src="common/yui/json/json-min.js"></script>
<script type="text/javascript" src="common/yui/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="common/yui/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="common/yui/event/event-min.js"></script>
<script type="text/javascript" src="common/yui/connection/connection-min.js"></script>
<script type="text/javascript" src="common/js/utility.js"></script>
</head>

<body>
<div id="pagewidth"><!--begin header -->
<div id="header">
<div id="logo"><img src="common/images/logo_DGMT.png" 	alt="Directorate General of Military Training" width="568" height="46" />
<div class="logout">
	<a href="javascript:void(0);" onclick="download('Candidate_Examination_Help.pdf')"><img	src="common/images/help.png" alt="Help" border="0" /></a>
</div>
</div>
<div id="myjquerymenu" class="jquerycssmenu">

<div class="date">
<script>
	var now = new Date();
	var days = new Array('Sunday', 'Monday', 'Tuesday', 'Wednesday',
			'Thursday', 'Friday', 'Saturday');
	var months = new Array('Jan', 'Feb', 'March', 'Apr', 'May', 'Jun', 'Jul',
			'Aug', 'Sep', 'Oct', 'Nov', 'Dec');
	var date = ((now.getDate() < 10) ? "0" : "") + now.getDate();
	function fourdigits(number) {
		return (number < 1000) ? number + 1900 : number;
	}
	today = days[now.getDay()] + ", " + months[now.getMonth()] + " " + date
			+ ", " + (fourdigits(now.getYear()));
	document.write(today);

</script></div>
</div>
<!-- end of header --></div>
<script>
var strongpassword = '<%=session.getAttribute("strongpassword")%>';

function saveChangePassword()
{
	var res = validateSecurity();
	if(res != false)
	{
		document.forgotPassword.action="saveOrUpdateChangeCandidatePassword.dgmt";
		document.forgotPassword.method="post";
		document.forgotPassword.submit();	
	}
}

function validateSecurity()
{
	var newPassword = document.getElementById("newpassword").value;
	var retypePassword = document.getElementById("retypepassword").value;	
	
	var secAnswer = document.getElementById("secAnswer").value
	var securityAnswer = document.getElementById("securityAnswer").value;
	

	if(isEmpty(trim(document.forgotPassword.user.value)))
	{
		//alert("Please enter user id.");
		alert(DGMT.Messages.FORGOTPWD.UserID);
		setTimeout('document.forgotPassword.user.focus()', 0);
		document.forgotPassword.user.style.background="#F7CB6C";
		return false;
	}
	else
	{
		document.forgotPassword.user.style.background="#FFffff"
	}

	if(isEmpty(trim(document.forgotPassword.selSecurityQuest.value)))
	{
		//alert("Please select security question.");
		alert(DGMT.Messages.FORGOTPWD.SelSecQues);
		setTimeout('document.forgotPassword.selSecurityQuest.focus()', 0);
		document.forgotPassword.selSecurityQuest.style.background="#F7CB6C";
		return false;
	}
	else
	{
		document.forgotPassword.selSecurityQuest.style.background="#FFffff"
	}
		
	if(isEmpty(trim(document.forgotPassword.securityAnswer.value)))
	{
		//alert("Please enter security answer.");
		alert(DGMT.Messages.FORGOTPWD.EntrSecAns);
		setTimeout('document.forgotPassword.securityAnswer.focus()', 0);
		document.forgotPassword.securityAnswer.style.background="#F7CB6C";
		return false;
	}
	else
	{
		document.forgotPassword.securityAnswer.style.background="#FFffff"
	}
	if(trim(newPassword) == '')
	{
		//alert("Please enter new password.");
		alert(DGMT.Messages.FORGOTPWD.NewPwd);		
		setTimeout('document.forgotPassword.newpassword.focus()', 0);
		document.forgotPassword.newpassword.style.background="#F7CB6C";		
		return false;
	}
	else
	{
		document.forgotPassword.newpassword.style.background="#FFffff"
	}
	if(checkForspl(newPassword))
	{
		//alert("Please enter new password.");
		alert("Please Don't use # & + symbols ");
		document.getElementById("newpassword").focus();
		document.getElementById("newpassword").style.background="#F7CB6C";
		return false;
	}
	else
	{
		document.getElementById("newpassword").style.background="#FFffff"
	}
	if(strongpassword == 'true')
	{
		if(newPassword.length < 6)
		{
			//alert("Password must contain at least six characters!");
			alert(DGMT.Messages.FORGOTPWD.PwdLength);
			document.getElementById("newpassword").focus();
			document.getElementById("newpassword").style.background="#F7CB6C";
			return false;
		}
		else
		{
			document.getElementById("newpassword").style.background="#FFffff"
		}
		if(isSpclChar(newPassword))
		{
			//alert('Password should contain atleast one special character');
			alert(DGMT.Messages.FORGOTPWD.TestSplChar);
			document.getElementById("newpassword").focus();
			document.getElementById("newpassword").style.background="#F7CB6C";
			return false;
		}
		else
		{
			document.getElementById("newpassword").style.background="#FFffff"
		}
		re = /[0-9]/;
		if(!re.test(newPassword)) 
		{
			//alert("Password must contain at least one number (0-9)!");
			alert(DGMT.Messages.FORGOTPWD.TestNumber);
			document.getElementById("newpassword").focus();
			document.getElementById("newpassword").style.background="#F7CB6C";
			return false;
		}
		else
		{
			document.getElementById("newpassword").style.background="#FFffff"
		}
		re = /[A-Z]/;
		if(!re.test(newPassword))
		{
			//alert("Password must contain at least one uppercase letter (A-Z)!");
			alert(DGMT.Messages.FORGOTPWD.TestUpCase);
			document.getElementById("newpassword").focus();
			document.getElementById("newpassword").style.background="#F7CB6C";
			return false;
		}
		{
			document.getElementById("newpassword").style.background="#FFffff"
		}
	}
	if(trim(retypePassword) == '')
	{
		//alert("Please enter re-type new password.");
		alert(DGMT.Messages.FORGOTPWD.ReTypePwd);
		setTimeout('document.forgotPassword.retypepassword.focus()', 0);		
		document.forgotPassword.retypepassword.style.background="#F7CB6C";
		return false;
	}
	else
	{		
		document.forgotPassword.retypepassword.style.background="#FFffff"
	}	
	if(trim(newPassword) != trim(retypePassword))
	{
		//alert("New Password is not matching with Re-Type New Password.");
		alert(DGMT.Messages.FORGOTPWD.TestNewPWD);
		document.getElementById("newpassword").focus();
		document.getElementById("newpassword").style.background="#F7CB6C";
		return false;
	}
	else
	{
		document.getElementById("newpassword").style.background="#FFffff"
	}
}

function cancelChangePassword()
{	
	document.forgotPassword.action="logout.dgmt";
	document.forgotPassword.method="post";
	document.forgotPassword.submit();
}

</script>   

<div id="breadcrumb">&nbsp;</div>
<div id="wrapper" class="clearfix"><s:form theme="simple"
	action="#" name="forgotPassword" autocomplete="off">

	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		
				
		<tr>
			<td align="center" valign="center">
				
			<table width="400" border="0" cellpadding="3" cellspacing="0"
				style="border: 1px solid #ccc; background: #fff; margin-top: 100px">
				
				<s:if test="hasActionErrors()"> 
				<tr>
					<td  colspan="2" align="left" class="label"><font color="red"><s:actionerror/></font></td>		 
				</tr>
				</s:if>

				<tr class="Title">
					<td colspan="2" align="left" class="label">Forgot Password</td>
				</tr>

				<tr>
					<td class="label" align="right" nowrap>User Id:<strong><span class="mandate">*</span></strong></td>
					<td valign="top"  align="left">
					<input type="text" id="user" name="user" size="20"/> 
					</td>
				</tr>

				<tr>
					<td class="label" align="right" nowrap>Security Question:<strong><span class="mandate">*</span></strong></td>
						 <td valign="top" align="left">
							 <s:select name="selSecurityQuest" id="secQuest"
							   headerKey=""
							   headerValue="-- Please Select --"
							   list="securityQuestionList" listKey="id" listValue="desc"/> 
						</td>
				</tr>
				<input type="hidden" name="secAnswer" id="secAnswer"/>	
				<input type="hidden" name="csrfPreventionSalt" value="<%=request.getAttribute("csrfPreventionSalt")%>"/>
				<tr>
					<td class="label" align="right" nowrap>Security Answer:<strong><span
						class="mandate">*</span></strong></td>
					<td valign="top" align="left"><input type="text"
						id="securityAnswer" name="securityAnswer" size="50"></td>
				</tr>

				<tr>
					<td class="label" align="right" nowrap>New Password:<strong><span class="mandate">*</span></strong></td>
					<td valign="top"  align="left">
					<input type="password" id="newpassword" name="newpassword" size="20" autocomplete="off"/> 
					</td>
				</tr>

				<tr>
					<td class="label" align="right" nowrap>Re-Type New Password:<strong><span class="mandate">*</span></strong></td>
					<td valign="top" align="left">
					<input type="password" id="retypepassword" name="retypepassword" size="20" autocomplete="off"/> 
					</td>
				</tr>

				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>

				<tr class="tablefooter">
					<td colspan="2" align="right">
					
						 <input name="button1" type="button" class="button" id="button1"  value="Done" onClick="saveChangePassword();"/>		
						 <input name="button12" type="button" class="button" id="button12" value="Cancel" onClick="cancelChangePassword();"/>							
					 </td>
				</tr>
				
				 <%if("true".equalsIgnoreCase((String)session.getAttribute("strongpassword"))){%>	
				<tr>
					<td colspan="2" align="left">
					<ol style="color:red; line-height:20px; font-weight:bold; font-size:12px;">
						<li>Password should have at least one capital alphabet (A-Z)</li>
						<li>Password size must be at least 6 characters</li>
						<li>Password should have at least one special character</li>
						<li>Password should have at least one numeral (0-9)</li>
					</ol>					
					</td>
			   </tr>
			   <%}%>

			</table>
			</td>
		</tr>
		<tr>
			<td>
		</tbody>
		</td>
		</tr>
		<tr>
			<td height="30">&nbsp;</td>
		</tr>
	</table>
</s:form> <!-- end bodycontent --></div>
</div>
<!--begin footer -->
<div id="footer">Copyright © <script>
var footerDate = new Date();
function fourdigits(number)	{
    return (number < 1000) ? number + 1900 : number;}
document.write(fourdigits(footerDate.getYear()));
</script>
	 by Indian Army </div><!--begin footer -->
</body>
</html>
<script>
setTimeout('document.forgotPassword.user.focus()', 0);
</script>