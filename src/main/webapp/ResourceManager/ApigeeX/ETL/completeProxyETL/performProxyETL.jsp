<%@page import="com.google.api.client.googleapis.auth.oauth2.GoogleIdToken"%>
<%@page import="com.mashape.unirest.http.HttpResponse"%>
<%@page import="com.smartvalue.apigee.migration.ProcessResult"%>
<%@page import="com.smartvalue.apigee.migration.ProcessResults"%>
<%@page import="com.smartvalue.apigee.rest.schema.proxy.ProxyServices"%>
<%@page import="com.smartValue.web.AppContext"%>
<%@page import="com.smartvalue.apigee.configuration.AppConfig"%>
<%@page import="com.smartvalue.apigee.resourceManager.Renderer"%>
<%@page import ="com.smartvalue.apigee.rest.schema.application.Application"%>
<%@page import ="com.smartvalue.apigee.rest.schema.organization.Organization"%>
<%@page import ="com.smartvalue.apigee.rest.schema.product.ProductsServices"%>
<%@page import ="com.smartvalue.apigee.rest.schema.proxy.Proxy"%>
<%@page import ="com.smartvalue.apigee.rest.schema.developer.Developer"%>
<%@page import ="com.smartvalue.apigee.rest.schema.server.Postgres"%>

<%@page import ="com.smartvalue.apigee.rest.schema.TargetServer"%>
<%@page import ="com.smartvalue.apigee.rest.schema.virtualHost.VirtualHost"%>
<%@page import ="com.smartvalue.apigee.configuration.infra.ManagementServer"%>
<%@page import ="java.util.*"%>
<%@page import ="java.io.InputStream"%>
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
		GoogleIdToken gidt =  AppContext.getGoogleIdToken(session);
		String userEmail = gidt.getPayload().getEmail();
	
		String destOrgName = request.getParameter("orgSelect"); //"moj-prod-apigee" 
		ManagementServer ms = AppContext.getApigeeManagementServer(session); 
		ms.setOrgName(destOrgName); 
		//----- ETL Starting Loading ----
		ProxyServices proxiesServices =(ProxyServices) ms.getProxyServices(destOrgName); 
		String proxyName = request.getParameter("proxyName") ; 
		ProcessResults eTLResult = proxiesServices.performETL(proxyName , userEmail); 
		ProcessResults successResults = eTLResult.filterFailed(false) ;
		HashMap<Class<?>,ProcessResults>  classifiedResults = eTLResult.classify();
	%>
	<h1>ETL Proxy Results Statistics </h1>
	<table border="1">
		<tr>
			<td>#</td>
			<td>Status </td>
			<td>count</td>
		</tr>
	<%
	int count = 1 ; 
	for (Map.Entry<Class<?>,ProcessResults> entry : classifiedResults.entrySet())
	{
		if (entry.getValue().size() == 0 ){continue ; }
		count++ ; 
		Class<?> clazz = entry.getKey() ; 
		int size = entry.getValue().size() ;
		
		%>
		<tr>
			<td><%=count%></td>
			<td>Class : <%=clazz%>  </td>
			<td><%=size%></td>
		</tr>
		<%
	}
	%> 
	</table>
	
	
	<h1>ETL Proxy Results Classification </h1>
		<%
		
		for (Map.Entry<Class<?>,ProcessResults> entry : classifiedResults.entrySet())
		{
			if (entry.getValue().size() == 0 ){continue ; }
			Class<?> resultsClassName = entry.getKey() ; 
			
			%> 
			<h2>ETL Process  : <%= resultsClassName %></h2>
			<table border="1">
				<tr>
					<td>Count</td>
					<td>source </td>
					<td>Status</td>
					<td>Error</td>
					<td>ExceptionClass</td>
					<td>responseBody </td>  
				</tr>
			<% 
			
			ProcessResults processResults = entry.getValue() ; 
			for (int i =0 ; i< processResults.size() ; i++)
			{
				ProcessResult loadResult =  processResults.get(i);
				HttpResponse<String> httpResponse =  loadResult.getHttpResponse(); 
				
				int statusCode =0; 
				String responseBody = null; 
				if (httpResponse != null)
				{
				statusCode = httpResponse.getStatus();
				responseBody = httpResponse.getBody();
				}
				String error = loadResult.getError(); 
				%>
				<tr>
					<td><%=i%></td>
					<td><%=loadResult.getSource()%>  </td>
					<td><%=statusCode%></td>
					<td><%=error%></td>
					<td><%=loadResult.getExceptionClassName()%></td>
					<td><%=responseBody %> </td>  
					
				</tr>
				<%
			}
			%>
			</table>
			<%
			
		}
		
	%> 
	</body>
</html>