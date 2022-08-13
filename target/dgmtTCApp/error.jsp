<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>error page</title>
</head>
<body>
	<%
		HttpSession ses = request.getSession(false);
		if(ses!=null)
		ses.invalidate();
	%>
	You are not authorized to view this page. Please <a href="${pageContext.request.contextPath}/index.jsp">login</a> again.	
</body>
</html>