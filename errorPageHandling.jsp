<%@page import="com.implex.database.map.SecUsrDta"%>
<%@ page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>

<HTML>
<HEAD>
<TITLE>System Error Page  </TITLE>
<% String appURL = Support.Misc.getAppURL(request) ;  %>
<script type="text/javascript" src="<%=appURL%>/includes/smartTool.js"></script>
</HEAD>
<BODY bgcolor="#FFEEFF" TEXT="blue" LINK="red">
<p>

<center></center>
<% String appPath = Support.Misc.getAppURL(request) ;  %>
<p align="center"><big><font color="#FF0000" size="4"><strong>
<input name="imageField" type="image" src="<%=appPath%>/images/error1.jpg" width="100"
	height="100" border="0"> <br>
<input name="imageField" type="image" src="<%=appPath%>/images/alert_error.gif"
	width="34" height="34" border="0"> System Error حدث خطأ ما بالنظام</strong></font></big></p>
<% 


Throwable exception = (Throwable )request.getAttribute("exception");

String shortMessage = exception.getMessage() ;
if (shortMessage != null && shortMessage.indexOf("Unable to find Details of User") >= 0 ) shortMessage ="Invalid User name and/or Password اسم المستخدم او كلمة المرور غير صحيحة" ;

else 
{
	
	int index1 = (shortMessage != null )? shortMessage.indexOf("ORA-") : -1;
	if (index1 != -1)
	{
	try{
	shortMessage = shortMessage.substring( shortMessage.indexOf("ORA-") + 10) ;
	shortMessage = shortMessage.substring( 0 ,  shortMessage.indexOf("ORA-")) ;
	if (shortMessage.indexOf("unique constraint") >= 0 )   shortMessage ="Similar Item aready added - يوجد عنصر مشابه للعنصر المراد تسجيله " ;  
	
	}
	catch (Exception e) {}
	}
}

SecUsrDta loggedUser = Support.Misc.getLoggedUserFromSession(session) ; 

%><p align="center"> <font color="#FF0000" size="4">
	<strong> <%=shortMessage%> 
		<br><br> <font color="#FF0000" size="2"> Note: You Can Automatically Send this Error to System Admin By Click The Below "Show Details" Button then Click on "Send This Error to System Admin" </font> </br>
	</strong></font></p>
<%   
try {
	String indexPath = "" ;
	indexPath = "/Company/"+ Support.Misc.getLoggedUserFromSession(session).getUsrCmpIdValue() ;  
	%><p align="center">&nbsp;<a href = "<%=appPath + indexPath %>/index.jsp" target = "parent">Back To Main Page الصفحة الرئيسية  </a> </p><% 
}
catch (Exception e ){
	Cookie loginPageCookie  = Support.Misc.getCookiByName(request , Support.Misc.LoginScreenPageNameCookiVarName) ;
	%> <a href = "/SmartTool/<%=loginPageCookie.getValue()%>" >Back To Login Screen </a><% 
} %>


<% 
     String DBUserID = "";
     java.sql.Connection dbCon = (java.sql.Connection) session.getAttribute("con");
     try{ //-----------Getting User DB Name
        if (dbCon!=null)
        {
         DBUserID = dbCon.getMetaData().getUserName();
        }
     }
     catch (Exception e){}

	if (! DBUserID.equalsIgnoreCase("ANONYM")) 
{ %>

	<p align="center"><input type="submit" name="toggleDetails" value="Show Details" onClick="toggleShowDetails();"> 
	
	
	<div id="DetailLayer" 	style="visibility: hidden;">
		<form name="form1" method="post" action="/SmartTool/sendError2SysSupp.jsp">
			<p align="center">
			
			<p align="center"><%=Support.Misc.repalceAll( exception.getMessage() , "\n" , "<br>" )%>
			<br>
				<textarea name="errorDetails" cols="100" rows="10" readonly="readonly">
				<% exception.printStackTrace(new java.io.PrintWriter(out));%>
				</textarea>
			</p>
	<%
	     String dateTime = new java.util.Date().toString();
	   	 String remoteAddr = java.net.InetAddress.getByName(request.getRemoteAddr().toString()).getHostName(); //
	  	 String myApplication =request.getRequestURI()+"?"+request.getQueryString();
	     String realPath =session.getServletContext().getRealPath("\\xxx") ;
	   %>
	<table width="71%" border="0" align="center">
		<tr> 
			
			<td ><div title="Please Provide More Details about the Error, When and How You Get This Error برجاء كتابة اي تفاصيل عن هذا الخطأ " > <strong>Your Comment تعليقك عن الخطأ - متى و كيف تم حدوثه ؟</strong> </div></td>
			<td><textarea name="userComment" cols="70"></textarea></td>
			
		</tr>
		<tr>
			<td><strong>User ID</strong></td>
			<td><%=DBUserID%> <input type="hidden" name="DBUserID"
				value="<%=DBUserID%>"></td>
		</tr>
		<tr>
			<td width="28%"><strong>Date &amp; Time</strong></td>
			<td width="72%"><%=dateTime%> <input type="hidden"
				name="dateTime" value="<%=dateTime%>"></td>
		</tr>
		<tr>
			<td><strong>Web Client</strong></td>
			<td><%=remoteAddr%> <input type="hidden" name="remoteAddr"
				value="<%=remoteAddr%>"></td>
		</tr>
		<tr>
			<td><strong>Application</strong></td>
			<td><%=myApplication%> <input type="hidden"
				name="serlveltContext" value="<%=myApplication%>"></td>
		</tr>
		<tr>
			<td><strong>Real Path</strong></td>
			<td><%=realPath%> <input type="hidden" name="realPath"
				value="<%=realPath%>"></td>
		</tr>
	</table>
	<p align="center"><input type="submit" name="Submit2"
		value="Send This Error To System Admin"></p>
	</form>
	</div>

<% } %>

</BODY>
</HTML>
