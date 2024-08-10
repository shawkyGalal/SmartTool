

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
<%@page import ="com.smartvalue.apigee.resourceManager.*"%>

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
			String org = request.getParameter("orgSelect") ;
%>
<h1> List Of Proxies found in Org : <%=org %> and Not Deployed To any Environment </h1>
<%@include file="../../intialize.jsp" %>

		<br>Apigee Infrastructure (<%=ms.getInfraName()%>) <br> <br> <br> 
		<%
			Organization orgObj = (Organization) ms.getOrgByName(org) ;  
			ArrayList<String> proxies = orgObj.getUndeployedProxies(); 
			out.print(Renderer.arrayListToHtmlTable(proxies)) ;
			
		%> 
		</body>
</html>