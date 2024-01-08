package SADAD.DATASTRCTURE;
import SADAD.*;
import SADAD.DATASTRCTURE.*;
import java.util.*;
import java.sql.*;
import org.w3c.dom.*;
import java.net.*;

public class PmtTransId //extends SADADMessage 
{
  private static final String PmtIdNodeName = "PMTID";
  private static final String PmtIdTypeNodeName = "PMTIDTYPE";
  public String  PmtId;
  public String PmtIdType;
  public String toXMLString()
  {
  String xmlString= "";
  xmlString  += "\n" + "<PmtTransId>";
  xmlString  += "\n\t" + "<PmtId>" + PmtId +"</PmtId>";
  xmlString  += "\n\t" + "<PmtIdType>" + PmtIdType +"</PmtIdType>";
  xmlString  += "\n" + "</PmtTransId>";
  return xmlString;
    
  }
  
  public PmtTransId( Node pmtTransIdNode)throws Exception
  {
    setParameters(pmtTransIdNode);
  }

  private void setParameters(Node pmtTransIdNode ) throws Exception 
  {
    NodeList childNodes = pmtTransIdNode.getChildNodes();
    int size = childNodes.getLength();
    String nodeName1 = "";
    String nodeValue1  = "";
    short nodeType1  = 0;
    
    String nodeName2 = "";
    String nodeValue2  = "";
    short nodeType2  = 0;
    boolean PmtIdFound = false;
    boolean PmtIdTypeFound = false;
   
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
                      if ( ! PmtIdFound  &&  nodeName1.toUpperCase().equals(PmtIdNodeName))
                      {
                        this.PmtId = nodeValue2;
                        PmtIdFound = true;
                        continue;
                      }
                      //---------------Setting The ExternalPmtId
                      if (! PmtIdTypeFound &&  nodeName1.toUpperCase().equals(this.PmtIdTypeNodeName))
                      {
                        this.PmtIdType = nodeValue2;
                        PmtIdTypeFound = true;
                        continue;
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
 public void validate()
 {
   
 }
}