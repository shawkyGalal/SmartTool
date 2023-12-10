<%@ page errorPage="errorPage.jsp"%>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>
<%@page import = "Support.Misc" %> 
<%@page import="com.implex.database.map.SecUsrDta"%>
<html>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<head>
<%SecUsrDta loggedUser = (SecUsrDta) session.getAttribute("loggedUser") ; %>
<link rel="shortcut icon" href="/SmartTool/Company/<%=(loggedUser !=null) ? loggedUser.getUsrCmpIdValue(): 20%>/images/logo.png">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<% 

String reportTopOpen = request.getParameter("reportTopOpen"); 
reportTopOpen = (reportTopOpen == null) ? "30005" :reportTopOpen ;

if ( loggedUser == null ) 
{
	Cookie cookie = Misc.getCookiByName ( request , Misc.LoginScreenPageNameCookiVarName) ; 
	String loginScreenPage = (cookie!= null) ? cookie.getValue() : "loginScreen.jsp" ; // session.getAttribute("loginScreenPage") ;
	response.sendRedirect(loginScreenPage + "?comeFrom=index.jsp?reportTopOpen="+ reportTopOpen);
}
String userGroup = loggedUser.getUsrGrpIdValue(); 
com.implex.database.map.MasCompanyData userCompany = loggedUser.getUserCompany(); 
  
%>

<title><%=(userCompany!=null)? userCompany.getCmpDescValue(): "Unknown Company" %> - Smart Value</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%@ include file="includes/jquery_commons.js"%>
</head>

 
  <frameset id='frameset1' rows="8%,92%" >
	  <frame src="Top_Frame.jsp" name="topFrame" scrolling="Yes"> 
	<frameset name="downFrame" cols="25%,75%" >
		<frame src="queryTree.jsp?nodeToBeToggled=562" 	name="leftFrame" scrolling="Yes">
		<% 
			boolean smartToolAdmin = loggedUser.isSmartToolAdmin() ; 
			Support.LookupTreeV10 mainQueriesTree = (Support.LookupTreeV10) session.getAttribute("queriesTree");
			boolean treeAlreadyIntialized = mainQueriesTree!= null && mainQueriesTree.getNodesStatusAsBitSet()!= null ; 
		   if ( treeAlreadyIntialized) // If System Tree is already Initialized and ready for use.
		   {
			   
				if ( smartToolAdmin ) 
				{ 
				%> <frame src="editAndExecute.jsp?id=<%=reportTopOpen%>&branch_id=<%=userGroup%>" name="mainFrame" scrolling="auto"> 	<% 	
				} 
			   else 
			    {		   
			    	%> <frame src="executeQuery.jsp?id=<%=reportTopOpen%>&branch_id=<%=userGroup%>" name="mainFrame" scrolling="auto"> <%
				} 
		   }
		   else 
		   {
			   %> Welcome To Enterprise Perormance System (EPM) <%
		   }
		%>
		
	</frameset>
	
  </frameset> 

<noframes>
<body>

</body>
</noframes>
</html>
