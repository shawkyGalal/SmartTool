 <%@page import ="java.io.InputStream"%>
 <%@page import ="com.smartvalue.moj.clients.environments.*"%>
 <%@page import ="com.smartvalue.moj.clients.environments.Environment"%>
 <%@page import ="com.smartvalue.apigee.rest.schema.ApigeeAccessToken"%>
 <%@page import="com.smartValue.SmartToolLoginAuthenticator"%>
<!DOCTYPE html>
<html>
<head>
<title>NajizCallBack Page</title>
</head>
<body>

<%@include file="intialize.jsp" %>
<%
	String authorizationCode = request.getParameter("code") ;
	%>Received Authorization Code : <%=authorizationCode%><% 
	
	mojEnv.setAccessToken(authorizationCode);
	
	try{
	// Simulate Login To SmartTool 
	SmartToolLoginAuthenticator smartToolLoginAuthenticator = new SmartToolLoginAuthenticator ( mojEnv , request , response) ; 
	smartToolLoginAuthenticator.authenticate(session, request, response, out, application);
	}
	catch(Exception e ){
		out.println("Failure to Authenticate SmartTool Due To : " + e.getMessage()) ; 
		response.sendRedirect("dashboard.jsp") ;
	}

%>
</body>
</html>