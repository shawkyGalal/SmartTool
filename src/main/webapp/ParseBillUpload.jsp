<%@ page errorPage="errorPage.jsp"
	contentType="text/html;charset=UTF-8"
	import="com.jscape.inet.ftp.*, SADAD.DATASTRCTURE.*, java.net.*"%>
<%@page import="Support.Misc" %>	
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=UTF-8">
<title>Bill Upload Parsing</title>
</head>
<body>

<p>
<% 
     java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
     if (con == null)
     {
      session.setAttribute("comeFrom","ParseBillUpload.jsp" + "?" + request.getQueryString());
      response.sendRedirect("loginScreen.jsp");
     }
    String logedUser = Misc.getConnectionUserName(con);    
  //-----------Getting The file from Server Using FTP--------
    String dirPath = request.getParameter("dirPath");
    String fileName =request.getParameter("fileName"); 
    String serverName = request.getParameter("serverName"); 
    String userName ="", passwordx = "";
    if (serverName == null )
    {throw new Exception("You should define Server IP");}
  
    SADADService ss = null;
    String fileURL = "";
    //---------if server is a Windows Server
    if (serverName.equals("10.16.17.51")  || serverName.equals("10.16.17.71"))
    {
      if (serverName.equals("10.16.17.57"))
        { userName = "itadmin";
          passwordx = "IT123admin";
        }
      if (serverName.equals("10.16.17.51"))
        { 
          userName = "utadmin" ; //"Administrator";
          passwordx = "riyadh123";
        }
      Support.DownLoad.MyAuthenticator auth = new Support.DownLoad.MyAuthenticator(userName,passwordx);
      java.net.Authenticator.setDefault(auth);      
      fileURL = "file:////"+serverName+"/" + dirPath + "/" + fileName ;
     }
    //----if Server is AIX Server --------
    else 
    {
      //--------First Down Load the file using FTP ----
      String tempFileDir = "C:\\Temp";
      Ftp ft = null;
      %> <%@ include file="ServersAuthuntication.html"%>
<%
      try{
      ft.connect();
      ft.setLocalDir(new java.io.File(tempFileDir)); //-----------Store The file to temp Dir-----
      ft.setDir(dirPath);  
      ft.download(fileName);
      ft.disconnect();
      //
      fileURL = "file:///"+tempFileDir + java.io.File.separator + fileName ;
      //ss = new SADADService(fileURL ,null, true);
      }
      catch (Exception e) {throw new Exception ("Unable to FTP Connect to " + serverName  + dirPath + fileName + "  User Name = " + ft.getUsername() + "  Due To " + e.getMessage() );}
    }
    //-----Testing File Size
    long fileSize = new java.io.File(fileURL).length();
    
    //----------Start Parsing The file------
    try{  
         ss = new SADADService( fileURL ,null, false);
       }
      catch (Exception e) { throw new Exception ("Unable to Connect to " + fileURL + "Using ("+userName + ","+ passwordx + ") Due to " + e.getMessage());}
    //--------End Parsing the file----------
    

    java.util.Date startParsingTime = new java.util.Date();
    java.util.Date endParsingTime = new java.util.Date();
    SADAD.DATASTRCTURE.BillUploadRq bur = (BillUploadRq) ss.presSvcRq.messageRequests.elementAt(0);  //new PmtNotifyUploadRq(xmlFileURL,con);     
  
    long duration =  endParsingTime.getTime() - startParsingTime.getTime();
    
    int billRecCount = bur.billRecs.size();
    BillRec billRec;
    %>
</p>
Parse time =
<%=duration%>
ms
<p align="center"><strong><font size="5">Bill Data
Details</font></strong></p>
<p align="left">File Name : <%=fileName%></p>
<p align="left">Bill Data Time Stamp <%=( (bur.timeStamp!= null )? "abc" : "Missing Time Stamp" ) %></p>
<p align="left">The Rquest Uniqu ID is <a
	title="Click To Display all Bill Upload Files with this RqUID"
	target="All Bill Upload File "
	href="editAndExecute.jsp?id=10517&rquid=<%= ss.presSvcRq.RqUID%>"><%= ss.presSvcRq.RqUID%></a>
<em><font size="1">(Click To Display all Bill Upload Files
having this RqUID)</font></em></p>
<p align="left">This Bill Upload Request File Contain <%=billRecCount%>
Records</p>
<table width="75%" border="1" align="center">
	<tr>
		<td>
		<div align="center"><strong>S/N </strong></div>
		</td>
		<td>
		<div align="center"><strong>Billing Account</strong></div>
		</td>
		<td>
		<div align="center"><strong>Bill Cat.</strong></div>
		</td>
		<td>
		<div align="center"><strong>Bill Number </strong></div>
		</td>
		<td>
		<div align="center"><strong>Status </strong></div>
		</td>
		<td>
		<div align="center"><strong>Amount Due</strong></div>
		</td>
		<td>
		<div align="center"><strong>Expire Date</strong></div>
		</td>
	</tr>
	<% 
  String signedString = "";
  startParsingTime = new java.util.Date();
  for (int i = 0 ; i<billRecCount ; i++ ) 
    { billRec = (BillRec) bur.billRecs.elementAt(i);%>
	<tr>
		<td><%=i+1%></td>
		<td><a target='Account Info'
			href='editAndExecute.jsp?id=3107&billerId=<%=billRec.billInfo.BillerId%>&accId=<%=billRec.billInfo.BillingAcct%>'><%=billRec.billInfo.BillingAcct%></a></td>
		<td><%=billRec.billInfo.billCategory%></td>
		<td><%=billRec.billInfo.BillNumber%></td>
		<td><%=billRec.billStatusCode%></td>
		<td><%=billRec.billInfo.ammountDue%></td>
		<td><%=billRec.billInfo.expDt%></td>
	</tr>
	<% if (i> 10000) 
      { i= billRecCount;
      %><tr>
		System not allowed to parse more than 10000 Bill Records
	</tr>
	<%
      }
    } 
          
   
    System.out.println("User : " +logedUser + " Have Parsed Biller BillUpload Message " + dirPath +fileName );
    endParsingTime = new java.util.Date();
    duration =  endParsingTime.getTime() - startParsingTime.getTime();
    %>

</table>
Display time =
<%=duration%>
ms
</body>
</html>
