<%@ page errorPage="errorPage.jsp" 	%>
<%@ page import="Support.Misc" 	%>
<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>

<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=UTF-8">
<title>Http Poster</title>
</head>
<body>

<%
	String tableName = request.getParameter("tableName");
String rowId = request.getParameter("rowId");
String columnName = request.getParameter("columnName");
String ukColumnName =  request.getParameter("ukColumnName");
String ukColumnValue =  request.getParameter("ukColumnValue");

boolean useUniqueKey = ( request.getParameter("useUniqueKey")!= null && request.getParameter("useUniqueKey").equals("Y"));


String columnValue = "";
java.util.Vector  columnValuePerLine = new java.util.Vector();
//( (useRowId)? "rowid = '" + rowId + "'" : "" )+
String whereClause = " where " +  ( (useUniqueKey)? ukColumnName + "='" + ukColumnValue + "'" :"rowid = '" + rowId + "'");
String query = "select " + columnName + " from "  + tableName + whereClause;

java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");
java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
int seq = Integer.parseInt(request.getParameter("seq"));
String queryId = request.getParameter("id");
Support.SqlReader sqlReader = new Support.SqlReader(repCon,"LU_Queries" , "QUERY_BODY",  queryId , session , request );
%>

<p align="center"><strong><font color="#000000">Message
Poster Application</font></strong></p>
<form action="queryToHttpPoster.jsp" method="post">
<table width="75%" border="1">
	<tr>
		<td width="21%">Post This Message</td>
		<td colspan="3"><textarea name="Message" cols="70" rows="20"><%=sqlReader.getResultAsXML(seq,con)%></textarea></td>
	</tr>
	<tr>
		<script language="JavaScript" type="text/JavaScript">
      	function reconstructURL(input1 )
        {
          document.getElementById('URL').value =document.getElementById('DBase').value +document.getElementById('msgType').value;
        }
	</script>

		<td>To Environment</td>
		<td><select onChange="reconstructURL(this.value);" name="DBase"
			size="1">
			<% 
             Support.XMLConfigFileReader supportConfig =  Misc.getXMLConfigFileReader(false) ; 
             java.util.Vector conParms  = supportConfig.connParms ;
             for (int i = 0 ; i< conParms.size() ; i++ ) 
              {
              
                 Support.ConnParms thisConParms = (Support.ConnParms)conParms.elementAt(i);
                 if (thisConParms.active.equals("Y"))
                  { 
                    %><Option
				value="<%="http://"+thisConParms.wdServer%>"><%=thisConParms.name%></Option>
			<%
                  } 
              }
          %>
		</select></td>
		<td>Message Type</td>
		<td><select onChange="reconstructURL(this.value);" id="msgType"
			size="1">
			<Option
				value=":5285/invoke/SADADEX01PaymentInitialisation.Accept:validatePayment">PVALRQ
			</Option>
			<Option
				value=":5285/invoke/SADADEX01PaymentInitialisation.Accept:confirmPayment">PADVRQ
			</Option>
			<Option value=":5285/invoke/SADADEX01BillQuery.Accept:queryBill">BINQRQ
			</Option>
			<Option
				value=":5285/invoke/SADADEX01CustomerProfile.Accept:addCustomerProfile">CADDRQ</Option>
			<Option
				value=":5285/invoke/SADADEX01CustomerProfile.Accept:inquireCustomerProfile">CINQRQ</Option>

			<Option
				value=":5355/invoke/SADADEX02AccountUpload.Accept:receiveAccountData">AUPLRQ</Option>
			<Option
				value=":5285/invoke/SADADEX01PaymentInitialisation.Accept:modifyPayment">PMDVRQ
			</Option>
			<Option
				value=":5355/invoke/SADADEX02VoucherUpload.Accept:receiveVoucherData">VUPLRQ
			</Option>
			<Option value=":5285/invoke/SADADEX01BankRecon.Accept:receiveSPL">BSPLRQ
			</Option>
			<Option
				value=":5355/invoke/SADADEX02BillUpload.Accept:receiveBillData">BUPLRQ</Option>
			<Option
				value=":5355/invoke/SADADEX02BillUpload.Accept:receiveRTBillData">RT
			BILL UPLOAD</Option>
			<Option
				value=":5355/invoke/SADADEX02PaymentNotification.Submit:sendRTPmtNotification">Send
			PNOTRQ to Biller</Option>


		</select></td>
	</tr>
	<tr>
		<td>To This URL</td>
		<td colspan="3"><input name="URL" id="URL" type="text" size="75"
			value=""></td>
	</tr>
	<tr>
		<td>User Name</td>
		<td width="21%"><input name="userName" type="text"
			value="sabbsari"></td>
		<td width="18%">Password</td>
		<td width="40%"><input name="password" type="text"
			value="irasbbas"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="3">
		<div align="center"><input type="hidden" name="tableName"
			value="<%=tableName%>"> <input type="hidden" name="rowId"
			value="<%=rowId%>"> <input type="hidden" name="columnName"
			value="<%=columnName%>"> <input type="submit" name="Post"
			value="Post"></div>
		</td>
	</tr>
</table>
<p>&nbsp;</p>
</form>

<%
 
     if (request.getParameter("Post") !=null )
     {
      String Message = request.getParameter("Message");
      String userName= request.getParameter("userName");
	    String password= request.getParameter("password");
      String URL =  request.getParameter("URL");

      Support.DownLoad.MyAuthenticator  auth = new Support.DownLoad.MyAuthenticator(userName,password);
      java.net.Authenticator.setDefault(auth);
    
      Support.HttpPoster httpPoster = new Support.HttpPoster(URL,"", userName,password);
      httpPoster.postRequest(Message);
      String httpResponse =  httpPoster.readResponse();
      System.out.println("User " + Misc.getConnectionUserName(con) + " Posted Msg " + ukColumnName + "=" + ukColumnValue);
      %>
<table width="75%" border="1">
	<tr>
		<td>Server Response :</td>
		<td><textarea name="ServerResponse" cols="70" rows="20"><%=httpResponse%></textarea></td>
	</tr>
</table>
<p>&nbsp;</p>
<p>
<%
     }
     
%>
</p>

</body>
</html>