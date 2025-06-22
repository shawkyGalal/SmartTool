<%@page import="java.util.UUID"%>
<%@page import="com.smartvalue.apigee.configuration.infra.ServiceFactory"%>
<%@page import="com.smartvalue.apigee.rest.schema.sharedFlow.SharedFlowServices"%>
<%@page import="com.smartvalue.apigee.rest.schema.BundleObjectService"%>
<%@page import="com.smartvalue.apigee.rest.schema.ApigeeService"%>
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
<title>Result of Extracting Bundled Objects </title>
</head>
<body>
<%
p

	GoogleIdToken gidt =  AppContext.getGoogleIdToken(session);
	String userEmail = gidt.getPayload().getEmail();
	
	
	ManagementServer sourceMs = AppContext.getApigeeManagementServer(session) ;
	
	String migrationBasePath = sourceMs.getProcessFolderPath(UUID.randomUUID().toString()); 
	String sourceOrgName = request.getParameter("orgSelect"); 
	sourceMs.setOrgName(sourceOrgName); 
	Infra sourceInfra = sourceMs.getInfra(); 
	String bundleType = request.getParameter("bundleType") ;
%>
	<h1>Results of Exporting Deployed <%= bundleType%>  </h1>
	<br> <h2> InfraStructure : </h2> <%=sourceInfra.getName()%>
	<br> <h2>Apigee Organization : </h2> <%=sourceOrgName%> 
	<%
	//----- ETL Starting Extraction ----  
	Class<? extends BundleObjectService> type = null ; 
	if (bundleType.equalsIgnoreCase("proxies")) type =  ProxyServices.class ;
	if (bundleType.equalsIgnoreCase("sharedFlows")) type =  SharedFlowServices.class ;
	BundleObjectService bundleObjectService = ServiceFactory.createBundleServiceInstance(type, sourceMs.getInfra() , null ) ; 
	ExportResults result = bundleObjectService.exportAll( migrationBasePath ) ;
	
	ProcessResults successResults = result.filterFailed(false) ;
	HashMap<String,ProcessResults>  classifiedResults = result.getExceptionClasses();
%> 
<h1>Export (<%=type%>) Results Statistics </h1>
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
			
			%>
			<tr>
				<td><%=i%></td>
				<td><%=exportResult.getSource()%>  </td>
				<td><%=exportResult.getDestination() %></td>
				<td><%=exportResult.getError() %></td>
				<td><%=exportResult.getExceptionClassName()%></td>
				<td><%=exportResult.getResponseBody() %> </td>  
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