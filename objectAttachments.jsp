<%@page import="com.implex.database.map.SecUsrDta"%>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>

<%@page import="com.implex.database.PersistantObject"%>
<%@page import="java.net.URLEncoder"%><html>

<%@page import="Support.SqlReader , java.util.* "%><html>
<head>
<title>Object Attachments </title>
</head>
<%
	SecUsrDta  loggedUser = (SecUsrDta)session.getAttribute("loggedUser") ;
	boolean smartToolAdmin = loggedUser.isSmartToolAdmin() ;
	int lang =  loggedUser.getUserLangIntValue(); //  (isEnglish)? 0 : 1 ;
	boolean isEnglish = lang==0 ;
	String step0[] = {"Attachments of an Item" , "  ملحقات العنصر"} ; 
	String step1[] = {"1- Select File to Be Uploaded"  , "1- اختار الملف المراد الحاقه بهذا العنصر  " } ; 
	String step2[] = {"2- Click To Upload Your File" , "2- إضغط لتحميل الملف"} ;
	String step3[] = {"Upload File" , "تحميل الملف" } ;
	String step4[] = {"After Uploading, You Can Write a Notes, Description or comment for your uploaded file" , 
						"بعد التحميل يمكنك كتابة ملاحظات او وصف للملف بعد تحميله" } ;
	String step5[] = {"Please be sure to Click on Green Save Button to save your attachment(s)" , "برجاء التأكد من الضغط على السهم الاخضر للحفظ النهائى "};
	String step6[] = {"Item Attachments" , "  قائمة الوثائق الملحقة بالعنصر " };
%>

<body dir="<%=(isEnglish)? "ltr" : "rtl"  %>" >

<h2 align="center"><%=step0[lang] %></h2>
<jsp:include page="/includes/serverMessagesList.jsp"></jsp:include>
<%  
	String dirPathVarName = "dirPath" ;
	String dirPathValue = Support.SqlReader.getValueFromRequestOrSession( dirPathVarName , request, session);
	String queryId = Support.SqlReader.getValueFromRequestOrSession( "queryId" , request, session);
	String selectedOraRowId = Support.SqlReader.getValueFromRequestOrSession( SqlReader.SELECTED_ORA_ROWID , request, session);
	String NoDBRecord = Support.SqlReader.getValueFromRequestOrSession( "NoDBRecord" , request, session);
		
	Support.ConnParms selectedConnParms = (Support.ConnParms)session.getAttribute("selectedConnParms"); 
   	java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");
   	java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
	
	String serverName = "127.0.0.1" ;
	java.io.File  folder = new java.io.File(new java.net.URI("file:////"+serverName+"/" + dirPathValue));
	if (! folder.exists())
	{
		folder.mkdir();
	}
	
	StringTokenizer st = new StringTokenizer(dirPathValue , "/");
	String tableOwner = st.nextToken() ;
	tableOwner = st.nextToken() ;
	String tableName = st.nextToken() ;
	String fileListQueryId = "23887" ;
	String uniqueWhereCaluse = Support.Misc.getRecordUniqueWhereClaue(con ,  tableOwner , tableName , selectedOraRowId ) ; 
	String rqs = request.getQueryString() ; 

%> 
<form  action = "saveUploadedFile.jsp?<%=rqs%>" ENCTYPE="multipart/form-data" 	method=POST>  <!--  action="saveUploadedFile.jsp"  -->

	<h1><font size="4"> <%=step1[lang]%></font>	 
	<INPUT TYPE="FILE" NAME="File" SIZE="20" MAXLENGTH="80" VALUE = "xxxx" ></h1>
	
	<h1><font size="4"> <%=step2[lang]%>  
	<INPUT TYPE="SUBMIT" NAME="Submit" VALUE="<%=step3[lang]%>" >
	<%=step4[lang]%> </font> </h1>
	
</form>
<font size="4">
<div title = "If Not Found, Click First on the Edit Button" style = "color :red " >  <%=step5[lang]%> </div>
<br>
<div align="center" style="width:75%"><font style="underline : true" > <%=step6[lang]%></font> </div>
</font>
<div align="center" style="width:75%">
<jsp:include page="queryResultWithEditableRS.jsp" >
		<jsp:param name="id" 				value="<%=fileListQueryId %>" />
		<jsp:param name="table_owner" 		value="<%=tableOwner%>" />
		<jsp:param name="table_name" 		value="<%=tableName%>" />
		<jsp:param name="uniqueWhereCaluse" value="<%=uniqueWhereCaluse %>" />
		<jsp:param name="rowidValue" 		value="<%=selectedOraRowId %>" />
		<jsp:param name="printable" 		value="F" />
</jsp:include>
</div>


 	
<% if (smartToolAdmin){ %>
<br>
<a href ="<%=dirPathValue%>" > Files Listing (Tomcat) </a> -- 
<a target = "Maintain Report" href = "editAndExecute.jsp?id=<%=fileListQueryId%>&table_owner=<%=tableOwner%>&table_name=<%=tableName%>&rowidValue=<%=URLEncoder.encode(selectedOraRowId)%>&uniqueWhereCaluse=<%=(uniqueWhereCaluse)%>">Maintain File List</a>
<% } %>	
</body>
</html>

