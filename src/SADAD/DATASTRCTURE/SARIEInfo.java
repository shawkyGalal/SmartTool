
package SADAD.DATASTRCTURE;
import org.w3c.dom.*;

public class SARIEInfo //extends SADADMessage 
{
  public String BankCode;
  private static final String BankCodeName = "BANKCODE";
  
  public String CrDr="";
  private static final String CrDrName = "CRDR";

  public String Currency="";
  private static final String CurrencyName = "CURRENCY";

  public String ActualAmt="";
  private static final String ActualAmtName = "ACTUALAMT";
   
  
  public SARIEInfo( Node sarieInfoNode ) throws Exception
  {
     
    setParameters(sarieInfoNode);
  }

  private void setParameters(Node sarieInfoNode ) throws Exception 
  {
    NodeList childNodes = sarieInfoNode.getChildNodes();
    int size = childNodes.getLength();
    String nodeName1 = "";
    String nodeValue1  = "";
    short nodeType1  = 0;
    
    String nodeName2 = "";
    String nodeValue2  = "";
    short nodeType2  = 0;
    //----The following booleans to speed up the search process-----
    boolean BankCodeFound = false;
    boolean CrDrFound = false; 
    boolean CurrencyFound = false; 
    boolean ActualAmtFound = false; 
    
     
    
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
                      if ( !BankCodeFound &&  nodeName1.toUpperCase().equals(BankCodeName))
                      {
                        this.BankCode = nodeValue2;
                        BankCodeFound = true;
                        continue;
                      }
                      
                      if (! CrDrFound &&  nodeName1.toUpperCase().equals(CrDrName))
                      {
                        this.CrDr = nodeValue2;
                        CrDrFound = true;
                        continue;
                      }
                      if (! CurrencyFound &&  nodeName1.toUpperCase().equals(CurrencyName))
                      {
                        this.Currency = nodeValue2;
                        CurrencyFound = true;
                        continue;
                      }
                     if (! ActualAmtFound &&  nodeName1.toUpperCase().equals(ActualAmtName))
                      {
                        this.ActualAmt = nodeValue2;
                        ActualAmtFound = true;
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
 /*public String toXMLString()
  {

  String xmlString= "";
  xmlString  += "\n" + "<AccountId>";
  xmlString  += "\n" + "<BillingAcct>" + this.BillingAcct+ "</BillingAcct>";
  xmlString  += "\n" + "<BillerId>" + this.BillerId + "</BillerId>";
  xmlString  += "\n" + "</AccountId>";
  return xmlString;
  }
  */
 public static void main(String[] args)
  {
    
  }
}


 