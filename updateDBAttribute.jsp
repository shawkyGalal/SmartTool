<%@page import="com.smartValue.DbAttributeUpdater"%>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>
<HTML lang="ar">
	<BODY>
	
		<%
		DbAttributeUpdater dbau = new DbAttributeUpdater() ;
		dbau.update(session, request, response, out); 
	    %>
	  
	</BODY>
</HTML>
