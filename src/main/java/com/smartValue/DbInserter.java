package com.smartValue;



import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import com.smartValue.database.map.SecUsrDta;

import Support.Misc;
import Support.UserUnCommitedTransactions;

public class DbInserter {
	
public static void insert(HttpSession session , HttpServletRequest request , HttpServletResponse response, JspWriter out) throws Exception
{
	  java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
	  java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");
	  String tableName = request.getParameter("tableName");
	  String owner = request.getParameter("owner");
	  String qs = "select t.* , t.rowid  from Support.table_maint_details t where upper(table_name) = upper('"+tableName+"') and upper(owner) = upper('"+owner+"') and column_included = 'Y' order by tab_index ";
	  com.smartValue.database.map.TableMaintMaster tmm = Misc.getTableMaintMaster(session , owner , tableName);  

	  SecUsrDta  loggedUser = (SecUsrDta)session.getAttribute("loggedUser") ;
	  int counter = 0;
	  
	  //-----------
	  con.setAutoCommit(false);

	  boolean userCanCreate = false ;
	  try { 
		  userCanCreate = tmm.isUserCanCreate( loggedUser.getUsrNameValue() , repCon );
	  }
	  catch(Exception e)
	  {
 		e.printStackTrace() ; 
		out.println("System Error : Unable to Check User Ability to Create Object Due to : "+e.getMessage()+"") ;
	  }
	  
	  if ( !userCanCreate )
	  {
		  out.print( "<font color='FF0000' > You Are Not Authorized. غير مصرح لك بإضافة هذا العنصر <a href ='editAndExecute.jsp?id=25048&user_name="+loggedUser.getUsrNameValue()+"' target = 'User Roles' >Why?</a> </font>" ) ;
	  }
	  else
	  {
	  java.sql.ResultSet rs1 = repCon.createStatement().executeQuery(qs);
	  String columnNames = "";
	  String columnValues = "";

	  while (rs1.next())
	  {
		  counter++;
		  String columnName = rs1.getString("COLUMN_NAME");
		  String columnValue =  request.getParameter(columnName);
		  
		  columnNames +=( (counter == 1)? "":"," ) + columnName;
		  String columnValueForrSql = columnValue ; 
		  int html_type = Integer.parseInt(rs1.getString("HTML_TYPE"));
		   if (html_type == 4 || html_type == 12) // Date 
		   {	
			   columnValueForrSql = " to_Date('"+columnValue+"','dd-mm-yyyy')";
		   }
		   else
		   {
			   if (columnValue != null)
			   columnValueForrSql = "'"+Misc.replaceSingleQuteWithDouble(columnValue)  +"'" ;
		   }

		  columnValues += ( (counter == 1)? "":"," ) + columnValueForrSql ;
	  }
	  
	  String insertStmt = "Insert into "+ owner + "." + tableName +" (" + columnNames + ") values (" + columnValues + ")"; 
	  System.out.println (insertStmt);

	  com.smartValue.tableControllers.ItableTriggerController tableTrigger = null ;
	  try { tableTrigger =  tmm.getTableTriggers();} catch ( Exception e ) {} 
	  java.sql.Savepoint sp = null;
	  java.sql.CallableStatement  insCallStmt ;
	  String rowIdString = null;
	  boolean insertSuccess = false ; 
	  try {
		  con.setAutoCommit(false);
		  sp =  con.setSavepoint() ; 
		  if (tableTrigger != null ) { tableTrigger.setRepCon(repCon); 
		  tableTrigger.beforeInsert(con); }
		  insCallStmt = con.prepareCall("begin " + insertStmt + " RETURNING ROWID INTO ? ; end;");
		  insCallStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.ROWID);
		  insCallStmt.executeUpdate();
		  oracle.sql.ROWID rowId = (oracle.sql.ROWID) insCallStmt.getObject(1);
		  rowIdString = rowId.stringValue();
		  if (tableTrigger != null ) tableTrigger.afterInsert(con , rowId.stringValue());
		    		  
		  insertSuccess = true ;
		  
		  if (tableName.equalsIgnoreCase("PAYMENT"))
		  {
		  	out.println ("<a href = 'javascript: window.close(); ' >إغلاق - Close </a>");
		  	return ; // Do Nothng
		  } 

	  }
	  catch (Exception e) 
	  { 

		Exception rollBackExc = null ; 
	  	try { con.rollback(sp);}
	  	catch (SQLException se ) 
	  	{
	  	 rollBackExc = new Exception("Unable to Rolle Back " + insertStmt + " \nDue to " + se.getMessage() + "A DDL May Be Injected " + "..Due to " + e.getMessage());
	  	}   
		Exception ex = new Exception("Unable to execute " + insertStmt + " \nDue to " + e.getMessage());
	  	request.setAttribute("exception" , (rollBackExc == null)? ex : rollBackExc );
	  	throw  (rollBackExc == null)? ex : rollBackExc ; 
	  	//%> 	<jsp:include page="errorPageHandling.jsp"></jsp:include>  	<% 
	  }
 	  
	  if ( insertSuccess )
	  {
		  	// Store transaction info in user session 
		  	ArrayList<com.smartValue.UnCommittedDbTransaction> dbOperations =( ArrayList<com.smartValue.UnCommittedDbTransaction> ) session.getAttribute(UserUnCommitedTransactions.dbOperationsNameInSession) ;
		  	if (dbOperations == null)
		  	{
		  		dbOperations = new ArrayList<com.smartValue.UnCommittedDbTransaction> () ; 
		  	}
		  	com.smartValue.UnCommittedDbTransaction oper = new com.smartValue.UnCommittedDbTransaction(tmm  , rowIdString , com.smartValue.UnCommittedDbTransaction.INSERT_OPERATION) ;
		  	dbOperations.add( oper ) ; 
		  	session.setAttribute(UserUnCommitedTransactions.dbOperationsNameInSession , dbOperations) ; 
		  // End of Store transaction info in user session
		  
		  //ToDo  implement the Controller of the MVC model ( Navigation Rules ) 
		  
		  String afterInsertView = (String) tmm.getAttributeValue("AFTER_INSERT_VIEW");
		  if (afterInsertView != null ) 
		  {
			  if (  loggedUser.getUsrNameValue().equalsIgnoreCase("ANONYM")
				 || loggedUser.getUsrNameValue().equalsIgnoreCase("GUEST") )
			  { UserUnCommitedTransactions.commitConnection(con , session); }
			  
			  com.smartValue.database.map.SecUsrDta loggedUser1 =  (com.smartValue.database.map.SecUsrDta) session.getAttribute("loggedUser");
			  boolean afterInsertViewContainsQuestionMark = afterInsertView.indexOf("?")>0 ; 
			  boolean isRedirectToGetClientyFile = afterInsertView.indexOf("getClientFile.jsp")>=0 ;
			  String redirecttTo = afterInsertView +((afterInsertViewContainsQuestionMark)? "&":"?") +"objectRowId="+rowIdString + "&_SelectedOraRowId="+rowIdString 
			                      + ( (isRedirectToGetClientyFile)? "&dirPath=DB_Attachs/"+owner+"/"+tableName+"/"+rowIdString  : "" )  ;
			  response.sendRedirect(redirecttTo);	
			  return ; 
		  }
		  
		  else if (tableName.equalsIgnoreCase("CONTRACT") && owner.equalsIgnoreCase("CARRENT"))
		  {
			  com.smartValue.database.map.SecUsrDta loggedUser1 =  (com.smartValue.database.map.SecUsrDta) session.getAttribute("loggedUser"); 
			  response.sendRedirect("Company/"+loggedUser1.getUsrCmpIdValue()+"/tableEditorContract.jsp?ROWID="+rowIdString );
		  }
		  else if (tableName.equalsIgnoreCase("CUSTOMER") && owner.equalsIgnoreCase("CARRENT"))
		  {
			  response.sendRedirect("TableInsertForm.jsp?tableName=CONTRACT&owner=CARRENT&IDSOURCE="+request.getParameter("IDSOURCE")+"&IDTYPE="+request.getParameter("IDTYPE")+"&IDNO="+request.getParameter("IDNO") );
		  }
		  
		  else
		  {
			  String queryStringForLastInsert_VarName = "queryStringForLastInsert" ; // Used to keep request Details in sesskion for subsequent insert for the same table and the same intial values
			  String queryStringForLastInsert = (String) session.getAttribute(queryStringForLastInsert_VarName) ; 
		  	 
		  	String outStr = " <p align='center'> " ; 
		  		   outStr += " تم إضافة العنصر بنجاح  Record Added Successfully.... " ; 
		  		   outStr += "<br><a href = 'javascript: closeWindow(); ' > إغلاق - Close </a> " ; 
		  		   outStr += "... <a href = 'TableInsertForm.jsp?"+queryStringForLastInsert+" >  إضافة عنصر جديد Add New Item</a>" ;
		  		   outStr += "</p>" ;
		  		   out.println(outStr);

		  } 

	  }
	}
}

}