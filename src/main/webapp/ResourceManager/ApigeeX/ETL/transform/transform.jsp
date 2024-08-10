<%@page import="com.smartValue.web.AppContext"%>
<%@page import="com.smartvalue.apigee.rest.schema.ApigeeService"%>
<%@page import="java.util.ArrayList"%>
<%@page import ="com.smartvalue.apigee.rest.schema.proxy.*"%>
<%@page import ="com.smartvalue.apigee.rest.schema.proxy.transformers.*"%>
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
<title>Insert title here</title>
</head>
<body>
<%
AppConfig ac = AppContext.getAppConfig(application);

//----- ETL Starting Transforming ----
Infra sourceInfra = ac.getInfra("MasterWorks" , "MOJ" , "Stage") ;
String sourceOrgName = "stg" ; 
String transformedFolder = "C:\\temp\\transformed\\proxies" ; 

ManagementServer sourceMs = sourceInfra.getManagementServer(sourceInfra.getRegions().get(0).getName()) ;
ApigeeService sourceProxiesServices = sourceMs.getProxyServices(sourceOrgName);

//sourceProxiesServices.transformAllProxies(exportFolder, transformedFolder);
%>
</body>
</html>