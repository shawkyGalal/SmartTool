<%@page errorPage="/errorPage.jsp"	import="Support.* "  contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");
  if( ( repCon == null || repCon.isClosed()) )
  { 
    XMLConfigFileReader aa =  Misc.getXMLConfigFileReader(false ) ; // new XMLConfigFileReader( false);
    repCon = aa.reposatoryConn.generateConnection();
    session.setAttribute("repCon",repCon);
  }
  java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
   Support.SqlReader sqlReader = null;
     
  String id = request.getParameter("id");
  String seq = request.getParameter("seq");
  if ( id != null)  
  {
    Support.LookupTreeV10 mainQueriesTree = (Support.LookupTreeV10) session.getAttribute("queriesTree");
    if (mainQueriesTree == null) 
	{	Misc.initializeMainSystemTrees(request, response, session, application , out);
		mainQueriesTree = (Support.LookupTreeV10) session.getAttribute("queriesTree"); 
	} 
      String pathLinks = "";
		try {
		pathLinks = mainQueriesTree.getPathLinks(id) ; 
		}
		catch( Exception e ) 
		{
			String errorMessage = "<br> غير مصرح لك للوصول لهذه الوحدة  "+id+" او هذه الوحدة غير موجودة بالنظام .برجاء التواصل مع مدير النظام" 
					+ "<br>System/Module You are Trying to Reach (Module ID : "+id+") is not found in the Main System Tree." 
					+ "<Br> Please Contact System Administrator to Provide Access to this System/Module " ;
			%>{error : <%=errorMessage%> }	<%
	  		out.flush();
	  		return ; 
		}  
		String htmlBodyAttribute = null ;
  	  try { sqlReader = new Support.SqlReader(repCon,"LU_QUERIES" , "QUERY_BODY",  id, session , request);  
  	  		}
  	  catch ( QueryParamValueNotFoundException e ) 
  	  {
  		%> {error : برجاء اختيار و تحديد القيم اعلاه ثم الضغط على "تنفيذ"  <%=e.getMissingParam() %> }	<%
  		out.flush();
  		return ; 
  	  }
		%><%=sqlReader.getQueryResultAsJSON(Integer.parseInt(seq), con)%><%
  }%> 
