<%@ page contentType="text/html;charset=UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=UTF-8">
<title>Query Viewer</title>
</head>
<body>
<form name="form1" method="post" action=""><textarea
	name="textarea" cols="100" rows="30">
  <%String[] queryStmt= (String[])session.getAttribute("queryStmt"); 
	for (int i = 0 ; i<queryStmt.length ; i++)
	{%>
  <%=queryStmt[i]%>
<%}
  %>

  </textarea></form>
<p>&nbsp;</p>
<p></p>
</body>
</html>
