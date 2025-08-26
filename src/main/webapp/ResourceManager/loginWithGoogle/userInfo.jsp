<%@page import="com.smartValue.web.AppContext"%>
<%@page import="com.smartvalue.apigee.resourceManager.Renderer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import = "com.google.api.client.googleapis.auth.oauth2.GoogleIdToken" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	GoogleIdToken googleIdToken = AppContext.getGoogleIdToken(session);
String accessToken = (AppContext.getApigeeManagementServer(session) != null) ? AppContext.getApigeeManagementServer(session).getAccessToken().getAccess_token() :"Only JWT.." ; 
	%>
	<img alt="<%=googleIdToken.getPayload().get("name")%>" src="<%=googleIdToken.getPayload().get("picture")%>">
	Google ID Token Details : 
	<%	
	out.print(Renderer.objectToHtmlTable(googleIdToken.getPayload())); 
	%>
	Google API Access Token : <br><%=accessToken %>
	<br> <a href = "../index.jsp">Home</a>

</body>
</html>