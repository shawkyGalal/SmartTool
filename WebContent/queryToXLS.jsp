<%@ page errorPage="errorPage.jsp"
	contentType="text/html;charset=UTF-8"
	import="java.util.*, java.io.* , java.sql.* , jxl.* , jxl.write.* , Support.Misc"%>
<HTML>
<TITLE>Query To XLS Sheet</TITLE>

<BODY  TEXT="blue" LINK="red">

<%
	java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
  if (con == null)
 {response.sendRedirect("loginScreen.jsp?comeFrom=queryToXLS.htm");}

  //-------------The follwoing Connection Used to get The Queries Where it may reside in a defrent DB
  java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");

  java.util.Vector sqlBoundNames = (Vector)session.getAttribute("sqlBoundNames");
  java.util.Vector sqlBoundValues = (Vector)session.getAttribute("sqlBoundValues");
  
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

  //----------End if Insert ----------------
  Support.SqlReader sqlReader = null;
     
  //-------------Case Of reading the Queries from DB ----------------------
  if ( request.getParameter("id")!= null)  //request.getParameter("tableName")!= null &&
  {
      String id = request.getParameter("id");
      String QueryTableName = "LU_QUERIES"; //request.getParameter("tableName");
   
		  // -----------Checking Authority--------------------
		  Support.LookupTreeV10 queriesTree = null;
		  queriesTree = (Support.LookupTreeV10)session.getAttribute("queriesTree");
		  Support.QueryTreeSecurity querySecurity = new Support.QueryTreeSecurity(queriesTree, con);
		  querySecurity.checkAuthority( QueryTableName,Integer.parseInt(id), Support.QueryTreeSecurity.EXECUTE);  
		  //-----------------End Of checking Authority---------------
		  sqlReader = new Support.SqlReader(repCon,QueryTableName , "QUERY_BODY",  id, session, request ); 
		  //-----------End of Case Of reading the Queries from DB ----------------------
		  String[] queryStmt = sqlReader.getQueryStatments();
		  String[] titles = sqlReader.getQueryTitles();
		  int counter = sqlReader.getNoOfQueries();
	  
		  session.setAttribute("queryStmt",queryStmt);
   	  //----------4 ------Excecuete The Query ------------------
	Statement stmt = null;
      boolean execAgainstRep = (request.getParameter("execAgainstRep") != null);
      if (execAgainstRep){stmt = repCon.createStatement();}
		  else { stmt = con.createStatement();}
      
		  ResultSet rs = null;
		  ResultSetMetaData rsmd = null;
		  int columnCount = 0;
		  String[] columnTypes = null;
      String[] columnNames = null;
  	  java.math.BigDecimal[] columnSums = null;
	  
		  int j = Integer.parseInt(request.getParameter("seq"));
	if (!queryStmt[j].trim().equals(""))
	{
 					try{
			  rs = stmt.executeQuery(queryStmt[j]);
            }
          catch (SQLException sqle)
           {  stmt.close(); 
            throw new Support.MyException("Unable to Execute the following Query " + queryStmt[j] + "  Due To the Following SQL Error " + sqle.getMessage(), sqle, 1, session.getServletContext().getRealPath(""));
           }
			 
			rsmd = rs.getMetaData();
			columnCount = rsmd.getColumnCount();
			columnTypes = new String[columnCount+1];
          columnNames = new String[columnCount+1];
      }

        String appPath = session.getServletContext().getRealPath("");//"D:\\MyWork\\JavaWork\\Jdev9iWS\\SmartTool\\public_html\\";
        //-------------Getting Column Names ------------------
        for (int count = 1 ; count<=columnCount ; count++ )
        { 
          columnTypes[count] = rsmd.getColumnTypeName(count);
          columnNames[count] =  rsmd.getColumnName(count);
        }
        //-------Writing Data to XLS Sheet --------------------
        String queryXLSTempFile = appPath+ File.separator+"xls"+ File.separator + id+"_temp.xls";
        File inputWorkbook = new File (queryXLSTempFile);
        String outFileStr = appPath+ File.separator+"xls"+ File.separator+Misc.getConnectionUserName(con)+"/"+id+".xls";
        File outputWorkbook = new File (outFileStr);
        Workbook w1 = null;
        WritableWorkbook w2 = null;
        try{
        w1 = Workbook.getWorkbook(inputWorkbook);
        w2 = Workbook.createWorkbook(outputWorkbook ,w1 );
        }
        //----------if Query Templete does not exist use the standard template 
        catch (Exception e) 
        {
        queryXLSTempFile = appPath+File.separator+"xls"+File.separator+"standard_temp.xls";
        inputWorkbook = new File (queryXLSTempFile);
        w1 = Workbook.getWorkbook(inputWorkbook);
        w2 = Workbook.createWorkbook(outputWorkbook ,w1 );
        }
        

        WritableSheet sheet = w2.getSheet(0);
        WritableCell cell = null;
        
        //----------Writing Query Title 
          cell = sheet.getWritableCell(0 , 0 );
          CellType  cellType = cell.getType();
          if ( cellType == CellType.LABEL)
          {
            Label l = (Label) cell;
            l.setString(titles[j]);
          }  
        //------End of Writing Query Title 
        int rowsOffset = 5; // -- Start writ data after rowsOffset
        //----------Wirting Column Names----------
        for (int coulmIndex = 0 ; coulmIndex < columnNames.length ; coulmIndex++ )
        {
          cell = sheet.getWritableCell(coulmIndex-1 , rowsOffset );
          cellType = cell.getType();
          if ( cellType == CellType.LABEL)
          {
            Label l = (Label) cell;
            l.setString(columnNames[coulmIndex]);
          }  
        }
        //-------End of Wirting Column Names----------       
       
        int rownum = rowsOffset;
        while (rs.next())
        { rownum++;
          String columnValue = "";
          for (int count = 1 ; count<=columnCount ; count++ )
          { 
            columnValue = "";
            //--------------------Getting The Column Value---------------
            try
            {
               //--------------If Column Type is CLOB 0r LONG -----
                columnValue= rs.getString(count);
                columnValue = (columnValue == null )? "": columnValue;
                //--------Removing The hyperLink if exist--------
                try {
                Support.HTML.AnchorTag at = new Support.HTML.AnchorTag(columnValue , "a") ;  
                boolean isAnchor = at.isHtmlTag("a");
                if (isAnchor){columnValue = at.getValue() ;}
                } catch(Exception e) {}
                
                //-------End of Removing The hyperLink if exist--------
            }
            catch (Exception e) { w1.close(); w2.close(); rs.close();   stmt.close(); throw e;}
            //--------------------Rendering The Column Value---------------
            {  
              cell = sheet.getWritableCell( count-1 , rownum);
              cellType = cell.getType();
              if ( cellType == CellType.LABEL)
              {
                Label l = (Label) cell;
                l.setString(columnValue);
              }  
              if ( cellType == CellType.NUMBER)
              {
                jxl.write.Number n = (jxl.write.Number) cell;
                if (columnValue != null && !columnValue.equals(""))
                 { n.setValue(Double.parseDouble(columnValue)); }
              }  
          
            }
            //------------------End of Rendering The Column Value---------------
          }

        }

      rs.close();
      stmt.close();
      //--------------Closing the Excel File -------------      
      w2.write();
      w2.close();
      
     }
     response.sendRedirect("xls/"+Misc.getConnectionUserName(con) + "/" + request.getParameter("id")+".xls");
%>
</BODY>
</HTML>