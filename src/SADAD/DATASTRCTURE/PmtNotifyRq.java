package SADAD.DATASTRCTURE;
import SADAD.*;
import SADAD.DATASTRCTURE.*;
import java.util.*;
import java.sql.*;
import org.w3c.dom.*;
import java.net.*;


public class PmtNotifyRq extends SADADMessage
{ 
    
  public static final String recordCountNodeName = "RECORDCOUNT";
  public static final String PmtRecNodeName = "PMTREC";
  
  public String recordCount;
  public Vector pmtRecs = new Vector();    
  public Connection con ;
  
  public PmtNotifyRq(String xmlString ,boolean  validateSchema , Connection m_con) throws Exception
  {
    super(xmlString , validateSchema , m_con);
    this.con = m_con;
    setParameters();
  }
  public PmtNotifyRq(String XmlFilePath , Connection m_con,boolean  validateSchema) throws Exception
  {
    super(XmlFilePath, m_con, validateSchema);
    this.con = m_con;
    setParameters();
  }
  
  public PmtNotifyRq(URL XmlFileURL , Connection m_con,boolean validateSchema) throws Exception
  {
    super(XmlFileURL ,  m_con, validateSchema);
    this.con = m_con;
    setParameters();
  }
  
  public PmtNotifyRq(Node pmtNotifyUploadRqNode , Connection m_con) throws Exception
  {
    super(pmtNotifyUploadRqNode , m_con );
    this.con = m_con;
    setParameters();
  }
  
protected void setParameters() throws Exception 
{
    NodeList childNodes = docRootNode.getChildNodes();
    int size = childNodes.getLength();
    String nodeName1 = "";
    String nodeValue1  = "";
    short nodeType1  = 0;
    
    String nodeName2 = "";
    String nodeValue2  = "";
    short nodeType2  = 0;
    try 
    {
      for (int i=0 ; i< size; i++)
        {
          Node firstLevelNode = childNodes.item(i);
          nodeName1  = firstLevelNode.getNodeName();
          nodeValue1 = firstLevelNode.getNodeValue();
          nodeType1 = firstLevelNode.getNodeType();
          
          if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(PmtRecNodeName)  )
              {
                pmtRecs.addElement(new PmtRec(firstLevelNode, this.con));
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
                        if (nodeName1.toUpperCase().equals(recordCountNodeName))
                        {
                          recordCount = nodeValue2;
                        }
                    }
                  
                 }
           }
         }
    validate();
    this.logAction("Payment Notification Message Have been Parsed Successfully - Found (" + this.pmtRecs.size() + ") Payments From SADAD" );
    }
    catch (Exception e) 
    { 
    e.printStackTrace();
    throw e;}
}
  protected void validate() throws Exception
  {
    //-----------------------Check Not Found data For Mandatory Data -----------
  
  /* 
    //-----------Check Data Size-----------
     if (Integer.parseInt(recordCount.trim()) != this.pmtRecs.size())
    {
      throw new SADADException("Payment Records Count ("+recordCount+") Does Not match Actual Data in the XML File ("+pmtRecs.size()+")",null,1);
    }
  */
  /*
    //-----------Check Non repeated payment Records------
    for (int i =0 ; i< this.pmtRecs.size() ; i++)
    {
      PmtRec outerPmtRec = (PmtRec) this.pmtRecs.elementAt(i);
      for (int j = i+1 ; j< this.pmtRecs.size(); j++ )
      {
        PmtRec innerPmtRec = (PmtRec) this.pmtRecs.elementAt(j);
        if (outerPmtRec.equals(innerPmtRec))
        {
          throw new SADADException ("Payment Rec No " + String.valueOf(i+1) +" repeated in Payment Rec No " + String.valueOf(j+1) +"(Payment ID ="+outerPmtRec.PmtId+" )",null,1);
        }
      }
    }
   */
  }
     private void checkIfAlreadyLoaded(SADADService parentBillerServiceRq) throws SADADException
  {
    boolean alreadyExist = false;
    String queryStmt = "select req_uid from pmt_requests where req_uid = '"+parentBillerServiceRq.billerSvcRq.RqUID +"'";
    try
    {
      Statement stmt = this.con.createStatement();
      ResultSet rs = stmt.executeQuery(queryStmt);
      while(rs.next())
      {
        alreadyExist = true;
        break;
      }
        stmt.close();
        rs.close();
    }
    catch (Exception ex) {throw new SADADException("Unable to Check if Rquest No ." +parentBillerServiceRq.billerSvcRq.RqUID + "have been loaded Into edge system before or not",ex,1);}

      
    if (alreadyExist)
      {
        throw new SADADException ("Payment Notification Request with RqUID = "+parentBillerServiceRq.billerSvcRq.RqUID +" already loaded before",null,1);
      }
  }

  public Vector  uplaodIntoEdge(SADADService parentBillerServiceRq,  boolean overRideRqIfExist, boolean allOrNone) throws Exception
  {
   
    Vector ex = new Vector();
    if (overRideRqIfExist)
    {
     deletePmtRquest(parentBillerServiceRq);
    }
    else
    {
      checkIfAlreadyLoaded(parentBillerServiceRq);
    }
    this.con.setAutoCommit(false);
    Statement stmt = con.createStatement();
    
    //----------Inserting Master data---
    String insertStmt = "";
    try
    {
      insertStmt = "insert into pmt_requests ( req_uid, prc_date, no_of_rec ) values ('" + parentBillerServiceRq.billerSvcRq.RqUID +"',"
                                                                                                 + "Sysdate" +","
                                                                                                 + this.pmtRecs.size() 
                                                                                            +")";
      stmt.execute(insertStmt);
    }
    catch (Exception e)
    {
      stmt.close();
      throw new SADADException("Unable to Insert Payment Notification Master Data Due to : "+ e.getMessage() ,e,1);
    }
    //----------Inserting Details data---
    PmtRec pmtRec;
    for (int i= 0; i<pmtRecs.size();i++)
    {
      pmtRec = (PmtRec)pmtRecs.elementAt(i);
     try{
       pmtRec.uploadIntoEdge(parentBillerServiceRq.billerSvcRq.RqUID);
      }
      catch (Exception e)
      {
        int count = i+1;
        SADADException sadadEx = new SADADException("Error During Inserting Record Number " + count + " due To " +e.getMessage(),e,1);
        ex.addElement(sadadEx);
      }
    }
    
    if (ex.size()==0 || !allOrNone ) // No exception Done During Data loading  Or Commit Valid records only
    {
      con.commit();
    }
    else 
    {
     con.rollback();   
    }

    stmt.close();
       
    return ex;
  }
  

  protected void deletePmtRquest(SADADService parentBillerServiceRq ) throws SADADException
  {
    String delStmtDetails = "Delete from payment_notification where req_uid = '" + parentBillerServiceRq.billerSvcRq.RqUID +"'";
    String delStmtMaster = "Delete from pmt_requests where req_uid = '" + parentBillerServiceRq.billerSvcRq.RqUID +"'";
    Statement stmt= null;
    try
    {
      stmt = this.con.createStatement();
      stmt.execute(delStmtDetails);
      stmt.execute(delStmtMaster);

    }
    catch (Exception e)
    {
      try{stmt.close();} catch(Exception ex){}
      throw new SADADException("Unable to Delete Payment Notifications for RqUID= " + parentBillerServiceRq.billerSvcRq.RqUID + " Due to " + e.getMessage(),e,1);
    }
  }
  public String toXMLString()
  {
  String xmlString= "";
  xmlString  += "\n" + "<PmtNotifyRq>";
  for (int i = 0 ; i< this.pmtRecs.size() ; i++)
  {
  PmtRec pmtRec = (PmtRec)this.pmtRecs.elementAt(i) ;
  xmlString  += "\n" + pmtRec.toXMLString();
  }
  xmlString  += "\n" + "</PmtNotifyRq>";
  return xmlString;
    
  }
   public void save2DB(SADADService parentService) throws SADADException
  {
    try{
    Statement  stmt = con.createStatement();
    String insertStmt = "";
    // String insertStmt = "insert into Pmt_Notify_master (rquid ) "
    //        + " values ('"+parentService.billerSvcRq.RqUID+"')";
    //stmt.execute(insertStmt); // to be calculated 
    
    //-------Inserting Payment Details 
    for (int i =0 ; i< this.pmtRecs.size() ; i++)
    {
    PmtRec pmtRec = (PmtRec)this.pmtRecs.elementAt(i);
    String sptn = "";
    String bnkptn = "";
    PmtTransId AA = (PmtTransId)pmtRec.pmtTransIds.elementAt(0);
    PmtTransId BB = (PmtTransId)pmtRec.pmtTransIds.elementAt(1);
    
    if (AA.PmtIdType.toString().toUpperCase().equalsIgnoreCase("SPTN"))
    {
      sptn = AA.PmtId.toString();
    }
    else if (AA.PmtIdType.toString().toUpperCase().equalsIgnoreCase("BNKPTN"))
    {
      bnkptn = AA.PmtId.toString();
    }
    
    if (BB.PmtIdType.toString().toUpperCase().equalsIgnoreCase("SPTN"))
    {
      sptn = BB.PmtId.toString();
    }
    else if (BB.PmtIdType.toString().toUpperCase().equalsIgnoreCase("BNKPTN"))
    {
      bnkptn = BB.PmtId.toString();
    }    
    
    insertStmt = "insert into Pmt_Notify_Details (RqUID,SPTN, BNKPTN, PmtStatusCode , CurAmt , BillNumber ,BillingAcct   ) "
            + " values ('"+parentService.billerSvcRq.RqUID+"', '"+sptn+"', '"+bnkptn+"','"+pmtRec.pmtStatus.PmtStatusCode+"', '"+pmtRec.pmtInfo.curAmt+"', '"+pmtRec.pmtInfo.BillNumber+"', '"+pmtRec.pmtInfo.accountId.BillingAcct+"')";
    stmt.execute(insertStmt); 
 
    }
    
    stmt.close();}
    catch (Exception e ){
    System.out.print(e.getMessage());
    throw new SADADException("Faild To Record Payment Notification Message "+ e.getMessage(),e,1);
    }
  }
  /**
   *  This Method is to Check If this Rquest have been Loaded before into Edge system
   *  This check is based on the RqUID send from SADAD with the service request
   */

    public static void main(String[] args) 
  {
   try{ 
   Connection con = Support.ConnectionFactory.createConnection_old("192.1.1.8","edgedev","SADAD","SADAD");
   URL xmlFileURL = new URL("file:///C:/Edge_Work/SADAD/public_html/SADAD_Messages/FromSADAD/PmtNotifications/PmtNotifyUploadRqSample.xml");
   PmtNotifyUploadRq xx= new  PmtNotifyUploadRq(xmlFileURL, con,false);
   //xx.uplaodIntoEdge(false,true);   
   //xx.logAction("testing ....");
    System.out.print("sfasdf");
   //xx.save2DB();
  }
   catch(Exception e)    {System.out.print(e.getMessage());
   }
    
  }
}