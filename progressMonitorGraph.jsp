<HTML>
<HEAD>
<TITLE>Progress monitoring Graph</TITLE>
</HEAD>
<BODY>
<H2>Progress Monitoring Graph</H2>
<p><BR>
<%
  	java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
    Support.ConnParms selectedConnParms = (Support.ConnParms)session.getAttribute("selectedConnParms");
    
    boolean AutoLog    = ( request.getParameter("AutoLog") != null ) ; //----To Enable automatically read page contents 
    Support.XMLConfigFileReader supportConfig =  Support.Misc.getXMLConfigFileReader(false) ; 
    if (con == null)
    {
      if( AutoLog)
      { 
       //--TO be used at Production  ---
       // con = supportConfig.getConnParmsByName("MNSPDB3.SADAD.COM").generateConnection("shawky","redsea11");
       //--TO be used at DR  ---
       // con = supportConfig.getConnParmsByName("DR.SADAD.COM").generateConnection("shawky","redsea11");
       //--To Be Used with Local DB
       con = supportConfig.getConnParmsByName("Local DB").generateConnection("support","redsea11" , "NORMAL");
       //con= Support.ConnectionFactory().createConnection("localhost","orcl", "Shawky", "redsea11");      
      }
      else { session.setAttribute("queryString",request.getQueryString());
            response.sendRedirect("loginScreen.jsp");
           }
    }

    //-------------The follwoing Connection Used to get The Queries Where it may reside in a defrent DB
    java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");
    if( repCon == null && AutoLog )
    { 
       repCon = supportConfig.reposatoryConn.generateConnection();
    }
    java.util.Vector sqlBoundNames = (java.util.Vector)session.getAttribute("sqlBoundNames");
    java.util.Vector sqlBoundValues = (java.util.Vector)session.getAttribute("sqlBoundValues");
    if (sqlBoundNames == null){sqlBoundNames = new java.util.Vector(); sqlBoundValues = new java.util.Vector();}
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
     // if Auto login  use connection parameters for connection  no. 0 (Production)
     if (selectedConnParms == null) 
     {
        //Support.XMLConfigFileReader supportConfig =  Misc.getXMLConfigFileReader(false) ; 
         java.util.Vector conParms  = supportConfig.connParms ;
         selectedConnParms = (Support.ConnParms)conParms.elementAt(0) ;
     }
     // if Auto login  use connection parameters for connection  no. 0 (Production)

   //----------Adding also sqlBONVars specially for the selected connection----
	    java.util.Vector sqlBVs = selectedConnParms.getSqlBindVars();
    for (int k = 0 ; k< sqlBVs.size() ; k++)
     {
       Support.SqlBindVar currentSqlBV = (Support.SqlBindVar) sqlBVs.elementAt(k);
       sqlBoundNamesAfterInsert.add ("$$"+currentSqlBV.bindVarName);
       sqlBoundValuesAfterInsert.add(currentSqlBV.bindVarValue);
     }
    //----------Adding also sqlBONVars specially for the selected connection----
    
    Support.SqlReader sqlReader = null;
       
    //-------------Case Of reading the Queries from DB ----------------------

    if ( request.getParameter("id")!= null)  //request.getParameter("tableName")!= null &&
    {
        String id = request.getParameter("id");
        String QueryTableName = request.getParameter("tableName");
        if (QueryTableName != null) // QueryTableName is sent from the tree
        {
          session.setAttribute("QueryTableName",QueryTableName);
        }
        else  //---QueryTableName is not sent
        {
         QueryTableName = "LU_QUERIES";//session.getAttribute("QueryTableName").toString();
        }
      
     
   			  // -----------Checking Authority--------------------
  	  if (!AutoLog)
          {
          Support.LookupTreeV10 queriesTree = null;
  	  queriesTree = (Support.LookupTreeV10)session.getAttribute("queriesTree");
  	  Support.QueryTreeSecurity querySecurity = new Support.QueryTreeSecurity(queriesTree, con);
  	  querySecurity.checkAuthority( QueryTableName,Integer.parseInt(id), Support.QueryTreeSecurity.EXECUTE);  
          }
  	  //-----------------End Of checking Authority---------------
  	  sqlReader = new Support.SqlReader(repCon,QueryTableName , "QUERY_BODY",  id, session , request ); 
  	  }
  	  //-----------End of Case Of reading the Queries from DB ----------------------
  	  String[] queryStmt = sqlReader.getQueryStatments();
  	  String[] titles = sqlReader.getQueryTitles();
  	  int counter = sqlReader.getNoOfQueries();
  %> <!--APPLET CODE="Support.JAS.TestJAS" ARCHIVE="jas.jar" HEIGHT="800" WIDTH="800" ALIGN="bottom"-->
<APPLET CODE="Support.JAS.TestJAS" ARCHIVE="jas.jar, ojdbc14.jar"
	HEIGHT="600" WIDTH="1000" ALIGN="bottom"> <!-- The Following Information about DB at which Query will be executed -->
	<%
     String dbUrl = "";
     String dbDriver = "";
     
     boolean execAgainstRep = (request.getParameter("execAgainstRep") != null && request.getParameter("execAgainstRep").equals("Y"));
        if (execAgainstRep)
             { 
              
        	   dbUrl = repCon.getMetaData().getURL(); //jdbc:oracle:thin:@10.16.17.53:1521:SADAD
        	   dbDriver = repCon.getClass().toString();
          
               
             }
        else { dbUrl = selectedConnParms.url;
               dbDriver = selectedConnParms.driver; }
    %>
	<PARAM NAME="dbUrl" VALUE="<%=dbUrl%>">
	<PARAM NAME="dbDriver" VALUE="<%=dbDriver%>">
	<PARAM NAME="userName" VALUE="scott">
	<PARAM NAME="password" VALUE="tiger">

	<PARAM NAME="queryString" VALUE="<%=queryStmt[0]%>">
	<PARAM NAME="queryTitle" VALUE="<%=titles[0]%>">
	This browser does not appear to support Applets. </APPLET></p>
<p>Query Used in this Graph :</p>
<p><%=queryStmt[0]%></p>

<p>Executed at DB</p>
<%=dbUrl%>




</BODY>
</HTML>
