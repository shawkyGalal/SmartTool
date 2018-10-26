<html>
<head>
<title>Get File from Client</title>
</head>

<body>

<h1 align="center">Reading XLS Sheet Data File</h1>

<form action="../BillUploadGenerator.jsp" ENCTYPE="multipart/form-data"
	method=POST>
<h1><em><font size="4" color="#FF0000">Notes :</font></em></h1>
<h1><em><strong><font color="#FF0000" size="4">1</font></strong><font
	color="#FF0000" size="4"><strong>- First Row of the XLS
File Should Contains Exact column Names of Table to which Data needs to
be Uploaded </strong></font></em></h1>
<p><em><strong><font color="#FF0000" size="4">2-
File should Contains No Balank Rows , No Blanck Columns</font></strong></em></p>
<p><em><strong><font color="#FF0000" size="4">3-
Columns of SQL Type DATE should be in the form of 'dd-mm-yyyy' </font></strong></em></p>
<p>&nbsp;</p>
<h1><font size="4">1- Select File to Be Uploaded</font> <INPUT
	TYPE="FILE" NAME="File" SIZE="20" MAXLENGTH="80"></h1>
<h1><font size="4">2- Click To Convert File To XML</font> <INPUT
	TYPE="SUBMIT" NAME="Submit" VALUE="Convert File To XML "></h1>
</form>

<hr>
<FONT SIZE=-2> Copyright &copy; 1998,2005 NCI (ISD) . All rights
reserved. <BR>
</body>
</html>

