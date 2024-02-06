<%@ page errorPage="errorPage.jsp"
	import="java.util.*, java.io.* , java.sql.* , java.text.SimpleDateFormat"%>

<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>

<%@page import="java.text.DateFormat"%>

<html>
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">

<title>Table Editor</title>
</head>
<body>
<h2 align="center">Table Editor</h2>
<script language=javascript>
   function confirmDelete()
   {
     if (confirm("Are you sure you want to Commit Your Transaction(s)?")) 
        {return true;} 
	 else {return false;}
    }
  </script>

<%
  String updateButtonSuffix = "_XXXX";
  String dateFormate = "dd-mm-yyyy hh24:mi:ss";
  java.sql.Connection  con =null;
  boolean execAgainstRep = (request.getParameter("execAgainstRep") != null && request.getParameter("execAgainstRep").toString().equals("Y") );
  if (execAgainstRep)
  {
  con = (java.sql.Connection)session.getAttribute("repCon");
  }
  else
  {con = (java.sql.Connection)session.getAttribute("con");}
  if (con == null)
  {response.sendRedirect("loginScreen.jsp?comeFrom=tableEditor.jsp");}
  java.sql.Statement stmt = con.createStatement();  
  String owner = request.getParameter("owner");
  String tableName = request.getParameter("tableName");
  String uniqueColumnName = "rowId";
  
  if( con.getMetaData().getDatabaseProductName().equals("Microsoft SQL Server") )
  {
	  out.print(con.getMetaData().getDatabaseProductName());
	  uniqueColumnName = "ID";
  }
  String uniqueColumnValue = "'" + request.getParameter("ROWID") +"'";
  //-----------if Delete Pressed------------------------------
  if (request.getParameter("Delete.x") != null )
  {
    String deleteStmt = "delete from " + tableName + " where "+uniqueColumnName+" = " + uniqueColumnValue ;
    con.setAutoCommit(false);
    int x = stmt.executeUpdate(deleteStmt);
    out.print(x + "Record(s)  Deleted Successfully");
    return ;
  }
  //----------------------------------------------------------
  //-----------if RollBack Pressed------------------------------
  if (request.getParameter("RollBack.x") != null )
  {
    con.rollback();
    out.print("Transaction(s) Rolled Back");
  }
  //----------------------------------------------------------

  //-----------if Commit Pressed------------------------------
  if (request.getParameter("Commit.x") != null )
  {
    con.commit();
    out.print("Transaction(s) Commited");
  }
  //-----------if Ctreate Like Pressed------------------------------
  if (request.getParameter("createLike") != null )
  {
    throw new Exception ("This Utility not yet implemented");
	  /*String insertStmt = "insert into " +tableName  + "("  
                        +")"
                        + "Values ("
                        +")";
    */
  }

  //----------------------------------------------------------
  //--------------Check Update Buttons----------
  java.util.Enumeration reqParms = request.getParameterNames();
  String reqParmName = "";
  boolean updatePressed = false ;
  int columnNameLength= 0;
  String[] xx = new String[200] ; //request.getParameter("BRANCH_CODE_XXXX")  ;
  int i = 0;
  while (reqParms.hasMoreElements())
  {
    reqParmName = reqParms.nextElement().toString();
    xx[i] = reqParmName;
    i++;
    columnNameLength =reqParmName.indexOf(updateButtonSuffix) ;
    updatePressed = (columnNameLength != -1 )? true:false;
    if (updatePressed) 
    {break;}
  }
  if (updatePressed) 
  { 
  String columnName = reqParmName.substring(0,columnNameLength);
  String columnType = request.getParameter(columnName+"columnType");
  String newColumnValue = "";
  if ( columnType.equals("DATE"))
  {
    newColumnValue = "to_date('" + request.getParameter(columnName) + "' , '"+dateFormate+"')";
  }
  else { 
      newColumnValue = request.getParameter(columnName) ;
      newColumnValue = Support.Misc.replaceSingleQuteWithDouble(newColumnValue);
      newColumnValue = "'" + newColumnValue + "'" ;
      }

  String updateStmt = "Update "+ tableName + " set " +  columnName +" = " + newColumnValue + " Where " +uniqueColumnName +" = " + uniqueColumnValue ;
  con.setAutoCommit(false);
  int updateCount = 0;
  try{
  updateCount = stmt.executeUpdate(updateStmt);
  }
  catch (SQLException sqle){ throw new SQLException ("Unable To Execute : <br>" + updateStmt + "<br> Due To " +  sqle );}
  out.print("("+ updateCount + ") Records Updated Successfully");
  }
  
  //-------------------------------------------
  String queryStatment = "select * from " + tableName + "\n" + " Where " +uniqueColumnName +" = " + uniqueColumnValue ; 
  java.sql.ResultSet rs= null;
  try{
  rs =  stmt.executeQuery(queryStatment);
  }
  catch (Exception  e) {throw new Exception("Unable to execute : \n" + queryStatment + "\n Due to : \n" + e.getMessage());}
  rs.next();
  ResultSetMetaData rsmd = rs.getMetaData();
  int columnCount = rsmd.getColumnCount();
  String cloumnName ="";
  String columnValue = "";
  int coulmnDisplaySize = 4;
  String columnType = "";
  boolean isDate = false;

%>
<p align="center"><strong>Table Name is</strong> <%=tableName%> <strong>RowId
is</strong> <%=uniqueColumnName %></p>
<p align="center">&nbsp;</p>
<form name="form1" method="post" action=""><input type="hidden"
	name="execAgainstRep" value='<%=((execAgainstRep)? "Y" : "N" )%>'>
<table width="90%" border="1">
	<tr>
		<td colspan="3">
		<div align="center"><input title="Commit Transactions"
			type="image" src="images/save.gif" name="Commit" value="Commit"
			onClick="return confirm('Are you sure you want to Commit Your Transaction(s)?');">
		<input title="RollBack Transactions" type="image"
			src="images/rollback.gif" name="RollBack" value="RollBack"
			onClick="return confirm('Are you sure you want to RollBack Your Transaction(s)?');">
		<input title="Commit Transactions" type="image"
			src="images/deleterec.gif" name="Delete" value="Delete"
			onClick="return confirm('Are you sure you want to Delete This Record?');">
		... <input type="submit" name="createLike" value="Create Like">
		<%
          int dotIndex =  tableName.indexOf(".");
          if (dotIndex==-1) {throw new Exception ("Table Name in Your query does not include owner name..it should be in the form of 'OWNER.TABLE_NAME'. please specify");}
    
          %> <a
			href="TableInsertForm.jsp?owner=<%=tableName.substring(0, dotIndex)%>&tableName=<%=tableName.substring(dotIndex+1 )%>">create
		new </a></div>
		</td>
	</tr>
	<tr>
		<td width="4%">Update</td>
		<td width="40%">
		<div align="center"><strong>Coulmn Name</strong></div>
		</td>
		<td width="56%">
		<div align="center"><strong>Value </strong></div>
		</td>
	</tr>
	<%    for  (int count = 1 ; count<=columnCount ; count++ )
  { cloumnName = rsmd.getColumnName(count);
    columnType = rsmd.getColumnTypeName(count);
    coulmnDisplaySize = rsmd.getColumnDisplaySize(count);
    columnValue = "";
    isDate = (columnType.toUpperCase().equals("DATE"))? true : false;
    if (isDate)
    {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    	java.util.Date dt = rs.getDate(count) ; 
    	 
     columnValue = ( dt== null)? "": sdf.format(dt);
    }
    else if (columnType.equals("LONG")|| columnType.equals("CLOB") )
    {     //----Reading a Long Value from the Result Set
          java.io.InputStream is = rs.getAsciiStream(cloumnName);
          if (is != null)
          {
          java.io.InputStreamReader isr = new java.io.InputStreamReader(is);
          java.io.BufferedReader br  = new java.io.BufferedReader(isr);
          String line  ="";
          while ((line = br.readLine())!=null) 
          {columnValue +=line +"\n";}
          }
          coulmnDisplaySize = 301;  // -- any value more than 300 to render it  a text area 
    }
    else 
    {columnValue =  (rs.getString(count) == null)? "" :rs.getString(count);}

    
  %>
	<tr>
		<td><input type="submit"
			name="<%=cloumnName+updateButtonSuffix%>" value="Update"> <input
			type="hidden" name="<%=cloumnName+"columnType"%>"
			value="<%=columnType%>"></td>
		<td>
		<div align="left"><%=cloumnName%></div>
		</td>
		<td>
		<div align="left">
		<%
          if (isDate)
          {
            %><%= Support.Misc.renderhtmlTextFildForDate(cloumnName ,"form1", "value = '" + columnValue +"'" , dateFormate ) %>
		<%
          }
         else
         {
            %> <%=(coulmnDisplaySize > 300 )? "<textarea" : "<input " %>
		name = <%=cloumnName%> <%=(coulmnDisplaySize > 300 )? "cols='80' rows='10' >" + columnValue + "</textarea>"  
                                               : "type=Text " + "value =\""+ columnValue + "\" size="+(coulmnDisplaySize) + ">" %>
		<%                                             
         }    
       %>
		</div>
		</td>
	</tr>
	<%
   
  }
  %>
</table>
<p>&nbsp;</p>
<p align="center"><input title="Commit Transactions" type="image"
	src="images/save.gif" name="Commit" value="Commit"
	onClick="return confirm('Are you sure you want to Commit Your Transaction(s)?');">
. <input title="RollBack Transactions" type="image"
	src="images/rollback.gif" name="RollBack" value="RollBack"
	onClick="return confirm('Are you sure you want to RollBack Your Transaction(s)?');">
. <input title="Commit Transactions" type="image"
	src="images/deleterec.gif" name="Delete" value="Delete"
	onClick="return confirm('Are you sure you want to Delete This Record?');">
</p>
</form>
<p>&nbsp;</p>
</body>
</html>
