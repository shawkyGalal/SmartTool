package Support.DownLoad;
import Support.*;
import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;

public class StocKInfoExtractor2 extends Thread
{
  String dateTimeValue = "";
  Vector symbols = new Vector();
  Vector companies = new Vector();
  Vector prices = new Vector();
  Vector last_trade_vols = new Vector();  
  Vector no_of_tradess = new Vector();
  Vector volume_tradeds = new Vector();
  Vector best_bid_prices = new Vector();
  Vector best_bid_volumes = new Vector();
  Vector best_offer_prices = new Vector();
  Vector best_offer_volumns = new Vector();
  String indexValue = "";
  
  public StocKInfoExtractor2()
  {
  }
  public void extractDataFromBufferReader(BufferedReader m_br) throws Exception
  {
    String line = m_br.readLine();
    boolean indexFound = false;
    boolean dateTimeFound = false;

    //-------The following today Date will be used to get exact current time
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    String todayDate = sdf.format(new Date());

    //-----------Searching for Index------
    while (line!= null && !indexFound)
      {
        if (!indexFound )
        {
          String searchForIndexBy  = "<A href=\"/wps/portal/!ut/p/_s.7_0_A/7_0_49S\">TASI";
          int indexToTASI = line.toLowerCase().indexOf(searchForIndexBy.toLowerCase()) ;
          if (indexToTASI != -1) //-------if index found
          {
            m_br.readLine(); //----------There is a line contains "</A></TD>"
            indexValue = m_br.readLine().replaceFirst("<TD class=\"table_numbers\" nowrap>", "").replaceFirst("</TD>","");
            indexFound = true;
          }
        }
       line = m_br.readLine();
      }
    //-----Searching for Date ---
    while (line!= null && !dateTimeFound)
    {
      int indexToDate = line.toLowerCase().indexOf(todayDate.toLowerCase()) ;
          if (indexToDate != -1) //-------if index found
          {
            dateTimeValue = line.replaceFirst("<TD class=\"table_numbers\" nowrap>", "").replaceFirst("</TD>","");
            dateTimeFound = true;
          }
      line = m_br.readLine();
    }
    //------------Searching for Companies-----------
    while (line!= null)
    {

      String serchforSybmoleBy = "<A href=\"/wps/portal/!ut/p/_s.7_0_A/7_0_4BC?tabOrder=1&amp;symbol=";
      int indexToSymbol = line.toLowerCase().indexOf(serchforSybmoleBy.toLowerCase()) ;
      if ( indexToSymbol !=-1)
      {
        indexToSymbol = indexToSymbol + serchforSybmoleBy.length();
        symbols.addElement(line.substring(indexToSymbol, indexToSymbol +4));
        //-------Reading symbole Data----
          //---Symbol company name ----
          String companyName = m_br.readLine().trim().replaceAll("</A></TD>","").replaceAll(">","");
          companies.addElement(companyName);
          //---Symbol Price ----
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
          
     
      }
      line = m_br.readLine();
    }
  }
  public void extractDataDirectly() throws Exception
  {
    Support.DownLoad.URLDownLoader urldown = new Support.DownLoad.URLDownLoader();
    BufferedReader br = urldown.getBufferreader( "http://www.tadawul.com.sa/wps/portal/!ut/p/.cmd/cs/.ce/7_0_A/.s/7_0_4AI/_s.7_0_A/7_0_4AI/.cmd/ChangeLanguage/.l/en"
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
      
    urldown.downLoad( "http://www.tadawul.com.sa/wps/portal/!ut/p/.cmd/cs/.ce/7_0_A/.s/7_0_4AI/_s.7_0_A/7_0_4AI/.cmd/ChangeLanguage/.l/en"
              ,  filePath
              , "proxy-dr" , 8080 , "" , "");
  
    File sdFile= new File(filePath);
    BufferedReader br =   new BufferedReader( new FileReader(sdFile));
    this.extractDataFromBufferReader(br);
    br.close();
 
  }

public void storeDatainDb(Connection con ) throws Exception 
{
  Statement stmt = con.createStatement();
  if (this.symbols.size() != this.prices.size() || this.prices.size() != this.companies.size()) 
  {
    throw new Exception("Invalid Data");
  }
  String insertStmt = "";
  
  //------- Storing The index
  insertStmt = "insert into stock_Info (symbol , price , price_time ) values ('0000' , '"+this.indexValue.trim().replaceAll(",","")+"' , to_date('"+this.dateTimeValue.trim()+"','yyyy/mm/dd hh24:mi:ss') )";
  try{
      stmt.execute(insertStmt);
     }
  catch (Exception e) {throw new Exception (e.getMessage()+ "...." + insertStmt);}
  
  //----------Storing Companies info-----
  for (int i = 0 ; i< this.symbols.size(); i++)
  {

    insertStmt = "insert into stock_Info (symbol , price , last_trade_vols , no_of_trades , volume_traded ,best_bid_price , best_bid_volume , best_offer_price , best_offer_volumn, price_time) values ("
                                        + "'"+this.symbols.elementAt(i)+"'"
                                        +",'"+this.prices.elementAt(i).toString().replaceAll(",","")+"'"
                                        +",'"+this.last_trade_vols.elementAt(i).toString().replaceAll(",","")+"'"
                                        +",'"+this.no_of_tradess.elementAt(i).toString().replaceAll(",","")+"'"
                                        +",'"+this.volume_tradeds.elementAt(i).toString().replaceAll(",","")+"'"
                                        +",'"+this.best_bid_prices.elementAt(i).toString().replaceAll(",","")+"'"
                                        +",'"+this.best_bid_volumes.elementAt(i).toString().replaceAll(",","")+"'"
                                        +",'"+this.best_offer_prices.elementAt(i).toString().replaceAll(",","")+"'"
                                        +",'"+this.best_offer_volumns.elementAt(i).toString().replaceAll(",","")+"'"
                                        +", to_date('"+this.dateTimeValue.trim()+"','yyyy/mm/dd hh24:mi:ss')"
                                        +")";
    
    try{
    stmt.execute(insertStmt);
    }
    catch (Exception e) {throw new Exception (e.getMessage()+ "...." + insertStmt);}
  }
}
  
  public static void main(String[] args) throws Exception
  {
     Date startTime = new Date();
     int  durationInMin = Integer.parseInt(args[0]);
     double intervalInMin = Double.parseDouble(args[1]);  // Interval Between data captures
     Date endTime = new Date ( startTime.getTime() +  durationInMin*60*1000);
     System.out.println("Process will finish By " + endTime );
     Connection con = ConnectionFactory.createConnection_old("10.16.17.53","Sadad", "support" , "support");     
    int counter = 1;
     while( ! new Date().after(endTime))
     { 
       System.out.println(new Date() + ": Satrting data capture No." + counter );
       StocKInfoExtractor2 sie = new StocKInfoExtractor2();
       sie.extractDataDirectly();
       sie.storeDatainDb(con);
       System.out.println(new Date() + ": End data capture No." + counter);
       sie.sleep( (long) (intervalInMin*1000*60));
       counter++;
     }
         
    con.close();
  }
}