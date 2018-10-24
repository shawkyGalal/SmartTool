<%@page import="Support.Misc" %>
<%@ page errorPage="errorPage.jsp"%>
<% 
boolean isXML = (request.getParameter("isXML")== null )? true : false; 
boolean execAgainstRep = (request.getParameter("execAgainstRep") != null && request.getParameter("execAgainstRep").toString().equals("Y") )? true : false; 


String tableOwner = request.getParameter("tableOwner");
String tableName = request.getParameter("tableName");
String rowId = request.getParameter("rowId");
String columnName = request.getParameter("columnName");
String ukColumnName =  request.getParameter("ukColumnName");
String ukColumnValue =  request.getParameter("ukColumnValue");

boolean useUniqueKey = ( request.getParameter("useUniqueKey")!= null && request.getParameter("useUniqueKey").equals("Y"));


String columnValue = "";
java.util.Vector  columnValuePerLine = new java.util.Vector();
//( (useRowId)? "rowid = '" + rowId + "'" : "" )+
String whereClause = " where " +  ( (useUniqueKey)? ukColumnName + "='" + ukColumnValue + "'" :"rowid = '" + rowId + "'");
String query = "select " + columnName + " from "  + tableOwner +"."+tableName + whereClause;
 java.sql.Connection  con = null;
 if (execAgainstRep)
 {
 con = (java.sql.Connection)session.getAttribute("repCon");
 }
 else
 { con = (java.sql.Connection)session.getAttribute("con");}
 
  if (con == null)
 {
 session.setAttribute("comeFrom","oracleCLOBReader.jsp" + "?" + request.getQueryString());
 response.sendRedirect("loginScreen.jsp");
 }
 oracle.jdbc.OracleStatement stmt = (oracle.jdbc.OracleStatement)con.createStatement();
 oracle.jdbc.OracleResultSet rs;
 try{
 rs = (oracle.jdbc.OracleResultSet) stmt.executeQuery(query);
 }
 catch(Exception e) { throw new Exception ("Unable to Execute " + query + " <br>Due to " + e.getMessage()+ java.net.URLEncoder.encode("  abc+def") );
 }
 
 String columnType = rs.getMetaData().getColumnTypeName(1);
 rs.next();
  if (columnType.equals("CLOB"))
  {
   java.io.InputStream is = rs.getAsciiStream(1);
   if (is != null)  // if coulmn value is null
     {
      java.io.InputStreamReader isr = new java.io.InputStreamReader(is);
      
       java.io.BufferedReader br  = new java.io.BufferedReader(isr);
        String line = "";
        while ((line = br.readLine())!=null) 
        {
          columnValuePerLine.addElement(line); columnValue +=line +"\n";
        }
        is.close();
     }
  }   
   else if (columnType.equals("BLOB"))
      {
        try{ //-----try use the stringValue() method-------
        oracle.sql.BLOB blob = (oracle.sql.BLOB)rs.getBlob(1);
        columnValue = blob.stringValue();
        } 
        catch (Exception e)
        {
            java.io.InputStream is =  rs.getBinaryStream(1);
            int x = 0;
            while ((x = is.read()) != -1)
            {
              char c = (char)x;
              columnValue += c;
            }
            
            is.close();
        }
      }
      
      System.out.println("User : " + Misc.getConnectionUserName(con) + " Have been read CLOB/BLOB value of ("+columnName+") from "+tableName + "where rowid = " + rowId );
 
  boolean asXML = (request.getParameter("addXmlHeader")== null || request.getParameter("addXmlHeader").equals("Y") )? true : false; 
      if (asXML) 
           { 
             response.setContentType( "text/xml;charset=UTF-8");
            %><%=columnValue%>
<%
           }     
      else { 
            %>
<title>Oracle CLOB Reader</title>
<p align="center"><strong><font size="5">Oracle CLOB
Reader </font></strong></p>


<p align="center"><textarea name="Contents" cols="100" rows="20"
	wrap="OFF"><%=columnValue%></textarea></p>

<div align="center"><a
	href="oracleCLOBReader.jsp?addXmlHeader=Y&<%=request.getQueryString()%>"%>Browse
as XML</a> <%
                if (! columnName.equals("DOC_SENT"))
                {
                %> ... <a
	href="HttpPostClobMessage.jsp?addXmlHeader=Y&<%=request.getQueryString()%>"%>Test
this message by posting it to a testing environment </a> <%}%>
</div>
<%
           }    
     %>

