<%@page import="com.smartValue.web.AppContext"%>
<%@page import="com.google.api.client.googleapis.auth.oauth2.GoogleIdToken"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@page import ="com.smartvalue.apigee.configuration.AppConfig"%>
<%@page import ="com.smartvalue.google.iam.GoogleWebAppCredential"%>


<!DOCTYPE html>
<html>
<head>
<script src="https://accounts.google.com/gsi/client" async></script>

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
	{
		out.print("You Need To Login To Your Google Account To Access the following Systems") ; 
		String contextPath = request.getContextPath(); 
		AppConfig ac = AppContext.getAppConfig(application); // (AppConfig) application.getAttribute(AppContext.APP_CONFIG_VAR_NAME) ;
		GoogleWebAppCredential googleWebAppCredential = ac.getGoogleWebAppCredential(); 
		String data_login_uri = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath+"/ResourceManager/loginWithGoogle/loginHandler.jsp" ; 
		String client_id= googleWebAppCredential.getClient_id() ;    
		 
		%>
		<!--  Google Sign in  Using  Google button --> 
		<div id="g_id_onload"
		     data-client_id="<%=client_id %>"
		     data-context="signin"
		     data-ux_mode="popup"
		     data-login_uri="<%=data_login_uri%>"
		     data-auto_prompt="false">
		</div>
		
		<div class="g_id_signin"
		     data-type="standard"
		     data-shape="rectangular"
		     data-theme="filled_blue"
		     data-text="signin_with"
		     data-size="large"
		     data-logo_alignment="left">
		</div>
		
		<!--  Google Sign in  Using  Enable OneTap --> 
		
		<div id="g_id_onload"
		     data-client_id="<%=client_id %>"
		     data-context="use"
		     data-login_uri="<%=data_login_uri%>"
		     data-nonce=""
		     data-itp_support="true">
		</div>
	<%} %>
	<h1><a href = "ApigeeAdmin/index.jsp" target = "Najiz"> Apigee Administration </a></h1>
	<h1><a href = "ApigeeX/index.jsp" target = "Apigee X"> Apigee X </a></h1>
	<h1><a href = "SDK_Generator/InputParams.jsp" target = "SDK Generator"> SDK Generator </a></h1>
	
</body>
</html>