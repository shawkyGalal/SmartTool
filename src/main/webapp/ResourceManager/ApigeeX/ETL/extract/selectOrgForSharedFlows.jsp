
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

<h2>Select Apigee Organization To Start The Export Process  </h2> 
<br> 
<%

String bundleType = request.getParameter("bundleType");

%>
    
<jsp:include page="../../../ApigeeAdmin/apigeeItemSelector.jsp" >
  	<jsp:param name="targetPage" value="bundledObjects.jsp?bundleType=sharedFlows" />
 	<jsp:param name="neededAttributes" value="org" /> 
 	
</jsp:include>
 
</body>
</html>