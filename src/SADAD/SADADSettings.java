package SADAD;
import java.net.*;
import java.util.*;
import java.text.*;
import java.sql.*;


public class SADADSettings 
{
  public static final int ERROR_LOG = 0;
  public static final int AUDIT_LOG = 1;
  static String errorlogFilePath_local="D:\\MyWork\\SADAD\\logs\\errorsLog\\errorsLog";
  static String errorlogFilePath_Prod_server = "c:\\ora9iasInfra\\j2ee\\SADAD_OC4J\\applications\\SADAD\\SADAD\\logs\\errorsLog\\errorsLog";//
  static String errorlogFilePath_Dev_server = "E:\\ora9ias2\\j2ee\\OC4J_SADAD\\applications\\SADAD\\SADAD\\logs\\errorsLog\\errorsLog";//

  static String aduitLogFilePath_local="D:\\MyWork\\SADAD\\logs\\actionsLog\\actionsLog"; 
  static String aduitLogFilePath_Prod_server ="c:\\ora9iasInfra\\j2ee\\SADAD_OC4J\\applications\\SADAD\\SADAD\\logs\\actionsLog\\actionsLog" ; 
  static String aduitLogFilePath_Dev_server ="E:\\ora9ias2\\j2ee\\OC4J_SADAD\\applications\\SADAD\\SADAD\\logs\\actionsLog\\actionsLog" ;   
  boolean logException = true;
  public SADADSettings()
  {
  }
  public String getTodayLogFilePath(int logType) throws Exception 
  {
    String result = "";
    java.net.InetAddress localHost =  InetAddress.getLocalHost();
    String hostIP = localHost.getHostAddress();

    SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy");
    String dateString = sdf.format(new java.util.Date());
    //----if code is running on my machine 
    if ( hostIP.equals("10.16.18.130") || hostIP.equals("10.16.18.129")|| hostIP.equals("172.16.20.16") || hostIP.equals("132.35.7.17") 
     || hostIP.equals("127.0.0.1") || hostIP.equals("10.16.18.181") 
     || hostIP.equals("10.16.18.84") || hostIP.equals("10.16.26.181") || hostIP.equals("10.16.17.100") )    //----if the system in running on the development (jdeveloper )
    {
      if (logType == SADADSettings.ERROR_LOG )
        {
        result = errorlogFilePath_local+dateString+".txt";
        }
      if (logType == SADADSettings.AUDIT_LOG)
        {
        result = aduitLogFilePath_local+dateString+".txt";
        }
    }
    
    else if (hostIP.equals("130.1.2.30"))   //-----if the system is running on the Application Server--
    {
      if (logType == SADADSettings.ERROR_LOG )
      {
         result = errorlogFilePath_Prod_server+dateString+".txt";  
      }
      if (logType == SADADSettings.AUDIT_LOG )
      {
        result = aduitLogFilePath_Prod_server+dateString+".txt";  
      }
    }
    else if (hostIP.equals("130.1.2.177"))   //-----if the system is running on the Application Server--
    {
      if (logType == SADADSettings.ERROR_LOG )
      {
         result = errorlogFilePath_Dev_server+dateString+".txt";  
      }
      if (logType == SADADSettings.AUDIT_LOG )
      {
        result = aduitLogFilePath_Dev_server+dateString+".txt";  
      }
    }

    
    else {throw new SADADException("Shawky : System is not defined to run on this Machine  " +hostIP ,null,(byte)0);}
    return result;
  }
  public static void setSystemParmValue(String parmName , String parmVale, Connection con) throws Exception
  {
    java.net.InetAddress localHost =  InetAddress.getLocalHost();
    String hostIP = localHost.getHostAddress();
    
    String updateStatmnet ="update system_parms set parm_value = '"+parmVale+ "' where server = '"+hostIP+"' and parm_name = '" + parmName+"'";
    try{
    Statement stmt= con.createStatement();
    stmt.execute(updateStatmnet);
    }
    catch (Exception e){ throw new SADADException("Unable To execute the following Statment  " + updateStatmnet +" Due to: "+ e.getMessage(),e,0);}
    
  }
  public static String getSystemParmValue(String parmName,  Connection con) throws Exception
  {
    String parmValue = "";
    boolean parm_found= false;
    
    java.net.InetAddress localHost =  InetAddress.getLocalHost();
    String hostIP = localHost.getHostAddress();
    
    String qStatmnet ="select parm_value from system_parms  where server = '"+hostIP+"' and upper(parm_name)  = '"+parmName.toUpperCase()+"'";
    try{
    Statement stmt= con.createStatement();
    
    ResultSet resSet=  stmt.executeQuery(qStatmnet);
    while ( resSet.next())
    {
      parmValue = resSet.getString(1);
      parm_found = true;
    }
    resSet.close();
    stmt.close();
    
    }
    catch (Exception e ) {throw new SADADException("Unable to Get the " + parmName + " For the SADAD System Configurations Due to : "+e.getMessage(),e,0);}
    if (! parm_found){throw new Exception("Value of " + parmName + "not Found for server  " + hostIP);}
    return parmValue;
  }
 
 
}