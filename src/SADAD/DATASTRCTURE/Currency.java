package SADAD.DATASTRCTURE;
import java.util.*;
import org.w3c.dom.*;

public class Currency 
{ 
  public String amt;
  final static String amtNodeName = "AMT";

  
  public String curCode ;
  final static String curCodeNodeName = "CURCODE";
  public Currency(String curValue)
  {
    this.amt= curValue;
    this.curCode= "SAR";
  }
    
  public Currency(Node pmtInfoXMLNode ) throws Exception
  {
    setValues(pmtInfoXMLNode);
  }
  private void setValues(Node currencyXMLNode ) throws Exception 
  {
    NodeList childNodes = currencyXMLNode.getChildNodes();
    int size = childNodes.getLength();
    String nodeName1 = "";
    String nodeValue1  = "";
    short nodeType1  = 0;
    
    String nodeName2 = "";
    String nodeValue2  = "";
    short nodeType2  = 0;
    
    boolean amtFound= false;
    boolean curCodeFound = false;
    
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
                      if ( ! amtFound &&  nodeName1.toUpperCase().equals(amtNodeName))
                      {
                        this.amt = nodeValue2;
                        amtFound = true;
                      }
                      
                      if (! curCodeFound &&  nodeName1.toUpperCase().equals(curCodeNodeName))
                      {
                        this.curCode = nodeValue2;
                        curCodeFound = true;
                      }
                  }
                  
                 }
    
           }
         }
    validateDate();
    }
    catch (Exception e) 
    { 
    e.printStackTrace();
    throw e;}
 }
 private void validateDate()
 {
   
 }
   public String toXMLString()
  {
    String xMLTag = "\n" +"<Currency>";
    xMLTag += "\n\t" +"<curCode>" +curCode +"</curCode>";
    xMLTag += "\nt" +"<amt>" +amt +"</amt>";
    xMLTag += "\n" +"</Currency>";
    return xMLTag;
  }
}