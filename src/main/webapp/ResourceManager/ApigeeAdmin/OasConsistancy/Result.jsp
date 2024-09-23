

<%@page import="com.smartvalue.apigee.proxyBundle.ProxyBundleParser"%>
<%@page import="io.swagger.v3.oas.models.Operation"%>
<%@page import="com.smartvalue.apigee.rest.schema.proxyEndPoint.auto.Flow"%>
<%@page import="com.smartvalue.apigee.rest.schema.proxyRevision.ProxyRevision"%>
<%@page import="com.smartvalue.apigee.rest.schema.proxyRevision.OasOperation"%>
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
<h1> List Of Flows For Selected Proxy Reviosn in Org : <%=org%> and Consistency with the result of calling the GetOAS flow   </h1>
<%@include file="../intialize.jsp" %>

		<br>Apigee Infrastructure (<%=ms.getInfraName()%>) 
		<%
		String selectedOrg = request.getParameter("orgSelect") ; 
			String selectedProxy = request.getParameter("OrgResourceSelect") ;
			String proxyRevision = request.getParameter("proxyRevision") ;
			ProxyRevision pr =    ms.getOrgByName(selectedOrg).getProxy(selectedProxy).getRevision("147") ;
			String serverUrl ; 
			serverUrl = (selectedOrg.equalsIgnoreCase("stg"))? "https://api-test.moj.gov.local/" : "https://api.moj.gov.local/" ; 
			HashMap<OasOperation , Flow> oasOperations = pr.checkOpenApiConsistancy(serverUrl , false) ;
			HashMap<Flow , OasOperation >  apigeeFlows = pr.checkFlowsConsistancy(serverUrl , false) ;
		%>
		<br> 
		Selected Proxy : <%=selectedProxy%>
		<br>
		Selected Revision : <%=proxyRevision%>
		<br> <br> 
		<h2> List Of OAS Operations and Matched Flow   
			<table>
			<tr>
			<td>operId</td> <td>operPath</td>  <td>OperVerb</td> 
			<td>flowName</td> <td>flowPath</td><td>flowVerb</td>   
			</tr>
		<%
		for (Map.Entry<OasOperation,Flow>  entry  : oasOperations.entrySet() )
			{
				OasOperation oper = entry.getKey();
				Flow flow = entry.getValue() ; 
				String flowNotFoundMessage = "Flow Not Found" ; 
				String flowName = (flow!= null)? flow.getName() : flowNotFoundMessage ;
				String flowPath = (flow!= null)? flow.extractPathSuffixFromCondition() : flowNotFoundMessage; 
				String flowVerb = (flow!= null)? flow.extractVerbFromCondition() : flowNotFoundMessage; 
				
				String operId = oper.getOperation().getOperationId(); 
				String operPath = oper.getPath();
				String operVerb = oper.getVerb() ; 
		%>
					<tr>
					<td><%=operId%></td> <td><%=operPath%></td>  <td><%=operVerb%></td> 
					<td><%=flowName%></td> <td><%=flowPath%></td> <td><%=flowVerb%></td>
					</tr>
				
				<%
			}
		
		%>
		</table> 
		
		
		<h2> List Of Apigee Flow and Matched OAS Operation    
			<table>
			<tr>
			<td>flowName</td> <td>flowPath</td> <td>flowVerb</td>
			<td>operId</td> <td>operPath</td> <td>operVerb</td>     
			
			</tr>
		<%
		for (Map.Entry<Flow, OasOperation>  entry  : apigeeFlows.entrySet() )
			{
				Flow flow = entry.getKey(); 	
				OasOperation oper = entry.getValue();
				 
				
				String flowName = flow.getName() ;
				String flowPath = flow.extractPathSuffixFromCondition() ; 
				String flowVerb = flow.extractVerbFromCondition() ; 
				
				String operationNotFoundMessage = "Operation Not Found" ;
				String operId = (oper != null)? oper.getOperation().getOperationId(): operationNotFoundMessage; 
				String operPath = (oper != null)? oper.getPath(): operationNotFoundMessage;
				String operVerb = (oper != null)? oper.getVerb() : operationNotFoundMessage ; 
		%>
					<tr>
					<td><%=flowName%></td> <td><%=flowPath%></td> <td><%=flowVerb%></td>
					<td><%=operId%></td> <td><%=operPath%></td>  <td><%=operVerb%></td> 
					
					</tr>
				
				<%
			}
		
		%>
		</table> 
		
		
		<h1> List Of OAS Operations defined in the GetOAS Flow For Selected exported Proxy Bundled in Org : <%=org %> and Consistency with the result of calling the GetOAS flow   </h1>
		<%
			ProxyBundleParser proxyBundle =  new ProxyBundleParser("C:\\temp\\Stage\\proxies\\moj-internal-clients\\"+selectedProxy+"\\"+proxyRevision+"\\"+selectedProxy+".zip") ;
			proxyBundle.getOasJson("GetOAS"); 
		%>
		
		</body>
</html>