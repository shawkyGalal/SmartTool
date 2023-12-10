<%@ page contentType="text/html;charset=windows-1252"
	errorPage="errorPage.jsp"
	import="java.net.* ,java.io.InputStream,   com.jscape.inet.ftp.* , Support.Misc"%>

<%  String dirPath = request.getParameter("dirPath");
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
        startTime =  new java.util.Date();
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
        String userName = "wasadm";
        String password = "gram30";
        try{
        ft = new Ftp(serverName, userName, password );
        ft.connect();
        }
        catch (Exception e) 
        {
          try{
               userName = "aixops"; //aixops
               password = "aixops"; 
               ft = new Ftp(serverName, userName, password );
               ft.connect();
               }
               catch (Exception ex)
               {
                   userName = "bgadmin"; 
                   password = "bgadmin"; 
                   ft = new Ftp(serverName, userName, password );
                   ft.connect();
               }
        }
    
        ft.setDir(dirPath);  //--"/sfs/bank/SAMBSARI/download/"
        // create an ByteArrayOutputStream to store data 
        startTime =  new java.util.Date();
        java.io.ByteArrayOutputStream bout = new java.io.ByteArrayOutputStream(); 
        String xx="";
        //java.io.File file =  ft.download(xx, fileName ); 
        ft.download(bout,fileName ); 
        ft.disconnect();
        
        fileContentsAsString = bout.toString();
    }
    int recordsCount = 0;
    String recordDelimiter ="\n";// "V[";
    //---------------if it is a log file -------------

    if (fileName.indexOf(".log") != -1 )
    {
  
        java.util.Vector cellNames =    new java.util.Vector();
        java.util.Vector cellNamesDesc =new java.util.Vector(); 
        //---can not be moved -----
        cellNames.add("] CC[");     cellNamesDesc.add("Type");
        cellNames.add("] NL[");      cellNamesDesc.add( "Level");
        //--end of can not be moved----- 
        
        cellNames.add("] TS[");     cellNamesDesc.add("Time Stamp");
        cellNames.add("] MC[");      cellNamesDesc.add("Msg Code");
        cellNames.add("] AC[");      cellNamesDesc.add("Application");
        cellNames.add("] HA[");      cellNamesDesc.add("Server");
        cellNames.add("] EN[");      cellNamesDesc.add("Exception");
        cellNames.add("] SM[");      cellNamesDesc.add("Method");
        cellNames.add("] RB[");      cellNamesDesc.add("RB");
        cellNames.add("] SL[");      cellNamesDesc.add("LINE");
        cellNames.add("] SC[");      cellNamesDesc.add("CLASS");
        cellNames.add("] MT[");      cellNamesDesc.add( "Message1");
        cellNames.add("] MA[");      cellNamesDesc.add( "Message2");
        boolean includeStack = (request.getParameter("includeStack")!= null && request.getParameter("includeStack").toString().equals("ON") );
        if (includeStack)
        {
          cellNames.add("] ST[");      cellNamesDesc.add("Stack Trace");
        }

        boolean nowrap = (request.getParameter("submit") == null ) || ( request.getParameter("nowrap")!= null  && request.getParameter("nowrap").toString().equals("ON"));
        String columnValue  = request.getParameter("columnValue");
        String filterColumnIndex = request.getParameter("filterColumnIndex");
        String fileDate = request.getParameter("fileDate");
        %>
<html>
<title>CF Log Reader- <%=fileName%>@<%=serverName%>/<%=dirPath%></title>
<body>
<form method="POST" action="LogReader.jsp">
<p><input type="hidden" name="dirPath" size="20"
	value="<%=dirPath %>"> <input type="hidden" name="fileName"
	size="20" value="<%=fileName%>"> <input type="hidden"
	name="serverName" size="20" value="<%=serverName%>"> <input
	type="hidden" name="fileDate" size="20" value="<%=fileDate%>">
<input type="checkbox" name="includeStack"
	<%=(includeStack)? "checked" : "" %> value="ON">Include Stack
Trace&nbsp; <input type="checkbox" name="nowrap"
	<%=(nowrap)? "checked" : "" %> value="ON">No Text Wrap &nbsp
&nbsp &nbsp &nbsp; Display only <select size="1"
	name="filterColumnIndex">
	<% for (int i =0 ; i<cellNamesDesc.size() ;i++) {
                      boolean selected = (filterColumnIndex != null ) && (Integer.parseInt(filterColumnIndex) == i);
                    %><option <%= (selected)? "selected" : "" %>
		value=<%=i%>><%=cellNamesDesc.elementAt(i).toString()%></option>
	<br>
	<%
                    }
                  %>
</select> With Value Contains <input type="Text" name="columnValue"
	value='<%=(columnValue != null)? columnValue.toString() : "" %>'>
<input type="submit" value="Submit" name="submit"></p>
</form>

<b>Server</b>
:
<%=serverName%>
&nbsp &nbsp &nbsp
<b>File Name :</b>
<%=dirPath+"/" + fileName%>
&nbsp &nbsp &nbsp
<b>File Date :</b>
<%=request.getParameter("fileDate")%>

<table border=1>
	<tr>
		<%
            
              for (int i=0 ; i< cellNames.size() ; i++)
              {
               %>
		<td nowrap=true><%=cellNamesDesc.elementAt(i)%></td>
		<%
              }
              %>
	</tr>
	<%
    
            int recordStartIndex = fileContentsAsString.indexOf(recordDelimiter);
            int recordEndIndex ;
           
            while (recordStartIndex != -1 )
            {
              recordStartIndex = recordStartIndex + recordDelimiter.length();
              recordEndIndex = fileContentsAsString.indexOf(recordDelimiter, recordStartIndex);
              if (recordEndIndex == -1)
              {
                break;
              }
                  String recordAsString = fileContentsAsString.substring(recordStartIndex , recordEndIndex);          
                    
                  java.util.Vector cellValues = new java.util.Vector();
                  String cellValue="";
                  int cellValueStartIndex;
                  int cellValueEndIndex;
            
                  
                  //-----------Filling the cellValues Vector----------- 
                  for (int i=0 ; i< cellNames.size() ; i++)
                  {
                    cellValueStartIndex= recordAsString.indexOf(cellNames.elementAt(i).toString());
                    if (cellValueStartIndex != -1 )
                      {
                      cellValueStartIndex = cellValueStartIndex + cellNames.elementAt(i).toString().length() ;
                      cellValueEndIndex = recordAsString.indexOf("] " ,cellValueStartIndex );
                      if (cellValueEndIndex == -1)
                      {
                       break;
                      }
                      cellValue = recordAsString.substring(cellValueStartIndex , cellValueEndIndex);
                      cellValue = Support.Misc.repalceAll(cellValue , "<" ,"&lt");
                      cellValue = Support.Misc.repalceAll(cellValue , ">" ,"&gt");
                      }
                      try{
                      if (cellNames.elementAt(i).toString().equals("] TS[")) //-----Time Stamp
                      {
                       java.util.Date ts = new java.util.Date( Long.parseLong(cellValue)/1000) ;
                       java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                       cellValue= sdf.format(ts);
                      }
                      }
                      catch (Exception e) {}
                      
                      cellValues.add(cellValue);
                    }
                    //-------------rendering a record-------------
                    int level = 0;
                    int type =0; 
                    level = Integer.parseInt(cellValues.elementAt(1).toString());
                    type = Integer.parseInt(cellValues.elementAt(0).toString());
    
                    //int filterColumnIndex = Integer.parseInt(request.getParameter("filterColumnIndex").toString());
    
                    boolean renderThisRecord = true;
                    if (filterColumnIndex != null  && columnValue != null )
                    { 
                      int filterColumnIndexAsInt = Integer.parseInt(filterColumnIndex.toString());
                      String thisColumnValue = cellValues.elementAt(filterColumnIndexAsInt).toString();
                      String requestedColumnValue = columnValue.toString();
                      renderThisRecord = (thisColumnValue.indexOf(requestedColumnValue)!=-1 || requestedColumnValue.equals("") ) ;}
                    if (renderThisRecord )
                    {
                      recordsCount++;
                      %>
	<tr>
		<%
                      for (int i=0 ; i< cellValues.size() ; i++)
                      {
                            //----------Rendering Type of log with coloring -----
                            if (i == 1 )
                            {
                                 if( type == 2)
                                 {
                                    if (level == 0) {cellValue = "<p><span style='background-color: #FF0000'> Error </span></p>";}
                                    if (level == 1) { cellValue = "<p><span style='background-color: #FFFF00'> Warn</span></p>";}
              
                                 }
                                 else if( type == 16)
                                 {
                                    if (level == 0) {cellValue = "<p><span style='background-color: #FF0000'>Excep</span></p>";}
                                    if (level == 1) {cellValue = "<p><span style='background-color: #FFFF00'>Warn</span></p>";}
                                 }
      
                                 else if( type == 18)
                                 {
                                  if (level == 1) { cellValue = "<p><span style='background-color: #FFFF00'>Warn</span></p>" ; }
                                 }
                                 else if( type == 20)
                                 {
                                    if (level == 5) {cellValue = "<p><span style='background-color: #FFFF00'>Warn</span></p>";}
                                 }
                                 else if( type == 83)
                                 {
                                  cellValue = "<p><font color= #FFFFFF ><span style='background-color: green'>Info</span></font></p>" ;
                                 }
                                 else if( type == 85)
                                 {
                                  if (level == 0) {cellValue = "<p><span style='background-color: #FF0000'> Error </span></p>";}
                                 }
    
    
                                 else {cellValue = cellValues.elementAt(i).toString() ;}
                            }
                            //--------End of Rendering Type of log with coloring -----
                            else {cellValue = cellValues.elementAt(i).toString() ;}
                            %>
		<td <%= (nowrap)? "nowrap = true" : ""%>><%=cellValue%></td>
		<%
                        }
                        %>
	</tr>
	<%
                      }
                   
                recordStartIndex = recordEndIndex;
              }
              java.util.Date endTime =  new java.util.Date();
              
            %>
</table>
Record Count =
<%=recordsCount%>
Read Time =
<%=endTime.getTime() - startTime.getTime()%>
ms
</body>
</html>
<%
        String user = "(Not Logged)";
       try{ 
           java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
           user = Misc.getConnectionUserName(con);
          }
      catch (Exception e) {}
       System.out.println("User :" + user+ " Have Read Log File " +serverName + "/" + dirPath + "/" + fileName ); 
    }
   
    else
    {
    %><%=fileContentsAsString %>
<%
    }
    %>