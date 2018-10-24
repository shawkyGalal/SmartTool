package SADAD.DATASTRCTURE;
import SADAD.*;
import org.w3c.dom.*;
import java.sql.*;

public class Status //extends SADADMessage
{
  public String statusCode;
  private static final String statusCodeNodeName = "STATUSCODE";

  public  String shortDesc;
  private static final String shortDescNodeName = "SHORTDESC";
  
  public  String severity;
  private static final String severityNodeName = "SEVERITY";
  
  
  
  public Status(Node custIdXMLNode  )throws Exception
  {
    setParameters(custIdXMLNode);
  }
  protected void setParameters(Node custIdXMLNode ) throws Exception 
  {
    NodeList childNodes = custIdXMLNode.getChildNodes();
    int size = childNodes.getLength();
    String nodeName1 = "";
    String nodeValue1  = "";
    short nodeType1  = 0;
    
    String nodeName2 = "";
    String nodeValue2  = "";
    short nodeType2  = 0;
    boolean statusCodeFound = false;
    boolean shortDescFound = false;
    boolean severityFound = false;
    try 
    {
      for (int i=0 ; i< size; i++)
        {
          Node firstLevelNode = childNodes.item(i);
          nodeName1  = firstLevelNode.getNodeName();
          nodeValue1 = firstLevelNode.getNodeValue();
          nodeType1 = firstLevelNode.getNodeType();
          {
                NodeList secondLeveNodes = firstLevelNode.getChildNodes();
                int size2 = secondLeveNodes.getLength();
                for (int j=0 ; j< size2; j++)
                {
                  Node secondLevelNode = secondLeveNodes.item(j);
                  nodeName2  = secondLevelNode.getNodeName();
                  nodeType2  = secondLevelNode.getNodeType();
                  nodeValue2 = secondLevelNode.getNodeValue();
                  //--------Setting the PrcDate-----
                  if ( nodeType2 ==Node.TEXT_NODE )
                  {
                      if ( ! statusCodeFound  &&  nodeName1.toUpperCase().equals(statusCodeNodeName))
                      {
                        this.statusCode = nodeValue2;
                        statusCodeFound = true;
                        continue;
                      }
                      
                      if (! shortDescFound &&  nodeName1.toUpperCase().equals(this.shortDescNodeName))
                      {
                        this.shortDesc = nodeValue2;
                        shortDescFound = true;
                      }
                      if (! severityFound &&  nodeName1.toUpperCase().equals(this.severityNodeName))
                      {
                        this.severity = nodeValue2;
                        severityFound = true;
                      }
                                        
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
 
 
 protected void validate() throws SADADException
 {
  
 }
 protected void save2DB()
 {
   
 }
 
  
   public String toXMLString()
  {
    String xMLTag ="\n"+"<Status>";
          xMLTag +="\n\t"+"<StatusCode>"+this.statusCode+"</StatusCode>";
          xMLTag +="\n\t"+"<ShortDesc>"+this.shortDesc+"</ShortDesc>";
          xMLTag +="\n\t"+"<Severity>"+this.severity+"</Severity>";
    xMLTag +="\n"+"</Status>";
    return xMLTag;
  }
  
}