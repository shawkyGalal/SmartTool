<%@ page errorPage="errorPage.jsp"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%@page import="com.implex.database.map.SecUsrDta"%>
<%request.setCharacterEncoding("UTF-8");%>
<html>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<head>
<%SecUsrDta loggedUser = (SecUsrDta) session.getAttribute("loggedUser") ; %>
<link rel="shortcut icon" href="/SmartTool/Company/<%=loggedUser.getUsrCmpIdValue()%>/images/logo.png">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@ include file = "jsScripts.html" %>
<% 
//boolean fileEditor =(request.getParameter("fileName")!=null);
boolean dbEditor =(request.getParameter("id")!=null || request.getParameter("code")!=null );
  String id  = request.getParameter("id");
  //-------------Getting the Query Title ----------------
  java.sql.Connection  con = (java.sql.Connection)session.getAttribute("repCon");

  String title = null;
  java.sql.Statement stmt = con.createStatement();
	try 
	{
	    java.sql.ResultSet rs = stmt.executeQuery( "select e_dsc from support.lu_queries where id = "  + id );
    	try {
        	while ( rs.next() ) {  title = rs.getString(1);	}
    	} 
    	finally { try { rs.close(); } catch (Exception ignore) { } }
	} 
	finally { try { stmt.close(); } catch (Exception ignore) { } }
  
  //------------End of getting Query Title------------
%>
<title><%=title%></title>
</head>

<% 
boolean smartToolAdmin =  loggedUser.isSmartToolAdmin () ;  
int topFrameHight = (smartToolAdmin) ? 1 : 1000 ; 
Support.LookupTreeV10 mainQueriesTree = (Support.LookupTreeV10) session.getAttribute("queriesTree");
mainQueriesTree.getSelectedIDs().clear() ;
mainQueriesTree.getSelectedIDs().put( request.getParameter("id"), null) ; 
%>
	<% if (false) //smartToolAdmin 
	{ 
	%> 	<div id = 'editQueryDivId'> 
			<jsp:include page="editQuery.jsp?<%=request.getQueryString()%>"></jsp:include>	
		</div>
	<%
	
	}
	%>
	
	<iframe name="mainFrame" scrolling="auto" src="executeQuery.jsp?<%=request.getQueryString()%>" 
		style="height: 600; " width = "100%" >
		
	</iframe>
                	
</html>
