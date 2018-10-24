<%@ page errorPage="errorPage.jsp"
	import="Support.*, java.util.*, java.io.* , java.sql.* "%>

<%@page  contentType="text/html;charset=UTF-8"%>	
<%request.setCharacterEncoding("UTF-8");%>

<%@page import="Support.event.resource.system.*"%>

<HTML>
<head>
<title>Sql Bound Variables </title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<a href = "editAndExecute.jsp?id=25078"> New Version</a>
<script type="text/javascript" src="includes/AJAX.js"></script>

</head>

<body>
<%
  java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
  java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");
  //--------------Reading XML Configuration File -----------
  Support.XMLConfigFileReader supportConfig =  Misc.getXMLConfigFileReader(false) ; 
  java.util.Vector conParms  = supportConfig.connParms ;
 //---------------
	int maxSize = 20;
  //-------1 -- getting the reposatory connection
  Statement stmt = repCon.createStatement();
  String loggedUser  = Misc.getConnectionUserName(con) ;
  String companyId = "" ;
  companyId = " ICDB.security.get_user_company('"+loggedUser+"') " ;
if (request.getParameter("Apply") != null || request.getParameter("SaveAsDefaults") != null )
{
	String saveToUser = "" ; 
	
 	if (request.getParameter("Apply") != null) 
 	{	
 		saveToUser = loggedUser ; 	
	}
 	else if (request.getParameter("SaveAsDefaults") != null)
 	{	
 		saveToUser = "DEFAULT" ; 
 	}
	 
   //-------------Storing the boundVars in the DB
  //--------1- Deleting old values----
  stmt.execute("delete from SQL_BOUND_VARS where company_id = "+ companyId + " and upper(owner) = '" + saveToUser.toUpperCase() +"'");
  //-------2-- storing new values--------
  int i= 1;
  while(request.getParameter("title"+i) != null)
	{
    String QUERY_FOR_LIST = Support.Misc.replaceSingleQuteWithDouble(request.getParameter("QUERY_FOR_LIST"+i).toString());
    String x = "Insert into SQL_BOUND_VARS (sn, owner, title, bound_var_name, active, default_val , data_type , QUERY_FOR_LIST , Company_id) values "
       +"("+request.getParameter("sn"+i).toString()+", '"+saveToUser+"', '"+request.getParameter("title"+i).toString()+"','"+request.getParameter("sqlBoundVar"+i).toString()+"','"+ ( (request.getParameter("active"+i) != null )? "Y" : "N" ) +"','"+request.getParameter("defaultValue"+i).toString()+"','"+request.getParameter("data_type"+i).toString()+ "','"+QUERY_FOR_LIST+ "' , "+companyId+")";
    stmt.execute(x);
    i++;                                          
  }
}
 
%>
<form name="form1" method="post" action="">
<p align="center"><strong><font size="5">
<div align="center"><input type="submit" name="Apply"
	value="Apply"> <a href='queryParams.jsp'> Show Parms</a><strong><font
	size="5"> </font></strong></div>
<p><strong><font size="5">Your (<%=(request.getParameter("loadDefaults") != null)? "DEFAULT" : loggedUser %>) Bound Variables </font></strong></p>
<table width="59%" border="1" align="center">
	<tr>
		<td width="8%">SN</td>
		<td width="13%">
		<div align="center"><strong>Tiltle</strong></div>
		</td>
		<td width="11%"><strong>Type</strong></td>
		<td width="23%">
		<div align="center"><strong>SQL Bound Var Name </strong></div>
		</td>
		<td width="12%"><strong>Default Value </strong></td>
		<td width="23%"><b>Query (only For Lists)</b></td>
		<td width="23%">
		<div align="center"><strong>Active?</strong></div>
		</td>
	</tr>
	<% 
    //-----------Getting The sql bound vars from DB
    String sqlBoundVarOwner  = (request.getParameter("loadDefaults") == null ) ? loggedUser:"DEFAULT" ; 
    String owner = sqlBoundVarOwner.toUpperCase() ; 
    //if ( owner == null || owner.trim().equalsIgnoreCase(""))  owner = "DEFAULT" ;
    String xx = "select * from Support.SQL_BOUND_VARS where "+((true)? " Company_id = " + companyId : " 1=1 ")  +"and upper(owner) = '" + owner + "' order by sn";  
	ResultSet rs = stmt.executeQuery(xx);
    
    int i = 0; 
    int lastSN = 0;
    while (rs.next())
    { lastSN  =rs.getInt("sn");
    %>
	<tr>
		<td height="28"><input type="text" name="sn<%=i+1%>"
			value="<%=rs.getString("sn")%>" size="7"></td>
		<td>
		<% String x = rs.getString("title"); %> <input type="text"
			name="title<%=i+1%>" value="<%=x%>" size="16"></td>
		<td><input type="text" name="data_type<%=i+1%>"
			value="<%=rs.getString("DATA_TYPE")%>" size="12"></td>

		<td><input type="text" name="sqlBoundVar<%=i+1%>"
			value="<%=rs.getString("BOUND_VAR_NAME")%>"></td>
		<td><input name="defaultValue<%=i+1%>" type="text"
			value="<%=(rs.getString("DEFAULT_VAL")==null)? "" : rs.getString("DEFAULT_VAL") %>"
			size="10"></td>
		<td><strong><font size="5"> <textarea name="QUERY_FOR_LIST<%=i+1%>" ><%=(rs.getString("QUERY_FOR_LIST")==null)? "" : rs.getString("QUERY_FOR_LIST") %> </textarea></td>
		<td>
		<div align="center"><input name="active<%=i+1%>" type="checkbox"
			readonly value="Y"
			<%=(rs.getString("Active").equals("Y"))? "checked" :"" %>></div>
		</td>
	</tr>
	<% 
    i++;
    }
    
    rs.close();
    stmt.close();
    

    if (request.getParameter("addNew") != null)
    { %>
	<tr>
		<td height="28"><input type="text" name="sn<%=i+1%>"
			value="<%=lastSN+1%>" size="7"></td>
		<td><input type="text" name="title<%=i+1%>" value="" size="16"></td>
		<td><input type="text" name="data_type<%=i+1%>" value=""
			size="12"></td>
		<td><input type="text" name="sqlBoundVar<%=i+1%>" value=""></td>
		<td><input name="defaultValue<%=i+1%>" type="text" value=""
			size="10"></td>
		<td><textarea name="QUERY_FOR_LIST<%=i+1%>" ></textarea> </td>
		<td>
		<div align="center"><input name="active<%=i+1%>" type="checkbox"
			readonly value="Y""checked" ></div>
		</td>
	</tr>
	<%}%>
</table> 
<p align="center">
<input type="submit" name="Apply"  value="<%=(request.getParameter("loadDefaults") == null ) ? "Apply" : "Copy Defaults To Mine"%>">
<input type="submit" name="addNew" value="Add New"></p>
<% 
	if (request.getParameter("loadDefaults") == null ) 
	{ 
	  	%><p align="center"> <input type="submit" name="SaveAsDefaults"  value="Save as System Default Values" onclick="return  confirm(' Are You Sure this Will Replace the Current Default Values ?');"> </p><% 
	}
%>

<p align="center"><input type="submit" name="loadDefaults"
	value="Load Defaults"> <input type="submit" name="loadMine"
	value="Load Mine"></p>
<div align="center"></div>
</font> </strong></p>
</form>
<a href="index.jsp"> Return To Support</a>
</body>
</html>
