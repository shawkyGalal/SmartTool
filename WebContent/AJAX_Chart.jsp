<%@ page errorPage="errorPage.jsp"
	contentType="text/html;charset=UTF-8"
	import="Support.*, Support.HTML.*, java.util.* "%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<title>Chart Demo (using Canvas painter)</title>
<script type="text/javascript" src="includes/excanvas.js"></script>
<script type="text/javascript" src="includes/chart.js"></script>
<script type="text/javascript" src="includes/canvaschartpainter.js"></script>
<link rel="stylesheet" type="text/css" href="includes/canvaschart.css" />
<style type="text/css">
html,body {
	border: none;
	padding: 0px;
	margin: 0px;
}
</style>
</head>
<body onload="demo();">

<%
  	//  String appURL = request.getRequestURL().substring(0,request.getRequestURL().indexOf(request.getServletPath()));
    boolean AutoLog  = ( request.getParameter("AutoLog") != null ) ; //----To Enable automatically read page contents 
    //-------------The follwoing Connection Used to get The Queries Where it may reside in a defrent DB
    java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");
    java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
    boolean conClosed =( con!=null && con.isClosed());
    if( repCon == null && AutoLog )
    { 
      XMLConfigFileReader aa = new XMLConfigFileReader( false);
      repCon = aa.reposatoryConn.generateConnection();
    }
    
    if (con == null || conClosed)
    {
      if( AutoLog)
      { 
       //--TO be used at Normal Production ---
       XMLConfigFileReader aa = new XMLConfigFileReader( false);
       //Misc abc = new Misc(repCon);
       String autologinDB =Support.SysConfigParams.getAutoLogin_Environment_Name();// abc.getSystemParameterValue(8);
       String autoLoginUserName =Support.SysConfigParams.getAutoLogin_Username();// abc.getSystemParameterValue(9);
       String autoLoginPassword =Support.SysConfigParams.getAutoLogin_Password();// abc.getSystemParameterValue(10);
       con = aa.getConnParmsByName(autologinDB).generateConnection(autoLoginUserName, autoLoginPassword , "NORMAL");     
       }
      else { session.setAttribute("queryString",request.getQueryString());
            response.sendRedirect("loginScreen.jsp");
           }
    }

    java.util.Vector sqlBoundNames = (Vector)session.getAttribute("sqlBoundNames");
    java.util.Vector sqlBoundValues = (Vector)session.getAttribute("sqlBoundValues");
    if (sqlBoundNames == null){sqlBoundNames = new Vector(); sqlBoundValues = new Vector();}
    //-------------Inserting the request Parameters and their values at the top of the sqlBoundNames & sqlBoundValues respecively--
    java.util.Vector sqlBoundNamesAfterInsert  = new java.util.Vector() ;
    java.util.Vector sqlBoundValuesAfterInsert = new java.util.Vector() ;
     
    java.util.Enumeration requestEnum =  request.getParameterNames();
    
    while ( requestEnum.hasMoreElements())
    {
     String parmName = requestEnum.nextElement().toString();
     if ( parmName.equals("id") || parmName.equals("tableName") ) {}
     else
     {
      sqlBoundNamesAfterInsert.insertElementAt("$$"+ parmName,0);
      sqlBoundValuesAfterInsert.insertElementAt(request.getParameter(parmName), 0);
     }
    }
    for (int i=0 ; i< sqlBoundNames.size() ; i++)
    {
      sqlBoundNamesAfterInsert.add(sqlBoundNames.elementAt(i));
      sqlBoundValuesAfterInsert.add(sqlBoundValues.elementAt(i));
    }
     Support.ConnParms selectedConnParms = (Support.ConnParms)session.getAttribute("selectedConnParms"); 
     // if Auto login  use connection parameters for connection  no. 0 (Production)
     if (selectedConnParms == null) 
     {
         Support.XMLConfigFileReader supportConfig =  Misc.getXMLConfigFileReader(false) ; 
         java.util.Vector conParms  = supportConfig.connParms ;
         selectedConnParms = (Support.ConnParms)conParms.elementAt(0) ;
     }
     // if Auto login  use connection parameters for connection  no. 0 (Production)

    //----------Adding also sqlBONVars specially for the selected connection----
    Vector sqlBVs = selectedConnParms.sqlBindVars;
    for (int k = 0 ; k< sqlBVs.size() ; k++)
     {
       Support.SqlBindVar currentSqlBV = (Support.SqlBindVar) sqlBVs.elementAt(k);
       sqlBoundNamesAfterInsert.add ("$$"+currentSqlBV.bindVarName);
       sqlBoundValuesAfterInsert.add(currentSqlBV.bindVarValue);
     }
    //----------Adding also sqlBONVars specially for the selected connection----
    
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
<div align="center">
<table width="84%" border="0" align="left">
	<tr>
		<td width="18%"><strong><font color="#FF0000" size="2"><em>(<%=Misc.getConnectionUserName(con)%>@<%=(selectedConnParms != null )? selectedConnParms.name : ""%>)</em></font></strong></td>
		<td width="82%">
		<div align="center"><a
			href="AJAX_Chart.jsp?<%=request.getQueryString()%>"> Refresh</a></div>
		</td>
		<td width="82%">
		<div align="center"><a
			href="executeQuery.jsp?<%=request.getQueryString()%>">Show Data</a></div>
		</td>
	</tr>
</table>
</div>
<br>
<%
        	// -----------Checking Authority--------------------
        	  if (! AutoLog)
                {
                Support.LookupTreeV10 queriesTree = null;
        	  queriesTree = (Support.LookupTreeV10)session.getAttribute("queriesTree");
        	  Support.QueryTreeSecurity querySecurity = new Support.QueryTreeSecurity(queriesTree, con);
        	  querySecurity.checkAuthority( QueryTableName,Integer.parseInt(id), Support.QueryTreeSecurity.EXECUTE);  
                }
        	  //-----End Of checking Authority---------------
        	  sqlReader = new Support.SqlReader(repCon,QueryTableName , "QUERY_BODY",  id, session , request); 
        	  }
           		  String[] queryStmt = sqlReader.getQueryStatments();
        	  String[] titles = sqlReader.getQueryTitles();
        	  int counter = sqlReader.getNoOfQueries();
                
                Support.HTMLDbTransactionBeanWOPooling xxx = new HTMLDbTransactionBeanWOPooling();
                xxx.setDBConnection(con);
                int seq = Integer.parseInt(request.getParameter("seq").toString());
                xxx.setQueryStatment(queryStmt[seq]);
                Vector[] data = xxx.getResultSetAsArrayofVectors();
                Vector columnNames = xxx.getColumnNames();
                if (AutoLog)
                {
                con.close();
                }
        %>
<strong><br><%=titles[seq]%></strong>
<div id="chart" class="chart" style="width: 1000px; height: 200px;"></div>
<script type="text/javascript">
    	function draw() {
    		var c = new Chart(document.getElementById('chart'));
        var CHART_LINE    =  1;
        var CHART_AREA    =  2;
        var CHART_BAR     =  3;
        var CHART_STACKED =  4;
        c.setDefaultType(CHART_AREA | CHART_STACKED);
				c.setGridDensity(<%=data[0].size()%>, 5 );
				c.setVerticalRange(0, 5);

        <%  //  'mon', 'tue', 'wed', 'thu', 'fri'
        String dataset = "";
        for (int i = 0 ; i< data[0].size() ; i++)
        { 
         dataset += "'" + data[0].elementAt(i)+ "'";
         if (i != data[0].size()-1 ) {dataset +=",";}
        }
        %>
				c.setHorizontalLabels([<%=dataset%>]);
        <%
        String[] colors = {"'#0000FF'" , "'#00FF00'" , "'#FF0000'" , "'#00FFFF'" , "'#FF00FF'", "'#FFFF00'" };
        // -- Starting from the second column
         for (int i = 1 ; i<columnNames.size() ; i++ )
        {
         dataset = "";          
          for (int j = 0 ; j< data[i].size() ; j++)
          { 
            //-----------If data contains Anchors hyperlinks remove it
            //AnchorTag at =  new AnchorTag(data[i].elementAt(j).toString());
            String x= "";
            if ( data[i].elementAt(j) != null &&  AnchorTag.isAnchor(data[i].elementAt(j).toString()) )
            {
              x = new Support.HTML.AnchorTag(data[i].elementAt(j).toString()).getValue(); // remove it 
              dataset += "'"+ x+ "'";
            }
            else 
            {
             dataset += "'"+ data[i].elementAt(j)+ "'";
            }
            if (j != data[i].size()-1 ) {dataset+=",";}
          }
          String colorCode = "'#"+i*2+"0"+i*2+"0FF'";
          out.print(" c.add('"+columnNames.elementAt(i).toString()+"', "+colors[i-1] +" , ["+dataset+"]);");
        }
/*
        c.add('Innocent',        '#8080FF', [ 8,  7, 12, 20, 24, 16, 36, 28, 28, 45]);
				c.add('Missed Spam',     '#A5A5FF', [ 8,  7, 12, 20, 24, 16, 36, 36, 18,  5]);
				c.add('False Positives', '#DEDEFF', [ 1,  2,  3,  2,  1,  4, 18, 12,  8,  7]);
*/        
        %>
				c.draw();
    	}
    	
    	window.onload = function() {
    	draw();
    	};
    </script>
</body>
</html>
