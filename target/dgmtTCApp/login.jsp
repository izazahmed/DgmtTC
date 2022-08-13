<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>DGMT Login</title>
<link rel="stylesheet"  type="text/css" href="common/css/login.css"  media="screen">

<script type="text/javascript" src="common/js/utility.js"></script>
<script>

function validateUser(){ 
	if(document.login.j_username.value == "")
	{
		//alert("Please enter user name.");
		alert(DGMT.Messages.LOGIN.Uname);
		document.login.j_username.focus();
		return false;
	}
	else if(document.login.j_password.value == "")
	{
		//alert("Please enter password.");
		alert(DGMT.Messages.LOGIN.Pwd);
		document.login.j_password.focus();
		return false;
	}
	else
	{
		document.login.action = "j_security_check";
		document.login.method="post";
		document.login.submit();
	}
}

function forgotPassword()
{
	document.login.action = "forgotPassword.dgmt?isFpwd=true";
	document.login.method="post";
	document.login.submit();
}

function check()
{
//	var j_username_select = document.getElementById("j_username_select").value;
	var j_username_text = document.getElementById("userName").value;
	var j_username = j_username_text;
	document.getElementById("userName").value = j_username;
}

</script>
</head>

<body>
<div class="login">
<div id="login">
<s:form method="POST" name="login" id="loginForm" action="tokenCheck" onsubmit="check()" autocomplete="off">
<input type="hidden" name="csrfPreventionSalt" value="<%= request.getAttribute("csrfPreventionSalt") %>"/>
<h1>Sign In</h1>
<%if("true".equalsIgnoreCase((String)session.getAttribute("sessionExp"))){%>
		<tr>
			<td colspan="3" align="center">
				<b>
					<span class="invalid"> <img src="common/images/alert_icon.gif" alt="Alert" width="21" height="20" align="absmiddle"> &nbsp;&nbsp;<script language="javascript">document.write("Your Session has expired. Please login again.");</script>
					<%
					session.removeAttribute("sessionExp");
					%>
					</span>
				</b>
			</td>
		</tr><br>
<%}else if("true".equalsIgnoreCase((String)session.getAttribute("failed"))){%>																				
		<tr>
			<td colspan="3" align="center">
				<b>
					<span class="invalid"> <img src="common/images/alert_icon.gif" alt="Alert" width="21" height="20" align="absmiddle"> &nbsp;&nbsp;<script language="javascript">document.write("Invalid User Name or Password / Data not available.");</script>
					<% session.removeAttribute("failed");
					%></span>
				</b>
			</td>
		</tr><br>
<%}else if("true".equalsIgnoreCase((String)session.getAttribute("passwordChanged"))){%>																				
		<tr>
			<td colspan="3" align="center">
				<b>
					<span class="invalid"> <img src="common/images/alert_icon.gif" alt="Alert" width="21" height="20" align="absmiddle"> &nbsp;&nbsp;<script language="javascript">document.write("Your password changed successfully. Please login with new password.");</script>
					<% session.removeAttribute("passwordChanged");
					%></span>
				</b>
			</td>
		</tr><br>
<%}else if("true".equalsIgnoreCase(request.getParameter("authorised"))){%>																				
<tr>
	<td colspan="3" align="center">
		<b>
			<span class="invalid"><img src="common/images/alert_icon.gif" alt="Alert" width="21" height="20" align="absmiddle"> &nbsp;&nbsp;<script language="javascript">document.write("You are not authorized to view this page. Please login again.");</script></span>
		</b>
	</td>
</tr>	  
<%}%>
<p><label>Username</label>
	<input type="text" name="foilautofilluserName" class="foilautofilluserName"/>
	<input type="text" size="18" name="j_username"  id="userName" autocomplete="off"/>
</p>
<p><label>Password</label>
	<input type="password" name="foilautofill" class="foilautofill"/>
	<input name="j_password" type="password" size="18" autocomplete="off"/>
</p>
<p><label>&nbsp;</label>
	<input name="button" type="submit" class="loginbutton" value="Login">
</p>
<br></br>
Forgot Password. Please click <a href="#" onClick="forgotPassword()" >here</a> 
</s:form>
</div>
</div>
</body>
</html>