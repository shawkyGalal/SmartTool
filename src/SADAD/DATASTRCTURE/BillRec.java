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
  
public class BillRec 
{
  public String supercedeBillNumber;
  public String billStatusCode;
  public Vector billPmtStatus = new Vector();
  public CustId custId ;
  public Msg msg;
  public BillInfo billInfo;
  
  private final String supercedeBillNumberNodeName="SUPERCEDBILLNUMBER";    //simple Node
  private final String billStatusCodeNodeName="BILLSTATUSCODE";         //simple Node
  private final String billPmtStatusNodeName="BILLPMTSTATUS";          //Complex Node
  private final String custIdNodeName="CUSTID";                 //Complex Node
  private final String msgNodeName="MSG";                    //Complex Node
  private final String billInfoNodeName="BILLINFO";               //Complex Node
  public Connection con;
  
  public BillRec(Node billRecNode , Connection m_con ) throws Exception
  { 
     this.con = m_con;
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
    
    boolean supercedeBillNumberFound = false;
    boolean billStatusCodeFound = false;
    boolean custIdFound = false;
    boolean msgFound = false;
    boolean billInfoFound = false;
    
    try 
    {
      for (int i=0 ; i< size; i++)
        {
          Node firstLevelNode = childNodes.item(i);
          nodeName1  = firstLevelNode.getNodeName();
          nodeValue1 = firstLevelNode.getNodeValue();
          nodeType1 = firstLevelNode.getNodeType();
               //-------Setting Complex Nodes First------------------------------------------------------
          //if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(billPmtStatusNodeName)  )
          //{
             //this.billPmtStatus.addElement(new BillPmtStatus(firstLevelNode));// Currently Node Used
          //}
          if (! custIdFound &&  nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(custIdNodeName)  )
          {
             this.custId= new CustId(firstLevelNode);
             custIdFound = true;
             continue;
          }
          else if (! msgFound && nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(msgNodeName)  )
          {
             this.msg= new Msg(firstLevelNode);
             msgFound = true;
             continue;
          }
          else if (! billInfoFound &&  nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(billInfoNodeName)  )
          {
             this.billInfo= new BillInfo(firstLevelNode, this.con);
             billInfoFound = true;
             continue;
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
                      if ( ! supercedeBillNumberFound  &&  nodeName1.toUpperCase().equals(supercedeBillNumberNodeName))
                      {
                        this.supercedeBillNumber = nodeValue2;
                        supercedeBillNumberFound = true;
                        continue;
                      }
                      
                      if (! billStatusCodeFound &&  nodeName1.toUpperCase().equals(this.billStatusCodeNodeName))
                      {
                        this.billStatusCode = nodeValue2;
                        billStatusCodeFound = true;
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
  public String toXMLString()
  {
    String xMLString = "\n" +"<BillRec>";
    xMLString+= "\n\t" + "<BillStatusCode>" + this.billStatusCode + "</BillStatusCode>";
    xMLString+= "\n\t" +  this.custId.toXMLString() ;

    for (int i= 0; i< billPmtStatus.size() ; i++ )
    {
       //xMLTag+=(BillPmtStatus) billPmtStatus.elementAt(i).toXMLTag();
    }
    //xMLTag+= custId.toXMLTag();
    xMLString+= msg.toXMLString();
    xMLString+= billInfo.toXMLString();
    xMLString += "\n" + "</BillRec>";
    return xMLString;
  }

}