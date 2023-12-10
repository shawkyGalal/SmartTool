<%@ page contentType="text/html;charset=windows-1252"
	errorPage="errorPage.jsp"
	import="SADAD.DATASTRCTURE.*,  com.jscape.inet.ftp.*"%>
<%@page import="Support.Misc" %>	
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
    SADADService ss = null;
    //---------if server is a Windows Server
    if (serverName.equals("10.16.17.51")  ) // || serverName.equals("10.16.17.52")
    {
       String userName ="", password = "";
      if (serverName.equals("10.16.17.52"))
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
      catch (Exception e) { throw e; //throw new Exception ("Unable to Connect to " + fileURL + "Using ("+userName + ","+ password + ") Due to " + e.getMessage());
      }
    }
    else  // AIX Server 
    {
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
        
        ss = new SADADService("file:///"+tempFileDir + java.io.File.separator + fileName ,null, false);
    }
    String clientDate = ss.signonRq.clientDt ;
    //-------------End Getting the File Contents from The Server Using FTP Proptocol-----------
    //-------------Parsing the Contents of the file -------------------------------------------
    XferAddRq xferAddRq= (XferAddRq) ss.bankSvcRq.messageRequests.elementAt(0);
   
    %>
<p align="center"><strong><font size="5">Intra Bank
Transfer Request </font>(From SADAD to <%= ss.signonRq.getSignonProfile().getReceiver()%>)</strong>
<BR>
<BR>
</p>
<BR>
Generation Date :
<%=ss.signonRq.getClientDt() %>
<BR>
To Bank :
<%= ss.signonRq.getSignonProfile().getReceiver()%>
<BR>
Status Code :
<%=ss.bankSvcRq.status.statusCode%>
<BR>
Status Short Desc :
<%=ss.bankSvcRq.status.shortDesc%>


<p align="center"><strong><font size="5"> Transfer
Details </font></strong> <BR>
<BR>
</p>
<table align=center border=1>
	<tr>
		<td rowspan="2">
		<p align="center"><b>PMT_ID</b></p>
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
    for (int i=0; i< xferAddRq.xferInfos.size() ; i++)
    {
     XferInfo xferInfo = (XferInfo) xferAddRq.xferInfos.elementAt(i);
     java.math.BigDecimal curAmtAsBigDec= new java.math.BigDecimal(xferInfo.CurAmt);
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
    Ministry of water 2010011119940
    NCB CC  63001299920103
    AMMD   500000010006089001135
    Fly SAMA 01417860
    American Express 0101121212014
    Bravo 1241400 
    Zakat 2180166699949
    */

     String biller = "Not Recognized By Smart Tool";
     if (xferInfo.DepAcctIdTo.AcctId.equals("20864006000100"))
     {
     biller = "Mobily ";
     }
     else if (xferInfo.DepAcctIdTo.AcctId.equals("500000010006089000000"))
     {
     biller = "Madinah_Municipality";
     }
     else if (xferInfo.DepAcctIdTo.AcctId.equals("01008520415000"))
     {
     biller = "Marafiq";
     }
     else if (xferInfo.DepAcctIdTo.AcctId.equals("50147324000105"))
     {
     biller = "CITC";
     }
     else if (xferInfo.DepAcctIdTo.AcctId.equals("21255039000108"))
     {
     biller = "STC";
     }
     else if (xferInfo.DepAcctIdTo.AcctId.equals("20853936000302"))
     {
     biller = "NCCI";
     }
     else if (xferInfo.DepAcctIdTo.AcctId.equals("4701709433"))
     {
     biller = "KAUU";
     }

     else if (xferInfo.DepAcctIdTo.AcctId.equals("2010011119940"))
     {
     biller = "Ministry of Water";
     }
     else if (xferInfo.DepAcctIdTo.AcctId.equals("2258056381"))
     {
     biller = "SAMBA CC";
     }
     else if (xferInfo.DepAcctIdTo.AcctId.equals("63001299920103"))
     {
     biller = "NCB CC";
     }

     else if (xferInfo.DepAcctIdTo.AcctId.equals("500000010006089001135"))
     {
     biller = "AMMD";
     }
     else if (xferInfo.DepAcctIdTo.AcctId.equals("01417860"))
     {
     biller = "Fly SAMA";
     }
     else if (xferInfo.DepAcctIdTo.AcctId.equals("0101121212014"))
     {
     biller = "American Express";
     }
     else if (xferInfo.DepAcctIdTo.AcctId.equals("01008552499000"))
     {
     biller = "Riyad Municipality";
     }
     else if (xferInfo.DepAcctIdTo.AcctId.equals("1241400"))
     {
     biller = "Bravo";
     }
     else if (xferInfo.DepAcctIdTo.AcctId.equals("2180166699949"))
     {
     biller = "Zakat";
     }
     else if (xferInfo.DepAcctIdTo.AcctId.equals("228608010122006"))
     {
     biller = "Makkah_Municipality";
     }
     else if (xferInfo.DepAcctIdTo.AcctId.equals("198000010006089001140"))
     {
     biller = "AMMD";
     }     
     else if (xferInfo.DepAcctIdTo.AcctId.equals("10796200104"))
     {
     biller = "SEC";
     }     
   // 
    
     //------ End of Trying to decode Account Id it its corresponding Biller
      %>
	
	<tr>
		<td><%=xferInfo.PmtId%></td>
		<td><%=xferInfo.CurAmt%></td>

		<td><%=xferInfo.DepAcctIdFrom.BankId%></td>
		<td><%=xferInfo.DepAcctIdFrom.AcctId%></td>
		<td><%=xferInfo.DepAcctIdFrom.AcctType%></td>

		<td><%=xferInfo.DepAcctIdTo.BankId%></td>
		<td><%=xferInfo.DepAcctIdTo.AcctId%></td>
		<td><%=biller%></td>
		<td><%=xferInfo.DepAcctIdTo.AcctType%></td>

	</tr>
	<%
    }
     
     System.out.println("User : " +logedUser + " Have Parsed Bank XrefAddReq Message " + dirPath +fileName );

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
