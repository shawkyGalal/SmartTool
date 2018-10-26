<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String queryIndex = request.getParameter("queryIndex");
	String queryId = request.getParameter("queryId");
	String rownum = request.getParameter("rownum");
	String execTime = request.getParameter("execTime");
	String appURL = request.getRequestURL().substring(0,request.getRequestURL().indexOf(request.getServletPath()));
	
	String rqs = request.getQueryString() ;
	boolean printable = ( request.getParameter("printable") != null  && request.getParameter("printable").equalsIgnoreCase("true") );
	String printableStr = "&printable=" ;
	if (rqs != null )
	{
	if ( rqs.indexOf(printableStr + printable) == -1 ) rqs = rqs + printableStr + printable ;
	rqs = Support.Misc.repalceAll(rqs , printableStr + printable , printableStr +! printable );
	}

%>
		 (<%=rownum%>) Record(s) 
		<li><a href = "/SmartTool/editAndExecute.jsp?<%=request.getQueryString()%>" target = "Design Report" >Design Using Old System</a></li>
		<li> Execution Time = <%=execTime%> ms </li> 
		<li><a	title="Display Data in Chart " target='Chart Viewer' href="<%=appURL%>/includes/jqueryChart.jsp?queryId=<%=queryId%>&queryIndex=<%=queryIndex%>&displaySeriesCount=2&chartCategory=Bar&chartHeight=500&chartWidth=500">Display Data in Chart (jQuery Chart)</a> </li>
		<li><a	title="Display statistics for executing this report" target='Execution Statistics' 	href="<%=appURL%>/editAndExecute.jsp?execAgainstRep=Y&id=10155&queryId=<%=queryId%>&seq=<%=queryIndex%>">Show exec stat.</a> </li>
		<li><a target='query XML Viewer' Title="Display The result as XML (should not contain hyperlinks)" href='<%=appURL%>/queryToXML.jsp?<%=request.getQueryString()%>&seq=<%=queryIndex%>'>To XML </a></li> 
		<li><a target='query JSON Viewer' Title="Display The result as JSON (should not contain hyperlinks)" href="javaScript: handelAjaxJson('/SmartTool/queryResultToJSON.jsp?<%=request.getQueryString()%>&seq=<%=queryIndex%>') ;   ">To JSON </a></li>
		<li><a target='SOA ' Title="Post Results as XML to a Web Service" href='<%=appURL%>/queryToHttpPoster.jsp?<%=request.getQueryString()%>&seq=<%=queryIndex%>'>Post To Web Service</a> </li>
		<li><a target='query Chart Drawer' 	href='<%=appURL%>/Charts/queryChartDrawer.jsp?<%=request.getQueryString()%>&seq=<%=queryIndex%>'>Chart Viewer  </a></li>
		<li><a target='query Chart Drawer' 	href='<%=appURL%>/AJAX_Chart.jsp?<%=request.getQueryString()%>&seq=<%=queryIndex%>'>AJAX Chart Viewer </a> </li>
		<li><a target='SMS Sender'	href='<%=appURL%>/SmsSender.jsp?smsQueryId=<%=queryId%>&queryIndex=<%=queryIndex%>&<%=request.getQueryString()%>'>Send As SMS</a></li>
		<li><a title = "Send a request to System Admin to Schedule this report to be sent as an email " target='Create a request for email Notification'	href='<%=appURL%>/createMailNotificationBatch.jsp?<%=request.getQueryString()%>&seq=<%=queryIndex%>'>Request for Email Notification Schedule</a> </li>
		<li><a title="Open Data with XLS sheet (You Can Build You own template)" target='XLS Viewer' href="<%=appURL%>/queryToXLS.jsp?<%=request.getQueryString()%>&seq=<%=queryIndex%>"><img border=0 src="<%=appURL%>/images/icxls.gif"></img></a></li>
		<li><a href = "?<%=rqs %>" >Printable Version</a></li>
		<li><a href = "editAndExecute.jsp?id=13654&lookupTableName=LU_QUERIES&query_id=<%=queryId%>&_queryIndex=<%=queryIndex%>" 
			   target = "Notification List" 
			   title = "Maintain the notification List for this report and Send Results of ths report to the active items of this notification list" 	>Maintain Notification list and Send By Email</a></li> 
