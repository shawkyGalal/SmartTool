 <%@page import="com.google.gson.Gson"%>
 <%@page import ="com.smartvalue.moj.clients.environments.*"%>
 <%@page import ="com.smartvalue.moj.clients.environments.Environment"%>
 <%@page import ="com.smartvalue.apigee.rest.schema.ApigeeAccessToken"%>
 <%@page import ="com.smartvalue.apigee.resourceManager.*"%>
 <%@page import ="com.mashape.unirest.http.*"%>
 <%@page import ="com.google.gson.internal.LinkedTreeMap"%>
  
 
<!DOCTYPE html>
<html>
<head>

<title>Insert title here</title>
</head>
<body>
<%@include file="../intialize.jsp" %>
<form action="">

<%
		Gson gson = new Gson();
		String serviceBasePath ; 
		String serviceSuffix ;
		String serviceUrl ; 
		HttpResponse<String> serviceResponse = null ; 
		
		%>
		
		<h1> List My Appointments <h1>
		<h2>Flow Name : GetPersonFutureAppointments </h2>
		<%
		serviceBasePath = "/v1/self-services/appointment-mobile" ;  
		serviceSuffix = "/api/people/$userId/appointments?includeRequests=True" ; 
		serviceUrl = mojEnv.getMojServicesBaseUrl() + serviceBasePath + serviceSuffix ; 
		serviceResponse = mojEnv.executeRequest( serviceUrl , null, "GET", "") ; 
		//out.print(Renderer.objectToHtmlTable(serviceResponse));
		HashMap<String, Object>  appointments = gson.fromJson(serviceResponse.getBody(), HashMap.class);
		ArrayList<Object> requests = (ArrayList) appointments.get("requests") ; 
		HashMap<String , String > extrLinks = new HashMap<String , String >() ; 
		extrLinks.put ( "deleteAppoint.jsp?id=xxx" , "delete" ) ;
		//out.print(Renderer.generateArrayHtmlTable(requests , extrLinks ));
		
		for (Object req : requests)
		{
			out.print("<tr>"); 
			LinkedTreeMap <Object , String> ReqMap = (LinkedTreeMap) req ;
			out.print(Renderer.hashMaptoHtmlTable(ReqMap));
		}
		
		 %>

</body>
</html>