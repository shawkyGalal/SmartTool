package Support;

 /* Author=Shawky Foda----------- 
//--created 1/8/2001----------
//--purpose : this class offers a set of methods to facilate to the progarmmer to:


//----------3- also this class provide some methods which calls PL/SQL functions and prodecures in the SEKPKG and MISC packages
//----------4- Use a secure dataBase transactions methods
 */
//import oracle.jbo.*;

import java.sql.*;
import java.util.*;
import javax.servlet.jsp.JspWriter;

public class HTMLDbTransactionBeanWOPooling extends oracle.jdeveloper.html.WebBeanImpl
{
String selectClause;
String tableName;
String whereClause;
String queryStatment;
public String insertStatment;
String orderBy;
Connection con;
int conIndex;
public Statement stmt;
String userLang="1";
String DbSession;
String pKName="id";
String fKName="header_id";
String E_DscColumnName="E_Dsc";
String A_DscColumnName="A_Dsc";
boolean getDatesAsStrings=true;
boolean enableSingleQuteToDoubleQutConver= true;
public boolean popUpLimitFromSysParm= true;
public ResultSet resSet;
int popupLimit=1000;
String luPopupPath = "../LuMaintenance_html/";
String imagePath = "../webapp/images/";
Vector columnNames = new Vector();
public Vector getColumnNames()
{
  return this.columnNames;
}
public void myInitialize(javax.servlet.ServletContext application, javax.servlet.http.HttpSession session, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, JspWriter out , Connection conx ) throws Exception
{
  this.initialize(application,session,request,response,out);
  this.con=conx;
  this.stmt = con.createStatement();
}
//----------------------------------------------------------------------------------                           
public void setEnablesingleQuteToDoubleQutConver(boolean x)
{//--------this will enable or disable converting single qute strings to doublequte strings to be inserted in the database--
this.enableSingleQuteToDoubleQutConver=x;
}
public void setluPopupPath(String m_luPopupPath)
{
luPopupPath = m_luPopupPath;
}
//------------------------------
public void setImagePath (String m_imagePath)
{
  imagePath = m_imagePath;
}
//---------------------------------------------------
//public void myInitialize(PageContext page)
//{
//try
//{initialize(application, session, request,response,out);
//if (session.getValue("lang") != null)
// {
//  userLang=  session.getValue("userLang").toString();
// }
//}
//catch(Exception e){}
//}

//-------------------------------------
public void setSelectClause(String v_SelectClause, String elementCode) throws Exception
{
String roleId="";
boolean withSecurity=((elementCode.equals("")||elementCode==null )? false : true );
if (withSecurity)
 {
 roleId=session.getValue("roleId").toString();
 }
String xxxxxxx="";
if (v_SelectClause.equals("*"))
{
  Vector columnNames =getTableColumnNames(tableName);
  xxxxxxx=columnNames.elementAt(0).toString();
  for (int i=1; i<columnNames.size(); i++)
    {
      xxxxxxx+=", "+ columnNames.elementAt(i);
    }
}
selectClause= ( (v_SelectClause.equals("*"))? xxxxxxx : v_SelectClause ) +
              ((withSecurity)? ","
               + getAccessColumnQS(elementCode, "S"): "" );

}
//---------------------------------------------
private String getAccessColumnQS(String elementCode, String AccessType)
{
String accessColumnQS= null;
int ConnectionNO = 0;
try{
ConnectionNO = Integer.parseInt(this.getSessionParameterValue("connNumber"));
String roleId=this.getSessionParameterValue("roleId");

  //----------execute get_Access_Column pl/SQL function-----------
    //this.setDBConnection();
    CallableStatement data_statment=con.prepareCall("{?= call SECPKG.Get_Access_Col(?,?,?)}");
    data_statment.setString(2,elementCode);
    data_statment.setString(3,roleId);
    data_statment.setString(4,AccessType);
    data_statment.registerOutParameter(1, Types.VARCHAR);
    data_statment.execute();
    accessColumnQS=data_statment.getString(1).toString();
    data_statment.close();
    }
catch (SQLException e){out.print(e.getMessage());}
catch (Exception e){out.print(e.toString());}
finally
{
  //ConnectionPoolManager.releaseConnection(ConnectionNO, conIndex);
  //out.println("Shawky:\n"+e.getMessage());
}
return accessColumnQS;
}
//-----------------
public String getSessionParameterValue(String sessionParameter ) throws Exception
{
String sessionParameterValue=null;
if (session.getValue(sessionParameter)!=null)
{
sessionParameterValue=session.getValue(sessionParameter).toString();
}
else
{
throw new Exception("Session Parameter not Found in the session <br> You may Not Logged in Yet Please Login (no roleId found)");
}
return sessionParameterValue;
}
//--------------------

public void setTableName(String v_TableName)
{
tableName= v_TableName;
}

//---------------------------------------------

public void setWhereClause(String v_WhereClause)
{
whereClause= v_WhereClause;
}
//---------------------------------------------

public void setOrderBy(String v_OrderClause)
{
orderBy=v_OrderClause;
}
//-----------------------------------------------------


public void setQueryStatment()throws Exception
{
  boolean noWhere= (whereClause==null)? true: false;

  queryStatment="select"+" " + selectClause + " "+
              "From" + " " + tableName + " "
              +((noWhere)? " " : "Where" + " " + whereClause + " ")
              +(!(orderBy==null)? "Order By "+orderBy: " " );
}

//---------------------------------------------
public void setQueryStatment(String v_queryStatment)
{
queryStatment=v_queryStatment;
}
//--------------------------------------------------------
/*
private void setDBConnection()/
{
 int ConnectionNO=0 ;
try {
    //------Try to Get a Free Connection From the Connection Pool Manager-----
    ConnectionNO = Integer.parseInt(this.getSessionParameterValue("connNumber"));
    Vector x= ConnectionPoolManager.getFreeConnection(ConnectionNO);
    con = (Connection) x.elementAt(0);
    conIndex=Integer.parseInt(x.elementAt(1).toString());
    //out.println("This transaction have been done using the Connection Number" +conIndex );
    stmt = con.createStatement();
    }
catch(Exception e){
  System.out.println(e.getMessage());
  out.println("Error: Unable to Esatablish Connection to Database "+e.getMessage());}
}*/
//------------------------------------------------------------------
public void setDBConnection(Connection conx) throws SQLException
{
  this.con  = conx;
  this.stmt = con.createStatement();
}
//
private void setResultSet() throws Exception
{
int ConnectionNO= 0 ;
try{
    //ConnectionNO = Integer.parseInt(this.getSessionParameterValue("connNumber"));
    //setDBConnection();
    if (this.queryStatment==null)
      {setQueryStatment();}
    resSet = stmt.executeQuery(queryStatment);
   }
catch(Exception e)
  {
    //---------Release the Connection to the Connection Pool Manager-----

    //ConnectionPoolManager.releaseConnection(ConnectionNO, conIndex);
    throw new Exception("<BR>Unable to Execute the Following Query Statment :" + "<BR>"+queryStatment + "\n" +e.getMessage());
    /*out.println("<BR>Unable to Execute the Following Query Statment :");
    out.println("<BR>"+queryStatment);
    out.println("<BR>"+e.getMessage()); */
  }
finally
{
    //---------Release the Connection to the Connection Pool Manager-----
    // ConnectionPoolManager.releaseConnection(conIndex);
}
}
//---------------------------------------------------------------
private ResultSet getResultSet()
{
return resSet;
}
//----------------------------------------------------------
public Vector[] getResultSetAsArrayofVectors() throws Exception
{
ResultSet resSet;
Vector[] resultSetAsArrayofVectors=null;
int rowCounter=0;
Object x;
java.sql.Date date;
int y;
int ConnectionNO = 0;
//try
{
    //ConnectionNO = Integer.parseInt(this.getSessionParameterValue("connNumber"));
    setResultSet();
    resSet=getResultSet();

    int  ColumnCount=resSet.getMetaData().getColumnCount();

    resultSetAsArrayofVectors=new Vector[ColumnCount];
    
    for (int i=0; i<ColumnCount; i++)
    {
      resultSetAsArrayofVectors[i]=new Vector();
      columnNames.addElement(resSet.getMetaData().getColumnName(i+1).toString());
    }
      while ( resSet.next())
      {
        for (int columnNumber=1; columnNumber<=resSet.getMetaData().getColumnCount();columnNumber++)
          {
            //--the following line for debugging
            int type = resSet.getMetaData().getColumnType(columnNumber);
            if (type==93 && !getDatesAsStrings) // The Column type is Date
              {
               x=resSet.getDate(columnNumber);
              }
            else
              {
               x=resSet.getString(columnNumber);
              }
            y=columnNumber-1;
            resultSetAsArrayofVectors[y].addElement(x);
          }
        rowCounter++;
      }
//    if (rowCounter==0)
//    throw new Exception("No Data Found form the Query "+queryStatment);
    resSet.close();
//   stmt.close();
   }
//catch (Exception e)
//  {
//    out.println(e.getMessage());
//  }
//finally
//{
//    //---------Release the Connection to the Connection Pool Manager-----
//    //ConnectionPoolManager.releaseConnection(ConnectionNO , conIndex);
//}

return resultSetAsArrayofVectors;
}
//---------------------------------------------------------------------
public void renderResSet() throws Exception
{
ResultSet ResSet;
setResultSet();
ResSet=getResultSet();
String Data;
int rowCount=1;
int ConnectionNO = 0 ;
try{
ConnectionNO = Integer.parseInt(this.getSessionParameterValue("connNumber"));
out.println("<table border=1> <tr><td></td></tr>");
    while ( ResSet.next())
      {out.println("<tr><td>"+rowCount+"</td>");
        for (int i=1; i<=ResSet.getMetaData().getColumnCount();i++)
          {
             Data= ResSet.getString(i);
             out.println("<td>"+ Data +"</td>");
          }
           out.println("</tr>");
           rowCount++;
      }
    out.println("</table>");

    ResSet.close();
    //stmt.close();
    }
catch(Exception e)
  {
    out.println("Error");
  }
finally
  {
    // if (conIndex!=0)
    {//---------Release the Connection to the Connection Pool Manager-----
    //ConnectionPoolManager.releaseConnection(ConnectionNO, conIndex);
    }
  }
}
//------------
public Vector getTableColumnNames(String tableName) throws Exception
{
Vector tableColumnNames=null;
String qs= "select column_Name from sys.user_tab_columns where table_name = '"+tableName.toUpperCase()+"' ";
this.setQueryStatment(qs);
tableColumnNames= getResultSetAsArrayofVectors()[0];
return tableColumnNames;
}
//-------------------------------------------------------------------
public int executeInsert(String[] columnNames, String[] values,boolean withAudit  ) throws Exception
{
int insertResult=0;
int auditResult=-1;
String tablePrimaryKey=null;
 Vector tableprimaryKeys= getPrimaryKeyName();
tablePrimaryKey=(tableprimaryKeys.size()>0 && tableprimaryKeys.elementAt(0)!= null)? tableprimaryKeys.elementAt(0).toString() :"";

String tableNextSequValue=null;
int ConnectionNO = 0;
//---------Checking if the primery key already send in the method argment columnNames-------
boolean primaryKeyalreadySent= false;
for (int i=0 ; i< columnNames.length; i++)
{
  if (columnNames[i].toUpperCase().equals(tablePrimaryKey.toUpperCase()) || tablePrimaryKey.equals(""))
  {primaryKeyalreadySent=true;}
}

if (!primaryKeyalreadySent){
  tableNextSequValue=this.getSeqNextValue();
}


//----the primary key value will be inserted manually , without a database trigger----
//setDBConnection();
try
  {
    if (columnNames.length!=values.length)
      throw new Exception("Column size not equal value size");
      String columnsNamesAsString= (primaryKeyalreadySent)? columnNames[0] : tablePrimaryKey  ;     //Previously ----String columnsNamesAsString=columnNames[0];
      String valuesAsString=(primaryKeyalreadySent)? values[0]: "'"+tableNextSequValue+"'"; //Previously ----String valuesAsString=values[0];
      int loopIndex= (primaryKeyalreadySent)? 1:0;
    for (int i=loopIndex ;i<columnNames.length; i++)            //Previously ----    for (int i=1 ;i<columnNames.length; i++)
      { columnsNamesAsString=columnsNamesAsString+","+columnNames[i];}
    for (int i=loopIndex ;i<values.length; i++)                 //Previously ----  for (int i=0 ;i<values.length; i++)
      {
      valuesAsString=valuesAsString+"," + ( (enableSingleQuteToDoubleQutConver)? replaceSingleQuteWithDouble(values[i]): values[i]);
      }

    insertStatment="Insert into "+" "+tableName+
                   " ("+columnsNamesAsString+" )"+
                   " values (" + valuesAsString + " )";
    System.out.print(insertStatment);
    if (withAudit)
    {
      auditResult= auditTransaction();
    }
    if (auditResult==1 || !withAudit)
    {
        //con.setAutoCommit(false);
        insertResult = stmt.executeUpdate(insertStatment);
        boolean commit=true;
        //--------commit need to be recalculated here--------
        boolean withoutSecurity=true;
        //--------withoutSecurity need to be completed--------
        if (commit || withoutSecurity)
        con.commit();
        else {
        con.rollback();
        throw new Exception("Insufficient Authorization to Add an Item");
        }
    }
    else {out.println("Sorry can not insert with auditing");}
    if (withAudit && auditResult==1)
      {unAuditTransaction(DbSession);}

    //  out.println(insertResult+ "Record(s) added ");
    //stmt.close();
    //out.println("<P>"+getMessage("LU-004"));
    ConnectionNO = Integer.parseInt(this.getSessionParameterValue("connNumber"));
  }
  catch(SQLException e)
  {
    if (withAudit && auditResult==1)
      {unAuditTransaction(DbSession);}
    out.println(" SQL Error: No data added... "+ e);
    out.println("localized Message : "+ e.getLocalizedMessage());
    out.println("Error Message : "+ e.getMessage());
    throw new Exception("Unable to execute the following SQL statement <br>" + insertStatment + "<br> SQL Error : <br>" +e.getMessage() );
  }
finally
{
    //---------Release the Connection to the Connection Pool Manager-----
    //ConnectionPoolManager.releaseConnection(ConnectionNO , conIndex);
}
  return insertResult;
}//-------------------end of Method------------------


public int executeDeleteSecure(String pKName, String id, String lastModifiedDate, boolean checkOtherUpdates, boolean withAudit, String elementCode ) throws Exception
{/**--------------THIS METHOD CHECKS IF THE USER ROLE HAS THE PRIVILLAGE TO DELETE THIS RECORD OR NOT-------------*/
int result=-1;

int roleId= Integer.parseInt(getSessionParameterValue("roleId"));

boolean authorized=(checkAccessRight(elementCode, roleId, "D", id)==1)? true : false ;

if (authorized)
{
result = executeDelete(pKName, id, lastModifiedDate,  checkOtherUpdates, withAudit );
}
if (!authorized) throw new Exception("Insufficient Authorization");
return result;
}
//------------------------------
public int checkAccessRight(String elementCode, int roleId, String Access, String id) throws Exception
{
int result=-1;
int ConnectionNO = 0 ;
//setDBConnection();
try
  {
  CallableStatement cstmt =con.prepareCall("{?= call SecPKG.check_Access_Right(?,?,?,?)}");
  cstmt.setString(2,elementCode);
  cstmt.setInt(3,roleId);
  cstmt.setString(4,Access);
  cstmt.setString(5,id);
  cstmt.registerOutParameter(1,Types.NUMERIC);
  cstmt.execute();
  result= cstmt.getInt(1);
  //cstmt.close();
  ConnectionNO = Integer.parseInt(this.getSessionParameterValue("connNumber"));
  }
  catch (Exception e)
  {
  throw e;
  }
  finally
  {
   //---------Release the Connection to the Connection Pool Manager-----
   //ConnectionPoolManager.releaseConnection(    ConnectionNO, conIndex);
  }
return result;
}
//------------------------------------------------------------
public int executeDelete(String pKName,  String id, String lastModifiedDate, boolean checkOtherUpdates, boolean withAudit )
/**
This method uses the delete_Rec procedure in the Database to
logically or physically delete a record in the Database
----*/
{
int deleteResult=-1;
int auditResult=-1;
boolean rowOutOfDate=false;
//this.setDBConnection();
int ConnectionNO = 0;
try
  {
  CallableStatement cstmt =con.prepareCall("{call Misc.Delete_Rec(?,?,?,?,?)}");
  cstmt.setString(1,tableName);
  cstmt.setString(2,"HR");
  cstmt.setString(3,pKName);
  cstmt.setString(4,id);
  cstmt.registerOutParameter(5,Types.NUMERIC);
  if (withAudit)
    {
      auditResult= auditTransaction();
    }
  if (auditResult==1 || !withAudit )
    {
        if (checkOtherUpdates)
        {   //check if the record has been changed by another user
            setQueryStatment("select for update Modify_Seq from "+ " "+tableName+ " "+"where"+" "+pKName+"="+id);
            rowOutOfDate=getResultSetAsArrayofVectors().equals(lastModifiedDate);
            if (rowOutOfDate)
              {
               out.println(this.getMessage("LU-007"));
              }
        }
        if (!rowOutOfDate || !checkOtherUpdates )
           {
            cstmt.execute();
            deleteResult= cstmt.getInt(5);
            //cstmt.close();
           }
    }
    else {
    //out.println("Shawky: Sorry can not be Deleted with auditing");
        }
    ConnectionNO = Integer.parseInt(this.getSessionParameterValue("connNumber"));
  }
catch(Exception sqle)
  {
     if (withAudit && auditResult==1 )
      {
      this.unAuditTransaction(DbSession);
      }
//  out.println("Shawky:\n"+sqle.getMessage());
  }
finally
  {
   //---------Delete from the User_Session Table-------------------
    if (withAudit && auditResult==1)
    {
      unAuditTransaction(DbSession);
    }
   //---------Release the Connection to the Connection Pool Manager-----
   //ConnectionPoolManager.releaseConnection(ConnectionNO , conIndex);
  }
return deleteResult;
}
//---------------------
public int executeRestore(String pKName,  String id, String modifySeq, boolean checkOtherUpdates, boolean withAudit )
/**
This method uses the Restore_Rec procedure in the Database to
restore a record (change value of Deleted from 'T' to 'F') and restore values in both prev_Parent_Id and prev_Header_Id to both parent_id and Header_id
----*/
{
int deleteResult=-1;
int auditResult=-1;
boolean rowOutOfDate=false;
//this.setDBConnection();
int  ConnectionNO = 0;
try
  {
  CallableStatement cstmt =con.prepareCall("{call Misc.Restore_Rec(?,?,?,?)}");
  cstmt.setString(1,tableName);
  cstmt.setString(2,pKName);
  cstmt.setString(3,id);
  cstmt.registerOutParameter(4,Types.NUMERIC);
  if (withAudit)
    {
      auditResult= auditTransaction();
    }
  if (auditResult==1 || !withAudit )
    {
        if (checkOtherUpdates)
        {   //check if the record has been changed by another user
            setQueryStatment("select for update Modify_Seq from "+ " "+tableName+ " "+"where"+" "+pKName+"="+id);
            rowOutOfDate=getResultSetAsArrayofVectors().equals(modifySeq);
            if (rowOutOfDate)
              {
               out.println(this.getMessage("Gen-010"));
              }
        }
        if (!rowOutOfDate || !checkOtherUpdates )
           {
            cstmt.execute();
            deleteResult= cstmt.getInt(4);
            cstmt.close();
           }
    }
    else {out.println("Shawky: Sorry can not be Deleted with auditing");}
    ConnectionNO = Integer.parseInt(this.getSessionParameterValue("connNumber"));
  }
catch(Exception sqle)
  {
    out.println("Shawky:\n"+sqle.getMessage());
  }

finally
  {
   //---------Delete from the User_Session Table-------------------
    if (withAudit && auditResult==1)
    {
      unAuditTransaction(DbSession);
    }
       //---------Release the Connection to the Connection Pool Manager-----
   //ConnectionPoolManager.releaseConnection(ConnectionNO , conIndex);
  }
return deleteResult;
}
//-------------------------------------------------------------------------------
public int executePhysicallDelete(String DeleteCondition, boolean checkOtherUpdates , String lastModiDateFromForm, boolean withAudit ) throws Exception
{
int DeletetResult=0;
String DeleteStatment;
String LastModDateFromDB="";
int ConnectionNO = 0;
try
  {
    if (checkOtherUpdates)
    {
    //----------get the last modification date of the record from database----------
    setSelectClause ("Modify_Seq", "");
    setTableName(tableName);
    setWhereClause(DeleteCondition);
    try{this.setQueryStatment();}
    catch (Exception e){throw new Exception("xxxxxxx");}
    LastModDateFromDB=getResultSetAsArrayofVectors()[0].elementAt(0).toString().replace(' ','_');
    }
    //-----------------------if both values are equal-----------------------------------
    if (LastModDateFromDB.equals(lastModiDateFromForm) || !checkOtherUpdates )
      {
      DeleteStatment="Delete From "+" "+tableName +" " +"Where" + " "+ DeleteCondition;
      //setDBConnection();
      DeletetResult = stmt.executeUpdate(DeleteStatment);
      //stmt.close();
      //out.println(getMessage("LU-005"));
      }
    else
      {
        out.println("<p>"+this.getMessage("LU-007"));
        //out.println("The Record You Are Trying To Delete From DataBase Has Been Changed After Your Last Access");
        DeletetResult=-2;// indicate the record have been Changed
      }
      ConnectionNO = Integer.parseInt(this.getSessionParameterValue("connNumber"));
  }
catch(SQLException e)
{
out.println("Error : Cannot Delete the Record "+e);
throw e ;
}
finally
{
//---------Release the Connection to the Connection Pool Manager-----
//ConnectionPoolManager.releaseConnection(ConnectionNO , conIndex);
}
return DeletetResult;
}
//-----------------
public int executeUpdateSecure(String[] columnNames, String idToBeUpdated, String LastModDateFromForm, boolean checkOtherUpdate,boolean withAudit, String elementCode) throws Exception
{//--------This is an overloaded version of the executeUpdateSecure() methode but without the newValues to Update in the DB where
//--------- these values will be got from the request object using the getRequestParametersValue(columnNames) method.
//-----notes : 1- if any coulmname sent to this method is not in the request it will be Neglected.
//-------------2- the parameters send and recived from the request object should have the same name as the column nmes in the database
String[][] x= this.getRequestParametersValue(columnNames);
String[] newColumnNames=x[0];
String[] newValues=x[1];
int updateResult=this.executeUpdateSecure(newColumnNames,newValues,idToBeUpdated,LastModDateFromForm,checkOtherUpdate,withAudit,elementCode);
return updateResult;

}
//---------------------
public int executeUpdateSecure(String[] columnNames, String[] newValues, String idToBeUpdated, String LastModDateFromForm, boolean checkOtherUpdate,boolean withAudit, String elementCode) throws Exception
{

//-----if developer wants to use it without security just sent null or "" to the elementCode)
//-----if developer wants to use it without notes maintenance just send null to the notesValues
 //---notesValues should Contain the following
 //-------notesValues[0]= note value to be updated or inserted in the database table notes.
 //-------notesValues[1]= keyWords value to be updated or inserted in the database table notes.
 //-------notesValues[2]= E_dsc value to be updated or inserted in the database table notes.
 //-------notesValues[3]= A_dsc value to be updated or inserted in the database table notes.
 //-------notesValues[4]= code value to be updated or inserted in the database table notes.
 //-------notesValues[5]= "T" if The Page URL will be stored in the notes table
 //-------if notesValues==null treat exactly if without notes

boolean withoutSecurity= (elementCode==null || elementCode.equals("") )? true:false;
boolean authorized=false;
int updateResult=-1;
int deleteResult=-1;
int roleId= Integer.parseInt(getSessionParameterValue("roleId"));
if (!withoutSecurity)
 { authorized=(checkAccessRight(elementCode, roleId, "U", idToBeUpdated)==1)? true : false ; }
String[] notesValues= this.getNotesValues(true);
boolean doUpdate= authorized || withoutSecurity;
String noteId=null;
if (doUpdate)
{
int result=-1;
String[] newColumnNames=columnNames;
String[] newNewValues=newValues;
boolean withNotes=(notesValues==null )? false:true;
int oldLength=columnNames.length;
int newLength=(withNotes)? oldLength+1 : oldLength;
  if (withNotes)
    {//-------------Notes table Mainntenance----------------
    /*----------updateing the notes table--
    check if the item have already note_id vaule
      if it has,  execute update in the notes table or delete if the notes value =""
      if not,  execute insert in the notes table
   */
    setQueryStatment("select note_notes_id from "+tableName+" where " +pKName+"="+idToBeUpdated);
    Vector[] x= getResultSetAsArrayofVectors();
    boolean hasAlreadyNoteId= (x[0].elementAt(0)== null)? false : true;
     //String pageURL=request.getRequestURI()+"?"+request.getQueryString();
    if(hasAlreadyNoteId)
      {//------execute update in the notes table --------
       noteId=x[0].elementAt(0).toString();
       String[] notesColumnNames= {"note","KeyWords","E_dsc", "A_dsc","code","PAGEURL"};
       String tabNam=tableName; // store the table name
       setTableName("notes");

       if (notesValues[0]==null || notesValues[0].trim().equals("")|| notesValues[0].trim().equals("''") )
       {deleteResult= executeDelete("notes_id", x[0].elementAt(0).toString(),"",false,false);
        noteId=null;}
       else
       {
        executeUpdate(notesColumnNames , notesValues, "notes_id="+noteId,"",false, false);}
        setTableName(tabNam);
       }
    else if (!(notesValues[0]==null || notesValues[0].trim().equals("")|| notesValues[0].trim().equals("''") )) //---if it has not previos note_id value
    {//------execute Insert in the notes table--------
      String[] notesValuesWithNotesId =new String[notesValues.length+1];
      //System.arraycopy(notesColumnNames, 0, notesValuesWithNotesId, 0);
      for (int i=0 ; i<notesValues.length ;i++)
        {
          notesValuesWithNotesId[i]=notesValues[i];
        }
      setQueryStatment("select notes_seq.nextval from dual");
      noteId=this.getResultSetAsArrayofVectors()[0].elementAt(0).toString();
      notesValuesWithNotesId[notesValues.length]=noteId;
      String tabNam=tableName; // store the table name
      setTableName("notes");
      String[] notesColumnNames= {"note","KeyWords","E_dsc", "A_dsc", "code", "PageURL","notes_id" };
      executeInsert(notesColumnNames, notesValuesWithNotesId, false);
      setTableName(tabNam);   //  restore the table Name

    }//------------end of else --------------------------

  } //------------end of Notes table mainntenance---------

//----------Basic Table Mentainnce -------------------
      newColumnNames=new String[newLength];
      newNewValues=new String[newLength];
      for (int i=0 ; i<oldLength ;i++)
      {
      newColumnNames[i]=columnNames[i];
      newNewValues[i]=newValues[i];
      }
      if (withNotes)
      {newColumnNames[oldLength]="note_notes_id";
       newNewValues[oldLength]=noteId;}
      updateResult = executeUpdate(newColumnNames, newNewValues, "upper("+pKName+")="+idToBeUpdated.toUpperCase(), LastModDateFromForm, checkOtherUpdate, withAudit);
}

if (!doUpdate) throw new Exception("Insufficient Authorization");

return updateResult;
}
//---------------------
public int executeInsertSecure(String[] columnNames, String[] newValues,  boolean withAudit, String elementCode) throws Exception
{//----------------------not yet Completed-------------
int insertResult=-1;
int insertNoteresult=-1;
int roleId= Integer.parseInt(getSessionParameterValue("roleId"));
boolean authorized=false;
boolean withSecurity=(elementCode==null || elementCode.equals(""))? false : true;
String[] notesValues=this.getNotesValues(false);
String[] newColumnNames=columnNames;
String[] newNewValues= newValues;
String notesId=null;

String idToBeUpdated="";  /*-the id of the record if it is inserted in the DB */
if (withSecurity)
{authorized=(checkAccessRight(elementCode, roleId, "I",idToBeUpdated)==1)? true : false ;}
boolean doInsert=(!withSecurity || authorized );
if (doInsert)
{
  boolean withNotes=(notesValues==null || notesValues[0]==null || notesValues[0].trim().equals("''") || notesValues[0].trim().equals("''"))? false:true;
   if (withNotes)
    {//---------insertNotes Data-----------
          String[] notesValuesWithNotesId =new String[notesValues.length+1];
      setQueryStatment("select notes_seq.nextval from dual");
      notesId=this.getResultSetAsArrayofVectors()[0].elementAt(0).toString();
      for (int i=0 ; i<notesValues.length ;i++)
        {
          notesValuesWithNotesId[i]=notesValues[i];
        }
      notesValuesWithNotesId[notesValues.length]=notesId;
      String tabNam=tableName; // store the table name
      setTableName("notes");
      String[] notesColumnNames= {"note","KeyWords","E_dsc", "A_dsc", "code", "notes_id" };
      insertNoteresult = executeInsert(notesColumnNames, notesValuesWithNotesId, false);
      setTableName(tabNam);   //  restore the table Name
    }
    if (notesId != null && insertNoteresult==1 )
      {//------modifiy columnNames and values with the noteId---------
        newColumnNames=new String[columnNames.length+1];
        newNewValues=new String[columnNames.length+1];
        for (int i=0 ; i<columnNames.length ;i++)
          {
            newColumnNames[i]=columnNames[i];
            newNewValues[i]=newValues[i];
          }
        newColumnNames[columnNames.length]="note_notes_id";
        newNewValues[columnNames.length]=notesId;
      }
    insertResult = executeInsert(newColumnNames, newNewValues,withAudit);
 }
if (!authorized && withSecurity) throw new Exception("Insufficient Authorization");

return insertResult;
}
//------------------------
public int executeInsertSecure(String[] columnNames, boolean withAudit, String elementCode)throws Exception
{//--------This is an overloaded version of the executeinsertSecure() methode but without the newValues to Insert in the DB where
//--------- these values will be got from the request object using the getRequestParametersValue(columnNames) method.
//-----notes : 1- if any coulmname sent to this method is not in the request it will be Neglected.
//-------------2- the parameters send and recived from the request object should have the same name as the column nmes in the database
String[][] x= this.getRequestParametersValue(columnNames);
String[] newColumnNames=x[0];
String[] newValues=x[1];
int y= this.executeInsertSecure(newColumnNames,newValues,withAudit,elementCode);
return y;

}

//------------------------------------
public int executeUpdate(String[] columnNames, String[] newValues, String updateCondition, String LastModDateFromForm, boolean checkOtherUpdate,boolean withAudit) throws Exception
{
//--  This method executes update in the Databse
//-- it returns
      //---------1 if updates executes successfully
      //---------0 if updates did not execute successfully
      //------- -2 if the record you are trying to modify have been changed by another user
String setClause=columnNames[0]+"="+newValues[0];
int updateResult=0;
int auditResult=0;
String updateStatment="";
String LastModDateFromDB="";
int ConnectionNO = 0;
for(int i=1 ; i<columnNames.length ;i++)
  {
  setClause=setClause+" "+","+" "+columnNames[i]+"="+newValues[i];
  }
try
  {
     //----------get the last modification date of the record from database----------
    setSelectClause ("Modify_Seq", "");
    setTableName(tableName);
    setWhereClause(updateCondition);
    setQueryStatment();
    LastModDateFromDB=(checkOtherUpdate)? getResultSetAsArrayofVectors()[0].elementAt(0).toString().replace(' ','_') : "";
    //-----------------------------------
    if (!checkOtherUpdate || LastModDateFromDB.equals(LastModDateFromForm) )
    {
    updateStatment="Update"+" "+tableName +" "+ "SET" +" "+setClause +" "+"where"+" "+updateCondition;
    System.out.println(updateStatment);
    //setDBConnection();//-------------set the Connection----------
    if (withAudit)
      {
        auditResult=this.auditTransaction();
      }
    if (auditResult==1 ||  !withAudit)
      {
        updateResult = stmt.executeUpdate(updateStatment);
      }
    //out.println("Update Executed Successfully");
    if (withAudit && auditResult==1 )
      {
      this.unAuditTransaction(DbSession);
      }

    //stmt.close();
    }
    else
    {
      //stmt.close();
      out.println("<p>"+this.getMessage("LU-010"));
      updateResult =-2;// indicate the record have been Changed
    }
      ConnectionNO = Integer.parseInt(this.getSessionParameterValue("connNumber"));
  }
catch(Exception e)
    {
      if (withAudit && auditResult==1 )
      {
        this.unAuditTransaction(DbSession);
        throw new Exception ("Cannot update the DataBase with the Following Statment:\n" +updateStatment );
      }
      if (withAudit && auditResult!=1 )
      {
        throw new Exception ("Can not Audit the Transaction.... " + e.getMessage());
      }
      out.print(e.getMessage());

    }
finally
{
//---------Release the Connection to the Connection Pool Manager-----
    //ConnectionPoolManager.releaseConnection(ConnectionNO, conIndex);
}
return updateResult;
}
//---------------------------------------------------
public int executeUpdate(String columnName, String newValue, String updateCondition)
{
int updateResult=0;
String updateStatment;
ResultSet rs;
int ConnectionNO = 0;
//setDBConnection();
try
  {
    updateStatment="Update"+" "+tableName +" "+ "SET" +" "+columnName+"="+newValue+ " "+"where"+" "+updateCondition;
    updateResult = stmt.executeUpdate(updateStatment);
    //out.println("Update Executed Successfully");
    //stmt.close();
    ConnectionNO = Integer.parseInt(this.getSessionParameterValue("connNumber"));
  }
catch(SQLException e)
  {
    out.println("Error : Cannot update the DataBase.. "+e);
  }
catch(Exception e)
  {
    out.println("Error : "+e);
  }

  finally
  {
      //---------Release the Connection to the Connection Pool Manager-----
    //ConnectionPoolManager.releaseConnection(      ConnectionNO , conIndex);

  }
return updateResult;
}
//------------------
public void test(int ConnectionNO)
  {
  Connection con;
  int conIndex =0;

  try
    {
    //------Try to Get a Free Connection From the Connection Pool Manager-----

    //Vector x= ConnectionPoolManager.getFreeConnection(ConnectionNO );
    //con = (Connection) x.elementAt(0);
    //conIndex=Integer.parseInt(x.elementAt(1).toString());
    //--------use this Connection--------------
    //stmt=con.createStatement();
    resSet=stmt.executeQuery("select * from Emp");
    out.println("no of columns.."+resSet.getMetaData().getColumnCount()+"..using connection Number.. "+ conIndex );
    out.println("<p><a href=untitled3.jsp>RELOAD</a></p>");

      for (int i=0; i<100; i++)
    {

    }
    //----Release the Connection to the Connection Pool Manager-----

    }
  catch (SQLException e) {out.println(e.getMessage());}
  catch (Exception e) {out.println(e.getMessage());}
  finally {    //ConnectionPoolManager.releaseConnection(ConnectionNO, conIndex);
          }
  }
//----------------------------------------------------
  public String getMessage(String messageCode) throws Exception
{
String sc="E_MSG";

if (session.getValue("userLang") != null)
 {
  userLang=  session.getValue("userLang").toString();
 }
if (userLang.equals("EN"))
  {
   sc="E_MSG"; }
else if (userLang.equals("AR"))
  {
  sc="A_MSG";}

String qs="select " +sc+ " from sys_msgs where code="+ "'"+ messageCode+"'";

setQueryStatment(qs);
Vector[] result=getResultSetAsArrayofVectors();
return result[0].elementAt(0).toString();
 }
//----------------------------------------------------
public List[] getPageItems(String pageCode, String type, String selectedId) throws Exception
{/**------send null or "" to the selectedId if without item security---*/
boolean withSecurity=(selectedId ==null || selectedId.equals(""))? false : true ;
List pageItems[]=new List[6];
String sc="", wc="";
if (type.equals("*"))
{
wc="where lower(Page_code)='"+pageCode.toLowerCase()+"'  " ;
}
else
{
wc="where Lower(Page_code)='"+pageCode.toLowerCase()+"' " +"and upper(typ)="+" '"+type.toUpperCase()+"' ";
}
//if (session.getValue("userLang")!=null && session.getValue("userLang").toString().equals("2"))
// {
//  session.putValue("userLang", "AR");
// }

//if (session.getValue("userLang")!=null && session.getValue("userLang").toString().equals("1") )
// {
//  session.putValue("userLang", "EN");
// }

userLang=  this.getSessionParameterValue("userLang");

if (userLang.equals("2"))
  {
    sc="Item_Code, E_DSC, E_LABEL";
    }
else if (userLang.equals("1"))
  {
    sc="Item_Code, A_DSC, A_LABEL";
  }
String qs="select " +sc+ " From MT_Items "+wc+" order by item_code";
setQueryStatment(qs);
Vector[] result=getResultSetAsArrayofVectors();

pageItems[0]=(List)result[0]; //Arrays.asList(namesAsArray);
pageItems[1]=(List)result[1];//Arrays.asList(descsAsArray);
pageItems[2]=(List)result[2];//Arrays.asList(lablesAsArray);
//---------initialize the security columns with ""----------
pageItems[3]=new Vector();
pageItems[4]=new Vector();
for (int i=0; i<result[0].size(); i++)
{
  pageItems[3].add("");
  pageItems[4].add("");
}
//------------End Of Initialization---------------
if (withSecurity )
{//----------get item security Access and append it to the pageItem---
String x= this.getPageItemsAccessQueryString(pageCode,selectedId);
  if ( !(x==null ||x.equals("")) )
  {
   this.setQueryStatment(x);
   Vector[] access=getResultSetAsArrayofVectors();
   if (access!=null && access[0].size()!= 0 && access[0].equals(result[0]))
    {
     pageItems[3]=(List)access[1];
     pageItems[4]=(List)access[2];
    }
   else {out.print("<Br>Warnning: page item security is not well established ");}
  }
}
return pageItems;
}
//-------------------------------------------------------
public String getPageItemsAccessQueryString(String PageCode, String recordId) throws SQLException
{
String resultedQueryStatment="";
int     ConnectionNO = 0;

//this.setDBConnection();
try
{
  int roleId=Integer.parseInt(this.getSessionParameterValue("roleId"));
  CallableStatement cstmt =con.prepareCall("{?= call SecPkg.check_Access_Page_Items(?,?,?)}");
  cstmt.registerOutParameter(1,Types.VARCHAR);
  cstmt.setInt(2,roleId);
  cstmt.setString(3,PageCode);
  cstmt.setInt(4,Integer.parseInt(recordId));
  cstmt.execute();
  resultedQueryStatment= cstmt.getString(1);
  cstmt.close();
  ConnectionNO = Integer.parseInt(this.getSessionParameterValue("connNumber"));
}
catch(Exception e)
{
  out.println("Shawky:\n"+e.getMessage());
  //throw sqle;
}
finally
{
 //---------Release the Connection to the Connection Pool Manager-----
 //ConnectionPoolManager.releaseConnection(ConnectionNO, conIndex);
}

return resultedQueryStatment;
}
//-------------------------------------------------------
public String generateQueryStatmentForTree(LookupTreeV10 treeOfTableNames, String tableId) throws Exception
{
int tableIndex=treeOfTableNames.getindex2(tableId);
String tableName=treeOfTableNames.getDesc(tableIndex);
int parentTableIndex;
String parentTableName;

String rootSelect="SELECT 'LU_ROOT'||ID as id, 'LU_ROOT' as tableName , decode(PARENT_ID,null,'','LU_ROOT'||PARENT_ID) AS PARENT, '0' linked,  A_DSC, E_DSC , id as idInTable FROM LU_ROOT";
String parentTableId;
String dummySelect;
String parentSelect;
String s="SELECT '"+tableName+"'||ID as id, '"+tableName+"' as tableName , decode(parent_id,0,'0"+tableName+"'||header_id,decode(PARENT_ID,null,'','"+tableName+"'||PARENT_ID)) AS PARENT, '1' linked,  A_DSC, E_DSC , id as idInTable FROM "+" "+tableName+" "+ "order by id";

while  (treeOfTableNames.getParentIndex(tableIndex)!=-1)
{
    parentTableIndex=treeOfTableNames.getParentIndex(tableIndex);
    tableName=treeOfTableNames.getDesc(tableIndex);
    parentTableId=treeOfTableNames.getId(parentTableIndex);
    parentTableName=treeOfTableNames.getDesc(parentTableIndex);
    dummySelect="SELECT '0"+tableName+"'||ID as id, '"+parentTableName+"' as tableName, '"+parentTableName+"'||ID  AS PARENT, '0' linked,  '"+tableName+"' as A_DSC, '"+tableName+"' as E_DSC , id as idintable FROM "+" "+parentTableName+" "+" where "+" " +parentTableName+".id not in (select nvl("+parentTableName+".Parent_Id,-1)from "+" "+parentTableName+")";
    parentSelect="SELECT '"+parentTableName+"'||ID as id, '"+parentTableName+"' as tableName , decode(parent_id,0,'0"+parentTableName+"'||header_id,decode(PARENT_ID,null,'','"+parentTableName+"'||PARENT_ID)) AS PARENT, '0' linked,  A_DSC, E_DSC , id as idInTable FROM "+" "+parentTableName;
    s=((parentTableIndex!=0)? parentSelect: rootSelect)
    +" "+"union"+" "
    +dummySelect+" "
    +"union"+" "
    +s ;
   tableIndex=parentTableIndex;
}

return s;
}

//-----------------------------
public String generateQueryStatmentForTree(String tableName)
{
String s="SELECT '"+tableName+"'||ID as id,'"+tableName+"' as tableName, decode(PARENT_ID,null,'','"+tableName+"'||PARENT_ID) AS PARENT, '0' linked, A_DSC, E_DSC, id as idintable FROM " + " "+tableName+" "+ "order by id";
return s;
}
//------------------------------------------------------

public  String getHeaderTableName(String tableName) throws Exception
{
String headerTableName="";
queryStatment="SELECT Comments FROM all_Col_COMMENTS where OWNER = '"+Misc.getConnectionUserName(this.con).toUpperCase()+"' and table_Name="+" '"+tableName.toUpperCase()+"'" +"and column_Name='HEADER_ID'";
Vector[] x=getResultSetAsArrayofVectors();
if (x[0].elementAt(0) !=null)
{
 headerTableName=x[0].elementAt(0).toString();
}

return headerTableName.toUpperCase();
}



public void renderAsSelectList(String tableName, boolean addHeaderCond, String headerId, String fieldName, String hiddenFieldName, String formName, String selectedId, boolean asTree ) throws Exception
{
userLang=(session.getValue("userLang") !=null)? session.getValue("userLang").toString() :"0";
boolean fKColumnIsVarchar=false;
boolean hasDeletedColumn=false;

if (addHeaderCond)
{//------------get data Type of the Header Column --------
String dataTypeQuery = "select DATA_TYPE from sys.user_tab_columns where table_name = '"+tableName.toUpperCase()+"' and column_name = '"+fKName.toUpperCase()+"'";
this.setQueryStatment(dataTypeQuery);
String colDataType = this.getResultSetAsArrayofVectors()[0].elementAt(0).toString();
  if(colDataType.equals("VARCHAR2"))
  {
    fKColumnIsVarchar=true;
  }
}
//-------------check if the table has a deleted field-----
String hasDeletedQuery ="select Column_Name from sys.user_tab_columns where table_name ='"+tableName.toUpperCase()+"' and column_Name='DELETED'";
//"select Column_Name from sys.user_tab_columns where table_name = '"+tableName.toUpperCase()+"' and column_name = '"+fKName.toUpperCase()+"'";
this.setQueryStatment(hasDeletedQuery);
System.out.println(hasDeletedQuery);
hasDeletedColumn=(getResultSetAsArrayofVectors()[0].size()==1)? true:false;

//--------
String noOfItemsQS=  "SELECT COUNT(*) as No_Of_Items FROM ("
            + "select"+" " +((this.userLang.equals("AR"))? pKName+"," +A_DscColumnName : pKName+", "+E_DscColumnName )
            + "  from" + " " + tableName+" "
            + ((hasDeletedColumn)? " " + " where NVL("+ tableName+ ".DELETED,-1)<>'T'" : "" )
            +((addHeaderCond)? ((hasDeletedColumn)? " and ":" where ") +fKName+"="+((fKColumnIsVarchar)? "'":"")+headerId+((fKColumnIsVarchar)? "')":")") : ")" );
setQueryStatment(noOfItemsQS);
System.out.println(noOfItemsQS);

int noOfItems=Integer.parseInt(getResultSetAsArrayofVectors()[0].elementAt(0).toString());

String qS= "select"+" " +((this.userLang.equals("AR"))? pKName+", " + A_DscColumnName  : pKName+", " +E_DscColumnName )
            + " from" + " " + tableName+" "
            + ((hasDeletedColumn)? " " + " where NVL("+ tableName+ ".DELETED,-1)<>'T'" : "" )
            + ((addHeaderCond)? ((hasDeletedColumn)? " and ":" where ") + fKName+"="+((fKColumnIsVarchar)? "'":"")+headerId+((fKColumnIsVarchar)? "'":"") : " ");
System.out.println(qS);


String popupLimitQS="select val from support.sys_params where id= 17";
setQueryStatment(popupLimitQS);
int popupLimit=Integer.parseInt(getResultSetAsArrayofVectors()[0].elementAt(0).toString());

if (noOfItems>popupLimit || asTree) // not
  {
    //-------if no of items > popupLimit render as a TexT Box and a gif
    //--------render as a popup window containing table-----------
    out.println(" <SCRIPT LANGUAGE='JavaScript1.1'> ");
    out.println("      function itemPick"+fieldName+"() ");
    out.println("        { ");
    out.println("          popupWin = window.open('LUpoppup.jsp?E_DscColumnName="+E_DscColumnName+"&A_DscColumnName="+A_DscColumnName+"&tableName="+tableName+"&headerId="+headerId+"&formName="+formName+"&asTree="+asTree+"&fieldName="+fieldName+"&hiddenFieldName="+hiddenFieldName+"&addHeaderCond="+addHeaderCond+"&pKName="+pKName+"&fKName="+fKName+"&fKColumnIsVarchar="+fKColumnIsVarchar+"&hasDeletedColumn="+hasDeletedColumn+"','imagepick','Scrollbars=1,status=1, toolbar=1 resizable=1,width=400,height=500'); ");
    out.println("        }");
    out.println("     </SCRIPT> ");
    String selectedDesc="";

    if (!selectedId.equals(""))
    {
    setQueryStatment(
                     "select "+ ( (userLang.equals("AR"))?  pKName + ", " + A_DscColumnName :  pKName + "," + E_DscColumnName )
                     +" "+"from" +" "+tableName+" " + "where "+ pKName + " ='"+selectedId+"'"
                     );
    selectedDesc=this.getResultSetAsArrayofVectors()[1].elementAt(0).toString();
    }
    selectedDesc= new Misc().toHTMLString(selectedDesc);
    out.println("Hidden <Input type=text name="+hiddenFieldName +" value="+selectedId+">");
    out.println("<Input Type=text name="+fieldName +" VALUE="+selectedDesc+" >");
    out.println("<a href= 'javascript:itemPick"+fieldName+"()'> <img border=0 src="+imagePath+"itempick.gif width=22 height=22></a>");
  }
else
  { //----------------simply render as a Combo box-----------
  setQueryStatment(qS);
  Vector[] data=this.getResultSetAsArrayofVectors();
    //--------javaScript to fill the hidden field with the selection
  out.println("<script language = javaScript>");
  out.println("function fillhiddenfield"+fieldName+"(x)");
  out.println("{document."+formName+"."+hiddenFieldName+".value=x; ");
  out.println("}");
  out.println("</script>");

  out.println("<Input type=hidden name="+hiddenFieldName +" value="+selectedId+">");
  out.println("<select size=1 name="+fieldName+ " onChange='fillhiddenfield"+fieldName+"(this.value);' >");
   out.println("<option >Select </option>");
   out.println("<option >------------</option>");
    for (int i=0; i<data[0].size(); i++)
      {
        if (! data[0].elementAt(i).toString().equals("0"))
          {out.println("<option value="+data[0].elementAt(i).toString()+((data[0].elementAt(i).toString().equals(selectedId))? " "+"selected" : "") +">"+data[1].elementAt(i).toString()+"</option>");}
          //{out.println("<option value="+(String)data[0].elementAt(i)+((data[0].elementAt(i).toString().equals(selectedId))? " "+"selected" : "") +">"+(String)data[1].elementAt(i)+"</option>");}
      }

  out.println(" </select>");

  }

//            out.println(noOfItems);
}

  //-----------------------------------------------------
public void renderLookupAsSelectList(String tableName, boolean addHeaderCond, String headerId, String fieldName, String hiddenFieldName, String formName,String selectedId, boolean asTree,String headerIDFieldName, boolean hyperLinkLeafOnly  ) throws Exception
{
boolean fKColumnIsVarchar=false;
boolean hasDeletedColumn=true;

userLang=(session.getValue("userLang") !=null)? session.getValue("userLang").toString() :"EN";
String noOfItemsQS=  "SELECT COUNT(*) as No_Of_Items FROM ("
            + "select"+" " +((userLang.equals("EN"))? "id, E_dsc " : "id, A_dsc ")
            + "from" + " " + tableName+" "
            + "where id not in (select NVL(Parent_id,-1) from" + " "+ tableName +")"
            + " " + " and NVL("+ tableName+ ".DELETED,-1)<>'T' and nvl (Active, 'T') <> 'F'"
            + ((addHeaderCond)? " and header_id="+headerId :"" ) + ")";
            setQueryStatment(noOfItemsQS);
System.out.println(noOfItemsQS);
int noOfItems=Integer.parseInt(getResultSetAsArrayofVectors()[0].elementAt(0).toString());

String qS= "select"+" " +((userLang.equals("EN"))? "id, E_dsc " : "id, A_dsc ")
            + "from" + " " + tableName+" "
            + "where id not in (select NVL(Parent_id,-1) from" + " "+ tableName +")"
            + " " + " and NVL("+ tableName+ ".DELETED,-1)<>'T' and nvl (Active, 'T') <> 'F' "
            +  ((addHeaderCond)? " and header_id="+headerId :"" );

String popupLimitQS="select val from sys_params where id= 2";
setQueryStatment(popupLimitQS);
int popupLimit=Integer.parseInt(getResultSetAsArrayofVectors()[0].elementAt(0).toString());

if (noOfItems>popupLimit || asTree) // not
  {
    //-------if no of items > popupLimit render as a TexT Box and a gif
    //--------render as a popup window containing table-----------
    out.println(" <SCRIPT LANGUAGE='JavaScript1.1'> ");
    out.println("      function itemPick"+fieldName+"() ");
    out.println("      { ");
    out.println("        if (document."+formName+"."+headerIDFieldName+"== null) ");    
    out.println("        { ");
    out.println("          popupWin = window.open('"+luPopupPath+"LUpoppup.jsp?hyperLinkLeafOnly="+ hyperLinkLeafOnly+"&E_DscColumnName="+E_DscColumnName+"&A_DscColumnName="+A_DscColumnName+"&tableName="+tableName+"&headerId="+headerId+"&formName="+formName+"&asTree="+asTree+"&fieldName="+fieldName+"&hiddenFieldName="+hiddenFieldName+"&addHeaderCond="+addHeaderCond+"&pKName="+pKName+"&fKName="+fKName+"&fKColumnIsVarchar="+fKColumnIsVarchar+"&hasDeletedColumn="+hasDeletedColumn+"','imagepick','Scrollbars=1,status=1, toolbar=1 resizable=1,width=400,height=500'); ");
    out.println("        }");    
    out.println("        else ");        
    out.println("        { ");
    out.println("         var headerIdValue = document."+formName+"."+headerIDFieldName+".value; ");    
    out.println("          popupWin = window.open('"+luPopupPath+"LUpoppup.jsp?hyperLinkLeafOnly="+ hyperLinkLeafOnly+"&E_DscColumnName="+E_DscColumnName+"&A_DscColumnName="+A_DscColumnName+"&tableName="+tableName+"&headerId='+headerIdValue+'&formName="+formName+"&asTree="+asTree+"&fieldName="+fieldName+"&hiddenFieldName="+hiddenFieldName+"&addHeaderCond="+addHeaderCond+"&pKName="+pKName+"&fKName="+fKName+"&fKColumnIsVarchar="+fKColumnIsVarchar+"&hasDeletedColumn="+hasDeletedColumn+"','imagepick','Scrollbars=1,status=1, toolbar=1 resizable=1,width=400,height=500'); ");
    out.println("        }");        
    out.println("      }");
    out.println("     </SCRIPT> ");
    String selectedDesc="";

    if (!selectedId.equals(""))
    {setQueryStatment("select"+ ( (userLang.equals("EN"))? " id, E_dsc ": " id, A_dsc " ) +" "+"from" +" "+tableName+" " + "where id ="+selectedId );
    selectedDesc=this.getResultSetAsArrayofVectors()[1].elementAt(0).toString();
    }
    selectedDesc= new Misc().toHTMLString(selectedDesc);
    out.println("<Input type=hidden name="+hiddenFieldName +" value="+selectedId+">");
    out.println("<Input Type=text name="+fieldName +" VALUE="+selectedDesc +" >");
    out.println("<a href= 'javascript:itemPick"+fieldName+"()'> <img border=0 src='"+imagePath+"itempick.jpg' width=22 height=22></a>");
  }
else
  { //----------------simply render as a Combo box-----------
  setQueryStatment(qS);
  Vector[] data=this.getResultSetAsArrayofVectors();
    //--------javaScript to fill the hidden field with the selection
  out.println("<script language = javaScript>");
  out.println("function fillhiddenfield"+fieldName+"(x)");
  out.println("{document."+formName+"."+hiddenFieldName+".value=x; ");
  out.println("}");
  out.println("</script>");

  out.println("<Input type=hidden name="+hiddenFieldName +" value="+selectedId+">");

  out.println("<select size=1 name="+fieldName+ " onChange='fillhiddenfield"+fieldName+"(this.value);' >");
  out.println("<option >Select </option>");
    out.println("<option >------------</option>");
    for (int i=0; i<data[0].size(); i++)
      {
        if (! data[0].elementAt(i).toString().equals("0"))
          { out.println("<option value="+data[0].elementAt(i).toString()+((data[0].elementAt(i).toString().equals(selectedId))? " "+"selected" : "") +">"+data[1].elementAt(i).toString()+"</option>");}
      }

  out.println(" </select></p>");

  }

//            out.println(noOfItems);
}
//-------------------//-------------------
private int auditTransaction() throws SQLException
{
int trackResult;
 //--------------get database session id----------
      ResultSet sessionIdRS= stmt.executeQuery("select userenv('SESSIONID') from dual");
      while (sessionIdRS.next())
        {
          DbSession=sessionIdRS.getString(1);
        }
 //--------------store userId and Dbsession in the sec_user_sessions table
  if (session.getValue("userId")!=null && DbSession!=null )
  {
    String userId=session.getValue("userId").toString();
    String insertAudit="insert into sys_user_sessions (userId, session_id)"+ " "
                              + "values ('"+userId+"','"+DbSession+"')";
    stmt=con.createStatement();
    trackResult=this.stmt.executeUpdate(insertAudit);
     if (trackResult==0)
      {
      out.println("Sorry the Transaction can not be Audited");
      throw new SQLException();
      }
  }
  else
  { trackResult=-1;
    out.println("Sorry You are not Logged yet");
    throw new SQLException();
  }
return trackResult;
}
//---------------------
private void unAuditTransaction(String v_DBsession) //throws SQLException
{
  int unAuditResut=0;
  String  deletFromUser_Session_table="delete from sys_user_sessions where session_id="+v_DBsession;
  try{
  unAuditResut=stmt.executeUpdate(deletFromUser_Session_table);
  if (unAuditResut==0)
  out.println("unable to unAudit in the sec_user_sessions table");
  }
  catch(Exception e){out.println("Can Not unAudit The transaction");}

}
//--------------------------------------------------------------
public void setPKName(String v_PKName){
this.pKName=v_PKName;
}

public void setFKName(String v_fKName){
this.fKName=v_fKName;
}

public boolean isRefrenced(String v_TableName, String PKName, String idValue) throws Exception
{
//-----this method checks if the given row in the database table is refrenced by any other table or not
boolean isRefrenced=true;
int   ConnectionNO = 0;
//setDBConnection();

try
  {
  CallableStatement cstmt =con.prepareCall("{?=call Misc.Check_Ref(?,?,?)}");
  cstmt.setString(2,v_TableName);
  cstmt.setString(3,PKName);
  cstmt.setString(4,idValue);
  cstmt.registerOutParameter(1,Types.NUMERIC);
  cstmt.execute();
  int check= cstmt.getInt(1);
  cstmt.close();
  isRefrenced=(check==1)? true:false;
  ConnectionNO = Integer.parseInt(this.getSessionParameterValue("connNumber"));
  }
  catch (Exception e)
  {
    out.print(e.getMessage());
    throw e;
  }
  finally
  {
  //ConnectionPoolManager.releaseConnection(    ConnectionNO , conIndex);
  }
return isRefrenced;
}
//--------------
public String calcDate(int afterMonthes, int afterDays)
{
//this.setDBConnection();
String x="";
int     ConnectionNO =0 ;

try {
    CallableStatement data_statment=this.con.prepareCall("{call misc.calc_date(?,?,?)}");
    data_statment.setInt(1,afterMonthes);
    data_statment.setInt(2,afterDays);
    data_statment.registerOutParameter(3, Types.DATE);
    data_statment.execute();
    x=data_statment.getDate(3).toString();
    data_statment.close();
    ConnectionNO = Integer.parseInt(this.getSessionParameterValue("connNumber"));
    }
catch (Exception e){out.print(e.getMessage());}
finally
{
  //ConnectionPoolManager.releaseConnection(    ConnectionNO , conIndex);
  //out.println("Shawky:\n"+e.getMessage());
}
return x;
}
//------------------

public String decrypt(String enPassword)
{
//this.setDBConnection();
String x="";
int  ConnectionNO =0;
try {
    CallableStatement data_statment=this.con.prepareCall("{?=call secpkg.decrypt(?)}");
    data_statment.setString(2,enPassword);
    data_statment.registerOutParameter(1, Types.VARCHAR);
    data_statment.execute();
    x=data_statment.getString(1).toString();
    ConnectionNO = Integer.parseInt(this.getSessionParameterValue("connNumber"));
    }
catch (Exception e){out.print(e.getMessage());}
finally
{
  //ConnectionPoolManager.releaseConnection(ConnectionNO , conIndex);
}
return x;
}
//------------------
public String getItemFromPageItems(List[] pageItems, int columnNumber, String index)
{
String x=null;

if (pageItems[0].indexOf(index)>=0 && pageItems[columnNumber].get(pageItems[0].indexOf(index))!=null)
{
x= pageItems[columnNumber].get(pageItems[0].indexOf(index)).toString();
}
//else x=index;;
return x;
}
//------------------------------------------
public void login(String defaultUserlang, String defaultUserId, String defaultUserRol,  String DbConnectionNo)
{
if ( defaultUserlang!= null && defaultUserId != null && defaultUserRol != null )

  {
    session.putValue("userLang",defaultUserlang);
    session.putValue("userId",defaultUserId);
    session.putValue("roleId",defaultUserRol);
    session.putValue("connNumber",DbConnectionNo);

  }
  else
  {
//    session.invalidate();
    session.removeValue("userLang");
    session.removeValue("userId");
    session.removeValue("roleId");
  }

}
public void simulateLogin(String defaultUserlang, String defaultUserId, String defaultUserRol, String connNumber)
{
//session.setMaxInactiveInterval(5);
//------------------------for userLang--------------------------------
if (request.getParameter("userLang")==null)// && session.getValue("userLang")== null)
    {
    session.putValue("userLang",defaultUserlang);
    }
if (request.getParameter("userLang")!=null)
    {
     session.putValue("userLang",request.getParameter("userLang").toString());
    }
//------------------------for userId--------------------------------
if (request.getParameter("userId")==null )//&& session.getValue("userId")== null)
    {
    session.putValue("userId",defaultUserId);
    }
  if (request.getParameter("userId")!=null )//&& (request.getParameter("roleId")!=null) )
    {
     session.putValue("userId",request.getParameter("userId").toString());
    }
//------------------------for roleId---------------------------------
if (request.getParameter("roleId")==null )//&& session.getValue("roleId")== null)
    {
    session.putValue("roleId",defaultUserRol);
    }
if (request.getParameter("roleId")!=null)
    {
     session.putValue("roleId",request.getParameter("roleId").toString());
    }

    //------------------------for connNumber---------------------------------
if (request.getParameter("connNumber")==null )//&& session.getValue("roleId")== null)
    {
    session.putValue("connNumber",connNumber);
    }
if (request.getParameter("connNumber")!=null)
    {
     session.putValue("connNumber",request.getParameter("connNumber").toString());
    }

}

//------------------------------------
public void renderNotesMaintForm(String id)
{//----------send id= null "" for insert(blanck) form ----------------
String noteId,code,E_Dsc, A_Dsc,  KeyWords, Notes, Reset;
String codeFromDB="",E_DscFromDB="", A_DscFromDB="",  KeyWordsFromDB="", notesFromDB="" , pageURLFromDB="";
String tableName1;
int y;
List[] notesPageItems;
Vector[] data;
boolean forUpdate,forUpdate2=false ,NoteFound=false;

forUpdate=(id==null || id.equals("") )? false : true ;

try {
  notesPageItems=getPageItems("NotesMaint","*", "");
  code=getItemFromPageItems(notesPageItems,2,"Code_Notes_Miant");
  E_Dsc=getItemFromPageItems(notesPageItems,2,"E_Dsc_Notes_Miant");
  A_Dsc=getItemFromPageItems(notesPageItems,2,"A_Dsc_Notes_Miant");
  KeyWords=getItemFromPageItems(notesPageItems,2,"KeyWords_Notes_Miant");
  Notes=getItemFromPageItems(notesPageItems,2,"Notes_Notes_Miant");
  Reset=getItemFromPageItems(notesPageItems,2,"Reset_Notes_Miant");

  if (forUpdate)
    {
     tableName1=request.getParameter("tableName");
      //id=request.getParameter("id");
      String x="select note_notes_id from "+tableName+ " where "+this.pKName+" = " +id ;
      System.out.print(x);
      setQueryStatment(x);
      data=getResultSetAsArrayofVectors();
      NoteFound= (data[0].elementAt(0)!=null)? true : false;
      if(NoteFound)
          {
          noteId=data[0].elementAt(0).toString();
          setQueryStatment("select code,E_Dsc, A_Dsc, keyWords, Note, pageUrl from Notes where notes_Id="+noteId);
          data=getResultSetAsArrayofVectors();
          codeFromDB=(data[0].elementAt(0)==null)? "" : data[0].elementAt(0).toString();           codeFromDB=new Misc().toHTMLString(codeFromDB);
          E_DscFromDB=(data[1].elementAt(0)==null)? "" : data[1].elementAt(0).toString();          E_DscFromDB=new Misc().toHTMLString(E_DscFromDB);
          A_DscFromDB=(data[2].elementAt(0)==null)? "" : data[2].elementAt(0).toString();           A_DscFromDB=new Misc().toHTMLString(A_DscFromDB);
          KeyWordsFromDB=(data[3].elementAt(0)==null)? "" : data[3].elementAt(0).toString();        KeyWordsFromDB=new Misc().toHTMLString(KeyWordsFromDB);
          notesFromDB=(data[4].elementAt(0)==null)? "" : data[4].elementAt(0).toString();
          pageURLFromDB=(data[5].elementAt(0)==null)? "" : data[5].elementAt(0).toString();
         }
    if (!NoteFound)
      { out.print ("<BR><center> <Big> No Note Found </center></Big>");
        forUpdate=false;
        forUpdate2=true;
      }
    }
    out.println("<TABLE border='1'  width='100%' cellpadding='3' cellspacing='0' bordercolor='#2b6b9f'><TR><TD>");
    
    out.println("<table border='0' class='tbT' width='100%'>");
    out.println("<tr>");
    /*out.println("<td width=27%>"+code+"</td>" );
          String htmlTextCode= "<td width='85%' colspan='3'><input type='text' name='Note_Code' size='31' ";
        if (!forUpdate )
          {out.println(htmlTextCode+"></td>");}
        else
          {out.println(htmlTextCode +"value="+ codeFromDB +" ></td>");}

    out.println("</tr>");*/
    out.println("<tr>");
          out.println("<td width='27%' nowrap>"+E_Dsc+"</td>");
      String htmlTextE_Dsc= "<td width='31%'><input type='text' name='Note_E_dsc' size='31'";
        if (!forUpdate)
          {out.println(htmlTextE_Dsc+"></td>");}
        else
          {out.println(htmlTextE_Dsc +"value="+ E_DscFromDB +" ></td>");}

       out.println("</TR><TR><td width='18%' nowrap>"+A_Dsc+"</td>");

      String htmlTextA_Dsc= "<td width='36%'><input type='text' name='Note_A_dsc' size='30' ";
        if (!forUpdate)
          {out.println(htmlTextA_Dsc+"></td>");}
        else
          {out.println(htmlTextA_Dsc +"value="+ A_DscFromDB +" ></td>");}

    out.println("</tr><tr><td width='27%'>"+KeyWords+"</td>");
       String htmlTextKeywords= "<td width='85%' colspan='3'><input type='text' name='Note_Keywords' size='55'";
        if (!forUpdate)
          {out.println(htmlTextKeywords+"></td>");}
        else
          {out.println(htmlTextKeywords +"value="+ KeyWordsFromDB +" ></td>");}

      out.println("</tr><tr><td width='27%'>"+Notes+"</td>");

       String htmlTextNotes="<td width='85%' colspan='3'>* Mandatory to be saved Otherwise these notes information will not saved (i.e. will be removed from the Database)  <br>"
                              +"<textarea rows='6' name='Note_Note' cols='41'>";
        if (!forUpdate)
          {out.println(htmlTextNotes+"</textarea></td>");}
        else
          {out.println(htmlTextNotes + notesFromDB +"</textarea></td>");}
    out.println("</tr></table></TD></TR></TABLE><p align=center>");

        if (forUpdate || forUpdate2)
          { String pageURL=request.getRequestURI()+"?"+request.getQueryString();
            out.println("<p><input type='checkbox' name='HyperLinkedInTheSearchResult'"+((pageURLFromDB.equals(""))? "": "checked") +" onClick=\"if (this.checked ) {PageURL.value='"+pageURL+"';} else {PageURL.value='';}\">&nbsp; HyperLink The Result of The Search to This Page ");
            out.println("<input type='hidden' name='PageURL' size='20'></p>");
          }
//    out.println("<input type='reset' value='"+Reset+"' name='Note_Reset'></p>");
}
catch (Exception e ) {}
}
//-----------------

public void renderFootNote() throws Exception
{
String userId="Not Looged IN";
String roleId="Not Looged IN"; String roleDesc="Not Looged IN";
String userName="";
String dateAndTime="";
String companyName="";

//try {
userId=this.getSessionParameterValue("userId");
roleId=this.getSessionParameterValue("roleId");
companyName=this.getSessionParameterValue("connNumber");
companyName=(companyName.equals("0"))? "R&S Company":"NCCI Company";
setQueryStatment("select emp_Aname from emp_master where upper(emp_no)='"+ userId.toUpperCase()+"'");
//setQueryStatment("select name from person, sec_users  where person.id= sec_users.person_id and   sec_users.userid='"+ userId+"'");
userName=getResultSetAsArrayofVectors()[0].elementAt(0).toString();
setQueryStatment("select E_dsc from sec_roles where id="+roleId);
roleDesc=getResultSetAsArrayofVectors()[0].elementAt(0).toString();
setQueryStatment("select sysdate from dual");
dateAndTime=getResultSetAsArrayofVectors()[0].elementAt(0).toString();

//}
//catch (Exception e){}
out.println("<BODY topmargin='0' leftmargin='0' >");
out.println("<link rel='stylesheet' href='Images/Theme1/Nvinterface.css' type='text/css'>");

out.println("<body class='botbar' leftmargin='0' topmargin='0' marginwidth='0' marginheight='0'> ");
out.println("<table border='0' width='98% cellpadding='0' cellspacing='0'>"
  +"<tr>"
    +"<td width='25%' class='botbar'>Copyright&copy; 2003 Shawky All rights reserved.</td>"
    +"<td width='8%' class='botbar'><b>User ID</b></td>"
    +"<td width='8%' class='botbar'><font color='#CC0000'>"+userId+"</td>"
    +"<td width='8%' class='botbar'><b>Employee</b></td>"
    +"<td width='10%' class='botbar'><font color='#CC0000'>"+userName+"</td>"
    +"<td width='5%' class='botbar'><b>Role</b></td>"
    +"<td width='15%' class='botbar'><font color='#CC0000'>"+roleDesc+"</td>"
//    +"<td width='5%'  class='botbar'><b>Date</b></td>"
//    +"<td width='20%' class='botbar'><font color='#CC0000'>"+dateAndTime.substring(0,10)+"</td>"
    +"<td width='10%' class='botbar'><b>Database/Server</b></td>"
    +"<td width='10%' class='botbar'><font color='#CC0000'>&nbsp;"+companyName+"</td>"
  +"</tr>"
+"</table>" );
out.println("</BODY>");
}
//-----------------------------------------------------------------
private String[] getNotesValues(boolean forUpdate)
{
    String[] notesValues;
    String Note_Note=   ((request.getParameter("Note_Note")!= null )? request.getParameter("Note_Note").toString() : null );
    if (Note_Note== null || Note_Note.trim() == "" )
    {
      notesValues=null; // treat the note form as if it is totlay empty if only the note textbox is empty
      return notesValues;
    }
    else
    {
      String Note_Keywords=   request.getParameter("Note_Keywords");
      String Note_E_dsc=   request.getParameter("Note_E_dsc");
      String Note_A_dsc=   request.getParameter("Note_A_dsc");
      String Note_Code=   request.getParameter("Note_Code");
      if (forUpdate)
        {
          String PageURL=request.getParameter("PageURL");
          notesValues=new String[]{"'"+ Note_Note+"'","'"+Note_Keywords+"'","'"+Note_E_dsc+"'","'"+Note_A_dsc+"'","'"+Note_Code+"'", "'"+PageURL+"'"};
        }
      else notesValues=new String[]{"'"+ Note_Note+"'","'"+Note_Keywords+"'","'"+Note_E_dsc+"'","'"+Note_A_dsc+"'","'"+Note_Code+"'"};

      return notesValues;
    }

}
//----------------------------
public void SetE_DscColumnName(String v_E_DscColumnName)
{
E_DscColumnName=v_E_DscColumnName;
}
//----------------------
public void SetA_DscColumnName(String v_A_DscColumnName)
{
A_DscColumnName=v_A_DscColumnName;
}
//----------------------------
private String getSystemParameterValue(int parmId) throws Exception
{
String popupLimitQS="select val from support.sys_params where id= "+parmId;
setQueryStatment(popupLimitQS);
String value=getResultSetAsArrayofVectors()[0].elementAt(0).toString();
//int popupLimit=Integer.parseInt(getResultSetAsArrayofVectors()[0].elementAt(0).toString());
return value;
}

public void renderQueryResultAsSelectList(String quertStmt, String formName, String fieldName, String hiddenFieldName, String selectedId, String otherAttributes, boolean asTree) throws Exception
{
//-quertStmt should have at least three columns with the following orders
//------1-The Id of the item
//------2- The Edsc
//------3- The Adsc

try{
userLang=this.getSessionParameterValue("userLang");
}
catch (Exception e){userLang="2";}

String noOfItemsQS=  "SELECT COUNT(*) as No_Of_Items FROM " +"  ("+quertStmt+") abcd";
setQueryStatment(noOfItemsQS);
//System.out.println(noOfItemsQS);

int noOfItems=Integer.parseInt(getResultSetAsArrayofVectors()[0].elementAt(0).toString());

//String popupLimitQS="select val from sys_params where id= 2";
//setQueryStatment(popupLimitQS);

if (popUpLimitFromSysParm)
{
// getting the popup limit parameter form the system Parameter Table
 popupLimit=Integer.parseInt(this.getSystemParameterValue(17));
}

if ( noOfItems>popupLimit || asTree)  // not
  {
    //-------if no of items > popupLimit render as a TexT Box and a gif
    //--------render as a popup window containing table-----------
    out.println(" <SCRIPT LANGUAGE='JavaScript1.1'> ");
    out.println("      function itemPick"+fieldName+"() ");
    out.println("        { ");
    out.println("          popupWin = window.open(\"LUpoppup.jsp?E_DscColumnName="+E_DscColumnName+"&A_DscColumnName="+A_DscColumnName+"&tableName="+tableName+"&formName="+formName+"&asTree="+asTree+"&fieldName="+fieldName+"&hiddenFieldName="+hiddenFieldName+"&pKName="+pKName+"&fKName="+fKName+"','imagepick','Scrollbars=1,status=1, toolbar=1 resizable=1,width=400,height=500\"); ");
    out.println("        }");
    out.println("     </SCRIPT> ");
    String selectedDesc="";
    if (!selectedId.equals(""))
    {
    setQueryStatment(
                     "select "+ ( (userLang.equals("1"))?  pKName + ", " + A_DscColumnName :  pKName + "," + E_DscColumnName )
                     +" "+"from" +" "+tableName+" " + "where "+ pKName + " ='"+selectedId+"'"
                     );
    selectedDesc=this.getResultSetAsArrayofVectors()[1].elementAt(0).toString();
    }
    selectedDesc= new Misc().toHTMLString(selectedDesc);
    out.println("<Input type='hidden' name="+hiddenFieldName +" value="+selectedId+">");
    out.println("<Input Type=text name="+fieldName +" VALUE="+selectedDesc+" "+otherAttributes+" >");
    out.println("<a href= 'javascript:itemPick"+fieldName+"()'> <img border=0 src="+imagePath+"itempick.gif width=22 height=22></a>");
  }
else
  { //----------------simply render as a Select List-----------
  setQueryStatment(quertStmt);
  Vector[] data=this.getResultSetAsArrayofVectors();
    //--------javaScript to fill the hidden field with the selection
if(! hiddenFieldName.equals("") && hiddenFieldName!=null)
  { //-------if developer wants the selected value will be automatically inserted into a hidden field
    out.println("<script language = javaScript>");
    out.println("function fillhiddenfield"+fieldName+"(x)");
    out.println("{document."+formName+"."+hiddenFieldName+".value=x; ");
    out.println("}");
    out.println("</script>");
    out.println("<Input type=hidden name="+hiddenFieldName +" value="+selectedId+">");
  }

  out.println("<select name="+fieldName+" "+ otherAttributes +" "+ ((! hiddenFieldName.equals("") && hiddenFieldName!=null)? "onChange='fillhiddenfield"+fieldName+"(this.value);'>": ">" ) );
  out.println("<option value = '' > إختر - Select </option>");
  //out.println("<option >------------</option>");
    int index=(userLang.equals("1"))? 2:1;
    for (int i=0; i<data[0].size(); i++)
      {
        String optionID = (data[0].elementAt(i) != null)? data[0].elementAt(i).toString() : "" ;
        String optionDesc = (data[index].elementAt(i) != null)? data[index].elementAt(i).toString() : "" ;
    	//if (! optionID.equals("0"))
          {
    		String abc = "<option value =\""+optionID+ "\"" +((optionID.equalsIgnoreCase(selectedId))? " "+"selected" : "") +">"+optionDesc+"</option>" ;  
        	out.println(abc);
          }
      }
  out.println(" </select>");

  }
//            out.println(noOfItems);
}

//-----------------------------------------------------

public void renderQueryResultAsMultiSelect(String quertStmt, String formName, String fieldName, String selectedId, String otherAttributes) throws Exception
{
    setQueryStatment(quertStmt);
    Vector[] data=this.getResultSetAsArrayofVectors();
      for (int i=0; i<data[0].size(); i++)
        {
          	String value = data[0].elementAt(i).toString() ;
          	String option = data[1].elementAt(i).toString() ;
          	boolean isChecked =  (selectedId != null && selectedId.equalsIgnoreCase(value));
            out.println( option + " <input type='checkbox' name='"+fieldName+"' value='"+value+ "'" +( (isChecked)? " checked = 'Y' " : "" ) +">" );
        }
}

public void renderQueryResultAsSelectOptions(String quertStmt, String formName, String fieldName, String selectedId, String otherAttributes) throws Exception
{
    setQueryStatment(quertStmt);
  Vector[] data=this.getResultSetAsArrayofVectors();
    //int index=(userLang.equals("1"))? 2:1;
    for (int i=0; i<data[0].size(); i++)
      {
        if (! data[0].elementAt(i).toString().equals("0"))
          {
        	String value = data[0].elementAt(i).toString() ;
        	String option = data[1].elementAt(i).toString() ;
        	boolean isChecked =  (selectedId != null && selectedId.equalsIgnoreCase(value));
            out.println( "<input type='radio' name='"+fieldName+"' value='"+value+ "'" +( (isChecked)? " checked = 'Y' " : " " ) +otherAttributes + " >" + option + "</option>" );

          //out.println("<option value="+data[0].elementAt(i).toString()+((data[0].elementAt(i).toString().toUpperCase().equals(selectedId.toUpperCase()))? " "+"selected" : "") +">"+data[1].elementAt(i).toString()+"</option>");
          }
      }

  out.println(" </select>");

}
public String[][] getRequestParametersValue(String[] parameters)
{
Vector[] outValuesAsVector= new Vector[2];
outValuesAsVector[0]=new Vector();
outValuesAsVector[1]=new Vector();
int count=0;
for (int i=0; i< parameters.length; i++)
  {
    String x=parameters[i];
    if ( request.getParameter(x) != null )
      {
        outValuesAsVector[0].addElement(parameters[i]);
        outValuesAsVector[1].addElement("'"+ request.getParameter(parameters[i].toString())+ "'" ) ;
      }
  }
//----------converting array of vectors to a 2 dimentioanl array of String------------
int actualSize=outValuesAsVector[0].size();
String[][] outValues = new String[2][actualSize];
for(int i=0 ; i<actualSize ;i++)
  {
    outValues[0][i]=outValuesAsVector[0].elementAt(i).toString(); //----parameter Names----
    outValues[1][i]=outValuesAsVector[1].elementAt(i).toString(); //----Parameters Values----
  }
return outValues;
}
//--------------------------------------------------------------------
private Vector getPrimaryKeyName() throws Exception{
String localtableName=tableName.toUpperCase();
  if (tableName.toUpperCase().startsWith("V$")){//--------if table name has been set to the corresponding view
     localtableName=tableName.toUpperCase().substring(2);
  }
Vector result=null;
String queryString= "select column_name from sys.user_cons_columns where table_name='"+localtableName+"' and "
                    + "constraint_name=(select constraint_name from sys.user_constraints where table_name='"+localtableName+"' and constraint_type='P' )";
System.out.print(queryString);
this.setQueryStatment(queryString);
Vector primaryKeys= this.getResultSetAsArrayofVectors()[0];

//if (primaryKeys.size()>1){
//throw new Exception("Multiple Primary keys Found");
//}
result=primaryKeys;//.elementAt(0).toString();

return result;
}
//------------------------------------------
private String getSeqNextValue() throws Exception
{
String localtableName=tableName;
  if (tableName.toUpperCase().startsWith("V$")){//--------if table name has been set to the corresponding view
     localtableName=tableName.toUpperCase().substring(2);
  }
String result="";
String queryString= "select "+ localtableName +"_seq.nextval from dual" ;
this.setQueryStatment(queryString);
result=this.getResultSetAsArrayofVectors()[0].elementAt(0).toString();
return result;
}

public static void main(String[] args){
//---------the purpose of this main method is to test this Class methods----
String[] data;
HTMLDbTransactionBeanWOPooling x= new HTMLDbTransactionBeanWOPooling();
//String y=x.replaceSingleQuteWithDouble("'A'BCD''");
try {
//x.initialize(application, session, request, response, out);
data = x.getSingleDBRow("select sysdate from dull");
System.out.print("hjhjg");
}
catch (Exception a)
{}
//System.out.println(y);
}
//---------------------------------------------------------
public String replaceSingleQuteWithDouble(String s)
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
public String getLookupItemDesc(String tableName, String id) throws Exception
{
String dsc="";
if (id==null || id.equals("")) return "";
userLang=this.getSessionParameterValue("userLang");
setQueryStatment( "select "+ ((userLang.equals("1"))? "A_Dsc" : "E_dsc") + " from " + tableName +" Where id = "+id);
dsc=this.getResultSetAsArrayofVectors()[0].elementAt(0).toString();
return dsc;
}

public String getLookupItemDesc(String tableName, String primryKey, String id) throws Exception
{
String dsc="";
//userLang=this.getSessionParameterValue("userLang");
setQueryStatment( "select "+ ((userLang.equals("1"))? "A_Dsc" : "E_dsc") + " from " + tableName +" Where id = "+id);
dsc=this.getResultSetAsArrayofVectors()[0].elementAt(0).toString();
return dsc;
}
//String x.compareTo()
public void setDatesAsStrings(boolean x)
{
this.getDatesAsStrings=x;
}
//------------------------------------
public void checkEmpHimSelf(String emp_no) throws Exception
{
String curentlyLoggedEmp_no= getSessionParameterValue("userId");
if ( !curentlyLoggedEmp_no.toUpperCase().equals(emp_no.toUpperCase())  )
    {
      throw new Exception("Insufficient Authorization");
    }
}
//-------------------------
public void checkAdmin() throws Exception
{

   String curentlyLoggedRoleId= getSessionParameterValue("roleId");
      if ( !curentlyLoggedRoleId.equals("1"))
      {
        throw new Exception("Insufficient Authorization");
      }


}
//--------------------------------------------------
public void checkHimSelfOrAdmin(String emp_no) throws Exception
{

try {
    checkEmpHimSelf(emp_no);
    }
catch (Exception e)
    {
      checkAdmin();
    }
}

public void checkLoggedUserIsManagerOf(String emp_no) throws Exception
{
this.setQueryStatment("select emp_mgr_code from emp_master where upper(emp_no)='"+emp_no.toUpperCase()+"'");
Vector[] x= this.getResultSetAsArrayofVectors();
String empManager= (x[0].elementAt(0)!=null)? x[0].elementAt(0).toString():"";
String LoggedEmpNo=this.getSessionParameterValue("userId");
if (!LoggedEmpNo.toUpperCase().equals(LoggedEmpNo.toUpperCase()) )
  {
    throw new Exception("Insufficient Authorization");
  }

}

public void getPopUpLimitFromSysParm(boolean x)
{popUpLimitFromSysParm=x;
}
//-----------------
public void setPopUpLimit(int x)
{
this.popupLimit=x;
}
public String[] getSingleDBRow(String selectStatment ) throws Exception
{

  Vector[] result;
  String[] abc;
  int noOfColumns= 0;
  this.setQueryStatment(selectStatment);
  result= this.getResultSetAsArrayofVectors();
  noOfColumns = result.length;
  abc= new String[noOfColumns];
  if (result[0].size()==0)
  {
     throw new Exception("No Data Found From Your SQL Statment..." + selectStatment);
  }
  if (result[0].size()>1)
  {
    throw new Exception("More Than One Record Found in Your SQL statment..." + selectStatment);
  }
  for (int i=0; i<noOfColumns ; i++)
  {
    abc[i] = (result[i].elementAt(0) != null )? result[i].elementAt(0).toString() : "";
  }
  return abc;
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


public String attchCountStr = "Select Count(*) from SUPPORT.TABLE_ATTACHEMAT t " 
  	 + "\n where t.TABLE_OWNER = ? " 
   	 + "\n	and t.TABLE_NAME = ? "
   	 //+ "\n	and t.KEY_VALE = ? " 
   	 + "\n  and t.obj_where_clause = support.misc.get_object_unique_where_clause( t.TABLE_OWNER  , t.TABLE_NAME  , ?  ) ";

private CallableStatement fetchAttatchCountcallStmt  ;   
public  int getTableAttachmentCount(String tableOwner , String tableName , String rowIdValue) throws SQLException
{
	int v_result = 0 ;
	if (fetchAttatchCountcallStmt == null)  fetchAttatchCountcallStmt = (CallableStatement) this.con.prepareCall(attchCountStr) ;
	
	fetchAttatchCountcallStmt.setString(1, tableOwner) ; 
	fetchAttatchCountcallStmt.setString(2, tableName) ;
	fetchAttatchCountcallStmt.setString(3, rowIdValue) ;
	fetchAttatchCountcallStmt.execute() ; 
	ResultSet rs = (ResultSet) fetchAttatchCountcallStmt.getResultSet();
	while (rs.next())
	{
		  v_result= rs.getInt(1) ; 
	}
	rs.close() ;
	
	return v_result ;
}

public void release()
{
	try 
	{
		if (this.stmt != null) this.stmt.close();
		if (this.fetchAttatchCountcallStmt != null ) this.fetchAttatchCountcallStmt.close() ; 
	}
	catch ( Exception e) {}
	
}
public void releaseResourses() {
	try {
		this.stmt.close();
	} catch (SQLException e) {

		e.printStackTrace();
	} 

}
}

