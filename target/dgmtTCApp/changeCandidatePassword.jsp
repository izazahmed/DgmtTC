<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" >
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en" >
<head>
<title>Directorate General of Military Training</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<meta name="generator" content="" />
<link rel="stylesheet" type="text/css" href="common/css/jquerycssmenu.css" />
<link rel="stylesheet" href="common/css/default.css" type="text/css" />
<link rel="stylesheet" type="text/css" media="all" href="common/css/calendar-win2k-cold-1.css" />
<script type="text/javascript" src="common/js/common.js"></script>
<script>
var oldPswd = '<%=session.getAttribute("dob")%>';
var strongpassword = '<%=session.getAttribute("strongpassword")%>';

function saveChangePassword()
{
	var oldPassword = document.getElementById("oldpassword").value;	
	var newPassword = document.getElementById("newpassword").value;
	var retypePassword = document.getElementById("retypepassword").value;
	


	if(trim(oldPassword) == '')
	{
		//alert("Please enter old password");
		alert(DGMT.Messages.CHANGECANDIDATEPWD.OldPwd);
		document.getElementById("oldpassword").focus();
		document.getElementById("oldpassword").style.background="#F7CB6C";
		return false;
	}
	else
	{
		document.getElementById("oldpassword").style.background="#FFffff"
	}
	if(trim(newPassword) == '')
	{
		//alert("Please enter new password.");
		alert(DGMT.Messages.CHANGECANDIDATEPWD.NewPwd);
		document.getElementById("newpassword").focus();
		document.getElementById("newpassword").style.background="#F7CB6C";
		return false;
	}
	else
	{
		document.getElementById("newpassword").style.background="#FFffff"
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
			alert(DGMT.Messages.CHANGECANDIDATEPWD.PwdLength);
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
			alert(DGMT.Messages.CHANGECANDIDATEPWD.TestSplChar);
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
			alert(DGMT.Messages.CHANGECANDIDATEPWD.TestNumber);
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
			alert(DGMT.Messages.CHANGECANDIDATEPWD.TestUpCase);
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
		//alert("Please enter re-type password.");
		alert(DGMT.Messages.CHANGECANDIDATEPWD.ReTypePwd);
		document.getElementById("retypepassword").focus();
		document.getElementById("retypepassword").style.background="#F7CB6C";
		return false;
	}
	else
	{
		document.getElementById("retypepassword").style.background="#FFffff"
	}
	if(trim(oldPassword) != oldPswd)
	{
		//alert("Password is not matching.");
		alert(DGMT.Messages.CHANGECANDIDATEPWD.PwdMisMatch);
		document.getElementById("oldpassword").focus();
		document.getElementById("oldpassword").style.background="#F7CB6C";
		return false;
	}
	else
	{
		document.getElementById("oldpassword").style.background="#FFffff"
	}
	if(trim(newPassword) != trim(retypePassword))
	{
		//alert("New Password is not matching with Re-Type New Password.");
		alert(DGMT.Messages.CHANGECANDIDATEPWD.TestNewPWD);
		document.getElementById("newpassword").focus();
		document.getElementById("newpassword").style.background="#F7CB6C";
		return false;
	}
	else
	{
		document.getElementById("newpassword").style.background="#FFffff"
	}
	if(trim(newPassword) == trim(oldPswd))
	{
		//alert("New Password should not be same as current password.");
		alert(DGMT.Messages.CHANGECANDIDATEPWD.TestPWD);
		document.getElementById("newpassword").focus();
		document.getElementById("newpassword").style.background="#F7CB6C";
		return false;
	}
	else
	{
		document.getElementById("newpassword").style.background="#FFffff"
	}
	var secQuest =  document.getElementById("secQuest");
	if(isEmpty(trim(secQuest.value))){
		//alert('Please select security question.');
		alert(DGMT.Messages.CHANGECANDIDATEPWD.SecQuestIsEmpty);
		document.getElementById("secQuest").focus();	
		secQuest.style.background="#F7CB6C";
		return false;
	}
	else
	{
		secQuest.style.background="#FFffff";
	}
	var securityAns =  document.getElementById("securityAns");
	if(isEmpty(trim(securityAns.value))){
		//alert('Please select security answer.');
		alert(DGMT.Messages.CHANGECANDIDATEPWD.SecAnsIsEmpty);
		document.getElementById("securityAns").focus();	
		securityAns.style.background="#F7CB6C";
		return false;
	}
	else
	{
		securityAns.style.background="#FFffff";
	}
	
		document.changePasswordForm.action="saveOrUpdateChangeCandidatePassword.dgmt";
		document.changePasswordForm.method="post";
		document.changePasswordForm.submit();
	

}

</script>
</head>
<body>
<div id="pagewidth">
  <!--begin header -->
  <div id="header" >
    <div id="logo"><img src="common/images/logo_DGMT.png" alt="Directorate General of Military Training" width="568" height="46" />
      <div class="logout"><a href="javascript:void(0);" onclick="download('Candidate_Enrollment_and_Examination_Help.pdf')"><img src="common/images/help.png" alt="Help" border="0" /></a> <a href="#" onclick="forwardToAction('logout.dgmt')"><img src="common/images/logout.png" alt="Logout" border="0" /></a></div>
    </div>
    <div id="myjquerymenu" class="jquerycssmenu">
      
      <div class="date">Welcome<strong class="guest"> <%=request.getUserPrincipal()%> </strong> &nbsp;&nbsp;
        <script>var now = new Date();
    var days = new Array(
      'Sunday','Monday','Tuesday',
      'Wednesday','Thursday','Friday','Saturday');
    var months = new Array(
      'Jan','Feb','March','Apr','May',
      'Jun','Jul','Aug','Sep','Oct',
      'Nov','Dec');
    var date = ((now.getDate()<10) ? "0" : "")+ now.getDate();
    function fourdigits(number)	{
      return (number < 1000) ? number + 1900 : number;}
    today =  days[now.getDay()] + ", " +
       months[now.getMonth()] + " " +
       date + ", " +
       (fourdigits(now.getYear()));
     document.write(today);</script>
      </div>
    </div>
    <!-- end of header -->
  </div>
  <div id="breadcrumb">&nbsp; </div>
  <div id="wrapper" class="clearfix">
	<s:form theme="simple" action="saveOrUpdateChangePassword.dgmt" name="changePasswordForm" autocomplete="off">   
       <!--  begin bodycontent -->
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
	
	<tr>
		<td align="center" valign="center">
		<table width="400" border="0" cellpadding="3" cellspacing="0"
			style="border:1px solid #ccc;background:#fff;margin-top:150px">
			<tr class="Title">
				<td colspan="2" align="left" class="label">Change Password</td>
			</tr>
			<tr>
				<td class="label" align="right" nowrap>Old Password:<strong><span class="mandate">*</span></strong></td>
                <td valign="top" align="left">
				<input type="password" id="oldpassword" name="oldpassword" size="20" readonly="true" autocomplete="off"/>                
				</td>
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
				<td class="label" align="right" nowrap>Security Question:<strong><span class="mandate">*</span></strong></td>
                <td valign="top" align="left">
                <s:select name="selSecurityQuest" id="secQuest"
							   headerKey=""
							   headerValue="-- Please Select --"
							   list="securityQuestionList" listKey="id" listValue="desc"/> 
				</td>
			</tr>

			<tr>
				<td class="label" align="right" nowrap>Security Answer:<strong><span class="mandate">*</span></strong></td>
                <td valign="top"  align="left">
                <input type="text" id="securityAns" name="securityAns" size="50"/> 
			    </td>
			</tr>

			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			
			<tr class="tablefooter">
				<td colspan="2" align="right">
				 <label>
					<input name="button1" type="button" class="button" id="button1" onclick="saveChangePassword();" value="Done" />
				 </label></td>
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
</s:form>

    <!-- end bodycontent -->
  </div>
</div>
<!--begin footer -->
<div id="footer">Copyright © 2017 by Indian Army </div>
<!--begin footer -->
</body>
</html>
<script>
document.onload = document.getElementById("oldpassword").value = oldPswd;
</script>
