package Support.XML;

import java.sql.*;
import org.w3c.dom.*;
import java.util.*;
import java.io.*;
import java.net.*;
import oracle.xml.parser.schema.*;
import oracle.xml.jaxp.*;
/**
 * This Class Reads any XML File and stores it contents in a two vectors tagNames and tagValues with a recursion. 
 * i.e if the tag is complex the content of the tagValue will be an object of XmlToJavaVectors 
 */
public class XmlToJavaVectors 
{  
  protected Node docRootNode ;
  public Vector tagNames = new Vector();
  public Vector tagValues = new Vector();
  String dateFormat = "dd-mm-yyyy";
  public void setDateFormate(String x)
  {
    this.dateFormat = x;
  }
  
  public Vector getTagNames()
  {
    return this.tagNames;
  }
  
    public Vector getTagValues()
  {
    return this.tagValues;
  }

  /*
   * Used in case of reading parameters from URL
   */
  public XmlToJavaVectors(Node node) throws Exception
  {
    this.docRootNode = node;
    this.setParameters();    
  }


  public XmlToJavaVectors(URL theURL, boolean validateSchema) throws Exception
  {
    Support.Validator1.checkExpiry();
    setRootNode(theURL,validateSchema);
    this.setParameters();    
  }
 
  /*
   * Used in case of reading parameters from xml file
   */
 
  public XmlToJavaVectors(String xmlFilePath , boolean validateSchema ) throws Exception
  {
    Support.Validator1.checkExpiry();
    setRootNode(new URL(xmlFilePath),validateSchema);
    this.setParameters();    
  }

  private void setRootNode(URL theURL , boolean validateSchema)  throws Exception
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
          if (hostIP.equals("130.1.3.200"))    //----if the system in running on the development (jdeveloper )
          {
            schemaObject = sb.build(new URL("file:///C:/Edge_Work/SADAD/public_html/SADAD_Messages/SADAD.xsd"));//"http://www.example.com/Report.xsd"
          }
        
          else if (hostIP.equals("130.1.2.30"))   //-----if the system is running on the Application Server--
          {
          schemaObject = sb.build(new URL("file:///C:/ora9iasInfra/j2ee/OC4J_NCCI/applications/Support/Support/BankMessages/SADAD.xsd"));//"http://www.example.com/Report.xsd"
          }
          else if (hostIP.equals("130.1.2.177"))   //-----if the system is running on the Application Server--
          {
          schemaObject = sb.build(new URL("file:///E:/ora9ias2/j2ee/OC4J_SADAD/applications/SADAD/SADAD/SADAD_Messages/SADAD.xsd"));//"http://www.example.com/Report.xsd"
          }
          else {throw new Exception("Shawky : System is not defined to run on this Machine");}

      dbf.setAttribute(JXDocumentBuilderFactory.SCHEMA_OBJECT, schemaObject);
      //---------End of validating againt Schema------------------
      }
      javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
      InputStream is = theURL.openStream();
      try
      {
        Document doc = db.parse(is);
        is.close();
        this.docRootNode = doc.getFirstChild();
        String docRottNodeName = docRootNode.getNodeName();
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

   private void setParameters() throws Exception 
  {
    NodeList childNodes = this.docRootNode.getChildNodes();
    int size = childNodes.getLength();
    String nodeName1 = "";
    String nodeValue1  = "";
    short nodeType1  = 0;

  
    String nodeName2 = "";
    String nodeValue2  = "";
    String prev_node_name = "";
    short nodeType2  = 0;
    
    try 
    {
      for (int i=0 ; i< size; i++)
        {
          Node firstLevelNode = childNodes.item(i);
          nodeName1  = firstLevelNode.getNodeName();
          nodeValue1 = firstLevelNode.getNodeValue();
          nodeType1 = firstLevelNode.getNodeType();
          
          if ( nodeType1 == Node.ELEMENT_NODE)
          {
              NodeList secondLeveNodes = firstLevelNode.getChildNodes();
              int size2 = secondLeveNodes.getLength();
                  for (int j=0 ; j< size2; j++)
                  {
                    Node secondLeveNode = secondLeveNodes.item(j);
                    nodeName2  = secondLeveNode.getNodeName();
                    nodeType2  = secondLeveNode.getNodeType();
                    nodeValue2 = secondLeveNode.getNodeValue();

                    if ( nodeType2 ==Node.TEXT_NODE &&  nodeValue2 != null) 
                    {
                      this.tagNames.addElement(nodeName1);
                      this.tagValues.addElement(nodeValue2);
                      // break;
                    }

                    if ( nodeType2 ==Node.ELEMENT_NODE ) 
                    {
                      if (tagNames.size() >=1 )
                      {
                      this.tagNames.removeElementAt(tagNames.size()-1);
                      this.tagValues.removeElementAt(tagValues.size()-1);
                      }
                      this.tagNames.addElement(nodeName1);
                      this.tagValues.addElement(new XmlToJavaVectors(firstLevelNode));
                      break;
                    }
                  }
          }
         }
    validate();
    }
    catch (Exception e) 
    { 
    e.printStackTrace();
    throw e;}
 }
 
 
 public String toHtmlTable()
 {
   String htmlTableStr = "<table border = 1>";
   
   
   for (int i =0 ; i< this.tagNames.size() ; i++)
   {
     htmlTableStr +=          "\n\t <tr>";
     //-------------Name  Column-----------  
     htmlTableStr +=          "\n\t\t <td><div align='center'><strong>";
     htmlTableStr +=          tagNames.elementAt(i);
     htmlTableStr +=          "</strong></div></td>";
    //-------------Data Column-----------
      htmlTableStr +=          "\n\t\t <td>";
     if (tagValues.elementAt(i) instanceof String)
     {
      htmlTableStr += tagValues.elementAt(i);
     }
     if (tagValues.elementAt(i) instanceof XmlToJavaVectors)
     {
     XmlToJavaVectors xx =  (XmlToJavaVectors)tagValues.elementAt(i);
     htmlTableStr +=        xx.toHtmlTable()  ;
     }
     htmlTableStr +=          "</td>";
     
     htmlTableStr +=          "\n\t </tr>";
   }

   
   for (int i =0 ; i< this.tagNames.size() ; i++)
   {
     
   }
   
   htmlTableStr +=       "\n </table>";
   
   return htmlTableStr;
 }
 protected void validate()
 {
   
 }
 public Vector upload2DB(String tableName , java.sql.Connection con) throws Exception
 {
  Vector exceptions = new Vector();
  for (int counter = 0 ; counter < this.getTagValues().size() ; counter++)
  {
    XmlToJavaVectors firstLevel =  (XmlToJavaVectors)this.getTagValues().elementAt(counter);
    int recordSize = firstLevel.getTagValues().size();
    Vector columnNames = firstLevel.getTagNames();
    Vector columnValues = firstLevel.getTagValues();
    String columnNamesSeperated = "";
    String columnValuesSeperated = "";
    for (int i =0 ; i <recordSize ; i++)
    {
      columnNamesSeperated += columnNames.elementAt(i).toString() ;
      if (! (i == recordSize - 1))
      {
      columnNamesSeperated += ",";
      }
    }
    //-----------Getting db Column Types --------
    String query = "select "  + columnNamesSeperated  + " from " + tableName +" where 1<> 1";
    ResultSetMetaData rsmd =   con.createStatement().executeQuery(query).getMetaData();
    Vector columnTypes = new Vector();
    for (int i = 1 ; i<= recordSize ; i++)
    {
      columnTypes.addElement(rsmd.getColumnTypeName(i));
    }
    //----------End of Getting db Column Types --------  

    for (int i =0 ; i <recordSize ; i++)
    {
      if (columnTypes.elementAt(i).toString().equals("DATE"))
      { columnValuesSeperated = "to_date('"+columnValues.elementAt(i).toString()+"','"+dateFormat+"')"; }
      else
      { columnValuesSeperated += "'" + columnValues.elementAt(i).toString() + "'"; }
      if (! (i == recordSize - 1))
      { columnValuesSeperated += ","; }
    }
    String insertStmt = "insert into "+tableName+"( " + columnNamesSeperated + " ) values ( " + columnValuesSeperated + ")" ;
    try
    {
      con.createStatement().execute(insertStmt);
    }
    catch (Exception e) {exceptions.addElement(e);}
  } 
  String x= "";
  return exceptions;
 }
    public  static void main(String[] args)
   {
    try{
     Connection con = Support.ConnectionFactory.createConnection_old("130.1.2.2","edgedev","os310","redsea11");
     XmlToJavaVectors aa = new XmlToJavaVectors("file:///C:/temp/test.xml", false);//"file:///C:/Edge_Work/Support/public_html/RukhsaBankUpload/BankMessages/FromBank/RenewalRequests/Complex.xml", false);
     aa.upload2DB("edge.APP_CONFIG" , con);
     String html = aa.toHtmlTable();
     System.out.print("sdfsd");
    }
    catch(Exception e){e.printStackTrace();}
     //String x= SADADMessage.getUUID();//toSqlDate("2004-03-18T19:49:52");
     //System.out.print(x);
     
   }
}