<%@page import="Support.Misc"%>
<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"
    import = "com.implex.database.mapGeneration.*" 
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.implex.database.ApplicationContext"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>
<form action="">
<br>Table Owner   : <input name = "MAP_TABLE_OWNER" value = "<%=request.getParameter("MAP_TABLE_OWNER")%>"></input>
<br>Table Name    : <input name = "MAP_TABLE_NAME" value = "<%=request.getParameter("MAP_TABLE_NAME")%>" ></input>
<br>Output Source Path  : <input name = "sourceOutPath" value = "<%=(request.getParameter("sourceOutPath") == null)? "D:\\SmartValue\\Sources\\App\\Implex-core\\src\\" :  request.getParameter("sourceOutPath")%>" size="80" ></input>
<br>Class Package : <input name = "PackageName" value = "<%=(request.getParameter("PackageName") == null)? "com.implex.database.map" :  request.getParameter("PackageName")%>" size="80" ></input>
<br>Generate Service Class : <input type = "checkbox" name = "GenServiceClass"  checked="checked" value="Y"></input>

<br><input  type="submit" name = "submit" >
</form>
<%
	if (request.getParameter("submit")!= null )
	{
		String mapTableName = request.getParameter("MAP_TABLE_NAME");
		String mapTableOwner = request.getParameter("MAP_TABLE_OWNER");
		String packageName = request.getParameter("PackageName");
		String sourceOutPath = request.getParameter("sourceOutPath");
		packageName = (packageName==null)? "com.smartValue.support.map" : packageName; 
		boolean GenServiceClass = request.getParameter("GenServiceClass").equalsIgnoreCase("Y") ; 
		MapGenerator mg = new MapGenerator(null);
		mg.getMapCalculatedVariables().add(new MapCalculatedVariable("abc", com.implex.database.PersistantObject.class)) ;
		mg.setGenerateValidationClass(false);
		mg.setGenerateServiceClass(GenServiceClass);
		mg.setGenerateXhtmlMaintForm(false);
		mg.setGenerateXhtmlMaintainance(false);
		mg.setGenerateSecurityHandlerClass(false);
		mg.setAddAsTreeNode(false);
		mg.setTreeNodeParent("HR");// the parent tree Node node XHTML page
		mg.setAddToFavorite(false);
		
		mg.setXhtmlFolder("test");
		mg.setSourceOutPath("D:\\SmartValue\\Sources\\App\\SmartTool\\src\\");
		mg.setTableOwner(mapTableOwner) ;
		mg.setPackageName(packageName);
		mg.setSourceOutPath(sourceOutPath) ; 
		mg.setTableName(mapTableName);
		mg.execute();
		java.util.Stack<Object> sm =  Misc.getModuleServiceContainerFromUserSession(session).getMessageCommnunicatorService().getSysMessagesToUser();
		out.print (sm) ; 
		Misc.getModuleServiceContainerFromUserSession(session).getMessageCommnunicatorService().clear();
		//sm.clear();
	}
	
%>
</body>
</html>