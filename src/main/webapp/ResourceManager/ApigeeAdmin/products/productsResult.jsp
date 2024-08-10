<%@page import="com.smartvalue.apigee.rest.schema.product.Product"%>
<%@page import="com.smartvalue.apigee.resourceManager.Renderer"%>
<%@page import="com.smartvalue.apigee.configuration.AppConfig"%>
<%@page import="com.smartvalue.apigee.configuration.Partner"%>
<%@page import="com.smartvalue.apigee.configuration.Customer"%>
<%@page import="com.smartvalue.apigee.configuration.infra.*"%>

<%@page import ="com.smartvalue.apigee.rest.schema.environment.Environment"%>
<%@page import ="com.smartvalue.apigee.rest.schema.organization.Organization"%>
<%@page import ="com.smartvalue.apigee.rest.schema.product.ProductsServices"%>
<%@page import ="com.smartvalue.apigee.rest.schema.server.MPServer"%>

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
			out.print ("<br>Products for Organization " + orgSelect ) ; 
			try {
				
			Organization org = ms.getOrgByName(orgSelect) ;  
			ArrayList<String> products = org.getAllProductsNames() ;
			ProductsServices ps = (ProductsServices) ms.getProductServices(orgSelect) ;
			ArrayList<Product> allproducts =  ps.getAllProducs();
			out.print(Renderer.arrayListToHtmlTable(allproducts)) ;
			
			}
			catch ( Exception e) 
			{
				out.print ( "<br>Unable to Display Organization Products due to : " + e.getMessage() ) ; 
			}
				
		
		%> 
		</body>
</html>