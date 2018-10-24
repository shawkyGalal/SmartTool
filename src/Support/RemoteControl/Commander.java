package Support.RemoteControl;
import Support.*;
import Support.mail.*;
public abstract class  Commander 
{
protected String mailTo ;
protected String AllCommands;
protected String command;
protected String commandOutput;

 public  abstract void execAndSendMail() throws Exception;
 
 public String extractInfo( String tagName) throws Exception
{
  String value = "";
  String openTag = "<"+tagName+">";
  openTag= openTag.toUpperCase();
  try{
  int beginIndex = this.AllCommands.toUpperCase().indexOf(openTag)+openTag.length();
  
  String closeTag = "</"+tagName+">";
  closeTag= closeTag.toUpperCase(); 
  int endIndex = this.AllCommands.toUpperCase().indexOf(closeTag);
  value = this.AllCommands.substring(beginIndex , endIndex);
  }
  catch (Exception e) {throw new Exception ("Unable to Find Tag " + tagName + "in The Commands \n" + this.AllCommands);}
  return value;
}

 public void sendCommandOutByEmail() throws Exception
 {

    MailSender ms = null;
    String[] to = {this.mailTo};

    boolean useGmail = SysConfigParams.getUseGmailAccount()=="Y";
    if (useGmail)
    {
            // Sending Using Gmail Account
            ms =new MailSender("smtp.gmail.com", "sfoda" , "foda");
            EmailMessage em = new EmailMessage ("remoteController@SmartWay.com",to,null,"Result of Executing: " + this.command , commandOutput );
            ms.useGmail(true);
            ms.sendMail(em);
      
    }
    else 
    {
             //-- Sending Results to my email
            Support.XMLConfigFileReader supportConfig =  Misc.getXMLConfigFileReader(false ) ;  
            java.sql.Connection repCon = supportConfig.reposatoryConn.generateConnection();// NCCI.ConnectionFactory.createConnection(dBServerName,"edgeDev","compl","compl");

            //Support.Misc msc = new Support.Misc(repCon);
            String smtpServerIP = SysConfigParams.getSMTP_SERVER();//msc.getSystemParameterValue(2);
            ms = new MailSender(smtpServerIP , "sfoda" , "foda");
            EmailMessage em = new EmailMessage("remoteController@Smart-value.com",to,null,"Result of Executing: " + this.command , commandOutput);
            ms.sendMail(em);
            //--End of Sending Mail---
    }       
            
 }
  public static void main(String[] args) throws Exception
  {
    //Commander commander = new Commander();
  }
}