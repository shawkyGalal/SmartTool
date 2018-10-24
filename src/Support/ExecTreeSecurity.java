package Support;
//import NCCI.*;
import java.sql.*;

public class ExecTreeSecurity extends TreeSecurityManager
{

  /*
   * To Check if the user logged with the given connection is the owner for the given element id in the given queriestree
   *  or the given query id is under the standard query 
   * throws exception
   */
  public ExecTreeSecurity(LookupTreeV10 m_tree , Connection conn)
  {
    super(m_tree , conn);
  }
  public void  checkAuthority(String tableName , int id , int operation) throws Exception
  {
  String queryFullPath = this.tree.getFullPath(tableName+id);
  int index1 = queryFullPath.indexOf("Users Executables/Standard");
  boolean standardExecutable = !(index1 == -1) ? true : false;
  int index2 = queryFullPath.indexOf("Users Executables/"+Misc.getConnectionUserName(this.loggedConn));
  boolean queryOwner = !(index2 == -1) ? true : false;
  //-----------OS310 , EDGE is the only Admin User ----------
  String loggedUser = Misc.getConnectionUserName(this.loggedConn);
  boolean adminUser ;
  adminUser  = (loggedUser.equals("SYSTEM") || loggedUser.equals("SYS") )? true: false;
  if (adminUser )
  {
    return;
  }
  //---------Executing--------------------------
  if ( operation == EXECUTE)
    {
    //-----Not Able to execute if he is not the owenr of the query and the 
    if(queryOwner || standardExecutable )
      {}
      else
      {
        //-------Security disabled where security should be implemented in the statement it self 
        //------ To Simplify security maintenance------------------
       // throw new Exception ("You Are not Authorized to Execute This Command");
      }
    }
    
 //------------------Updating------------------
  else if ( operation == UPDATE)
    {
    if( queryOwner || standardExecutable )
      {}
      else
      {
       throw new Exception ("You Are not Authorized to Update  This Executable");
      }
    }
 //------------------Deleting--------------------   
    else if ( operation == DELETE)
    {
    if( queryOwner || standardExecutable )
      {}
      else
      {
       throw new Exception ("You Are not Authorized to Delete  This Command");
      }
    }
 //-----------------Reading------------------
  else if ( operation == READ)
    {
    if( queryOwner  || standardExecutable  )
    {}
    else
      {
       // throw new Exception ("You Are not Authorized to READ  This Command");
      }
    }
//----------------------Inserting---------------------    
  else if ( operation == INSERT)
    {
    if( queryOwner || standardExecutable)
    {}
    else
      {
       throw new Exception ("You Are not Authorized to Insert New Item Under The Selected Item");
      }
    }    
    
//----------------------Copying---------------------    
  else if ( operation == COPY)
    {
    if( queryOwner || standardExecutable)
    {}
    else
      {
       throw new Exception ("You Are not Authorized to Copy from  X to Y");
      }
    }    

  }
@Override
public boolean isTreeNodeOwner(String id) throws Exception {
	// TODO Auto-generated method stub
	return false;
}
    

}
  
