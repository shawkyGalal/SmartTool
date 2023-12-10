package Support;


import java.net.*;
import java.util.*;
import java.text.*;
import java.sql.*;


public class MySettings 
{
  public static final int ERROR_LOG = 0;
  public static final int AUDIT_LOG = 1;
  static String errorlogFilePath_local="\\logs\\errorsLog\\errorsLog";
//  static String errorlogFilePath_Prod_server = "c:\\ora9iasInfra\\j2ee\\SADAD_OC4J\\applications\\SADAD\\SADAD\\logs\\errorsLog\\errorsLog";//
//  static String errorlogFilePath_Dev_server = "E:\\ora9ias2\\j2ee\\OC4J_SADAD\\applications\\SADAD\\SADAD\\logs\\errorsLog\\errorsLog";//

  static String aduitLogFilePath_local="\\logs\\actionsLog\\actionsLog"; 
//  static String aduitLogFilePath_Prod_server ="c:\\ora9iasInfra\\j2ee\\SADAD_OC4J\\applications\\SADAD\\SADAD\\logs\\actionsLog\\actionsLog" ; 
//  static String aduitLogFilePath_Dev_server ="E:\\ora9ias2\\j2ee\\OC4J_SADAD\\applications\\SADAD\\SADAD\\logs\\actionsLog\\actionsLog" ;   
  String relativeFilePath ="";
  boolean logException = true;
  public MySettings(String m_relativeFilePath)
  {
   relativeFilePath = m_relativeFilePath;
  }
  public String getTodayLogFilePath(int logType) //throws Exception 
  {
    String result = "";
    //java.net.InetAddress localHost =  InetAddress.getLocalHost();
    //String hostIP = localHost.getHostAddress();

    SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy");
    String dateString = sdf.format(new java.util.Date());

//    if (hostIP.equals("130.1.3.200"))    //----if the system in running on the development (jdeveloper )
    {
      if (logType == MySettings.ERROR_LOG )
        {
        result = relativeFilePath + errorlogFilePath_local+dateString+".txt";
        }
      if (logType == MySettings.AUDIT_LOG)
        {
        result = relativeFilePath + aduitLogFilePath_local+dateString+".txt";
        }
    }
     return result;
  }
  public  void setSystemParmValue(String parmName , String parmVale, Connection con) throws Exception
  {
    java.net.InetAddress localHost =  InetAddress.getLocalHost();
    String hostIP = localHost.getHostAddress();
    
    String updateStatmnet ="update system_parms set parm_value = '"+parmVale+ "' where server = '"+hostIP+"' and parm_name = '" + parmName+"'";
    try{
    Statement stmt= con.createStatement();
    stmt.execute(updateStatmnet);
    }
    catch (Exception e){ throw new MyException("Unable To execute the following Statment  " + updateStatmnet +" Due to: "+ e.getMessage(),e,0 , relativeFilePath);}
    
  }
  public String getSystemParmValue(String parmName,  Connection con) throws Exception
  {
    String parmValue = "";
    
    java.net.InetAddress localHost =  InetAddress.getLocalHost();
    String hostIP = localHost.getHostAddress();
    
    String qStatmnet ="select parm_value from system_parms  where server = '"+hostIP+"' and upper(parm_name)  = '"+parmName.toUpperCase()+"'";
    try{
    Statement stmt= con.createStatement();
    
    ResultSet resSet=  stmt.executeQuery(qStatmnet);
    while ( resSet.next())
    {
      parmValue = resSet.getString(1);
    }
    resSet.close();
    stmt.close();
    }
    catch (Exception e ) {throw new MyException("Unable to Get the " + parmName + " For the SADAD System Configurations Due to : "+e.getMessage(),e,0, relativeFilePath);}
    return parmValue;
  }
 public  static void main(String[] abc)
 {
  MySettings x=  new MySettings("C:\\Edge_Work\\SADAD\\");
  x.getTodayLogFilePath(0);
 }
 
}