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

	ManagementServer sourceMs = AppContext.getApigeeManagementServer(session) ;
	String org = request.getParameter("org"); 
	Infra sourceInfra ; 
	sourceMs.setOrgName(org); 
	sourceInfra = sourceMs.getInfra() ;
	%>
	<h1>Transformation Results </h1>
	<br> <h2> InfraStructure : </h2> <%=sourceInfra.getName()%>
	<br> <h2>Apigee Organization : </h2> <%=org%> 
	<%
	//----- ETL Starting Transforming ----
	ProxyServices proxyServ =  (ProxyServices) sourceMs.getProxyServices(); 
	TransformationResults transformationResults =  proxyServ.transformAll("C:\\temp\\"+org+"\\proxies",  "C:\\temp\\"+org+"\\Transformed\\proxies") ;
	TransformationResults transformationSuccess =  transformationResults.filterFailed(false); 
	TransformationResults transformationFailed =  transformationResults.getNotMatchedResult();
	
	
	String error01 = "Not Found" ; 
	String error02 = "OAS Flow (GetOAS) Found But Empty" ; 
	String error03 = "Unable to Extract Documentation from Flow : GetOAS" ;
	String error04 = "Unexpected character" ; 
	
	String[] knownErrors = {error01 , error02 , error03 , error04 } ; 
	HashMap <String , TransformationResults> errorTypes =  transformationFailed.filterErrorDesc(knownErrors)  ; 
	errorTypes.put("UnClassefied Errors ", transformationFailed.getNotMatchedResult()); 
%> 
<h2> Success Transformations </h2>
 
<Table border="1">
<tr> 
	<td>Count</td>
	<td>Status</td>
	<td>Transformer </td>  
	<td>source </td>
	<td>Error </td>
</tr>
<% 
 int count = 0 ; 
 for ( TransformResult tr :  transformationSuccess  ) 
 {	
	 count ++ ;  
	 %>
		 <tr> 
		 	<td><%=count %> </td>
		 	<td><%=(tr.isFailed())? "Fail":"Success"%> </td>
			<td><%=tr.getTransformerClass().toString().substring("class com.smartvalue.apigee.migration.transformers.proxy.".length())%> </td>  
			<td><%=tr.getSource()%> </td>
			<td><%=tr.getError()%> </td>
		</tr>
	
	 <% 
 }
 
 %>
</Table>

<% 
for ( Map.Entry<String,TransformationResults>  entry : errorTypes.entrySet())
{
	String error = entry.getKey() ;
	TransformationResults trx = entry.getValue(); 

	%>
	<h2> Failed Transformations - Error Type :  <%=error%> </h2>
	 
	<Table border="1">
	<tr> 
		<td>Count</td>
		<td>Status</td>
		<td>Transformer </td>  
		<td>source </td>
		<td>Error </td>
	</tr>
	<% 
	 count = 0 ; 
	 for ( TransformResult tr :  trx ) 
	 {	
		 count ++ ;  
		 %>
			 <tr> 
			 	<td><%=count %> </td>
			 	<td><%=(tr.isFailed())? "Fail":"Success"%> </td>
				<td><%=tr.getTransformerClass().toString().substring("class com.smartvalue.apigee.migration.transformers.proxy.".length())%> </td>  
				<td><%=tr.getSource()%> </td>
				<td><%=tr.getError()%> </td>
			</tr>
		
		 <% 
 	}
 
 	%>
</Table>
<% } %>
</body>
</html>