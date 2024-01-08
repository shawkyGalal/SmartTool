package SADAD.DATASTRCTURE;
import org.w3c.dom.*;
import java.sql.*;
import java.io.*;
import SADAD.*;

public class BillInfo 
{
  
  private final static String billCategoryNodeName="BILLCATEGORY" ;
  private final static String serviceTypeNodeName="SERVICETYPE";
  private final static String billCycleNodeName = "BILLCYCLE";
  private final static String BillNumberNodeName="BILLNUMBER";
  private final static String accountIdNodeName="ACCOUNTID" ;
  private final static String ammountDueNodeName="AMOUNTDUE";
  private final static String dueDtNodeName="DUEDT";
  private final static String openDtNodeName="OPENDT";
  private final static String closeDtNodeName="CLOSEDT";
  private final static String expDtNodeName="EXPDT";
  private final static String billSummaryAmtNodeName="BILLSUMMARYAMT";
  private final static String billRefInfoNodeName="BILLREFINFO";
  private static final String BillingAcctNodeName = "BILLINGACCT";
  private static final String BillerIdNodeName = "BILLERID";
  
  public String billCategory ;
  public String serviceType = "INS";
  public String billCycle = "";
  public String BillNumber;
  public AccountId accountId ;
  public String ammountDue;
  public String dueDt;
  public String openDt;
  public String closeDt;
  public String expDt;
  public String billRefInfo;
  public BillSummaryAmt billSummaryAmt;
  public String BillingAcct;
  public String BillerId;
  public Connection con ;
   
   

  public BillInfo( Node BillInfoNode , Connection m_con ) throws Exception
  {
   setParameters(BillInfoNode);
   this.con = m_con;
  }
  
 
  private void setParameters(Node billInfoXMLNode ) throws Exception 
  {
    NodeList childNodes = billInfoXMLNode.getChildNodes();
    int size = childNodes.getLength();
    String nodeName1 = "";
    String nodeValue1  = "";
    short nodeType1  = 0;
    
    String nodeName2 = "";
    String nodeValue2  = "";
    short nodeType2  = 0;
    
    boolean billCategoryFound = false;
    boolean serviceTypeFound = false;
    boolean billCycleFound = false;
    boolean BillNumberFound = false;
    boolean accountIdFound = false;
    boolean ammountDueFound = false;
    boolean dueDtFound = false;    
    boolean openDtFound = false;
    boolean closeDtFound = false;
    boolean expDtFound = false;
    boolean billRefInfoFound = false;
    boolean billSummaryAmtFound = false;
    boolean BillerIdFound = false;
    boolean BillingAcctFound = false;     
    try 
    {
      for (int i=0 ; i< size; i++)
        {
          Node firstLevelNode = childNodes.item(i);
          nodeName1  = firstLevelNode.getNodeName();
          nodeValue1 = firstLevelNode.getNodeValue();
          nodeType1 = firstLevelNode.getNodeType();
          // if ( !accountIdFound && nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(accountIdNodeName)  )
          //    {
          //      this.accountId = new AccountId(firstLevelNode);
          //      accountIdFound = true ;
          //      continue;
          //    }
          //if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(billSummaryAmtNodeName)  )
          //    {
          //      this.billSummaryAmt = new BillSummaryAmt(); //--Need to Be implemented
          //    }
          // else    
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
                      if ( !billCategoryFound &&  nodeName1.toUpperCase().equals(billCategoryNodeName))
                      {
                        this.billCategory = nodeValue2;
                        billCategoryFound = true;
                        continue;
                      }
                      
                      if (! serviceTypeFound &&  nodeName1.toUpperCase().equals(serviceTypeNodeName))
                      {
                        this.serviceType = nodeValue2;
                        serviceTypeFound = true;
                        continue;
                      }
                      
                       if (! billCycleFound &&  nodeName1.toUpperCase().equals(billCycleNodeName))
                      {
                        this.billCycle = nodeValue2;
                        billCycleFound = true;
                        continue;
                      }
                      
                       if (! BillNumberFound &&  nodeName1.toUpperCase().equals(BillNumberNodeName))
                      {
                        this.BillNumber = nodeValue2;
                        BillNumberFound = true;
                        continue;
                      }
                    
                    if (! ammountDueFound &&  nodeName1.toUpperCase().equals(ammountDueNodeName))
                      {
                        this.ammountDue = nodeValue2;
                        ammountDueFound = true;
                        continue;
                      }                 
                    
                    if (! dueDtFound &&  nodeName1.toUpperCase().equals(dueDtNodeName))
                      {
                        this.dueDt = nodeValue2;
                        dueDtFound = true;
                        continue;
                      }
                       if (! openDtFound &&  nodeName1.toUpperCase().equals(openDtNodeName))
                      {
                        this.openDt = nodeValue2;
                        openDtFound = true;
                        continue;
                      }             
                      if (! closeDtFound &&  nodeName1.toUpperCase().equals(closeDtNodeName))
                      {
                        this.closeDt = nodeValue2;
                        closeDtFound = true;
                        continue;
                      }             
                      if (! expDtFound &&  nodeName1.toUpperCase().equals(expDtNodeName))
                      {
                        this.expDt = nodeValue2;
                        expDtFound = true;
                        continue;
                      }             
                      if (! billRefInfoFound &&  nodeName1.toUpperCase().equals(billRefInfoNodeName))
                      {
                        this.billRefInfo = nodeValue2;
                        billRefInfoFound = true;
                        continue;
                      }             
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
    //--------Need To Be Implemented---------
  }
  protected void save2DB() 
  {
    //--------Need To Be Implemented---------
  }

   public String toXMLString()
  {
    String xMLTag = "\n" +"<BillInfo>";
    xMLTag += "\n\t" + "<serviceType>" + serviceType+ "</serviceType>";
    xMLTag += "\n\t" + "<BillID>" + BillNumber + "</BillID>";
    xMLTag += "\n\t" +"<SupercedBillId>" +"</SupercedBillId>";
    
    xMLTag += "\n\t" +"<BillCycle>"+ billCycle +"</BillCycle>";
    xMLTag += "\n" +"</BillInfo>";
    return xMLTag;
  }
  
}