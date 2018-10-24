<%@ page errorPage="../errorPage.jsp" 	import="java.util.*, java.sql.* , Support.Misc"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
	<%
		String appUrl = Support.Misc.getAppURL(request) ;
		java.sql.Connection con = (java.sql.Connection)session.getAttribute("con");
		String tableOwner = request.getParameter("tableOwner").toUpperCase();
		String tableName = request.getParameter("tableName").toUpperCase();
		String rowIdValue = request.getParameter("rowIdValue");

	//-----------if Delete Pressed------------------------------
	  if (request.getParameter("Delete.x") != null )
	  {
		    con.setAutoCommit(false);
		    String loggedUser = Misc.getConnectionUserName(con);   
		    java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");
			boolean userCanDelete = false ;
			Statement repStmt = null;
			ResultSet rs = null ; 
			try 
			{
				repStmt = repCon.createStatement() ;
				String qs = "select icdb.security.is_User_Can_Delete('"+loggedUser+"' , 'Table' , '"+tableOwner +"."+tableName +"') from dual  " ; 
				rs = repStmt.executeQuery(qs) ;
				System.out.println("Smart Tool Executed Query " + qs + "Succcessfully ") ;
				while (rs.next())
				{
					String canUpdateStr = rs.getString(1) ;  
					userCanDelete = ( canUpdateStr != null && canUpdateStr.equalsIgnoreCase("Y") )   ; 
				}
			}
			catch (Exception e)
			{
				e.printStackTrace() ; 
				out.println("System Error : Unable to Check User Ability to Update Due to : "+e.getMessage()+"") ;
				if (rs != null ) rs.close();
				if (repStmt != null ) repStmt.close() ; 
			}
			if (! userCanDelete )
			{
				out.print( "<font color='FF0000' > You Are Not Authorized. <a href ='"+appUrl+"/editAndExecute.jsp?id=25048&user_name="+loggedUser+"' target = 'User Roles' >Why?</a> </font>" ) ; 
			}
			else 
			{

			    Statement delStmt = con.createStatement();
			    String deleteStmt = "Delete from " + tableOwner +"."+ tableName + " where RowId = " + rowIdValue ;
			    System.out.println( deleteStmt ) ; 	
			    con.setAutoCommit(false);
			   	//stmt = con.createStatement();
			   	Savepoint sp1 = con.setSavepoint();
			    try {
			    	// --- Before Delete trigger -------
			    	com.implex.database.map.TableMaintMaster tmm = Support.Misc.getTableMaintMaster(session , tableOwner , tableName) ;
			  	    com.smartValue.tableControllers.ItableTriggerController tableTriggers = null;
					try{ 	tableTriggers = tmm.getTableTriggers();} catch (Exception e){}
				  	if (tableTriggers != null) 
			  		{
						tableTriggers.setRepCon(repCon);
			  			tableTriggers.beforeDelete(con , rowIdValue ) ;
			  		}
					//---Execute Delete Operation --------
			    	int x = delStmt.executeUpdate(deleteStmt);

					//-------After Delete trigger ---------
					if (tableTriggers != null) 
			  		{
						tableTriggers.setRepCon(repCon);
			  			tableTriggers.afterDelete(con , rowIdValue ) ;
			  		}

				    
				    out.print(x + "تم حذف العنصر بنجاح  Record(s)  Deleted Successfully");
				    return ;
				    }
			    catch (Exception e ) {con.rollback(sp1); throw e; }
			    finally { delStmt.close();}
			 }
	  }
	%>
<form action="" method="post" name = "<%=tableOwner%>_<%=tableName%>_DeleteRowForm">
	 <td width="30" >
			 <input type="image" src="<%=appUrl%>/images/deleterec.gif" 
				 	name="Delete" 
				 	style ="hight:25px; width:25px"
				 	title="Delete This Record حذف هذا السجل  "
				 	onclick="return confirm('Are you sure you want to Delete This Record? هل انت متأكد سوف يتم حذف هذا السجل ؟');"	>
	</td>
</form>
	