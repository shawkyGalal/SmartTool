
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

<h2>Select Apigee Infrastructure to Display Target Servers for the selected environment </h2> 
<br> 

    
<jsp:include page="../apigeeItemSelector.jsp" >
  <jsp:param name="targetPage" value="targetServersResult.jsp" />
   <jsp:param name="neededAttributes" value="org,env" />
</jsp:include>
 
</body>
</html>