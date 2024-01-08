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
  
public class PmtBranchRec 
{
  public String BranchCode = "";
  public String CurAmt = "";
  public Vector pmtRecs = new Vector();

  private final String BranchCodeNodeName="BRANCHCODE";    //simple Node
  private final String CurAmtNodeName="CURAMT";         //simple Node
  private final String PmtRecNodeName="PMTREC";          //Complex Node
  
  public PmtBranchRec(Node billRecNode) throws Exception
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
    
    boolean BranchCodeFound = false;
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
          if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(PmtRecNodeName)  )
          {
             this.pmtRecs.addElement(new PmtRec(firstLevelNode, null));
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
                      if ( ! BranchCodeFound  &&  nodeName1.toUpperCase().equals(BranchCodeNodeName))
                      {
                        this.BranchCode = nodeValue2;
                        BranchCodeFound = true;
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
    String xMLString = "\n" +"<PmtBranchRec>";
    xMLString+= "\n\t" + "<BranchCode>" + this.BranchCode + "</BranchCode>";
    xMLString+= "\n\t" + "<CurAmt>" + this.CurAmt + "</CurAmt>";
    for (int i= 0; i< this.pmtRecs.size() ; i++ )
    {
      PmtRec x= (PmtRec) pmtRecs.elementAt(i);
      xMLString+=x.toXMLString();  
    }
       
    xMLString += "\n" + "</PmtBranchRec>";
    return xMLString;
  }

}