package SADAD.TRANSFERS;
import java.net.*;
public class MyAuthenticator extends Authenticator
{ 
  public String userName = "";
  public String passWord = "";
  
  public  MyAuthenticator(String v_userName , String v_passWord)
  {
    super();
    this.userName = v_userName;
    this.passWord = v_passWord;
  }
  protected  PasswordAuthentication getPasswordAuthentication ()
  {
     return new PasswordAuthentication(userName,passWord.toCharArray());
  }
}