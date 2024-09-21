
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

<h2>Select Proxy revision To Check Consistency Between its Flows and the Defined OAS Operations  </h2> 
<br> 

    
<jsp:include page="../apigeeItemSelector.jsp" > 
  <jsp:param name="targetPage" value="Result.jsp?" />
  <jsp:param name="neededAttributes" value="org, orgResourceType" />
  <jsp:param name="extraNeededFormParams" value="proxyRevision" />
  
</jsp:include>
 
</body>
</html>