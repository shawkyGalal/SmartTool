<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="Support.Deployment.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>Deployment Sample - For Deploying Smart Tool to Different
Environments</p>
<p>1- prepare The Deployment File ... <a href="Deploy_SmartTool.xml">Sample
File</a></p>
<p>2- <a href="deployymentDescriptor.jsp?run">Run the Deployment</a></p>
<%
if (request.getParameter("run") != null)
{
	FileOperationsXmlDoc foxd = new FileOperationsXmlDoc(session.getServletContext().getRealPath("Deployyment")+"\\Deploy_SmartTool.xml");
	List allOperations = foxd.getAllOperations();
	Iterator it = allOperations.iterator();
	while (it.hasNext())
	{	
		Operation op = (Operation)it.next();
		op.execute();
	}
	out.println("Deployment Done Suuccessfully");
}
%>
</body>
</html>