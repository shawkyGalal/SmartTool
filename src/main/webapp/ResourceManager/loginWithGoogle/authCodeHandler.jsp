<%@page import="com.google.api.client.googleapis.auth.oauth2.GoogleIdToken"%>
<%@page import="com.smartvalue.google.iam.auto.GoogleAccessToken"%>
<%@page import ="com.smartvalue.apigee.configuration.infra.ManagementServer"%>

<%@page import="org.springframework.security.crypto.codec.Base64"%>
<%@page import="java.nio.charset.Charset"%>
<%@page import="com.mashape.unirest.http.HttpResponse"%>
<%@page import="com.mashape.unirest.http.Unirest"%>
<%@page import="com.smartValue.web.AppContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	ManagementServer ms = AppContext.getApigeeManagementServer(session); 
	String authCode = request.getParameter("code") ;
	String contextPath = request.getContextPath(); 
	String redirectUri = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath+"/ResourceManager/loginWithGoogle/authCodeHandler.jsp" ; 
	ms.webLogin(authCode, redirectUri) ; 
	GoogleAccessToken googleAccessToken = ms.getGoogelAccessToken() ;
	
	GoogleIdToken googleIdToken =  googleAccessToken.getGoogleIdToken() ; 
	AppContext.setGoogleIdToken(session , googleIdToken ) ; 

	response.sendRedirect("../ApigeeAdmin/index.jsp"); 
	
%>
</body>
</html>