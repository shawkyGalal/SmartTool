package SADAD.DATASTRCTURE;
import org.w3c.dom.*;

public class XferAddRq //extends SADADMessage 
{
  public String PrcDt;
  private static final String PrcDtName = "PRCDT";
  
  public java.util.Vector  xferInfos = new java.util.Vector();
  private static final String xferInfosName = "XFERINFO";  
  
  
  
  
  public XferAddRq(Node accountIDNode ) throws Exception
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
    //----The following booleans to speed up the search process-----
    boolean PrcDtFound = false;
    boolean CurAmtFound = false; 
    boolean BankIdFound = false; 
 
    
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
             if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(xferInfosName))
              {
                this.xferInfos.addElement( new XferInfo(firstLevelNode));
              }
 
              
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
                      if ( !PrcDtFound &&  nodeName1.toUpperCase().equals(PrcDtName))
                      {
                        this.PrcDt = nodeValue2;
                        PrcDtFound = true;
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
 
 
 protected void validate()
 {
   
 }
}

