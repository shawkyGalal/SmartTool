package Support.Stock;
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
 * This Class Extract Stock Info from Tadawl site and store it in Oracle DB tables
 * table 1: stock_info Table contains instantanous information for each share
 * table 2: summary_info contains summary information about each share
 * also it sends alerts emails and SMS if share new information match alert condition in Alerts table in DB
 */

public class StocKInfoExtractor extends Thread
{
  String dateTimeValue = "";
  Date dateTimeValueAsDate = null;
  Vector symbols = new Vector();
  boolean mailsConstructed =false; //
  String tdwlAllCompsURL = "http://www.tadawul.com.sa/wps/portal/!ut/p/.cmd/cs/.ce/7_0_A/.s/7_0_4AI/_s.7_0_A/7_0_4AI/.cmd/ChangeLanguage/.l/en";

  String insertNewInfo =     "insert into stock_info (symbol , price , last_trade_vols , no_of_trades , volume_traded ,best_bid_price , best_bid_volume , best_offer_price , best_offer_volumn, price_time)"
                                        +" values (?,?,?,?,?,?,?,?,?,?)";

  String deleteTodaySummary= "delete from summary_info where symbole = ? and trunc(working_day)= trunc(?)";

  String insertTodaySummary = "insert into summary_info (symbole, working_day, open_value, close_value, min_value, max_value, volume_traded)"
                                      + " values (?,trunc(?),?,?,?,?,?) ";

  PreparedStatement insertNewInfoPS =  null;
  PreparedStatement deletTodaySummaryPS =  null;
  PreparedStatement insertTodaySummaryPS =  null;

  Vector companies = new Vector();
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


  String indexValue = "";
    
  Vector upperLimitAlertMailBodies=new Vector();
  Vector lowerLimitAlertMailBodies=new Vector();
  
  Vector upperLimitAlertMailSubjects=new Vector();
  Vector lowerLimitAlertMailSubjects=new Vector();
  String upperLimtMailTemplate = "";  
  String lowerLimtMailTemplate = ""; 
  String upperLimitAlertMailSubject="Stock Alert- $$companyName ($$symbole) has reached your defined maximum value $$alertValue SR";
  String lowerLimitAlertMailSubject="Stock Alert- $$companyName ($$symbole) has reached your defined minimum value $$alertValue SR";
  boolean sendSMS =false;
  Connection con = null;
  public void setSendSMS(boolean m_sendSMS)
  {
    this.sendSMS = m_sendSMS;
  }
  public StocKInfoExtractor()
  {}
  public StocKInfoExtractor(Connection m_con) throws Exception
  { 
     this.con = m_con;
  
     this.insertNewInfoPS =  this.con.prepareStatement(insertNewInfo);
     this.deletTodaySummaryPS =  this.con.prepareStatement(deleteTodaySummary);
     this.insertTodaySummaryPS =  this.con.prepareStatement(insertTodaySummary);
  
     //1-read Upper limit mail Template
     this.upperLimtMailTemplate = readFile("upperLimitMailTemplate.html");  
     //2-read lower limit mail Template
     this.lowerLimtMailTemplate = readFile("lowerLimitMailTemplate.html");
  }
  public void extractDataFromBufferReader(BufferedReader m_br) throws Exception
  {  
  
   symbols = new Vector();
   companies = new Vector();
   prices = new Vector();
   last_trade_vols = new Vector();  
   no_of_tradess = new Vector();
   volume_tradeds = new Vector();
   best_bid_prices = new Vector();
   best_bid_volumes = new Vector();
   best_offer_prices = new Vector();
   best_offer_volumns = new Vector();
  
    String line = m_br.readLine();
    boolean indexFound = false;
    boolean dateTimeFound = false;

 

    //-----------Searching for Index------
    while (line!= null && !indexFound)
      {
        if (!indexFound )
        {
          String searchForIndexBy  = "<A href=\"/wps/portal/!ut/p/_s.7_0_A";
          int indexToTASI = line.toLowerCase().indexOf(searchForIndexBy.toLowerCase()) ;
          if (indexToTASI != -1) //-------if index found
          {
            indexToTASI = line.toLowerCase().indexOf("TASI".toLowerCase()) ;
            if (indexToTASI != -1)
             { m_br.readLine(); //----------There is a line contains "</A></TD>"
               indexValue = m_br.readLine().replaceFirst("<TD class=\"table_numbers\" nowrap>", "").replaceFirst("</TD>","");
               indexFound = true;
             }
          }
        }
       line = m_br.readLine();
      }
    //-----Searching for Date ---
    //-------The following today Date will be used to get exact current time
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    String todayDate = sdf.format(new Date());
    

    while (line!= null && !dateTimeFound)
    {
      int indexToDate = line.toLowerCase().indexOf(todayDate.toLowerCase()) ;
          if (indexToDate != -1) //-------if index found
          {
            this.dateTimeValue = line.replaceFirst("<TD class=\"table_numbers\" nowrap>", "").replaceFirst("</TD>","");
            sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            this.dateTimeValueAsDate = sdf.parse(this.dateTimeValue.trim());
            dateTimeFound = true;
          }
      line = m_br.readLine();
    }


    //------------Searching for Companies-----------
    while (line!= null)
    {

      String serchforSybmoleBy = "<A href=\"/wps/portal/!ut/p/_s.7_0_A/";
      int indexToSymbol = line.toLowerCase().indexOf(serchforSybmoleBy.toLowerCase()) ;
      if ( indexToSymbol !=-1)
      {
        indexToSymbol = line.toLowerCase().indexOf("symbol=");
        if ( indexToSymbol !=-1)
        {
        indexToSymbol = indexToSymbol + "symbol=".length();
        // indexToSymbol = indexToSymbol + serchforSybmoleBy.length();
        symbols.addElement(line.substring(indexToSymbol, indexToSymbol +4));
        //-------Reading symbole Data----
          //---Symbol company name ----
          String companyName = m_br.readLine().trim().replaceAll("</A></TD>","").replaceAll(">","");
          companies.addElement(companyName);
          //---Symbol Price ----
          //line = m_br.readLine();  // bank Name become in a seperate line
          String price  = m_br.readLine().trim().replaceAll("<TD class=\"table_numbers\" height=\"18\">","").replaceAll("</TD>","");
          prices.addElement(price);
          //----Last Trade Volume
          String last_trade_vol = m_br.readLine().trim().replaceAll("<TD class=\"table_numbers\" height=\"18\">","").replaceAll("</TD>","");
          this.last_trade_vols.addElement(last_trade_vol);
          
          //--------Ignore Change Value %--
          m_br.readLine();
          //--------Ignore  Percentage %--
          m_br.readLine();
          
          //--- no. of trade ----
          String no_of_trades  = m_br.readLine().trim().replaceAll("<TD class=\"table_numbers\" height=\"18\">","").replaceAll("</TD>","");
          this.no_of_tradess.addElement(no_of_trades);
          
          //---Volume Tradede ----
          String volume_traded  = m_br.readLine().trim().replaceAll("<TD class=\"table_numbers\" height=\"18\">","").replaceAll("</TD>","");
          this.volume_tradeds.addElement(volume_traded);
          
          //---best_bid_prices ----
          String best_bid_price  = m_br.readLine().trim().replaceAll("<TD class=\"table_numbers\" height=\"18\">","").replaceAll("</TD>","");
          this.best_bid_prices.addElement(best_bid_price);

          //---best_bid_volumes ----
          String best_bid_volume  = m_br.readLine().trim().replaceAll("<TD class=\"table_numbers\" height=\"18\">","").replaceAll("</TD>","");
          this.best_bid_volumes.addElement(best_bid_volume);

          //---best_bid_volumes ----
          String best_offer_price  = m_br.readLine().trim().replaceAll("<TD class=\"table_numbers\" height=\"18\">","").replaceAll("</TD>","");
          this.best_offer_prices.addElement(best_offer_price);
          
          //---best_bid_volumes ----
          String best_offer_volumn  = m_br.readLine().trim().replaceAll("<TD class=\"table_numbers\" height=\"18\">","").replaceAll("</TD>","");
          this.best_offer_volumns.addElement(best_offer_volumn);
          
          /*
           <TD class="bold_text_center" height="18">Open</TD>
					<TD class="bold_text_center" height="18">High</TD>
					<TD class="bold_text_center" height="18">Low</TD>
          */
          //-- The following are today summary values from tadawl (better than calculating it locallay) 
          //---today_open_value ----
          String today_open_value  = m_br.readLine().trim().replaceAll("<TD class=\"table_numbers\" height=\"18\">","").replaceAll("</TD>","");
          this.today_open_values.addElement(today_open_value);
          //---today_high_value ----
          String today_high_value  = m_br.readLine().trim().replaceAll("<TD class=\"table_numbers\" height=\"18\">","").replaceAll("</TD>","");
          this.today_high_values.addElement(today_high_value);
          //---today_low_value ----
          String today_low_value   = m_br.readLine().trim().replaceAll("<TD class=\"table_numbers\" height=\"18\">","").replaceAll("</TD>","");          
          this.today_low_values.addElement(today_low_value);
          
        }     
      }
      line = m_br.readLine();

    }
    //--Construct upper and lower mail body for each company to be ready to be used by SymboleAlert Class
    // Did it only once...
    if (! this.mailsConstructed)
    {
      for (int i=0; i<this.symbols.size() ; i++)
      {
       String upper = Misc.repalceAll(this.upperLimtMailTemplate, "$$symbole",this.symbols.elementAt(i).toString());
       upper= Misc.repalceAll(upper, "$$companyName",this.companies.elementAt(i).toString());
       this.upperLimitAlertMailBodies.addElement(upper);

       String lower = Misc.repalceAll(this.lowerLimtMailTemplate, "$$symbole",this.symbols.elementAt(i).toString());
       lower= Misc.repalceAll(lower, "$$companyName",this.companies.elementAt(i).toString());
       this.lowerLimitAlertMailBodies.addElement(lower);
     
       String lowerSubject = Misc.repalceAll(this.lowerLimitAlertMailSubject, "$$symbole",this.symbols.elementAt(i).toString());
       lowerSubject= Misc.repalceAll(lowerSubject, "$$companyName",this.companies.elementAt(i).toString());
       this.lowerLimitAlertMailSubjects.addElement(lowerSubject);
       
       String upperSubject = Misc.repalceAll(this.upperLimitAlertMailSubject, "$$symbole",this.symbols.elementAt(i).toString());
       upperSubject= Misc.repalceAll(upperSubject, "$$companyName",this.companies.elementAt(i).toString());
       this.upperLimitAlertMailSubjects.addElement(upperSubject);
       
       //this.upperLimitAlertMailSubjects.addElement(this.upperLimitAlertMailSubject.replaceAll("$$symbole",this.symbols.elementAt(i).toString()));
       // this.lowerLimitAlertMailSubjects.addElement(this.lowerLimitAlertMailSubject.replaceAll("$$symbole",this.symbols.elementAt(i).toString()));
      }
      this.mailsConstructed = true; // do not do it again
    }
  }
  public void extractDataDirectly() throws Exception
  {
    Support.DownLoad.URLDownLoader urldown = new Support.DownLoad.URLDownLoader();
    BufferedReader br = urldown.getBufferreader( this.tdwlAllCompsURL 
                            , "proxy-dr" , 8080 , "" , "")    ;
    this.extractDataFromBufferReader(br);
    br.close();
  }
 public void extractData() throws Exception
 {
    Support.DownLoad.URLDownLoader urldown = new Support.DownLoad.URLDownLoader();
    
    SimpleDateFormat df  = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
    String dateTimeStamp = df.format(new Date());
    String filePath  = "D:/StockInfo/"+dateTimeStamp + ".html";
      
    urldown.downLoad( tdwlAllCompsURL
              ,  filePath
              , "proxy-dr" , 8080 , "" , "");
  
    File sdFile= new File(filePath);
    BufferedReader br =   new BufferedReader( new FileReader(sdFile));
    this.extractDataFromBufferReader(br);
    br.close();
 
  }

public void storeDatainDb() throws Exception 
{
  Statement stmt = con.createStatement();
  if (this.symbols.size() != this.prices.size() || this.prices.size() != this.companies.size()) 
  {
    throw new Exception("Invalid Data");
  }
  //System.out.println("Size = " + this.symbols.size());

  //------- Storing The index
  insertNewInfo = "insert into  stock_info (symbol , price , price_time ) values ('0000' , '"+this.indexValue.trim().replaceAll(",","")+"' , to_date('"+this.dateTimeValue.trim()+"','yyyy/mm/dd hh24:mi:ss') )";
  try{
      stmt.execute(insertNewInfo);
     }
  catch (Exception e) {throw new Exception (e.getMessage()+ "...." + insertNewInfo);}
  
  this.updateSummaryinfo(0);

  StocKInfoExtractor sie2 =  this.cloneMe() ;//new StocKInfoExtractor(this.con);
  
  NewValuesDealer newValueDealer = new NewValuesDealer(sie2,-1, this.sendSMS);
  newValueDealer.start();
  //this.con.commit();
  
   //----------Storing Companies info-----


  for (int i = 0 ; i< this.symbols.size(); i++)
  {
  try{
      insertNewInfoPS.setString(1,this.symbols.elementAt(i).toString());
      insertNewInfoPS.setString(2,this.prices.elementAt(i).toString().replaceAll(",",""));  
      insertNewInfoPS.setString(3,this.last_trade_vols.elementAt(i).toString().replaceAll(",",""));  
      insertNewInfoPS.setString(4,this.no_of_tradess.elementAt(i).toString().replaceAll(",",""));
      insertNewInfoPS.setString(5,this.volume_tradeds.elementAt(i).toString().replaceAll(",",""));  
      insertNewInfoPS.setString(6,this.best_bid_prices.elementAt(i).toString().replaceAll(",",""));  
      insertNewInfoPS.setString(7,this.best_bid_volumes.elementAt(i).toString().replaceAll(",",""));
      insertNewInfoPS.setString(8,this.best_offer_prices.elementAt(i).toString().replaceAll(",",""));
      insertNewInfoPS.setString(9,this.best_offer_volumns.elementAt(i).toString().replaceAll(",",""));
      insertNewInfoPS.setTimestamp(10,new Timestamp(this.dateTimeValueAsDate.getTime()));
      insertNewInfoPS.execute();
      //System.out.println("Symbole " + this.symbols.elementAt(i).toString() + " New Info Sored in DB" );
    }
    catch (Exception e) {
    throw new Exception (e.getMessage()+ "...." + insertNewInfo);
    }
    //
    try{
    this.updateSummaryinfo(i);
    }
    catch (Exception e){System.out.println ("Unable to Update Summary info Due to " + e.getMessage() );}


    //--------This Section is to Deal with the new values
    //--1- Recalculate the summary info
    //--2- send email and sms alerts if new values fire the alerts stored in db 
    
    // StocKInfoExtractor sie3 = this;
    newValueDealer = new NewValuesDealer(sie2,i, sie2.sendSMS);
    newValueDealer.start();
    //this.con.commit();
    //System.out.println("Thread Name = " + newValueDealer.getName() + "to Deal with its new Value,  Thread Periority " + newValueDealer.getPriority());
  }

}

 public void updateSummaryinfo( int m_symbolIndex) throws Exception
 {
    //Delete Summary Info for this symbol (if exist)
    try
    {
     deletTodaySummaryPS.setString(1, this.symbols.elementAt(m_symbolIndex).toString());
     deletTodaySummaryPS.setTimestamp(2, new Timestamp(this.dateTimeValueAsDate.getTime()));
     deletTodaySummaryPS.execute();
    }
    catch (Exception e) {
    throw new Exception (e.getMessage()+ "...." + deleteTodaySummary);
    }

    //Insert New Summary Info for this symbol (if exist)
    try
    {
//   insertTodaySummary = "insert into summary_info (symbole, working_day, open_value, close_value, min_value, max_value, volume_traded)"
     insertTodaySummaryPS.setString(1, this.symbols.elementAt(m_symbolIndex).toString());
     insertTodaySummaryPS.setDate(2, new java.sql.Date( this.dateTimeValueAsDate.getTime()));// new Timestamp(this.dateTimeValueAsDate.getTime()));
     insertTodaySummaryPS.setString(3, this.today_open_values.elementAt(m_symbolIndex).toString());
     insertTodaySummaryPS.setString(4, this.prices.elementAt(m_symbolIndex).toString().replaceAll(",",""));
     insertTodaySummaryPS.setString(5, this.today_low_values.elementAt(m_symbolIndex).toString().replaceAll(",",""));
     insertTodaySummaryPS.setString(6, this.today_high_values.elementAt(m_symbolIndex).toString().replaceAll(",",""));
     insertTodaySummaryPS.setString(7, this.volume_tradeds.elementAt(m_symbolIndex).toString().replaceAll(",",""));
     insertTodaySummaryPS.execute();
    }
    catch (Exception e) {
    throw new Exception (e.getMessage()+ "...." + insertTodaySummary);
    }
 
 }
  
  private String readFile( String fileName)
  {
   String fileContents = "";
   try{
   ClassLoader c = Class.forName("Support.Stock.StocKInfoExtractor").getClassLoader();
   URL url  = Class.forName("Support.Stock.StocKInfoExtractor").getClassLoader().getResource("Support/Stock/"+fileName);    
   InputStream is = url.openStream();
   int ch = is.read();
   while(ch!= -1)
    {
     fileContents +=(char)(ch);
     ch = is.read();
    }
   is.close();
   }
   catch (Exception e) {System.out.println("Unable to find Html template file Support/Stock/"+fileName + "\n due to " + e.getMessage());}
   return fileContents;
    
  }

  public static void main(String[] args) throws Exception
  {
     Date startTime = new Date();
     Support.XMLConfigFileReader supportConfig =  Misc.getXMLConfigFileReader(false ) ;  
     Connection con = supportConfig.reposatoryConn.generateConnection();

     
     int  durationInMin = Integer.parseInt(args[0]);
     double intervalInMin = Double.parseDouble(args[1]);  // Interval Between data captures
     Date endTime = new Date ( startTime.getTime() +  durationInMin*60*1000);
     System.out.println("=======Starting Capture Stock Info Data from Tadaul Site ========");
     System.out.println("DataBase Used " + con.getMetaData().getURL() );
     System.out.println("Process will finish By " + endTime );
 
     int counter = 1;
   
     StocKInfoExtractor sie =  new StocKInfoExtractor(con);
     while( ! new Date().after(endTime))
     { 
       Date date1 = new Date();
       System.out.println(new Date() + ": Satrting data capture No." + counter );
       try{
          sie.extractDataDirectly();
          Date date2 = new Date(); long  period21 = date2.getTime() - date1.getTime();
          System.out.println("\t" + new Date() + "Data extracted from Tadawl Successfully in "+ period21/1000 + " Sec" );

          // The following will store new value in DB and invoke another threads (a new thread with a new db connection for each company) to deal with the changes
          sie.setSendSMS(true);
          sie.storeDatainDb(); 
          Date date3 = new Date(); long  period32 = date3.getTime() - date2.getTime();
          System.out.println("\t" + new Date() + "Data Stored locally successfully in "+ period32/1000 + " Sec" );
          
          con.commit();
       }
       catch (Exception e) 
       { 
        try{ //-- try to reconstruct the connection
          con = supportConfig.reposatoryConn.generateConnection();
         } catch (Exception ex){}
       System.out.println("unable to extract Data from Tadual Due to" + "\n" + e.getMessage());
       }
       System.out.println(new Date() + ": End data capture No." + counter);
       sie.sleep( (long) (intervalInMin*1000*60));
       counter++;
     }
    sie.insertNewInfoPS.close();
    sie.deletTodaySummaryPS.close();
    sie.insertTodaySummaryPS.close();
    con.close();
  }
public StocKInfoExtractor cloneMe()
{
  StocKInfoExtractor x = new StocKInfoExtractor();
  x.con = this.con;
  x.best_bid_prices = this.best_bid_prices;
  x.best_bid_volumes= this.best_bid_volumes;
  x.best_offer_prices = this.best_offer_prices;
  x.best_offer_volumns = this.best_offer_volumns;
  x.companies =  this.companies ;
  x.dateTimeValue = this.dateTimeValue;
  x.dateTimeValueAsDate= this.dateTimeValueAsDate;
  x.deleteTodaySummary = this.deleteTodaySummary;
  x.deletTodaySummaryPS = this.deletTodaySummaryPS;
  x.indexValue = this.indexValue;
  x.insertNewInfo = this.insertNewInfo;
  x.insertNewInfoPS = this.insertNewInfoPS;
  x.last_trade_vols = this.last_trade_vols;
  x.lowerLimitAlertMailBodies = this.lowerLimitAlertMailBodies;
  x.lowerLimitAlertMailSubject = this.lowerLimitAlertMailSubject;
  x.lowerLimitAlertMailSubjects  = this.lowerLimitAlertMailSubjects;
  x.lowerLimtMailTemplate = this.lowerLimtMailTemplate;
  x.mailsConstructed = this.mailsConstructed;
  x.no_of_tradess = this.no_of_tradess;
  x.prices = this.prices;
  x.sendSMS = this.sendSMS;
  x.symbols = this.symbols;
  x.tdwlAllCompsURL = this.tdwlAllCompsURL;
  x.today_high_values = this.today_high_values;
  x.today_low_values = this.today_low_values;
  x.today_open_values = this.today_open_values;
  x.upperLimitAlertMailBodies = this.upperLimitAlertMailBodies;
  x.upperLimitAlertMailSubject = this.upperLimitAlertMailSubject;
  x.upperLimitAlertMailSubjects =this.upperLimitAlertMailSubjects;
  x.upperLimtMailTemplate = this.upperLimtMailTemplate;
  x.volume_tradeds = this.volume_tradeds;
  
  return x;
  
}
}