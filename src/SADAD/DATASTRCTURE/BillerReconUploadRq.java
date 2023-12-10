package SADAD.DATASTRCTURE;
import java.util.*;
import java.sql.*;
import java.io.*;
import SADAD.*;
import org.w3c.dom.*;

 /**
  * @version 1.0
  * @author Shawky Galal Foda
  */
  
public class BillerReconUploadRq 
{
  public String PrcDt = "";
  public String CollectPmtAmt = "";
  public String ReconPmtAmt ="";
  public String UnReconPmtAmt = "";
  public String TransFees= "";
  public Vector PmtBankRecs = new Vector();

  private final String PrcDtNodeName="PRCDT";    //simple Node
  private final String CollectPmtAmtNodeName="COLLECTPMTAMT";         //simple Node
  private final String ReconPmtAmtNodeName="RECONPMTAMT";          //simple Node
  private final String UnReconPmtAmtNodeName="UNRECONPMTAMT";          //simple Node
  private final String TransFeesNodeName="TRANSFEES";          //simple Node
  private final String PmtBankRecsNodeName="PMTBANKREC";          //Complex Node
  
  

  public String toXMLString()
  {
  String xmlString= "";
  xmlString  += "\n" + "<BillerReconUploadRq>";
  xmlString  += "\n\t" + "<PrcDt>" + PrcDt + "</PrcDt>";
  xmlString  += "\n\t" + "<CollectPmtAmt>" + CollectPmtAmt + "</CollectPmtAmt>";
  xmlString  += "\n\t" + "<ReconPmtAmt>" + ReconPmtAmt + "</ReconPmtAmt>";
  xmlString  += "\n\t" + "<UnReconPmtAmt>" + UnReconPmtAmt + "</UnReconPmtAmt>";
  xmlString  += "\n\t" + "<TransFees>" + TransFees + "</TransFees>";
  for (int i =0 ; i< this.PmtBankRecs.size() ; i++)
  {
    PmtBankRec x = (PmtBankRec)this.PmtBankRecs.elementAt(i);
    xmlString  += "\n\t" + x.toXMLString();
  }
  xmlString  += "\n" + "</BillerReconUploadRq>";
  return xmlString;
    
  }
  public BillerReconUploadRq(Node billRecNode) throws Exception
  { 
     setParameters(billRecNode);
  }
  protected void setParameters(Node billRecNode ) throws Exception 
  {
    NodeList childNodes = billRecNode.getChildNodes();
    int size = childNodes.getLength();
    String nodeName1 = "";
    String nodeValue1  = "";
    short nodeType1  = 0;
    
    String nodeName2 = "";
    String nodeValue2  = "";
    short nodeType2  = 0;
    
    boolean PrcDtFound = false;
    boolean CollectPmtAmtFound = false;
    boolean PmtBankRecsFound = false;
    boolean ReconPmtAmtFound = false;
    boolean UnReconPmtAmtFound = false;
    boolean TransFeesFound = false;
    try 
    {
      for (int i=0 ; i< size; i++)
        {
          Node firstLevelNode = childNodes.item(i);
          nodeName1  = firstLevelNode.getNodeName();
          nodeValue1 = firstLevelNode.getNodeValue();
          nodeType1 = firstLevelNode.getNodeType();
               //-------Setting Complex Nodes First------------------------------------------------------
          if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(PmtBankRecsNodeName)  )
          {
             this.PmtBankRecs.addElement(new PmtBankRec(firstLevelNode));
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
                      if ( ! PrcDtFound  &&  nodeName1.toUpperCase().equals(PrcDtNodeName))
                      {
                        this.PrcDt = nodeValue2;
                        PrcDtFound = true;
                        continue;
                      }
                      
                      if (! CollectPmtAmtFound &&  nodeName1.toUpperCase().equals(this.CollectPmtAmtNodeName))
                      {
                        this.CollectPmtAmt = nodeValue2;
                        CollectPmtAmtFound = true;
                      }
                       
                       if (! ReconPmtAmtFound &&  nodeName1.toUpperCase().equals(this.ReconPmtAmtNodeName))
                      {
                        this.ReconPmtAmt = nodeValue2;
                        ReconPmtAmtFound = true;
                      }
                       if (! UnReconPmtAmtFound &&  nodeName1.toUpperCase().equals(this.UnReconPmtAmtNodeName))
                      {
                        this.UnReconPmtAmt = nodeValue2;
                        ReconPmtAmtFound = true;
                      }
                       if (! TransFeesFound &&  nodeName1.toUpperCase().equals(this.TransFeesNodeName))
                      {
                        this.TransFees = nodeValue2;
                        TransFeesFound = true;
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
 public void validate()
 {
   
 }
 
 public void save2DB(SADADService parentService) throws SADADException
  {
    //-1--------------------Inserting Master Data-----------
    String rqUId  = parentService.billerSvcRq.RqUID;
    String insertStmt = "insert into RECONC_MASTER (requid, prc_dt, collect_pmt_amt, recon_pmt_amt, unrecon_pmt_amt, trans_fees) "
                            +"values ('"+rqUId+"', to_date ('"+this.PrcDt.replace('T',' ')+"', 'yyyy-mm-dd hh24:mi:ss'),'"+this.CollectPmtAmt+"','"+this.ReconPmtAmt+"','"+this.UnReconPmtAmt+"','"+this.TransFees+"')";
     Statement stmt = null;
    try{
       stmt = parentService.con.createStatement();
       stmt.execute(insertStmt);
       }
    catch(SQLException sqle) {throw new SADADException("Unable to execute : \n" + insertStmt +"\n Due To : "+ sqle ,sqle,0);}
    
    //-2--------------------Inserting Details Data-----------
    String detailInsert = "";
    for (int i = 0 ; i< this.PmtBankRecs.size() ; i++)
    { 
      PmtBankRec pmtBankRec = (PmtBankRec)this.PmtBankRecs.elementAt(i);
      for (int j=0 ; j< pmtBankRec.PmtBranchRecs.size() ; j++)
      {
       PmtBranchRec pmtBranchRec = (PmtBranchRec)pmtBankRec.PmtBranchRecs.elementAt(j);
       for (int k=0 ; k< pmtBranchRec.pmtRecs.size() ; k++)
       {
        PmtRec pmtRec = (PmtRec) pmtBranchRec.pmtRecs.elementAt(k);
        String billnumber = ( pmtRec.pmtInfo.BillNumber ==null  || pmtRec.pmtInfo.BillNumber.equals(""))? pmtRec.pmtInfo.accountId.BillingAcct : pmtRec.pmtInfo.BillNumber ; 
        String pmt_id_type_1 = "", pmt_id_1 = "" , pmt_id_type_2 ="", pmt_id_2 = ""; 
        //--------------Trying getting the payment transId's----------------
        try{
        PmtTransId pmtTransId = (PmtTransId)pmtRec.pmtTransIds.elementAt(0);
        pmt_id_type_1 = pmtTransId.PmtIdType;
        pmt_id_1 = pmtTransId.PmtId;
        }
        catch (Exception e){}
        try{
        PmtTransId pmtTransId = (PmtTransId)pmtRec.pmtTransIds.elementAt(1);
        pmt_id_type_2 = pmtTransId.PmtIdType;
        pmt_id_2 = pmtTransId.PmtId;
        }
        catch (Exception e){}
        
        insertStmt = "insert into reconc_details (rquid, bank_id, branch_code, pmt_id_type_1, pmt_id_1, pmt_id_type_2, pmt_id_2, pmt_status_code, cur_amt, prc_dt, due_dt, bill_number, access_channel, pmt_method, Billing_ACC)"
                        + "Values ('"+ rqUId+"'"
                                     + ",'" + pmtBankRec.BankId+"'"
                                     + ",'" + pmtBranchRec.BranchCode+"'"
                                     + ",'" + pmt_id_type_1+"'"
                                     + ",'" + pmt_id_1+"'"
                                     + ",'" + pmt_id_type_2+"'"
                                     + ",'" + pmt_id_2+"'"
                                     + ",'" + pmtRec.pmtStatus.PmtStatusCode +"'"
                                     + ",'"+pmtRec.pmtInfo.curAmt +"'"
                                     + "," + "to_date('"+pmtRec.pmtInfo.PrcDt.replace('T' ,' ') + "' , 'yyyy-mm-dd hh24:mi:ss')"
                                     + "," + "to_date('"+pmtRec.pmtInfo.DueDt.replace('T' ,' ') + "' , 'yyyy-mm-dd hh24:mi:ss')"
                                     + ",'"+billnumber +"'"
                                     + ",'"+pmtRec.pmtInfo.AccessChannel +"'"
                                     + ",'"+pmtRec.pmtInfo.PmtMethod +"'"
                                     + ",'"+pmtRec.pmtInfo.accountId.BillingAcct +"'"                                     
                                     + ")";   
        try{
        stmt.execute(insertStmt);
        }
        catch(SQLException sqle) {throw new SADADException("Unable to execute : \n" + insertStmt +"\n Due To : "+ sqle ,sqle,0);}
       }
        
      }

    }
    
  }
/*
  public String toXMLString()
  {
    String xMLString = "\n" +"<PmtBankRec>";
    xMLString+= "\n\t" + "<BankId>" + this.BankId + "</BankId>";
    xMLString+= "\n\t" + "<CurAmt>" + this.CurAmt + "</CurAmt>";
    for (int i= 0; i< this.PmtBranchRecs.size() ; i++ )
    {
      PmtBranchRec x= (PmtBranchRec) PmtBranchRecs.elementAt(i);
      xMLString+=x.toXMLString();  
    }
       
    xMLString += "\n" + "</PmtBankRec>";
    return xMLString;
  }
*/
}