
<%@page language="java" import="java.util.*" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<% Properties prop = new Properties();
 
 prop.load(application.getResourceAsStream("/META-INF/MANIFEST.MF")); 
 
 %>
 Implementation Version:<%= prop.getProperty("Implementation-Build") %>

