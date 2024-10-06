<%@page import="com.smartvalue.apigee.migration.load.LoadResults"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.mashape.unirest.http.HttpResponse"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.smartValue.web.AppContext"%>
<%@page import ="com.smartvalue.apigee.rest.schema.proxy.*"%>
<%@page import ="com.smartvalue.moj.clients.environments.JsonParser"%>
<%@page import ="com.smartvalue.apigee.configuration.infra.Infra"%>
<%@page import ="com.smartvalue.apigee.configuration.infra.ManagementServer"%>
<%@page import ="com.smartvalue.apigee.configuration.AppConfig"%>
<%@page import ="com.smartvalue.apigee.rest.schema.proxy.google.auto.GoogleProxiesList"%>
<%@page import ="java.io.InputStream"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Load Apigee Object </title>
</head>
<body>
<%

//----- ETL Starting Loading ----

ManagementServer ms = AppContext.getApigeeManagementServer(session); 
String destOrgName = "lean-it" ; //"moj-prod-apigee" ; 
String transformedFolder = "C:\\temp\\Apigee\\Stage\\stg\\Transformed\\proxies\\" ; 

ProxyServices proxiesServices = (ProxyServices)ms.getProxyServices(destOrgName); 
//GoogleProxiesList proxiesList= proxiesServices.getAllProxiesList(GoogleProxiesList.class); 
//proxiesServices.deleteAll(proxiesList) ;

proxiesServices.setDeployUponUpload(false); 
HashMap<String,HttpResponse<String>> result = proxiesServices.importAll(transformedFolder) ;
LoadResults lr = new LoadResults(result) ; 
HashMap<String,HttpResponse<String>> successResults = lr.filterSuccess() ; 
HashMap<String,HttpResponse<String>> failedResults = lr.getUnMatchedResponses() ;

for ( Map.Entry<String,HttpResponse<String>> entry  : successResults.entrySet())
{
	HttpResponse<String> resultResponse = entry.getValue(); 
	int status = resultResponse.getStatus();
	String body = resultResponse.getBody(); 
}

for ( Map.Entry<String,HttpResponse<String>> entry  : failedResults.entrySet())
{
	HttpResponse<String> resultResponse = entry.getValue(); 
	int status = resultResponse.getStatus();
	String body = resultResponse.getBody(); 
}
%>
</body>
</html>