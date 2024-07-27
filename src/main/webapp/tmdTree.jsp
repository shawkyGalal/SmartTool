<%@ page errorPage="errorPage.jsp"%>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="Support.LookupTreeV10 ,com.smartValue.database.map.TableMaintDetails"%>

<%@page import="java.util.StringTokenizer"%>
<%@page import="com.smartValue.database.map.TableMaintMaster"%><html>
<head>
		<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
	</head>
	<title>Tree Selection </title>
	<body>
		<p>
		<% 
		 java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
		  if (con == null || con.isClosed() )
		 {response.sendRedirect("loginScreen.jsp?comeFrom=tmdTree.jsp"+request.getQueryString());}
		  
	 
		 	String treeIdInSession = request.getParameter("treeIdInSession") ; 
		  	TableMaintDetails tmd = null; 
		  	String tmdTableOwner = null ; 
		  	String tmdTableName = null ; 
		  	String tmdColumnName = null ; 
		  	
		  	if ( treeIdInSession == null )
			{ 
				 tmdTableOwner = request.getParameter("tmdTableOwner") ; 
				 tmdTableName = request.getParameter("tmdTableName" ) ;
				 tmdColumnName = request.getParameter("tmdColumnName" ) ; 
				 treeIdInSession = tmdTableOwner+"/"+tmdTableName+"/"+tmdColumnName ;
			}
		  	else 
		  	{
		  		tmdTableOwner = treeIdInSession.substring(0 , treeIdInSession.indexOf("/" , 0)) ; 
				tmdTableName  = treeIdInSession.substring(tmdTableOwner.length() +1 , treeIdInSession.indexOf("/" , tmdTableOwner.length()+1)) ;
				tmdColumnName = treeIdInSession.substring((tmdTableOwner+"/"+tmdTableName).length() +1 )  ; 
		  	}
				TableMaintMaster tmm = Support.Misc.getTableMaintMaster(session , tmdTableOwner , tmdTableName ) ;
				tmd =  tmm.getTmdByColumnName(tmdColumnName) ; 
				%> <Strong> Select Value For : <%=tmd.getDisplayNameValue() %> </Strong> <%
				
				String mainTreeTableName =null ; 
		 		Support.LookupTreeV10 tmdTree = null ; 
		 		tmdTree = (Support.LookupTreeV10)session.getAttribute(treeIdInSession);
		 	
		 	if (tmdTree != null )
		 	{
		 		mainTreeTableName = (String)tmdTree.DataAsVectors[1].elementAt(0); 
		 	}
		 					  
			boolean refreshAll = ( request.getParameter("refreshAll.x")!=null  );
			if ( refreshAll  || tmdTree == null )  //---------- Re generate it again and store it in the session
		  	{
			  if (tmdTree == null )
			  {
				  tmdTree = new Support.LookupTreeV10(treeIdInSession);
			  }
			  String operationModeFromRequest = request.getParameter("_operationMode") ; 
			  if ( operationModeFromRequest!= null ){ tmdTree.setOperationMode(operationModeFromRequest); }
			   
			  tmdTree.setImagepath("images/");
			  tmdTree.setAddFormUrl("TableInsertForm.jsp?owner=JCCS");
			  tmdTree.setParentIdVarName("PARENT_ORG_UNIT_ID"); 
			  tmdTree.setTargetFrame("mainFrame");
			  tmdTree.setAddFormTargetFram("mainFrame");
			  tmdTree.setShowAddLink(false); 
			  tmdTree.setShowToolBar(false);
			  tmdTree.sethyperlinkLastTableItemsOnly(true);
			  tmdTree.showRecycleBinButton(false);
			  tmdTree.setShowCheckBox(true ); 
			  tmdTree.setShowMoveForm(false);

			  tmdTree.initialize(application,session,request,response,out);
			  tmdTree.setDBConnection(con); 
			  
			  String treeQuey = tmd.getSelectListQueryValue(); 
				tmdTree.setTreeDataByQuery(treeQuey);  //----------This Method internally check if the reload button pressed or not
			
			}
		
			tmdTree.initialize(application,session,request,response,out);
			mainTreeTableName = (String)tmdTree.DataAsVectors[1].elementAt(0); 
			String selectedIDsStr = request.getParameter ("_selectedIDs");
			if ( selectedIDsStr != null )
			{
				StringTokenizer st = new StringTokenizer(selectedIDsStr , ",") ;
				java.util.HashMap<String , String > selectedIDs = new java.util.HashMap<String , String >();
				tmdTree.setSelectedIDs(selectedIDs) ; // clear selections
				while (st.hasMoreElements())
				{
					String selectedID = st.nextToken().trim() ; 
					try { tmdTree.setSelectedID(selectedID); } 
					catch(Exception e)
					{out.println("<br>Hint : Node With ID = " + selectedID + " Not Found in the Tree") ; }  
				}
				
			}
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
		  
		  tmdTree.render(displayType);
		  String fillObject = request.getParameter("_fillObject") ; 
		  if (fillObject != null)
		  {
			  tmdTree.setFillObject(fillObject) ; 
		  }
		%>
		
		<script type="text/javascript" src="includes/AJAX_new.js"></script>
		<div id="serverSideProcessResult"><%=tmdTree.getSelectedIDsCommaSeperated()%></div>
		<script language ='JavaScript' >  
			    function processSelection(m_name , m_value){                                                    
			  		var div = document.getElementById('serverSideProcessResult') ; 
			 		div.innerHTML = '' ; 
			 		if ( m_value == 'Y')						
			   		{
			 			loadDetails('processTreeSelection.jsp?treeIdInSession=<%=tmdTree.getTreeIdInSession()%>&operation=Add&selectedNodeID='+m_name , 'serverSideProcessResult') ;  
				   	}	
			 		else 
			 		{
				 		loadDetails('processTreeSelection.jsp?treeIdInSession=<%=tmdTree.getTreeIdInSession()%>&operation=Remove&selectedNodeID='+m_name , 'serverSideProcessResult') ;  
			 			
			 		}	
					 
			} 											
		 </script>
		 

		 <div>
			 <table>
			 	<tr>
			 		<td>
	    		    	<input type ="button" value ="Update Your Selection"  title="Update Your Selection"   onclick="  updateSelection();  ">
	    		    </td>
	    		    <td> 
	    	    		<input type ="button" value ="Cancel"    onclick=" window.close(); ">
	    	    	</td> 
	    		</tr>
	    	</table>
    	</div>
    	<script type="text/javascript">
    		function updateSelection ()
    		{
    			var selectedValues =  ( document.getElementById('serverSideProcessResult').innerHTML ).trim() ; 
	   			 var openerObject = opener.<%=tmdTree.getFillObject()%> ; 
	   			 openerObject.value = selectedValues;
	   			 // alert ( openerObject );
	   			 openerObject.onchange() ; 
	   			 window.close() ;
    		}
    	</script> 
		</p>
		
		Tree Id in User Session : <%=treeIdInSession %>
		<small> Display List As :
			<% if (displayType != (LookupTreeV10.DEFAULT_TREE)) { %><br><a href="?treeIdInSession=<%=treeIdInSession%>&displayType=<%=LookupTreeV10.DEFAULT_TREE %>">Server Side Tree Default/Original </a> <% } %>
			<% if (displayType != (LookupTreeV10.JQUERY_TREE)) { %><br><a href="?treeIdInSession=<%=treeIdInSession%>&displayType=<%=LookupTreeV10.JQUERY_TREE %>">Jquery Tree</a> <% } %>
			<% if (displayType != (LookupTreeV10.JQUERY_MENU)) { %><br><a href="?treeIdInSession=<%=treeIdInSession%>&displayType=<%=LookupTreeV10.JQUERY_MENU %>">Jquery Menu</a> <% } %>
			<% if (displayType != (LookupTreeV10.LSIT_ITEMS)) { %><br><a href="?treeIdInSession=<%=treeIdInSession%>&displayType=<%=LookupTreeV10.LSIT_ITEMS %>">HTML List</a> <% } %>
		</small>
			
	</body>
</html>
