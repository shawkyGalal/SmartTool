package SADAD.TRANSFERS;
//import oracle.soap.transport.http.OracleSOAPHTTPConnection;
import java.net.*;
import java.io.*;
import java.text.*;
import java.util.*;
//import org.apache.soap.rpc.*;

public class URLDownLoader 
{
  public static void URLDownLoader()
  {
   
  }
  public static void  main(String[] arg)
  {
    URLDownLoader abc = new URLDownLoader();
    SimpleDateFormat df  = new SimpleDateFormat("dd-MM-yyyy HH-mm");
    String dateTimeStamp = df.format(new Date());
    try {
    abc.downLoad(  "http://130.1.2.76:8988/SAMA_Web/bill_Data_prepare.jsp"// "https://tabadul.alrajhibank.com.sa/main/download.asp" //"http://www.hp.com/sbso/index_evo.html?jumpid=ex_R295_go/business-evo/compaqservices" //"http://edge9ias:7777/complaints/Complaints_attchments/1015/CMS_Shawky.pep" "http://ncci2001/pls/edgedev/training.main", 
                         , "c:\\temp\\renewal_Data "+dateTimeStamp +".xml"
                         , "130.1.2.111"
                         , 8080
                         , "555-001","1234567");
    }
    catch (Exception e) {   System.out.print(e.getMessage());}
  }
  //------------------------------------------------------------------
 
  
  public void downLoad( String fromUrl // The URL which needed to be downloaded 
                              , String toPath  // the Path to Which the down loaded url will be saved 
                              , String proxyServer //
                              , int proxyPort
                              , String userName // the user name & password for the HTTP server Or Proxy server if exists
                              , String password ) throws Exception
  {
  //------By Shawky Foda----
  //------Date 10-1-2004----------
  //--Description : This method tries to Open a given URl and download its contents to a file System----

  
  //---------Passing through the Proxy Server---------------
   System.setProperty("https.proxyHost",proxyServer);
   System.setProperty("https.proxyPort",String.valueOf(proxyPort));
   System.setProperty("http.proxyHost",proxyServer);
   System.setProperty("http.proxyPort",String.valueOf(proxyPort));

   MyAuthenticator  auth = null;
   auth = new MyAuthenticator(userName, password );
   Authenticator.setDefault(auth);
   BufferedReader br = null;
try{
   
   URL url = new URL(fromUrl);
   URLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
   
   InputStream is =    httpUrlConn.getInputStream();
   
   InputStreamReader isr = new InputStreamReader(is);
   br = new BufferedReader(isr);
   String lineData= ""; 
   StringBuffer allData = new StringBuffer("");
   while( ( lineData= br.readLine()) != null ) 
    {   
      allData  = allData.append("\n" + lineData);// +  "\n"  + lineData ;
    }
   //----------wirite To File System
   PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(toPath)));
   pw.write(allData.toString());    
   pw.close();
   is.close();
   br.close();

   //--------passing INto the Rajhi Bank HTTP Server------
   auth  = new MyAuthenticator(userName, password );
   Authenticator.setDefault(auth);
   
   url = new URL(fromUrl);
   httpUrlConn = (HttpURLConnection) url.openConnection();
   is =    httpUrlConn.getInputStream();
   isr = new InputStreamReader(is);

   br = new BufferedReader(isr);
   lineData= ""; 
   allData = new StringBuffer("");
   while( ( lineData= br.readLine()) != null ) 
    {   
      allData  = allData.append("\n" + lineData);// +  "\n"  + lineData ;
    }
   //----------wirite To File System
   pw = new PrintWriter(new BufferedWriter(new FileWriter(toPath)));
   pw.write(allData.toString());    
   //----------Closing The Streams--------
   pw.close();
   is.close();
   br.close();
   }
   catch (Exception e) 
   {
    if (br!= null)
    {
      try{
            br.close();
          } catch(Exception ex){}
    } 
    throw e ; //new Exception("Unable To Down Load From " + fromUrl + " To Path " + toPath + ": "+e.getMessage() );
   }
  }
}