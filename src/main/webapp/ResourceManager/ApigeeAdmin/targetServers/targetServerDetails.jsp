<%@page import="com.smartvalue.apigee.rest.schema.targetServer.TargetServer"%>
<%@page import="com.smartvalue.apigee.rest.schema.targetServer.TargetServerServices"%>
<%@page import="com.smartValue.web.AppContext"%>
<%@page import="com.smartvalue.apigee.rest.schema.keyValueMap.KvmServices"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import ="com.smartvalue.apigee.rest.schema.keyValueMap.KeyValueMap"%>
<%@page import ="com.smartvalue.apigee.configuration.infra.ManagementServer"%>
<%@page import="com.smartvalue.apigee.resourceManager.Renderer"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

		<%
		ManagementServer ms = AppContext.getApigeeManagementServer(session); 
		String org = request.getParameter("org") ; 		
		String env = request.getParameter("env") ;
		ms.setOrgName(org);
		String targetServer = request.getParameter("targetServer") ;
		
		%> <br>Apigee Target Server  (<%=org %>)/ <%=env %>/<%=targetServer %> Details <br> <br> <br> <%
			
		try {
				TargetServerServices targetServerServices = (TargetServerServices) ms.getTargetServersServices(env) ; 
				TargetServer targetServerObj  = targetServerServices.getTargetServerByName(targetServer) ;
				out.print(Renderer.objectToHtmlTable(targetServerObj)) ; 
			}
			catch ( Exception e) 
			{
				out.print ( "<br>Unable to Display KVM "+targetServer+" due to : " + e.getMessage() ) ; 
			}
		%> 
</body>