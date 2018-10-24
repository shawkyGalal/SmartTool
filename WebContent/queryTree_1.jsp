<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>
<%
java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");
if (repCon == null || repCon.isClosed() )
{response.sendRedirect("loginScreen.jsp?comeFrom=queryTree.htm");}

Support.LookupTreeV10 queriesTree = (Support.LookupTreeV10)session.getAttribute("queriesTree");
boolean treeAlreadyIntialized = queriesTree != null && queriesTree.getNodesStatusAsBitSet() != null ; 

  if (  ! treeAlreadyIntialized ) return  ; 
  queriesTree.initialize(application,session,request,response,out);
  queriesTree.setShowMoveForm(false); 
  queriesTree.setShowAddLink(false); 
  queriesTree.setShowToolBar(false); 
  String startFromNodeId = "0" ; 
  String startFromNodeIdFromRequest = request.getParameter("startFromNodeId") ;
  startFromNodeId = (startFromNodeIdFromRequest != null )? startFromNodeIdFromRequest : startFromNodeId ; 
  queriesTree.openNode("LU_QUERIES" + startFromNodeId );
  queriesTree.setStartFromNodeId("LU_QUERIES"+startFromNodeId);   
  queriesTree.render(Support.LookupTreeV10.JQUERY_MENU );

%>
</body>
</html>