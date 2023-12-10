<%@ page errorPage="errorPage.jsp"
	contentType="text/html;charset=UTF-8"
	import="SADAD.DATASTRCTURE.*, java.net.* ,  com.jscape.inet.ftp.*"%>
<%@page import="Support.Misc" %>	
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=UTF-8">
<title>Biller Reconciliation Request</title>
</head>
<body>
<p align="center"><strong><font size="4"> Biller
Reconciliation Request From SADAD </font></strong></p>


<%
    java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
     if (con == null)
     {
      session.setAttribute("comeFrom","ParseBankSPL.jsp" + "?" + request.getQueryString());
      response.sendRedirect("loginScreen.jsp");
     }
    String logedUser = Misc.getConnectionUserName(con);    
    //--------------Getting the File Contents from The Server Using FTP Proptocol-----------
    String dirPath = request.getParameter("dirPath");
    String fileName =request.getParameter("fileName"); 
    String serverName = request.getParameter("serverName"); 
    if (serverName == null )
    {throw new Exception("You should define Server IP");}
          SADADService ss = null;
    //---------if server is a Windows Server
    if (serverName.equals("10.16.17.51") || serverName.equals("10.16.17.57") )
    {
       String userName ="", password = "";
      if (serverName.equals("10.16.17.57"))
        { userName = "itadmin";
          password = "IT123admin";
        }
      if (serverName.equals("10.16.17.51"))
        { 
          userName = "Administrator";
          password = "riyadh123";
        }
      Support.DownLoad.MyAuthenticator auth = new Support.DownLoad.MyAuthenticator(userName,password);
      java.net.Authenticator.setDefault(auth);      
      String fileURL = "";
      try{  
          fileURL = "file:////"+serverName+"/" + dirPath + "/" + fileName ;
          //fileURL = "file:\\\\"+serverName +  dirPath + "\\"+ fileName ; 
          ss = new SADADService( fileURL ,null, true);
      }
      catch (Exception e) { throw new Exception ("Unable to Connect to " + fileURL + "Using ("+userName + ","+ password + ") Due to " + e.getMessage());}
    }
    //----if Server is AIX Server --------
    else 
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
   
     
    SADAD.DATASTRCTURE.BillerReconUploadRq brur = (BillerReconUploadRq) ss.billerSvcRq.messageRequests.elementAt(0);  //new PmtNotifyUploadRq(xmlFileURL,con);
  
%>
<p><strong><font size="5">Summary </font></strong></p>
<table width="50%" border="0">
	<tr>
		<td width="57%"><strong>To Biller :</strong></td>
		<td width="43%"><%=ss.signonRq.signonProfile.receiver%></td>
	</tr>

	<tr>
		<td width="57%"><strong>Processing Date :</strong></td>
		<td width="43%"><%=brur.PrcDt %></td>
	</tr>
	<tr>
		<td><strong>Collected Payments Amount :</strong></td>
		<td><%=brur.CollectPmtAmt %></td>
	</tr>
	<tr>
		<td>
		<p><strong>Reconciled paymeny Amount : </strong></p>
		</td>
		<td><%=brur.ReconPmtAmt%></td>
	</tr>
	<tr>
		<td><strong>unreconciled Payment Amount :</strong></td>
		<td><%=brur.UnReconPmtAmt%></td>
	</tr>
	<tr>
		<td><strong>Transaction Fees : </strong></td>
		<td><%=brur.TransFees %></td>
	</tr>
</table>
<p><strong><font size="5">Details</font></strong></p>
<table width="73%" border="1" align="center">
	<tr>
		<td width="12%">
		<div align="center"><strong>Bank ID</strong></div>
		</td>
		<td width="13%">
		<div align="center"><strong> Bank Total </strong></div>
		</td>
		<td width="75%">
		<div align="center"><strong> Branches Details </strong></div>
		</td>
	</tr>
	<% 
  java.math.BigDecimal total = new java.math.BigDecimal(0);
  for (int i = 0 ; i<brur.PmtBankRecs.size(); i++)
   { PmtBankRec pmtBankRec = (PmtBankRec)brur.PmtBankRecs.elementAt(i); %>
	<tr>
		<td><%=pmtBankRec.BankId%></td>
		<td><%=pmtBankRec.CurAmt%></td>
		<td>
		<table width="97%" border="1">
			<tr>
				<td width="21%">
				<div align="center"><strong>Branch </strong></div>
				</td>
				<td width="23%">
				<div align="center"><strong>Branch Total</strong></div>
				</td>
				<td width="56%">
				<div align="center"><strong>Payment Details</strong></div>
				</td>
			</tr>
			<% for (int j = 0 ; j < pmtBankRec.PmtBranchRecs.size() ; j++ ) {
        PmtBranchRec pmtBranchRec = (PmtBranchRec) pmtBankRec.PmtBranchRecs.elementAt(j);  %>
			<tr>
				<td><%=pmtBranchRec.BranchCode%></td>
				<td><%=pmtBranchRec.CurAmt%></td>
				<td>
				<table width="75%" border="1">
					<tr>
						<td>Acct No</td>
						<td>Bill No</td>
						<td>Amount</td>
						<td>PrcDate</td>
						<td>PMT_ID's</td>
					</tr>
					<% for (int k = 0 ; k < pmtBranchRec.pmtRecs.size() ; k++ ) {
                 PmtRec pmtRec = (PmtRec) pmtBranchRec.pmtRecs.elementAt(k); 
            %>
					<tr>
						<% 
                  String accNo = pmtRec.pmtInfo.accountId.BillingAcct;
                  String billnumber = pmtRec.pmtInfo.BillNumber;//( pmtRec.pmtInfo.BillNumber ==null  || pmtRec.pmtInfo.BillNumber.equals(""))? pmtRec.pmtInfo.accountId.BillingAcct : pmtRec.pmtInfo.BillNumber ; 
                  String prcDate = pmtRec.pmtInfo.PrcDt;
                  java.math.BigDecimal x = new java.math.BigDecimal(pmtRec.pmtInfo.curAmt);
                  total = total.add(x);                  
                %>
						<td><%= accNo %></td>
						<td><%= billnumber %></td>
						<td><%= pmtRec.pmtInfo.curAmt %></td>
						<td><%= prcDate %></td>
						<td>
						<table width="75%" border="1">
							<tr>
								<% for (int L = 0 ; L < pmtRec.pmtTransIds.size() ; L++ ) {
                        PmtTransId pmtTransId = (PmtTransId)pmtRec.pmtTransIds.elementAt(L);
                     %>

								<td><%= pmtTransId.PmtIdType %></td>
								<% } %>
							</tr>
							<tr>
								<% boolean isSPTN  = false;
                       for (int L = 0 ; L < pmtRec.pmtTransIds.size() ; L++ ) 
                        {
                         PmtTransId pmtTransId = (PmtTransId)pmtRec.pmtTransIds.elementAt(L);
                         String pmtId= pmtTransId.PmtId;
                         isSPTN = pmtTransId.PmtIdType.equals("SPTN");
                         if (isSPTN)
                         { pmtId ="<a target 'Payment Details'  href = 'editAndExecute.jsp?id=3388&sptn="+pmtTransId.PmtId+"' >"+pmtTransId.PmtId+"</a>" ;}
                     %>
								<td><%= pmtId %></td>
								<% } %>
							</tr>
						</table>
						</td>
					</tr>
					<% } %>
				</table>
				</td>
			</tr>
			<% } %>
		</table>
		</td>
	</tr>
	<%}
      
       

    System.out.println("User : " +logedUser + " Have Parsed Biller Reconciliation Message " + dirPath +fileName );
  %>

</table>
Total =
<%=total%>
</body>

</html>
