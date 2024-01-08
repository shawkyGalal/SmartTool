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
  
public class SARIERq 
{
  public String TransID;
  public String PrcDt;
  public Vector SARIEInfo = new Vector();

  
  private final String TransIDName="TRANSID";    //simple Node
  private final String PrcDtName="PRCDT";         //simple Node
  private final String SARIEInfoName="SARIEINFO";          //Complex Node

  public SARIERq(Node SARIERqNode) throws Exception
  { 
     setParameters(SARIERqNode);
  }
  protected void setParameters(Node SARIERqNode ) throws Exception 
  {
    NodeList childNodes = SARIERqNode.getChildNodes();
    int size = childNodes.getLength();
    String nodeName1 = "";
    String nodeValue1  = "";
    short nodeType1  = 0;
    
    String nodeName2 = "";
    String nodeValue2  = "";
    short nodeType2  = 0;
    
    boolean SARIEInfoFound = false;
    boolean TransIDNameFound = false;
    boolean PrcDtFound = false;
 
    
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
          if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(SARIEInfoName)  )
          {
             this.SARIEInfo.addElement( new SARIEInfo(firstLevelNode));
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
                      if ( ! TransIDNameFound  &&  nodeName1.toUpperCase().equals(TransIDName))
                      {
                        this.TransID = nodeValue2;
                        TransIDNameFound = true;
                        continue;
                      }
                      
                      if (! PrcDtFound &&  nodeName1.toUpperCase().equals(this.PrcDtName))
                      {
                        this.PrcDt = nodeValue2;
                        PrcDtFound = true;
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
/*
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
*/
}