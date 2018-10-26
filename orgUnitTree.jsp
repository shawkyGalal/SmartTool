<%@ page errorPage="errorPage.jsp"%>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="Support.LookupTreeV10"%>

<%@page import="java.util.StringTokenizer"%><html>
	<head>
		<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
	</head>
	<title>Organization Structure Tree</title>
	<body>
		<p>
		<% 
		 java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
		  if (con == null || con.isClosed() )
		 {response.sendRedirect("loginScreen.jsp?comeFrom=orgUnitTree.jsp");}
		  
		  String operationMode = request.getParameter("operationMode") ; 
		  operationMode = (operationMode== null) ? Support.LookupTreeV10.POINTER_OPERATION_MODE : operationMode;
		  
		 	String treeIdInSession = request.getParameter("treeIdInSession") ; 
		  	String startFromNodeId = request.getParameter("startFromNodeId") ;
		 	if ( treeIdInSession == null )
			{ if (startFromNodeId == null){throw new Exception("startFromNodeId Should be identified in request ") ; }
		 		
			 treeIdInSession = "orgUnitTree_"+operationMode ;
			}
			Support.LookupTreeV10 orgUnitTree = (Support.LookupTreeV10)session.getAttribute(treeIdInSession);
				  
			boolean refreshAll = ( request.getParameter("refreshAll.x")!=null  );
			if ( refreshAll  || orgUnitTree == null )  //---------- Re generate it again and store it in the session
		  	{
			  if (orgUnitTree == null && startFromNodeId != null )
			  {
				  orgUnitTree = new Support.LookupTreeV10(treeIdInSession);
			  }
			  if (startFromNodeId != null )
			  orgUnitTree.setStartFromNodeId("ORG_UNIT"+startFromNodeId) ;
			  
			  orgUnitTree.setOperationMode(operationMode); 
			  orgUnitTree.setImagepath("images/");
			  orgUnitTree.setAddFormUrl("TableInsertForm.jsp?owner=JCCS");
			  orgUnitTree.setParentIdVarName("PARENT_ORG_UNIT_ID"); 
			  orgUnitTree.setTargetFrame("mainFrame");
			  orgUnitTree.setAddFormTargetFram("mainFrame");
			  orgUnitTree.setShowAddLink(true); 
			  orgUnitTree.setShowToolBar(false);
			  orgUnitTree.sethyperlinkLastTableItemsOnly(true);
			  orgUnitTree.showRecycleBinButton(false);
			  orgUnitTree.setShowCheckBox(orgUnitTree.isMultiSelectMode() ); 
			  orgUnitTree.setShowMoveForm(false);
			  
			  orgUnitTree.initialize(application,session,request,response,out);
			  orgUnitTree.setDBConnection(con);
				String tdq = "SELECT 'ORG_UNIT'||ORG_UNIT_id as id " 
					   	+ " , 'ORG_UNIT' as tableName "  
						+ " 	 , decode(parent_Org_unit_id ,null,null,'ORG_UNIT'||t.parent_org_unit_id ) AS PARENT " 
						+ " 	 , '"+(orgUnitTree.isPointerMode() ? "1":"0") +"' linked "
						+ "      , t.description  "
						+ "      , t.description  "
						+ "      , org_unit_id as idInTable " 
						+ "      , 'header_id'  "
						+ "      , 'Total Balance = ttttt'  " 
						+ "      , 'editAndExecute.jsp?id=34441&org_unit_id='||t.org_unit_id Target_URL" 
						+ "      , 'tableEditor.jsp?tableName=JCCS.ORG_UNIT&ROWID='||support.urlencode(t.rowid)||'&'  editURL"
						+ "      , '0' selectable " 
						+ "      , level  "
						+ "      , SYS_CONNECT_BY_PATH(t.ORG_UNIT_id, '/') Pathx " 
						+ "        FROM jccs.org_unit t "
						+ " Start with ORG_UNIT_id = '"+startFromNodeId + "'" 
						+ " Connect By PRIOR   ORG_UNIT_id = t.parent_org_unit_id " 
						+ "        and t.company_id = ICDB.SECURITY.GET_USER_COMPANY  --- and t.org_unit_id not in ( -1 ) "   
						+ "        Order by upper(t.description) " ;
			
				orgUnitTree.setTreeDataByQuery(tdq);  //----------This Method internally check if the reload button pressed or not
				//orgUnitTree.setNodeEditorPage("tableEditor.jsp") ;
		  }
		
			orgUnitTree.initialize(application,session,request,response,out);
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
		  orgUnitTree.render(displayType);
		  
		  if (orgUnitTree.isMultiSelectMode()) {
		%>
		
		<script type="text/javascript" src="includes/AJAX_new.js"></script>
		<div id="serverSideProcessResult"></div>
		<script language ='JavaScript' >  
			var selectedItems = []; 
			   	function processSelection(m_name , m_value){                                                    
			  		// alert('Selected Node :' + m_name + '...Selected Value :' + m_value ) ; 				
			 		var div = document.getElementById('serverSideProcessResult') ; 
			 		if ( m_value == 'Y')						
			   		{
			 			div.innerHTML = '' ; 
			 			selectedItems.push ( m_name ) ; 
			 			loadDetails('processTreeSelection.jsp?treeIdInSession=<%=orgUnitTree.getTreeIdInSession()%>&operation=Add&selectedNodeID='+m_name , 'serverSideProcessResult') ;  
				   	}	
			 		else 
			 		{
			 			
			 			div.innerHTML = '' ; 
			 			
				 		var index = selectedItems.indexOf(m_name) ;
				 		if (index >=0 ) 
				 		{	selectedItems.splice(index); }

			 			loadDetails('processTreeSelection.jsp?treeIdInSession=<%=orgUnitTree.getTreeIdInSession()%>&operation=Remove&selectedNodeID='+m_name , 'serverSideProcessResult') ;  
			 			
			 		}	

			 		nodeSelectionForm_<%=orgUnitTree.getTreeIdInSession()%>._selectedItems.value = 	selectedItems.toString() ;
			 		document.getElementById('selectedItemsDiv').innerHTML = selectedItems.toString() ;	
			   	} 											
		 </script>
		 
		 <div id="selectedItemsDiv"></div>
		 <div>
    		 <form name = 'nodeSelectionForm_<%=orgUnitTree.getTreeIdInSession()%>'> 
    			
    			<input type = 'text' name = '_selectedItems' >
    			<input type ='submit'> 
    		 </form> 
    	</div>  
		</p>
		<%} %>
		Tree Id in User Session : <%=treeIdInSession %>
		<small> Display List As :
			<% if (displayType != (LookupTreeV10.DEFAULT_TREE)) { %><br><a href="?treeIdInSession=<%=treeIdInSession%>&displayType=<%=LookupTreeV10.DEFAULT_TREE %>">Server Side Tree Default/Original </a> <% } %>
			<% if (displayType != (LookupTreeV10.JQUERY_TREE)) { %><br><a href="?treeIdInSession=<%=treeIdInSession%>&displayType=<%=LookupTreeV10.JQUERY_TREE %>">Jquery Tree</a> <% } %>
			<% if (displayType != (LookupTreeV10.JQUERY_MENU)) { %><br><a href="?treeIdInSession=<%=treeIdInSession%>&displayType=<%=LookupTreeV10.JQUERY_MENU %>">Jquery Menu</a> <% } %>
			<% if (displayType != (LookupTreeV10.LSIT_ITEMS)) { %><br><a href="?treeIdInSession=<%=treeIdInSession%>&displayType=<%=LookupTreeV10.LSIT_ITEMS %>">HTML List</a> <% } %>
		</small>
		
		Selected ID : <%= orgUnitTree.getSelectedIDs()%>
	</body>
</html>
