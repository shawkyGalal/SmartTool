
<%@ page contentType="text/html;charset=UTF-8"
	errorPage="errorPage.jsp"%>
<HTML>
<HEAD>
<TITLE>New Lookup item</TITLE>
</HEAD>
<BODY TEXT="blue" LINK="red">
<%
  request.setCharacterEncoding("UTF-8");
  String tableName =request.getParameter("tableName").toUpperCase();
  String parentId= request.getParameter("parentId");
  String headerId= "0" ; //request.getParameter("headerId");

  java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
  java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");//ConnectionFactory.createConnection(ComplaintSettings.dBHostName,ComplaintSettings.dBServiceName,ComplaintSettings.dBUsername,ComplaintSettings.dBPassword);
  // -----------Checking Authority--------------------
  Support.LookupTreeV10 executeTree = null;
  executeTree = (Support.LookupTreeV10)session.getAttribute("executeTree");
  Support.ExecTreeSecurity execSecurity = new Support.ExecTreeSecurity(executeTree, con);
  execSecurity.checkAuthority( tableName,Integer.parseInt(request.getParameter("parentId")), Support.ExecTreeSecurity.INSERT);  
  // -----------Checking Authority--------------------
  Support.HTMLDbTransactionBeanWOPooling  x = new Support.HTMLDbTransactionBeanWOPooling();
  x.myInitialize(application,session,request,response,out,repCon);
  x.setImagePath("/Support/images/");
  x.setluPopupPath("");
  x.simulateLogin("0","user1","role1","0");
  //java.sql.Connection  con = null;  
    if (request.getParameter("tableName")==null || request.getParameter("parentId")==  null || request.getParameter("headerId")==null)
  {
    throw new Exception("Please Select an Item from the Tree to Add an Item Under It");
  }
  
  java.sql.Statement  stm= repCon.createStatement();
%>
Adding an item in
<%=tableName%>
Under parent
<%=parentId%>
and its master item Id is
<%=headerId%>
<form name="form1" method="post" action="executableInsert.jsp">
<div align="center">
<p><input type="hidden" name="tableName" value='<%=tableName%>'>
<input type="hidden" name="parentId" value='<%=parentId%>'> <input
	type="hidden" name="headerId" value='<%=headerId%>'></p>

<table width="96%" border="1">
	<tr>
		<td width="23%">
		<div align="right"><strong><font color="#FF0000"
			face="Times New Roman, Times, serif">Code </font></strong></div>
		</td>
		<td width="77%"><input name="CODE" type="text" size="60"
			maxlength="20" value=""></td>
	</tr>
	<tr>
		<td width="23%">
		<div align="right"><strong><font color="#FF0000"
			face="Times New Roman, Times, serif">English Description </font></strong></div>
		</td>
		<td width="77%"><input name="E_DSC" type="text" size="60"
			maxlength="200" value=""></td>
	</tr>
	<tr>
		<td>
		<div align="right"><strong><font color="#FF0000"
			face="Times New Roman, Times, serif">Arabic Description </font></strong></div>
		</td>
		<td><input name="A_DSC" type="text" size="60" maxlength="200"
			value=""></td>
	</tr>
	<tr>
		<td>
		<p>Executable Satatment</p>
		<p><font size="2"></font></p>
		</td>
		<td><textarea name="EXEC_BODY" cols="100" rows="15"></textarea></td>
	</tr>
</table>

<br>
<div align="center"><input type="Submit" name="insertBtn"
	value="Save"></div>

<%   
 if ((request.getParameter("insertBtn")!= null)&&!(request.getParameter("headerId").equals(null)))
{
     String E_DESC = "'"+ request.getParameter("E_DSC")+"'";
     String A_DESC = "'"+ request.getParameter("A_DSC")+"'";            
     String CODE   = "'"+ request.getParameter("CODE")+"'";
     String EXEC_BODY   = "'"+Support.Misc.replaceSingleQuteWithDouble(request.getParameter("EXEC_BODY"))+"'";     
     parentId= "'"+parentId+"'";
     headerId= "'"+headerId+"'";

  { String defaultInsertStrmt = "INSERT INTO support."+tableName+
                             " (CODE , E_DSC , A_DSC , PARENT_ID , HEADER_ID, EXEC_BODY) "+
                             " VALUES  ("+
                             CODE+" , "+E_DESC+" , "+A_DESC+" , "+parentId+" , "+headerId+", "+EXEC_BODY+")" ; 
    boolean b = stm.execute(defaultInsertStrmt);
    b= stm.execute("commit");
    }
    out.print("Record Inserted Successfully");
    %>
<div align="center"><SCRIPT>
     parent.leftFrame.location.href="executeTree.jsp?tableName=<%=tableName%>&refreshAll.x=refreshAll";
     </SCRIPT> <%
}//outer if insert button
%> <br>
</div>
</form>
</BODY>
</HTML>
