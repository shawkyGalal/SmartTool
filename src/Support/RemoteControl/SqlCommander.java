package Support.RemoteControl;
import Support.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class SqlCommander extends Commander
{
  Connection con;
   
  public SqlCommander(String m_allCommands) throws Exception
  {
   this.AllCommands = m_allCommands;
   this.AllCommands = this.extractInfo("SQL_Command");
  }
public void executeSQL() throws Exception
{
  this.command      = extractInfo("SQL");
  String serverName = extractInfo("SERVER_NAME");
  String DBName     = extractInfo("DBNAME");  
  String userName   = extractInfo("USERNAME");  
  String password   = extractInfo("PASSWORD"); 
  String envName    = extractInfo( "ConnName"); 
  String useSmartConn = extractInfo( "useSmartConn"); 
  this.mailTo= extractInfo( "mailTo"); 
  
  ConnectionFactory cf = new ConnectionFactory();
  if (useSmartConn.equals("Y") ) 
  { Support.XMLConfigFileReader supportConfig =  Misc.getXMLConfigFileReader(false ) ;  
   this.con =  supportConfig.getConnParmsByName(envName).generateConnection(userName,password , "NORMAL");
  }
  else 
  {
  con = cf.createConnection_old(serverName, DBName,userName,password); 
  }
  boolean result = con.createStatement().execute(this.command);
  this.commandOutput = String.valueOf(result);
}


 public void execAndSendMail() throws Exception
 {
     try
        {            
           executeSQL();  // Need Implementatin    
            //commandOutput += " Command Output Not Yet Implemented" ; // Need Implementatin    
            this.sendCommandOutByEmail();
            
        } catch (Throwable t)
          {
            t.printStackTrace();
          }
   
 }
  public static void main(String[] args) throws Exception
  {
    Commander c = new SqlCommander("");
    c.execAndSendMail();

     
  }
}