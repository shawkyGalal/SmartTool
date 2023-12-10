package Support;
import java.sql.*;

public abstract class  TreeSecurityManager 
{
  public final static int READ = 0;
  public final static int UPDATE = 1;
  public final static int EXECUTE = 2;
  public final static int DELETE = 3;
  public final static int INSERT = 4;
  public final static int COPY = 5;
  public LookupTreeV10 tree;
  public Connection loggedConn;
  public TreeSecurityManager(LookupTreeV10 m_tree , Connection m_loggedConn)
  {
    this.loggedConn = m_loggedConn; 
    this.tree = m_tree;
    this.tree.setSecurityManager(this);
  }
  public abstract void checkAuthority( String tableName,  int id , int operation) throws Exception;
  public abstract boolean isTreeNodeOwner(String id ) throws Exception ; 
  
}