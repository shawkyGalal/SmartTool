<%@page import="Support.*, java.util.*, java.io.* , java.sql.* , java.text.SimpleDateFormat , javax.faces.model.SelectItem "%>
<%@page import="Support.event.resource.system.*"%>
<%@page import="com.implex.database.map.services.*"%>
<%@page import="com.implex.database.map.*"%>
<%@page import="com.sideinternational.sas.configuration.Configuration"%>
<%@page import="com.implex.database.PersistantObject"%>

<%@page errorPage="errorPage.jsp"%>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>
		<%
			Connection con = (Connection)session.getAttribute("con");
			Connection repCon = (Connection)session.getAttribute("repCon");

			boolean printable = ( request.getParameter("printable") != null  && request.getParameter("printable").equalsIgnoreCase("true") ); 			
			boolean AutoLog  = ( request.getParameter("AutoLog") != null ) ; //----To Enable automatically read page contents
	  	    if( ( repCon == null || repCon.isClosed()) && AutoLog )
			{ 
			    XMLConfigFileReader aa =  Misc.getXMLConfigFileReader(false ) ;
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
			String appURL = request.getRequestURL().substring(0,request.getRequestURL().indexOf(request.getServletPath()));
			String id = request.getParameter("id");

			Support.SqlReader sqlReader = (Support.SqlReader)request.getAttribute("sqlReader");
			if (sqlReader == null) 
			{
	          sqlReader = new Support.SqlReader(repCon,request.getParameter("lookupTableName") , "QUERY_BODY",  id, session , request , true); 
			}
	
			int queryIndex = Integer.parseInt( request.getParameter("queryIndex"));
			String logResult =sqlReader.ifDataFoundSendQueryResultByMail(queryIndex , con );    // -1 to send all queries 
			out.print(Misc.repalceAll(logResult  , "\n" , "<br>")) ;
%>