<%@ page contentType="text/html;charset=windows-1252"
	errorPage="errorPage.jsp"
	import="SADAD.DATASTRCTURE.*,  com.jscape.inet.ftp.*"%>
<%@page import="Support.Misc" %>	
<title>BANK Settlement Response</title>
<%  
        java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
     if (con == null)
     {
      session.setAttribute("comeFrom","ParseXferAddRq.jsp" + "?" + request.getQueryString());
      response.sendRedirect("loginScreen.jsp");
     }
    String logedUser = Misc.getConnectionUserName(con);    
    //--------------Getting the File Contents from The Server Using FTP Proptocol-----------
    String dirPath = request.getParameter("dirPath");
    String fileName =request.getParameter("fileName"); 
    String serverName = request.getParameter("serverName"); 
    if (serverName == null )
    {throw new Exception("You should define Server IP");}    
    
    String tempFileDir = "C:\\Temp";
    Ftp ft = null;
    %>
<%@ include file="ServersAuthuntication.html"%>
<%
    ft.connect();
    ft.setLocalDir(new java.io.File(tempFileDir)); //-----------Store The file to temp Dir-----
    ft.setDir(dirPath);  
    
    ft.download(fileName);
    
    ft.disconnect();
    //String fileContentsAsString = bout.toString();
    SADADService ss = new SADADService("file:///"+tempFileDir + java.io.File.separator + fileName ,null, false);
    String clientDate = ss.signonRq.clientDt ;
    //-------------End Getting the File Contents from The Server Using FTP Proptocol-----------
    //-------------Parsing the Contents of the file -------------------------------------------
    XferResultRq xferResultRq= (XferResultRq) ss.bankSvcRq.messageRequests.elementAt(0);
   
    %>
<p align="center"><strong><font size="5">Intra Bank
Transfer Result (from Bank) </font></strong> <BR>
<BR>
</p>
<BR>
Generation Date :
<%=ss.signonRq.clientDt %>
<BR>
From Bank :
<%= ss.signonRq.getSignonProfile().getSender()%>
<%
      String description ="" ;
      if (ss.bankSvcRq.status.statusCode.equals("0")){description = "Success";}
      if (ss.bankSvcRq.status.statusCode.equals("52085")){description = "<font size=3 color = red> One or more specified accounts did not exist</font>";}
      if (ss.bankSvcRq.status.statusCode.equals("52086")){description = "<font size=3 color = red> One or more payments failed to be accomplished </font>";}
    %>
<BR>
Status Code :
<%=ss.bankSvcRq.status.statusCode%>
<BR>
Status Code Desc :
<%=description%>
<BR>
Status :
<%= ss.bankSvcRq.status.severity%>
<BR>
Status Short Desc :
<%=ss.bankSvcRq.status.shortDesc%>


<p align="center"><strong><font size="5"> Transfer
Result Details </font></strong> <BR>
<BR>
</p>
<table align=center border=1>
	<tr>
		<td rowspan="2">
		<p align="center"><b>PMT_ID</b></p>
		</td>
		<td rowspan="2">
		<p align="center"><b>Date</b></p>
		</td>
		<td rowspan="2">
		<p align="center"><strong><font size="3">Amount</p>
		</td>
		<td colspan="3">
		<p align="center"><b>From Account</b>
		</td>
		<td colspan="4">
		<p align="center"><b>To Account</b>
		</td>
		<td rowspan="2">
		<p align="Transfer Result"><b>Result</b></p>
		</td>
	</tr>
	<tr>
		<td>
		<p align="center"><b>BankId</b></p>
		</td>
		<td>
		<p align="center"><b>AccountID</b></p>
		</td>
		<td><strong>AccountType</strong></td>
		<td>
		<p align="center"><b>BankId</b></p>
		</td>
		<td>
		<p align="center"><b>AccountID</b></p>
		</td>
		<td>
		<p align="center"><b>Biller</b></p>
		</td>
		<td><strong>AccountType</strong></td>
	<tr>

		<%
    java.math.BigDecimal total =new java.math.BigDecimal(0);
    for (int i=0; i< xferResultRq.XferResultInfos.size(); i++)
    {
     XferResultInfo xferResultInfo = (XferResultInfo) xferResultRq.XferResultInfos.elementAt(i);
     java.math.BigDecimal curAmtAsBigDec= new java.math.BigDecimal(xferResultInfo.xferInfo.CurAmt);
     total = total.add(curAmtAsBigDec);
     //------ Trying to decode Account Id it its corresponding Biller
    /*
    Mobily   20864006000100
    Madinah_Municipality   500000010006089000000
    Marafiq  1008520415000
    CITC 50147324000105
    STC  21255039000108
    NCCI  20853936000302
    KAU  4701709433
    */


     String biller = "Not Recognized By Smart Tool";
     if (xferResultInfo.xferInfo.DepAcctIdTo.AcctId.equals("20864006000100"))
     {
     biller = "Mobily ";
     }
     else if (xferResultInfo.xferInfo.DepAcctIdTo.AcctId.equals("500000010006089000000"))
     {
     biller = "Madinah_Municipality";
     }
     else if (xferResultInfo.xferInfo.DepAcctIdTo.AcctId.equals("01008520415000"))
     {
     biller = "Marafiq";
     }
     else if (xferResultInfo.xferInfo.DepAcctIdTo.AcctId.equals("50147324000105"))
     {
     biller = "CITC";
     }
     else if (xferResultInfo.xferInfo.DepAcctIdTo.AcctId.equals("21255039000108"))
     {
     biller = "STC";
     }
     else if (xferResultInfo.xferInfo.DepAcctIdTo.AcctId.equals("20853936000302"))
     {
     biller = "NCCI";
     }
     else if (xferResultInfo.xferInfo.DepAcctIdTo.AcctId.equals("4701709433"))
     {
     biller = "KAUU";
     }
          
     else if (xferResultInfo.xferInfo.DepAcctIdTo.AcctId.equals("2010011119940"))
     {
     biller = "Ministry of Water";
     }
     
     else if (xferResultInfo.xferInfo.DepAcctIdTo.AcctId.equals("2258056381"))
     {
     biller = "SAMBA CC";
     }
     
    else if (xferResultInfo.xferInfo.DepAcctIdTo.AcctId.equals("63001299920103"))
     {
     biller = "NCB CC";
     }

     else if (xferResultInfo.xferInfo.DepAcctIdTo.AcctId.equals("500000010006089001135"))
     {
     biller = "AMMD";
     }
     else if (xferResultInfo.xferInfo.DepAcctIdTo.AcctId.equals("01417860"))
     {
     biller = "Fly SAMA";
     }
     else if (xferResultInfo.xferInfo.DepAcctIdTo.AcctId.equals("0101121212014"))
     {
     biller = "American Express";
     }
   
     else if (xferResultInfo.xferInfo.DepAcctIdTo.AcctId.equals("01008552499000"))
     {
     biller = "Riyad Municipality";
     }
     else if (xferResultInfo.xferInfo.DepAcctIdTo.AcctId.equals("1241400"))
     {
     biller = "Bravo";
     }

     else if (xferResultInfo.xferInfo.DepAcctIdTo.AcctId.equals("2180166699949"))
     {
     biller = "Zakat";
     }
     
     else if (xferResultInfo.xferInfo.DepAcctIdTo.AcctId.equals("228608010122006"))
     {
     biller = "Makkah_Municipality";
     }
     else if (xferResultInfo.xferInfo.DepAcctIdTo.AcctId.equals("198000010006089001140"))
     {
     biller = "AMMD";
     }     
     else if (xferResultInfo.xferInfo.DepAcctIdTo.AcctId.equals("10796200104"))
     {
     biller = "SEC";
     } 
     
     //------ End of Trying to decode Account Id it its corresponding Biller
      %>
	
	<tr>
		<td><%=xferResultInfo.xferInfo.PmtId%></td>
		<td><%=ss.signonRq.clientDt.replaceAll("T","") %></td>
		<td><%=xferResultInfo.xferInfo.CurAmt%></td>

		<td><%=xferResultInfo.xferInfo.DepAcctIdFrom.BankId%></td>
		<td><%=xferResultInfo.xferInfo.DepAcctIdFrom.AcctId%></td>
		<td><%=xferResultInfo.xferInfo.DepAcctIdFrom.AcctType%></td>

		<td><%=xferResultInfo.xferInfo.DepAcctIdTo.BankId%></td>
		<td><%=xferResultInfo.xferInfo.DepAcctIdTo.AcctId%></td>
		<td><%=biller%></td>
		<td><%=xferResultInfo.xferInfo.DepAcctIdTo.AcctType%></td>
		<td><%=xferResultInfo.status.shortDesc+"("+xferResultInfo.status.statusCode+")" %></td>

	</tr>
	<%
    }
    System.out.println("User : " +logedUser + " Have Parsed Bank XrefResultReq Message " + dirPath +fileName );
    %>
	<tr>
		<td>
		<p align="center"><b>Total</b></p>
		</td>
		<td>
		<p align="center"><b><%=total.toString()%></b></p>
		</td>
	</tr>
</table>
