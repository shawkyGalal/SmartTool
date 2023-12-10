package com.smartValue;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SmsNotificationRequest 
{

	private String RqUID ;
	private static final String RqUIDNodeName = "RqUID";
    private boolean asyncRqUIDFound = false;
    
	private ArrayList<NotificationRequestParams> notificationRequestParamss = new ArrayList<NotificationRequestParams>();
	String notificationRequestParamsNodeName = "NotificationRequestParams" ;	
	
	public SmsNotificationRequest(Node smsNotificationRequestNode) 
	{
	    NodeList childNodes = smsNotificationRequestNode.getChildNodes();
	    int size = childNodes.getLength();
	    String nodeName1 = "";
	    String nodeValue1  = "";
	    short nodeType1  = 0;
	    
	    String nodeName2 = "";
	    String nodeValue2  = "";
	    short nodeType2  = 0;
	
	    //---End-The following booleans to speed up the search process-----
	    for (int i=0 ; i< size; i++)
	    {

	    	Node firstLevelNode = childNodes.item(i);
	        nodeName1  = firstLevelNode.getNodeName();
	        nodeValue1 = firstLevelNode.getNodeValue();
	        nodeType1 = firstLevelNode.getNodeType();
	        //-------Setting Complex Nodes First------------------------------------------------------
	        if ( nodeType1 ==Node.ELEMENT_NODE && nodeName1.toUpperCase().equalsIgnoreCase(notificationRequestParamsNodeName)  )
	              {
	                this.notificationRequestParamss.add(new NotificationRequestParams(firstLevelNode));
	              }
	          //----End of Setting Complex Nodes First------------------------------------------------------
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
	                      if ( !asyncRqUIDFound &&  nodeName1.toUpperCase().equalsIgnoreCase(RqUIDNodeName))
	                      {
	                        this.setRqUID(nodeValue2);
	                        asyncRqUIDFound = true;
	                        continue;
	                      }
	                      
	                  }
	                  
                 }
	           }
	         }
			
	}


	public void setRqUID(String asyncRqUID) {
		this.RqUID = asyncRqUID;
	}


	public String getRqUID() {
		return RqUID;
	}
	
	public ArrayList<NotificationRequestParams> getNotificationRequestParamss()
	{
		return this.notificationRequestParamss ;
	}


	public Object toXmlString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getSuccessCount()
	{
		int result = 0 ;
		for (NotificationRequestParams xx : notificationRequestParamss )
		{
			if (xx.isSentSuccessfully()) result++;
		}
		return result ;
	}
	
	public int getErrorCount()
	{
		return this.getNotificationRequestParamss().size() - this.getSuccessCount() ; 
	}
}