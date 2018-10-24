<%@ page contentType="text/html;charset=windows-1252"
	errorPage="errorPage.jsp"
	import="java.net.* ,java.io.InputStream,   com.jscape.inet.ftp.*"%>
<%  
    String dirPath = request.getParameter("dirPath");
    String fileName =request.getParameter("fileName"); 
    String serverName = request.getParameter("serverName"); 
    java.util.Date startTime = null;
    //---------if server is a Windows Server
    String fileContentsAsString = "";
if (  serverName.equals("10.16.17.52") 
      || serverName.equals("10.16.17.51")
      || serverName.equals("10.16.18.130") 
      || serverName.equals("10.16.18.181") 
    ) 
    { 
      String fileURLStr = "file:////"+serverName+"/" + dirPath + "/" + fileName ;
      java.io.File  file = new java.io.File(new java.net.URI(fileURLStr ));
      java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader (file));
     
      try
      {
        //-Reading file Contents
        String line = "";
        while(( line = br.readLine() ) != null)
        {
          fileContentsAsString += line + "\n";
        }
        br.close();
      }
      catch (Exception e) { throw new Exception ("Unable to read " + fileURLStr + " Due to " + e.getMessage());}
    }
    //---------Server Is AIX Server-------------
    else
    {
     Ftp ft ;
     %><%@ include file="ServersAuthuntication.html"%>
<%
        
        ft.connect();
        ft.setDir(dirPath);  
        startTime =  new java.util.Date();
        java.io.ByteArrayOutputStream bout = new java.io.ByteArrayOutputStream(); 
        ft.download(bout,fileName ); 
        ft.disconnect();
        
        fileContentsAsString = bout.toString();
        
        boolean addXmlHeader = (request.getParameter("addXmlHeader")== null || request.getParameter("addXmlHeader").equals("Y") )? true : false; 
        if (addXmlHeader) 
           { 
             response.setContentType( "text/xml;charset=UTF-8");
             //fileContentsAsString = "<?xml version=\"1.0\" ?>\n" + fileContentsAsString; 
            
            %><%=fileContentsAsString %>
<%
           
           }     
        else { 
            %>

<p align="center"><strong><font size="5">FILE READER
</font></strong></p>
<p><strong> Your Are Logged as: <%=SSIuserName%>
<p><strong>Server :</strong> <%=serverName%> <strong>File
Name:</strong> <%=dirPath+"/"+fileName%></p>

<p align="center"><textarea name="Contents" cols="100" rows="20"
	wrap="OFF">
                     <%=fileContentsAsString %>
                  </textarea></p>

<div align="center"><a
	href="FileReader.jsp?addXmlHeader=Y&<%=request.getQueryString()%>"%>Browse
as XML</a></div>
<%
           }
    }
    %>