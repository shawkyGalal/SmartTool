<%@ page errorPage="../../errorPage.jsp"
	import="java.util.*, java.io.* , java.sql.* , java.text.SimpleDateFormat , javax.faces.model.SelectItem"%>
<%@ page import="java.util.* , Support.Misc"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.implex.database.map.*"%>

<%@page import="java.net.URLEncoder"%>

<%@page import="java.math.BigDecimal"%><html>
<head>
<% String appUrl =  Support.Misc.getAppURL(request) ;  // "/SmartTool" ; %>
	<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="<%=appUrl%>/includes/HijriCalender/lib/jquery.calendars.picker.css">
		<script type="text/javascript" src="<%=appUrl%>/jQueryAssets/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="<%=appUrl%>/includes/HijriCalender/lib/jquery.calendars.js"></script>
		<script type="text/javascript" src="<%=appUrl%>/includes/HijriCalender/lib/jquery.calendars.plus.js"></script>
		<script type="text/javascript" src="<%=appUrl%>/includes/HijriCalender/lib/jquery.calendars.picker.js"></script>
		<script type="text/javascript" src="<%=appUrl%>/includes/HijriCalender/lib/jquery.calendars.picker-ar.js"></script>
		<script type="text/javascript" src="<%=appUrl%>/includes/HijriCalender/lib/jquery.calendars.ummalqura.js"></script>
		<script type="text/javascript" src="<%=appUrl%>/includes/HijriCalender/lib/jquery.calendars.ummalqura-ar.js"></script>
		<script type="text/javascript" src="<%=appUrl%>/includes/HijriCalender/hijriCalender.js"></script>
		
		<link rel="stylesheet" href="<%=appUrl%>/jQueryAssets/jquery-ui.css">
		<script src="<%=appUrl%>/jQueryAssets/jquery-ui.js"></script>
		<%@ include file="/includes/jquery_commons.js"%>
		<script>
		  $(function() {
		    $( "#sysMessage" ).dialog(
		    { position: { my: "center top", at: "center top", of: window  } },
		    {
		      autoOpen: false,
		      modal : true , 
		      width : 800 ,
		      show: { effect: "explode", duration: 500  },
		      hide: { effect: "explode", duration: 500  },
		      buttons: {
        		Ok: function() {
          				$( this ).dialog( "close" );}
          				}, 
		      close : function () {
		      history.go(0);  
		      $( this ).dialog( "close" );}
		      
		    });
		    
		  });
		</script>
		<title>تعديل عقد</title>
		
		<script language=javascript>
		   function confirmDelete()
		   {
		     if (confirm("Are you sure you want to Commit Your Transaction(s)?")) 
		        {return true;} 
			 else {return false;}
		    }
		  </script>
		<style type="text/css">
			body {	background-color: #00FFff; }
		</style>
	
	</head>

	<body>
	<script type="text/javascript" src="<%=appUrl%>/includes/AJAX_new.js"></script>
	<p align="center">
	<%
  		String rowIdValue =  request.getParameter("ROWID");
		boolean useRowIdValue = rowIdValue != null ;	
		
  		boolean printable = request.getParameter("printable") != null &&  request.getParameter("printable").equalsIgnoreCase("true");
  		String _rqs = request.getQueryString() ;  
		if ( _rqs!= null)
			{
			_rqs =  Misc.repalceAll(_rqs , "Delete=Y&" , "") ;
			_rqs =  Misc.repalceAll(_rqs , "Commit=Y&" , "") ;
			_rqs =  Misc.repalceAll(_rqs , "RollBack=Y&" , "") ;
			} 


	  java.sql.Connection  con , repCon  =null;
	  con = (java.sql.Connection)session.getAttribute("con");
	  repCon = (java.sql.Connection)session.getAttribute("repCon");
	  //String loggedUser = Misc.getConnectionUserName(con) ;  
	  java.sql.Statement stmt = con.createStatement();  
	  String uniqueColumnName = "rowId";
      String tableowner = "CARRENT" ;
	  String tableNameonly = "CONTRACT" ;
	  
	  String uniqueWhereClauseVarValue = "" ; 
	  String uniqueWhereClauseVarName = "uniqueWhereClause" ; 
	  
	  	  
	  if ( useRowIdValue ) 
	  {
		  String uniqueColumnValue = "'" + request.getParameter("ROWID") +"'";
		  uniqueWhereClauseVarValue = "t."+uniqueColumnName+" = " + uniqueColumnValue ; 
	  }
	  else 
	  {
		  uniqueWhereClauseVarValue = request.getParameter(uniqueWhereClauseVarName) ;  
	  }
 
	  //-----------if Delete Pressed------------------------------
	   TableMaintMaster contractTmm = Support.Misc.getTableMaintMaster(session , tableowner , tableNameonly) ;
	  
	  if (request.getParameter("Delete") != null )
  {
	    con.setAutoCommit(false);
	    String loggedUser = Misc.getConnectionUserName(con) ;  
	    //java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");
		boolean userCanDelete = false ;
		Statement repStmt = null;
		ResultSet rs = null ; 
		try 
		{
			userCanDelete = contractTmm.isUserCanDelete(loggedUser , repCon);
		}
		catch (Exception e)
		{
			e.printStackTrace() ; 
			out.println("System Error : Unable to Check User Ability to Delete Due to : "+e.getMessage()+"") ;
			if (rs != null ) rs.close();
			if (repStmt != null ) repStmt.close() ; 
		}
		if (! userCanDelete )
		{
			out.print( "<font color='FF0000' > You Are Not Authorized. <a href ='../editAndExecute.jsp?id=25048&user_name="+loggedUser+"' target = 'User Roles' >Why?</a> </font>" ) ; 
		}
		else 
		{
			//----Before Delete Operation ---------
		    com.smartValue.tableControllers.ItableTriggerController tableTriggers = null;
		   	Savepoint sp1 = con.setSavepoint(); 
			try{ 	tableTriggers = contractTmm.getTableTriggers();} catch (Exception e){}
			try 
			{
			  	if (tableTriggers != null) 
		  		{
					tableTriggers.setRepCon(repCon);
		  			tableTriggers.beforeDelete(con , rowIdValue ) ;
		  		}
				String deleteStmt = "delete from " + tableowner +"." + tableNameonly + " t where "+uniqueWhereClauseVarValue ;
				com.implex.event.logging.Console.log(deleteStmt , this.getClass()) ; 
			    //con.setAutoCommit(false);
			    //--Execute Delete Operation----- 
			    int x = stmt.executeUpdate(deleteStmt);
	
				// ----- After Delete trigger ------
			  	if (tableTriggers != null) 
		  		{
		  			tableTriggers.afterDelete(con , rowIdValue ) ;
		  		}
	 		    out.print(x + " Record(s) Deleted Successfully");
			}
			catch (Exception e ) {
				con.rollback(sp1) ;
				throw e; 
			}
		    return ;
		}
  }
	/*
	  if (request.getParameter("Delete") != null )
	  {
	  	
	    Statement delStmt = con.createStatement();
	    String deleteStmt = "delete from " + tableowner +"."+ tableNameonly + " where "+uniqueColumnName+" = " + uniqueColumnValue ;
	    con.setAutoCommit(false);
	    Savepoint sp1 = con.setSavepoint();
	   	//stmt = con.createStatement();
	    
	    try 
	    {
	    	TableMaintMaster contractTmm = Support.Misc.getTableMaintMaster(session , tableowner , "CONTRACT") ;
	  	    com.smartValue.tableControllers.ItableTriggerController tableTriggers = null;
			try{ 	tableTriggers = contractTmm.getTableTriggers();} catch (Exception e){}
		  	if (tableTriggers != null) 
	  		{
				tableTriggers.setRepCon(repCon);
	  			tableTriggers.beforeDelete(con , rowIdValue ) ;
	  		}

		    int x = delStmt.executeUpdate(deleteStmt);
		  	if (tableTriggers != null) 
	  		{
	  			tableTriggers.afterDelete(con , rowIdValue ) ;
	  		}

		    out.print(x + "Record(s)  Deleted Successfully");
		    return ;
	    }
	    catch (Exception e ) {con.rollback(sp1); throw e; }
	    finally { delStmt.close();}
	  }
  */
  	//-------------------------------------------
	  
	  Vector pramNames = new Vector() ;
  	  Vector pramValues = new Vector() ;
  		pramNames.add("$$"+uniqueWhereClauseVarName) ;
  		pramValues.add(uniqueWhereClauseVarValue) ;
  		if (useRowIdValue) 
  		{ 
  			pramNames.add("$$"+uniqueColumnName);   
  		 	pramValues.add(rowIdValue) ; 
  		}   
	  String ContMaintQueryId = "32723" ;
	  Support.SqlReader sqlReader = new Support.SqlReader(repCon,"LU_QUERIES" , "QUERY_BODY", ContMaintQueryId , pramNames , pramValues , false);
	  String queryStatment = sqlReader.getQueryStatments()[0] ;
	  SecUsrDta loggedUser = (SecUsrDta) session.getAttribute("loggedUser") ;
	  String loggedUserName = loggedUser.getUsrNameValue() ; 
	  if ( loggedUser.isSmartToolAdmin())
	  {
	   %><a href = "<%=appUrl%>/editAndExecute.jsp?id=<%=ContMaintQueryId%>&<%=uniqueWhereClauseVarName%>=<%=URLEncoder.encode(uniqueWhereClauseVarValue) %>" >Maint</a> <%
	  }

  	java.sql.ResultSet rs= null;
  	try{
  		rs =  stmt.executeQuery(queryStatment);
  	}
  	catch (Exception  e) {throw new Exception("Unable to execute : \n" + queryStatment + "\n Due to : \n" + e.getMessage());}
	rs.next();
	String sysMessageDivId = "sysMessageDivId" ;	
	String branch_id = "" ;
	String serialNo = "";
	String car = "";
	String company_id = "" ;
	String status = "" ;
	String carType = "" ;  
	BigDecimal totalPayments = null;
	BigDecimal carIncome = null;   
	BigDecimal total_penalty = null ; 

	branch_id = rs.getString("BRANCH_ID") ;
	serialNo = rs.getString("SERIALNO") ;
	car = rs.getString ("CAR");
	company_id = rs.getString ("COMPANY_ID");
	totalPayments = rs.getBigDecimal("totalPayments");
	total_penalty = rs.getBigDecimal("total_penalty");
	carIncome = rs.getBigDecimal("carIncome");
	String companyName = rs.getString("company_name");
	BigDecimal toSettleAmount = carIncome.subtract(totalPayments); 
	status = rs.getString("STATUS"); 
	boolean isMigrated = rs.getString("IS_MIGRATED").equalsIgnoreCase("Y");  
	String contRowid = rs.getString("C_ROWID");
	String contCarRowid = rs.getString("ROWID");
	carType = rs.getString("CARTYPE");
	boolean closed = (status.equalsIgnoreCase("1"));
  	ResultSetMetaData rsmd = rs.getMetaData();
  	int columnCount = rsmd.getColumnCount();
  	String realInterval = rs.getString("real_interval") ;
  	
	if (! printable) 
	{ %>
	
	<a target = "طباعة العقد" href = ?<%=request.getQueryString()+ "&printable=true" %> > للطباعة-قديمة </a> 
	<% String contRowidURLEncoded = URLEncoder.encode(contRowid) ;  %>
	- <a target = "contractPrint" href = "contract_print.jsp?contRowIdValue=<%=contRowidURLEncoded %>" >الطباعة </a>
	- <a target = "contractPrint" href = "contract_print_onform.jsp?contRowIdValue=<%=contRowidURLEncoded %>" title="طباعة العقد على النموذج المطبوع ">الطباعة على النموذج </a>
	- <a href = "?<%=_rqs%>" title="Refresh" >إعادة تحميل </a>

	<%
	}

%>

<h2 align="center"> عهدة رقم: <%=serialNo %>  -- شركة : <%=companyName %> -- فرع رقم: <%=branch_id %>-<%= (isMigrated)? "منقول من النظام القديم" : "" %></h2> 
<h2 align="center"> حالة العهدة  : <%=(status.equalsIgnoreCase("0"))? "مفتوحه" : "مغلقة" %>   </h2>

<% 

	if (! printable) 
	{ %>
			<div id="<%=sysMessageDivId%>"></div>		
			<table border="0" align="center" dir="rtl">
			<tr>
				 <jsp:include page="/includes/toolBarButtons.jsp"></jsp:include>
				 <td width="30" >
					 <a href = "?Delete=Y&<%=_rqs%>"> 
				     	<img src="<%=appUrl%>/images/deleterec.gif"  
				     	title="Delete This Record حذف هذا السجل  "
						onClick="return confirm('Are you sure you want to Delete This Record? هل انت متأكد سوف يتم حذف هذا السجل ؟');"
						width="25" height="25" ></img>
					</a>
					<% String showHide = (request.getParameter("toggleDesign")!= null && request.getParameter("toggleDesign").equals ("Show Design") )? "Hid Design": "Show Design" ; %>
					
				</td>
				<td width="50"> 
					<form action="" method="post">
						<% if ( loggedUser.isSmartToolAdmin() ) { %>
							<input type="submit" name="toggleDesign" value="<%=showHide%>" >
						<%} %>
							<input type="hidden"  name="<%=uniqueWhereClauseVarName %>" value="<%=uniqueWhereClauseVarValue %>">
					</form> 
				</td>
			</tr>
		</table> <%
	}

	    TableMaintMaster tmm = Support.Misc.getTableMaintMaster(session , tableowner , "CONTRACT_CAR") ;

       	if (!printable)
       	{
         	out.println();
          %>
 			 <table dir="rtl" align="center">
				<tr>
					<td>
						<p align="center">
							<%   
								String registerCarreturnHref = "'"+Support.Misc.getAppURL(request)+"/executeExecutable.jsp?id=21188&imbedded=y&branch_id="+branch_id+"&company_id="+company_id+"&serialno="+serialNo +"'";
								String settleContractHref = "'"+Support.Misc.getAppURL(request)+"/executeExecutable.jsp?id=21393&imbedded=Y&branch_id="+branch_id+"&company_id="+company_id+"&serialno="+serialNo +"'";
								String registerDebitHref = "'"+Support.Misc.getAppURL(request)+"/executeExecutable.jsp?id=21187&imbedded=Y&branch_id="+branch_id+"&company_id="+company_id+"&serialno="+serialNo +"'";
								String registerCreditHref = "'"+Support.Misc.getAppURL(request)+"/executeExecutable.jsp?id=21873&imbedded=Y&branch_id="+branch_id+"&company_id="+company_id+"&serialno="+serialNo +"&credit_amount="+ ( toSettleAmount.multiply(new BigDecimal(-1) ))+"'";
								out.println(); 
							 %>	
							<script type="text/javascript">
								function buildHref (anchorx)
								{
									var returnKM = prompt('عداد العودة'); 
									if ( returnKM == null) return false ;   
									var newHref = <%=registerCarreturnHref%> + '&returnKM=' + returnKM ;
									newHref = '(\'' +  newHref + '\'' +',' + '\'<%=sysMessageDivId%>\'' + ')'; 
									anchorx.href += newHref ;
								}
							</script>		
						<% if (!closed) { %>					  
							<a href = "javaScript:sendAjaxRequestToJsp"  onclick = "buildHref(this);"> 
								<input type="button" name="registerCarReturn"  value =" تسجيل عودة السيارة"  style="font:20">
							</a>

							<input name="AddPayment" value="إستلام /إعادة نقدية" type="button" style="font:20"
								onclick="window.open('<%=appUrl%>/TableInsertForm.jsp?DRCR=<%=toSettleAmount.signum() %>&AMOUNT=<%=toSettleAmount.multiply(new BigDecimal(toSettleAmount.signum()))%>&BRANCH_ID=<%=branch_id%>&SERIALNO=<%=serialNo %>&owner=carrent&tableName=PAYMENT',' إضافة مدفوعات ','toolbar=0,menubar=0,location=0, width= 900 ,height=500, location = no');">
							
							<a href = "javaScript:sendAjaxRequestToJsp(<%=settleContractHref%> , '<%=sysMessageDivId%>' )" 
							   onclick="return confirm (' سوف يتم تسجيل تحصيل/رد باقى قيمة العقد من/الى العميل تلقائيا.هل انت متأكد انك حصلت كامل قيمة العقد ؟') ;"> 
								<input type="button" name="settelContract" value =" تصفية العهدة" style="font:20" title="بعد تحصيل كامل قيمة العقد من العميل "> 
							</a>

							<% if (toSettleAmount.compareTo(new BigDecimal(0)) == 1) { %>
							<a href = "javaScript:sendAjaxRequestToJsp(<%=registerDebitHref%> , '<%=sysMessageDivId%>' )" > 
								<input type="button" name="registerDebit" value =" تسجيل باقى العهدة  كمديونية على الموظف" style="font:18" title="تسجيل باقى العقد كمديونية على العميل "> 
							</a>
							<% } else if (toSettleAmount.compareTo(new BigDecimal(0)) == -1){ %>
							<a href = "javaScript:sendAjaxRequestToJsp(<%=registerCreditHref%> , '<%=sysMessageDivId%>' )" > 
								<input type="button" name="registerDebit" value =" تسجيل فائض  العقد كرصيد للموظف" style="font:18" title="تسجيل فائض  العقد كرصيد للعميل"> 
							</a>
							<% } %>
							

							<% String closeContractHref = "'"+appUrl+"/executeExecutable.jsp?id=21186&imbedded=Y&branch_id="+branch_id+"&company_id="+company_id+"&serialno="+serialNo +"'"; %>
							<a href = "javaScript:sendAjaxRequestToJsp(<%=closeContractHref%> , '<%=sysMessageDivId%>' )" > 
								<input type="button" name="settelContract" value =" إغلاق العهدة" title="بعد" style="font:18"> 
							</a>
						<%} %>

						<% if (closed) { 
								String reOpenContractHref = "'"+appUrl+"/executeExecutable.jsp?id=21185&imbedded=Y&branch_id="+branch_id+"&company_id="+company_id+"&serialno="+serialNo +"'"; 
								%> <a href = "javaScript:sendAjaxRequestToJsp(<%=reOpenContractHref%> , '<%=sysMessageDivId%>' )" > 
								<input type="button" name="settelContract" value =" إعادة فتح العهدة" title="" style="font:18"> 
								</a>
						<%} %>

						</p>
					</td>
				</tr> 
			</table>
		

		<% } %>
		<table  border="1" align="Right" dir="rtl"   style="font-size:15px;" > 
		<%
	    	request.setAttribute("rs" , rs);
	    	request.setAttribute("tmm" , tmm);
	    	HashMap<String , TableMaintDetails> tmdshm =  tmm.getTableMaintDetailsHashMap();
	    	String columnName = "FMDATE";
	    	TableMaintDetails tmd = tmdshm.get(columnName);
	    	String tmdRenderer= "../../includes/renderTmdByColumnName.jsp" ; 
	    	
	   	%>
	   	<tr >
	   		<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %></strong></td>
			<td><jsp:include page="<%=tmdRenderer%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>
			
			<%  columnName = "CAR";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td>  <%=carType%> <jsp:include page="<%=tmdRenderer%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include></td>

			<%  columnName = "DAYRATE";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=tmdRenderer%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>

			   	 
	   	</tr>
   		<tr>


			<%  columnName = "RETURNDATE";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=tmdRenderer%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>

			<%  columnName = "INTERVAL";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> 
			 	<strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <%=realInterval%> </td>
			
		

			<%  columnName = "CONTRACT_DAYS_VALUE";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=tmdRenderer%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>


		</tr>
		
		<tr>
			<%  columnName = "OVERKMRATE";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=tmdRenderer%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> هلله</td>

			<%  columnName = "FMKMREADING";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=tmdRenderer%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include>  

			<% if (! printable ) 
				{	
				String refresh_Contract_Kmout = "'"+appUrl+"/executeExecutable.jsp?id=23279&imbedded=Y&branch_id="+branch_id+"&company_id="+company_id+"&serialno="+serialNo +"'"; %>
				<a target = "mainFrame" href = "<%=appUrl%>/executeQuery.jsp?id=25200&car_code=<%=car %>" >بيانات السيارة</a>
				<% if ( !closed ) { %>
					<a href = "javaScript:sendAjaxRequestToJsp(<%=refresh_Contract_Kmout%> , '<%=sysMessageDivId%>' )" > 
								<input type="button" name="refrshKmOut" value ="Refresh" title="إعادة حساب اخر كم تم تسجيله للسيارة . فى حالة الاختلاف مع الواقع برجاء تسجيل الفرق فى حركة السيارة" style="font:14"> 
							</a>
				<% } %>
			<% } %>
			</td>


			<%  columnName = "CONTRACT_EXTRAKM_VALUE";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=tmdRenderer%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>

		</tr>
		<tr>
			<%  columnName = "KMPERDAY";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=tmdRenderer%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>


			<%  columnName = "HOURSVALUE";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=tmdRenderer%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>

			<%  columnName = "CONTRACT_EXTRAHOURS_VALUE";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=tmdRenderer%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>

		</tr>
		<tr>
			<td>كم مستهلك</td><td><%=rs.getBigDecimal("USED_KM") %></td>

			<%  columnName = "RETURNKMREADING";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=tmdRenderer%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>

			<%  columnName = "PENALITY";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=tmdRenderer%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> <%=total_penalty%> </td>

		</tr>
		<tr>
			<%  columnName = "DAMEGE";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=tmdRenderer%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>

			<td></td><td></td>

			<%  columnName = "DAMGEVALUE";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=tmdRenderer%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>

		</tr>
		<tr>

			<%  columnName = "NOTES";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=tmdRenderer%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>

			 
			<td></td><td></td>

			<%  columnName = "DISCOUNT";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=tmdRenderer%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>
		
		</tr>		
			
		<tr>
			<%  columnName = "EXTRA_DRIVER_NAME";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=tmdRenderer%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>

			<%  columnName = "EXTRA_DRIVER_LIC_ID";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=tmdRenderer%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>


			<%  columnName = "CARINCOME";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=tmdRenderer%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>
					
		</tr>
		<tr>
			<%  columnName = "EXTRA_DRIVER_LIC_EDATE";     tmd = tmdshm.get(columnName); %>
			<td title="<%=tmd.getCommentsValue()%>"> <strong><%=tmd.getDisplayNameValue() %> </strong></td>
			<td> <jsp:include page="<%=tmdRenderer%>" >
				<jsp:param name="columnName" value="<%=columnName%>"></jsp:param>
			</jsp:include> </td>
			
			<td></td><td></td>
			
			<td> <strong>إجمالى المدفوعات </strong> </td> <td> <strong># <%=totalPayments %> # </strong></td>

		</tr>
			
		<tr>
			<td></td><td></td>
			<td colspan="2"> <strong> المطلوب لتصفية العهدة</strong></td>
			<td><strong><%=(toSettleAmount.signum()==1)? "تحصيل مبلغ من العميل " : " رد مبلغ الى العميل"  %></strong></td> 
			<td><Strong><%=toSettleAmount.multiply(new BigDecimal(toSettleAmount.signum())) %></Strong></td>

		</tr>
		</table><br>
			

		 <% 
		 	stmt.close();
          	rs.close();
         %>
<br/>
 <table dir="rtl" align="right">
 	<tr align="right" dir="rtl"> 
	 	<td>
			<jsp:include page="/queryResultNonTabbed.jsp?id=25414&printable=true">
				<jsp:param value="<%=branch_id%>" name="branch_id"/>
				<jsp:param value="<%=serialNo%>" name="serialno"/>
			</jsp:include>
		</td>
	</tr>
</table>
<table align="center">
<tr><td>
<p align="center">
	<% if (!printable) { %>
		<a target = "طباعة العقد" href = ?<%=request.getQueryString()+ "&printable=true" %> >نسخة للطباعة</a>
		<% if (loggedUser.isSmartToolAdmin()) { %>
		<p align="center"><a href = "../tableEditor.jsp?tableName=CARRENT.CONTRACT_CAR&ROWID=<%=java.net.URLEncoder.encode(contCarRowid)%>">Load with Default Page editor</a>
		<% } if (isMigrated) { %>
			<a href = "<%=appUrl%>/editAndExecute.jsp?id=25552&branch_id=<%=branch_id%>&serialno=<%=serialNo%>" target = "النظام القديم ">بيانات العقد بالنظام القديم</a>
		<% } %>
	<%} %>
</p>
</td>
</tr>
</table>
</body>
</html>
