package SADAD;
import java.io.*;
import java.util.*;
import java.text.*;
import java.sql.*;

import Support.Misc;
public class Logger 
{
  RandomAccessFile raf ;
  Connection con;
  String userName = "Not a DB Logged User";
  
  public  Logger(String logFilePath, Connection con  ) throws FileNotFoundException
  {
    try{
    if (con != null && !con.isClosed())
    {
    userName = Misc.getConnectionUserName(con);
    }
    }
    catch (Exception e){}
    init(logFilePath);
  }
  public  Logger(String logFilePath ) throws FileNotFoundException
  {
    init(logFilePath);
  }
  private synchronized  void init(String logFilePath) throws FileNotFoundException
  {
    raf = new RandomAccessFile(logFilePath, "rw");
  }
  public synchronized void  logMessage(String msg) throws IOException
  {
    //RandomAccessFile raf = new RandomAccessFile(logFilePath, "rw");
    long length = raf.length();
    raf.seek(length);
    if (length==0)
    {raf.writeBytes("*****************SADAD SYSTEM LOG File Created at  " + new java.util.Date()+"**********");}
    raf.writeByte(13); raf.writeByte(10); //---new Line
    raf.writeByte(13); raf.writeByte(10); //---new Line
    raf.writeBytes("------------------------------------------------------------------------------------------");
    raf.writeByte(13); raf.writeByte(10); //---new Line
    raf.writeBytes(("SADAD log at " + new java.util.Date() + "For User : " + this.userName));
    raf.writeByte(13); raf.writeByte(10);
    raf.writeBytes(msg);
    //raf.close();
  }
  public synchronized void logString(String str) throws IOException
  {
    long length = raf.length();
    raf.seek(length);
    raf.writeBytes(str);
  }
  public void close() throws IOException
  {
    raf.close();
  }
  public static void main(String[] args)
  {
    try{ 
    Logger log = new Logger (new SADADSettings().getTodayLogFilePath(SADADSettings.ERROR_LOG));
    log.logString("jhghjgh");
    log.logString("jkghjkhjkhjkh");
    
    }
    catch (Exception e){ 
    System.out.print(e.getMessage());
    System.out.print(e.getMessage());
               }
  }
}