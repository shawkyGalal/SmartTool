package Support;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuth extends Authenticator 
{
  private String userNamme = "";
  private String password = "";
  public MailAuth(String m_userName , String m_password)
  {
   super();
   this.userNamme = m_userName;
   this.password = m_password;
  }
  public PasswordAuthentication requestPasswordAuthentication()
  {
    return new PasswordAuthentication(this.userNamme,this.password);
  }
}