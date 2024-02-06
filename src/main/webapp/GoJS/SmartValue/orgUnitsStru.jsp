<%@page import="com.implex.database.map.SecUsrDta"%>
<%@ page errorPage="/errorPage.jsp"%>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="Support.LookupTreeV10 ,Support.TreeNode , com.implex.database.map.TableMaintDetails"%>
<% String appURL = Support.Misc.getAppURL(request) ;  %>
<%@page import="java.util.StringTokenizer"%>
<%@page import="com.implex.database.map.TableMaintMaster"%><html>
	<% 	    SecUsrDta loggedUser = Support.Misc.getLoggedUserFromSession(session);
			java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
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
			  Support.SqlReader queryReader ; 
			  try{
				  queryReader = new Support.SqlReader(con , "LU_QUERIES" , "Query_body" , queryId , session , request) ;
			  }
			  catch ( Support.QueryParamValueNotFoundException e ) 
		  	  {
		  		//out.print( pathLinks ) ; 
		  		%> <P></P><p dir="rtl" align="center"> <font color="red" size="5"> برجاء اختيار و تحديد القيم اعلاه ثم الضغط على "تنفيذ" </font><p align="center"> <%=e.getMissingParam() %></p></p>
		  			<p align="center"><a href = "index.jsp?reportTopOpen=<%=queryId%>" target = top title="الصفحة الرئيسية"><img src="<%=appURL%>/images/homeIcon.jpg" width="30"></a></p>
		  		<%
		  		out.flush();
		  		return ; 
		  	  }
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
			String tree_max_level = request.getParameter("tree_max_level") ; 
		  %>

<html>

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Organization Structure الهيكل التنظيمى</title>
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
            maxScale: 0.4,
            contentAlignment: go.Spot.Center,  // center the tree in the viewport
            isReadOnly: true,  // don't allow user to change the diagram
            "animationManager.isEnabled": false,
            layout: $(go.TreeLayout,
                      { angle: 90, sorting: go.TreeLayout.SortingAscending })
            , maxSelectionCount: 1  // only one node may be selected at a time in each diagram
         	// don't have ContextMenuTool select the context-clicked node
            ,"contextMenuTool.standardMouseSelect": function() { }
            // when the selection changes, update the myLocalDiagram view
            , "ChangedSelection": showLocalOnFullClick
         	
            
          });
      function makeButton(text, action, visiblePredicate) {
          return $("ContextMenuButton",
                   $(go.TextBlock, text),
                   { click: action },
                   // don't bother with binding GraphObject.visible if there's no predicate
                   visiblePredicate ? new go.Binding("visible", "", function(o, e) { return o.diagram ? visiblePredicate(o, e) : false; }).ofObject() : {});
        }
      var partContextMenu =
          $(go.Adornment, "Vertical",
              			makeButton("عرض الخرطة بدءا من هذه الوحدة" 
		              					,function(e, obj) {	var contextmenu = obj.part;  // the Button is in the context menu Adornment
		                                					var part = contextmenu.adornedPart;
                        	 								var orgUnitId = part.data.key ; 
            	  											window.open("orgUnitsStru.jsp?<%=Support.Misc.removeParamFromQueryStr("org_unit_tree" , request)+"&org_unit_tree="%>" + orgUnitId , "Target1") ; 
            	  										  }
		              				  )
                         , makeButton("تعديل بيانات الوحدة" 
		              					,function(e, obj) {	var editUrl = obj.part.adornedPart.data.editUrl ;
		              										if (editUrl === null ) {alert ("Editor URL is not Defined in Data"); return }
            	  											window.open("../../"+editUrl , "objectEditor" , "width=600, height=600" ) ; 
            	  										  }
		              				  )
		              	 , makeButton("إضافة وحدة إدارية جديدة" 
		              					,function(e, obj) {	var insertUrl = "TableInsertForm.jsp?owner=JCCS&PARENT_ORG_UNIT_ID="+obj.part.adornedPart.data.key+"&tableName=ORG_UNIT&headerId=header_id" ;
		              										//if (editUrl === null ) {alert ("Editor URL is not Defined in Data"); return }
            	  											window.open("../../"+insertUrl , "objectCreator" , "width=600, height=600" ) ; 
            	  										  }
		              				  )
		              ,	makeButton("عرض الخارطة الاستراتيجية",
                         function(e, obj) {  // OBJ is this Button
                           var contextmenu = obj.part;  // the Button is in the context menu Adornment
                           var part = contextmenu.adornedPart;  // the adornedPart is the Part that the context menu adorns
                           // now can do something with PART, or with its data, or with the Adornment (the context menu)
                           if (part instanceof go.Link) alert(linkInfo(part.data));
                           else if (part instanceof go.Group) alert(groupInfo(contextmenu));
                           else 
                           { // alert(nodeInfo(part.data));
	                           var orgUnitId = part.data.key ; 
	                           var urlToOrgUnitBSC = "orgUnitBSC.jsp?_operationMode=SINGLE_SELECT&_querySouce=3&treeIdInSession=66455_0&refreshAll.x=xx&org_unit_tree=" + orgUnitId  ;
	                           <% if (tree_max_level != null ) { %>
	                            urlToOrgUnitBSC = urlToOrgUnitBSC + "&tree_max_level=<%=tree_max_level%>" ;
	                           <%} %>
	                           window.open(urlToOrgUnitBSC, "OrgUnitBCS" , "width=900, height=600") ; 
                           }
                         })
       				  
                      , makeButton("نسخ الوحدات الفرعية من وحدة إدارية اخرى" 
            					,function(e, obj) {	var copyURL = "editAndExecuteExecutable.jsp?id=49560&lookupTableName=LU_EXECUTABLES&des_org_unit="+obj.part.adornedPart.data.key ;
            										//if (editUrl === null ) {alert ("Editor URL is not Defined in Data"); return }
  	  											window.open("../../"+copyURL , "BSC Copier" , "width=900, height=300,  status=0" ) ; 
  	  										  }
            				  )
                      , makeButton("نسخ الخارطة الاستراتيجية من وحدة إدارية اخرى" 
		              					,function(e, obj) {	var copyURL = "editAndExecuteExecutable.jsp?id=33055&lookupTableName=LU_EXECUTABLES&des_org_unit="+obj.part.adornedPart.data.key ;
		              										//if (editUrl === null ) {alert ("Editor URL is not Defined in Data"); return }
            	  											window.open("../../"+copyURL , "BSC Copier" , "width=900, height=300,  status=0" ) ; 
            	  										  }
		              				  )
		              , makeButton("نسخ الخارطة الاستراتيجية إلى جميع الوحدات الفرعية" 
		              					,function(e, obj) {	var copyURL = "editAndExecuteExecutable.jsp?id=49299&lookupTableName=LU_EXECUTABLES&source_org_unit="+obj.part.adornedPart.data.key ;
		              										//if (editUrl === null ) {alert ("Editor URL is not Defined in Data"); return }
            	  											window.open("../../"+copyURL , "BSC Copier" , "width=900, height=300,  status=0" ) ; 
            	  										  }
		              				  )
		              	
		              	
		               , makeButton("حذف  كل الخارطة الاستراتيجية لهذه الوحدة" 
		              					,function(e, obj) {	var deleteURL = "editAndExecuteExecutable.jsp?id=48227&org_unit_tree="+obj.part.adornedPart.data.key ;
            	  											window.open("../../"+deleteURL , "BSC Delete") ; 
            	  										  }
		              				  )
		              				  
                        );

        function nodeInfo(d) {  // Tooltip info for a node data object
          var str = "Org Unit ID :" + d.key + "\n Org Unit Name :" + d.items[0].t + "\n  :" + d.items[1].t;
          if (d.group)
            str += "member of " + d.group;
          else
            //str += "\n top-level node";
          return str;
        }
        // Define the appearance and behavior for Groups:

        function groupInfo(adornment) {  // takes the tooltip or context menu, not a group node data object
          var g = adornment.adornedPart;  // get the Group that the tooltip adorns
          var mems = g.memberParts.count;
          var links = 0;
          g.memberParts.each(function(part) {
            if (part instanceof go.Link) links++;
          });
          return "Group " + g.data.key + ": " + g.data.text + "\n" + mems + " members including " + links + " links";
        }
     	// Define the appearance and behavior for Links:
        function linkInfo(d) {  // Tooltip info for a link data object
            return "Link:\nfrom " + d.from + " to " + d.to;
        }

      myLocalDiagram =  // this is very similar to the full Diagram
        $(go.Diagram, "localDiagram",
          {
            autoScale: go.Diagram.UniformToFill,
            maxScale: 1,
            contentAlignment: go.Spot.Center,
            // isReadOnly: true,
            layout: $(go.TreeLayout,
                      { angle: 90, sorting: go.TreeLayout.SortingAscending }),
            "LayoutCompleted": function(e) {
              var sel = e.diagram.selection.first();
              if (sel !== null) myLocalDiagram.scrollToRect(sel.actualBounds);
            },
            maxSelectionCount: 1
         	// don't have ContextMenuTool select the context-clicked node
            ,"contextMenuTool.standardMouseSelect": function() { }
            // when the selection changes, update the contents of the myLocalDiagram
            , "ChangedSelection": showLocalOnLocalClick          	
          }
        
        );
	         
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
    		                          	,$(go.TextBlock, new go.Binding("text", "t"), {font: "bold 12pt serif"}, { margin: 2}  )
    		                         )
    		                      }
    				        ) 
    				)
    			
    				// To Add an Expander Button
    				, $("TreeExpanderButton",
    				        { alignment: go.Spot.Bottom, alignmentFocus: go.Spot.Top },
    				        { visible: true }
    				    ) ,
    				    
			    	{ // this tooltip Adornment is shared by all nodes
			          toolTip:
			            $(go.Adornment, "Auto",
			              $(go.Shape, { fill: "#FFFFCC" }),
			              $(go.TextBlock, { margin: 4 },  // the tooltip shows the result of calling nodeInfo(data)
			                new go.Binding("text", "", nodeInfo))
			            ),
			          // this context menu Adornment is shared by all nodes
			          contextMenu: partContextMenu
			        }
    	    
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
        	
        	String redLimitStr = treeNode.getAttributeValue(15) ;
        	float redLimit = Float.valueOf( (redLimitStr != null )? redLimitStr.trim() : "60" ) ;
        	
        	String greenLimitStr = treeNode.getAttributeValue(16) ;
        	float greenLimit = Float.valueOf( (greenLimitStr != null )? greenLimitStr.trim() : "80" ) ;
        	
        	float achivement = Float.valueOf( (achiveStr != null )? achiveStr.trim() : "NA" ) ;
        	
        	int nodeLevel = Integer.valueOf( (levelStr != null)?  levelStr.trim() : "NA" ) ;
        	String objectRowId = treeNode.getIdInTable() ; 
        	String statusColor = "red" ;
        	String statusDesc = "ضعيف" ; 
        	
        	if (achivement < greenLimit && achivement >= redLimit ) {	statusColor = "orange";  statusDesc = "مقبول" ; }
        	else if (achivement >= greenLimit ) { statusColor = "green"; statusDesc = "جيد" ; }
        	String descHtmlEncoded = Support.Misc.repalceAll(Support.Misc.repalceAll(treeNode.geteDesc().trim() ,  "\"" , "'" ) , "\n" , "\\n")  ; 
        	descHtmlEncoded = Support.Misc.repalceAll(descHtmlEncoded , "\r" , "") ; 
        	v_result.append("\n " + "{ key: \""+treeNode.getId()+"\" "
				    			+ ", items: [ "
							                + "{ t: \""+ descHtmlEncoded+"\", c: \"#ffffff\" } "
							                + ", { t: \"الاداء : %"+achivement+" \", c: \"#ffffff\" } "
							                
							                + ", { t: \"الحالة : "+statusDesc+"\", c: \""+statusColor+"\" } "
							               // + ( (nodeLevel == 5 )? ", {t: \" xxx \"  , c: \"#ffffff\" }" : "" ) 
				              				+"]"
    	 						+", table_name : \""+treeNode.getTableName()+ "\" "
    	 						+", editUrl : \""+treeNode.getNodeEditor()+ "\" "  
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
<body onload="init(); toogleFullDigramView(); ">
	<script type="text/javascript">
		var fullDigramHidden = document.getElementById("fullDiagram").hidden ; 
		var butonText = ( (fullDigramHidden)? "Show" : " Hide " ) +  " Full Digram " ;
		function toogleFullDigramView()
		{	var fullDigram = document.getElementById("fullDiagram")
			var localDigram = document.getElementById("localDiagram") ;
			fullDigramHidden = 	! fullDigram.hidden ;
			fullDigram.hidden = fullDigramHidden ;
			butonText = ( (fullDigramHidden)? "Show" : " Hide " ) +  " Full Digram " ;
			document.getElementById("toogleFullDigramViewButton").value = butonText ; 
		}
	</script>
<div id="sample"  dir="<%=loggedUser.getDisplayDirection()%>"  align="center" >
		<div id = "options" >
			<a href = "" target = "New Window" ><img src = "../../images/toolbar_icon_menu_active.gif" title = "Open in stand alone Window"></a>
			<% boolean showSamples = loggedUser.isSmartToolAdmin() // && loggedUser.getUsrNameValue().equalsIgnoreCase("JCCS") 
									;  
			if (showSamples) { %>
			<a href = "/SmartTool/GoJS/samples/index.html" target = "Samples Window"  title = "Click To Display Galary of Digrams" >Demos </a>
			<%}%>
			<input type="button" onclick="toogleFullDigramView(); " id="toogleFullDigramViewButton" value="Hide Full Digram" >
			<% 	String indexPath = "" ;
			indexPath = "/Company/20" ; // + Support.Misc.getLoggedUserFromSession(session).getUsrCmpIdValue() ;  
			%><a href = "<%=appURL + indexPath %>/index.jsp" target = "parent">Back To Main Screen الصفحة الرئيسية  </a> 
			Organization Chart : الهيكل التنظيمي للمؤسسة 
		</div>
	<div >
  		<div id="fullDiagram" style="height:30%;width:100%;border:1px solid black;margin:2px" ></div>
  		<div id="localDiagram" style="height:90%;width:100%;border:1px solid black;margin:2px" ></div>
	</div>
</div>
<% if (loggedUser.isSmartToolAdmin() ) {%>
<a target = "Maintain Digram Query" href = "/SmartTool/editAndExecute.jsp?id=<%=treeIdInSession.substring(0,treeIdInSession.indexOf("_") )%>" > Maintain Digram Data Source Query</a>
<%} %>
</body>
</html>