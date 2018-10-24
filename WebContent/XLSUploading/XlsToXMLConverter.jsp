<%@ page errorPage="../errorPage.jsp"
	contentType="text/html;charset=UTF-8"
	import="java.util.* , java.sql.* , java.io.*"%>
<?xml version="1.0" ?>
<% String userdir = "XLSUploading\\Data"; %>
<jsp:useBean id="upbean"
	class="oracle.jsp.webutil.fileaccess.HttpUploadBean">
	<jsp:setProperty name="upbean" property="destination"
		value="<%= userdir %>" />
</jsp:useBean>

<%   
     //-----------Saving The client File at the Server--
     upbean.setBaseDir(application, request);
     upbean.upload(request);
     java.util.Enumeration fileNames = upbean.getFileNames();
     String fileName = "";
     while (fileNames.hasMoreElements())
     {
      fileName = fileNames.nextElement().toString();
     }
     //----------------- Reading The XLS File  -----------
     File xlsFile = new File (session.getServletContext().getRealPath("")+"\\"+fileName);
     Support.XLSUploading.XLSUpload  xLSUpload = new Support.XLSUploading.XLSUpload(xlsFile, 0);
     Vector[] xlsData = xLSUpload.getXLSData();
     session.setAttribute("xLSUpload", xLSUpload);

  //--------Getting TagNames from The firt Ro----
  int noOfRoInHeader = 1;
  Vector[] tagNames = new Vector[noOfRoInHeader];
  tagNames[0] = new Vector();
  
    for (int rowCounter = 0 ; rowCounter<noOfRoInHeader ; rowCounter++)
    {
  
      for  (int columnCounter = 0 ; columnCounter<xlsData.length ; columnCounter++)
      {
      tagNames[rowCounter].addElement(xlsData[columnCounter].elementAt(rowCounter)); 
      
      }
    }  
        
  //----------Rendering Xls Data-----------------
  try
  {
    for (int rowCounter = noOfRoInHeader ; rowCounter<xlsData[0].size() ; rowCounter++)
    {
    %>
<DataRecord>
<%
        for  (int columnCounter = 0 ; columnCounter<xlsData.length ; columnCounter++)
      { 
        String thisTagName = tagNames[0].elementAt(columnCounter).toString();
        String tagValue = xlsData[columnCounter].elementAt(rowCounter).toString();
        
        out.println("\t <"+thisTagName+">"+tagValue+"</"+thisTagName+">");
      }
      %>
</DataRecord>
<%
    }
  }
  catch (Exception e) {throw new Support.MyException("Unable to Render Excel Sheet Data file "+xlsFile.toURL().toString()+ ", it may contain Graph. Due to "+ e.getMessage(),e,1,session.getServletContext().getRealPath(""));  }
  
  %>
