package SADAD.DATASTRCTURE;
import org.w3c.dom.*;

public class BankAccountId //extends SADADMessage 
{
  public String AcctId;
  private static final String AcctIdName = "ACCTID";
  
  public String AcctType;
  private static final String AcctTypeName = "ACCTTYPE";

  public String BankId;
  private static final String BankIdName = "BANKID";
  
  
  public BankAccountId(Node accountIDNode ) throws Exception
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
    boolean AcctIdFound = false;
    boolean AcctTypeFound = false; 
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
                      if ( !AcctIdFound &&  nodeName1.toUpperCase().equals(AcctIdName))
                      {
                        this.AcctId = nodeValue2;
                        AcctIdFound = true;
                        continue;
                      }
                      
                      if (! AcctTypeFound &&  nodeName1.toUpperCase().equals(AcctTypeName))
                      {
                        this.AcctType = nodeValue2;
                        AcctTypeFound = true;
                        continue;
                      }

                      if (! BankIdFound &&  nodeName1.toUpperCase().equals(BankIdName))
                      {
                        this.BankId = nodeValue2;
                        BankIdFound = true;
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