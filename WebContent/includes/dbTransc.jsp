<%@ page errorPage="../errorPage.jsp"%>
<%@ page import="java.util.* , Support.Misc"%>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
	
	<%
	  java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
	  java.sql.Connection repCon = (java.sql.Connection)session.getAttribute("repCon");

 	  request.setCharacterEncoding("UTF-8");
	  Support.XMLConfigFileReader supportConfig =  Misc.getXMLConfigFileReader(false ) ; //
	  //java.sql.Connection repCon = Support.XMLConfigFileReader.getRepostoryConnectionPool().getConnection();
	  Support.XMLConfigFileReader.getRepostoryConnectionPool().printInfor();
	   

	  if (con == null || repCon ==null )
	  { session.setAttribute("queryString",request.getQueryString());
	    response.sendRedirect("loginScreen.jsp");
	  }
	  //-----------if RollBack Pressed------------------------------
	  if (request.getParameter("RollBack") != null )
	  {
		 try{
		  Support.UserUnCommitedTransactions.rollBackConnection(con , session ) ;
		 repCon.setAutoCommit(false);
		 repCon.rollback();
		 %>
  			Transaction(s) Rolled Back  تم الغاء جميع التعديلات والإضافات بنجاح 
	 	 <% 
	 	} catch ( Exception e) {out.println(e); }
		 
	  }

	  //-----------if Commit Pressed------------------------------
	  if (request.getParameter("Commit") != null )
	  {
		 con.setAutoCommit(false);
		 Support.UserUnCommitedTransactions.commitConnection(con , session ) ; 
		 //con.commit();
		 repCon.setAutoCommit(false);
		 repCon.commit(); 
		 %>
  			Transaction(s) Committed  تم حفظ جميع التعديلات و الإضافات بنجاح 
		<%
		
	  }
	  //----------------------------------------------------------

	
	%>	 