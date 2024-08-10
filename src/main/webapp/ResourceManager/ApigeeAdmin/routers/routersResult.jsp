<%@page import="com.smartvalue.apigee.resourceManager.Renderer"%>
<%@page import="com.smartvalue.apigee.configuration.filteredList.FilteredList"%>

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
<body>
<%@include file="../intialize.jsp" %>
<h1> !!!!!!! There is an issue in reading routers of other regions !!!!!!!!!!</h1>
<%
		ArrayList<String> regions = ms.getRegions() ;
		
		ServerServices ss = ms.getServerServices() ;	
		%> <br>Apigee Infrastructure (<%=ms.getInfraName() %>) <br> <br> <br> 
			  

			<Table border = 1>
				<tr><td colspan = <%=regions.size()%> >Regions</td></tr>
				<tr>
				<% 
					for (String  region : regions)
					{
						out.print ("<td>" + region + "</td>" ) ;  
					}
				%>
				
				</tr>
				<tr>
				<% 
					for (String  region : regions)
					{	%> <td><table border = 1 name = "Region Routers "> 
						<% 
							FilteredList<Router> routerServers =  ss.getRouterServers(region);
							for ( Router router : routerServers )
							{	
								%><tr><td><%=router.getInternalIP()%> : <%=router.getuUID() %></td> </tr><% 
							}
						%> </table></td> <%
					}
							
				%>
					
			</Table>
	
</body>
</html>