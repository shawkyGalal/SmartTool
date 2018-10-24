	<%@ page errorPage="errorPage.jsp"%>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	    pageEncoding="ISO-8859-1"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	
	<%@page import="Support.TextFileLineEditor"%>
	<%@page import="Support.XMLConfigFileReader"%>
	<%@page import="Support.SysConfigParams"%>
	<%@page import="com.implex.database.map.SysParams"%>
	
	<%@page import="com.sideinternational.sas.configuration.Configuration"%>
	
	
	
	<%@page import="java.io.File"%>
	<%@page import="com.sideinternational.mail.MailSender"%>
	<%@page import="com.implex.database.map.SecUsrDta"%><html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	</head>
	<body>
	<form action="" method="get">
	<p><em><strong>SmartTool will Send an Email to
	SmartTool Administrator to Create an Email Notification Message with
	your selected Report Please write down your requirments including
	notification schedual and any others requirments</strong></em></p>
	<p>&nbsp;</p>
	<table width="75%" border="1">
		<tr>
			<td width="23%">&nbsp;</td>
			<td width="73%"><input type="text" name="textfield"></td>
		</tr>
		<tr>
			<td><strong>Your Requirments </strong></td>
			<td><textarea name="userReq" cols="80"></textarea></td>
		</tr>
	</table>
	<p align="center"><input type="hidden" name="id"
		value=<%=request.getParameter("id")%>> <input type="hidden"
		name="seq" value=<%=request.getParameter("seq")%>> <input
		type="submit" name="Submit" value="Submit"></p>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
	</form>
	<% 
	if (request.getParameter("Submit") != null)
	{
		SecUsrDta loggedUser = (SecUsrDta) session.getAttribute("loggedUser") ; 
		String companyId = loggedUser.getUsrCmpIdValue(); 
	String queryId = request.getParameter("id");
	String querySeq = request.getParameter("seq");
	String configSubDir = com.sideinternational.web.swaf.SWAF.getConfigurationSubDirectory() ; 
	
	//File inputFile = new File (session.getServletContext().getRealPath("Batches/If_Data_Found_Send_Mail/Mailesender_templ.bat"));
	String configFolder = Configuration.getConfigurationHome() +"/"+ configSubDir ; 
	File inputFile = new File ( configFolder+"/Batches/If_Data_Found_Send_Mail/Mailesender_templ.bat");
	//File outFile = new File (session.getServletContext().getRealPath("Batches/If_Data_Found_Send_Mail/"+companyId+"/Mailesender_"+queryId+"_"+querySeq+".bat"));
	File outFile = new File (configFolder + "/Batches/If_Data_Found_Send_Mail/"+companyId+"/Mailesender_"+queryId+"_"+querySeq+".bat");
	
	TextFileLineEditor tfle = new TextFileLineEditor(inputFile , outFile );
	String[] oldValues = {"<QUERY_ID>" , "<QUERY_SEQUANCE>" , "<DB CONFIGURATION FILE CONNECTION NAME>", "<USERNAME>" , "<PASSWORD>" , "<OUT_LOG_FILE>"};
	
	XMLConfigFileReader aa = new XMLConfigFileReader( false);
	//Misc abc = new Misc(repCon);
	com.implex.database.DataSet sysParamsDs = ((SecUsrDta) session.getAttribute("loggedUser")).getUserCompany().getSysParams(); 
	String autologinDB = ((SysParams)sysParamsDs.getFirstFilteredPO("E_NAME" , "AutoLogin_Environment_Name" )).getValValue(); //  SysConfigParams.getAutoLogin_Environment_Name();// abc.getSystemParameterValue(8);
	String autoLoginUserName = ((SysParams)sysParamsDs.getFirstFilteredPO("E_NAME" , "AutoLogin_Username" )).getValValue(); //  SysConfigParams.getAutoLogin_Username();// abc.getSystemParameterValue(9);
	String autoLoginPassword = ((SysParams)sysParamsDs.getFirstFilteredPO("E_NAME" , "AutoLogin_Password" )).getValValue(); //  SysConfigParams.getAutoLogin_Password();// abc.getSystemParameterValue(10);
	
	
	String[] newValues = {queryId , querySeq , autologinDB , autoLoginUserName , autoLoginPassword , queryId + "_" + querySeq + ".log"};
	tfle.replace(oldValues , newValues , 0 , 2000);
	// Send Email to System Admin Requesting to create a scheduled task
	String message = "";
	try{
	    String smtpServerIP = Support.SysConfigParams.getSMTP_SERVER();
	    String supportAdminMailSender = Support.SysConfigParams.getSupport_User_Email();
	    String supporEmailReciver = Support.SysConfigParams.getAdmin_Notify_Mail_Address_Receiver();
	
	    String remoteAddr = java.net.InetAddress.getByName(request.getRemoteAddr().toString()).getHostName(); 
	
	  // if (! remoteAddr.equals("10.16.18.181") ) //-------execlude loging from my labtop when connected to local network 
	  { 
	  /* Using a seperate thread to send an email notification ..
	    Thanks to Java threading this is greatly enhances the user satisfaction
	  */
	   String userRequirments = request.getParameter("userReq");
	   message = "User Has the Following Requirments <br>" + userRequirments ;
	   message +="<br> Batch file for the selected report is : " + outFile.getAbsolutePath()  ;
	   String subject = "Request For a New Scherdualed Task";
	   String[] to = {supporEmailReciver};
	    Support.mail.EmailMessage em = new Support.mail.EmailMessage();
	    em.setFrom(supportAdminMailSender);
	    em.setTo(to);
	    em.setSubject(subject);
	    em.setBody(message);
	   
	    Support.mail.MailSender ms = new Support.mail.MailSender(smtpServerIP , "sfoda" , "redsea11");
	    Support.mail.MailSenderThread mst = new Support.mail.MailSenderThread(em ,ms);
	    mst.start();
	    }
	}
	catch (Exception e){out.print("Smart Tool Failed to Send an Email to System Admin ... Please contact Your System Admin.");}
	
	out.println("Message : " + message + "Send to System Admin");
	}
	%>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
	</body>
	</html>