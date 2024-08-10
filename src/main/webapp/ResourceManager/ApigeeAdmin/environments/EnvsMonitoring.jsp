<%@page import="com.smartvalue.apigee.resourceManager.Renderer"%>
<%@page import="com.smartvalue.apigee.configuration.AppConfig"%>
<%@page import="com.smartvalue.apigee.configuration.Partner"%>
<%@page import="com.smartvalue.apigee.configuration.Customer"%>
<%@page import="com.smartvalue.apigee.configuration.infra.*"%>

<%@page import ="com.smartvalue.apigee.rest.schema.environment.Environment"%>
<%@page import ="com.smartvalue.apigee.rest.schema.organization.Organization"%>

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
	HashMap<String , HashMap<String , Environment>> storedEnvs = ms.getStoredEnvs(false)  ; 
		String envName = request.getParameter("envName") ; 
		String orgName = request.getParameter("orgName") ;
		String envUniqueName = orgName + "_" + envName; 
		Environment env = storedEnvs.get(orgName).get(envName) ;  
		
		
		String operation = request.getParameter("operation") ;
		if (operation.equalsIgnoreCase("start"))
		{		 
			env.startMonitoring(2) ;
			out.print("Monitoring "+envUniqueName+" Successfully Started"); 
		}
		else if (operation.equalsIgnoreCase("stop"))
		{
			env.stopMonitoring() ;
			out.print("Monitoring "+envUniqueName+" Successfully Stoped");
		}
%>
 
	
</body>
</html>