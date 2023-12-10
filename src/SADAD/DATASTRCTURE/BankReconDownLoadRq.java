package SADAD.DATASTRCTURE;
import SADAD.*;
import SADAD.DATASTRCTURE.*;
import java.util.*;
import java.sql.*;
import org.w3c.dom.*;
import java.net.*;


public class BankReconDownLoadRq extends SADADMessage
{ 
    
  public static final String recordCountNodeName = "RECORDCOUNT";
  public static final String PmtRecNodeName = "PMTREC";
  public static final String PrcDtName = "PRCDT";
  
  public String prcDt;
  public String recordCount;
  public Vector pmtRecs = new Vector();    // Payment records
  
  public BankReconDownLoadRq(String xmlString ,boolean  validateSchema , Connection con) throws Exception
  {
    super(xmlString , validateSchema , con);
    setParameters();
  }
  public BankReconDownLoadRq(String XmlFilePath , Connection con,boolean  validateSchema) throws Exception
  {
    super(XmlFilePath, con, validateSchema);
    setParameters();
  }
  
  public BankReconDownLoadRq(URL XmlFileURL , Connection con,boolean validateSchema) throws Exception
  {
    super(XmlFileURL ,  con, validateSchema);
    setParameters();
  }
  
  public BankReconDownLoadRq(Node pmtNotifyUploadRqNode , Connection con) throws Exception
  {
    super(pmtNotifyUploadRqNode , con );
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
                        if (nodeName1.toUpperCase().equals(this.PrcDtName))
                        {
                          this.prcDt = nodeValue2;
                        }
                        
                    }
                  
                 }
           }
         }
    validate();
    this.logAction("BankReconDownLoadRq  Message Have been Parsed Successfully - Found (" + this.pmtRecs.size() + ") Payments From SADAD" );
    }
    catch (Exception e) 
    { 
    e.printStackTrace();
    throw e;}
}
  protected void validate() throws Exception
  {
    //-----------------------Check Not Found data For Mandatory Data -----------
 
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
   //----To be implemented 
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
  xmlString  += "\n" + "<BankReconDownLoadRq>";
  xmlString  += "\n" + "<PrcDt>" +this.prcDt +"</PrcDt>";
  for (int i = 0 ; i< this.pmtRecs.size() ; i++)
  {
  PmtRec x = (PmtRec)this.pmtRecs.elementAt(i) ;
  xmlString  += "\n" + x.toXMLString();
  }
  xmlString  += "\n" + "</BankReconDownLoadRq>";
  return xmlString;
    
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
   BankReconDownLoadRq xx= new  BankReconDownLoadRq(xmlFileURL, con,false);
   //xx.uplaodIntoEdge(false,true);   
   //xx.logAction("testing ....");
    System.out.print("sfasdf");
   //xx.save2DB();
  }
   catch(Exception e)    {System.out.print(e.getMessage());
   }
    
  }
}

