package SADAD.DATASTRCTURE;
import org.w3c.dom.*;

public class XferInfo //extends SADADMessage 
{
  public String PmtId;
  private static final String PmtIdName = "PMTID";
  
  public String CurAmt;
  private static final String CurAmtName = "CURAMT";
  
  public BankAccountId DepAcctIdFrom;
  private static final String DepAcctIdFromName = "DEPACCTIDFROM";
  
  public BankAccountId DepAcctIdTo;
  private static final String DepAcctIdToName = "DEPACCTIDTO";
  
  
  
  
  public XferInfo(Node accountIDNode ) throws Exception
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
    boolean PmtIdFound = false;
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
             if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(DepAcctIdFromName)  )
              {
                this.DepAcctIdFrom = new BankAccountId(firstLevelNode);
              }
            if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(DepAcctIdToName)  )
              {
                this.DepAcctIdTo = new BankAccountId(firstLevelNode);
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
                      if ( !PmtIdFound &&  nodeName1.toUpperCase().equals(PmtIdName))
                      {
                        this.PmtId = nodeValue2;
                        PmtIdFound = true;
                        continue;
                      }
                      
                      if (! CurAmtFound &&  nodeName1.toUpperCase().equals(CurAmtName))
                      {
                        this.CurAmt = nodeValue2;
                        CurAmtFound = true;
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