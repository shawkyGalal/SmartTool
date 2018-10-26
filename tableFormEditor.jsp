<%@ page errorPage="errorPage.jsp" 	import="java.util.*, java.io.* , java.sql.* "%>
<%@page import="Support.MyException"%>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>

<html>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<head>
<meta http-equiv="Content-Type">
<title>Table Editor</title>
</head>

<body bgcolor="#FFEEFF">
<h2 align="center">Table Insert Form Editor</h2>
<%

boolean SmartToolAdmin = session.getValue("SmartToolAdmin") != null ;
if (! SmartToolAdmin)
{
	throw new MyException("Smart Tool Amin Only can perform this Function <a href =checkMyPassword.jsp >Are You Admin?</a>", null , MyException.LOG_EXCEPTION_MESSAGE , session.getServletContext().getRealPath("")); 
}

  java.sql.Connection  con =null;
  boolean execAgainstRep = (request.getParameter("execAgainstRep") != null && request.getParameter("execAgainstRep").toString().equals("Y") );
  if (execAgainstRep)
  {
  con = (java.sql.Connection)session.getAttribute("repCon");
  }
  else
  {con = (java.sql.Connection)session.getAttribute("con");}
  Connection repCon = (java.sql.Connection)session.getAttribute("repCon");
  if (con == null)
  {response.sendRedirect("loginScreen.jsp?comeFrom=tableEditor.jsp");}
  java.sql.Statement stmt = con.createStatement();  
  String owner = request.getParameter("owner");
  String tableName = request.getParameter("tableName");
  //-------------------------------------------
  String queryStatment = "select * from " +owner +"."+ tableName + "\n" + " where 1<>1" ;  // rowid = " + rowId;  to get metadata
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
<p align="center"><strong>Table Name is</strong> <%=owner%>.<%=tableName%>
</p>
<p align="left">
<a href="TableInsertForm.jsp?owner=<%=owner%>&tableName=<%=tableName%>">Display Insert Form</a></p>
<a href = "editAndExecute.jsp?id=25192&owner=<%=owner%>&table_name=<%=tableName %>" >Table Insert Form Designer By Smart Tool</a>
<form name="form1" method="post" action="tableFormEditor.jsp"><input
	type="hidden" name="execAgainstRep"
	value='<%=((execAgainstRep)? "Y" : "N" )%>'> <input
	type="hidden" name="owner" value='<%=owner%>'> <input
	type="hidden" name="tableName" value='<%=tableName%>'> <input
	type="hidden" name="<%=cloumnName+"columnType"%>"
	value="<%=columnType%>">
	
		<p align="center"><input type="submit" name="Submit" value="Submit"></p>
<table width="84%" border="1">
	<tr>
		<td colspan="9">
		<div align="center"></div>
		</td>
	</tr>
	<tr>
		<td width="6%">
		<div align="center"><strong>Column Name</strong></div>
		</td>
		<td width="6%">
		<div align="center"><strong>Include in the form</strong></div>
		</td>
		<td width="15%">
		<div align="center"><strong>Display Name(size)</strong></div>
		</td>
		<td width="15%">
		<div align="center"><strong>Tab Index</strong></div>
		</td>
		<td width="23%">
		<div align="center"><strong>Default value</strong></div>
		</td>

		<td width="12%">
		<div align="center"><strong>HTML Type</strong></div>
		</td>
		<td width="23%">
		<div align="center"><strong>Select Query List</strong></div>
		</td>
		<td width="23%">Attribute</td>
		<td width="23%">
		<div align="center"><strong>java script Validation</strong></div>
		</td>
		<td width="23%">
		<div align="center"><strong>Comments</strong></div>
		</td>
		
	</tr>

	<%    

// -- if submit is pressed
if (request.getParameter("Submit") != null)
{ 
	String insertStmt = "" ; 
	repCon.setAutoCommit(false);
	try
	{
	//-----Deleting Old Values -------------
	String deleteStmt = "delete from support.table_maint_details where upper(owner) = upper('"+owner+"') and upper(table_Name) = upper('"+tableName+"')";
	Statement delStmt = repCon.createStatement() ; 
	delStmt.execute(deleteStmt);
	delStmt.close();
	//-------Inserting New values ---------------
	Statement insrtStmt = repCon.createStatement() ; 
	for  (int count = 1 ; count<=columnCount ; count++ )
  	{
	   	cloumnName = rsmd.getColumnName(count);
	   	boolean isNullable = rsmd.isNullable(count)== ResultSetMetaData.columnNullable ; 
	   	String displayName = (request.getParameter("displayName_"+cloumnName) != null)? request.getParameter("displayName_"+cloumnName):"";
	   	String formIncluded = (request.getParameter("include_"+cloumnName ) != null )? "Y":"N" ;
	   	String tabIndex = (request.getParameter("tab_index_"+cloumnName )!= null )? request.getParameter("tab_index_"+cloumnName ):"";
	   	String html_type = request.getParameter("html_type_"+cloumnName );
	   	String sql = request.getParameter("sql_"+cloumnName );
	   	if (sql != null )sql = Support.Misc.replaceSingleQuteWithDouble(sql);
	   	String js = request.getParameter("js_"+cloumnName );
	   	if (js != null )js = Support.Misc.replaceSingleQuteWithDouble(js);
	   	String attribute = request.getParameter("attribute_"+cloumnName );
	   	if (attribute != null )attribute = Support.Misc.replaceSingleQuteWithDouble(attribute);
	   	
	   	String comments = request.getParameter("comments_"+cloumnName );
	   	if (comments != null )comments = Support.Misc.replaceSingleQuteWithDouble(comments);
	   	
	   	
	   	String defaultValue = request.getParameter("default_value_"+cloumnName );   
	   	defaultValue = (defaultValue == null || defaultValue.equals("") ) ? null : "'" + defaultValue +"'" ; 
	   	insertStmt = "insert into support.table_maint_details "
	           +"(column_included, select_list_query, java_script_validation, html_type, attribute,  tab_index, display_name, display_name_, column_name, table_name, owner , default_value , comments , JQUERY_VALIDATOR )"
	   			+" values ('"+formIncluded+"','"+sql+"','"+js+"' , '"+html_type+"' , '"+attribute+"' , '"+tabIndex+"' ,'"+displayName+"' , '"+displayName+"' , '"+cloumnName+"', '"+tableName.toUpperCase()+"' , '"+owner.toUpperCase()+"' , "+defaultValue+" , '"+ comments +"'," +((isNullable)? "''" :"'validate[required]'") +" )";
	   			//System.out.println(insertStmt);
	   	System.out.println( insertStmt ) ; 
	    insrtStmt.execute(insertStmt);
	}
    insrtStmt.close();
    out.println("Items Saved Successfully") ; 
  }
  catch (Exception e)
  {	
	repCon.rollback(); 
   	throw new Exception("Un able to execute :<br>" + insertStmt + "<br> Due To : " + e ) ;
  }

  repCon.commit();
}
//--
  Statement stmt1 ; 
  for  (int count = 1 ; count<=columnCount ; count++ )
  { 
	cloumnName = rsmd.getColumnName(count);
    columnType = rsmd.getColumnTypeName(count);
    int colummnsize = rsmd.getColumnDisplaySize(count);
    coulmnDisplaySize = rsmd.getColumnDisplaySize(count);
    isDate = (columnType.toUpperCase().equals("DATE"))? true : false;
	boolean isLOB = (columnType.equals("LONG")|| columnType.equals("CLOB") );

    //--get already stored data (if found )--
    
    String column_included = "";    String select_list_query = "";   String java_script_validation = "";
    String html_type = "";    String attribute = "";    String tabIndex = "";
    String display_name = ""; String default_value = "";  String comments = "" ; 
    
    String abc = "select * from support.table_maint_details where upper(table_name) = upper('"+tableName+"') and upper(owner) = upper('"+owner+"') and column_name = '"+cloumnName+"'";
    stmt1 = repCon.createStatement() ; 
    java.sql.ResultSet rs1 = stmt1.executeQuery(abc);
    while(rs1.next())
    {
	    column_included = rs1.getString("column_included");
	    select_list_query = rs1.getString("select_list_query");
	    java_script_validation = rs1.getString("java_script_validation");
	    html_type = rs1.getString("html_type");
	    attribute = rs1.getString("attribute");
	    tabIndex = rs1.getString("tab_Index");
	    display_name = rs1.getString("display_name");
	    default_value = rs1.getString("default_Value");
	    comments = rs1.getString("comments");
    }
    rs1.close();
    stmt1.close();
     //--
    %>
	<tr>
		<td>
		<div align="left"><%=cloumnName%></div>
		</td>
		<td>
		<div align="center">
		<p>&nbsp;</p>
		<p><input name="include_<%=cloumnName%>" type="checkbox" onClick="
			   if (this.checked ) 
			    {
				  document.form1.html_type_<%=cloumnName%>.disabled = false;
				  document.form1.tab_index_<%=cloumnName%>.disabled = false;
 	 			  if (document.form1.html_type_<%=cloumnName%>.value== 2 || document.form1.html_type_<%=cloumnName%>.value== 3)
			      	document.form1.sql_<%=cloumnName%>.disabled=false;
				  document.form1.js_<%=cloumnName%>.disabled=false
				  document.form1.displayName_<%=cloumnName%>.disabled = false;
				}
				else 
				{
				 document.form1.sql_<%=cloumnName%>.disabled=true;
    		     document.form1.tab_index_<%=cloumnName%>.disabled = true;
   			  	 document.form1.html_type_<%=cloumnName%>.disabled = true;
				 document.form1.js_<%=cloumnName%>.disabled=true;
				 document.form1.displayName_<%=cloumnName%>.disabled = true;
				}
				"
			value="Y" <%=(column_included.equals("Y"))? "checked":""%>>
		</p>
		<p>&nbsp;</p>
		</div>
		</td>
		<td><input type="text" name="displayName_<%=cloumnName%>"
			value="<%=(display_name == null)? cloumnName : display_name %>">(<%= colummnsize%>)</td>

		<td>
		<div align="center"><input name="tab_index_<%=cloumnName%>"
			type="text" size="3"
			value=<%=(tabIndex== null || tabIndex.equals(""))? String.valueOf(count) :tabIndex %>>
		</div>
		</td>
		<td>
			<div align="center"> <input name="default_value_<%=cloumnName%>"  
				type="text"	size = "6" 
				value="<%=( default_value == null ) ? "" : default_value %>"></div>
		</td>
		
		<td><select name="html_type_<%=cloumnName%>"
			onChange="if (this.value == 2 || this.value == 3 || this.value == 13)
	   { document.form1.sql_<%=cloumnName%>.disabled = false;}
	   else {document.form1.sql_<%=cloumnName%>.disabled = true;}
	   ">
			<option value="1" <%=(html_type.equals("1"))? "selected":"" %>>Text box</option>
			<option value="2" <%=(html_type.equals("2"))? "selected":"" %>>Drop Down List</option>
			<option value="3" <%=(html_type.equals("3"))? "selected":"" %>>Options</option>
			<option value="4" <%=(html_type.equals("4") || isDate )? "selected":"" %>>Date 	Calender</option>
			<option value="5" <%=(html_type.equals("5") || isLOB )? "selected":"" %>>TextArea</option>
			<option value="6" <%=(html_type.equals("6"))? "selected":"" %>>Hidden</option>
			<option value="9" <%=(html_type.equals("9"))? "selected":"" %>>Check Box</option>
			<option value="10" <%=(html_type.equals("10"))? "selected":"" %>>Label</option>
			<option value="11" <%=(html_type.equals("11"))? "selected":"" %>>Date & Time</option>
			<option value="12" <%=(html_type.equals("12"))? "selected":"" %>>Hijri Date </option>
			<option value="13" <%=(html_type.equals("13"))? "selected":"" %>>xxxxx</option>	
			<option value="14" <%=(html_type.equals("14"))? "selected":"" %>>Multi Select Tree</option>		
			<option value="15" <%=(html_type.equals("15"))? "selected":"" %>>Single Select Tree </option>			
		</select></td>
		<td>
		<div align="center"><textarea name="sql_<%=cloumnName%>"
			cols="30" rows="4" disabled
			onFocus=" if (this.value == 'Write Down Your SQL') {this.value = '';} "
			onBlur=" if (this.value == '') {this.value = 'Write Down Your SQL';} "><%=(select_list_query == null || select_list_query.equals("") )? "Write Down Your SQL" : select_list_query %></textarea>
		</div>
		</td>
		<td><textarea name="attribute_<%=cloumnName%>" cols="30" rows="4"><%=(attribute == null )? "" : attribute %></textarea></td>
		<td>
		<div align="center"><textarea name="js_<%=cloumnName%>"
			cols="30" rows="4"><%=(java_script_validation == null)? "" : java_script_validation %></textarea></div>
		</td>
		<td>
		<div align="center"><textarea name="comments_<%=cloumnName%>"
			cols="30" rows="4"><%=(comments == null)? "" : comments %></textarea></div>
		</td>
		
	</tr>
	<script language="JavaScript">
 if (document.form1.html_type_<%=cloumnName%>.value== 2 || document.form1.html_type_<%=cloumnName%>.value== 3)
   	document.form1.sql_<%=cloumnName%>.disabled=false;

</script>
	<%
   
  }
  %>
	<tr>
		<td colspan="9">
		<div align="center">Form Validation
		<p><textarea name="textarea"></textarea></p>
		<p><input type="submit" name="Submit" value="Submit"></p>
		<p align="left"><a
			href="TableInsertForm.jsp?owner=<%=owner%>&tableName=<%=tableName%>">Display
		Insert Form</a></p>
		</div>
		</td>
	</tr>
</table>
<p align="center">&nbsp;</p>
</form>
<p>&nbsp;</p>
<jsp:include page="/footer.jsp" />
</body>
</html>
