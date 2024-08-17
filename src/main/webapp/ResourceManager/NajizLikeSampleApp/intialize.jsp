<%@page import="com.smartValue.web.AppContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@page import ="java.util.*"%>
<%@page import ="java.io.InputStream"%>
<%@page import ="java.io.InputStream"%>
<%@page import ="com.smartvalue.moj.clients.environments.*"%>
<%@page import ="com.smartvalue.moj.clients.environments.Environment"%>


<!DOCTYPE html>
<html>
<head>

<title>Najiz Initialization</title>
</head>
<body>
<%

Environment mojEnv = (Environment) AppContext.getMojEnv(session);
if (mojEnv == null)
{
	ServletContext serveletContext = request.getServletContext();
	InputStream inputStream = serveletContext.getResourceAsStream("/WEB-INF/classes/moj-environments.json");
	Environments mojEnvs = ClientEnvironmentsFactory.create(inputStream) ;
	mojEnv  =mojEnvs.getEnvByName("prod") ;
	mojEnv.initialize() ; 
	mojEnv.setAccessTokenMandatory(true); 
	AppContext.setMojEnv(session , mojEnv ) ; 
}
		
%>
</body>
</html>