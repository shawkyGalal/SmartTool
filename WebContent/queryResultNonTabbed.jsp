<%@ page import="Support.*, java.util.*, java.io.* , java.sql.* "%>
<%@page import="Support.event.resource.system.*"%>
<%@page errorPage="errorPage.jsp"%>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>



<HTML>
<head>
<% String appURL = Support.Misc.getAppURL(request) ;  %>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=appURL%>/includes/AJAX_new.js"></script>
<script type="text/javascript" src="<%=appURL%>/includes/resizable-tables.js"></script>
<link rel="stylesheet" href="<%=appURL%>/jQueryAssets/jquery-ui.css">
<script src="<%=appURL%>/jQueryAssets/jquery-1.10.2.js"></script>
<script src="<%=appURL%>/jQueryAssets/jquery-ui.js"></script>

</head>
<title>Query result</title>
<body bgcolor="#FFEEFF">

<%
  boolean AutoLog  = ( request.getParameter("AutoLog") != null ) ; //----To Enable automatically read page contents 
  //-------------The follwoing Connection Used to get The Queries Where it may reside in a defrent DB
  java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");
  if( ( repCon == null || repCon.isClosed()) && AutoLog )
  { 
    XMLConfigFileReader aa = new XMLConfigFileReader( false);
    repCon = aa.reposatoryConn.generateConnection();
    session.setAttribute("repCon",repCon);
  }
  java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
  if (con == null || con.isClosed())
  {
    if( AutoLog)
    { 
     //--TO be used at Normal Production ---
     XMLConfigFileReader aa = new XMLConfigFileReader( false);
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

  

   Support.ConnParms selectedConnParms = (Support.ConnParms)session.getAttribute("selectedConnParms"); 
 
  
    Support.SqlReader sqlReader = null;
    boolean printable = ( request.getParameter("printable") != null  && request.getParameter("printable").equalsIgnoreCase("true") ); 
	String rqs = request.getQueryString() ;
	String printableStr = "&printable=" ;
	if (rqs != null )
	{
	if ( rqs.indexOf(printableStr + printable) == -1 ) rqs = rqs + printableStr + printable ;
	rqs = Misc.repalceAll(rqs , printableStr + printable , printableStr +! printable );
	}
     
  String id = request.getParameter("id");
  if ( id != null)  
  {
	  String QueryTableName = "LU_QUERIES";
	  session.setAttribute("QueryTableName",QueryTableName);
      /*
	  String QueryTableName = request.getParameter("tableName");
      if (QueryTableName != null) // QueryTableName is sent from the tree
      {
        session.setAttribute("QueryTableName",QueryTableName);
      }
      else  //---QueryTableName is not sent
      {
       QueryTableName = "LU_QUERIES";//session.getAttribute("QueryTableName").toString();
      }
      */
%>
<div align="center">
<table width="84%" border="0" align="left">
	<tr><div id="sysMessage"></div></tr>
	<% if (!printable)
	{ %>
	<tr>
		<td width="40%">
			<%
		    Support.LookupTreeV10 mainQueriesTree = (Support.LookupTreeV10) session.getAttribute("queriesTree"); 
			String pathLinks = "";
			pathLinks = mainQueriesTree.getPathLinks(id) ; 
			%>
			<%= pathLinks %>
		 </td>
		<td width="18%"><strong><font color="#FF0000" size="2"><em>(<%=Misc.getConnectionUserName(con)%>@<%=(selectedConnParms != null )? selectedConnParms.name : ""%>)</em></font></strong></td>
		<td width="82%">
		<div align="center">
		   <a href="?<%=request.getQueryString()%>"> Refresh</a>
		-- <a href = "?<%=rqs%>"> Printable Version</a>
		-- <a href = "queryResultWithEditableRS.jsp?<%=request.getQueryString() %>" title="Click To Update" >Editable  Version</a>
		</em></font>
		
		</div>
		</td>
	</tr>
	<%} %>
</table>
</div>
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
          	  
	          	request.setAttribute("sqlReader" , sqlReader);
//	    		request.setAttribute("con" , con);
//				request.setAttribute("repCon" , repCon);
    	         	  
          	  for (int queryIndex = 0 ; queryIndex<noOfQueries ; queryIndex++)
          	  { 
          	  	if (!queryStmt[queryIndex].trim().equals(""))
          		  { //--------if order by is required for the current Query statement ------------

            	    String queryIndexToFilterByRowId  = request.getParameter(SqlReader.QUERY_INDEX_TO_FILTER_BY_ROWID) ;
          		  	boolean filterByRowId = request.getParameter("filterByRowId")!=null;
            	    Integer queryIndexToFilterByRowIdInt = null; 
        	    	try{queryIndexToFilterByRowIdInt = Integer.parseInt(queryIndexToFilterByRowId);} 
        	    	catch(Exception e ) {}
        	    	if ( queryIndexToFilterByRowIdInt != null && queryIndexToFilterByRowIdInt != queryIndex){continue;}
					if (true) // this will replace the below code  till the end of the for loop
					{
					%>
						<jsp:include page="renderQueryResult.jsp">
		        			<jsp:param value="<%=queryIndex%>" name="queryIndex"/>
						</jsp:include>
					<%
					}
          		  }
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
<% if (! printable) 
{  %>
<div align="center">
<p><input name="viewQuery" value="View Query" type="button"
	onClick="window.open('<%=appURL%>/viewQueries.jsp','Query_Viewer','toolbar=0,menubar=0,location=0, width= 900 ,height=500 ');">
</p>
</div>
<jsp:include page="/footer.jsp" />
<%} %>
</body>
</html>
