 <%@page import="org.apache.commons.beanutils.BeanUtils"%>
<%@page import="com.smartvalue.moj.najiz.mapping.appointments.Request"%>
 <%@page import ="com.smartvalue.apigee.rest.schema.ApigeeAccessToken"%>
 <%@page import ="com.smartvalue.apigee.resourceManager.*"%>
 <%@page import ="com.mashape.unirest.http.*"%>
 <%@page import ="com.smartvalue.moj.najiz.mapping.appointments.AppointmentServices"%>
 <%@page import ="com.mashape.unirest.http.exceptions.UnirestException"%>
 
 
 
<!DOCTYPE html>
<html>
<head>

<title>Create New Appointment request action</title>
</head>
<body>
<%@include file="../intialize.jsp" %>
    
<%
		%><h1>Proxy : Create Request </h1><%
		%><h2>Flow Name : Create An Appointment Request </h2><%
		
		Request req01 = new Request() ;
		BeanUtils.populate(req01, request.getParameterMap() ); 
		AppointmentServices  appointmentServices = mojEnv.getAppointmentService() ;
		HttpResponse<String> res = null ; 
		try {  
			res = appointmentServices.createRequest(req01); 
		}
		catch ( UnirestException | AccessTokenNotFound t) {response.sendRedirect("/ResourceManagerWeb/NajizLikeSampleApp/index.jsp") ; return ;  }
		
		out.print(Renderer.objectToHtmlTable(req01));
		%>
		 <br><br>Najiz Response : 
		<% 
		out.print(Renderer.objectToHtmlTable(res));
%>
</body>
</html>