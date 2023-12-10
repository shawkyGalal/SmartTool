package SADAD.XMLPARSERS;
import SADAD.*;
import org.xml.sax.*;
import oracle.xml.parser.v2.*;
import java.net.*;
import java.io.*;
import org.w3c.dom.*;
import java.sql.*;
public  class BasicXMLParser 

{
  XMLDocument XMLDoc;
  Node docRootNode ;
  Connection con;

  public BasicXMLParser(URL theURL , String rootNodeName , Connection m_con) throws Exception
  {
    parseURLString(theURL);
    docRootNode = XMLDoc.selectSingleNode(rootNodeName);
    con = m_con;
  }  
  public BasicXMLParser(String xmlFilePath , String rootNodeName , Connection m_con) throws Exception
  {
    parseURLString(new URL(xmlFilePath));
    docRootNode = XMLDoc.selectSingleNode(rootNodeName);
    con = m_con;
  }
  
  private  void parseURLString(URL theURL)    throws IOException, XMLParseException, SAXException
  {
    // Create an oracle.xml.parser.v2.DOMParser to parse the document.
    DOMParser theParser = new DOMParser();
    // Set the parser to work in non-Validating mode
    theParser.setValidationMode(false);
    theParser.showWarnings(false);
    try
    {
      theParser.parse(theURL);
    }
    catch (XMLParseException s)
    {
      System.out.println(s.getMessage());
      throw s;
    }
    // Get the parsed XML Document from the parser
    XMLDoc =  theParser.getDocument();
  }
  public static void main(String[] arg )
  {
     try{
     
     BasicXMLParser xx = new BasicXMLParser("file:///c:/temp/BillUploadReq10-04-2004 10-35.xml", "BillUploadRq", null);
     xx.logAction("hjhjhjgf");
     Node first = xx.docRootNode.getFirstChild();
     
     System.out.print(first);
     }catch (Exception e )
     {System.out.print(e.getMessage());}
     
  }
   void logAction(String action) throws Exception 
   {
    try{
     Logger logger = new Logger(new SADADSettings().getTodayLogFilePath(SADADSettings.AUDIT_LOG));
     logger.logMessage(action);
     logger.close();
    }
    catch (Exception e){throw new Exception("Unable to Log the Action due to :" + e.getMessage());}
   }
  
   public void save2DB() throws Exception
   {
      throw new SADADException("Saving The XML File To Data Base Not Yet Implemented ",null,0);
   }
   protected void validate()
   {
     
   }
}