<%@page import="com.sideinternational.sas.license.entity.Application"%>
<%@ page errorPage="../errorPage.jsp"
	import="java.util.*, java.io.* , java.sql.* , java.text.SimpleDateFormat , javax.faces.model.SelectItem"%>
<%@ page import="java.util.* , Support.Misc"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>

<%@page import="com.implex.database.* , com.implex.database.map.* "%>
<%@page import="Support.UserUnCommitedTransactions"%>
<%@page import="com.smartValue.UnCommittedDbTransaction"%><html>
<a href = "systemMonitor.jsp">Refresh</a>
<H3> User Un Committed Transactions: 
<%
	ArrayList <UnCommittedDbTransaction> ucts = UserUnCommitedTransactions.getUnCommitedTransactions(session);
%> <table border = 1 >
		<tr>	
			<td>Operation Date </td> 
			<td>Operation Type </td> 
			<td>Table Name </td> 
			<td>Column Name </td> 
			<td>Old value </td>
			<td>New value</td>
			<td>Rowid </td>
		</tr>
	
<% 
	for ( UnCommittedDbTransaction uct : ucts)
	{ boolean updateOperation = uct.getOperationType()==UnCommittedDbTransaction.UPDATE_OPERATION ;  
	  boolean insertOperation = uct.getOperationType()==UnCommittedDbTransaction.INSERT_OPERATION ;
		%> <tr>	
				<td><%=uct.getOperateDate() %> </td> 
				<td><%= (updateOperation) ? "Update " : (insertOperation? "Insert" : "Delete")  %> </td> 
				<td><%=(updateOperation) ? uct.getTableMaintdetail().getTableNameValue() : uct.getTableMaintMaster().getTableNameValue()%> </td> 
				<td><%=(updateOperation) ? uct.getTableMaintdetail().getColumnNameValue() : "NA" %></td>
				<td><%=(updateOperation) ? uct.getOldValue() : "NA" %> </td>
				<td><%=(updateOperation) ? uct.getNewValue() : "NA" %> </td>
				<td><%= uct.getRowId()%> </td>
		</tr> <%
		 		
	}
%>

</table>
<H3>List Of Used DB Tables (<a href = "?ClearTableMD=Y"  onclick="return confirm (Are You Sure ?) " >Clear?</a>>):</H3> 
<%  

Hashtable<DbTable, TableMaintMaster>  listOfUsedTables =  Misc.getModuleServiceContainerFromUserSession(session).getListOfUsedTables() ; 
if (request.getParameter("ClearTableMD")!= null)
{
	Misc.getModuleServiceContainerFromUserSession(session).getListOfUsedTables().clear() ; 
}
 
StringBuffer htmlTable = new StringBuffer(); 
int counter = 0 ;
PersistantObject po1 = null ; 
try {
for (com.implex.database.DbTable xx : listOfUsedTables.keySet())
{
	po1 = listOfUsedTables.get(xx); 
	
	if ( counter == 0 )
	{
		htmlTable.append(po1.toStringAsHtmlTableHeader());
	}
	htmlTable.append(po1.toStringAsHtmlTableData());
	counter ++; 
}
}
catch ( Exception e) { %> Unable To render PO:  <%=po1 %>  Due To <%= e %> <% }
out.print("<Table border = '1'>" + htmlTable.toString() + "</Table>") ; 

%><br > User Session Attributes: </br>

<% 	
	Enumeration e = session.getAttributeNames();
	%> <H3>Here is your saved session data:</H3>
	   <table><% 
	while (e.hasMoreElements()) {
	String name = (String)e.nextElement();
	Object obj = session.getAttribute(name);
	String value = null ;
	if (obj instanceof PersistantObject)
	{
		PersistantObject po = (PersistantObject) obj ;
		value = po.toStringAsHtmlTable(); 
	}
	else 
	{
		value = session.getAttribute(name).toString();
	}
	%> 
	<tr><td> <%=name %> </td> <td><%=value%></td> </tr>
	<%
	}

%>

</table>
<br> License Information 
<br>
<jsp:include page="../certExpiry.jsp"></jsp:include>
</html>
