<%@page import="com.implex.database.DbServices"%>
<%@page import="java.sql.* , Support.transactions.UserSessionUpdates"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.smartValue.tableControllers.ItableTriggerController" %>
<%@page import="com.implex.database.map.TableMaintMaster , Support.Misc" %>
<%@page import="com.implex.database.PersistantObject"%>
<%@page import="com.implex.database.DBKey"%>
<%@page import="com.implex.database.DbTable"%>


<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="java.net.URLDecoder"%>

<%@page import="java.net.URLEncoder"%>
<%@page import="com.implex.database.ApplicationContext"%>
<%@page import="com.smartValue.UnCommittedDbTransaction"%>
<%@page import="com.implex.database.map.TableMaintDetails"%>
<%@page import="com.implex.database.map.TableNotificationRule"%>
<%@page import="Support.UserUnCommitedTransactions"%>
<%@page import="Support.SqlReader"%>
<%@page import="com.implex.database.DirectJdbcServiceImpl"%><HTML lang="ar">
<BODY>

	<%
	  java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
	  java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");
	  if (con == null)
	  {response.sendRedirect("loginScreen.jsp?comeFrom=editQuery.jsp");}
	  
	  String tableName = request.getParameter("tableName");
	  String tableOwner = request.getParameter("tableOwner");
	  String rowIdValue = URLDecoder.decode( request.getParameter("rowIdValue") );

	  //--- Check if the Record Data is Locked By Approval Cycle or not -----------
	  
	  ArrayList<String> usersByPassLocking = new ArrayList<String>();
	  usersByPassLocking.add("JCCS") ; usersByPassLocking.add("ASAMIR") ;
	  String loggedUser = Misc.getConnectionUserName(con) ;
	  boolean loggedUserByPassLocking =  usersByPassLocking.contains(loggedUser) ; 

	  if (! loggedUserByPassLocking )
	  {
		  boolean isRecordLockedByApproval = Misc.isRecordLockedByApproval( tableOwner , tableName , rowIdValue , repCon ) ;
		  
		  if ( isRecordLockedByApproval ) 
		  {
		  out.print( "<font color='FF0000' > Data You are trying to update is Locked By Approval Cycle, Please Contact Person Approved it and ask him to Un Approve </font>" ) ; 
			return ;
		  }
	  }
	 //--- End of Check if the Record Data is Locked By Approval Cycle or not -----------
	  String columnName = request.getParameter("columnName");
	  
	  String newValue = "'"+ Support.Misc.repalceAll( request.getParameter("newValue") , "@@@" , "''") +"'";
	  newValue = URLDecoder.decode(newValue , "UTF-8") ; 
	  System.out.println(newValue);
	  boolean isDate = request.getParameter("isDate")!=null && request.getParameter("isDate").equalsIgnoreCase("true") ;
	  boolean isDateTime = request.getParameter("isDateTime")!=null && request.getParameter("isDateTime").equalsIgnoreCase("true") ;
	  boolean isHijriDate = request.getParameter("isHijriDate")!=null && request.getParameter("isHijriDate").equalsIgnoreCase("true") ;
	  if (isDate){newValue = " to_Date("+ newValue + ", '"+Support.SqlReader.getDateFormate()+"')" ; }
	  if (isDateTime){newValue = " to_Date("+ newValue.replace('T' , ' ') + ", 'yyyy-mm-dd hh24:mi')" ; }
	  if (isHijriDate){newValue = " to_Date("+ newValue + ", 'dd-mm-yyyy')" ; }
	  
	  String updateStatement = "Update " + tableOwner + "." + tableName + " Set " + columnName + "= " + newValue + " Where rowId = '" + rowIdValue + "'" ;
	  System.out.println(updateStatement);
	  UserSessionUpdates userSessionUpdates = (UserSessionUpdates)session.getAttribute("userSessionUpdates");
	
	  con.setAutoCommit(false);
	  //String loggedUser = Misc.getConnectionUserName(con) ; 
	  java.sql.Statement stmt = null; 
	  Savepoint save1 = con.setSavepoint();
      TableMaintMaster tmm = Support.Misc.getTableMaintMaster(session , tableOwner , tableName) ;
	  
	//Check if logged user can update 

	boolean userCanUpdate = false ;
	try 
	{
		userCanUpdate =tmm.isUserCanUpdate(loggedUser ,con , columnName , rowIdValue); 
	}
	catch (Exception e)
	{
		e.printStackTrace() ; 
		out.println("System Error : Unable to Check User Ability to Update Due to : "+e.getMessage()+"") ;
	}
	if (! userCanUpdate )
	{
		out.print( "<font color='FF0000' > You Are Not Authorized to update. غير مصرح لك بهذا التعديل <a href ='editAndExecute.jsp?id=25048&user_name="+loggedUser+"' target = 'User Roles' >Why لماذا ?</a> </font>" ) ; 
	}
	else 
	{	
				try
			  	{ 
			  		stmt = con.createStatement();
			  		String oldValue = "Unkown" ;
			  		String rowUiqueKeyValue = null;  
			  		boolean tableIsAuditable = tmm.getAuditable().getBooleanValue() ; 
		  			boolean objectIsCommited = true ;
			  		if ( tableIsAuditable ) 
			  		{
				  		try{
					  		PersistantObject po =  Misc.getPersObjectByRowId(session , tableOwner , tableName , rowIdValue) ;
					  		objectIsCommited = po!= null ; 
					  	    if ( objectIsCommited ) 
					  	    {
						  		DBKey objectUniqueKey = po.getUniqeKey() ; 
						  	  	rowUiqueKeyValue =( objectUniqueKey != null ) ? objectUniqueKey.getEqualSqlString() : "Object Has No Unique Key Defined" ;
						  		rowUiqueKeyValue = Misc.repalceAll( rowUiqueKeyValue , "'" , "$$$$" ) ;
						  		rowUiqueKeyValue = Misc.repalceAll( rowUiqueKeyValue , "$$$$" , "''" );
					  	    }
				  		}
			  			catch (Exception ex ) {ex.printStackTrace() ; }
			  			try{
			  				String columnNameForAuditing = columnName ; 
			  				if ( isDate || isDateTime || isHijriDate ){columnNameForAuditing = "to_char("+columnName+" , 'dd-mm-yyyy hh24:mi')" ; }
					  	    String oldValueStmt  = "Select "+ columnNameForAuditing + " From " + tableOwner + "." + tableName + " Where rowid = '" + rowIdValue + "'" ;
					  		ResultSet rs = stmt.executeQuery(oldValueStmt);
					  		rs.next(); 
					  		oldValue = rs.getString(1) ;
					  		rs.close(); 
			  			}
			  			catch(Exception ex ) {}
			  		}
				  	ItableTriggerController tableTriggers = null;
				  	try{ 	tableTriggers = tmm.getTableTriggers();} catch (Exception e){}
			
				  	if (tableTriggers != null) 
				  		{
							tableTriggers.setRepCon(repCon);
				  			tableTriggers.beforeUpdate(con , rowIdValue , columnName , newValue ) ;
				  		}
				  	int result = stmt.executeUpdate(updateStatement);
				  	if (tableTriggers != null) tableTriggers.afterUpdate(con , rowIdValue , columnName , newValue) ;
				  	if (tableIsAuditable && objectIsCommited)
				  	{
				  	// Start Auditing the update Operation
				  		String newValueForAuditing = newValue ;
				  		if ( isDate || isDateTime || isHijriDate ){newValueForAuditing = "to_char("+newValueForAuditing+" , 'dd-mm-yyyy hh24:mi') " ;} 
				  		
				  		String oldValueForAuditing = Misc.repalceAll( oldValue , "'" , "$$$$" ) ;
				  		oldValueForAuditing = Misc.repalceAll( oldValueForAuditing ,  "$$$$" , "''") ;
				  		if ( (oldValueForAuditing == null )|| ( oldValueForAuditing!= null && ! oldValueForAuditing .equalsIgnoreCase(newValueForAuditing)))
				  		{		  					  		
					  		String auditStmt = "Insert Into Support.audit_update_OPERATION "
					  				+ "\n\t        (TABLE_OWNER      , TABLE_NAME    , ROWID_VALUE , COLUMN_NAME  , OLD_VALUE , NEW_VALUE , ROW_UIQUE_KEY_VALUE , UPDATE_BY)" 
					  				+ "\n\t VALUES('"+tableOwner+"','"+tableName+"', '"+rowIdValue+"', '"+columnName+"', '"+oldValueForAuditing+"', "+newValueForAuditing+", '"+rowUiqueKeyValue+"', '"+loggedUser+"' ) " ;
					  		stmt.executeUpdate(auditStmt) ; 
				  		}
				  	}
				  	// If updating Table_Maint_Master Table itself  
				  	DbTable dbTable = null;
				  	
				  	if ( tableName.toUpperCase().equals(TableNotificationRule.DB_TABLE_NAME)
				  			|| tableName.toUpperCase().equals(TableMaintDetails.DB_TABLE_NAME) 
				  			|| tableName.toUpperCase().equals(TableMaintMaster.DB_TABLE_NAME)  // If updating Table_Maint_Master Table itself   
				  			)
				  	{
				  		String queryForTableName = null ;
				  		String tableOwnerColumnName = "TABLE_OWNER" ; 
				  		if ( tableName.toUpperCase().equals(TableNotificationRule.DB_TABLE_NAME) ) 
					  	{	
				  		   tableOwnerColumnName = "TABLE_OWNER" ;
					  	}
				  		else if (tableName.toUpperCase().equals(TableMaintDetails.DB_TABLE_NAME))
					  	{
				  			tableOwnerColumnName = "OWNER" ;
				  	    }
				  		else if (tableName.toUpperCase().equals(TableMaintMaster.DB_TABLE_NAME))
				  		{
				  			tableOwnerColumnName = "OWNER" ;
				  			
				  		}
  			  		    queryForTableName = "Select "+tableOwnerColumnName+" , TABLE_NAME from " + tableOwner + "." + tableName + " Where rowid = '" + rowIdValue + "'" ;
				  		Statement stmt2 = con.createStatement() ; 
				  		ResultSet rs = stmt2.executeQuery(queryForTableName) ;
				  		DbServices dbs =  Misc.getModuleServiceContainerFromUserSession(session).getDbServices(); 
				  		while (rs.next()){ dbTable = new DbTable(rs.getString(tableOwnerColumnName), rs.getString("TABLE_NAME") , dbs);}
				  		DirectJdbcServiceImpl.releaseResources(rs , stmt2 , null); 
				  		Misc.getModuleServiceContainerFromUserSession(session).getListOfUsedTables().remove(dbTable);
				  	}
				  	
				  	
				  					  	
				  	// Storing the operation in user session to apply the oncommit funtions if user commit his updates 
				  	ArrayList<com.smartValue.UnCommittedDbTransaction> dbOperations =( ArrayList<com.smartValue.UnCommittedDbTransaction> ) session.getAttribute(UserUnCommitedTransactions.dbOperationsNameInSession) ;
				  	if (dbOperations == null)
				  	{
				  		dbOperations = new ArrayList<com.smartValue.UnCommittedDbTransaction> () ; 
				  	}
				  	TableMaintDetails tmd = (TableMaintDetails) tmm.getTableMaintDetailss().getFirstFilteredPO(TableMaintDetails.COLUMN_NAME , columnName) ;
				  	UnCommittedDbTransaction unCommiterTrans = new UnCommittedDbTransaction(tmd  , rowIdValue , oldValue , URLDecoder.decode(request.getParameter("newValue"))) ;
				  	int index1 = dbOperations.indexOf(unCommiterTrans) ; 
				  	if ( index1 != -1 )
				  	{
				  		UnCommittedDbTransaction previousSimilarUpdateOper =  dbOperations.get( index1) ; 
				  		dbOperations.remove(previousSimilarUpdateOper) ;  
				  		unCommiterTrans.setOldValue(previousSimilarUpdateOper.getOldValue()) ; 
				  	}
				  	dbOperations.add( unCommiterTrans ) ; 
				  	session.setAttribute(UserUnCommitedTransactions.dbOperationsNameInSession , dbOperations) ; 
				  	// End of Storing the operation in user session to apply the oncommit funtions if user commit his updates
				  	 
					%><font color='00FF00'>Ok</font><%
				}
				catch (Exception e)
			  	{
				  	out.print("<font color='FF0000' >" + e.getMessage() + "</font>");
					if (con != null) 
					{
						try 
						{
							%>Transaction is being rolled back<%
							con.rollback(save1);
						} 
						catch(SQLException excep) { }
					}
			  	}
			  	finally
			  	{
			  		if (stmt != null)
			  		stmt.close();
			  	}
	}

	  %>
  
	</BODY>
</HTML>
