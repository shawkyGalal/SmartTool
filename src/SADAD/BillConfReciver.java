package SADAD;
import Support.*;
import Support.Logger;
import SADAD.DATASTRCTURE.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.*;
/*
 * This Class recives  Bill Confirmation Rq from SADAD Server
 */
public class BillConfReciver extends HttpServlet 
{
  private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
  Connection con;

  public void init(ServletConfig config) throws ServletException
  {
    super.init(config);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    Logger auditLogger;
    Logger errorLogger;
    try{
    errorLogger =new Logger(new SADADSettings().getTodayLogFilePath(SADADSettings.ERROR_LOG),SADADSettings.ERROR_LOG);
    auditLogger =new Logger(new SADADSettings().getTodayLogFilePath(SADADSettings.AUDIT_LOG),SADADSettings.AUDIT_LOG);
    }
    catch (Exception e )
    {errorLogger =new Logger("c:\\temp\\sd_error_Log.log");
     auditLogger =new Logger("c:\\temp\\sd_audit_Log.log");
    }
    response.setContentType(CONTENT_TYPE);
    java.util.Date startDate= new java.util.Date();
    InputStream is = request.getInputStream();
    String requestStr = "";
    int c = 0;
    while( (c= is.read()) != -1)
    {
    requestStr += (char)c;
    }
    
    SADADService serviceRq = null;
    String ShortDesc = "Success";
    String StatusCode = "0";
    String Severity = "Info";
    //---------------Creating a connection to DB---
    try{
    this.con = ConnectionFactory.createConnection("127.0.0.1","orcl" , "biller","biller","1521");
    }
    catch (Exception e) 
    {
    ShortDesc = e.getMessage();
    StatusCode = "200";
    Severity = "Error";
    errorLogger.logMessage(e.getMessage());
    }

    //--------Parsing the request Message-----------
    try{
    serviceRq = new SADADService(requestStr ,true , this.con );  
    }
    catch (Exception e) 
    {
    ShortDesc = e.getMessage();
    StatusCode = "100";
    Severity = "Error";
    errorLogger.logMessage(e.getMessage());
    }
    //---Store to File system 
    try{
    serviceRq.saveInSFS("D:\\MyWork\\SADAD\\public_html");
    }
    catch (Exception e)
    {
     errorLogger.logMessage(e.getMessage());
    }
    //-------Storing the request in DB---
    try{
    serviceRq.saveInDb();
    this.con.commit();
    }
    catch (Exception e)
    {
     try{con.rollback();}catch (Exception ex){}
     ShortDesc = e.getMessage();
     StatusCode = "300";
     Severity = "Error";
     errorLogger.logMessage(e.getMessage());
    }
    //--------Construct the Response from the request----------
    BillConfirmationRq bcrq = (BillConfirmationRq)serviceRq.presSvcRq.messageRequests.elementAt(0);
    SimpleDateFormat df  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    String serviceRsStr = "";
    serviceRsStr = "<?xml version=\"1.0\"?>";
    serviceRsStr +=  "<SADAD>";
    serviceRsStr +=  "<SignonRs>";
    serviceRsStr += "   <ClientDt>"+serviceRq.signonRq.getClientDt()+"</ClientDt>";
    serviceRsStr += "   <ServerDt>"+df.format(new java.util.Date()).replace(' ','T')+"</ServerDt>";
    serviceRsStr +="    <LanguagePref>en-gb</LanguagePref>";
    serviceRsStr +="    <SignonProfile>";
    serviceRsStr +="      <Sender>007</Sender>";
    serviceRsStr +="      <Receiver>SADAD-001</Receiver>";
    serviceRsStr +="      <MsgCode>BCONRS</MsgCode>";
    serviceRsStr +="    </SignonProfile>";
    serviceRsStr +="  </SignonRs>";
    serviceRsStr +="  <PresSvcRs>";
    serviceRsStr +="    <Status>";
    serviceRsStr +="      <StatusCode>"+StatusCode+"</StatusCode>";
    serviceRsStr +="      <ShortDesc>"+ShortDesc+"</ShortDesc>";
    serviceRsStr +="      <Severity>"+Severity+"</Severity>";
    serviceRsStr +="    </Status>";
    serviceRsStr +="    <RqUID>"+SADAD.DATASTRCTURE.SADADMessage.getUUID()+"</RqUID>";
    serviceRsStr +="    <BillConfirmationRs>";
    serviceRsStr +="         <AsyncRqUID>"+bcrq.asyncRqUID+"</AsyncRqUID>";
    serviceRsStr +="         <UploadDt>"+serviceRq.signonRq.getClientDt()+"</UploadDt>";
    serviceRsStr +="         <PrcDt>"+df.format(new java.util.Date()).replace(' ','T')+"</PrcDt>";
    serviceRsStr +="         <BillCategory>YYYY</BillCategory>";
    serviceRsStr +="         <ServiceType>xxxx</ServiceType>";
    serviceRsStr +="         <SuccessRecordCount>"+bcrq.sucessRecordCount+"</SuccessRecordCount>";
    serviceRsStr +="         <ErrorRecordCount>"+bcrq.errorRecordCount+"</ErrorRecordCount>";
    serviceRsStr +="    </BillConfirmationRs>";
    serviceRsStr +="  </PresSvcRs>";
    serviceRsStr +="</SADAD>";

    //---------reply to the request -----------
    PrintWriter out = response.getWriter();
    out.println(serviceRsStr);
    out.close();

    //--------Storing both request and response in DB
    java.util.Date endDate= new java.util.Date();
    long resTime = endDate.getTime() -  startDate.getTime();
    SADADService serviceRs = null;
    try{
    serviceRs = new SADADService(serviceRsStr,true, con);
    SendReciveWriter srr = new SendReciveWriter(con);
    srr.storeRequestResponse(serviceRq , serviceRs , resTime , startDate);
    }
    catch (Exception e ) 
    {
    errorLogger.logMessage(e.getMessage());
    }
    
    
  }
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<head><title>ReciveBills</title></head>");
    out.println("<body>");
    out.println("<p>The servlet has received a Get. This is the reply. xxxx </p>");
    out.println("</body></html>");
    out.close();
    
  }
  
}