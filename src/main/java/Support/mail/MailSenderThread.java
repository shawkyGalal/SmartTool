package Support.mail;
import Support.mail.*;

public class MailSenderThread extends Thread 
{
EmailMessage em ;
Support.mail.MailSender ms ;
  
  public MailSenderThread(EmailMessage m_em ,MailSender m_ms )
  {
  this.ms = m_ms;
  this.em = m_em;
   }
  public void run() 
  {
    //MailSender ms = new MailSender(this.host);
    try 
    {
      ms.sendMail(this.em);
    }
    catch ( Exception e){}
  }
  public  static void main(String[] args) throws Exception
  {
     String[] to = {"foda.sh@smart-value.com"};
    String from = "sfoda@eastnets.com";//"foda.sh@gmail.com";
    EmailMessage em = new EmailMessage ();
    em.setTo(to);
    em.setSubject("testSubject");
    em.setBody("<a href ='gfgfg' > body .....</a>");
    em.setFrom(from);
   //from,to,null,,"", 
   MailSender ms = new MailSender("213.132.40.100" , "sfoda" , "foda" ) ;
    MailSenderThread x = new MailSenderThread(em , ms);
    x.start();
    System.out.print("sss");
  }
}