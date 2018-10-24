<%@ page errorPage="../errorPage.jsp"
	contentType="text/html;charset=UTF-8"
	import="Support.* , java.util.* , java.sql.* , java.io.*"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>

<%@page import="Support.TextFileLineEditor"%>

<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=UTF-8">
</head>
<p>
<% String dirPathVarName = "dirPath" ;

  	String userdir = (String)session.getAttribute(dirPathVarName) ;//"XLSUploading\\Data";
  	File userDirFullPath =  new File ( session.getServletContext().getRealPath("")+ File.separator +userdir ) ;
  	userDirFullPath.mkdirs() ; 
	oracle.jsp.webutil.fileaccess.HttpUploadBean upbean = new oracle.jsp.webutil.fileaccess.HttpUploadBean();
	upbean.setDestination(userdir);%> <%   
     //-----------1- Saving The client File at the Server--
     	//-----------1.1 Adjusting the fileaccess.properties file ----

	     String filePath = session.getServletContext().getRealPath("WEB-INF")+"\\"; 
	     TextFileLineEditor tfle = new TextFileLineEditor(filePath+"fileaccess_template.properties",filePath+"fileaccess.properties");
	     String[] oldV = {"$$fileaccess.basedir"};
	     String aa = Support.Misc.repalceAll(session.getServletContext().getRealPath("") , "\\" , "$$$$$");
	     aa = Support.Misc.repalceAll(aa ,  "$$$$$" , "\\\\");
	     String[] newV = {aa};
	     tfle.replace(oldV , newV , 0 , 200);
   		//-----------1.1 (END) Adjusting the fileaccess.properties file ----
     upbean.setBaseDir(application, request);
     upbean.upload(request);
     
	  java.sql.Connection  repCon = (java.sql.Connection)session.getAttribute("repCon");
	  if( repCon == null || repCon.isClosed())
	  { 
	    XMLConfigFileReader xcr = new XMLConfigFileReader( false);
	    repCon = xcr.reposatoryConn.generateConnection();
	    session.setAttribute("repCon",repCon);
	  }
	  
     java.util.Enumeration fileNames = upbean.getFileNames();
     String fileName = "";
     boolean noDBrecord = Support.SqlReader.getValueFromRequestOrSession( "NoDBRecord" , request, session).equalsIgnoreCase("Y"); //request.getParameter("NoDBRecord") != null && request.getParameter("NoDBRecord").toString().equalsIgnoreCase("Y") ; 
     while (fileNames.hasMoreElements())
     {
      fileName = fileNames.nextElement().toString();
      //---------Create a corresponding record in DB--------------------
      if (! noDBrecord) 
      {
      StringTokenizer st = new StringTokenizer(userdir , "/");
      String tableNamex = st.nextToken() ;
      tableNamex = st.nextToken() ;
      String keyValue = st.nextToken() ;
      
	  String createStmt = "Insert into Support.TABLE_ATTACHEMAT (table_name , key_name,key_vale,file_name,uploaded_to) " 
	  	+ " Values ('"+tableNamex.toUpperCase()+"' , 'RowId' , '"+keyValue+"', '"+fileName+"' , '"+userDirFullPath.getAbsolutePath()+"')";
	  repCon.createStatement().execute(createStmt);
      }
    //---------End of Create a corresponding record in DB -------------
    
     }
     if (! noDBrecord) 
     {
     out.println("File Uploaded Successfully to : " +userDirFullPath);
     response.sendRedirect("getClientFile.jsp");
     }
	%>


</html>
