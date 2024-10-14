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
<title>List Last Proxies Deployments Status </title>
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
	<h1>RollBack Results </h1>
	<br> <h2> InfraStructure : </h2> <%=sourceInfra.getName()%>
	<br> <h2>Apigee Organization : </h2> <%=orgSelect%> 
	
	<%
	 //----- ETL Starting Loading ----
	 ProxyServices sps = (ProxyServices) sourceMs.getProxyServices(orgSelect);
	 String basePath =  migrationBasePath +"\\"+ userEmail +"\\"+sourceInfra.getName()+"\\"+orgSelect ; 
	 String deplyStatusFileName = basePath + "\\deplysStatus.ser" ; 
	 HashMap<String,HashMap<String,ArrayList<String>>> lastDeploymentsStatus = sps.deSerializeDeployStatus(deplyStatusFileName); 
	 
	 %> 
	<h1>Before Last Extract Proxies Deployments Status</h1> 
	<a href = "RollBack.jsp?orgSelect=<%=orgSelect%>">RollBack</a>
	<table border="1">
		<tr>
			<td>ProxyName</td>
			<td>Environment </td>
			<td>Revision</td>
		</tr>
		<%
		int count = 1 ; 
		for (Map.Entry<String,HashMap<String,ArrayList<String>>> entry : lastDeploymentsStatus.entrySet())
		{
			count++ ; 
			String proxyName = entry.getKey() ; 
			HashMap<String, ArrayList<String>> ds = entry.getValue();
			for ( Map.Entry<String, ArrayList<String>> entry01 :  ds.entrySet() )
			{
				String envName = entry01.getKey(); 
				ArrayList<String> revisions = entry01.getValue() ;
				for (String revision : revisions)
				{
					%>
					<tr>
						<td><%=proxyName%> </td>
						<td><%=envName%>  </td>
						<td><%=revision%></td>
					</tr>
					<%
				}
			}
		}
	%>
	</table>
	
	</body>
</html>