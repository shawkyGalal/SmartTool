package SADAD;
import java.sql.*;
public class ConnectionFactory 
{
  public ConnectionFactory()
  {
  }

  public static Connection  createConnection(String dbURL , String userName , String password) throws SQLException
  {
    Connection abc= null;
    DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
    abc= DriverManager.getConnection(dbURL,userName,password);
    return abc;
    
  }
  public static Connection  createConnection(String hostName , String oracleSID, String userName , String password) throws SQLException
  { //-----------
    Connection abc= null; 
    String ConnectionString="jdbc:oracle:thin:@"+hostName+":1521:"+oracleSID;
    DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
    abc= DriverManager.getConnection(ConnectionString,userName,password);
    return abc;
  }
  public static Connection  createConnection(String hostName , String oracleSID, String userName , String password , String port) throws SQLException
  { //-----------
    Connection abc= null; 
    String ConnectionString="jdbc:oracle:thin:@"+hostName+":"+port+":"+oracleSID;
    DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
    abc= DriverManager.getConnection(ConnectionString,userName,password);
    return abc;
  }
  //public static Connection createConnection(ConnParms connParms) throws Exception
  //{
  //  return connParms.generateConnection();
  //}
 /* public static Connection  createSqlConnection(String hostName , String instanceName, String userName , String password) throws Exception  
  {
   DriverManager.registerDriver (new com.microsoft.jdbc.sqlserver.SQLServerDriver());
   Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");  
   String ConnectionString = "jdbc:microsoft:sqlserver://RWERWE\\carrent;User=test;Password=secret "; //"jdbc:microsoft:sqlserver://"+hostName+"\\"+instanceName;
   Connection conn = DriverManager.getConnection(ConnectionString); 
   return conn;
  }*/
  public static Connection  createODBCConnection(String sourceName , String userName , String password) throws Exception  
  {
   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
   DriverManager.registerDriver (new net.sourceforge.jtds.jdbc.Driver());//(new sun.jdbc.odbc.JdbcOdbcDriver());
   String url = "jdbc:odbc:"+sourceName;
   Connection conn = DriverManager.getConnection(url, userName , password);
  
   return conn;
  }
 
  
  public static void main(String[] args)
  {
    try{
    Connection conn =  ConnectionFactory.createConnection("10.16.24.9", "SPDB" , "bgadmin" ,"changeme" );
    Statement st = conn.createStatement() ;
    ResultSet rs = st.executeQuery("select sysdate from dual ");
    String cat = conn.getMetaData().getDatabaseProductName();
    String shold_name = "";
    while(rs.next())
     {
      shold_name = rs.getString(1);
      shold_name = rs.getString(2);
      shold_name = rs.getString(3);
     }
    rs.close();
    }
    
    catch (Exception  e){e.printStackTrace();}
  }
}