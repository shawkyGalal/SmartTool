package Support.DownLoad;
//import oracle.soap.transport.http.OracleSOAPHTTPConnection;
import Support.*;
import java.net.*;
import java.io.*;
import java.text.*;
import java.util.*;
//import org.apache.soap.rpc.*;

public class URLDownLoader 
{
  public URLDownLoader() throws Exception
  {
    Validator1.checkExpiry();
  }
  public BufferedReader getBufferreader(String fromUrl // The URL which needed to be downloaded 
                              , String proxyServer //
                              , int proxyPort
                              , String userName // the user name & password for the HTTP server Or Proxy server if exists
                              , String password ) throws Exception
  
  {
     //---------Passing through the Proxy Server---------------
   System.setProperty("https.proxyHost",proxyServer);
   System.setProperty("https.proxyPort",String.valueOf(proxyPort));
   System.setProperty("http.proxyHost",proxyServer);
   System.setProperty("http.proxyPort",String.valueOf(proxyPort));

   MyAuthenticator  auth = null;
   auth = new MyAuthenticator(userName, password );
   Authenticator.setDefault(auth);
   BufferedReader br = null;
   //PrintWriter pw = null;
   InputStream is = null;
   try{
   
   URL url = new URL(fromUrl);
   URLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
   
   is =    httpUrlConn.getInputStream();
   
   InputStreamReader isr = new InputStreamReader(is);
   br = new BufferedReader(isr);
   }
   catch (Exception e) { throw new Exception("Unable to open " + fromUrl + " due to " + e.getMessage());}
   return br;
  }
  public static void  main(String[] arg) throws Exception
  {
    URLDownLoader abc = new URLDownLoader();
    if ( arg.length < 2 )
    {
      System.out.println("Usage");
      System.out.println("URLDownLoader fromUrlPath toFileNamePath");
      System.exit(0);
    }
    SimpleDateFormat df  = new SimpleDateFormat("dd-MM-yyyy HH-mm");
    String dateTimeStamp = df.format(new Date());

    try 
    {
    
      abc.downLoad(  arg[0] //"http://130.1.2.76:8988/SAMA_Web/bill_Data_prepare.jsp"// "https://tabadul.alrajhibank.com.sa/main/download.asp" //"http://www.hp.com/sbso/index_evo.html?jumpid=ex_R295_go/business-evo/compaqservices" //"http://edge9ias:7777/complaints/Complaints_attchments/1015/CMS_Shawky.pep" "http://ncci2001/pls/edgedev/training.main", 
                         , arg[1]+dateTimeStamp +".xml"  //"c:\\temp\\renewal_Data "
                         , ""   //130.1.2.111
                         , 8080
                         , "tabadul/555-001","123456");
    System.out.println("URL Downloaded Successuly to " + arg[1]+dateTimeStamp +".xml" );                     
    }
    catch (Exception e) {   System.out.print(e.getMessage());}
  }
  //------------------------------------------------------------------
 
  public StringBuffer  getURLContents( String fromUrl // The URL which needed to be downloaded 
          , String proxyServer //
          , int proxyPort
          , String userName // the user name & password for the HTTP server Or Proxy server if exists
          , String password ) throws Exception
  {
	//StringBuffer allData = null ; 
	StringBuffer allData = new StringBuffer("");
	
	  //------By Shawky Foda----
	  //------Date 10-1-2004----------
	  //--Description : This method tries to Open a given URl and download its contents to a file System----
	  
	  //--Check if Secure HTTPS Or Not
	  if (fromUrl.toUpperCase().startsWith("HTTPS"))
	  {
	    System.setProperty("javax.net.ssl.keyStore","C:\\jdev10g\\jdk\\bin\\keystore.jks");
	    System.setProperty("javax.net.ssl.keyStorePassword","changeit");
	    System.setProperty("javax.net.ssl.trustStore","C:\\jdev10g\\jdk\\bin\\cacerts.jks");
	    System.setProperty("javax.net.ssl.trustStorePassword","changeit");
	  }
	  
	  //---------Passing through the Proxy Server---------------
	   System.setProperty("https.proxyHost",proxyServer);
	   System.setProperty("https.proxyPort",String.valueOf(proxyPort));
	   System.setProperty("http.proxyHost",proxyServer);
	   System.setProperty("http.proxyPort",String.valueOf(proxyPort));

	   MyAuthenticator  auth = null;
	   auth = new MyAuthenticator(userName, password );
	   Authenticator.setDefault(auth);
	   BufferedReader br = null;

	   InputStream is = null;
	try{
	   
	   URL url = new URL(fromUrl);
	   URLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
	   
	   is =    httpUrlConn.getInputStream();
	   
	   InputStreamReader isr = new InputStreamReader(is);
	   br = new BufferedReader(isr);
	   String lineData= ""; 

	   while( ( lineData= br.readLine()) != null ) 
	    { 
	      if (! lineData.equals("")) //---------To avoid new empty lines
	      {
	       allData  = allData.append(lineData+"\n");// +  "\n"  + lineData ;
	      }
	    }

	   } 
	   catch (Exception e) 
	   { 
		 e.printStackTrace() ; 
	     throw e ; //new Exception("Unable To Down Load From " + fromUrl + " To Path " + toPath + ": "+e.getMessage() );
	   }
	   finally
	   {   
	    if (is != null ) {is.close();}
	    if (br != null ) {br.close();}
	   }
	
	return allData ; 
  }
  public void downLoad( String fromUrl // The URL which needed to be downloaded 
                              , String toPath  // the Path to Which the down loaded url will be saved 
                              , String proxyServer //
                              , int proxyPort
                              , String userName // the user name & password for the HTTP server Or Proxy server if exists
                              , String password ) throws Exception
  {
	  StringBuffer allData = null ;  
	  allData = getURLContents(fromUrl , proxyServer , proxyPort , userName , password ) ;
	  PrintWriter pw = null;	  
	  try {
		   //----------wirite To File System
		   pw = new PrintWriter(new BufferedWriter(new FileWriter(toPath)));
		   pw.write(allData.toString());    

	  }
	   catch (Exception e) 
	   {
	     throw e ; //new Exception("Unable To Down Load From " + fromUrl + " To Path " + toPath + ": "+e.getMessage() );
	   }
	   finally
	   {   
	    if (pw != null ) {pw.close();}
	   }

  }
}