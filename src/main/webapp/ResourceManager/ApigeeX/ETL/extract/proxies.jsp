<%@page import="com.google.api.client.googleapis.auth.oauth2.GoogleIdToken"%>
<%@page import="com.mashape.unirest.http.HttpResponse"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.smartvalue.apigee.migration.ProcessResults"%>
<%@page import="com.smartvalue.apigee.migration.ProcessResult"%>
<%@page import="com.smartvalue.apigee.migration.export.ExportResult"%>
<%@page import="com.smartvalue.apigee.migration.export.ExportResults"%>

<%@page import="com.smartValue.web.AppContext"%>
<%@page import="com.smartvalue.apigee.rest.schema.targetServer.TargetServerServices"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.PrintStream"%>
<%@page import ="com.smartvalue.apigee.rest.schema.proxy.ProxyServices"%>

<%@page import ="com.smartvalue.moj.clients.environments.JsonParser"%>
<%@page import ="com.smartvalue.apigee.configuration.infra.Infra"%>
<%@page import ="com.smartvalue.apigee.configuration.infra.ManagementServer"%>
<%@page import ="com.smartvalue.apigee.configuration.AppConfig"%>
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
String migrationBasePath = AppConfig.getMigrationBasePath() ;

ManagementServer sourceMs = AppContext.getApigeeManagementServer(session) ;
String sourceOrgName = request.getParameter("orgSelect"); 
sourceMs.setOrgName(sourceOrgName); 
Infra sourceInfra = sourceMs.getInfra(); 

%>
<h1>Export Deployed Proxies Results </h1>
<br> <h2> InfraStructure : </h2> <%=sourceInfra.getName()%>
<br> <h2>Apigee Organization : </h2> <%=sourceOrgName%> 
<%
//----- ETL Starting Extraction ----  


ProxyServices sps = (ProxyServices) sourceMs.getProxyServices(sourceOrgName);
String basePath =  migrationBasePath +"\\"+ userEmail +"\\"+sourceInfra.getName()+"\\"+sourceOrgName ; 
String sourceFolder =basePath +"\\proxies\\" ; 
String deplyStatusFileName = basePath + "\\deplysStatus.ser" ; 
ExportResults result = sps.exportAll(sourceFolder , deplyStatusFileName) ;
ProcessResults successResults = result.filterFailed(false) ;
HashMap<String,ProcessResults>  classifiedResults = result.getExceptionClasses();
%> 
<h1>Export Results Statistics </h1>
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


<h1>Export Results Classification </h1>
	<%
	
	for (Map.Entry<String,ProcessResults> entry : classifiedResults.entrySet())
	{
		String resultsClassName = entry.getKey() ; 
		
		%> 
		<h2>Upload Status : <%=(resultsClassName == null)? "(Success) " : "Error :"+ resultsClassName %></h2>
		<table border="1">
			<tr>
				<td>Count</td>
				<td>source (Environment.Proxy.Version) </td>
				<td>Destination </td>
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
			ExportResult exportResult = ((ExportResult) processResults.get(i));
			HttpResponse<String> httpResponse =  exportResult.getHttpResponse(); 
			
			int statusCode =0; 
			String responseBody = null; 
			if (httpResponse != null)
			{
			statusCode = httpResponse.getStatus();
			responseBody = httpResponse.getBody();
			}
			String error = exportResult.getError(); 
			String destination = exportResult.getDestination(); 
			
			%>
			<tr>
				<td><%=i%></td>
				<td><%=exportResult.getSource()%>  </td>
				<td><%=destination%></td>
				<td><%=statusCode%></td>
				<td><%=error%></td>
				<td><%=exportResult.getExceptionClassName()%></td>
				<td><%=responseBody %> </td>  
				<td>Actions</td>
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