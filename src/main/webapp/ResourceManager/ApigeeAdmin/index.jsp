<%@page import="com.smartvalue.apigee.rest.schema.ApigeeAccessToken"%>
<%@page import="com.smartvalue.apigee.rest.schema.AccessToken"%>
<%@page import="com.google.api.client.googleapis.auth.oauth2.GoogleIdToken"%>
<%@page import="com.smartvalue.apigee.resourceManager.Renderer"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import ="com.smartvalue.apigee.configuration.infra.ManagementServer"%>
<%@page import="com.smartvalue.google.iam.auto.GoogleAccessToken"%>
<%@page import="com.smartValue.web.AppContext"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<head>
	<script src="https://accounts.google.com/gsi/client" async></script>
</head>

<body>
	
	<a href = "InfraSelector.jsp">Select Apigee Infrastructure </a>
	
	<% 
		ManagementServer ms = AppContext.getApigeeManagementServer(session); 
		if (ms != null  ) 
		{
			out.print ("Current Infra Name : " + ms.getInfraName() ) ;
			AccessToken at = ms.getAccessToken() ; 
			GoogleIdToken googleIdToken = null; 
			ApigeeAccessToken apigeeAccessToken = null ; 
			if (at instanceof GoogleAccessToken)
			{
				GoogleAccessToken googleAccessToken = (GoogleAccessToken) at ;
				googleIdToken = googleAccessToken.getGoogleIdToken();
			}
			else if (at instanceof ApigeeAccessToken) 
			{
				googleIdToken = AppContext.getGoogleIdToken(session) ; 
			}
			
			if ( googleIdToken != null)
			{
				out.print(Renderer.objectToHtmlTable(googleIdToken.getPayload()));
			}
 		  	 
		}
		else {return ; }
	%>
	<h1> Apigee Operational Tasks </h1>
	<table border = 1>
		<tr><td><a href = "proxies/proxies.jsp" target = "Proxies">Proxies</a></td></tr>
		<tr><td><a href = "environments/Environments.jsp" target = "envs">Environments</a></td></tr>
		<tr><td><a href = "products/products.jsp" target = "products" >Products </a></td></tr>
		<tr><td><a href = "developers/developers.jsp" target = "developers">Developers  </a></td></tr>
		<tr><td><a href = "apps/apps.jsp" target = "applications">Applications  </a></td></tr>
		<tr><td><a href = "kvms/kvms.jsp" target = "kvms">Key value Maps  </a></td></tr>
	
	</table>
	
	<h1> Special Queries </h1>
	<table border = 1>
		<tr><td><a href = "specialQueries/proxiesUsingTargetServer/inputParams.jsp" target = "listProxiesUsingTargetServer" >listProxiesUsingTargetServer </a></td></tr>
		<tr><td><a href = "specialQueries/listProxiesNotDeployed/inputParams.jsp" target = "listProxiesNotDeployed">listProxiesNotDeployed  </a></td></tr>
		<tr><td><a href = "specialQueries/productsWithoutProxies/inputParams.jsp" target = "productsWithoutProxies">productsWithoutProxies  </a></td></tr>
		<tr><td><a href = "specialQueries/ProxiesWithoutPolices/inputParams.jsp" target = "ProxiesWithoutPolices">ProxiesWithoutPolices  </a></td></tr>
		<tr><td><a href = "OasConsistancy/inputParams.jsp" target = "OasConsistancy">Apigee Flows & OAS Consistancy Check  </a></td></tr>
	
	</table>
	
	
	<h1> Apigee Administration Tasks </h1>
	<table border = 1>
		<tr><td><a href = "messageProcessor/MessageProcessors.jsp"  target = "messageProcessors">message Processors  </a></td></tr>
		<tr><td><a href = "routers/routers.jsp"  target = "routers">Routers </a></td></tr>
	</table>
	
	<h1> REST Services </h1>
	<table border = 1>
		<tr><td><a href = "rest/v1/organizations/"  target = "Rest Services ">REST Services  </a></td></tr>
	</table>
	
	
	
	
</body>
</html>