<%@page  language="java" contentType="text/html;charset=UTF-8"%>
<%@page import="java.io.InputStream , Support.SmsSernder"%>
<%@page import="com.smartValue.*" %>
<%
	SmsNotificationRequest smsNotificationRequest = null;
	
	java.util.ArrayList<NotificationRequestParams>  notificationRequestParamss = new java.util.ArrayList<NotificationRequestParams> ();  
	try {	     
     		
	
			SmartValueMessage svm = new SmartValueMessage(request.getInputStream() , true ,  Support.Misc.getAutoLoginConnection() ) ; 

			smsNotificationRequest = svm.getSmsNotificationRequest();
			notificationRequestParamss =  smsNotificationRequest.getNotificationRequestParamss();
			String sender = svm.getSignonRq().getSignonProfile().getSender();
			String clientDate = svm.getSignonRq().getClientDt();
			if (svm.isMessageAuthenticated())
			{
				SmsSernder smsSernder = new SmsSernder(SmsSernder.DEFAULT_CLICKTELL_API_ID ,  "172.27.0.2", "3129", "", "");
				smsSernder.setSmsGatwayUserName("foda.sh@smart-value.com");
				smsSernder.setSmsGatewayPassword("redsea11");
				 
				for ( NotificationRequestParams nrp : notificationRequestParamss ) 
				{
					nrp.sendSms(smsSernder , "SmartValue");// Finally I got approval for "SmratValue" as a valid Sender ID
				} 
			}
			%><?xml version='1.0' encoding='UTF-8'?>
				<SmartValue>
					<SignonRs>
						<ClientDt><%=clientDate%></ClientDt>
						<ServerDt><%=new java.util.Date()%></ServerDt>
						<LanguagePref>en-gb</LanguagePref>
						<SignonProfile>
							<Sender>SMARTVALUE</Sender>
							<Receiver><%=sender%></Receiver>
							<MsgCode>SMSNOTRS</MsgCode>
						</SignonProfile>
					</SignonRs>
					<SmsNotificationResponse>
						<AsyncRqUID><%=(smsNotificationRequest != null) ? smsNotificationRequest.getRqUID() : "" %></AsyncRqUID>
						<SuccessRecordCount><%=smsNotificationRequest.getSuccessCount()%></SuccessRecordCount>
						<ErrorRecordCount><%=smsNotificationRequest.getErrorCount()%></ErrorRecordCount>
						
						<%  String statusCode ; 
							String shortDesc ;
							String severity ; 
							for ( NotificationRequestParams nrp : notificationRequestParamss ) 
							{ %>
							<SmsNotificationStatus>
								<Status>
									<%  
	 									if (! svm.isMessageAuthenticated())
										{
											statusCode = "-99" ;
											shortDesc  = "Overal Message Authentication Failure" ;
											severity   = "Error" ;
										}
										else
										{
											String gateWayResponse = nrp.getGateWayResponse() ;
											boolean successToSend = (nrp.getSendSmsException()==null && nrp.isSentSuccessfully()) ;  
											statusCode = (successToSend)? "0" :"-1" ;
											shortDesc  = (successToSend)? "Success : GateWay Response = " + gateWayResponse : "Error :" + nrp.getSendSmsException() ;
											severity   = (successToSend)? "Info" :"Error" ;
										}		
									%>
									
									<StatusCode><%=statusCode%></StatusCode>
									<ShortDesc><%=shortDesc%></ShortDesc>
									<Severity><%=severity%></Severity>			
								</Status>
								<NotificationRequest>
									<PolicyNo><%=nrp.getPolicyNo()%></PolicyNo>
									<ClaimNo><%=nrp.getClaimNo()%></ClaimNo>
									<CustMobileNo><%=nrp.getCustMobileNo()%></CustMobileNo>
									<SMSMessage><%=nrp.getSMSMessage()%></SMSMessage>			
								</NotificationRequest>
							</SmsNotificationStatus>
							<%
							} 
						%>
					</SmsNotificationResponse>
					<Signature>
						<XPath>gfghfhkjhsdfkjhsdjfkh</XPath>
						<SignatureValue>hgjg GDSHJgsdhjGASHJdfvkjzhd vkjhdkjfhdsfkjhdskghdsnufgydsiu</SignatureValue>
					</Signature>
					
				</SmartValue>
			 <%	
		}
	catch (Exception e) 
	{ 
		out.println( e.getMessage() );
	}	
%>

