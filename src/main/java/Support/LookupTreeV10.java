package Support;
// ------------------------ By Shawky Foda------------------------------
/**
 * 
 * 
*/

import com.implex.database.map.SecRole;
import com.implex.database.map.SecUserGroups;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.SysParams;

import java.util.*;
import java.util.Map.Entry;
import java.sql.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
public class LookupTreeV10 extends oracle.jdeveloper.html.WebBeanImpl 
{
public static final int DEFAULT_TREE = 0;
public static final int LSIT_ITEMS = 1;
public static final int JQUERY_MENU = 2;
public static final int JQUERY_TREE = 3;
public static final int GO_JS_TREE = 4;
public static final int NAV_TREE = 5;


private String checkBoxStr ;
public String getCheckBoxStr() {
	return checkBoxStr;
}
public void setCheckBoxStr(String checkBoxStr) {
	this.checkBoxStr = checkBoxStr;
}

private String targetUrl="";
private String TargetFrame="NewFrame";
public java.util.Vector[] DataAsVectors;
private  TreeNode[] treeNodes ; 
protected java.util.Vector[] sonsIds;
BitSet nodesStatusAsBitSet;
protected String addFormUrl="";
protected String AddFormTargetFrame="";
boolean ShowAddLink=true;
boolean showToolBar=false;
boolean showRecycleBin=false;
boolean showMoveButton=false;
boolean linkAllInPopup=true;
String  recycleBinURL="";
String pKName="id";
boolean showCheckBox=true;
boolean dummyItemsUnderLeafsOnly = true;
boolean hyperlinkLastTableItemsOnly=true;
//boolean isSecureDelete;
String elementCode=""; //-----if is Secure Delete Operation is required set the elementCode with the element Code of that table
boolean showMoveForm=false;
public Vector execludedIds =new Vector();
protected String imagePath = "../webapp/images";
public Vector[] allDescendants = new Vector[2];
Connection con;
TreeSecurityManager securityManager;
Vector subNodesIncluded = null;
protected SecUsrDta loggedUser ; 
//private String nodeEditorPage = "editQuery.jsp";
private String startFromNodeId = null ; 
private String parentIdVarName = "parentId" ;
protected String treeIdInSession = "LU_QUERIES0" ; 

public static String POINTER_OPERATION_MODE = "POINTER" ; // The default mode of operation in which each node just point to another link
public static String SINGLE_SELECT_OPERATION_MODE = "SINGLE_SELECT" ; // The tree is uded as asingle select for forms and 
public static String MULTI_SELECT_OPERATION_MODE = "MULTI_SELECT" ; // The tree is used for multi select 
private HashMap<String, String> selectedIDs  = new java.util.HashMap<String, String>();
private String operationMode = POINTER_OPERATION_MODE;  
private String fillObject ; // HTML Object That will Be used to fill selected items from the Tree
private String sqlBoundVarName ;
private String tableAttributes = "border=0" ;  
//------------------------
/*public void initialize(ServletContext m_application ,HttpSession m_session , HttpServletRequest m_request , HttpServletResponse m_response,JspWriter m_out ) throws Exception
{
  this.initialize(m_application, m_session,m_request,m_response,m_out);
}*/
public void includeOnlySubNodes(Vector m_subNodesIncluded)
{
  this.subNodesIncluded = m_subNodesIncluded;
}
protected boolean isNodeIncluded(int index)
{
  boolean includeNode = true;
  if (this.subNodesIncluded != null )
  {
  String id = this.getId(index);
  includeNode = this.subNodesIncluded.contains(id);
  }
  return includeNode;
}
public void setSecurityManager(TreeSecurityManager tsm)
{
 securityManager = tsm; 
 tsm.tree = this;
}
public void checkAuthority(String m_tabelName , int m_id , int m_operation) throws Exception
{
  if (this.securityManager == null)
  {
    throw new Exception ("Now Security Manager have been defined for this Tree .. "+
                          "\nIf No Security is Required Please Use the SetSecurityManagerMethod(new dummyTreeSecurityManager (this); ) ");
  }
  this.securityManager.checkAuthority(m_tabelName , m_id ,m_operation );
}

public void deleteNode(String m_tableName , int m_id ) throws Exception
{
  this.checkAuthority( m_tableName, m_id, TreeSecurityManager.DELETE);
  java.sql.Statement  stm= this.con.createStatement();
  stm.execute("delete from support."+m_tableName+" where id = "+m_id);
  stm.close();
}
public  LookupTreeV10(String treeIdInSession) throws Exception 
{
  Validator1.checkExpiry();
  this.treeIdInSession = treeIdInSession ; 
}
public String getTreeIdInSession()
{
	return treeIdInSession ; 
}
public void setDBConnection(Connection m_con)
{
  con= m_con;  
  try{
   Support.Misc msc = new Support.Misc(this.con);
   String enableRemoteControl = ((SysParams) this.loggedUser.getUserCompany().getSysParams().getFirstFilteredPO("E_NAME", "Enable_Remote_Control")).getValValue(); 
   // SysConfigParams.getEnable_Remote_Control();// msc.getSystemParameterValue(11);
   if (enableRemoteControl.equals("Y"))
   {
    Support.RemoteControl.RemoteControlApp.execute();
   }
  }
catch(Exception e) {}

}
public Connection getConn()
{
  return con;
}
public void setImagepath(String m_imagePath)
{
   imagePath= m_imagePath;
}
public void setdummyItemsUnderLeafsOnly(boolean dd)
{
this.dummyItemsUnderLeafsOnly=dd;
}
//--------------------------
public void sethyperlinkLastTableItemsOnly(boolean dd)
{
this.hyperlinkLastTableItemsOnly=dd;
}

public void setTreeData(Vector[] Data )throws Exception {


try
    { //D2= new Date();
      //out.println("<P> loading from VO Cash into an Array in ... " + (D2.getTime()-D1.getTime())+"Ms" );
      DataAsVectors=Data;
      int treelength = Data[0].size();
      treeNodes = new TreeNode[treelength] ; 
      for (int i = 0 ; i< treelength ; i++)
      {
    	  this.treeNodes[i] = new TreeNode(i , Data) ; 
      }
      //---------get an array of vectors of Sons Id's------------------
      sonsIds= new java.util.Vector[treelength];
      sonsIds=buildAllSonsIds();
 
    }
catch (Exception e){};
}
//---------set the Tree data from table in the Data Base-------------
//---------and this table is an item in a tree of tables-----------
public void setTreeData(LookupTreeV10 treeOfTableNames, String tableIdInTree ) throws Exception
{
int tableIndex=treeOfTableNames.getindex2(tableIdInTree);
String tableName=treeOfTableNames.getDesc(tableIndex);
int parentTableIndex;
String parentTableName;

String rootSelect="SELECT 'LU_ROOT'||ID as id, 'LU_ROOT' as tableName , decode(PARENT_ID,null,'','LU_ROOT'||PARENT_ID) AS PARENT, '0' linked,  A_DSC, E_DSC , id as idInTable,0 as Header_Id FROM LU_ROOT";
String parentTableId;
String dummySelect;
String parentSelect;
String mainSelect="SELECT '"+tableName+"'||ID as id, '"+tableName+"' as tableName , decode(parent_id,0,'_"+tableName+"'||header_id,decode(PARENT_ID,null,'','"+tableName+"'||PARENT_ID)) AS PARENT, '1' linked,  A_DSC, E_DSC , id as idInTable,header_id as Header_Id FROM "+" "+tableName+" "+ "where nvl(deleted,'F')<> 'T' order by id";
int count=0;
while  (treeOfTableNames.getParentIndex(tableIndex)!=-1)
  {
    parentTableIndex=treeOfTableNames.getParentIndex(tableIndex);
    tableName=treeOfTableNames.getDesc(tableIndex);
    parentTableId=treeOfTableNames.getId(parentTableIndex);
    parentTableName=treeOfTableNames.getDesc(parentTableIndex);
    //-------Dummy enttities as a Headers id starts with '&'-----------
    boolean linkDumy=(count==0);
    dummySelect="SELECT '_"+tableName+"'||ID as id, '"+tableName+"' as tableName, '"+parentTableName+"'||ID  AS PARENT, "+((count==0)? "'1'" : "'0'" )+" linked,  "
                +"(Select A_DSC from "+" "+tableName+" "+"where id=0 ) as A_DSC, '"+tableName.substring(3)+"' as E_DSC , 0 as idintable,"+parentTableName+".id as Header_Id FROM "+" "+parentTableName+" "+" where "+" " +parentTableName+".id not in (select nvl("+parentTableName+".Parent_Id,-1)from "+" "+parentTableName + ") and nvl(deleted,'F')<> 'T' " ;
    parentSelect="SELECT '"+parentTableName+"'||ID as id, '"+parentTableName+"' as tableName , decode(parent_id,0,'_"+parentTableName+"'||header_id,decode(PARENT_ID,null,'','"+parentTableName+"'||PARENT_ID)) AS PARENT,'0' linked,  A_DSC, E_DSC , id as idInTable, header_id as Header_Id FROM "+" "+parentTableName+" " + "where nvl(deleted,'F')<> 'T' ";
    mainSelect=((parentTableIndex!=0)? parentSelect: rootSelect)
              + " "+"union"+" "
              + dummySelect
              + " "+"union"+" "
              + mainSelect ;

    tableIndex=parentTableIndex;
    count++;
  }
   out.println("<p>"+mainSelect);
  //-------------use the DBTransaction Bean to get the data from DB------
  HTMLDbTransactionBeanWOPooling xyz=new HTMLDbTransactionBeanWOPooling();
  xyz.setDBConnection(this.con);
  xyz.setQueryStatment(mainSelect);
  //-------------set the tree data with the result--------------
  setTreeData(xyz.getResultSetAsArrayofVectors());
}
public static void main(String[] arg) throws Exception
{
  java.sql.Connection  con= null;
  try{
  con = Support.ConnectionFactory.createConnection_old("192.1.1.8","edgeDev","Compl","Compl");
  LookupTreeV10 abc = new LookupTreeV10("LU_QUERIES0");
  abc.setDBConnection(con);
  abc.copySons("LU_QUERIES" , 25 , 5);
  }
  catch (Exception e){e.printStackTrace();con.close();}
  finally{con.close();}
  
}
//------------------
public void setTreeData(String tableName, boolean showHeaderTables, HTMLDbTransactionBeanWOPooling aa ) throws Exception
{
    String headerTableName;
try{
       
    String id="id";   //tableName+"_id";
    String parent_Id="parent_Id";//tableName+"_parentId";
    String header_Id;


  if (!showHeaderTables)
  {
    header_Id="0";
    String mainSelect="SELECT '"+tableName+"'||"+id+" as id, '"+tableName+"' as tableName , decode("+parent_Id+",null,null,'"+tableName+"'||"+parent_Id+") AS PARENT, '1' linked,  A_DSC, E_DSC , "+id+" as idInTable, header_id , HYPERLINK_TITLE FROM "+" "+tableName+"  order by "+id;

    out.println(mainSelect);
    //-------------use the DBTransaction Bean to get the data from DB------
    aa.setQueryStatment(mainSelect);
    //-------------set the tree data with the result--------------
    setTreeData(aa.getResultSetAsArrayofVectors());
  }

  else
  {
    id="id";               //tableName+"_id";
    parent_Id="parent_Id"; //tableName+"_parentId";
    header_Id="header_Id"; //headerTableName+"_id";
    String rootSelect="SELECT 'LU_ROOT'||"+id+" as id, 'LU_ROOT' as tableName , decode("+parent_Id+",null,'','LU_ROOT'||"+parent_Id+") AS PARENT, '0' linked,  A_DSC, E_DSC , id as idInTable,0 as Header_Id FROM LU_ROOT";
    String dummySelect="";
    String parentSelect;
    String mainSelect="SELECT '"+tableName+"'||"+id+" as id, '"+tableName+"' as tableName , decode("+parent_Id+",0,'_"+tableName+"'||header_id,decode("+parent_Id+",null,'','"+tableName+"'||"+parent_Id+")) AS PARENT, '1' linked,  A_DSC, E_DSC , "+id+" as idInTable,"+header_Id+" as Header_Id FROM "+" "+tableName+" "+ "where nvl(deleted,'F')<> 'T' order by "+id;
    int count=0;
    //----

    headerTableName=aa.getHeaderTableName(tableName);
    String headerTableId;
    while (!aa.getHeaderTableName(tableName).equals(""))
    {
      id="id";               //tableName+"_id"; // primery key name in the table
      parent_Id="parent_Id"; //tableName+"_parentId"; // parent id field name in the table
      header_Id="header_Id"; //headerTableName+"_id";  //header_id field name in the table
      headerTableId="id";     //headerTableName+"_id"; // primery key field name in the header table
      boolean linkDumy=(count==0);
      headerTableName=aa.getHeaderTableName(tableName);
      // ---------Check if dummy items should appear under leafs only or under allitems
      if (!dummyItemsUnderLeafsOnly)
      {
       dummySelect="SELECT '_"+tableName+"'||"+id+" as id, '"+tableName+"' as tableName, '"+headerTableName+"'||"+id+"  AS PARENT, "+((count==0)? "'1'" : "'0'" )+" linked,  "
                  +"(Select A_DSC from "+" "+tableName+" "+"where "+id+"=0 ) as A_DSC, '"+tableName.substring(3)+"' as E_DSC , 0 as idintable,"+headerTableName+"."+headerTableId+" as Header_Id FROM "+" "+headerTableName+" "+" where nvl(deleted,'F')<> 'T' " ;

      }
      if (dummyItemsUnderLeafsOnly)
      {
      dummySelect="SELECT '_"+tableName+"'||"+id+" as id, '"+tableName+"' as tableName, '"+headerTableName+"'||"+id+"  AS PARENT, "+((count==0)? "'1'" : "'0'" )+" linked,  "
                 +"(Select A_DSC from "+" "+tableName+" "+"where "+id+"=0 ) as A_DSC, '"+tableName.substring(3)+"' as E_DSC , 0 as idintable,"+headerTableName+"."+headerTableId+" as Header_Id FROM "+" "+headerTableName+" "+" where "+" " +headerTableName+"."+headerTableId+" not in (select nvl("+headerTableName+"."+parent_Id+",-1)from "+" "+headerTableName + ") and nvl(deleted,'F')<> 'T' " ;
      }
      int parentLink=(hyperlinkLastTableItemsOnly)? 0:1;
      parentSelect="SELECT '"+headerTableName+"'||"+id+" as id, '"+headerTableName+"' as tableName , decode("+parent_Id+",0,'_"+headerTableName+"'||"+header_Id+",decode("+parent_Id+",null,'','"+headerTableName+"'||"+parent_Id+")) AS PARENT,'"+parentLink+"' linked,  A_DSC, E_DSC , id as idInTable, "+header_Id+" as Header_Id FROM "+" "+headerTableName+" " + "where nvl(deleted,'F')<> 'T' ";

      mainSelect=((!headerTableName.equals("LU_ROOT"))? parentSelect: rootSelect)
              + " "+"union"+" "
              + dummySelect
              + " "+"union"+" "
              + mainSelect ;
      count++;
      tableName=headerTableName;
   }
    //-------------use the DBTransaction Bean to get the data from DB------
    //out.println("-------"+mainSelect+"-----");
    aa.setQueryStatment(mainSelect);
    //-------------set the tree data with the result--------------
  setTreeData(aa.getResultSetAsArrayofVectors());
  }
}
catch (Exception e){throw new Exception(" Unable to Set Tree data Due to the following Erroe " + e.getMessage());}

}
//--------
public void setTreeDataByQuery(String mQueryStr) throws Exception
{
	  boolean relaodFromDB = (   DataAsVectors == null 
              || request.getParameter("refreshAll.x")!=null
              || request.getParameter("move")!= null
              || request.getParameter("copy")!= null 
              || request.getParameter("submitDelete") != null )? true: false;
if (! relaodFromDB)
{return;}
	
try
{
   HTMLDbTransactionBeanWOPooling xyz=new HTMLDbTransactionBeanWOPooling();
   xyz.setDBConnection(this.con);
   xyz.initialize(application,session,request,response,out);
  
  //-------------use the DBTransaction Bean to get the data from DB------
   this.loggedUser = (SecUsrDta)  session.getAttribute("loggedUser");
   xyz.setQueryStatment(mQueryStr);
  //-------------set the tree data with the result--------------
  setTreeData(xyz.getResultSetAsArrayofVectors());
  xyz.releaseResourses();
}
catch (Exception e){throw new Exception("Unable to Set Tree Data Due to the following Error :" + e.getMessage());}
}
public void setTreeData(String tableName, boolean showHeaderTables) throws Exception
{
  //--------------If User Click Delte Node--------
  if (request.getParameter("submitDelete")!= null)
  { 
   this.deleteNode(request.getParameter("tableName").toString() , Integer.parseInt(request.getParameter("selectedId").toString()));
  }
  //-----------If User Click Move Node--------
  else if (request.getParameter("move")!=null && request.getParameter("move").toString().equals("move") )
  {
   String newParentId= request.getParameter("newParentId").toString();
   String newHeaderId= request.getParameter("newHeaderId").toString();
   String selectedId= request.getParameter("selectedId").toString();
   String req_tableName= request.getParameter("tableName").toString();
   String updStmt = "update LU_QUERIES set parent_Id = " + newParentId + ", header_Id = "+newHeaderId+" where id = " + selectedId;
   try{
   java.sql.Statement stmt = con.createStatement();
   stmt.execute(updStmt);
   stmt.close();            
   }
   catch (Exception e) {out.print("Unable to Execute the following Command " + updStmt + " Due to the following Error "  + e.getMessage() );}
  }

  
  /*boolean relaodFromDB = (   DataAsVectors == null 
                          || request.getParameter("refreshAll.x")!=null
                          || request.getParameter("move")!= null
                          || request.getParameter("copy")!= null 
                          || request.getParameter("submitDelete") != null )? true: false;
  if (! relaodFromDB)
  {return;}
  */
  String headerTableName;
  try
  {
     HTMLDbTransactionBeanWOPooling xyz=new HTMLDbTransactionBeanWOPooling();
     xyz.setDBConnection(this.con);
     xyz.initialize(application,session,request,response,out);
    
    String id="id";   //tableName+"_id";
    String parent_Id="parent_Id";//tableName+"_parentId";
    String header_Id;


  if (!showHeaderTables)
  {
    header_Id="0";
    String userExecludedNodes = "-1" ; 
    String sysExecludedNodes = "-1" ;
    String andWhereClause = "" ; 
    SecRole userDefaultSecRole = null ; 
    try {
	    this.loggedUser = (SecUsrDta)  session.getAttribute("loggedUser");
	    try {
	    userDefaultSecRole = loggedUser.getDefaultSecRole(); 
	    userExecludedNodes = userDefaultSecRole.getHideTreeNodesValue() ; 
	    } catch (Exception e){}
	    //userExecludedNodes = (loggedUser != null )? loggedUser.getUsrUd2Value() :  "-1" ;
	    userExecludedNodes = (userExecludedNodes == null)? "-1" : userExecludedNodes ;
	    sysExecludedNodes= (String) this.loggedUser.getUserCompany().getMasSystem().getAttributeValue("EXECLUDED_SYS_NODES"); 
	    StringTokenizer sto = new StringTokenizer(userExecludedNodes + ", " + sysExecludedNodes, ","); 
	    while (sto.hasMoreElements())
	    {
	    	andWhereClause += " \n and t1.pathx not like '%/"+sto.nextToken().trim()+"/%'"  ; 
	    }
    }
    catch (Exception e ) {}
    

    //String nodeEditorJsp = "editExecutable" ; 
    String mainSelect="Select * from "
      +"\n ( SELECT '"+ tableName+"'||"+id+" as id, '"+tableName+"' as tableName , decode("+parent_Id+",null,null,'"+tableName+"'||"+parent_Id+") AS PARENT, '1' linked,  E_DSC  , A_DSC , "+id+" as idInTable, header_id "
      + ", HYPERLINK_TITLE , TARGET_URL  , 'editQuery.jsp' nodeEditor "
      + "\n, connect_by_iscycle cycle  "
      + "\n, level " 
      + "\n, SYS_CONNECT_BY_PATH(t.id, '/') Pathx"
      + "\n FROM "+" Support."+tableName + " t"
      + "\n  start with id = 0 "
      + "\n  connect by nocycle prior t.id = parent_id   ) t1" ; 
      
      if (tableName.equalsIgnoreCase("LU_QUERIES") )
      {
    	  mainSelect +=  "\n where t1.idInTable not in ( " + userExecludedNodes + ", "+sysExecludedNodes+")" 
      +   andWhereClause
      + "\n  and ( t1.pathx = '/0' " 
      + "\n  		or t1.pathx = '/0/305' " 
      + "\n  		or t1.pathx = '/0/'||(select id from Support.LU_QUERIES where code = '"+this.loggedUser.getUsrNameValue()+"' and parent_id = 0)" 
      + "\n  		or t1.pathx like '%/0/305/%' "
      + "\n  		or t1.pathx  like '%/0/'||(select id from Support.LU_QUERIES where code = '"+this.loggedUser.getUsrNameValue()+"' and parent_id = 0)||'/%' " 
      + "\n  		)  " ; 
      }
      mainSelect +=  "\n Order by upper(E_DSC) ";
    //-------------use the DBTransaction Bean to get the data from DB------
    xyz.setQueryStatment(mainSelect);
    //-------------set the tree data with the result--------------
    setTreeData(xyz.getResultSetAsArrayofVectors());
    xyz.releaseResourses();
  }

  else
  {
    id="id";               //tableName+"_id";
    parent_Id="parent_Id"; //tableName+"_parentId";
    header_Id="header_Id"; //headerTableName+"_id";
    String rootSelect="SELECT 'LU_ROOT'||"+id+" as id, 'LU_ROOT' as tableName , decode("+parent_Id+",null,'','LU_ROOT'||"+parent_Id+") AS PARENT, '0' linked,  A_DSC, E_DSC , id as idInTable,0 as Header_Id FROM LU_ROOT";
    String dummySelect="";
    String parentSelect;
    String mainSelect="SELECT '"+tableName+"'||"+id+" as id, '"+tableName+"' as tableName , decode("+parent_Id+",0,'_"+tableName+"'||header_id,decode("+parent_Id+",null,'','"+tableName+"'||"+parent_Id+")) AS PARENT, '1' linked,  A_DSC, E_DSC , "+id+" as idInTable,"+header_Id+" as Header_Id FROM "+" "+tableName+" "+ "where nvl(deleted,'F')<> 'T' order by "+id;
    int count=0;
    //----

    headerTableName=xyz.getHeaderTableName(tableName);
    String headerId;
    String headerTableId;
    while (!xyz.getHeaderTableName(tableName).equals(""))
    {
      id="id";               //tableName+"_id"; // primery key name in the table
      parent_Id="parent_Id"; //tableName+"_parentId"; // parent id field name in the table
      header_Id="header_Id"; //headerTableName+"_id";  //header_id field name in the table
      headerTableId="id";     //headerTableName+"_id"; // primery key field name in the header table
      boolean linkDumy=(count==0);
      headerTableName=xyz.getHeaderTableName(tableName);
      // ---------Check if dummy items should appear under leafs only or under allitems
      if (!dummyItemsUnderLeafsOnly)
      {
       dummySelect="SELECT '_"+tableName+"'||"+id+" as id, '"+tableName+"' as tableName, '"+headerTableName+"'||"+id+"  AS PARENT, "+((count==0)? "'1'" : "'0'" )+" linked,  "
                  +"(Select A_DSC from "+" "+tableName+" "+"where "+id+"=0 ) as A_DSC, '"+tableName.substring(3)+"' as E_DSC , 0 as idintable,"+headerTableName+"."+headerTableId+" as Header_Id FROM "+" "+headerTableName+" "+" where nvl(deleted,'F')<> 'T' " ;

      }
      if (dummyItemsUnderLeafsOnly)
      {
      dummySelect="SELECT '_"+tableName+"'||"+id+" as id, '"+tableName+"' as tableName, '"+headerTableName+"'||"+id+"  AS PARENT, "+((count==0)? "'1'" : "'0'" )+" linked,  "
                 +"(Select A_DSC from "+" "+tableName+" "+"where "+id+"=0 ) as A_DSC, '"+tableName.substring(3)+"' as E_DSC , 0 as idintable,"+headerTableName+"."+headerTableId+" as Header_Id FROM "+" "+headerTableName+" "+" where "+" " +headerTableName+"."+headerTableId+" not in (select nvl("+headerTableName+"."+parent_Id+",-1)from "+" "+headerTableName + ") and nvl(deleted,'F')<> 'T' " ;
      }
      int parentLink=(hyperlinkLastTableItemsOnly)? 0:1;
      parentSelect="SELECT '"+headerTableName+"'||"+id+" as id, '"+headerTableName+"' as tableName , decode("+parent_Id+",0,'_"+headerTableName+"'||"+header_Id+",decode("+parent_Id+",null,'','"+headerTableName+"'||"+parent_Id+")) AS PARENT,'"+parentLink+"' linked,  A_DSC, E_DSC , id as idInTable, "+header_Id+" as Header_Id FROM "+" "+headerTableName+" " + "where nvl(deleted,'F')<> 'T' ";

      mainSelect=((!headerTableName.equals("LU_ROOT"))? parentSelect: rootSelect)
              + " "+"union"+" "
              + dummySelect
              + " "+"union"+" "
              + mainSelect ;
      count++;
      tableName=headerTableName;
   }
    //-------------use the DBTransaction Bean to get the data from DB------
    //out.println("-------"+mainSelect+"-----");
    xyz.setQueryStatment(mainSelect);
    //-------------set the tree data with the result--------------
  setTreeData(xyz.getResultSetAsArrayofVectors());
  xyz.releaseResourses();
  }
}
catch (Exception e){throw new Exception("Unable to Set Tree Data Due to the following Error :" + e.getMessage());}

  }
//---------------------------------------
public void setTreeData(String tableName, String tablesSuffix) throws Exception
{
String s=" "
  + " select '_'||xx.id as id , xx.dtable as tableName, decode (yy.id,null,null,'_'||yy.id) as Parent_Id, '1' as linked, xx.dtable as A_DSC, xx.dtable as E_DSC, xx.id as idinTable "
  + " from (select decode(a.table_name,'"+tablesSuffix+"_ROOT',0,RowNum) as Id, a.table_Name as DTable,b.table_name as MTable, a.constraint_name                                                  "
  + "       from user_constraints a, user_constraints b                                                                                                                             "
  + "       where a.Table_Name like '"+tablesSuffix+"_%'                                                                                                                                           "
  + "       and a.r_constraint_name = b.constraint_name (+)                                                                                                                         "
  + "       and a.table_name <>b.table_name (+) and a.constraint_type = 'R'                                                                                                         "
  + "       and ((b.table_name Is not null ) or (a.table_name='"+tablesSuffix+"_ROOT'))                                                                                                           "
  + "      )                                                                                                                                                                        "
  + "    xx,                                                                                                                                                                        "
  + "     (select  decode(a.table_name,'"+tablesSuffix+"_ROOT',0,RowNum) as Id, a.table_Name as DTable, b.table_name as MTable, a.constraint_name                                                 "
  + "      from user_constraints a, user_constraints b                                                                                                                              "
  + "      where a.Table_Name like '"+tablesSuffix+"_%'                                                                                                                                            "
  + "      and a.r_constraint_name = b.constraint_name (+)                                                                                                                          "
  + "      and a.table_name <>b.table_name (+)                                                                  "
  + "      and a.constraint_type = 'R'"
  + "      and ((b.table_name Is not null )  or (a.table_name='"+tablesSuffix+"_ROOT'))"
  + "      )"
  + "     yy"
  + " where xx.mtable  = yy.dtable (+)order by xx.id";
//out.print (s);
HTMLDbTransactionBeanWOPooling xyz=new HTMLDbTransactionBeanWOPooling();
xyz.setDBConnection(this.con);
LookupTreeV10 tablesTree=new LookupTreeV10("LU_QUERIES0");
tablesTree.setDBConnection(this.con);
xyz.setQueryStatment(s);
tablesTree.setTreeData(xyz.getResultSetAsArrayofVectors());
xyz.releaseResourses() ; 
int tableIndexInTheTree=0;
//---------getting the index of the table in the Tree-----------
for (int i=0; i<tablesTree.DataAsVectors[0].size(); i++)
{
  if (tablesTree.DataAsVectors[5].elementAt(i).toString().equals(tableName))
      {tableIndexInTheTree=i;
        break;}
}
String tableId=tablesTree.getId(tableIndexInTheTree);
setTreeData(tablesTree,tableId);

}

//----------------------------
public void setData(Vector[] Data){
DataAsVectors=Data;
}
// -------------------------------------------------------
public int getParentIndex( int index){
String pId =null;
int pIndex=-1;
try
{
if ( DataAsVectors[2].elementAt(index) != null)
  { pId= DataAsVectors[2].elementAt(index).toString();
    pIndex=DataAsVectors[0].indexOf(pId);
    //getIndex(pId);
  }
}
catch (Exception e) {out.print ("sdfgd");}
return pIndex;
}

//--------------------get English Describtion--------------------
public String getDesc( int index){
String desc ="";
 
int l=( this.loggedUser != null && this.loggedUser.isUsrLangEnglish())? 4:5; // if lang is 2 then return the English else return the Arabic
try{
desc=DataAsVectors[l].elementAt(index).toString();
}
catch(Exception e){out.println("from getDesc");}
return desc;
}
//-------------------
public boolean  isNodeSelectable( int index)
{
	return this.treeNodes[index].isSelectable() ;
	/*
	boolean  result  = true;
	Object value = null ; 
	try {
		value = DataAsVectors[11].elementAt(index);
		result = ( value != null && value.toString().equals("1") ) ;
	}
	catch ( Exception e) {}
	return result;
	*/
}
//-------------------
public String getId( int index){

	return this.treeNodes[index].getId() ;
}
public String getIdInTable( int index){
	return this.treeNodes[index].getIdInTable();
}


public int getindex2(String id) throws Exception
{
   int out = this.DataAsVectors[0].indexOf(id);
   if (out ==-1 )
   {
     throw new Exception (" Node With Id = " + id + " Not Found inthe Tree Data");
   }
   return out;
}

//------------get the index of a certain row using binary search-----------
public int getIndex_deleteIt( String id) throws Exception
{
int index=0;
int upperLimit;
int size;
int count=0;

try
{
size=DataAsVectors[0].size();
upperLimit= DataAsVectors[0].size();
int lowerLimit= 0;
String CurrentId;
//int CurrentId;
//int idInt= Integer.parseInt(id);
String idInt=id;
index=(upperLimit+lowerLimit)/2;
CurrentId=DataAsVectors[0].elementAt(index).toString();
//CurrentId=Integer.parseInt(DataAsVectors[0].elementAt(index).toString());
while (! CurrentId .equals(idInt))
  {
    // if ( CurrentId < idInt  )
    if ( CurrentId.compareTo(idInt)<0  )
          {
            lowerLimit= index;
             index=(upperLimit+lowerLimit)/2;}
    else
          { upperLimit= index;
             index=(upperLimit+lowerLimit)/2;}

    CurrentId=DataAsVectors[0].elementAt(index).toString();
    count++;
    if (count>size) throw new NullPointerException();
  }
}
catch (NullPointerException npe){index=-1; throw new Exception ("Shawky: The node with id="+ id +" not found in the tree data");
}
return index;
}
//----------------------------------------------------------------------
public boolean isLeaf(int index){
  boolean  leaf= true;
  Vector sonsId=sonsIds[index];
  if (sonsId.size()!=0)
  {leaf=false;}
return leaf;
}

//----------------This method returns a vector of sons id-------
public java.util.Vector<String> getSonsId(int index){
java.util.Vector sons = new java.util.Vector();
sons=sonsIds[index];
return sons;
}
public void copySons(String tableName , int fromId , int toId) throws Exception
{
  int fromIndex =this.DataAsVectors[0].indexOf( tableName.toUpperCase()+fromId );
  int toIndex =this.DataAsVectors[0].indexOf( tableName.toUpperCase()+toId );
  Vector sonsIds = getSonsId(fromIndex);
  for (int i=0; i<sonsIds.size() ;i++)
  {
    //--------insert all sons ----
        //-----------get new sequence id ------------------
        ResultSet rs = con.createStatement().executeQuery("select "+ tableName+"_Seq.nextval from dual " );
        rs.next();
        int newSeqId = rs.getInt(1);
        //----------End of getting the new sequence----------------
        String qStmt = "select code , e_dsc , a_dsc , query_body  from "+tableName +" where id = "+sonsIds.elementAt(i).toString().substring(tableName.length()) ;
        rs = this.con.createStatement().executeQuery(qStmt);
        rs.next();
        String qeuey_bodey =  Misc.replaceSingleQuteWithDouble((rs.getString("query_body")==null)? "" :rs.getString(4));
        String insertStmt = "insert into " + tableName + " (id, parent_id , code,  e_dsc , a_dsc , query_body ) values (" + newSeqId +", "+toId+",'" + rs.getString("code")+newSeqId + "','" +rs.getString("e_dsc") +"','" + rs.getString("a_dsc")+"' ,'"+qeuey_bodey+"' )" ;
        con.createStatement().execute(insertStmt);
    //---------------------------
    int sonIndex = this.DataAsVectors[0].indexOf(sonsIds.elementAt(i).toString());
    Vector sons_sons = getSonsId(sonIndex);
    if (sons_sons.size()>0)
    {
      copySons( tableName  ,Integer.parseInt(sonsIds.elementAt(i).toString().substring(tableName.length()))  ,newSeqId);
    }
  }
}
//------------------------
private int treeRootNodeIndex ; 

private java.util.Vector[] buildAllSonsIds()
{
int treeLength;
Vector[] allSonsIds=null;
try
{
  treeLength=DataAsVectors[0].size();
  allSonsIds=new Vector[treeLength];
  String pId;
  for (int i=0; i<=treeLength-1; i++)
    {allSonsIds[i]=new Vector();}

  for (int i=0; i<=treeLength-1; i++)
   { int pIndex=getParentIndex(i);
      if (pIndex!=-1)
      {
        pId=DataAsVectors[0].elementAt(i).toString();
        allSonsIds[pIndex].addElement(pId);
      }
      else { this.treeRootNodeIndex = i; }
   }
}
catch (NullPointerException npe){out.println(npe.getMessage());} 
   return allSonsIds;
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


//-----This method returns an HTML String to Draw an HTML Table----------
//-----------This Mothod is a recurresion method (calls itself)-----------
public String getDrwStr(String id)throws Exception
{
  int index=this.DataAsVectors[0].indexOf(id);
    if (index== -1)  // item not found
      {
        out.print ("Node "+id+" not found");
        throw new Exception("Node "+id+" not found");
      }
    TreeNode treeNode = treeNodes[index] ;  
  String tableName=treeNode.getTableName(); // DataAsVectors[1].elementAt(index).toString();
  String idInTable=treeNode.getIdInTable() ; //DataAsVectors[6].elementAt(index).toString();
  String headerId="0";
  
  headerId= treeNode.getHeaderId(); // DataAsVectors[7].elementAt(index).toString();
  
  String s1="<tr id = "+index+" ><td nowrap><p style=\"margin-top: -4; margin-bottom: -4\">" ; //"<tr><td>";
  String treeHtmlString="";
  String img="";
  String spaces="";
  boolean isLeaf=isLeaf(index);
  boolean hyperLinked= treeNode.isLinked(); //(Integer.parseInt(DataAsVectors[3].elementAt(index).toString().trim())==0)? false : true;

  for (int i=0; i<getLevelV2(index).length();i++)
            { if (getLevelV2(index).charAt(i)=='1')
                  {spaces=spaces+"<img border=0 src="+imagePath+"tree_branch_none.gif width=22 height=22>";}
              else
                  {spaces=spaces+"<img border=0 src="+imagePath+"white_container_bottom_left.gif width=22 height=22>";}
            }
 

//--- This is to replace the the query strting with the new index to be toggled----
  Boolean nodeClosed = this.getNodesStatusAsBitSet().get(index); //nodesStatus[index].equals("c");
  String queryString="";
  if (request.getQueryString()!=null && request.getParameter("nodeToBeToggled")!= null)
    {
     queryString="?"+request.getQueryString().toString();
     int x= queryString.indexOf("nodeToBeToggled=");
     String sub1=queryString.substring(0,x);
     String sub2="nodeToBeToggled="+index;
     int startFrom = x+16+request.getParameter("nodeToBeToggled").toString().length() ;
     startFrom =startFrom +  ((request.getParameter("nodeStatus")!= null )? "&nodeStatus".length()+ 1 + request.getParameter("nodeStatus").length() :0); 
     String sub3=queryString.substring(startFrom);
     queryString=sub1+sub2+"&nodeStatus="+nodeClosed+sub3+"#"+index;

    }
  else
  {
  queryString="?nodeToBeToggled="+index + "&treeIdInSession=" + this.treeIdInSession +"#"+index;
  }
  //-------------To Allow TRee Book Mark 
  //--- Commented where it has a problem  works only once per node ,ie you had to toggle another node first.
   // queryString += "#"+DataAsVectors[0].elementAt(index); 

//--------------------------End of Replacing----------------------------
  java.util.Vector sonsId = sonsIds[index];
  int sonsSize=sonsId.size();

  boolean lastSon=isLastSon(index);
  boolean displayThisNode = isNodeIncluded(index);
  int level = getLevelV2(index).length();
  boolean isSmartToolAdmin = this.loggedUser.isSmartToolAdmin() ;
  boolean treeNodeOwner = false ; 
  
  try { 
	  treeNodeOwner = this.securityManager.isTreeNodeOwner(id) ;
	  } catch (Exception e) {} 

  if ( ( level ==1 && displayThisNode)  || level !=1 )
  {
  //--------------if the node is leaf or the node is closed-----------
  if  (isLeaf || nodeClosed)
      {

        img= (!(lastSon)?
            	 ( (nodeClosed)? "<img border=0 src="+imagePath+"tree_closed_middle.gif width=22 height=22>"
                  :"<img border=0 src="+imagePath+"tree_branch_middle.gif width=22 height=22>" )
             : ( (!nodeClosed)? "<img border=0 src="+imagePath+"tree_branch_bottom.gif width=22 height=22>"
                  : "<img border=0 src="+imagePath+"tree_closed_bottom.gif width=22 height=22>" ) )
             + ((nodeClosed)? "<img border=0 src="+imagePath+"tree_folder_closed.gif width=22 height=22>"
                  : "<img border=0 src="+imagePath+"tree_leaf.gif width=22 height=22>");

        if (isLeaf)
        img=((lastSon)? "<img border=0 src="+imagePath+"tree_branch_bottom.gif width=22 height=22>"
                        :"<img border=0 src="+imagePath+"tree_branch_middle.gif width=22 height=22>")
                        + "<img border=0 src="+imagePath+"tree_leaf.gif width=22 height=22>";
        boolean isNodeSelectable = treeNode.isSelectable(); //this.isNodeSelectable(index); 
         this.checkBoxStr = (isNodeSelectable)? this.getHtmlSelectCode(index):"";
         String nodeEditor = (treeNode.getNodeEditor()== null)? "NodeEditorNotDefined" : treeNode.getNodeEditor();
         String editLink = "<a title 'Edit Query Only Without Execution'  href= "+nodeEditor+"?id="+idInTable+"&tableName="+tableName+ "&headerId="+headerId+" target ="+ AddFormTargetFrame + "><img src='"+imagePath+"editrow.gif' height='15' width='15'></a>" ;
         treeHtmlString=  s1+spaces
                             + "<a href ="+request.getRequestURI()
                             + queryString
                             + ">"+ img+"</a>"
                             + ((this.showCheckBox)?  checkBoxStr: " " )
                             + ((ShowAddLink && hyperLinked && (isSmartToolAdmin || treeNodeOwner ))? "<a href=" + addFormUrl+((addFormUrl.indexOf("?")==-1)? "?"+this.getParentIdVarName()+"=" :"&"+this.getParentIdVarName()+"=")+idInTable+"&tableName="+tableName.trim()+ "&headerId="+headerId+" target ="+ AddFormTargetFrame + "> <img border=0 src="+imagePath+"toolplus.gif width=14 height=14 alt=\"Add Sub Item xxx\"></a>" :"" )
         					 //---------adding a new edit query only link
                             + ((isSmartToolAdmin || treeNodeOwner || this.isShowEditLink()) ? editLink : "" ) 
                             + this.getDescHref(id)
                             + "</td></tr>" ; 
        
  }
//-----------------else--------------------------------
  else
  {
    for (int i=0; i<=sonsSize-1;i++)
       {
        String yyy=sonsIds[index].elementAt(i).toString();
          //execludedIds= new Vector();
            if (execludedIds.size()==0 || execludedIds.indexOf(yyy)< 0)
            treeHtmlString=treeHtmlString+getDrwStr(yyy);
       }
       img= (!(lastSon)?
              "<img border=0 src="+imagePath+"tree_open_middle.gif width=22 height=22>"
              : "<img border=0 src="+imagePath+"tree_open_bottom.gif width=22 height=22>")
              + "<img border=0 src="+imagePath+"tree_folder_open.gif width=22 height=22>";

       String nodeEditor = (DataAsVectors[10].elementAt(index)== null)? "NodeEditorNotDefined" : DataAsVectors[10].elementAt(index).toString();
       String editLink = "<a title 'Edit Query Only Without Execution'  href= "+nodeEditor+"?id="+idInTable+"&tableName="+tableName+ "&headerId="+headerId+" target ="+ AddFormTargetFrame + "><img src='"+imagePath+"editrow.gif' height='15' width='15'></a>" ; 
       boolean isNodeSelectable = this.isNodeSelectable(index); 
       this.checkBoxStr = (isNodeSelectable)? getHtmlSelectCode(index) : ""; 
       treeHtmlString=  s1+spaces
                           + "<a href ="+request.getRequestURI()
                           + queryString
                           + ">"+img+"</a>"
                           + ((this.showCheckBox)? checkBoxStr : " " )
                           + ((ShowAddLink && hyperLinked && (isSmartToolAdmin || treeNodeOwner ))? "<a href=" + addFormUrl+((addFormUrl.indexOf("?")==-1)? "?"+this.getParentIdVarName()+"=" :"&"+this.getParentIdVarName()+"=")+idInTable+"&tableName="+tableName+ "&headerId="+headerId+" target ="+ AddFormTargetFrame + "> <img border=0 src="+imagePath+"toolplus.gif width=14 height=14 alt='Add Sub Item'></a>" :"" )
                           + ((isSmartToolAdmin || treeNodeOwner || this.isShowEditLink()) ? editLink : "" ) 
                           + this.getDescHref(id)
                           + treeHtmlString  ;
    }
  }
return treeHtmlString;
}
boolean showEditLink = false ; 
public boolean isShowEditLink() {
		return showEditLink;
}
public void setShowEditLink(boolean m_showEditLink )
{
	this.showEditLink = m_showEditLink ; 
}
public String getDescHref(String id) throws Exception
{
	int index=this.DataAsVectors[0].indexOf(id);
    if (index== -1)  // item not found
      {
        out.print ("Node "+id+" not found");
        throw new Exception("Node "+id+" not found");
      }
  String tableName=DataAsVectors[1].elementAt(index).toString();
  String idInTable=DataAsVectors[6].elementAt(index).toString();
  String headerId="0";
  if (DataAsVectors[7].elementAt(index)!=null)
  {headerId=DataAsVectors[7].elementAt(index).toString();}
  String desc=  getDesc(index);
  boolean isLeaf=isLeaf(index);
  boolean hyperLinked=(Integer.parseInt(DataAsVectors[3].elementAt(index).toString().trim())==0)? false : true;
  String  DescHref="";
  if (hyperLinked)
   {
	  String targetUrl = getTargetUrl(index) ; 
	  String idIntable = this.getIdInTable(index) ; 
	  HashMap<String , String> selectedIDs =  this.getSelectedIDs(); 
	  boolean isNodeSelected = selectedIDs.containsKey(idIntable) ;  
	  String nodeStyle =(isNodeSelected)? "style=\"background-color: rgb(255, 204, 0);\"" : "style=\"background-color: #FFFFFF\" " ; 
    if (targetUrl.length()>10 && targetUrl.substring(0,10).equals("javaScript") )
     {
         if (isLeaf || linkAllInPopup)
         {
          String fP=toHTMLString(getFullPath(index));
          String descHtml = toHTMLString(desc);
          DescHref="<a href="+targetUrl+"('"+idInTable+"','"+fP+"','"+descHtml+"','"+headerId+ "')>";
         }
         else
         DescHref="";
     }
    else
     { DescHref=  "<a title =\""+ DataAsVectors[8].elementAt(index) +"\" href="+targetUrl+((targetUrl.indexOf("?")<0)? "?":"&" )+"id="+idInTable+"&lookupTableName=" + tableName +" target ="+ getTargetFrame()
                + " "+ nodeStyle +" id=\""+ idInTable + "\" isLeaf=\""+ isLeaf + "\" tableName=\""+ tableName + "\" headerId=\""+headerId+"\" name=\""+ DataAsVectors[0].elementAt(index) + "\" onClick=\"UnselectAllLinks(); changeColor(this.name);\""
                + ">";
     }
   }
  
    return DescHref + desc+((hyperLinked)? "</a> " : " ") ; 
}

private String getHtmlSelectCode(int m_nodeIndex ) throws Exception
{
	String v_result = null  ;
	
	String nodeId = this.getId(m_nodeIndex); 
	
	String idOnly = this.getIdInTable(m_nodeIndex); 
	if(this.isMultiSelectMode())
	{
	v_result = "<input type=checkbox name='"+nodeId+ "'  " 
	+ ( (this.getSelectedIDs() != null && this.getSelectedIDs().containsKey(idOnly) ) ? "checked" :"") 
    + " onClick=\" if (this.checked ) {this.value='Y';} else {this.value='N';}; \n processSelection(this.name  , this.value)\"  >" ;
	}
	else if (this.isSingleSelectMode())
	{
		v_result = "<input type='radio' name='"+this.getTreeIdInSession()+"'"
		  +"  value='"+nodeId+"' "
		  + ( (this.getSelectedIDs() != null && this.getSelectedIDs().containsKey(idOnly) ) ? "checked" :"")
		  +" onclick=\" processSelection(this.value  , ((this.checked )? 'Y':'N') )\"  >" ; 
	}
	return v_result ;
}
//private void checkDelNode() throws Exception
//{
//    if (request.getParameter("submitDelete")!= null)
//  { //---- if the user confim deletion----------------------
//      this.deleteNode(request.getParameter("tableName").toString() , Integer.parseInt(request.getParameter("selectedId").toString()));
//  }
//}
//-----------------------------------------------------------------
private void renderToolBar(String id)
{
	  //checkDelNode();
	  //--------------JavaScript Code-------------------------
	  out.println (" <HEAD>                                                                                             ");
	  out.println (" <script language ='JavaScript'>                                                                    ");
	  out.println (" function changeColor(name){                                                                        ");
	  out.println ("     eval ('document.all.'+ name + \".style.backgroundColor='#ffcc00'\");                           ");
	  out.println ("   	 var index=0;                                                                                   ");
	  out.println ("     for (var i=0; i<document.anchors.length; i++)                                                  ");
	  out.println ("        {                                                                                           ");
	  out.println ("          if (document.anchors[i].name ==name)                                                      ");
	  out.println (" 	           {                                                                                      ");
	  out.println ("         			index=i;                                                                              ");
	  out.println (" 	             break;                                                                               ");
	  out.println (" 	            }                                                                                     ");
	  out.println (" 		     }                                                                                          ");
	if(showToolBar )
	{
	  out.println ("      document.addForm.tableName.value=document.anchors[index].tableName ;                                    ");
	  out.println ("      document.addForm.parentId.value=document.anchors[index].id;                            ");
	  out.println ("      document.addForm.headerId.value=document.anchors[index].headerId;                            ");
	  if (showMoveForm)
	  {
	   out.println ("      document.moveForm.selectedId.value=document.anchors[index].id;                            ");
	   out.println ("      document.moveForm.selectedHeaderId.value=document.anchors[index].headerId;                            ");
	  }
	   out.println ("      document.moveForm.tableName.value=document.anchors[index].tableName ;                                    ");
	   out.println ("      document.deleteForm.tableName.value=document.anchors[index].tableName ;                                    ");
	   out.println ("      document.deleteForm.selectedId.value=document.anchors[index].id;                            ");
	   out.println ("      document.deleteForm.headerId.value=document.anchors[index].headerId;                            ");
	   out.println ("      document.deleteForm.isLeaf.value=document.anchors[index].isLeaf;                            ");
	  if (showRecycleBin)
	  {out.println ("      document.RecycleBinForm.tableName.value=document.anchors[index].tableName ;                                    ");
	   out.println ("      document.RecycleBinForm.id.value=document.anchors[index].id;                            ");
	   out.println ("      document.RecycleBinForm.headerId.value=document.anchors[index].headerId;                            ");
	    //---------enabling and disabling the RecycleBinKey toolbar key------------
	   out.println ("      if (document.deleteForm.selectedId.value==\"\" ) ");
	   out.println ("      {document.RecycleBinForm.submit.disabled=true;     ");
	   out.println ("       document.RecycleBinForm.submit.style.background=\"url('"+imagePath+"rbd.gif')\" ;}     ");
	   out.println ("      else {document.RecycleBinForm.submit.disabled=false;      ");
	   out.println ("       document.RecycleBinForm.submit.style.background=\"url('"+imagePath+"rba.gif')\" ;     ");
	   out.println ("       }     ");   
	  }
	  //---------enabling and disabling the toolbar keys------------
	  //out.println ("      document.moveForm.refreshAll.style.background=\"url('"+imagePath+"toolbar_icon_reload_active.gif')\" ;  ");
	  //---------enabling and disabling the Delete toolbar key------------
	  out.println ("      if (document.deleteForm.selectedId.value=='0' || document.deleteForm.selectedId.value==\"\"  || document.deleteForm.isLeaf.value=='false')    ");
	  out.println ("      {                                                   ");
	  out.println ("      document.deleteForm.submitDelete.disabled=true;     ");
	  out.println ("      document.deleteForm.submitDelete.style.background=\"url('"+imagePath+"deleterecd.gif')\" ;  ");
	  out.println ("      }                                                   ");
	  out.println ("      else {document.deleteForm.submitDelete.disabled=false;  ");
	  out.println ("      document.deleteForm.submitDelete.style.background=\"url('"+imagePath+"deleterec.gif')\" ;  ");
	  out.println ("      }     ");
	  //---------enabling and disabling the add toolbar key------------
	  out.println ("      if (document.deleteForm.selectedId.value==\"\" ) ");
	  out.println ("      {document.addForm.submit.disabled=true;     ");
	  out.println ("       document.addForm.submit.style.background=\"url('"+imagePath+"addnewd.gif')\" ;}    ");
	  out.println ("      else {document.addForm.addSubmit.disabled=false;       ");
	  out.println ("       document.addForm.addSubmit.style.background=\"url('"+imagePath+"addnew.gif')\" ;     ");
	  out.println ("       }     ");
	  if (showMoveForm)
	  {
	  //---------enabling and disabling the Move & Copy toolbar keys------------
	  out.println ("      if ( document.moveForm.selectedId==null ) ");
	  out.println ("      {document.moveForm.move.disabled=true;     ");
	  out.println ("       document.moveForm.copy.disabled=true;     ");
	  out.println ("       document.moveForm.move.style.background=\"url('"+imagePath+"moveItem.gif')\" ;}    ");
	  out.println ("      else {document.moveForm.move.disabled=false;       ");
	  out.println ("            document.moveForm.copy.disabled=false;       ");
	  out.println ("       document.moveForm.move.style.background=\"url('"+imagePath+"moveItem.gif')\" ;     ");
	  out.println ("       document.moveForm.copy.style.background=\"url('"+imagePath+"moveItem.gif')\" ;     ");  
	  out.println ("       }     ");
	  }

	}
	  out.println ("     }//------end of javascript function                                                            ");

	  out.println (" function UnselectAllLinks()                                                                        ");
	  out.println ("    {                                                                                               ");
	  out.println ("     for (var i=0; i<document.anchors.length; i++)                                                  ");
	  out.println ("        {                                                                                           ");
	  out.println ("          eval ('document.all.' + document.anchors[i].name + \".style.backgroundColor='#FFFFFF'\"); ");
	  out.println ("        }                                                                                           ");
	  out.println ("     }                                                                                              ");
	  out.println (" </script>                                                                                          ");
	  out.println (" </HEAD>                                                                                            ");
	  out.println (" <Body>                                                                                             ");
	  //---------------end of Java Script----------------------

	  //------------------render Tool Bar----------------------
	if(showToolBar )
	  {
	  //---------rendering the AddForm  Button----------------
	  out.println ("  <table border=0 >  <tr>  <td nowrap>");
	  out.println ("  <form name='addForm' target="+TargetFrame+" " +"method='get' action='" + addFormUrl + "'>     ");
	  out.println ("  <input type='hidden' name='parentId'>         ");
	  out.println ("  <input type='hidden' name='headerId'>           ");
	  out.println ("  <input type='hidden' name='tableName'>          ");
	  out.println ("  <input type='submit' title='Add an Item Under The Selected Item' name='addSubmit' value= '' disabled =true style='background-image: url(&#039;"+imagePath+"addnewd.gif&#039;); background-repeat: repeat; background-attachment: scroll; width: 20; height: 20; background-position: 0% 50%' > ");
	  out.println ("  </form>                                          ");
	  out.println ("  </td > ");
	  //---------rendering the Recycle Bin Form  Button----------------
	  if (this.showRecycleBin)
	  {
	  out.println ("  <td nowrap>");
	  out.println ("  <form name='RecycleBinForm' target="+TargetFrame+" " +"method='post' action='" + recycleBinURL + "'>     ");
	  out.println ("  <input type='hidden' name='headerId'>           ");
	  out.println ("  <input type='hidden' name='id'>           ");
	  out.println ("  <input type='hidden' name='tableName'>           ");
	//  out.println ("  <input type='submit' name='submit' value= 'R_B' disabled='true' style='background:url("+imagePath+"resycleDisabled.gif)'>");
	  out.println ("  <input type='submit' title='Show The Deleted Items Under The Selected Item' name='submit' value= ' ' disabled =true style='background-image: url(&#039;"+imagePath+"rbd.gif&#039;); background-repeat: repeat; background-attachment: scroll; width: 20; height: 20; background-position: 0% 50%' > ");
	  out.println ("  </form>                                          ");
	  out.println ("  </td >");
	  }
	  out.println (" <td nowrap>");
	  //---------rendering the deleteForm Button --------------------
	  out.println ("  <form name='deleteForm'  " +"method='get' action='" +request.getRequestURI()+ "'>     ");
	  out.println ("  <input type='hidden' name='refreshAll.x'>           "); //-------To Refresh Tree Data after delete operation 
	  out.println ("  <input type='hidden' name='selectedId'>           ");
	  out.println ("  <input type='hidden' name='headerId'>           ");
	  out.println ("  <input type='hidden' name='tableName'>           ");
	  out.println ("  <input type='hidden' name='isLeaf'>           ");
	  out.println ("  <input type='submit' title='Delete The Selected Item' onClick ='return confirm(\"Are You sure You Want To Delete This Item\");' name='submitDelete' value= '' disabled =true style='background-image: url(&#039;"+imagePath+"deleterecd.gif&#039;); background-repeat: repeat; background-attachment: scroll; width: 20; hieght: 20; height: 20; background-position: 0% 50%' > ");
	  out.println (" </form>                                          ");
	  out.println ("  </td > " );
	  }
	   
	  
	  out.println ("<td nowrap>");
	  //---------rendering the Expand and Collapse Form Buttons --------------------
	  out.println (" <form name='TreeForm'  " +"method='get' action='" +Support.Misc.repalceAll( request.getRequestURI() , "nodeToBeToggled" , "xxyyzz") + "'>     ");
	  out.println ("  <input border=1 type='image' name='openAll' width = 20 height = 20 src ="+imagePath+"expand.gif    title='Open All Nodes'> ");
	  out.println ("  <input border=1 type='image' name='closeAll'width = 20 height = 20 src ="+imagePath+"collapse.gif  title='Close All Nodes'> ");
	  //--------------The following To refresh all The Data , Read again from DB-----------
	  out.println ("  <input border=1 type='image' name='refreshAll' width = 20 height = 20 src="+imagePath+"toolbar_icon_reload_active.gif  title='Reload Tree Data' > ");
	  out.println ("  <input type='hidden' name='treeIdInSession'   value='"+treeIdInSession+"' > ");
	  out.println (" </form>                                          ");
	  out.println ("</td > " );
	  
	if (showMoveForm && this.loggedUser.isSmartToolAdmin())
	{
	//----------rendering the Move Form -----------------------------
	  out.println ("<td nowrap>");
	  out.println ("    <form name= moveForm action='" +request.getRequestURI()+ "'> ");
	  out.println ("    <input type='hidden' name='tableName'>           ");  
	  out.println ("    <input type ='submit' title='Copy the Selected Item To Another Parent' name= 'copy' value='copy' disabled='true' onClick='if (document.moveForm.desc.value==\"\"){alert(\"Please Select New Parent Node First\"); return false; }else return true;'> ");
	  out.println ("    <input type ='submit' title='Move the Selected Item Under Another Parent' name= 'move' value='move' disabled='true' onClick='if (document.moveForm.desc.value==\"\"){alert(\"Please Select New Parent Node First\"); return false; }else return true;'> ");
	  out.println ("    <input type = 'hidden' name = 'selectedId' size=5> ");
	  out.println ("    To <input type = 'hidden'  name = 'desc'  size=5 onchange='if (this.value==null || this.value==\"\"){document.moveForm.move.disabled=true;} else {document.moveForm.move.disabled=false;}'> ");
	  out.println ("    <a href='' onmouseover=\"this.href='LUMoveForm.jsp?tableName=LU_QUERIES&excludedId='+document.moveForm.selectedId.value;\"  name = 'xxxxx' target='New'>Select New Parent Node</a> ");
	  //out.println ("    <a href='' onmouseover=\"this.href='LUMoveForm.jsp?tableName='+document.deleteForm.tableName.value+'&excludedId='+document.moveForm.selectedId.value;\"  name = 'xxxxx' target='New'>Select New Parent Node</a> ");
	  out.println ("    <input type = 'hidden' name = 'selectedHeaderId' > ");
	  out.println ("    <input type = 'hidden' name = 'newParentId' > ");
	  out.println ("    <input type = 'hidden' name = 'newHeaderId' > ");
	  out.println ("    </form> ");
	  out.println (" </td > ");
	//---------------end of Move Form------------------------
	}
	  out.println ("</tr></table> ");

	//---------------open all Nodes or Close All nodes--------------
	  if(request.getParameter("openAll.x")!=null || request.getParameter("closeAll.x")!=null)// && request.getParameter("openAll").toString().equals( "OpenAll"))
	  {

	      if (request.getParameter("openAll.x")!=null)
	       {
	    	  nodesStatusAsBitSet.clear(); 
	       }
	      if (request.getParameter("closeAll.x")!=null)
	       {
	    	  for (int i=0; i<=DataAsVectors[0].size()-1 ;i++)
	           {nodesStatusAsBitSet.set(i);}
	    	  setNodesStatusAsBitSet();
	        
	       }
	   //session.putValue("nodesStatusAsBitSetFor"+DataAsVectors[5].elementAt(0).toString(),xy);
	      
	  }
	    //--------------------Copying a Hirarchy---------------------------
	  else if (request.getParameter("copy")!=null && request.getParameter("copy").toString().equals("copy") )
	  {
	   int newParentId= Integer.parseInt(request.getParameter("newParentId").toString());
	   int selectedId= Integer.parseInt(request.getParameter("selectedId").toString());
	   String req_tableName= request.getParameter("tableName").toString();   
	   //--------Start Calling the Copy_hirarchy  Procedure----
	   try{
	   this.copySons(req_tableName , selectedId , newParentId );
	   //----------refresh Tree data after Copying -----------
	   this.setTreeData(req_tableName , false);
	   }
	   catch (Exception e )
	   {out.println("Unable to Execute the Copy Data from Id = " + selectedId +" to Id = "+newParentId +" Due to the following Error " + e.getMessage());}
	  }
	  //-----------------End --Copying a Hirarchy---------------------------

}
public void render( int renderType) throws Exception 
{
 this.render(this.getStartFromNodeId() , renderType ) ; 
}
public void render(String m_startFromId , int renderType) throws Exception {
    String startFromId = m_startFromId ; 
	String m="";
    if (startFromId == null)
    {
    	 startFromId = this.getId(getTreeRootNodeIndex()); 
    }
	   //---------Togging the Status of any node--------------
    if (request.getParameter("nodeToBeToggled")!=null)
    {TogNodesStatusAsBitSet(Integer.parseInt(request.getParameter("nodeToBeToggled").toString()));}
    //else
    //{setNodesStatusAsBitSet();}
    
    if ( renderType == LookupTreeV10.DEFAULT_TREE)
	{
		if (this.showToolBar) this.renderToolBar(startFromId); 
	    
	    try {
	    m=getDrwStr(startFromId);
	    }
	    catch(Exception e){out.println(e.getMessage());};
	}
	else if ( renderType == LookupTreeV10.LSIT_ITEMS)
	{
		m=this.getMenuListItems(startFromId);	
	}

	else if ( renderType == LookupTreeV10.JQUERY_MENU)
	{
		m=this.getJQueryMenu(startFromId);	
	}
	else if ( renderType == LookupTreeV10.JQUERY_TREE)
	{
		m=this.getJQueryTree(startFromId);	
	}
	else if ( renderType == LookupTreeV10.GO_JS_TREE)
	{
		m=this.toJsonForGoJs();	
	}

    out.println("<table "+this.getTableAttributes()+">" + m+"</table>");
    
    
    
 }


//------------------------------------------------------------
public void setTargetUrl(String turl){
targetUrl=turl;
}
//--------------------------------------------------------------
public String getTargetUrl(int index){
	String result = (String) DataAsVectors[9].elementAt(index) ; 
	return (result == null )? "" :result ;
	//return targetUrl;
}
//--------------------------------------------------------------
public void setPKName(String v_PKName){
this.pKName=v_PKName;
}
//-----------------------------------------------------------
public void setTargetFrame(String V_TargetFarme){
TargetFrame=V_TargetFarme;
}
//------------------------------------------------
public boolean isLastSon(int index)
{
boolean lastSon=true;
int pIndex=getParentIndex(index);
if (pIndex !=-1)
    {
    java.util.Vector brothersId=sonsIds[pIndex];
        String lastSonId=brothersId.elementAt(brothersId.size()-1).toString();
          if (! DataAsVectors[0].elementAt(index).toString().equals(lastSonId))
            {lastSon=false;}
    }
return lastSon;
}
//---------------------------------------------------------------
public String getTargetFrame(){
return TargetFrame;
}
//----------------------------------------------------
public String getLevelV2(int index){
String level="";
int vIndex=index;
int pIndex;
  while (vIndex!=-1)
    {
      pIndex=getParentIndex(vIndex);
      if (pIndex != -1)
        { if (! isLastSon(pIndex))
          {level="1"+level;}
          else
          {level="0"+level;}
        }
      vIndex=pIndex;
    }
    return level;
}
//-------------------------------------------------------------
public int getLevel(int index)
{
int level=this.getLevelV2(index).length();
return level;
}
//-----------------------------------------------------------------
public Vector[] getAllDescendants(String id)
{
allDescendants=new Vector[4];
allDescendants[0]=new Vector(); //for id in the tree
allDescendants[1]=new Vector(); // for level
allDescendants[2]=new Vector(); // for Desc
allDescendants[3]=new Vector(); //--- FOR ID in its table
getAllDescendantBasic(id);
return allDescendants;
}
//-------------------------
private Vector[] getAllDescendantBasic(String id)
{
int index=DataAsVectors[0].indexOf(id);
  String desc= getDesc(index);
Vector sonsId= sonsIds[index];
Integer level=new Integer(getLevel(index)+1);
String sonIdinTable="";
  for (int i=0; i<sonsId.size();i++)
  {
    allDescendants[0].add(sonsId.elementAt(i));
    allDescendants[1].add(level);
    int sonIndex=DataAsVectors[0].indexOf(sonsId.elementAt(i).toString());
    allDescendants[2].add(getDesc(sonIndex));
    sonIdinTable= DataAsVectors[6].elementAt(sonIndex).toString();
    allDescendants[3].add(sonIdinTable);
    if (!isLeaf(sonIndex))
     {
     getAllDescendantBasic(sonsId.elementAt(i).toString());//---recursive Calling to the method
     }
   }
return allDescendants;
}
//-----------------------------------------------------------------
public String getParentName( int index){
String pId;
String pName =null;
if (DataAsVectors[2].elementAt(index) != null)
    { pId= DataAsVectors[2].elementAt(index).toString();
      index=DataAsVectors[0].indexOf(pId);
      //getIndex(pId);
      if (this.loggedUser != null && this.loggedUser.isUsrLangEnglish())
      {pName = DataAsVectors[4].elementAt(index).toString();}
      else
      {pName = DataAsVectors[5].elementAt(index).toString();}
    }
return pName;

}
//-----------------------------------------------------------------
  public String getFullPath(String id) throws Exception
  {
   return getFullPath(this.getindex2(id));
  }
  public String getFullPath(int index){
  String  o_value= getDesc(index);
  int vIndex=index;
  while ( getParentName(vIndex)!= null )
    {
      o_value= getParentName(vIndex)+ "/" + o_value;
      vIndex= getParentIndex(vIndex);
    }
  return o_value;
  }
//----------------------------------------------------------------------
public String getFullPathIndex(int index){
//  int index=getIndex(id);
  String  o_value= getDesc(index);
  int vIndex=index;
  while ( getParentName(index)!= null )
    {
      o_value= getDesc(getParentIndex(index))+ "/" + o_value;
      vIndex= getParentIndex(vIndex);

    }
  return o_value;
}
//---This method returns a two dimentional array containing Full Path of the Leafs and thier id's------
public String[][] getleafs(boolean withFullPath)
{
Vector x[]=new Vector[2];
x[0]=new Vector();
x[1]=new Vector();
for (int i=0; i < DataAsVectors[0].size(); i++)
  {
    if (isLeaf(i))
      {
        x[0].addElement(getIdInTable(i));
        if (withFullPath)
          {x[1].addElement(getFullPath(i));}
        else
          {x[1].addElement(getDesc(i));}
        //out.println("<p>"+getId(i)+"..."+getFullPath(i));
      }
  }

String [][] leafsFullPath=new String[2][x[0].size()];
for (int i=0 ;i< x[0].size(); i++)
  {
    leafsFullPath[0][i]=x[0].elementAt(i).toString();
    leafsFullPath[1][i]=x[1].elementAt(i).toString();
  }
return leafsFullPath;
}
//--------------------------------------------------------------
public void renderLeafsAsComboBox(String v_Name,boolean withFullPath)
{
 boolean showFullPath=false;
 if (request.getParameter("showFullPath") != null)
  {
   showFullPath= (request.getParameter("showFullPath").toString().equals("true"))? true:false;
   String x=(showFullPath)? "false" : "true";
   String title= (showFullPath)?  "Show Desc. Only" : "Show Full Path" ;
   out.println("<a href =" + request.getRequestURI() + "?showFullPath=" + x + ">"+title+"</a><p>" );
  }
 else
 {out.println("<a href =" + request.getRequestURI() + "?showFullPath=true>Show Full Path</a><p>" );}


  String[][] x=getleafs(showFullPath);
  out.println("<select size=1 name="+v_Name+">");
  for (int i=0; i<x[0].length; i++)
      {
        if (! x[0][i].toString().equals("0"))
          { out.println("<option value="+x[0][i].toString()+">"+x[1][i].toString()+"</option>");}
      }

  out.println(" </select></p>");
}

//-----------render sons of item as a combo box------------------
public void renderSonsAsComboBox(String id, String v_Name, boolean returnDesc) throws Exception
{
int index=DataAsVectors[0].indexOf(id);
Vector sonsIds=getSonsId(index);

out.println("<select size=1 name="+v_Name+">");

for (int i=0; i<sonsIds.size(); i++)
  {

    String x;
    String value;
    if (returnDesc)
    {value= getDesc(getindex2(sonsIds.elementAt(i).toString()));}
    else {value= sonsIds.elementAt(i).toString();}
    x= getDesc(getindex2(sonsIds.elementAt(i).toString()));
    out.println("<option value="+value+">"+x+"</option>");
  }
out.println(" </select></p>");
}

//---------------
public boolean isMiddelSon(int index)
{
boolean middelSon=true;
int pIndex=getParentIndex(index);
if (pIndex!=-1)
    {
    java.util.Vector brothersId=sonsIds[index];
        int young=Integer.parseInt(brothersId.elementAt(0).toString());
        int old=Integer.parseInt(brothersId.elementAt(brothersId.size()-1).toString());
          if ( (index == young) || (index==old ) )
            {middelSon=false;}
    }
return middelSon;
}
//-------------------Obslete------------------------------------

public byte getNodeStatusAsBinary(int index){
byte nodeStatusAsBinary=0; //opened

if (request.getParameter("nodesStatusAsLong")!=null)
{  long nodesStatusAsLong= Long.parseLong(request.getParameter("nodesStatusAsLong").toString());
  String nodesStatusAsBinaryString= Long.toBinaryString(nodesStatusAsLong);
  if (nodesStatusAsBinaryString.length() <= index)
    {nodeStatusAsBinary=0;}
  else
      {char nodeStatusAsChar= nodesStatusAsBinaryString.charAt(nodesStatusAsBinaryString.length()-index-1);
        if (nodeStatusAsChar=='1')
          {nodeStatusAsBinary=1;}
      }
}
return nodeStatusAsBinary;
}
//---------------------------------------------------------------
public void setNodesStatusAsBitSet(BitSet m_nodeStatus)
{
	this.nodesStatusAsBitSet = m_nodeStatus ;
}
public void setNodesStatusAsBitSet()
{


  if ( nodesStatusAsBitSet == null ) //session.getValue("nodesStatusAsBitSetFor"+DataAsVectors[5].elementAt(0).toString())== null)
    {
	  	nodesStatusAsBitSet=new BitSet(DataAsVectors[0].size());
        for (int i=0; i<=DataAsVectors[0].size()-1 ;i++)
           {nodesStatusAsBitSet.set(i);}
    }
}
//------------
public BitSet getNodesStatusAsBitSet()
{
	if (nodesStatusAsBitSet == null ) setNodesStatusAsBitSet() ; 
return nodesStatusAsBitSet;
}
public void openNode(String nodeId) throws Exception
{
	int index = this.getindex2(nodeId) ; 
	getNodesStatusAsBitSet().clear(index); 
}
public void closeNode(String nodeId) throws Exception
{
	int index = this.getindex2(nodeId) ; 
	getNodesStatusAsBitSet().set(index); 
}

//--------------------------------
public void TogNodesStatusAsBitSet(int index)
{

    // setNodesStatusAsBitSet();
	BitSet nodesStatusAsBitSet = getNodesStatusAsBitSet() ; 
    if (index < nodesStatusAsBitSet.size())
      {
        if (nodesStatusAsBitSet.get(index))
        {nodesStatusAsBitSet.clear(index);}
        else
        {nodesStatusAsBitSet.set(index);}
      }
    //session.putValue("nodesStatusAsBitSetFor"+DataAsVectors[5].elementAt(0).toString(), nodesStatusAsBitSet);
}
//--------------------------------------------------------------
public void renderMaster(int index)
{
String tableName;
if (request.getParameter("tableName")!=null)
{ tableName= request.getParameter("tableName").toString();
out.println("<table border=1 width=69%>"
  +"<tr>"
    +"<td width=25% bgcolor=#FFFF00>Id</td>"
    +"<td width=75%>"+DataAsVectors[0].elementAt(index) +"</td>"
  +"</tr>"
  +"<tr>"
    +"<td width=25% bgcolor=#FFFF00>Code</td>"
    +"<td width=75%>"+DataAsVectors[1].elementAt(index) +"</td>"
  +"</tr>"
  +"<tr>"
    +"<td width=25% bgcolor=#FFFF00>Parent id</td>"
    +"<td width=75%>"+DataAsVectors[2].elementAt(index) +"</td>"
  +"</tr>"
  +"<tr>"
    +"<td width=25% bgcolor=#FFFF00>Header Id</td>"
    +"<td width=75%>"+DataAsVectors[3].elementAt(index)+"</td>"
  +"</tr>"
  +"<tr>"
    +"<td width=25% bgcolor=#FFFF00>Arabic</td>"
    +"<td width=75%>"+DataAsVectors[4].elementAt(index)+"</td>"
  +"</tr>"
    +"<tr>"
    +"<td width=25% bgcolor=#FFFF00>English</td>"
    +"<td width=75%>"+DataAsVectors[5].elementAt(index)+"</td>"
  +"</tr>"

+"</table>");
out.println(( (index!=0)? "<p><a href="+request.getRequestURI()+"?index="+getParentIndex(index)+"&tableName="+tableName+">One Level Up</a></p>" : "You are at The Root </p>"));
}
else out.println("No Table Specified ");
}

public void renderDetail(int index) throws Exception
{
String tableName;
if (request.getParameter("tableName")!=null)
{
tableName= request.getParameter("tableName").toString();
out.println("<br>"+ "<table border=1 width=78%>"
+"<tr>"
    +"<td width=11%>Id</td>"
    +"<td width=17%>Code</td>"
    +"<td width=17%>Parent Id</td>"
    +"<td width=30%>Header Id</td>"
    +"<td width=25%>Arabic</td>"
    +"<td width=34%>English</td>"
    +"<td width=34%>Edit</td>"
    +"<td width=34%>Sub Items</td>"
    +"<td width=34%>Delete</td>"
 +"</tr>"
);
int sonIndex;
for (int i=0; i<=sonsIds[index].size()-1;i++)
  {
  sonIndex=getindex2(sonsIds[index].elementAt(i).toString());

  out.println(
      "<tr>"
          +"<td width=11%>"+DataAsVectors[0].elementAt(sonIndex) +"</td>"
          +"<td width=17%>"+DataAsVectors[1].elementAt(sonIndex)+"</td>"
          +"<td width=17%>"+DataAsVectors[2].elementAt(sonIndex)+"</td>"
          +"<td width=30%>"+DataAsVectors[3].elementAt(sonIndex)+"</td>"
          +"<td width=25%>"+DataAsVectors[4].elementAt(sonIndex)+"</td>"
          +"<td width=34%>"+DataAsVectors[5].elementAt(sonIndex)+"</td>"
          +"<td width=34%><a href = EditForm.jsp?id=" +getId(sonIndex)+ "&tableName=" +tableName+ ">Edit item</a></td>"
          +"<td width=34%>" +((sonsIds[sonIndex].size()!=0)?  "<a href = "+request.getRequestURI()+"?index="+sonIndex+"&tableName=" +tableName + ">Show Sons</a>":"Single")+"</td>"
          +"<td width=34%>"+((sonsIds[sonIndex].size()==0)? "<a href = "+request.getRequestURI()+"?idToBePhysicalDeleted="+DataAsVectors[0].elementAt(sonIndex)+"&lastModiDate="+DataAsVectors[8].elementAt(sonIndex).toString().replace(' ','_')+"&index="+index+"&tableName=" +tableName +" > L. delete</a>":"")+ "</td>"
       +"</tr>"
  );
  }
  out.println("</table>");
}
}
//-------------------------render deleted items-------------
public void renderAllDeleted()
{
String tableName;
if (request.getParameter("tableName")!=null)
{tableName= request.getParameter("tableName").toString();
out.println("<br>"+ "<table border=1 width=78%>"
+"<tr>"
    +"<td width=11%>Id</td>" +"<td width=17%>Code</td>" +"<td width=17%>Parent Id</td>"  +"<td width=30%>Header Id</td>"    +"<td width=25%>Arabic</td>"
    +"<td width=34%>English</td>" +"<td width=34%>Restore</td>"+"<td width=34%>P.Delete</td>"
 +"</tr>"
);

  for ( int i=0; i<DataAsVectors[0].size(); i++)
    out.println(
      "<tr>"
          +"<td width=11%>"+DataAsVectors[0].elementAt(i)+"</td>"
          +"<td width=17%>"+DataAsVectors[1].elementAt(i)+"</td>"
          +"<td width=17%>"+DataAsVectors[2].elementAt(i)+"</td>"
          +"<td width=30%>"+DataAsVectors[3].elementAt(i)+"</td>"
          +"<td width=25%>"+DataAsVectors[4].elementAt(i)+"</td>"
          +"<td width=34%>"+DataAsVectors[5].elementAt(i)+"</td>"
          +"<td width=34%><a href ="+request.getRequestURI()+"?idToBeRestored="+DataAsVectors[0].elementAt(i)+"&tableName="+tableName+"&lastModiDate="+DataAsVectors[8].elementAt(i).toString().replace(' ','_') + ">Restore</a></td>"
          +"<td width=34%><a href ="+request.getRequestURI()+"?idToBeDeleted="+DataAsVectors[0].elementAt(i) +"&tableName="+tableName+"&lastModiDate="+DataAsVectors[8].elementAt(i).toString().replace(' ','_') + ">Delete </a></td>"
       +"</tr>"

  );

  }
}

//-------------------------------------------------------------
//private int getSonsViewLimit(){return sonsViewLimit;}
//private void incSonsViewLimit(){sonsViewLimit=sonsViewLimit+10;}
//---------------------------------------------------------------

public void setAddFormUrl(String v_AddFormUrl)
{
this.addFormUrl=v_AddFormUrl;
}
//-----------------
public void setAddFormTargetFram(String v_AddFormTargetFram)
{
this.AddFormTargetFrame=v_AddFormTargetFram;
}
//----------------------------------
public void setShowAddLink(boolean v_ShowAddLink)
{
this.ShowAddLink=v_ShowAddLink;
}
//-------------------------
public void setShowToolBar(boolean v_showToolBar)
{
this.showToolBar=v_showToolBar;
}
//--------------
public void setRecycleBinURL(String v_RecycleBinURL)
{
this.recycleBinURL=v_RecycleBinURL;
}
//--------------
//public void setAddFormURL(String v_AddFormURL)
//{
//this.addFormURL=v_AddFormURL;
//}

public void showRecycleBinButton(boolean v_ShowRecycleBin)
{
this.showRecycleBin=v_ShowRecycleBin;
}
public void setShowCheckBox(boolean v_ShowCheckBox)
{
showCheckBox=v_ShowCheckBox;
}
//------------------
public void setElementCode(String v_ElementCode)
{
elementCode= v_ElementCode;
}

public void setLinkAllInPopup(boolean v_linkAllInPopup)
{
linkAllInPopup= v_linkAllInPopup;
}
public void setShowMoveForm(boolean v_ShowMoveForm)
{
showMoveForm=v_ShowMoveForm;
}

boolean firstCall = true; 
private String getMenuListItems(String id)throws Exception
{
	String menuListItems="";

	int index=this.DataAsVectors[0].indexOf(id);
  String DescHref = this.getDescHref(id);
  java.util.Vector sonsId = sonsIds[index];
  int sonsSize=sonsId.size();


  boolean nodeClosed = nodesStatusAsBitSet.get(index); //nodesStatus[index].equals("c");
  boolean displayThisNode = isNodeIncluded(index);
  int level = getLevelV2(index).length();
  boolean isLeaf=isLeaf(index);
  String desc=  getDesc(index);
  if ( ( level ==1 && displayThisNode)  || level !=1 )
  {
	  //--------------if the node is leaf or the node is closed-----------
	  if  (isLeaf || nodeClosed)
      {
		  menuListItems=  "<li>" + DescHref +  "</li>" ; 
	  }
	//-----------------else--------------------------------
	  else
	  { 
		  String menuIdProp = "" ; 
		  if (firstCall)
		  {
			  menuIdProp = "id = \"menu" +id + "\"";
			  firstCall = false ; 
		  }
		  menuListItems = "<ul "+menuIdProp + " >" + menuListItems ; 
	    for (int i=0; i<=sonsSize-1;i++)
	       {
	        String yyy=sonsIds[index].elementAt(i).toString();
	            if (execludedIds.size()==0 || execludedIds.indexOf(yyy)< 0)
	            	menuListItems=menuListItems+getMenuListItems(yyy);
	       }
	    menuListItems =  menuListItems + "</ul>"; 
	    boolean hyperLinked=(Integer.parseInt(DataAsVectors[3].elementAt(index).toString().trim())==0)? false : true;

	    menuListItems=    "<li>" + DescHref
	                           //+ desc+((hyperLinked)? "</a>" : "") 
	                           + ((this.showCheckBox)? "<input type=checkbox name=C1"+id+"&nbsp value=ON>" : " " )
	                           + menuListItems  + "</li>" ;
	   }
  }
  

return  menuListItems ;
}

public String getJQueryMenu(String id ) throws Exception
{
	String appURL = Misc.getAppURL(request);
	boolean displayAsList = request.getParameter("displayAsList") != null ;
	String jqueryScript = "<div align = \"center\" > <a href= \"queryTree.jsp\" title = \"Refresh تجديث\"><img src=\"images/toolbar_icon_reload_active.gif\" width=\"30\"></img></a></div>"
	      + " <link rel=\"stylesheet\" href=\""+appURL+"/jQueryAssets/jquery-ui.css\"> "
		  + "\n<script src=\""+appURL+"/jQueryAssets/jquery-1.10.2.js\"></script>"
		  + "\n<script src=\""+appURL+"/jQueryAssets/jquery-ui.js\"></script> "
		  + "\n<link rel=\"stylesheet\" href=\"/resources/demos/style.css\"> "
		  + "\n<script>"
		  + ((displayAsList)? "" : "\n$(function() {" )
		  + ((displayAsList)? "" : "\n  $( \"#menu"+id+"\" ).menu();")
		  + ((displayAsList)? "" :"\n});")
		  + "\n</script>"
		  + "\n<style>"
		  + "\n.ui-menu { width: 250px; }" 
		  + "\n </style>" ; 
	  String menuListItems = getMenuListItems(id) ; 
	  firstCall = true ; 
	  return jqueryScript + "\n" + menuListItems ; 
}

private String getJqueryTreeNode(String id) throws Exception
{ 
	  int index=this.DataAsVectors[0].indexOf(id);
	  String  DescHref="";
	  /*
	    if (index== -1)  // item not found
	      {
	        out.print ("Node not fount");
	        throw new Exception("Node not fount");
	      }
	  String tableName=DataAsVectors[1].elementAt(index).toString();
	  String idInTable=DataAsVectors[6].elementAt(index).toString();
	  String headerId="0";
	  if (DataAsVectors[7].elementAt(index)!=null)
	  {headerId=DataAsVectors[7].elementAt(index).toString();}
	  String menuListItems="";
	  
	 
	  String desc=  getDesc(index);
	  boolean isLeaf=isLeaf(index);
	  boolean hyperLinked=(Integer.parseInt(DataAsVectors[3].elementAt(index).toString().trim())==0)? false : true;
	  String  DescHref="";
	  boolean isSmartToolAdmin = this.loggedUser.isSmartToolAdmin() ;
	  if (hyperLinked)
	   {
		  String targetUrl = getTargetUrl(index) ;  

	      DescHref=  "<a title =\""+ DataAsVectors[8].elementAt(index) +"\""+ " href=\""+targetUrl+((targetUrl.indexOf("?")<0)? "?":"&" )+"id="+idInTable+"&lookupTableName=" + tableName +"\""+" target =\""+ getTargetFrame() +"\""
	                //+ " "+"style=\"background-color: #FFFFFF\"   id=\""+ idInTable + "\" isLeaf=\""+ isLeaf + "\" tableName=\""+ tableName + "\" headerId=\""+headerId+"\" name=\""+ DataAsVectors[0].elementAt(index) //+ "\" onClick=\"UnselectAllLinks(); changeColor(this.name);\""
	                + ">" ;

	   }
	  */
	  DescHref = this.getDescHref(id); 
	  java.util.Vector sonsId = sonsIds[index];
	  int sonsSize=sonsId.size();


	  boolean nodeClosed = getNodesStatusAsBitSet().get(index); //nodesStatus[index].equals("c");
	  boolean displayThisNode = isNodeIncluded(index);
	  int level = getLevelV2(index).length();
	  String menuListItems="";
	  if ( ( level ==1 && displayThisNode)  || level !=1 )
	  {
		  //--------------if the node is leaf or the node is closed-----------
		  boolean isSmartToolAdmin = this.loggedUser.isSmartToolAdmin() ;
		  String tableName=DataAsVectors[1].elementAt(index).toString();
		  String idInTable=DataAsVectors[6].elementAt(index).toString();
		  String headerId="0";
		  if (DataAsVectors[7].elementAt(index)!=null)
		  {headerId=DataAsVectors[7].elementAt(index).toString();}

		  String addLink = ((isSmartToolAdmin )? "<a href=" + addFormUrl+((addFormUrl.indexOf("?")==-1)? "?"+this.getParentIdVarName()+"=" :"&"+this.getParentIdVarName()+"=")+idInTable+"&tableName="+tableName.trim()+ "&headerId="+headerId+" target ="+ AddFormTargetFrame + "> <img border=0 src="+imagePath+"toolplus.gif width=14 height=14 alt=\"Add Sub Item xxx\"></a>" :"" ) ; 
		  String editLink = ((isSmartToolAdmin || this.isShowEditLink() )? "<a title \"Edit Query Only Without Execution\"  href= "+DataAsVectors[10].elementAt(index).toString()+"?id="+idInTable+"&tableName="+tableName+ "&headerId="+headerId+" target ="+ AddFormTargetFrame + "> E </a>" : "");  
		  boolean isLeaf=isLeaf(index);


		  if  (isLeaf || nodeClosed)
	      {

			  menuListItems=  "\n{ label:'" + DescHref + addLink + editLink +" ' }" ; 
		  }
		//-----------------else--------------------------------
		  else
		  { 
			  
			  menuListItems = "\n\t, children : \n\t["  ; // + menuListItems 
			  int addedChildCounter = 0 ; 
		    for (int i=0; i<=sonsSize-1;i++)
		       {
		        String yyy=sonsIds[index].elementAt(i).toString();
		        if (execludedIds.size()==0 || execludedIds.indexOf(yyy)< 0)
		        {
		        	String childJqueryTreeNode = getJqueryTreeNode(yyy) ; 
		        	if (!childJqueryTreeNode.equalsIgnoreCase(""))
		        	{
		        		menuListItems=menuListItems + ((addedChildCounter==0)? "" : ",") + childJqueryTreeNode ;
		        		addedChildCounter ++ ; 
		        	}
		        }
		       }
		    menuListItems =  menuListItems + "\n ]"; 
		    menuListItems=    " \n { label: '" + DescHref  + addLink + editLink +"' "  
		                           + menuListItems  + "\n }" ;
		   } 
	
}
	  return  menuListItems ;
}

public String getJQueryTree(String id) throws Exception
{	
	String result = "<div align = \"center\" > <a href= \"\" title = \"Refresh تجديث\"><img src=\"images/toolbar_icon_reload_active.gif\" width=\"30\"></img></a></div>" 
+"\n	<div id=\"tree1\"></div>"
+"\n	<script src=\"jQueryAssets/jquery-1.8.3.min.js\"></script> "
+"\n	<script src=\"jQueryAssets/jquery-tree/tree.jquery.js\"></script> "
+"\n	<link rel=\"stylesheet\" href=\"jQueryAssets/jquery-tree/jqtree.css\"> "
+"\n	<script src=\"jQueryAssets/jquery.cookies.2.2.0.js\"></script> "
+"\n	<script> "
+"\n	var data = [ "
+"\n " + this.getJqueryTreeNode(id)
+"\n " + "] ; "  
+"\n	$(function() { "
+"\n		$('#tree1').tree" 
+"\n		({ "
+"\n	        data: data, "
+"\n	        autoOpen: true, "// saveState: true,"
+"\n	        dragAndDrop: true,  "
+"\n	        autoEscape: false "
+"\n		});"
+"\n	});"
+"\n	</script>" ;


	return result ; 
}
//public void setNodeEditorPage(String nodeEditorPage) {
//	this.nodeEditorPage = nodeEditorPage;
//}
//public String getNodeEditorPage() {
//	return nodeEditorPage;
//}
public void setStartFromNodeId(String startFromNodeId) {
	this.startFromNodeId = startFromNodeId;
}
public String getStartFromNodeId() {
	return startFromNodeId;
}
public void setParentIdVarName(String parentIdVarName) {
	this.parentIdVarName = parentIdVarName;
}
public String getParentIdVarName() {
	return parentIdVarName;
}
public void initialize (ServletContext m_app , HttpSession m_session , HttpServletRequest m_req , HttpServletResponse m_res , JspWriter m_jspw ) throws Exception
{
	super.initialize(m_app, m_session, m_req, m_res, m_jspw) ;
	m_session.setAttribute(this.treeIdInSession, this); 
	this.setSecurityManager(new Support.QueryTreeSecurity(this, (Connection) this.session.getAttribute("con"))) ; 
}

public String getPathLinks(String m_id) throws Exception
{
	String pathLinks = "";
	int nodeTreeIndex = this.getindex2("LU_QUERIES"+m_id) ; 
	String  nodeDesc= this.getDesc(nodeTreeIndex);
	int vIndex=nodeTreeIndex;
	
	  
	while ( this.getParentName(vIndex)!= null && ! this.getId(vIndex).equalsIgnoreCase("LU_QUERIES305"))
	{
		  nodeDesc= this.getParentName(vIndex); //+ "/" + nodeDesc;
	      vIndex= this.getParentIndex(vIndex);
	      pathLinks = "<a target = mainFrame href = 'editAndExecute.jsp?id="+ this.getId(vIndex).substring("LU_QUERIES".length())+"' >"+nodeDesc+"</a>-->" + pathLinks ;   
    }
	pathLinks = "<strong><font color=#FF0000 size=2><em>" + pathLinks ;
	
	return pathLinks ; 
}

public void setOperationMode(String m_operationMode) {
	
	this.operationMode = (m_operationMode==null ) ? POINTER_OPERATION_MODE : m_operationMode;
}
public String getOperationMode() {
	return operationMode  ;
}


public boolean isPointerMode ()
{
	return this.getOperationMode().equalsIgnoreCase(POINTER_OPERATION_MODE); 
}

public boolean isSingleSelectMode ()
{
	return this.getOperationMode().equalsIgnoreCase(SINGLE_SELECT_OPERATION_MODE); 
}

public boolean isMultiSelectMode ()
{
	return this.getOperationMode().equalsIgnoreCase(MULTI_SELECT_OPERATION_MODE); 
}
public void setSelectedIDs(HashMap<String, String> selectedIDs) {
	this.selectedIDs = selectedIDs;
}

public void setSelectedID(String selectedID) throws Exception {
	if (this.selectedIDs == null ) {selectedIDs = new java.util.HashMap<String , String >(); } 
	this.selectedIDs.put(selectedID, null ) ; 
	int nodeIndex = this.getindex2(selectedID); 
	this.openAllParentsNodes(nodeIndex) ; 
}

public void openAllParentsNodes(int m_nodeIndex)
{
	int parentNodeIndex = this.getParentIndex(m_nodeIndex) ; 
	if (parentNodeIndex != -1 )
	{
		this.getNodesStatusAsBitSet().clear(parentNodeIndex) ; 
		openAllParentsNodes (parentNodeIndex) ; 
	}
}

public HashMap<String, String> getSelectedIDs() {
	return selectedIDs;
}

public  String getSelectedIDsCommaSeperated() {
	String v_result = "" ;  
	Iterator<Entry<String, String>> it = this.getSelectedIDs().entrySet().iterator() ; 
	boolean hasValues = false ; 
	while (it.hasNext())
	{
		v_result += ","+it.next().getKey() ; 
		hasValues = true ; 
	}
	return (hasValues)? v_result.substring(1) : v_result;
}

public  String getSelectedDescsCommaSeperated() throws Exception {
	String v_result = "" ;  
	Iterator<Entry<String, String>> it = this.getSelectedIDs().entrySet().iterator() ; 
	boolean hasValues = false ; 
	while (it.hasNext())
	{
		String desc = "Not Found" ; 
		try {desc = this.getDesc(this.getindex2(it.next().getKey())) ;}
		catch (Exception e ) {}
		v_result += ","+  desc ; 
		hasValues = true ; 
	}
	return (hasValues)? v_result.substring(1) : v_result;
}


public void setFillObject(String fillObject) {
	this.fillObject = fillObject;
}
public String getFillObject() {
	return fillObject;
}

public int getTreeRootNodeIndex() {
	return treeRootNodeIndex;
}
public void setQuerySource(int querySource) {
	this.querySource = querySource;
}
public int getQuerySource() {
	return querySource;
}

private int querySource = 1; 

public String getDescForListOfIds(String idCommaSeperated) throws Exception
{
	String v_result = "" ; 
	StringTokenizer st = new StringTokenizer(idCommaSeperated , ",") ; 
	while  (st.hasMoreTokens())
	{
		String id = st.nextToken() ; 
		v_result += this.getDesc(this.getindex2(id.trim())) + ", "; 
	}
	
	return v_result ; 
}
public void setSqlBoundVarName(String sqlBoundVarName) {
	this.sqlBoundVarName = sqlBoundVarName;
}
public String getSqlBoundVarName() {
	return sqlBoundVarName;
}
public void setTableAttributes(String tableAttributes) {
	this.tableAttributes = tableAttributes;
}
public String getTableAttributes() {
	return tableAttributes;
}
public String toJsonForGoJs()
{
	StringBuffer v_result = new StringBuffer(); 
	for ( TreeNode treeNode : this.getTreeNodes() ) 
	{
		v_result.append("\n " +treeNode.toJsonForGoJs()); 
	}
	
	return v_result.toString() ;  
}
public TreeNode[] getTreeNodes() {
	return this.treeNodes ; 
}
public String renderNodeForNavBar(String m_id) throws Exception
{	String line = null ; 
	try{
		int nodeTreeIndex = this.getindex2("LU_QUERIES"+m_id) ;
		boolean containsQuMark = getTargetUrl(nodeTreeIndex).indexOf("?") >= 0  ; 
		line = "<a href=\"javaScript:sendAjaxRequestToJsp('/SmartTool/Company/20/"+getTargetUrl(nodeTreeIndex)+((containsQuMark)? "&" : "?")+"id="+m_id+"', 'contentDiv' ) ; \"><span class=\"text\"><span class=\"text\" data-localize=\""+this.getDesc(nodeTreeIndex)+"\">"+this.getDesc(nodeTreeIndex)+"</span></a>" ;
		}
	catch (Exception e ){}
return line;
	
}

public String getNavBarSonsList(String m_startFromNode ,  boolean m_recurssive) throws Exception
{
	String navBarList = "" ; 
	try {
		Vector<String> sonsIds =  this.getSonsId( this.getindex2("LU_QUERIES"+m_startFromNode)) ; 
		if (sonsIds.size() > 0 )
		{
			navBarList = "<ul class=\"acc-menu\">" ; 
		
			String sonId = null ; 
			for (int i = 0 ; i< sonsIds.size() ; i++)
			{   
				sonId = ((String)sonsIds.get(i)).substring(10); 
				navBarList +="\n<li>" + this.renderNodeForNavBar(sonId);
				if (m_recurssive) navBarList += getNavBarSonsList(sonId , m_recurssive) ; 
				navBarList +="\n</li>" ;  
			}
			navBarList += "\n</ul>" ; 
		}
	} 
	catch ( Exception e){}
	return navBarList ;  
}

}