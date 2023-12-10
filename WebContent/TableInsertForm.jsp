
<%@page import="com.smartValue.DbInserter"%>
<%@page errorPage="errorPage.jsp" 	import="Support.*, java.util.*, java.io.* , java.sql.* "%>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%@page import="com.implex.database.map.SecUsrDta , com.implex.database.map.TableMaintDetails"%>
<%request.setCharacterEncoding("UTF-8");%>


<%@page import="java.net.URLEncoder"%><html>
<head>
	<%String appUrl = Support.Misc.getAppURL(request) ; %>
	<link rel="stylesheet" type="text/css" href="<%=appUrl%>/includes/HijriCalender/lib/jquery.calendars.picker.css">
	<script type="text/javascript" src="<%=appUrl%>/jQueryAssets/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=appUrl%>/includes/HijriCalender/lib/jquery.calendars.js"></script>
	<script type="text/javascript" src="<%=appUrl%>/includes/HijriCalender/lib/jquery.calendars.plus.js"></script>
	<script type="text/javascript" src="<%=appUrl%>/includes/HijriCalender/lib/jquery.calendars.picker.js"></script>
	<script type="text/javascript" src="<%=appUrl%>/includes/HijriCalender/lib/jquery.calendars.picker-ar.js"></script>
	<script type="text/javascript" src="<%=appUrl%>/includes/HijriCalender/lib/jquery.calendars.ummalqura.js"></script>
	<script type="text/javascript" src="<%=appUrl%>/includes/HijriCalender/lib/jquery.calendars.ummalqura-ar.js"></script>
	<script type="text/javascript" src="<%=appUrl%>/includes/HijriCalender/hijriCalender.js"></script>
	<script type="text/javascript" src="<%=appUrl%>/includes/smartTool.js"></script>
	<script type="text/javascript" src="<%=appUrl%>/includes/AJAX_new.js"></script>
	<!--  Jquery Validation Engine  -->
	<link rel="stylesheet" href="<%=appUrl%>/jQuery-Validation-Engine/css/validationEngine.jquery.css" type="text/css"/>
	<link rel="stylesheet" href="<%=appUrl%>/jQuery-Validation-Engine/css/template.css" type="text/css"/>
	<script src="<%=appUrl%>/jQuery-Validation-Engine/js/jquery-1.8.2.min.js" type="text/javascript">	</script>
	<script src="<%=appUrl%>/jQuery-Validation-Engine/js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8">	</script>
	<script src="<%=appUrl%>/jQuery-Validation-Engine/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8">	</script>
	<script>
		jQuery(document).ready(function(){
			// binds form submission and fields to the validation engine
			jQuery("#formID").validationEngine();
		});
	</script>

	<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
	<title>نموذج ادخال بيانات Data Entry Form </title>
</head>
<body>
<jsp:include page="/includes/serverMessagesList.jsp"></jsp:include>
<div id = "sysMessage" > </div>
	<p>
		<a href="#" onclick="alert('is Your inputs valid? '+jQuery('#formID').validationEngine('validate'))">Check Validity of Your Inputs تأكد من جاهزية بياناتك	</a>
	</p>
<%


boolean AutoLog  = ( request.getParameter("AutoLog") != null ) ; //----To Enable automatically read page contents 
//-------------The follwoing Connection Used to get The Queries Where it may reside in a defrent DB
java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");
if( ( repCon == null|| repCon.isClosed())  && AutoLog )
{ 
  XMLConfigFileReader aa =  Misc.getXMLConfigFileReader(false ) ; 
  repCon = aa.reposatoryConn.generateConnection();
  session.setAttribute("repCon",repCon);
}
java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
if (con == null || con.isClosed())
{
  if( AutoLog )
  { 
   //--TO be used at Normal Production ---
   XMLConfigFileReader aa =  Misc.getXMLConfigFileReader(false ) ;
   //Misc abc = new Misc(repCon);
   String autologinDB =SysConfigParams.getAutoLogin_Environment_Name();// abc.getSystemParameterValue(8);
   String autoLoginUserName = SysConfigParams.getAutoLogin_Username();// abc.getSystemParameterValue(9);
   String autoLoginPassword = SysConfigParams.getAutoLogin_Password();// abc.getSystemParameterValue(10);
   
   con = aa.getConnParmsByName(autologinDB).generateConnection(autoLoginUserName, autoLoginPassword , "NORMAL");
   session.setAttribute("con",con);
   }
  else { session.setAttribute("queryString",request.getQueryString());
        response.sendRedirect("loginScreen.jsp");
       }
}
  SecUsrDta  loggedUser = (SecUsrDta)session.getAttribute("loggedUser") ;
  boolean smartToolAdmin = loggedUser.isSmartToolAdmin() ;

  String tableName = request.getParameter("tableName");
  String owner = request.getParameter("owner");
  String qs = "select t.* , t.rowid  from Support.table_maint_details t where upper(table_name) = upper('"+tableName+"') and upper(owner) = upper('"+owner+"') and column_included = 'Y' order by tab_index ";
  com.implex.database.map.TableMaintMaster tmm = Misc.getTableMaintMaster(session , owner , tableName);  
  String formValidationScript = "" ;
  String formValidationScriptAnding = "" ;
  String tableObjectName = (loggedUser.isUsrLangEnglish())? "Add New : "+ tmm.getObjectNameValue() :"إضافة "+ tmm.getObjectName_Value() + " جديد بالنظام " ;
  %>
<p align="center"><strong><font size="5"><%=tableObjectName%></font></strong> </p>
</p>
	<% if (!con.getAutoCommit()) { %>
	<div align="center">
  	 	<jsp:include page = "/includes/dbTransc.html" ></jsp:include>
  	</div>

	<% }
  Vector<String> sqlBoundNames = (Vector<String>)session.getAttribute("sqlBoundNames") ; if (sqlBoundNames == null) sqlBoundNames =  new java.util.Vector<String>(); 
  Vector<String> sqlBoundValues = (Vector<String>)session.getAttribute("sqlBoundValues") ; if (sqlBoundValues == null) sqlBoundValues =  new java.util.Vector<String>();//new java.util.Vector(); 
  Vector<String> sqlBoundNamesUpperCase =  new java.util.Vector<String>();
  int index = 0 ;
  for (String s : sqlBoundNames)
  {
	  sqlBoundNamesUpperCase.addElement(sqlBoundNames.elementAt(index).toUpperCase());
	  index++;
  }
  int counter = 0;
  String queryStringForLastInsert_VarName = "queryStringForLastInsert" ; // Used to keep request Details in sesskion for subsequent insert for the same table and the same intial values  
  
  if( request.getParameter("Save") != null )
  {
	  DbInserter.insert(session, request, response, out) ; 
	  return ; 
  }
  else {
  session.setAttribute(queryStringForLastInsert_VarName , request.getQueryString());
  java.sql.ResultSet rs = repCon.createStatement().executeQuery(qs);
  counter = 0 ; 
  %>
  <form name="<%=tableName+"_InsertForm"%>" id="formID" action="TableInsertForm.jsp" method="post">
  <table width="100%" border="0" dir="rtl">
  	<tr>
  		<td><input name="tableName" type="hidden" value="<%=tableName%>">
  		<input name="owner" type="hidden" value="<%=owner%>"></td>
  	</tr>
  <%
  while (rs.next())
  { counter++;
   int html_type = Integer.parseInt(rs.getString("HTML_TYPE"));
   String sql = rs.getString("SELECT_LIST_QUERY");
   String jsv = rs.getString ("JAVA_SCRIPT_VALIDATION");
   String displayName = rs.getString("DISPLAY_NAME");
   String displayName_ = rs.getString("DISPLAY_NAME_");
   String comments = rs.getString("COMMENTS");
   String columnName = rs.getString("COLUMN_NAME");
   String attribute = rs.getString("ATTRIBUTE");
   String defaultValue = rs.getString ("DEFAULT_VALUE");
   String jQueryValidatorClass = rs.getString ("JQUERY_VALIDATOR");
   String rowid = rs.getString ("ROWID");
   String disabledStr = rs.getString ("DISABLED");
   boolean disabled = (disabledStr != null && disabledStr.equalsIgnoreCase("Y")) ; 
   String resultDevId = "validDiv"+ columnName ;

   boolean columnValueFoundInSession = false ;
   boolean columnValueFromRequest = false;
   String columnValue ="";
   String columnValueForrSql = "" ; 
   boolean htmlControlDisabled = false ; 
   if (request.getParameter(columnName)!= null) 
	   {
	   columnValue =  request.getParameter(columnName);
	   columnValueFromRequest = true;
	   }
   else
   {
	   try{
	   columnValue = sqlBoundValues.elementAt(sqlBoundNamesUpperCase.indexOf("$$"+columnName));
	   columnValueFoundInSession = true;
	   }
	   catch(Exception e) {}
   }
   htmlControlDisabled = columnValueFromRequest || disabled ; 
   if ( ( columnValue == null || columnValue.equals("")) && defaultValue != null )
   {
	   columnValue = defaultValue ; 
   }
   
	%>
		<tr>
			<td title="<%=comments%>"><strong><%=((html_type == 6)? "": displayName_ ) %></strong></td>
		<td>
		<%
  try{

	if (html_type == 1) // input Text
   	{  
    	%> <input id = "<%=columnName %>" data-validation-engine ="<%=jQueryValidatorClass %>"  <%=htmlControlDisabled? "disabled=disabled" : "" %>  name="<%=columnName%>" type="text" <%=(columnValue !=null)? "Value = \"" + columnValue + "\"":"" %>" <%=((attribute!=null)? attribute : "" )%> > 
		<%  
   	}

	else if (html_type == 2 || html_type == 3 || html_type == 13 ) // drop down List or Option list
   {
	try{
	 Support.HTMLDbTransactionBeanWOPooling db = new Support.HTMLDbTransactionBeanWOPooling();
     db.myInitialize(application,session,request,response,out,con);
     db.setDBConnection(con);
     db.popUpLimitFromSysParm = true;
     // Replacing Query Paramters (if Any ) With their values from requet 
     String paramInitial = "$$" ; 
     ArrayList<String> paramNames = SqlReader.getParamQueryNames(sql , paramInitial);
     String sqlAfterSubst = sql ; 
     for (String paramName : paramNames)
     {
    	 String paramValue =request.getParameter(paramName) ; 
    	 if (paramValue != null)
    	 {
    		 sqlAfterSubst =Misc.repalceAll(sqlAfterSubst , paramInitial+ paramName , paramValue) ; 
    	 }
     }
     
     if (html_type == 3) 
     db.renderQueryResultAsSelectList(sqlAfterSubst, tableName+"_InsertForm",columnName,"varHidden" ,columnValue, attribute + (htmlControlDisabled? " disabled=disabled" : "" ) + " data-validation-engine=" + jQueryValidatorClass , false  );
     if (html_type == 2) 
     db.renderQueryResultAsSelectOptions(sqlAfterSubst, tableName+"_InsertForm",columnName ,columnValue,attribute + (htmlControlDisabled? " disabled=disabled" : "" )+ " data-validation-engine=" + jQueryValidatorClass );
	 if (html_type == 13) 
	 {
	 	// db.renderQueryResultAsMultiSelect(sql, tableName+"_InsertForm",columnName ,columnValue,attribute + (htmlControlDisabled? " disabled=disabled" : "" ));
	 	db.setQueryStatment(sqlAfterSubst);
    	Vector[] data=db.getResultSetAsArrayofVectors();
      	for (int i=0; i<data[0].size(); i++)
        {
          	String value = data[0].elementAt(i).toString() ;
          	String option = data[1].elementAt(i).toString() ;
          	boolean isChecked =  (columnValue != null && columnValue.equalsIgnoreCase(value));
            out.println( "<br>" + option + " <input type='checkbox' data-validation-engine = "+jQueryValidatorClass +" name='"+columnName+"' value='"+value+ "'" +( (isChecked)? " checked = 'Y' " : "" ) +">" );
        }
     }     
	}
	catch (Exception e) 
	{
		System.out.print("Unable to render List Due to " + e.getMessage()); 
	    %> 
	       <input <%=htmlControlDisabled? "disabled=disabled" : "" %>  data-validation-engine ="<%=jQueryValidatorClass %>" name="<%=columnName%>" type="text" <%=(columnValue !=null)? "Value = '" + columnValue + "'":"" %>" <%=attribute%> onChange = "<%=jsv%>" >
		<%
	}

   }

	else if (html_type == 4) // calender
   {	
		try{
    	out.print(Support.Misc.renderhtmlTextFildForDate(columnName ,tableName+"_InsertForm" ,"data-validation-engine = "+ jQueryValidatorClass + " size = 6 value ='' " , "dd-mm-yyyy"));
   		}
   		catch ( Exception e) {
   		    %> <input data-validation-engine ="<%=jQueryValidatorClass %>" <%=htmlControlDisabled? "disabled=disabled" : "" %>  name="<%=columnName%>" type="text" <%=(columnValue !=null)? "Value = '" + columnValue + "'":"" %>" <%=attribute%> onChange = "<%=jsv%>" > <%
   		}
   }

	else if (html_type == 5) // Text Area
   {
   %> <textArea data-validation-engine ="<%=jQueryValidatorClass %>"  <%=htmlControlDisabled? "disabled=disabled" : "" %> name="<%=columnName%>" <%=attribute%>><%= columnValue%></textArea> <%   
   }


	else if (html_type == 6) // Hidden
   {
   %> <input name="<%=columnName%>" type="hidden" value="<%= columnValue%>" <%=attribute%>> <%   
   }

	else if (html_type == 9) // checkbox
   {
   	 %>
  		
  		<input type="checkbox"    Value = "<%=columnValue%>"  <%=(columnValue.equalsIgnoreCase("Y") || columnValue.equalsIgnoreCase("Yes") )? "checked" : "" %>   
        	onClick=" if (this.checked ) {<%=columnName%>.value='Y';} else {<%=columnName%>.value='N';}; " >	
        		        	     	 <input type="hidden"  name="<%=columnName%>"  value="" > 
 	<%
   
   }
	else if (html_type == 10) // Hidden
   	{
   	%> <%=columnValue%> <%   
   	}

	else if (html_type == 11) // date and Time
   	{
   	    %>
   	    	<input data-validation-engine ="<%=jQueryValidatorClass %>"  name="<%=columnName%>" type="datetime-local" id="var<%=columnName%>" 
				max="2099-01-01" min="1980-01-01" value="<%=columnValue%>"  <%=attribute %>  > 
		<%
   
   	}

	else if (html_type == 12) // Hijri Date 
   	{	columnValue = (columnValue == null || columnValue.equalsIgnoreCase(""))? "" : columnValue ; 
		String validatorValue = "data-validation-engine ='"+jQueryValidatorClass +"'" ;  
   	    %>
        		<jsp:include page="./includes/HijriCalender/hijriCalender.jsp">
	       			<jsp:param value="<%=columnValue%>" name="columnValue"/>
           			<jsp:param value="<%=columnName%>" name="columnName"/>
           			<jsp:param value="dd/mm/yyyy" name="dateFormat"/>
           			<jsp:param value="<%=validatorValue %>" name="validator"/>
           			 
           			
           		</jsp:include>

		<%
   
   	}
	else if (html_type == 14 || html_type == 15) // Multi or single Select Tree 
	{	String operationMode = (html_type == 14 )? LookupTreeV10.MULTI_SELECT_OPERATION_MODE :LookupTreeV10.SINGLE_SELECT_OPERATION_MODE ;
		int treeQuerySource = 1 ; 
		
		%> <textarea data-validation-engine ="<%=jQueryValidatorClass %>"  readonly <%=htmlControlDisabled? "disabled=disabled" : "" %> id=<%=columnName%> name = <%=columnName%>  <%=attribute%> onchange="  updaetHref_<%=columnName%>( this ) ;  " ><%=columnValue%></textarea>
			<a target = "tree Selection"  id="<%=columnName%>_TreeLink"
      					title = "Click To Selected Items from Tree" 
      					href = "javascript:window.open('selectionTree.jsp?refreshAll=xx&_operationMode=<%=operationMode%>&_querySouce=<%=treeQuerySource%>&_selectedIDs=<%=columnValue%>&treeIdInSession=<%=owner %>/<%=tableName%>/<%=columnName%>&_fillObject=<%=columnName%>' , 'Select From Tree' , 'width=450, height=600' ) " ><img width="25" height = "25"  src="images/treeIcon.jpg"> </a>
		<div id="<%=columnName%>_label"></div>
		
	 	<script type="text/javascript">
	 			function updaetHref_<%=columnName%>(m_object)
	 			{
	 				var newhref = "javascript:window.open('selectionTree.jsp?_operationMode=<%=operationMode%>&_querySouce=<%=treeQuerySource%>&_selectedIDs=" + m_object.value + "&treeIdInSession=<%=owner %>/<%=tableName%>/<%=columnName%>&_fillObject=<%=columnName%>' , 'Select From Tree' , 'width=400, height=600' )" ;  
	        	    <%=columnName%>_TreeLink.href =  newhref ; 
	 			}
	 	</script>
		 <%

	}
	
	if (jsv!=null) 
	{ 	
		String objValidScript =  "validate"+columnName+"(document.getElementById('"+columnName+"'))" ;
		formValidationScript +=       ((formValidationScript.equalsIgnoreCase(""))? "" :"\n") + "var vr"+counter+" = check"+columnName+"() ; " ;
		formValidationScriptAnding += (( formValidationScriptAnding.equalsIgnoreCase("")) ? " " : " & "  )+ " vr"+counter+".status " ; 
		%>
		<script> 
			function validate<%=columnName%>(object) 
			{
				<%=jsv%>  
			}
			function check<%=columnName%>()
			{
				document.getElementById('<%=resultDevId%>').innerHTML = "<img width = 20px height = 20px  src = '/SmartTool/images/ajaxLoading.gif' ></img> Please Wait ..." ;
				validationResult = <%=objValidScript%> ;
				document.getElementById('<%=resultDevId%>').innerHTML = validationResult.message ; 
				return validationResult ; 
			}
	  	</script>
	  	<a href = "javascript: check<%=columnName%>()" >Check</a>		
		<% 
	}

		if (htmlControlDisabled)
 		{%><input type="hidden" name="<%=columnName%>" value="<%=columnValue %>"> <%}
 
   }  
   catch (Exception e) { throw new Exception ("Unable to Render <a href = 'tableFormEditor.jsp?owner="+owner+"&tableName="+tableName+"'>Table Insert Form</a>Table Insert Form Due to " + e.getMessage());}
	%>
		<div  style = "color:red " id=<%=resultDevId%>></div>
		</td>
		<td><%=(comments==null) ? "": comments  %></td>
		<% if (smartToolAdmin) { %>
		<td><a target = "Column Design" href = "tableEditor.jsp?ROWID=<%=URLEncoder.encode(rowid) %>&tableName=SUPPORT.TABLE_MAINT_DETAILS" >Design</a></td>
		<% } %>
	</tr>
	<%
  }
  
%>
</table>

<div align="center">

	<p>
	<% if (!formValidationScriptAnding.equals("")) { %>
	<script>
		function formValidate()
		{
			<%=formValidationScript%>
			
			var result =  <%=formValidationScriptAnding%> ;
			return (result === 0 )? false : true ;
		}
	</script>
	<% } %>
	
	<a href = 'javascript: closeWindow(); ' ><img title="إغلاق-Close" src="/SmartTool/images/exit.png" width="24"></a>
	<input class="submit" type="submit" name="Save" value="حفظ" title = "إضغط هنا اولا للحفظ المبدئي ثم الضغط على السهم الاخضر عند الحفظ النهائى" onclick ="return formValidate() ; "></p>
	<%  
	   if (smartToolAdmin) 
	   { %>
		<p align="left"><a href="tableFormEditor.jsp?owner=<%=owner%>&tableName=<%=tableName%>">Reconstruct The Form</a></p>
		<p align="left"><a href="editAndExecute.jsp?id=25192&owner=<%=owner%>&table_name=<%=tableName%>">New(Smart Tool) Reconstruct The Form</a></p>
	   <% } %>
	
</div>
</form>
<% } %>
</body>
</html>