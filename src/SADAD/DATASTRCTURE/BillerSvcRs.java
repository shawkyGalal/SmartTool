package SADAD.DATASTRCTURE;
import Support.*;
import java.util.*;
import org.w3c.dom.*;
import java.sql.*;
import java.net.*;

public class BillerSvcRs 
{
  public Status status;
  public static final String statusNodeName = "STATUS";
  
  public static final String RqUIDNodeName = "RQUID";
  public String  RqUID;   //--- Rquest Uniqe ID of Type UUID (Uinversally Unique ID)
  
  public String PmtUploadRs ;
  public static final String PmtUploadRsNodeName = "PMTUPLOADRS";
  
  public BillerReconUploadRs billerReconUploadRs ;
  public static final String BillerReconUploadRsNodeName = "BILLERRECONUPLOADRS";
  
  
  //-------Currently SADAD System Supports only one message Per Service 
  // So the messageRequests Vector will Contain only One message
  //--In the Future messageRequests may contain multiple message objects
  public Vector messageRequests= new Vector();
  
  public BillerSvcRs(Node BillerSvcRsNode, Connection con ) throws Exception
  {
    NodeList childNodes = BillerSvcRsNode.getChildNodes();
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
          if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(this.BillerReconUploadRsNodeName)  )
              {
                this.billerReconUploadRs = new BillerReconUploadRs(firstLevelNode);
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
                      if ( nodeName1.toUpperCase().equals(PmtUploadRsNodeName))
                      {
                        this.PmtUploadRs = nodeValue2;
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
    String xmlString = "<BillerSvcRs>";
    xmlString+= "\n" + this.status.toXMLString();
    xmlString+= "\n" + "<RqUID>"+this.RqUID+"</RqUID>";
    if (this.PmtUploadRs != null)
    { xmlString+= "\n" + "<PmtUploadRs>"+this.PmtUploadRs+"</PmtUploadRs>"; }
    if (this.billerReconUploadRs != null)
    { xmlString+= "\n" + this.billerReconUploadRs.toXMLString(); }
    xmlString += "\n" + "</BillerSvcRs>";
    return xmlString;
    
  }
}



