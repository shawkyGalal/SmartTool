<%@page import="java.net.URLDecoder"%>
<%@ page errorPage="errorPage.jsp"
	import="java.util.*, java.io.* , java.sql.* , java.text.SimpleDateFormat , javax.faces.model.SelectItem"%>
<%@ page import="java.util.* , Support.Misc"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>

<%@page import="java.text.DateFormat"%>
<%@page import="com.implex.database.map.services.*"%>
<%@page import="com.implex.database.map.*"%>
<%@page import="com.sideinternational.sas.configuration.Configuration"%>
<%@page import="com.implex.database.PersistantObject"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="oracle.jsp.parse.Include"%>
<%@page import="Support.UserUnCommitedTransactions"%>
<%@page import="com.smartValue.UnCommittedDbTransaction"%><html>

<head>
	<%String appUrl = Support.Misc.getAppURL(request) ;%>
	<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=appUrl%>/includes/HijriCalender/lib/jquery.calendars.picker.css">
	<script type="text/javascript" src="<%=appUrl%>/jQueryAssets/jquery-1.8.3.min.js"></script>

	<script type="text/javascript" src="<%=appUrl%>/jQueryAssets/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="<%=appUrl%>/jQueryAssets/jquery-ui.js"></script>
	<script type="text/javascript" src="<%=appUrl%>/jQueryAssets/jquery-ui-1.9.2.tabs.custom.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="<%=appUrl%>/includes/smartTool.js"></script>
	
	<link href="<%=appUrl%>/jQueryAssets/jquery.ui.core.min.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="<%=appUrl%>/jQueryAssets/jquery-ui.css">
	<link rel="stylesheet" href="<%=appUrl%>/includes/smartTool.css">
	<link href="<%=appUrl%>/jQueryAssets/jquery.ui.theme.min.css" rel="stylesheet" type="text/css">
	<link href="<%=appUrl%>/jQueryAssets/jquery.ui.tabs.min.css" rel="stylesheet" type="text/css">
	
	<link rel="stylesheet" href="<%=appUrl%>/jQueryAssets/jquery-ui.css">
	<script src="<%=appUrl%>/jQueryAssets/jquery-ui.js"></script>

	<script>
		  $(function() {
		    $( "#sysMessage" ).dialog(
		    { position: { my: "center top", at: "center top", of: window  } },
		    {
		      autoOpen: false,
		      modal : true , 
		      width : 800 ,
		      show: { effect: "explode", duration: 500  },
		      hide: { effect: "explode", duration: 500  },
		      buttons: {
        		Ok: function() {
          				$( this ).dialog( "close" );}
          				}, 
		      close : function () {
		      history.go(0);  
		      $( this ).dialog( "close" );}
		      
		    });
		    
		  });
		</script>
		<%@ include file="includes/jquery_commons.js"%>
	<title>تعديل بيانات</title>
	<style type="text/css">
		body {	background-color: #F0E8E8; }
	</style>
	<script language=javascript>
	   function confirmDelete()
	   {
	     if (confirm("Are you sure you want to Commit Your Transaction(s)?")) 
	        {return true;} 
		 else {return false;}
	    }
	 </script>
  <script type="text/javascript">
		   $(function() {
			    $( "#objectEditorTabs" ).tabs(

					    {
			      beforeLoad: function( event, ui ) {
			    	ui.panel.html("<img width = 20px Hieght = 20px  src = \"images/ajaxLoading.gif\" ></img> Please Wait ...") ; 
			        ui.jqXHR.error(function() {
			          ui.panel.html(
			            "Couldn't load this tab. Please Contact IT Department We'll try to fix this as soon as possible. " );
			        });
			      }
			    }
					    );
			    
			    $( "#objectEditorTabs" ).tabs({ active: 0 }); 
			  });
	</script>

</head>
<body>
<script type="text/javascript" src="<%=appUrl%>/includes/AJAX_new.js"></script>
<jsp:include page="/includes/serverMessagesList.jsp"></jsp:include>
<div id="sysMessage" title="System Message" dir="ltr"></div>
<%  
SecUsrDta  loggedUserObj = Support.Misc.getLoggedUserFromSession(session);
boolean smartToolAdmin = loggedUserObj.isSmartToolAdmin();
if (loggedUserObj.isUsrLangArabic())
{
	%><link rel="stylesheet" href="<%=appUrl%>/includes/smartTool_ar.css"> <%
}
String owner = request.getParameter("owner");
String tableName = request.getParameter("tableName");
int dotIndex =  tableName.indexOf(".");
String tableowner = tableName.substring(0, dotIndex ).trim() ;
String tableNameonly = tableName.substring(dotIndex +1 ) ;
String rowIdValue =  request.getParameter("ROWID") ; 
String commentlListQueryId = "36166" ; 
String auditQueryId = "69240" ;
String udvQueryId = "70882" ; 

%>
<h3 align="center">Data Editor تعديل بيانات</h3>
 
<%
  boolean printable = request.getParameter("printable") != null ; 
  String updateButtonSuffix = "_XXXX";
  java.sql.Connection  con =null;
  boolean execAgainstRep = (request.getParameter("execAgainstRep") != null && request.getParameter("execAgainstRep").toString().equals("Y") );
  java.sql.Connection repCon = (java.sql.Connection)session.getAttribute("repCon");
  if (execAgainstRep)
  {
  con = repCon ; //(java.sql.Connection)session.getAttribute("repCon");
  }
  else
  {con = (java.sql.Connection)session.getAttribute("con");}
  if (con == null)
  {response.sendRedirect("loginScreen.jsp?comeFrom=tableEditor.jsp");}
  java.sql.Statement stmt = con.createStatement();  
  if (dotIndex==-1) {throw new Exception ("Table Name in Your query does not include owner name..it should be in the form of 'OWNER.TABLE_NAME'. please specify");}
  TableMaintMaster tmm = Support.Misc.getTableMaintMaster(session , tableowner , tableNameonly) ;

  String uniqueColumnName = "rowId";
  String uniqueWhereClauseVarValue = "" ; 
  String uniqueWhereClauseVarName = "uniqueWhereClause" ; 
  
  if( con.getMetaData().getDatabaseProductName().equals("Microsoft SQL Server") )
  {
	  out.print(con.getMetaData().getDatabaseProductName());
	  uniqueColumnName = "ID";
  }
  boolean useRowIdValue = rowIdValue != null ;

  
  if ( useRowIdValue ) 
  {
	  String uniqueColumnValue = "'" + rowIdValue +"'";
	  uniqueWhereClauseVarValue = "t."+uniqueColumnName+" = " + uniqueColumnValue ; 
  }
  else 
  {
	  uniqueWhereClauseVarValue = request.getParameter(uniqueWhereClauseVarName) ;  
  }
 
  //-------------------------------------------
  
  String queryStatment = "select t.* , t.rowid  "
  		  + " , Support.Misc.get_object_unique_where_clause('"+tableowner+"' , '"+tableNameonly+"' , '"+rowIdValue+"'  ) UWC" // Unique Where Clause
		  +" from " + tableName  + " t " + "\n" + " Where " +uniqueWhereClauseVarValue ; 
  java.sql.ResultSet rs= null;
  try{
  rs =  stmt.executeQuery(queryStatment);
  }
  catch (Exception  e) {throw new Exception("Unable to execute : \n" + queryStatment + "\n Due to : \n" + e.getMessage());}
  boolean objectExist =  rs.next();
  boolean objectForOtherCompany = false ; 
  String objectUniqueWhereClaue = "Unable to Get" ; 
  if (objectExist )  
  {		// Check if the Object Belong to the loggedUser Company or not 
	  try {
	  			String objectCompany = rs.getString("COMPANY_ID") ;
	  			String loggedUserCompany = loggedUserObj.getUsrCmpIdValue().toString() ; 
	  			objectForOtherCompany = !objectCompany.equalsIgnoreCase(loggedUserCompany);
	  			
  		  }
  		catch ( Exception e ){}
  
	  if (smartToolAdmin) {  
	  objectUniqueWhereClaue = rs.getString("UWC") ; 
	  out.print("UiqueWhereClause : " + objectUniqueWhereClaue  ) ;
	  }
  }
  if ( !objectExist || ( objectForOtherCompany && !loggedUserObj.getUsrNameValue().equalsIgnoreCase("JCCS")) ) 
  {
	  
	  %>
	  <p align="center"> 
	  	<font color="Red" size="4"> عفواً هذا العنصر (<%=tmm.getObjectName_Value()%>)   غير موجود - قد يكون قد تم حذفه او ليس لك صلاحية لعرضه او تعديله 
	  		<br>Object Belongs to Other Company 
	  	</font>
	  	<!--  <br> <%=queryStatment%>  -->
	  </p>
	  <%
	  return ; 
  }
  if (objectForOtherCompany)
  { %>
	  	<font color="blue" size="4">  Warnning : This Object Belongs to Other Company 	</font>
	<%
  }
  ResultSetMetaData rsmd = rs.getMetaData();
  int columnCount = rsmd.getColumnCount();
  String tableObjectName = (loggedUserObj.isUsrLangEnglish())? "Data Type : "+ tmm.getObjectNameValue() :"النوع: "+ tmm.getObjectName_Value() ; 
  
  
  if ( smartToolAdmin ) 
  {
	%><p align="center"><strong><%=tableObjectName %> ( Table Name is</strong> <%=tableName%>) </p> <% 
  } 
  else 
  {
	  %>  <p align="center"><strong><%=tableObjectName%> </p> <%  
  }
  String requestQueryString = request.getQueryString() ; 
  String requestQueryStringWithoutCommit = requestQueryString ;

%>
<form name="form1" method="post" action="tableEditor.jsp?<%= requestQueryStringWithoutCommit%>">
	<input type="hidden" name="execAgainstRep" value='<%=((execAgainstRep)? "Y" : "N" )%>'>
	<table width="493"  border="1" align="center" dir="<%=loggedUserObj.getDisplayDirection()%>">
		<tr>
			<td colspan="3">
			<div align="center">
			 <a href = "?tableName=<%=tableName%>&ROWID=<%=URLEncoder.encode(rowIdValue)%>&<%=uniqueWhereClauseVarName%>=<%=URLEncoder.encode(uniqueWhereClauseVarValue) %>"  >
				<img name="refreshAll" src="<%=appUrl%>/images/toolbar_icon_reload_active.gif" title="Reload Data تحديث ">
			 </a>
			 
			  <jsp:include page = "/includes/dbTransc.html" ></jsp:include>
			  <script>
			  	function deleteObject()
			  	{ sendAjaxRequestToJsp('/SmartTool/deleteObject.jsp?tableName='+'<%=tableName%>'+'&ROWID='+'<%=URLEncoder.encode(rowIdValue)%>' , 'objectEditorTabs') ;	  	}
			  </script>	 	
		     <a href = "javascript:deleteObject()"  id="deleteLink"> 
		     	<img src="/SmartTool/images/deleterec.gif"  
		     	title="Delete This <%=tmm.getObjectName_Value()%> حذف هذا ال<%=tmm.getObjectNameValue()%>  "
				onClick="return confirm('Are you sure you want to Delete This Record? هل انت متأكد سوف يتم حذف هذا السجل ؟');"
				width="25" height="25" ></img>
			</a>
			
			  
			<% if (smartToolAdmin) { %>
			 <input type="submit" name="toggleDesign" value="<%=(request.getParameter("toggleDesign")!= null && request.getParameter("toggleDesign").equals ("Show Design") )? "Hid Design": "Show Design" %>" >
		    <%} %>
	       
	         </div>
			</td>
		</tr>
</table>
</form>
    <% String rowidUrlEncoded =  URLEncoder.encode(rowIdValue); 	
       String beforeEditorPage = (String) tmm.getAttributeValue("BEFORE_EDITOR_PAGE") ; // "renderQueryResult.jsp?id=33880&queryIndex=1&printable=true"
    %>
	<%
		if (beforeEditorPage != null)
		{ 
			try {
			%>	
			<div id="<%=tableowner%>.<%=tableName%>.ParentPage" align="right">
				<jsp:include page="<%=beforeEditorPage%>" > 
					<jsp:param name="objectRowId" value="<%=rowidUrlEncoded%>"  />
					<jsp:param name="schma_table" value="<%=tableName%>"  />
					<jsp:param name="schema" value="<%=tableowner%>"  />
				</jsp:include>
			</div>
			<%
			}
			catch ( Exception e) {out.print("Unable to Include Page " + beforeEditorPage + " due to " + e.getMessage()) ; }
		}
	%>
<div id="objectEditorTabs" >
  <ul>
    <li><a href="#MasterData">البيانات الاساسية</a></li>
    <li><a href="queryResultWithEditableRS.jsp?id=<%=auditQueryId %>&schema=<%=tableowner%>&schma_table=<%=tableNameonly%>&objectRowId=<%=rowidUrlEncoded%>&printable=True&execludeJqScripts=Y">المراجعة</a></li>
    <li><a href="queryResultWithEditableRS.jsp?id=<%=commentlListQueryId %>&table_owner_com=<%=tableowner%>&table_name_com=<%=tableNameonly%>&_SelectedOraRowId=<%=rowidUrlEncoded%>&printable=True&execludeJqScripts=Y">التعليقات</a></li>
    <li><a href="queryResultWithEditableRS.jsp?id=34650&table_owner=<%=tableowner%>&tableName=LU_QUERIES&table_name=<%=tableNameonly%>&_SelectedOraRowId=<%=rowidUrlEncoded%>&printable=True&execludeJqScripts=Y">إعتماد البيانات</a></li>
    <li><a href="/SmartTool/objectAttachmentsFrame.jsp?dirPath=DB_Attachs/<%=tableowner%>/<%=tableNameonly%>/<%=rowidUrlEncoded%>&_SelectedOraRowId=<%=rowidUrlEncoded%>" >الملحقات</a></li>
    <li><a href="queryResultWithEditableRS.jsp?id=<%=udvQueryId %>&schema=<%=tableowner%>&schma_table=<%=tableNameonly%>&objectRowId=<%=rowidUrlEncoded%>&printable=True&execludeJqScripts=Y">User Defined Variables</a></li>
    
  </ul>
  
 <div id = "MasterData">  

<table width="100%" border="0" align="Center" dir="<%=loggedUserObj.getDisplayDirection()%>"  <%= (tableNameonly.equalsIgnoreCase("TABLE_MAINT_DETAILS"))? "bgcolor=\"#CC3311\"" : "" %>  >
		<%
	    boolean synchWithDb = request.getParameter("synchWithDb") != null ; 
	    boolean genAuditColumns = request.getParameter("genAuditColumns") != null ;
	    if (genAuditColumns) 
	    {  
	        tmm.createAuditColumns(); 
	    	com.implex.database.BatchExecuteResult result = tmm.getCreateAuditColumnsExecResult();
	    	out.println(result.getExceptions());
	    } 
	    if (synchWithDb) tmm.synchronizeWithDb();
	    tmm.save();	    // this method internally checks  if the object is new it should be saved ..
	    tmm.getTableMaintDetailss().restore();
        ArrayList<PersistantObject> tmds = tmm.getTableMaintDetailss().getPersistantObjectList();
        int count =0 ;
    	request.setAttribute("rs" , rs);
        for (PersistantObject po : tmds)
          {  count++;
        	 TableMaintDetails tmd = (TableMaintDetails) po ;
         	 request.setAttribute("tmd" , tmd);
			 String divID = "'sysMsgDivIdFor_"+tmd.getColumnNameValue()+"'" ; 

          	 if (tmd.getIncludedInEdit().getBooleanValue())
          	 { String columnNameValue = tmd.getColumnNameValue() ; 
          	 	String jsTableRowid = columnNameValue+"_RowID" ; 
         	  %><tr id = '<%=jsTableRowid%>' >
        		  	<td width="119">&nbsp;</td>
       			  	<td width="191" title="<%=tmd.getCommentsValue()%>">
        				  <input 	type="hidden" name="<%=tmd.getColumnNameValue()+"columnType"%>" 	value="<%=tmd.getHtmlTypeValue()%>">
        				  <strong><h3><%=tmd.getDisplayNameValue()%> </h3></strong>
        			</td>
        			<td width="344" dir= "rtl">
        				<div align="right">
         	   
         	  			<jsp:include page="includes/renderTmd.jsp" >
         	  				<jsp:param name="divID" value="<%=divID%>"></jsp:param>
         	  			</jsp:include>   	 
         	  
         	  			</div>
        			</td>
        			<td><%=(tmd.getCommentsValue()== null)? "" : tmd.getCommentsValue() %> </td>
        		</tr>
         	  <% 
          	 }
          }
          stmt.close();
          rs.close();
          %>

</table>


<p>&nbsp;</p>

<p align="center">&nbsp;
</p>

<% if (tableName.equalsIgnoreCase("CARRENT.CAR") )
{ 
	
 %>  <%@ include file="Company/Car_info.htm"%> <% 
} 
%>
<p align="center">&nbsp;</p>
<p align="center">&nbsp;</p>
<p align="center">&nbsp;</p>
<p align="center">&nbsp;</p>
<p align="center"><a href = tableEditor.jsp?<%=request.getQueryString()+ "&printable" %> >Printable Version</a>
</p>
</div>
<p align="center"> <a href = 'javascript: closeWindow(); ' ><img title="إغلاق-Close" src="/SmartTool/images/exit.png" width="24"></a> </p>	
	
</div>

<%
		String afterEditorPage = (String) tmm.getAttributeValue("AFTER_EDITOR_PAGE") ; // "renderQueryResult.jsp?id=33880&queryIndex=1&printable=true" 
		if (afterEditorPage != null)
		{try {
			%>	
			<div id="<%=tableowner%>.<%=tableName%>.ParentPage" align="right">
				<jsp:include page="<%=afterEditorPage%>" >
					<jsp:param name="objectRowId" value="<%=rowidUrlEncoded%>"  />
					<jsp:param name="schma_table" value="<%=tableName%>"  />
					<jsp:param name="schema" value="<%=tableowner%>"  />
				</jsp:include>
			</div>
			<%
			} catch ( Exception e ) {out.print("Unable to include page " + afterEditorPage + "Due to " + e.getMessage() ) ; }
		}
	%>
<% 	if ( smartToolAdmin ) 
	{
		%>
			<% if (beforeEditorPage != null){ %><a target = "Before Editor Page" href="<%=((String)beforeEditorPage).replaceAll("renderQueryResult", "editAndExecute")%>&schema=<%=tableowner%>&schma_table=<%=tableNameonly%>&objectRowId=<%=URLEncoder.encode(rowIdValue)%>&printable=True">Maintain Before Editor Page</a><br><%}%> 
			<a target = "Approval Cycle Current State " href = "editAndExecute.jsp?id=34650&table_owner=<%=tableowner%>&table_name=<%=tableNameonly%>&_SelectedOraRowId=<%=URLEncoder.encode(rowIdValue) %>" > Maintain Approval Cycle</a>
			<br> <a target = "Maintain Report" href = "editAndExecute.jsp?id=<%=commentlListQueryId%>&table_owner_com=<%=tableowner%>&table_name_com=<%=tableNameonly%>&_SelectedOraRowId=<%=URLEncoder.encode(rowIdValue) %>">Maintain Comments Data</a>
			<br> <a target = "Audit Report" href="editAndExecute.jsp?id=<%=auditQueryId %>&schema=<%=tableowner%>&schma_table=<%=tableNameonly%>&objectRowId=<%=URLEncoder.encode(rowIdValue)%>&printable=True">Maintain Audit Report</a>
			<% if (afterEditorPage != null){ %><br><a target = "After Editor Page" href="<%=((String)afterEditorPage).replaceAll("renderQueryResult", "editAndExecute") %>&schema=<%=tableowner%>&schma_table=<%=tableNameonly%>&objectRowId=<%=URLEncoder.encode(rowIdValue)%>&printable=True">Maintain After Editor Page</a><%} %>						
			
			<p><a href = "tableEditor.jsp?<%=request.getQueryString()%>&synchWithDb">Synchronize System Metadata With DB metadata </a>
			.... <a href = "tableEditor.jsp?<%=request.getQueryString()%>&genAuditColumns">Create Audit Columns</a> 
			....<a target = "EditFormMaint" href = "queryResultWithEditableRS.jsp?id=22264&owner=<%=tableowner%>&table_Name=<%=tableNameonly%>" ><small>Form Maintainance</small></a>
			....<a href = "editAndExecute.jsp?id=44755&owner=<%=tableowner%>&table_Name=<%=tableNameonly%>" ><small>Email Notification Rules</small></a></p>
			
		<% 
	} %>
</body>
</html>
