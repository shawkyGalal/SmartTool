<%@ page errorPage="../errorPage.jsp"
	import="Support.*, java.util.*, java.io.* , java.sql.* "%>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>

<%@page import="Support.HTML.AnchorTag"%><html>
<head>
	<% String appURL = Support.Misc.getAppURL(request) ;  %>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.system.js"></script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.coreVector.js"></script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.advanced.js"></script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.gauge.js"></script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.funnel.js"></script>
    <script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/motifs/jchartfx.motif.js"></script>
	<script type="text/javascript" src="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/js/jchartfx.animation.js"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
  	<script src="<%=appURL%>/jQueryAssets/jquery-1.10.2.js"></script>
  	<script src="<%=appURL%>/jQueryAssets/jquery-ui.js"></script>
  
	<% 
	   	java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");
 	   	java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
 	   	String QueryTableName = "LU_QUERIES" ;
 	    String queryId = request.getParameter("queryId");
 	   	String chartCategory = request.getParameter("chartCategory") ; if ( chartCategory == null ) {chartCategory = "Bar" ; }
 	    int queryIndex = Integer.valueOf(request.getParameter("queryIndex"));
 	    String chartHeight = request.getParameter("chartHeight");
 	    if (chartHeight == null) chartHeight="500" ; 
 	    String chartWidth = request.getParameter("chartWidth");
 	   if (chartWidth == null) chartWidth="500" ;
 	   boolean seriesInRows = ( request.getParameter("seriesInRows") != null) && (request.getParameter("seriesInRows").equalsIgnoreCase("Y")) ;   
 	   
 	  	Support.SqlReader sqlReader = null;
 	  	try { sqlReader = new Support.SqlReader(repCon,QueryTableName , "QUERY_BODY",  queryId, session , request);  }
 	  	  catch ( QueryParamValueNotFoundException e ) 
 	  	  {
 	  		%> <P></P><p dir="rtl" align="center"> <font color="red" size="5"> برجاء اختيار و تحديد القيم اعلاه ثم الضغط على "Submit" </font><p align="center"> <%=e.getMissingParam() %></p></p>
 	  			<p align="center"><a href = "index.jsp?reportTopOpen=<%=queryId%>" target = top title="الصفحة الرئيسية"><img src="images/homeIcon.jpg" width="30"></a></p>
 	  		<%
 	  		out.flush();
 	  		return ; 
 	  	  }
 	  	String queryStmt =  sqlReader.getQueryStatments()[queryIndex] ; 
 	  	Statement stmt = con.createStatement();
 	  	ResultSet rs = stmt.executeQuery(queryStmt) ; 
	    String queryTitle = sqlReader.getQueryTitles()[queryIndex] ;
	    try {
	    AnchorTag tag = new AnchorTag (queryTitle, "ui") ;
	    if ( tag.isHtmlTag("ui") || tag.isHtmlTag("a")) 
	    { 	queryTitle = tag.getValue();   }
	    }
	    catch(Exception e) {}
	%>
    <script type="text/javascript">
        var chart_<%=queryId%>_<%=queryIndex%>;
        function initialize(m_galary) {
            chart_<%=queryId%>_<%=queryIndex%> = new cfx.Chart();
			chart_<%=queryId%>_<%=queryIndex%>.getAnimations().getLoad().setEnabled(true);
			chart_<%=queryId%>_<%=queryIndex%>.getDataGrid().setVisible(true); 
            doInitializeDash(m_galary);
            chart_<%=queryId%>_<%=queryIndex%>.create('divChart_<%=queryId%>_<%=queryIndex%>');
        }
    </script>
    <script type="text/javascript">
    function doInitializeDash(m_galary)
    {
        var title;
        <% if (seriesInRows) {%>
        populateDatAsSeriesInRows(chart_<%=queryId%>_<%=queryIndex%>);
        <% } else { %>
        populateDatAsSeriesInColumns(chart_<%=queryId%>_<%=queryIndex%>);
        <% }%>
        chart_<%=queryId%>_<%=queryIndex%>.setGallery(m_galary);
        title = new cfx.TitleDockable();
        title.setText("<%=Misc.repalceAll(queryTitle , "\"" , "''" ) %>");
        chart_<%=queryId%>_<%=queryIndex%>.getTitles().add(title);
        chart_<%=queryId%>_<%=queryIndex%>.getAllSeries().getPointLabels().setVisible(true);
        <% 
        int displaySeriesCount = 2 ;
        try{displaySeriesCount = Integer.valueOf(request.getParameter("displaySeriesCount")) ; }
        catch(Exception e) {}
        int displaySeriesCountM = displaySeriesCount + 1 ;
        int realColumnCount = rs.getMetaData().getColumnCount(); 
        if (displaySeriesCountM > realColumnCount  ){displaySeriesCountM = realColumnCount ; displaySeriesCount = displaySeriesCountM - 1 ; }
        for ( int i = 0 ; i< displaySeriesCount ; i++ ) { %>
        chart_<%=queryId%>_<%=queryIndex%>.getSeries().getItem(<%=i%>).getPointLabels().setOffset("0,3");
        <%}%>
        //var gantt = chart_<%=queryId%>_<%=queryIndex%>.getGalleryAttributes();
        //chart1.getSeries().getItem(0).setVolume(80);
        //chart1.getSeries().getItem(1).setVolume(40);
        var axisY = chart_<%=queryId%>_<%=queryIndex%>.getAxisY();
        //axisY.setScaleUnit(0);
        axisY.setMax(axisY.getMax() * 1.1);
    }
    var items ;
</script>
<% 	Support.HTMLDbTransactionBeanWOPooling db = new Support.HTMLDbTransactionBeanWOPooling();
	db.myInitialize(application,session,request,response,out,con);
	db.setDBConnection(con);
	db.setQueryStatment(queryStmt); 
	Vector[] xx = db.getResultSetAsArrayofVectors() ;
  	int rowCounter = 0 ;
    Object[] columnNames = new String[displaySeriesCountM];  
    Object [] series = new Object[displaySeriesCountM] ;
 
if (! seriesInRows) {%>
<script>
    function populateDatAsSeriesInColumns(chart)
    {
        items = [
      <% 
      	rowCounter = 0 ;
      	columnNames = db.getColumnNames().toArray() ;

		for ( int rowCount = 0 ; rowCount<xx[0].size() ; rowCount++)
      	{
      		%>
			 <%= ( rowCount == 0 )? "" : "," %>
     		 {
         		<% for ( int  colCount = 0 ; colCount < displaySeriesCountM ; colCount++ ) 
         		  { 
         			String columnValue = (String) xx[colCount].elementAt(rowCount) ; 
         			try {AnchorTag at = new AnchorTag( columnValue , "a"); columnValue = at.getValue(); }
         			catch (Exception e){}
         			 
         			 columnValue = ((colCount==0)? "\"" : "" ) + columnValue + ((colCount==0)? "\"" : "" );
         			 Object columnName = columnNames[colCount] ;
          			try {AnchorTag at = new AnchorTag( (String)columnName , "a"); columnName = at.getValue(); }
         			catch (Exception e){}

         		  	%>
         		  	"<%=columnName%>" : <%=columnValue %> <%=(colCount +1 ==displaySeriesCountM )? "" : ","%>	
         		  	<%
         		  }
         		%>
      		  } 
      		<% 
      	} 
      	%>
        ];
       chart.setDataSource(items);

    }
    </script>
    <% } %>
   <%   
   if (seriesInRows) {%>
    <script>   
     function populateDatAsSeriesInRows(chart)
    {
        items = [
      <% 
      	int colCounter = 0 ;
        columnNames = db.getColumnNames().toArray() ;

		for ( int  colCount = 1 ; colCount < xx.length ; colCount++ )
      	{ 

			%> 
			 <%= ( colCount == 1 )? "" : "," %>
     		 {
     			"<%= columnNames[0]%>" : "<%= columnNames[colCount]%> " , 
         		<% 
         		try{displaySeriesCount = Integer.valueOf(request.getParameter("displaySeriesCount")) ; }
                catch(Exception e) {}
                displaySeriesCountM = displaySeriesCount ;
         		if (displaySeriesCountM > xx[0].size()  ){displaySeriesCountM = xx[0].size() ; }
         		for ( int rowCount = 0 ; rowCount < displaySeriesCountM ; rowCount++ ) 
         		  { 
         			String columnValue = (String) xx[colCount].elementAt(rowCount) ; 
         			try {AnchorTag at = new AnchorTag( columnValue , "a"); columnValue = at.getValue(); }
         			catch (Exception e){}
         			 
         			 columnValue = ((colCount==0)? "\"" : "" ) + columnValue + ((colCount==0)? "\"" : "" );
         			 String columnName = (String)xx[0].elementAt(rowCount) ;
           			try {AnchorTag at = new AnchorTag( columnName , "a"); columnName = at.getValue(); }
          			catch (Exception e){}
					series[rowCount + 1] = columnName ; 
         		  	%>
         		  	"<%=columnName%>" : <%=columnValue %> <%=(rowCount +1 ==xx[0].size() )? "" : ","%>	
         		  	<%
         		  }
         		%>
      		  } 
      		<% 
      	} 
      	%>
        ];
       chart.setDataSource(items);

    }
    
    </script>
    <% } %>
    <link rel="stylesheet" type="text/css" href="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/styles/Attributes/jchartfx.attributes.css" />
    <link rel="stylesheet" type="text/css" href="<%=appURL%>/jQueryAssets/jchartfx.7.2.5289/styles/Palettes/jchartfx.palette.css" />
    <style>
  		#resizable { border:1px solid red ; width: 500px; height: 500px; padding: 0.5em; }
  	</style>
 
</head>
<body class="jchartfx_body">
    <font size="1">
    Chart Type : <select onchange="changeGalary(this.value); ">
    	<option  value="Bar">Bars Chart </option>
    	<option  value="Lines">Lines Chart </option>
    	<option  value="Pie">Pie Chart </option>
    	<option  value="Gantt">Gantt Chart </option>
    	<option  value="Scatter">Scatter Chart </option>
    	<option  value="Area">Area Chart </option>
    	<option  value="Step">Step Chart </option>
    	<option  value="HiLowClose">HiLowClose Chart </option>
    	<option  value="Cube">Cube Chart </option>
    	<option  value="Bubble">Bubble Chart </option>
    	<option  value="CandleStick">CandleStick Chart </option>
    	<option  value="CurveArea">CurveArea Chart </option>
    </select>
   
    
    
    <input type="checkbox" checked="checked" selected = "false"  value="T" onchange=" chart_<%=queryId%>_<%=queryIndex%>.getDataGrid().setVisible(this.selected); this.selected = !this.selected ;"> Show DataGrid
	<a href = "<%=appURL%>/editAndExecute.jsp?id=<%=queryId%>&<%=request.getQueryString() %>">View Data</a>
	<a href = "" target = "ChartViewer" >Load in Stand alone Window </a> 
	<br>
	<div dir = "rtl" >
	 <% 
	 Object[] seriesList = columnNames ;
	  
	 if (seriesInRows) {seriesList = series ;  }
	 for ( int i = 1 ; i<= displaySeriesCount ; i++)
    {
    %> <%=seriesList[i]%>  <input id="Checkbox<%=i%>" type="checkbox" checked="CHECKED" onclick="onFilter(<%=i%>,Checkbox<%=i%>);" />   <% 	
    }
    %>
	</div>
	</font>    
    <script type="text/javascript">
    	function changeGalary(m_galary)
    	{
            // Axis Charts    	:Lines,Bar,Curve,Scatter,Area,Step,HiLowClose,Cube,Bubble,CandleStick,CurveArea, OpenHiLowClose, Pareto.
            // Non-axis charts	:Pie,Radar,DoughNut,Pyramid

    		if (m_galary=="Lines")
        	{  	chart_<%=queryId%>_<%=queryIndex%>.setGallery (cfx.Gallery.Lines) ;    	}
        	else if (m_galary=="Gantt")
        	{  chart_<%=queryId%>_<%=queryIndex%>.setGallery (cfx.Gallery.Gantt) ;   	}
        	else if (m_galary=="Pie")
        	{  	chart_<%=queryId%>_<%=queryIndex%>.setGallery (cfx.Gallery.Pie) ;   	}
        	else if (m_galary=="Bar")
        	{  	chart_<%=queryId%>_<%=queryIndex%>.setGallery (cfx.Gallery.Bar) ;   	}
        	else if (m_galary=="Scatter")
        	{  	chart_<%=queryId%>_<%=queryIndex%>.setGallery (cfx.Gallery.Scatter) ;  	}
        	else if (m_galary=="Area")
        	{  	chart_<%=queryId%>_<%=queryIndex%>.setGallery (cfx.Gallery.Area) ;   	}
        	else if (m_galary=="Step")
        	{  	chart_<%=queryId%>_<%=queryIndex%>.setGallery (cfx.Gallery.Step) ;   	}
        	else if (m_galary=="HiLowClose")
        	{  	chart_<%=queryId%>_<%=queryIndex%>.setGallery (cfx.Gallery.HiLowClose);	}
        	else if (m_galary=="Cube")
        	{  	chart_<%=queryId%>_<%=queryIndex%>.setGallery (cfx.Gallery.Cube) ;   	}
        	else if (m_galary=="Bubble")
        	{  	chart_<%=queryId%>_<%=queryIndex%>.setGallery (cfx.Gallery.Bubble) ;   	}
        	else if (m_galary=="CandleStick")
        	{  	chart_<%=queryId%>_<%=queryIndex%>.setGallery (cfx.Gallery.CandleStick) ;   	}
        	else if (m_galary=="CurveArea")
        	{  	chart_<%=queryId%>_<%=queryIndex%>.setGallery (cfx.Gallery.CurveArea) ;   	}

    	
    	}
    	var flag = false;
    	
    	function doHide(pointId, state) {

    		chart_<%=queryId%>_<%=queryIndex%>.getSeries().getItem(pointId - 1).getVisible() ? chart_<%=queryId%>_<%=queryIndex%>.getSeries().getItem(pointId - 1).setVisible(false) : chart_<%=queryId%>_<%=queryIndex%>.getSeries().getItem(pointId - 1).setVisible(true);
    		    		
    	}    	    	
    	function onFilter(pointId, checkBoxId) {
    	    checkBoxId.checked ? doHide(pointId, true) : doHide(pointId, false);
    	}
    </script>
    <div  class="jchartfx_container" align="center" dir="ltr">
        
        <div id="myDash" style="width:<%=chartWidth%>px;height:<%=chartHeight%>px;display:inline-block;position:relative">
            <div id="divChart_<%=queryId%>_<%=queryIndex%>" class="ui-widget-content" style="width:<%=chartWidth%>px;height:<%=chartHeight%>px;display:inline-block;position:absolute;left:0px;top:0px"></div>
        </div>
    
        <script language="javascript">
            $(document).ready(function ($) {
            	initialize(cfx.Gallery.<%=chartCategory%>);
            	$( "#resizable" ).resizable();
            	$( "#divChart_<%=queryId%>_<%=queryIndex%>" ).resizable();
            	
            	
            });
        </script>
   </div> 
</body>
</html>