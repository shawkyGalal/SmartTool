<%@ page contentType="text/html;charset=UTF-8" language="java"
	errorPage="errorPage.jsp"%>
<%@page import="com.implex.database.map.SecUsrDta" %>
<%@page import="com.implex.database.map.SysParams"%>
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
      mailBody = mailBody + "...<a target = 'User Details' href = '"+Support.Misc.getAppURL(request)+"/queryResult.jsp?id=34491&obj_name="+DBUserID+"'> User Details </a>" ;  
      mailBody = mailBody + "<p>Date And Time" + dateTime;
      mailBody = mailBody + "<p>Web Client : " +remoteAddr;
      mailBody = mailBody + "<p>Application :" + serlveltContext;
      mailBody = mailBody + "<p>Real Path  :" + realPath;
      //-------------------------
       //String smtpServerIP = "10.16.18.12";
       String mailFrom = "shawky.foda@sadad.com";
       String mailReceiver = "";
      java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");
            
        //Support.Misc msc = new Support.Misc(repCon);
    	SecUsrDta loggedUser  = (SecUsrDta) session.getAttribute("loggedUser" ) ; 
        com.implex.database.DataSet sysParams = loggedUser.getUserCompany().getSysParams() ;
         mailFrom = ((SysParams) sysParams.getFirstFilteredPO("E_NAME" , "Support User Email")).getValValue(); // Support.SysConfigParams.getSupport_User_Email();
         mailReceiver = ((SysParams) sysParams.getFirstFilteredPO("E_NAME" , "Admin_Notify_Mail_Address_Receiver")).getValValue(); //Support.SysConfigParams.getAdmin_Notify_Mail_Address_Receiver();

        //smtpServerIP =Support.SysConfigParams.getSMTP_SERVER();// msc.getSystemParameterValue(2);
        //mailFrom = Support.SysConfigParams.getSupport_User_Email();// .getSystemParameterValue(3); 
        //mailReceiver = Support.SysConfigParams.getAdmin_Notify_Mail_Address_Receiver();// .getSystemParameterValue(3);         
        //String useGmailStr = Support.SysConfigParams.getUseGmailAccount(); 
        //boolean useGmail = (useGmailStr.equals("Y")  || useGmailStr.equals("y"));
      String[] mailRecepiants = {mailReceiver};
      
      Support.mail.MailSender mailSender = new  Support.mail.MailSender(sysParams);  //--------Connection is passed to get SMTP SERVER IP from DB -
      //mailSender.useGmail(useGmail);
      String[] ccRecepiants = null;
      Support.mail.EmailMessage em = new Support.mail.EmailMessage(mailFrom,mailRecepiants,ccRecepiants,"Error In The Support System", mailBody);
      
      mailSender.sendMail(em);
    %>
<div align="center">
<p>A Mail Sent to The System Support For This Error.</p>
<p>Please Keep Following Up</p>
</div>
</body>
</html>
