<%@page import="com.mashape.unirest.http.HttpResponse"%>
<%@page import="com.smartvalue.apigee.migration.ProcessResult"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.smartvalue.apigee.migration.ProcessResults"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.smartValue.web.AppContext"%>
<%@page import ="com.smartvalue.apigee.rest.schema.proxy.*"%>
<%@page import ="com.smartvalue.moj.clients.environments.JsonParser"%>
<%@page import ="com.smartvalue.apigee.configuration.infra.Infra"%>
<%@page import ="com.smartvalue.apigee.configuration.infra.ManagementServer"%>
<%@page import ="com.smartvalue.apigee.configuration.AppConfig"%>
<%@page import ="com.smartvalue.apigee.rest.schema.proxy.google.auto.GoogleProxiesList"%>
<%@page import ="java.io.InputStream"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Apigee Objects </title>
</head>
<body>
	<%
	AppConfig ac = AppContext.getAppConfig(application);
	String destOrgName = request.getParameter("orgSelect"); //"moj-prod-apigee" 
	//----- ETL Starting Loading ----
	ManagementServer ms = AppContext.getApigeeManagementServer(session); 
	ProxyServices proxiesServices =(ProxyServices) ms.getProxyServices(destOrgName); 
	DeleteResults deleteResults = proxiesServices.deleteAll() ;
	ProcessResults successResults = deleteResults.filterFailed(false) ;
	HashMap<String,ProcessResults>  classifiedResults = deleteResults.getExceptionClasses();
	%> 
	<h1>Delete Proxies Results Statistics </h1>
	<table border="1">
		<tr>
			<td>#</td>
			<td>Status </td>
			<td>count</td>
		</tr>
	<%
	int count = 1 ; 
	for (Map.Entry<String,ProcessResults> entry : classifiedResults.entrySet())
	{
		count++ ; 
		String errorClass = entry.getKey() ; 
		int size = entry.getValue().size() ;
		
		%>
		<tr>
			<td><%=count%></td>
			<td>Error : <%=errorClass%>  </td>
			<td><%=size%></td>
		</tr>
		<%
	}
	%> 
	</table>
	
	
	<h1>Delete Proxies Results Classification </h1>
		<%
		
		for (Map.Entry<String,ProcessResults> entry : classifiedResults.entrySet())
		{
			String resultsClassName = entry.getKey() ; 
			
			%> 
			<h2>Upload Status : <%=(resultsClassName == null)? "(Success) " : "Error :"+ resultsClassName %></h2>
			<table border="1">
				<tr>
					<td>Count</td>
					<td>source </td>
					<td>Status</td>
					<td>Error</td>
					<td>ExceptionClass</td>
					<td>responseBody </td>  
					<td>Actions</td>
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