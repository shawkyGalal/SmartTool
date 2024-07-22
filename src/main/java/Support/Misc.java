package Support;
import java.io.*;
import java.security.SecureRandom;
import java.text.*;
import java.util.*;
import java.sql.*;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import com.implex.database.map.SecUsrDta;
import com.implex.database.map.SysParams;
import com.implex.database.map.TableMaintMaster ;
import com.implex.database.map.services.*;
import com.implex.database.ApplicationContext ;
import com.implex.database.DBKey;
import com.implex.database.DataSet;
import com.implex.database.DbServices;
import com.implex.database.DbTable;
import com.implex.database.MessagesCommnuicatorService;
import com.implex.database.PersistantObject;



public class Misc 
{
  public static String appUrl = "http://epm.algihaz.com:8080/SmartTool/" ; // need To Be Calculated Later from selectedEnvironmentName 
  static Connection con = null;
  public final static String SPECIAL_WORD = "@@@";
 
  
  public Misc() throws Exception
  {
	  
	     
    Validator1.checkExpiry();
  }
  public static  Support.ConnParms getConnectionParamsByName ( String connName ) throws Exception
  {
	  Support.ConnParms result = null ; 
	  Support.XMLConfigFileReader supportConfig =  Misc.getXMLConfigFileReader(false ) ;  
	     java.util.Vector conParms  = supportConfig.connParms  ; 
	     
	     for (int i = 0 ; i< conParms.size() ; i++ ) 
         {
            Support.ConnParms thisConParms = (Support.ConnParms)conParms.elementAt(i);
            if (thisConParms.active.equals("Y"))
             { 
           	 boolean isSameName = connName != null && thisConParms.name.equalsIgnoreCase(connName);
           	 if (  isSameName )  
           	 {
           	 	result =  thisConParms ;
           	 	break ; 
           	 }
             } 
         }
	     
	     return result ; 
  }
  
  public Misc(Connection conec) throws Exception
  {
    Validator1.checkExpiry();
    this.con = conec;
  }
  public static String  renderhtmlTextFildForDate(String name,  String formName, String otherAttributes , String dateFormate)
  {//-----this method render an html text box and an image behind it , by ckliking it a calender window will be opened to select a date,
     //once selected it will close returning the selected date to the text box.
     String htmlStr = "";
      htmlStr = "<script src='/SmartTool/jslib/cabo_utilities.js' language='javascript'></script>" ;
      htmlStr +="<script src='/SmartTool/jslib/calendar_constructor.js' language='javascript'></script>";
      htmlStr +="\n\t <script language='javascript'>";
      htmlStr +="\n\t NLSforNLSformat = '"+dateFormate+"';";
      htmlStr +="</script>";
      htmlStr +="<INPUT type='text' name='"+name+"' , "+otherAttributes+">";
      htmlStr +="<a href='javascript:void opencal(document."+formName+"."+name+");'> ";
      htmlStr +="<img src=/SmartTool/images/FNDICLDR.gif align=absmiddle border=0 alt='Calendar for date entry assistance'></a>";
      return htmlStr;
  }

  public String toHTMLString(String s)
/** This method is to convert spaces in any java String to an HTML String
    it replaces the spaces in the java String with  "&nbsp;" */
{
  StringBuffer descForHTML;
//-------replacing spaces in the desc with "&nbsp;" for Html standard-----------
while (s.indexOf(' ')!=-1)
    {
       descForHTML=  new StringBuffer(s);
       descForHTML.replace(s.indexOf(' '),s.indexOf(' ')+1,"&nbsp;");
       s=descForHTML.toString();
    }
return s;
}
  public static java.util.Date execuldeWeekEnds(java.util.Date startDate, java.util.Date endDate)  //throws Exception
  {
   if( endDate.before(startDate) )
   {
    return startDate;
    //throw new Exception("End Date should be greater than start date ");
   }
   ParsePosition pos =new ParsePosition(0);
   SimpleDateFormat dateFormatter = new SimpleDateFormat ("dd-MM-yyyy HH:mm:ss");// HH indicates Hours in 24 Format while hh indicates Hours in 12 format
   String startDateString = dateFormatter.format(startDate);
   String endDateString = dateFormatter.format(endDate);
   String querystmt = "select misc.get_new_end_date(to_date('"+startDateString+"','dd-mm-yyyy hh24:mi:ss'),to_date('"+endDateString+"','dd-mm-yyyy hh24:mi:ss'),'FRIDAY','THURSDAY') from dual";
   try {
   Statement stmt = con.createStatement();
   ResultSet resSet=  stmt.executeQuery(querystmt);
    while ( resSet.next())
      {
      endDate = resSet.getTimestamp(1);
      }
    resSet.close();
    stmt.close();
    }
  catch (SQLException sqle)
  {System.out.print(sqle.getMessage());}
   return endDate;
  }
 
  public static String repalceAll(String s ,String oldString, String newString)
{
/** This method is to replace a string with another one is not case sensetive */
  if (s== null) return null; 
  StringBuffer descForHTML;
  String sUpper = s.toUpperCase();
  String oldStringUpper = oldString.toUpperCase();
  String remain = s.toUpperCase();

while (remain.indexOf(oldStringUpper)!=-1)
    {
       descForHTML=  new StringBuffer(s);
       descForHTML.replace(sUpper.indexOf(oldStringUpper),sUpper.indexOf(oldStringUpper)+oldStringUpper.length(),newString);
       s=descForHTML.toString();
       remain = descForHTML.toString().substring(descForHTML.indexOf(newString)+ newString.length()).toUpperCase();
       sUpper = s.toUpperCase();
    }
return s;
}
//-------------------------------------------------
public static String replaceSingleQuteWithDouble(String s)
/** This method will replace only middle singlequets if exists (not the first nor the last )
    in any string with double singlequtes
*/
{
  StringBuffer descForHTML;
//-------replacing spaces in the desc with "&nbsp;" for Html standard-----------
int curr=1;
while (curr<s.length()-1 && s.indexOf('\'',curr) >0 && s.indexOf('\'',curr)< s.length()-1)
    {
       descForHTML=  new StringBuffer(s);
       descForHTML.replace(s.indexOf('\'',curr),s.indexOf('\'',curr)+1,"''");
       s=descForHTML.toString();
       curr=s.indexOf('\'',curr)+2;
    }
return s;
}

public String getSystemParameterValue(String m_key) throws Exception
{	// TODO 
	return null;
}

public String getSystemParameterValue(int parmId ) throws Exception
{
  if (this.con == null || this.con.isClosed() )
  {
    throw new Exception("DB Connection does not passed to Support.Misc Class or connection is closed");
  }
    String value="";
    String qs="select val from support.sys_params where id= "+parmId ;
    try{
    Statement stmt = this.con.createStatement();
    ResultSet rs = stmt.executeQuery(qs);
    rs.next();
    value=rs.getString("val");
    stmt.close();
  }
  catch (Exception e) { throw new Exception("Unable to Get System Parmameter ("+qs+") due to : " + e );}
  return value;
}
	public void saveStringToFile(String filePath , String contents) throws Exception 
	{
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filePath)));
		pw.write(contents);
		pw.close();
	}
	
	public static Connection  getAutoLoginConnection(SecUsrDta loggedUser) throws Exception
	{
		DataSet sysParams = loggedUser.getUserCompany().getSysParams(); 
		XMLConfigFileReader xcgr =  Misc.getXMLConfigFileReader(false ) ;  
		
 		String autologinDB 		 = ((SysParams) sysParams.getFirstFilteredPO(SysParams.E_NAME, "AutoLogin_Environment_Name")).getValValue(); // SysConfigParams.getAutoLogin_Environment_Name();
 		String autoLoginUserName = ((SysParams) sysParams.getFirstFilteredPO(SysParams.E_NAME, "AutoLogin_Username")).getValValue(); //SysConfigParams.getAutoLogin_Username();
 		String autoLoginPassword = ((SysParams) sysParams.getFirstFilteredPO(SysParams.E_NAME, "AutoLogin_Password")).getValValue(); // SysConfigParams.getAutoLogin_Password();
 
 		return xcgr.getConnParmsByName(autologinDB).generateConnection(autoLoginUserName, autoLoginPassword , "NORMAL");

	}

 public static String getSqlBoundVarValue(String m_connName , String m_sqlBoundvarName ) throws Exception
 {
	 String result = null ;
	 Vector<SqlBindVar> sqlBindVars = getConnectionParamsByName(m_connName).getSqlBindVars() ; 
		for (int i = 0 ; i < sqlBindVars.size() ; i++ )
		{
			SqlBindVar xx =  sqlBindVars.get(i);
			if (xx.bindVarName.equalsIgnoreCase(m_sqlBoundvarName))
			{
				result =  xx.bindVarValue ; 
			}
			
		}
		return result ; 
 }
  public static void main(String[] arg) throws Throwable
  {
	String result =   getSqlBoundVarValue("PNU_PROD" , "appURL") ; 
	
    System.out.print(Misc.repalceAll("Shawky Galal Foda"," ","%20"));
    
   //Connection  con = ConnectionFactory.createConnection(ComplaintSettings.dBHostName,ComplaintSettings.dBServiceName,ComplaintSettings.dBUsername,ComplaintSettings.dBPassword);
   //java.util.Date newEnddate =  new Misc(con).execuldeWeekEnds(new java.util.Date(200550000000L),new java.util.Date());
   //con.close();
  }
  public static final String MscSessionKey = "moduleServiceContainer" ;
  public static final String selectedConnParmsSessionKey = "selectedConnParms" ; 
  public static ModuleServicesContainer getModuleServiceContainer(String ENV_NAME , int LANG ) throws Exception
  {
	 ModuleServicesContainer msc = null ;
 
	 try{
		 	msc =  ApplicationContext.generateModuleServicesContainer(ENV_NAME , LANG) ;
		 	//ApplicationContext.setClientModuleServicesContainer(msc); 
			//com.implex.database.map.services.SecUserDataService secUsrDtaServices = msc.getSecUserDataService();
			/*
			SecUsrDta loggedUser = secUsrDtaServices.getUserByUserName(userName); 
			//msc.getDbServices().setLoggedUser(loggedUser);
	 		ApplicationContext.setLoggedUser(loggedUser);
	 		//ApplicationContext.setClientModuleServicesContainer(msc) ;
	 		*/
		       
	 }
	 catch (Exception e) {throw new Exception ("Unable t Read Table Meta Data Due to :" + e ) ; }
	return msc;
  }
  
  public static PersistantObject getPersObjectByRowId (javax.servlet.http.HttpSession m_session, String m_table_owner , String m_table_name , String m_rowid)
  {
	PersistantObject po = null ; 
	ModuleServicesContainer msc;
	try 
	{
		msc = getModuleServiceContainerFromUserSession ( m_session);
		DbServices dbs =  msc.getDbServices() ;
		String queryStr = " Select t.* , t.rowid from " + m_table_owner + "." + m_table_name + " t Where t.rowid = '" + m_rowid + "'" ;  
		ArrayList<PersistantObject>  list = dbs.queryForDataSet(queryStr, null).getPersistantObjectList();
		if (list != null && ! list.isEmpty())
		{
			po = list.get(0);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	  
	  return po; 
	    
  }
  public static PersistantObject getPersObjectByRowId (ModuleServicesContainer msc, String m_table_owner , String m_table_name , String m_rowid , Class pm_class )
  {
	PersistantObject po = null ; 

	try 
	{
		DbServices dbs =  msc.getDbServices() ;
		String queryStr = " Select t.* , t.rowid from " + m_table_owner + "." + m_table_name + " t Where t.rowid = '" + m_rowid + "'" ;  
		ArrayList<PersistantObject>  list = dbs.queryForDataSet(queryStr, pm_class).getPersistantObjectList();
		if (list != null && ! list.isEmpty())
		{
			po = list.get(0);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	  
	  return po; 
	    
  }

  public static ModuleServicesContainer getModuleServiceContainerFromUserSession ( javax.servlet.http.HttpSession session) throws Exception
  {
		ModuleServicesContainer msc = (ModuleServicesContainer) session.getAttribute(Misc.MscSessionKey);  
		if (msc == null)
		{
			Support.ConnParms selectedConnParms = (Support.ConnParms)session.getAttribute(Misc.selectedConnParmsSessionKey);
		    String ENV_NAME = selectedConnParms.name;
		  	int LANG = 1 ;
			msc = Misc.getModuleServiceContainer(ENV_NAME , LANG );
			session.setAttribute(Support.Misc.MscSessionKey , msc); 
		}
		return msc; 
  }
  
  public static TableMaintMaster getTableMaintMaster( javax.servlet.http.HttpSession m_session , String owner , String tableName  ) throws Exception
  {
	  ModuleServicesContainer msc = getModuleServiceContainerFromUserSession ( m_session) ;
	  com.implex.database.map.services.TableMaintMasterServices tmms = msc.getTableMaintServices();

		//tmms.setTableOwner(owner.toUpperCase());
		//tmms.setTableName(tableName.toUpperCase());
		DbTable dbt = new DbTable(owner.toUpperCase() , tableName.toUpperCase() , Misc.getModuleServiceContainerFromUserSession(m_session).getDbServices() ) ;
	    TableMaintMaster tmm =  tmms.getTableMaintMaster(dbt); // tmms.getCurrentTable() ; 
	    
	    return tmm ; 
  }
  public static final String LoginScreenPageNameCookiVarName = "loginScreenPageName" ;  
  public static Cookie getCookiByName(javax.servlet.ServletRequest request , String cookieName )
  {
	  Cookie result = null ;
	  Cookie cookies [] = ((javax.servlet.http.HttpServletRequest)request).getCookies ();
	  
	  if (cookies != null)
	  {
		  for (int i = 0; i < cookies.length; i++) 
		  {
			  if (cookies [i].getName().equals (cookieName))
			  {
				  result = cookies[i];
				  break;
			  }
		  }
	  }
	  
	  return result ; 
  }
 
  public static String getConnectionUserName ( Connection m_con) throws SQLException
  {
	  String result = null ;
	  if (m_con !=null)
	  result = m_con.getMetaData().getUserName(); 
	  return  result ; 
  }
  public static String getRequestOrigin(HttpServletRequest request)
  {
	  String v_result ; 
	  String requestURI = request.getRequestURI() ; 
	 
	  StringBuffer requestURL = request.getRequestURL() ; 
	  int index = requestURL.indexOf(requestURI) ; 
	  v_result = (index >0 )? requestURL.substring(0,index) : requestURL.toString() ;
	  return v_result ; 
  }
  
  public static String getAppURL(HttpServletRequest request)
  {
	  return "/SmartTool" ; 
	  /*
	  String v_result ; 
	  String servletPath = request.getServletPath() ; 
	  StringBuffer requestURL = request.getRequestURL() ; 
	  int index = requestURL.indexOf(servletPath) ; 
	  v_result = (index >0 )? requestURL.substring(0,index) : requestURL.toString() ;
	  return v_result ;
	  */ 
	  
  }
   
  public static XMLConfigFileReader xMLConfigFileReader ; 
  public static XMLConfigFileReader getXMLConfigFileReader(boolean m_schemaValidate) throws Exception
  {
  	  if (xMLConfigFileReader == null) 
  	  {xMLConfigFileReader =  new XMLConfigFileReader( m_schemaValidate ) ;}
  	  return xMLConfigFileReader ; 
  }	
  
  public static boolean isRecordLockedByApproval( String m_tableOwner , String m_tableName , String m_rowid , Connection m_con) throws SQLException
  {
	  String checkForApprovalQueryStr = " Select support.APPROVAL_SERVICES.get_approval_status('"+m_tableOwner+"' , '"+m_tableName+"' , '"+m_rowid+"' , 1) "
		+ ", support.APPROVAL_SERVICES.get_approval_status('"+m_tableOwner+"' , '"+m_tableName+"' , '"+m_rowid+"' , 2) "
		+ ", support.APPROVAL_SERVICES.get_approval_status('"+m_tableOwner+"' , '"+m_tableName+"' , '"+m_rowid+"' , 3) "
		+ " From Dual " ;
		boolean isRecordLockedByApproval = false ; 
		
		Statement stmt1 = null ; 
		ResultSet approvalStatusResSet = null ; 
		try {
			stmt1 = m_con.createStatement() ; 
			approvalStatusResSet =  stmt1.executeQuery(checkForApprovalQueryStr);
			while ( approvalStatusResSet.next())
			{
			String level1Status = approvalStatusResSet.getString(1); boolean level1Locked = level1Status !=null && level1Status.equals("1");
			String level2Status = approvalStatusResSet.getString(2); boolean level2Locked = level2Status !=null && level2Status.equals("1"); 
			String level3Status = approvalStatusResSet.getString(3); boolean level3Locked = level3Status !=null && level3Status.equals("1");
			isRecordLockedByApproval =  level1Locked || level2Locked || level3Locked ; 
			}
		}
		catch ( Exception e ) {if (approvalStatusResSet != null) approvalStatusResSet.close() ; 
							   if (stmt1!= null) stmt1.close() ; 
							   }
		finally {if (approvalStatusResSet != null) approvalStatusResSet.close() ; 
		   		 if (stmt1!= null) stmt1.close() ; }
		
		return isRecordLockedByApproval ; 
  }
  
  public static String getRecordUniqueWhereClaue(Connection m_con , String tableOwner , String tableName , String selectedOraRowId)
  {
		String uniqueWhereCaluse = null ; 
		java.sql.ResultSet rs1 = null; 
		java.sql.Statement s1 = null ; 
		try {
			String qs = "Select Support.Misc.get_object_unique_where_clause ('"+tableOwner+"' , '"+tableName+"' , '"+selectedOraRowId+"' ) from dual " ; 
			s1 = m_con.createStatement(); 
			rs1 =  s1.executeQuery(qs); 
			while (rs1.next())
			{
				uniqueWhereCaluse = rs1.getString(1) ; 
			}
		}
		catch (Exception e ) {
				try {
				if (rs1 != null) rs1.close(); 
				if (s1 != null) s1.close(); 
				}
				catch (SQLException sqle) {}
			 }
		try {
			if (rs1 != null) rs1.close(); 
			if (s1 != null) s1.close(); 
			}
			catch (SQLException sqle) {}
		uniqueWhereCaluse = Support.Misc.repalceAll( uniqueWhereCaluse , "'" , "$$$$" ) ;  
		uniqueWhereCaluse = Support.Misc.repalceAll( uniqueWhereCaluse , "$$$$" , "''" ) ;
		
		return uniqueWhereCaluse ;
  }
  
  public static void initializeMainSystemTrees( HttpServletRequest request 
		   							, HttpServletResponse response 
		   							, HttpSession session 
		   							, ServletContext application 
		   							, Writer out
		   							 ) throws Exception 
  {
	  Support.LookupTreeV10 queriesTree ;
	  Support.LookupTreeV10 executeTree ;
	  String treeIdInSession = "queriesTree" ; 
	  queriesTree = new Support.LookupTreeV10(treeIdInSession);
	  queriesTree.setImagepath("images/");
	  queriesTree.setAddFormUrl("queryInsert.jsp");
	  queriesTree.setTargetFrame("mainFrame");
	  queriesTree.setAddFormTargetFram("mainFrame");
	  queriesTree.setShowAddLink(true); queriesTree.setShowToolBar(true);
	  queriesTree.sethyperlinkLastTableItemsOnly(true);
	  queriesTree.showRecycleBinButton(false);
	  queriesTree.setShowCheckBox(false); queriesTree.setShowMoveForm(true);
	  //---------This Section to Display only Node for the logged User Standard -------- 
	  java.util.Vector nodesIncluded = new java.util.Vector();
	  Connection con = (Connection) session.getAttribute("con");
	  String loggedUer =con.getMetaData().getUserName().toUpperCase() ; 
	  String query = "select id from SUPPORT.LU_QUERIES where code = '" + loggedUer +"'" ; 
	  	Connection repCon = (Connection) session.getAttribute("repCon");  
	  	java.sql.Statement stmt =  repCon.createStatement();
	  	java.sql.ResultSet rs = null;
	      try 
	  	{
	  	    rs = stmt.executeQuery( "select id from Support.LU_QUERIES where code = '" + loggedUer +"'" );
	      	try {
	          	while ( rs.next() ) {  nodesIncluded.addElement("LU_QUERIES"+rs.getString("id"));	}
	      	} 
	      	finally { try { rs.close(); } catch (Exception ignore) { } }
	  	} 
	  	finally { try { stmt.close(); } catch (Exception ignore) { } }
	  nodesIncluded.addElement("LU_QUERIES305");

	  //---------Include The Standard Node 
	  queriesTree.includeOnlySubNodes(nodesIncluded);
	  //nodesIncluded 
	  //---------This Section to Display only Node for the logged User + Standard --------
	  queriesTree.initialize(application,session,request,response, (JspWriter)out);
	  queriesTree.setDBConnection(repCon);

	  queriesTree.setTreeData("LU_QUERIES",false); 
	  if (request.getParameter("nodeToBeToggled") == null)
	  {
	     com.implex.database.map.SecUsrDta loggedUser1 = (com.implex.database.map.SecUsrDta) session.getAttribute("loggedUser") ;
	  	try { // By default open Carrent system nodes 
	  		String[] treeOpenNodes = loggedUser1.getUsrUd1Value().split(",");
	  		for (String nodeId : treeOpenNodes )
	  		{
	  			queriesTree.TogNodesStatusAsBitSet(queriesTree.getindex2("LU_QUERIES" + nodeId ) ); // Tree opened
	  		}
	  	   }
	  		catch (Exception e ){System.out.println(e.getMessage());}
	  }
	  session.setAttribute("queriesTree" , queriesTree);

	  
	  //----------This Method internally check if the reload button pressed or not 

	  //session.setAttribute("queriesTree", queriesTree); 
	  //End of Query Tree Initialization

	  //********************************************************************


	  //Start of Executables Tree Initialization 
	  executeTree = new Support.LookupTreeV10("executeTree"); executeTree.setImagepath("images/");
	  executeTree.setTargetUrl("editAndExecuteExecutable.jsp");
	  executeTree.setAddFormUrl("executableInsert.jsp");
	  executeTree.setTargetFrame("mainFrame");
	  executeTree.setAddFormTargetFram("mainFrame");
	  executeTree.setShowAddLink(true); executeTree.setShowToolBar(true);
	  executeTree.sethyperlinkLastTableItemsOnly(true);
	  executeTree.showRecycleBinButton(false);
	  executeTree.setShowCheckBox(false); executeTree.setShowMoveForm(true);
	  //---------This Section to Display only Node for the logged User + Standard -------- 
	  nodesIncluded = new java.util.Vector(); 
	      stmt =  repCon.createStatement();
	      try 
	  	{
	  	    rs = stmt.executeQuery( "select id from Support.lu_executables where code = '" + loggedUer +"'" );
	      	try {
	          	while ( rs.next() ) {  nodesIncluded.addElement("LU_EXECUTABLES"+rs.getString("id"));	}
	      	} 
	      	finally { try { rs.close(); } catch (Exception ignore) { } }
	  	} 
	  	finally { try { stmt.close(); } catch (Exception ignore) { } }

	  nodesIncluded.addElement("LU_EXECUTABLES20161"); 
	  //---------Include The Standard Node 
	  executeTree.includeOnlySubNodes(nodesIncluded);
	  //nodesIncluded 
	  //---------This Section to Display only Node for the logged User + Standard --------

	  executeTree.initialize(application,session,request,response,(JspWriter)out );
	  executeTree.setDBConnection(repCon);
	  executeTree.setTreeData("LU_EXECUTABLES",false); 
	  session.setAttribute("executeTree" , executeTree);
	  //----------This Method internally check if the reload button pressed or not

	  //End of Executables Tree Initialization 

  }
   // Return Logged User  
  public static SecUsrDta getLoggedUserFromSession(HttpSession session )
  {
	  SecUsrDta result = null ; 
	  result = (SecUsrDta) session.getAttribute("loggedUser") ;
	  return result  ;  
	  
  }
  
  public static String generateRandomPassword(int length )
  {
	  String result ; 
	  String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	 SecureRandom rnd = new SecureRandom();

	StringBuilder sb = new StringBuilder( length );
	 for( int i = 0; i < length; i++ ) 
	 {
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	 }
	 result =  sb.toString();
	 
	 return result ; 
  }
  
  public static String removeParamFromQueryStr(String m_paramName , HttpServletRequest m_request )
  {
	  String result = null ; 
	  String queryStr = m_request.getQueryString() ; 
	  int startIndex = (queryStr !=null)? queryStr.indexOf(m_paramName) : -1;
	  if (startIndex < 0 )
	  {
		  result = queryStr ; 
	  }
	  else 
	  { 
		  boolean andExists =  (queryStr.substring(startIndex -1 , startIndex).equals("&") )  ; 
		  if (andExists )  startIndex = startIndex -1 ; 
		  int endIndex = queryStr.indexOf("&" , startIndex +1 ) ;
		  if ( endIndex < 0 ) endIndex = queryStr.length() ;  
		  String paramNameAndValue = queryStr.substring(startIndex, endIndex) ; 
		  result = queryStr.replace(paramNameAndValue, ""); 
	  }
	  
	  return result ; 
  }
  
  public static void saveUploadedFile ( HttpSession session , HttpServletRequest request , HttpServletResponse response, JspWriter out ,  ServletContext application ) throws Exception
  {
	  	String dirPathVarName = "dirPath" ;
	  	String userdir = (String)session.getAttribute(dirPathVarName) ;//"XLSUploading\\Data";
	    
	  	StringTokenizer st = new StringTokenizer(userdir , "/");
	    String tableOwner = st.nextToken() ;
	    tableOwner = st.nextToken() ;
	    String tableNamex = st.nextToken() ;
	    int keyValueStartIndex = userdir.indexOf(tableOwner+"/"+tableNamex) + (tableOwner+"/"+tableNamex).length()+1; 
	    String keyValue = userdir.substring(keyValueStartIndex) ;
	    Connection con = (Connection)session.getAttribute("con");
	    SecUsrDta loggedUserObj = Misc.getLoggedUserFromSession(session) ; 
	    String loggedUser = loggedUserObj.getUsrNameValue() ; //Misc.getConnectionUserName(con);
	    if (loggedUser.equalsIgnoreCase("ANONYM")) {con.commit();} // In case of Anonymous login force commit
	    PersistantObject po =  Misc.getPersObjectByRowId(session , tableOwner , tableNamex , keyValue) ;
	    MessagesCommnuicatorService messageCS= loggedUserObj.getDbService().getModuleServiceContainer().getMessageCommnunicatorService() ;
	    if (po == null ) 
	    {
	    	
	    	messageCS.sendMessageToUser("Object Not Found.. You May Not Saved Your Object, You Can Not add an attachement to object before save, try to Save it first " + po + "Has No Unique Key Defined");
	    	//out.print("Object Not Found.. You May Not Saved Your Object, You Can Not add an attachement to object before save, try to Save it first " + po + "Has No Unique Key Defined") ;
	    	return ; 
	    }
	    DBKey objectUniqueKey = po.getUniqeKey() ; 
	 
	    if ( objectUniqueKey == null ) 
	    {
	    	messageCS.sendMessageToUser("Object " + po + "Has No Unique Key Defined"); 
	    	// out.print("Object " + po + "Has No Unique Key Defined") ;
	    	return ; 
	    }
	    String objectUniqueKeyStr = objectUniqueKey.toFoldersPath(); 
	    
	    String uploadDistination = Misc.repalceAll(userdir , keyValue , Misc.repalceAll( objectUniqueKeyStr , ":" , "-" )  ) ; 
	  	File userDirFullPath =  new File ( session.getServletContext().getRealPath("")+ File.separator + uploadDistination ) ;
	  	userDirFullPath.mkdirs() ; 
		oracle.jsp.webutil.fileaccess.HttpUploadBean upbean = new oracle.jsp.webutil.fileaccess.HttpUploadBean();
		upbean.setDestination(uploadDistination);   
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
	     
		  // java.sql.Connection  con = (java.sql.Connection)session.getAttribute("con");
		  if( con == null || con.isClosed())
		  { 
			  session.setAttribute("queryString",request.getQueryString());
		      response.sendRedirect("loginScreen.jsp");
		  }
		  
	     java.util.Enumeration fileNames = upbean.getFileNames();
	     String fileName = "";
	     //boolean noDBrecord = Support.SqlReader.getValueFromRequestOrSession( "NoDBRecord" , request, session).equalsIgnoreCase("Y"); //request.getParameter("NoDBRecord") != null && request.getParameter("NoDBRecord").toString().equalsIgnoreCase("Y") ; 
	     String objectWhereClause = Support.Misc.getRecordUniqueWhereClaue(con , tableOwner , tableNamex , keyValue ) ; 
	     
	     while (fileNames.hasMoreElements())
	     {
	      fileName = fileNames.nextElement().toString();
	      //---------Create a corresponding record in DB--------------------
	      //if (! noDBrecord) 
	      {

	      	String uploadTo = userDirFullPath.getAbsolutePath() ;
	      	uploadTo = Misc.repalceAll( uploadTo , "'" , "$$$$" ) ;
	      	uploadTo = Misc.repalceAll( uploadTo , "$$$$" , "''" );
	      
	      	fileName = Misc.repalceAll( fileName , "'" , "$$$$" ) ;
	      	fileName = Misc.repalceAll( fileName , "$$$$" , "''" );
	   	  	
	      	String createStmt = "Insert into Support.TABLE_ATTACHEMAT (table_owner , table_name , key_vale, OBJ_WHERE_CLAUSE , file_name,uploaded_to) " 
		  		+ " Values ('"+tableOwner+"' , '"+tableNamex.toUpperCase()+"' , '"+keyValue+"', '"+objectWhereClause+"', '"+fileName+"' , '"+uploadTo+"')";

	   		Statement stmt = null; 
		  	try 
		  	{ 	con.setAutoCommit(false);
		  		stmt = con.createStatement();
		  		stmt.execute(createStmt); 
		  	}
		  	catch (Exception e) 
		  	{
		  		if (stmt != null ) stmt.close();
		  		throw new Exception ("Unable to Execute :" + createStmt + " <br>Due To " + e.getMessage()) ; 
		  	}
	      }
	    //---------End of Create a corresponding record in DB -------------
	    
	     }

  }
}