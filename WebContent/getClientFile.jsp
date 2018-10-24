<%@page import="com.implex.database.map.SecUsrDta"%>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>

<%@page import="com.implex.database.PersistantObject"%>
<%@page import="java.net.URLEncoder"%><html>

<%@page import="Support.SqlReader , java.util.* "%><html>
<head>
<title>Get File from Client</title>
</head>
<%
    SecUsrDta  loggedUser = (SecUsrDta)session.getAttribute("loggedUser") ;
	boolean smartToolAdmin = loggedUser.isSmartToolAdmin() ;
	int lang =  loggedUser.getUserLangIntValue(); //  (isEnglish)? 0 : 1 ;
	boolean isEnglish = lang==0 ;
%>

<body dir="<%=(isEnglish)? "ltr" : "rtl"  %>" >

	<%  
	String dirPathVarName = "dirPath" ;
	String dirPathValue = Support.SqlReader.getValueFromRequestOrSession( dirPathVarName , request, session);
	String queryId = Support.SqlReader.getValueFromRequestOrSession( "queryId" , request, session);
	String selectedOraRowId = Support.SqlReader.getValueFromRequestOrSession( SqlReader.SELECTED_ORA_ROWID , request, session);
	String queryIndexToFilterByRowId = Support.SqlReader.getValueFromRequestOrSession( SqlReader.QUERY_INDEX_TO_FILTER_BY_ROWID , request, session);
	  
   	Support.SqlReader sqlReader = null;
   	java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");
   	java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
   	sqlReader = new Support.SqlReader(repCon,"LU_QUERIES" , "QUERY_BODY",  queryId, session , request); 
	request.setAttribute("sqlReader" , sqlReader);
			
	String step0[] = {"Object Information " , "  البيانات الاساسية العنصر"} ;
	
	%>
		<h2 align="center"><%=step0[lang] %></h2>
		<jsp:include page="renderQueryResult.jsp">
        	<jsp:param name="filterByRowId" 	value="T" />
        	<jsp:param name="queryIndex" 		value="<%=queryIndexToFilterByRowId%>" />
        	<jsp:param name="printable" 		value="true" />
        	<jsp:param name="_SelectedOraRowId" value="<%=selectedOraRowId%>" />
        	<jsp:param name="_queryIndexToFilterByRowId" value="<%=queryIndexToFilterByRowId %>" />
    	</jsp:include>
    	

		<jsp:include page="objectAttachments.jsp">
        	<jsp:param name="_SelectedOraRowId" value="<%=selectedOraRowId%>" />
        	<jsp:param name="dirPath" value="<%=dirPathValue%>" />
    	</jsp:include>
 
</body>
</html>

