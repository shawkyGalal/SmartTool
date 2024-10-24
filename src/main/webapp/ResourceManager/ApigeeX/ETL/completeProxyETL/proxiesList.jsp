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
AppConfig ac = AppContext.getAppConfig(application);
String orgSelect = request.getParameter("orgSelect"); //"moj-prod-apigee" 
//----- ETL Starting Loading ----
ManagementServer ms = AppContext.getApigeeManagementServer(session); 
ProxyServices proxiesServices =(ProxyServices) ms.getProxyServices(orgSelect); 

 	 
 	out.print ("<br>Proxies for Organization " + orgSelect ) ; 
 			
 	try {
 		Organization org = ms.getOrgByName(orgSelect) ;  
 		ArrayList<String> proxiesNames= org.getAllProxiesNames() ;
 		int counter = 0 ;
 %>
 		<br>Apigee Infrastructure (<%=ms.getInfraName() %>) <br> <br> <br> 
		<table border = 1 > 
		<tr>	<td> <%=counter%>
				<td>Proxy Name </td>  
				<td>Details</td></tr>
				<%
					for (String proxyName  : proxiesNames)
					{ 
						%> 
						<tr>
							<td><%=counter%></td>
							<td><%=proxyName%> </td> 
							<td><a href ="performProxyETL.jsp?proxyName=<%=proxyName%>&orgSelect=<%=orgSelect%>" >Perform Complete ETL </a></td>
						</tr> 
						
						<%
						counter++; 
					}
				%>
				</table>
				<%
			}
			catch ( Exception e) 
			{
				out.print ( "<br>Unable to Display Organization Proxies  due to : " + e.getMessage() ) ; 
			}
		
		%> 
		</body>
</html>