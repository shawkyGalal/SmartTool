
<%@page import ="com.smartvalue.apigee.rest.schema.application.Application"%>
<%@page import ="com.smartvalue.apigee.rest.schema.organization.Organization"%>
<%@page import ="com.smartvalue.apigee.rest.schema.product.ProductsServices"%>
<%@page import ="com.smartvalue.apigee.rest.schema.proxy.Proxy"%>
<%@page import ="com.smartvalue.apigee.rest.schema.developer.Developer"%>
<%@page import ="com.smartvalue.apigee.rest.schema.server.Postgres"%>

<%@page import ="com.smartvalue.apigee.rest.schema.TargetServer"%>
<%@page import ="com.smartvalue.apigee.rest.schema.virtualHost.VirtualHost"%>
<%@page import ="com.smartvalue.apigee.configuration.infra.ManagementServer"%>
<%@page import="com.smartvalue.apigee.resourceManager.Renderer"%>
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
 	out.print ("<br>Applications for Organization " + orgSelect ) ; 
 			
 	try {
 		Organization org = ms.getOrgByName(orgSelect) ;  
 		ArrayList<Application> apps = org.getAllApps() ;
 		int counter = 0 ;
 %>
				<table border = 1 > 
				<tr><td> <%=counter%><td>App Name </td>  <td>Developer</td> <td>Details</td> <td>ProductsCount</td> <td>Edge Details</td> </tr>
				<%
					for (Application app  : apps)
						{ 
							String developerEmail = "xxxxxxUnknownxxxxxx"; 
							if (app.getDeveloperId() != null )
							{
								Developer developer = org.getDeveloper( app.getDeveloperId() ) ;
								developerEmail = developer.getEmail() ;
							}
							counter++ ;
				%> <tr>
							<td><%=counter%></td>
							<td><%=app.getName()%> </td> 
							<td><a href = "../developers/devDetails.jsp?org=<%=orgSelect %>&developerId=<%=developerEmail%>" > <%=developerEmail%></a></td>
							<td><a href = "appDetails.jsp?org=<%=orgSelect%>&appId=<%=app.getAppId()%>" > Details</a></td>
							<td><%=app.getCredentials().get(0).getApiProducts().size() %></td>
							<td><a href = "https://api-prd-n.moj.gov.sa:3001/platform/<%=orgSelect%>/apps/<%=app.getAppId()%>" target = "edge" >Edge Details</a></td>
							
							
							
					</tr> 
					<%
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