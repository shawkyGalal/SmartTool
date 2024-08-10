<%@page import="com.smartvalue.apigee.rest.schema.server.MPServer"%>
<%@page import ="com.smartvalue.apigee.rest.schema.environment.Environment"%>
<%@page import="com.smartvalue.apigee.resourceManager.Renderer"%>

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
	String mPUUID = request.getParameter("uUID");
	String envName = request.getParameter("envName");
	String orgName = request.getParameter("orgName");
	String region = request.getParameter("region");
	
	String operation = request.getParameter("operation");
	com.smartvalue.apigee.rest.schema.server.ServerServices serverServices = ms.getServerServices(); 
	
	Environment env = ms.getStoredEnvs(false).get(orgName).get(envName);
	MPServer mpServer = serverServices.getMPServerByUUID(region , mPUUID) ; 
	String mPIP = mpServer.getInternalIP() ; 
	
	String mpIdentifier = region + "_" + mPIP ; 
	ArrayList<String> mpList ; 
	if ( operation.equalsIgnoreCase("remove"))
	{
		mpList = mpServer.removeFromEnvironmnt(env) ; 
		out.println("Message Processor " + mpIdentifier + "Successfully removed from Environment " + orgName + "/"+envName ) ; 
		out.print(Renderer.objectToHtmlTable(mpList)) ; 
	}
	
	if ( operation.equalsIgnoreCase("add"))
	{
		mpList = mpServer.addToEnvironmnt(env) ;
		out.println("Message Processor " + mpIdentifier + "Successfully Added To Environment " + orgName + "/"+envName ) ; 
		out.print(Renderer.objectToHtmlTable(mpList)) ; 

	}
	
	if ( operation.equalsIgnoreCase("healthCheck"))
	{
		boolean running = mpServer.healthCheck();
		out.println("MP Server Running Status " + running ) ; 
	}
	
	

%>

</body>
</html>