<%@ page errorPage="errorPage.jsp"
	import="Support.*, java.util.*, java.io.* , java.sql.* "%>

<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>

<%@page import="Support.event.resource.system.* ,com.smartValue.database.map.SecUsrDta"%>


<%@page import="com.smartValue.support.map.LuQueries"%><HTML>
<head>
<% String appURL = Support.Misc.getAppURL(request) ;  %>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=appURL%>/includes/AJAX_new.js"></script>
<script type="text/javascript" src="<%=appURL%>/includes/resizable-tables.js"></script>
<script type="text/javascript" src="<%=appURL%>/jQueryAssets/jquery-1.8.3.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=appURL%>/jQueryAssets/jquery-1.10.2.js"></script>
<script type="text/javascript" src="<%=appURL%>/jQueryAssets/jquery-ui.js"></script>
<script type="text/javascript" src="<%=appURL%>/jQueryAssets/jquery-ui-1.9.2.tabs.custom.min.js" type="text/javascript"></script>

<link href="<%=appURL%>/jQueryAssets/jquery.ui.core.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=appURL%>/jQueryAssets/jquery-ui.css">
<link rel="stylesheet" href="<%=appURL%>/includes/smartTool.css">
<link href="<%=appURL%>/jQueryAssets/jquery.ui.theme.min.css" rel="stylesheet" type="text/css">
<link href="<%=appURL%>/jQueryAssets/jquery.ui.tabs.min.css" rel="stylesheet" type="text/css">
<% 
SecUsrDta loggedUserDta  = Misc.getLoggedUserFromSession(session);
if (loggedUserDta.isUsrLangArabic())
{
	%><link rel="stylesheet" href="<%=appURL%>/includes/smartTool_ar.css"> <%
}
%>
       
<%@ include file="includes/jquery_commons.js"%>
<script type="text/javascript">
$(window).load(function() {
	$("#spinner").fadeOut("slow");
});
</script>
</head>
<title>Query result</title>

	<div id="spinner"  style=" position: fixed; left: 0px;	top: 0px; width: 100%;	height: 100%;	z-index: 9999;
		background: url('images/ajaxLoading.gif') 50% 50% no-repeat rgb(249,249,249) "> Please Wait....
	</div>
<%
  boolean AutoLog  = ( request.getParameter("AutoLog") != null ) ; //----To Enable automatically read page contents 
  //-------------The follwoing Connection Used to get The Queries Where it may reside in a defrent DB
  java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");
  if( ( repCon == null || repCon.isClosed()) && AutoLog )
  { 
    XMLConfigFileReader aa =  Misc.getXMLConfigFileReader(false ) ; // new XMLConfigFileReader( false);
    repCon = aa.reposatoryConn.generateConnection();
    session.setAttribute("repCon",repCon);
  }
  java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
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
			Cookie cookie = Misc.getCookiByName ( request , Misc.LoginScreenPageNameCookiVarName) ; 
			String loginScreenPage = (cookie!= null) ? cookie.getValue() : "loginScreen.jsp" ; // session.getAttribute("loginScreenPage") ;
    		response.sendRedirect(loginScreenPage);
         }
  }

  

   Support.ConnParms selectedConnParms = (Support.ConnParms)session.getAttribute("selectedConnParms"); 
 
  
    Support.SqlReader sqlReader = null;
    boolean printable = ( request.getParameter("printable") != null  
    						&& (  request.getParameter("printable").equalsIgnoreCase("true") 
    							|| request.getParameter("printable").equalsIgnoreCase("Y")
    							)
    					);
     
  String id = request.getParameter("id");
  if ( id != null)  
  {
      String QueryTableName = request.getParameter("tableName");
      if (QueryTableName != null) // QueryTableName is sent from the tree
      {
        session.setAttribute("QueryTableName",QueryTableName);
      }
      else  //---QueryTableName is not sent
      {
       QueryTableName = "LU_QUERIES";//session.getAttribute("QueryTableName").toString();
      }
    Support.LookupTreeV10 mainQueriesTree = (Support.LookupTreeV10) session.getAttribute("queriesTree");
    if (mainQueriesTree == null) 
	{	Misc.initializeMainSystemTrees(request, response, session, application , out);
		mainQueriesTree = (Support.LookupTreeV10) session.getAttribute("queriesTree"); 
	} 


      String pathLinks = "";
		try {
		pathLinks = mainQueriesTree.getPathLinks(id) ; 
		}
		catch( Exception e ) {
			String errorMessage = "<br> غير مصرح لك للوصول لهذه الوحدة  "+id+" او هذه الوحدة غير موجودة بالنظام .برجاء التواصل مع مدير النظام" 
					+ "<br>System/Module You are Trying to Reach (Module ID : "+id+") is not found in the Main System Tree." 
					+ "<br> <a target = 'Unit Details' href = editAndExecute.jsp?id=13654&query_id="+id+">Check This Unit</a>" 
					+ "<Br> Please Contact System Administrator to Provide Access to this System/Module " ;
					
			throw new Exception (errorMessage ) ; } 
		String htmlBodyAttribute = null ;
		LuQueries luQuery = null ;
  	  try { sqlReader = new Support.SqlReader(repCon,QueryTableName , "QUERY_BODY",  id, session , request);  
  	  		luQuery =  sqlReader.getLuQuery() ;
  	  		htmlBodyAttribute = luQuery.getAttribute("HTML_BODY_ATTRIBUTE").getStringValue(); 
  	  		}
  	  catch ( QueryParamValueNotFoundException e ) 
  	  {
  		out.print( pathLinks ) ; 
  		%> <P></P><p dir="rtl" align="center"> <font color="red" size="5">  برجاء اختيار و تحديد القيم اعلاه ثم الضغط على "تنفيذ " </font><p align="center"> <%=e.getMissingParam() %></p></p>
  			<p align="center"><a href = "index.jsp?reportTopOpen=<%=id%>" target = top title="الصفحة الرئيسية"><img src="images/homeIcon.jpg" width="30"></a></p>
  		<%
  		out.flush();
  		return ; 
  	  }
  	  catch ( Exception e ) {throw e ; }
%>
  <body <%=htmlBodyAttribute%> dir="<%=loggedUserDta.getDisplayDirection()%>"> 
<% if (! printable) { %>
<div align="center">
	<script>
		  $(function() {
		    $( "#sysMessage" ).dialog(
		    { position: { my: "center top", at: "center top", of: window  } },
		    {
		      autoOpen: false,
		      modal : true , 
		      width : 800 ,
		      show: { effect: "explode", duration: 500  },
		      hide: { effect: "explode", duration: 500  },
		      buttons: {
        	  Cancel: function() 
       	  		{  $( this ).dialog( "close" );}
          			, 
          	  OK : function () 
	      		{
	      			history.go(0);  
	      			$( this ).dialog( "close" );
	      		}
		      
		    } } );
		    
		  });
		</script>
		<div id="sysMessage" title="System Message" dir="ltr"></div>
		
		<div align="left">
		<%
		

		String firstQuery = (sqlReader!= null)? sqlReader.getQueryStatments()[0] : null;
		Support.LookupTreeV10 queriesTree = (Support.LookupTreeV10)session.getAttribute("queriesTree");
		boolean treeAlreadyIntialized = queriesTree != null && queriesTree.getNodesStatusAsBitSet() != null ; 
		
		if (firstQuery == null ||  firstQuery.trim().equals("") )
		{
		  if (  ! treeAlreadyIntialized ) return  ; 
		  out.print(pathLinks) ;  
		  queriesTree.initialize(application,session,request,response,out);
		  queriesTree.setShowMoveForm(false); 
		  queriesTree.setShowAddLink(false); 
		  queriesTree.setShowToolBar(false); 
		  queriesTree.openNode("LU_QUERIES" + id );
		  queriesTree.setStartFromNodeId("LU_QUERIES"+id);   
		  queriesTree.render(LookupTreeV10.JQUERY_MENU );
		  %><p align="center"> <a href = "index.jsp?reportTopOpen=<%=id%>" target = top title="الصفحة الرئيسية"><img src="images/homeIcon.jpg" width="30"></a></p><% 
		  return ; 
		}
		%>
		</div>
<table width="100%" border="0" align="left">
	<tr> 

		<td width="40%"><strong><font color="#FF0000" size="2"><em> <%=pathLinks%> <br> </font></strong></td>
		<td width="50%">
		<div align="center">
		 <a href = "queryResultNonTabbed.jsp?<%=request.getQueryString()%>" title="nonTapVersion"> <img src="images/listIcon.jpg" width="30"></a>
		 <a href = "index.jsp?reportTopOpen=<%=id%>" target = top title="الصفحة الرئيسية"><img src="images/homeIcon.jpg" width="30"></a>
		   <% 
		   String refreshLink = request.getQueryString() ;
		   refreshLink = Support.Misc.repalceAll(refreshLink , "Commit=Y" , "");
		   refreshLink = Support.Misc.repalceAll(refreshLink , "Commit=N" , "");
		   refreshLink = Support.Misc.repalceAll(refreshLink , "RollBack=Y" , "");
		   refreshLink = Support.Misc.repalceAll(refreshLink , "RollBack=N" , "");
		   
			String printableLink = refreshLink ;
			String printableStr = "&printable=" ;
			if (printableLink != null )
			{
			if ( printableLink.indexOf(printableStr + printable) == -1 ) printableLink = printableLink + printableStr + printable ;
			printableLink = Misc.repalceAll(printableLink , printableStr + printable , printableStr +! printable );
			}

		   %> 
		 <a href = "?<%=refreshLink%>" title = "Reload Data إعادة التحميل"  ><img src="images/toolbar_icon_reload_active.gif" width="30"></img></a>
		 <a href = "?<%=printableLink%>" title = "نسخة قابلة للطباعة Printable Version" target = "Printable"><img src="images/cleardot.jpg" width="30" ></a>
		 <a href = "queryResultWithEditableRS.jsp?<%=request.getQueryString() %>" title="Click To Update إضغط للتعديل " > <img src="images/editList.jpg" width="30" ></a>
		 <img  title="تغيير إتجاه الجدول" src="images/direction.jpg" 
		       onclick="
		       // document.getElementById('Tabs1').align='right' ;
		       // document.getElementById('Tabs1').dir='rtl' ;
		          document.dir='rtl' ; 
		          document.align='right' ;
		       //for (i =0 ; i< 8 ; i++ ) 
		       //{
			   //    document.getElementById('div<%=id%>_'+i).dir = 'rtl' ; 
			   //    document.getElementById('resSetTable<%=id%>_'+i).dir = 'rtl' ;
		       //	   document.getElementById('resSetTable<%=id%>_'+i).align = 'right' ;
	 	       //}
		        " 
		       width="30" ></img>
		</em></font>
		
		</div>
		</td>
		<td><font color="#FF0000" size="2"><em> (<%=Misc.getConnectionUserName(con)%>@<%=(selectedConnParms != null )? selectedConnParms.name : ""%>)</em></font></td>
	</tr>
</table>
</div>

<p><br>
</p>
<p>
<% } %>

  <%
          	// -----------Checking Authority--------------------
          	  if (! AutoLog)
                  {
                  Support.LookupTreeV10 queriesTree = null;
          	  queriesTree = (Support.LookupTreeV10)session.getAttribute("queriesTree");
          	  Support.QueryTreeSecurity querySecurity = new Support.QueryTreeSecurity(queriesTree, con);
          	  querySecurity.checkAuthority("LU_QUERIES",Integer.parseInt(id), Support.QueryTreeSecurity.EXECUTE);  
                  }
          	  //-----End Of checking Authority---------------
                          
                  if ( sqlReader.active.equals("F"))
                   {
                    throw new Exception ("Sorry : This Report is currently disabled, Please contact Application Support");
                   }
          	  }
          		  
          	  String[] queryStmt = sqlReader.getQueryStatments();
          	  String[] titles = sqlReader.getQueryTitles();
          	  int noOfQueries = sqlReader.getNoOfQueries();
          	  
          	  session.setAttribute("queryStmt",queryStmt);
          	  //----------------Excecuete The Query ---------------------
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
          	  
  	  		%> 
</p>


	<%  request.setAttribute("sqlReader" , sqlReader);
   	  	int startTabsFrom = 1 ;
 
   	  	for (int queryIndex = 0 ; queryIndex<startTabsFrom ; queryIndex++)
   	  	{
   	   	  	String renderQueryResultPageName = sqlReader.getLuQuery().getQueryDetailDs().getPersistantObjectList().get(queryIndex).getAttributeValue("RENDER_QUERY_RESULT_PAGE").toString() ; //  "renderQueryResult.jsp" ;
   	  		%>
		<jsp:include page="<%=renderQueryResultPageName %>">
        	<jsp:param value="<%=queryIndex%>" name="queryIndex"/>
        	<jsp:param value="<%=printable %>" name="printable"/>
    	</jsp:include>
	<%	} if ( noOfQueries > startTabsFrom) { %>
	 
	<div id="Tabs1" > 
        <ul> 
		<%
	     	  for (int queryIndex = startTabsFrom ; queryIndex<noOfQueries ; queryIndex++)
          	  { 
	     		 String tabTitle = titles[queryIndex] ;
	     		 int tagStartIndex = tabTitle.indexOf("<a") ;
	     		  if (tagStartIndex != -1 && tabTitle.indexOf("</") != -1)
	     		 {
	     			tabTitle = tabTitle.substring(0 ,tagStartIndex ) ; 
	     		 }
	     		if ( queryIndex-startTabsFrom == 0) // First Tab only Will be loaded
	     		{
	     			
          	    %> <li><a href="#tabs-<%=queryIndex-startTabsFrom%>"><strong><em><%=tabTitle%></em></strong></a></li> <%
	     		}
	     		else // Remainaing Tabs will be loaded with Ajax Request
	     		{
	       	   	  	String renderQueryResultPageName = sqlReader.getLuQuery().getQueryDetailDs().getPersistantObjectList().get(queryIndex).getAttributeValue("RENDER_QUERY_RESULT_PAGE").toString() ; //  "renderQueryResult.jsp" ;
	          	  %> <li><a href="<%=renderQueryResultPageName %>?<%=request.getQueryString()%>&lookupTableName=LU_QUERIES&queryIndex=<%=queryIndex%>&printable=<%=printable%>"><strong><em><%=tabTitle%></em></strong></a></li> <%	     			
	     		}
          	  }
		%> 
  		</ul> 
  		<%
          	  for (int queryIndex = startTabsFrom ; queryIndex<noOfQueries ; queryIndex++)
          	  { 
          		if ( queryIndex-startTabsFrom == 0)
          		{
           	   	  	String renderQueryResultPageName = sqlReader.getLuQuery().getQueryDetailDs().getPersistantObjectList().get(queryIndex).getAttributeValue("RENDER_QUERY_RESULT_PAGE").toString() ; //  "renderQueryResult.jsp" ;
          			%> 	
          	  	<div id="tabs-<%=queryIndex-startTabsFrom%>">
	   	  			<jsp:include page="<%=renderQueryResultPageName %>">
				        <jsp:param value="<%=queryIndex %>" name="queryIndex"/>
				        <jsp:param value="<%=printable %>" name="printable"/>
				        
				    </jsp:include>
				</div> 	
			   <%
          		}
		  	  }
  	  
  	   	%>
  	 </div> 
  	 <%} %>
<font size = 1 color = black>Report Date : <%=new java.util.Date() %></font>
  	<% int activeTab =0 ;
  	  try {activeTab = Integer.parseInt( request.getParameter("_QuerySeq")) - startTabsFrom ;  }
  	  catch(Exception e){} %>
   <script type="text/javascript">
		   $(function() {
			    $( "#Tabs1" ).tabs({
			      beforeLoad: function( event, ui ) {
			    	ui.panel.html("<img width = 20px Hieght = 20px  src = \"images/ajaxLoading.gif\" ></img> Please Wait ...") ; 
			        ui.jqXHR.error(function() {
			          ui.panel.html(
			            "Couldn't load this tab. Please Contact IT Department We'll try to fix this as soon as possible. " );
			        });
			      }
			    });
			    
			    $( "#Tabs1" ).tabs({ active: <%=activeTab %> }); 
			  });
	</script>
  	<%
    String loggedUser = Misc.getConnectionUserName(con);
    boolean loggingRquired = sqlReader.isLogExecution();    	
    if (loggingRquired & ! execAgainstRep)    	
    {
    	String msg = "User : " + loggedUser + " Executed Query No " + request.getParameter("id") + " Title: " + titles[0];
    	String[] msgParms = {loggedUser , request.getParameter("id") , titles[0]};
    	new Support.event.resource.system.EventSysm1035(msgParms, null);
    }
    stmt.close();
    if( AutoLog)
    {
    con.close();
    repCon.close();
    }
  %>
<% if (! printable) 
{ 
%>
<div align="center">
<p>
	<% 
 
	if (loggedUserDta.isSmartToolAdmin())
	{
	%> 
	<input name="viewQuery" value="View Query" type="button"
	onClick="window.open('<%=appURL%>/viewQueries.jsp','Query_Viewer','toolbar=0,menubar=0,location=0, width= 900 ,height=500 ');">
	<% } %>
	<a href = "index.jsp?reportTopOpen=<%=id%>" target = top title = "الصفحة الرئيسية" ><img src="images/homeIcon.jpg" width="30"></a>
</p>
</div>
<jsp:include page="/footer.jsp" />
<%} %>
<!--Start of Tawk.to Script-->
<script type="text/javascript">
var Tawk_API=Tawk_API||{}, Tawk_LoadStart=new Date();
(function(){
var s1=document.createElement("script"),s0=document.getElementsByTagName("script")[0];
s1.async=true;
s1.src='https://embed.tawk.to/5c18fd6982491369ba9e9ad1/default';
s1.charset='UTF-8';
s1.setAttribute('crossorigin','*');
s0.parentNode.insertBefore(s1,s0);
})();
</script>
<!--End of Tawk.to Script-->
</body>
</html>
