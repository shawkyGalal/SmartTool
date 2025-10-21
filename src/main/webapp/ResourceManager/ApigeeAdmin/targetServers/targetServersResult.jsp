<%@page import="com.smartvalue.apigee.rest.schema.organization.Organization"%>
<%@page import ="com.smartvalue.apigee.rest.schema.environment.Environment"%>
<%@page import="com.smartvalue.apigee.rest.schema.targetServer.TargetServerServices"%>
<%@page import="com.smartvalue.apigee.rest.schema.targetServer.TargetServer"%>
<%@page import="com.smartvalue.apigee.resourceManager.Renderer"%> 


<%@page import ="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
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
			out.print ("<br>KVMs for Organization " + orgSelect ) ; 
			ms.setOrgName(orgSelect); 	
			try {
			Organization org = ms.getOrgByName(orgSelect) ;  
			List<String> envs = org.getEnvironments() ;
			int counter = 0 ; 
			%>
			<table border = 1 > 
			<tr><td> <%=counter%><td>Environment </td><td>KVM Name </td> <td>Protocol</td> <td>SSLInfo</td> <td>Details</td></tr>
			<%
			
			for (String env  : envs)
			{
				Environment envObj = org.getEnvByName(env) ;
				List<String> targetServersNames = envObj.getAllTargetServersNames() ; 
				TargetServerServices targetServerServices = (TargetServerServices) ms.getTargetServersServices(env) ; 
				for (String targetServersName : targetServersNames )
				{
					counter++ ; 
					TargetServer targetServerObj  = targetServerServices.getTargetServerByName(targetServersName) ;
					com.smartvalue.apigee.rest.schema.virtualHost.auto.SSLInfo  sslInfo = targetServerObj.getsSLInfo() ; 
					boolean secure = sslInfo != null && sslInfo.getEnabled().equalsIgnoreCase("true") ;
					boolean clientAuthenticated = secure && sslInfo.getClientAuthEnabled().equalsIgnoreCase("true") ; 
					%> <tr>
							<td><%=counter%></td>
							<td><%=env%></td>
							<td><%=targetServersName%></td>
							<td><%=(secure) ? "https" : "http" %></td>
							<!--  <td><%=(clientAuthenticated)? ( "{\"KeyStore\" : " + "\""+sslInfo.getKeyStore() +"\" , \"KeyAlias\"" + "\"" + sslInfo.getKeyAlias()+"\"}"  )  : "ClientNotAuthenticated"  %> </td>  -->
							<td><%=(clientAuthenticated)? Renderer.objectToHtmlTable(sslInfo) : "ClientNotAuthenticated" %></td>
							<td><a href = "targetServerDetails.jsp?org=<%=orgSelect%>&env=<%=env%>&targetServer=<%=targetServersName%>"> Details</a> </td>
							 
						</tr> 
					<%
				}
			}
			%>
			</table>
			<%

			}
			catch ( Exception e) 
			{
				out.print ( "<br>Unable to Display Organization KVM's due to : " + e.getMessage() ) ; 
			}
		
		%> 
		</body>
</html>