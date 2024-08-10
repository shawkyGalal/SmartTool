<%@page import="com.smartvalue.apigee.rest.schema.organization.Organization"%>
<%@page import ="com.smartvalue.apigee.rest.schema.environment.Environment"%>
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
					
			try {
			Organization org = ms.getOrgByName(orgSelect) ;  
			List<String> envs = org.getEnvironments() ;
			int counter = 0 ; 
			%>
			<table border = 1 > 
			<tr><td> <%=counter%><td>Environment </td><td>KVM Name </td> <td>Details</td></tr>
			<%
			
			for (String env  : envs)
			{
				Environment envObj = org.getEnvByName(env) ;
				List<String> kvmsNames = envObj.getAllKvmNames() ; 
				for (String kvmName : kvmsNames )
				{
				counter++ ; 
				%> <tr>
						<td><%=counter%></td>
						<td><%=env%></td>
						<td><%=kvmName%></td>
						<td><a href = "kvmDetails.jsp?org=<%=orgSelect%>&env=<%=env%>&kvm=<%=kvmName%>"> Details</a> </td> 
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