package Support;

import java.io.*;
import java.util.*;
import java.text.*;
public class Logger 
{
  RandomAccessFile raf ;
  
  public Logger(String logFilePath ) throws  IOException
  {
    try{
	  raf = new RandomAccessFile(logFilePath, "rw");
    }
	 catch ( FileNotFoundException fnfe ) {
		 File folder = new File(logFilePath);
		 folder.createNewFile();
		 raf = new RandomAccessFile(logFilePath, "rw");
	 }
	 
  }
  public  Logger(String logFilePath ,   int logType) throws FileNotFoundException , IOException
  {
    init(logFilePath ,  logType);
  }
  private synchronized  void init(String logFilePath , int logType) throws FileNotFoundException , IOException
  {		 String x = new MySettings(logFilePath).getTodayLogFilePath(logType);
		 //File folder = new File(x);
		 //folder.createNewFile();
	  raf = new RandomAccessFile(x, "rw");
  }
  public synchronized void  logMessage(String msg) throws IOException
  {
    //RandomAccessFile raf = new RandomAccessFile(logFilePath, "rw");
    long length = raf.length();
    raf.seek(length);
    if (length==0)
    {raf.writeBytes("***************** SYSTEM LOG File Created at  " + new Date()+"**********");}
    raf.writeByte(13); raf.writeByte(10); //---new Line
    raf.writeByte(13); raf.writeByte(10); //---new Line
    raf.writeBytes("------------------------------------------------------------------------------------------");
    raf.writeByte(13); raf.writeByte(10); //---new Line
    raf.writeBytes((" log created at " + new Date()));
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
    if (raf!= null)
    { raf.close(); }
  }
  public static void main(String[] args)
  {
    try{ 
    //Logger log = new Logger (new SADADSettings().getTodayLogFilePath(SADADSettings.ERROR_LOG));
    //log.logString("jhghjgh");
    //log.logString("jkghjkhjkhjkh");
    
    }
    catch (Exception e){ 
    System.out.print(e.getMessage());
    System.out.print(e.getMessage());
               }
  }
}
