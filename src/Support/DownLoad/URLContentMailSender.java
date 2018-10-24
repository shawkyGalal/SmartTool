package Support.DownLoad;

import Support.mail.*;
import Support.*;
import java.net.*;
import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.*;


public class URLContentMailSender 
{
static String logFilePath="D:\\MyWork\\";

  public URLContentMailSender() throws Exception
  {
    
    Validator1.checkExpiry();
  }
  public static void  main(String[] arg) throws Exception
  {
    
    URLContentMailSender abc = new URLContentMailSender();
    
    if ( arg.length < 4)
    {
      System.out.println("Usage");
      System.out.println("URLDownLoader toMail ccMail subject fromUrlPath  parm2=Parm2Value parm1=Parm3Value parm1=Parm3Value ..");
      System.exit(0);
    }

    try 
    {
     String to= arg[0];//"shawky.foda@sadad.com";
     String cc= arg[1];//"shawky.foda@sadad.com";
     String subject = arg[2];
     String url = arg[3];//"http://10.16.18.130:8988/Support/executeQuery.jsp?id=3179&sptn=74204&AutoLog=Y"; //arg[0]
 
     
     String parms = "";
     for (int i = 4 ; i< arg.length ; i++)
     { parms += ( (i==4)? "": "&" ) + arg[i]   ;}
     //---------Logging the Action -----------    
     if (URLContentMailSender.logFilePath != null )
     {
      try{
      Logger logger = new Logger(URLContentMailSender.logFilePath,1);
      logger.logMessage("\n"+ " To : " + to);
      logger.logString("\n "+ " Cc : " + cc);
      logger.logString("\n "+ " Subject : " + subject );     
      logger.logString("\n "+ " URL : " + url );     
      logger.logString("\n "+ "Parms : " + parms );     
      logger.close();
      }
      catch(Exception e){}
     }
     System.out.println("To : " + to ); 
     System.out.println("Cc : " + cc ); 
     System.out.println("Subject : " + subject );     
     System.out.println("URL : " + url );     
     System.out.println("Parms : " + parms );     

     //-----------Generate toRec array-----------
     StringTokenizer st= new StringTokenizer(to, ",");
     int recepiants_size = st.countTokens();
     String[] toRec = new String[recepiants_size]; //arg[1]
     for (int i = 0; i<recepiants_size ; i++)
     {
      toRec[i] = st.nextElement().toString();
     }
     //-----------Generate ccRec array-----------
     st= new StringTokenizer(cc, ",");
     recepiants_size = st.countTokens();
     String[] ccRec = new String[recepiants_size]; //arg[1]
     for (int i = 0; i<recepiants_size ; i++)
     {
      ccRec[i] = st.nextElement().toString();
     }
     
     abc.sendContents(url+"?"+parms ,toRec,ccRec, subject);
     //---------Logging the Action -----------    
     if (URLContentMailSender.logFilePath != null )
     {
      try{
      Logger logger = new Logger(URLContentMailSender.logFilePath,1);
      logger.logString("\n"+ " URL Content Successuly Sent to: " + to + " And CC to: " + cc );
      logger.close();
      }
      catch(Exception e) {}
     }
     System.out.println("URL Content Successuly Sent to: " + to + " And CC to: " + cc );                     
    }
    catch (Exception e) {   System.out.print(e.getMessage());}
  }
  //------------------------------------------------------------------
 
  
  public void sendContents( String fromUrl // The URL which needed to be Sent 
                              , String[] mailRecipients  // Mail Reciepients 
                              , String[] ccRecipients
                              , String subject
                          ) throws Exception
  {
  //------By Shawky Foda----
  //------Date 10-1-2004----------
  
  //--Check if Secure HTTPS Or Not
   BufferedReader br = null;
   InputStream is = null;
   
   	Support.XMLConfigFileReader supportConfig =  Misc.getXMLConfigFileReader(false ) ; 
   	
   	Connection repCon = supportConfig.reposatoryConn.generateConnection();
    String smtpServerIP = SysConfigParams.getSMTP_SERVER();// msc.getSystemParameterValue(2);
    String supportAdminMail =SysConfigParams.getSupport_User_Email();// msc.getSystemParameterValue(3); 
    String useGmailAccount = SysConfigParams.getUseGmailAccount();//msc.getSystemParameterValue(12);
try{
   
   URL url = new URL(fromUrl);
   URLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
   
   is =    httpUrlConn.getInputStream();
	
   InputStreamReader isr = new InputStreamReader(is);
   br = new BufferedReader(isr);
   String lineData= ""; 
   StringBuffer allData = new StringBuffer("");
   while( ( lineData= br.readLine()) != null ) 
    { 
      if (! lineData.equals("")) //---------To avoid new empty lines
      {
       allData  = allData.append(lineData+"\n");// +  "\n"  + lineData ;
      }
    }
 
    MailSender mailSender = null;

    EmailMessage em = new EmailMessage();
    em.setBody(allData.toString());
    em.setSubject(subject);
    em.setTo( mailRecipients);
    em.setCc(ccRecipients);
    em.setFrom(supportAdminMail);
      
    mailSender = new MailSender(smtpServerIP , SysConfigParams.getMailSenderUserName()  , SysConfigParams.getMailSenderPassword());
    mailSender.useGmail(useGmailAccount.equals("Y"));
    mailSender.sendMail(em);
    
   } 
   catch (Exception e) 
   {
	e.printStackTrace();
    System.out.print("Unable to Send URL Contents Due to " + e.getMessage());
    throw e;
   }
   finally
   {   
    //if (pw != null ) {pw.close();}
    if (is != null ) {is.close();}
    if (br != null ) {br.close();}
   }
  }

}