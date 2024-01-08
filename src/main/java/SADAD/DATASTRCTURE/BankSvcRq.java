package SADAD.DATASTRCTURE;
import java.util.*;
import org.w3c.dom.*;
import java.sql.*;

public class BankSvcRq 
{
  public static final String RqUIDNodeName = "RQUID";
  public String  RqUID;   //--- Rquest Uniqe ID of Type UUID (Uinversally Unique ID)
  
  public static final String BankReconDownloadRqName = "BANKRECONDOWNLOADRQ";
  public static final String BankCutOffRqName = "BANKCUTOFFRQ";
  public static final String BankSplUploadRqName = "BANKSPLUPLOADRQ";
  public static final String XferAddRqName = "XFERADDRQ"; 
  public static final String XferResultRqName = "XFERRESULTRQ";   
  
  public static final String StatusName = "STATUS";
  public Status status ;
  

 
  //-------Currently SADAD System Supports only one message Per Service 
  // So the messageRequests Vector will Contain only One message
  //--In the Future messageRequests may contain multiple message objects
  public Vector messageRequests= new Vector();
  
  
  public BankSvcRq(Node bankSvcRqNode, Connection con ) throws Exception
  {
     NodeList childNodes = bankSvcRqNode.getChildNodes();
    int size = childNodes.getLength();
    String nodeName1 = "";
    String nodeValue1  = "";
    short nodeType1  = 0;
    
    String nodeName2 = "";
    String nodeValue2  = "";
    short nodeType2  = 0;
    boolean clientDtFound = false;    
    try 
    {
      for (int i=0 ; i< size; i++)
        {
          Node firstLevelNode = childNodes.item(i);
          nodeName1  = firstLevelNode.getNodeName();
          nodeValue1 = firstLevelNode.getNodeValue();
          nodeType1 = firstLevelNode.getNodeType();
          
          if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(StatusName)  )
              {
                this.status = new Status(firstLevelNode);
                continue;
              } 
           //-------------BankReconDownLoadRq -----------------
           if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(BankReconDownloadRqName)  )
              {
                this.messageRequests.addElement(new BankReconDownLoadRq(firstLevelNode , con));
                continue;
              }

           //----------------Bank Cut off Message----
           if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(BankCutOffRqName)  )
              {
                this.messageRequests.addElement(new BankCutOffRq(firstLevelNode , con));
                continue;
              }
           //----------------Bank SPL Message----   
           if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(BankSplUploadRqName)  )
              {
                this.messageRequests.addElement(new BankSPLRq(firstLevelNode , con));
                continue;
              }
           //----------------Bank Transfer Message (To Bank )----   
           if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(XferAddRqName)  )
              {
                this.messageRequests.addElement(new XferAddRq(firstLevelNode ));
                continue;
              }
              
           //----------------Bank Transfer Result Message (from Bank)----                 
           if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(XferResultRqName)  )
              {
                this.messageRequests.addElement(new XferResultRq(firstLevelNode ));
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
                      if ( nodeName1.toUpperCase().equals(RqUIDNodeName))
                      {
                        this.RqUID = nodeValue2;
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
  public String toXMLString() throws Exception 
  {
     String xmlString = "<BillerSvcRq>";
    for (int i = 0 ; i< this.messageRequests.size() ; i++)
    {
      if( messageRequests.elementAt(i) instanceof PmtNotifyUploadRq )
      {
        PmtNotifyUploadRq pnur = (PmtNotifyUploadRq) messageRequests.elementAt(i);
        xmlString += "\n" + pnur.toXMLString();
      }

     if( messageRequests.elementAt(i) instanceof PmtUploadRq )
      {
        PmtUploadRq pur = (PmtUploadRq) messageRequests.elementAt(i);
        // xmlString += "\n" + pur.toXMLString();
      }
      
     
     if( messageRequests.elementAt(i) instanceof BillerReconUploadRq )
      {
        BillerReconUploadRq brur = (BillerReconUploadRq) messageRequests.elementAt(i);
        xmlString += "\n" + brur.toXMLString();
      }
      
      else {throw new Exception ("Invalid PresSvcRq Object");}
      
    }
    xmlString += "\n" + "</BillerSvcRq>";
    return xmlString;
    
  }
}