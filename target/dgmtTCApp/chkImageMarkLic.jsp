<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <title>Edit</title>
</head>

<%@ page import="java.io.*,com.isavvix.tools.*,java.util.Hashtable" %><%

String base_name = "image";
String user_vdir = application.getRealPath("/")+"/DGMTImages/";

File currentDir = new File(application.getRealPath(request.getServletPath())).getParentFile();
File user_dir = new File(currentDir,user_vdir);

String picture                = base_name+"-pic.jpg";
String annotation             = base_name+"-pic.xml";
String annotation_image       = base_name+"-pic.png";
String picture_and_annotation = base_name+"-cmt.jpg";

%>
<style>
   table { font-family: Helvetica; font-size: 10pt; }
</style>

<body bgcolor="#c0c0c0" topmargin="2" marginheight="2">

<div align="center">

<table>

<tr><td>
   <b>Before running the example</b> copy <code>upload.jar</code> from the example's directory<br>
   to <code>&lt;your_application_dir&gt;/WEB-INF/lib</code></code><br>
   This file contains classes for processing file uploads on the server (the source code inside JAR).
</td></tr>

<tr><td><applet name="annotator" codebase="applet/" archive="imagemark.jar" code="Annotator.class" width="700" height="500"> 

    <% if ((new File(user_dir,picture)).exists()) { %>
	<param name="picture" value="<%=user_vdir+"/"+picture%>">
	<% if ((new File(user_dir,annotation)).exists()) { %>
	    <param name="annotation" value="<%=user_vdir+"/"+annotation%>">
	<% } %>
    <% } %>
    <param name="cliparts" value="cliparts.zip">
</applet></td></tr>

<tr><td><table width="100%"><tr>

<td><form><input type="button" value="Save" onclick="document.applets['annotator'].submit('save.jsp')"></form></td>

<td align="right"><form action="upload.jsp" method="post"
	enctype="multipart/form-data">Your image <input name="user_file" type="file"><input 
	type="submit" value="Upload"></form></td>
</tr></table></td></tr>

</table>

</div>

</body>
</html>