<%@ page errorPage="../errorPage.jsp"
	contentType="text/html;charset=UTF-8"
	import="java.util.* , Support.XLSUploading.*, java.sql.* , java.io.*"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=UTF-8">
<title>Upload XLS Sheet into DB</title>
</head>
<body>
<%
  java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
  if (con == null)
 {response.sendRedirect("../loginScreen.jsp?comeFrom=XLSUploading/uploadXlsData.jsp");}

  XLSUpload  xLSUpload = (XLSUpload)session.getAttribute("xLSUpload"); 
  String tableName = request.getParameter("tableName");
  String sqlDateFormate = request.getParameter("sqlDateFormate");

  if (sqlDateFormate !=null && !sqlDateFormate.equals("")) 
	  { xLSUpload.setSqlDateFormate(sqlDateFormate);}
  con.setAutoCommit(false);
  boolean uploadOperation = request.getParameter("Upload") != null ;  
  if (uploadOperation)
  	xLSUpload.uploadXlsData2DB(con, tableName);
  boolean updateOperation = request.getParameter("Update") != null ;
  if (updateOperation )
  {
	int ukColumnCount = Integer.parseInt( request.getParameter("ukColumnCount"));
	xLSUpload.setUkColumnCount(ukColumnCount); 
	xLSUpload.updateXlsData2DB(con, tableName);
  }
  Vector[] data = xLSUpload.getXLSData();
  Vector errors = xLSUpload.getErrors();
  Vector errorRowNumbers = xLSUpload.getErrorRowNumbers();
  
  %>
<font size="3"><strong>Successfully <%=(updateOperation)? "Updated" : "Loaded" %> records </strong></font>
<strong><%=(uploadOperation)? xLSUpload.data[0].size() -1 - errors.size() : xLSUpload.getUpdateCount()%></strong>
<%
  if (errors.size() > 0 )
  {
    %>

<p><br>
<font size="3"><strong>Upload Errors (<%=errors.size()%>
Error(s)): </strong></font>
<p><br>
<%
  }
  //for  (int i = 0 ; i< errors.size() ; i++)
  //{
  //  out.print(errors.elementAt(i).toString()+"<br><br>");
  //}
  %> <br><font color="#FF0000" size="3"><strong>Failed Records To Be Uploaded / Updated </strong></font>
<br>
<table border="1">
	<%
    try{
        //--------------Rendering Header (Column Names )----------
        %><tr>
		<td>SN</td>
		<%
        for (int L=0 ; L<data.length ; L++)
         {
           %><td><%=data[L].elementAt(0) %></td>
		<%
         }
           %><td>Error Desc</td>
	</tr>
	<%
        //----------End of Rendering Header (Column Names )----------
        
        for (int k = 0 ; k<errorRowNumbers.size(); k++)
        {
		  %><tr>
		<td><%=errorRowNumbers.elementAt(k).toString()%></td>
		<%
            for (int L=0 ; L<data.length ; L++)
            {
              %>
		<td><%=data[L].elementAt(Integer.parseInt(errorRowNumbers.elementAt(k).toString())) %></td>
		<%
            }
            %><td><%=errors.elementAt(k).toString()%></td>
	</tr>
	<%
        }
      }
      catch ( Exception e) { %>Unable to Render Error Records Due to the
	following Error<%=e.getMessage()%>
	<%}
  %>
</table>
</body>
</html>
