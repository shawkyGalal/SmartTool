package Support.RemoteControl;

import Support.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;
/**
 * Auther : Shawky Galal Foda
 * Date : 16/09/2006
 * This Class Extract WebMethod Info from WM Admin site and store it in Oracle DB tables
 * also it sends alerts emails and SMS if mem used exceeds threshold value
 * */

public class CommandsReader 

{
  String wmURL;
  Connection con = null;
 

  public CommandsReader(String wmURL_m , Connection m_con ) throws Exception
  { 
     this.con = m_con;
     this.wmURL = wmURL_m;
 
  }

  public String getAllCommands() throws Exception
  {
    Support.DownLoad.URLDownLoader urldown = new Support.DownLoad.URLDownLoader();
    Support.DownLoad.MyAuthenticator  auth = new Support.DownLoad.MyAuthenticator( "Administrator", "manage");
    java.net.Authenticator.setDefault(auth);
    BufferedReader br = urldown.getBufferreader( this.wmURL 
                            , "isa01-drxx" , 8080 , "Administrator", "manage"); // "SADAD\\shawky.foda" , "Redsea22")    ;
      String line = br.readLine();
      String allCommands = line;
      while (line!= null )
      { 
        allCommands += "\n" + line;
        line = br.readLine();
      }
    br.close();
    return allCommands;
  }
  


}