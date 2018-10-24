<%@ page errorPage="../errorPage.jsp"
	contentType="text/html;charset=UTF-8"
	import="java.util.* , java.sql.* , java.io.*"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=UTF-8">
<title>XML to DB Uploader</title>
</head>
<body>
<p>
<%   
  String fileName  = request.getParameter("fileName");
  File xlsFile = new File (session.getServletContext().getRealPath("")+"\\"+fileName);
  Support.XML.XmlToJavaVectors xml2java  = new Support.XML.XmlToJavaVectors("file:///"+xlsFile.getPath() , false);
  java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
  if (con == null)
  {response.sendRedirect("../loginScreen.jsp?comeFrom=editQuery.jsp");} 
  con.setAutoCommit(false);
  Vector errors = xml2java.upload2DB(request.getParameter("tableName"),con);
  if (errors.size()== 0 )
  {
    out.println("<Bold>Successfully uploaded"); 
  }
  for (int i=0 ;i<errors.size() ; i++)
  {
    out.println("Error in Record No. "+i +" Due to :<br>" + errors.elementAt(i).toString());
  }
%>
</p>
</body>
</html>
