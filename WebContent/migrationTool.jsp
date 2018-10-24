<%@ page errorPage="errorPage.jsp"
	contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=UTF-8">
<title>Table Migration Tool</title>
</head>
<body>
<h2 align="center">Migration Tool</h2>
<%
 String toEnvUserName = request.getParameter("toEnvUserName");
 String toEnvPassWord = request.getParameter("toEnvPassWord");
 String tableName = request.getParameter("tableName") ;
 String owner = request.getParameter("owner") ;
 String whereClause = request.getParameter("whereClause") ;
%>
<form name="form1" method="post" action="migrationTool.jsp">
<div align="center">

<table width="89%" border="1">
	<tr>
		<td>
		<p align="left">&nbsp;<em><strong>&nbsp;</strong></em><b>Table&nbsp;
		to be&nbsp; Migrated:</b> <input type="text" name="tableName"
			value='<%=tableName%>'> <b>Schema Name : </b> <input
			type="text" name="owner" value='<%=owner%>'></p>
		<p align="left"><b>Where Clause</b> <textarea name="whereClause"
			cols="100" rows="6"><%=whereClause%></textarea></p>
		</td>
	</tr>
	<tr>
		<td>
		<table width="50%" border="0" align="center">
			<tr>
				<td width=><strong>To Environment :</strong></td>
				<td><select name="envValue">
					<option value=1>--- Select Environment</option>
					<option value=1 selected>Intergation Testing Env.</option>
					<option value=2>Unit Testing</option>
				</select></td>
			</tr>
			<tr>
				<td><strong>User Name:</strong></td>
				<td><input type="text" name="toEnvUserName" value='bgadmin'></td>
			</tr>
			<tr>
				<td><strong>Password</strong></td>
				<td><input type="password" name="toEnvPassWord" value='bgadmin'></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<div align="center"><input type="submit" name="migrateTable"
			value="Start Table Migration " size="30">&nbsp;</div>
		</td>
	</tr>
</table>
</div>
</form>
<p align="center">&nbsp;</p>
<p></p>
<p align="center">
<%
 java.sql.Connection  con  = null; 
//--------------- Migrate To Development -------------
if (request.getParameter("migrateTable")!= null  )
{
 java.sql.Connection toEnvCon = null;

 String toEnvServerIP = "";
 String toEnvServiceName = "";
 if (request.getParameter("envValue").equals("1"))
 {
 toEnvServerIP = "10.16.17.52";  toEnvServiceName = "SADAD";
 }
 if (request.getParameter("envValue").equals("2"))  //-----Testing Env selected 
 {
 toEnvServerIP = "10.16.17.51";  toEnvServiceName = "testsvr";
 }
 toEnvCon = Support.ConnectionFactory.createConnection_old(toEnvServerIP,toEnvServiceName,toEnvUserName,toEnvPassWord );
 java.util.Vector errors = null;
 try
 {
  Support.DataMigration.TableMigrator tm = null;
 
        //--------Connection to Production --------------
        con = (java.sql.Connection)session.getAttribute("con");
        if (con == null  )
        {response.sendRedirect("loginScreen.jsp?comeFrom=policyMigrate.jsp");}
        tm = new Support.DataMigration.TableMigrator(con,toEnvCon);
        java.util.Vector[] compResult = tm.compareTables( tableName , owner);
        if (compResult[0].size() > 0 )
        {
          %> <img border="0" src="images/alert_error.gif" width="34"
	height="34">&nbsp; Tables are not identical in both environments.
The following are the discrepancies, Please adjust before migration.</p>
<table border=1 align=center>
	<tr>
		<td colspan="3" align="center"><b>Active Environment (<%=tableName%>)</b></td>
		<td colspan="3" align="center"><b>To&nbsp; Environment (<%=tableName%>)</b></td>
	</tr>
	<tr>
		<td align="center"><b>Column Name</b></td>
		<td align="center"><b>Type</b></td>
		<td align="center"><b>Size</b></td>
		<td align="center"><b>Column Name</b></td>
		<td align="center"><b>Type</b></td>
		<td align="center"><b>Size</b></td>
	</tr>
	<%
          for (int i = 0; i<compResult[0].size() ; i++ )
          {
              %><tr>
		<td><%=compResult[0].elementAt(i)%></td>
		<td><%=compResult[1].elementAt(i)%></td>
		<td><%=compResult[2].elementAt(i)%></td>
		<td><%=compResult[3].elementAt(i)%></td>
		<td><%=compResult[4].elementAt(i)%></td>
		<td><%=compResult[5].elementAt(i)%></td>
	</tr>
	<%
          }
          %><table>
		<%
        }
        else  // tables are identicals in both environments---
        {
        int recordmigrated = tm.migrateTable(tableName,owner,whereClause);
        }
          
        //-------------Closing The Connection to developmrnt--- 
        toEnvCon.close();
 }
 catch (Exception e){toEnvCon.close(); throw e;} 
      
}

%>
	
</body>
</html>