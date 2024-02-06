<%@page import="java.util.*, java.io.* , java.sql.* "%>
<%@page import="Support.Misc"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="com.implex.database.map.services.*"%>
<%@page import="com.implex.database.map.*"%>
<%@page import="com.implex.database.PersistantObject"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="Support.UserUnCommitedTransactions"%>
<%@page import="com.smartValue.UnCommittedDbTransaction"%><html>
<%request.setCharacterEncoding("UTF-8");%>
<div align='center' >
<%  
  SecUsrDta  loggedUserObj = Support.Misc.getLoggedUserFromSession(session);
  String owner = request.getParameter("owner");
  String tableName = request.getParameter("tableName");
  int dotIndex =  tableName.indexOf(".");
  String tableowner = tableName.substring(0, dotIndex ).trim() ;
  String tableNameonly = tableName.substring(dotIndex +1 ) ;
  String rowIdValue =  request.getParameter("ROWID") ; 
  java.sql.Connection  con =null;
  boolean execAgainstRep = (request.getParameter("execAgainstRep") != null && request.getParameter("execAgainstRep").toString().equals("Y") );
  java.sql.Connection repCon = (java.sql.Connection)session.getAttribute("repCon");
  if (execAgainstRep)
  {
  con = repCon ; 
  }
  else
  {con = (java.sql.Connection)session.getAttribute("con");}
  
  java.sql.Statement stmt = con.createStatement();  
  if (dotIndex==-1) {throw new Exception ("Table Name in Your query does not include owner name..it should be in the form of 'OWNER.TABLE_NAME'. please specify");}
  TableMaintMaster tmm = Support.Misc.getTableMaintMaster(session , tableowner , tableNameonly) ;
  String uniqueColumnName = "rowId";
  String uniqueWhereClauseVarValue = "" ; 
  String uniqueWhereClauseVarName = "uniqueWhereClause" ; 
  
  if( con.getMetaData().getDatabaseProductName().equals("Microsoft SQL Server") )
  {
	  out.print(con.getMetaData().getDatabaseProductName());
	  uniqueColumnName = "ID";
  }
  boolean useRowIdValue = rowIdValue != null ;
  
  if ( useRowIdValue ) 
  {
	  String uniqueColumnValue = "'" + request.getParameter("ROWID") +"'";
	  uniqueWhereClauseVarValue = "t."+uniqueColumnName+" = " + uniqueColumnValue ; 
  }
  else 
  {
	  uniqueWhereClauseVarValue = request.getParameter(uniqueWhereClauseVarName) ;  
  }

   con.setAutoCommit(false);
   String loggedUser = Misc.getConnectionUserName(con) ;  
   boolean userCanDelete = false ;
   try 
	{
		userCanDelete = tmm.isUserCanDelete(loggedUser , con , rowIdValue );
	}
	catch (Exception e)
	{
		e.printStackTrace() ; 
		out.println("System Error : Unable to Check User Ability to Delete Due to : "+e.getMessage()+"") ;
	}
		if (! userCanDelete )
		{
			out.print( "<font color='FF0000' > You Are Not Authorized. <a href ='editAndExecute.jsp?id=25048&user_name="+loggedUser+"' target = 'User Roles' >Why?</a> غير مصرح لك بحذف هذا العنصر </font>" ) ; 
		}
		else 
		{
			//----Before Delete Operation ---------
		    com.smartValue.tableControllers.ItableTriggerController tableTriggers = null;
		   	Savepoint sp1 = con.setSavepoint(); 
			try{ 	tableTriggers = tmm.getTableTriggers();} catch (Exception e){}
			try 
			{
			  	if (tableTriggers != null) 
		  		{
					tableTriggers.setRepCon(repCon);
		  			tableTriggers.beforeDelete(con , rowIdValue ) ;
		  		}
				String deleteStmt = "delete from " + tableName + " t where "+uniqueWhereClauseVarValue ;
				com.implex.event.logging.Console.log(deleteStmt , this.getClass()) ; 
				// -- Audit The Delete Operation 
				boolean auditable = tmm.getAuditable().getBooleanValue() ;
				PersistantObject objInfo = null ; 
				if (auditable) // get Object information
				{
					objInfo = Support.Misc.getPersObjectByRowId(tmm.getModuleServiceContainer(), tableowner, tableNameonly, rowIdValue, null) ;
					objInfo.auditDelete(con); 
				}
				//---- End of the Audit Delete Operation 
				
			    //--Execute Delete Operation----- 
			    int x = stmt.executeUpdate(deleteStmt);
	
				// ----- After Delete trigger ------
			  	if (tableTriggers != null) 
		  		{
		  			tableTriggers.afterDelete(con , rowIdValue ) ;
		  		}
	 		    // Soring Operation in User Session
	 		    UnCommittedDbTransaction uct = new UnCommittedDbTransaction (tmm , request.getParameter("ROWID") , UnCommittedDbTransaction.DELETE_OPERATION) ; 
	 		    UserUnCommitedTransactions.getUnCommitedTransactions(session).add(uct) ;
	 		    out.print("تم حذف عدد ("+x+") سجل بنجاح <br> " + x + " Record(s) Deleted Successfully");
			}
			catch (Exception e ) {
				con.rollback(sp1) ;
				out.print("خطاء اثناء حذف العنصر نتيجة  : " + e.getMessage());
				out.print("<br>Unable To Delete This Object Due to Error : " + e.getMessage()); 
			}
		    return ;
		}
%>
</div>
