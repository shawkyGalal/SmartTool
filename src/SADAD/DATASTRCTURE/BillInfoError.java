package SADAD.DATASTRCTURE;
import org.w3c.dom.*;
import java.util.*;
import java.sql.*;
import java.io.*;
import SADAD.*;


public class BillInfoError extends SADADMessage 
{
  //private final static String rootName = "BillInfoError";
  private static final String errorNodeName = "ERROR";
 // private static final String billInfoNodeName = "BILLINFO";
  private static final String billingAccNodeName = "BILLINGACCT";
  private static final String billerIdNodeName = "BILLERID";
  private static final String billCategoryNodeName = "BILLCATEGORY";
  private static final String serviceTypeNodeName = "SERVICETYPE";
  private static final String billCycleNodeName = "BILLCYCLE";
  private static final String billNumberNodeName = "BILLNUMBER";
  
  
  public ERROR error;
  public BillInfo billInfo ;
  public String billingAcct ;
  public String billerId;
  public String billCategory;
  public String serviceType;
  public String billCycle;  
  public String billNumber; 

  public BillInfoError( Node BillInfoErrorNode , Connection m_con) throws Exception
  {
    super(BillInfoErrorNode,m_con);
    this.con = m_con;//
    setParameters();
  }
  
  protected void setParameters() throws Exception 
  {
    setParameters(this.docRootNode);
  }
  
  private void setParameters(Node billInfoErrorXMLNode ) throws Exception 
  {
    NodeList childNodes = billInfoErrorXMLNode.getChildNodes();
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
         // if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(billInfoNodeName)  )
         //     {
         //       billInfo = new BillInfo(firstLevelNode, this.con);
         //     }
          if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(errorNodeName)  )
              {
                error = new ERROR(firstLevelNode , this.con);
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
                        if (nodeName1.toUpperCase().equals(this.billerIdNodeName))
                        {
                          this.billerId = nodeValue2;
                        }
                        if (nodeName1.toUpperCase().equals(this.billingAccNodeName))
                        {
                          this.billingAcct = nodeValue2;
                        }
                        
                        if (nodeName1.toUpperCase().equals(this.billCategoryNodeName))
                        {
                          this.billCategory = nodeValue2;
                        }

                        if (nodeName1.toUpperCase().equals(this.serviceTypeNodeName))
                        {
                          this.serviceType = nodeValue2;
                        }
                        if (nodeName1.toUpperCase().equals(this.billCycleNodeName))
                        {
                          this.billCycle = nodeValue2;
                        }
                        if (nodeName1.toUpperCase().equals(this.billNumberNodeName))
                        {
                          this.billNumber = nodeValue2;
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
  protected void save2DB(SADADService parentService)  throws SADADException
  {
        try{
    Statement  stmt = con.createStatement();
    String insertStmt = "insert into bill_conf_details (rquid ,bill_no, error_code, error_desc , BILLINGACC ) "
            + " values ('"+parentService.presSvcRq.RqUID+"','"+this.billNumber+"','"+this.error.errorCode+"' , '"+this.error.errorMsg  +"', '"+this.billingAcct+"')";
    stmt.execute(insertStmt); // to be calculated 
    stmt.close();}
    catch (Exception e ){
    System.out.print(e.getMessage());
    throw new SADADException("Faild To Record Bill Info Details "+ e.getMessage(),e,1);
    }

  }
    public String toXMLString()
  {
  String xmlString= "";
  xmlString  += "\n" + "<BillInfoError>";
  xmlString  += "\n\t" + this.error.toXMLString();
   
  xmlString  += "\n" + "<BillingAcct>"+this.billingAcct+"</BillingAcct>";
  xmlString  += "\n" + "<BillerId>"+ this.billerId+"</BillerId>";
  xmlString  += "\n" + "<BillCategory>"+this.billCategory+"</BillCategory>";
  xmlString  += "\n" + "<ServiceType>"+this.serviceType+"</ServiceType>";
  
  xmlString  += "\n" + "</BillInfoError>";
  return xmlString;
    
  }

}