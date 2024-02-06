<%@ page errorPage="../errorPage.jsp"
	contentType="text/html;charset=UTF-8"
	import="java.util.* , java.sql.* , java.io.*"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=UTF-8">
<title>Excel Sheet Reading Form</title>
</head>
<p>
<% String userdir = "XMLParser\\XMLFiles"; //"XLSUploading\\Data" %> <jsp:useBean
	id="upbean" class="oracle.jsp.webutil.fileaccess.HttpUploadBean">
	<jsp:setProperty name="upbean" property="destination"
		value="<%= userdir %>" />
</jsp:useBean> <%   
     //-----------Saving The client File at the Server--
     upbean.setBaseDir(application, request);
     upbean.upload(request);
     java.util.Enumeration fileNames = upbean.getFileNames();
     String fileName = "";
     while (fileNames.hasMoreElements())
     {
      fileName = fileNames.nextElement().toString();
     }
     //----------------- Reading The XLS File  -----------
     
     File xlsFile = new File (session.getServletContext().getRealPath("")+"\\"+fileName);
     Support.XML.XmlToJavaVectors xml2java  = new Support.XML.XmlToJavaVectors("file:///"+xlsFile.getPath() , false);
     out.print(xml2java.toHtmlTable());
%>
</p>
<form name="form1" method="post" action="uploadXML2DB.jsp">
<p>To Upload XML File Contenets to DB:</p>
<p><strong><font color="#FF0000">1- XML Tag Names
should be identical to Column Names of the DB table </font> </strong></p>
<p>Upload To DB Table <input type="text" name="tableName"> <input
	type="hidden" name="fileName" value='<%=fileName%>'> <input
	type="submit" name="Submit" value="Submit"></p>
</form>

<hr>
<FONT SIZE=-2> Copyright &copy; 1998,2007 Shawky Foda . All
rights reserved. <BR>
</html>
