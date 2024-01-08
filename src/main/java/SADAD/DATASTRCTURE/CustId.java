package SADAD.DATASTRCTURE;
import SADAD.*;
import org.w3c.dom.*;
import java.sql.*;

public class CustId //extends SADADMessage
{
  public String OfficialID;
  private static final String OfficialIDNodeName = "OFFICIALID";

  public  String OfficialIDType;
  private static final String OfficialIDTypeNodeName = "OFFICIALIDTYPE";
  
  
  public CustId(Node custIdXMLNode  )throws Exception
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
    boolean OfficialIDFound = false;
    boolean OfficialIDTypeFound = false;
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
                      if ( ! OfficialIDFound  &&  nodeName1.toUpperCase().equals(OfficialIDNodeName))
                      {
                        this.OfficialID = nodeValue2;
                        OfficialIDFound = true;
                        continue;
                      }
                      
                      if (! OfficialIDTypeFound &&  nodeName1.toUpperCase().equals(this.OfficialIDTypeNodeName))
                      {
                        this.OfficialIDType = nodeValue2;
                        OfficialIDTypeFound = true;
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
   if (this.OfficialID == null )
   
   {
     throw new SADADException("Customer ID Not Found in the CustID Node",null,0);
   }
 }
 protected void save2DB()
 {
   
 }
 
  
   public String toXMLString()
  {
    String xMLTag ="\n"+"<CustId>";
          xMLTag +="\n\t"+"<officialIdType>"+OfficialIDType+"</officialIdType>";
          xMLTag +="\n\t"+"<OfficialId>"+OfficialID+"</OfficialId>";
    xMLTag +="\n"+"</CustId>";
    return xMLTag;
  }
  
}