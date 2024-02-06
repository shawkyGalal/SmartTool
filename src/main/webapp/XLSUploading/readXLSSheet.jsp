<%@ page errorPage="../errorPage.jsp"
	contentType="text/html;charset=UTF-8"
	import="java.util.* , java.sql.* , java.io.*"%>

<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>

<title>Excel Sheet Reading Form </title>
</head>
<p>

<%@ include file="saveXlsUploadedFile.jsp"%>
 
 <%  //----------------- Reading The XLS File  -----------
     File xlsFile = new File (session.getServletContext().getRealPath("")+"\\"+fileName);
     Support.XLSUploading.XLSUpload  xLSUpload = new Support.XLSUploading.XLSUpload(xlsFile , 0 );
     Vector[] xlsData = xLSUpload.getXLSData();
     session.setAttribute("xLSUpload", xLSUpload);
     
     java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
     if (con == null)
    {response.sendRedirect("../loginScreen.jsp?comeFrom=XLSUploading/readXLSSheet.jsp");}  

  %>

<p>&nbsp;</p>
<form name="form1" method="post" action="uploadXlsData.jsp">
Upload to <%=con.getMetaData().getURL()%>
<% String  uploadToTableName = (String) session.getAttribute("uploadToTableName") ; %>
<% String  uploadToTableOwner = (String) session.getAttribute("uploadToTableOwner") ;
String  tableOperation = (String) session.getAttribute("tableOperation") ;
boolean tableUpdates = (tableOperation!=null && tableOperation.equalsIgnoreCase("UPDATES")); 
boolean tableUploads = tableOperation == null || (tableOperation!=null && tableOperation.equalsIgnoreCase("UPLOADS"));
%>

<table>
	<tr> <td>Upload Given Data to Table Name</td>
		<td> <input type="text"  value = "<%=uploadToTableOwner+"."+uploadToTableName%>"	name="tableName"> </td>
			<td> 
				<a href = "../editAndExecute.jsp?id=8192&owner=<%=uploadToTableOwner%>&obj_name=<%=uploadToTableName%>" target = "Table Columns" >Show Table Columns</a>
			</td>
	</tr>	
	<tr><td>Use Date Pattern for Oracle DB </td> <<td> <input type="text" 	name="sqlDateFormate"> </td>
	</table>
	<% if (tableUploads) { %>
	<p><input type="submit" name="Upload" 	value="Upload تحميل"></p>
	<%} %>
	
	<% if (tableUpdates) {
	 String keyCoulmnsCount = (String)session.getAttribute("keyCoulmnsCount"); 
	keyCoulmnsCount = (keyCoulmnsCount == null)? "1" : keyCoulmnsCount ;
	 %>
	<input type="submit" name="Update" 	value="Update تعديل" title="Update Your Table with This Data provided that first column is your primary key"> 
	Update Your Table with This Data provided that first <input type="text" width="20" value= <%=keyCoulmnsCount %> name = "ukColumnCount" > Column(s) is your primary key </p>
	<%} %>
	
<table border=1>
	<%
  //----------Rendering Xls Data-----------------
  try
  {
    for (int rowCounter = 0 ; rowCounter<xlsData[0].size() ; rowCounter++)
    {
    %>
	<tr>
		<td><%=(rowCounter==0)? "SN" : String.valueOf(rowCounter) %></td>
		<%
        for  (int columnCounter = 0 ; columnCounter<xlsData.length ; columnCounter++)
      { String cellValue =  xlsData[columnCounter].elementAt(rowCounter).toString() ; 
        %>
		<td><%=(rowCounter==0)? "<Strong>":""%><%= cellValue %><%=(rowCounter==0)? "</Strong>":""%></td>
		<%
      }
      %>
	</tr>
	<%
    }
  }
  catch (Exception e) {throw new Support.MyException("Unable to Render Excel Sheet Data file "+xlsFile.toURL().toString()+ ", it may contain Graph.",e,1,session.getServletContext().getRealPath(""));  }
  
  %>
</table>
<p>&nbsp;</p>
<p>&nbsp;</p>
</form>

</html>
