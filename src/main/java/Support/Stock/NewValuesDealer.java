package Support.Stock;
import java.sql.*;

import Support.Misc;

public class NewValuesDealer extends Thread 
{
  StocKInfoExtractor sie;
  int symboleIndex;
  int symbole;
  boolean sendSMS = false;
  Connection con = null;
  
  // send m_symboleIndex = -1 for Stock index
  public NewValuesDealer(StocKInfoExtractor m_sie , int m_symboleIndex,  boolean m_sendSMS) throws Exception 
  {
   this.sie = m_sie;
   this.symboleIndex = m_symboleIndex;
   this.sendSMS = m_sendSMS;
   if (m_symboleIndex == -1) // 
   {
   this.symbole = 0;}
   else {this.symbole = Integer.parseInt(this.sie.symbols.elementAt(symboleIndex).toString());}
   this.con = m_sie.con;
  }
   public void run() // deal with the current values of the given symbole
   {
     boolean useTheSameCon = true;
     if (! useTheSameCon)
     {
     try{
      //-------generate a new DB Connection dedicated to this newValuesDealer
     Support.XMLConfigFileReader supportConfig =  Misc.getXMLConfigFileReader(false ) ;  
     con= supportConfig.reposatoryConn.generateConnection();
     }
     catch (Exception e){System.out.println("unable to establish a new DB connection for newvalueDealers due to \n" + e.getMessage());}
     }
    //1----------Update Summary info---
    if (this.symbole == 0) // currently used only to update index summary info , all companies summaries info extracted from tadawl directly
    {
      try{
       // Commented after getting these values from tadual 
       PreparedStatement prstmt =  con.prepareCall("call update_summary_info("+this.symbole+", trunc(sysdate))");
       prstmt.execute();
       prstmt.close();
      }
      
      catch (Exception e){System.out.println("Unable to Update summary info for symbole no" + this.symbole + "due to " + e.getMessage());}
    }
    //System.out.println ("Summary info for Company " + this.symbole + " Update Successfully");

    //2--------Send Alerts emails and SMS
    //double price = Double.parseDouble(this.sie.prices.elementAt(this.symboleIndex).toString());
    //if(price != 0)
    {
      try{
      SymboleAlerts symboleAlerts = new SymboleAlerts(this.symboleIndex ,this.sie , this.con);
      symboleAlerts.fire(this.sendSMS);
      }
    catch (Exception e){System.out.println("Unable to Send alerts due to  " + e.getMessage() + " For company " + this.sie.companies.elementAt(this.symboleIndex));}
    }
    //2---End of Send Alerts email and SMS
    //System.out.println ("Alerts (if exists) sent for Symbol " + this.symbole +"Successfully");
    //---------Commit and close DB Connection---
    if (con != null )
    {
     try{
     //con.commit();
     //con.close();
     }
     catch (Exception e) {}
    }
   }
   


}