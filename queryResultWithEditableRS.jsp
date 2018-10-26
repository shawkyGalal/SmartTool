<%@page import="Support.*, java.util.*, java.io.* , java.sql.* , java.text.SimpleDateFormat , javax.faces.model.SelectItem "%>
<%@page import="Support.event.resource.system.*"%>
<%@page import="com.implex.database.map.services.*"%>
<%@page import="com.implex.database.map.*"%>
<%@page import="com.sideinternational.sas.configuration.Configuration"%>
<%@page import="com.implex.database.PersistantObject"%>
<%@page import="com.smartValue.support.map.LuQueryDetails"%>
<%@page errorPage="errorPage.jsp"%>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>

<HTML>
<head>
<% String appURL = Misc.getAppURL(request);
 String requestQueryString = request.getQueryString() ; 
 String requestQueryStringWithoutCommit = requestQueryString ;
 requestQueryStringWithoutCommit = Support.Misc.repalceAll(requestQueryStringWithoutCommit , "Commit=Y&" , "");
 requestQueryStringWithoutCommit = Support.Misc.repalceAll(requestQueryStringWithoutCommit , "Commit=N&" , "");
 requestQueryStringWithoutCommit = Support.Misc.repalceAll(requestQueryStringWithoutCommit , "RollBack=Y&" , "");
 requestQueryStringWithoutCommit = Support.Misc.repalceAll(requestQueryStringWithoutCommit , "RollBack=N&" , "");
 SecUsrDta loggedUserDta  = Misc.getLoggedUserFromSession(session);
%>
	<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
	<script type="text/javascript" src="includes/AJAX_new.js"></script>
	<script type="text/javascript" src="includes/resizable-tables.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=appURL%>/includes/HijriCalender/lib/jquery.calendars.picker.css">
	<script type="text/javascript" src="<%=appURL%>/jQueryAssets/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=appURL%>/includes/HijriCalender/lib/jquery.calendars.js"></script>
	<script type="text/javascript" src="<%=appURL%>/includes/HijriCalender/lib/jquery.calendars.plus.js"></script>
	<script type="text/javascript" src="<%=appURL%>/includes/HijriCalender/lib/jquery.calendars.picker.js"></script>
	<script type="text/javascript" src="<%=appURL%>/includes/HijriCalender/lib/jquery.calendars.picker-ar.js"></script>
	<script type="text/javascript" src="<%=appURL%>/includes/HijriCalender/lib/jquery.calendars.ummalqura.js"></script>
	<script type="text/javascript" src="<%=appURL%>/includes/HijriCalender/lib/jquery.calendars.ummalqura-ar.js"></script>
	<script type="text/javascript" src="<%=appURL%>/includes/HijriCalender/hijriCalender.js"></script>
	<link rel="stylesheet" href="<%=appURL%>/jQueryAssets/jquery-ui.css">
	<link rel="stylesheet" href="<%=appURL%>/includes/smartTool.css">
	<% if ( request.getParameter("execludeJqScripts") == null ) { %>
	<script src="<%=appURL%>/jQueryAssets/jquery-1.10.2.js"></script>
	<script src="<%=appURL%>/jQueryAssets/jquery-ui.js"></script>
	<% } %>
	<%@ include file="includes/jquery_commons.js"%>
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
		<script type="text/javascript">
			$(window).load(function() {
				 $("#spinner").fadeOut("slow");
			});
		</script>
</head>
<title>Query result</title>
<BODY bgcolor="#FFEEFF"  TEXT="blue" LINK="red" dir="<%=loggedUserDta.getDisplayDirection()%>">
<!-- 
<div id="spinner"  style=" position: fixed; left: 0px;	top: 0px; width: 100%;	height: 100%;	z-index: 9999;
		background: url('images/ajaxLoading.gif') 50% 50% no-repeat rgb(249,249,249) "> Please Wait....
	</div>
-->
	
<div id="sysMessage" title="System Message" dir="rtl"></div>


<%
	boolean AutoLog  = ( request.getParameter("AutoLog") != null ) ; //----To Enable automatically read page contents 
  //-------------The follwoing Connection Used to get The Queries Where it may reside in a defrent DB
  java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");
  if( ( repCon == null || repCon.isClosed()) && AutoLog )
  { 
    XMLConfigFileReader aa =  Misc.getXMLConfigFileReader(false ) ; // new XMLConfigFileReader( false);
    repCon = aa.reposatoryConn.generateConnection();
    session.setAttribute("repCon",repCon);
  }
  java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
  if (con == null || con.isClosed())
  {
    if( AutoLog)
    { 
     //--TO be used at Normal Production ---
     XMLConfigFileReader aa =  Misc.getXMLConfigFileReader(false ) ; 
     //Misc abc = new Misc(repCon);
     String autologinDB =SysConfigParams.getAutoLogin_Environment_Name();// abc.getSystemParameterValue(8);
     String autoLoginUserName = SysConfigParams.getAutoLogin_Username();// abc.getSystemParameterValue(9);
     String autoLoginPassword = SysConfigParams.getAutoLogin_Password();// abc.getSystemParameterValue(10);
     
     con = aa.getConnParmsByName(autologinDB).generateConnection(autoLoginUserName, autoLoginPassword , "NORMAL");
     session.setAttribute("con",con);
     }
    else { session.setAttribute("queryString",requestQueryStringWithoutCommit);
          response.sendRedirect("loginScreen.jsp");
         }
  }
	//-----------if RollBack Pressed------------------------------
	if (request.getParameter("RollBack") != null && request.getParameter("RollBack").equals("Y"))
	{ System.out.print("Will execute Commit"); 
	  Support.UserUnCommitedTransactions.rollBackConnection(con , session ) ; 
	  repCon.setAutoCommit(false);
	  repCon.rollback();
		%>
		 	<script> 
		 	  $(function() { $( "#rollBackDialog" ).dialog({modal: true}, { position: { my: "center top", at: "center top", of: window  } });  }); 
		 	</script>
		 	
		 	<div id="rollBackDialog" title="إلغاء التعديلات ">
				<p> Transaction(s) Rolled Back </p><p> تم الغاء جميع التعديلات بنجاح  </p>
			</div>
		<%

	}
	
	//-----------if Commit Pressed------------------------------
	if (request.getParameter("Commit") != null && request.getParameter("Commit").equals("Y"))
	{
		UserUnCommitedTransactions.commitConnection(con , session ) ; 
	  //con.commit();
	  repCon.setAutoCommit(false) ;
	  repCon.commit();

	  %>
	 	<script> 
	 	  $(function() { $( "#commitDialog" ).dialog({modal: true}, { position: { my: "center top", at: "center top", of: window  } });  }); 
	 	</script>
	 	
	 	<div id="commitDialog" title="حفظ  التعديلات ">
			<p> Transaction(s) Commited  </p><p>  تم حفظ جميع التعديلات بنجاح </p>
		</div>
	
		<%
	  
	}

   boolean printable = ( request.getParameter("printable") != null  && request.getParameter("printable").equalsIgnoreCase("true") ); 

   
	String printableLink = requestQueryStringWithoutCommit ;
	String printableStr = "&printable=" ;
	if (printableLink != null )
	{
	if ( printableLink.indexOf(printableStr + printable) == -1 ) printableLink = printableLink + printableStr + printable ;
	printableLink = Misc.repalceAll(printableLink , printableStr + printable , printableStr +! printable );
	}

   Support.ConnParms selectedConnParms = (Support.ConnParms)session.getAttribute("selectedConnParms"); 
  
   Support.SqlReader sqlReader = null;
     
  String id = request.getParameter("id");
  if ( id != null)  
  {
      String QueryTableName = request.getParameter("tableName");
      if (QueryTableName != null) // QueryTableName is sent from the tree
      {
        session.setAttribute("QueryTableName",QueryTableName);
      }
      else  //---QueryTableName is not sent
      {
       QueryTableName = "LU_QUERIES";//session.getAttribute("QueryTableName").toString();
      }
%>
<% if (! printable) 
{ %>
<div align="center">
<table width="100%" border="0" align="left">
	<tr>
		<td width="40%">
			<%
		    Support.LookupTreeV10 mainQueriesTree = (Support.LookupTreeV10) session.getAttribute("queriesTree"); 
			if (mainQueriesTree == null) 
			{	Misc.initializeMainSystemTrees(request, response, session, application , out);
				mainQueriesTree = (Support.LookupTreeV10) session.getAttribute("queriesTree"); 
			} 
			String pathLinks = "";
			pathLinks = mainQueriesTree.getPathLinks(id) ; 
			%>
			<%= pathLinks %>
		 </td>
		<td width="50%">
		
			<div align="center">
			  	 	<jsp:include page = "/includes/dbTransc.html" ></jsp:include>
	
			 <a href = "?<%=requestQueryStringWithoutCommit%>" title = "Reload Data إعادة التحميل"  ><img src="images/toolbar_icon_reload_active.gif" width="30"></img></a>
			 <a href = "index.jsp?reportTopOpen=<%=id%>" target = top title="الصفحة الرئيسية"><img src="images/homeIcon.jpg" width="30"></a>
			 <a href = "?<%=printableLink%>" title = "نسخة قابلة للطباعة Printable Version" target = "Printable"><img src="images/cleardot.jpg" width="30" ></a>
			 <a href = "queryResult.jsp?<%=printableLink %>" title="Read only Version نسخة غير قابلة للتعديل"><img src="images/readonly.jpg" width="30" ></a>
			 <img  title="تغيير إتجاه الجدول" src="images/direction.jpg" 
			       onclick="
					        for (i =0 ; i< 8 ; i++ ) 
					        {
					       document.getElementById('resSetTable<%=id%>_'+i).dir = 'rtl' ;
					       document.getElementById('resSetTable<%=id%>_'+i).align = 'right' ;
					       document.getElementById('titleDiv<%=id%>_'+i).dir = 'rtl' ; 
					       }
				        " 
			       width="30" ></img>
			</em></font>
			</div>
		</td>
	</tr>
</table>
</div>
<% } %>
<%
          	// -----------Checking Authority--------------------
          	  if (! AutoLog)
                  {
                  Support.LookupTreeV10 queriesTree = null;
          	  queriesTree = (Support.LookupTreeV10)session.getAttribute("queriesTree");
          	  Support.QueryTreeSecurity querySecurity = new Support.QueryTreeSecurity(queriesTree, con);
          	  querySecurity.checkAuthority("LU_QUERIES",Integer.parseInt(id), Support.QueryTreeSecurity.EXECUTE);  
                  }
          	  //-----End Of checking Authority---------------
          	  sqlReader = new Support.SqlReader(repCon,QueryTableName , "QUERY_BODY",  id, session , request); 
                          
                  if ( sqlReader.active.equals("F"))
                   {
                    throw new Exception ("Sorry : This Report is currently disabled, Please contact Application Support");
                   }
          	  }
          		  
          	  String[] queryStmt = sqlReader.getQueryStatments();
          	  String[] titles = sqlReader.getQueryTitles();
          	  int noOfQueries = sqlReader.getNoOfQueries();
          	  
          	  session.setAttribute("queryStmt",queryStmt);
          	  //----------------Excecuete The Query ---------------------
          	  Statement stmt = null;
                  boolean execAgainstRep = (request.getParameter("execAgainstRep") != null);
                  if (execAgainstRep)
                       { stmt = repCon.createStatement();}
                  else { stmt = con.createStatement(); }
                  
          	  ResultSet rs = null;
          	  ResultSetMetaData rsmd = null;
          	  int columnCount = 0;
          	  String[] columnTypes = null;
                  String[] columnNames = null;
          	  java.math.BigDecimal[] columnSums = null;
          	  boolean [] isNumericColumn ; 
           	  TableMaintDetails[] tmds = null ; 
          	  
  
          	  for (int queryIndex = 0 ; queryIndex<noOfQueries ; queryIndex++)
          	  { 
          	  	if (!queryStmt[queryIndex].trim().equals(""))
          		  { //--------if order by is required for the current Query statement ------------

            	    String queryIndexToFilterByRowId  = request.getParameter(SqlReader.QUERY_INDEX_TO_FILTER_BY_ROWID) ;
          		  	boolean filterByRowId = request.getParameter("filterByRowId")!=null;
            	    Integer queryIndexToFilterByRowIdInt = null; 
        	    	try{queryIndexToFilterByRowIdInt = Integer.parseInt(queryIndexToFilterByRowId);} 
        	    	catch(Exception e ) {}
        	    	boolean skipQuery = filterByRowId && queryIndexToFilterByRowIdInt != null && queryIndexToFilterByRowIdInt != queryIndex ; 
        	    	if (skipQuery ){continue;}
          			String orderByClause= "";
                      if (request.getParameter("_QuerySeq") != null && Integer.parseInt(request.getParameter("_QuerySeq").toString()) == queryIndex  )
                      {
                          //-------if Query already contains order by, remove it 
                        int orderByIndex = queryStmt[queryIndex].toLowerCase().indexOf("order by ") ;
                        if ( orderByIndex!= -1 )
                        { queryStmt[queryIndex] = queryStmt[queryIndex].substring(0,orderByIndex ); }
            
                        if (request.getParameter("_OrderAscBy")!= null)
                        {
                         orderByClause = "Order By " + request.getParameter("_OrderAscBy") + " Asc ";
                        }
                        if (request.getParameter("_OrderDescBy")!= null)
                        {
                        orderByClause = "Order By " + request.getParameter("_OrderDescBy") + " Desc ";
                        }
                      }
                     //------End of if order is required------------
                    java.util.Date startTime = new java.util.Date(); 
                    String qs = queryStmt[queryIndex]+ "\n" + orderByClause;
                    
            	    // --------if Extra filtering for a specific Oracle RowId is REquired-----
            	    String selectedOraRowId = request.getParameter(SqlReader.SELECTED_ORA_ROWID) ;
        	    
                   try
                   {
	       			  rs = stmt.executeQuery(qs);
                   }
                   catch (SQLException sqle)
                   {  stmt.close(); 
                     throw new Support.MyException("Unable to Execute the following Query <Br>" + qs+ "\n" + orderByClause + " <Br> Due To the Following SQL Error " + sqle.getMessage(), sqle, 1, session.getServletContext().getRealPath(""));
                   }

                    //----------------Logging The Action in today actions Log file 
                    if (false) //disabled where it causes issues, morever I have logged the action in DB
                    {
                      try{
                          //auditLogger.logMessage("User : " + con.getMetaData().getUserName() + " Executed "+ "\n" + queryStmt[j] );
                          //auditLogger.close();
                          }
                      catch (Exception e) {}
                    }

                    rsmd = rs.getMetaData();
          			columnCount = rsmd.getColumnCount();
          			columnTypes = new String[columnCount+1];
                    columnNames = new String[columnCount+1];
          			columnSums = new java.math.BigDecimal[columnCount+1];
          			isNumericColumn = new boolean [columnCount+1];
          			tmds = new TableMaintDetails[columnCount+1] ; 
          			
          			for (int count = 1 ; count< columnCount+1 ; count++ )
          			 { 
          			   columnSums[count] = new java.math.BigDecimal("0");
          			 }
              
          		String rowIdValue = null;
          		String rowIdValueUrlEncoded = "";
          		String rowidColumnName = "ROWID";
          		String tableName ="";
          		String tableOwner="";

          		int rowIdIndex = sqlReader.getRowIDIndex(rsmd , rowidColumnName) ;
          		boolean rowIdFound = (rowIdIndex != 0);
          		TableMaintMaster tmm = null ;
          		String genericObjectEditorPage = "tableEditor.jsp" ;
          		String tableObjectEditorPage = "";
          		boolean hasSpecicEditor = false ;
          		 
                if (rowIdFound) //----------If its Found .....
                {
                   tableName = sqlReader.estimateTableName(queryIndex);
                   tableOwner = sqlReader.estimateTableOwner(queryIndex);
					tmm = Support.Misc.getTableMaintMaster(session , tableOwner , tableName ) ;
					//out.print(tmm) ; 
			  	    tmm.save(); // this method internally checks  if the object is new it should be saved ..
			  	    tmm.getTableMaintDetailss().restore();
			  	    String specificObjectEditor = tmm.getObjectEditorJspPageValue() ;
			  	    hasSpecicEditor = !genericObjectEditorPage.equalsIgnoreCase(specificObjectEditor)  ;
			  	    tableObjectEditorPage =  (hasSpecicEditor)? specificObjectEditor : genericObjectEditorPage ;
					tableObjectEditorPage = Misc.repalceAll(tableObjectEditorPage , "$$company_id" , loggedUserDta.getUsrCmpIdValue())  ; 
                }
     %>
<br>
<strong><br>
<div id="titleDiv<%=id%>_<%=queryIndex%>" ><%=titles[queryIndex]%> 
<%=(rowIdFound && !printable)?"(<a href=TableInsertForm.jsp?"+( (requestQueryStringWithoutCommit!= null)? requestQueryStringWithoutCommit.toUpperCase() : "" ) +"&owner="+tableOwner+"&tableName="+tableName+" target =new><img border=0 src=images/toolplus.gif width=14 height=14 title='Add New "+tableName+" Entry'></a>)":""%>
<%=(rowIdFound && !printable)?"(<a href=XLSUploading/getXLSClientFile.jsp?dirPath=XLSUploading/Data&uploadToTableOwner="+tableOwner.toUpperCase()+"&uploadToTableName="+tableName.toUpperCase()+" target =new><img border=0 src=images/icxls.gif width=14 height=14 title='Import Data from Excel تحميل بيانات من ملف اكسل'></a>)":""%>
<%=(!printable)?" <a id=opener_"+queryIndex+">More..</a>":""%>
</div>
</strong>
<% String htmlFormName = "form"+queryIndex ; %>
<form action="" name="<%=htmlFormName%>">
    
<table border=1 class="basicTable resizable" id = "resSetTable<%=id%>_<%=queryIndex %>">
	<tr>
		<%
        //-------------Rendering Header Data------------------
        if (rowIdFound & !printable){%>
		<td bgcolor="#0099CC"><strong><font color="#FFFFFF">E</font></strong></td>
		<%}
        
        String queryString = requestQueryStringWithoutCommit;
        int orderByIndexInRequest = (queryString!=null)? queryString.indexOf("_Order") : -1 ;
        if (queryString != null && orderByIndexInRequest != -1 )
        {
         queryString= queryString.substring(0,orderByIndexInRequest-1);
        }
        String columnDisplayName  ; 
        String columnComments  ;
             
        for (int count = 1 ; count<=columnCount ; count++ )
        { 
          //----Do not show rowid column
		  
          if (rowIdFound && count == rowIdIndex)
          { continue; }
          //---End of Do not show rowid column
          
          columnTypes[count] = rsmd.getColumnTypeName(count);
          columnNames[count] =  rsmd.getColumnName(count);
          isNumericColumn[count] = columnTypes[count].equals("NUMBER")||columnTypes[count].equals("int");
          columnDisplayName = columnNames[count] ; 
          columnComments = columnDisplayName ;
          try{
          	TableMaintDetails tmd = tmm.getTmdByColumnName( columnNames[count] ) ;
          	tmds[count] = tmd ; 
           	columnDisplayName = (tmd!=null)? tmd.getDisplayNameValue() : columnNames[count] ;
           	columnComments = (tmd!=null && tmd.getCommentsValue() != null )? tmd.getCommentsValue() : columnComments ;
           }
           catch (Exception e){ columnDisplayName = Support.Misc.repalceAll(columnNames[count],"_"," ") ;} 
          %>
		<td align="center" nowrap=true bgcolor="#0099CC" title="<%=columnComments%>"><strong> <font
			color="#FFFFFF"><%=columnDisplayName%></font></strong>
		<% 
			boolean showSortingArrows = !AutoLog && !printable ; 
			if (showSortingArrows){  %> 
			<a	href="?<%=queryString%>&_OrderAscBy=<%=count%>&_QuerySeq=<%=queryIndex%>">
				<img border="0" src="images/asc.gif" width="10" height="10"></a> 
			<a	href="?<%=queryString%>&_OrderDescBy=<%=count%>&_QuerySeq=<%=queryIndex%>">
			<img border="0" src="images/desc.gif" width="10" height="10"></a> <%}%>
		</td>
		<%
         }
        %>
	</tr>
		<tr id="bulkUpdateRow_<%=queryIndex%>">
		<%
        if (rowIdFound & !printable){%>
		<td bgcolor="#0099CC"><strong><font color="#FFFFFF"></font></strong></td>
		<%}
		//------------Render Bulck Update Row ----------
	        for (int count = 1 ; count<=columnCount ; count++ )
	        { 
	          //----Do not show rowid column
	          if (rowIdFound && count == rowIdIndex)
	          { continue; }
	           	columnDisplayName = (tmds[count]!=null)? tmds[count].getDisplayNameValue() : columnNames[count] ;
	          //---End of Do not show rowid column
	          if (tmds[count] != null && ! tmds[count].getDisabled().getBooleanValue())
	          {
	        	String htmlType = tmds[count].getHtmlTypeValue() ; 
	        	boolean isTextBox = (htmlType.toUpperCase().equals("1"));
  				boolean isOptions = (htmlType.toUpperCase().equals("2"));
  				boolean isCombo = (htmlType.toUpperCase().equals("3"));
      		    boolean isTextArea = htmlType.equals("5");
      		    boolean isCheckBox = htmlType.equals("9");
      		    boolean isLabel = htmlType.equals("10");
      		    boolean isDateTime = htmlType.equals("11");
      		    boolean isHijriDate =  htmlType.equals("12");
      		    boolean isPassword =  htmlType.equals("13");
      		    boolean isTree =  htmlType.equals("14");
	          %>
				<td bgcolor="#0099CC" align="center" ><strong><font color="#FFFFFF">
				<%if (isTextBox) { %>
					<input type ="text"  name="bulckUpdateFor_<%=columnNames[count] %>"  style = "width:<%=tmds[count].getDatalengthValue() %>" >
				<%} else if (isCheckBox) {%>	
					<input type="checkbox"   name="bulckUpdateFor_<%=columnNames[count] %>"     
        		        	        onClick=" if (this.checked ) {this.value='Y';} else {this.value='N';}; " >
				<% } else if (isTextArea || isTree) {%>
				   	<textarea  name = "bulckUpdateFor_<%=columnNames[count] %>" title = "Enter New Value .. أدخل قيمة جديدة"></textarea> 
				<% } else if (isCombo) 
				{
					ArrayList<SelectItem> sis = tmds[count].getQuerySelectItems(con , null);
					%> <select  name = "bulckUpdateFor_<%=columnNames[count] %>"    ><%
					for (SelectItem si : sis)
					{
						%><option  value = "<%=si.getValue()%>"  > <%=si.getLabel() %></option> <% 
					}
					%> </select><%
				} %>
				<a href ="#" title = "تعديل الكل" 
						onclick="var userConfirm = confirm ('This Will Update Column  <%= columnDisplayName %> with new value = ' + <%=htmlFormName %>.bulckUpdateFor_<%=columnNames[count]%>.value ) ; 
								if ( userConfirm ) 
								{
									execBulkUpdate_<%=queryIndex %>( '<%=columnNames[count]%>' , <%=htmlFormName %>.bulckUpdateFor_<%=columnNames[count]%>.value) ;
								} ">Go</a>
					</font></strong>
				</td>
		      <%
	          }
	          else {%> <td bgcolor="#0099CC" ></td> <%}
	          }
	        //--------End of Render Bulck Update Row ---- 
	     %>
     </tr> 
   
     <%
        //-------rendering Data--------------------
        int rownum = -1;
        String databaseName = con.getMetaData().getDatabaseProductName();
        boolean evenRow = true;
        Support.HTMLDbTransactionBeanWOPooling db = new Support.HTMLDbTransactionBeanWOPooling();
        db.myInitialize(application,session,request,response,out,con);
        db.setDBConnection(con);

        while (rs.next())
        { rownum++;
          evenRow = !evenRow;
          
            try{ rowIdValue = rs.getString(rowIdIndex) ; }
            catch (Exception e) {}
 			if ( filterByRowId ) 
			{
				boolean isTheRequestedRow = selectedOraRowId.equalsIgnoreCase(rowIdValue) ;  
				if ( ! isTheRequestedRow ) 
				{
					continue ; 
				}
			}
	  	%><tr class = "basicTable" ><%        
		if (rowIdFound && !printable) //----------if rowid found then  render an edit cell at the begining 
          {
            rowIdValueUrlEncoded = (rowIdValue != null)? java.net.URLEncoder.encode(rowIdValue) : "Null?";
    		
            int attachsCountInt = db.getTableAttachmentCount(tableOwner , tableName , rowIdValue ) ; 
            boolean renderCount = attachsCountInt > 0 ;  

            
            String dirPath = "DB_Attachs/"+tableOwner + "/" + tableName +"/" +rowIdValueUrlEncoded ;  
            String uploadLinkHref = "getClientFile.jsp?NoDBRecord=N&"+requestQueryStringWithoutCommit+"&serverName=127.0.0.1&dirPath="+dirPath +"&queryId="+id+"&"+SqlReader.SELECTED_ORA_ROWID +"="+rowIdValueUrlEncoded+"&"+SqlReader.QUERY_INDEX_TO_FILTER_BY_ROWID + "=" +queryIndex ;
            String objApprovLinkHref = Misc.repalceAll(uploadLinkHref , "getClientFile" , "objectApprovalData" );

            %><td><strong> <a title = " Update <%=tmm.getObjectNameValue() %>.. تعديل بيانات <%=tmm.getObjectName_Value() %>"  target='Table Object Editor '
								href='<%=tableObjectEditorPage%>?<%=rowidColumnName%>=<%=rowIdValueUrlEncoded%><%=(hasSpecicEditor)? "" : "&tableName="+ tableOwner.toUpperCase()+"."+tableName %>'>
								<img alt="" src="images/editrow.gif" height="20" width="20"> 
							</a></strong>
			<% boolean showAttachlLink = tmm.getAttribute("ENABLE_ATTACHEMENTS").getBooleanValue() ; 
			 		if (showAttachlLink) 
			 		{%>
			<a title = "Add New Attachement For <%=tmm.getObjectNameValue() %> ... إضافة مستند خاص ب<%=tmm.getObjectName_Value() %>" target = "UploadFile" href = "getClientFile.jsp?NoDBRecord=N&<%=requestQueryStringWithoutCommit%>&serverName=127.0.0.1&dirPath=<%=dirPath%>
						&queryId=<%=id%>&<%=SqlReader.SELECTED_ORA_ROWID%>=<%=rowIdValueUrlEncoded%>&<%=SqlReader.QUERY_INDEX_TO_FILTER_BY_ROWID %>=<%=queryIndex%>">
				<img alt="" src="images/attach_Icon.gif" height="20" width="20"></img>
				<%= renderCount ? "(" + attachsCountInt + ")": "" %>
			</a>
			<%} %>
			<% boolean showApprovalLink = tmm.getAttribute("ENABLE_APPROVAL_CYCLE").getBooleanValue() ; 
			 if (showApprovalLink) 
			 {%>
			<a title = "Check Approvals " <%=tmm.getObjectName_Value() %>" target = "Check Approvals" href = "<%=objApprovLinkHref %>" 	>
					<img alt="" src="images/approvalCycle.jpg" height="20" width="20">
				</a>
			<%} %>
			</td>
		<%
          }
          
          String columnValue = "";
		  boolean isOracle = databaseName.equals("Oracle") ; 
          for (int count = 1 ; count<=columnCount ; count++ )
          { 
            //----Do not show rowid column
            if (rowIdFound && count == rowIdIndex)
            { continue; }
            //---End of Do not show rowid column
            columnValue = "";
            Vector columnValuePerLine  = new Vector();
            //--------------------Getting The Column Value---------------
            int maxDisplayableSize = 4000;
            try
            {
              //--------------For Oracle Database---------
              if (isOracle)
              { String columnType = columnTypes[count].toUpperCase() ;
               if ( columnType.equals("DATE") 
                 	|| columnType.equals("TIMESTAMP") 
                    || columnType.equals("NUMBER") 
                    || columnType.equals("ROWID") 
                    || columnType.equals("VARCHAR2") 
                    || columnType.equals("RAW") 
                    || columnType.equals("CHAR") 
                    || columnType.equals("LONG")
                  ) 
               { 
                columnValue= rs.getString(count);
                columnValue = (columnValue != null)? Support.Misc.repalceAll(columnValue , "\n" , "<br>"): null ;
                columnValue = (columnValue == null )? "": columnValue;
               }
               //--------------If Column Type is CLOB 0r LONG --Or a Varchar with size more than maxDisplayableSize---
               if (columnTypes[count].toUpperCase().equals("BLOB") 
            		   || columnTypes[count].toUpperCase().equals("CLOB")  
            		   //||columnTypes[count].toUpperCase().equals("LONG")
            		   || ( columnValue.length() > maxDisplayableSize && columnValue.indexOf("</a>") == -1) )
               {
                 if (rowIdFound)
                 {
                	columnValue = "<a target= CLOBReader href = "+appURL+"/oracleCLOBReader.jsp?addXmlHeader=N&execAgainstRep="+ ( (execAgainstRep)? "Y":"N") +"&columnName="+columnNames[count]+"&rowId="+rowIdValueUrlEncoded+"&tableName="+tableName+"&tableOwner="+tableOwner+">"+columnNames[count]+"</a>" ;
                 }
                 else 
                 {
                   throw new Exception ("Query Contains CLOB Column. it Should include Rowid also");
                 }
              }
            }
              //--------------For SQL SERVER Database---------
              else //if (databaseName.equals("Microsoft SQL Server")) the condition removed after using Visual FoxPro DB 
              {
                columnValue= rs.getString(count);
                columnValue = (columnValue == null )? "&nbsp;": columnValue;
              }
            
          }
          catch (Exception e) {  rs.close();   stmt.close(); throw e;}
        
          TableMaintDetails tmd = null;
          if (rowIdFound && tmm != null) 
        	{
        	  String qColumnName = rsmd.getColumnName(count) ; 
          	  tmd = tmm.getTmdByColumnName(qColumnName) ;
          	} 
          %> <td><%
		if ( tmd != null )
        { 
	          request.setAttribute("tmd" , tmd);
    	      request.setAttribute("rs" , rs);
       		String divID = "'" + queryIndex + "_" +rownum  + "_" +columnNames[count]+ "'" ;

          	%> <jsp:include page="includes/renderTmd.jsp" >
          			<jsp:param name="divID" value="<%=divID%>"></jsp:param> 
          		</jsp:include><% 
	    } 
		else
		{
			%> 
				<%=columnValue %>
			<%
		}
		
		%> </td><%


            //------------------End of Rendering The Column Value---------------
            boolean  isNumber = columnTypes[count].equals("NUMBER")||columnTypes[count].equals("int");
            boolean isHyberNumber = false;
            java.math.BigDecimal bd = new java.math.BigDecimal(0);
            if (isNumber)
            {
            	try{bd = rs.getBigDecimal(count);}catch (Exception e) {}
            }
            try
            {
            Support.HTML.AnchorTag at = new Support.HTML.AnchorTag(columnValue , "a");
            if (at.isHtmlTag("a") ) //-------if it is a hyperLinked Number 
            { 
                bd = new java.math.BigDecimal(at.getValue());
                isHyberNumber = true;
            }
            }
            catch(Exception e) {}
            if (isNumber || isHyberNumber ) //-------------if Column Type is Number 
            {
              if (bd != null)
              {
                columnSums[count]=  bd.add(columnSums[count]);
              }
            }
          }
          %>
	</tr>
	<%
        }
        %>
	<tr>
		<%
        //-------------Rendering Sum of Numeric Data------------------
		boolean rendersSums =  rownum > 0 && !filterByRowId;
		LuQueryDetails queryDetail ;
		boolean renderAggregate = true ; 
        try {
        	 queryDetail = sqlReader.getQueryDetail(queryIndex);
        	renderAggregate = queryDetail.getShowAggregate().getBooleanValue() ;}
        catch (Exception e){}
		if ( rendersSums && renderAggregate )
		{

	        if (rowIdFound && ! printable)
	        {%>
			<td><strong> </strong></td>
			<%
	        }
	        for (int count = 1 ; count<=columnCount ; count++ )
	        { 
	          //----Do not show rowid column
	          if (rowIdFound && count == rowIdIndex)
	          { continue; }
	          //---End of Do not show rowid column
	           if (!isNumericColumn[count]) 
	          {
	            %><td></td> <%
	          }	  
	          else 
	          {
		          %> <td><strong> <%=columnSums[count]%> </strong></td> <%
	          }
	        }
	      }
        %>
	</tr>
	<tr id="noDataFoundRow_<%=queryIndex%>">
		<td colspan="<%=columnCount%>" > No Data Found .... لا يوجد بيانات </td>
	</tr>
</table>
 <script>
 	$('#noDataFoundRow_<%=queryIndex%>').hide();
	if ( <%=rownum%> == -1 )
	{
 		$('#noDataFoundRow_<%=queryIndex%>').show();
	}
	if ( <%=rownum%> <= 0 )
 	{ 
 		$('#bulkUpdateRow_<%=queryIndex%>').hide();
 	}
 </script>   

</form>
<%
		db.release();
        java.util.Date endTime = new java.util.Date();
        long execTime = endTime.getTime()-startTime.getTime();
        boolean loggingRquired = sqlReader.isLogExecution();
        if (loggingRquired & ! execAgainstRep)
        {
        //--loging the execution into reposatory DB
            Statement repstmt = repCon.createStatement();
            java.text.SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat ("dd-MM-yyyy HH:mm:ss");// HH indicates Hours in 24 Format while hh indicates Hours in 12 format
            String insert = "insert into exec_stat (queryid, sequance ,  executed_by, db_url, starttime, endtime) values ";
                    insert +=   "( "+id+","+queryIndex+ ", '"+ Misc.getConnectionUserName(con) +"', '"+con.getMetaData().getURL()+"', to_date('"+dateFormatter.format(startTime)+"','dd-mm-yyyy hh24:mi:ss') , to_date('"+dateFormatter.format(endTime)+"','dd-mm-yyyy hh24:mi:ss')  )";        
            repstmt.execute(insert);
            repCon.commit();
            repstmt.close();
        //--End loging the execution into reposatory DB
        }
      %>

<%
if (! AutoLog && ! printable) 
{
	%>
	<script>
		  $(function() {
		    $( "#dialog_<%=queryIndex%>" ).dialog({
		      autoOpen: false,
			  width : 500 , 
			  modal : true , 
		      show: { effect: "blind", duration: 500   },
		      hide: { effect: "explode", duration: 500 }
		    });
		 
		    $( "#opener_<%=queryIndex%>" ).click(function() {
		      $( "#dialog_<%=queryIndex%>" ).dialog( "open" );
		    });
		  });
	</script>
	(<%=rownum + 1%>) Record(s)
	<div id="dialog_<%=queryIndex%>" title="More Options">
		<jsp:include page="renderQueryFooter.jsp">
				<jsp:param value="<%=queryIndex%>" name="queryIndex"/>
				<jsp:param value="<%=id %>" name="queryId"/>
				<jsp:param value="<%=rownum + 1%>" name="rownum"/>
				<jsp:param value="<%=execTime%>" name="execTime"/>
		</jsp:include>
	</div>
	<script type="text/javascript">
		 function execBulkUpdate_<%=queryIndex%> ( columnName ,    newValue)
		 {
			 for ( i = 0 ; i<  <%=rownum+1%> ; i++)
			 {
				 var divId = <%=queryIndex%>+"_"+i+"_"+columnName; 
				 var objectId = columnName+"_"+divId ; 
				 var object = document.getElementById(objectId) ;
				 object.onfocus() ;
				 object.value = newValue ;
				 if (object.type == "checkbox" ) 
				  { 	if (object.value == "Y") object.checked=true ;
				  		else object.checked=false ;
					   object.onclick();
				  }  
				 else  object.onchange() ;
			 }	  
		 }
	</script>
	
	<%
}
      }
      rs.close();
  	  }
    String loggedUser = Misc.getConnectionUserName(con);
    //if (! (loggedUser.equals("SHAWKY"))  &&  ( ! loggedUser.equals("SFODA") ) )
    boolean loggingRquired = sqlReader.isLogExecution();    	
    if (loggingRquired & ! execAgainstRep)    	
    {
    	String msg = "User : " + loggedUser + " Executed Query No " + request.getParameter("id") + " Title: " + titles[0];
    	//DataContext.bindThreadDataContext(DataContext.createDataContext());
    	String[] msgParms = {loggedUser , request.getParameter("id") , titles[0]};
    	new Support.event.resource.system.EventSysm1035(msgParms, null);
    }
    stmt.close();
    if( AutoLog)
    {
    con.close();
    repCon.close();
    }
  %>

<% if (! printable) {%>
<div align="center">
<p><input name="viewQuery" value="View Query" type="button"
	onClick="window.open('viewQueries.jsp','Query_Viewer','toolbar=0,menubar=0,location=0, width= 900 ,height=500 ');">
</p>
</div>
	<%  SecUsrDta  loggedUserObj = (SecUsrDta)session.getAttribute("loggedUser") ;
		boolean smartToolAdmin = loggedUserObj.isSmartToolAdmin() ;
 
		if (smartToolAdmin) 
		{ %> <jsp:include page="/footer.jsp" /><% } 
}%>
</body>
</html>
