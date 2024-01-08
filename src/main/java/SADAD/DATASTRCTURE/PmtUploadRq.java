package SADAD.DATASTRCTURE;
import Support.*;
import java.util.*;
import java.sql.*;
import java.io.*;
import SADAD.*;
import org.w3c.dom.*;
import java.net.*;

public class PmtUploadRq //extends SADADMessage
{
  static String serviceType = "INSR";
  public String timeStamp;
  public Vector pmtRecs = new Vector();
  
  private static final String timeStampNodeName = "TIMESTAMP";
  private static final String pmtRecNodeName = "PMTREC";
  Connection con;
   public void save2DB(SADADService parentService) throws Exception
   {
   String XMLfileName = parentService.fileName;
    String insertStmt = "";
    String RqUID = parentService.billerSvcRq.RqUID;
    Statement stmt;
    try{ this.con.setAutoCommit(false);
         stmt = this.con.createStatement();}
    catch (Exception e) {throw new Exception ("Invalid Connection to DB");}
    //---------1--Record the Process in the DB System (without commit)
    try
      {
        String timeDataExtracted = this.timeStamp.replace('T',' ');
          //---------1.1- Inserting Master date
          insertStmt = "insert into Pmt_upload_process (PROCESS_UID,Pmt_Extraction_Date,file_name)"
                + "values ('"+RqUID.trim()+"',to_Date('"+timeDataExtracted.trim()+"','yyyy-mm-dd hh24:mi:ss'),'"+XMLfileName+"')";
          stmt.execute(insertStmt);
      }
      catch (Exception e ) 
      { throw new SADADException("Unable to Record PmtUpload Process in DB System Due to:\n " + e.getMessage() +"While Executing the following SQL \n" + insertStmt ,e,1);}
      try
      {
          //---------1.2- Inserting detail date
          PmtRec pmtRec;
          for (int i =0 ; i<this.pmtRecs.size() ; i++)
          {
            pmtRec = (PmtRec)this.pmtRecs.elementAt(i);
            PmtTransId pmtTRansId = (PmtTransId) pmtRec.pmtTransIds.elementAt(0);
            String pmtID = pmtTRansId.PmtId;
          
            insertStmt = "insert into pmt_upload_process_det(pmt_id,process_uid) values('"+pmtID+"','"+RqUID+"') ";
            stmt.execute(insertStmt);
          }
        }
    
    catch(Exception e)
    { 
      con.rollback();
      this.con.setAutoCommit(true); 
      throw new SADADException("Unable to Record PmtUpload Process Det in Edge System Due to " + e.getMessage()+"\nWhile Executing the following SQL \n" + insertStmt,e,1);
    }
   }
   protected  void validate()throws Exception
   {
     
   }
   
  public PmtUploadRq(Node billUploadRqNode, Connection m_con) throws Exception
  { 
     setParameters(billUploadRqNode);
     this.con = m_con;
  }
  protected void setParameters(Node billUploadRqNode ) throws Exception 
  {
    NodeList childNodes = billUploadRqNode.getChildNodes();
    int size = childNodes.getLength();
    String nodeName1 = "";
    String nodeValue1  = "";
    short nodeType1  = 0;
    
    String nodeName2 = "";
    String nodeValue2  = "";
    short nodeType2  = 0;
    
    boolean billCategoryFound = false;
    boolean timeStampFound = false;
    try 
    {
      for (int i=0 ; i< size; i++)
        {
          Node firstLevelNode = childNodes.item(i);
          nodeName1  = firstLevelNode.getNodeName();
          nodeValue1 = firstLevelNode.getNodeValue();
          nodeType1 = firstLevelNode.getNodeType();
               //-------Setting Complex Nodes First------------------------------------------------------
          if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(pmtRecNodeName)  )
          {
             this.pmtRecs.addElement(new PmtRec(firstLevelNode,con));
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
                      if (! timeStampFound &&  nodeName1.toUpperCase().equals(this.timeStampNodeName))
                      {
                        this.timeStamp = nodeValue2;
                        timeStampFound = true;
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
    public boolean isLoadedIntoEdge(SADADService parentSADADService) throws Exception
  {
    boolean loaded = false;
    String RqUID = parentSADADService.billerSvcRq.RqUID;
    String qStmt = "select * from pmt_upload_process  where PROCESS_UID = '" + RqUID + "'" ;
    Statement stmt = this.con.createStatement();
    ResultSet rs = stmt.executeQuery(qStmt);
    loaded = rs.next();
    return loaded;
  }
  public void sendToSADAD(SADADService parentBillerServiceRq) throws Exception
  {
    //---------1--Record the Process in the Edge System
    String timeDataExtracted = this.timeStamp.replace('T',' ');
    String RqUID = parentBillerServiceRq.billerSvcRq.RqUID;
    String XMLfileName = parentBillerServiceRq.fileName;
    this.con.setAutoCommit(false);
    try
    {//---------1.1- Inserting Master date
      Statement stmt = this.con.createStatement();
      String insertStmt = "insert into pmt_upload_process (PROCESS_UID,pmt_Extraction_Date,file_name)"
            + "values ('"+RqUID.trim()+"',to_Date('"+timeDataExtracted.trim()+"','yyyy-mm-dd hh24:mi:ss'),'"+XMLfileName+"')";
      stmt.execute(insertStmt);
      //---------1.2- Inserting detail date
      PmtRec pmtRec;
      for (int i =0 ; i<this.pmtRecs.size() ; i++)
      {
        pmtRec = (PmtRec)this.pmtRecs.elementAt(i);
        
        PmtTransId   firstPmtTranId= (PmtTransId) pmtRec.pmtTransIds.elementAt(0);
        insertStmt = "insert into pmt_upload_process_det(pmt_id,process_uid) values('"+firstPmtTranId.PmtId+"','"+RqUID.trim()+"') ";
        stmt.execute(insertStmt);
      }
    }
    catch(Exception e)
    {throw new SADADException("The File "+XMLfileName+" May already Send Before Or Due To " + e.getMessage(),e,1);}
    //---------2--Send To SADAD-------
    String PaymentUploadEndPoint;
    String response;
    boolean sendResult= true;
    try{
     //----------------2.1----Getting The Payment Upload URL From DB-------
     PaymentUploadEndPoint = SADADSettings.getSystemParmValue("PaymentUploadEndPoint",this.con);
     //---------------2.2-----Send --------------
     HttpPoster httpPoster = new  HttpPoster(PaymentUploadEndPoint, "", "userName", "pass");
     String messageAsString =  parentBillerServiceRq.toXMLString();
     httpPoster.postRequest(messageAsString);
     response = httpPoster.readResponse();
     //------------- 2.3 Check The Bill Upload Response Message ---------
     //---------Not Yet Implemented---
     sendResult = true; //--------should be parsed from the response----
     System.out.print(response);
     con.commit();
     con.setAutoCommit(true);
    }
    catch (Exception e)
    {
      //-------Roll Back the Process of Recording into Edge System
      con.rollback();
      throw new SADADException("Unable to Send the Bill Data due to the following Error : " + e.getMessage(),e,1);
    }
    
  }
 
 /**
   * This Method Used To Set The Bill Records Objects by its values from DB---
   * Not Yet Implemented-----
   */
  private void setAllBillRecs(Connection con) throws SADADException
  {
    try{
    Statement stmt = con.createStatement();
    String qStatmet= "select * from comp.SADAD_Bill_REquest";
    ResultSet rs = stmt.executeQuery(qStatmet);
      while(rs.next())
      {
      
      
      }
    }
    catch (Exception e){}
    
  
  }
     public String toXMLString()
  {
    String xMLTag = "\n" + "<PmtUploadRq>" ;
    xMLTag += "\n" + "<TimeStamp>" ;
    xMLTag += "\n" + this.timeStamp ;
    xMLTag += "\n" + "</TimeStamp>" ;
    for (int i = 0 ; i< this.pmtRecs.size() ; i++)
    {
        PmtRec pmtRec = (PmtRec) pmtRecs.elementAt(i);
        xMLTag += "\n" + pmtRec.toXMLString();
    }
    xMLTag += "\n" + "</PmtUploadRq>" ;
    return xMLTag;
  }
  
  public void writeToFile(String filePath) throws IOException
  {
    String xmlData = this.toXMLString();
     
   //----------wirite To File System
   PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filePath)));
   pw.write(xmlData.toString());    
   pw.close();
   //----------Closing The Streams--------
   
    
  }
}