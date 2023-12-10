package SADAD;
import java.util.*;
import java.io.*;

public class SADADException extends Exception  
{
    static final int  NO_LOGGING = 0; 
    static final int  LOG_EXCEPTION_MESSAGE = 1;
    static final int  LOG_EXCEPTION_STACK_TRACE = 2;
    /**
     * @parm Message message of this SADAD Exception 
     * @PARM causedByException  : The Orginal Exception (if Exist) Caused SADAD System To throw this SADADException
     *                            This will be effective only if the running java is J2SE 1.4  or more
     * @parm  logLevel = 0       No Logging for the Error
     *        logLevel = 1       Log Exception Error Message Only
     *        logLevel = 2       Log Exception Stack Trace
     */
  public SADADException(String message, Exception causedByException, int logLevel) //throws Exception
  { 
    
    super(message); // Should be upgraded to super(message,causedByException ); to enable Exception Chaining in J2SE 1.4 
    
      try
      {
        if (logLevel == LOG_EXCEPTION_MESSAGE)
        {
          this.logMessage(message);
        }
        if (logLevel == LOG_EXCEPTION_STACK_TRACE)
        {
          logMyStackTrace();
        }
      }
      catch (Exception e){e.printStackTrace();};
    
 }
  private void logMessage( String message) throws Exception
  {
    Logger logger = new Logger(new SADADSettings().getTodayLogFilePath(SADADSettings.ERROR_LOG));
    logger.logMessage(message);
    logger.close();
  }
  
  public void logMyStackTrace() throws Exception
  {
    Logger logger = new Logger(new SADADSettings().getTodayLogFilePath(SADADSettings.ERROR_LOG));
       
    logger.raf.writeByte(13);logger.raf.writeByte(10); //--new Line
    logger.logString("------------------------------------------------------------------------------------------");
    logger.raf.writeByte(13);logger.raf.writeByte(10); //--new Line
    logger.logString("SADAD Error at " + new Date());
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
}