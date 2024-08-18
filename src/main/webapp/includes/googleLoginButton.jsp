<%@page import="com.smartvalue.google.iam.GoogleWebAppCredential"%>
<%@page import="com.smartValue.web.AppContext"%>
<%@page import="com.smartvalue.apigee.configuration.AppConfig"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
				AppConfig ac = AppContext.getAppConfig(application); // (AppConfig) application.getAttribute(AppContext.APP_CONFIG_VAR_NAME) ;
				GoogleWebAppCredential googleWebAppCredential = ac.getGoogleWebAppCredential(); 
				String googleLoginHandler = "/SmartTool/ResourceManager/loginWithGoogle/loginHandler.jsp" ; 
				String client_id= googleWebAppCredential.getClient_id() ;    
		 
				%>
				<!--  Google Sign in  Using  Google button  
				<div id="g_id_onload"
				     data-client_id="<%=client_id %>"
				     data-context="signin"
				     data-ux_mode="popup"
				     data-login_uri="<%=googleLoginHandler%>"
				     data-auto_prompt="false">
				</div> -->
				
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
				     data-login_uri="<%=googleLoginHandler%>"
				     data-nonce=""
				     data-itp_support="true">
				</div>
</body>
</html>