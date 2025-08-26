<%@page import="com.smartValue.web.AppContext"%>
<%@page import="com.smartvalue.apigee.configuration.AppConfig"%>
<%@page import ="com.smartvalue.apigee.configuration.infra.ManagementServer"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.InputStream"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<head> 	<script src="https://accounts.google.com/gsi/client" async></script> </head>



<body>
	<% 	
		AppConfig ac = AppContext.getAppConfig(application);
		ManagementServer ms = AppContext.getApigeeManagementServer(session) ;
		
		String clientId = ac.getGoogleWebAppCredential().getClient_id() ; //"455673897131-f610c9tau1i582tpk8nq2q5794qdb1oi.apps.googleusercontent.com" ; //"743562068929-2m0gujbpdcs9g3gebrroeaj4hbkelc3b.apps.googleusercontent.com" ;
	    String contextPath = request.getContextPath();
	    String xx = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath ;
		String callbackUrl = xx+"/ResourceManager/loginWithGoogle/authCodeHandler.jsp" ;
		String errorCallback = xx + "/ResourceManager/loginWithGoogle/errorCallBack.jsp" ; 
		
		if ( ms == null )
		{
			ms = new ManagementServer(ac); 
			AppContext.setApigeeManagementServer(session, ms) ;
		}
		else 
		{ 
			if (ms.getGoogelAccessToken() != null)
			{//--- Already Logged before to Google Cloud 
				response.sendRedirect("../ApigeeAdmin/index.jsp?message=AlreadyLogedToGooleCloud") ; 
			}
			//ms.setOnPremise(false) ;  
		}
	%>
	
	You Need To Authorize Access to Your Google Cloud Account from this Web Application
	<br>   
	
	<script>
	function requestCode()
	{
		const client = google.accounts.oauth2.initCodeClient({
			  client_id: '<%=clientId %>',
			  scope: 'https://www.googleapis.com/auth/cloud-platform' ,
			  ux_mode: 'redirect',
			  redirect_uri: "<%=callbackUrl%>",
			  error_callback : "<%=errorCallback%>",
			  state: "YOUR_BINDING_VALUE"
				});
		client.requestCode(); 
	
	}
	</script>

	<button onclick="requestCode();">Grant SmartTool Access to Your Google Account</button>
	
	
</body>
</html>