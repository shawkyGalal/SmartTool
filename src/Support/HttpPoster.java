package Support;


import Support.DownLoad.*;
import java.util.Hashtable;

import javax.net.ssl.HttpsURLConnection;

import java.io.*;
import java.net.*;

public class HttpPoster 
{
  private HttpURLConnection con = null;
  private String proxyServer = "proxy-mn";
  //private String keyStoreFileName = "";
  //private String trustStoreFileName= "";
  private String userName="";
  private String password="";
  private String contentType = "text/xml" ;
  private Hashtable<String , String> requestHeaders = new Hashtable<String , String>(); 
  private Hashtable<String , String> requestQueryParams = new Hashtable<String , String>(); 
  private String requestMethod = "POST" ; 
  
  public HttpPoster(String urlString , String m_proxyServer , String m_userName , String m_password) throws Exception
  {
	  int count = 0 ; 
	   for (String querParam : this.getRequestQueryParams().keySet())
	   {
		   urlString +=((count==0)? "?" : "&") +querParam+"="+this.getRequestQueryParams().get(querParam);
		   count +=1; 
	   }
	   URL url = new URL(urlString) ;
	   this.userName = m_userName;
	   this.password = m_password;
	   this.proxyServer = m_proxyServer;
	   
	   try {
	 	    setCommParms(this.proxyServer,8080);
			con = (HttpURLConnection) url.openConnection();
		    HttpURLConnection.setFollowRedirects(true);
		    con.setInstanceFollowRedirects(false);
		    con.connect();
		    /* Following the redirection 
		    while (String.valueOf(con.getResponseCode()).startsWith("3")) {
		       String theLocation = con.getHeaderField("Location");
		       con.disconnect();
		       url = new URL(theLocation);
		       con = (HttpURLConnection) url.openConnection();
		       con.setFollowRedirects(true);
		       con.setInstanceFollowRedirects(false);
		       con.connect();
		       }
		       */
		       /** at this point you are located at the last(target)page of
		           redirection chain */
	   	} 
	   catch (Exception ex) 
	   { ex.printStackTrace(); }
	  	//trustAllCerts();    
	   con = (HttpURLConnection) url.openConnection();
	   con.setDoInput(true);
	   con.setDoOutput(true);///	  	
	  
	  	
	  	//getSiteCertificates(url.toString(),"",0);

   
   }

  
 
    private void setCommParms( String proxyServer , int proxyPort )
{
   //---------Passing through the Proxy Server---------------
   System.setProperty("https.proxyHost",proxyServer);
   System.setProperty("https.proxyPort",String.valueOf(proxyPort));
   System.setProperty("http.proxyHost",proxyServer);
   System.setProperty("http.proxyPort",String.valueOf(proxyPort));
   
   //---------The following lines used for sending a certificate in case of client authentication required-----
   //System.setProperty("javax.net.ssl.keyStore","C:\\jdev10g\\jdk\\bin\\SADAD\\NCCI_Server.jks");
   System.setProperty("javax.net.ssl.keyStorePassword","changeit");
   //--------To trust certificate of the site to be downloaded should be included in the java key store file cacers.jks 
   //-------You can add another certificate using the NCCIKeyStore Class
   
   //System.setProperty("javax.net.ssl.trustStore","C:\\jdev10g\\jdk\\bin\\SADAD\\SAMATrustedCAs.jks");  //
   System.setProperty("javax.net.ssl.trustStorePassword","changeit");
    
  }
 /*
  private void trustAllCerts()
  {
    // Create a trust manager that does not validate certificate chains
    TrustManager[] trustAllCerts = new TrustManager[]{
        new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(
                java.security.cert.X509Certificate[] certs, String authType) {
                String x= "";
            }
            public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)throws CertificateException 
            {
                FileInputStream x;
                try{
                x = new FileInputStream("C:\\jdev10g\\jdk\\bin\\SADAD\\SAMATrustedCAs.jks");
                char[] pass = new String("changeit").toCharArray(); //new String("changeit").toCharArray();
                NCCIKeyStore ncciKeyStore = new NCCIKeyStore(x,pass );
                ncciKeyStore.clearContents();
                for (int i = 0 ; i< certs.length ; i++)
                {
                  ncciKeyStore.addCertificate(certs[i], "alias"+i);
                }
                ncciKeyStore.writeToJKSFile("C:\\jdev10g\\jdk\\bin\\SADAD\\SAMATrustedCAs.jks",pass);
                }
                catch (Exception e){throw new CertificateException(e.getMessage());}
            }
        }
    };
    
    // Install the all-trusting trust manager
    try {
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    } catch (Exception e) {
    }

  }
  */
  public void postRequest(String xmlPostData) throws Exception
  {
    con.setRequestProperty("Content-length",String.valueOf(xmlPostData.length()));
    con.setRequestProperty("Content-type" ,this.getContentType() );
	for (String headerKey : this.getRequestHeaders().keySet())
	{
		con.setRequestProperty(headerKey,this.getRequestHeaders().get(headerKey));
	}
	con.setRequestMethod(this.getRequestMethod());
     
    OutputStream out = con.getOutputStream();
    out.write(xmlPostData.getBytes());
    out.flush();
  }
  public String readResponse() throws Exception
  {
  
    String httpResponse= "";
    MyAuthenticator  auth = null;
    auth = new MyAuthenticator(this.userName,this.password);
    Authenticator.setDefault(auth);
    InputStream in = con.getInputStream();
    int c= 0;
    while( (c=  in.read()) != -1 )
    {
      httpResponse+= (char)c;
    }
    return httpResponse;
  }
    private java.security.cert.Certificate[] getSiteCertificates(String fromUrl // The URL which needed to be downloaded 
                              , String proxyServer //
                              , int proxyPort ) throws Exception
  {
   //MyAuthenticator  auth = null;
   //auth = new MyAuthenticator("may","may2000");// "may","may2000");//userName, password );
   //Authenticator.setDefault(auth);
   
    java.security.cert.Certificate[] certs = null;
    try
    {
     URL url = new URL(fromUrl);
     HttpsURLConnection httpsUrlConn = (HttpsURLConnection) url.openConnection();
     httpsUrlConn.connect();
     //---------Getting the certificates of this site 
     certs = httpsUrlConn.getServerCertificates();
     httpsUrlConn.disconnect();
    }
    catch (Exception e) {e.printStackTrace(); throw e;
    }
    return certs;
  }

  public static void main(String[] args) 
  {
    try{
  
    String BINQRQ =  " <?xml version='1.0' encoding='UTF-8'?> "
  +" \n <SADAD> "
  +" \n   <SignonRq> "
  +" \n     <ClientDt>2005-07-04T15:45:47</ClientDt> "
  +" \n     <SignonProfile> "
  +" \n       <Sender>ABCDEF</Sender> "
  +" \n       <Receiver>SADAD</Receiver> "
  +" \n       <MsgCode>BINQRQ</MsgCode> "
  +" \n     </SignonProfile> "
  +" \n   </SignonRq> "
  +" \n   <PresSvcRq> "
  +" \n     <RqUID>55343acf-ead5-11d9-9e84-cfdc660aaaaa</RqUID> "
  +" \n     <BillInqRq> "
  +" \n       <AccessChannel>BTELLER</AccessChannel> "
  +" \n       <BillingAcct>1111001103829</BillingAcct> "
  +" \n       <BillerId>004</BillerId> "
  +" \n       <IncPayments>false</IncPayments> "
  +" \n       <IncPaidBills>false</IncPaidBills> "
  +" \n       <IncBillSummAmt>false</IncBillSummAmt> "
  +" \n       <IncExactPmt>true</IncExactPmt> "
  +" \n     </BillInqRq> "
  +" \n   </PresSvcRq> "
  +" \n </SADAD> " ;

    String PmtNotifyRq= "<SADAD>"
  +" \n <SignonRq>"
  +" \n  <ClientDt>2004-03-19T19:49:52</ClientDt>"
  +" \n<SignonProfile>"
  +" \n    <Sender>SADAD</Sender>"
  +" \n      <Receiver>003</Receiver>"
  +" \n    <MsgCode>PNOTRQ</MsgCode>"
  +" \n  </SignonProfile>"
  +" \n </SignonRq>"
  +" \n <BillerSvcRq>"
  +" \n  <RqUID>f81d4fae-7dec-11d0-a765-00a0c91e6bf6</RqUID>"
  +" \n  <PmtNotifyRq>"
  +" \n    <PmtRec>"
  +" \n      <PmtTransId>"
  +" \n        <PmtId>222</PmtId>"
  +" \n        <PmtIdType>SPTN</PmtIdType>"
  +" \n      </PmtTransId>"
  +" \n      <CustId>"
  +" \n        <OfficialId>5785687678697</OfficialId>"
  +" \n        <OfficialIdType>NAT</OfficialIdType>"
  +" \n      </CustId>"
  +" \n      <PmtStatus>"
  +" \n        <PmtStatusCode>PmtNew</PmtStatusCode>"
  +" \n        <EffDt>2004-03-18T19:49:52</EffDt>"
  +" \n      </PmtStatus>"
  +" \n      <PmtInfo>"
  +" \n        <CurAmt>1200</CurAmt>"
  +" \n        <PrcDt>2004-03-18T19:49:52</PrcDt>"
  +" \n        <DueDt>2004-04-18T19:49:52</DueDt> "
  +" \n        <BillCycle>Daily</BillCycle> "
  +" \n       <BillNumber>11111</BillNumber> "
  +" \n        <AccountId> "
  +" \n          <BillingAcct>003</BillingAcct> "
  +" \n          <BillerId>003</BillerId>" 
  +" \n        </AccountId> "
  +" \n          <BankId>SAMBA</BankId>"
  +" \n        <DistrictCode>1111</DistrictCode>"
  +" \n        <BranchCode>444</BranchCode>"
  +" \n        <AccessChannel>ATM</AccessChannel> "
  +" \n        <PmtMethod>cash</PmtMethod> "
  +" \n        <PmtRefInfo>This paymnet regarding  Policy No = 989898</PmtRefInfo> "
+" \n        </PmtInfo>"
+" \n      </PmtRec>"
+" \n    </PmtNotifyRq>"
+" \n  </BillerSvcRq>"
+" \n </SADAD>" ;

String BillConfirmationRq = "";
String billConfiemationRqURL = "http://10.16.18.181:8988/SADAD/servlet/SADAD.BillConfirmationReciver";

    String pushBillsURL = "https://ncci9iasp:5403/invoke/NCCISadad.BillUploadProcess:PushBillData";
    String pushPaymentURL = "https://130.1.2.30:5403/invoke/NCCISadad.PmtUploadRq:PushPaymentData";    
    String pmtNotifyURL = "http://130.1.2.30:5555/invoke/NCCISadad.PmtNotifyRq:receiveRealTimePaymentInfo";
    
    String pushBillInqRq = "http://10.16.17.52:5285/invoke/SADADEX01BillQuery.Accept:queryBill";
    String getSystemParmsURL = "https://ncci9iasp:5403/invoke/NCCISadad.helpers/getSystemParms?fileName=packages%5CNCCISadad%5Cdoc%5Cncci_Unit_test.props";
    //http://10.16.17.9:5355/invoke/SADADEX99Utilities.Config/getMessageDetails
    String pushPmtValRq = "http://10.16.17.52:5285/invoke/SADADEX01PaymentInitialisation.Accept/validatePayment?";
    String getMessageDetailsURL  = "http://10.16.17.9:5355/invoke/SADADEX99Utilities.Config/getMessageDetails";
    String apigeeURL = "http://shawkyfoda-eval-prod.apigee.net/tokensConsumer/write" ;
    
    //HttpPoster httpPoster = new HttpPoster("http://10.16.17.52:5285/invoke/SADADEX01PaymentInitialisation.Accept/confirmPayment?","proxy-dr", "shawky.foda","Redsea88");//"http://10.16.17.51:5402/invoke/SADADEX02BillUpload.Accept/receiveBillData"));
    
   // HttpPoster httpPoster = new HttpPoster(new URL ("http://127.0.0.1:8988/SADAD/servlet/SADAD.BillConfirmationReciver"),"","");
    //HttpPoster httpPoster = new HttpPoster("http://webservices.tiscali.com/SMSServices.asmx/SendFreeSMS", "proxy-dr","shawky.foda", "Redsea88");
    //HttpPoster httpPoster = new HttpPoster("http://10.16.25.41:5285/invoke/SADADEX01PaymentInitialisation.Accept:validatePayment", "","Administratorx", "manage");
    HttpPoster httpPoster = new HttpPoster(apigeeURL, "","Administratorx", "manage");
    Hashtable<String , String > requestHeaders = new Hashtable<String , String >() ; 
    requestHeaders.put("Authorization", "Bearer l3HOYRyyOf0k21OANIrKkmBJKYBA") ; 
    requestHeaders.put("g_access_token", "Bearer  ya29.GlyGBql-y30DD1O7CVlLgqXy89OIh8P62BiPbpUpzUujP4ww7nkWl1upfHDebWKrdfCsbED2wfJhIZHEG62DXWS3OmPgjKYCZR2jLv7Di9gsgoEEO6YsowlSEoclIQ") ; 
    
    httpPoster.setRequestHeaders(requestHeaders);
    
    
    httpPoster.setContentType("application/x-www-form-urlencoded");
    String x = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                            +"\n<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                            +"\n<soap:Body>"
                            +"\n<SendSMS xmlns=\"http://webservices.tiscali.com/\">"
                            +"\n<billingCode>12345</billingCode>"
                            +"\n<userAccount>987987897</userAccount>"
                            +"\n<phoneNumber>+966506818206</phoneNumber>"
                            +"\n<message>testing</message>"
                            +"\n</SendSMS>"
                            +"\n</soap:Body>"
                            +"\n</soap:Envelope>";         // using Soap
    x = "phoneNumber=+966506818206&message=Testing"  ;     // Using post                    
    //httpPoster.postRequest(x);
    httpPoster.setContentType("");
    httpPoster.setRequestMethod("GET");
    
    httpPoster.postRequest("");
    String response = httpPoster.readResponse();
    System.out.print(response);
    }
    catch (Exception e) {e.printStackTrace();}
  }


public String getRequestMethod() {
	return requestMethod;
}


public void setRequestMethod(String requestMethod) {
	this.requestMethod = requestMethod;
}



public String getContentType() {
	return contentType;
}



public void setContentType(String contentType) {
	this.contentType = contentType;
}



public Hashtable<String , String> getRequestQueryParams() {
	return requestQueryParams;
}



public void setRequestQueryParams(Hashtable<String , String> requestQueryParams) {
	this.requestQueryParams = requestQueryParams;
}



public Hashtable<String , String> getRequestHeaders() {
	return requestHeaders;
}



public void setRequestHeaders(Hashtable<String , String> requestHeaders) {
	this.requestHeaders = requestHeaders;
}}