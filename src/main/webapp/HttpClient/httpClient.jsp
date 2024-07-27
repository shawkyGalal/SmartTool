<%@page import="com.smartValue.database.map.SecUsrDta"%>
<%@page import="com.masterWorks.httpClient.map.HttpRequest"%>
<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256" 
    import=" java.util.List , Support.Misc"
 
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>

<%
	SecUsrDta loggedUser = Support.Misc.getLoggedUserFromSession(session); 
	String httpRequestId = request.getParameter("httpRequestId") ; 
	String query = "Select * from " + HttpRequest.DB_TABLE_OWNER + "." + HttpRequest.DB_TABLE_NAME + " Where " + HttpRequest.REQUEST_ID + "=" +  httpRequestId ; 
	HttpRequest httpRequest = (HttpRequest) loggedUser.getDbService().queryForDataSet(query, HttpRequest.class).getPersistantObjectList().get(0) ;
	httpRequest.send() ; 
	out.print(httpRequest.getResponse()) ; 
%>

</body>
</html>