package Support;
import java.sql.*;
import java.util.*;
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
  public static Connection createOciConnection(String tnsName, String userName , String password) throws SQLException
  { //-----------
    Connection abc= null; 
      String ConnectionString="jdbc:oracle:oci8:@"+tnsName;
    
   try {
              Class.forName ("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
              e.printStackTrace();
        }


    //DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
    abc= DriverManager.getConnection(ConnectionString,userName,password);
    return abc;
  }
  
  public static Connection  createConnectionByUrl(String url , String driver, String userName , String password) throws Exception
  {
	  	Properties props = new Properties();
	  	props.put("user", userName);
	  	props.put("password", password);
	  	return createConnectionUsingProps(url , driver , props );
	  
  }
  /*
   * @@deprecated 
   */
  public static Connection  createConnection_old(String hostName , String oracleSID, String userName , String password) throws SQLException
  { //-----------
    Connection abc= null; 
    String ConnectionString="jdbc:oracle:thin:@"+hostName+":1521:"+oracleSID;
    DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
    abc= DriverManager.getConnection(ConnectionString,userName,password);
    return abc;
  }
  public static Connection createConnectionAsSysDBA(String url , String driver, String userName , String password) throws Exception
  {
	  	Properties props = new Properties();
	  	props.put("user", userName);
	  	props.put("password", password);
	  	props.put("internal_logon", "sysdba");

    return createConnectionUsingProps(url , driver , props);

  }

  private static Connection createConnectionUsingProps(String url , String driver, Properties props) throws Exception
  
  {
	    Class.forName(driver);
	    System.out.println("Generating a DB Connection Using " + url + "\n" + props) ; 
	    Connection con= DriverManager.getConnection(url,props);
	    return con;
	  
  }
  public static Connection createConnection(ConnParms connParms) throws Exception
  {
    return connParms.generateConnection();
  }
  public static Connection  createMsSqlConnection(String hostName , String instanceName, String userName , String password) throws Exception  
  {
  //Temp commented
   DriverManager.registerDriver (new net.sourceforge.jtds.jdbc.Driver()); //(new com.microsoft.jdbc.sqlserver.SQLServerDriver());
   //Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver"); 
   // jdbc:jtds:sqlserver://localhost:1433;databasename=scdb
   String ConnectionString = "jdbc:jtds:sqlserver://"+hostName+":1433;databasename="+ instanceName + " ;namedPipe=true" ; //  namedPipe=true as per Mahmod eastnets 
   Connection conn = DriverManager.getConnection(ConnectionString,userName ,password); 
   return conn;
  }
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
    	Class.forName("oracle.jdbc.driver.OracleDriver");
 	
        Connection conx = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/SVDB", "icdb", "123");
        displayConnDetails(conx);
    
    //Connection conn =  ConnectionFactory.createOciConnection("ORCL.SADAD.COM" , "biller" ,"biller" );
    //Connection conn =  ConnectionFactory.createConnection("localhost","orcl", "biller" , "biller");//.createOciConnection("ORCL.SADAD.COM" , "biller" ,"biller" );
    //Connection con1 =  ConnectionFactory.createMsSqlConnection("127.0.0.1","timeAtt", "sa" , "redsea11");
    //Connection con2 =  ConnectionFactory.createMsSqlConnection("10.10.0.196","timeAtt", "attread" , "123");
    String url = "jdbc:oracle:thin:@localhost:1521/SVDB";//"jdbc:jtds:sqlserver://localhost:1433;databasename=scdb ";
    String driver = "oracle.jdbc.driver.OracleDriver";//"net.sourceforge.jtds.jdbc.Driver";
    Connection con =  ConnectionFactory.createConnectionByUrl(url,driver,"icdb","123");
    //Connection connection =  ConnectionFactory.createODBCConnection("carrent","sa" , "");
    //Connection connection =  ConnectionFactory.createConnectionAsSysDBA("localhost","orcl","sys" , "redsea11");
    //displayConnDetails(con1);
    //displayConnDetails(con2);
    displayConnDetails(con);

    //con1.close();
    //con2.close();
    con.close();
  /*  Statement st = conn.createStatement() ;
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
*/  
}
    
    catch (Exception  e){e.printStackTrace();}
  }
  public static void displayConnDetails(Connection con) throws Exception
  {
	  if (con != null) {
		     System.out.println();
		      System.out.println("Successfully connected" );
		      System.out.println("User name : " + Misc.getConnectionUserName(con));
		      // Meta data
		      DatabaseMetaData meta = con.getMetaData();
		      System.out.println("\nDriver Information");
		      System.out.println("Driver Name: "
		       + meta.getDriverName());
		      System.out.println("Driver Version: "
		       + meta.getDriverVersion());
		      System.out.println("\nDatabase Information ");
		      System.out.println("Database Name: "
		       + meta.getDatabaseProductName());
		      System.out.println("Database Version: "+
		      meta.getDatabaseProductVersion());
		    }
  }
}