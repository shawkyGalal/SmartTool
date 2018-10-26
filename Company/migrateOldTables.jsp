<%@ page errorPage="../errorPage.jsp"%>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%@page import="java.sql.*" %>
<%request.setCharacterEncoding("UTF-8");%>

<%@page import="com.smartValue.carrent.*"%>
<%@page import="java.util.ArrayList"%><html>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<head>
	<meta http-equiv="Content-Type"  content="text/html; charset=UTF-8">
<title>Table Migration</title>
</head>
<body>
<%
String fromConName = request.getParameter("fromConName") ;
String toConName = request.getParameter("toConName") ;
String toConUserName = request.getParameter("toConUserName") ;
String toConUserPW = request.getParameter("toConUserPW") ;
String tableNamePattern = request.getParameter("tableNamePattern") ;

%>
This Process assumes that you have a copy of the DBF Files and an ODBC connection of type " Microsoft Foxpro VFP Driver (*.dbf) " is created pointing to these files then a jdbcodbc connection is defined in the SmartTool pointing to this odbc service name ;   
<br><p></p>

<form action="" method="POST">

Migrate from : <input title="JDBCODBC Connection points to a folder contains Foxpro DBF files " type="text" name = "fromConName" value="AmeeraOld" readonly="readonly">
Migrate to : <input type="text" name = "toConName" value="CAROLD" readonly="readonly">
<br> To User Name : <input type="text" name = "toConUserName" value="AmeeraOld" > 
To password <input type="password" name = "toConUserPW" value="123">
<br></br>
Table Name Pattern : <input type="text" name = "tableNamePattern" value="<%= (tableNamePattern == null) ? "%CARD%" :tableNamePattern %>" >
Regenerate Tables : <input type="checkbox" value="Y" checked="checked" readonly="readonly" disabled="disabled">
<br></br>
<input type="submit" name="Submit" value="Start Migration" onclick="return confirm (' This Will Delete Current Data. Are You sure? '); ">
</form>

<% 
if (request.getParameter("Submit") != null) 
{	
	
	java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con"); 
	if (con == null)
	  {
	   session.setAttribute("comeFrom","Carrent/migrateOldTables.jsp" + "?" + request.getQueryString());
	   response.sendRedirect("../loginScreen.jsp"  );
	  }//String tableName = request.getParameter("tableName"); 
 
     Support.XMLConfigFileReader supportConfig =  Misc.getXMLConfigFileReader(false) ; 
     Support.ConnParms fromConParam = supportConfig.getConnParmsByName(fromConName);
     java.sql.Connection fromCon = fromConParam.generateConnection("" , "" , "");
     
     Support.ConnParms toConParam = supportConfig.getConnParmsByName(toConName);
     java.sql.Connection toCon = toConParam.generateConnection(toConUserName , toConUserPW , "");

     
     FoxproMigrator migrator = new FoxproMigrator(fromCon , toCon ) ; 
	 ResultSet rs = fromCon.getMetaData().getTables(null, null, tableNamePattern , null);
	 int tablesCounter = 0 ; 
	 while(rs.next())
	 {
		 	tablesCounter++; 
			String tableNamex = rs.getString("TABLE_NAME") ;
			if ( tableNamex.equalsIgnoreCase("carderor")) continue ; // Ignore Error Log Table  
			System.out.println("\nStart Processing Table No. " + tablesCounter + "-" + tableNamex ) ;
			int processedRecordCount =  migrator.migrateTable( tableNamex , true ) ;
			ArrayList<Exception> errors = migrator.getMigrateErrors() ;
			%>
			<br> <p align="center" ><strong><%=tablesCounter%> :  Table <%=tableNamex %> Migration Result </strong>  </p>
			<br> No Of Processed Records  =  <%=processedRecordCount %> 
			<br> No Of Succeeded Records = <%=processedRecordCount - errors.size()%>
			<%

			if ( errors.size() > 0 ) 
			{ 
				out.println("<br> Errors : " ) ;
				for (Exception err : errors )
				{
				 out.println("<br>" + err);	 
				}
			}
			System.out.println("\nEnd Processing Table No. " +tablesCounter + "-"  + tableNamex  + "("+processedRecordCount+")") ;
	}
	 rs.close();
}	 
%>