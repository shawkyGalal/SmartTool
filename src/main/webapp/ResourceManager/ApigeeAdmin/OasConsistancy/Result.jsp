

<%@page import="io.swagger.v3.oas.models.Operation"%>
<%@page import="com.smartvalue.apigee.rest.schema.proxyEndPoint.auto.Flow"%>
<%@page import="com.smartvalue.apigee.rest.schema.proxyRevision.ProxyRevision"%>
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
<h1> List Of Flows For Selected Proxy Reviosn in Org : <%=org %> and Consistency  </h1>
<%@include file="../intialize.jsp" %>

		<br>Apigee Infrastructure (<%=ms.getInfraName()%>) <br> <br> <br> 
		<%
			String selectedOrg = request.getParameter("orgSelect") ; 
			String selectedProxy = request.getParameter("OrgResourceSelect") ;
			String proxyRevision = request.getParameter("proxyRevision") ;
			ProxyRevision pr =    ms.getOrgByName(selectedOrg).getProxy(selectedProxy).getRevision("147") ;
			List<Flow> apigeeFlows= pr.checkOpenApiConsistancy("https://api-test.moj.gov.local/") ; 
			for (Flow flow : apigeeFlows )
			{
				flow.getName() ;
				String flowPath = flow.extractPathSuffixFromCondition() ; 
				String flowVerb = flow.extractVerbFromCondition() ; 
				ArrayList<Operation> machedOasOper = flow.getMatchedOasOper();
				if (machedOasOper.size() == 0 )
				{
					out.println("") ; 
				}
				out.print(Renderer.objectToHtmlTable(machedOasOper)) ;
			}
			
			
		%> 
		</body>
</html>