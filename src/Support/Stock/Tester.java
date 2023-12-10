package Support.Stock;
import java.text.*;
import java.util.*;

import Support.Misc;
public class Tester 
{
  public Tester()
  {
  }

  public static void main(String[] args) throws Exception
  {
       
    Support.XMLConfigFileReader supportConfig =  Misc.getXMLConfigFileReader(false ) ;  
    java.sql.Connection con= supportConfig.reposatoryConn.generateConnection();
     
    StocKInfoExtractor sie = new StocKInfoExtractor(con);
    sie.setSendSMS(true);
    String[] x = {"300","0.1"};
    for (int i = 0 ; i< 4 ; i++)
    {
       
    
     sie.main(x);
     //sie.extractDataDirectly();
     // The following will store new value in DB and invoke another threads (a new thread with a new db connection for each company) to deal with the changes
     
     //sie.storeDatainDb(); 
    }
   
  }
}