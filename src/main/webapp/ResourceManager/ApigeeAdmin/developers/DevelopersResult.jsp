<%@page import="com.smartvalue.apigee.rest.schema.developer.DevelopersList"%>
<%@page import="com.smartvalue.apigee.rest.schema.developer.DeveloperServices"%>
<%@page import="com.smartvalue.apigee.resourceManager.Renderer"%>
<%@page import="com.smartvalue.apigee.configuration.AppConfig"%>
<%@page import="com.smartvalue.apigee.configuration.Partner"%>
<%@page import="com.smartvalue.apigee.configuration.Customer"%>
<%@page import="com.smartvalue.apigee.configuration.infra.*"%>
<%@page import ="com.smartvalue.apigee.rest.schema.environment.Environment"%>
<%@page import ="com.smartvalue.apigee.rest.schema.organization.Organization"%>
<%@page import ="com.smartvalue.apigee.rest.schema.product.ProductsServices"%>
<%@page import ="com.smartvalue.apigee.rest.schema.proxy.Proxy"%>
<%@page import ="com.smartvalue.apigee.rest.schema.developer.Developer"%>
<%@page import ="com.smartvalue.apigee.rest.schema.server.MPServer"%>
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
<%
		
		%> <br>Apigee Infrastructure (<%=ms.getInfraName() %>) <br> <br> <br> <%
			
			
			String orgSelect = request.getParameter("orgSelect") ;
			out.print ("<br>Developers for Organization " + orgSelect ) ;
			try {
		
			Organization org = ms.getOrgByName(orgSelect) ; 
			DeveloperServices ds =  (DeveloperServices) ms.getDevelopersServices(orgSelect) ; 
			ds.setExtend(true) ; 
			
			
			DevelopersList allDevelopers = ds.getAllDevelopersList() ; 
			int devAppsSize ; 
			int counter = 0 ; 
			out.print ("<Table border=1 >") ; 
			out.print ("<tr><td>#</td><td> Developer Email </td><td> Domain </td><td> Apps Count </td> <td> Status </td></tr>") ; 
			for ( Developer  dev :  allDevelopers.getDevelopers() )
			{
				//Developer dev = ds.getDeveloperById(developerName) ;
				counter ++ ; 
				devAppsSize = dev.getApps().size(); 
				String devEmail = dev.getEmail() ; 
				out.print ("<tr><td>"+counter+"</td>"
						+"<td>" + devEmail + "</td>"
						+"<td>" + devEmail.substring(devEmail.indexOf("@")+1) + "</td>"
						+"<td>" + devAppsSize + "</td>"
						+"<td>" + dev.getStatus()+ "</td></tr>" ) ;
			}
			out.print ("</Table>") ;
			HashMap<String , String > extrLinks = new HashMap<String , String >() ; 
			extrLinks.put ("devDetails.jsp?orgSelect="+orgSelect+"&developerId=" , "Details") ;
			extrLinks.put ("xxxxx.jsp?orgSelect="+orgSelect+"&developerId=" , "xxxx") ;
			
			//out.print(Renderer.arrayListToHtmlTable(allDevelopers.getDevelopers() , extrLinks)) ;
			}
			catch ( Exception e) 
			{
				out.print ( "<br>Unable to Display Organization developers due to : " + e.getMessage() ) ; 
			}
		
		%> 
		</body>
</html>