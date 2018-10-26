<%@ page contentType="text/html;charset=UTF-8"%>
<HTML>
<HEAD>
</HEAD>
<BODY BACKGROUND="Nccibgd2.gif" TEXT="blue" LINK="red">
<form name="form1" method='get' action="simpleLogin.jsp"><input
	type=Hidden name=comeFrom value='<%=request.getParameter("comeFrom")%>'>
<p align="center"><strong><font size="5">EDGE System
Login</font></strong></p>
<table width="325" border="1" align="center">
	<tr>

		<td width="117">User Name</td>
		<td width="172"><input type=text name=userName value=''></td>
	</tr>
	<tr>
		<td>Password</td>
		<td><input type=password name=password value=''> <input
			name="dBServerName" type="hidden" value="130.1.2.226"> <input
			name="dBServiceName" type="hidden" value="edge2"></td>
	</tr>
</table>


<p align="center"><input type=submit name=login value='Login'>
</p>
<div align="center">
<p>&nbsp;</p>
</div>
</form>
<% 
	request.setCharacterEncoding("UTF-8");
  	java.sql.Connection  con = null;
    Object conObj=  session.getValue("con"); 
     if (conObj != null)
     { 
        con = (java.sql.Connection)conObj;
        boolean isclosed = con.isClosed();
        if (con!=null && ! isclosed)
          { 
            con.close();
            session.removeValue("con");
          }
     }

   if (request.getParameter("login")!= null)
  {
      //--- Temproley connect using the standard settings-----
      String userName = request.getParameter("userName");
      String password = request.getParameter("password");
      String dBServerName = request.getParameter("dBServerName");
      String dBServiceName = request.getParameter("dBServiceName");
      //con = NCCI.ConnectionFactory.createConnection(dBServerName,dBServiceName,userName,password);

      String ConnectionString="jdbc:oracle:thin:@"+dBServerName+":1521:"+dBServiceName;
      java.sql.DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
      con= java.sql.DriverManager.getConnection(ConnectionString,userName,password);

      java.sql.Statement stmt = con.createStatement();

      session.putValue("con",con);
 
      String comeFrom = request.getParameter("comeFrom").toString();
      if (!comeFrom.equals("null") )
      {response.sendRedirect(request.getParameter("comeFrom"));}
      else
      //Enter Your URL here is logon passed
      {response.sendRedirect("http://ns_ho_srv1/local");}
  }
%>
<p>&nbsp;</p>
<div align="center"></div>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p align="center"><a href="changeEdgePassword.jsp">Change EDGE
System Password</a></p>
</BODY>
</HTML>
