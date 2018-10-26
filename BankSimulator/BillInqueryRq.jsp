<%@ page contentType="text/html;charset=windows-1252"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">
<title>Hello World</title>
</head>
<body>
<table width="100%" id="table2">
	<tr>
		<td width="562"><img border="0" src="../SadadLogo.gif"
			width="256" height="89"></td>
		<td><img border="0" src="../Atos Orgin Logo.bmp" width="145"
			height="50" align="right"></td>
	</tr>
</table>

<p align="center"><b><font size="6">Bank Simulator System
</font></b></p>
<p align="center"><font size="6"><b>1- Bill Inquiry&nbsp;
Process</b></font></p>
<form method="POST" action="BillInqueryRs.jsp">
<table border="1" width="63%" id="table1" align=center>
	<tr>
		<td width="29%">
		<p dir="rtl" align="left"><b><font size="4">Bank to Be
		Simulated</font></b>
		</td>
		<td colspan="2">
		<p align="center"><select size="1" name="bankId">
			<option value="SAMBSARI">SAMBA</option>
			<option value="ARNBSARI">ARAB National Bank</option>
			<option value="RJHISARI">Al Rajhi</option>
			<option value="BSFRSARI">Saudi Frency</option>
		</select>
		</td>
	</tr>
	<tr>
		<td width="29%"><b><font size="4">Biller Specific
		Query</font></b></td>
		<td width="69%" colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<td width="29%">&nbsp;</td>
		<td width="42%"><b>Select Biller : <i><font size="2">(for
		Example Marafiq)</font></i></b></td>
		<td width="27%"><select size="1" name="billerId">
			<option value="003">NCCI</option>
			<option value="004">Marafiq</option>
			<option value="005">Mobily</option>
			<option value="006">MM</option>
			<option value="007">CITC</option>
		</select></td>
	</tr>
	<tr>
		<td width="29%">&nbsp;</td>
		<td width="42%"><b>Enter Account ID :<i><font size="2">(for
		example 1111001103829)</font></i></b></td>
		<td width="27%"><input type="text" name="billingAcct" size="20"></td>
	</tr>
	<tr>
		<td width="29%"><b><font size="4">Customer Specific
		Query</font></b></td>
		<td width="42%">&nbsp;</td>
		<td width="27%">&nbsp;</td>
	</tr>
	<tr>
		<td width="29%">&nbsp;</td>
		<td width="42%"><b>Official ID Type (Example : NAT)</b></td>
		<td width="27%"><select size="1" name="OfficialIdType">
			<option value="IQA">IQA</option>
			<option value="NAT">NAT</option>
			<option value="NAT">BTL</option>
		</select></td>
	</tr>
	<tr>
		<td width="29%">&nbsp;</td>
		<td width="42%"><b>ID Number (Example : 1003832159)</b></td>
		<td width="27%"><input type="text" name="officialId" size="20"></td>
	</tr>
</table>
<p align="center"><b>Select SADAD Environment to Which
Simulation will be Executed : </b> <select size="1" name="environment">
	<option value="0">Integration Testing</option>
	<option value="1">Unit Testing</option>
</select></p>
<p align="center">&nbsp; <input type="submit" value="Submit"
	name="B1"><input type="reset" value="Reset" name="B2"></p>
</form>
<p dir="ltr">&nbsp;</p>
<p dir="ltr">&nbsp;</p>
</body>
</html>