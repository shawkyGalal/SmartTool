<%@ page errorPage="../errorPage.jsp"
	import="java.util.*,java.io.*,java.sql.*,java.text.SimpleDateFormat,javax.faces.model.SelectItem"%>
<%@ page import="java.util.*,Support.Misc"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8");%>

<%@page import="java.text.DateFormat"%>
<%@page import="com.implex.database.map.services.*"%>
<%@page import="com.implex.database.map.*"%>
<%@page import="com.sideinternational.sas.configuration.Configuration"%>
<%@page import="com.implex.database.PersistantObject"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="Support.LookupTreeV10"%>



<%
java.sql.Connection con = (java.sql.Connection) session.getAttribute("con"); 
try{
		String toggleDesign = request.getParameter("toggleDesign") ;
       	boolean showDesign = toggleDesign!=null && toggleDesign.equals("Show Design") ;
       	TableMaintDetailPOAware tmd = (TableMaintDetailPOAware)request.getAttribute("tmd") ;
  	  	ResultSet rs = (ResultSet)request.getAttribute("rs") ;
  	  	//Connection con = rs.getStatement().getConnection(); 
  	  	String rowIdStrValue = rs.getString("ROWID"); 
   	  	String cloumnName ="";
   	  	String columnValue = "";
   	  	int coulmnDisplaySize = 4;
   	  	String htmlType = "";
   	 	boolean isDate = false;
        		 cloumnName = tmd.getColumnNameValue();
        		 htmlType = tmd.getHtmlTypeValue(con , rowIdStrValue); // rsmd.getColumnTypeName(count);
        		 columnValue = "";
        		    isDate = (htmlType.toUpperCase().equals("4"));
        		try {
        		    if (isDate)
        		    {
        		    	java.util.Date dt = rs.getTimestamp(cloumnName) ; 
        		    	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm"); 
        		     	columnValue = ( dt== null)? "": sdf.format(dt);
        		    }
        		    else if (htmlType.equals("LONG")|| htmlType.equals("CLOB") )
        		    {     //----Reading a Long Value from the Result Set
        		          java.io.InputStream is = rs.getAsciiStream(cloumnName);
        		          if (is != null)
        		          {
        		          java.io.InputStreamReader isr = new java.io.InputStreamReader(is);
        		          java.io.BufferedReader br  = new java.io.BufferedReader(isr);
        		          String line  ="";
        		          while ((line = br.readLine())!=null) 
        		          {columnValue +=line +"\n";}
        		          }
        		          coulmnDisplaySize = 301;  // -- any value more than 300 to render it  a text area 
        		    }
        		    else 
        		    {columnValue =  (rs.getString(cloumnName) == null)? "" :rs.getString(cloumnName);}
        		}
        		catch(SQLException sqle ) {cloumnName += "....ERROR reading metadata, Clikc Synch Metadata link Below";}
        		    
        		  %>
        				<%
        				boolean isTextBox = (htmlType.toUpperCase().equals("1"));
        				boolean isOptions = (htmlType.toUpperCase().equals("2"));
        				boolean isCombo = (htmlType.toUpperCase().equals("3"));
            		    boolean isTextArea = htmlType.equals("5");
            		    boolean isCheckBox = htmlType.equals("9");
            		    boolean isLabel = htmlType.equals("10");
            		    boolean isDateTime = htmlType.equals("11");
            		    boolean isHijriDate =  htmlType.equals("12");
            		    boolean isPassword =  htmlType.equals("13");
            		    boolean isMultiSelectTree =  htmlType.equals("14");
            		    boolean isSingleSelectTree =  htmlType.equals("15");
            		    
            		    
                      	String rowIdEncoded = java.net.URLEncoder.encode(rs.getString("ROWID")) ;
                   		String divID = request.getParameter("divID") ; //"'"+tmd.getColumnNameValue()+"'" ; //"'" + j + "_" +rownum + "_" + count + "'" ;
                   		String appPath = Misc.getAppURL(request) ; 
                   		String url = "'"+appPath+"/updateDBAttribute.jsp?isHijriDate="+isHijriDate+"&isDateTime="+isDateTime+"&isDate="+isDate+"&tableName="+tmd.getTableNameValue()+"&tableOwner="+tmd.getOwnerValue()+"&columnName="+tmd.getColumnNameValue()+"&rowIdValue="+rowIdEncoded+"&newValue=' + adjustNewValue(encodeURIComponent(this.value)) "; //encodeURI
                   		url = "encodeURI("+url+")" ;   
                   		String tmdOnChange = (String)tmd.getAttributeValue("ONCHANGE");
                   		String onChangeJsCode = tmdOnChange + ";\n sendAjaxRequestToJsp("+url+" , "+divID +") ; "  ;
						String  disbledHtmlAttrib = ( tmd.getDisabled().getBooleanValue())? "disabled = 'disabled' ":"" ;
						String divIdWithoutQuot = divID.substring(1, divID.length()-1) ;  
						String htmlAttribute = tmd.getAttributeValue(con , rowIdStrValue );
	       		        if (isTextBox) 
    			         {
    			        	%> 
								<input onfocus="this.oldvalue = this.value;" id=<%=cloumnName%>_<%=divIdWithoutQuot%> name = <%=cloumnName%> type=Text value ="<%=columnValue%>"  <%= htmlAttribute%> style='Width:<%=tmd.getDatalength() %>;'  
    			        	 			onchange="<%=onChangeJsCode %>; 
								       // Store Value in the oldvalue 
							          this.oldvalue = this.value;" 
							        <%=disbledHtmlAttrib %> >
  							<%
    		        	 }
		   		         else if (isTextArea)
    			         {
    		    	        %> 
    		            	<textarea id=<%=cloumnName%>_<%=divIdWithoutQuot%> name = <%=cloumnName%> <%=htmlAttribute%> 
    		            	 	onchange="<%=onChangeJsCode %> ; "  <%=disbledHtmlAttrib %>><%=columnValue%></textarea> 
    						<%                                             
    			         } 

	       		        else if (isDate)
        		        {
	       		        	java.util.Date dt = rs.getDate(cloumnName) ; 
	       	   		    	Support.SqlReader.setDateFormate("dd-MM-yyyy");
	       	   		  		String dateFormate = "dd-mm-yyyy hh24:mi:ss";
	       	   		    	dateFormate = Support.SqlReader.getDateFormate();
	       	   		    	SimpleDateFormat sdf = new SimpleDateFormat(dateFormate); 
	       	   		     	columnValue = ( dt== null)? "": sdf.format(dt);
	       	   		     	
        		           	%><%= Support.Misc.renderhtmlTextFildForDate(cloumnName ,"form1", "onchange = \""+onChangeJsCode +"\" value = '" + columnValue +"'  " + htmlAttribute , dateFormate ) %>
        					<%
        		        }
        		        else if (isDateTime)
        		        {
	       		        	java.util.Date dt = rs.getTimestamp(cloumnName) ; 
	       	   		  		String dateFormate = "yyyy-MM-dd'T'HH:mm";
	       	   		    	SimpleDateFormat sdf = new SimpleDateFormat(dateFormate); 
	       	   		     	columnValue = ( dt== null)? "": sdf.format(dt);
        		        	%><input name="<%=cloumnName%>" type="datetime-local" id="var<%=cloumnName%>" 
								max="2099-01-01" min="1980-01-01" value="<%=columnValue%>"  <%=htmlAttribute %> onfocus="this.oldvalue = this.value;" onblur=" if (this.oldvalue != this.value ) { <%=onChangeJsCode %> ; this.oldvalue = this.value;} " > 
							<%
        		        
        		        }
        		        else if (isHijriDate)
        		        {
    		        		java.util.Date dt = rs.getTimestamp(cloumnName) ; 
       	   		  			String dateFormate = "dd/MM/yyyy";
       	   		    		SimpleDateFormat sdf = new SimpleDateFormat(dateFormate); 
       	   		     		columnValue = ( dt== null)? "": sdf.format(dt);
        		        	%>
        		        		<jsp:include page="./HijriCalender/hijriCalender.jsp">
        		        			<jsp:param value="<%=columnValue%>" name="columnValue"/>
        		        			<jsp:param value="<%=cloumnName%>" name="columnName"/>
        		        			<jsp:param value="dd/mm/yyyy" name="dateFormat"/>
        		        			<jsp:param value="<%=onChangeJsCode%>" name="onCloseJsCode"/>
        		        			<jsp:param value="<%=divIdWithoutQuot%>" name="divIdWithoutQuot"/>
        		        		</jsp:include>
     		        		
        		        	<% 
        		        }
        				else if (isCombo || isOptions)
        				{
        					
        					ArrayList<SelectItem> sis = tmd.getQuerySelectItems (con , rs.getString("ROWID"));
        					if (isCombo)
        					{
	        					%> <select onfocus="this.oldvalue = this.value;" id=<%=cloumnName%>_<%=divIdWithoutQuot%> name = "<%=cloumnName %>"  
	        					       onchange="<%=onChangeJsCode %>"  
	        					       <%=htmlAttribute%> 
	        					       <%=disbledHtmlAttrib %> ><%
	        					for (SelectItem si : sis)
	        					{
	        						%><option <%=(String.valueOf( si.getValue()).equalsIgnoreCase(columnValue))? "selected" : "" %> value = "<%=si.getValue()%>"  > <%=si.getLabel() %></option> <% 
	        					}
								%> </select><%
        					}
        					else 
        					{
        						for (SelectItem si : sis)
 	        					{
 	        						 %>
 	        						 <input onfocus="this.oldvalue = this.value;" type="radio" id=<%=cloumnName%>_<%=divIdWithoutQuot%> name = "<%=cloumnName%>_<%=rowIdEncoded %>"  onchange="<%=onChangeJsCode %>"  <%=htmlAttribute%> <%=disbledHtmlAttrib %>  value="<%=si.getValue()%>" <%=(String.valueOf( si.getValue()).equalsIgnoreCase(columnValue))? "checked" : "" %> > <%=si.getLabel()%>  
 	        						 <%
 	        					}
        					}
        					String maintURL = (String)tmd.getValueSearchFormValue();
        					if (maintURL != null){
        						%><a target = "Maintain LOV" href = "<%=maintURL%>"  title = "Click To Maintain The List اضغط لصيانة القائمة" >Maintain</a> <%
        					}
        				 }
        					

        		         else if (isCheckBox)
        		         {
        		        	 %><input id=<%=cloumnName%>_<%=divIdWithoutQuot%> type="checkbox"   name="<%=cloumnName%>"  Value = "<%=columnValue%>"  <%=(columnValue.equalsIgnoreCase("Y") || columnValue.equalsIgnoreCase("Yes") )? "checked" : "" %>   
        		        	        <%=disbledHtmlAttrib %>
        		        	 		onClick=" this.value= (this.checked)? 'Y' : 'N'; 
        		        	 		<%=onChangeJsCode %>" >	
        		        	     	<!-- <input type="hidden"  name="<%=cloumnName%>"  value="" >  -->
        		        	    <%
        		         }
	       		        
        		         else if (isLabel)
        		         {
        		        	 %> <%=columnValue%> <%
        		         }
        		         else if (isPassword)
        		         {
        		        	 
        		        	 %> <input id=<%=cloumnName%>_<%=divIdWithoutQuot%>  name = <%=cloumnName%> type="password" value ="<%=columnValue%>"  <%=htmlAttribute %> style='Width:<%=tmd.getDatalength() %>;'  
    			        	 			onchange="<%=onChangeJsCode %>"   <%=disbledHtmlAttrib %> >
        		        	 <%
        		         }
        		         else if (isMultiSelectTree || isSingleSelectTree )
        		         {  String operationMode = null ; 
        		         	if (isSingleSelectTree) {operationMode = LookupTreeV10.SINGLE_SELECT_OPERATION_MODE ; }
        		         	else if (isMultiSelectTree) 
        		         		{operationMode = LookupTreeV10.MULTI_SELECT_OPERATION_MODE ;}
        		         	int treeQuerySource = 1 ; // inform Tree to get query from tmd 
        		         	String treeIdInSession= tmd.getOwnerValue() +"/"+tmd.getTableNameValue()+"/"+tmd.getColumnNameValue() ;
        		         	LookupTreeV10 tmdTree = (LookupTreeV10)session.getAttribute(treeIdInSession); 
        		         	String itemsDesc = null; 
        		         	try {itemsDesc = (isMultiSelectTree)? tmdTree.getDescForListOfIds(columnValue.replaceAll("\n" , "")) : tmdTree.getDesc( tmdTree.getindex2(columnValue)) ; } 
        		         	catch (Exception e) {e.printStackTrace(); } ; 
        		        	 %> <textarea id=<%=cloumnName%>_<%=divIdWithoutQuot%> name = <%=cloumnName%> <%=htmlAttribute%> 
	    		            	 	onchange=" 
	    		            	 	           updaetHref_<%=cloumnName%>_<%=divIdWithoutQuot%>( this ) ;   
	    		            	 	  			<%=onChangeJsCode %> ; 	
	    		            	 	  		 "  
	    		            	 	<%=disbledHtmlAttrib %>
    		            	 	><%=columnValue%></textarea> 
    		            	 <a target = "tree Selection"  id="<%=cloumnName%>_<%=divIdWithoutQuot%>_TreeLink"
        	      					title = "Click To Selected Items from Tree" 
        	      					href = "javascript:window.open('selectionTree.jsp?refreshAll=xx&_operationMode=<%=operationMode%>&_selectedIDs=<%=columnValue%>&_querySouce=<%=treeQuerySource%>&treeIdInSession=<%=tmd.getOwnerValue() %>/<%=tmd.getTableNameValue()%>/<%=tmd.getColumnNameValue()%>&_fillObject=<%=cloumnName%>_<%=divIdWithoutQuot%>' , 'Select From Tree' , 'width=400, height=600' ) " ><img width="25" height = "25"  src="images/treeIcon.jpg"> </a>
    		            	 	<div id="<%=cloumnName%>_<%=divIdWithoutQuot%>_label"><%=itemsDesc %></div>
    		            	 	<script type="text/javascript">
    		            	 			function updaetHref_<%=cloumnName%>_<%=divIdWithoutQuot%>(m_object)
    		            	 			{
        		            	 			var newhref = "javascript:window.open('selectionTree.jsp?_operationMode=<%=operationMode%>&_selectedIDs=" + m_object.value + "&_querySouce=<%=treeQuerySource%>&treeIdInSession=<%=treeIdInSession %>&_fillObject=<%=cloumnName%>_<%=divIdWithoutQuot%>' , 'Select From Tree' , 'width=400, height=600' )" ;  
    		    		            	    <%=cloumnName%>_<%=divIdWithoutQuot%>_TreeLink.href =  newhref ; 
    		            	 			}
    		            	 			
    		            	 	</script>
    		            	 	
        		        	 
        		        	  <%
        		         }
        		         else 
        		         { 
        		        	 %> <%=columnValue%> <%
        		         }

	       		        if (showDesign )
        					{
	        					String tmdRowid = tmd.getRowIdString(); 
	        					%>
	        						<a href = "<%=appPath%>/tableEditor.jsp?execAgainstRep=N&tableName=ICDB.Table_Maint_details&ROWID=<%=URLEncoder.encode(tmdRowid)%>" target = "Design Form" >Design</a> 
	        					<%
	        				}

	       	        	 %> <div id=<%=divID %>></div>
	       	        <% } catch (Exception e) {out.println(e.getMessage());} %>