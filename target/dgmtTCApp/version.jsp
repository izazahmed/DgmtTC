<%@page language="java" import="java.util.*" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<% Properties prop = new Properties();

prop.load(getServletContext().getResourceAsStream("/META-INF/MANIFEST.MF"));
%>

<%= prop.getProperty("Implementation-Title") %>-<%= prop.getProperty("Implementation-Build") %>-2010-04-20;
