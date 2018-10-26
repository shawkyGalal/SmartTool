<%@page import="com.implex.database.map.SecUsrDta"%>
<%@ page errorPage="errorPage.jsp"%>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="Support.SelectionTree , com.implex.database.map.TableMaintDetails"%>

<%@page import="java.util.StringTokenizer"%>
<%@page import="com.implex.database.map.TableMaintMaster"%><html>
<head>
		<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
	</head>
	<title>Tree Selection </title>
	<body>
	<% 	 java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
		  if (con == null || con.isClosed() )
		 {response.sendRedirect("loginScreen.jsp?comeFrom=selectionTree.jsp"+request.getQueryString());}
				 
		 	String treeIdInSession = request.getParameter("treeIdInSession") ; 
			
		 	Support.SelectionTree selectionTree = null ; 
		 	selectionTree = (Support.SelectionTree)session.getAttribute(treeIdInSession);
		 	
		 	 					  
			boolean refreshAll = ( request.getParameter("refreshAll.x")!=null  );
			if ( refreshAll  || selectionTree == null )  //---------- Re generate it again and store it in the session
		  	{
				int querySource = SelectionTree.QUERY_FROM_TMD ; // datasource from tmd
				String treeQuey = null ; 
				
				if (selectionTree == null)
				{
				  	if (request.getParameter("_querySouce") == null )
					 {
						 throw new Exception ("Query Souce of the Selection Tree not defined in the Request... Please send it (_querySouce) in the http Request" ) ; 
					 }
					
				  	querySource = Integer.parseInt(request.getParameter("_querySouce")) ; 
				  	 selectionTree = new SelectionTree(treeIdInSession , querySource , con , session , request);
				  	selectionTree.setSqlBoundVarName("$$"+request.getParameter("_boundVarName")) ;
				  	
				}
				if (refreshAll)  
				{
					selectionTree.setRequest(request) ; 
					querySource = selectionTree.getQuerySource();
				}

		  		try { treeQuey = selectionTree.getTreeQuery(true) ;}
		  		catch ( Exception e ) { if (treeQuey == null ) { selectionTree = null ; } throw e ;  } 
		  		
		  		if (! selectionTree.isPointerMode()) { 	
				%> <Strong> Select Value For : <%=selectionTree.getDisplayTitle() %> </Strong> <%
			  	}
		   
			  selectionTree.setImagepath("images/");
			  selectionTree.setAddFormUrl("TableInsertForm.jsp?owner=JCCS");
			  selectionTree.setParentIdVarName("PARENT_ORG_UNIT_ID");
			  selectionTree.setTargetFrame("Edit Tree Item");
			  selectionTree.setAddFormTargetFram("Edit Tree Item");
			  selectionTree.setShowAddLink(true); 
			  selectionTree.setShowToolBar(true);
			  selectionTree.sethyperlinkLastTableItemsOnly(true);
			  selectionTree.showRecycleBinButton(false);
			  selectionTree.setShowCheckBox(true ); 
			  selectionTree.setShowMoveForm(false);
			  selectionTree.initialize(application,session,request,response,out);
			  selectionTree.setDBConnection(con); 
			  selectionTree.setTreeDataByQuery(false);  
			  selectionTree.setTableAttributes("border=1") ; 
			  selectionTree.setShowEditLink(true) ; 

			}
			selectionTree.setShowToolBar(true);
			selectionTree.initialize(application,session,request,response,out);
			String operationModeFromRequest = request.getParameter("_operationMode") ;
			if ( operationModeFromRequest!= null ){ selectionTree.setOperationMode(operationModeFromRequest); }
			if (! selectionTree.isPointerMode()) 
			{
		%>

			<div>
				 <table>
				 	<tr>
				 		<td>
		    		    	<input type ="button" value ="تأكيد الاختيار"  title="Update Your Selection"   onclick="  updateSelection();  ">
		    		    </td>
		    		    <td> 
		    	    		<input type ="button" value ="الغاء"    onclick=" window.close(); ">
		    	    	</td> 
		    		</tr>
		    	</table>
	    	</div>
			<p>
			<% 
			}

			  
			String selectedIDsStr = request.getParameter ("_selectedIDs");
			if ( selectedIDsStr != null )
			{
				StringTokenizer st = new StringTokenizer(selectedIDsStr , ",") ;
				java.util.HashMap<String , String > selectedIDs = new java.util.HashMap<String , String >();
				selectionTree.setSelectedIDs(selectedIDs) ; // clear selections
				while (st.hasMoreElements())
				{
					String selectedID = st.nextToken().trim() ; 
					try { selectionTree.setSelectedID(selectedID); } 
					catch(Exception e)
					{out.println("<br>Hint : Node With ID = " + selectedID + " Not Found in the Tree") ; }  
				}
				
			}
		  int displayType = SelectionTree.DEFAULT_TREE ;
		  String displayTypeParamName = "displayType" ;
		  try {  
			  displayType = Integer.parseInt( request.getParameter(displayTypeParamName) );
		 	  Cookie cookie = new Cookie (displayTypeParamName ,String.valueOf(displayType));
			  response.addCookie(cookie);
		  } 
		  catch(Exception e ) 
		  {
			  try { displayType = Integer.parseInt( Support.Misc.getCookiByName(request , displayTypeParamName ).getValue()); }
			  catch ( Exception e1) { displayType = SelectionTree.JQUERY_TREE ; }
		  }
		  
		  selectionTree.render(SelectionTree.DEFAULT_TREE);
		  String fillObject = request.getParameter("_fillObject") ; 
		  if (fillObject != null)
		  {
			  selectionTree.setFillObject(fillObject) ; 
		  }
		%>
		
		<script type="text/javascript" src="includes/AJAX_new.js"></script>
		<% if (! selectionTree.isPointerMode()) { %>
		
		<div id="serverSideProcessResult"><%=selectionTree.getSelectedIDsCommaSeperated()%>$$<%=selectionTree.getSelectedDescsCommaSeperated()%></div>
		
		
		<script language ='JavaScript' >  
			    function processSelection(m_name , m_value){                                                    
			  		var div = document.getElementById('serverSideProcessResult') ; 
			 		div.innerHTML = '' ; 
			 		if ( m_value == 'Y')						
			   		{
			 			loadDetails('processTreeSelection.jsp?treeIdInSession=<%=selectionTree.getTreeIdInSession()%>&operation=Add&selectedNodeID='+m_name , 'serverSideProcessResult') ;  
				   	}	
			 		else 
			 		{
				 		loadDetails('processTreeSelection.jsp?treeIdInSession=<%=selectionTree.getTreeIdInSession()%>&operation=Remove&selectedNodeID='+m_name , 'serverSideProcessResult') ;  
			 			
			 		}	
					 
			} 											
		 </script>
		 

		 <div>
			 <table>
			 	<tr>
			 		<td>
	    		    	<input type ="button" value ="تأكيد الإختيار"  title="Update Your Selection"   onclick="  updateSelection();  ">
	    		    </td>
	    		    <td> 
	    	    		<input type ="button" value ="إلغاء"    onclick=" window.close(); ">
	    	    	</td> 
	    		</tr>
	    	</table>
    	</div>
    	<script type="text/javascript">
    		function updateSelection ()
    		{
    			var selectedValues =  ( document.getElementById('serverSideProcessResult').innerHTML ).trim() ; 
				var selectedIds = selectedValues.substring(0, selectedValues.indexOf("$$")) ; 
				var selectedDescs = selectedValues.substring( selectedValues.indexOf("$$")+2 ) ;
				//alert (selectedIds) ; 
				//alert (selectedDescs) ; 
	   			 var openerObject = opener.<%=selectionTree.getFillObject()%> ; 
	   			 if (openerObject.disabled == true)
	   			 { alert ('عفوا لا يمكن تعديل هذه القيمة') ; }
	   			 else 
	   			 {
	   			 openerObject.value = selectedIds;
	   			 var openerLable = opener.<%=selectionTree.getFillObject()+"_label"%> ; 
	   			 openerObject.onchange() ; 
	   			 openerLable.innerHTML = selectedDescs ;
	   			 window.close() ;
	   			 }
    		}
    	</script> 
		</p>
		<% } if (false ) {%>
		
		Tree Id in User Session : <%=treeIdInSession %>
		<small> Display List As :
			<% if (displayType != (SelectionTree.DEFAULT_TREE)) { %><br><a href="?treeIdInSession=<%=treeIdInSession%>&displayType=<%=SelectionTree.DEFAULT_TREE %>">Server Side Tree Default/Original </a> <% } %>
			<% if (displayType != (SelectionTree.JQUERY_TREE)) { %><br><a href="?treeIdInSession=<%=treeIdInSession%>&displayType=<%=SelectionTree.JQUERY_TREE %>">Jquery Tree</a> <% } %>
			<% if (displayType != (SelectionTree.JQUERY_MENU)) { %><br><a href="?treeIdInSession=<%=treeIdInSession%>&displayType=<%=SelectionTree.JQUERY_MENU %>">Jquery Menu</a> <% } %>
			<% if (displayType != (SelectionTree.LSIT_ITEMS)) { %><br><a href="?treeIdInSession=<%=treeIdInSession%>&displayType=<%=SelectionTree.LSIT_ITEMS %>">HTML List</a> <% } %>
		</small>
		<%}%>
		
		<% SecUsrDta loggedUser = Support.Misc.getLoggedUserFromSession(session) ;   
		if (loggedUser.isSmartToolAdmin()) { %>
		<a href = "<%=selectionTree.getQueryMaintainLink() %>" target = "TreeQueryMaint" >Maintain Tree Query</a>
		<% } %> 
	</body>
</html>
