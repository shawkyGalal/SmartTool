<%@page import="com.smartValue.web.AppContext"%>
<%@page import="com.smartvalue.apigee.rest.schema.proxy.ProxyServices"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="com.smartvalue.apigee.resourceManager.Renderer"%>
<%@page import="com.smartvalue.apigee.configuration.AppConfig"%>
<%@page import="com.smartvalue.apigee.configuration.infra.*"%>

<%@page import ="com.smartvalue.apigee.rest.schema.environment.Environment"%>
<%@page import ="com.smartvalue.apigee.rest.schema.organization.Organization"%>
<%@page import ="com.smartvalue.apigee.rest.schema.product.ProductsServices"%>
<%@page import ="com.smartvalue.apigee.rest.schema.proxy.Proxy"%>
<%@page import ="com.smartvalue.apigee.rest.schema.server.MPServer"%>
<%@page import ="com.smartvalue.apigee.rest.schema.server.Postgres"%>
<%@page import ="com.smartvalue.apigee.rest.schema.server.Router"%>
<%@page import ="com.smartvalue.apigee.rest.schema.server.Server"%>
<%@page import ="com.smartvalue.apigee.rest.schema.server.ServerServices"%>
<%@page import ="com.smartvalue.apigee.rest.schema.sharedFlow.SharedFlow"%>
<%@page import ="com.smartvalue.apigee.rest.schema.TargetServer"%>
<%@page import ="com.smartvalue.apigee.rest.schema.virtualHost.VirtualHost"%>
<%@page import ="com.smartvalue.apigee.configuration.infra.ManagementServer"%>
<%@page import="com.smartvalue.apigee.configuration.AppConfigFactory"%>


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

		AppConfig ac = AppContext.getAppConfig(application);		
		Infra infra = ac.getInfra("MasterWorks" , "MOJ" , "Stage") ;
		String orgName = "stg" ; 
		String envName = "cert-protected" ; 
		String proxyName = "oidc-core" ; 
		
		ManagementServer ms = infra.getManagementServer("dc-1"); // ManagementServer() ; 
	
		
		//out.print(Renderer.hashMaptoHtmlTable(orgs) )  ;
		Organization org = ms.getOrgByName(orgName) ;
		 
		out.print(Renderer.objectToHtmlTable(org));
		
		
		
		Environment env = (Environment) org.getEnvs().get(envName);
		out.print(Renderer.objectToHtmlTable(env)); 
		
		Proxy proxy = org.getProxy(proxyName);
		out.print(Renderer.objectToHtmlTable(proxy));
		
		ProductsServices   productServices = (ProductsServices)ms.getProductServices(orgName) ; 
		//ArrayList<String>  productsWithoutProxies  =productServices.getProductsWithoutProxies(org) ;  
		//out.print(productsWithoutProxies.toString()); 
		 
		List<MPServer> envMpServers = env.getMessageProcesors("dc-1") ;
		out.print(Renderer.arrayListToHtmlTable(envMpServers));
		//envMpServers.get(0).healthCheck() ;
		//ArrayList<String> result = envMpServers.get(0).removeFromEnvironmnt(org , env ) ; 
		//result = envMpServers.get(0).addToEnvironmnt(org , env ) ; 
		System.out.println(envMpServers);
		
		//result = env.removeMessageProcessor(envMpServers.get(0)) ; 
		//result = env.addMessageProcessor(envMpServers.get(0)) ;
		
		String[] aa = {"FC-ELK-Logger" ,  "ELK-Logger" ,  "FC-Elk-Logger" } ; 
		HashMap<String , List<Object>>  result =  ((ProxyServices)ms.getProxyServices(orgName)).getProxiesWithoutPolices(aa, true) ;
		
		out.print(Renderer.hashMapWithArraylisttoHtmlTable(result));
%>
</body>
</html>