<?xml version='1.0' encoding='UTF-8'?>
<%@ page errorPage="errorPage.jsp"
	import="Support.*, java.util.* , java.sql.* "%>
<%
	request.setCharacterEncoding("UTF-8");
  boolean AutoLog  = ( request.getParameter("AutoLog") != null ) ; //----To Enable automatically read page contents 
  //-------------The follwoing Connection Used to get The Queries Where it may reside in a defrent DB
  java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");
  if( repCon == null && AutoLog )
  { 
    XMLConfigFileReader aa = new XMLConfigFileReader( false);
    repCon = aa.reposatoryConn.generateConnection();
  }
  java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
  if (con == null)
  {
    if( AutoLog)
    { 
     //--TO be used at Normal Production ---
     XMLConfigFileReader aa = new XMLConfigFileReader( false);
     //Misc abc = new Misc(repCon);
     String autologinDB =SysConfigParams.getAutoLogin_Environment_Name();// abc.getSystemParameterValue(8);
     String autoLoginUserName = SysConfigParams.getAutoLogin_Username();// abc.getSystemParameterValue(9);
     String autoLoginPassword = SysConfigParams.getAutoLogin_Password();// abc.getSystemParameterValue(10);
     con = aa.getConnParmsByName(autologinDB).generateConnection(autoLoginUserName, autoLoginPassword, "NORMAL");     
     }
    else { session.setAttribute("queryString",request.getQueryString());
          response.sendRedirect("loginScreen.jsp");
         }
  }  
  //----------End if Insert ----------------
  Support.SqlReader  sqlReader = null;
     
  //-------------Case Of reading the Queries from DB ----------------------
  if ( request.getParameter("id")!= null)  //request.getParameter("tableName")!= null &&
  {
      String id = request.getParameter("id");
      String QueryTableName = "LU_QUERIES"; //request.getParameter("tableName");
   
	  if (!AutoLog)
      {
      // -----------Checking Authority--------------------
		  Support.LookupTreeV10 queriesTree = null;
		  queriesTree = (Support.LookupTreeV10)session.getAttribute("queriesTree");
		  Support.QueryTreeSecurity querySecurity = new Support.QueryTreeSecurity(queriesTree, con);
		  querySecurity.checkAuthority( QueryTableName,Integer.parseInt(id), Support.QueryTreeSecurity.EXECUTE);  
		  //-----------------End Of checking Authority---------------
	  }
      	  sqlReader = new Support.SqlReader(repCon,QueryTableName , "QUERY_BODY",  id,  session , request ); 
		  //-----------End of Case Of reading the Queries from DB ----------------------
		  //String[] queryStmt = sqlReader.getQueryStatments();
		  //String[] titles = sqlReader.getQueryTitles();
		  int counter = sqlReader.getNoOfQueries();
	  
		  //session.setAttribute("queryStmt",queryStmt);
		  int j = Integer.parseInt(request.getParameter("seq"));
		  Connection execCon = con;
		  if (request.getParameter("execAgainstRep") != null)
		  execCon = repCon;
		  //----------4 ------Excecuete The Query ------------------	  
		  out.print(sqlReader.getResultAsXML(j ,execCon));
 
     //------If This Page accessed with Autologin , then close the connection
     if (AutoLog)
     {
      con.close();
     }
  }
%>