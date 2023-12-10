package SADAD.DATASTRCTURE;
import Support.*;
import java.util.*;
import org.w3c.dom.*;
import java.sql.*;
import java.net.*;

public class PresSvcRs 
{
  public Status status;
  public static final String statusNodeName = "STATUS";
  
  public static final String RqUIDNodeName = "RQUID";
  public String  RqUID;   //--- Rquest Uniqe ID of Type UUID (Uinversally Unique ID)
  
  public String billUploadRs ;
  public static final String billUploadRsNodeName = "BILLUPLOADRS";
  
  public BillConfirmationRs  billConfirmationRs ;
  public static final String billConfirmationRsNodeName = "BILLCONFIRMATIONRS";
  //-------Currently SADAD System Supports only one message Per Service 
  // So the messageRequests Vector will Contain only One message
  //--In the Future messageRequests may contain multiple message objects
  public Vector messageRequests= new Vector();
  
  public PresSvcRs(Node billerSvcRqNode, Connection con ) throws Exception
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
                      if ( nodeName1.toUpperCase().equals(billUploadRsNodeName))
                      {
                        this.billUploadRs = nodeValue2;
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
    String xmlString = "<PresSvcRs>";
    xmlString += "\n\t" +  this.status.toXMLString() ;
    xmlString += "\n\t" + "<RqUID>" + this.RqUID + "</RqUID>";
    if (billUploadRs != null)
    {
      xmlString += "\n" + "<BillUploadRs>" + this.billUploadRs + "</BillUploadRs>";
    }
    for (int i = 0 ; i< this.messageRequests.size() ; i++)
    {
      if( messageRequests.elementAt(i) instanceof BillUploadRq )
      {
        BillUploadRq bur = (BillUploadRq) messageRequests.elementAt(i);
        xmlString += "\n" + bur.toXMLString();
      }
      
      else {throw new Exception ("Invalid PresSvcRq Object");}
      
    }
    xmlString += "\n" + "</PresSvcRs>";
    return xmlString;
    
  }
}