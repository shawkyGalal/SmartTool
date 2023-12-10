<%@ page contentType="text/html;charset=UTF-8" language="java"
	errorPage="errorPage.jsp"%>
<html>
<head>
<title>Sending Error Report To System Support</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body BACKGROUND="Nccibgd2.gif" TEXT="blue" LINK="red">
<%
      request.setCharacterEncoding("UTF-8");
      String errorDetails = request.getParameter("errorDetails");
      String userComment = request.getParameter("userComment");
      String DBUserID = request.getParameter("DBUserID");
      String dateTime = request.getParameter("dateTime");
      String remoteAddr = request.getParameter("remoteAddr");
      String serlveltContext = request.getParameter("serlveltContext");
      String realPath = request.getParameter("realPath");
      //------------Creating Message Body--------------------------------
      String     mailBody = "ERROR DETAILS: "+errorDetails ;
      mailBody = mailBody + "<p>Usre Comments : " + userComment;
      mailBody = mailBody + "<p>User ID : " +DBUserID;
      mailBody = mailBody + "<p>Date And Time" + dateTime;
      mailBody = mailBody + "<p>Web Client : " +remoteAddr;
      mailBody = mailBody + "<p>Application :" + serlveltContext;
      mailBody = mailBody + "<p>Real Path  :" + realPath;
      //-------------------------
       String smtpServerIP = "130.1.2.36";
       String supportAdminMail = "foda_sh@NCCI.COM.SA";
      java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");
       if (repCon !=null )
      {
        Support.Misc msc = new Support.Misc(repCon);
        smtpServerIP = msc.getSystemParameterValue(2);
        supportAdminMail = msc.getSystemParameterValue(3); 
      }
      String[] mailRecepiants = {supportAdminMail,"foda_sh@NCCI.COM.SA"};
            
      Support.mail.MailSender mailSender = new  Support.mail.MailSender(smtpServerIP , "sfoda" , "foda");  //--------Connection is passed to get SMTP SERVER IP from DB -
      Support.mail.EmailMessage em = new Support.mail.EmailMessage("Support@NCCI.COM.SA",mailRecepiants,null, "Error In The Support System", mailBody);
      mailSender.sendMail(em);
    %>
<div align="center">
<p>A Mail Send to The System Support For This Error.</p>
<p>Please Keep Following Up</p>
</div>
</body>
</html>
