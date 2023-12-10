<%@ page errorPage="errorPage.jsp"%>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="Support.LookupTreeV10"%>
<html>
	<head>
		<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
	</head>
	<title>Accounts List</title>
	<body>
		<p>
		<% 
		 java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
		  if (con == null || con.isClosed() )
		 {response.sendRedirect("loginScreen.jsp?comeFrom=accountsTree.htm");}
		 	String treeIdInSession = request.getParameter("treeIdInSession") ; 
		  	String startFromNodeId = request.getParameter("startFromNodeId") ;
		 	if ( treeIdInSession == null )
			{
			 treeIdInSession = "accountsTree"+startFromNodeId ;
			}
			Support.LookupTreeV10 accountsTree = (Support.LookupTreeV10)session.getAttribute(treeIdInSession);
			Support.LookupTreeV10 queriesTree = (Support.LookupTreeV10)session.getAttribute("queriesTree");
			queriesTree.getStartFromNodeId(); 
		  
			boolean refreshAll = ( request.getParameter("refreshAll.x")!=null  );
			if ( refreshAll  || accountsTree == null )  //---------- Re generate it again and store it in the session
		  	{
			  if (accountsTree == null)
			  {
				accountsTree = new Support.LookupTreeV10(treeIdInSession);
				accountsTree.setStartFromNodeId("Account"+startFromNodeId) ;
			  }
				accountsTree.setImagepath("images/");
				accountsTree.setAddFormUrl("TableInsertForm.jsp?owner=JCCS");
				accountsTree.setParentIdVarName("PARENT_ACCOUNT_ID"); 
				accountsTree.setTargetFrame("mainFrame");
				accountsTree.setAddFormTargetFram("mainFrame");
				accountsTree.setShowAddLink(true); accountsTree.setShowToolBar(false);
				accountsTree.sethyperlinkLastTableItemsOnly(true);
				accountsTree.showRecycleBinButton(false);
				accountsTree.setShowCheckBox(false); accountsTree.setShowMoveForm(false);
				accountsTree.initialize(application,session,request,response,out);
				accountsTree.setDBConnection(con);
				String tdq = "SELECT 'Account'||account_id as id " 
					   	+ " , 'Account' as tableName "  
						+ " 	 , decode(parent_account_id ,null,null,'Account'||t.parent_account_id ) AS PARENT " 
						+ " 	 , '1' linked "
						+ "      , t.description ||'('||Jccs.ACCOUNT_SERVICES.get_balance(account_id)||')' "
						+ "      , t.description ||'('||Jccs.ACCOUNT_SERVICES.get_balance(account_id)||')' "
						+ "      , account_id as idInTable " 
						+ "      , 'header_id'  "
						+ "      , 'Total Balance = ' ||  Jccs.ACCOUNT_SERVICES.get_balance(account_id) " 
						+ "      , 'editAndExecute.jsp?id=24918&account_id='||t.account_id Target_URL" 
						+ "      , 'tableEditor.jsp?tableName=JCCS.ACCOUNT&ROWID='||support.urlencode(t.rowid)||'&'  editURL" 		
						+ "        FROM jccs.account t "
						+ "         Where t.account_id not in ( -1 ) "   
						+ "        Order by upper(t.description) " ;
			
			  accountsTree.setTreeDataByQuery(tdq);  //----------This Method internally check if the reload button pressed or not
			  //accountsTree.setNodeEditorPage("tableEditor.jsp?") ;
		  }
		
		  accountsTree.initialize(application,session,request,response,out);
		  int displayType = LookupTreeV10.JQUERY_TREE ;
		  String displayTypeParamName = "displayType" ;
		  try {  
			  displayType = Integer.parseInt( request.getParameter(displayTypeParamName) );
		 	  Cookie cookie = new Cookie (displayTypeParamName ,String.valueOf(displayType));
			  response.addCookie(cookie);
		  } 
		  catch(Exception e ) 
		  {
			  try { displayType = Integer.parseInt( Support.Misc.getCookiByName(request , displayTypeParamName ).getValue()); }
			  catch ( Exception e1) { displayType = LookupTreeV10.JQUERY_TREE ; }
		  }
		  accountsTree.render(displayType);
		%>
		</p>
		
		<small> Display List As :
			<% if (displayType != (LookupTreeV10.DEFAULT_TREE)) { %><br><a href="?treeIdInSession=<%=treeIdInSession%>&displayType=<%=LookupTreeV10.DEFAULT_TREE %>">Server Side Tree Default/Original </a> <% } %>
			<% if (displayType != (LookupTreeV10.JQUERY_TREE)) { %><br><a href="?treeIdInSession=<%=treeIdInSession%>&displayType=<%=LookupTreeV10.JQUERY_TREE %>">Jquery Tree</a> <% } %>
			<% if (displayType != (LookupTreeV10.JQUERY_MENU)) { %><br><a href="?treeIdInSession=<%=treeIdInSession%>&displayType=<%=LookupTreeV10.JQUERY_MENU %>">Jquery Menu</a> <% } %>
			<% if (displayType != (LookupTreeV10.LSIT_ITEMS)) { %><br><a href="?treeIdInSession=<%=treeIdInSession%>&displayType=<%=LookupTreeV10.LSIT_ITEMS %>">HTML List</a> <% } %>
		</small>
	</body>
</html>
