<%@ page errorPage="errorPage.jsp"
	contentType="text/html;charset=UTF-8"
	import="Support.Misc"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>

	<head> 
		<meta http-equiv="Content-Type"	content="text/html; charset=UTF-8">
	</head>
	
	<% 
		Misc.saveUploadedFile(session, request, response, out, application) ; 
		
		if (request.getParameter("queryIndex") != null) // if comming from getClientFile.jsp
		{
			response.sendRedirect("getClientFile.jsp?"+request.getQueryString());	
		}
		else 
		response.sendRedirect("objectAttachments.jsp?"+request.getQueryString());	
		
	%>