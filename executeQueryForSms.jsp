<%@page errorPage="errorPage.jsp" 	import="Support.*, java.util.*, java.io.* , java.sql.* "%><%@page  contentType="text/html;charset=UTF-8"%><%request.setCharacterEncoding("UTF-8");%>
<%String appURL = request.getRequestURL().substring(0,request.getRequestURL().indexOf(request.getServletPath()));
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
  Support.ConnParms selectedConnParms = (Support.ConnParms)session.getAttribute("selectedConnParms"); 
  String id = request.getParameter("id");
  String QueryTableName = request.getParameter("tableName");
  Support.SqlReader sqlReader= null;
  if ( id != null)  
  {
      if (QueryTableName != null) // QueryTableName is sent from the tree
      {
        session.setAttribute("QueryTableName",QueryTableName);
      }
      else  //---QueryTableName is not sent
      {
       QueryTableName = "LU_QUERIES";//session.getAttribute("QueryTableName").toString();
      }

      sqlReader = new Support.SqlReader(repCon,QueryTableName , "QUERY_BODY",  id, session , request ); 
	  // -----------Checking Authority--------------------
	  if (! AutoLog)
        {
        	Support.LookupTreeV10 queriesTree = null;
		  	queriesTree = (Support.LookupTreeV10)session.getAttribute("queriesTree");
		  	Support.QueryTreeSecurity querySecurity = new Support.QueryTreeSecurity(queriesTree, con);
			querySecurity.checkAuthority( QueryTableName,Integer.parseInt(id), Support.QueryTreeSecurity.EXECUTE);  
        }
	  //-----End Of checking Authority---------------
                
        if ( sqlReader.active.equals("F"))
         {
          throw new Exception ("Sorry : This Report is currently disabled, Please contact Application Support");
         }
	  }
		  
	  String[] queryStmt = sqlReader.getQueryStatments();
	  String[] titles = sqlReader.getQueryTitles();
	  int counter = sqlReader.getNoOfQueries();
	  
	  session.setAttribute("queryStmt",queryStmt);
	  //----------4 ------Excecuete The Query ------------------
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
	  
	  for (int j = 0 ; j< counter ; j++)
	  {
		if (!queryStmt[j].trim().equals(""))
		  { //--------if order by is required for the current Query statement ------------
            String orderByClause= "";
            if (request.getParameter("_QuerySeq") != null && Integer.parseInt(request.getParameter("_QuerySeq").toString()) == j  )
            {
                //-------if Query already contains order by, remove it 
              int orderByIndex = queryStmt[j].toLowerCase().indexOf("order by ") ;
              if ( orderByIndex!= -1 )
              { queryStmt[j] = queryStmt[j].substring(0,orderByIndex ); }
  
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
			try
            {
			  rs = stmt.executeQuery(queryStmt[j]+ "\n" + orderByClause);
            }
          catch (SQLException sqle)
           {  stmt.close(); 
            throw new Support.MyException("Unable to Execute the following Query <Br>" + queryStmt[j]+ "\n" + orderByClause + " <Br> Due To the Following SQL Error " + sqle.getMessage(), sqle, 1, session.getServletContext().getRealPath(""));
           }

          rsmd = rs.getMetaData();
			columnCount = rsmd.getColumnCount();
			columnTypes = new String[columnCount+1];
          columnNames = new String[columnCount+1];
			columnSums = new java.math.BigDecimal[columnCount+1];
			
			for (int count = 1 ; count< columnCount+1 ; count++ )
			 { 
			   columnSums[count] = new java.math.BigDecimal("0");
			 }
%><%=titles[j]%><%
        boolean rowIdFound = false;
        String tableName = "";
        String rowIdValue = null;
        String rowIdValueUrlEncoded = "";
        int rowIdIndex = 0 ;
      
        //----------Check if Oracle RowID included in the Query----------
        while (!rowIdFound &&  rowIdIndex < columnCount)
        {
          rowIdIndex++;
          rowIdFound = (rsmd.getColumnName(rowIdIndex).toString().toUpperCase().equals("ROWID") 
                        ||rsmd.getColumnName(rowIdIndex).toString().toUpperCase().equals("MYROWID") )? true:false;
        }
        if (rowIdFound) //----------If its Found .....
        {
          //---------getting Table Name for the given rowid -----------
          tableName = rsmd.getTableName(rowIdIndex);  //-----------Somtimes Failes to get the table Name ?? ??
        
          if (tableName.equals(""))                   //-----------If it Failed....----------
          {
            try
            {
              int tableNameStartIndex = queryStmt[j].toLowerCase().indexOf(" from ")+ " from ".length();
              int tableNameEndIndex = queryStmt[j].toLowerCase().indexOf(" where ");
              tableName = queryStmt[j].substring(tableNameStartIndex ,tableNameEndIndex );
          
              boolean isMultipleTables = (tableName.indexOf(",") == -1 )? false : true;
              if (isMultipleTables)
              { 
                tableName = tableName.substring(0, tableName.indexOf(",") ); 
              }
            }
            catch (Exception e) {throw new Exception ("Unable to Extract Table Name ");}
          }
        }
        //-------------Rendering Header Data------------------
        String queryString = request.getQueryString();
        int orderByIndexInRequest = queryString.indexOf("_Order");
        if (orderByIndexInRequest != -1 )
        {
         queryString= queryString.substring(0,orderByIndexInRequest-1);
        }

		out.println();
        for (int count = 1 ; count<=columnCount ; count++ )
        { 
          columnTypes[count] = rsmd.getColumnTypeName(count);
          columnNames[count] =  rsmd.getColumnName(count);
          %><%=Support.Misc.repalceAll(columnNames[count],"_"," ")%><%
	  	  out.print("\t\t");
         }
        //-------rendering Data--------------------
        int rownum = -1;
        String databaseName = con.getMetaData().getDatabaseProductName();
        boolean evenRow = true;
        out.println();

        while (rs.next())
        { rownum++;
          evenRow = !evenRow;
          String columnValue = "";
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
              //--------------For SQL SERVER Database---------
              if (databaseName.equals("Microsoft SQL Server"))
              {
                columnValue= rs.getString(count);
                columnValue = (columnValue == null )? "&nbsp;": columnValue;
              }
              //--------------For Oracle Database---------
              if (databaseName.equals("Oracle"))
              {
               if ( columnTypes[count].toUpperCase().equals("DATE") 
                    || columnTypes[count].toUpperCase().equals("NUMBER") 
                    || columnTypes[count].toUpperCase().equals("ROWID") 
                    || columnTypes[count].toUpperCase().equals("VARCHAR2") 
                    || columnTypes[count].toUpperCase().equals("RAW") 
                    || columnTypes[count].toUpperCase().equals("CHAR") 
                  ) 
               { 
                columnValue= rs.getString(count);
                columnValue = (columnValue == null )? "&nbsp;": columnValue;
               }
               //--------------If Column Type is CLOB 0r LONG --Or a Varchar with size more than maxDisplayableSize---
               if (columnTypes[count].toUpperCase().equals("BLOB") || columnTypes[count].toUpperCase().equals("CLOB")  ||columnTypes[count].toUpperCase().equals("LONG")|| ( columnValue.length() > maxDisplayableSize && columnValue.indexOf("</a>") == -1) )
               {
                 if (rowIdFound)
                 {
                  columnValue = "<a target= CLOBReader href = "+appURL+"/oracleCLOBReader.jsp?addXmlHeader=N&execAgainstRep="+ ( (execAgainstRep)? "Y":"N") +"&columnName="+columnNames[count]+"&rowId="+rowIdValueUrlEncoded+"&tableName="+tableName+">"+columnNames[count]+"</a>" ;
               
                }
                else { throw new Exception ("Query Contains CLOB Column. it Should include Rowid also");}
              }
            }
          }
          catch (Exception e) {  rs.close();   stmt.close(); throw e;}
            //--------------------Rendering The Column Value---------------
            %><%=columnValue %><%
            out.print("\t\t");
            //------------------End of Rendering The Column Value---------------
            boolean  isNumber = columnTypes[count].equals("NUMBER")||columnTypes[count].equals("int");
            boolean isHyberNumber = false;
            java.math.BigDecimal bd = new java.math.BigDecimal(0);
            if (isNumber)
            {
              bd = rs.getBigDecimal(count);
            }
            try
	    	{
		    	Support.HTML.AnchorTag at = new Support.HTML.AnchorTag(columnValue , "a") ; 
	            if (at.isHtmlTag("a") ) //-------if it is a hyperLinked Number 
	            {   bd = new java.math.BigDecimal(at.getValue());
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
          out.println();
        }
        //-------------Rendering Sum of Numeric Data------------------
        for (int count = 1 ; count<=columnCount ; count++ )
        { 
          %><%=columnSums[count]%><%
          out.print("\t\t");
        }
      }
  
      rs.close();
   
  }
    String loggedUser = Misc.getConnectionUserName(con);
    if (! (loggedUser.equals("SHAWKY"))  &&  ( ! loggedUser.equals("SFODA") ) )
    {
    System.out.println("User : " + loggedUser + " Executed Query No " + request.getParameter("id") + " Title: " + titles[0]);
    }
    stmt.close();
    if( AutoLog)
    {
    con.close();
    repCon.close();
    }
  %>