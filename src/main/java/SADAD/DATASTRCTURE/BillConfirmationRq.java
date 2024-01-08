package SADAD.DATASTRCTURE;
import SADAD.*;
import java.sql.*;
import java.util.*;
import org.w3c.dom.*;
import java.net.*;

public class BillConfirmationRq extends SADADMessage
{
  public final static String rootName = "BillConfirmationRq";
  public static final String asyncRqUIDNodeName = "ASYNCRQUID";
  public static final String uploadDtNodeName = "UPLOADDT";
  public static final String prcDtNodeName = "PRCDT";
  public static final String billCategoryNodeName = "BILLCATEGORY";
  public static final String serviceTypeNodeName = "SERVICETYPE";
  public static final String sucessRecordCountNodeName = "SUCCESSRECORDCOUNT"   ; //SuccessRecordCount
  public static final String errorRecordCountNodeName = "ERRORRECORDCOUNT";
  public static final String billInfoErrorNodeName = "BILLINFOERROR";
  
  public  String asyncRqUID;
  public  String  uploadDt;
  public  String  prcDt;
  public  String billCategory;
  public  String serviceType = "INS";
  public  String sucessRecordCount;
  public  String errorRecordCount;
  public Vector billInfoError = new Vector();
  
  
  public BillConfirmationRq(String XmlFilePath , Connection m_con,boolean validateSchema) throws Exception
  {
    super(XmlFilePath, m_con, validateSchema);
    this.con = m_con;
    setParameters();
    
  }
   public BillConfirmationRq(URL XmlFileURL , Connection m_con,boolean validateSchema) throws Exception
  {
    super(XmlFileURL ,  m_con, validateSchema);
    this.con = m_con;
    setParameters();
    
  }
    public BillConfirmationRq( Node BillConfirmationRqNode , Connection m_con) throws Exception
  {
    super(BillConfirmationRqNode);
    this.con = m_con;
    setParameters(BillConfirmationRqNode);
 
  }
  
   protected void setParameters() throws Exception 
  {
    setParameters(this.docRootNode);
  }
  private void setParameters(Node billConfirmationRqNode ) throws Exception 
  {
    NodeList childNodes = billConfirmationRqNode.getChildNodes();
    int size = childNodes.getLength();
    String nodeName1 = "";
    String nodeValue1  = "";
    short nodeType1  = 0;
    
    String nodeName2 = "";
    String nodeValue2  = "";
    short nodeType2  = 0;
    //----The following booleans to speed up the search process-----
    boolean asyncRqUIDFound = false;
    boolean uploadDtFound = false;
    boolean dueDtFound = false;
    boolean prcDtFound = false;
    boolean billCategoryFound = false;
    boolean serviceTypeFound = false;
    boolean sucessRecordCountFound = false;    
    boolean errorRecordCountFound = false;
    boolean billInfoErrorFound = false;
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
          if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(this.billInfoErrorNodeName)  )
              {
                this.billInfoError.addElement(new BillInfoError(firstLevelNode,this.con));
                billInfoErrorFound= true;
              }
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
                      if ( !asyncRqUIDFound &&  nodeName1.toUpperCase().equals(asyncRqUIDNodeName))
                      {
                        this.asyncRqUID = nodeValue2;
                        asyncRqUIDFound = true;
                        continue;
                      }
                      
                      if (! uploadDtFound &&  nodeName1.toUpperCase().equals(uploadDtNodeName))
                      {
                        this.uploadDt = nodeValue2;
                        uploadDtFound = true;
                        continue;
                      }
                      
                       if (! prcDtFound &&  nodeName1.toUpperCase().equals(prcDtNodeName))
                      {
                        this.prcDt = nodeValue2;
                        prcDtFound = true;
                        continue;
                      }
                      
                       if (! billCategoryFound &&  nodeName1.toUpperCase().equals(billCategoryNodeName))
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

                       if (! sucessRecordCountFound &&  nodeName1.toUpperCase().equals(sucessRecordCountNodeName))
                      {
                        this.sucessRecordCount = nodeValue2;
                        sucessRecordCountFound = true;
                        continue;
                      } 
                      if (! errorRecordCountFound &&  nodeName1.toUpperCase().equals(errorRecordCountNodeName))
                      {
                        this.errorRecordCount = nodeValue2;
                        errorRecordCountFound = true;
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
  public void validate()
  {
    
  }
    public void save2DB(SADADService parentService) throws SADADException
  {
    try{
    Statement  stmt = con.createStatement();
    String insertStmt = "insert into bill_conf_master (rquid ,asyncRqUID, sucessRecordCount , errorRecordCount  ) "
            + " values ('"+parentService.presSvcRq.RqUID+"','"+this.asyncRqUID+"','"+this.sucessRecordCount+"' , '"+this.errorRecordCount+"')";
    stmt.execute(insertStmt); // to be calculated 
    for (int i =0 ; i< this.billInfoError.size() ; i++)
    {
      BillInfoError x = (BillInfoError)this.billInfoError.elementAt(i);
      x.save2DB(parentService);
    }
    stmt.close();}
    catch (Exception e ){
    System.out.print(e.getMessage());
    throw new SADADException("Faild To Record Bill Confirmation Rq "+ e.getMessage(),e,1);
    }
  }
   public String toXMLString()
  {
  String xmlString= "";
  xmlString  += "\n" + "<BillConfirmationRq>";
  xmlString  += "\n\t" + "<AsyncRqUID>" + this.asyncRqUID + "</AsyncRqUID>";
  xmlString  += "\n\t" + "<UploadDt>" + this.uploadDt + "</UploadDt>";
  xmlString  += "\n\t" + "<PrcDt>" + this.prcDt + "</PrcDt>";
  xmlString  += "\n\t" + "<SuccessRecordCount>" + this.sucessRecordCount + "</SuccessRecordCount>";
  xmlString  += "\n\t" + "<ErrorRecordCount>" + this.errorRecordCount + "</ErrorRecordCount>";
  for (int i =0 ; i< this.billInfoError.size() ; i++)
  {
    BillInfoError x = (BillInfoError)this.billInfoError.elementAt(i);
    xmlString  += "\n\t" + x.toXMLString();
  }
  xmlString  += "\n" + "</BillConfirmationRq>";
  return xmlString;
    
  }

  public static void main(String[] args) 
  {
    try
    {
      BillConfirmationRq xx =new BillConfirmationRq("file:///C:/Edge_Work/SADAD/public_html/SADAD_Messages/FromSADAD/BillConfirmationRq/BillConfirmationRq.xml",null,false);  
      System.out.print("asdasd");
    }
    catch (Exception e) {    System.out.print(e.getMessage()); }
  }
}