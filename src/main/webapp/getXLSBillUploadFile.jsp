<html>
<head>
<title>Get File from Client</title>
</head>

<body>

<h1 align="center">Bill Upload Generator from XLS</h1>

<form action="BillUploadGenerator.jsp" ENCTYPE="multipart/form-data"
	method=POST>
<h1><em><font size="4" color="#FF0000">Notes :Use an
XLS File Exactly like : <a href="xls/BillUploadTempl.xls">Templ of
The XLS File</a> </font></em></h1>
<table width="75%" border="1">
	<tr>
		<td><font size="4">1- Select File to Be Converted</font></td>
		<td><input type="FILE" name="File" size="20" maxlength="80"></td>
	</tr>
	<tr>
		<td>2- Sheet No.</td>
		<td><input name="sheetNo" type="text" id="sheetNo" value="0"></td>
	</tr>
	<tr>
		<td><font size="4">3- Click To Convert File To XML</font></td>
		<td><input type="SUBMIT" name="Submit"
			value="Convert File To XML Bill Upload "></td>
	</tr>
</table>
</form>


<p>&nbsp;</p>
<hr>
<FONT SIZE=-2> Copyright &copy; 1998,2005 SADAD (ICT) . All
rights reserved. <BR>
</body>
</html>

