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
	HashMap<String , HashMap<String , Environment>> storedEnvs = ms.getStoredEnvs(false) ; 

	ArrayList<String> regions = ms.getRegions() ;
%> <br>Apigee Infrastructure (<%=ms.getInfraName() %>) <br> <br> <br> 
<%
		//HashMap <String , Organization> orgs = ms.getOrgs() ; 
		for ( String orgName : storedEnvs.keySet())
		{ 
			HashMap<String, Environment> envs = storedEnvs.get(orgName) ; // org.getEnvironments(); 
			%>
			  
			<br>Environments of Organization ( <%=orgName %> ) 
			<Table border = 1>
				<tr><td>Environments</td> <td>Config</td> <td colspan = 2 >Monitoring</td></tr>
				
				<% for (String env : envs.keySet()) { %>
					<tr>
						<td><%=env%></td>
						<td><a href = "EnvConfig.jsp?envName=<%=env%>&orgName=<%=orgName%>">Config </a></td>
						<td><a href = "EnvsMonitoring.jsp?operation=Start&envName=<%=env%>&orgName=<%=orgName%>">Start </a></td>
						<td><a href = "EnvsMonitoring.jsp?operation=Stop&envName=<%=env%>&orgName=<%=orgName%>">Stop </a></td>
					</tr>
				<%}%>
			</Table>
		<%}
				
 %>
	
</body>
</html>