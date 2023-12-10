package Support.Stock;
import Support.mail.*;
import Support.*;

import java.sql.*;
import java.util.*;
import java.sql.Connection;
public class SymboleAlerts 
{
  public int symboleIndex;
  public StocKInfoExtractor sie;
  public int symbole;
  public double symbolePrice;
  Vector alertOwnerEmails;
  Vector values;
  Vector alertTypes;
  Vector user_msgs;
  Vector rowIds;
  Vector mobile_nos;
  String companyName;
  MailSender ms;
  Connection con;
  String stockWatcherEmail  = "stockWatcher@StockSafe.com.sa";  
  public SymboleAlerts(int m_symboleIndex ,StocKInfoExtractor m_sie,  Connection m_con) throws Exception 
  {
   this.con = m_con;
   this.sie = m_sie;
   alertOwnerEmails = new Vector();
   values = new Vector();
   user_msgs = new Vector();
   alertTypes = new Vector();
   rowIds = new Vector();
   mobile_nos = new Vector();
   this.symboleIndex = m_symboleIndex;
   //-For stock index 
   
   if (m_symboleIndex == -1)
   {
     this.symbole = 0;
     this.companyName = "Stock Index";
   }
   else 
   {
    this.symbole = Integer.parseInt(this.sie.symbols.elementAt(symboleIndex).toString());
    this.companyName = this.sie.companies.elementAt(symboleIndex).toString();
    this.symbolePrice = Double.parseDouble(this.sie.prices.elementAt(symboleIndex).toString());
   }
   if(this.symbolePrice != 0)
   {
       //----implement seting Alert values users, valueExceeds , valueLess  
       //-- Get all Alerts for this Symbole---
       String queryStmt = "select ua.rowid, ui.email , ua.alert_type, ua.value , ua.mobile_no -- ,ua.user_msg "
                         + "\n from users_alerts ua, users_info ui , companies_info ci "  
                         + "\n where ui.user_id = ua.alert_Owner "
                         + "\n and ua.symbole = '" + this.symbole + "'"
                         + "\n and ci.symbole = ua.symbole" 
                         + "\n and ua.enabled = 'Y' " 
                         + "\n and ua.active ='Y'"
                         + "\n and ua.expiry_date > sysdate"
                         + "\n and ua.alert_counter < ua.max_alerts" 
                         //----- Select only alerts which should be fired
                         //+ "\n and ( "
                         //+ "\n       ( ua.alert_type = 'GREATER_THAN' and ua.value <= "+this.symbolePrice + " )"
                         //+ "\n        or "
                         //+ "\n       (ua.alert_type = 'LESS_THAN' and ua.value >= "+this.symbolePrice + " )"
                         //+ "\n     ) " 
                         ;
       try
       {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(queryStmt);
        while(rs.next())
        {
         alertOwnerEmails.addElement( rs.getString("email"));
         values.addElement( rs.getString("value"));
         //user_msgs.addElement( rs.getString("user_msg"));
         alertTypes.addElement( rs.getString("alert_Type"));
         this.rowIds.addElement(rs.getString("rowid"));
         this.mobile_nos.addElement(rs.getString("mobile_no"));
        }
        st.close();
        rs.close();
       }
       catch (SQLException sqle){throw new Exception("Unable to Extract symbole alerts from db \ndue to unable to execute "+ queryStmt + "\ndue to " +sqle.getMessage());}
    
       //Support.Misc msc = new Support.Misc(con);
       String smtpServerIP = SysConfigParams.getSMTP_SERVER();// msc.getSystemParameterValue(2);
       ms = new MailSender(smtpServerIP , "sfoda" , "foda");
  
   }
}
  
  public void fire(boolean sendSMS)
  {
   //-- This Code will send an email and sms 
   SmsSernder smsSender = new SmsSernder(SmsSernder.DEFAULT_CLICKTELL_API_ID , "proxy-dr", "8080" ,"SADAD\\shawky.foda" ,"redsea11");
   if (this.symbolePrice == 0 )
   {
    return;
   }
   String from_mobile_no = "966506818206";
   for (int i=0 ; i< alertOwnerEmails.size() ; i++)
    {
      double symbolePrice = Double.parseDouble(this.sie.prices.elementAt(this.symboleIndex).toString());
      double alertValue = Double.parseDouble(values.elementAt(i).toString());
      String mailSubject="";
      String mailBody = "";
      String[] bcc = {alertOwnerEmails.elementAt(i).toString()};
      String[] cc = bcc;
      String[] to = {"sfoda@sadad.com"};
      
      boolean greaterThanAlertType = alertTypes.elementAt(i).toString().equals("GREATER_THAN");
      boolean lessThanAlertType =alertTypes.elementAt(i).toString().equals("LESS_THAN") ;
      if ( greaterThanAlertType && symbolePrice >=  alertValue 
         ||lessThanAlertType && symbolePrice <=  alertValue
         )
      {
        if (greaterThanAlertType )
          {
            mailSubject = sie.upperLimitAlertMailSubjects.elementAt(symboleIndex).toString();
            mailBody = sie.upperLimitAlertMailBodies.elementAt(symboleIndex).toString();
          }
      
        if (lessThanAlertType)
         {
            mailSubject = sie.lowerLimitAlertMailSubjects.elementAt(symboleIndex).toString();
            mailBody = sie.lowerLimitAlertMailBodies.elementAt(symboleIndex).toString();
         }

        mailBody = Misc.repalceAll(mailBody , "$$symbPrice" , String.valueOf(symbolePrice));
        mailBody = Misc.repalceAll(mailBody , "$$alertValue" , String.valueOf(alertValue));
        mailSubject = Misc.repalceAll(mailSubject, "$$alertValue" , String.valueOf(alertValue));
        //mailBody += "\n\n<br>Your Custom Message:" + user_msgs.elementAt(i).toString();
        //---send mail to user
        boolean mailSent = false;
        boolean smsSent = false;
        try{
            Support.mail.EmailMessage em = new Support.mail.EmailMessage(this.stockWatcherEmail ,to,cc,mailSubject,mailBody);
          em.setBcc(bcc);
          ms.sendMail(em);
          mailSent = true;
        }
        catch (Exception e) {System.out.println("Unable to send mail to " + bcc[0] + "due to\n" + e.getMessage() );}
        //----------Send an SMS ------
        if (sendSMS)
        {
         try
         { 
           SmsMessage smsMessage = new SmsMessage(mailSubject , this.mobile_nos.elementAt(i).toString() , from_mobile_no ) ;
           smsSender.sendSMS(smsMessage); 
           smsSent= true;
         }
         catch (Exception e) 
         {System.out.println("Unable to send SMS to  " + cc[0] + "Due to " + e.getMessage() );}
        }  //------End of sending an SMS-----
        if (mailSent && smsSent && sendSMS || !sendSMS && mailSent)
        {
         try
         {
          this.disableAlert(rowIds.elementAt(i).toString());
         }
         catch (Exception e) {System.out.println("Unable to Disable Alert "+ "due to\n" + e.getMessage() );}
        }
      }
   }
  }
  
  private void disableAlert(String m_rowid) throws SQLException
  {
    String updateStmt = "update users_alerts t set enabled = 'N', alert_counter = alert_counter+1 where t.rowid= '" + m_rowid +"'";
    this.con.createStatement().execute(updateStmt);
    //- Enableing the other type 
    updateStmt = "update users_alerts u set u.enabled = 'Y'"
                    +" where u.rowid = "
                    +" (select ua.rowid "    
                    +   " from users_alerts ua , users_alerts ua1 "
                    +   " where ua1.rowid = '"+m_rowid+"'"
                    +   " and ua.alert_owner = ua1.alert_owner"
                    +   " and ua.symbole = ua1.symbole"
                    +   " and ua.alert_type = case when ua1.alert_type = 'GREATER_THAN' then 'LESS_THAN' "
                    +   "                         else "
                    +   "                           case when ua1.alert_type = 'LESS_THAN' then 'GREATER_THAN' end"
                    +   "                    end "
                    +" )";
   //System.out.println(updateStmt);
   this.con.createStatement().execute(updateStmt);
  }
  public static void main(String[] args)
  {
  
  }
}