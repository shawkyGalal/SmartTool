package com.smartValue;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Support.SmsMessage;
import Support.SmsSernder;

public class NotificationRequestParams {
	
	private String policyNo ;
	private String claimNo ;
	private String custMobileNo ;
	private String SMSMessage ;
	private Exception sendSmsException;
	private String gateWayResponse = "Notification Not Yet Sent" ;
	private static final String SUCCESS_IF_CONTAINS = "ID:" ; 
	
	
	public void setGateWayResponse(String gateWayResponse) 
	{
		this.gateWayResponse = gateWayResponse;
		this.sentSuccessfully =  this.gateWayResponse!=null 
				&&  this.gateWayResponse.toUpperCase().indexOf(SUCCESS_IF_CONTAINS ) != -1;
	}

	private boolean sentSuccessfully = false ;
	
	
	public String getGateWayResponse() {
		return gateWayResponse;
	}

	public NotificationRequestParams(Node notificationRequestParamsNode) {

		String policyNoNodeName = "POLICYNO";
		boolean policyNoFound = false ;
		
		String claimNoNodeName = "CLAIMNO" ;
		boolean claimNoNodeFound = false ;
		
		String custMobileNoNodeName = "CUSTMOBILENO" ;
		boolean custMobileNoFound = false ;
		
		String smsMessageNodeName = "SMSMESSAGE" ;
		boolean smsMessageNodeFound = false ;
		
		
		NodeList childNodes = notificationRequestParamsNode.getChildNodes();
	    int size = childNodes.getLength();
	    String nodeName1 = "";
	    String nodeValue1  = "";
	    short nodeType1  = 0;
	    
	    String nodeName2 = "";
	    String nodeValue2  = "";
	    short nodeType2  = 0;
	    //----The following booleans to speed up the search process-----
		String notificationRequestParamsNodeName = "NotificationRequestParams" ; 
		

	    boolean asyncRqUIDFound = false;

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
	                      if ( !policyNoFound &&  nodeName1.toUpperCase().equalsIgnoreCase(policyNoNodeName))
	                      {
	                        this.setPolicyNo(nodeValue2);
	                        policyNoFound = true;
	                        continue;
	                      }
	                      
	                      if ( !claimNoNodeFound &&  nodeName1.toUpperCase().equalsIgnoreCase(claimNoNodeName))
	                      {
	                        this.setClaimNo(nodeValue2);
	                        claimNoNodeFound = true;
	                        continue;
	                      }
	                      
	                      if ( !custMobileNoFound &&  nodeName1.toUpperCase().equalsIgnoreCase(custMobileNoNodeName))
	                      {
	                        this.setCustMobileNo(nodeValue2);
	                        custMobileNoFound = true;
	                        continue;
	                      }
	                      
	                      if ( !smsMessageNodeFound &&  nodeName1.toUpperCase().equalsIgnoreCase(smsMessageNodeName))
	                      {
	                        this.setSMSMessage(nodeValue2);
	                        smsMessageNodeFound = true;
	                        continue;
	                      }
	                      

	                  }
	                  
                 }
	           }
	         }

	}

	public void  sendSms(SmsSernder smss , String fromMobile) 
	{
		try {
			SmsMessage smsMessage = new SmsMessage(this.getSMSMessage(), this.getCustMobileNo() , fromMobile) ; 
			this.setGateWayResponse(smss.sendSMS(smsMessage)); 
			//if (gateWayResponse != null && gateWayResponse.length()>3 && gateWayResponse.substring(0, 3).equalsIgnoreCase("ID:"))
			//gateWayResponse = smss.queryMessageCharge(gateWayResponse.substring(3).trim());
		}
		catch (Exception e) { this.setSendSmsException(e); }
		//return gateWayResponse ; 
	}
	
	private void setSendSmsException(Exception e) {
		this.sendSmsException = e;
		
	}
	
	public Exception getSendSmsException()
	{
		return this.sendSmsException ; 
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}
	public String getClaimNo() {
		return claimNo;
	}
	public void setCustMobileNo(String custMobileNo) {
		this.custMobileNo = custMobileNo;
	}
	public String getCustMobileNo() {
		return custMobileNo;
	}
	public void setSMSMessage(String sMSMessage) {
		SMSMessage = sMSMessage;
	}
	public String getSMSMessage() {
		return SMSMessage;
	}


	public boolean isSentSuccessfully() {
		return this.sentSuccessfully ;
	}

	public boolean isMessageContainsArabic()
	{
		boolean result = false ;
		
		return result ;
	}
}
