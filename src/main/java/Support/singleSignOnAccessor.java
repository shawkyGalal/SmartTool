package Support;
import java.sql.*;


public class singleSignOnAccessor 
{
  Connection supConn;
  
  public singleSignOnAccessor(Connection m_supConn)
  {
    this.supConn = m_supConn;
    
  }

  public String getPassword(String server , String userName) throws Exception
  {
    String password = null;
    String querString ="select password from support.single_sign_in where  ( server= '" + server + "' or server_alt_ip = '"+server+"' )  and userName = '" + userName +"'";
    Statement stmt = this.supConn.createStatement();
    ResultSet rs;
    try{ rs =stmt.executeQuery(querString);}
    catch (Exception e) {throw new Exception ("Unable to Eecute " + querString + " Due to " + e.getMessage());}
    while (rs.next())
    {
      password = rs.getString("password");
    }
    rs.close();
    stmt.close();
    
    if (password == null){throw new Exception ("No Record returned from the following Query: "+ querString);}    
    
    return password;
  }
  public static void main(String[] args) throws Exception
  {
    ConnectionFactory cf = new ConnectionFactory();
    Connection con =cf.createConnection_old("10.16.17.52","sadad" , "support", "support");
    singleSignOnAccessor singleSignOnAccessor = new singleSignOnAccessor(con);
    singleSignOnAccessor.getPassword("10.16.16.17","wmin0101");
  }
}