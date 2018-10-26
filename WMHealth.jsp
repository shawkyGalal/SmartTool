<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
<head>
<title>SADAD Overall Health</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<%

java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
  java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");
  if (con == null || repCon ==null )
  { session.setAttribute("comeFrom","sadadHealth.jsp" + "?" + request.getQueryString());
    //session.setAttribute("queryString",request.getQueryString());
    response.sendRedirect("loginScreen.jsp");
  }
java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
java.util.Date nowDate = new java.util.Date();
String today= sdf.format(nowDate);
%>
<frameset rows="300,*" cols="*" border="0" scrolling="Yes"
	framespacing="0">
	<frameset name="upFrames" rows="*" cols="25%,25%,25%,25%"
		framespacing="0" border="0">
		<frame src="http://10.16.25.9:5285" name="Neptune_Banks"
			scrolling="Yes">
		<frame src="http://10.16.25.9:5355" name="Neptune_Billers"
			scrolling="Yes">
		<frame src="http://10.16.25.13:5285" name="Venus_Banks"
			scrolling="Yes">
		<frame src="http://10.16.25.13:5355" name="Venus_Billers"
			scrolling="Yes">
	</frameset>
	<frameset name="downFrame" rows="*" cols="25%,25%,25%,25%"
		framespacing="0" border="0">
		<frame src="http://10.16.24.17:5255" name="Collins_Banks"
			scrolling="Yes">
		<frame src="http://10.16.24.17:5265" name="Collins_Billers"
			scrolling="Yes">
		<frame src="http://10.16.24.21:5255" name="Duke_Banks" scrolling="Yes">
		<frame src="http://10.16.24.21:5265" name="Duke_Billers"
			scrolling="Yes">
	</frameset>
</frameset>
<noframes>
<body>

</body>
</noframes>
</html>
