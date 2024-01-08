package SADAD.DATASTRCTURE;
import org.w3c.dom.*;
import SADAD.*;


public class SignonProfile 
{
  private String sender;
  private String senderPassword;
  private String receiver;
  private String msgCode;
  
  private final static String senderNodeName="SENDER";
  private final static String receiverNodeName="RECEIVER";
  private final static String msgCodeNodeName="MSGCODE";
  private final static String SenderPasswordNodeName = "SenderPassword" ;
  
  public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}
  public SignonProfile( Node SignonProfileNode ) throws Exception
  {
    NodeList childNodes = SignonProfileNode.getChildNodes();
    int size = childNodes.getLength();
    String nodeName1 = "";
    String nodeValue1  = "";
    short nodeType1  = 0;
    
    String nodeName2 = "";
    String nodeValue2  = "";
    short nodeType2  = 0;
    boolean senderFound = false;
    boolean receiverFound = false;
    boolean msgCodeFound= false;
    boolean senderPasswordFound = false ; 
        
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
                      if ( !senderFound &&  nodeName1.toUpperCase().equals(senderNodeName))
                      {
                        this.sender = nodeValue2;
                        senderFound = true;
                        continue;
                      }
                      else if ( !receiverFound &&  nodeName1.toUpperCase().equals(receiverNodeName))
                      {
                        this.receiver = nodeValue2;
                        receiverFound = true;
                        continue;
                      }
                  
                  
                      else if ( !msgCodeFound &&  nodeName1.toUpperCase().equals(msgCodeNodeName))
                      {
                        this.msgCode = nodeValue2;
                        msgCodeFound = true;
                        continue;
                      }
                      
                      else if ( !senderPasswordFound &&  nodeName1.toUpperCase().equalsIgnoreCase(SenderPasswordNodeName))
                      {
                        this.senderPassword = nodeValue2;
                        senderPasswordFound = true;
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

  public void validate() throws SADADException
  {
    if (this.sender== null || this.receiver == null || this.msgCode == null)
    {
      throw new SADADException("Invalid SignOnProfile",null,0);
    }
    
  }
   public String toXMLString()
 {
   
   String xmlString = "<SignonProfile>";
   xmlString += "\n\t " + "<"+this.senderNodeName+">" + this.sender +"</"+this.senderNodeName+">";
   xmlString += "\n\t " + "<"+this.SenderPasswordNodeName+">*******</"+this.SenderPasswordNodeName+">";
   xmlString += "\n\t " + "<"+this.receiverNodeName+">" + this.receiver +"</"+this.receiverNodeName+">";
   xmlString += "\n\t " + "<"+this.msgCodeNodeName+">" + this.msgCode +"</"+this.msgCodeNodeName+">";
   xmlString += "\n " +"</SignonProfile>";
   return xmlString;
 
 }

public String getSenderPassword() {
	return this.senderPassword;
}

public void setSenderPassword(String senderPassword) {
	this.senderPassword = senderPassword;
}
}