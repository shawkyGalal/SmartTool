package SADAD.DATASTRCTURE;
import org.w3c.dom.*;
import SADAD.*;
import java.sql.*;
import java.util.*;

public class PmtRec extends SADADMessage
{
  
  public PmtStatus pmtStatus; 
  private static final String pmtStatusNodeName = "PMTSTATUS";
    
  public Vector pmtTransIds = new Vector();
  private static final String pmtTransIdNodeName = "PMTTRANSID";
  
  public  CustId custId ;
  private static final String custIdNodeName = "CUSTID";
  
  public PmtInfo pmtInfo ;
  private static final String pmtInfoNodeName = "PMTINFO";
  Connection con ; 
  
  
  public PmtRec(Node pmtRecXMLNode, Connection m_con )throws Exception
  {
    super(pmtRecXMLNode);
    setParameters();
    con = m_con;
  }
  protected void setParameters() throws Exception
  {
    setParameters(this.docRootNode);
  }
  private void setParameters(Node pmtRecXMLNode ) throws Exception 
  {
    NodeList childNodes = pmtRecXMLNode.getChildNodes();
    int size = childNodes.getLength();
    String nodeName1 = "";
    String nodeValue1  = "";
    short nodeType1  = 0;
    
    String nodeName2 = "";
    String nodeValue2  = "";
    short nodeType2  = 0;
    
    boolean pmtStatusFound = false;  
    boolean custIdFound = false;
    boolean pmtInfoFound = false;
    boolean pmtTransIdFound = false; 
    try 
    {
      for (int i=0 ; i< size; i++)
        {
          Node firstLevelNode = childNodes.item(i);
          nodeName1  = firstLevelNode.getNodeName();
          nodeValue1 = firstLevelNode.getNodeValue();
          nodeType1 = firstLevelNode.getNodeType();

          if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(pmtTransIdNodeName)  )
              {
                this.pmtTransIds.addElement(new PmtTransId(firstLevelNode));
              }
         
          if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(pmtInfoNodeName)  )
              {
                pmtInfo = new PmtInfo(firstLevelNode);
                pmtInfoFound = true;
              }
          
          else if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(custIdNodeName)  )
              {
                this.custId = new CustId(firstLevelNode );
                custIdFound = true;
              }
              
          else if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(pmtStatusNodeName)  )
              {
                this.pmtStatus = new PmtStatus(firstLevelNode);
                pmtStatusFound = true;
              }    
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
                      /* The following removed in the last vesion
                      if ( ! PmtIdFound  &&  nodeName1.toUpperCase().equals(PmtIDNodeName))
                      {
                        this.PmtId = nodeValue2;
                        PmtIdFound = true;
                        continue;
                      }
                      //---------------Setting The ExternalPmtId
                      if (! ExternalPmtIdFound &&  nodeName1.toUpperCase().equals(this.ExternalPmtIdNodeName))
                      {
                        this.ExternalPmtId = nodeValue2;
                        ExternalPmtIdFound = true;
                        continue;
                      }
                      */
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
 

 public void validate()
 {
   
 }
 public void validateDb() throws Exception
 {
  //--insert into DB
  //throw new SADADException("The record already exist ",null,1);
  //--RollBack
 }
 /**
  * Simply Inserts the Record into Edge DB
  */
 public void uploadIntoEdge(String rqUUID) throws SQLException
 {
  
  PmtTransId pmtTransId = (PmtTransId)this.pmtTransIds.elementAt(0);
  String  insertStmt ="insert into payment_notification (pmt_id, pmt_status, pmt_method, bill_no, req_uid, cur_amt," 
                                  +  "  due_date, prc_date, status_eff_date ,  branch_code"
                                  //+ ", bank_id "
                                  //+  ", bank_name"
                                  +", access_ch, pmt_ref_info) "
                                  +  " values ('" + pmtTransId.PmtId + "','"
                                                  + this.pmtStatus.PmtStatusCode + "','"
                                                  + this.pmtInfo.PmtMethod+ "','"
                                                  + this.pmtInfo.BillNumber + "','"
                                                  + rqUUID + "','"
                                                  + this.pmtInfo.curAmt + "',"
                                                  + toSqlDate( this.pmtInfo.DueDt) + ","   
                                                  + toSqlDate( this.pmtInfo.PrcDt) + ","
                                                  + toSqlDate( this.pmtStatus.EffDate) + ",'"
                                                  + this.pmtInfo.BranchCode + "','"
                                                  //+ this.pmtInfo.bankInfo.BankId + "','"
                                                  //+ this.pmtInfo.bankInfo.Name + "','"
                                                  + this.pmtInfo.AccessChannel + "','"
                                                  + this.pmtInfo.PmtRefInfo + "'"
                                  +")";
       Statement stmt = this.con.createStatement();
       stmt.execute(insertStmt);
 }

  public void save2DB(SADADService parentService) throws SADADException
  {
    try{
    Statement  stmt = con.createStatement();
    String insertStmt = "insert into Pmt_Notify_Details (RqUID,SPTN, BNKPTN, PmtStatusCode , CurAmt , PrcDt , DueDt , BillNumber ,BillingAcct , BillerId ,BankId , DistrictCode , BranchCode , AccessChannel,PmtMethod , ServiceType  ) "
            + " values ('"+parentService.billerSvcRq.RqUID+"', '', '', '', '')";
    stmt.execute(insertStmt); // to be calculated 
    stmt.close();}
    catch (Exception e ){
    System.out.print(e.getMessage());
    throw new SADADException("Faild To Record Payment Notification Message "+ e.getMessage(),e,1);
    }
  }


 /*
  * Consider pmtRec is equal to another one if both of them have the same pmtId
  * @return true if pmtId is the same
  */
 /*
 public boolean equals(PmtRec otherPmtRec)
 {
   return  ( this.pmtTransId.equals(otherPmtRec.pmtTransId));
 }
 */
   public String toXMLString()
  {
  String xmlString= "";
  xmlString  += "\n" + "<PmtRec>";
  for (int i=0 ; i< pmtTransIds.size() ; i++)
  {
    PmtTransId x = (PmtTransId)pmtTransIds.elementAt(i);
    x.toXMLString();
  }
  xmlString  += (this.custId==null)? "":"\n" + this.custId.toXMLString();
  xmlString  += "\n" + this.pmtStatus.toXMLString();
  xmlString  += "\n" + this.pmtInfo.toXMLString();
  xmlString  += "\n" + "</PmtRec>";
  return xmlString;
    
  }
}