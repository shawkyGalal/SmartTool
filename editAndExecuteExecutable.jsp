<%@ page errorPage="errorPage.jsp"%>
<%@page import="com.implex.database.map.SecUsrDta"%>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>
<html>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=UTF-8">

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
	    java.sql.ResultSet rs = stmt.executeQuery( "select e_dsc from support.lu_executables  where id = "  + id );
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
SecUsrDta loggedUser = (SecUsrDta) session.getAttribute("loggedUser") ;
boolean smartToolAdmin =  loggedUser.isSmartToolAdmin () ;  
int topFrameHight = (smartToolAdmin) ? 1 : 1000 ; 
%>
<frameset rows="<%=topFrameHight%>,*" name = "editAndExecuteFS">
<% if ( smartToolAdmin ) { %>
	<frame src="editExecutable.jsp?<%=request.getQueryString()%>" 	name="editorFrame" scrolling="Yes">
 <%} %>
	<frame src="executableParams.jsp?<%=request.getQueryString()%>&lookupColumnName=EXEC_BODY" 	name="paramsFrame" scrolling="Yes">

	 
 </frameset>
 
</html>
