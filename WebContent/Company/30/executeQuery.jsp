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

<body style="
    margin-top: 0px;
    margin-left: 0px;
    margin-bottom: 0px;
    margin-right: 0px;
">


<div id = "paramAndResultFrame">
	<div id = "paramsFrame" >
		<iframe width ='100%'  src="queryParams.jsp?<%=request.getQueryString()%>"></iframe>
	</div>
	
	<div id = "queryResultDiv" >
		<iframe id="queryResultFrame" width ='100%' height = '400'  src="queryResult.jsp?<%=request.getQueryString()%>"></iframe>
	</div>
</div>
</body>
</html>
