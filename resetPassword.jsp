<%@page import="com.implex.database.map.SecUsrDta"%>
<%@page import="Support.Misc"%>
<%@page import="com.implex.database.map.services.SecUserDataService"%>
<%@page import="com.implex.database.map.services.ModuleServicesContainer"%>

<%@page errorPage="errorPage.jsp" %>
<%@page import="java.sql.Statement"%>
<%@page import="Support.mail.MailSender , com.implex.database.map.SysParams"%>
<%@page import="java.security.SecureRandom"%>
<%@page import="java.util.Random"%>
<%@page  language="java" contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Reset Password</title>
</head>
<body dir="rtl" >

<h2  align="center"> Password Reset Using User Email Registered in The System   </h2>
<h2 align="center" > إسترجاع كلمة المرور بإستخدام الايميل المسجل بالنظام </h2>

<%
String userEmailAddress = request.getParameter("userEmailAddress") ; userEmailAddress = (userEmailAddress==null)? "" : userEmailAddress ;  
String userID = request.getParameter("userID") ; userID = (userID==null)? "" : userID ; 
String selectedConnParamsName = request.getParameter("selectedConnParamsName") ; selectedConnParamsName = (selectedConnParamsName==null)? "PNU_PROD" : selectedConnParamsName ;

boolean sbmitted = request.getParameter("submit")!= null ;
if ( ! sbmitted) {
%>
 
<form action="" method="post">
	<br>إدخال البريد الالكنرونى للمستخدم- Please Enter Your Email  
	<br><input name ="userEmailAddress" type = "text"  value="<%=userEmailAddress%>">
	<br> 
	<br>إدخال إسم المستخدم - Enter Your User ID    
	<br><input name ="userID" type = "text"  value = "<%=userID%>" >
	
	<input type="hidden" name="selectedConnParamsName" value = "<%=selectedConnParamsName%>" >
	
	<br><br><br><input type="submit" name="submit">
</form>
<% } %>
<% if (sbmitted) 
{
	//1- Get User Object Info.  
	ModuleServicesContainer msc = Support.Misc.getModuleServiceContainer(selectedConnParamsName , 1 );  
	SecUserDataService secUsrDtaServices = msc.getSecUserDataService();
	SecUsrDta userToBeReset = secUsrDtaServices.getUserByUserNameAndEmail(userID.trim().toUpperCase() , userEmailAddress.trim().toUpperCase() );
				
	String randomPassword = userToBeReset.alterUserPassword(); 
	com.implex.database.DataSet sysParams = userToBeReset.getUserCompany().getSysParams() ; 
	String appUrl = ((SysParams) sysParams.getFirstFilteredPO("E_NAME" , "AppURL")).getValValue();
    appUrl = appUrl +"/SmartTool/Company/"+userToBeReset.getUsrCmpIdValue()+"/loginScreen.jsp" ; 
	String message = "<div align='right' dir='rtl'> تم تغيير كلمة المرور لحسابك فى نظام قياس الاداء  "
						+"<br> اسم المستخدم : " +userToBeReset.getUsrNameValue() 
						+"<br> البريد الالكتروني :" +userToBeReset.getUsrEmailValue() 
						+"<br> كلمة المرور الجديدة  : " + randomPassword ;
    message += "<br><br><a href = "+appUrl+">إضغط هنا للدخول للنظام </a> </div>" ;

    String subject = "EPM System Password Reset Request result - كلمة مرور جديدة لنظام قياس الاداء " ;

	userToBeReset.sendResetPasswordEmail(subject , message) ; 

	%> تم إرسال ايميل الى بريد المستخدم المسجل بالنظام يتحوى على كلمة المرور الجديدة <br>An Email Message sent To User Email Address with the new password instructions
	<br>
	<div align="center">
		<a href = "Company/<%=userToBeReset.getUsrCmpIdValue()%>/loginScreen.jsp"  > Login to System  الدخول للنظام </a>
	</div> 
	<%
}
%>
</body>
</html>