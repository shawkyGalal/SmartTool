package SADAD.DATASTRCTURE;
import org.w3c.dom.*;

public class BillerReconUploadRs //extends SADADMessage 
{
  public String prcDt;
  private static final String prcDtNodeName = "PRCDT";
  
 
  
  public BillerReconUploadRs( Node accountIDNode ) throws Exception
  {
     
    setParameters(accountIDNode);
  }

  private void setParameters(Node accountIDNode ) throws Exception 
  {
    NodeList childNodes = accountIDNode.getChildNodes();
    int size = childNodes.getLength();
    String nodeName1 = "";
    String nodeValue1  = "";
    short nodeType1  = 0;
    
    String nodeName2 = "";
    String nodeValue2  = "";
    short nodeType2  = 0;
    
    //---End-The following booleans to speed up the search process-----
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
                      if ( nodeName1.toUpperCase().equals(this.prcDtNodeName))
                      {
                        this.prcDt = nodeValue2;
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
 
 
 protected void validate()
 {
   
 }
 public String toXMLString()
  {

  String xmlString= "";
  xmlString  += "\n" + "<BillerReconUploadRs>";
  xmlString  += "\n" + "<PrcDt>" + this.prcDt+ "</PrcDt>";
  xmlString  += "\n" + "</BillerReconUploadRs>";
  return xmlString;
  }
}