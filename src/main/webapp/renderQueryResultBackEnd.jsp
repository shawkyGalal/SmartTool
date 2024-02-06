<%@page import="Support.*, java.util.*, java.io.* , java.sql.* , java.text.SimpleDateFormat , javax.faces.model.SelectItem "%>
<%@page import="Support.event.resource.system.*"%>
<%@page import="com.implex.database.map.services.*"%>
<%@page import="com.implex.database.map.*"%>
<%@page import="com.sideinternational.sas.configuration.Configuration"%>
<%@page import="com.implex.database.PersistantObject"%>
<%@page import="com.smartValue.support.map.LuQueryDetails"%>

<%@page errorPage="errorPage.jsp"%>
<%@page  contentType="text/html;charset=UTF-8"%>	
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
          	  	         	  
          	    //----------------Excecuete The Query ---------------------
          	  Statement stmt = null;
                  boolean execAgainstRep = (request.getParameter("execAgainstRep") != null && request.getParameter("execAgainstRep").equalsIgnoreCase("Y"));
                  if (execAgainstRep)
                       {stmt = repCon.createStatement();}
                  else {stmt = con.createStatement(); }
                  
          	  ResultSet rs = null;
          	  ResultSetMetaData rsmd = null;
          	  int columnCount = 0;
          	  String[] columnTypes = null;
                  String[] columnNames = null;
          	  java.math.BigDecimal[] columnSums = null;
          	  boolean [] isNumericColumn ;
				
          	  	if (!queryStmt[queryIndex].trim().equals(""))
          		  { //--------if order by is required for the current Query statement ------------

            	    String queryIndexToFilterByRowId  = request.getParameter(SqlReader.QUERY_INDEX_TO_FILTER_BY_ROWID) ;
          		  	boolean filterByRowId = request.getParameter("filterByRowId")!=null;
            	    Integer queryIndexToFilterByRowIdInt = null; 
        	    	try{queryIndexToFilterByRowIdInt = Integer.parseInt(queryIndexToFilterByRowId);} 
        	    	catch(Exception e ) {}
					boolean skipQuery = filterByRowId && queryIndexToFilterByRowIdInt != null && queryIndexToFilterByRowIdInt != queryIndex ;
        	    	if ( skipQuery ){return ;}
          			String orderByClause= "";
                      if (request.getParameter("_QuerySeq") != null && Integer.parseInt(request.getParameter("_QuerySeq").toString()) == queryIndex )
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
                          //auditLogger.logMessage("User : " + Misc.getConnectionUserName(con) + " Executed "+ "\n" + queryStmt[j] );
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
          		com.implex.database.map.TableMaintMaster tmm = null ;
          		String genericObjectEditorPage = "tableEditor.jsp" ;
          		String tableObjectEditorPage = "";
          		boolean hasSpecicEditor = false ;
                if (rowIdFound) //----------If its Found .....
                {
                   tableName = sqlReader.estimateTableName(queryIndex);
                   tableOwner = sqlReader.estimateTableOwner(queryIndex);
                   tmm = Support.Misc.getTableMaintMaster(session , tableOwner , tableName ) ;
			  	   // tmm.getTableMaintDetailss().restore();
			  	   String specificObjectEditor = tmm.getObjectEditorJspPageValue() ;
			  	   hasSpecicEditor = !genericObjectEditorPage.equalsIgnoreCase(specificObjectEditor)  ;
			  	   tableObjectEditorPage =  (hasSpecicEditor)? specificObjectEditor : genericObjectEditorPage ;
			  	   SecUsrDta loggedUserDta  = (SecUsrDta)session.getAttribute("loggedUser") ;
			  	   tableObjectEditorPage = Misc.repalceAll(tableObjectEditorPage , "$$company_id" ,  loggedUserDta.getUsrCmpIdValue())  ;
                }
     %>
     

</html>