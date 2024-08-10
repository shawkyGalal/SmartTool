

<%@page import ="com.smartvalue.apigee.rest.schema.application.Application"%>
<%@page import ="com.smartvalue.apigee.rest.schema.organization.Organization"%>
<%@page import ="com.smartvalue.apigee.rest.schema.product.ProductsServices"%>
<%@page import ="com.smartvalue.apigee.rest.schema.proxy.Proxy"%>


<%@page import ="com.smartvalue.apigee.rest.schema.virtualHost.VirtualHost"%>
<%@page import ="com.smartvalue.apigee.configuration.infra.ManagementServer"%>
<%@page import ="java.util.*"%>
<%@page import ="java.io.*"%>
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
<%@include file="../../intialize.jsp" %>

		<br>Apigee Infrastructure (<%=ms.getInfraName()%>) <br> <br> <br> 
		<%
			
		
			
			String deployedRevisionOnly = request.getParameter("deployedRevisionOnly") ; 
			String targetServer = request.getParameter("resourceSelect") ;
			String org = request.getParameter("orgSelect") ;
			Organization orgObj = (Organization) ms.getOrgByName(org) ;
			//OutputStream os = new org.apache.commons.io.output.WriterOutputStream(out);
			//PrintStream ps = new PrintStream(os);
			//orgObj.setPrintStream(ps); 
			HashMap<Object, Object> proxies = orgObj.getAllProxiesUsesTargetServer(targetServer , true ); 
			out.print(Renderer.objectToHtmlTable(proxies)) ;
			
		%> 
		</body>
</html>