

<%@page import="Support.*, java.util.*, java.io.* , java.sql.* , java.text.SimpleDateFormat , javax.faces.model.SelectItem "%>
<%@page import="Support.event.resource.system.*"%>
<%@page import="com.smartValue.database.map.services.*"%>
<%@page import="com.smartValue.database.map.*"%>
<%@page import="com.sideinternational.sas.configuration.Configuration"%>
<%@page import="com.smartValue.database.PersistantObject"%>
<%@page import="com.smartValue.support.map.LuQueryDetails"%>
<%@ page import="com.google.cloud.bigquery.BigQuery , com.google.cloud.bigquery.BigQueryOptions
                  , com.google.cloud.bigquery.FieldValueList
                  , com.google.cloud.bigquery.Job 
                  , com.google.cloud.bigquery.JobId 
                  , com.google.cloud.bigquery.JobInfo 
                  , com.google.cloud.bigquery.QueryJobConfiguration 
                  , com.google.cloud.bigquery.QueryResponse 
                  , com.google.cloud.bigquery.TableResult
                  , com.google.auth.oauth2.GoogleCredentials
				  , com.google.auth.oauth2.ServiceAccountCredentials 
				  , com.google.cloud.bigquery.Field
				  , java.io.File
				  , java.io.FileInputStream
				  , java.io.IOException
                  , java.util.UUID" %>
<%@page errorPage="errorPage.jsp"%>
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

<% 
request.setCharacterEncoding("UTF-8");
Connection con = (Connection)session.getAttribute("con");
Connection repCon = (Connection)session.getAttribute("repCon");

boolean printable = ( request.getParameter("printable") != null  && request.getParameter("printable").equalsIgnoreCase("true") ); 			
boolean AutoLog  = ( request.getParameter("AutoLog") != null ) ; //----To Enable automatically read page contents
  if( ( repCon == null || repCon.isClosed()) && AutoLog )
{ 
    XMLConfigFileReader aa =  Misc.getXMLConfigFileReader(false ) ; // new XMLConfigFileReader( false);
    repCon = aa.reposatoryConn.generateConnection();
    session.setAttribute("repCon",repCon);
}
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
  else { session.setAttribute("queryString",request.getQueryString());
        response.sendRedirect("loginScreen.jsp");
       }
}

//String appURL = request.getRequestURL().substring(0,request.getRequestURL().indexOf(request.getServletPath()));
String id = request.getParameter("id");

Support.SqlReader sqlReader = (Support.SqlReader)request.getAttribute("sqlReader");
if (sqlReader == null) 
{
  String lookupTableName = "LU_QUERIES" ; 	
  sqlReader = new Support.SqlReader(repCon,lookupTableName , "QUERY_BODY",  id, session , request); 
}

int queryIndex = Integer.parseInt( request.getParameter("queryIndex"));
LuQueryDetails queryDetails = null; 
try { queryDetails = sqlReader.getQueryDetail(queryIndex) ; }
catch (Exception e) {}
		String[] queryStmt = sqlReader.getQueryStatments();
	  	String[] titles = sqlReader.getQueryTitles();
%>

<!--  Scripts To Enable Pivot Table Renderer  -->

    <!-- PivotTable.js libs from ../dist -->
    <link rel="stylesheet" type="text/css" href="<%=appURL%>/pivottable-master/dist/pivot.css">
    <script type="text/javascript" src="<%=appURL%>/pivottable-master/dist/pivot.js"></script>

	<!--  Enable Google Charts -->
		<script type="text/javascript" src="https://www.google.com/jsapi"></script>
		<script type="text/javascript" src="<%=appURL%>/pivottable-master/dist/gchart_renderers.js"></script>
	<!--  End of Enable Google Charts -->

    <!-- optional: mobile support with jqueryui-touch-punch -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui-touch-punch/0.2.3/jquery.ui.touch-punch.min.js"></script>
<!-- End of Scripts To Enable Pivot Table Renderer  -->

<!-- script for DataTable (Search & sorting )  -->
	<link rel="stylesheet" type="text/css" href="<%=appURL%>/includes/dataTable/dt-1.10.13/datatables.min.css"/>
 
	<script type="text/javascript" src="<%=appURL%>/includes/dataTable/dt-1.10.13/datatables.min.js"></script>
<!-- End of script for DataTable (Search & sorting )  -->        

<%@ include file="includes/jquery_commons.js"%>
<script type="text/javascript">
$(window).load(function() {
	$("#spinner").fadeOut("slow");
});
</script>
</head>
<div>
 
     
<strong><br>
<div id="div<%=id%>_<%=queryIndex%>" >
<%=titles[queryIndex]%> 

<%-- // This Script To Display the Data in a Pivot Table --%>
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
		             var pivotAttributes = <%= (queryDetails.getPivottableInitParamsValue()!=null)? queryDetails.getPivottableInitParamsValue() : "''" %>  ;

		            	
				}
				if (showAnalytics)
					$("#Pivot_out<%=id%>_<%=queryIndex %>").pivotUI($("#resSetTable<%=id%>_<%=queryIndex %>") , pivotAttributes  );
		            else 
		            $("#Pivot_out<%=id%>_<%=queryIndex %>").pivot($("#resSetTable<%=id%>_<%=queryIndex %>") , pivotAttributes  );
				// This Script is To Enable saving the pivot table status  
				$("#savePivotStatus<%=id%>_<%=queryIndex%>").on("click", function(){
	                var config = $("#Pivot_out<%=id%>_<%=queryIndex%>").data("pivotUIOptions");
	                var config_copy = JSON.parse(JSON.stringify(config));
	                //delete some values which will not serialize to JSON
	                delete config_copy["aggregators"];
	                delete config_copy["renderers"];
	                pivotAttributes = JSON.stringify(config_copy) ; 
	                //$.cookie("pivotConfig<%=id%>_<%=queryIndex%>", JSON.stringify(config_copy));
				    
	               	                
				    // Saving new value to db  
					var updateResultDivID = 'updateResult<%=id%>_<%=queryIndex %>' ;
				    var updateURL =  '/SmartTool/updateDBAttribute.jsp?isHijriDate=false&isDateTime=false&isDate=false&tableName=LU_QUERY_DETAILS&tableOwner=SUPPORT&columnName=PIVOTTABLE_INIT_PARAMS&rowIdValue=<%=queryDetails.getRowIdString()%>&newValue=' + adjustNewValue(encodeURIComponent(pivotAttributes) ) ;
	                alert('UpdateURL = ' +  updateURL ) ; 
	                sendAjaxRequestToJsp(encodeURI(updateURL) , updateResultDivID) ; 

	                
	            });
	
	            $("#restorePivotStatus<%=id%>_<%=queryIndex%>").on("click", function(){
	                $("#Pivot_out<%=id%>_<%=queryIndex%>").pivotUI($("#resSetTable<%=id%>_<%=queryIndex %>"), JSON.parse(pivotAttributes), true);
	        	});
        
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
<%if ( ! printable) {%>
	<img title="Show Pivot Table"  name="toggleViewPivitTable_<%=queryIndex %>" src="<%=appURL%>/images/PivotTable.ico" height="25" onclick="toggleShowPivotTable_<%=queryIndex %>(true);">
	<img title="Show Table Filtering"  name="toggleViewPivitTable_<%=queryIndex %>" src="<%=appURL%>/images/tableFilterIco.jpg" height="20" onclick="showTableFiltering_<%=queryIndex %>();">
	<img title = "Transpose Table" height="20" src = "<%=appURL%>/images/transpose_thumb.png" id = "transposeLink" onclick = "TransposeTable('resSetTable<%=id%>_<%=queryIndex %>');"></img>
<% }
SecUsrDta loggedUser = (SecUsrDta) session.getAttribute("loggedUser") ;
boolean smartToolAdmin =  loggedUser.isSmartToolAdmin () ;

 if (!printable)
{
	%>
		 <a id="opener_<%=queryIndex%>">More..</a>
	<%
}
%>
<div id = "Pivot_out<%=id%>_<%=queryIndex %>_container"  style="visibility: hidden; width: 800 ; height :0">
 <% if (smartToolAdmin) { %>
 <a href = "/SmartTool/queryResultWithEditableRS.jsp?id=13654&lookupTableName=LU_QUERIES&query_id=<%=id%>" target = "MaintainPivotTable" >Maintain Pivot Table Settings</a>
 .. <a href = "/SmartTool/pivottable-master/examples/index.html" target = "PivotTableDemo" >Samples </a>
 <% } %>
 	<input type="button" value="Save Pivot Status" id="savePivotStatus<%=id%>_<%=queryIndex%>" style="font-size: 12px;" />
    <input type="button" value="Restore Saved Pivot Status" id="restorePivotStatus<%=id%>_<%=queryIndex%>" style="font-size: 12px;" />
    <div id = "updateResult<%=id%>_<%=queryIndex %>"> </div>
    <br /><br /><br />
 	<div id = "Pivot_out<%=id%>_<%=queryIndex %>"    >
		Not Able to render Data in Pivot Table 
	</div>
</div> 

</div>
</strong>


<% // Start Executing The Query against Google Cloud Big Query  
	java.util.Date startTime = new java.util.Date();
 	GoogleCredentials credentials;
	File credentialsPath = new File(XMLConfigFileReader.getConfigurationHome() + File.separator + "epm.smartvalue.json");  // TODO: update to your key path.
	FileInputStream serviceAccountStream = new FileInputStream(credentialsPath); 
	credentials = ServiceAccountCredentials.fromStream(serviceAccountStream);

	BigQuery bigQuery = BigQueryOptions.newBuilder().setCredentials(credentials).build().getService();
	QueryJobConfiguration queryConfig =
		    QueryJobConfiguration.newBuilder( queryStmt[queryIndex]) 
		        .setUseLegacySql(false)
		        .build();

		// Create a job ID so that we can safely retry.
		JobId jobId = JobId.of(UUID.randomUUID().toString());
		Job queryJob = bigQuery.create(JobInfo.newBuilder(queryConfig).setJobId(jobId).build());

		// Wait for the query to complete.
		queryJob = queryJob.waitFor();

		// Check for errors
		if (queryJob == null) {
		  throw new RuntimeException("Job no longer exists");
		} else if (queryJob.getStatus().getError() != null) {
		  // You can also look at queryJob.getStatus().getExecutionErrors() for all
		  // errors, not just the latest one.
		  throw new RuntimeException(queryJob.getStatus().getError().toString());
		}
		// [END bigquery_simple_app_query]

		// [START bigquery_simple_app_print]
		// Get the results.
		QueryResponse qResponse = bigQuery.getQueryResults(jobId);
		TableResult result = queryJob.getQueryResults();
 %>

<table border = 1 class="basicTable resizable" id = "resSetTable<%=id%>_<%=queryIndex %>" dir="<%=loggedUser.getDisplayDirection()%>"   >
	<thead>
		<tr>
			<% for (Field f : result.getSchema().getFields())
				{ 
					out.print("<th>"+ f.getName() + "</th>") ; 	
				} 
			%>	
		</tr>
	</thead>
	<%
    	int rownum = -1;
    //-------rendering Data--------------------
		for (FieldValueList row : result.iterateAll()) 
		{
			rownum++; 
			out.print("<tr>"); 
			for (Field f : result.getSchema().getFields())
			{
				out.print("<td>"+ row.get(f.getName()).getStringValue() + "</td>" )  ; 
			}
			out.print("</tr>");
		}
    %> </table>
    <% 
    //-----End of rendering Data--------------------

		java.util.Date endTime = new java.util.Date();
        long execTime = endTime.getTime()-startTime.getTime();
        boolean loggingRquired = sqlReader.isLogExecution();
        if (loggingRquired )
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

if (! AutoLog && ! printable) 
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

	<% if (!printable) { %>
	<div id="dialog_<%=queryIndex%>" title="More Options">
		<jsp:include page="renderQueryFooter.jsp">
			<jsp:param value="<%=queryIndex%>" name="queryIndex"/>
			<jsp:param value="<%=id %>" name="queryId"/>
			<jsp:param value="<%=rownum + 1 %>" name="rownum"/>
			<jsp:param value="<%=execTime%>" name="execTime"/>
		</jsp:include>
	 </div>
	<%}%>
			
	<% 
}
      
     %>
</div>

</html>