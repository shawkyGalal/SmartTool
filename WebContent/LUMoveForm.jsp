<%@ page errorPage="errorPage.jsp"%>
<%@ page import="Support.Misc" %>
<HTML>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<HEAD>
<META NAME="GENERATOR" CONTENT="Oracle JDeveloper">
<TITLE>Move Lookup Item</TITLE>
</HEAD>
<BODY>
<%! java.util.Vector[] data;
String tableName=" ";
%>
<%

out.println("<Script lang= javaScript>");
out.println("   function passBack( x,y,z,w )        ");
out.println(" {                                            ");
out.println("    window.opener.document.moveForm.newParentId.value=x;       ");
out.println("    window.opener.document.moveForm.desc.value = y;            ");
//out.println("    window.opener.document.moveForm.desc.value = z;            ");
out.println("    window.opener.document.moveForm.newHeaderId.value=w;       ");
out.println("    window.close();                                            ");
out.println(" }                                                             ");
out.println("</Script>");
%>


<%
if (request.getParameter("tableName")!=null)
{tableName=request.getParameter("tableName").toString();}
if (!tableName.toUpperCase().equals("LU_ROOT") )
 { %>
<%
  String excludedId= request.getParameter("excludedId");
   java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");//ConnectionFactory.createConnection(ComplaintSettings.dBHostName,ComplaintSettings.dBServiceName,ComplaintSettings.dBUsername,ComplaintSettings.dBPassword);
 
    java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
   
	Support.LookupTreeV10 xxx = new Support.LookupTreeV10();
  xxx.setDBConnection(repCon);
  xxx.initialize(application,session, request,response,out);
  xxx.setImagepath("images/");
  //  xxx.setTargetUrl("LUEditTabs.jsp");
  xxx.setTargetFrame("contentsFrame");
  //------enable Recycle Bin button---------
   
  xxx.setTreeData(tableName.toUpperCase(),false);
  xxx.setTargetUrl("javaScript:passBack");
  xxx.execludedIds.add(tableName+excludedId);
  //---------This Section to Display only Node for the logged User + Standard --------
  
  java.util.Vector nodesIncluded = new java.util.Vector();
  String loggedUer = Misc.getConnectionUserName(con).toUpperCase() ;
  String query = "select id from LU_QUERIES where code = '" + loggedUer +"'" ;
  java.sql.ResultSet rs = repCon.createStatement().executeQuery(query);
  rs.next();
  nodesIncluded.addElement("LU_QUERIES"+rs.getString("id"));
  nodesIncluded.addElement("LU_QUERIES305"); //---------Include The Standard Node
  xxx.includeOnlySubNodes(nodesIncluded); //nodesIncluded
  //---------This Section to Display only Node for the logged User + Standard --------
	
	xxx.render(tableName.toUpperCase()+"0");
%>
<% } else out.print("Please Select a Lookup Table"); %>

</BODY>
</HTML>

