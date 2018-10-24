<%@ page import="Support.*, java.util.*, java.io.* , java.sql.* "%>

<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>


<%@page import="java.net.URLDecoder"%>
<%@page import="java.io.InputStreamReader"%><HTML lang="ar">
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<script type="text/javascript" src="includes/AJAX_new.js"></script>
<TITLE>List Of Referenced Queries in a master Query</TITLE>
</head>
<BODY BACKGROUND="Nccibgd2.gif" TEXT="blue" LINK="red">

<%
	request.setCharacterEncoding("UTF-8");
	java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
  if (con == null)
  {response.sendRedirect("loginScreen.jsp?comeFrom=editQuery.jsp");}
  boolean AutoLog  = ( request.getParameter("AutoLog") != null ) ; //----To Enable automatically read page contents 
  //-------------The follwoing Connection Used to get The Queries Where it may reside in a defrent DB
  java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");
  if( ( repCon == null || repCon.isClosed()) && AutoLog )
  { 
    XMLConfigFileReader aa = new XMLConfigFileReader( false);
    repCon = aa.reposatoryConn.generateConnection();
    session.setAttribute("repCon",repCon);
  }
  
  String id  = request.getParameter("queryId");	
  //------------If delete button Pressed---
%>
<% 
	SqlReader  sqlReader = new SqlReader(repCon,"LU_QUERIES" , "QUERY_BODY",  id, session , request , true);
	Hashtable<String, ArrayList<String>> refrencedQuires = sqlReader.getRefrencedQueryIds() ;
	  Iterator it = refrencedQuires.keySet().iterator() ;
	  out.println("List Of Quieries Refreanced By Query Id = " + id + "<br>") ;
	  while (it.hasNext())
	  {
	  	String key = (String) it.next();
	  	out.println ("<br><a target = 'newWindow' href = 'editQuery.jsp?id="+key +"'>Details</a>");  
	  } 
  	out.println(refrencedQuires); 
%> 

</BODY>
</HTML>
