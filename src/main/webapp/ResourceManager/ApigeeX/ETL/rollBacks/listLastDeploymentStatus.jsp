<%@page import="com.smartvalue.apigee.rest.schema.DeploymentsStatus"%>
<%@page import="com.smartvalue.apigee.migration.ProcessResult"%>
<%@page import="com.google.api.client.googleapis.auth.oauth2.GoogleIdToken"%>
<%@page import="com.smartvalue.apigee.migration.load.LoadResult"%>
<%@page import="com.smartvalue.apigee.migration.ProcessResults"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.mashape.unirest.http.HttpResponse"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.smartValue.web.AppContext"%>
<%@page import ="com.smartvalue.apigee.rest.schema.proxy.*"%>
<%@page import ="com.smartvalue.moj.clients.environments.JsonParser"%>
<%@page import ="com.smartvalue.apigee.configuration.infra.Infra"%>
<%@page import ="com.smartvalue.apigee.configuration.infra.ManagementServer"%>
<%@page import ="com.smartvalue.apigee.configuration.AppConfig"%>
<%@page import ="com.smartvalue.apigee.rest.schema.proxy.google.auto.GoogleProxiesList"%>
<%@page import ="com.smartvalue.apigee.rest.schema.proxy.ProxyServices"%>
<%@page import ="java.io.InputStream"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Last Extracted Proxies Deployments Status </title>
</head>
	<body>
	<%
		GoogleIdToken gidt =  AppContext.getGoogleIdToken(session);
		String userEmail = gidt.getPayload().getEmail();
		String migrationBasePath = AppConfig.getMigrationBasePath() ;
		
		ManagementServer sourceMs = AppContext.getApigeeManagementServer(session) ;
		String orgSelect = request.getParameter("orgSelect"); 
		sourceMs.setOrgName(orgSelect); 
		Infra sourceInfra = sourceMs.getInfra(); 
	
	%>
	<h2> InfraStructure : </h2> <%=sourceInfra.getName()%>
	<br> <h2>Apigee Organization : </h2> <%=orgSelect%> 
	
	<%
	 //----- ETL Starting Loading ----
	 ProxyServices sps = (ProxyServices) sourceMs.getProxyServices(orgSelect);
	 String deplyStatusFileName = sps.getSerlizeDeplyStateFileName(userEmail) ;
	 
	 DeploymentsStatus lastDeploymentsStatus = sps.deSerializeDeployStatus(deplyStatusFileName); 
	 
	 %> 
	<h1>Before Last Extract Proxies/SharedFlows Deployments Status</h1> 
	<a href = "RollBack.jsp?orgSelect=<%=orgSelect%>">RollBack</a>
	<table border="1">
		<tr>
			<td>ProxyName</td>
			<td>Revision</td>
			<td>Environments </td>
		</tr>
		<%
		int count = 1 ; 
		for (Map.Entry<String,HashMap<String,ArrayList<String>>> entry : lastDeploymentsStatus.entrySet())
		{
			count++ ; 
			String proxyName = entry.getKey() ;
			ArrayList<String> revisions = lastDeploymentsStatus.getDeployedRevisions(proxyName) ; 
			
			for (String revision : revisions  )
			{
				ArrayList<String> envs = lastDeploymentsStatus.getRevisionDeployedEnvs(proxyName , revision ) ;  
				%>
				<tr>
					<td><%=proxyName%> </td>
					<td><%=revision%></td>
					<td><%=envs%>  </td>
					
				</tr>
				<%
			}
			
		}
	%>
	</table>
	
	</body>
</html>