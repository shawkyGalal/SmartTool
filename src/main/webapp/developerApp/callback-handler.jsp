<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Call Back Handler</title>
</head>
<body>
Request Query String  <%= request.getQueryString() %>
<br>Request Parameters : 
<table border="1">
<tr><td>Param Name </td> <td>Value</td> </tr>

<%
for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet())
{	out.println("<tr>");
	out.println("<td>" + entry.getKey() + ": </td>"
				+"<td>" + entry.getValue()[0] + "</td>");
	out.println("</tr>"); 
}
%>
</table>
<br>Request Headers :
<table border="1"> 
<tr><td>Header Name </td> <td>Value</td> </tr>
<% 

java.util.Enumeration<String> headerNames = request.getHeaderNames();
while (headerNames.hasMoreElements()) {
	String headerName = headerNames.nextElement();
	out.println("<tr>");
	out.print("<td>" + headerName + ": </td>");
	String headerValue = request.getHeader(headerName);
	out.print("<td>" + headerValue + "</td>");
	out.println("</tr>");
}
%>
</table>

<br>Request Attributes :
<table border="1"> 
<tr><td>Attribute Name </td> <td>Value</td> </tr>
<% 

java.util.Enumeration<String> attNames = request.getAttributeNames();
while (attNames.hasMoreElements()) {
	String attName = attNames.nextElement();
	out.println("<tr>");
	out.print("<td>" + attName + ": </td>");
	String attValue = request.getAttribute(attName).toString();
	out.print("<td>" + attValue + "</td>");
	out.println("</tr>");
}
%>
</table>


<br>Method : <%=request.getMethod()%>
<br>RequestURI : <%=request.getRequestURI()%>
<br>Content Length : <%=request.getContentLength() %>
<br>Auth Type : <%=request.getAuthType()%>
<br>Character Encoding : <%=request.getCharacterEncoding()%>
<br>ContextPath : <%=request.getContextPath()%>
<br>Local Address : <%=request.getLocalAddr()%>
<br>Local Name : <%=request.getLocalName()%>
<br>Local Port : <%=request.getLocalPort()%>
<br>Remote Addr : <%=request.getRemoteAddr()%>

<%
java.io.BufferedReader br = request.getReader();
out.print("<br>----Request BODY-----");
String line = "" ; 
while((line = br.readLine()) != null) {
        out.print(line);
}
out.print("<br>----End of Request BODY-----");
%> 




</body>
</html>