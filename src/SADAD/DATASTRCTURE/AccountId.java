package SADAD.DATASTRCTURE;
import org.w3c.dom.*;

public class AccountId //extends SADADMessage 
{
  public String BillingAcct;
  private static final String BillingAcctNodeName = "BILLINGACCT";
  
  public String BillerId="003";
  private static final String BillerIdNodeName = "BILLERID";
   
  
  public AccountId( Node accountIDNode ) throws Exception
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
    boolean BillerIdFound = false;
    boolean BillingAcctFound = false; 
 
    
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
                      if ( !BillingAcctFound &&  nodeName1.toUpperCase().equals(BillingAcctNodeName))
                      {
                        this.BillingAcct = nodeValue2;
                        BillingAcctFound = true;
                        continue;
                      }
                      
                      if (! BillerIdFound &&  nodeName1.toUpperCase().equals(BillerIdNodeName))
                      {
                        this.BillerId = nodeValue2;
                        BillerIdFound = true;
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
 public String toXMLString()
  {

  String xmlString= "";
  xmlString  += "\n" + "<AccountId>";
  xmlString  += "\n" + "<BillingAcct>" + this.BillingAcct+ "</BillingAcct>";
  xmlString  += "\n" + "<BillerId>" + this.BillerId + "</BillerId>";
  xmlString  += "\n" + "</AccountId>";
  return xmlString;
  }
}