package SADAD.DATASTRCTURE;
import Support.Validator1;
import java.util.*;
import Support.*;
import SADAD.*;
import oracle.xml.parser.schema.*;
import oracle.xml.parser.v2.*;
import oracle.xml.jaxp.*;
import javax.xml.parsers.*;
import java.net.*;
import java.io.*;
import org.w3c.dom.*;
import java.sql.*;
import org.doomdark.uuid.*;
import org.doomdark.uuid.UUID;
//import org.xml.sax.helpers.*;   //---For Generating a Universal Unique ID
public  class SADADMessage  

{
  protected Node docRootNode ;
  Connection con;
  final String tempFileName = "file:///C:/Temp/SadadMessage.xml" ; 
  
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
  public  SADADMessage(String xmlString ,boolean validateSchema , Connection m_con) throws Exception
  {
    Validator1.checkExpiry();
    saveXmlString2File(xmlString, validateSchema);
    con = m_con;
  }
  /*
   * Used in case of reading parameters from URL
   */

  public SADADMessage(URL theURL,Connection m_con,boolean validateSchema) throws Exception
  {
    Support.Validator1.checkExpiry();
    setRootNode(theURL,validateSchema);
    con = m_con;
  }

  public SADADMessage(InputStream is ,boolean validateSchema , Connection m_con) throws Exception
  {
    Support.Validator1.checkExpiry();
    setRootNode(is,validateSchema);
    con = m_con;
  }
 
  /*
   *  Used in case of reading parameters from XML Node
   */
  public SADADMessage (Node messageNode) throws Exception
  {
    Support.Validator1.checkExpiry();
    docRootNode = messageNode;
  }

  /*
   *  Used in case of reading parameters from XML Node and writing to DB or vice vesa
   */
  public SADADMessage (Node messageNode, Connection m_con) throws Exception
  {
    Support.Validator1.checkExpiry();
    docRootNode = messageNode;
    con = m_con;
  }
  /*
   * Used in case of reading parameters from xml file
   */
 
  public SADADMessage(String xmlFilePath , Connection m_con, boolean validateSchema ) throws Exception
  {
    Support.Validator1.checkExpiry();
    con = m_con;
    setRootNode(new URL(xmlFilePath),validateSchema);
        
  }

  private void setRootNode(InputStream is , boolean validateSchema)  throws Exception
  {
      javax.xml.parsers.DocumentBuilderFactory dbf = javax.xml.parsers.DocumentBuilderFactory.newInstance();
      dbf.setNamespaceAware(false);
      dbf.setValidating(false);

      if (validateSchema)
      {
          //----------validating againt Schema------------------
          XSDBuilder sb = new XSDBuilder();
          
          java.net.InetAddress localHost =  InetAddress.getLocalHost();
          String hostIP = localHost.getHostAddress();
    
          Object schemaObject;
          
          if (hostIP.equals("10.16.18.130") ||hostIP.equals("10.16.18.129")|| hostIP.equals("172.16,20.16")|| hostIP.equals("127.0.0.1") || hostIP.equals("10.16.18.181")|| hostIP.equals("172.16.20.16") )    //----if the system in running on the development (jdeveloper )
          {
            schemaObject = sb.build(new URL("file:///D:/MyWork/SADAD/public_html/SADAD_Messages/SADAD.xsd"));//"http://www.example.com/Report.xsd"
          }
          else if (hostIP.equals("172.16,20.16"))    //----if the system in running on the development (jdeveloper )
          {
            schemaObject = sb.build(new URL("file:///D:/MyWork/SADAD/public_html/SADAD_Messages/SADAD.xsd"));//"http://www.example.com/Report.xsd"
          }
          
          else if (hostIP.equals("130.1.2.30"))   //-----if the system is running on the Application Server--
          {
          schemaObject = sb.build(new URL("file:///C:/ora9iasInfra/j2ee/SADAD_OC4J/applications/SADAD/SADAD/SADAD_Messages/SADAD.xsd"));//"http://www.example.com/Report.xsd"
          }
          else if (hostIP.equals("130.1.2.177"))   //-----if the system is running on the Application Server--
          {
          schemaObject = sb.build(new URL("file:///E:/ora9ias2/j2ee/OC4J_SADAD/applications/SADAD/SADAD/SADAD_Messages/SADAD.xsd"));//"http://www.example.com/Report.xsd"
          }
          else {throw new SADADException("Shawky : System is not defined to run on this Machine",null,1);}

      dbf.setAttribute(JXDocumentBuilderFactory.SCHEMA_OBJECT, schemaObject);
      //---------End of validating againt Schema------------------
      }
      javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
//      InputStream is = theURL.openStream();
      try{
      Document doc = db.parse(is);
      is.close();
      this.docRootNode = doc.getFirstChild();
      String docRottNodeName  = docRootNode.getNodeName();
       if (docRottNodeName.toUpperCase().equals("XML") ) //----------If the URL Contents containt <?xml version="1.0" encoding="utf-8"?>
        {
          docRootNode = docRootNode.getNextSibling();
        }
      }
      catch (Exception e )
      {
       is.close();
       throw e;
      }
    
  }
  
  private void setRootNode(URL theURL , boolean validateSchema)  throws Exception
 {    
     InputStream is = theURL.openStream();
     this.setRootNode(is , validateSchema );
/*
      javax.xml.parsers.DocumentBuilderFactory dbf = javax.xml.parsers.DocumentBuilderFactory.newInstance();
      dbf.setNamespaceAware(false);
      dbf.setValidating(false);

      if (validateSchema)
      {
          //----------validating againt Schema------------------
          XSDBuilder sb = new XSDBuilder();
          
          java.net.InetAddress localHost =  InetAddress.getLocalHost();
          String hostIP = localHost.getHostAddress();
    
          Object schemaObject;
          if (hostIP.equals("10.16.18.130") || hostIP.equals("172.16,20.16")|| hostIP.equals("127.0.0.1") || hostIP.equals("10.16.18.181"))    //----if the system in running on the development (jdeveloper )
          {
            schemaObject = sb.build(new URL("file:///D:/MyWork/SADAD/public_html/SADAD_Messages/SADAD.xsd"));//"http://www.example.com/Report.xsd"
          }
          else if (hostIP.equals("172.16,20.16"))    //----if the system in running on the development (jdeveloper )
          {
            schemaObject = sb.build(new URL("file:///D:/MyWork/SADAD/public_html/SADAD_Messages/SADAD.xsd"));//"http://www.example.com/Report.xsd"
          }
          
          else if (hostIP.equals("130.1.2.30"))   //-----if the system is running on the Application Server--
          {
          schemaObject = sb.build(new URL("file:///C:/ora9iasInfra/j2ee/SADAD_OC4J/applications/SADAD/SADAD/SADAD_Messages/SADAD.xsd"));//"http://www.example.com/Report.xsd"
          }
          else if (hostIP.equals("130.1.2.177"))   //-----if the system is running on the Application Server--
          {
          schemaObject = sb.build(new URL("file:///E:/ora9ias2/j2ee/OC4J_SADAD/applications/SADAD/SADAD/SADAD_Messages/SADAD.xsd"));//"http://www.example.com/Report.xsd"
          }
          else {throw new SADADException("Shawky : System is not defined to run on this Machine",null,1);}

      dbf.setAttribute(JXDocumentBuilderFactory.SCHEMA_OBJECT, schemaObject);
      //---------End of validating againt Schema------------------
      }
      javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
      InputStream is = theURL.openStream();
      try{
      Document doc = db.parse(is);
      is.close();
      this.docRootNode = doc.getFirstChild();
      String docRottNodeName  = docRootNode.getNodeName();
       if (docRottNodeName.toUpperCase().equals("XML") ) //----------If the URL Contents containt <?xml version="1.0" encoding="utf-8"?>
        {
          docRootNode = docRootNode.getNextSibling();
        }
      }
      catch (Exception e )
      {
       is.close();
       throw e;
      }
  */    
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
     String rquid = SADADMessage.getUUID();
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
  
   //protected abstract void save2DB() throws Exception;
    /*
    * Each sub class should implement its validate method
    */

  
  
  
 
  
  
   
 
}