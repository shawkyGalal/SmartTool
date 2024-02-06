<%@ page errorPage="errorPage.jsp" import="com.jscape.inet.ftp.*"%>
<%  String dirPath = request.getParameter("dirPath");
    String fileName =request.getParameter("fileName"); 
    String serverName = request.getParameter("serverName"); 
    if (serverName == null )
    {throw new Exception("You should define Server IP");}

    //--if server is Windows System 
    boolean isAIX = false;
    
    if ((serverName.equals("10.16.17.51") ) )  // serverName.equals("10.16.17.52") ||
    {
    //-----------Redirct 
    response.sendRedirect("file://"+serverName+"/" + dirPath + "/" + fileName );
    isAIX = false;
    }
  
    else 
    {
     isAIX = true;
    }
    
    if (isAIX)
    {
     Ftp ft ; 
     %><%@ include file="ServersAuthuntication.html"%>
<%
      ft.connect();
      ft.setDir(dirPath);  
      // create an ByteArrayOutputStream to store data 
      java.io.ByteArrayOutputStream bout = new java.io.ByteArrayOutputStream(); 
      ft.download(bout,fileName );     
      ft.disconnect();
      String fileContentsAsString = bout.toString();
      
     boolean asXML = (request.getParameter("addXmlHeader")== null || request.getParameter("addXmlHeader").equals("Y") )? true : false; 
      if (asXML) 
           { 
             response.setContentType( "text/xml;charset=UTF-8");
                       
            %><%=fileContentsAsString %>
<%
           
           }     
      else { 
            %>
<p align="center"><strong><font size="5">FTP FILE
READER </font></strong></p>
<p><strong>Server :</strong> <%=serverName%> <strong>File
Name:</strong> <%=dirPath+fileName%></p>

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