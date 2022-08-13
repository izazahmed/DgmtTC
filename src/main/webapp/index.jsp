<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="s" uri="/struts-tags"%>
</head>
<script>
function loadApp(){
	document.index.action = "login.dgmt";
	document.index.method="post";
	document.index.submit();
}
</script>
<body onload="loadApp()">
<s:form name="index"  id="indexForm" autocomplete="off">
</s:form>
</body>
</html>