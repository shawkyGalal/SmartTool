<%@ page contentType="text/html;charset=windows-1252"
	errorPage="errorPage.jsp"
	import="java.net.* ,java.io.InputStream,   com.jscape.inet.ftp.*"%>
<%  
    String dirPath = request.getParameter("dirPath");
    String fileName =request.getParameter("fileName"); 
    String serverName = request.getParameter("serverName");
    
    java.util.Date startTime = null;
    String fileContentsAsString = "";

     Ftp ft ;
    

     %><%@ include file="ServersAuthuntication.html"%>
<%
      
        ft.connect();
        ft.setDir(dirPath);  
        ft.deleteFile(fileName);
        ft.disconnect();
%>
File Deleted.....
