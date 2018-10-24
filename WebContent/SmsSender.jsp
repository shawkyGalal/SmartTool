<%@page  language="java" contentType="text/html;charset=UTF-8" errorPage="errorPage.jsp"%>
<%request.setCharacterEncoding("UTF-8");%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%><html>
<head>
<title>SMS Sender </title>

</head>
<%

%>
<body>
<br>
<a href="http://www.clickatell.com">Service provider </a>
<%
if (request.getParameter("Submit") != null)
{
    String x = "SADAD\\shawky.foda";
    String proxyServer = request.getParameter("proxyServer").toString();
    String proxyPort = request.getParameter("proxyPort").toString(); 
    
    Support.SmsSernder smsSernder = new Support.SmsSernder(Support.SmsSernder.DEFAULT_CLICKTELL_API_ID,  proxyServer, proxyPort ,x ,"redsea11");
    smsSernder.setMaxParts(10);
    smsSernder.setSmsGatwayUserName("foda.sh@smart-value.com");
    smsSernder.setSmsGatewayPassword("redsea11");
    String mobileNo = request.getParameter("mobileNo").toString();
//    String from_mobile_no = "966506818206";
    String msg = request.getParameter("msg").toString(); //"This is The First Automated SMS Alert Sent by Smart Tool. Shawky Foda";
	String approvedSenderId = request.getParameter("approvedSenderId");
    Support.SmsMessage smsMessage = new Support.SmsMessage( msg , mobileNo , approvedSenderId ) ;  
    String gatwayResponse = smsSernder.sendSMS(smsMessage );

    out.println(" تم الارسال بنجاح ....."  + gatwayResponse);

    //---Audit the operation -----
    com.implex.database.map.SecUsrDta loggedUser = (com.implex.database.map.SecUsrDta)session.getAttribute("loggedUser" ) ;
    String insStr = "Insert Into support.sms_sender_aduit ( sms_message , sent_date , sent_by , gateway_response , sent_by_company , sent_to) values (" 
    		+ "'" + msg + "'," 
    		+ "sysdate , "
    		+ "'" + loggedUser.getUsrNameValue()+ "' , " 
    		+ "'"+ gatwayResponse + "' , "
    		+ "'" + loggedUser.getUsrCmpIdValue()+ "' , "
    		+ "'" + mobileNo + "'"    		
    		+ " ) " ;
    java.sql.Connection repCon = (java.sql.Connection) session .getAttribute("repCon") ;
    Statement stmt = null ;
   	stmt = repCon.createStatement(); 
   	System.out.print( "System Will Execute " + insStr ) ;  
    stmt.execute(insStr); 
    
    //-----End of Audit Operation ------
     
}
%>


<form name="form1" method="post" action="SmsSender.jsp">
<div align="center"><font size="5">SMS Sender </font></div>

<table width="75%" border="1">
	<tr> <td>Proxy Server</td> <td><input name = proxyServer value = "172.27.0.1"></td> </tr>
	<tr> <td>Proxy Port</td> <td><input style="width: 51px" name = "proxyPort" value = "3128"></td> </tr>
	<tr>
	
		<td>Message</td>
		<td>
		<p>&nbsp;</p>
		<%
			String smsMessage = "";
			String toMobile = "";  
			String smsQueryId = request.getParameter("smsQueryId");
			if (smsQueryId != null)
			{
			    java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");
			    java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
			    Support.SqlReader  sqlReader = new Support.SqlReader(repCon, "LU_QUERIES" , "QUERY_BODY",  smsQueryId, session , request );
			    int queryIndex = Integer.parseInt( request.getParameter("queryIndex"));
			    smsMessage = sqlReader.getQueryResultForSms(queryIndex , con);
			    toMobile = sqlReader.getNotifyTo();
			}
			else 
			{
			    smsMessage = request.getParameter("smsMessage");
			    toMobile = request.getParameter("toMobile");
			}
		%>
		<p><textarea name="msg" cols="60" rows="10"><%= smsMessage %></textarea></p>
		</td>
	</tr>
	<tr>
		<td>Send to Mobile Noxxx.</td>
		<td><input type="text" name="mobileNo" value = "<%=toMobile %>"> 9665xxxxxxxx</td>
	</tr>
	<tr>
	<td>Approved Sender ID : </td> 
	<td>
	<select name="approvedSenderId" > 
		<option  value="SmartValue"> SmartValue </option>
		<option  value="966564627751"> 966564627751 </option>
	</select>
	</td>
	</tr>
	<tr>
		<td colspan="2">
		<div align="center"><input type="submit" name="Submit"
			value="Submit"></div>
		</td>
	</tr>
</table>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
</form>
</body>
</html>
