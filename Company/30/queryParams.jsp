<%@ page errorPage="../../errorPage.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="Support.Misc" %>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="Support.LookupTreeV10"%><html>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<head>
<% String appURL = Support.Misc.getAppURL(request) ;  %>
<link href="<%=appURL%>/jQueryAssets/jquery.ui.core.min.css" rel="stylesheet" type="text/css">
<link href="<%=appURL%>/jQueryAssets/jquery.ui.theme.min.css" rel="stylesheet" type="text/css">
<script src="<%=appURL%>/jQueryAssets/jquery-1.8.3.min.js" type="text/javascript"></script>


<script type="text/javascript" src="<%=appURL%>/includes/AJAX_new.js"></script>
<script type="text/javascript" src="<%=appURL%>/includes/smartTool.js"></script>
<%@ include file = "jsScripts.html" %>
<script src="<%=appURL%>/jQueryAssets/jquery-1.10.2.js"></script>
<script src="<%=appURL%>/jQueryAssets/jquery-ui.js"></script>


<%@ include file="../../includes/jquery_commons.js"%>

</head>

<%
String bodyAttrib = "";
if( request.getParameter("Submit") != null && request.getParameter("updateOnly") == null)
{
// bodyAttrib = "onLoad=\"sendAjaxRequestToJsp( 'queryResult.jsp?"+request.getQueryString()+"' , 'queryResultDiv' );\"";
 bodyAttrib = "onLoad=\"changeFrameSrc( 'queryResultFrame' , 'queryResult.jsp?"+request.getQueryString()+"' );\"";
}
%>
<body <%=bodyAttrib%> class="infobar-offcanvas rtl" >
     <div class="static-content">
        <div class="page-content">
            <div class="page-heading" style=" padding-top: 0px; padding-bottom: 0px;  margin-bottom: 0px;"> 
<%
  request.setCharacterEncoding("UTF-8");
  java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
  Support.XMLConfigFileReader supportConfig =  Misc.getXMLConfigFileReader(false ) ; 
  java.sql.Connection repCon = (java.sql.Connection)session.getAttribute("repCon");
   

java.util.Vector<String> sqlBoundNames = (Vector)session.getAttribute("sqlBoundNames") ; if (sqlBoundNames == null) sqlBoundNames =  new java.util.Vector(); 
java.util.Vector<String> sqlBoundValues = (Vector)session.getAttribute("sqlBoundValues") ; if (sqlBoundValues == null) sqlBoundValues =  new java.util.Vector();//new java.util.Vector(); 
if (request.getParameter("Submit") != null || request.getParameter("updateOnly") != null )
{
  for  (int i = 0 ; i< 20 ;  i ++)
  {
    if (request.getParameter("var"+i) != null)
    {
    String   varName = request.getParameter("sqlBoundVar"+i) ;
    String newVarValue  = request.getParameter("var"+i) ; 
      if (! sqlBoundNames.contains(varName))
      {
      sqlBoundNames.addElement(varName);
      sqlBoundValues.addElement(newVarValue);
      } 
      else
      {
      	int index = sqlBoundNames.indexOf(varName);
      	sqlBoundValues.set(index , newVarValue) ; 
      }
    }
  }

  session.setAttribute("sqlBoundNames" ,sqlBoundNames );
  session.setAttribute("sqlBoundValues" ,sqlBoundValues );
}
 String queryId = request.getParameter("id") ;
%>
<script language="JavaScript" type="text/JavaScript">

  function refreshExecuteFreame()
  {
	   
		var loc = window.parent.frames[1].location  ;
	  	window.parent.frames[1].location = "queryResult.jsp?id=<%=queryId%>" ; //loc; 
  }
</script>
<% Support.ConnParms selectedConnParms = (Support.ConnParms)session.getAttribute("selectedConnParms"); 
      Vector<String> titles = new Vector<String>(); //(Vector)application.getAttribute("titles");
      Vector<String> sqlBoundVar = new Vector<String>(); //(Vector)application.getAttribute("sqlBoundVar");
      Vector<String> active = new Vector<String>(); // (Vector)application.getAttribute("active");
      Vector<String>  defaultValues = new Vector<String>(); //; 
      Vector<String>  dataTypes = new Vector<String>(); //; 
      Vector<String>  QUERY_FOR_LIST = new Vector<String>(); //; 
      Vector<String> other_attributes = new Vector<String>();
      //------------------Getting bound Variable from DB -----
      java.sql.Statement stmt = repCon.createStatement();
      String loggedUser = Misc.getConnectionUserName(con).toUpperCase() ; 
      String queryStr = " Select * from "
      	  + "("
    	  + "select * from support.sql_bound_vars where active = 'Y'" 
          + " and upper(owner) = '"+loggedUser + "'"
          + " and company_id = icdb.security.get_user_company('"+loggedUser+"')" 
          + " union "
          + " select * from support.sql_bound_vars def where def.active = 'Y' and upper(def.owner) = 'DEFAULT' " 
		  + " 		and def.company_id = icdb.security.get_user_company('"+loggedUser+"')"
          + "       and def.BOUND_VAR_NAME not in "
          + "		( select BOUND_VAR_NAME from support.sql_bound_vars "
          + "          where active = 'Y' and upper(owner) = '"+loggedUser +"'"
          + "		   and  company_id = icdb.security.get_user_company('"+loggedUser+"') " 
          + "		)" 
          + ") "
          + " where bound_var_name in " 
          + " ( " 
          + "  select distinct '$$'|| param_name from "  
          + "  ( select aa.param_name "
          + "    from TABLE (icdb.udv_services.get_Query_Params ( ( " 
          + "                select support.query_services.get_query_after_subs_refrence ( '"+queryId+"' )  from dual "  
          + "      )) ) aa " 
          + "    order by aa.param_index " 
          + "  ) "
          + " ) "
	      + " order by sn " ;
      
      java.sql.ResultSet rs =  stmt.executeQuery(queryStr ) ; 
      while (rs.next())      
      {
        titles.addElement(rs.getString("title")) ;
        sqlBoundVar.addElement(rs.getString("bound_var_name")) ;
        active.addElement(rs.getString("active")) ;
        defaultValues.addElement(( rs.getString("default_val")== null ) ? "" : rs.getString("default_val")) ;
        dataTypes.addElement(rs.getString("DATA_TYPE")) ;
        QUERY_FOR_LIST.addElement(rs.getString("QUERY_FOR_LIST")) ;
        other_attributes.addElement(rs.getString("OTHER_ATTRIBUTES"));
     }
      rs.close();
      
      //------------------Rendering Bind Variables Titles and Names------------------------------
      
      Support.SqlReader  sqr =null ;
       ArrayList<String> queryParams = new ArrayList<String>() ; 
       String queryDesc ="";
       String queryHyperLinkTitle = ""; 
       try
       {
      	sqr =  new Support.SqlReader(repCon, "lu_queries" , "QUERY_BODY",  queryId, session , request, true );
      	queryParams =  sqr.getQueryParams() ;
       	queryDesc = sqr.getQueryADesc(); 
       	queryHyperLinkTitle = sqr.getQueryHyperLinkTitle() ;
	   }
       catch (Exception e){}
		String nodeHelpLink = "<a target = 'Report Meta Data' title = 'Click for More Details and Help Docs related to this node in the Main System Tree'  href = 'editAndExecute.jsp?id=13654&lookupTableName=LU_QUERIES&query_id="+queryId+ "' ><img src='"+appURL+"/images/help.gif' ></img></a>" ; 

      %>
	   <h1 title = "<%= queryHyperLinkTitle%>"><font size="5" style="Bold"> <%=queryDesc %> (<%=nodeHelpLink %>)</font> </h1>

      <div style="float:right; width:100%; height:20px;"></div>
      <form name="form1" method="post" action="queryParams.jsp?id=<%=queryId%>">  
      <div class="form-group">
		<%

		int elementsCounter = 0 ;
		Support.HTMLDbTransactionBeanWOPooling db = new Support.HTMLDbTransactionBeanWOPooling();
   		db.myInitialize(application,session,request,response,out,con);
   		db.setDBConnection(con);
   		
   		String htmlClass = "col-sm-3" ; 
		if ( titles.size() > 4 ) htmlClass = "col-sm-2" ; 
		
      for (int i = 0 ; i< titles.size() ; i++)
      {  
    	String queryParamToBeRendred = sqlBoundVar.elementAt(i).toString().substring(2) ;
    	boolean queryParamSentInRequest = request.getParameter(queryParamToBeRendred) != null ;
    	boolean renderParamHtmlInput = queryParams.contains(queryParamToBeRendred.toUpperCase()) ; //  && ! queryParamSentInRequest ; 
    	
		
    	if ( renderParamHtmlInput )
      	{
    		
        %>
        <div class="<%=htmlClass%>">
        	<label class="control-label text-left"  style="width: 100%"  title="(<%=sqlBoundVar.elementAt(i)%>)"><%=titles.elementAt(i)%></label>
		<input name="sqlBoundVar<%=i%>"  type='hidden' value='<%=sqlBoundVar.elementAt(i)%>' readonly size="3">
        <% 
			elementsCounter ++ ; 
	      	boolean isDate = (dataTypes.elementAt(i).toString().toUpperCase().equals("DATE")) ? true : false ;
	      	boolean isDateTime = (dataTypes.elementAt(i).toString().toUpperCase().equals("DATETIME")) ? true : false ;
	      	boolean isList = (dataTypes.elementAt(i).toString().toUpperCase().equals("LIST")) ? true : false ;
	      	boolean isMultiSelectTree = (dataTypes.elementAt(i).toString().toUpperCase().equals("MULTI_SELECT_TREE")) ? true : false ;
	      	boolean isSingleSelectTree = (dataTypes.elementAt(i).toString().toUpperCase().equals("SINGLE_SELECT_TREE")) ? true : false ;
	      	String queryParamValueFromRequest = request.getParameter(queryParamToBeRendred) ;
	      	
    	  	String varName = sqlBoundVar.elementAt(i).toString().substring(2) ;
      	   	String defaultValue = defaultValues.get(i).toString();
    	  	String varValue = (sqlBoundNames.indexOf("$$"+varName) != -1 ) ?  sqlBoundValues.elementAt(sqlBoundNames.indexOf("$$"+varName)) : defaultValue ; 
    		if (queryParamSentInRequest)
			{
    	  	%>
				<input 	name="var<%=i%>" value='<%=queryParamValueFromRequest %>'
							type="hidden" size="6"> <%=queryParamValueFromRequest %>
				<% 
			}
    	  	else 
    	  	{
	       		if ( isDate ) 
	    	    { 
	           		if (varValue.toUpperCase().equals("SYSDATE"))
	           		{
	            		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
	            		java.util.Date nowDate = new java.util.Date();
	            		varValue = (varValue.equalsIgnoreCase("SYSDATE"))? sdf.format(nowDate) : varValue ; 
	            		//sqlBoundValues.setElementAt(sdf.format(nowDate), sqlBoundNames.indexOf("$$"+varName) ) ;
	           		}
	           		out.print(Support.Misc.renderhtmlTextFildForDate("var"+i ,"form1" ,"size = 6 onchange = '' value = " + varValue ,"dd-mm-yyyy"));
	         	} 
				else if (isDateTime)
				{
					if (defaultValue.toUpperCase().equals("SYSDATE"))
	           		{
	            		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
	            		java.util.Date nowDate = new java.util.Date();
	            		varValue = (varValue.equals(""))? sdf.format(nowDate)+"T00:00:00" : varValue ; 
	           		}
	           		%>	<input name="var<%=i%>" type="datetime-local" id="var<%=i%>" 
							max="2099-01-01" min="1980-01-01" value="<%=varValue%>"> <%
				}
	         	else if ( isList ) 
	         	{ 
	           		String queryList = QUERY_FOR_LIST.elementAt(i).toString();//"select EXTERNALKEY  , EXTERNALKEY  from bgadmin.organization where Substr(id , 14, 3) = 'B64'" ;
	           		//-- Subsitute variables with its values in the query List
	           		boolean queryContainsVariables = queryList.indexOf("$$") != -1 ;
	           		if (queryContainsVariables)
	           		{
		           		for (int j = 0 ; j< sqlBoundVar.size() ; j++)
		    			{
		             	  	String varName1 = sqlBoundVar.elementAt(j).toString();
		             	  	if (queryList.indexOf(varName1) != -1)
		             	  	{
		             	  		String valueFromRequest = request.getParameter(varName1.substring(2)) ; 
		                 	  	String varValue1 = (valueFromRequest == null )? ( (sqlBoundNames.indexOf(varName1) != -1 ) ?  sqlBoundValues.elementAt(sqlBoundNames.indexOf(varName1)) : defaultValue ) : valueFromRequest;
		                 	  	queryList = Misc.repalceAll(queryList , varName1 , varValue1 ) ; 
		                 	  	queryContainsVariables = queryList.indexOf("$$") != -1 ;
		             	  	}
		             	  	
		             	  	if (!queryContainsVariables)
		             	  	{
		             	  		break ; 
		             	  	}
		    			}
	           		}
	           		//--
	           		db.popUpLimitFromSysParm = false;
	           		
		           	try{
		           	String defVal = (varValue == null)? defaultValues.elementAt(i).toString().trim() : varValue; 
		           	
		           	db.renderQueryResultAsSelectList(queryList, "form1","var"+i,"varHidden"+i ,defVal, "class='form-control' style='color:red'"  +other_attributes.elementAt(i), false  );
		           	}
		           	catch ( Exception e)
		           	{
		        	  out.print(e.getMessage());
		        	 %><input onBlur() ="alert('abc');" name="var<%=i%>" 	value='<%=varValue %>' type="text" size="6"> <% 
		   
		           	}
		      	} 
				
	         	else if (isMultiSelectTree || isSingleSelectTree)
	         	{
	         		String operationMode = null ; 
		         	if (isSingleSelectTree) operationMode = Support.LookupTreeV10.SINGLE_SELECT_OPERATION_MODE ; 
		         	else if (isMultiSelectTree) operationMode = Support.LookupTreeV10.MULTI_SELECT_OPERATION_MODE ;
					String treeIdInSession = "sqlBoundVar_"+sqlBoundVar.elementAt(i).substring(2) ; 
					LookupTreeV10 tmdTree = (LookupTreeV10)session.getAttribute(treeIdInSession); 
		         	String itemsDesc = null; 
		         	try {itemsDesc = (isMultiSelectTree)? tmdTree.getDescForListOfIds(varValue.replaceAll("\n" , "")) : tmdTree.getDesc( tmdTree.getindex2(varValue)) ; } 
		         	catch (Exception e) {e.printStackTrace(); } ; 
		         	
		         	int treeQuerySource = 2 ; // inform Tree to get query from tmd 
		        	 %>
		        	 <div><input readonly type="text"  onBlur() ="alert('abc');"
							name="var<%=i%>" id="var<%=i%>"  value='<%=varValue %>'
							onchange=" 
							           updaetHref_var( this , document.getElementById('var<%=i%>_TreeLink')  ) ;  
							           document.getElementById('updateOnlyButtonId').click(); 	 	  	
							         "  
							size="10" title="">
						
						 <a target = "tree Selection"  id="var<%=i%>_TreeLink"
		        	      		title = "Click To Selected Items from Tree" 
		        	      					href = "javascript:window.open('<%=appURL%>/selectionTree.jsp?_operationMode=<%=operationMode%>&_boundVarName=<%=sqlBoundVar.elementAt(i).substring(2) %>&_selectedIDs=<%=varValue %>&_querySouce=<%=treeQuerySource%>&treeIdInSession=<%=treeIdInSession%>&_fillObject=var<%=i%>' , 'Select_From_Tree' , 'width=400, height=600' ) " ><img width="25" height = "25"  src="<%=appURL%>/images/treeIcon.jpg"> </a> 
						
						<div id="var<%=i%>_label"><%=itemsDesc %></div>
					 
		        	  	</div>
	            	 	
	            	 	
		        	 
		        	<%
	
	         	}
         	
	         	else 
	         	{
	           	%>
					<input onBlur() ="alert('abc');"
					name="var<%=i%>" value='<%=varValue %>'
					type="text" size="6">
				<% 
	         	} 
    	  	}
      	
         %>
		
 		</div>
		<% 
		}
      }
      //-----------End of Rendering Bind Variables Titles and Names------------------------------
   	db.stmt.close();
    stmt.close();
        
    if ( elementsCounter > 0 ) { %>
    <div class="<%=htmlClass%>" style="margin-top:22px">
		<input type="submit" class="btn-primary btn" name="Submit" value="تنفيذ"   ">
		<input type="submit" class="btn-success btn" id = "updateOnlyButtonId" name="updateOnly" 	value="Update Only">
	</div>
			
	<%}%>
</div>
</form>

</div>
</div>
</div>
</body>
</html>
