<%@ page errorPage="errorPage.jsp"%>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="Support.LookupTreeV10"%>

<%@page import="java.util.StringTokenizer"%><html>
	<head>
		<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
	</head>
	<title>Accounts List</title>
	<body>
		<p>
		<% 
		 java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
		  if (con == null || con.isClosed() )
		 {response.sendRedirect("loginScreen.jsp?comeFrom=processTree.jsp");}
		  
		  String operationMode = request.getParameter("operationMode") ; 
		  operationMode = (operationMode== null) ? Support.LookupTreeV10.POINTER_OPERATION_MODE : operationMode;
		  
		 	String treeIdInSession = request.getParameter("treeIdInSession") ; 
		  	String startFromNodeId = request.getParameter("startFromNodeId") ;
		 	if ( treeIdInSession == null )
			{ // if (startFromNodeId == null){throw new Exception("startFromNodeId Should be identified in request ") ; }
		 		
			 treeIdInSession = "processTree_"+operationMode  ;
			}
			Support.LookupTreeV10 processesTree = (Support.LookupTreeV10)session.getAttribute(treeIdInSession);
				  
			boolean refreshAll = ( request.getParameter("refreshAll.x")!=null  );
			if ( refreshAll  || processesTree == null )  //---------- Re generate it again and store it in the session
		  	{
			  if (processesTree == null && startFromNodeId != null )
			  {
				  processesTree = new Support.LookupTreeV10(treeIdInSession);
			  }
			  processesTree.setStartFromNodeId(startFromNodeId) ;
			  processesTree.setOperationMode(operationMode); 
			  processesTree.setImagepath("images/");
			  processesTree.setAddFormUrl("TableInsertForm.jsp?owner=SUPPORT");
			  processesTree.setParentIdVarName("PARENT_ORG_PROCESS_ID"); 
			  processesTree.setTargetFrame("mainFrame");
			  processesTree.setAddFormTargetFram("mainFrame");
			  processesTree.setShowAddLink(true); 
			  processesTree.setShowToolBar(false);
			  processesTree.sethyperlinkLastTableItemsOnly(true);
			  processesTree.showRecycleBinButton(false);
			  processesTree.setShowCheckBox(processesTree.isMultiSelectMode() ); 
			  processesTree.setShowMoveForm(false);
			  
			  processesTree.initialize(application,session,request,response,out);
			  processesTree.setDBConnection(con);
				String tdq = "select abc.id , abc.tablename , abc.parent , abc.linked , abc.edesc , abc.adesc , abc.header_id , abc.title , abc.Target_URL , abc.editUrl "
				   +" , connect_by_iscycle cycle  "
				   +" , level "
				   +" , SYS_CONNECT_BY_PATH(abc.id , '/') Pathx "
				+" from "
				+" ( "
				+"   SELECT to_char(ORG_Process_id) as id  "
				+"  , 'ORG_PROCESS' as tableName " 
				+"  , Case when t.Parent_org_process_id is null then 'ORG_UNIT_'||t.OWNER_ORG_UNIT_ID  else to_char(Parent_org_process_id) end AS PARENT  " 
				+"  , '1' linked "
				+"  , t.description  edesc "
				+"  , t.description   adesc "
				+"  , to_char(org_process_id) as idInTable  "
				+"  , to_char(ORG_Process_id)  header_id "
				+"  , 'Total Balance = ttttt'  title "
				+"  , 'editAndExecute.jsp?id=37103&org_process_id='||t.ORG_Process_id Target_URL "
				+"  , 'tableEditor.jsp?tableName=SUPPORT.ORG_PROCESS&ROWID='||support.urlencode(t.rowid)||'&'  editURL	"
				+"  FROM Support.ORG_PROCESS  t "
				+"  Where t.company_id = ICDB.SECURITY.GET_USER_COMPANY  "
				+" union "
				+"    SELECT 'ORG_UNIT_'||ORG_UNIT_id as id "
				+"         , 'ORG_UNIT' as tableName "
				+"         , 'ORG_UNIT_'||t.parent_org_unit_id  AS PARENT "
				+"         , '0' linked "
				+"         , t.description  edesc "
				+"         , t.description  adesc "
				+"         , org_unit_id as idInTable " 
				+"         , ORG_UNIT_id  header_id "
				+"         , 'Total Balance = ttttt' title  "
				+"         , 'editAndExecute.jsp?id=34441&org_unit_id='||t.org_unit_id Target_URL "
				+"         , 'tableEditor.jsp?tableName=JCCS.ORG_UNIT&ROWID='||support.urlencode(t.rowid)||'&'  editURL "		
				+"    FROM jccs.org_unit t "
				+"    Where t.company_id = ICDB.SECURITY.GET_USER_COMPANY  "  
				+"    and t.parent_org_unit_id <> 'IN_ACTIVE_DEPTS' "
				+"  ) abc "
				+"   start with abc.id = 'ORG_UNIT_0' "
				+"   connect by nocycle prior abc.id = abc.parent " ;
			
				processesTree.setTreeDataByQuery(tdq);  //----------This Method internally check if the reload button pressed or not
				//orgUnitTree.setNodeEditorPage("tableEditor.jsp") ;
		  }
		
			processesTree.initialize(application,session,request,response,out);
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
		  processesTree.render(displayType);
		  
		  if (processesTree.isMultiSelectMode()) {
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
			 			loadDetails('processTreeSelection.jsp?treeIdInSession=<%=processesTree.getTreeIdInSession()%>&operation=Add&selectedNodeID='+m_name , 'serverSideProcessResult') ;  
				   	}	
			 		else 
			 		{
			 			
			 			div.innerHTML = '' ; 
			 			
				 		var index = selectedItems.indexOf(m_name) ;
				 		if (index >=0 ) 
				 		{	selectedItems.splice(index); }

			 			loadDetails('processTreeSelection.jsp?treeIdInSession=<%=processesTree.getTreeIdInSession()%>&operation=Remove&selectedNodeID='+m_name , 'serverSideProcessResult') ;  
			 			
			 		}	

			 		nodeSelectionForm_<%=processesTree.getTreeIdInSession()%>._selectedItems.value = 	selectedItems.toString() ;
			 		document.getElementById('selectedItemsDiv').innerHTML = selectedItems.toString() ;	
			   	} 											
		 </script>
		 
		 <div id="selectedItemsDiv"></div>
		 <div>
    		 <form name = 'nodeSelectionForm_<%=processesTree.getTreeIdInSession()%>'> 
    			
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
		
		Selected ID : <%= processesTree.getSelectedIDs()%>
	</body>
</html>
