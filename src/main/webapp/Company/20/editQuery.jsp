<%@ page errorPage="errorPage.jsp"%>
<%@page  language="java" contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");
 String appURL = Support.Misc.getAppURL(request) ;  %>
<html>
<head>
	<script type="text/javascript" src="<%=appURL%>/includes/AJAX_new.js"></script>
	<script type="text/javascript" src="<%=appURL%>/includes/resizable-tables.js"></script>
	<script type="text/javascript" src="<%=appURL%>/jQueryAssets/jquery-1.8.3.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="<%=appURL%>/jQueryAssets/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="<%=appURL%>/jQueryAssets/jquery-ui.js"></script>
	<script type="text/javascript" src="<%=appURL%>/jQueryAssets/jquery-ui-1.9.2.tabs.custom.min.js" type="text/javascript"></script>
	
	<link href="<%=appURL%>/jQueryAssets/jquery.ui.core.min.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="<%=appURL%>/jQueryAssets/jquery-ui.css">
	<link rel="stylesheet" href="<%=appURL%>/includes/smartTool.css">
	<link href="<%=appURL%>/jQueryAssets/jquery.ui.theme.min.css" rel="stylesheet" type="text/css">
	<link href="<%=appURL%>/jQueryAssets/jquery.ui.tabs.min.css" rel="stylesheet" type="text/css">
	   
	<script type="text/javascript">
		   $(function() {
			    $( "#EditorsTab" ).tabs({
			      beforeLoad: function( event, ui ) {
			    	ui.panel.html("<img width = 20px Hieght = 20px  src = \"<%=appURL%>/images/ajaxLoading.gif\" ></img> Please Wait ...") ; 
			        ui.jqXHR.error(function() {
			          ui.panel.html(
			            "Couldn't load this tab. Please Contact IT Department We'll try to fix this as soon as possible. " );
			        });
			      }
			    });
			     
			  });
	</script>
	<% String idWithoutQuote = request.getParameter("id") ;  %>

</head>
	<body>
		<div id="EditorsTab"> 
			<ul>
				<li><a href="#oldEditor"><strong><em>Old Editor</em></strong></a></li> 
				<li><a href="queryResultWithEditableRS.jsp?id=64679&query_id=<%=idWithoutQuote%>"><strong><em>New Editor</em></strong></a></li>
			</ul>
		
			<div id = "oldEditor" >
				<jsp:include page="editQuery_old.jsp?<%=request.getQueryString()%>"></jsp:include>
			</div>
		
		</div>
		

</BODY>
</HTML>
