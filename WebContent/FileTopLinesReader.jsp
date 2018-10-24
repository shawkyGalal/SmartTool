<%@ page contentType="text/html;charset=windows-1252"
	errorPage="errorPage.jsp"
	import="java.net.* ,java.io.InputStream,   com.jscape.inet.ftp.*"%>
<%  
    String dirPath = request.getParameter("dirPath");
    String fileName =request.getParameter("fileName"); 
    String serverName = request.getParameter("serverName");
    //int lineCount = Integer.parseInt(request.getParameter("lineCount"));
    int charCount = Integer.parseInt(request.getParameter("charCount"));
    
    java.util.Date startTime = null;
    //---------if server is a Windows Server
    String fileContentsAsString = "";
if (  serverName.equals("10.16.17.51")
      || serverName.equals("10.16.18.130") 
      || serverName.equals("10.16.18.181") 
    ) 
    { 
      String fileURLStr = "file:////"+serverName+"/" + dirPath + "/" + fileName ;
      java.io.File  file = new java.io.File(new java.net.URI(fileURLStr ));
      java.io.FileReader fr = new java.io.FileReader (file);
      java.io.BufferedReader br = new java.io.BufferedReader(fr);
     
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
        java.io.File myFile = ft.download(fileName);
        long fileSize = myFile.length();

        java.io.FileReader fr = new java.io.FileReader (myFile);        
   
        //java.io.BufferedReader bro = new java.io.BufferedReader(fr);
        
      
        char charFromFile = (char)fr.read();
        
        //String stringFromFile = bro.readLine( );
        %>
<title>File Top Lines Reader</title>
<p align="center"><strong><font size="5">First <%=charCount%>
Char's FILE READER </font></strong></p>
<p><strong>Server :</strong> <%=serverName%> <strong>File
Name:</strong> <%=dirPath+"/"+fileName%> <br>
<strong> File Size = </strong> <%=fileSize%> <strong>Estimated
number of BillRec or PmtRec = </strong><%=fileSize/500%></p>
<textarea name="Contents" cols="100" rows="20" wrap="OFF">
        <%
           for (int i = 0 ; i<=charCount ; i++)
             {
               out.print(charFromFile ); 
               charFromFile = (char)fr.read();
             }

          %> 
        </textarea>
<div align="center"></div>
<%        
        fr.close();
        //bro.close( );
       
        ft.disconnect();
        
        
    }
    %>

