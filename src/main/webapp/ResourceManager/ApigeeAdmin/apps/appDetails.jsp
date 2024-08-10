<%@page import="com.smartValue.web.AppContext"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<%@page import ="com.smartvalue.apigee.rest.schema.application.Application"%>
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
		String appId = request.getParameter("appId") ;
		
		%> <br>Apigee Application  <%=org %>/ <%=appId %> Details <br> <br> <br> <%
			
		try {
				Application app  = ms.getOrgByName(org).getAppByAppId(appId) ;
				out.print(Renderer.objectToHtmlTable(app)) ; 
			}
			catch ( Exception e) 
			{
				out.print ( "<br>Unable to Display KVM "+appId+" due to : " + e.getMessage() ) ; 
			}
		%> 
</body>
</html>