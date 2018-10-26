<%@ page contentType="text/html;charset=windows-1252"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">
<title>User Profile</title>
</head>
<%

 java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");
 java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
  if (con == null || repCon ==null )
  { session.setAttribute("queryString",request.getQueryString());
    response.sendRedirect("loginScreen.jsp");
  }
 String user_id = Support.Misc.getConnectionUserName(con);

%>
<body>
<h2 align="center">User Profile</h2>
<p align="left">Please complete this info:</p>

<form name="form1" method="post" action="UserProfile.jsp">
<table width="75%" border="1">
	<tr>
		<td>Your Smart Account</td>
		<td><%=user_id%></td>
	</tr>
	<tr>
		<td width="29%">Your Email Address:</td>
		<td width="71%"><input name="emailAdsress" type="text" size="50"
			value="abcd@sadad.com"></td>
	</tr>
	<tr>
		<td>Your Mobile No</td>
		<td><input name="mobileNo" type="text" value="00966"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
</table>
<p align="center"><input type="submit" name="Submit" value="Submit">
<br>
</p>
<p align="left">&nbsp;</p>
</form>
<%
if (request.getParameter("Submit") != null)
{
 String emailAdsress = request.getParameter("emailAdsress");
 String mobileNo = request.getParameter("mobileNo");
 repCon.createStatement().execute("delete from users_info where user_id= '"+user_id+"'");
 repCon.createStatement().execute("insert into users_info (user_id, email, mobile_no) values ('"+user_id+"','"+emailAdsress+"','"+mobileNo+"')");
 repCon.commit();
 out.println("Your Profile Added Successfully.. Thanks");
 
  //-------Send a notification mail --- 
 try{
      Support.Misc msc = new Support.Misc(repCon);
      String smtpServerIP = Support.SysConfigParams.getSMTP_SERVER();// msc.getSystemParameterValue(2);
      String remoteAddr = java.net.InetAddress.getByName(request.getRemoteAddr().toString()).getHostName(); 
      // if (! remoteAddr.equals("10.16.18.181") ) //-------execlude loging from my labtop when connected to local network 
      { 
        String message = "Dear "+user_id+", \nThank You for using Smart Tool User Profile" ;
        Support.mail.MailSender ms = new Support.mail.MailSender(smtpServerIP , "sfoda" , "foda");
        String[] to = {emailAdsress, "sfoda@sadad.com"};
        Support.mail.EmailMessage  em = new Support.mail.EmailMessage("smart_alerts@sadad.com",to,null,"Thank You" , message);
        ms.sendMail(em);
        out.println("<p> an email sent To You");
       }
     }
 catch (Exception e){}
 
}
%>
<p align="left">&nbsp;</p>
</body>
</html>
