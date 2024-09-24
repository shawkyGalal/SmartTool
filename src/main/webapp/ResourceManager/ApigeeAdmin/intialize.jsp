
<%@page import ="com.smartvalue.apigee.configuration.infra.ManagementServer"%>
<%@page import ="java.util.*"%>
<%@page import="com.smartValue.web.AppContext"%>



<%
		ManagementServer ms = AppContext.getApigeeManagementServer(session); 
		String xx = request.getParameter("refreshSessionInfo") ; 
		boolean refreshSessionInfo = xx != null && xx.equalsIgnoreCase("true") ; 
 		if (ms == null || refreshSessionInfo )
 		{
 			response.sendRedirect("/infraSelector.jsp") ; 
 			
 		}
		
%>