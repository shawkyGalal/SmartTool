<%@ page errorPage="errorPage.jsp"%>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.implex.database.map.SecUsrDta "%>

<%@page import="Support.LookupTreeV10"%><html>
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
</head>
<title>Query List</title>
<body>

<p>
<% 
 java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");
  if (repCon == null || repCon.isClosed() )
 {response.sendRedirect("loginScreen.jsp?comeFrom=queryTree.htm");}
 java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
  if (con == null || con.isClosed() )
 {response.sendRedirect("loginScreen.jsp?comeFrom=queryTree.htm");}
 
  Support.LookupTreeV10 queriesTree = (Support.LookupTreeV10)session.getAttribute("queriesTree");
  Support.LookupTreeV10 executeTree = (Support.LookupTreeV10)session.getAttribute("executeTree");
  if (queriesTree== null || executeTree == null ) 
  {
	  Support.Misc.initializeMainSystemTrees(request, response, session, application , out) ;
	  queriesTree = (Support.LookupTreeV10)session.getAttribute("queriesTree");
	  executeTree = (Support.LookupTreeV10)session.getAttribute("executeTree");
  } 

  queriesTree.setShowToolBar(true) ; 
  
  boolean refreshAll = ( request.getParameter("refreshAll.x")!=null 
		  || request.getParameter("move")!=null 
		  || request.getParameter("copy")!= null );
  if ( refreshAll  )  //---------- Re generate it again and store it in the session
  {
	  java.util.BitSet nodeStatusBeforeRefresh= queriesTree.getNodesStatusAsBitSet() ; 
	  
	  //Support.Misc.initializeMainSystemTrees(request, response , session , application , out) ;
 	  queriesTree.setTreeData("LU_QUERIES",false);  //----------This Method internally check if the reload button pressed or not
	  queriesTree.setShowAddLink(true);
	  java.util.BitSet nodeStatusAfterRefresh= queriesTree.getNodesStatusAsBitSet() ; 
	  if ( nodeStatusBeforeRefresh.size() >= nodeStatusAfterRefresh.size() )
	  {
		  // Restore Before Refresh NodeStatus 
		  queriesTree.setNodesStatusAsBitSet(nodeStatusBeforeRefresh) ; 
	  }

  }

  queriesTree.initialize(application,session,request,response,out);
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
  int mainNodeID = 0 ; 
  queriesTree.render("LU_QUERIES"+mainNodeID , displayType);

 
 
%>
</p>
<% 
	SecUsrDta  loggedUser = (SecUsrDta)session.getAttribute("loggedUser") ;
	boolean smartToolAdmin = loggedUser.isSmartToolAdmin() ;
	if (smartToolAdmin ) 
	{
		%> <p align="center"><a href="executeTree.jsp?&refreshAll.x=Y">Show Executables</a></p> <%
	} 
%>
<small>
<a href = "/SmartTool/editAndExecute.jsp?id=72102" target = "help" >Why Node does not appear ?</a>
<br>Display List As :
<% if (displayType != (LookupTreeV10.DEFAULT_TREE)) { %><br><a href="?displayType=<%=LookupTreeV10.DEFAULT_TREE %>">Server Side Tree Default/Original </a> <% } %>
<% if (displayType != (LookupTreeV10.JQUERY_TREE)) { %><br><a href="?displayType=<%=LookupTreeV10.JQUERY_TREE %>">Jquery Tree</a> <% } %>
<% if (displayType != (LookupTreeV10.JQUERY_MENU)) { %><br><a href="?displayType=<%=LookupTreeV10.JQUERY_MENU %>">Jquery Menu</a> <% } %>
<% if (displayType != (LookupTreeV10.LSIT_ITEMS)) { %><br><a href="?displayType=<%=LookupTreeV10.LSIT_ITEMS %>">HTML List</a> <% } %>


<br> Rep Conn : <%=repCon.getMetaData().getURL()%>
</small>
</body>
</html>
