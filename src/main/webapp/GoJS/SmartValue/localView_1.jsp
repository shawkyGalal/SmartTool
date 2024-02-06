<%@ page errorPage="/errorPage.jsp"%>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="Support.LookupTreeV10 ,Support.TreeNode , com.implex.database.map.TableMaintDetails"%>


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

<html>

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>الخطة الاستراتيجية</title>
  <meta name="description" content="In one diagram show the whole tree and in a second diagram show a subset that is logically near a selected node." />
  <!-- Copyright 1998-2017 by Northwoods Software Corporation. -->
  <meta charset="UTF-8">
  <script src="../release/go.js"></script>
  <script id="code">
    function init() {
      // if (window.goSamples) goSamples();  // init for these samples -- you don't need to call this
      var $ = go.GraphObject.make;  // for conciseness in defining templates

      myFullDiagram =
        $(go.Diagram, "fullDiagram",  // each diagram refers to its DIV HTML element by id
          {
            initialAutoScale: go.Diagram.UniformToFill,  // automatically scale down to show whole tree
            maxScale: 0.25,
            contentAlignment: go.Spot.Center,  // center the tree in the viewport
            isReadOnly: true,  // don't allow user to change the diagram
            "animationManager.isEnabled": false,
            layout: $(go.TreeLayout,
                      { angle: 90, sorting: go.TreeLayout.SortingAscending }),
            maxSelectionCount: 1,  // only one node may be selected at a time in each diagram
            // when the selection changes, update the myLocalDiagram view
            "ChangedSelection": showLocalOnFullClick
          });

      myLocalDiagram =  // this is very similar to the full Diagram
        $(go.Diagram, "localDiagram",
          {
            autoScale: go.Diagram.UniformToFill,
            contentAlignment: go.Spot.Center,
            // isReadOnly: true,
            layout: $(go.TreeLayout,
                      { angle: 90, sorting: go.TreeLayout.SortingAscending }),
            "LayoutCompleted": function(e) {
              var sel = e.diagram.selection.first();
              if (sel !== null) myLocalDiagram.scrollToRect(sel.actualBounds);
            },
            maxSelectionCount: 1,
            // when the selection changes, update the contents of the myLocalDiagram
            "ChangedSelection": showLocalOnLocalClick
          });

      // Define a node template that is shared by both diagrams
      var myNodeTemplate =
    	  $(go.Node, "Spot"       		 
    	            //,{ locationSpot: go.Spot.Center }
    	        	// , new go.Binding("text", "text", go.Binding.toString)  // for sorting
    	            , $( go.Panel, "Auto" 
    		            , $(go.Shape, "RoundedRectangle", new go.Binding("fill", "table_name", nodeTypeBrushConverter)         	)
    		           // , $(go.TextBlock, "textssss" ,  {font: "bold 10pt serif"}, { margin: 2} )
    	            
    	              , $(go.Panel, "Vertical"
    		                    , new go.Binding("itemArray", "items")
    		                    , {itemTemplate:
    		                        $(go.Panel, "Auto", { margin: 2 }
    		                        	,$(go.Shape, "RoundedRectangle", new go.Binding("fill", "c") )
    		                          	,$(go.TextBlock, new go.Binding("text", "t"), {font: "bold 10pt serif"}, { margin: 2} )
    		                         )
    		                      }
    				        ) 
    		  			/*
    		              , $(go.Panel, "Table" 
    		            		, $("Button",  {  // margin: new go.Margin(0, 1, 0, 0) ,
    					                    click: function(e, obj) {
    					                    // OBJ is this Button Panel;
    					                    // find the TableRow Panel containing it
    					                    var itempanel = obj.panel.panel.node;
    					                    alert("Clicked on row " + new go.Binding("text", "table_name"));
    					                  }
    					                },
    					                $(go.Shape, "FivePointedStar",
    					                  { desiredSize: new go.Size(8, 8) })
    								)
    						)
    					*/
    					
    				)
    			
    				// To Add an Expander Button
    				, $("TreeExpanderButton",
    				        { alignment: go.Spot.Bottom, alignmentFocus: go.Spot.Top },
    				        { visible: true }
    				    )
    	    
    	        );
      myFullDiagram.nodeTemplate = myNodeTemplate;
      myLocalDiagram.nodeTemplate = myNodeTemplate;
      
   // define the Link template
      var myLinkTemplate2 =
        $(go.Link,  // the whole link panel
                { routing: go.Link.Orthogonal, corner: 5, selectable: false },
          $(go.Shape,{ strokeWidth: 2, stroke: '#424242' }) 
          , $(go.Shape, { toArrow: "OpenTriangle", fill: null }) // the arrowhead
        	        
         );  // the gray link shape
          
      myFullDiagram.linkTemplate = myLinkTemplate2;
      myLocalDiagram.linkTemplate = myLinkTemplate2;

      // Create the full tree diagram
       setupDiagram();
		 
      // Create a part in the background of the full diagram to highlight the selected node
      highlighter =
        $(go.Part, "Auto",
          {
            layerName: "Background",
            selectable: false,
            isInDocumentBounds: false,
            locationSpot: go.Spot.Center
          },
          $(go.Shape, "Ellipse",
            {
              fill: $(go.Brush, "Radial", { 0.0: "yellow", 1.0: "white" }),
              stroke: null,
              desiredSize: new go.Size(400, 400)
            })
          );
      myFullDiagram.add(highlighter);

      // Start by focusing the diagrams on the node at the top of the tree.
      // Wait until the tree has been laid out before selecting the root node.
      myFullDiagram.addDiagramListener("InitialLayoutCompleted", function(e) {
        var node0 = myFullDiagram.findPartForKey('<%=digramTree.getId(0)%>');
        if (node0 !== null) node0.isSelected = true;
        showLocalOnFullClick();
      });
    }

    // Make the corresponding node in the full diagram to that selected in the local diagram selected,
    // then call showLocalOnFullClick to update the local diagram.
    function showLocalOnLocalClick() {
      var selectedLocal = myLocalDiagram.selection.first();
      if (selectedLocal !== null) {
        // there are two separate Nodes, one for each Diagram, but they share the same key value
        myFullDiagram.select(myFullDiagram.findPartForKey(selectedLocal.data.key));
      }
    }

    function showLocalOnFullClick() {
      var node = myFullDiagram.selection.first();
      if (node !== null) {
        // make sure the selected node is in the viewport
        myFullDiagram.scrollToRect(node.actualBounds);
        // move the large yellow node behind the selected node to highlight it
        highlighter.location = node.location;
        // create a new model for the local Diagram
        var model = new go.TreeModel();
        // add the selected node and its children and grandchildren to the local diagram
        var nearby = node.findTreeParts(3);  // three levels of the (sub)tree
        // add parent and grandparent
        var parent = node.findTreeParentNode();
        if (parent !== null) {
          nearby.add(parent);
          var grandparent = parent.findTreeParentNode();
          if (grandparent !== null) {
            nearby.add(grandparent);
          }
        }
        // create the model using the same node data as in myFullDiagram's model
        nearby.each(function(n) {
            if (n instanceof go.Node) model.addNodeData(n.data);
          });
        myLocalDiagram.model = model;
        // select the node at the diagram's focus
        var selectedLocal = myLocalDiagram.findPartForKey(node.data.key);
        if (selectedLocal !== null) selectedLocal.isSelected = true;
      }
    }
    var bluegrad = '#90CAF9';
    var pinkgrad = '#F48FB1';
    var redgrad = '#FF0000' ; 
    var greegrad = '#00FF00' ; 
    var kpiColor = "#AA0088" ; 
    // define Converters to be used for Bindings
    function nodeTypeBrushConverter(table_name) {
        if (table_name === "ORG_UNIT") return bluegrad;
        if (table_name === "OBJECTIVE") return pinkgrad;
        if (table_name === "PROGRAM") return "orange";
        if (table_name === "BSC_PROSPECTIVE") return greegrad;
        if (table_name === "BSC_OBJECTIVE_KPI") return kpiColor;
        
        return "orange";
      }

    // Create the tree model containing TOTAL nodes, with each node having a variable number of children
    <% 
        StringBuffer v_result = new StringBuffer(); 
        for ( TreeNode treeNode : digramTree.getTreeNodes() ) 
    	{
        	String achiveStr = treeNode.getAttributeValue(12) ;
        	String levelStr = treeNode.getAttributeValue(14) ; 
        	float achivement = Float.valueOf( (achiveStr != null )? achiveStr.trim() : "NA" ) ;
        	int nodeLevel = Integer.valueOf( (levelStr != null)?  levelStr.trim() : "NA" ) ;
        	String objectRowId = treeNode.getIdInTable() ; 
        	String statusColor = "red" ;
        	String statusDesc = "ضعيف" ; 
        	
        	if (achivement < 80 && achivement > 60 ) {	statusColor = "orange";  statusDesc = "مقبول" ; }
        	else if (achivement > 80 ) { statusColor = "green"; statusDesc = "جيد" ; }
    		v_result.append("\n " + "{ key: \""+treeNode.getId()+"\" "
				    			+ ", items: [ "
							                + "{ t: \""+treeNode.geteDesc().trim()+"\", c: \"#ffffff\" } "
							                + ", { t: \"الاداء : %"+achivement+" \", c: \"#ffffff\" } "
							                
							                + ", { t: \"الحالة : "+statusDesc+"\", c: \""+statusColor+"\" } "
							               // + ( (nodeLevel == 5 )? ", {t: \" xxx \"  , c: \"#ffffff\" }" : "" ) 
				              				+"]"
    	 						+", table_name : \""+treeNode.getTableName()+ "\" "
    	 						// +", stroke: \"#4d90fe\" "
    	 						+", parent: \""+treeNode.getParentId()+"\" }," ); // treeNode.toJsonForGoJs()
    	}
    %>
    function setupDiagram(total) {
    	
    var nodeDataArray = [ 
    						<%=  v_result.toString()%>
		 				]
	
      myFullDiagram.model = new go.TreeModel(nodeDataArray);
    }
  </script>
</head>
<body onload="init();">
<div id="sample">
<div id = "options" >
			<a href = "" target = "New Window" ><img src = "../../images/toolbar_icon_menu_active.gif" title = "Open in stand alone Window"></a>
			<a href = "/SmartTool/GoJS/samples/index.html" target = "Samples Window"  title = "Click To Display Galary of Digrams" >Demos </a>
		</div>
  <div id="fullDiagram" style="height:200px;width:100%;border:1px solid black;margin:2px" hidden="true"></div>
  <div id="localDiagram" style="height:550px;width:100%;border:1px solid black;margin:2px"></div>
 
</div>
</body>
</html>