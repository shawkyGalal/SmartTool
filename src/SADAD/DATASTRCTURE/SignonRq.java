package SADAD.DATASTRCTURE;
import java.security.Signature;
import org.w3c.dom.*;


public class SignonRq 
{

public String clientDt;

private static final String clientDtNodeName = "CLIENTDT";

private SignonProfile signonProfile;
private static final String signonProfileNodeName = "SIGNONPROFILE";

	public String getClientDt() {
		return clientDt;
	}
	public void setClientDt(String clientDt) {
		this.clientDt = clientDt;
	}
	public SignonProfile getSignonProfile() {
		return signonProfile;
	}
	public void setSignonProfile(SignonProfile signonProfile) {
		this.signonProfile = signonProfile;
	}

 public String toXMLString()
 {
   
   String xmlString = "<SignonRq>";
   xmlString += "\n " + "<ClientDt>" + this.clientDt +"</ClientDt>";
   xmlString += "\n " +  this.signonProfile.toXMLString() ;
   xmlString += "\n " +"</SignonRq>";
   return xmlString;
 
 }
  public SignonRq(Node signonRqNode) throws Exception
  {
    NodeList childNodes = signonRqNode.getChildNodes();
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
           if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equals(signonProfileNodeName)  )
              {
                this.signonProfile = new SignonProfile(firstLevelNode);
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
                      if ( !clientDtFound &&  nodeName1.toUpperCase().equals(clientDtNodeName))
                      {
                        this.clientDt = nodeValue2;
                        continue;
                      }
                  }
                } 
         }
    validate();
    }
    }
    catch (Exception e) 
    { 
    e.printStackTrace();
    throw e;}
 }

  public void validate()
  {
    
  }
}