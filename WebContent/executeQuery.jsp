<%@ page errorPage="errorPage.jsp"%>
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
  String paramsCounter = null ; 
  java.sql.Statement stmt = con.createStatement();
	try 
	{ String queryForTitle = "select e_dsc"
    		+ " , ( select count(*) from TABLE (icdb.udv_services.get_Query_Params ( ( "
   			+"  select support.query_services.get_query_after_subs_refrence ( '"+id+"' )  from dual "
			    +" )) ) aa ) paramsCounter "
	+ " from support.lu_queries where id = "  + id  ; 
	//System.out.print(queryForTitle) ; 
	    java.sql.ResultSet rs = stmt.executeQuery(queryForTitle);
    	try {
        	while ( rs.next() ) {  title = rs.getString(1); paramsCounter = rs.getString(2) ;  	}
    	} 
    	finally { try { rs.close(); } catch (Exception ignore) { } }
	} 
	finally { try { stmt.close(); } catch (Exception ignore) { } }

  //------------End of getting Query Title------------
%>
<title><%=title%></title>
</head>
<%int paramFrameHight = 20 ; 
if (paramsCounter.equalsIgnoreCase("0"))  paramFrameHight = 10;  %>
<frameset rows="<%=paramFrameHight%>%,*" name = "paramAndResultFrame">
	<frame src="queryParams.jsp?<%=request.getQueryString()%>" 	name="paramsFrame" id = "paramsFrame" scrolling="Yes">
	<frame src="queryResult.jsp?<%=request.getQueryString()%>" 	name="queryResultFrame" id = "queryResultFrame" >
</frameset>
</html>
