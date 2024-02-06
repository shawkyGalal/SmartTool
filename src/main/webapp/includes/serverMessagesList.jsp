
<%@page import="java.util.Stack"%>
<%@ page errorPage="errorPage.jsp"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%@page import="com.implex.database.map.SecUsrDta"%>
<%request.setCharacterEncoding("UTF-8");%>
<html>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<head>
<%	
	SecUsrDta loggedUser = (SecUsrDta) session.getAttribute("loggedUser") ; 
	String appUrl = Support.Misc.getAppURL(request) ; 
%>
<script type="text/javascript" src="<%=appUrl%>/includes/AJAX_new.js"></script>

</head>
<body>
<% 	com.implex.database.MessagesCommnuicatorService mServices = loggedUser.getModuleServiceContainer().getMessageCommnunicatorService() ; 
	// For Testing 
 		//mServices.sendMessageToUser("Message 0 ");
	 	//mServices.sendMessageToUser("Message 1 ");
	 	//mServices.sendMessageToUser("Message 2 ");
	 
	Stack<Object> sysMessagesToUser = mServices.getSysMessagesToUser();
	if (request.getParameter("clearAll")!=null)
	{
		sysMessagesToUser.clear(); 
	}
	if (request.getParameter("OK")!=null)
	{ 
		int i = Integer.parseInt( request.getParameter("msgId") ) ; 
		
		sysMessagesToUser.remove(i); 
	}
	String queryString = request.getQueryString() ; 
	
%>
<div id = "serverMessages" >
	<% if ( sysMessagesToUser.size() > 1 ) 
		{ 
			%><div align="center"> <a href = "javascript:sendAjaxRequestToJsp('/SmartTool/includes/serverMessagesList.jsp?clearAll=Y' , 'serverMessages' ) ; " >Clear All</a> </div><% 	
		}	
		
		int i = 0 ; 
		for ( Object x : sysMessagesToUser)
		{	 
		  %> <div style="color:Green" align="center"> <%=x %>..
		  		<a href = "javascript:sendAjaxRequestToJsp('/SmartTool/includes/serverMessagesList.jsp?OK=Y&msgId=<%=i%>' , 'serverMessages' ) ; " >OK</a>		
		  	 </div> <%  
		  	i++ ; 
		}
	
	%>
</div>
</body> 
</html>
