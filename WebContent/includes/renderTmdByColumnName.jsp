<%@ page errorPage="../errorPage.jsp"
	import="java.util.*,java.io.*,java.sql.*,java.text.SimpleDateFormat,javax.faces.model.SelectItem"%>
<%@ page import="java.util.*,Support.Misc"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8");%>

<%@page import="com.implex.database.map.*"%>


<% 	  
		TableMaintMaster tmm = (TableMaintMaster) request.getAttribute("tmm") ;
		HashMap<String , TableMaintDetails> tmdshm =  tmm.getTableMaintDetailsHashMap();
		String columnName = request.getParameter("columnName");
		TableMaintDetails tmd = tmdshm.get(columnName);

		if (tmd != null) 
		{ 
	   		String divID = "'DivId_Update_Result_For_"+tmd.getColumnNameValue()+"'" ; 
			
		%>
			<div>
				<%request.setAttribute("tmd" , tmd) ;%>
				<jsp:include page="renderTmd.jsp">
					<jsp:param name="divID" value="<%=divID%>"/>
				</jsp:include>
			</div>
			
	
	     <%}
	     else
	     {
	    	%>  
	    		ERROR: Column Name ( <%=columnName%> ) Not Found
	    	<% 	 
	     } 
	      
	     %>