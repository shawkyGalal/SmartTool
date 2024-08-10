 <%@page import="com.smartvalue.moj.najiz.mapping.appointments.auto.Appointment"%>
<%@page import="com.google.gson.Gson"%>
 <%@page import ="com.smartvalue.moj.clients.environments.*"%>
 <%@page import ="com.smartvalue.moj.clients.environments.Environment"%>
 <%@page import ="com.smartvalue.apigee.rest.schema.ApigeeAccessToken"%>
 <%@page import ="com.smartvalue.apigee.resourceManager.*"%>
 <%@page import ="com.mashape.unirest.http.*"%>
 <%@page import ="com.google.gson.internal.LinkedTreeMap"%>
 <%@page import ="com.smartvalue.moj.najiz.mapping.appointments.Appointments"%>
 <%@page import ="com.smartvalue.moj.najiz.mapping.appointments.auto.Request"%>
 <%@page import ="com.smartvalue.moj.najiz.mapping.appointments.AppointmentServices"%>
 <%@page import ="com.mashape.unirest.http.exceptions.UnirestException"%>

 
 
<!DOCTYPE html>
<html>
<head>

<title>Insert title here</title>
</head>
<body>
<%@include file="../intialize.jsp" %>
<form action="">

<%
	
		HttpResponse<String> serviceResponse = null ; 
		
		%><h1>Proxy : Appointment </h1><%
		%><h2>Flow Name : GetPersonFutureAppointmentsCount </h2><%
		
		try { serviceResponse = mojEnv.getAppointmentService().getMyAppintmentsCount(); }
		catch ( UnirestException | AccessTokenNotFound t) {response.sendRedirect("/ResourceManagerWeb/NajizLikeSampleApp/index.jsp") ; return ;  }
		
		out.print(Renderer.objectToHtmlTable(serviceResponse));
		%>
		<h1> List My Appointments <h1>
		<h2>Flow Name : GetPersonFutureAppointments </h2>
		<%
		
		//AppointmentServices  appointServices = mojEnv.getAppointmentService() ;
		List<Request> myRequests ;  
		List<Appointment> myAppointments ; 

		try { 
				myRequests = mojEnv.getAppointmentService().getMyRequests(); 
				myAppointments = mojEnv.getAppointmentService().getMyAppontments() ; 
				%><li>Your Appointments Requests</li><%
				for (Request reqx : myRequests ) 
				{
					out.print(Renderer.objectToHtmlTable(reqx));
					out.print("<a href = 'Delete.jsp?protectedId="+reqx.getProtectedId()+"' >Delete </a>") ; 
				}
				%><li>Your Appointments</li><%
				
				for (Appointment appo : myAppointments ) 
				{
					out.print(Renderer.objectToHtmlTable(appo));
					//out.print("<a href = 'Delete.jsp?protectedId="+appo.getProtectedId()+"' >Delete </a>") ; 
				}
				
		}
		catch ( UnirestException | AccessTokenNotFound t)	{
			response.sendRedirect("/ResourceManagerWeb/NajizLikeSampleApp/index.jsp") ; return ;  
		}
	 %>
	 <br>
	 <a href="CreateForm.jsp">New Appointment Request</a>

</body>
</html>