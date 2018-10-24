package SADAD.DATASTRCTURE;

import org.w3c.dom.*;
import java.util.*;
import java.sql.*;
import java.io.*;
import SADAD.*;

public class ERROR //extends SADADMessage 
{
 private static final String errorCodeNodeName = "ERRORCODE";
 private static final String errorMsgNodeName = "ERRORMSG";
 public String errorCode;
 public String errorMsg;
 public Connection con;
  
  public ERROR( Node BillInfoErrorNode , Connection m_con ) throws Exception
  {
    this.con = m_con;
    setParameters(BillInfoErrorNode);
  }
  

  private void setParameters(Node errorNode ) throws Exception 
  {
    NodeList childNodes = errorNode.getChildNodes();
    int size = childNodes.getLength();
    String nodeName1 = "";
    String nodeValue1  = "";
    short nodeType1  = 0;
    
    String nodeName2 = "";
    String nodeValue2  = "";
    short nodeType2  = 0;
    boolean errorCodeFound = false;
    boolean errorMsgFound = false;
    try 
    {
      for (int i=0 ; i< size; i++)
        {
          Node firstLevelNode = childNodes.item(i);
          nodeName1  = firstLevelNode.getNodeName();
          nodeValue1 = firstLevelNode.getNodeValue();
          nodeType1 = firstLevelNode.getNodeType();
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
                      if ( ! errorCodeFound  &&  nodeName1.toUpperCase().equals(errorCodeNodeName))
                      {
                        this.errorCode = nodeValue2;
                        errorCodeFound = true;
                        continue;
                      }
                      
                      if (! errorMsgFound &&  nodeName1.toUpperCase().equals(this.errorMsgNodeName))
                      {
                        this.errorMsg = nodeValue2;
                        errorMsgFound = true;
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
  
  protected void validate() throws Exception 
  {
  }
    public String toXMLString()
  {
  String xmlString= "";
  xmlString  += "\n" + "<Error>";
  xmlString  += "\n\t" + "<ErrorCode>" + this.errorCode + "</ErrorCode>";
  xmlString  += "\n\t" + "<ErrorMsg>" + this.errorMsg + "</ErrorMsg>";
  xmlString  += "\n" + "</Error>";
  return xmlString;
    
  }
}