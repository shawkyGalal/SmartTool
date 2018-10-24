<%@ page contentType="text/html;charset=windows-1252"
	errorPage="errorPage.jsp"
	import="SADAD.DATASTRCTURE.*,  com.jscape.inet.ftp.*"%>
<%  
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
    ft.setDir(dirPath);  //--"/sfs/bank/SAMBSARI/download/"
    // create an ByteArrayOutputStream to store data 
    // java.io.ByteArrayOutputStream bout = new java.io.ByteArrayOutputStream(); 
    //ft.download(bout,fileName );     //"SAMBSARI-BKRCRQ-20050627-1.xml"
    ft.download(fileName);
    
    ft.disconnect();
    //String fileContentsAsString = bout.toString();
    SADADService ss = new SADADService("file:///"+tempFileDir + java.io.File.separator + fileName ,null, false);
    //String clientDate = ss.signonRq.clientDt ;
    //-------------End Getting the File Contents from The Server Using FTP Proptocol-----------
    //-------------Parsing the Contents of the file -------------------------------------------
    SARIERq sarieRq= ss.sarieRq;
   
    int sarieInfoSize = sarieRq.SARIEInfo.size();
 
    %>
<p align="center"><strong><font size="5"> SARIE
Rquest </font></strong> <BR>
<BR>
</p>
<BR>
Processing Date :
<%=ss.sarieRq.PrcDt %>
<BR>
Transaction ID :
<%= ss.sarieRq.TransID %>

<p align="center"><strong><font size="5"> SARIE
Information Details </font></strong> <BR>
<BR>
</p>
<table align=center border=1>
	<tr>
		<td>
		<p align="center"><strong><font size="3">S/N</p>
		</td>
		<td>
		<p align="center"><strong><font size="3">Bank
		Code</p>
		</td>
		<td>
		<p align="center"><strong><font size="3">Credit
		Amount</p>
		</td>
		<td>
		<p align="center"><strong><font size="3">Debit
		Amount</p>
		</td>
		<td>
		<p align="center"><strong><font size="3">CrDr</p>
		</td>
		<td>
		<p align="center"><strong><font size="3">Currency</p>
		</td>
	<tr>

		<%
    
    java.math.BigDecimal sumCreditAmount = new java.math.BigDecimal (0);
    java.math.BigDecimal sumDebitAmount = new java.math.BigDecimal (0);          

    for (int i=0; i< sarieInfoSize ; i++)
    {
     SARIEInfo sarieInfo = (SADAD.DATASTRCTURE.SARIEInfo) sarieRq.SARIEInfo.elementAt(i);
      %>
	
	<tr>
		<td><%=i+1%></td>
		<td><%=sarieInfo.BankCode%></td>
		<%
          java.math.BigDecimal creditAmount = new java.math.BigDecimal (0);
          java.math.BigDecimal debitAmount = new java.math.BigDecimal (0);          
          if (sarieInfo.CrDr.equals("C"))
          {creditAmount = new java.math.BigDecimal (sarieInfo.ActualAmt);}
          if (sarieInfo.CrDr.equals("D"))
          {debitAmount = new java.math.BigDecimal (sarieInfo.ActualAmt);}
          sumCreditAmount = sumCreditAmount.add(creditAmount);
          sumDebitAmount = sumCreditAmount.add(debitAmount);
          %>
		<td><%=creditAmount%></td>
		<td><%=debitAmount%></td>

		<td><%=sarieInfo.CrDr %></td>
		<td><%=sarieInfo.Currency %></td>
	</tr>
	<%
    }
    %>
	<tr>
		<td></td>
		<td>Total</td>
		<td><%=sumCreditAmount%></td>
		<td><%=sumCreditAmount%></td>

	</tr>
</table>
