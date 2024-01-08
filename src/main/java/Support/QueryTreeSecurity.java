package Support;
import Support.*;
import java.sql.*;

import com.implex.database.map.SecUsrDta;

public class QueryTreeSecurity extends TreeSecurityManager
{

  /*
   * To Check if the user logged with the given connection is the owner for the given element id in the given queriestree
   *  or the given query id is under the standard query 
   * throws exception
   */
  public QueryTreeSecurity(LookupTreeV10 m_tree , Connection conn)
  {
    super(m_tree , conn);
  }
  public boolean isTreeNodeOwner(String id ) throws Exception
  {
	  String queryFullPath = this.tree.getFullPath(id);
	  int index2 = queryFullPath.indexOf("Users Queries/"+Misc.getConnectionUserName(this.loggedConn).toUpperCase());
	  boolean treeNodeOwner = !(index2 == -1) ? true : false;
	  return treeNodeOwner ; 
  }
  public void  checkAuthority(String tableName , int id , int operation) throws Exception
  {
  String queryFullPath = this.tree.getFullPath(tableName+id);
  int index1 = queryFullPath.indexOf("Users Queries/Standard/");
  boolean standardQuery = !(index1 == -1) ? true : false;
  int index2 = queryFullPath.indexOf("Users Queries/"+Misc.getConnectionUserName(this.loggedConn).toUpperCase());
  boolean queryOwner = !(index2 == -1) ? true : false;
  //-----------OS310 , EDGE , MA203685  is the  Admin User ----------
  
  SecUsrDta loggedUser = this.tree.loggedUser ; 
  boolean adminUser ;
  adminUser  = loggedUser.isSmartToolAdmin() ; 
  if (adminUser )
  {
    return;
  }
  //---------Executing--------------------------
  if ( operation == EXECUTE)
    {
    //-----Not Able to execute if he is not the owenr of the query and the 
    if(queryOwner || standardQuery )
      {}
      else
      {
       throw new Exception ("You Are not Authorized to Execute This Query");
      }
    }
    
 //------------------Updating------------------
  else if ( operation == UPDATE)
    {
    if( queryOwner )
      {}
      else
      {
       throw new Exception ("You Are not Authorized to Update  This Query");
      }
    }
 //------------------Deleting--------------------   
    else if ( operation == DELETE)
    {
    if( queryOwner )
      {}
      else
      {
       throw new Exception ("You Are not Authorized to Delete  This Query");
      }
    }
 //-----------------Reading------------------
  else if ( operation == READ)
    {
    if( queryOwner  || standardQuery  )
    {}
    else
      {
       throw new Exception ("You Are not Authorized to READ  This Query");
      }
    }
//----------------------Inserting---------------------    
  else if ( operation == INSERT)
    {
    if( queryOwner )
    {}
    else
      {
       throw new Exception ("You Are not Authorized to Insert New Item Under The Selected Item");
      }
    }    
    
//----------------------Copying---------------------    
  else if ( operation == COPY)
    {
    if( queryOwner )
    {}
    else
      {
       throw new Exception ("You Are not Authorized to Copy from  X to Y");
      }
    }    

  }
    

}
  
