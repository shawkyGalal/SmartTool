package Support.Stock;
import java.util.*;

public class StockInfo 
{  
  String indexValue = "";
  String dateTimeValue = "";
  Date dateTimeValueAsDate = null;

   Vector companies = new Vector();
   Vector symbols = new Vector();
   Vector prices = new Vector();
   Vector last_trade_vols = new Vector();  
   Vector no_of_tradess = new Vector();
   Vector volume_tradeds = new Vector();
   Vector best_bid_prices = new Vector();
   Vector best_bid_volumes = new Vector();
   Vector best_offer_prices = new Vector();
   Vector best_offer_volumns = new Vector();
  //-Today Summary Info
   Vector today_open_values  = new Vector();
 //---today_high_value ----
   Vector today_high_values  = new Vector();
 //---today_low_value ----
   Vector today_low_values   = new Vector();
   
   Vector upperLimitAlertMailBodies=new Vector();
   Vector lowerLimitAlertMailBodies=new Vector();
  
   Vector upperLimitAlertMailSubjects=new Vector();
   Vector lowerLimitAlertMailSubjects=new Vector();
  public StockInfo(int no_of_Companies)
  {
   Integer  initialValue = new Integer(-2);
   for (int i =0 ; i< no_of_Companies ; i++)
   {
    companies.addElement(""); 
    symbols.addElement(initialValue);
    prices.addElement(initialValue);
    last_trade_vols.addElement(initialValue);
    no_of_tradess.addElement(initialValue);
    volume_tradeds.addElement(initialValue);
    best_bid_prices.addElement(initialValue);
    best_bid_volumes.addElement(initialValue);
    best_offer_prices.addElement(initialValue);
    best_offer_volumns.addElement(initialValue);
  //-Today Summary Info
    today_open_values.addElement(initialValue);
 //---today_high_value ----
    today_high_values.addElement(initialValue);
 //---today_low_value ----
    today_low_values.addElement(initialValue);
   
    upperLimitAlertMailBodies.addElement("");
    lowerLimitAlertMailBodies.addElement("");
  
    upperLimitAlertMailSubjects.addElement("");
    lowerLimitAlertMailSubjects.addElement("");
   }
  }
}