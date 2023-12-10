package SADAD.DATASTRCTURE;
import java.util.*;
import org.w3c.dom.*;
import java.sql.*;

public class BillerSvcRq 
{
  public static final String RqUIDNodeName = "RQUID";
  public String  RqUID;   //--- Rquest Uniqe ID of Type UUID (Uinversally Unique ID)
  
  public Status status;
  public static final String statusNodeName = "STATUS";
  
  public static final String pmtNotifyUploadRqNodeName = "PMTNOTIFYUPLOADRQ";
  public static final String pmtNotifyRqNodeName = "PMTNOTIFYRQ";
   public static final String pmtUploadRqNodeName = "PMTUPLOADRQ";
  
  public static final String BillerReconUploadRqNodeName = "BILLERRECONUPLOADRQ";
  //-------Currently SADAD System Supports only one message Per Service 
  // So the messageRequests Vector will Contain only One message
  //--In the Future messageRequests may contain multiple message objects
  public Vector messageRequests= new Vector();
  
  
  public BillerSvcRq(Node billerSvcRqNode, Connection con ) throws Exception
  {
     NodeList childNodes = billerSvcRqNode.getChildNodes();
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
          if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(statusNodeName)  )
              {
                this.status = new Status(firstLevelNode);
              }
          //-------------Payment Notify Upload Rq-----------------
           if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(pmtNotifyUploadRqNodeName)  )
              {
                this.messageRequests.addElement(new PmtNotifyUploadRq(firstLevelNode , con));
                continue;
              }
          //-------------Payment Notify Rq-----------------    
          if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(pmtNotifyRqNodeName)  )
              {
                this.messageRequests.addElement(new PmtNotifyRq(firstLevelNode , con));
                continue;
              }
          //-------------Payment Upload Rq-----------------    
          if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(pmtUploadRqNodeName)  )
              {
                this.messageRequests.addElement(new PmtUploadRq(firstLevelNode,con));
                continue;                
              }
          //-------------Biller Reconciliation Upload Rq-----------------    
          if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(BillerReconUploadRqNodeName)  )
              {
                this.messageRequests.addElement(new BillerReconUploadRq(firstLevelNode));
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
     if (this.status != null ) {xmlString += "\n" + this.status.toXMLString();}
     xmlString += "\n" + "<RqUID>"+this.RqUID+ "</RqUID>";
    for (int i = 0 ; i< this.messageRequests.size() ; i++)
    {
      if( messageRequests.elementAt(i) instanceof PmtNotifyUploadRq )
      {
        PmtNotifyUploadRq pnur = (PmtNotifyUploadRq) messageRequests.elementAt(i);
        xmlString += "\n" + pnur.toXMLString();
      }

     else if( messageRequests.elementAt(i) instanceof PmtUploadRq )
      {
        PmtUploadRq pur = (PmtUploadRq) messageRequests.elementAt(i);
        xmlString += "\n" + pur.toXMLString();
      }
      
     
     else if( messageRequests.elementAt(i) instanceof BillerReconUploadRq )
      {
        BillerReconUploadRq brur = (BillerReconUploadRq) messageRequests.elementAt(i);
        xmlString += "\n" + brur.toXMLString();
      }
     else if( messageRequests.elementAt(i) instanceof PmtNotifyRq )
      {
        PmtNotifyRq pnrq = (PmtNotifyRq) messageRequests.elementAt(i);
        xmlString += "\n" + pnrq.toXMLString();
      }
      
      
      else {throw new Exception ("Invalid PresSvcRq Object");}
      
    }
    xmlString += "\n" + "</BillerSvcRq>";
    return xmlString;
    
  }
}