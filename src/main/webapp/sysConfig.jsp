<%@ page errorPage="errorPage.jsp"
	contentType="text/html;charset=UTF-8"
	import="java.util.* , java.sql.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Environmente Parameters</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<%
  //----------restrict the usage of this to Amin Only-------------
   //-------The password  is dynamically changes (on hour password) -----
   java.util.Date currentDate = new java.util.Date();
   java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("ddMMyyHH");
   String s = formatter.format(currentDate);
   String adminPassword = request.getParameter("adminPassword");
   if (! s.equals(adminPassword))
   {
    throw new Support.MyException("Invalid Admin login Trail " , null, Support.MyException.LOG_EXCEPTION_STACK_TRACE ,session.getServletContext().getRealPath(""));
   }
   session.putValue("SmartToolAdmin" , "true");
 //--------------Reading XML Configuration File -----------
  Support.XMLConfigFileReader supportConfig =  Support.Misc.getXMLConfigFileReader(false) ; 
  java.util.Vector conParms  = supportConfig.connParms ;
  //--------------------------Updating The XML Config File If requiresd-------------
  if (request.getParameter("Apply") != null)
  {
    //----------------Updating XML Configuration File -----------
    Support.ConnParms xxx = null;
    for (int i =0 ; i< conParms.size() ; i++)
    {
      xxx= (Support.ConnParms)conParms.elementAt(i);
      xxx.type = request.getParameter("type"+i);
      xxx.odbcDataSource = request.getParameter("odbcDataSource"+i);
      xxx.name = request.getParameter("name"+i);
      xxx.server = request.getParameter("server"+i);
      xxx.serviceName = request.getParameter("serviceName"+i);
      xxx.active = request.getParameter("active"+i);
    }

    //---------setting The REposatory Connection Parms-------------
    supportConfig.reposatoryConn.type = request.getParameter("repType");
    supportConfig.reposatoryConn.odbcDataSource = request.getParameter("repOdbcDataSource");
    supportConfig.reposatoryConn.name = request.getParameter("repConnName");
    supportConfig.reposatoryConn.server = request.getParameter("repConnServer");
    supportConfig.reposatoryConn.serviceName = request.getParameter("repConnServiceName");
    supportConfig.reposatoryConn.userName = request.getParameter("repUserName");
    supportConfig.reposatoryConn.password = request.getParameter("repPassword");
    //------------End of Updating XML Configuration File -----------
    //----------------Saving XML Configuration File ----------- 
    supportConfig.save();
    //--------------End of Saving XML Configuration File ----------- 
  }
%>
<form name="form1" method="post" action="">
<p align="center"><strong><font size="5">
<div align="center"><input type="submit" name="Apply"
	value="Apply"> <a href='loginScreen.jsp'> logon/off</a><strong><font
	size="5"> </font></strong></div>
<p><strong><font size="5">1- DB Connections</font></strong><strong><font
	size="5"> </font></strong><strong><font size="5"> </font></strong></p>
<div align="center">
<table width="53%" border="1">
	<tr>
		<td width="10%"><strong>Type</strong></td>
		<td width="24%"><strong>Data Source (For ODBC)</strong></td>
		<td width="24%">
		<div align="center"><strong>Name</strong></div>
		</td>
		<td width="18%">
		<div align="center"><strong>Server</strong></div>
		</td>
		<td width="27%">
		<div align="center"><strong>Service Name</strong></div>
		</td>
		<td width="31%">
		<div align="center"><strong>Active</strong></div>
		</td>
		<td width="31%">
		<div align="center"><strong>Bind Variables</strong></div>
		</td>
	</tr>
	<% for (int i =0; i < conParms.size() ; i++)
          { Support.ConnParms thisConParm = (Support.ConnParms)conParms.elementAt(i);
      %>
	<tr>
		<td><input type="text" size=6 name="type<%=i%>"
			value="<%=thisConParm.type%>"></td>
		<td><input type="text" name="odbcDataSource<%=i%>"
			value="<%=thisConParm.odbcDataSource%>"></td>
		<td><input type="text" name="name<%=i%>"
			value="<%=thisConParm.name%>"></td>
		<td><input type="text" name="server<%=i%>"
			value="<%=thisConParm.server%>"></td>
		<td><input type="text" name="serviceName<%=i%>"
			value="<%=thisConParm.serviceName%>"></td>
		<td>
		<div align="center"><input type="text" name="active<%=i%>"
			value="<%=thisConParm.active%>" size="2"></div>
		</td>
		<td>
		<table border=1>
			<tr>
				<td>
				<div align="center"><strong>BindVarName</strong>
				<td>
				<td>
				<div align="center"><strong>BindVarValue</strong>
				<td>
			</tr>
			<%             
              for (int j=0 ; j< thisConParm.getSqlBindVars().size() ; j++)
              {
                Support.SqlBindVar sqlBindVar = (Support.SqlBindVar) thisConParm.getSqlBindVars().elementAt(j);
              
                %>
			<tr>
				<td><%=sqlBindVar.bindVarName%>
				<td>
				<td><%=sqlBindVar.bindVarValue%>
				<td>
			</tr>
			<%
              }
  
              %>
		</table>
		</td>
	</tr>
	<%}%>
</table>
<p align="left"><font size="5"><strong>2- Support
Reposatory DB Connection</strong></font></p>
<table width="41%" border="1">
	<tr>
		<td><strong>TYPE</strong></td>
		<td><input name="repType" type="text"
			value="<%=supportConfig.reposatoryConn.type%>" size="20"></td>
	</tr>
	<tr>
		<td>Data source</td>
		<td><input name="repOdbcDataSource" type="text"
			value="<%=supportConfig.reposatoryConn.odbcDataSource%>" size="20"></td>
	</tr>

	<tr>
		<td width="34%"><strong>Name</strong></td>
		<td width="66%"><input name="repConnName" type="text"
			value="<%=supportConfig.reposatoryConn.name%>" size="20"></td>
	</tr>
	<tr>
		<td><strong>Server</strong></td>
		<td><input name="repConnServer" type="text"
			value="<%=supportConfig.reposatoryConn.server%>" size="20"></td>
	</tr>
	<tr>
		<td><strong>Service Name</strong></td>
		<td><input name="repConnServiceName" type="text"
			value="<%=supportConfig.reposatoryConn.serviceName%>" size="20"></td>
	</tr>
	<tr>
		<td>User Name</td>
		<td><input name="repUserName" type="text"
			value="<%=supportConfig.reposatoryConn.userName%>" size="20"></td>
	</tr>
	<tr>
		<td><strong>Password</strong></td>
		<td><input name="repPassword" type="password"
			value="<%=supportConfig.reposatoryConn.password%>" size="20"></td>
	</tr>
</table>
<p><input type="submit" name="Apply" value="Apply"> (Take
Care if you save you will loose SqlVar bind variables defination)</p>
</div>
<div align="center"></div>
</form>
</body>
</html>
