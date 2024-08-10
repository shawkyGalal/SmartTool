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
									.withScope("openid")
									.buildAuthorizationURL();
		
	%>
	Your are Not Logged in , or Your Session is Expired.  
	<a href = <%=authURL%> Target = "NajizLikeApp" >Login Using Nafath Mowahad </a>
	<br><br>
	
	
	<button type="button" class="white--text v-btn v-btn--is-elevated v-btn--has-bg theme--light v-size--default" id="rf_home_intro_individualsLoginBtn" style="width: 100%; background-color: rgb(0, 141, 85); border-color: rgb(0, 141, 85);"><span class="v-btn__content">
        تسجيل الدخول
        <i aria-hidden="true" class="v-icon notranslate mr-4 mdi mdi-arrow-left theme--light white--text"></i></span></button>
	
	
	
	
	
	
</body>
</html>