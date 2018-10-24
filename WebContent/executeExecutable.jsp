<%@page import="com.implex.database.map.SecUsrDta"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*, java.io.* , java.sql.*  "%>
<%@page import="Support.MyException , Support.Misc"%>
<%@page import="com.sideinternational.sas.event.logging.Logger"%>
<%@page import="com.sideinternational.sas.event.resource.debug.EventDbug9998"%><html>

<title>Excutable result</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<body>
<%
	String appUrl = Support.Misc.getAppURL(request) ;
	SecUsrDta loggedUser = Support.Misc.getLoggedUserFromSession(session) ; 
	boolean SmartToolAdmin = loggedUser.isSmartToolAdmin() ;
	if (! SmartToolAdmin)
	{
		// throw new MyException("Smart Tool Amin Only can perform this Function <a href =checkMyPassword.jsp >Are You Admin?</a>", null , MyException.LOG_EXCEPTION_MESSAGE , session.getServletContext().getRealPath("")); 
	}
  java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");

  //-------------The follwoing Connection Used to get The Queries Where it may reside in a defrent DB
  java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");
  Support.SqlReader sqlReader = null;
  String id = request.getParameter("id");
  if ( request.getParameter("id")!= null)  //request.getParameter("tableName")!= null &&
  {
	  String execTableName = "lu_executables";//request.getParameter("tableName");
	  %><div align="center">
<%
		// -----------Checking Authority--------------------
	  Support.LookupTreeV10 executeTree = null;
	  executeTree = (Support.LookupTreeV10)session.getAttribute("executeTree");
	  //Support.ExecTreeSecurity execSecurity = new Support.ExecTreeSecurity(executeTree, con);
	  //execSecurity.checkAuthority( execTableName,Integer.parseInt(id), Support.ExecTreeSecurity.EXECUTE);  
	  //-----------------End Of checking Authority---------------
	  sqlReader = new Support.SqlReader(repCon, execTableName , "EXEC_BODY" ,  id, session , request ); 
  }

  String[] execStmt = sqlReader.getQueryStatments();
  String[] titles = sqlReader.getQueryTitles();
  int counter = sqlReader.getNoOfQueries();
  
  session.setAttribute("execStmt",execStmt);
  //----------4 ------Excecuete The Query ------------------
  application.getAttribute("");
  
  java.sql.CallableStatement callStmt = null;
  java.sql.Statement stmt = null;
  boolean errorHappened = false ;
  boolean userCanExec = false ;
  //String loggedUser = Misc.getConnectionUserName(con);
  try 
  { userCanExec = sqlReader.isUserCanExecute(loggedUser.getUsrNameValue());}
  catch (Exception e)
  {
 		e.printStackTrace() ; 
		out.println("System Error : Unable to Check User Ability to Execute Service Due to : "+e.getMessage()+"") ;
  }
  if (! userCanExec)
  {
	  out.print( "<font color='FF0000' >غير مصرح لك ...برجاء مراجعة مدير النظام You Are Not Authorized to Use This Service Process. <a href ='"+appUrl+"/editAndExecute.jsp?id=25048&user_name="+loggedUser.getUsrNameValue()+"' target = 'User Roles' >Why?</a> </font>" ) ;	  
  }
  else
  {
  con.setAutoCommit(false);
  Savepoint sv1 =  con.setSavepoint() ;
  
  for (int j = 0 ; j< counter ; j++)
  {
    if (!execStmt[j].trim().equals(""))
      {
 
      ResultSet rs = null;
     
  	  
      try
      {
    	  
       //rs = sqlReader.executeExecutable(j , con) ;
       con.setAutoCommit(false);
       callStmt = con.prepareCall(execStmt[j]);
       stmt = con.createStatement();
       int updatecount = 0;
       boolean haveResultSet = false;
   		try{ 
   			callStmt.registerOutParameter(1 , oracle.jdbc.OracleTypes.CURSOR);
   			haveResultSet  =  callStmt.execute();
    		   
   			rs = (ResultSet) callStmt.getObject(1);
   			updatecount = callStmt.getUpdateCount();
   			}
   		catch (Exception e)
   			{ 
   				System.out.print(e.getMessage());
   				System.out.println(execStmt[j]);
   				stmt.execute(execStmt[j].trim()); 
  				updatecount = stmt.getUpdateCount();
  				rs = stmt.getResultSet();
   			}
		   //---------Audit the Service Execute Operation ----
	   		String auditStr = "Insert Into Support.AUDIT_SERVICE_EXECUTE (service_id , SERVICE_INDEX , sql_text) values ("+id+" , "+j+", '"+Misc.replaceSingleQuteWithDouble(execStmt[j])+"' )" ;
		   con.createStatement().execute(auditStr) ;
		   //--- End of Audit the Service Execute Operation ----
   		if (rs == null)
            {
		         if (updatecount >=0 )
	             {
		        	 %> <br> ( <%=updatecount %> )  Records Updated"  <% ;
		         }
	             else
	             {
	            	  
	            	   %><br> تم تنفيذ العملية بنجاح  Action Executed Successfully 
	            	   <% if (SmartToolAdmin)  {%>
	            	   <br> Command <br> <%= execStmt[j]%>
	            	   <% }
	             }
            }
            else 
            {
            	%><table border="1"><%
	            ResultSetMetaData rsmd = rs.getMetaData();
	            int columnCount = rsmd.getColumnCount();
	            String[] columnTypes = new String[columnCount+1];
	            java.math.BigDecimal[] columnSums = new java.math.BigDecimal[columnCount+1];
	               
	            %><tr><%
	            for (int count = 1 ; count<=columnCount ; count++ )
	            { 
	              	columnSums[count] = new java.math.BigDecimal("0");
	               	columnTypes[count] = rsmd.getColumnTypeName(count);
				 	%><td><strong> <%=rsmd.getColumnName(count)%> </strong></td><%
	            }
	            %></tr><%
	            //-------rendering Data--------------------
	            while (rs.next())
	            { 
	             %><tr><%
	             String columnValue = "";
	             for (int count = 1 ; count<=columnCount ; count++ )
	             { 
	               try{
	               columnValue = (rs.getString(count) == null )? "&nbsp;": rs.getString(count);
	               }
	               catch (Exception e) {  rs.close();   callStmt.close(); throw e;}
	               %><td><%=columnValue%></td><%
	               if (columnTypes[count].equals("NUMBER")) //-------------if Column Type is Number 
	               {
	                if (rs.getBigDecimal(count) != null)
	                {
	                 columnSums[count]=  rs.getBigDecimal(count).add(columnSums[count]);
	                }
	               }
	              }
	                
	              %></tr><%
	            }
	              %><table><%
	               rs.close();
            	}
           
           //---------------------Loging The Action in today Action logger File -------------------
          	  // Using SAS Logging Framwork
         	  new EventDbug9998("User : " + Misc.getConnectionUserName(con)+ " Executed the Following : \n\t" + execStmt[j] );
             //Support.Logger logger = new Support.Logger(request.getRealPath(""), 1);
             //logger.logMessage("User : " + con.getMetaData().getUserName() + " Executed the Following : \n\t" + execStmt[j] );
             //logger.close();
          //-----------------End Loging The Action in today Action logger File -------------------
      }
      catch (Exception sqle)
      {
    	con.rollback(sv1);
    	errorHappened = true ;
    	if (callStmt != null) callStmt.close();
    	Exception ex = 	new Support.MyException(Misc.getConnectionUserName(con) + ": Unable to Execute the following " + execStmt[j] + "  Due To the Following SQL Error " + sqle.getMessage(), sqle, 1, session.getServletContext().getRealPath(""));
    	request.setAttribute("exception" , ex); 
    	%> <jsp:include page="errorPageHandling.jsp">
    	   	<jsp:param value="N" name="showDetails"/>
    	   </jsp:include> <% 

        //-------Throw Exception with Logging------------------------
        //throw new Support.MyException(con.getMetaData().getUserName() + ": Unable to Execute the following " + execStmt[j] + "  Due To the Following SQL Error " + sqle.getMessage(), sqle, 1, session.getServletContext().getRealPath(""));
      }
      finally{
      	callStmt.close();    	  
      }

      //-------------Rendering Header Data------------------
  
      %>
	</table>
	<%
      }
  }
 
  // boolean value to tel the page that it is imbedded inside another page 
  	boolean imbedded = request.getParameter("imbedded") != null ;
  	if (! imbedded)
  	{
  		%> <p align="center"> <a href="javascript: self.close()">Close Window</a> </p> <%		
  	}
  
  	if (!errorHappened) 
  	{ 
  		 %><%@ include file="./includes/toolBarButtons.jsp"%><%
  	}
  }
 %>
	<div align="center">
		<p><a href = "editAndExecute.jsp?id=25034">Check Code Generated</a></p>
	</div>
</body>
</html>
