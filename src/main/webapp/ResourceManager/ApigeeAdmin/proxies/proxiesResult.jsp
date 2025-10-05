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
<%@include file="../intialize.jsp" %>

		<br>Apigee Infrastructure (<%=ms.getInfraName() %>) <br> <br> <br> <%
 	String orgSelect = request.getParameter("orgSelect") ; 
 	out.print ("<br>Proxies for Organization " + orgSelect ) ; 
 			
 	try {
 		Organization org = ms.getOrgByName(orgSelect) ;  
 		ArrayList<String> proxiesNames= org.getAllProxiesNames() ;
 		int counter = 0 ;
 %>
				<table border = 1 > 
				<tr><td> <%=counter%><td>Proxy Name </td>  <td>BasePaths</td> <td>Details</td></tr>
				<%
					for (String proxyName  : proxiesNames)
						{ 
							
				%> <tr>
							<td><%=counter%></td>
							<td><%=proxyName%> </td> 
							<%
							List<String> allversions = null; 
							HashMap<String , List<Object>> basePaths = new HashMap<String , List<Object>>() ; 
							try {
								Proxy proxy = org.getProxy(proxyName) ; 
								 allversions = proxy.getRevision() ;
								
								Boolean displyRevisions = true ; 
								if (displyRevisions)
								{
									for (String revision : allversions )
									{
										basePaths.put(revision , (List<Object>)(Object)proxy.getRevision(revision).getBasepaths() );
									}
								}
							} catch(Exception e ) {System.out.print(e.getMessage()); e.printStackTrace(); }
							%>
							<td><%=Renderer.hashMapWithArraylisttoHtmlTable(basePaths) %> </td>
							<td><a href = "https://api-stg-n.moj.gov.sa:3001/platform/stg/proxies/<%=proxyName%>/overview/<%=allversions.getLast() %>" >Apigee Edge</a></td>
							
					</tr> 
					
					<%
					counter++; 
				}
				%>
				</table>
				<%
				//out.print(Renderer.arrayListToHtmlTable(applications)) ;
			}
			catch ( Exception e) 
			{
				out.print ( "<br>Unable to Display Organization Applications due to : " + e.getMessage() ) ; 
			}
		
		%> 
		</body>
</html>