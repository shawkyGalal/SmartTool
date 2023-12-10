package Support;
import java.sql.Connection;
public class SysConfigParams 
{
  public static boolean intiated = false;
  private static String SMTP_SERVER;
  private static String Support_User_Email;
  private static String Admin_Notify_Mail_Address_Receiver;
  private static String AutoLogin_Environment_Name;
  private static String AutoLogin_Username;
  private static String AutoLogin_Password;
  private static String Enable_Remote_Control;
  private static String UseGmailAccount;
  private static String CompanyLogo;
  private static String MailSenderUserName;
  private static String MailSenderPassword;
  private static String SystemLoggingFolder;
  //private static String GoogleUserName;
  //private static String GooglePassword;
  
  public SysConfigParams()
  {
  }
    
  public static void initialize() throws Exception
  {
  if (SysConfigParams.intiated == false)
  {
  
  Support.XMLConfigFileReader supportConfig =  Misc.getXMLConfigFileReader(false ) ;  
  Connection repCon = supportConfig.reposatoryConn.generateConnection();
  Support.Misc msc = new Support.Misc(repCon);
  SysConfigParams.SMTP_SERVER = msc.getSystemParameterValue(2);
  SysConfigParams.Support_User_Email = msc.getSystemParameterValue(3);
  SysConfigParams.Admin_Notify_Mail_Address_Receiver = msc.getSystemParameterValue(7);
  SysConfigParams.AutoLogin_Environment_Name = msc.getSystemParameterValue(8);
  SysConfigParams.AutoLogin_Username = msc.getSystemParameterValue(9);
  SysConfigParams.AutoLogin_Password = msc.getSystemParameterValue(10);
  SysConfigParams.Enable_Remote_Control = msc.getSystemParameterValue(11);
  SysConfigParams.UseGmailAccount = msc.getSystemParameterValue(12);
  SysConfigParams.CompanyLogo = msc.getSystemParameterValue(13);
  SysConfigParams.MailSenderUserName = msc.getSystemParameterValue(14);
  SysConfigParams.MailSenderPassword = msc.getSystemParameterValue(15);
  SysConfigParams.SystemLoggingFolder = msc.getSystemParameterValue(16);
//  SysConfigParams.GoogleUserName = msc.getSystemParameterValue(21);
//  SysConfigParams.GooglePassword = msc.getSystemParameterValue(22);

  repCon.close();
  SysConfigParams.intiated = true;
  }
  }
 public static String getCompanyLogo() throws Exception
 {
   if (! SysConfigParams.intiated)
   {
     SysConfigParams.initialize();
   }
   return SysConfigParams.CompanyLogo;
 }

 
 public static String getUseGmailAccount() throws Exception
 {
   if (! SysConfigParams.intiated)
   {
     SysConfigParams.initialize();
   }
   return SysConfigParams.UseGmailAccount;
 }

 public static String getEnable_Remote_Control() throws Exception
 {
   if (! SysConfigParams.intiated)
   {
     SysConfigParams.initialize();
   }
   return SysConfigParams.Enable_Remote_Control;
 }

 public static String getAutoLogin_Password() throws Exception
 {
   if (! SysConfigParams.intiated)
   {
     SysConfigParams.initialize();
   }
   return SysConfigParams.AutoLogin_Password;
 }

 public static String getAutoLogin_Username() throws Exception
 {
   if (! SysConfigParams.intiated)
   {
     SysConfigParams.initialize();
   }
   return SysConfigParams.AutoLogin_Username;
 }

 public static String getAutoLogin_Environment_Name() throws Exception
 {
   if (! SysConfigParams.intiated)
   {
     SysConfigParams.initialize();
   }
   return SysConfigParams.AutoLogin_Environment_Name;
 }

 public static String getAdmin_Notify_Mail_Address_Receiver() throws Exception
 {
   if (! SysConfigParams.intiated)
   {
     SysConfigParams.initialize();
   }
   return SysConfigParams.Admin_Notify_Mail_Address_Receiver;
 }

 public static String getSupport_User_Email() throws Exception
 {
   if (! SysConfigParams.intiated)
   {
     SysConfigParams.initialize();
   }
   return SysConfigParams.Support_User_Email;
 }

 public static String getSMTP_SERVER() throws Exception
 {
   if (! SysConfigParams.intiated)
   {
     SysConfigParams.initialize();
   }
   return SysConfigParams.SMTP_SERVER;
 }
 
 
  public static void main(String[] args) throws Exception
  {
    SysConfigParams.getSMTP_SERVER(); 
  }

public static String getMailSenderUserName() throws Exception{
	 if (! SysConfigParams.intiated)
	   {
	     SysConfigParams.initialize();
	   }
	   return SysConfigParams.MailSenderUserName;
}

public static String getMailSenderPassword()  throws Exception{
	if (! SysConfigParams.intiated)
	   {
	     SysConfigParams.initialize();
	   }
	   return SysConfigParams.MailSenderPassword;
}

public static String getLoggingPath() throws Exception{
	if (! SysConfigParams.intiated)
	   {
	     SysConfigParams.initialize();
	   }
	   return SysConfigParams.SystemLoggingFolder;
}
}