package SADAD.DATASTRCTURE;
import org.w3c.dom.*;
public class Signature 
{ 
  private String signatureValue;
  private static final String signatureNodeName = "SIGNATUREVALUE";

  private String xPath;
  private static final String xPathNodeName = "XPATH";
  
  
  public Signature(Node signatureXMLNode  )throws Exception
  {
    setParameters(signatureXMLNode);
  }
  protected void setParameters(Node signatureXMLNode ) throws Exception 
  {
    NodeList childNodes = signatureXMLNode.getChildNodes();
    int size = childNodes.getLength();
    String nodeName1 = "";
    String nodeValue1  = "";
    short nodeType1  = 0;
    
    String nodeName2 = "";
    String nodeValue2  = "";
    short nodeType2  = 0;
    boolean signatureFound = false;
    boolean xPathFound = false;
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
                      if ( ! signatureFound  &&  nodeName1.toUpperCase().equals(signatureNodeName))
                      {
                        this.setSignatureValue(nodeValue2);
                        signatureFound = true;
                        continue;
                      }
                      
                      if (! xPathFound &&  nodeName1.toUpperCase().equals(this.xPathNodeName))
                      {
                        this.setxPath(nodeValue2);
                        xPathFound = true;
                      }
                                        
                  }
                  
                 }
    
           }
         }
    }
    catch (Exception e) 
    { 
    e.printStackTrace();
    throw e;}
 }
public String toXmlString() {
	StringBuffer result = new StringBuffer();
	result.append("<"+xPathNodeName+">" + this.getxPath() + "<\\"+xPathNodeName+">");
	result.append("<"+signatureNodeName+">" + this.getSignatureValue() + "<\\"+signatureNodeName+">");
	return null;
}
public void setSignatureValue(String signatureValue) {
	this.signatureValue = signatureValue;
}
public String getSignatureValue() {
	return signatureValue;
}
public void setxPath(String xPath) {
	this.xPath = xPath;
}
public String getxPath() {
	return xPath;
}
 
 

}