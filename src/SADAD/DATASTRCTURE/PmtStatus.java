package SADAD.DATASTRCTURE;
import org.w3c.dom.*;
public class PmtStatus 
{
  public String PmtStatusCode;
  private static final String PmtStatusCodeNodeName = "PMTSTATUSCODE";
   
  public String EffDate;
  private static final String EffDateNodeName = "EFFDT";
    
  public String PmtMethod;
  private static final String PmtMethodNodeName = "PMTMETHOD";
    
  public PmtStatus(Node pmtStatusXMLNode)throws Exception
  {
    setValues(pmtStatusXMLNode);
  }
  private void setValues(Node pmtStatusXMLNode ) throws Exception 
  {
    NodeList childNodes = pmtStatusXMLNode.getChildNodes();
    int size = childNodes.getLength();
    String nodeName1 = "";
    String nodeValue1  = "";
    short nodeType1  = 0;
    
    String nodeName2 = "";
    String nodeValue2  = "";
    short nodeType2  = 0;
    boolean PmtStatusCodeFound = false;
    boolean PmtMethodFound = false;
    boolean EffDateFound = false;
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
                     if (! PmtStatusCodeFound &&  nodeName1.toUpperCase().equals(this.PmtStatusCodeNodeName))
                      {
                        this.PmtStatusCode = nodeValue2;
                        PmtStatusCodeFound = true;
                      }
                      
                      if (! EffDateFound &&  nodeName1.toUpperCase().equals(this.EffDateNodeName))
                      {
                        this.EffDate = nodeValue2;
                        EffDateFound = true;
                      }
                      
                      if (! PmtMethodFound &&  nodeName1.toUpperCase().equals(this.PmtMethodNodeName))
                      {
                        this.PmtMethod = nodeValue2;
                        PmtMethodFound = true;
                      }
                      
                  }
                  
                 }
    
           }
         }
    validateDate();
    }
    catch (Exception e) 
    { 
    e.printStackTrace();
    throw e;}
 }
 
 
 private void validateDate()
 {
   
 }
 
  public String toXMLString()
  {
  String xmlString= "";
  xmlString  += "\n" + "<PmtStatus>";
  xmlString  += "\n" + "<PmtStatusCode>" + this.PmtStatusCode + "</PmtStatusCode>";
  if (this.EffDate != null) 
  { xmlString  += "\n" + "<EffDt>" + this.EffDate + "</EffDt>" ;}
  xmlString  += "\n" + "</PmtStatus>";
  return xmlString;
    
  }
}