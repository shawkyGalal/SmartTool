<%@ page contentType="text/html;charset=UTF-8"
	errorPage="errorPage.jsp" import="Support.* "%>

<HTML lang="ar">

<TITLE>Edit Lookups</TITLE>

<BODY  TEXT="blue" LINK="red">

<%
  request.setCharacterEncoding("UTF-8");
  java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
  // -----------Checking Authority--------------------
  Support.LookupTreeV10 executeTree = null;
  executeTree = (Support.LookupTreeV10)session.getAttribute("executeTree");
  java.sql.Connection  repCon = executeTree.getConn();//.sql.Connection)session.getAttribute("repCon");
  //----------Creating a Security Manager for the Tree----
  Support.ExecTreeSecurity execSecurity = new Support.ExecTreeSecurity(executeTree, con);
  executeTree.setSecurityManager(execSecurity);
  String QueryTableName = request.getParameter("tableName");
  if (QueryTableName == null) // QueryTableName is sent from the tree
  {
	  QueryTableName= "lu_executables".toUpperCase() ;
  }
  executeTree.checkAuthority( QueryTableName,Integer.parseInt(request.getParameter("id")), Support.TreeSecurityManager.READ);  
  java.sql.Statement stm = repCon.createStatement();
  if ( request.getParameter("id")==  null ) // request.getParameter("tableName")==null ||
  {
    throw new Exception("Please select an Item from the Tree on the left pan");
  }
  //String tableName =  "V_USER_QUERIES"; //request.getParameter("tableName").toUpperCase();
  String id  = "'"+request.getParameter("id")+"'";	
  //------------If delete button Pressed---
  if(request.getParameter("deleteBtn")!= null&&request.getParameter("hdelete").equals("1"))
  {
    executeTree.deleteNode(QueryTableName,Integer.parseInt(request.getParameter("id")));
    %>
<div align="center">Deleted Successfully <SCRIPT> 
    	parent.parent.leftFrame.location.href="executeTree.jsp?tableName=<%=QueryTableName%>&refreshAll.x=refreshAll";
	  </SCRIPT> <script language=javascript>
    function myOnload()
		 {
			// This script function to override the one called in the HTML Body in case of Escalation Map table
		 }  
	  </script> <%
    out.println("</body>");	 return;
 }//End of  if delete button  pressed
  //--------------------------If Update Button Pressed ------------------
  else if (request.getParameter("updateBtn")!= null)
  {

	  boolean SmartToolAdmin = session.getValue("SmartToolAdmin") != null ;
	  if (! SmartToolAdmin)
	  {
	  	// throw new MyException("Smart Tool Amin Only can perform this Function <a href =checkMyPassword.jsp >Are You Admin?</a>", null , MyException.LOG_EXCEPTION_MESSAGE , session.getServletContext().getRealPath("")); 
	  }
	execSecurity.checkAuthority( QueryTableName,Integer.parseInt(request.getParameter("id")), Support.ExecTreeSecurity.UPDATE);  
    String E_DESC = "'"+request.getParameter("E_DSC")+"'";
    String A_DESC = "'"+request.getParameter("A_DSC")+"'";            
    String CODE   = "'"+request.getParameter("CODE")+"'";
    String PARENT_ID =  "'"+request.getParameter("PARENT_ID")+"'"; 
    String EXEC_BODY   = "'"+Support.Misc.replaceSingleQuteWithDouble(request.getParameter("EXEC_BODY"))+"'";
    String MSG   = "'"+Support.Misc.replaceSingleQuteWithDouble(request.getParameter("MSG"))+"'";
    String condition ="id = "+id;      
     
    String updateStmt = " UPDATE Support."+QueryTableName+ " SET CODE  = "+CODE+  " , "+
             "     E_DSC = "+E_DESC+" , "+ " A_DSC = "+A_DESC+   ", EXEC_BODY = " +EXEC_BODY + ", MSG = " +MSG + ", PARENT_ID = " + PARENT_ID 
             + " WHERE "+condition;
     boolean b =  stm.execute(updateStmt);
     b= stm.execute("commit");
    %> <SCRIPT> 
		 // parent.parent.leftFrame.location.href="executeTree.jsp?tableName=<%=QueryTableName%>&refreshAll.x=refreshAll";
     </SCRIPT> <%
  }//------------End Of Update Operation-----------
	%> <script language=javascript>
   function confirmDelete()
    {
      if (confirm("Are you sure you want to delete this Record and its details?")) 
        {document.form1.hdelete.value=1;} 
    }
  </script></div>
<form name="form1" method="post" action="editExecutable.jsp">
<div align="center">
<%
  if((id.equals("'0'")))
  {
   out.println("Please select an active item");
  }
  else
  {
   java.sql.ResultSet rs2 = stm.executeQuery("select E_Dsc from support."+ QueryTableName +" where id = 0");
   rs2.next();
   String tableDesc = rs2.getString("E_Dsc");  
  
   String selectClause = "select ID, CODE,E_DSC,A_DSC , Exec_body , msg , parent_id from support."+ QueryTableName +" where id ="+id;
   java.sql.ResultSet rs1 = stm.executeQuery(selectClause);
   rs1.next();
   String CODE , E_DSC , A_DSC , Exec_body , MSG , PARENT_ID = "";
   CODE = rs1.getString("CODE"); 
   E_DSC = rs1.getString("E_DSC"); 
   A_DSC = rs1.getString("A_DSC"); 
   Exec_body = rs1.getString("Exec_body");
   PARENT_ID = rs1.getString("PARENT_ID");
   MSG = rs1.getString("MSG");
   %>


<table width="92%" border="1">
	<tr>
		<td width="12%">ID = <%=rs1.getString("ID")%></td>
		<td width="88%">
		<div align="center"><input type="hidden" name="tableName"
			value='<%=QueryTableName%>'> <input type="hidden" name="id"
			value=<%=id%>> <input type="hidden" name='hdelete'>
		<!--
          <input type="Submit" name="updateBtn" value="Modify">
          <input type="submit" name="deleteBtn" value="Delete" onClick="return confirmDelete();" >
          --></div>
		</td>
	</tr>

	<tr>
		<td width="11%">
		<div align="right"><strong><font color="#FF0000"
			face="Times New Roman, Times, serif">Code </font></strong></div>
		</td>
		<td width="89%"><input name="CODE" type="text" size="10"
			maxlength="20" value='<%=CODE%>'> <strong><font
			color="#FF0000" face="Times New Roman, Times, serif">English
		Description <input name="E_DSC" type="text" size="30" maxlength="200"
			value='<%=E_DSC%>'> Arabic Description <input name="A_DSC"
			type="text" size="30" maxlength="200" value='<%=A_DSC%>'> </font></strong></td>
	</tr>
	<tr>
		<td>
		<p>Executable Satatments</p>
		</td>
		<td><textarea name="EXEC_BODY" cols="100" rows="20"><%=Exec_body%></textarea></td>
	</tr>
	<tr>
		<td>Message</td>
		<td><strong><font color="#FF0000"
			face="Times New Roman, Times, serif"> <input name="MSG"
			type="text" size="110" maxlength="200" value='<%=MSG%>'> </font></strong></td>
	</tr>
	<tr>
		<td>Parent Query ID</td>
		<td><input name="PARENT_ID" type="text" size="80" maxlength="200" value='<%=PARENT_ID%>' style="width: 318px">
		</td>
	</tr>
</table>
<div align="center">
<p>
<% 
    String remoteAdress = request.getRemoteAddr();
   // if (remoteAdress.equals("10.16.18.181"))  //----Allow only my self connected frmo my labtop to modify and delete
    {
    %> <input type="Submit" name="updateBtn" value="Modify"> <input
	type="submit" name="deleteBtn" value="Delete"
	onClick="return confirmDelete();"></p>
</div>
<%}%>
<p align="center"><a
	onClick="return confirm('<%=MSG%> Are You Sure ?');"
	href="executeExecutable.jsp?<%=request.getQueryString()%>">Execute</a><br>
<%
    } // end of if (id !=0)
 
  stm.close();
  %>
</p>
</div>
</form>
</BODY>
</HTML>
