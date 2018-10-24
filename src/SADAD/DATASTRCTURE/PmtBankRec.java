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
  
public class PmtBankRec 
{
  public String BankId = "";
  public String CurAmt = "";
  public Vector PmtBranchRecs = new Vector();

  private final String BankIdNodeName="BANKID";    //simple Node
  private final String CurAmtNodeName="CURAMT";         //simple Node
  private final String PmtBranchRecNodeName="PMTBRANCHREC";          //Complex Node
  
  public PmtBankRec(Node billRecNode) throws Exception
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
    
    boolean BankIdFound = false;
    boolean CurAmtFound = false;
    try 
    {
      for (int i=0 ; i< size; i++)
        {
          Node firstLevelNode = childNodes.item(i);
          nodeName1  = firstLevelNode.getNodeName();
          nodeValue1 = firstLevelNode.getNodeValue();
          nodeType1 = firstLevelNode.getNodeType();
               //-------Setting Complex Nodes First------------------------------------------------------
          if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(PmtBranchRecNodeName)  )
          {
             this.PmtBranchRecs.addElement(new PmtBranchRec(firstLevelNode));
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
                      if ( ! BankIdFound  &&  nodeName1.toUpperCase().equals(BankIdNodeName))
                      {
                        this.BankId = nodeValue2;
                        BankIdFound = true;
                        continue;
                      }
                      
                      if (! CurAmtFound &&  nodeName1.toUpperCase().equals(this.CurAmtNodeName))
                      {
                        this.CurAmt = nodeValue2;
                        CurAmtFound = true;
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

}