
<%@page import ="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h2>Select Apigee Organization To Start Uploading Last Transformed Proxy Bundles   </h2> 
<br> 
<%

String targetPage = request.getParameter("targetPage");

%>
    
<jsp:include page="../../../ApigeeAdmin/apigeeItemSelector.jsp" >
  	<jsp:param name="targetPage" value="load.jsp" />
 	<jsp:param name="neededAttributes" value="org" /> 
</jsp:include>
 
</body>
</html>