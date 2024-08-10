<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import ="java.io.InputStream"%>
 <%@page import ="com.smartvalue.moj.clients.environments.*"%>
 <%@page import ="com.smartvalue.moj.clients.environments.Environment"%>
 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@include file="intialize.jsp" %>
<%
	String authURL = mojEnv.getUrlBuilder()
								.withForthAuth(false)
								.withResponseType("code")
								.withScope("openid")
								.buildAuthorizationURL();
	
%>
<a href = <%=authURL%>>Login Using Nafath Mowahad </a>
</body>
</html>