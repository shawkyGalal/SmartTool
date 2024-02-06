<%@ page errorPage="errorPage.jsp"%>
<%@page  language="java" contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>


<%@page import="java.net.URLDecoder"%>
<%@page import="java.io.InputStreamReader"%>



<head>
<% String appURL = Support.Misc.getAppURL(request) ;  %>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=appURL%>/includes/AJAX_new.js"></script>
<script type="text/javascript" src="<%=appURL%>/includes/resizable-tables.js"></script>
<script type="text/javascript" src="<%=appURL%>/jQueryAssets/jquery-1.8.3.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=appURL%>/jQueryAssets/jquery-1.10.2.js"></script>
<script type="text/javascript" src="<%=appURL%>/jQueryAssets/jquery-ui.js"></script>
<script type="text/javascript" src="<%=appURL%>/jQueryAssets/jquery-ui-1.9.2.tabs.custom.min.js" type="text/javascript"></script>

<link href="<%=appURL%>/jQueryAssets/jquery.ui.core.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=appURL%>/jQueryAssets/jquery-ui.css">
<link href="<%=appURL%>/jQueryAssets/jquery.ui.theme.min.css" rel="stylesheet" type="text/css">
<link href="<%=appURL%>/jQueryAssets/jquery.ui.tabs.min.css" rel="stylesheet" type="text/css">

<TITLE>Edit Lookups</TITLE>
</head>
<HTML>
<BODY >
<%
	request.setCharacterEncoding("UTF-8");
	java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
  // -----------Checking Authority--------------------
  Support.LookupTreeV10 queriesTree = null;
  queriesTree = (Support.LookupTreeV10)session.getAttribute("queriesTree");
  java.sql.Connection  repCon = queriesTree.getConn();//.sql.Connection)session.getAttribute("repCon");
  //----------Creating a Security Manager for the Tree----
  Support.QueryTreeSecurity querySecurity = new Support.QueryTreeSecurity(queriesTree, con);
  queriesTree.setSecurityManager(querySecurity);
  String QueryTableName = request.getParameter("tableName");
  if (QueryTableName != null) // QueryTableName is sent from the tree
  {
    session.setAttribute("QueryTableName",QueryTableName);
  }
  else  //---QueryTableName is not sent
  {
   QueryTableName = "LU_QUERIES"; //session.getAttribute("QueryTableName").toString();
  }
  queriesTree.checkAuthority( QueryTableName,Integer.parseInt(request.getParameter("id")), Support.TreeSecurityManager.READ);  
  java.sql.Statement stm = repCon.createStatement();
  if ( request.getParameter("id")==  null ) // request.getParameter("tableName")==null ||
  {
    throw new Exception("Please select an Item from the Tree on the left pan");
  }
  //String tableName =  "V_USER_QUERIES"; //request.getParameter("tableName").toUpperCase();
  String id  = "'"+request.getParameter("id")+"'";
  String idWithoutQuote = request.getParameter("id") ; 
  //------------If delete button Pressed---
  if(request.getParameter("deleteBtn")!= null&&request.getParameter("hdelete").equals("1"))
  {
    queriesTree.deleteNode(QueryTableName,Integer.parseInt(request.getParameter("id")));
    %>

<div align="center">Deleted Successfully <SCRIPT> 
    	parent.parent.leftFrame.location.href="queryTree.jsp?tableName=<%=QueryTableName%>&refreshAll.x=refreshAll";
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
    querySecurity.checkAuthority( QueryTableName,Integer.parseInt(request.getParameter("id")), Support.QueryTreeSecurity.UPDATE);  
    String E_DESC = "'"+request.getParameter("E_DSC")+"'";
    String A_DESC = "'"+request.getParameter("A_DSC")+"'";            
    String CODE   = "'"+request.getParameter("CODE")+"'";
    String QUERY_BODY   = request.getParameter("QUERY_BODY");
    String HYPERLINK_TITLE =  "'"+request.getParameter("HYPERLINK_TITLE")+"'"; 
    String EMAIL_NOTIFY_TO =  "'"+request.getParameter("EMAIL_NOTIFY_TO")+"'"; 
    String EMAIL_NOTIFY_CC =  "'"+request.getParameter("EMAIL_NOTIFY_CC")+"'";
    String Target_URL =  "'"+request.getParameter("Target_URL")+"'";
    
    String PARENT_ID =  "'"+request.getParameter("PARENT_ID")+"'"; 
    String Active =  "'"+( (request.getParameter("Active")==null)? "F":"T") +"'"; 
    String logExecution =  "'"+( (request.getParameter("logExecution")==null)? "N":"Y") +"'";
    String condition ="id = "+id;      
       
    String updateStmt = " UPDATE Support."+QueryTableName+ " SET CODE  = "+CODE+  " , "+
             "     E_DSC = "+E_DESC+" , "+ " A_DSC = "+A_DESC+   ", HYPERLINK_TITLE = "+ HYPERLINK_TITLE +", EMAIL_NOTIFY_TO = "+EMAIL_NOTIFY_TO+",EMAIL_NOTIFY_CC = "+EMAIL_NOTIFY_CC+", QUERY_BODY = ? , Active = "+Active +", log_execution= " + logExecution + ""   
             + ", PARENT_ID = "+PARENT_ID+", Target_URL = "+Target_URL+"  WHERE "+condition;
    oracle.jdbc.OraclePreparedStatement pstmt = (oracle.jdbc.OraclePreparedStatement)repCon.prepareStatement(updateStmt);
     
    try 
    {
    //request.setCharacterEncoding("UTF-8");	
    oracle.sql.CLOB  myClob = oracle.sql.CLOB.createTemporary(repCon,true,oracle.sql.CLOB.DURATION_SESSION);
    java.io.Writer writer = myClob.getCharacterOutputStream();
    //java.io.Writer writer = myClob.();   
    
    int size = QUERY_BODY.length();
    writer.write(QUERY_BODY,0,size);
    writer.flush();
    writer.close();
    pstmt.setCLOB(1,myClob);
    repCon.setAutoCommit(true);  
    pstmt.execute();
    repCon.setAutoCommit(false); 
    pstmt.close();
    } 
    catch (Exception e)
    { 
    	repCon.setAutoCommit(false); 
    	pstmt.close(); throw e;
    }
  
    %> <SCRIPT> 
   		// alert ( window.parent.frames[1].frames[1].name ) ;
        window.parent.frames[1].frames[1].history.go(0);// To refresh The Execute Frame after Updating the Query.
      	//parent.parent.leftFrame.location.href="queryTree.jsp?tableName=<%=QueryTableName%>&refreshAll.x=refreshAll";
     </SCRIPT> <%
  }//------------End Of Update Operation-----------
	%> <script language=javascript>
   function confirmDelete()
    {
      if (confirm("Are you sure you want to delete this Record and its details?")) 
        {document.form1.hdelete.value=1;} 
    }
  </script></div>
<form name="form1" method="post" action="editQuery.jsp">

<%
  if(!(id.equals("'0'")))
  {
   java.sql.ResultSet rs2 = stm.executeQuery("select E_Dsc from support."+ QueryTableName +" where id = 0");
   rs2.next();
   String tableDesc = rs2.getString("E_Dsc");  
  
   String selectClause = "select ID, CODE,E_DSC,A_DSC , Query_body , HYPERLINK_TITLE , EMAIL_NOTIFY_TO , EMAIL_NOTIFY_CC , Active, log_execution , PARENT_ID , Target_URL from Support."+ QueryTableName +" where id ="+id;
   java.sql.ResultSet rs1 = stm.executeQuery(selectClause);
   rs1.next();
   %>

<a target = "mainFrame" href = "editAndExecute.jsp?id=13654&lookupTableName=LU_QUERIES&query_id=<%=request.getParameter("id")%>">New SmartTool Vesion </a>
<table width="92%" border="1">
	<tr>
		<td width="12%">ID = <%=rs1.getString("ID")%></td>
		<td width="88%">
		<div align="center"><input type="hidden" name="tableName" value="<%=QueryTableName%>"> 
		<input type="hidden" name="id" value=<%=id%>> <input type="hidden" name="hdelete">
		<input type="Submit" name="updateBtn" value="Modify"> <input type="submit" name="deleteBtn" value="Delete" onClick="return confirmDelete();"></div>
		</td>
	</tr>
	<tr>
		<td width="11%">
		<div align="right"><strong><font color="#FF0000" face="Times New Roman, Times, serif">Code </font></strong></div>
		</td>
		<td width="89%"><input name="CODE" type="text" size="10" maxlength="20" value='<%=rs1.getString("CODE")%>'> <strong><font color="#FF0000" face="Times New Roman, Times, serif">English
		Description <input name="E_DSC" type="text" size="30" maxlength="200" value='<%=rs1.getString("E_DSC")%>'> Arabic Description <input name="A_DSC" type="text" size="30" maxlength="200" value='<%=rs1.getString("A_DSC")%>'> </font></strong></td>
	</tr>
	<tr>
		<td>
		<p>&nbsp;</p>
		<p>Query Satatments</p>
		<p><font size="2">1- (include rowId to enable editing)</font></p>
		<p><font size="2">2- You can refere to another query using @@1234[2] to include query id=1234 and index =2 </font></p>
		<p><font size="2">3-Template for a Details Link....<p></p> &lt;a href = "editAndExecute.jsp?id=????" &gt;Details&lt;/a&gt;'</font></p>
        <p><font size="2">4-Replace Java Variables: $$#HostIp = Host IP<p></p> </font></p>
		</td>
		<% //---------------Reading Long Data Type (Query_body) from Database 
          String QUERY_BODY="";
          try{
           //oracle.sql.CLOB qb= (oracle.sql.CLOB)rs1.getClob("Query_body");
          
          //java.io.Reader char_stream = qb.getCharacterStream();
          //char [] char_array = new char [1000000];
          //int chars_read = char_stream.read (char_array, 0, 1000000);


          //QUERY_BODY = new String(char_array);
          throw new Exception ("");
          }
          catch (Exception e)
          {
        	  java.io.Reader  isr = rs1.getCharacterStream("Query_body");
          
          QUERY_BODY =  "";
          if (isr != null)
          { //java.io.InputStreamReader isr = new java.io.InputStreamReader(is);
            java.io.BufferedReader br  = new java.io.BufferedReader(isr);
            String line  ="";
            while ((line = br.readLine())!=null) {QUERY_BODY +=line +"\n";}
          }
          //out.print("From DB" + QUERY_BODY);      
          }
        %>
		<td><textarea name="QUERY_BODY" cols="100" rows="20" wrap="OFF"><%=QUERY_BODY%></textarea>
			<a onmouseover = "setTimeout( function () { loadDetails('refrencedQueryList.jsp?queryId=<%=request.getParameter("id")%>', 'sysMessage') }, 2000) ; " href = "javaScript:loadDetails('refrencedQueryList.jsp?queryId=<%=request.getParameter("id")%>', 'sysMessage')" >Display Referenced Queries </a>
		<script>
		  $(function() {
		    $( "#sysMessage" ).dialog(
		    { position: { my: "center top", at: "center top", of: window  } },
		    {
		      autoOpen: false,
		      modal : true , 
		      width : 800 ,
		      show: { effect: "explode", duration: 500  },
		      hide: { effect: "explode", duration: 500  },
		      buttons: {
        	  Cancel: function() 
        	  		{
          			 $( this ).dialog( "close" );}
          			}, 
		      close : function () 
		      		{
		      			history.go(0);  
		      			$( this ).dialog( "close" );
		      		}
		      
		    });
		    
		  });
		</script>
		<div id="sysMessage" title="System Message" dir="rtl"></div>
		
		</td>
	</tr><tr>
		<td>Status</td>
		<td><input name="Active" type="checkbox" value="T" <%= ((rs1.getString("Active").equals("T"))? "checked" :"" )  %>>
		Active ............................<input name="logExecution" type="checkbox" value="T" <%= ((rs1.getString("log_execution").equals("Y"))? "checked" :"" )  %>>
		Log Execution</td>
	</tr>
	<tr>
		<td>To enable AJAX</td>
		<td>
		<p>1- use in your query a column like the below:</p>
		<p>'&lt;a href =
		&quot;javaScript:loadDetails(''executeQuerySimple.jsp?id=16578&amp;DeptNo='||DEPTNO||'
		'', ''DeptNo'||DeptNo||''') &quot;&gt;Emps&lt;/a&gt; ' <br>
		||'&lt;div id=&quot;DeptNo'||DeptNO||'&quot;&gt;&lt;/div&gt;' Emps<br>
		
		<br>2-To Open The Link in an Ajax Window use  : javaScript:sendAjaxRequestToJsp( &lt;URL&gt; , 'sysMessage')<br>
		</p>
		<p><font size="2">2-To Call an Executable Service - Example :  &lt;a target = "extend_contract" href = "javaScript:loadDetails(''executableParams.jsp?id=24138&branch_id='||cc.branch_id||'&serialno='||cc.serialno||'&lookupTableName=LU_EXECUTABLES'', ''sysMessage'') " &gt; تمديد:&lt/a&gt;' تمديد
		</p>
		</td>
	</tr>
	<tr>
		<td>Tree Hyper Link Title</td>
		<td>  <textarea name="HYPERLINK_TITLE" style="height: 61px; width: 378px"><%=rs1.getString("HYPERLINK_TITLE")%></textarea></td>
	</tr>
	<tr>
		<td>Email Notify To</td>
		<td><input name="EMAIL_NOTIFY_TO" type="text" size="80" maxlength="400" value='<%=rs1.getString("EMAIL_NOTIFY_TO")%>'>
		use coma seperator</td>
	</tr>
	<tr>
		<td>Email Notify CC</td>
		<td><input name="EMAIL_NOTIFY_CC" type="text" size="80" maxlength="400" value='<%=rs1.getString("EMAIL_NOTIFY_CC")%>'>
		use coma seperator</td>
	</tr>
	
	<tr>
		<td>Parent Query ID</td>
		<td><input name="PARENT_ID" type="text" size="80" maxlength="200" value='<%=rs1.getString("PARENT_ID")%>' style="width: 318px">
		</td>
	</tr>

	<tr>
		<td>Target_URL</td>
		<td>
			<%String Target_URL = rs1.getString("Target_URL") ; %>
			<!-- <input type="text" name="Target_URL" value="<%=Target_URL %>"  style="width: 758px"> -->
			<textarea name="Target_URL" rows="" cols=""><%=Target_URL %></textarea>
			<!-- 
			<select name="Target_URL"  >
				<option value="executeQuery.jsp"  <%=(Target_URL.equalsIgnoreCase("executeQuery.jsp"))? "selected ='selected'" : ""  %> title="Simply Execute Your Query">executeQuery.jsp</option>
				<option value="editAndExecute.jsp"  <%=(Target_URL.equalsIgnoreCase("editAndExecute.jsp"))? "selected ='selected'" : ""  %> title="Edit and Execute Query">editAndExecute.jsp</option>
				<option value="queryResultWithEditableRS.jsp" <%=(Target_URL.equalsIgnoreCase("queryResultWithEditableRS.jsp"))? "selected ='selected'" : ""  %> title="Execute Query With Edit Option for the Result Set if rowid is included">queryResultWithEditableRS.jsp</option> 
				<option value="queryResultTabbed.jsp" <%=(Target_URL.equalsIgnoreCase("queryResultTabbed.jsp.jsp"))? "selected ='selected'" : ""  %> title="Execute Query With Edit Option for the Result Set if rowid is included">queryResultTabbed.jsp</option>
			</select>
		 	-->
		</td>
	</tr>
	
</table>
<div align="center">
<p><input type="Submit" name="updateBtn" value="Modify"> <input type="submit" name="deleteBtn" value="Delete" onClick="return confirmDelete();"></p>
</div>
<p>
<%
  } // end of if (id !=0)
    else
  {
   out.println("Please select an active item");
  }
  stm.close();
  
  %> <a target="Refrencing Queries" href='editAndExecute.jsp?id=18984&amp;querId=<%=request.getParameter("id")%>&amp;execAgainstRep=Y'>Other
Reports refering to this report</a> <br>
</p>
</form>
</BODY>
</HTML>
