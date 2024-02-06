<%@ page contentType="text/html;charset=windows-1252"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">
<title>Hello World</title>
</head>
<body>
<%
     Support.Telnet.TelnetExample telnet = null;
      java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");
      String command = request.getParameter("command");
      String serverName = request.getParameter("serverName"); //"10.16.16.51" ; 
      String SSIuserName = request.getParameter("SSIuserName"); //"wmin0201";
      Support.singleSignOnAccessor ssi = new Support.singleSignOnAccessor(repCon);
      String password = ssi.getPassword(serverName,SSIuserName);
      
      String[] unixCommands = {"wmin0201" , "wmadmin02" , "cd /sfs/biller/001/download" , "pwd" , "exit" };

			telnet = new Support.Telnet.TelnetExample(serverName, unixCommands , out);

%>

<p align="center"><strong><font size="5">Server
Response </font></strong></p>
<p align="center"><textarea name="Contents" cols="100" rows="40"
	wrap="PHYSICAL"><%=telnet.getServerResponse() %> </textarea></p>

</body>
</html>
