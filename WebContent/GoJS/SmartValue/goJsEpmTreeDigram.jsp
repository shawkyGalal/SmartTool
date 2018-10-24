<%@ page errorPage="/errorPage.jsp"%>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="Support.LookupTreeV10 , com.implex.database.map.TableMaintDetails"%>

<%@page import="java.util.StringTokenizer"%>
<%@page import="com.implex.database.map.TableMaintMaster"%><html>
	<% 	 java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
		  if (con == null || con.isClosed() )
		 {response.sendRedirect("loginScreen.jsp?comeFrom=selectionTree.jsp"+request.getQueryString());}
			final int QUERY_FROM_TMD = 1;  
		 	final int QUERY_FROM_SQL_BOUND_VAR = 2; 
		 	final int QUERY_FROM_LU_QUERY = 3; 
  
	 
		 	String treeIdInSession = request.getParameter("treeIdInSession") ; 
			
			Support.LookupTreeV10 digramTree = null ; 
			digramTree = (Support.LookupTreeV10)session.getAttribute(treeIdInSession);
		 	
		 	 					  
			boolean refreshAll = ( request.getParameter("refreshAll.x")!=null  );
			if ( refreshAll  || digramTree == null )  //---------- Re generate it again and store it in the session
		  	{
				int querySource = QUERY_FROM_TMD ; // datasource from tmd
				String treeQuey = null ; 

				if (digramTree == null)
				{
					
				  	if (request.getParameter("_querySouce") == null )
					 {
						 throw new Exception ("Query Souce of the Selection Tree not defined in the Request... Please send it (_querySouce) in the http Request" ) ; 
					 }
					
				  	querySource = Integer.parseInt(request.getParameter("_querySouce")) ; 
				  	digramTree = new Support.LookupTreeV10(treeIdInSession);
				  	digramTree.setQuerySource(querySource) ; 
					
				}
				if (refreshAll)  
				{
			
					querySource = digramTree.getQuerySource();
				}

			   		  	
			  int underScoreIndex = treeIdInSession.indexOf("_") ;  // the formate of the treeIdINSession = QueryId_queryIndex
			  String queryId = treeIdInSession.substring(0, underScoreIndex) ; 
			  int queryIndex = Integer.parseInt( treeIdInSession.substring(underScoreIndex+1)) ; 
			  Support.SqlReader queryReader = new Support.SqlReader(con , "LU_QUERIES" , "Query_body" , queryId , session , request) ;
        	  treeQuey  = queryReader.getQueryStatments()[queryIndex] ;
			  			  	
		   
			  digramTree.setImagepath("images/");
			  digramTree.setAddFormUrl("TableInsertForm.jsp?owner=JCCS");
			  digramTree.setParentIdVarName("PARENT_ORG_UNIT_ID"); 
			  digramTree.setTargetFrame("mainFrame");
			  digramTree.setAddFormTargetFram("mainFrame");
			  digramTree.setShowAddLink(false); 
			  digramTree.setShowToolBar(true);
			  digramTree.sethyperlinkLastTableItemsOnly(true);
			  digramTree.showRecycleBinButton(false);
			  digramTree.setShowCheckBox(true ); 
			  digramTree.setShowMoveForm(false);
			  digramTree.initialize(application,session,request,response,out);
			  digramTree.setDBConnection(con); 
			  digramTree.setTreeDataByQuery(treeQuey);  //----------This Method internally check if the reload button pressed or not
			  digramTree.setTableAttributes("border=1") ; 

			}
			digramTree.setShowToolBar(true);
			digramTree.initialize(application,session,request,response,out);
			String operationModeFromRequest = request.getParameter("_operationMode") ;
			if ( operationModeFromRequest!= null ){ digramTree.setOperationMode(operationModeFromRequest); }

			  
			String selectedIDsStr = request.getParameter ("_selectedIDs");
			if ( selectedIDsStr != null )
			{
				StringTokenizer st = new StringTokenizer(selectedIDsStr , ",") ;
				java.util.HashMap<String , String > selectedIDs = new java.util.HashMap<String , String >();
				digramTree.setSelectedIDs(selectedIDs) ; // clear selections
				while (st.hasMoreElements())
				{
					String selectedID = st.nextToken().trim() ; 
					try { digramTree.setSelectedID(selectedID); } 
					catch(Exception e)
					{out.println("<br>Hint : Node With ID = " + selectedID + " Not Found in the Tree") ; }  
				}
				
			}
		  %>
<head>
	<script type="text/javascript" src="includes/AJAX_new.js"></script>
	<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">

  <script src="../release/go.js"></script>
    <script id="code">
    function init() {
      var $ = go.GraphObject.make;  // for conciseness in defining templates
      myDiagram =
        $(go.Diagram, "myDiagramDiv",
          { allowCopy: false,
            allowDelete: false,
            allowMove: false,
            initialContentAlignment: go.Spot.Center,
            initialAutoScale: go.Diagram.Uniform,
            layout:
              $(FlatTreeLayout,  // custom Layout, defined below
                { angle: 90,
                  compaction: go.TreeLayout.CompactionNone }),
            "undoManager.isEnabled": true
          });
      myDiagram.nodeTemplate =
        $(go.Node, "Vertical",
          { selectionObjectName: "BODY" },
          $(go.Panel, "Auto", { name: "BODY" },
            $(go.Shape, "RoundedRectangle",
              new go.Binding("fill"),
              new go.Binding("stroke")),
            $(go.TextBlock,
              { font: "bold 12pt Arial, sans-serif", margin: new go.Margin(4, 2, 2, 2) },
              new go.Binding("text"))
          ),
          $(go.Panel,  // this is underneath the "BODY"
            { height: 15 },  // always this height, even if the TreeExpanderButton is not visible
            $("TreeExpanderButton")
          )
        );
      myDiagram.linkTemplate =
        $(go.Link,
          $(go.Shape, { strokeWidth: 1.5 }));
      // set up the nodeDataArray, describing each part of the sentence
      var nodeDataArray = [ 
      				<%=  digramTree.toJsonForGoJs()%>
		 		]
	      // create the Model with data for the tree, and assign to the Diagram
	      myDiagram.model =    $(go.TreeModel,  { nodeDataArray: nodeDataArray });
	    }
	    // Customize the TreeLayout to position all of the leaf nodes at the same vertical Y position.
	    function FlatTreeLayout() {
	      go.TreeLayout.call(this);  // call base constructor
	    }
	    go.Diagram.inherit(FlatTreeLayout, go.TreeLayout);
	    // This assumes the TreeLayout.angle is 90 -- growing downward
	    /** @override */
	    FlatTreeLayout.prototype.commitLayout = function() {
	      go.TreeLayout.prototype.commitLayout.call(this);  // call base method first
	      // find maximum Y position of all Nodes
	      var y = -Infinity;
	      this.network.vertexes.each(function(v) {
	          y = Math.max(y, v.node.position.y);
	        });
	      // move down all leaf nodes to that Y position, but keeping their X position
	      this.network.vertexes.each(function(v) {
	          if (v.destinationEdges.count === 0) {
	            // shift the node down to Y
	            v.node.position = new go.Point(v.node.position.x, y);
	            // extend the last segment vertically
	            v.node.toEndSegmentLength = Math.abs(v.centerY - y);
	          } else {  // restore to normal value
	            v.node.toEndSegmentLength = 10;
	          }
	        });
	    };
	    // end FlatTreeLayout
	  </script>

	</head>
	<body onload="init()">
	
	<div style="position: relative;">
		<div id = "options" >
			<a href = "#" target = "New Window" ><img src = "../../images/toolbar_icon_menu_active.gif" title = "Open in stand alone Window"></a>
			<a href = "../samples/index.html" target = "Samples Window"  title = "Click To Display Galary of Digrams" >Demos </a>
		</div>
      <div id="myDiagramDiv" style="border: solid 1px black; width:100%; height:100%"></div>
    </div>
    
	</body>
</html>
