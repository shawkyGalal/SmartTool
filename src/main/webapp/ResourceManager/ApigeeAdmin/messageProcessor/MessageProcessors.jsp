
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

<h2>Display Message Processors associations with Environment for all available Regions </h2> 
<br> 

    
<jsp:include page="../apigeeItemSelector.jsp" >
    <jsp:param name="targetPage" value="MessageProcessorsResult.jsp" />
</jsp:include>
 
</body>
</html>