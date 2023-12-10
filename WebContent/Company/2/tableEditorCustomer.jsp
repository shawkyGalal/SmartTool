<%@ page errorPage="../../errorPage.jsp"
	import="java.util.*, java.io.* , java.sql.* , java.text.SimpleDateFormat , javax.faces.model.SelectItem"%>
<%@ page import="java.util.* , Support.Misc"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.implex.database.map.*"%>

<%@page import="java.net.URLEncoder"%>

<%@page import="java.math.BigDecimal"%><html>
	<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
	<title>بيانات عميل  </title>
	<head>
	<% String appUrl = Support.Misc.getAppURL(request) ;  %>
		<link rel="stylesheet" type="text/css" href="<%=appUrl%>/includes/HijriCalender/lib/jquery.calendars.picker.css">
		<script type="text/javascript" src="<%=appUrl%>/jQueryAssets/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="<%=appUrl%>/includes/HijriCalender/lib/jquery.calendars.js"></script>
		<script type="text/javascript" src="<%=appUrl%>/includes/HijriCalender/lib/jquery.calendars.plus.js"></script>
		<script type="text/javascript" src="<%=appUrl%>/includes/HijriCalender/lib/jquery.calendars.picker.js"></script>
		<script type="text/javascript" src="<%=appUrl%>/includes/HijriCalender/lib/jquery.calendars.picker-ar.js"></script>
		<script type="text/javascript" src="<%=appUrl%>/includes/HijriCalender/lib/jquery.calendars.ummalqura.js"></script>
		<script type="text/javascript" src="<%=appUrl%>/includes/HijriCalender/lib/jquery.calendars.ummalqura-ar.js"></script>
		<script type="text/javascript" src="<%=appUrl%>/includes/HijriCalender/hijriCalender.js"></script>
		<script type="text/javascript" src="<%=appUrl%>/includes/AJAX_new.js"></script>

		<script type="text/javascript" src="<%=appUrl%>/includes/resizable-tables.js"></script>
		<link href="<%=appUrl%>/jQueryAssets/jquery.ui.core.min.css" rel="stylesheet" type="text/css">
		<link href="<%=appUrl%>/jQueryAssets/jquery.ui.theme.min.css" rel="stylesheet" type="text/css">
		
		<link href="<%=appUrl%>/jQueryAssets/jquery.ui.tabs.min.css" rel="stylesheet" type="text/css">
		<script src="<%=appUrl%>/jQueryAssets/jquery-ui-1.9.2.tabs.custom.min.js" type="text/javascript"></script>

		<script language=javascript>
		   function confirmDelete()
		   {
		     if (confirm("Are you sure you want to Commit Your Transaction(s)?")) 
		        {return true;} 
			 else {return false;}
		    }
		  </script>
		<style type="text/css">
			body {	background-color: #00FFff; }
		</style>
		<script src="<%=appUrl%>/includes/jquery_commons.js" type="text/javascript"></script>

	
	</head>

	<body>
	<p align="center">
	<%
  		boolean printable = request.getParameter("printable") != null &&  request.getParameter("printable").equalsIgnoreCase("true");
  		
  		String _rqs = request.getQueryString() ;  
		if ( _rqs!= null)
			{
			_rqs =  Misc.repalceAll(_rqs , "Delete=Y&" , "") ;
			_rqs =  Misc.repalceAll(_rqs , "Commit=Y&" , "") ;
			_rqs =  Misc.repalceAll(_rqs , "RollBack=Y&" , "") ;
			}
		
		if (! printable) { 
		 %>
	
		<a target = "بيانات العميل " href = ?<%=request.getQueryString()+ "&printable=true" %> >نسخة للطباعة</a>
		<a href = "?<%=_rqs%>">Refresh</a>
	</p>
	<p align="center">
	<%
	}
	  java.sql.Connection  con =null;
	  con = (java.sql.Connection)session.getAttribute("con");
	  java.sql.Statement stmt = con.createStatement();  
	  String uniqueColumnName = "rowId";
      String tableowner = "CARRENT" ;
	  String tableNameonly = "CUSTOMER" ;
	  String sysMessageDivId = "sysMessageDivId" ;	  
	   String rowIdValue =  request.getParameter("ROWID");
	  String uniqueColumnValue = "'" + request.getParameter("ROWID") +"'";
	  
  	//-------------------------------------------
	 if (! printable) 
	{ %>
			<div id="<%=sysMessageDivId%>"></div>
			<table border="0" align="center" dir="rtl">
			<tr>
				 <jsp:include page="/includes/toolBarButtons.jsp"></jsp:include>
				 <jsp:include page="/includes/deleteButton.jsp">
				 	<jsp:param name="tableOwner" value="<%=tableowner%>"/>
				 	<jsp:param name="tableName" value="<%=tableNameonly%>"/>
				 	<jsp:param name="rowIdValue" value="<%=rowIdValue%>"/>
				 </jsp:include>

				<td width="50"> 
					<% String showHide = (request.getParameter("toggleDesign")!= null && request.getParameter("toggleDesign").equals ("Show Design") )? "Hid Design": "Show Design" ; %>
					<form action="" method="post">
							<input type="submit" name="toggleDesign" value="<%=showHide%>" >
							<input type="hidden"  name="ROWID" value="<%=uniqueColumnValue %>">
					</form> 
				</td>
			</tr>
		</table> <%
	}
  	
  	String queryStatment = "select t.* , t.rowid "
  		+"\n From " + tableowner +"."+tableNameonly  + " t " 
  		+ "\n" + " Where t." +uniqueColumnName +" = " + uniqueColumnValue 	 ; 
  	java.sql.ResultSet rs= null;
  	try{
  		rs =  stmt.executeQuery(queryStatment);
  	}
  	catch (Exception  e) {throw new Exception("Unable to execute : \n" + queryStatment + "\n Due to : \n" + e.getMessage());}
	boolean customerFound = rs.next();
	if ( ! customerFound ) {return ; } 
	
	String branch_id = "" ;
	String company_id = "" ;

	branch_id = rs.getString("BRANCH_ID") ;
	company_id = rs.getString ("COMPANY_ID");
	String cust_no = rs.getString ("CUST_NO"); 
	ResultSetMetaData rsmd = rs.getMetaData();
  	int columnCount = rsmd.getColumnCount();

%>
<h2 align="center">بيانات عميل رقم  : <%=cust_no %>  -- شركة : <%=company_id %> -- فرع رقم: <%=branch_id %></h2>
<% 
	    TableMaintMaster tmm = Support.Misc.getTableMaintMaster(session , tableowner , tableNameonly) ;
       %>
<div dir="rtl" align="right">
	<table  border="1" align="Right" dir="rtl" > 
		<%
	    	request.setAttribute("rs" , rs);
	    	request.setAttribute("tmm" , tmm);
	    	HashMap<String , TableMaintDetails> tmdshm =  tmm.getTableMaintDetailsHashMap();
	    	String columnName = "";
	    	TableMaintDetails tmd = tmdshm.get(columnName);
			String renderTmdJspPage ="../../includes/renderTmdByColumnName.jsp" ; 
	   	%>
	   	<tr>
	   		<%  columnName = "NAME";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=renderTmdJspPage%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>
	   	
	   		<%  columnName = "TEL1";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=renderTmdJspPage%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>


	   		<%  columnName = "TEL2";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=renderTmdJspPage%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>
	
	   		<%  columnName = "NATIONALITY";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=renderTmdJspPage%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>

	   	 
	   	</tr>
	   	<tr>

	   		<%  columnName = "IDTYPE";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=renderTmdJspPage%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>

	   		<%  columnName = "IDSOURCE";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=renderTmdJspPage%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>

	   		<%  columnName = "IDNO";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=renderTmdJspPage%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>
		
	   		<%  columnName = "JOB";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=renderTmdJspPage%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>

	   	</tr>
	   	<tr>

	   		<%  columnName = "ID_DATE_EXPIRE";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=renderTmdJspPage%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>


	   		<%  columnName = "LIC_DATE_EXPIRE";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=renderTmdJspPage%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>

	   		<%  columnName = "LICSOURCE";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=renderTmdJspPage%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>

	   		<%  columnName = "SUSPECTED";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=renderTmdJspPage%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>

	   	</tr>
	   	
	   	<tr>
	   		<%  columnName = "COMPANY";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=renderTmdJspPage%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>

	   		<%  columnName = "COMPANYTEL";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=renderTmdJspPage%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>

	   		<%  columnName = "ADDRESS";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=renderTmdJspPage%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>
			
	   		<%  columnName = "SPONSOR";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=renderTmdJspPage%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>

			 	
	   	</tr>
	   	
	   	<tr>
	   		<%  columnName = "SPONSORTEL";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=renderTmdJspPage%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>

	   		<%  columnName = "SPONSORADDRESS";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=renderTmdJspPage%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>


	   		<%  columnName = "ALLOW_MULTI_CONTRACTS";     tmd = tmdshm.get(columnName);  if (tmd != null) { %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=renderTmdJspPage%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>
			<%} else { %>
			 <td colspan="2" >Column <%= columnName %> Not Found <td/>
			<%} %>
			
	   	
	   		<%  columnName = "CUST_CATEGORY";     tmd = tmdshm.get(columnName);  if (tmd != null) { %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=renderTmdJspPage%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>
			<%} else { %>
			 <td colspan="2">Column <%= columnName %> Not Found <td/>
			<%} %>
	   	
	   	</tr>
	   	
	</table>
<% String custDetailsDivId = "custDetailsDivId" ; %>
	<a href = "javaScript:sendAjaxRequestToJsp('<%=appUrl%>/queryResultNonTabbed.jsp?id=25198&printable=true&idsource=<%=rs.getString("IDSOURCE")%>&idtype=<%=rs.getString("IDTYPE")%>&idno=<%=rs.getString("IDNO")%>' , '<%=custDetailsDivId %>' )"> عقود العميل</a>
	
</div>
	
<div id="<%=custDetailsDivId %>" >	
	<table dir="rtl" align="right" border = 0 >
	 	<tr align="right" dir="rtl"> 
		 	<td>
				<div id="<%=custDetailsDivId %>"></div>
			</td>
		</tr>
	</table>
<br></br>
</div>	
	<table align="center" border = 1>
		<tr>
			<td>
				<p align="center">
					<% if (!printable) { %>
						<a target = "طباعة العقد" href = ?<%=request.getQueryString()+ "&printable=true" %> >نسخة للطباعة</a>
						<p align="center"><a href = "../../tableEditor.jsp?tableName=CARRENT.CUSTOMER&ROWID=<%=URLEncoder.encode(request.getParameter("ROWID"))%>">Load with Default Page editor</a>
					<%} %>
				</p>
			</td>
		</tr>
	</table>
<% 
	stmt.close();
   	rs.close();
%>
	
</body>
</html>
