<%@page import="java.io.FileWriter"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.net.URL"%>
<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
</head>
<body>
<%
	String filesPath = request.getParameter("filesPath") ; // "file:///C:/TEMP/diagrams/" 
	String fileName = request.getParameter("fileName") ; 
	String fileContent = request.getParameter("fileContent") ;
	//-- Save the file Here
	try{
	URL tempFileURL = new URL("file:///" + filesPath +fileName);
    PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter(tempFileURL.getFile())));
    out1.print(fileContent);
    out1.close();
	}
	catch ( Exception e) 
	{
		out.print("Unable To Save File Due To " + e.getMessage()) ; 
		e.printStackTrace();
	}
%>
Diagram Saved Successfully in File : <%=filesPath+fileName %>  
</body>
</html>