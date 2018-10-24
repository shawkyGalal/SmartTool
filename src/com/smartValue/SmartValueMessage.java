package com.smartValue;

import Support.Validator1;
import SADAD.*;

import SADAD.DATASTRCTURE.Signature;
import SADAD.DATASTRCTURE.SignonRq;
import SADAD.DATASTRCTURE.SignonRs;
import oracle.xml.parser.schema.*;
import oracle.xml.jaxp.*;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;

import java.net.*;
import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.sql.*;

import org.doomdark.uuid.*;
//import org.xml.sax.helpers.*;   //---For Generating a Universal Unique ID
public  class SmartValueMessage  
{
	private SmsNotificationRequest smsNotificationRequest ;	
	private SmsNotificationResponse smsNotificationResponse ;
	private Signature signature ;
	private String asyncRqUID ;
	private Node docRootNode ;
	private SignonRq signonRq ; 
	private SignonRs signonRs ;
	private boolean messageAuthenticated = false ;
	Connection con;
	
	private String asyncRqUIDNodeName = "asyncRqUID" ; 
	
	private static final String SmsNotificationRequest_NODE_NAME = "SmsNotificationRequest";
	boolean smsNotificationRequestFound = false ;

	private static final String smsNotificationResponseNodeName = "SmsNotificationResponse" ;
	private boolean smsNotificationResponseNodeFound = false ;
	
	private static final String signatureNodeName = "Signature" ;
	private boolean signatureNodeFound = false ; 
	
	private static final String signonRqNodeName = "SignonRq" ;
	private boolean signonRqNodeFound = false ;

	private static final String signonRsNodeName = "SignonRs" ;
	private boolean signonRsNodeFound = false ;

	
	final String tempFileName = "file:///C:/Temp/SadadMessage.xml" ;
	

	
	public String toXmlString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<SmartValue>");
		if (this.getSignonRq() != null) 
		{
			sb.append(this.getSignonRq().toXMLString());
			if (this.getSmsNotificationRequest()!= null)
			{
				sb.append(this.getSmsNotificationRequest().toXmlString());
			}
		}
		
		else if (this.getSignonRs() != null)
		{
			sb.append("\n<"+signonRsNodeName+">" + this.getSignonRs().toXMLString() + ("</"+signonRsNodeName+">") );
			if (this.getSmsNotificationResponse()!=null)
			{
				sb.append("\n<"+this.smsNotificationResponseNodeName+">"+ this.getSmsNotificationResponse().toXmlString());
			}
			
		}
		sb.append("<"+signatureNodeName+">" + this.getSignature().toXmlString() + "</"+signatureNodeName+">" );
		//TODO
		
		sb.append("<\\SmartValue>");
		return sb.toString();
	}
	
	
  /*
   * This method is used to write an xmlstring to a file system then sets the rootnode with this file
   * this method should be synchronous to prevent file ovewriting from other threads.
   */
  private synchronized void saveXmlString2File(String xmlString ,boolean validateSchema) throws Exception 
  {
    //----------Writing the String to a tmp file----
    URL tempFileURL = new URL(tempFileName);
    PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter(tempFileURL.getFile())));
    out1.print(xmlString);
    out1.close();
    setRootNode(tempFileURL,validateSchema);

   
    //----------Writing the String to a tmp file----
  }
  public  SmartValueMessage(String xmlString ,boolean validateSchema , Connection m_con) throws Exception
  {
    Validator1.checkExpiry();
    saveXmlString2File(xmlString, validateSchema);
    con = m_con;
  }
  /*
   * Used in case of reading parameters from URL
   */

  public SmartValueMessage(URL theURL,Connection m_con,boolean validateSchema) throws Exception
  {
    Support.Validator1.checkExpiry();
    setRootNode(theURL,validateSchema);
    con = m_con;
  }

  public SmartValueMessage(InputStream is ,boolean validateSchema , Connection m_con) throws Exception
  {
	con = m_con;
	Support.Validator1.checkExpiry();
    setRootNode(is,validateSchema);
    
  }
 
  /*
   *  Used in case of reading parameters from XML Node
   */
  public SmartValueMessage (Node messageNode) throws Exception
  {
    Support.Validator1.checkExpiry();
    docRootNode = messageNode;
  }

  /*
   *  Used in case of reading parameters from XML Node and writing to DB or vice vesa
   */
  public SmartValueMessage (Node messageNode, Connection m_con) throws Exception
  {
    Support.Validator1.checkExpiry();
    docRootNode = messageNode;
    con = m_con;
  }
  /*
   * Used in case of reading parameters from xml file
   */
 
  public SmartValueMessage(String xmlFilePath , Connection m_con, boolean validateSchema ) throws Exception
  {
    Support.Validator1.checkExpiry();
    con = m_con;
    setRootNode(new URL(xmlFilePath),validateSchema);
        
  }

  private void setRootNode(InputStream is , boolean validateSchema)  throws Exception
  {
	
	String requestMessage = "";
	int c= 0;
	while( (c=  is.read()) != -1 )
	{
	   requestMessage+= (char)c;
	}
	
	InputStream isToValidate = new ByteArrayInputStream(requestMessage.getBytes());
	InputStream isToParse = new ByteArrayInputStream(requestMessage.getBytes());


    if (validateSchema)
    {
        //----------validating againt Schema------------------
    	// 1. Lookup a factory for the W3C XML Schema language
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
          
        // 2. Compile the schema. 
        // Here the schema is loaded from a java.io.File, but you could use 
        // a java.net.URL or a javax.xml.transform.Source instead.
          
        URL schemaLocation = this.getClass().getResource("SMART_VALUE.xsd");
    	Schema schema = factory.newSchema(schemaLocation);
    	  
    	// 3. Get a validator from the schema.
        Validator validator = schema.newValidator();
          
        // 4. Parse the document you want to check.
        Source source = new StreamSource(isToValidate);
        // 5. Check the document
        validator.validate(source);
          
      //---------End of validating againt Schema------------------
      }

      javax.xml.parsers.DocumentBuilderFactory dbf = javax.xml.parsers.DocumentBuilderFactory.newInstance();
      dbf.setNamespaceAware(false);
      dbf.setValidating(false);

      javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();

   	  Document doc = db.parse(isToParse);
   	  this.docRootNode = doc.getFirstChild();
   	  String docRottNodeName  = docRootNode.getNodeName();
   	  if (docRottNodeName.toUpperCase().equals("XML") ) //----------If the URL Contents containt <?xml version="1.0" encoding="utf-8"?>
   	  {
   		docRootNode = docRootNode.getNextSibling();
   	  }
   	  this.setParameters();
    
  }
  
  
  private void setParameters( ) throws Exception 
  {
	NodeList childNodes = this.docRootNode.getChildNodes();
    int size = childNodes.getLength();
    String nodeName1 = "";
    String nodeValue1  = "";
    short nodeType1  = 0;
    
    String nodeName2 = "";
    String nodeValue2  = "";
    short nodeType2  = 0;
    //----The following booleans to speed up the search process-----
    boolean asyncRqUIDFound = false;
    //---End-The following booleans to speed up the search process-----
    try 
    {
      for (int i=0 ; i< size; i++)
        {
          Node firstLevelNode = childNodes.item(i);
          nodeName1  = firstLevelNode.getNodeName();
          nodeValue1 = firstLevelNode.getNodeValue();
          nodeType1 = firstLevelNode.getNodeType();
          //-------Setting Complex Nodes First------------------------------------------------------
          if ( ! smsNotificationRequestFound 
        		  && nodeType1 ==Node.ELEMENT_NODE 
        		  && nodeName1.toUpperCase().equalsIgnoreCase(SmsNotificationRequest_NODE_NAME) )
          {
              this.setSmsNotificationRequest(new SmsNotificationRequest(firstLevelNode));
              smsNotificationRequestFound= true;
          }
          else if (! smsNotificationResponseNodeFound 
        		  && nodeType1 ==Node.ELEMENT_NODE 
        		  && nodeName1.toUpperCase().equalsIgnoreCase(smsNotificationResponseNodeName) )
          {
        	  this.setSmsNotificationResponse(new SmsNotificationResponse(firstLevelNode));
          }
          else if (! signonRqNodeFound 
        		  && nodeType1 ==Node.ELEMENT_NODE 
        		  && nodeName1.toUpperCase().equalsIgnoreCase(signonRqNodeName))
          {
        	this.setSignonRq(new SignonRq(firstLevelNode));
        	this.signonRqNodeFound = true;
          }
          
          else if (! signonRsNodeFound 
        		  && nodeType1 ==Node.ELEMENT_NODE 
        		  && nodeName1.toUpperCase().equalsIgnoreCase(signonRsNodeName))
          {
        	this.setSignonRs(new SignonRs(firstLevelNode));
        	this.signonRsNodeFound = true;
          }
          
          else if (! signatureNodeFound 
        		  && nodeType1 ==Node.ELEMENT_NODE 
        		  && nodeName1.toUpperCase().equalsIgnoreCase(signatureNodeName))
          {
        	this.setSignature(new Signature(firstLevelNode));
        	this.signatureNodeFound = true;
          }
          

          
         }
      }
    catch (Exception e) 
    { 
    	e.printStackTrace();
    	throw e;
    }
    
    this.setMessageAuthenticated(this.authenticate()); 
  }

  
  
private void setRootNode(URL theURL , boolean validateSchema)  throws Exception
 {    
     InputStream is = theURL.openStream();
     this.setRootNode(is , validateSchema );
 }

public SmsNotificationRequest getSmsNotificationRequest()
  {
	  return this.smsNotificationRequest; 
  }
  
  public static String toSqlDate(String xmlDate)
   {
     String sqlDate= "";
     if( xmlDate == null )     
     {sqlDate = " to_date('', 'YYYY-MM-DD HH24:MI:SS') ";}
     else
     { sqlDate= " to_date('"+xmlDate.substring(0,10) + " "
                                 + xmlDate.substring(11) +""
                                + "','YYYY-MM-DD HH24:MI:SS') ";
     }                                
     return sqlDate;
   }
   public static String getUUID()
   {
    UUIDGenerator uuig =  UUIDGenerator.getInstance();
    // Replace with the Actual MAC Address Ethernet Adaptor
    // You will find it in  (Windows )
    // Start----> Programes-----> Accessories----->System information------>Componants---->network------->adaptor----> Mac address
    UUID x= uuig.generateTimeBasedUUID( new EthernetAddress("00:0B:CD:B5:26:F0"));  // This is My Machine (IP = 130.1.2.30) 
    String s = x.toString();
    return s;
   }
   public  static void main(String[] args)
   {
     //com.ccg.net.ethernet.EthernetAddress xx= com.ccg.net.ethernet.EthernetAddress.getPrimaryAdapter();
    try{
     String rquid = SmartValueMessage.getUUID();
     //Connection con = ConnectionFactory.createConnection("192.1.1.8","edgedev","SADAD","SADAD");
     //"http://130.1.2.76:8993/SADAD/SADAD_Messages/ToSADAD/BillUploads/BillUploadReqs29-05-2004-09-54.xml
     //SADADMessage aa = new SADADMessage("file:///C:/Edge_Work/SADAD/public_html/SADAD_Messages/FromSADAD/PmtNotifications/PmtNotifyUploadRqSample.xml",null, true);
     
     System.out.print("sdfsd");
    }
    catch(Exception e){e.printStackTrace();}
     //String x= SADADMessage.getUUID();//toSqlDate("2004-03-18T19:49:52");
     //System.out.print(x);
     
   }
 
   void logAction(String action) throws Exception 
   {
    try{
     Support.Logger logger = new Support.Logger(new SADADSettings().getTodayLogFilePath(SADADSettings.AUDIT_LOG));
     logger.logMessage(action);
     logger.close();
    }
    catch (Exception e){throw new Exception("Unable to Log the Action due to :" + e.getMessage());}
   }

   
private boolean authenticate( )
{
	boolean result = false ;
	String sender ;
	String senderPassword ;
	java.sql.ResultSet rs = null ;
	try
	{
		sender = this.getSignonRq().getSignonProfile().getSender();
		senderPassword = this.getSignonRq().getSignonProfile().getSenderPassword();
		String queryString = "Select PASSWORD from JCCS.CUSTOMERS C Where C.CUSTOMER_CODE = '" + sender + "'" ;
		String storedSenderPassword = null;
		rs =  con.createStatement().executeQuery(queryString) ;
		while (rs.next())
		{
			storedSenderPassword = rs.getString(1);
		}
		
		rs.close();
		result = senderPassword.equalsIgnoreCase(storedSenderPassword) ;
	}
	catch (Exception e) 
	{
		if (rs != null)
			try { rs.close(); } 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
	}
		
	return result; 
}

private void setSmsNotificationRequest( SmsNotificationRequest smsNotificationRequest2) 
{
	this.smsNotificationRequest = smsNotificationRequest2 ;
}


public void setSignonRq(SignonRq signonRq) {
	this.signonRq = signonRq;
}


public SignonRq getSignonRq() {
	return signonRq;
}


public void setSignonRs(SignonRs signonRs) {
	this.signonRs = signonRs;
}


public SignonRs getSignonRs() {
	return signonRs;
}


public void setSmsNotificationResponse(SmsNotificationResponse smsNotificationResponse) {
	this.smsNotificationResponse = smsNotificationResponse;
}


public SmsNotificationResponse getSmsNotificationResponse() {
	return smsNotificationResponse;
}


public void setSignature(Signature signature) {
	this.signature = signature;
}


public Signature getSignature() {
	return signature;
}


public void setMessageAuthenticated(boolean messageAuthenticated) {
	this.messageAuthenticated = messageAuthenticated;
}


public boolean isMessageAuthenticated() {
	return messageAuthenticated;
}
     
 
}