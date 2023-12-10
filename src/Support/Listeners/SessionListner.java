package Support.Listeners;

import javax.servlet.http.*;

import Support.Misc;
import Support.UserUnCommitedTransactions;

import java.sql.*;
import java.util.*;
import java.io.*;
public class SessionListner implements HttpSessionListener
{
  public  Connection con=null;
  public  Connection repCon=null;  
  

//-----------------------------------------------------------------------------
  public void sessionDestroyed(HttpSessionEvent se)
  {
    //--------Close DB Connection  when Http session destroied---
    HttpSession session = se.getSession();
    String sessionId = session.getId() ; 
    this.con = (Connection) session.getAttribute("con");
    String user = "'Unable to Get User Name' ";
    try{
    user = Misc.getConnectionUserName(con);
    }
    catch (Exception e) {}
     //--------When session destroyed close the connection added to it---------
    if (con != null)
    {
    	try {
			UserUnCommitedTransactions.commitConnection(con, session);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
      try {this.con.close();   }
      catch (Exception e){}
    } 
        
    this.repCon = (Connection) session.getAttribute("repCon");
    if (repCon != null)
    {
      try {this.repCon.close();   }
      catch (Exception e){}
    }
    
    System.out.println("Session ("+sessionId+") for DB User " + user + " Have Been Terminated Successfully At Date-time = " + new java.util.Date() );
  }
//-----------------------------------------------------------------------------
  public void sessionCreated(HttpSessionEvent se)
  {
     HttpSession session = se.getSession();
     System.out.println("New Http Session Created : session id = " + session.getId() + " At Date-time = " + new java.util.Date());
      File f = new File("c:\\temp\\xxxx.xx"); 
      try{
      f.createNewFile();
      }
      catch (Exception e){}
  }
//------------
  public void setTypeMap(Map tm )
  {
  }
//---------------------------------
}