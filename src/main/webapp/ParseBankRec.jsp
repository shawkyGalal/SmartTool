<%@ page contentType="text/html;charset=windows-1252"
	errorPage="errorPage.jsp"
	import="java.net.*, java.io.* ,SADAD.DATASTRCTURE.*,  com.jscape.inet.ftp.*"%>
<%@page import="Support.Misc" %>	

<% 
String fileName =request.getParameter("fileName"); 
%>
<title>Bank Reconciliation Message <%=fileName%></title>
<%  
    //--------------Getting the File Contents from The Server Using FTP Proptocol-----------
     java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
     if (con == null)
     {
      session.setAttribute("comeFrom","ParseBankRec.jsp" + "?" + request.getQueryString());
      response.sendRedirect("loginScreen.jsp");
     }
    String logedUser = Misc.getConnectionUserName(con);     
    String dirPath = request.getParameter("dirPath");
    String serverName = request.getParameter("serverName"); 
    
    System.setProperty("http.proxyHost", "10.16.17.140");
    System.setProperty("http.proxyPort", "8080");
    System.setProperty("http.nonProxyHosts", "10.16.17.*");


    if (serverName == null )
    {throw new Exception("You should define Server IP");}
   
    SADADService ss = null;
    //---------if server is a Windows Server
    
     String fileURL = "file:////"+serverName+"/" + dirPath + "/" + fileName ;
    //String fileURL = "file://"+serverName+"/"+dirPath+"/" + fileName ;
    if (serverName.equals("10.16.17.51")  || serverName.equals("10.16.17.57"))
    { 
      //----------Reading the file contents by getting its input stream ------
      URL url =new URL (fileURL);
      InputStream in = null;
      String datastr = "";
      try
      {
        URLConnection urlcon =  url.openConnection();
        urlcon.connect();
        in =(InputStream) urlcon.getContent();
      }
      catch (Exception e) { throw new Exception ("Unable to read " + fileURL + "Due to " + e.getMessage());}
      //-----End of Reading the file contents ------
      //--------Parsing the file Contents into SADADService object----
      try
      {  
       ss = new SADADService( in ,false , null);
      }
      catch (Exception e) { throw new Exception ("Unable to parse " + fileURL + "contents Due to " + e.getMessage());}
    }
    //----if Server is AIX Server --------
    else 
/*    if (serverName.equals("10.16.17.13") || serverName.equals("10.16.17.9")
            || serverName.equals("10.16.24.13") || serverName.equals("10.16.24.9")
            || serverName.equals("10.16.24.21")
            
            )
*/            
    {
      //--------First Down Load the file using FTP ----
      String tempFileDir = "C:\\Temp";
      Ftp ft = null;
      %>
<%@ include file="ServersAuthuntication.html"%>
<%
      ft.connect();
      ft.setLocalDir(new java.io.File(tempFileDir)); //-----------Store The file to temp Dir-----
      ft.setDir(dirPath);  //--"/sfs/bank/SAMBSARI/download/"
      ft.download(fileName);
      ft.disconnect();
      ss = new SADADService("file:///"+tempFileDir + java.io.File.separator + fileName ,null, true);
    }
   // else {throw new Exception ("Shawky: Server " + serverName + "is not recognized as Windows nor AIX Server Due To  " );}

/*   
    Ftp ft = new Ftp(serverName, "wasadm" , "gram30");
    ft.connect();
    ft.setLocalDir(new java.io.File(tempFileDir)); //-----------Store The file to temp Dir-----
    ft.setDir(dirPath);  //--"/sfs/bank/SAMBSARI/download/"
    // create an ByteArrayOutputStream to store data 
    // java.io.ByteArrayOutputStream bout = new java.io.ByteArrayOutputStream(); 
    //ft.download(bout,fileName );     //"SAMBSARI-BKRCRQ-20050627-1.xml"
    ft.download(fileName);
    
    ft.disconnect();
    //String fileContentsAsString = bout.toString();
    SADADService ss = new SADADService("file:///"+tempFileDir + java.io.File.separator + fileName ,null, true);
*/    
    
    String clientDate = ss.signonRq.clientDt ;
    //-------------End Getting the File Contents from The Server Using FTP Proptocol-----------
    //-------------Parsing the Contents of the file -------------------------------------------
    BankReconDownLoadRq bankReconDownLoadRq= (BankReconDownLoadRq) ss.bankSvcRq.messageRequests.elementAt(0);
   
    int pmtRecSize = bankReconDownLoadRq.pmtRecs.size();
 
    %>
<p align="center"><strong><font size="5">Bank
Reconciliation Request </font></strong> <BR>
<BR>
</p>
<BR>
Generation Date :
<%=ss.signonRq.clientDt %>
<BR>
To Bank :
<%= ss.signonRq.signonProfile.receiver%>
<BR>
Status Short Desc :
<%=ss.bankSvcRq.status.shortDesc%>
<BR>
Processing Date :
<%=bankReconDownLoadRq.prcDt%>
<p align="center"><strong><font size="5"> Payments
Information </font></strong> <BR>
<BR>
</p>
<table align=center border=1>
	<tr>
		<td>
		<p align="center"><strong><font size="3">S/N</p>
		</td>
		<td>
		<p align="center"><strong><font size="3">PMT_ID1</p>
		</td>
		<td>
		<p align="center"><strong><font size="3">Statuse</p>
		</td>
		<td>
		<p align="center"><strong><font size="3">Amount</p>
		</td>
		<td>
		<p align="center"><strong><font size="3">BillerId</p>
		</td>
		<td>
		<p align="center"><strong><font size="3">BillingAcct</p>
		</td>
		<td>
		<p align="center"><strong><font size="3">PrcDt</p>
		</td>
		<td>
		<p align="center"><strong><font size="3">PMT_ID2</p>
		</td>
	<tr>

		<%
    java.math.BigDecimal totalReconciledAmount = new java.math.BigDecimal(0);
    for (int i=0; i< pmtRecSize ; i++)
    {
     PmtRec pmtRec = (PmtRec) bankReconDownLoadRq.pmtRecs.elementAt(i);
     boolean passed = false;
     if (pmtRec.pmtStatus.PmtStatusCode.equals("PmtRecon")){passed = true;}
     String colorString = (passed)? "":"<font size=3 color = red>" ;
      %>
	
	<tr>
		<td><%=colorString%><%=i+1%></td>
		<%
            PmtTransId pmtTransId0 = (PmtTransId)pmtRec.pmtTransIds.elementAt(0);
            PmtTransId pmtTransId1 = (PmtTransId)pmtRec.pmtTransIds.elementAt(1);
            java.math.BigDecimal x = new java.math.BigDecimal(pmtRec.pmtInfo.curAmt);
            //---------Calculate the sum of Reconciled amount only--
            if (pmtRec.pmtStatus.PmtStatusCode.equals("PmtRecon"))
            {
            totalReconciledAmount = totalReconciledAmount.add(x);
            }
            %>
		<td><a
			href="/Support/editAndExecute.jsp?id=3388&sptn=<%=pmtTransId0.PmtId%>"
			title="Show Payment Details" target="Payment Into"><%=colorString%><%=pmtTransId0.PmtId%></a></td>
		<td><%=colorString%><%=pmtRec.pmtStatus.PmtStatusCode%></td>
		<td><%=colorString%><%=pmtRec.pmtInfo.curAmt %></td>
		<td><%=colorString%><%=pmtRec.pmtInfo.accountId.BillerId%></td>
		<td><%=colorString%><%=pmtRec.pmtInfo.accountId.BillingAcct%></td>
		<td><%=colorString%><%=pmtRec.pmtInfo.PrcDt%></td>

		<td><%=colorString%><%=pmtTransId1.PmtId%></td>
	</tr>
	<%
    }
    

    System.out.println("User : " +logedUser + " Have Parsed Bank Reconciliation message " + dirPath +fileName );
    %>
	<tr>
		<td colspan=3>
		<p align="center"><strong><font size="3">Total
		Reconciled Amount</p>
		</td>
		<td>
		<p align="center"><strong><font size="3"><%=totalReconciledAmount%></p>
		</td>
		<td>
		<p align="center"><strong><font size="3"></p>
		</td>
		<td>
		<p align="center"><strong><font size="3"></p>
		</td>
		<td>
		<p align="center"><strong><font size="3"></p>
		</td>
		<td>
		<p align="center"><strong><font size="3"></p>
		</td>
	<tr>
</table>
