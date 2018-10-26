<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>

<%@page import="com.implex.database.PersistantObject"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.sql.ResultSet"%><html>

<%@page import="Support.SqlReader , java.util.* "%><html>
<head>
<title>Approval Cycle </title>
</head>

<body>

<h1 align="center">ِData Approval Cycle دورة اعتماد البيانات </h1>
<h3 align="left">ِInformation Need Your Approval : </h3>
	<%  
	String dirPathVarName = "dirPath" ;
	String dirPathValue = Support.SqlReader.getValueFromRequestOrSession( dirPathVarName , request, session);
	String queryId = Support.SqlReader.getValueFromRequestOrSession( "queryId" , request, session);
	String selectedOraRowId = Support.SqlReader.getValueFromRequestOrSession( SqlReader.SELECTED_ORA_ROWID , request, session);
	String queryIndexToFilterByRowId = Support.SqlReader.getValueFromRequestOrSession( SqlReader.QUERY_INDEX_TO_FILTER_BY_ROWID , request, session);
	String NoDBRecord = Support.SqlReader.getValueFromRequestOrSession( "NoDBRecord" , request, session);
		
	Support.ConnParms selectedConnParms = (Support.ConnParms)session.getAttribute("selectedConnParms"); 
	 
	  
   	Support.SqlReader sqlReader = null;
   	java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");
   	java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
   	sqlReader = new Support.SqlReader(repCon,"LU_QUERIES" , "QUERY_BODY",  queryId, session , request ); 
	request.setAttribute("sqlReader" , sqlReader);
	%>
	
		<jsp:include page="renderQueryResult.jsp">
        	<jsp:param name="filterByRowId" 	value="T" />
        	<jsp:param name="queryIndex" 		value="<%=queryIndexToFilterByRowId%>" />
        	<jsp:param name="printable" 		value="true" />
        	<jsp:param name="_SelectedOraRowId" value="<%=selectedOraRowId%>" />
        	<jsp:param name="_queryIndexToFilterByRowId" value="<%=queryIndexToFilterByRowId %>" />
    	</jsp:include>
	
 
<% 
	//String serverName = "127.0.0.1" ;

	
	StringTokenizer st = new StringTokenizer(dirPathValue , "/");
	String tableOwner = st.nextToken() ;
	tableOwner = st.nextToken() ;
	String tableName = st.nextToken() ;
	String approvalListQueryId = "34557" ;
	 

%>
---------------------------------------------------------------------------------
<jsp:include page="queryResultWithEditableRS.jsp" >
		<jsp:param name="id" 				value="<%=approvalListQueryId %>" />
		<jsp:param name="table_owner" 		value="<%=tableOwner%>" />
		<jsp:param name="table_name" 		value="<%=tableName%>" />
		<jsp:param name="_SelectedOraRowId" value="<%=selectedOraRowId %>" />
		<jsp:param name="printable" 		value="True" />
</jsp:include>
---------------------------------------------------------------------------------
<div id="User_Updates">
<br><% String commentlListQueryId = "36166" ; %>
<a href = "TableInsertForm.jsp?KEY_VALUE=<%=URLEncoder.encode(selectedOraRowId) %>&_QUERYINDEXTOFILTERBYROWID=0&TABLE_OWNER_COM=<%=tableOwner%>&TABLE_NAME_COM=<%=tableName%>&owner=SUPPORT&tableName=TABLE_ROW_COMMENT" >ADD New Update/Comment</a>
<jsp:include page="queryResultWithEditableRS.jsp" >
		<jsp:param name="id" 				value="<%=commentlListQueryId %>" />
		<jsp:param name="table_owner_com" 	value="<%=tableOwner%>" />
		<jsp:param name="table_name_com" 	value="<%=tableName%>" />
		<jsp:param name="_SelectedOraRowId" value="<%=selectedOraRowId %>" />
		<jsp:param name="printable" 		value="False" />
</jsp:include>
</div>
<jsp:include page="/includes/dbTransc.html"></jsp:include>

 	

<br>
<a target = "" title = "Maintain Data Need Your Approval List " href = "editAndExecute.jsp?id=<%=queryId%>" >Maintain Data Need  Approval </a>
<br> <a target = "Maintain Approvals" href = "editAndExecute.jsp?id=<%=approvalListQueryId%>&table_owner=<%=tableOwner%>&table_name=<%=tableName%>&_SelectedOraRowId=<%=URLEncoder.encode(selectedOraRowId) %>">Maintain Approval Cycle Data</a>
<br> <a target = "Maintain Comments" href = "editAndExecute.jsp?id=<%=commentlListQueryId%>&table_owner_com=<%=tableOwner%>&table_name_com=<%=tableName%>&_SelectedOraRowId=<%=URLEncoder.encode(selectedOraRowId) %>">Maintain Comments Data</a>

	
</body>
</html>

