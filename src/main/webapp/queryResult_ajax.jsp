<%@ page errorPage="errorPage.jsp" 	%>

<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>

<HTML>
	<head>
		<%String appURL = Support.Misc.getAppURL(request) ;  %>
		<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
		<script type="text/javascript" src="<%=appURL%>/includes/AJAX_new.js"></script>
		<script type="text/javascript" src="<%=appURL%>/jQueryAssets/jquery-1.10.2.js"></script>
		<script type="text/javascript" src="<%=appURL%>/jQueryAssets/jquery-ui.js"></script>
		<script type="text/javascript" src="<%=appURL%>/jQueryAssets/jquery-ui-1.9.2.tabs.custom.min.js" type="text/javascript"></script>
	
	
		<script>
		  $( document ).ready(function() {
			  sendAjaxRequestToJsp ( 'queryResult_origin.jsp?<%=request.getQueryString()%>'  , 'mainBody') ;
		  });
		</script>
  
	</head>

	<title>Query result</title>

	<body bgcolor="#FFEEFF" id = "mainBody"  >
		
	</body>

</html>
