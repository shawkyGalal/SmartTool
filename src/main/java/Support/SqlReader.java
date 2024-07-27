
package Support;
import Support.mail.*;
import java.io.*;
import java.math.BigDecimal;
import java.net.*;
import java.util.*;
import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sideinternational.web.swaf.SWAF;
import com.smartValue.database.ApplicationContext;
import com.smartValue.database.DataSet;
import com.smartValue.database.DbServices;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.Pool;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.SysParams;
import com.smartValue.database.map.TableNotificationRule;
import com.smartValue.database.map.services.ModuleServicesContainer;
import com.smartValue.listeners.ApplicationContextListener;
import com.smartValue.support.map.LuExecutables;
import com.smartValue.support.map.LuQueries;
import com.smartValue.support.map.QueryNotifListParams;
import com.smartValue.support.map.QueryNotifier;
import com.smartValue.support.map.LuQueryDetails ;

import Support.mail.MailSender;
import Support.HTML.*;

public class SqlReader 
{
private String tableName = "LU_QUERIES" ; 
private String[] queryStmts;
private String[] titles;
String appURL = "http://10.16.18.130:8988/Support";
public int noOfQueries= 0;
String queryId= "";
int[] queryResultCount ;
Connection repCon ;
private String queryDelimiter= "\\"; //--------a delimiter between queries 
private static String boundVarInit = "$$"; //----------- bound varialbes initial special letter
private String notifyTo ;
public String getNotifyTo() {
	return notifyTo;
}

public void setNotifyTo(String notifyTo) {
	this.notifyTo = notifyTo;
}

public String active = "F";
private boolean logExecution = false;
private int rownum = 0;
public final static String SELECTED_ORA_ROWID = "_SelectedOraRowId" ;
public final static String SELECTED_ROW_NUM = "_SelectedRowNum" ;
public final static String QUERY_INDEX_TO_FILTER_BY_ROWID = "_queryIndexToFilterByRowId"; 
public static String dateFormate; 
private ArrayList<String> queryParams = new ArrayList<String>() ;
private Hashtable<String , ArrayList<String>> refrencedQueryIds = new Hashtable<String , ArrayList<String>>() ;
private String queryADesc = null ;
private String queryEDesc = null ;
private String executableAlertMessage = null;
private SecUsrDta loggedUser ; 
private String selectedEnvName ;
private javax.servlet.http.HttpSession userSession ;
private HttpServletRequest userRequest ; 

private String queryHyperLinkTitle ; 
public SecUsrDta getLoggedUser() {
	return loggedUser;
}
public  void setLoggedUser(SecUsrDta m_logged_user) {
	 loggedUser = m_logged_user;
}

public static String getDateFormate() {
	return dateFormate;
}

public static void setDateFormate(String dateFormate) {
	SqlReader.dateFormate = dateFormate;
}

public int getRownum()
{
    return rownum;
}

public boolean isLogExecution() {
	return logExecution;
}

/**
 * Purpose : To Read an SQL File Containing multiple queries Up to 4 seperated with a tile line starting with '/--'
 * each query has a title in that line 
 * Also it replaces all parmNames found in the query with ParmValues supplied in the constructor.
 * File content Example :
 * \---------Query No 1--------
 * select * from abc where xyz = %p
 * \---------Query 2 --------- 
 * select * from def
 * 
*/ 
public static String getboundVarInit()
{
  return boundVarInit;
}
private Hashtable<String, String> getParams(HttpSession session , HttpServletRequest request) throws Exception
{
	  Hashtable<String , String> paramsHashTable = new Hashtable<String , String>();
		
	  java.util.Vector<String> sqlBoundNamesFromSession = (Vector)session.getAttribute("sqlBoundNames");
	  java.util.Vector<String> sqlBoundValuesFromSession = (Vector)session.getAttribute("sqlBoundValues");
	  
	  //--Inserting the request Parameters and their values at the top of the sqlBoundNames & sqlBoundValues respecively--
	   
	  java.util.Enumeration requestEnum =  request.getParameterNames();
	  
	  while ( requestEnum.hasMoreElements())
	  {
	   String parmName = requestEnum.nextElement().toString();
	   if ( parmName.equals("id") || parmName.equals("tableName") ) {}
	   else
	   {
		   String paramValue = request.getParameter(parmName) ; 
		   paramsHashTable.put("$$"+ parmName, paramValue);
		   // replace the equivalent in the session if exist
		   if (sqlBoundNamesFromSession != null && sqlBoundNamesFromSession.contains("$$"+ parmName))
		   {
			   int index = sqlBoundNamesFromSession.indexOf("$$"+ parmName) ; 
			   sqlBoundValuesFromSession.set(index, paramValue); 
		   }
	   }
	  }
	  if (sqlBoundNamesFromSession != null)
	  {
	   for (int i=0 ; i< sqlBoundNamesFromSession.size() ; i++)
	   {
		   // if parameter already exist sent in the request override the one in the session 
		   if (! paramsHashTable.containsKey(sqlBoundNamesFromSession.elementAt(i)))
		   {
			   paramsHashTable.put( sqlBoundNamesFromSession.elementAt(i), sqlBoundValuesFromSession.elementAt(i));
		   }
	   }
	  }
	  
	  //----------Adding also sqlBONVars specially for the selected connection----

	  Support.ConnParms selectedConnParms = (Support.ConnParms)session.getAttribute(Misc.selectedConnParmsSessionKey);
	  this.setSelectedEnvName(selectedConnParms.name); 
	   if (selectedConnParms == null) 
	   {
	       Support.XMLConfigFileReader supportConfig =  Misc.getXMLConfigFileReader(false ) ;  
	       java.util.Vector conParms  = supportConfig.connParms ;
	       selectedConnParms = (Support.ConnParms)conParms.elementAt(0) ;
	   }
	   
	  Vector<SqlBindVar> sqlBVs = selectedConnParms.getSqlBindVars();
	  for (int k = 0 ; k< sqlBVs.size() ; k++)
	   {
	     Support.SqlBindVar currentSqlBV = sqlBVs.elementAt(k);
	     paramsHashTable.put("$$"+currentSqlBV.bindVarName , currentSqlBV.bindVarValue);
	     
	   }
	  //System.out.println(paramsHashTable.toString());
	  return paramsHashTable;
}
public String getResultAsXML(int seq , Connection con) throws Exception
{	
	  StringBuffer  xmlValue = new StringBuffer();
	  ResultSetMetaData rsmd = null;
	  int columnCount = 0;
	  String[] columnTypes = null;
	  String[] columnNames = null;
  	  ResultSet rs = this.getQueryResultSet(seq, con);
  	  Statement stmt = rs.getStatement();
	  rsmd = rs.getMetaData();
	  columnCount = rsmd.getColumnCount();
  	  columnTypes = new String[columnCount+1];
	  columnNames = new String[columnCount+1];


    //-------------Getting Column Names ------------------
    for (int count = 1 ; count<=columnCount ; count++ )
    { 
      columnTypes[count] = rsmd.getColumnTypeName(count);
      columnNames[count] =  rsmd.getColumnName(count);
    }
    //-------rendering Data--------------------
    xmlValue.append("<allData>");
    int rownum = -1;
    while (rs.next())
    { rownum++;
      String columnValue = "";
      xmlValue.append("\t<Data rowNo ='"+rownum+"'>");          
      for (int count = 1 ; count<=columnCount ; count++ )
      { 
        columnValue = "";
        //--------------------Getting The Column Value---------------
        try
        {
           //--------------If Column Type is CLOB 0r LONG -----
            columnValue= rs.getString(count);
            columnValue = (columnValue == null )? "": columnValue;
        }
        catch (Exception e) {  rs.close();   stmt.close(); throw e;}
        //--------------------Rendering The Column Value---------------
        { 
        	xmlValue.append("\t\t<"+columnNames[count]+">" + columnValue + "</" + columnNames[count] + ">");
        }
        //------------------End of Rendering The Column Value---------------
      }
      xmlValue.append("\t</Data>");
    }
    xmlValue.append("</allData>");
  rs.close();
  stmt.close();
	//////////////
	return xmlValue.toString();
	
}

private ResultSet getQueryResultSet(int seq , Connection con) throws SQLException
{
  Statement stmt = con.createStatement();
  ResultSet rs = null;
  try{
  rs = stmt.executeQuery( this.queryStmts[seq] );
  }
  catch (SQLException sqle)
  {  stmt.close(); 
      throw new SQLException("Unable to Execute the following Query <Br>" + this.queryStmts[seq]+ "\n" + " <Br> Due To the Following SQL Error " + sqle.getMessage());
  }
 return rs;
}
public static String getUrlContent(String url ) throws Exception
{
		Support.DownLoad.URLDownLoader urld = new Support.DownLoad.URLDownLoader() ;
		StringBuffer result =  urld.getURLContents(url, "" , 1234 , "" , "");
		return result.toString(); 
}

public String getQueryResultAsJSON(int seq , Connection con) throws SQLException
{
	  StringBuffer queryResult= new StringBuffer("");
	  ResultSet rs = null;
	  ResultSetMetaData rsmd = null;
	  int columnCount = 0;
	  rs = this.getQueryResultSet(seq, con);
	  rsmd = rs.getMetaData();
	  Statement stmt = rs.getStatement();
	  columnCount = rsmd.getColumnCount();
	  String key ="" ; 
	  String value = "" ; 
	  int rowCount = -1 ;
	  queryResult.append("[") ; 
	  while (rs.next())
      { rowCount++ ; 
        queryResult.append(((rowCount>0)? "," : "") +  "{") ; 
      	for (int i =1 ; i<= columnCount ; i++)
      	{
      		key = rsmd.getColumnName(i) ;
      		if (key.equals("ROWID")){continue ; } // Do Not Include Oracle ROWID
      		value = Misc.repalceAll(rs.getString(key), "\"", "'");
      		queryResult.append(((i!=1)? ", " : "" ) + "\"" +key+ "\"" +":\"" + value + "\"") ;
      	}
      	queryResult.append("}\n") ; 
      }
	  queryResult.append("]") ;
	  rs.close(); 
	  stmt.close(); 
	  return queryResult.toString() ; 
}

/**
 * This method execute query no seq using the given db connection and return the result in HTML table formated String
 */
public String getQueryResultForEmail(int seq , Connection con) throws SQLException
{
  String queryResult= "";
  ResultSet rs = null;
  ResultSetMetaData rsmd = null;
  int columnCount = 0;
  String[] columnTypes = null;
  String[] columnNames = null;
  java.math.BigDecimal[] columnSums = null;
  java.util.Date startDate = new java.util.Date();
  rs = this.getQueryResultSet(seq, con);
  Statement stmt = rs.getStatement();

  rsmd = rs.getMetaData();
  columnCount = rsmd.getColumnCount();
  columnTypes = new String[columnCount+1];
  columnNames = new String[columnCount+1];
  columnSums = new java.math.BigDecimal[columnCount+1];
  
  for (int count = 1 ; count< columnCount+1 ; count++ )
   { 
     columnSums[count] = new java.math.BigDecimal("0");
   }
  
  //queryResult += " <strong>" + this.titles[seq] + "</strong>";
  	   String displayDir = (loggedUser!=null )? loggedUser.getDisplayDirection() : "ltr" ; 
       queryResult += "<div dir='"+displayDir+"' align='center' > <table border=1 dir='"+displayDir+"' >";
       queryResult += "\n" + "<tr>";
      
        boolean rowIdFound = false;
        String tableName = "";
        String rowIdValue = "";
        int rowIdIndex = 0 ;
      
        //----------Check if Oracle RowID included in the Query----------
        while (!rowIdFound &&  rowIdIndex < columnCount)
        {
          rowIdIndex++;
          rowIdFound = (rsmd.getColumnName(rowIdIndex).toString().toUpperCase().equals("ROWID") )? true:false;
        }
        
              if (rowIdFound) //----------If its Found .....
        {
          //rowIdIndex = counter;
          //---------getting Table Name for the given rowid -----------
          tableName = rsmd.getTableName(rowIdIndex);  //-----------Somtimes Failes to get the table Name ?? ??
        
          if (tableName.equals(""))                   //-----------If it Failed....----------
          {
            try
            {
              int tableNameStartIndex = queryStmts[seq].toLowerCase().indexOf(" from ")+ " from ".length();
              int tableNameEndIndex = queryStmts[seq].toLowerCase().indexOf(" where ");
              tableName = queryStmts[seq].substring(tableNameStartIndex ,tableNameEndIndex );
          
              boolean isMultipleTables = (tableName.indexOf(",") == -1 )? false : true;
              if (isMultipleTables)
              { 
                tableName = tableName.substring(0, tableName.indexOf(",") ); 
              }
            }
            catch (Exception e) {throw new SQLException ("Unable to Extract Table Name ");}
          }
        }

        //-------------Rendering Header Data------------------
        if (rowIdFound){
        queryResult += "\n" + "<td><strong> Edit </strong></td>";
        }
        
                  
        for (int count = 1 ; count<=columnCount ; count++ )
        { 
          //----Do not show rowid column
          if (rowIdFound && count == rowIdIndex)
          { continue; }
          //---End of Do not show rowid column
          
          columnTypes[count] = rsmd.getColumnTypeName(count);
          columnNames[count] =  rsmd.getColumnName(count);
          queryResult += "\n" + "<td bgcolor=#0099CC nowrap= true > ";
          queryResult += "\n" + "<strong> "+columnNames[count]+"</strong>  </td>";
        }
          queryResult += "\n" + "</tr>";


        //-------rendering Data--------------------
        int rownum = -1;
        String databaseName = con.getMetaData().getDatabaseProductName();
        boolean evenRow = true;
        while (rs.next())
        { rownum++;
          evenRow = !evenRow;
          queryResult += "\n" + "<tr"+ ( (evenRow)? " bgcolor=#F1F4F8 ":" bgcolor=#FFFFCC " ) +">";
          
          if (rowIdFound) //----------if rowid found then  render an edit cell at the begining 
          {
            rowIdValue = rs.getString(rowIdIndex) ;  
          queryResult += "\n" + "<td><strong> <a target = 'Table Editor ' href = 'tableEditor.jsp?tableName="+tableName+"&rowId="+java.net.URLEncoder.encode(rowIdValue)+"' > E </a></strong></td>";
          }
          
          String columnValue = "";
          for (int count = 1 ; count<=columnCount ; count++ )
          { 
            //----Do not show rowid column
            if (rowIdFound && count == rowIdIndex)
            { continue; }
            //---End of Do not show rowid column
            columnValue = "";
            Vector columnValuePerLine  = new Vector();
            //--------------------Getting The Column Value---------------
            int maxDisplayableSize = 4000;
            try
            {
              //--------------For SQL SERVER Database---------
              if (databaseName.equals("Microsoft SQL Server"))
              {
                columnValue= rs.getString(count);
                columnValue = (columnValue == null )? "&nbsp;": columnValue;
              }
              //--------------For Oracle Database---------
              if (databaseName.equals("Oracle"))
              {
               if ( columnTypes[count].toUpperCase().equals("DATE") 
                    || columnTypes[count].toUpperCase().equals("NUMBER") 
                    || columnTypes[count].toUpperCase().equals("ROWID") 
                    || columnTypes[count].toUpperCase().equals("VARCHAR2") 
                    || columnTypes[count].toUpperCase().equals("RAW") 
                    || columnTypes[count].toUpperCase().equals("CHAR") 
                  ) 
               { 
                columnValue= rs.getString(count);
                columnValue = (columnValue == null )? "&nbsp;": columnValue;
               }
               //--------------If Column Type is CLOB 0r LONG --Or a Varchar with size more than maxDisplayableSize---
               if (columnTypes[count].toUpperCase().equals("BLOB") || columnTypes[count].toUpperCase().equals("CLOB")  ||columnTypes[count].toUpperCase().equals("LONG")|| ( columnValue.length() > maxDisplayableSize && columnValue.indexOf("</a>") == -1) )
               {
                 if (rowIdFound)
                 {
                  //String appURL = "http://10.16.18.130/support";//request.getRequestURL().substring(0,request.getRequestURL().indexOf(request.getServletPath()));
                  columnValue = "<a target= CLOBReader href = "+this.appURL+"/oracleCLOBReader.jsp?columnName="+columnNames[count]+"&rowId="+java.net.URLEncoder.encode(rowIdValue)+"&tableName="+tableName+">"+columnNames[count]+"</a>" ;
                 }
                else { throw new SQLException ("Query Contains CLOB Column. it Should include Rowid also");}
              }
            }
          }
          catch (SQLException e) {  rs.close();   stmt.close(); throw e;}
            //--------------------Rendering The Column Value---------------
        
          queryResult += "\n" + "<td>"+columnValue +"</td> ";
            
            //------------------End of Rendering The Column Value---------------
            boolean  isNumber = columnTypes[count].equals("NUMBER");
            boolean isHyberNumber = false;
            java.math.BigDecimal bd = new java.math.BigDecimal(0);
            if (isNumber)
            {
              bd = rs.getBigDecimal(count);
            }
            try
            {
	            AnchorTag at = new AnchorTag (columnValue , "a"); 
	            if (at.isHtmlTag("a") ) //-------if it is a hyperLinked Number 
	            { 
	                bd = new java.math.BigDecimal( at.getValue());
	                isHyberNumber = true;
	              
	            }
            }
            catch(Exception e) {}
            if (isNumber || isHyberNumber ) //-------------if Column Type is Number 
            {
              if (bd != null)
              {
                columnSums[count]=  bd.add(columnSums[count]);
              }
            }
          }
      
          queryResult += "\n" + "</tr>";
        }       
        java.util.Date endDate = new java.util.Date();
        long execTime = endDate.getTime()- startDate.getTime();
          
          //--------Storing query result count in this object-
          this.queryResultCount[seq] = rownum+1;
        //-------------Rendering Sum of Numeric Data------------------
          boolean renderSumAgregate = false;
          try{ LuQueryDetails qd = (LuQueryDetails) this.getLuQuery().getQueryDetailDs().getPersistantObjectList().get(seq) ;
          renderSumAgregate = qd.getShowAggregate().getBooleanValue() ;
          } catch ( Exception e ){}
          
     boolean rendersSums =  rownum > 0 && renderSumAgregate; 
     if ( rendersSums )
  	 {
    	queryResult += "\n" + "<tr>";
        if (rowIdFound)
        {
          queryResult += "\n" + "<td><strong> </strong></td>";
        }
        for (int count = 1 ; count<=columnCount ; count++ )
        { 
          //----Do not show rowid column
          if (rowIdFound && count == rowIdIndex)
          { continue; }
          //---End of Do not show rowid column
          
          queryResult += "\n" + "<td><strong> " + columnSums[count]+" </strong></td> ";
        }
          queryResult += "\n" + "</tr>";
          
  	 }
     queryResult += "\n" + "</table></div>";
 
  rs.close();
  
  stmt.close();
  return queryResult;
}

public String getQueryResultForSms(int seq , Connection con) throws SQLException
{
	
  String queryResult= "";
  ResultSet rs = null;
  ResultSetMetaData rsmd = null;
  int columnCount = 0;
  String[] columnTypes = null;
  String[] columnNames = null;
  java.math.BigDecimal[] columnSums = null;
  rs = this.getQueryResultSet(seq, con);
  Statement stmt = rs.getStatement();

  rsmd = rs.getMetaData();
  columnCount = rsmd.getColumnCount();
  columnTypes = new String[columnCount+1];
  columnNames = new String[columnCount+1];
  columnSums = new java.math.BigDecimal[columnCount+1];
  
  for (int count = 1 ; count< columnCount+1 ; count++ )
   { 
     columnSums[count] = new java.math.BigDecimal("0");
   }
  
  queryResult += this.titles[seq] ;
   
        boolean rowIdFound = false;
        String tableName = "";
        int rowIdIndex = 0 ;
      
        //----------Check if Oracle RowID included in the Query----------
        while (!rowIdFound &&  rowIdIndex < columnCount)
        {
          rowIdIndex++;
          rowIdFound = (rsmd.getColumnName(rowIdIndex).toString().toUpperCase().equals("ROWID") )? true:false;
        }
        
              if (rowIdFound) //----------If its Found .....
        {
          //rowIdIndex = counter;
          //---------getting Table Name for the given rowid -----------
          tableName = rsmd.getTableName(rowIdIndex);  //-----------Somtimes Failes to get the table Name ?? ??
        
          if (tableName.equals(""))                   //-----------If it Failed....----------
          {
            try
            {
              int tableNameStartIndex = queryStmts[seq].toLowerCase().indexOf(" from ")+ " from ".length();
              int tableNameEndIndex = queryStmts[seq].toLowerCase().indexOf(" where ");
              tableName = queryStmts[seq].substring(tableNameStartIndex ,tableNameEndIndex );
          
              boolean isMultipleTables = (tableName.indexOf(",") == -1 )? false : true;
              if (isMultipleTables)
              { 
                tableName = tableName.substring(0, tableName.indexOf(",") ); 
              }
            }
            catch (Exception e) {throw new SQLException ("Unable to Extract Table Name ");}
          }
        }

        //-------------Rendering Header Data------------------
       queryResult += "\n" ;

        for (int count = 1 ; count<=columnCount ; count++ )
        { 
          //----Do not show rowid column
          if (rowIdFound && count == rowIdIndex)
          { continue; }
          //---End of Do not show rowid column
          
          columnTypes[count] = rsmd.getColumnTypeName(count);
          columnNames[count] =  rsmd.getColumnName(count);
          queryResult +=  columnNames[count] + "\t\t" ;
        }
        //-------rendering Data--------------------
        int rownum = -1;
        String databaseName = con.getMetaData().getDatabaseProductName();
        boolean evenRow = true;
        while (rs.next())
        { rownum++;
          evenRow = !evenRow;
          queryResult += "\n" ;
          
      
          String columnValue = "";
          for (int count = 1 ; count<=columnCount ; count++ )
          { 
            //----Do not show rowid column
            if (rowIdFound && count == rowIdIndex)
            { continue; }
            //---End of Do not show rowid column
            columnValue = "";
            //--------------------Getting The Column Value---------------
            try
            {
              //--------------For SQL SERVER Database---------
              if (databaseName.equals("Microsoft SQL Server"))
              {
                columnValue= rs.getString(count);
              }
              //--------------For Oracle Database---------
              if (databaseName.equals("Oracle"))
              {
                columnValue= rs.getString(count);
            }
          }
          catch (SQLException e) {  rs.close();   stmt.close(); throw e;}
            //--------------------Rendering The Column Value---------------
      
          queryResult +=  columnValue + "\t\t" ; 
          
            //------------------End of Rendering The Column Value---------------
            boolean  isNumber = columnTypes[count].equals("NUMBER");
            boolean isHyberNumber = false;
            java.math.BigDecimal bd = new java.math.BigDecimal(0);
            if (isNumber)
            {
              bd = rs.getBigDecimal(count);
            }
            try
            {
	            AnchorTag at = new AnchorTag(columnValue , "a") ; 
	            if (at.isHtmlTag("a") ) //-------if it is a hyperLinked Number 
	            { 
	                bd = new java.math.BigDecimal(at.getValue());
	                isHyberNumber = true;
	             
	            }
            }
            catch(Exception e) {}
            if (isNumber || isHyberNumber ) //-------------if Column Type is Number 
            {
              if (bd != null)
              {
                columnSums[count]=  bd.add(columnSums[count]);
              }
            }
          }
        }       
        queryResult += "\n";
        //-------------Rendering Sum of Numeric Data------------------
        for (int count = 1 ; count<=columnCount ; count++ )
        { 
          //----Do not show rowid column
          if (rowIdFound && count == rowIdIndex)
          { continue; }
          //---End of Do not show rowid column
          
          queryResult +=   columnSums[count] + "\t\t" ;
        }
  rs.close();
  
  stmt.close();
  return queryResult;
}


public void ifDataFoundSendSMS (int seq , Connection con  ) throws Exception
{
  String queryResult = this.getQueryResultForSms(seq , con );
  //----------if data found then send a maill with this data-------
  int queryIndexToCheck = (seq == -1 )? this.noOfQueries-1 : seq ;  // if seq = -1 then send all results  
  if ( this.queryResultCount[queryIndexToCheck] != 0)
  {
    SmsSernder smsSender = new SmsSernder(SmsSernder.DEFAULT_CLICKTELL_API_ID ,  "proxy-dr","8080", "SADAD\\shawky.foda","redsea11");
    SmsMessage smsMessage = new SmsMessage(queryResult , this.getNotifyTo() , "966564627751" ) ;
    smsSender.sendSMS(smsMessage);
  }
}
/**
 * Execute query no seq on DB Connection con then send the result by mail to "recepient"  from  "sender"
 */
public  String  ifDataFoundSendQueryResultByMail(int seq , Connection con   ) throws Exception
{
	return ifDataFoundSendQueryResultByMail(seq , con , null , false) ; 
}

public  String  ifDataFoundSendQueryResultByMail(int seq , Connection con  , String[] arg , boolean m_sendAsynchronus) throws Exception
{
	StringBuffer resultLog = new StringBuffer(); 
	DataSet qnl = this.getQueryNotifierLists() ;
	for (PersistantObject po : qnl.getPersistantObjectList())
	{
	   //--------Generate the Parm Names and value -----
	   //Vector parmNames = new Vector();
	   //Vector parmValues = new Vector();

	   QueryNotifier qn = (QueryNotifier) po ; 
	   boolean isActiveNotifier = qn.getIsActive().getBooleanValue() ; 
	   boolean isGroupNotifier = qn.getGroupNotification().getBooleanValue() ; 
	   if (isGroupNotifier && isActiveNotifier)
	   {
		  BigDecimal groupId = qn.getGroupRefrenceIdValue();
		  if (groupId== null)
		  {
			  throw new Exception ("Query Notifier is Marked as of Type 'Group Notifier' Without a Refrence to the Group List ID ") ; 
		  }
		  Vector<String> v_parmNames = new Vector<String>() ; 
		  Vector<String> v_parmValues = new Vector<String>() ; 
		  SqlReader luQuerySqlReader = new SqlReader(con , "LU_QUERIES" , "query_body" , groupId.toString() , v_parmNames , v_parmValues , true , this.getSelectedEnvName());
		  luQuerySqlReader.setLoggedUser(this.getLoggedUser());
		  ResultSet rs = luQuerySqlReader.getQueryResultSet(0, con); 
 
		  //--- Create a Virtual QueryNotifier from a resultset 
		  ArrayList<QueryNotifier>  vertualQNs = this.convertRsToQueryNotifiers(rs) ;
		  rs.getStatement().close(); 
		  rs.close() ; 
		  // TODO Build Virtual QueryNotifier 
		  if (vertualQNs != null )		  
		  {
			  for (QueryNotifier queryNotifier : vertualQNs )
			  {
				  resultLog.append(sendNotificationByQueryNotifier (seq , con , arg , queryNotifier, m_sendAsynchronus ) );
			  }
		  }
	   }
	   else if (isActiveNotifier)
	   {
		   resultLog.append(sendNotificationByQueryNotifier (seq , con , arg , qn, m_sendAsynchronus ) );
	   }
	}
	return resultLog.toString(); 
}

private ArrayList<QueryNotifier> convertRsToQueryNotifiers(ResultSet rs) throws Exception {
	ArrayList<QueryNotifier> result = new ArrayList<QueryNotifier> () ; 
	DataSet blanckQnDS ; 
	String queryStr = "Select * from Support.QUERY_NOTIFIER qn where 1<>1 " ;
	ModuleServicesContainer msc = this.getDbServices().getModuleServiceContainer() ;
	if (msc == null )  msc = ApplicationContext.generateModuleServicesContainer(this.selectedEnvName , 1);
	blanckQnDS = msc.getDbServices().queryForDataSet(queryStr, com.smartValue.support.map.QueryNotifier.class) ;
    int counter = 1 ; 
	while (rs.next())
	{
		blanckQnDS.addNew(); 
		
		QueryNotifier qn = (QueryNotifier)blanckQnDS.getCurrentItem();
		qn.setNotifyIdValue(new BigDecimal(counter)) ; 
		qn.setEmailToValue( rs.getString("EMAIL_TO")) ; 
		qn.setEmailCcValue(rs.getString("EMAIL_CC")) ;
		qn.setEmailSubjectValue(rs.getString("EMAIL_SUBJECT")); 
		qn.setDescriptionValue(rs.getString("EMAIL_HEADER"));
		qn.setIsActiveValue(rs.getString("ACTIVE")) ; 
		qn.setQueryIdValue(new BigDecimal(-222)) ; // Dummy query Id to fetch an empty parameters child dataset
		//-- Adding Parameters ------
		DataSet params = qn.getDatasetQueryNotifListParamsFk1(); 
		params.addNew(); 
		QueryNotifListParams qnlp =  (QueryNotifListParams) params.getCurrentItem(); 
		qnlp.setParamNameValue(SqlReader.boundVarInit + rs.getString("PARAM_1_NAME")) ; 
		qnlp.setParamValueValue(rs.getString("PARAM_1_VALUE")) ;
		params.addNew(); 
		qnlp =  (QueryNotifListParams) params.getCurrentItem();
		qnlp.setParamNameValue(SqlReader.boundVarInit + rs.getString("PARAM_2_NAME")) ; 
		qnlp.setParamValueValue(rs.getString("PARAM_2_VALUE")) ;
		params.addNew(); 
		qnlp =  (QueryNotifListParams) params.getCurrentItem();
		qnlp.setParamNameValue(SqlReader.boundVarInit + rs.getString("PARAM_3_NAME")) ; 
		qnlp.setParamValueValue(rs.getString("PARAM_3_VALUE")) ;
		
		counter++; 
		
		result.add(qn); 

	}
	return result;
}

public HttpSession getUserSession() {
	return this.userSession;
}
public void setUserSession(HttpSession m_userSession ) {
	this.userSession = m_userSession;
}

private DbServices getDbServices() throws Exception
{
	DbServices result = null ;  
	if ( this.getUserSession() != null ) 
	{
		ModuleServicesContainer msc = (ModuleServicesContainer) this.getUserSession().getAttribute(Support.Misc.MscSessionKey ) ; 
		result = msc.getDbServices() ;  
	}
	else if (this.getLoggedUser()!= null)
	{
		result = this.getLoggedUser().getDbService();
	}
	else if (this.getSelectedEnvName() != null)
	{
		result = ApplicationContext.generateModuleServicesContainer(this.getSelectedEnvName(), 1).getDbServices() ;
	}
	return result ; 
}

public void sendNotificationByTableNotifiRule ( int seq , Connection con  , TableNotificationRule m_tnr , HashMap<String, String> m_params, boolean m_sendAsynchronus) throws Exception
{
	QueryNotifier qn = m_tnr.toQueryNotifier(con , m_params) ;
	String[] arg = new String[14] ;  
	Iterator it = m_params.entrySet().iterator();
	int i =6 ; 
    while (it.hasNext()) {
        Map.Entry<String , String> pair = (Map.Entry<String , String>)it.next();
        arg[i] = (String)pair.getKey().substring(2) ; 
        arg[i+1] = (pair.getValue() != null)? pair.getValue():"null" ;
        i++; i++;         
    }
    
	sendNotificationByQueryNotifier (seq ,  con  , arg  , qn , m_sendAsynchronus) ; 
}

private StringBuffer sendNotificationByQueryNotifier ( int seq , Connection con  , String[] arg , QueryNotifier m_qn , boolean m_sendAsynchronus) throws Exception
{
		StringBuffer resultLog = new StringBuffer() ; 
		Vector<String> parmNames = new Vector<String>(); 
		Vector<String> parmValues = new Vector<String>();
	   DataSet qnlps = m_qn.getDatasetQueryNotifListParamsFk1() ;
	   String paramsForURL = ""  ; 
	   for (PersistantObject po1 : qnlps.getPersistantObjectList())
	   {
		   QueryNotifListParams pnlp = (QueryNotifListParams) po1 ;
		   String paramName = pnlp.getParamNameValue() ; 
		   String paramValue = pnlp.getParamValueValue() ; 
		   paramValue = (paramValue == null)? "" : paramValue ; 
		   parmNames.addElement(paramName) ;
		   parmValues.addElement (paramValue) ;
		   paramsForURL += paramName.substring(2) + "=" + java.net.URLEncoder.encode(paramValue) + ((parmNames.size() != qnlps.getPersistantObjectList().size())? "&":"") ; 
	   }
	   //-- Adding Params from the command line aruuments if exist
	   if (arg != null )
	   {
		   for (int i = 6 ; i< arg.length-1 ; i++)
		   { if (arg[i] != null)
			   {
			    parmNames.addElement("$$"+arg[i]);
			    parmValues.addElement(arg[i+1]);
			    paramsForURL += arg[i]+"="+java.net.URLEncoder.encode(arg[i+1]) + "&" ; 
			    i++;
			   }
		   }
	   }
		// Adding params from selected env.
	   if (this.getSelectedEnvName() != null)
	   {
	    XMLConfigFileReader aa =  Misc.getXMLConfigFileReader(false ) ; //new XMLConfigFileReader( false);
		ConnParms cp =  aa.getConnParmsByName(this.getSelectedEnvName());
		for ( SqlBindVar sqbv : cp.getSqlBindVars() )  
		{
		  parmNames.add("$$"+sqbv.bindVarName);
		  parmValues.add(sqbv.bindVarValue);
		}
	   }
	  SqlReader sr = new SqlReader(this.repCon , "LU_QUERIES" , "query_body" , this.queryId ,parmNames,parmValues , true , this.getSelectedEnvName()) ; 
	  sr.setLoggedUser(this.getLoggedUser()); 

	   //-------log the action 

	  String emailBody = m_qn.getDescriptionValue() ; 
	  String  emailSubject= m_qn.getEmailSubjectValue(); 
	  
	  for (int i =0 ; i< parmNames.size() ; i++)
	   { 
		   resultLog.append("\nParameter  : "+ i +" Name " + parmNames.elementAt(i) + "     Value = " + parmValues.elementAt(i) );  
		   emailBody = Misc.repalceAll(emailBody, parmNames.elementAt(i), parmValues.elementAt(i)); 
		   emailSubject = Misc.repalceAll(emailSubject, parmNames.elementAt(i), parmValues.elementAt(i));
	   }
	  resultLog.append("\n==========="+emailBody+"================"  );
	  resultLog.append("\nTo : " + m_qn.getEmailToValue() ); 
	  resultLog.append("\nCc : " + m_qn.getEmailCcValue() ); 
	  //resultLog.append("\nSubject : " +  emailTitle );     
	  
	   //-------execute query and if data found send mail with data------
	   resultLog.append("\nStart Time : " +  new java.util.Date() );     
	   // sr.ifDataFoundSendQueryResultByMail(seq, con , autoLoginEnvName , qn);
	   //---
	   StringBuffer queryResult = new StringBuffer(); 
	   queryResult.append("<div dir = "+( (this.getLoggedUser()!=null)? this.getLoggedUser().getDisplayDirection() : "ltr") +">") ; 
	   if (seq == -1 )  // -1 Indicates That it is reqiuested to send all queries results
	   {
		   for (int querySeq = 0 ; querySeq < sr.getNoOfQueries() ; querySeq ++)
		   {
			   String emailTitle = sr.titles[querySeq] ; 
				  for (int i =0 ; i< parmNames.size() ; i++)
				   { 
					   emailTitle = Misc.repalceAll(emailTitle, parmNames.elementAt(i), parmValues.elementAt(i));
				   }
			   queryResult.append( "<br><Strong>" + emailTitle + "</Strong><br>") ; 
			   queryResult.append( sr.getQueryResultForEmail(querySeq , con ));
		   }
	   }
	   else 
	   {
		   String emailTitle = sr.titles[seq] ; 
			for (int i =0 ; i< parmNames.size() ; i++)
			 { 
				emailTitle = Misc.repalceAll(emailTitle, parmNames.elementAt(i), parmValues.elementAt(i));
			 }
		   queryResult.append( "<br>" + emailTitle + "<br>") ;
		   queryResult.append( sr.getQueryResultForEmail(seq , con ));
	   }
	   queryResult.append("</div>") ;
	    boolean includePatrams = !paramsForURL.equals(""); 
          String requestQueryString = "id=" + this.queryId+(includePatrams? "&"+ paramsForURL :"" );
            
          queryResult.append(  "<P><P><P><P> <a target = 'Query Details' href = '"+Misc.getSqlBoundVarValue("PNU_PROD" , "appURL")  +"/editAndExecute.jsp?"+requestQueryString+"'>Show Report in the Browser </a> </P></P></P></P>");
          queryResult.append(  " <small><small><small> This report executed at: " +con.getMetaData().getURL() +"<br>" ) ; 
          
		  //----------if data found then send a maill with this data-------
           
	      //int queryIndexToCheckResultCount = (seq == -1 )? sr.noOfQueries-1 : seq ; // If Requested to Send All The Queries Result , Check for The last Query REsult Count
	      int allRowsCount = 0 ; 
	      for (int i = 0 ; i<sr.queryResultCount.length ; i++ )
	      {
	    	  allRowsCount += sr.queryResultCount[i];  
	      }
	      int rowCount =  (seq == -1 )? 	allRowsCount : sr.queryResultCount [seq] ;
	      boolean dataFound = rowCount!= 0 ;  
	      if ( dataFound )
		  {
			DataSet sysParamsDs = sr.getLoggedUser().getUserCompany().getSysParams(); 
				
			String supportAdminMail = ((SysParams)sysParamsDs.getFirstFilteredPO("E_NAME" , "Support User Email" )).getValValue(); 
			MailSender ms = new  MailSender(sysParamsDs);
			EmailMessage em = new EmailMessage();
			em.setBody( emailBody + "<br> " + queryResult);
			em.setSubject(emailSubject);
			em.setFrom(supportAdminMail);	
			em.setTo( sr.StringToArray( m_qn.getEmailToValue()) ) ; 
			em.setCc(sr.StringToArray( m_qn.getEmailCcValue()) ) ; 
			if (m_sendAsynchronus)
			{
			Support.mail.MailSenderThread mst = new Support.mail.MailSenderThread(em ,ms);
			mst.start() ;
			}
			else { ms.sendMail( em);} 
			com.sideinternational.sas.event.logging.Console.log("Query Title. "+ sr.getQueryTitles()[0] + "have been executed @" + con.getMetaData().getURL()+". Query Result Record count = " + rowCount + "  and Result Sent by email" , SqlReader.class);
			resultLog.append("\n <font color = Green ><strong> (" +rowCount + ") Record(s) Found and sent By Email </strong></font>" );      
		  }
		  else 
		  {
			  resultLog.append("\n <font color = red ><strong>No Data Found and Hence No Emails Sent From Query : </strong></font><br> " + rowCount  ); 
		  }

	   //---
		  resultLog.append("\nEnd   Time : " +  new java.util.Date() );  
	return resultLog ;
}

public int getQueryResultCount(int sequeryIndex , Connection con) throws SQLException
{
  int queryCountResult= 0;
  Statement stmt = con.createStatement();
  ResultSet rs = null;
  try{
	  
  rs = stmt.executeQuery("select count(*) from ( \n "+ this.queryStmts[sequeryIndex] + " \n )");
  }
  catch (SQLException sqle)
  {  stmt.close(); 
      throw new SQLException("Unable to Execute the following Query <Br>" + this.queryStmts[sequeryIndex]+ "\n" + " <Br> Due To the Following SQL Error " + sqle.getMessage());
  }
  while(rs.next())
  {
    queryCountResult = rs.getInt(1);
  }
  return queryCountResult;
  
}

public SqlReader(FileReader fr , Vector parmNames , Vector parmValues ) throws Exception
{
    Validator1.checkExpiry();
    //----------2---- Read The SQL File-----------
    BufferedReader br = new BufferedReader( fr); 
    String s = "";
    queryStmts = new String[4];
    titles = new String[4];
    queryStmts[0]="";
    int counter = 0; 
    
    titles[0] = "";
    queryStmts[0]="";
    while (( s= br.readLine()) != null) 
    {
      if (s.indexOf(queryDelimiter) != -1 )  
      {
        titles[counter]= s;
        queryStmts[counter]="";
        counter++ ;   // to point to another query
        continue;
      }
      else 
      {
       //----------3-----Replace The quey parameters-------------
        if (counter==0){counter++;}
        for (int k = 0 ; k< parmNames.size() ; k++)
        {
 
          s= Misc.repalceAll(s,parmNames.elementAt(k).toString(), parmValues.elementAt(k).toString());    
        }
        queryStmts[counter-1] += s +"\n";
      }
    }
    br.close();
    this.noOfQueries = counter;
  }
  public String[] getQueryStatments()
  {
    return queryStmts;
  }
  public String[] getQueryTitles()
  {
    return this.titles;
  }
  public int getNoOfQueries()
  {
    return noOfQueries;
  }
  public String[] StringToArray(String str)
  { 
     if (str == null)
     {
       return null;
     }
     StringTokenizer st= new StringTokenizer(str, ",");
     int recepiants_size = st.countTokens();
     String[] arr = new String[recepiants_size]; //arg[1]
     for (int i = 0; i<recepiants_size ; i++)
     {
      arr[i] = st.nextElement().toString();
     }
     return arr;
  }
//-----------------------
private String allQuriesFromDB = null; 
public String getAllQueriesFromDB(Connection con , String tableName , String columnName , String id , boolean ignoreParamValueNotFound ) throws Exception
{
if (allQuriesFromDB == null )
{
  StringBuffer allQueriesString = new StringBuffer("");
  if (con == null || con.isClosed())
  {
    throw new Exception("Invalid Connection to DB");
  }
  String allQueries = getAllQueries() ; 
  StringTokenizer allQueriesST = new StringTokenizer(allQueries , "\n" ) ;
  while (allQueriesST.hasMoreElements())
  {
	  String line =allQueriesST.nextToken();
	   	//Allowing Writing nested Queries using @@ operator 
      	// For example @@2345[2] will be replaced with query id =  2345 and Index = 2
      	int nestedQIndex = line.indexOf("@@") ; 
      	if ( nestedQIndex > -1)
      	{
      		int endOfQueryId = line.indexOf("[",  nestedQIndex) ;
      		int endOfQueryIndex = line.indexOf("]",  nestedQIndex) ;
      		if (endOfQueryId < 0 || endOfQueryIndex < 0 )
      		{
      			throw new Exception("Please Use @@2345[2] format") ; 
      		}
      		int queryIndex = Integer.valueOf(line.substring(endOfQueryId+1 , endOfQueryIndex )) ;
      		String nestedId = line.substring(nestedQIndex+2 , endOfQueryId ) ;
      		if (! refrencedQueryIds.contains(nestedId))
      		{
      			ArrayList<String> qIndexs = new ArrayList<String>(); 
      			qIndexs.add(String.valueOf(queryIndex)); 
      			refrencedQueryIds.put(nestedId, qIndexs) ;
      		}
      		else { refrencedQueryIds.get(nestedId).add(String.valueOf(queryIndex)); }
      		 
      		if (! nestedId.equalsIgnoreCase(id) )
      		{
             	String nestedQueryStr ; 
      			//nestedQueryStr  = getAllQueriesFromDB(con, tableName ,columnName , nestedId , ignoreParamValueNotFound) ;
      			SqlReader nestedQueryReader ; 
      			if ( this.getUserSession() != null)
      			{ nestedQueryReader = new SqlReader(this.repCon, tableName , columnName , nestedId , this.getUserSession() , this.userRequest , true) ; }
      			else
      			{ nestedQueryReader = new SqlReader(this.repCon, tableName , columnName , nestedId , this.parmNames , this.parmValues , true , this.selectedEnvName);  } 
      			Hashtable<String, String> blankParams = new Hashtable<String, String>() ; 
      			nestedQueryReader.setAllQueries(blankParams , true);
      			nestedQueryStr = nestedQueryReader.getQueryStatments()[queryIndex] ;
          		line =  Misc.repalceAll(line , "@@"+ nestedId+"["+queryIndex+"]", nestedQueryStr );
      		}
      		
      	}
      	upadateRefrencedList ( line , "<iframe " , "renderQueryResult.jsp?id=" , "queryIndex=" ) ;
    	upadateRefrencedList ( line , "<iframe " , "jqueryChart.jsp?queryId=" , "queryIndex=" ) ;
    	
    	allQueriesString.append( line +"\n" );

  //try {this.setExecutableAlertMessage(rs.getString("MSG")); } catch(Exception e){}
  }
if (this.isLuQueryType)
{
	LuQueries luq =  this.getLuQuery() ;  
  if (this.notifyTo == null ) this.notifyTo =  luq.getEmailNotifyToValue(); //rs.getString("EMAIL_NOTIFY_TO"); // Currently used only for sms mobile no. for emails notification a more advanced model is used 
  this.active = luq.getActiveValue(); //rs.getString("ACTIVE");
  this.logExecution = luq.getLogExecution().getBooleanValue(); // rs.getString("log_execution").equalsIgnoreCase("Y");
  this.setQueryADesc(luq.getADscValue()); //rs.getString("A_DSC")
  this.setQueryEDesc(luq.getEDscValue()); //rs.getString("E_DSC")
  this.setQueryHyperLinkTitle(luq.getHyperlinkTitleValue() ); //rs.getString("HYPERLINK_TITLE")
}
else
{ LuExecutables lux = this.getLuExecutables() ;  
  if (this.notifyTo == null ) this.notifyTo =  lux.getEmailNotifyToValue();  
  this.active = lux.getActiveValue(); 
  this.logExecution = lux.getLogExecution().getBooleanValue(); 
  this.setQueryADesc(lux.getADscValue()); 
  this.setQueryEDesc(lux.getEDscValue()); 
  this.setQueryHyperLinkTitle(lux.getHyperlinkTitleValue() ); 

}
  allQuriesFromDB = allQueriesString.toString() ;
}

  return allQuriesFromDB ;

}

String allQueris = null ; 
private String getAllQueries() throws Exception
{
	if (this.isLuQueryType)
	{
	if (allQueris == null )
	{
		allQueris = "" ; 
		DataSet queryDetailsDs = this.getLuQuery().getQueryDetailDs() ; 
	  for (PersistantObject po :  queryDetailsDs.getPersistantObjectList()) 
	  {
		  LuQueryDetails qd = (LuQueryDetails) po ;
		  allQueris += "\n\\" + qd.getTitleValue() + "\n" +qd.getQueryBodyValue() ; 
	  }
	}
	}
	else {allQueris = this.getLuExecutables().getExecBodyValue();}
	
	return allQueris ; 

}
public static ArrayList<String> getParamQueryNames (String m_query , String m_paramInitial) throws Exception
{
	ArrayList<String> result = new ArrayList<String> () ; 
	StringTokenizer paramStrTok = new StringTokenizer(m_query, m_paramInitial) ;
	  
	  boolean skipToken = true ; 
	  while (paramStrTok.hasMoreTokens() )
	  {
		  String token = Support.Misc.repalceAll(paramStrTok.nextToken(), "\n", " ");
		  token = Support.Misc.repalceAll(token , "'" , " "); 
		  token = Support.Misc.repalceAll(token , ")" , " ");
		  token = Support.Misc.repalceAll(token , "%" , " ");
		  token = Support.Misc.repalceAll(token , "&" , " ");
		  token = Support.Misc.repalceAll(token , "/" , " ");
		  token = Support.Misc.repalceAll(token , "\\" , " ");
		  if (skipToken ) { skipToken = false ; continue; }  
		  int firstSpaceIndex = token.indexOf(" ") ;
		  int paramEndIndex = (firstSpaceIndex > 0 ) ? firstSpaceIndex :  token.length() ;  
		  String paramName = token.substring(0 , paramEndIndex ) ;
		  if ( !result.contains(paramName.toUpperCase()))
			  result.add(paramName.toUpperCase());
		   
	  }
	  return result; 
	
}
public ArrayList<String> getParamQueryNames (String m_paramInitial) throws Exception
{
	String allQueries = getAllQueriesFromDB(this.repCon ,this.getTableName() , "QUERY_BODY" , this.queryId  , true  ) ;
	return SqlReader.getParamQueryNames (allQueries , m_paramInitial ) ; 
}

private void upadateRefrencedList( String m_line , String m_htmlIncludeTag , String token1 , String token2)
{
	if (m_line.indexOf(m_htmlIncludeTag) > - 1)
	{
		if ( m_line.indexOf (token1) > -1 )
		{
    		int queryIdStartIndex = m_line.indexOf(token1)+ token1.length();
    		int queryIdEndIndex =m_line.indexOf("&" , queryIdStartIndex) ;
    	
    		int queryIndexStartIndex = m_line.indexOf(token2)+ token2.length();
    		int queryIndexEndIndex =m_line.indexOf("&" , queryIndexStartIndex) ;
    	
        	if (queryIdStartIndex > -1 && queryIdEndIndex > -1 && queryIndexStartIndex > -1 && queryIndexEndIndex > -1 )
        	{
        		String includedQueryId = m_line.substring(queryIdStartIndex , queryIdEndIndex ) ; 
            	String includedQuryIndex = m_line.substring(queryIndexStartIndex , queryIndexEndIndex ) ; 
            	if (! refrencedQueryIds.contains(includedQueryId))
        		{
        			ArrayList<String> qIndexs = new ArrayList<String>(); 
        			qIndexs.add(String.valueOf(includedQuryIndex)); 
        			refrencedQueryIds.put(includedQueryId, qIndexs) ;
        		}
        	}
		}
	}
}

public Hashtable<String, ArrayList<String>> getRefrencedQueryIds()
{
	return this.refrencedQueryIds ; 
}

public ArrayList<String> getQueryParams()
{
	return this.queryParams ; 
}

private Hashtable<String, String> paramHashTable = new Hashtable<String, String>();
private boolean isLuQueryType = true ; 
private void initialize(String tableName , String columnName , String id, HttpSession session , HttpServletRequest request , Boolean ignoreParamValueNotFound) throws Exception
{
	 queryId = id; 
	 this.userSession = session ; 
	 this.userRequest = request ; 
	 isLuQueryType = tableName.equalsIgnoreCase("LU_QUERIES"); 

	 paramHashTable = this.getParams(session, request);  
	 
	  String allQueriesString = getAllQueriesFromDB( this.repCon ,tableName, columnName , id , ignoreParamValueNotFound);
	  // ---- Setting Query parameters in 
	  this.queryParams = getParamQueryNames( "$$") ; 
	  setAllQueries(allQueriesString, paramHashTable , ignoreParamValueNotFound );
	  //setAllQueries( paramHashTable , ignoreParamValueNotFound );
	  this.appURL =  "http://"+ InetAddress.getLocalHost().getHostAddress()+":8988/Support";
	  this.loggedUser = (SecUsrDta)  session.getAttribute("loggedUser");
	 
	
}
/*
public SqlReader(String tableName , String columnName , String id , HttpSession session , HttpServletRequest request  , Boolean ignoreParamValueNotFound ) throws Exception
{
	// Use rep Connection from Connection configuration file
	this.setTableName(tableName);
	Support.XMLConfigFileReader supportConfig =  Misc.getXMLConfigFileReader(false ) ;  
	this.repCon = supportConfig.reposatoryConn.generateConnection();
	initialize( tableName ,  columnName ,  id ,  session ,  request , ignoreParamValueNotFound );

	
}
*/
public SqlReader(Connection con ,String tableName , String columnName , String id , HttpSession session , HttpServletRequest request , boolean igoneParamSubsValues ) throws Exception
{
	  this.repCon  = con;
	  this.setTableName(tableName);
	  initialize( tableName ,  columnName ,  id ,  session ,  request, igoneParamSubsValues );
}
public SqlReader(Connection con ,String tableName , String columnName , String id , HttpSession session , HttpServletRequest request   ) throws Exception
{
  this.repCon  = con;
  this.setTableName(tableName);
  initialize( tableName ,  columnName ,  id ,  session ,  request, false );
}
/*
public SqlReader(String allQueries , Vector parmNames , Vector parmValues , boolean m_ignoreParamValueNotFound) throws Exception
{
  setAllQueries(allQueries,parmNames, parmValues, m_ignoreParamValueNotFound);
  this.appURL =  "http://"+ InetAddress.getLocalHost().getHostAddress()+":8988/Support";
}


public SqlReader(Connection m_repCon , String tableName , String columnName ,  String id , Vector parmNames , Vector parmValues , Boolean ignoreParamValueNotFound ) throws Exception
{
	 this.repCon = m_repCon ;
	 this.setTableName(tableName); 
	 isLuQueryType = tableName.equalsIgnoreCase("LU_QUERIES"); 
	 //String allQueriesString = getAllQueriesFromDB( this.repCon  ,tableName, columnName , id , ignoreParamValueNotFound);
	 queryId = id;
	 this.queryParams = getParamQueryNames("$$") ;
	 Hashtable params = new Hashtable<String , String>() ;   
	 for (int i = 0 ;  i< parmNames.size()  ; i++)
	  { params.put(parmNames.get(i).toString() , parmValues.get(i).toString()); }
	 
	 setAllQueries(params , ignoreParamValueNotFound);

	 this.appURL =  "http://"+ InetAddress.getLocalHost().getHostAddress()+":8988/Support";

}
*/
 
public SqlReader(Connection m_repCon , String tableName , String columnName , String id , Vector<String> parmNames , Vector<String> parmValues , Boolean ignoreParamValueNotFound , String m_selectedEnvName) throws Exception
{
	this.parmNames = parmNames ; 
	this.parmValues = parmValues ; 
	this.setSelectedEnvName(m_selectedEnvName);
	this.setTableName(tableName);
	this.repCon = m_repCon ;
	isLuQueryType = tableName.equalsIgnoreCase("LU_QUERIES");
	//Support.XMLConfigFileReader supportConfig =  Misc.getXMLConfigFileReader(false ) ;  
	//this.repCon = supportConfig.reposatoryConn.generateConnection();
	queryId = id;
	String allQueriesString  = getAllQueriesFromDB( this.repCon  ,tableName, columnName , id , ignoreParamValueNotFound);
	
	this.queryParams = getParamQueryNames( "$$") ;
  
  Hashtable<String, String> params = new Hashtable<String, String>();
  if (parmNames != null )
  {
	  for (int i = 0 ;  i< parmNames.size()  ; i++)
	  { params.put(parmNames.get(i).toString() , parmValues.get(i).toString()); }
  }
  setAllQueries(allQueriesString ,params , ignoreParamValueNotFound);
  this.appURL =  "http://"+ InetAddress.getLocalHost().getHostAddress()+":8988/Support";
  
}
public String  substituteVariables(String xx , Vector parmNames , Vector parmValues)
{
   String result = xx;
   for (int k = 0 ; k< parmNames.size() ; k++)
   {
      result= Misc.repalceAll(result,parmNames.elementAt(k).toString(), parmValues.elementAt(k).toString());    
      result= Misc.repalceAll(result,parmNames.elementAt(k).toString(), parmValues.elementAt(k).toString());              
   } 
   return result;
}

private void setAllQueries(String allQueries , Hashtable<String, String> parms ,  boolean ignoreParamValueNotFound) throws Exception 
{
	int paramsSize = parms.size();
	  Validator1.checkExpiry();
	  StringTokenizer st = new StringTokenizer(allQueries, queryDelimiter);
	  queryStmts = new String[10];
	  titles = new String[10];
	  //messages = new String[10];
	  int counter = 0;
	  String temp ="";
	  int newLineIndex = 0;
	  while(st.hasMoreTokens())
	  {
	  temp = st.nextToken();
	  newLineIndex = temp.indexOf("\n");
	  if (newLineIndex != -1)
	    {
	    queryStmts[counter] = temp.substring(newLineIndex);
	    //---------Removing the Comments 
	    titles[counter] = temp.substring(0,newLineIndex);

	    //----------3-----Replace The quey parameters-------------
	    Enumeration<String> en = parms.keys();
	 
        while ( en.hasMoreElements())
	     {
	   	  String paramName = en.nextElement();
	   	  String paramValue =  parms.get(paramName);
	   	  queryStmts[counter]= Misc.repalceAll(queryStmts[counter],paramName, (paramValue == null)? "" :paramValue );
	   	  titles[counter]= Misc.repalceAll(titles[counter],paramName, paramValue);
	   	 
	     }
	   
	    //---------Replacing Java Calculated Parameters (Started with $$# )
        replaceJavaVariables( counter) ; 
       	    
	    //----------Check if still there are bound variables not defiened (replaced) ---------
	    int globalBoundVarIndex = -1 ;
	    if ((globalBoundVarIndex= queryStmts[counter].indexOf(boundVarInit)) != -1 )
	    { 
	      //--Check if it is sql Commented or not -------------
	      int boundVarIndex = -1 ;
	      boolean allCommented = true;
	      StringTokenizer st2 = new StringTokenizer(queryStmts[counter],"\n");
	      String queryLine= "";
	      while(st2.hasMoreTokens())
	      {
	    	queryLine = st2.nextToken();
	        boundVarIndex = queryLine.indexOf(boundVarInit);
	        boolean found = boundVarIndex != -1 ;
	        if (! found) {continue ; }
	        int commentIndex  =queryLine.indexOf("--");
	        
	        boolean commented = commentIndex >= 0 && commentIndex < boundVarIndex ; 
	        if ( found &&  ! commented )
	        {
	        	allCommented = false;
	          break;
	        }
	      }
	      //---------End of Checking if it is Commented or Not-------
	      if (allCommented){}
	      else{ if (! ignoreParamValueNotFound) throw new QueryParamValueNotFoundException ("Query Statement Still Contains undefined bound Variable at : \n" + queryStmts[counter].substring(globalBoundVarIndex, globalBoundVarIndex+8 ) , queryStmts[counter].substring(globalBoundVarIndex, globalBoundVarIndex+8 )  );}
	    }
	    counter ++;
	    }
	  }
	  this.noOfQueries = counter;
	  this.queryResultCount = new int[counter]; 
	  
	  // -- The below loop for substitute references to other queries in the same set 
	  for ( int i = 0 ; i < this.noOfQueries ; i++)
	  {
		  String qi = this.getQueryStatments()[i];
		  String searchForRefr = "@@"+this.queryId+"[" ; 
		  int searchForRefrStrIndex = qi.indexOf(searchForRefr) ;   
		  while ( searchForRefrStrIndex != -1 ) // the query String still refere to another query in the same set 
		  {
			  int selfRefrenceStartIndex = searchForRefrStrIndex + searchForRefr.length() ; 
			  int selfRefrenceEndIndex = qi.indexOf("]", selfRefrenceStartIndex) ; 
			  int refQueryIndex = Integer.valueOf( qi.substring(selfRefrenceStartIndex, selfRefrenceEndIndex)) ;
			  qi = Misc.repalceAll(qi, searchForRefr +refQueryIndex + "]" , this.getQueryStatments()[refQueryIndex]) ; 
			  searchForRefrStrIndex = qi.indexOf(searchForRefr) ;
		  }
		  this.getQueryStatments()[i] = qi ; 
	  }
}

private void replaceJavaVariables(int counter ) throws UnknownHostException {
	String appUrl = "Unable To Get AppURL" ; 
	if (this.userRequest != null){
		appUrl = Misc.getAppURL(this.userRequest);  
	}
	else if (this.getLoggedUser() != null)
	{
		appUrl = this.getLoggedUser().getAppURL(); 
		
	}
	queryStmts[counter]= Misc.repalceAll(queryStmts[counter],"$$#HostIP", InetAddress.getLocalHost().getHostAddress());
	queryStmts[counter]= Misc.repalceAll(queryStmts[counter],"$$#appURL", appUrl );
	 
	 titles[counter]= Misc.repalceAll(titles[counter],"$$#HostIP", InetAddress.getLocalHost().getHostAddress());
	 titles[counter]= Misc.repalceAll(titles[counter],"$$#appURL", appUrl);
	 

}

private void setAllQueries( Hashtable<String, String> parms ,  boolean ignoreParamValueNotFound) throws Exception 
{
	 Validator1.checkExpiry();
	  //StringTokenizer st = new StringTokenizer(allQueries, queryDelimiter);
	  queryStmts = new String[10];
	  titles = new String[10];
	  //messages = new String[10];
	  int counter = 0;
	  
	
	  //while(st.hasMoreTokens())
	  DataSet qdDs = this.getLuQuery().getQueryDetailDs() ; 
	  for (PersistantObject po :  qdDs.getPersistantObjectList()) 
	  {
		  LuQueryDetails qd = (LuQueryDetails) po ; 
		  queryStmts[counter] =  qd.getQueryBodyValue().toString() ;
		  titles[counter] =  qd.getTitleValue() ;

	    //----------3-----Replace The quey parameters-------------
	    Enumeration<String> en = parms.keys();
	 
        while ( en.hasMoreElements())
	     {
	   	  String paramName = en.nextElement();
	   	  String paramValue =  parms.get(paramName);
	   	  queryStmts[counter]= Misc.repalceAll(queryStmts[counter],paramName, (paramValue == null)? "" :paramValue );
	   	  titles[counter]= Misc.repalceAll(titles[counter],paramName, paramValue);
	   	 
	     }
	   
	    //---------Replacing Java Calculated Parameters (Started with $$# )
        replaceJavaVariables(counter);
	    
	    //----------Check if still there are bound variables not defiened (replaced) ---------
	    int globalBoundVarIndex = -1 ;
	    if ((globalBoundVarIndex= queryStmts[counter].indexOf(boundVarInit)) != -1 )
	    { 
	      //--Check if it is sql Commented or not -------------
	      int boundVarIndex = -1 ;
	      boolean commented = false;
	      StringTokenizer st2 = new StringTokenizer(queryStmts[counter],"\n");
	      String queryLine= "";
	      while(st2.hasMoreTokens())
	      {
	        queryLine = st2.nextToken();
	        boundVarIndex = queryLine.indexOf(boundVarInit);
	        int commentIndex  =queryLine.indexOf("--");
	        if (boundVarIndex >= 0 && commentIndex >= 0 && commentIndex < boundVarIndex )
	        {
	          commented = true;
	          break;
	        }
	      }
	      //---------End of Checking if it is Commented or Not-------
	      if (commented){}
	      else{ if (! ignoreParamValueNotFound) throw new QueryParamValueNotFoundException ("Query Statement Still Contains undefined bound Variable at : \n" + queryStmts[counter].substring(globalBoundVarIndex, globalBoundVarIndex+8 ) , queryStmts[counter].substring(globalBoundVarIndex, globalBoundVarIndex+8 )  );}
	    }
	    counter ++;
	    
	  }
	  this.noOfQueries = counter;
	  this.queryResultCount = new int[counter]; 
	  
	  // -- The below loop for substitute references to other queries in the same set 
	  for ( int i = 0 ; i < this.noOfQueries ; i++)
	  {
		  String qi = this.getQueryStatments()[i];
		  String searchForRefr = "@@"+this.queryId+"[" ; 
		  int searchForRefrStrIndex = qi.indexOf(searchForRefr) ;   
		  while ( searchForRefrStrIndex != -1 ) // the query String still refere to another query in the same set 
		  {
			  int selfRefrenceStartIndex = searchForRefrStrIndex + searchForRefr.length() ; 
			  int selfRefrenceEndIndex = qi.indexOf("]", selfRefrenceStartIndex) ; 
			  int refQueryIndex = Integer.valueOf( qi.substring(selfRefrenceStartIndex, selfRefrenceEndIndex)) ;
			  qi = Misc.repalceAll(qi, searchForRefr +refQueryIndex + "]" , this.getQueryStatments()[refQueryIndex]) ; 
			  searchForRefrStrIndex = qi.indexOf(searchForRefr) ;
		  }
		  this.getQueryStatments()[i] = qi ; 
	  }
}

@Deprecated
private Vector<String> parmNames ; 
private Vector<String> parmValues ; 

private void setAllQueries(String allQueries , Vector<String> pm_parmNames , Vector<String> pm_parmValues , boolean m_ignoreParamValueNotFound ) throws Exception
{
	if (pm_parmNames == null) return ; 
	this.parmNames =  pm_parmNames ; 
	this.parmValues = pm_parmValues ;
	
	if (allQueries == null ) return ; 
  Validator1.checkExpiry();
  StringTokenizer st = new StringTokenizer(allQueries, queryDelimiter);
  queryStmts = new String[10];
  titles = new String[10];
  //messages = new String[10];
  int counter = 0;
  String temp ="";
  int newLineIndex = 0;
  while(st.hasMoreTokens())
  {
  temp = st.nextToken();
  newLineIndex = temp.indexOf("\n");
  if (newLineIndex != -1)
    {
    queryStmts[counter] = temp.substring(newLineIndex);
    //---------Removing the Comments 
    titles[counter] = temp.substring(0,newLineIndex);

    //----------3-----Replace The quey parameters-------------
    for (int k = 0 ; k< pm_parmNames.size() ; k++)
    {
       //queryStmts[counter] = NCCI.Misc.repalceAll(queryStmts[counter],boundVarInit+boundVarInit,boundVarInit);   
    	String paramName  = ( pm_parmNames.elementAt(k) != null)? pm_parmNames.elementAt(k).toString() : "null" ; 
    	String ParamValue = ( pm_parmValues.elementAt(k)!= null)? pm_parmValues.elementAt(k).toString(): "null" ; 
       queryStmts[counter]= Misc.repalceAll(queryStmts[counter],paramName,ParamValue );    
       titles[counter]= Misc.repalceAll(titles[counter],paramName, ParamValue);              
    }
    //---------Replacing Java Calculated Parameters (Started with $$# )
    replaceJavaVariables(counter);
   
    //----------Check if still there are bound variables not defiened (replaced) ---------
    int globalBoundVarIndex = -1 ;
    if (!m_ignoreParamValueNotFound && (globalBoundVarIndex= queryStmts[counter].indexOf(boundVarInit)) != -1 )
    { 
      //--Check if it is sql Commented or not -------------
      int boundVarIndex = -1 ;
      boolean commented = false;
      StringTokenizer st2 = new StringTokenizer(queryStmts[counter],"\n");
      String queryLine= "";
      while(st2.hasMoreTokens())
      {
        queryLine = st2.nextToken();
        boundVarIndex = queryLine.indexOf(boundVarInit);
        int commentIndex  =queryLine.indexOf("--");
        if (boundVarIndex >= 0 && commentIndex >= 0 && commentIndex < boundVarIndex )
        {
          commented = true;
          break;
        }
      }
      //---------End of Checking if it is Commented or Not-------
      if (commented){}
      else{throw new Exception ("Query Statement Still Contains undefined bound Variable at : \n" + queryStmts[counter].substring(globalBoundVarIndex, globalBoundVarIndex+8 ) );}
    }
    counter ++;
    }
  }
  this.noOfQueries = counter;
  this.queryResultCount = new int[counter]; 
 
  //-- The below loop for substitute references to other queries in the same set 
  for ( int i = 0 ; i < this.noOfQueries ; i++)
  {
	  String qi = this.getQueryStatments()[i];
	  String searchForRefr = "@@"+this.queryId+"[" ; 
	  int searchForRefrStrIndex = qi.indexOf(searchForRefr) ;   
	  while ( searchForRefrStrIndex != -1 ) // the query String still refere to another query in the same set 
	  {
		  int selfRefrenceStartIndex = searchForRefrStrIndex + searchForRefr.length() ; 
		  int selfRefrenceEndIndex = qi.indexOf("]", selfRefrenceStartIndex) ; 
		  int refQueryIndex = Integer.valueOf( qi.substring(selfRefrenceStartIndex, selfRefrenceEndIndex)) ;
		  qi = Misc.repalceAll(qi, searchForRefr +refQueryIndex + "]" , this.getQueryStatments()[refQueryIndex]) ; 
		  searchForRefrStrIndex = qi.indexOf(searchForRefr) ;
	  }
	  this.getQueryStatments()[i] = qi ; 
  }
}

 private String getBothTableAndOwnerNames(int queryNum) throws Exception
 {
	 String tableAndOwnerNames = "";
     
     if (tableAndOwnerNames.equals(""))                   //-----------If it Failed....----------
     {
       try
       {
         int tableNameStartIndex = this.queryStmts[queryNum].toLowerCase().lastIndexOf(" from ")+ " from ".length();
         int tableNameEndIndex = queryStmts[queryNum].toLowerCase().lastIndexOf(" where ");
         tableAndOwnerNames = queryStmts[queryNum].substring(tableNameStartIndex ,tableNameEndIndex );
     
         boolean isMultipleTables = (tableAndOwnerNames.indexOf(",") == -1 )? false : true;
         if (isMultipleTables)
         { 
        	 tableAndOwnerNames = tableAndOwnerNames.substring(0, tableAndOwnerNames.indexOf(",") ); 
         }
       }
       catch (Exception e) {throw new Exception ("Unable to Extract Table Name ");}

     }
	 return tableAndOwnerNames.trim().toUpperCase();
 }
 private void throwException() throws Exception
 {
	 throw new Exception ("Table Name in Your query does not include owner name..it should be in the form of 'OWNER.TABLE_NAME TN'. please specify");	 
 }
 public String estimateTableName(int queryNum ) throws Exception
 {
	 String tableAndOwnerNames = getBothTableAndOwnerNames(queryNum).trim();

     int dotIndex =  tableAndOwnerNames.indexOf(".");
     if (dotIndex==-1) {throwException();}
     int endIndex =  tableAndOwnerNames.indexOf(" ");
     return tableAndOwnerNames.substring(dotIndex+1 , endIndex).toUpperCase();

 }

 public String estimateTableOwner(int queryNum ) throws Exception
 {
	 String all = getBothTableAndOwnerNames(queryNum).trim();

     int dotIndex =  all.indexOf(".");
     if (dotIndex==-1) {throwException();}
     return all.substring(0 , dotIndex);

 }

 public int getRowIDIndex(ResultSetMetaData rsmd , String rowidColumnName) throws Exception
 {
     boolean rowIdFound = false;
     int columnCount = rsmd.getColumnCount();
     int rowIdIndex = 0 ;
  
   
     //----------Check if Oracle RowID included in the Query----------
     while (!rowIdFound &&  rowIdIndex < columnCount)
     {
       rowIdIndex++;
       rowIdFound = (rsmd.getColumnName(rowIdIndex).toString().toUpperCase().equals(rowidColumnName) 
                     ||rsmd.getColumnName(rowIdIndex).toString().toUpperCase().equals("MYROWID") )? true:false;
     }
     
     return (rowIdFound)? rowIdIndex : 0;
 }




 public static void main(String[] arg) throws Exception
 {
	 java.sql.Connection  con = null;
	 java.sql.Connection  repCon = null;
  if ( arg.length < 2 || ! arg[0].equals("ifDataFoundSendQueryResultByMail"))
   {
     System.out.println("Usage");
     System.out.println(" SqlReader ifDataFoundSendQueryResultByMail [queryID] [seq] [DbName] [userName] [password] [parm1 Parm1Value] [parm2 Parm2Value] [parm3 Parm3Value] ......");
     System.exit(0);
   }

    String queryID= arg[1];//"shawky.foda@sadad.com";
    int  seq = Integer.parseInt(arg[2]);

try{
   XMLConfigFileReader aa =  Misc.getXMLConfigFileReader(false ) ; // new XMLConfigFileReader( false);
   repCon = aa.reposatoryConn.generateConnection();
   //Misc misc = new Misc(repCon);
   String autoLoginEnvName = arg[3];
   String autoLoginUsername = arg[4]; 
   String autoLoginPassword = arg[5]; 
   ConnParms cp =  aa.getConnParmsByName(autoLoginEnvName);  
   con = cp.generateConnection(autoLoginUsername, autoLoginPassword , "NORMAL");
   
	try{SWAF.initialize();}catch (Exception e) {e.printStackTrace();} 
	Map<String, Pool> allPools = ApplicationContextListener.initializeConnections();	
	ApplicationContext.setPools(allPools);
	ModuleServicesContainer msc = Support.Misc.getModuleServiceContainer(autoLoginEnvName , 1);  
	com.smartValue.database.map.services.SecUserDataService secUsrDtaServices = msc.getSecUserDataService();
	SecUsrDta autoLoggedUser = secUsrDtaServices.getUserByUserName(con.getMetaData().getUserName().toUpperCase());
    Vector<String> parmNames = new Vector<String>();
	Vector<String> parmValues = new Vector<String>();

    SqlReader sr = new SqlReader(repCon , "LU_QUERIES" , "query_body" , queryID ,parmNames,parmValues , true , autoLoginEnvName) ;
    sr.setSelectedEnvName(autoLoginEnvName) ; 
    sr.setLoggedUser(autoLoggedUser) ;
    String logResult = sr.ifDataFoundSendQueryResultByMail(seq, con, arg , false) ;
    System.out.print(logResult) ; 
    con.close();
    repCon.close();
  }
  catch (Exception e ) {  e.printStackTrace();} 
  con.close(); repCon.close();
  /* 
   * system.exit is added to terminate the process ( i do not know why it is not normally ?? !!!
   */
  System.exit(0);
 }
 
 public static String getValueFromRequestOrSession(String paramName , HttpServletRequest request , HttpSession session)
 {
		String dirPathValue = request.getParameter(paramName) ;  
		if ( dirPathValue != null )
		{
			session.setAttribute( paramName , dirPathValue ) ;
		}
		else dirPathValue = (String) session.getAttribute( paramName) ; 
		
		return dirPathValue ; 

 }
 
 public void executeExecutable (int queryIndex , Connection execAgainstCon) throws SQLException
 {
	 
		String[] execStmt =this.getQueryStatments();
		String updateStr = execStmt[queryIndex] ; 
		if (updateStr == null || updateStr.equals("")) {return ; }
		System.out.println("Smart Tool Will Execute Executable ID : " + this.queryId + "\n" +  updateStr ) ;
		execAgainstCon.setAutoCommit(false) ;
		Statement stmt = null ; 
		try{
			stmt = execAgainstCon.createStatement() ; 
			stmt.execute(updateStr);
			//---------Audit the Service Execute Operation ----
   	   		try{
			String auditStr = "Insert Into Support.AUDIT_SERVICE_EXECUTE (service_id , SERVICE_INDEX , sql_text) values ("+this.queryId+" , "+queryIndex+", '"+Misc.replaceSingleQuteWithDouble(updateStr)+"' )" ;
   	   		stmt.execute(auditStr) ;
   	   		}
   	   		catch (Exception e) {}
   		   //--- End of Audit the Service Execute Operation ----
			}
		catch(SQLException sqle )
		{
			if (stmt != null ) stmt.close() ;
			throw new SQLException ("Unable to Execute : " + updateStr + "\nDue To :" + sqle.getMessage())  ; 
		}
	
 }
 
 public boolean isUserCanExecute(String m_user) throws Exception
 {
	 boolean userCanExecute = false ;
	 
		Statement repStmt = null;
		ResultSet rs = null ; 
		try 
		{
			repStmt = repCon.createStatement() ;
			String qs = "Select icdb.security.is_User_Can_Execute('"+m_user+"' , 'Process' , '"+queryId+"') from dual  " ; 
			rs = repStmt.executeQuery(qs) ;
			System.out.println("Smart Tool Executed Query " + qs + "Succcessfully ") ;
			while (rs.next())
			{
				String canUpdateStr = rs.getString(1) ;  
				userCanExecute = ( canUpdateStr != null && canUpdateStr.equalsIgnoreCase("Y") )   ; 
			}
		}
		catch (Exception e)
		{
			e.printStackTrace() ; 
			if (rs != null ) rs.close();
			if (repStmt != null ) repStmt.close() ; 
			throw e ;
		}
		
	 return userCanExecute ;
 }

public void setQueryADesc(String queryADesc) {
	this.queryADesc = queryADesc;
}

public String getQueryADesc() {
	return queryADesc;
}

public void setQueryEDesc(String queryEDesc) {
	this.queryEDesc = queryEDesc;
}

public String getQueryEDesc() {
	return queryEDesc;
}

public void setExecutableAlertMessage(String executableAlertMessage) {
	this.executableAlertMessage = executableAlertMessage;
}

public String getExecutableAlertMessage() {
	return executableAlertMessage;
}
private DataSet queryNotifierLists ; 
public DataSet getQueryNotifierLists() throws Exception
 {
	if (queryNotifierLists== null )
	{
		queryNotifierLists = this.getQueryNotifierLists(this.queryId, this.getLoggedUser().getUsrCmpIdValue()) ; 
	}
	 return queryNotifierLists ; 
 }

private  DataSet getQueryNotifierLists(String m_queryId , String m_companyId) throws Exception
{
	DataSet result ; 
	String queryStr = "Select * from Support.QUERY_NOTIFIER qn where qn.query_id = " + m_queryId
	 + " and qn.company_id = " + m_companyId ;
	 //ModuleServicesContainer msc = Misc.getModuleServiceContainerFromUserSession(this.getUserSession()) ;
	 result = this.getDbServices().queryForDataSet(queryStr, com.smartValue.support.map.QueryNotifier.class) ;

	return result ; 
	
}
 



private LuQueries luQuery  ;
public LuQueries getLuQuery() throws Exception
{
	if (luQuery == null)
	{
		 
	 String queryStr = "Select * from Support.LU_QUERIES qn where qn.id = " + this.queryId ;
	 luQuery = (LuQueries) this.getDbServices().queryForDataSet(queryStr, com.smartValue.support.map.LuQueries.class).getPersistantObjectList().get(0) ;
	}
	 return luQuery ; 
	
}

private LuExecutables luExecutable  ;
public LuExecutables getLuExecutables() throws Exception
{
	if (luExecutable == null)
	{
	 String queryStr = "Select * from Support.LU_EXECUTABLES qn where qn.id = " + this.queryId ;
	 luExecutable = (LuExecutables) this.getDbServices().queryForDataSet(queryStr, com.smartValue.support.map.LuExecutables.class).getPersistantObjectList().get(0) ;
	}
	 return luExecutable ; 
	
}
public LuQueryDetails getQueryDetail(int m_query_index ) throws Exception
{
  return (LuQueryDetails) this.getLuQuery().getQueryDetailDs().getPersistantObjectList().get(m_query_index) ; 
}

public void setSelectedEnvName(String m_selectedEnvName) throws Exception{
	this.selectedEnvName = m_selectedEnvName;

}

public String getSelectedEnvName() {
	return selectedEnvName;
}

public void setQueryHyperLinkTitle(String queryHyperLinkTitle) {
	this.queryHyperLinkTitle = queryHyperLinkTitle;
}

public String getQueryHyperLinkTitle() {
	return queryHyperLinkTitle;
}

public void setTableName(String tableName) {
	this.tableName = tableName;
}

public String getTableName() {
	return tableName;
}
}
