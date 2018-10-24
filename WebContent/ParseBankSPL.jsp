<%@ page contentType="text/html;charset=windows-1252"
	errorPage="errorPage.jsp"
	import="SADAD.DATASTRCTURE.*,  com.jscape.inet.ftp.*"%>
<%@page import="Support.Misc" %>	
<% 
String fileName =request.getParameter("fileName"); 
%>
<title>Bank SPL Message <%=fileName%></title>

<%  
    java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
     if (con == null)
     {
      session.setAttribute("comeFrom","ParseBankSPL.jsp" + "?" + request.getQueryString());
      response.sendRedirect("loginScreen.jsp");
     }
    String logedUser = Misc.getConnectionUserName(con);    
    String dirPath = request.getParameter("dirPath");
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
          userName = "utadmin";
          password = "riyadh123";
        }
      Support.DownLoad.MyAuthenticator auth = new Support.DownLoad.MyAuthenticator(userName,password);
      java.net.Authenticator.setDefault(auth);      
      String fileURL = "";
      try{  
          fileURL = "file:////"+serverName+"/" + dirPath + "/" + fileName ;
          //fileURL = "file://"+serverName +  dirPath + fileName ; 
          ss = new SADADService( fileURL ,null, false);
      }
      catch (Exception e) {throw new SADAD.SADADException("Unable to Connect to " + fileURL + " Using ("+userName + ","+ password + ") Due to " + e.getMessage(),e,1);
      }
    }
    //----if Server is AIX Server --------
    //---Getting the File Contents from The Server Using FTP Proptocol-----------
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
    // else {throw new Exception ("Shawky: Server " + serverName + "is not recognized");}

    String clientDate = ss.signonRq.clientDt ;
    //-------------End Getting the File Contents from The Server Using FTP Proptocol-----------
    //-------------Parsing the Contents of the file -------------------------------------------
    BankSPLRq bankSPLRq= (BankSPLRq) ss.bankSvcRq.messageRequests.elementAt(0);
   
    int pmtRecSize = bankSPLRq.pmtRecs.size();
 
    %>
<p align="center"><strong><font size="5"> SADAD
Payment Log (SPL) Request </font></strong> <BR>
<BR>
</p>
<BR>
Client Date :
<%=ss.signonRq.clientDt %>
<BR>
From Bank :
<%= ss.signonRq.signonProfile.sender%>
<BR>
Processing Date :
<%=bankSPLRq.prcDt%>
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
		<p align="center"><strong><font size="3">DueDt</p>
		</td>
		<td>
		<p align="center"><strong><font size="3">EffDt</p>
		</td>

		<td>
		<p align="center"><strong><font size="3">PMT_ID2</p>
		</td>
	<tr>

		<%
    
    for (int i=0; i< pmtRecSize ; i++)
    {
     PmtRec pmtRec = (PmtRec) bankSPLRq.pmtRecs.elementAt(i);
        boolean passed = false;
       if (pmtRec.pmtStatus.PmtStatusCode.equals("PmtNew")){passed = true;}
       String colorString = (passed)? "":"<font size=3 color = red>" ;
      %>
	
	<tr>
		<td><%=colorString%><%=i+1%></td>
		<%
            PmtTransId pmtTransId0 = (PmtTransId)pmtRec.pmtTransIds.elementAt(0);
            String pmtTransId1Str = "";
            try 
            {
            PmtTransId pmtTransId1 = (PmtTransId)pmtRec.pmtTransIds.elementAt(1);
            pmtTransId1Str = pmtTransId1.PmtId;
            } catch (Exception e ){}
          %>
		<td><%=colorString%><%=pmtTransId0.PmtId%></td>
		<td><%=colorString%><%=pmtRec.pmtStatus.PmtStatusCode%></td>
		<td><%=colorString%><%=pmtRec.pmtInfo.curAmt %></td>
		<td><%=colorString%><%=pmtRec.pmtInfo.accountId.BillerId%></td>
		<td><%=colorString%><%=pmtRec.pmtInfo.accountId.BillingAcct%></td>
		<td><%=colorString%><%=pmtRec.pmtInfo.PrcDt%></td>
		<td><%=colorString%><%=pmtRec.pmtInfo.DueDt%></td>
		<td><%=colorString%><%=pmtRec.pmtStatus.EffDate%></td>
		<td><%=colorString%><%=pmtTransId1Str%></td>

	</tr>
	<%
    }
    
       

    System.out.println("User : " +logedUser + " Have Parsed Bank SPL message " + dirPath +fileName );
    %>
</table>
