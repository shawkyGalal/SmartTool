package Support;

import java.util.*;
import java.io.*;

public class MyException extends Exception  
{
    public static final int  NO_LOGGING = 0; 
    public static final int  LOG_EXCEPTION_MESSAGE = 1;
    public static final int  LOG_EXCEPTION_STACK_TRACE = 2;
    /**
     * @parm Message message of this SADAD Exception 
     * @PARM causedByException  : The Orginal Exception (if Exist) Caused SADAD System To throw this SADADException
     *                            This will be effective only if the running java is J2SE 1.4  or more
     * @parm  logLevel = 0       No Logging for the Error
     *        logLevel = 1       Log Exception Error Message Only
     *        logLevel = 2       Log Exception Stack Trace
     */
  public MyException(String message, Exception causedByException, int logLevel, String logPath) //throws Exception
  { 
    
    super(message); // Should be upgraded to super(message,causedByException ); to enable Exception Chaining in J2SE 1.4 
    
      try
      {
        if (logLevel == LOG_EXCEPTION_MESSAGE)
        {
          this.logMessage(logPath, message);
        }
        if (logLevel == LOG_EXCEPTION_STACK_TRACE)
        {
          logMyStackTrace(logPath);
        }
      }
      catch (Exception e){e.printStackTrace();};
    
 }
  private void logMessage(String logPath ,  String message) throws Exception
  {
    Logger logger = new Logger(logPath, 0);
    logger.logMessage(message);
    logger.close();
  }
  
  public void logMyStackTrace(String logPath) throws Exception
  {
    Logger logger = new Logger(logPath, 0);
       
    logger.raf.writeByte(13);logger.raf.writeByte(10); //--new Line
    logger.logString("------------------------------------------------------------------------------------------");
    logger.raf.writeByte(13);logger.raf.writeByte(10); //--new Line
    logger.logString("Smart Tool Error at " + new Date());
    logger.raf.writeByte(13);logger.raf.writeByte(10); //--new Line
    logger.logString("------------------------------------------------------------");
    logger.raf.writeByte(13);logger.raf.writeByte(10); //--new Line
    logger.logString("Class\t\tFile\t\t\tLine\tMethod");
    logger.raf.writeByte(13);logger.raf.writeByte(10); //--new Line
    
    StackTraceElement[] stes = this.getStackTrace();
    for (int i= 0; i<stes.length ; i++)
    {
      StackTraceElement currentElement = stes[i];
      logger.logString(currentElement.getClassName()+"\t");      
      logger.logString(currentElement.getFileName()+"\t\t");      
      logger.logString(currentElement.getLineNumber()+"\t");      
      logger.logString(currentElement.getMethodName()+"\t"); 
      logger.raf.writeByte(13);logger.raf.writeByte(10); //--new Line
    }
    //logger.log(this.fillInStackTrace().getMessage());
    logger.close();
  }
   public  static void main(String[] abc)
 {
  MyException x=  new MyException("Test Message ", null, 1, "C:\\Edge_Work\\SADAD\\");
 }
}