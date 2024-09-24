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

<%@page language="java" contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<title>Flows and OAS operations Consistency Check </title>
</head>
<body>
<%
String org = request.getParameter("orgSelect") ;
%>

<%@include file="../intialize.jsp" %>

		<br>Apigee Infrastructure (<%=ms.getInfraName()%>) 
		<%
		String selectedOrg = request.getParameter("orgSelect") ; 
			String selectedProxy = request.getParameter("OrgResourceSelect") ;
			String proxyRevision = request.getParameter("proxyRevision") ;
			ProxyRevision pr =    ms.getOrgByName(selectedOrg).getProxy(selectedProxy).getRevision(proxyRevision) ;
			String serverUrl ; 
			serverUrl = (selectedOrg.equalsIgnoreCase("stg"))? "https://api-test.moj.gov.local/" : "https://api.moj.gov.local/" ; 
			HashMap<OasOperation , Flow> oasOperations = pr.checkOpenApiConsistancy(serverUrl , false) ;
			boolean execludeKnownFlows = false ; 
			boolean fixOasOperationID = false ; 
			HashMap<Flow , OasOperation >  apigeeFlows = pr.checkFlowsConsistancy(serverUrl , fixOasOperationID , execludeKnownFlows) ;
		%>
		<br> 
		Selected Proxy : <%=selectedProxy%>
		<br>
		Selected Revision : <%=proxyRevision%>
		<br> <br> 
		<h2> List Of OAS Operations and Matched Flows  </h2> 
			<table border="1">
			<tr>
			<td>Counter</td> <td>operId</td> <td>operDesc</td> <td>operPath</td>  <td>OperVerb</td> 
			<td>flowName</td> <td>flowPath</td><td>flowVerb</td>   
			</tr>
		<%
		int counter = 0 ; 
		for (Map.Entry<OasOperation,Flow>  entry  : oasOperations.entrySet() )
			{
				counter++; 
				OasOperation oper = entry.getKey();
				Flow flow = entry.getValue() ; 
				String flowNotFoundMessage = "<p style='color: red;'>Matched Flow Not Found</p> " ; 
				String flowName = (flow!= null)? flow.getName() : flowNotFoundMessage ;
				String flowPath = (flow!= null)? flow.getCompletePath(): flowNotFoundMessage;  
				String flowVerb = (flow!= null)? flow.extractVerbFromCondition() : flowNotFoundMessage; 
				
				String operId = oper.getOperation().getOperationId(); 
				String operPath = oper.getPath();
				String operVerb = oper.getVerb() ; 
				String operDesc = oper.getOperation().getDescription() ; 
		%>
					<tr>
					<td><%=counter%></td> <td><%=operId%></td> <td><%=operDesc%></td> <td><%=operPath%></td>  <td><%=operVerb%></td> 
					<td><%=flowName%></td> <td><%=flowPath%></td> <td><%=flowVerb%></td>
					</tr>
				
				<%
			}
		
		%>
		</table> 
		
		
		<h2> List Of Apigee Flow and Matched OAS Operation  </h2>  
			<table border="1">
			<tr>
			<td>counter</td> <td>flowName</td> <td>flowPath</td> <td>flowVerb</td>
			<td>operId</td> <td>operPath</td> <td>operVerb</td>     
			
			</tr>
		<%
		counter = 0 ;
		for (Map.Entry<Flow, OasOperation>  entry  : apigeeFlows.entrySet() )
			{
				counter++; 
				Flow flow = entry.getKey(); 	
				OasOperation oper = entry.getValue();
				 
				
				String flowName = flow.getName() ;
				String flowPath = flow.getCompletePath() ; 
				String flowVerb = flow.extractVerbFromCondition() ; 
				
				String operationNotFoundMessage = "<p style='color: red;'>Matched Operation Not Found</p> " ;
				String operId = (oper != null)? oper.getOperation().getOperationId(): operationNotFoundMessage; 
				String operPath = (oper != null)? oper.getPath(): operationNotFoundMessage;
				String operVerb = (oper != null)? oper.getVerb() : operationNotFoundMessage ; 
		%>
					<tr>
					<td><%=counter%></td><td><%=flowName%></td> <td><%=flowPath%></td> <td><%=flowVerb%></td>
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