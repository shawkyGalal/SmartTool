<%@ page errorPage="errorPage.jsp" import="com.jscape.inet.ftp.*"%>
<%  String dirPath = request.getParameter("dirPath");
    String fileName =request.getParameter("fileName"); 
    String serverName = request.getParameter("serverName"); 
    if (serverName == null )
    {throw new Exception("You should define Server IP");}

    //--if server is Windows System 
    boolean isAIX = false;
    
    if ( (serverName.equals("10.16.17.51") ) ) 
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
    	String SSIuserName = request.getParameter("SSIuserName"); 
		java.sql.Connection repCon = (java.sql.Connection)session.getAttribute("repCon"); 
		if (repCon == null)
		{ response.sendRedirect("loginScreen.jsp" ); }
		
		Support.singleSignOnAccessor ssi = new Support.singleSignOnAccessor(repCon); 
		String password = ssi.getPassword(serverName, SSIuserName); 
		ft = new Ftp(serverName,SSIuserName , password); 
		
		if ( ! ft.isConnected()) 
     	{
	      ft.connect();
     	}
      ft.setDir(dirPath); 
      java.io.File localDir = new java.io.File("C:\\TEMP"); 
      ft.setLocalDir(localDir); 
      ft.download( fileName);
      ft.disconnect(); 
      out.println("<a href= File:\\\\localhost\\temp\\"+fileName +">Right Click and Select 'Save target As' To Download</a>" );

      out.println("<a href= File:\\\\"+java.net.InetAddress.getLocalHost().getHostAddress()+"\\temp\\"+fileName +">Right Click and Select 'Save target As' To Download</a>" );
      //response.sendRedirect("File\\\\10.16.18.181\\temp\\"+fileName); 
    }
%>