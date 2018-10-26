<%@ page errorPage="errorPage.jsp"
	contentType="text/html;charset=UTF-8"%>
<%@ page import=" java.io.* , Support.Misc"%>
<html>
<head>
<title>Query File List</title>
</head>
<body BACKGROUND="Nccibgd2.gif" TEXT="blue" LINK="red">
<%
  java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
  if (con == null)
 {response.sendRedirect("loginScreen.jsp?comeFrom=index.jsp");}
  String loggedUser = Misc.getConnectionUserName(con);
  request.setCharacterEncoding("UTF-8");
  String List = request.getParameter("List");
  String relativeDir="sql";
  if (List == null || List.equals("My") )
  {
  relativeDir += "\\"+loggedUser;
  }
  else
  {
  relativeDir += "\\Standard";
  }
  
   char Htmlspace = 20;
  
  String fileName="";
    
    java.net.InetAddress localHost =  java.net.InetAddress.getLocalHost();
    String hostIP = localHost.getHostAddress();
    String absoultePath = "";
    if (hostIP.equals("130.1.2.76")) 
    {
      absoultePath = "C:\\Edge_Work\\Support\\public_html";   // Should be exactly as in the  WEB-INF\fileaccess.properties
    }
    if (hostIP.equals("130.1.2.30")) 
    {
      absoultePath = "c:\\ora9iasInfra\\j2ee\\OC4J_NCCI\\applications\\Support\\Support";   // Should be exactly as in the  WEB-INF\fileaccess.properties  
    }  

  
  if (request.getParameter("remove")!= null)
  {
    String filetoBeDeleted = request.getParameter("fileName");
    if (filetoBeDeleted.indexOf("Standard")!= -1 )
    {
      throw new Exception ("Standard Files Can not be Deleted");
    }
    File x = new File( absoultePath + "\\"+ filetoBeDeleted);
    x.delete();
  }

  

  String connectedUser = "os310" ;//Complaints.ComplaintSecurity.getUserFromConnection(con);
  int attachIDinDB = 0;


	if (request.getParameter("Submit")!= null)
   {  
      String attachDesc = request.getParameter("attachDesc");

       java.io.InputStream is = request.getInputStream();
       is = request.getInputStream();
      StringWriter sw = new StringWriter();
      String searchForAttachDesc = "Content-Disposition: form-data; name=\"attachDesc\"";
      String searchUPtoDummy = "Content-Disposition: form-data; name=\"DUMMY\"\r\n\r\ndummy"; //SIZE = 45 CHAR
      System.out.print(searchUPtoDummy);
      int i = 0;
      int counter = 0;
      boolean finshed = false;
      while(!finshed)
      {
       counter ++;
       if (counter >= 45 )
        {
          if (sw.toString().indexOf(searchUPtoDummy)>0)
            {
              finshed = true;
            }
        }
       sw.write(i);
       i =  is.read();
       }
       String requestData = sw.toString();
       int indexOfAttachDesc = requestData.indexOf(searchForAttachDesc);
       int indexOfDummy = requestData.indexOf(searchUPtoDummy);
       attachDesc = requestData.substring(indexOfAttachDesc + searchForAttachDesc.length()+4,indexOfDummy-44);

    java.util.Enumeration x = request.getParameterNames();
      
    oracle.jsp.webutil.fileaccess.HttpUploadBean  upbean = new oracle.jsp.webutil.fileaccess.HttpUploadBean();
    	try {
          //----Important --- Geeting The BasDir from the "WEB-INF\fileaccess.properties" file         
          //-----Which should Contain the following entry
          //fileaccess.basedir=E:\\ora9ias\\Apache\\Apache\\htdocs\\Complaints\\Complaints_attchments
          upbean.setBaseDir(application, request);
          upbean.setDestination(relativeDir);
          //upbean.setOverwrite(false);
          upbean.upload(request);
          
          java.util.Enumeration fileNames = upbean.getFileNames();
          fileName  = (fileNames.nextElement().toString()); //thisComplaint.replaceSingleQuteWithDouble
          }
     catch ( Exception e) 
     {throw new Exception("<p align='center' >Unable To Add The Selected File To The Complaint. "
									+" <br> File Already Exist Or"
									+" <br> File Size May be Larger than the the allowed. "
									+" <br> Try to Compact File Size Using (For Example) WinZip "
									+" <br> <a href ='javascript: history.back();' >Back To </a>");
     } 
          //---------Storing file name in the DB-----------
    //      attachIDinDB = thisComplaint.addFileSystemAttachment(fileName,attachDesc );
          //-----End of Storing file name in the DB----------                       

	}
 %>
<div align="center">
<p><a href="selectQueryFile.jsp?List=My">My Queries</a> <a
	href="selectQueryFile.jsp?List=Standard">Standard Query </a></p>
</div>
<p align="center">
<% 
   String userDir = relativeDir+"/";   // user part of  download directory
   oracle.jsp.webutil.fileaccess.HttpDownloadBean  dbean = new oracle.jsp.webutil.fileaccess.HttpDownloadBean();
   dbean.setSource(userDir);
   dbean.setBaseDir(application, request);   
   dbean.listFiles(request); 
   %>
</p>
<p>
<table width="225" border="0" align="center">
	<tr>
		<td width="124">&nbsp;</td>
		<td width="38">&nbsp;</td>
		<td width="49">
		<div align="center"></div>
		</td>
	</tr>
	<tr>
		<td><strong>File Name</strong></td>
		<td>Edit</td>
		<td>Delete</td>
	</tr>
	<%  java.util.Enumeration fileNames = dbean.getFileNames(); 
         while (fileNames.hasMoreElements()) 
         { 
            String name = (String)fileNames.nextElement();
            String displayName = name ;
            if (List == null || List.equals("My") )
              { displayName = displayName.substring(5+loggedUser.length());}
            else 
              {displayName = displayName.substring(13);}
            %>
	<tr>
		<td><a
			href='editAndExecute.jsp?fileName=<%= Support.Misc.repalceAll(name," ","%20")%>'
			target="mainFrame"><%= displayName %></a></td>
		<td><a
			href='fileEditor.jsp?fileName=<%= Support.Misc.repalceAll(name," ","%20")%>'
			target="mainFrame">Edit</a></td>
		<td><a
			href='selectQueryFile.jsp?List=<%=List%>&remove=Y&fileName=<%= Support.Misc.repalceAll(name," ","%20")%>'>
		X</a></td>
	</tr>
	<%    } %>
</table>



<p align="center"><a href="queryTree.jsp">Queries from DB</a></p>
</body>
</html>
