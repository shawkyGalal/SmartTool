<%@ page errorPage="errorPage.jsp"
	contentType="text/html;charset=UTF-8"%>
<%@ page import = "Support.Misc" %>	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
<head>
<title>SADAD Overall Health</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<%


java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
 String loggedUser = Misc.getConnectionUserName(con);
 if (   !loggedUser.equals("SHAWKY")
     && !loggedUser.equals("SFODA")     
     && !loggedUser.equals("WES")
     && !loggedUser.equals("EALSAFADI") 
     && !loggedUser.equals("SBADRAN")
     && !loggedUser.equals("IHILAL")
     && !loggedUser.equals("SALZAGHMOUT")
     && !loggedUser.equals("MALQAHTANI")
     && !loggedUser.equals("MALANEZI")
     && !loggedUser.equals("AABBAS")
    )
 {
  throw new Exception ("Sorry: Currently this Feature is not available to You, if You are interested, Please Contact Shawky Foda");
 }
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
<frameset rows="25%,40%,35%*" cols="*" border="0" scrolling="Yes"
	framespacing="0">
	<frameset name="upFrames" rows="*" cols="50%,50%" framespacing="0"
		border="0">
		<frame
			src="AJAX_Chart.jsp?id=17276&seq=0&from_date=<%=today%>&to_date=<%=today%>&BankId=ALL_BANKS"
			name="AJAX Chart">
		<frame src="executeQuery.jsp?id=17484" name="All Periodical Processes">
	</frameset>
	<frameset name="middleFrame" rows="*" cols="33%,33%,33%"
		framespacing="0" border="0">
		<frame src="executeQuery.jsp?id=13793"
			name="Latest STC Bill Upload Run Status" scrolling="Yes">
		<frame src="executeQuery.jsp?id=17165"
			name="Latest Delivery of Payment Notification to all billers">
		<frame src="executeQuery.jsp?id=16827"
			name="Latest Delivery of Biller Recon to all billers">
	</frameset>
	<frameset name="downFrame" rows="*" cols="33%,40%,27%" framespacing="0"
		border="0">
		<frame src="executeQuery.jsp?id=17613"
			name="Latest Payment Upload Run Status" scrolling="Yes">
		<frame src="executeQuery.jsp?id=8450" name="Currently Running Tasks">
		<!--  <frame src="executeQuery.jsp?id=17276&from_date=<%=today%>&to_date=<%=today%>" name="Payment Counter" scrolling="Yes" > -->
		<frame
			src="executeQuery.jsp?id=16934&from_date=<%=today%>&to_date=<%=today%>"
			name="Hourly SSQ">
	</frameset>
</frameset>
<noframes>
<body>

</body>
</noframes>
</html>
