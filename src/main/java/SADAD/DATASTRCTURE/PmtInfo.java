package SADAD.DATASTRCTURE;
import org.w3c.dom.*;

public class PmtInfo //extends SADADMessage
{

  public String curAmt;
  private static final String curAmtNodeName = "CURAMT";

  public  String PrcDt;
  private static final String PrcDateNodeName = "PRCDT";
    
  public String DueDt;
  private static final String DueDateNodeName = "DUEDT";
  
  public String BillCycle;
  private static final String BillCycleNodeName = "BILLCYCLE";

  public String BillNumber;
  private static final String BillNumberNodeName = "BILLNUMBER";

  public String BillCategory;
  private static final String BillCategoryNodeName = "BILLCATEGORY";
 
  public AccountId accountId;
  private static final String accountIdNodeName = "ACCOUNTID";
 
  //public BankInfo bankInfo;
  //private static final String BankInfoNodeName = "BANKINFO";
  public String BankId;
  private static final String BankIdNodeName = "BANKID";
  
  public String DistrictCode;
  private static final String DistrictCodeNodeName = "DISTRICTCODE";
      
  public String BranchCode;
  private static final String BranchCodeNodeName = "BRANCHCODE";
 
  public String AccessChannel;
  private static final String AccessChannelNodeName = "ACCESSCHANNEL";
  
  public String PmtMethod;
  private static final String PmtMethodNodeName = "PMTMETHOD";

  public String PmtRefInfo;
  private static final String PmtRefInfoNodeName = "PMTREFINFO";
  
  
  
  public PmtInfo( Node pmtInfoXMLNode ) throws Exception
  {
    setParameters(pmtInfoXMLNode);
  }
  private void setParameters(Node pmtInfoXMLNode ) throws Exception 
  {
    NodeList childNodes = pmtInfoXMLNode.getChildNodes();
    int size = childNodes.getLength();
    String nodeName1 = "";
    String nodeValue1  = "";
    short nodeType1  = 0;
    
    String nodeName2 = "";
    String nodeValue2  = "";
    short nodeType2  = 0;

    //----The following booleans to speed up the search process-----
    boolean curAmtFound = false;
    boolean PrcDateFound = false;
    boolean DueDateFound = false;
    boolean BillCategoryFound = false;
    boolean BillNumberFound = false;
    boolean BillCycleFound = false;
    boolean DistrictCodeFound = false;        
    boolean BranchCodeFound = false;    
    boolean AccessChannelFound = false;
    boolean PmtMethodFound = false;
    boolean PmtRefInfoFound = false;
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
          //-------Setting Complex Nodes First------------------------------------------------------
          //if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(curAmtNodeName)  )
          //    {
          //      this.curAmt = new Currency(firstLevelNode);
          //    }

          //if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(originalAmtNodeName)  )
          //   {
          //      this.originalAmt = new Currency(firstLevelNode);
          //    }    

          if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(accountIdNodeName)  )
              {
                this.accountId = new AccountId(firstLevelNode);
              }

          //if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(BankInfoNodeName)  )
          //    {
          //      this.bankInfo = new BankInfo(firstLevelNode);
          //    }
              
          //----End of Setting Complex Nodes First------------------------------------------------------
          else
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
                      
                      if ( !BankIdFound &&  nodeName1.toUpperCase().equals(BankIdNodeName))
                      {
                        this.BankId = nodeValue2;
                        BankIdFound = true;
                        continue;
                      }

                      if ( !curAmtFound &&  nodeName1.toUpperCase().equals(curAmtNodeName))
                      {
                        this.curAmt = nodeValue2;
                        curAmtFound = true;
                        continue;
                      }


                      if ( !PrcDateFound &&  nodeName1.toUpperCase().equals(PrcDateNodeName))
                      {
                        this.PrcDt = nodeValue2;
                        PrcDateFound = true;
                        continue;
                      }
                      
                      if (! DueDateFound &&  nodeName1.toUpperCase().equals(DueDateNodeName))
                      {
                        this.DueDt = nodeValue2;
                        DueDateFound = true;
                        continue;
                      }
                      
                       if (! BillCategoryFound &&  nodeName1.toUpperCase().equals(BillCategoryNodeName))
                      {
                        this.BillCategory = nodeValue2;
                        BillCategoryFound = true;
                        continue;
                      }
                      
                       if (! BillNumberFound &&  nodeName1.toUpperCase().equals(BillNumberNodeName))
                      {
                        this.BillNumber = nodeValue2;
                        BillNumberFound = true;
                        continue;
                      }
                      
                       if (! BillCycleFound &&  nodeName1.toUpperCase().equals(BillCycleNodeName))
                      {
                        this.BillCycle = nodeValue2;
                        BillCycleFound = true;
                        continue;
                      }
                      
                      if (! BranchCodeFound &&  nodeName1.toUpperCase().equals(BranchCodeNodeName))
                      {
                        this.BranchCode = nodeValue2;
                        BranchCodeFound = true;
                        continue;
                      } 
                      
                      if (! DistrictCodeFound &&  nodeName1.toUpperCase().equals(DistrictCodeNodeName))
                      {
                        this.DistrictCode = nodeValue2;
                        DistrictCodeFound = true;
                        continue;
                      }
                      
                      if (! AccessChannelFound &&  nodeName1.toUpperCase().equals(AccessChannelNodeName))
                      {
                        this.AccessChannel = nodeValue2;
                        AccessChannelFound = true;
                        continue;
                      }
                      if (! PmtMethodFound &&  nodeName1.toUpperCase().equals(PmtMethodNodeName))
                      {
                        this.PmtMethod = nodeValue2;
                        PmtMethodFound = true;
                        continue;
                      }

                      if (! PmtRefInfoFound &&  nodeName1.toUpperCase().equals(PmtRefInfoNodeName))
                      {
                        this.PmtRefInfo = nodeValue2;
                        PmtRefInfoFound = true;
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
  protected void save2DB()
 {
   
 }
    public String toXMLString()
  {
  String xmlString= "";
  xmlString  += "\n" + "<PmtInfo>";
  xmlString  += "\n\t" + "<CurAmt>" + this.curAmt+ "</CurAmt>";
  xmlString  += "\n\t" + "<PrcDt>" + this.PrcDt + "</PrcDt>";
  xmlString  += "\n\t" + "<DueDt>" + this.DueDt + "</DueDt>";
  xmlString  += "\n\t" + "<BillCycle>" + this.BillCycle + "</BillCycle>";
  xmlString  += "\n\t" + "<BillNumber>" + this.BillNumber + "</BillNumber>";
  xmlString  += "\n\t" + this.accountId.toXMLString()  ;
  xmlString  += "\n" + "<BankId>" + this.BankId + "</BankId>";
  xmlString  += "\n\t" + "<DistrictCode>" + this.DistrictCode + "</DistrictCode>";
  xmlString  += "\n\t" + "<BranchCode>" + this.BranchCode + "</BranchCode>";
  xmlString  += "\n\t" + "<AccessChannel>" + this.AccessChannel + "</AccessChannel>";
  xmlString  += "\n\t" + "<PmtMethod>" + this.PmtMethod + "</PmtMethod>";
  xmlString  += "\n\t" + "<PmtRefInfo>" + this.PmtRefInfo + "</PmtRefInfo>";
  
  xmlString  += "\n" + "</PmtInfo>";
  return xmlString;
    
  }
}