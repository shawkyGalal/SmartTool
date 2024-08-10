<%@page import="com.smartvalue.apigee.resourceManager.Renderer"%>
<%@page import="com.smartvalue.apigee.configuration.AppConfig"%>
<%@page import="com.smartvalue.apigee.configuration.Partner"%>
<%@page import="com.smartvalue.apigee.configuration.Customer"%>
<%@page import="com.smartvalue.apigee.configuration.infra.*"%>

<%@page import ="com.smartvalue.apigee.rest.schema.environment.Environment"%>
<%@page import ="com.smartvalue.apigee.rest.schema.organization.Organization"%>
<%@page import ="com.smartvalue.apigee.rest.schema.product.ProductsServices"%>
<%@page import ="com.smartvalue.apigee.rest.schema.proxy.Proxy"%>
<%@page import ="com.smartvalue.apigee.rest.schema.server.MPServer"%>
<%@page import ="com.smartvalue.apigee.rest.schema.server.Postgres"%>
<%@page import ="com.smartvalue.apigee.rest.schema.server.Router"%>
<%@page import ="com.smartvalue.apigee.rest.schema.server.Server"%>
<%@page import ="com.smartvalue.apigee.rest.schema.server.ServerServices"%>
<%@page import ="com.smartvalue.apigee.rest.schema.sharedFlow.SharedFlow"%>
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
<%@include file="../intialize.jsp" %>
<body>
<%
	
				
		ArrayList<String> regions = ms.getRegions() ;
		%> <br>Apigee Infrastructure (<%=ms.getInfraName() %>) <br> <br> <br> <%
		HashMap<String , HashMap<String , Environment>> storedEnvs = ms.getStoredEnvs(false) ; 
		 
		for ( String orgName : storedEnvs.keySet())
		{ 
			HashMap<String , Environment> envs = storedEnvs.get(orgName) ; //org.getEnvs(); // getEnvironments(); 
			%>
			  
			<br>Environments of Organization ( <%=orgName %> ) 
			<Table border = 1>
				<tr><td>Environments</td> <td>Config</td> <td colspan = 3 >Monitoring</td></tr>
				
				<% for (String envName : envs.keySet()) { 
					Environment env = envs.get(envName) ; 
					int status = env.getMonitoringStatus(); 
				%>
					<tr>
						<td><%=envName%></td>
						<td><a href = "EnvConfig.jsp?envName=<%=env%>&orgName=<%=orgName%>">Config </a></td>
						<td><%=status%></td>
						<td><% if (status == 0 ) { 
							%> <a href = "EnvsMonitoring.jsp?operation=Start&envName=<%=envName%>&orgName=<%=orgName%>">Start </a>  
							<%} else {out.print("Running.."); } %>
							</td>
						
						<td><% if (status == 1 ) { 
							%>
							<a href = "EnvsMonitoring.jsp?operation=Stop&envName=<%=envName%>&orgName=<%=orgName%>">Stop </a>
							<%}%>
						</td>
					</tr>
				<%}%>
			</Table>
	<%} %>
	
</body>
</html>