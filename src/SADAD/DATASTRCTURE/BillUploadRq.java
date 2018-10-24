package SADAD.DATASTRCTURE;
import Support.*;
import java.util.*;
import java.sql.*;
import java.io.*;
import java.net.*;
import SADAD.*;
import org.w3c.dom.*;

public class BillUploadRq //extends SADADMessage
{
  static String serviceType = "INSR";
  public String billCategory ;
  public String timeStamp;
  public Vector billRecs = new Vector();
  
  private static final String billCategoryNodeName = "BILLCATEGORY";
  private static final String timeStampNodeName = "TIMESTAMP";
  private static final String billRecNodeName = "BILLREC";
  Connection con;

   protected  void validate()throws Exception
   {
     
   }
   
  public BillUploadRq(Node billUploadRqNode, Connection m_con ) throws Exception
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
          if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(billRecNodeName)  )
          {
             this.billRecs.addElement(new BillRec(firstLevelNode, this.con));
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
                      if ( ! billCategoryFound  &&  nodeName1.toUpperCase().equals(billCategoryNodeName))
                      {
                        this.billCategory = nodeValue2;
                        billCategoryFound = true;
                        continue;
                      }
                      
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
  public boolean isLoadedIntoEdge(SADADService parentPresServiceRq) throws Exception
  {
    boolean loaded = false;
    String RqUID = parentPresServiceRq.presSvcRq.RqUID;
    String qStmt = "select * from bill_upload_process where PROCESS_UID = '" + RqUID + "'" ;
    Statement stmt = this.con.createStatement();
    ResultSet rs = stmt.executeQuery(qStmt);
    loaded = rs.next();
    return loaded;
  }
  public void saveInDB(SADADService parentSADADService) throws Exception
  {
    String XMLfileName = parentSADADService.fileName;
    String insertStmt = "";
    String RqUID = parentSADADService.presSvcRq.RqUID;
    Statement stmt;
    try{ this.con.setAutoCommit(false);
         stmt = this.con.createStatement();}
    catch (Exception e) {throw new Exception ("Invalid Connection to DB");}
    //---------1--Record the Process in the Edge System (without commit)
    try
      {
        String timeDataExtracted = this.timeStamp.replace('T',' ');
          //---------1.1- Inserting Master date
          insertStmt = "insert into bill_upload_process (PROCESS_UID,Bill_Extraction_Date,file_name , dummy_col)"
                + "values ('"+RqUID.trim()+"',to_Date('"+timeDataExtracted.trim()+"','yyyy-mm-dd hh24:mi:ss' ) , '"+XMLfileName+"', 'Waiting')";
          stmt.execute(insertStmt);
      }
      catch (Exception e ) 
      { throw new SADADException("Unable to Record BillUpload Process in Edge System Due to:\n " + e.getMessage() +"While Executing the following SQL \n" + insertStmt ,e,1);}
      try
      {
          //---------1.2- Inserting detail date
          BillRec billRec;
          for (int i =0 ; i<this.billRecs.size() ; i++)
          {
            billRec = (BillRec)this.billRecs.elementAt(i);
            String billNumber = billRec.billInfo.BillNumber;
            String billAmount = billRec.billInfo.ammountDue;
            insertStmt = "insert into bill_upload_process_det(bill_id,process_uid) values('"+billNumber+"','"+RqUID+"') ";
            stmt.execute(insertStmt);
          }
        }
    
    catch(Exception e)
    { 
      con.rollback();
      this.con.setAutoCommit(true); 
      throw new SADADException("Unable to Record BillUpload Process Det in Edge System Due to " + e.getMessage()+"\nWhile Executing the following SQL \n" + insertStmt,e,1);
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
    String xMLString = "\n" + "<BillUploadRq>" ;
    
    xMLString += "\n\t" + "<BillCategory>"  + this.billCategory  + "</BillCategory>" ;
    xMLString += "\n\t" + "<ServiceType>"  + serviceType  + "</ServiceType>" ;
    xMLString += "\n\t" + "<TimeStamp>" + this.timeStamp + "</TimeStamp>" ;
    //---------------
    for (int i=0;i<billRecs.size(); i++)
    {
      BillRec billRec = (BillRec) billRecs.elementAt(i);
      xMLString +="\n\t" +billRec.toXMLString();
    } 
    //--------------
    xMLString += "\n" + "</BillUploadRq>" ;
    return xMLString;
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