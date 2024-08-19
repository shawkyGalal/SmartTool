<%@page import="com.smartValue.web.AppContext"%>
<%@page import="com.google.api.client.googleapis.auth.oauth2.GoogleIdToken"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@page import ="com.smartvalue.apigee.configuration.AppConfig"%>
<%@page import ="com.smartvalue.google.iam.GoogleWebAppCredential"%>


<!DOCTYPE html>
<html>
<head>


<meta charset="ISO-8859-1">
<title>MOJ Automation Services </title>
</head>
<body>


	<h2><a href = "NajizLikeSampleApp/index.jsp" target = "Najiz"> Najiz Sample Like Application Using Najiz SDK </a></h2>
	<h2><a href = "NajizLikeSampleApp/js/index.html" target = "Najiz"> Najiz Sample Like Application (JavaScript ) Using Najiz SDK </a></h2>
	<h2><a href = "loginWithGoogle/index.jsp" target = "Google Login" >Login with Google</a></h2>
	
	
	<% 
	
	GoogleIdToken googleIdToken =  AppContext.getGoogleIdToken(session); //(GoogleIdToken)session.getAttribute( AppContext.GOOGLE_ID_TOKEN_VAR_NAME); 
	if (googleIdToken != null)
	{
		%>	
			<img alt="<%=googleIdToken.getPayload().get("name")%>" src="<%=googleIdToken.getPayload().get("picture")%>">
			<a href = "./loginWithGoogle/googleIdLogout.jsp">Google ID Logout</a> 
		<%
	}
	else
	{ %> 
		<jsp:include page="/includes/googleLoginButton.jsp"></jsp:include>	
	<%} %>
	<h1><a href = "ApigeeAdmin/index.jsp" target = "Najiz"> Apigee Administration </a></h1>
	<h1><a href = "ApigeeX/index.jsp" target = "Apigee X"> Apigee X </a></h1>
	<h1><a href = "SDK_Generator/InputParams.jsp" target = "SDK Generator"> SDK Generator </a></h1>
	<h1><a href = "/SmartTool/Company/20/index.jsp" target = "SmartTool"> SmartTool </a></h1>
	
</body>
</html>