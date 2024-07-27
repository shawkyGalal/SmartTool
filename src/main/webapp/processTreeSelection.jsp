<%@page import="java.sql.* , Support.transactions.UserSessionUpdates"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.smartValue.tableControllers.ItableTriggerController" %>
<%@page import="com.smartValue.database.map.TableMaintMaster, Support.Misc" %>
<%@page import="com.smartValue.database.PersistantObject"%>
<%@page import="com.smartValue.database.DBKey"%>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.smartValue.database.ApplicationContext"%>
<%@page import="java.util.HashMap"%><HTML lang="ar">
<BODY>
	<%
	  java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
	  java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");
		
		String treeIdInSession = request.getParameter("treeIdInSession") ; 
		String selectedNodeID = request.getParameter("selectedNodeID"); 
		String operation = request.getParameter("operation"); 
		
		Support.LookupTreeV10 tree = (Support.LookupTreeV10)session.getAttribute(treeIdInSession);
		int selectedNodeIndex = tree.getindex2(selectedNodeID) ;
		String selectedNodeIDOnly = tree.getIdInTable(selectedNodeIndex); 
		//String selectedNodeIDOnly = selectedNodeID.substring(selectedNodeTabeleName.length()) ; 
		if(tree.isMultiSelectMode())
		{
			if (operation.equals("Add"))
			{
					if (!tree.getSelectedIDs().containsKey(selectedNodeIDOnly)) 
						{
						tree.getSelectedIDs().put(selectedNodeIDOnly, tree.getDesc(selectedNodeIndex)); 
						}
			}
			else if (operation.equals("Remove"))
			{
					tree.getSelectedIDs().remove(selectedNodeIDOnly);
			}
		}
		else if (tree.isSingleSelectMode())
		{
			tree.setSelectedIDs(new HashMap<String , String>() ) ;
			tree.getSelectedIDs().put(selectedNodeIDOnly, tree.getDesc(selectedNodeIndex)); 
						
		}
		
   %>
<%= tree.getSelectedIDsCommaSeperated()%>$$<%= tree.getSelectedDescsCommaSeperated()%>
	</BODY>
</HTML>
