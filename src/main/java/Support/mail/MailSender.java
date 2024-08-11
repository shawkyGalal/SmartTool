package Support.mail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;

import Support.ConnParms;
import Support.Misc;
import Support.XMLConfigFileReader;

import com.sideinternational.sas.event.logging.Console;
import com.sideinternational.web.swaf.SWAF;
import com.smartValue.database.ApplicationContext;
import com.smartValue.database.DataSet;
import com.smartValue.database.Pool;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.SysParams;
import com.smartValue.database.map.services.ModuleServicesContainer;
import com.smartValue.web.listners.SmartToolContextListener;

//import Support.SysConfigParams;

public class MailSender 
{

  private String smtpServerIP = "";
  private boolean useGmailAccount = false;
  private String userName ;
  private String password ;
  private String googleUserName = "shawky.foda" ;  
  private String googlePassword =  "Redsea1!" ;  
  private int smtpPort = 25;
  private DataSet sysParamsDs ; 
  
  public int getSmtpPort() {
	return smtpPort;
  }

  public void setSmtpPort(int smtpPort) {
	this.smtpPort = smtpPort;
  }

  
  	public String getUserName() {
	return userName;
  	}

  	public void setUserName(String userName) {
	this.userName = userName;
	}

	public void setPassword(String password) {
	this.password = password;
	}


	public MailSender(DataSet m_sysParamsDs ) throws Exception 
	{ this.sysParamsDs = m_sysParamsDs ; 
	     this.setParams () ; 
	}
 
 private void setParams()
 {
	 String useGmailAccountStr = ((SysParams)sysParamsDs.getFirstFilteredPO("E_NAME" , "UseGmailAccount" )).getValValue();
		this.useGmailAccount = useGmailAccountStr.equalsIgnoreCase("Y");
	    this.smtpServerIP = ((SysParams)sysParamsDs.getFirstFilteredPO("E_NAME" , "SMTP_SERVER" )).getValValue();
	    this.userName = ((SysParams)sysParamsDs.getFirstFilteredPO("E_NAME" , "MailSenderUserName" )).getValValue();
	    this.password = ((SysParams)sysParamsDs.getFirstFilteredPO("E_NAME" , "MailSenderPassword" )).getValValue();
	    this.googleUserName = ((SysParams)sysParamsDs.getFirstFilteredPO("E_NAME" , "google_user" )).getValValue();
	    this.googlePassword = ((SysParams)sysParamsDs.getFirstFilteredPO("E_NAME" , "google_password" )).getValValue();

 }
  public MailSender(String autoLoginEnvName , String autoLoginUsername , String autoLoginPassword ) throws Exception 
  {
	  XMLConfigFileReader aa =  Misc.getXMLConfigFileReader(false ) ; 
	  ConnParms cp =  aa.getConnParmsByName(autoLoginEnvName);  
	  java.sql.Connection  con = null;
	  con = cp.generateConnection(autoLoginUsername, autoLoginPassword , "NORMAL");
	   
		try{SWAF.initialize();}catch (Exception e) {e.printStackTrace();} 
		Map<String, Pool> allPools = SmartToolContextListener.initializeConnections();	
		ApplicationContext.setPools(allPools);
		ModuleServicesContainer msc = Support.Misc.getModuleServiceContainer(autoLoginEnvName , 1 );  
		com.smartValue.database.map.services.SecUserDataService secUsrDtaServices = msc.getSecUserDataService();
		SecUsrDta autoLoggedUser = secUsrDtaServices.getUserByUserName(con.getMetaData().getUserName().toUpperCase());
		this.sysParamsDs = ( autoLoggedUser.getUserCompany().getSysParams()) ;
		this.setParams () ; 
	/*	
	this.useGmailAccount = SysConfigParams.getUseGmailAccount().equals("T");
    this.smtpServerIP = m_smtpServerIP;
    this.userName = m_userName;
    this.password = m_password;
    */
  }
  
  public void sendUsingHotmail(String from , String[] to ,String[] cc, String subject , String Body ) throws Exception
  {
    this.smtpServerIP = "http://services.msn.com/svcs/hotmail/httpmail.asp";
   // not yet implemented
   }

  private Session getMailSession(EmailMessage em)
  {
   Session mailSession ;
   if (this.useGmailAccount)
   {
	 this.smtpServerIP = "smtp.gmail.com";
     Properties p = new Properties();
     p.put("mail.smtp.user", em.getFrom() ); //"foda.sh@gmail.com");
     p.put("mail.smtp.host", this.smtpServerIP);
     p.put("mail.smtp.port", "465");
     p.put("mail.smtp.starttls.enable","true");
     p.put("mail.smtp.auth", "true"); 
     p.put("mail.smtp.debug", "true");
     p.put("mail.smtp.socketFactory.port", "465"); 
     p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
     p.put("mail.smtp.socketFactory.fallback", "false"); 
     //p.setProperty("proxySet","true");
     //p.setProperty("socksProxyHost","172.27.0.1");
     //p.setProperty("socksProxyPort","3128");
     
        
     Authenticator auth = new SmtpGmailAuthenticator();
     mailSession = Session.getInstance(p,auth);
   }
   else 
   {
   	    Properties propos = new Properties();
	    propos.put("mail.host",this.smtpServerIP);  //"130.1.2.36"
	    propos.put("mail.port",String.valueOf(this.smtpPort));  
	    MailAuth mailAuth = new MailAuth(this.userName , this.password );
	    mailSession = Session.getInstance(propos,mailAuth);
     
   }
   return mailSession;
  }

  public void sendMail(EmailMessage em  ) throws Exception
  {
    
	  String from = em.getFrom();
	  String[] to = em.getTo();
	  String[] cc = em.getCc();
	  String[] bcc = em.getBcc();
	  String subject = em.getSubject();
	  String Body  = em.getBody();
	  String[] attFileNames = em.getAttFileNames();
	  Session mailSession = this.getMailSession(em); 
   
    Address fromUser = new InternetAddress(from);
    Message msg = new MimeMessage(mailSession);
    msg.setFrom(fromUser);

    Address[] toUsers = new Address[to.length];
    for (int i=0 ; i<to.length ; i++)
    {
      toUsers[i] = new InternetAddress(to[i]);
    }
    msg.setRecipients(Message.RecipientType.TO,toUsers);
    
    if (cc !=null)
    {
      Address[] ccUsers = new Address[cc.length];
      for (int i=0 ; i<cc.length ; i++)
      {
        ccUsers[i] = new InternetAddress(cc[i]);
      }
      msg.setRecipients(Message.RecipientType.CC,ccUsers);
    }
    if (bcc !=null)
    {
      Address[] bccUsers = new Address[bcc.length];
      for (int i=0 ; i<bcc.length ; i++)
      {
        bccUsers[i] = new InternetAddress(bcc[i]);
      }
      msg.setRecipients(Message.RecipientType.BCC,bccUsers);
    }

    msg.setSubject(subject);
    
  
    if ( attFileNames != null && attFileNames.length > 0  )
    {
    	  // create and fill the first message part
        MimeBodyPart mbp1 = new MimeBodyPart();
        mbp1.setText(Body);
        
        Multipart mp = new MimeMultipart();
        mp.addBodyPart(mbp1);    
        
        // create the second message part
	    for (int i=0 ; i<attFileNames.length ; i++)
	    {
	       MimeBodyPart mbp = new MimeBodyPart();
	        // attach the file to the message
	       mbp.attachFile(attFileNames[i]);
	       mp.addBodyPart(mbp);
	    }
	    msg.setContent(mp,"text/html; charset=UTF-8");
    }

    else
    {
    	
    	msg.setContent(Body,"text/html; charset=UTF-8");
    }

    try{
	    Console.log("Start Sending Email " ,MailSender.class );
	    Console.log("Sending email Message " + msg  , MailSender.class );
    	Transport.send(msg);
    	Console.log("End Sending Email " ,MailSender.class );
    }

    catch (javax.mail.SendFailedException sfe)
    {
     //- 1- Send only to valid Addresses
     if (sfe.getInvalidAddresses() != null && sfe.getInvalidAddresses().length > 0 && em.getTo().length !=0)
     {
      String errorMsg = "Smart Tool Unable to send to the following invalid Adresse(s):<br>";
      for (int i = 0; i< sfe.getInvalidAddresses().length;i++)
      {
       errorMsg += sfe.getInvalidAddresses()[i].toString()+"<br>";
      }
      String[] validAddreses = new String[sfe.getValidUnsentAddresses().length]; 
      for (int i = 0 ; i<  sfe.getValidUnsentAddresses().length ; i++)
      {
        validAddreses[i] = sfe.getValidUnsentAddresses()[i].toString();
      }
      if (validAddreses.length > 0 )
      {
      Console.log("\tStart Sending Only Valid Adresses.. Some Invalid Addresses Found " ,MailSender.class );
      em.setTo(validAddreses);
      em.setCc(null);
      this.sendMail(em);
      }
                   
      // Sending The Invalid Adresses To Support Administrator
      String adminNotifyEmailAddress = ((SysParams) this.getSysParamsDs().getFirstFilteredPO("E_NAME", "Admin_Notify_Mail_Address_Receiver")).getValValue() ; 
      Console.log("\tStart Sending Invalid Adresses to Smart Tool Admin ("+ adminNotifyEmailAddress+ ")" ,MailSender.class );
      em.setSubject(errorMsg);
      String[] admin = {adminNotifyEmailAddress};
      em.setTo(admin);
      this.sendMail(em);
     }
     else {throw sfe;}
    }
    catch ( Exception e)
    {		
    		// Try Send Using Gmail Account
    	   if (! this.useGmailAccount)
    	   {
    		   Console.log("Email Failed to be sent Using Your Email Server Due to : " + e.getMessage() + " \nSmart Tool will try To send it Using Gmail account " ,MailSender.class );
	   			Console.log ("Failed to Send From :  smtpServerIP : " + this.smtpServerIP + "UserName" + this.getUserName() + "password:" + this.password ,MailSender.class) ; 
     	   }
    	   else {
    		   		Console.log("OPPPPPP... Email Failed Using Gmail Too Due to : "+ e.getMessage()  ,MailSender.class);
    		   		throw e;
    		   	}
    	   em.setSubject("Smart Tool Unable to send..." + em.getSubject());
    	   this.useGmail(true);
    	   try{
    		   this.sendMail(em);
    		   Console.log("OK... Email Sent Successfully Using Gmail Account" , MailSender.class);
    	   	 }
    	   catch (Exception ex) {System.out.print("Unable to Send Using Gmail Due to " + ex.getMessage()); throw ex;}

    
    }
  }
//----------------------------------------------------
public static void main(String[] arg)
  {
    try{
    String[] to = {"shawky.foda@smart-value.com"};
    String from =  "shawky.foda@gmail.com";
    MailSender ms = new MailSender("PNU_PROD" , "JCCS" , "123");
    //String[] attFileNames = {"D:\\MyWork\\JavaWork\\SideWork\\Tutorial\\SideReportingCMD\\Command Line Reporting Tool- Architect .vsd"};
    ms.useGmailAccount = true ;
    EmailMessage em = new EmailMessage();
    
    em.setTo(to);
    em.setFrom(from);
    //em.setAttFileNames(attFileNames);
    em.setSubject("Test Subject");
    em.setBody("My Router IP Address : " + MailSender.getIp());
    //ms.useGmail(true);
    ms.sendMail(em);
  
    System.out.print("ok..");

    }
    catch (Exception e) {
    System.out.print(e.getMessage());
    System.out.print("");
    }
  }

public static String getIp() throws Exception {
    URL whatismyip = new URL("http://checkip.amazonaws.com");
    BufferedReader in = null;
    try {
        in = new BufferedReader(new InputStreamReader(
                whatismyip.openStream()));
        String ip = in.readLine();
        return ip;
    } finally {
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
private class SmtpGmailAuthenticator extends javax.mail.Authenticator 
{

	public PasswordAuthentication getPasswordAuthentication()  
		{
			return new PasswordAuthentication(googleUserName + "@gmail.com", googlePassword ); // password not displayed here, but gave the right password in my actual code.
		}
}

private class HttpHotmailAuthenticator extends javax.mail.Authenticator {

public PasswordAuthentication getPasswordAuthentication() {
return new PasswordAuthentication("foda_sh@hotmail.com", "redsea11"); // password not displayed here, but gave the right password in my actual code.
}
}

public void useGmail(boolean b) {
	this.useGmailAccount = b;
	
}


public DataSet getSysParamsDs() {
	return sysParamsDs;
}

}
