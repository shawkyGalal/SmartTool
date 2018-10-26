
<%@page import="Support.*, java.util.*, java.io.* , java.sql.* , java.text.SimpleDateFormat , javax.faces.model.SelectItem "%>
<%@page import="Support.event.resource.system.*"%>
<%@page import="com.implex.database.map.services.*"%>
<%@page import="com.implex.database.map.*"%>
<%@page import="com.sideinternational.sas.configuration.Configuration"%>
<%@page import="com.implex.database.PersistantObject"%>
<%@page import="com.smartValue.support.map.LuQueryDetails"%>

<%@page errorPage="../../errorPage.jsp"%>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>
<% String appURL = Support.Misc.getAppURL(request) ;  %>

<html>
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=appURL%>/includes/AJAX_new.js"></script>
<script type="text/javascript" src="<%=appURL%>/includes/resizable-tables.js"></script>
<script type="text/javascript" src="<%=appURL%>/jQueryAssets/jquery-1.8.3.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=appURL%>/jQueryAssets/jquery-1.10.2.js" ></script>
<script type="text/javascript" src="<%=appURL%>/jQueryAssets/jquery-ui.js"></script>
<script type="text/javascript" src="<%=appURL%>/jQueryAssets/jquery-ui-1.9.2.tabs.custom.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=appURL%>/includes/smartTool.js"></script>

<link href="<%=appURL%>/jQueryAssets/jquery.ui.core.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=appURL%>/jQueryAssets/jquery-ui.css">
<link rel="stylesheet" href="<%=appURL%>/includes/smartTool.css">
<link href="<%=appURL%>/jQueryAssets/jquery.ui.theme.min.css" rel="stylesheet" type="text/css">
<link href="<%=appURL%>/jQueryAssets/jquery.ui.tabs.min.css" rel="stylesheet" type="text/css">


<!--  Scripts To Enable Pivot Table Renderer  -->

    <!-- PivotTable.js libs from ../dist -->
    <link rel="stylesheet" type="text/css" href="<%=appURL%>/pivottable-master/dist/pivot.css">
    <script type="text/javascript" src="<%=appURL%>/pivottable-master/dist/pivot.js"></script>

	<!--  Enable Google Charts -->
		<script type="text/javascript" src="https://www.google.com/jsapi"></script>
		<script type="text/javascript" src="<%=appURL%>/pivottable-master/dist/gchart_renderers.js"></script>
	<!--  End of Enable Google Charts -->

    <!-- optional: mobile support with jqueryui-touch-punch 
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui-touch-punch/0.2.3/jquery.ui.touch-punch.min.js"></script>
    -->
<!-- End of Scripts To Enable Pivot Table Renderer  -->

<!-- script for DataTable (Search & sorting )  -->
	<link rel="stylesheet" type="text/css" href="<%=appURL%>/includes/dataTable/dt-1.10.13/datatables.min.css"/>
 
	<script type="text/javascript" src="<%=appURL%>/includes/dataTable/dt-1.10.13/datatables.min.js"></script>
<!-- End of script for DataTable (Search & sorting )  -->        

<%@ include file="../../includes/jquery_commons.js"%>
<script type="text/javascript">
$(window).load(function() {
	$("#spinner").fadeOut("slow");
});
</script>
</head>
<div>
	
	<%@ include file ="renderQueryResultBackEnd.jsp" %>
     
<strong><br>
<div id="div<%=id%>_<%=queryIndex%>" >
<%=titles[queryIndex]%> 

<%-- // This Script To Display the Data in a Pivot Table --%>
<%String printableParamValue =  request.getParameter("printable") ; 
boolean printable = printableParamValue != null && printableParamValue.equalsIgnoreCase("true"); 
if (! printable) {
%>
<script	language="JavaScript" type="text/JavaScript">
		var detailHidden_<%=queryIndex%> = true;
		var divHight_<%=queryIndex%>  ;
		var alreadyRendered_<%=queryIndex%> = false ;
		google.load("visualization", "1", {packages:["corechart", "charteditor"]});		

		function toggleShowPivotTable_<%=queryIndex %>(showAnalytics)
		{
			var pivotDiv = document.all['Pivot_out<%=id%>_<%=queryIndex %>_container'] ;
			var controlButton = document.all['toggleViewPivitTable_<%=queryIndex %>'] ;
			try {
			//if (! alreadyRendered_<%=queryIndex%> ) 
				{
				
					var derivers = $.pivotUtilities.derivers;
			        var renderers = $.extend($.pivotUtilities.renderers,
			            $.pivotUtilities.gchart_renderers);
					var sum = $.pivotUtilities.aggregatorTemplates.sum;
					var average = $.pivotUtilities.aggregatorTemplates.average;
					var numberFormat = $.pivotUtilities.numberFormat;
					var intFormat = numberFormat({digitsAfterDecimal: 0});
		            var tableData = document.getElementById("resSetTable<%=id%>_<%=queryIndex %>") ; // $("#resSetTable<%=id%>_<%=queryIndex %>") ;
		            var pivotAttributes = {
							renderers: renderers
							<%= (queryDetails.getPivottableInitParamsValue()!=null)? queryDetails.getPivottableInitParamsValue() : "" %> 
					
				            //,rows: [tableData.rows[0].cells[1].id]
				            //,cols: [tableData.rows[0].cells[2].id]
				            //,rendererName: "Bar Chart"
				            ////,aggregator: sum([tableData.rows[0].cells[2].id])
				        } ;
		            // alert (tableData.rows[0].cells[1].id) ; 
		            	
				}
			if (showAnalytics)
				$("#Pivot_out<%=id%>_<%=queryIndex %>").pivotUI($("#resSetTable<%=id%>_<%=queryIndex %>") , pivotAttributes  );
	            else 
	            $("#Pivot_out<%=id%>_<%=queryIndex %>").pivot($("#resSetTable<%=id%>_<%=queryIndex %>") , pivotAttributes  );
			}
			
		   	catch ( err )
		   	{ 
			  pivotDiv.style.color = "red" ; 
		   	  pivotDiv.innerHTML = 'Smart Tool Error PVT:001: Unable To Render Pivoit Table Due To : ' + err ; 
		   	  throw err ; 
		   	 }
		   	finally 
		   	{divHight_<%=queryIndex%>  = pivotDiv.style.height ;
		     alreadyRendered_<%=queryIndex%> = true ;}      
			
			
			if (detailHidden_<%=queryIndex%>)
			{
			pivotDiv.style.height = "" ; //divHight_<%=queryIndex%> ;
  			pivotDiv.style.visibility = 'visible';
			detailHidden_<%=queryIndex %> = false;
			controlButton.title = 'Hide Pivot Table' ; 
			}
			else
			{
			divHight_<%=queryIndex%> = 	pivotDiv.style.height ; 
			pivotDiv.style.height = 0 ; 
			pivotDiv.style.visibility = 'hidden';
			detailHidden_<%=queryIndex %> = true;
			controlButton.title = 'Show Pivot Table' ;
			}
		}
</script>

<script>
// Jquery Code to Add Search and filtering cabability to the datatable 
function showTableFiltering_<%=queryIndex %>() 
{
	var filterRow = document.getElementById('filteringRow_<%=id %>_<%=queryIndex %>') ; 
	filterRow.style.display = 'table-row' ; 
    // Setup - add a text input to each footer cell
    $('#resSetTable<%=id%>_<%=queryIndex %> thead td').each( function () {
        var title = $(this).text();
        $(this).html(  '<input type="text" placeholder="Search ' + title + '"  size = 10 />' );
    } );
 
    // DataTable
    var table = $('#resSetTable<%=id%>_<%=queryIndex %>').DataTable();
 
    // Apply the search
    table.columns().every( function () {
        var that = this;
 
        $( 'input', this.header() ).on( 'keyup change', function () {
            if ( that.search() !== this.value ) {
                that
                    .search( this.value )
                    .draw();
            }
        } );
    } );
}
</script>	


<img title="Show Pivot Table عرض تحليلى للبيانات "  name="toggleViewPivitTable_<%=queryIndex %>" src="<%=appURL%>/images/PivotTable.ico" height="20" onclick="toggleShowPivotTable_<%=queryIndex %>(true);">
<img title="Show Table Filtering"  name="toggleViewPivitTable_<%=queryIndex %>" src="<%=appURL%>/images/tableFilterIco.jpg" height="20" onclick="showTableFiltering_<%=queryIndex %>();">
<img title = "Transpose Table" height="20" src = "<%=appURL%>/images/transpose_thumb.png" id = "transposeLink" onclick = "TransposeTable('resSetTable<%=id%>_<%=queryIndex %>');"></img>
<%} %>
<%
SecUsrDta loggedUser = (SecUsrDta) session.getAttribute("loggedUser") ;
boolean smartToolAdmin =  loggedUser.isSmartToolAdmin () ;
if (rowIdFound && ! printable)
{
	if (smartToolAdmin)
	{
	%><a href=<%=appURL%>/TableInsertForm.jsp?<%=(request.getQueryString()!= null)? request.getQueryString().toUpperCase() : "" %>&owner=<%=tableOwner%>&tableName=<%=tableName %> target =new><img border=0 src=<%=appURL%>/images/toolplus.gif width=20 height=20 title='Add New "<%=tableName %>" Entry'></a><%
	}
	%><a href=<%=appURL%>/XLSUploading/getXLSClientFile.jsp?dirPath=XLSUploading/Data&uploadToTableOwner=<%=tableOwner.toUpperCase()%>&uploadToTableName=<%=tableName.toUpperCase() %> target =new><img border=0 src=<%=appURL%>/images/icxls.gif width=20 height=20 title='Import Data from Excel تحميل بيانات من ملف اكسل'></a><%
}

if (!printable) {

	%> <a id="opener_<%=queryIndex%>">More..</a><% 
}

%>
<div id = "Pivot_out<%=id%>_<%=queryIndex %>_container"  style="visibility: hidden; width: 800 ; height :0">
 <% if (smartToolAdmin) { %>
 <a href = "/SmartTool/queryResultWithEditableRS.jsp?id=13654&lookupTableName=LU_QUERIES&query_id=<%=id%>" target = "MaintainPivotTable" >Maintain Pivot Table Settings</a>
 .. <a href = "/SmartTool/pivottable-master/examples/index.html" target = "PivotTableDemo" >Samples </a>
 <% } %>
 	<div id = "Pivot_out<%=id%>_<%=queryIndex %>"    >
		Not Able to render Data in Pivot Table 
	</div>
</div> 

</div>
</strong>
<div class="panel mb0">
<div class="panel-body p-n">
<div class="table-responsive">
<table border = 1 class="table table-striped table-vam table-bordered mb0 " id = "resSetTable<%=id%>_<%=queryIndex %>"  dir="<%=loggedUser.getDisplayDirection()%>">
	<thead>
	<tr>
		<%
        //-------------Rendering Header Data------------------
        if (rowIdFound ){%>
		<th align="center" bgcolor="#0099CC"><strong><font color="#FFFFFF">E</font></strong></th>
		<%}
        
        String queryString = request.getQueryString();
        int orderByIndexInRequest = (queryString!=null)? queryString.indexOf("_Order") : -1 ;
        if (queryString != null && orderByIndexInRequest != -1 )
        {
         queryString= queryString.substring(0,orderByIndexInRequest-1);
        }
             
        for (int count = 1 ; count<=columnCount ; count++ )
        { 
          //----Do not show rowid column
		  
          if (rowIdFound && count == rowIdIndex)
          { continue; }
          //---End of Do not show rowid column
          
          columnTypes[count] = rsmd.getColumnTypeName(count);
          columnNames[count] =  rsmd.getColumnName(count);
          String columnDisplayName = columnNames[count] ;
          String columnComments = columnDisplayName ; 
          try{
          	TableMaintDetails tmd = tmm.getTmdByColumnName( columnNames[count] ) ;
           	columnDisplayName = (tmd!=null)? tmd.getDisplayNameValue() : columnNames[count] ;
           	columnComments = (tmd!=null && tmd.getCommentsValue() != null )? tmd.getCommentsValue() : columnComments ;
           }
           catch (Exception e){ columnDisplayName = Support.Misc.repalceAll(columnNames[count],"_"," ") ;} 
          %>
           <th align="center" nowrap=true bgcolor="#0099CC" title="<%=columnComments%>" id = "<%=columnDisplayName%>" ><strong><font	color="#FFFFFF"><%=columnDisplayName%></font></strong></th>
          
           
		<!-- 
		<th align="center" nowrap=true bgcolor="#0099CC" title="<%=columnComments%>" id = "<%=columnDisplayName%>" ><strong><font	color="#FFFFFF"><%=columnDisplayName%></font></strong><% 
			boolean showSortingArrows = !AutoLog ; 
			if (showSortingArrows){  %> 
			
			<a	href="?<%=queryString%>&_OrderAscBy=<%=count%>&_QuerySeq=<%=queryIndex%>">
				<img border="0" src="<%=appURL%>/images/asc.gif" width="10" height="10"></a> 
			<a	href="?<%=queryString%>&_OrderDescBy=<%=count%>&_QuerySeq=<%=queryIndex%>">
			<img border="0" src="<%=appURL%>/images/desc.gif" width="10" height="10"></a> <%}%>
</th>
		<-->
		 
		<%
         }
        %>
	</tr>
	<!--  Render Header Row For JQuery Searching & Sorting -->
		<tr id = "filteringRow_<%=id %>_<%=queryIndex %>"  style="display: none;" >
			<%
	        if (rowIdFound ){%>
			<th align="center" ><strong><font color="#FFFFFF">E</font></strong></th>
			<%}
	
			for (int count = 1 ; count<=columnCount ; count++ )
	    	{ 
		          if (rowIdFound && count == rowIdIndex)
		          { continue; }
		          //---End of Do not show rowid column
		          
		          columnTypes[count] = rsmd.getColumnTypeName(count);
		          columnNames[count] =  rsmd.getColumnName(count);
		          String columnDisplayName = columnNames[count] ;
		          String columnComments = columnDisplayName ; 
		          try{
		          	TableMaintDetails tmd = tmm.getTmdByColumnName( columnNames[count] ) ;
		           	columnDisplayName = (tmd!=null)? tmd.getDisplayNameValue() : columnNames[count] ;
		           	columnComments = (tmd!=null && tmd.getCommentsValue() != null )? tmd.getCommentsValue() : columnComments ;
		           }
		           catch (Exception e){ columnDisplayName = Support.Misc.repalceAll(columnNames[count],"_"," ") ;} 
	
				  %> <th><%=columnDisplayName%></th> <% 
	    	} %>  
				
		</tr>	
	</thead>
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
          
          
		 try{ rowIdValue = rs.getString(rowIdIndex) ;  }
	     catch (Exception e) {}
	  	 if ( filterByRowId ) 
			{
				boolean isTheRequestedRow = selectedOraRowId.equals(rowIdValue) ;  
				if ( ! isTheRequestedRow ) 
				{
					continue ; 
				}
			}
	  	%><tr class="basicTable"><%        
		if (rowIdFound && !printable) //----------if rowid found then  render an edit cell at the begining 
          {
            rowIdValueUrlEncoded = (rowIdValue != null)? java.net.URLEncoder.encode(rowIdValue) : "Null?";
            String uploadDirPath = "DB_Attachs/"+tableOwner + "/" + tableName +"/" +rowIdValueUrlEncoded ;  
            String uploadLinkHref = appURL+"/getClientFile.jsp?NoDBRecord=N&queryIndex="+queryIndex+"&"+request.getQueryString()+"&serverName=127.0.0.1&dirPath="+ uploadDirPath ;
            uploadLinkHref +=  "&queryId="+id +"&"+SqlReader.SELECTED_ORA_ROWID + "=" + rowIdValueUrlEncoded + "&"+SqlReader.QUERY_INDEX_TO_FILTER_BY_ROWID+ "="+queryIndex ;
    		int attachsCountInt = db.getTableAttachmentCount(tableOwner , tableName , rowIdValue ) ; 
            boolean renderCount = attachsCountInt > 0 ;  
            String objApprovLinkHref = Misc.repalceAll(uploadLinkHref , "getClientFile" , "objectApprovalData" );
            %>
            <td><strong> <a title = "Update data for <%=tmm.getObjectNameValue() %> ... تعديل بيانات <%=tmm.getObjectName_Value() %>" 
								href='<%=appURL%>/<%=tableObjectEditorPage%>?<%=rowidColumnName%>=<%=rowIdValueUrlEncoded%><%=(hasSpecicEditor)? "" : "&tableName="+ tableOwner.toUpperCase()+"."+tableName  %>'>
								<img alt="" src="<%=appURL%>/images/editrow.gif" height="20" width="20"> 
			
				<% boolean showAttachlLink = tmm.getAttribute("ENABLE_ATTACHEMENTS").getBooleanValue() ; 
			 		if (showAttachlLink) 
			 		{%>
				<a title = "Add New Attachement For <%=tmm.getObjectNameValue() %> ... إضافة مستند خاص ب<%=tmm.getObjectName_Value() %>" target = "UploadFile" href = "<%=uploadLinkHref %>" 	>
					<img alt="" src="<%=appURL%>/images/attach_Icon.gif" height="20" width="20">
				</a>
						<% if (renderCount) { %>
						<a title="عدد الملفات الملحقة : <%=attachsCountInt%> "  target = "UploadFile" href = "<%=uploadLinkHref %>"  ><%= attachsCountInt %></a>
						<%} %>
					<%} %>
				<% boolean showApprovalLink = tmm.getAttribute("ENABLE_APPROVAL_CYCLE").getBooleanValue() ; 
			 		if (showApprovalLink) 
			 		{%>
						<a title = "Check Approvals " <%=tmm.getObjectName_Value() %>" target = "Check Approvals" href = "<%=objApprovLinkHref %>" 	>
							<img alt="" src="<%=appURL%>/images/approvalCycle.jpg" height="20" width="20">
						</a>
				<% } %>
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
                      || columnType.equals("NVARCHAR2")
                      || columnType.equals("RAW") 
                      || columnType.equals("CHAR") 
                      || columnType.equals("LONG")
                    ) 
                 { 
                  columnValue= rs.getString(count);
                  columnValue = (columnValue == null )? "&nbsp;": columnValue;
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
            	else // if (databaseName.equals("Microsoft SQL Server"))
              {
                columnValue= rs.getString(count);
                columnValue = (columnValue == null )? "&nbsp;": columnValue;
              }

          }
          catch (Exception e) {  rs.close();   stmt.close(); throw new Exception ("Unable to Read Column Number : " + count + " Row Number "+ rownum +" Due to : " + e.getMessage()) ;}
            //--------------------Rendering The Column Value---------------
        	%><td><%
            if (columnValue.length()> maxDisplayableSize   //------- If column value length > 30 
                && columnValue.indexOf("</a>") == -1  //---------and it is not heyperLinked then render a button for details
               ) 
            {   %>
			<%=columnValue.substring(0,maxDisplayableSize-1) + "..."%> <input
			name="Det" type="button"
			onClick=" longTextViewer= window.open(); 
                                                              <% for (int lineNum = 0 ; lineNum<columnValuePerLine.size() ; lineNum ++  ) 
                                                              {
                                                               %>longTextViewer.document.write('<%=columnValuePerLine.elementAt(lineNum)%><br>');<% 
                                                              } %>
                                                            "
			value="Det">
		<%
            }
            else
            { 
                %>
				<%=(columnValue != null)? Support.Misc.repalceAll(columnValue , "\n" , "<br>"): null %>
				<% 
            }
            %></td><%
            //------------------End of Rendering The Column Value---------------
            isNumericColumn[count] = columnTypes[count].equals("NUMBER")||columnTypes[count].equals("int");
            boolean isHyberNumber = false;
            java.math.BigDecimal bd = new java.math.BigDecimal(0);
            if (isNumericColumn[count])
            {
            	try{bd = rs.getBigDecimal(count);}catch (Exception e) {}
            }
            try
            {
            Support.HTML.AnchorTag at = new Support.HTML.AnchorTag(columnValue , "a") ; 
            if (at.isHtmlTag("a") ) //-------if it is a hyperLinked Number 
            { 
                bd = new java.math.BigDecimal(at.getValue());
                isHyberNumber = true;
            }
            }
            catch(Exception e) {}
            if (isNumericColumn[count] || isHyberNumber ) //-------------if Column Type is Number 
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
        //-------------Rendering Sum of Numeric Data------------------
		LuQueryDetails queryDetail ; 
        boolean renderAggregate = true ; 
        try {
        	 queryDetail = sqlReader.getQueryDetail(queryIndex);
        	renderAggregate = queryDetail.getShowAggregate().getBooleanValue() ;}
        catch (Exception e){}
        
      if (renderAggregate )
      {
        %><tfoot><tr><%

    	  boolean rendersSums =  rownum > 0 && !filterByRowId;
		if ( rendersSums )
		{
			if (rowIdFound )
	        {%>
			<td><strong> </strong></td>
			<%
	        }

			for (int count = 1 ; count<=columnCount ; count++ )
	        { 

	          //----Do not show rowid column
	          if ((rowIdFound && count == rowIdIndex) )
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
        %></tr> </tfoot> <%
      }
	
     if (rownum == -1 ) { %>
	<tr id="noDataFoundRow_<%=queryIndex%>">
		<td colspan="<%=columnCount%>" > No Data Found .... لا يوجد بيانات </td>
	</tr>
	<%} %>
</table>
</div>
</div>
</div>
  


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
if (! AutoLog ) 
{
	%>
	<script>
		  $(function() {
		    $( "#dialog_<%=queryIndex%>" ).dialog({
		      autoOpen: false,
			  width : 500 , 
			  modal : true , 
		      show: { effect: "explode", duration: 500 },
		      hide: { effect: "explode", duration: 500 }
		    });
		 
		    $( "#opener_<%=queryIndex%>" ).click(function() {
		      $( "#dialog_<%=queryIndex%>" ).dialog( "open" );
		    });
		  });
	</script>
	<% if (rownum > 0 ) {  %>
	(<%=rownum + 1%>) Record(s)
	<%} %> 

	
	<div id="dialog_<%=queryIndex%>" title="More Options">
		<jsp:include page="renderQueryFooter.jsp">
			<jsp:param value="<%=queryIndex%>" name="queryIndex"/>
			<jsp:param value="<%=id %>" name="queryId"/>
			<jsp:param value="<%=rownum + 1 %>" name="rownum"/>
			<jsp:param value="<%=execTime%>" name="execTime"/>
		</jsp:include>
	 </div>
				
	<% 
}
      }
      if (rs != null) rs.close();
      if (stmt != null) stmt.close();
      
     %>
</div>

</html>