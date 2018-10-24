package SADAD.DATASTRCTURE;
import org.w3c.dom.*;

public class XferResultInfo //extends SADADMessage 
{
  public Status status;
  private static final String statusName = "STATUS";
  
  public XferInfo xferInfo;
  private static final String xferInfoName = "XFERINFO";
  

  
  public XferResultInfo(Node accountIDNode ) throws Exception
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
    boolean statusFound = false;
    boolean xferINfoFound = false; 
     
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
             if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(statusName)  )
              {
                this.status = new Status(firstLevelNode);
              }
            if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(xferInfoName)  )
              {
                this.xferInfo = new XferInfo(firstLevelNode);
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