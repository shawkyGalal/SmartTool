<%@ page errorPage="/errorPage.jsp" import="com.jscape.inet.ftp.*"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>

<%@page import="java.net.URL"%>
<%@page import="java.net.URI"%><html>
<head>
<title>Directory Listing</title>

</head>

<body>
<%  String dirPath = request.getParameter("dirPath");
    String serverName = request.getParameter("serverName"); 
    String fileNameLike = (request.getParameter("fileNameLike")== null)? "" : request.getParameter("fileNameLike"); 
%>
Server :
<%=serverName%>
<br>
Directory :
<%=dirPath%>
<br>
File Name Like :
<%=fileNameLike%>

<table border=1>
	<tr>

		<td>Type</td>
		<td>File Name</td>
		<td>DownLoad</td>
		<td>Edit</td>
		<td>Size</td>
		<td>Date</td>
		<td>Owner</td>
		<td>Group</td>
		<td>Line</td>
	</tr>
	<%
       //--if server is Windows System 
    if (  serverName.equals("10.16.17.51") 
          || serverName.equals("10.16.18.130") 
          || serverName.equals("10.16.18.181")
          || serverName.equals("127.0.0.1")
          || serverName.equals("10.30.16.129") 
          || serverName.equals("10.1.7.15")
       	  || serverName.equals("127.0.0.1") 
       ) 
    {
    
      java.io.File  file = new java.io.File(new java.net.URI("file:////"+serverName+"/" + dirPath));
      java.io.File[] listFiles =  file.listFiles();
      if ( listFiles == null ) 
    	  { 
    	  	out.print("<br> No Files Found ");
    	  	return ;
    	  }
      for (int i = 0 ; i < listFiles.length ; i++)
      {
        String fileName =listFiles[i].getName();
        if (fileName.toUpperCase().indexOf(fileNameLike.toUpperCase()) == -1 )
        {
        continue;
        }

        java.util.Date fileDate = new java.util.Date((listFiles[i].lastModified()));
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String fileDateStr = sdf.format(fileDate);
        long fileSize =listFiles[i].length();
        %><tr>
		<td><%=i+1%></td>
		<%
        
        if (listFiles[i].isDirectory())
        {

        %><td>Dir</td>
		<%  
        %><td><a
			href="FtpDirListing.jsp?serverName=<%=serverName%>&dirPath=<%=dirPath%>/<%=(fileName)%>"><%=fileName%>
		</a></td>
		<%      
        %><td>Edit</td>
		<%
        }
        else
        {
          %><td>File</td>
			<!-- 
			<td><a target="FileReader "
				href="FtpReader.jsp?serverName=<%=serverName%>&dirPath=<%=dirPath%>&fileName=<%=fileName%>&fileDate=<%=fileDate%>&addXmlHeader=N"><%=fileName%>
				</a>
			</td>
			 -->
			 <% String encodedFileName = java.net.URLEncoder.encode(fileName , "utf-8") ; %>
		<td><a target = "FileDownloader" href = <%=dirPath%>/<%=encodedFileName%>><%=fileName%></a></td>
		<%  
          %><td><a target="CFlogReader "
			href="LogReaderByCfAPI.jsp?serverName=<%=serverName%>&dirPath=<%=dirPath%>&fileName=<%=fileName%>&fileDate=<%=fileDate%>">Open
		</a></td>
		<%  
          %><td><a target="FtpWriter "
			href="FtpWriter.jsp?serverName=<%=serverName%>&dirPath=<%=dirPath%>&fileName=<%=fileName%>">E</a></td>
		<%        
          %><td><a target="CflogWriter2DB "
			href="CflogWriter2DB.jsp?serverName=<%=serverName%>&dirPath=<%=dirPath%>&fileName=<%=fileName%>">To
		DB</a></td>
		<%                  
        }      
          %><td><%=fileSize%></td>
		<%  
          %><td><%=fileDateStr%></td>
		<%  
          %><td>xxx</td>
		<%      
          %><td>GGGG</td>
		<%
          %><td></td>
		<%
      
        %>
	</tr>
	<%

      }
    %>
</table>
<%
    }
    else //-------------If Server IS AIX have FTP Running 
    {
         Ftp ft;
          %><!-- Determine Uer name and password for the given server -->


<% //---New Method of 
String SSIuserName = request.getParameter("SSIuserName"); 
java.sql.Connection repCon = (java.sql.Connection)session.getAttribute("repCon"); 

Support.singleSignOnAccessor ssi = new Support.singleSignOnAccessor(repCon); 
String password = ssi.getPassword(serverName, SSIuserName); 
ft = new Ftp(serverName,SSIuserName , password); 


	if ( ! ft.isConnected()) 
     	{
     		ft.connect();
     	}
         
        %>Logged as : <%=ft.getUsername()%>
<%
        java.util.Enumeration  enu = ft.getDirListing(dirPath);
        
        while (enu.hasMoreElements())
        {
	        FtpFile ftpFile = (FtpFile ) enu.nextElement();
	    
	        String fileName = ftpFile.getFilename();
	        if (fileName.toUpperCase().indexOf(fileNameLike.toUpperCase()) == -1 )
	        {
	        continue;
	        }
	        long fileSize = ftpFile.getFilesize();
	        String filedate = ftpFile.getDate();
	        //ftpFile.getLine()
        
	        %><tr>
			<%
		        if (ftpFile.isDirectory())
		        {
			        %><td>Dir</td>
					<%  
				        %><td><a
						href="FtpDirListing.jsp?SSIuserName=<%=SSIuserName%>&serverName=<%=serverName%>&dirPath=<%=dirPath%>/<%=fileName%>"><%=fileName%>
					</a></td>
					<%      
		        }
		        else
		        {
					          %><td>File</td>
						<% 
			                    
			          if (fileName.toLowerCase().indexOf(".log") != -1) // if it is a log file
			          {
			            %><td><a target="File Reader "
					href="FtpReader.jsp?SSIuserName=<%=SSIuserName%>&serverName=<%=serverName%>&dirPath=<%=dirPath%>&fileName=<%=fileName%>&fileDate=<%=filedate%>&addXmlHeader=N"><%=fileName%>
				</a></td>
				<%  
			            %><td><a target="logReader "
					href="LogReaderByCfAPI.jsp?SSIuserName=<%=SSIuserName%>&serverName=<%=serverName%>&dirPath=<%=dirPath%>&fileName=<%=fileName%>&fileDate=<%=filedate%>">CF
				Log Reader </a></td>
				<%
			            %><td><a target="CflogWriter2DB "
					href="CflogWriter2DB.jsp?SSIuserName=<%=SSIuserName%>&serverName=<%=serverName%>&dirPath=<%=dirPath%>&fileName=<%=fileName%>">To
				DB</a></td>
				<%                              
			          }
			          else  // not a log file , any other file 
			          {
			           %><td><a target="FileReader "
					href="FileReader.jsp?SSIuserName=<%=SSIuserName%>&addXmlHeader=N&serverName=<%=serverName%>&dirPath=<%=dirPath%>&fileName=<%=fileName%>&fileDate=<%=filedate%>"><%=fileName%>
				</a></td>
				<%  
			          }
			          %><td><a target="FTP Download "
					href="FtpDownLoad.jsp?SSIuserName=<%=SSIuserName%>&serverName=<%=serverName%>&dirPath=<%=dirPath%>&fileName=<%=fileName%>">DownLoad</a></td>
				<% 
			        }      
			          %><td><%=fileSize%></td>
				<%  
			          %><td><%=filedate%></td>
				<%  
			          %><td><%=ftpFile.getOwner()%></td>
				<%      
			          %><td><%=ftpFile.getGroup()%></td>
				<%
			          %><td><%=ftpFile.getLine()%></td>
				<%
			          %><td><a target="Read Header "
					href="FileTopLinesReader.jsp?charCount=1000&SSIuserName=<%=SSIuserName%>&serverName=<%=serverName%>&dirPath=<%=dirPath%>&fileName=<%=fileName%>">ReadHeader</a></td>
				<%
			      
			        %>
			</tr>
			<%
        }
        out.println("</table>");
           
        ft.disconnect();
      }
%>