package Support;
import Support.DownLoad.*;
import java.io.*;
import java.net.*;
import java.nio.charset.Charset;


public class SmsSernder 
{
private String  proxy = "";
private String proxyPort = "";
private String proxyUserName = "";
private String proxyPassword = "";
private String smsGatwayUserName;
private int maxParts = 2;   
public static final String DEFAULT_CLICKTELL_API_ID = "3451265" ; // Default ClickTell Api_id
private String clicktellApiId = DEFAULT_CLICKTELL_API_ID ;
public String getSmsGatwayUserName() {
	return smsGatwayUserName;
}

public void setSmsGatwayUserName(String smsGatwayUserName) {
	this.smsGatwayUserName = smsGatwayUserName;
}

public String getSmsGatewayPassword() {
	return smsGatewayPassword;
}

public void setSmsGatewayPassword(String smsGatewayPassword) {
	this.smsGatewayPassword = smsGatewayPassword;
}

private String smsGatewayPassword;
  
public SmsSernder(String m_clickTellApiId , String m_proxy, String m_port , String m_proxyUserName , String m_proxyPassword)
  {
	this.clicktellApiId = m_clickTellApiId ; 
	this.proxy = m_proxy;
	this.proxyPort = m_port;
	this.proxyUserName = m_proxyUserName;
	this.proxyPassword = m_proxyPassword;
  }
  // Sending bulk sms's as an xml post request Under Testing 
  public  String sendSMSusingXml(String to_mobile_no , String msg , String from_mobile_no) throws Exception
  {
	  String  url = "https://api.clickatell.com/xml/xml" ; 
	  String xmlMessage = "<clickAPI>"
		  			+ "\n<sendMsg>"
		  			+ "\n<api_id>3451280</api_id>"
		  			+ "\n<user>"+this.getSmsGatwayUserName()+"</user>"
		  			+ "\n<password>"+this.getSmsGatewayPassword()+"</password>"
		  			+ "\n<to>"+to_mobile_no+"</to>"
		  			+ "\n<text>"+msg+"</text>"
		  			+ "\n</sendMsg>" 
		  			+"\n</clickAPI>" ;
	  
      Support.HttpPoster httpPoster = new Support.HttpPoster(url,"", proxyUserName,proxyPassword);
      httpPoster.postRequest(xmlMessage);
      String httpResponse =  httpPoster.readResponse();
	  return httpResponse ; 
  }
  
  // Construct an HTTP request for Clicktell sms http service and send the request and returns the response  
  
  public String queryMessageStatus (String apiMsgId ) throws Exception
  {
	  String messageStatus = null ;
	  	  String url= "http://api.clickatell.com/http/querymsg?api_id="+this.getClicktellApiId()+"&user="+this.smsGatwayUserName+"&password="+this.smsGatewayPassword+"&apimsgid="+apiMsgId;
	  	  HttpPoster hp = new HttpPoster(url, this.proxy, this.proxyUserName, this.proxyPassword);  
	  	  hp.postRequest("");
	  	  messageStatus =  hp.readResponse(); 
	  return messageStatus ; 
  }

  public String queryMessageCharge (String apiMsgId ) throws Exception
  {
	  String messageStatus = null ;
	  	  String url= "http://api.clickatell.com/http/getmsgcharge?api_id="+this.getClicktellApiId()+"&user="+this.smsGatwayUserName+"&password="+this.smsGatewayPassword+"&apimsgid="+apiMsgId;
	  	  HttpPoster hp = new HttpPoster(url, this.proxy, this.proxyUserName, this.proxyPassword);  
	  	  hp.postRequest("");
	  	  messageStatus =  hp.readResponse(); 
	  return messageStatus ; 
  }


  private String constructSmsUrl(SmsMessage smsMessage)
  {
	  String msg = smsMessage.getMessage() ;
	  msg = SmsSernder.stringToUnicodeIfNeeded(msg);
	  boolean messageInUniCode = ! msg.equalsIgnoreCase(smsMessage.getMessage());
	  String url= "http://api.clickatell.com/http/sendmsg?unicode="+((messageInUniCode)?"1":"0" )+"&api_id="+this.getClicktellApiId()+"&user="+this.smsGatwayUserName+"&password="+this.smsGatewayPassword+"&to="+smsMessage.getToMobile()+"&text="+URLEncoder.encode(msg)+"&from="+smsMessage.getFromAddress()+"&concat="+this.getMaxParts();
	  return url ;
  }
  
  
  public String sendSMS( SmsMessage m_smsMessage ) throws Exception
  {
   String gateWayResponse = "";
   String msg = m_smsMessage.getMessage();
   String to_mobile_no = m_smsMessage.getToMobile() ;
   String from_mobile_no = m_smsMessage.getFromAddress() ;
   //msg= URLEncoder.encode(msg);
    if (to_mobile_no != null 
                && ! to_mobile_no.substring(0,3).equals("9665") 
                && to_mobile_no.length() != 11 ) //966506818206
   {
   String url= constructSmsUrl(m_smsMessage ) ; //"http://api.clickatell.com/http/sendmsg?unicode=0&api_id="+this.getClicktellApiId()+"&user="+this.smsGatwayUserName+"&password="+this.smsGatewayPassword+"&to="+to_mobile_no+"&text="+msg+"&from="+from_mobile_no;
   URLDownLoader urldown = new URLDownLoader();
   BufferedReader br =  urldown.getBufferreader(url,this.proxy,Integer.parseInt(this.proxyPort),this.proxyUserName,this.proxyPassword);
   String line = br.readLine();
    while (line!= null )
      {
       gateWayResponse += line;
       line = br.readLine();
      }
    br.close();
   }
   else {throw new Exception ("Invalid Mobile No. " + to_mobile_no);}
   
   if (gateWayResponse != null && gateWayResponse.indexOf("ERR:") != -1) 
   {
	   throw new Exception ("SMS Gateway Error : " + gateWayResponse) ;
   }
   return  gateWayResponse;
  }
  
  public static String sendSMSUsingBulkSMS(String to_mobile_no , String msg , String from_mobile_no) throws Exception
  {
	  String result = null;
	      // Construct data
          String data = "";
          /*
           * Note the suggested encoding for certain parameters, notably
           * the username, password and especially the message.  ISO-8859-1
           * is essentially the character set that we use for message bodies,
           * with a few exceptions for e.g. Greek characters.  For a full list,
           * see:  http://bulksms.vsms.net/docs/eapi/submission/character_encoding/
           */
          data += "username=" + URLEncoder.encode("sfoda", "ISO-8859-1");
          data += "&password=" + URLEncoder.encode("redsea11", "ISO-8859-1");
          data += "&message=" + URLEncoder.encode(msg, "ISO-8859-1");
          data += "&want_report=1";
          data += "&msisdn="+to_mobile_no;

          // Send data
          URL url = new URL("http://bulksms.vsms.net:5567/eapi/submission/send_sms/2/2.0");
          /*
          * If your firewall blocks access to port 5567, you can fall back to port 80:
          * URL url = new URL("http://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
          * (See FAQ for more details.)
          */

          URLConnection conn = url.openConnection();
          conn.setDoOutput(true);
          OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
          wr.write(data);
          wr.flush();

          // Get the response
          BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
          String line;
          while ((line = rd.readLine()) != null) {
        	  result += line ;
          }
          wr.close();
          rd.close();
      
	  return result ; 
  }
  public static String stringToUnicodeIfNeeded(String msg)
  {
	  String result ; 		
	  boolean needConversion = false ;
	  StringBuffer ostr = new StringBuffer();
	  for(int i=0; i<msg.length(); i++) 
	  {
		  char ch = msg.charAt(i);
		if ((ch >= 0x0020) && (ch <= 0x007e))				// Does the char need to be converted to unicode? 
			{
				ostr.append("00"+Integer.toHexString(ch));		// No.
			} else 												// Yes.
			{
				String hex = Integer.toHexString(msg.charAt(i) & 0xFFFF);	// Get hex value of the char. 
				for(int j=0; j<4-hex.length(); j++)	// Prepend zeros because unicode requires 4 digits
				ostr.append("0");
				ostr.append(hex.toLowerCase());		// standard unicode format.
				needConversion = true;
			}
	  }
	  result = (needConversion) ? new String(ostr) : msg ; // if non Lattain C/c founds return the Unicode
	  
	  return result;		
	}
    
  public static void stringToByte(String original)
  {
	  try {
		    byte[] utf8Bytes = original.getBytes("UTF8");
		    byte[] defaultBytes = original.getBytes("ASCII");

		    String roundTrip = new String(utf8Bytes, "UTF8");
		    System.out.println("roundTrip = " + roundTrip);
		    System.out.println();
		    printBytes(utf8Bytes, "utf8Bytes");
		    System.out.println();
		    printBytes(defaultBytes, "defaultBytes");
		} 
		catch (UnsupportedEncodingException e) {
		    e.printStackTrace();
		}
  }
  
  public static void printBytes(byte[] array, String name) {
	    for (int k = 0; k < array.length; k++) {
	        System.out.print( byteToHex(array[k]) );
	    }
	}
  static public String byteToHex(byte b) {
      // Returns hex String representation of byte b
      char hexDigit[] = {
         '0', '1', '2', '3', '4', '5', '6', '7',
         '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
      };
      char[] array = { hexDigit[(b >> 4) & 0x0f], hexDigit[b & 0x0f] };
      return new String(array);
   }
 
   static public String charToHex(char c) {
      // Returns hex String representation of char c
      byte hi = (byte) (c >>> 8);
      byte lo = (byte) (c & 0xff);
      return byteToHex(hi) + byteToHex(lo);
   }
  public static void main(String[] args) throws Exception
  {
	 String str = "abcdefghijklmnopqrstuvwxyz ;.()-1234567890ابتثجحخدذرزسشصضطظعغفقكلمنهوى"; 
	 System.out.println("\n"+stringToUnicodeIfNeeded(str));
	  
	 	
    SmsSernder smsSernder = new SmsSernder(SmsSernder.DEFAULT_CLICKTELL_API_ID , "172.27.0.2", "3128" ,"SADAD\\shawky.foda" ,"redsea11");
    smsSernder.setSmsGatwayUserName("foda.sh@smart-value.com");
    smsSernder.setSmsGatewayPassword("redsea11");
    String to_mobile_no = "966564627751";
    String from_mobile_no = "966564627751" ; //"00201060092660";
    String msg = "Allah Akbar" ; 
    SmsMessage smsMessage = new SmsMessage(msg, to_mobile_no, from_mobile_no) ;
    String clickTellResponse = smsSernder.sendSMS(smsMessage) ;   
    System.out.println(clickTellResponse);
    System.out.println( smsSernder.queryMessageCharge( clickTellResponse.substring(4)));  // "5d0d488be9d5f9fb8a0a0ea5d4971afc"  ;     
    //System.out.println(smsSernder.queryMessageStatus(clickTellResponse.substring(4))) ; // "5d0d488be9d5f9fb8a0a0ea5d4971afc"
     
  }

public void setClicktellApiId(String clicktellApiId) {
	this.clicktellApiId = clicktellApiId;
}

public String getClicktellApiId() {
	return clicktellApiId;
}

public void setMaxParts(int maxParts) {
	this.maxParts = maxParts;
}

public int getMaxParts() {
	return maxParts;
}

}