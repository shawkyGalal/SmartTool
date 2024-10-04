<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="com.smartvalue.apigee.migration.transformers.TransformationResults"%>
<%@page import="com.smartvalue.apigee.migration.transformers.TransformResult"%>
<%@page import="com.smartValue.web.AppContext"%>
<%@page import="com.smartvalue.apigee.rest.schema.ApigeeService"%>
<%@page import="java.util.ArrayList"%>
<%@page import ="com.smartvalue.apigee.rest.schema.proxy.*"%>
<%@page import ="com.smartvalue.moj.clients.environments.JsonParser"%>
<%@page import ="com.smartvalue.apigee.configuration.infra.Infra"%>
<%@page import ="com.smartvalue.apigee.configuration.infra.ManagementServer"%>
<%@page import ="com.smartvalue.apigee.configuration.AppConfig"%>
<%@page import ="com.smartvalue.apigee.rest.schema.proxy.google.auto.GoogleProxiesList"%>
<%@page import ="java.io.InputStream"%>
<%@page import="com.smartvalue.apigee.resourceManager.Renderer"%>


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
	Infra sourceInfra = AppContext.getApigeeManagementServer(session).getInfra() ; 
	//.getInfra("MasterWorks" , "MOJ" , "Stage") ;
	String sourceOrgName = request.getParameter("orgSelect") ; 

 
	//=====================Display Transformers Information ==========================
	%><h1> The Following Transformation is configured in the System and will be applied to the selected Infrastructure ( <%= sourceInfra.getParentCustomer().getName()%>. <%= sourceInfra.getName()%> ) and selected Organization <%=sourceOrgName%> </h1> 
	<%

	ManagementServer sourceMs = sourceInfra.getManagementServer(sourceInfra.getRegions().get(0).getName()) ;
	sourceMs.setOrgName(sourceOrgName) ;
	out.print(Renderer.arrayListToHtmlTable(sourceMs.getInfra().getTransformersConfig().getTransformers()));
%>
<a href = "transform.jsp?org=<%=sourceOrgName%>" >Next </a>
 
</body>
</html>