

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
<h1> List Of Products without Proxies </h1>
<%@include file="../..//intialize.jsp" %>

		<br>Apigee Infrastructure (<%=ms.getInfraName()%>) <br> <br> <br> 
		<%
			String org = request.getParameter("orgSelect") ;
			
			//OutputStream os = new org.apache.commons.io.output.WriterOutputStream(out);
			//PrintStream ps = new PrintStream(os);
		
			ProductsServices productsServices = (ProductsServices)ms.getProductServices(org); 
			//productsServices.setPrintStream(ps) ;
			
			ArrayList<Object> products =  productsServices.getProductsWithoutProxies(); 
			out.print(Renderer.arrayListToHtmlTable(products)) ;
			
		%> 
		</body>
</html>