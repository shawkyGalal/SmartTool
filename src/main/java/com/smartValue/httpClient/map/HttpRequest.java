package com.smartValue.httpClient.map;

import com.smartValue.database.Attribute;
import com.smartValue.database.AttributeChangeValidator;
import com.smartValue.database.DataSet;
import com.smartValue.database.DbForignKeyArray;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.audit.AuditInDbTriggerHandler;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.security.PersistentObjectSecurityControl;
import com.smartValue.database.trigger.TriggerHandler;
import com.smartValue.httpClient.map.auto._HttpRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.impl.client.CloseableHttpClient ;  
import org.apache.http.impl.client.HttpClients ;  
import org.apache.http.client.methods.HttpGet ;  
import org.apache.http.HttpEntity ; 
import org.apache.http.NameValuePair ;
import org.apache.http.client.entity.UrlEncodedFormEntity ; 
import org.apache.http.client.methods.CloseableHttpResponse ; 
import org.apache.http.client.methods.HttpPost ; 
import org.apache.http.message.BasicNameValuePair ;
import org.apache.http.util.EntityUtils ; 

public class HttpRequest extends _HttpRequest {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static HashMap<String, DbForignKeyArray> childrenForignKeys;
	private String response ; 
	private String responseStatusLine ; 
	@Override
	public HashMap<String, DbForignKeyArray> getChildrenForignKeys() 
	{
		 if (childrenForignKeys == null) 
		  { childrenForignKeys = new HashMap<String, DbForignKeyArray>(); 
			  for (DbForignKeyArray fka : this.getTableMaintMaster().getAllForignKeysArrays()) 
			  { 
			  childrenForignKeys.put(fka.getName(), fka); 
			  } 
			  //TODO... Fill Your Childern 
			  //Example ... this.getTableMaintMaster().getDbForignKey(new DbTable("ICDB" , SavedSearchDetail.DB_TABLE_NAME)); 
		  } 
	 return childrenForignKeys; 
	}
	@Override
	public PersistentObjectSecurityControl getSecurityController() 
	{
		return null;
	}
 
	 private static TriggerHandler triggerHandler = null; 
	@Override
	public TriggerHandler getTriggerHandler() 
	{
		 boolean auditable = this.getTableMaintMaster().getAuditable().getBooleanValue(); 
		 if (triggerHandler == null && auditable ) 
			{ 
				triggerHandler = new AuditInDbTriggerHandler(); 
			} 
		 return triggerHandler; 
	}
	@Override 
	public AttributeChangeValidator getAttributeChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key)
	{ 
		return null; 
	}
	 public void initialize() 
	 { 
		//Write your own initialization code here this will help you greatly improve performance especially
		// apply our standard rule < Minimise code inside any getXyz() method - simply return object -  > 
	 } 
	 private DataSet headers ; 
		public DataSet getHeaders()
		{
			if (headers == null)
			{
				String queryStr = "Select * from "+ HttpRequestHeader.DB_TABLE_OWNER +"."+ HttpRequestHeader.DB_TABLE_NAME  
					+" Where "+ HttpRequestHeader.REQUEST_ID + " = '" + this.getRequestIdValue() + "'"
					+" Order by  "+ HttpRequestHeader.HEADER_ID ;
				
				headers = this.getDbService().queryForDataSet(queryStr, com.smartValue.httpClient.map.HttpRequestHeader.class) ;

			}
			return headers ;
		}
		@Override
		public void beforeAttributeChange(Attribute att) throws Exception {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void afterAttributeChange(Attribute att) {
			// TODO Auto-generated method stub
			
		}
		
		private void sendGet() throws IOException
		{
			CloseableHttpClient httpclient = HttpClients.createDefault();
			try{
				HttpGet httpGet = new HttpGet(this.getUrlValue()) ;
				 ArrayList<PersistantObject> hrh = this.getHeaders().getPersistantObjectList() ;
				 for (PersistantObject po : hrh)
				 {
					 HttpRequestHeader rh = (HttpRequestHeader)po ;
					 httpGet.addHeader(rh.getHeaderNameValue(), rh.getHeaderValueValue());	 
				 }
				 System.out.println("<br>======GET Request =======<br>");
				 System.out.println(httpGet.toString());
			     CloseableHttpResponse response1 = httpclient.execute(httpGet);
			     
			    
			    try {
			    	this.setResponseStatusLine(response1.getStatusLine().toString());
			        HttpEntity entity = response1.getEntity();
			        // do something useful with the response body
			        // and ensure it is fully consumed
			        this.setResponse(EntityUtils.toString(entity));

			        EntityUtils.consume(entity);
			    } finally {
			        response1.close();
			    }
			}
			finally {
			    httpclient.close();
			}
			
		}
		private void sendPost() throws IOException
		{
			CloseableHttpClient httpclient = HttpClients.createDefault();
			try {
			HttpPost httpPost = new HttpPost(this.getUrlValue()) ; 
		    List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		    nvps.add(new BasicNameValuePair("username", "vip"));
		    nvps.add(new BasicNameValuePair("password", "secret"));
		    httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		    httpPost.addHeader("header01", "Heeeee");
		    System.out.println("<br><br>======Post Request =======<br>");
		    System.out.println(httpPost.toString());
		    System.out.println("<br>"+EntityUtils.toString(httpPost.getEntity()));
		    CloseableHttpResponse response2 = httpclient.execute(httpPost);

		    try {
		    	System.out.println("<br>======Post Response =======<br>");
		    	this.setResponseStatusLine(response2.getStatusLine().toString());
		        HttpEntity entity2 = response2.getEntity();
		        // do something useful with the response body
		        // and ensure it is fully consumed
		        this.setResponse(EntityUtils.toString(entity2));
		        EntityUtils.consume(entity2);
		    } finally {
		        response2.close();
		    }
			} finally { httpclient.close(); }
		}
		public void send() throws IOException
		{
			if (this.getMethodValue().equalsIgnoreCase("GET"))
			{
				this.sendGet();
			}
			
			else if (this.getMethodValue().equalsIgnoreCase("POST"))
			{
				this.sendPost();
			}
		}
		public String getResponse() {
			return response;
		}
		public void setResponse(String response1) {
			this.response = response1;
		}
		public String getResponseStatusLine() {
			return responseStatusLine;
		}
		public void setResponseStatusLine(String responseStatusLine) {
			this.responseStatusLine = responseStatusLine;
		}
}