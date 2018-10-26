<%@ page errorPage="errorPage.jsp"
	contentType="text/html;charset=UTF-8" import="java.io.*"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=UTF-8">
<title>File Editor</title>
</head>
<body>
<h2>
<%
String fileName = request.getParameter("fileName");
//-----------Saving the file -------
if (request.getParameter("Submit")!= null)
{
  if (fileName.indexOf("Standard") != -1 )
  {
    throw new Exception("Standard Queries Can Not Be Updated ");
  }
  String fileText = request.getParameter("fileText");
  java.io.PrintWriter pr = new PrintWriter(new BufferedWriter(new FileWriter(session.getServletContext().getRealPath("")+"\\"+fileName)));
  pr.println(fileText);
  pr.close();
}
//--------------------------
BufferedReader in = new BufferedReader( new FileReader(session.getServletContext().getRealPath("")+"\\"+fileName)); //"C:\\Edge_Work\\Support\\public_html\\"
String s = "";
String s2 = "";
while (( s= in.readLine()) != null) 
{
  s2+= s +"\n";
}
in.close();
%>
</h2>
<form name="form1" method="post" action="fileEditor.jsp">

<p align="center">File Name (Change to Save as)<input type="text"
	name="fileName" value=<%=fileName%>> <input type="submit"
	name="Submit" value="Save"></p>
<p align="center"><textarea name="fileText" cols="100" rows="30"><%= s2%></textarea>
</p>


</form>
</body>
</html>
