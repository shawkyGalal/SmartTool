<%@ page errorPage="errorPage.jsp"%>
<%@ page import="java.util.*, Support.Misc , com.implex.database.map.SecUsrDta"%>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<head>
<% String appURL = Support.Misc.getAppURL(request) ;  %>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=appURL%>/includes/AJAX_new.js"></script>
<script src="<%=appURL%>/jQueryAssets/jquery-1.10.2.js"></script>
<script src="<%=appURL%>/jQueryAssets/jquery-ui.js"></script>

<link href="<%=appURL%>/jQueryAssets/jquery.ui.core.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=appURL%>/jQueryAssets/jquery-ui.css">
<link href="<%=appURL%>/jQueryAssets/jquery.ui.theme.min.css" rel="stylesheet" type="text/css">

<%@ include file="includes/jquery_commons.js"%>
</head>
<%
String bodyAttrib = "";
if( request.getParameter("updateOnly") == null )
{
 bodyAttrib = "onLoad=\"refreshExecuteFreame();\"";
}
%>
<body <%=bodyAttrib%> bgcolor="#FFEEFF">
<%

%>
<script language="JavaScript" type="text/JavaScript">
  function refreshExecuteFreame()
  {
  	 top.frames[1].frames[2].history.go(0); 
  }
  </script>
	
	
<% 
java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
Support.ConnParms selectedConnParms = (Support.ConnParms)session.getAttribute("selectedConnParms"); %>
<form name="form2" method="post" action="" style="height: 20px">
<div id = "sysMessage" > </div>
<div align="center"><img src="images/smart-value.bmp"
			alt="Click to visit Smart Value Web Site" width="80" height="30"
			border="0" /> 
			<strong><font size="2"> </font><font
	color="#FF0000" size="2"><em>
			<% if (con.getMetaData().getURL().contains("Demo") ) { %>
			  <font color = red size="2" >  Demo Version </font> 
            <%} %>
	(<a	title="<%=con.getMetaData().getURL()%>" href=""> <%=Misc.getConnectionUserName(con) %></a>) <!--con.getMetaData().getURL().toUpperCase()%-->
</em></font><font size="2"> 

	<jsp:include page="/includes/dbTransc.html"></jsp:include>

	<%
		String loginScreenPage = null ; 
		//Cookie cookie = Misc.getCookiByName ( request , Misc.LoginScreenPageNameCookiVarName) ; 
		//loginScreenPage = (cookie!= null) ? cookie.getValue() : "loginScreen.jsp" ; 
		//loginScreenPage = (loginScreenPage == null ) ? "loginScreen.jsp" : loginScreenPage ;
		SecUsrDta loggedUser = (SecUsrDta)  session.getAttribute("loggedUser");
		Cookie loginPageCookie  = Misc.getCookiByName(request , Misc.LoginScreenPageNameCookiVarName) ;
		loginScreenPage = loginPageCookie.getValue(); // "Company/"+loggedUser.getUsrCmpIdValue() + "/loginScreen.jsp" ; 
	 %>
	</font>
	- <a target="_parent" href="<%=loginScreenPage%>" title="الخروج من النظام" onclick="return confirm('This Will Discard All Unsaved Changes, Are You Sure ? سوف يتم إلغاء جميع التعديلات التى لم يتم حفظها .. هل انت متأكد ؟')"> <em><font size="2">Change Login</font></em></a>
	
	<% 
		boolean smartToolAdmin = loggedUser.isSmartToolAdmin();
		if (smartToolAdmin)
		{
	%>
		<font size="3"> 
		- <a href="editAndExecute.jsp?id=25078" target="ConfigFrame" title = "Maintain Smart Tool System and User Parameters " > <em><font size="2"> Configure </font></em></a>
		- <a href="logs" target="Log Files">Logs</a> 
		- <a href="Smart_Docs/Smart_Tech_Guide.zip" 	target="Doc">Tech.User Guide </a> 
		- <a href="Smart_Docs/Smart_Tool_Demos/Smart_Tool_Demos.zip" target="Demo">Demo</a>
		</font>
	<%  } 
		String needYourAttentionQueryId = "34626" ;
		int allItemsCount = 2 ;
		
		boolean isAnonymUser = loggedUser.getUsrNameValue().equalsIgnoreCase("ANONYM"); 
		if (! isAnonymUser ) 
		{
	%>
	- <a href="usefulTools.htm" 	target="usefullTools" title="بعض الادوات المساعدة">Useful Tools</a>
	- <a href="queryResult.jsp?id=28234&lookupTableName=LU_QUERIES" target="User Prefrences" title = "إعدادات المستخدم" >User Prefrences</a>
	<a title = "Click To Display All Items Need Your Attention/Action" target = "mainFrame" href = "editAndExecute.jsp?id=<%=needYourAttentionQueryId %>&sys_user=<%=loggedUser.getUsrNameValue() %>" > <img src="images/emailIcon.jpg" width="30" height="20"></img> (<%=allItemsCount %>)</a>
	- <a target = "New UI" href = "./Company/20/index.jsp">New UI</a>
	<%} %>
	<img  title="تغيير إتجاه " src="images/direction.jpg" 
		       onclick="
		              document.dir='rtl' ; 
		          	  document.align='right' ;
		              " 
		       width="20" ></img>
	<a target = "Request For IT Service" Title = "Use this To Request a Service from IT/PID Department " href = "TableInsertForm.jsp?owner=MOEP&tableName=SERVICE_REQUEST">Request For IT Service </a>	       
	<% String compLogoLink =null; 
		try{compLogoLink = (String)loggedUser.getUserCompany().getAttribute("LOGO_LINK").getValue() ;}
		catch (Exception e) {}
	%>
	<%= compLogoLink%>
	
<!--  <jsp:include page="googleSuggest.html"></jsp:include>  --> 
<% boolean carrentSystem = loggedUser.getUserCompany().getAttributeValue("SYSTEM_ID").toString().equalsIgnoreCase("2")  ;   
  if (  carrentSystem && loggedUser.isSmartToolAdmin() )   // CARRENT SYSTEM  
	{ 	
		String loggedUserRowid = loggedUser.getRowIdString() ;
		String loggedUserCompanyID = loggedUser.getUserCompany().getCmpIdValue() ; 
		String changeCompanyDivId = "changeCompanyDivId" ;
		String updateScript = "if (  confirm ('This process will Save all your changes automatically are You Sure ?  هذه العملية تتطلب حفظ جميع التعديلات التى قمت بإجرائها فى هذه الشركة أتوماتيكا . هل انت متأكد؟  ') )   "  ; 
		updateScript += "\n { \n  sendAjaxRequestToJsp(encodeURI('"+appURL+"/updateDBAttribute.jsp?isHijriDate=false&isDateTime=false&isDate=false&tableName=SEC_USR_DTA&tableOwner=ICDB&columnName=USR_CMP_ID&rowIdValue="+loggedUserRowid+"&newValue='+ this.value )  , '"+changeCompanyDivId+"'); " ;
		updateScript += " \n } " ; 
		%>
		
		المؤسسة:
			<select onchange="<%=updateScript %>">
			 <option value="5" <%=(loggedUserCompanyID.equalsIgnoreCase("5"))? "selected ='selected'" :"" %> >أميرة</option>
			 <option value="6" <%=(loggedUserCompanyID.equalsIgnoreCase("6"))? "selected ='selected'" :"" %> >زهوة</option>
			 <option value="7" <%=(loggedUserCompanyID.equalsIgnoreCase("7"))? "selected ='selected'" :"" %> >أنوار</option>
			 <option value="2" <%=(loggedUserCompanyID.equalsIgnoreCase("2"))? "selected ='selected'" :"" %> >المريشيد</option>
			</select>
			<div id="<%=changeCompanyDivId %>" ></div>
		<%
	} 
%>
</strong></div>
</form>


</body>
</html>
