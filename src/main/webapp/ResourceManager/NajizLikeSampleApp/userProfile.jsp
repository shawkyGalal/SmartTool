 <%@page import ="com.smartvalue.moj.clients.environments.*"%>
 <%@page import ="com.smartvalue.moj.clients.environments.Environment"%>
 <%@page import ="com.smartvalue.apigee.rest.schema.ApigeeAccessToken"%>
 <%@page import ="com.smartvalue.apigee.resourceManager.*"%>
 <%@page import ="com.mashape.unirest.http.*"%>
 <%@page import ="com.auth0.jwt.exceptions.TokenExpiredException"%>
 
 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@include file="intialize.jsp" %>
<br> 
<a href="Appointments/List.jsp" target="Your Appointments"> Manage Your Appointments</a>
<%
		
	out.print(Renderer.objectToHtmlTable(mojEnv.getAccessToken()));

%>



</body>
</html>