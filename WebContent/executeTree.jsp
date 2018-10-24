<%@ page errorPage="errorPage.jsp"
	contentType="text/html;charset=UTF-8"%>

<%@page import="java.sql.Statement , Support.Misc"%>
<%@page import="Support.LookupTreeV10"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=UTF-8">
<title>Query List</title>
<body>

<p>
<% 
 java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");

 java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");

  Support.LookupTreeV10 executeTree = null;
  executeTree = (Support.LookupTreeV10)session.getAttribute("executeTree");
  boolean refreshAll = request.getParameter("refreshAll.x")!=null ;
  if ( refreshAll  )  //---------- Re generate it again and store it in the session
  {
    executeTree = new Support.LookupTreeV10("executeTree");
    executeTree.setImagepath("images/");
    executeTree.setTargetUrl("editExecutable.jsp");
    executeTree.setAddFormUrl("executableInsert.jsp");
    executeTree.setTargetFrame("mainFrame");
    executeTree.setAddFormTargetFram("mainFrame");
    executeTree.setShowAddLink(true);
    executeTree.setShowToolBar(true);
    executeTree.sethyperlinkLastTableItemsOnly(true);
    executeTree.showRecycleBinButton(false);
    executeTree.setShowCheckBox(false);
    executeTree.setShowMoveForm(true);
    session.setAttribute("executeTree", executeTree);

  //---------This Section to Display only Node for the logged User + Standard --------
    java.util.Vector nodesIncluded = new java.util.Vector();
    String loggedUer = Misc.getConnectionUserName(con) ;
    String query = "select id from support.lu_executables where code = '" + loggedUer +"'" ;
    Statement stmt =  repCon.createStatement(); 
    //java.sql.ResultSet rs = stmt.executeQuery(query);
    //rs.next();
    //nodesIncluded.addElement("LU_EXECUTABLES"+rs.getString("id"));
    //rs.close();
    //stmt.close();
    try 
	{
	    java.sql.ResultSet rs = stmt.executeQuery( "select id from support.lu_executables where code = '" + loggedUer +"'" );
    	try {
        	while ( rs.next() ) {  nodesIncluded.addElement("LU_EXECUTABLES"+rs.getString("id"));	}
    	} 
    	finally { try { rs.close(); } catch (Exception ignore) { } }
	} 
	finally { try { stmt.close(); } catch (Exception ignore) { } }
    
    //nodesIncluded.addElement("LU_EXECUTABLES1");//OS310);
    //nodesIncluded.addElement("LU_EXECUTABLES12956");  // SCOTT Node
    //nodesIncluded.addElement("LU_EXECUTABLES20889");  // ICDB Node
     
    nodesIncluded.addElement("LU_EXECUTABLES20893"); //---------Include The Standard Node
    executeTree.includeOnlySubNodes(nodesIncluded); //nodesIncluded
  //---------This Section to Display only Node for the logged User + Standard --------

  }      
  executeTree.initialize(application,session,request,response,out);
  executeTree.setDBConnection(repCon);
  
  executeTree.setTreeData("LU_EXECUTABLES",false);  //----------This Method internally check if the reload button pressed or not
  
  int displayType = LookupTreeV10.JQUERY_TREE ;
  String displayTypeParamName = "displayType" ;
  try {  
	  displayType = Integer.parseInt( request.getParameter(displayTypeParamName) );
 	  Cookie cookie = new Cookie (displayTypeParamName ,String.valueOf(displayType));
	  response.addCookie(cookie);
  } 
  catch(Exception e ) 
  {
	  try { displayType = Integer.parseInt( Support.Misc.getCookiByName(request , displayTypeParamName ).getValue()); }
	  catch ( Exception e1) { displayType = LookupTreeV10.JQUERY_TREE ; }
  }
  executeTree.render("LU_EXECUTABLES0" , displayType);
  
  //queriesTree.setTreeData("V_USER_QUERIES",false);  //----------This Method internally check if the reload button pressed or not
  //queriesTree.render("V_USER_QUERIES0");
%>
</p>
<p align="center"><a href="queryTree.jsp?refreshAll.x=Y">Show
Query Tree</a></p>

<small>
Display List As :
<br><a href="?displayType=<%=LookupTreeV10.DEFAULT_TREE %>">Server Side Tree Default/Original </a>
<br><a href="?displayType=<%=LookupTreeV10.JQUERY_TREE %>">Jquery Tree</a>
<br><a href="?displayType=<%=LookupTreeV10.JQUERY_MENU %>">Jquery Menu</a>
<br><a href="?displayType=<%=LookupTreeV10.LSIT_ITEMS %>">HTML List</a>


</body>
</html>
