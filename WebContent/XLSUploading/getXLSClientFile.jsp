<html>
<head>
<% String pageTitle = request.getParameter("pageTitle");  %>
<title><%=pageTitle %> - Get File from Client</title>
</head>

<body>

<h2 align="center"> <%=pageTitle %>-Reading XLS Sheet Data File</h2>
<% 
 String dirPathVarName = "dirPath" ;
 String dirPathValue = request.getParameter(dirPathVarName) ;
 session.setAttribute( dirPathVarName , dirPathValue ) ; 
 String uploadToTableOwner = request.getParameter("uploadToTableOwner") ;
 String uploadToTableName = request.getParameter("uploadToTableName") ;
 String keyCoulmnsCount = request.getParameter("keyCoulmnsCount"); 

 if (request.getParameter("uploadToTableOwner") != null ) { %>
		Excel Sheet Column Names should be : <a href = "../editAndExecute.jsp?id=8192&owner=<%=uploadToTableOwner%>&obj_name=<%=uploadToTableName%>" target = "Table Columns" >Show allowed Column Names</a>
<% } else { %>

<h1><em><font size="4" color="#FF0000">Notes :</font></em></h1>
<h1><em><strong><font color="#FF0000" size="4">1</font></strong><font

	color="#FF0000" size="4"><strong>- First Row of the XLS
File Should Contains Exact column Names of Table to which Data needs to
be Uploaded </strong></font></em></h1>
<p>Sample XLS files :</p>
<p>1 <a target="XLS Upload Samples" href=Samples/BranchCode.xls>Banks
Branch Code</a> 2 :</p>
<p><em><strong><font color="#FF0000" size="4">2-
File should Contains No Balank Rows , No Blanck Columns</font></strong></em></p>
<p><em><strong><font color="#FF0000" size="4">
3- Columns of SQL Type DATE should be in the form of 'dd-mm-yyyy' </font></strong></em></p>

<p><em><strong><font color="#FF0000" size="4">
4- File will be uploaded to :"<%= dirPathValue%>" </font></strong></em></p>

<p>&nbsp;</p>

<%} 
String tableOperation = request.getParameter("tableOperation"); 
%>
<form action="readXLSSheet.jsp" ENCTYPE="multipart/form-data"
	method=POST>

<h1><font size="4">1- Select File to Be Uploaded</font> <INPUT
	TYPE="FILE" NAME="File" SIZE="20" MAXLENGTH="80"></h1>

<h1><font size="4">2- Click To Read The File</font> <INPUT
	TYPE="SUBMIT" NAME="Submit" VALUE="Read File "></h1>
<% session.putValue("NoDBRecord" , "Y"); %>
<% session.setAttribute("uploadToTableName" , uploadToTableName ); %>
<% session.setAttribute("uploadToTableOwner" , uploadToTableOwner ); 
	session.setAttribute("tableOperation" , tableOperation );
	session.setAttribute("keyCoulmnsCount" , keyCoulmnsCount ); 

%>
</form>


<hr>
<FONT SIZE=-2> Copyright &copy; 1998,2012 SmartValue  . All rights reserved. <BR>
</body>
</html>

