<%@page import="com.smartValue.json.JsonServices"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.File"%>
<%@page import="java.net.URL"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Support.SqlReader"%>
<%@page import="com.implex.database.map.SecUsrDta"%>
<%@ page errorPage="/errorPage.jsp"%>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="Support.LookupTreeV10 ,Support.TreeNode , com.implex.database.map.TableMaintDetails"%>
<% String appURL = Support.Misc.getAppURL(request) ;  %>
<%@page import="java.util.StringTokenizer"%>
<%@page import="com.implex.database.map.TableMaintMaster"%>
<script type="text/javascript" src="<%=appURL%>/includes/AJAX_new.js"></script>
<div id="sysMessage"></div>

	<%	
		SecUsrDta loggedUser = Support.Misc.getLoggedUserFromSession(session);
  		boolean digramDataFromDB = request.getParameter("query_id") != null  ;
  		String digramsFilesPath =  "C:/TEMP/SecretProject/diagrams/" ;
  		String objectsFilesPath = "C:/TEMP/SecretProject/ObjectDigramModel/" ;
  		String filesPath = (digramDataFromDB)? objectsFilesPath : digramsFilesPath ;
  		
  		String objectDataModelPath = "C:/TEMP/SecretProject/ObjectDataModel/" ; 
  		
	%>
<html>

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Objects State Chart </title>
  <meta name="description" content="In one diagram show the whole tree and in a second diagram show a subset that is logically near a selected node." />
  <!-- Copyright 1998-2017 by Northwoods Software Corporation. -->
  <meta charset="UTF-8">
  <script src="../../release/go.js"></script>
  <script type="text/javascript">
	function performAction(action)
	{
		var v_url = '/SmartTool/includes/dbTransc.jsp?'+ action+'=Y' ; 
		sendAjaxRequestToJsp( v_url , 'sysMessage' ) ; 
		$( "#sysMessage" ).dialog({modal: true}, { position: { my: "center center", at: "center center", of: window  } });
	}
	</script>
  <script id="code">
  function init() {
	  var $ = go.GraphObject.make;  // for conciseness in defining templates
	    myDiagram =
	      $(go.Diagram, "myDiagramDiv",  // must name or refer to the DIV HTML element
	        {
	    	  layout: $(go.TreeLayout,{ angle: 0, sorting: go.TreeLayout.SortingAscending }),
            
	          // start everything in the middle of the viewport
	          initialContentAlignment: go.Spot.Center,
	          // have mouse wheel events zoom in and out instead of scroll up and down
	          "toolManager.mouseWheelBehavior": go.ToolManager.WheelZoom,
	          // support double-click in background creating a new node
	          "clickCreatingTool.archetypeNodeData": { text: "New Object" , c: "yellow" },
	          // enable undo & redo
	          "undoManager.isEnabled": true
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
	              			makeButton("Object Details" 
				              					,function(e, obj) {	var contextmenu = obj.part;  // the Button is in the context menu Adornment
				                                					var part = contextmenu.adornedPart;
		                        	 								var objectId = part.data.key ; 
		                        	 								var paramName = "<%=Support.Misc.removeParamFromQueryStr("object_id" , request)+"&object_id"%>" ;
		                        	 								var paramValue = objectId ; 
		                        	 								window.open("/SmartTool/editAndExecute.jsp?id=71644&"+ paramName +"=" + paramValue , "Target1") ; 
		            	  										  }
				              				  )
							, makeButton("إضافة عنصر مرتبط" 
			              					,function(e, obj) {	var contextmenu = obj.part;  // the Button is in the context menu Adornment
			                                					var part = contextmenu.adornedPart;
	                        	 								var objectId = part.data.key ; 
	                        	 								window.open("/SmartTool/TableInsertForm.jsp?FROM_OBJECT_ID="+objectId+"&owner=JCCS&tableName=OBJECTS_RELATION"  , "Target1") ; 
	            	  										  }
			              				  )
				            , makeButton("عرض الخرطة بدءا من هذا العنصر" 
			              					,function(e, obj) {	var contextmenu = obj.part;  // the Button is in the context menu Adornment
			                                					var part = contextmenu.adornedPart;
	                        	 								var objectId = part.data.key ; 
	                        	 								window.open("stateChart.jsp?<%=Support.Misc.removeParamFromQueryStr("object_id" , request)+"&object_id="%>" + objectId , "Target1") ; 
	            	  										  }
			              				  )
			              	, makeButton("Include Objects Related To This Object \n Under Construction" 
			              					,function(e, obj) {	var contextmenu = obj.part;  // the Button is in the context menu Adornment
			                                					var part = contextmenu.adornedPart;
	                        	 								var objectId = part.data.key ; 
	                        	 								window.open("xxxxxx.jsp?<%=Support.Misc.removeParamFromQueryStr("object_id" , request)+"&object_id="%>" + objectId , "Target1") ; 
	            	  										  }
			              				  )
	                        );
	    
	    // when the document is modified, add a "*" to the title and enable the "Save" button
	    myDiagram.addDiagramListener("Modified", function(e) {
	      var button = document.getElementById("SaveButton");
	      var savAsButton = document.getElementById("ServerSideSaveButton");
	      if (button) button.disabled = !myDiagram.isModified;
	      if (savAsButton) savAsButton.disabled = !myDiagram.isModified;
	      var idx = document.title.indexOf("*");
	      if (myDiagram.isModified) {
	        if (idx < 0) document.title += "*";
	      } else {
	        if (idx >= 0) document.title = document.title.substr(0, idx);
	      }
	    	updateJson() ; 
	    });
	  // define the Node template
	  myDiagram.nodeTemplate =
	   $(go.Node, "Spot", 
	      $(go.Panel, "Auto" ,
	        new go.Binding("location", "loc", go.Point.parse).makeTwoWay(go.Point.stringify),
	        // define the node's outer shape, which will surround the TextBlock
	        $(go.Shape, "RoundedRectangle", new go.Binding("fill", "c") ,
		          {
		            parameter1: 20,  // the corner has a large radius
		            //fill:  $(go.Brush, "Linear", { 0: "rgb(0, 201, 254)", 1: "rgb(254, 162, 0)" }),
		            stroke: null,
		            portId: "",  // this Shape is the Node's port, not the whole Node
		            fromLinkable: true, fromLinkableSelfNode: true, fromLinkableDuplicates: true,
		            toLinkable: true, toLinkableSelfNode: true, toLinkableDuplicates: true,
		            cursor: "pointer"
		          }

	        	)
		
	        , { // this tooltip Adornment is shared by all nodes
			          toolTip:
			            $(go.Adornment, "Auto",
			              $(go.Shape, { fill: "#FFFFCC" }),
			              $(go.TextBlock, { margin: 4 },  // the tooltip shows the result of calling nodeInfo(data)
			              //"Bind Node Information Here"  
			              // Use the below code 
			              new go.Binding("text", "", nodeInfo)
			              )
			            ),
			          // this context menu Adornment is shared by all nodes
			          contextMenu: partContextMenu
			        }
			, $(go.TextBlock,
		          {
		            font: "bold 11pt helvetica, bold arial, sans-serif",
		            editable: true  // editing the text automatically updates the model data
		          }
				  , new go.Binding("text").makeTwoWay()
				)
			
	       )
				// To Add an Expander Button
		   , $("TreeExpanderButton",
			        	{ alignment: go.Spot.Right, alignmentFocus: go.Spot.Top },
			        	{ visible: true }
			    		) 
	      );

	    // unlike the normal selection Adornment, this one includes a Button
	    myDiagram.nodeTemplate.selectionAdornmentTemplate =
	      $(go.Adornment, "Spot",
	        $(go.Panel, "Auto",
	          $(go.Shape, { fill: null, stroke: "blue", strokeWidth: 2 }),
	          $(go.Placeholder)  // a Placeholder sizes itself to the selected Node
	        ),
	        // the button to create a "next" node, at the top-right corner
	        $("Button",
	          {
	            alignment: go.Spot.TopRight,
	            click: addNodeAndLink  // this function is defined below
	          },
	          $(go.Shape, "PlusLine", { width: 6, height: 6 })
	        ) // end button
	      ); // end Adornment

	      function nodeInfo(d) {  // Tooltip info for a node data object
	          var str = "Object ID :" + d.key + "\n Object Name :" + d.text ;
	          return str;
	        }
	    // clicking the button inserts a new node to the right of the selected node,
	    // and adds a link to that new node
	    function addNodeAndLink(e, obj) {
	    	
	    	alert ("This feature is temperorly disabled, You Could right click on any object to display a drop down menu for more options") ;
	    	return ; 
	      var adornment = obj.part;
	      var diagram = e.diagram;
	      diagram.startTransaction("Add State");

	      // get the node data for which the user clicked the button
	      var fromNode = adornment.adornedPart;
	      var fromData = fromNode.data;
	      // create a new "State" data object, positioned off to the right of the adorned Node
	      var toData = { text: "New Object" , c: "yellow"};
	      var p = fromNode.location.copy();
	      p.x += 200;
	      toData.loc = go.Point.stringify(p);  // the "loc" property is a string, not a Point object
	      // add the new node data to the model
	      var model = diagram.model;
	      model.addNodeData(toData);

	      // create a link data from the old node data to the new node data
	      var linkdata = {
	        from: model.getKeyForNodeData(fromData),  // or just: fromData.id
	        to: model.getKeyForNodeData(toData),
	        text: "transition"
	      };
	      // and add the link data to the model
	      model.addLinkData(linkdata);

	      // select the new Node
	      var newnode = diagram.findNodeForData(toData);
	      diagram.select(newnode);

	      diagram.commitTransaction("Add State");
	      // if the new node is off-screen, scroll the diagram to show the new node
	      diagram.scrollToRect(newnode.actualBounds);
	      updateJson() ; 
	    }

	    // replace the default Link template in the linkTemplateMap
	    myDiagram.linkTemplate =
	      $(go.Link,  // the whole link panel
	        {
	          curve: go.Link.Bezier, adjusting: go.Link.Stretch,
	          reshapable: true, relinkableFrom: true, relinkableTo: true,
	          toShortLength: 3
	        },
	        new go.Binding("points").makeTwoWay(),
	        new go.Binding("curviness"),
	        $(go.Shape,  // the link shape
	          { strokeWidth: 1.5 }),
	        $(go.Shape,  // the arrowhead
	          { toArrow: "standard", stroke: null }),
	        $(go.Panel, "Auto",
	          $(go.Shape,  // the label background, which becomes transparent around the edges
	            {
	              fill: $(go.Brush, "Radial",
	                      { 0: "rgb(240, 240, 240)", 0.3: "rgb(240, 240, 240)", 1: "rgba(240, 240, 240, 0)" }),
	              stroke: null
	            }),
	          $(go.TextBlock, "transition",  // the label text
	            {
	              textAlign: "center",
	              font: "9pt helvetica, arial, sans-serif",
	              margin: 4,
	              editable: true  // enable in-place editing
	            },
	            // editing the text automatically updates the model data
	            new go.Binding("text").makeTwoWay())
	        )
	      );

	    // read in the JSON data from the "mySavedModel" element
	    updateDiagram();
	  }

	  // Show the diagram's model in JSON format
	  function updateJson() {
	    document.getElementById("mySavedModel").value = myDiagram.model.toJson();
	  }
	  function updateDiagram() {
	    myDiagram.model = go.Model.fromJson(document.getElementById("mySavedModel").value);
	  }
	  
	  function serverSideSave()
	  {
		  // save object Digram Model 
		  updateJson(); 
		  var filesPath = encodeURI('<%=filesPath%>') ;
		  var fileName = document.getElementById("savaAsFileName").value ;
		  var fileContent = encodeURI(document.getElementById("mySavedModel").value) ; 
		  sendAjaxRequestToJsp('/SmartTool/GoJS/SmartValue/SecretProject/serverSideSave.jsp?filesPath='+ filesPath +'&fileName='+ fileName +".json" +'&fileContent='+ fileContent , 'serverSideSaveResult') ;
		  
		  // save object Data Model 
		  filesPath = encodeURI('<%=objectDataModelPath%>') ;
		  fileContent = encodeURI(document.getElementById("dataModelJson").value) ;
		  sendAjaxRequestToJsp('/SmartTool/GoJS/SmartValue/SecretProject/serverSideSave.jsp?filesPath='+ filesPath +'&fileName='+ fileName +".json" +'&fileContent='+ fileContent , 'serverSideSaveResult') ;
		  
		  // Save also objects model in DB 
		  performAction('Commit') ; 
	  }
	  
	  function getNextObjectId()
	  {
		  // TO Do : get the next object id sequence 
		  return 100 ; 
	  }
	  function addNewDependant(objectName)
	  {
		  var objectId = getNextObjectId() ;
		  var jsonObj = JSON.parse( document.getElementById("dataModelJson").value ) ; 
		  jsonObj.RelatedObjects.push({"TO_OBJECT_ID":objectId, "OBJECT_NAME": objectName , "DESCRIPTION":"null"});
		  /*
		  for (i = 0 ; i< jsonObj.RelatedObjects.length ; i++ )
			  {
		  		alert (jsonObj.RelatedObjects[i].TO_OBJECT_ID) ;
			  }
		  */
		  document.getElementById("dataModelJson").value = JSON.stringify(jsonObj) ; 
	  }

  </script>
</head>
<body onload="init(); ">
<div id="sample">
	<table border = 0>
		<tr >
			<td width = "200" >
				<div id="ObjectsList" style="border: solid 1px black; width: 200; height: 400px overflow-y: scroll; " >

					<a target="ClearObjects" href="/SmartTool/editAndExecuteExecutable.jsp?id=49581&amp;lookupTableName=LU_EXECUTABLES" title = "Clear All Objects"><img src="/SmartTool/images/deleterec.gif" width="20" height="20"></a> 
					(<a href="/SmartTool/TableInsertForm.jsp?ID=71580&amp;LOOKUPTABLENAME=LU_QUERIES&amp;owner=JCCS&amp;tableName=OBJECT_MASTER" target="new"><img border="0" src="/SmartTool/images/toolplus.gif" width="14" height="14" title="Add New Object "></a>)
					(<a href="/SmartTool/XLSUploading/getXLSClientFile.jsp?dirPath=XLSUploading/Data&amp;uploadToTableOwner=JCCS&amp;uploadToTableName=OBJECT_FROM_EXCEL" target="new"><img border="0" src="http://epm.smart-value.com:8080/SmartTool/images/icxls.gif" width="14" height="14" title="Import Objects from Excel تحميل العناصر من ملف اكسل"></a>)
					<a href = "/SmartTool/editAndExecute.jsp?id=71620&lookupTableName=LU_QUERIES" target = "ObjectList" >Maintain</a>
					<jsp:include page="/renderQueryResult.jsp?id=71620&lookupTableName=LU_QUERIES&printable=true&queryIndex=0"></jsp:include>
				
					Categories : 
						<% 
							File folder = new File(digramsFilesPath);
							for ( File f :  folder.listFiles() ) 
							{
								String fileName1 = f.getName() ; 
								out.print("<br><a href = 'stateChart.jsp?fileName="+fileName1+"' >"+ fileName1.substring(0, fileName1.length()-5 )+"</a>" ) ;
								out.print("... Delete") ; 							
							} 
						%>
				</div>
			</td>
			<td width="1200" >
				<div id="myDiagramDiv" style="border: solid 1px black; width: 100%; height: 400px "></div>
			</td>
			
		</tr>
	</table>
  
    <div>
      <button id="SaveButton" onclick="updateJson()">Update Json</button>
      <button onclick="load()">Update Diagram</button>
      <button onclick="addNewDependant('ShawkyGalal' )">Add New Dependant</button>
      <%
    	String digramJson = "" ;	
      	String fileName = "New File" ; 
       if (digramDataFromDB)
       {
    	   digramJson = JsonServices.getDigramJsonFromDB(request, session   ) ; 
	   }
		else // Digram Data from File System  
		{
			digramJson = JsonServices.getDigramJsonFromFileSystem(request, session , filesPath ) ;
		}
       
       String categoryName  ;
       fileName = request.getParameter("fileName") ;
       categoryName = (fileName != null) ? fileName.substring(0,fileName.length()-5 ) : "NewCategory" ;
       String objectId = request.getParameter("object_id") ; 
       String saveAsFile = (digramDataFromDB) ?  objectId : categoryName ;
       
    %>
      		  
      		Save Digram as : <input <%=(digramDataFromDB)? "readonly" : "" %> type="text" id = "savaAsFileName" name = "savaAsFileName" value = "<%=saveAsFile%>" >
      		<button id="ServerSideSaveButton" onclick="serverSideSave()">Save</button>
      		<div id="serverSideSaveResult"></div>
      		<table border="1">
	      		<tr> <td align="center">Object Model in JSON</td> <td align="center"> Diagram Model in JSON</td>  </tr>
	      		 <tr> 
	      		 	<td>
						<% 	if (digramDataFromDB) 
							{
								String dataModelJson = "Under Construction"; 
								dataModelJson = JsonServices.getObjectDataModel(request , session , objectId , "71644") ; 
						 %>
			    		<textarea id="dataModelJson" style="width : 600 ; height:300px"><%=dataModelJson%></textarea>
			    		<br><a href = "/SmartTool/editAndExecute.jsp?id=71644&object_id=<%=objectId%>" target = "maintainQuery" >Maintain generating query</a>
			    		<% } %>
		    		</td>
	      		 	<td>
			      		<textarea id="mySavedModel" style="width : 600 ; height:300px"><%=digramJson %></textarea>
			      		<br><a href = "/SmartTool/editAndExecute.jsp?id=71587&object_id=<%=objectId%>" target = "maintainQuery" >Maintain generating query</a>
	    			</td>
		    		
		    	</tr>
	    	</table>
      
      
    </div>
  
  <p>
    The text is editable for both the nodes and the links.
    The user can draw as many links from one node to another node as desired,
    and the links can be reshaped or deleted when selected.
    Double-clicking in the background of the diagram creates a new node.
    The mouse wheel is set to zoom in and out instead of scroll.
  </p>
  <p align="right" dir="rtl">
  النص قابل للتعديل لكل من العقد والروابط. يمكن للمستخدم رسم العديد من الروابط من عقدة واحدة إلى عقدة أخرى حسب الرغبة ، ويمكن إعادة تشكيل الارتباطات أو حذفها عند تحديدها. يؤدي النقر المزدوج في خلفية المخطط إلى إنشاء عقدة جديدة. يتم تعيين عجلة الماوس للتكبير والتصغير بدلاً من التمرير.
  </p> 
  
  <p>
    This sample customizes the <a>Part.selectionAdornmentTemplate</a>
    of the node to a template that contains a button
    The button is positioned to be at the Top-Right corner of the node by
    being in a Spot Panel with its <a>GraphObject.alignment</a> property set to Spot.TopRight.
  </p>
  <p align="right" dir="rtl">
  	العنصر المختار ( تحت الفحص ) يظهر باللون الاحمر اما باقى العناصر تظهر باللون الاخضر 
	<br> عند إختيار اى عنصر يظهر مربع صغير به علامة + عند الضغط عليها يتم انشاء عنصر جديد مرتبط بالعنصر المختار
	   
  </p>
  <p>
    The Button's <a>GraphObject.click</a> method creates a new node data,
    adds it to the model, and creates a link from the original node data to the new node data.
    All of this is done inside a transaction, so that it can be undone by the user
    (Ctrl+Z and Ctrl+Y will undo and redo transactions). After the node is created,
    <a>Diagram.scrollToRect</a> is called in case it is off-screen.
  </p>
</div>
</body>
</html>