<%@ page language="java"     %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1> Jathwa Application  </h1>
	
	<%@include file="intialize.jsp" %>
	<%
		String authURL = mojEnv.getUrlBuilder()
									.withForthAuth(false)
									.withResponseType("code")
									.withScope("openid%20xxx%20yyyy")
									.buildAuthorizationURL();
		response.sendRedirect(authURL) ; 
		
	%>
	if not redirected , please click on : 
	<a href = "">Nafath Mowahad</a>
	
	
	
	
</body>
</html>