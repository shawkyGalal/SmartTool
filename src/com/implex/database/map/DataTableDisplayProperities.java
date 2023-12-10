package com.implex.database.map;

import java.util.ArrayList;
import java.util.HashMap;

import javax.faces.model.SelectItem;

import com.implex.database.Attribute;
import com.implex.database.AttributeChangeValidator;
import com.implex.database.DataSet;
import com.implex.database.DbForignKeyArray;
import com.implex.database.Header;
import com.implex.database.PersistantObject;
import com.implex.database.audit.AuditInDbTriggerHandler;
import com.implex.database.map.auto._DataTableDisplayProperities;
import com.implex.database.map.security.PersistentObjectSecurityControl;
import com.implex.database.trigger.TriggerHandler;
public class DataTableDisplayProperities extends _DataTableDisplayProperities {
	
	 private boolean useAttDefaultControl = false ;
		
		private ArrayList<Header> allAvailableHeaders = new ArrayList<Header>();
		private ArrayList<Header> userSelectedHeaders = new ArrayList<Header>();
		private String reRenderIds ; 
		private DataSet dataSet ; 

		private static HashMap<String, DbForignKeyArray> childrenForignKeys; 
			

		
		public void toggleUseAttDefaultControl()
		{
			this.getDisplayDefaultControl().setBooleanValue(!this.getDisplayDefaultControl().getBooleanValue());
		}

		public void setUseAttDefaultControl(boolean pm_useAttDefaultControl) {
			useAttDefaultControl = pm_useAttDefaultControl;
		}
		public boolean isUseAttDefaultControl() {
			return useAttDefaultControl;
		}


		public void setReRenderIds(String reRenderIds) {
			this.reRenderIds = reRenderIds;
		}

		public String getReRenderIds() {
			return reRenderIds;
		}
	
	@Override
	public HashMap<String, DbForignKeyArray> getChildrenForignKeys() 
	{
		 if (childrenForignKeys == null) 
		  { childrenForignKeys = new HashMap<String, DbForignKeyArray>(); 
			  //TODO... Fill Your Childern 
			  //Example ... this.getTableMaintMaster().getDbForignKey(new DbTable(SavedSearchDetail.DB_TABLE_NAME)); 
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

	public void setAllAvailableHeaders(ArrayList<Header> allAvailableHeaders) {
		this.allAvailableHeaders = allAvailableHeaders;
	}

	public ArrayList<Header> getAllAvailableHeaders() {
		return allAvailableHeaders;
	}


	public void setUserSelectedHeaders(ArrayList<Header> userSelectedHeaders) {
		this.userSelectedHeaders = userSelectedHeaders;
	}


	public ArrayList<Header> getUserSelectedHeaders() {
	 
		return userSelectedHeaders ;
	}

	public void setDataSet(DataSet pm_dataSet) {
		this.dataSet = pm_dataSet;
		if (userSelectedHeaders == null || userSelectedHeaders.isEmpty())
		{
			this.userSelectedHeaders = pm_dataSet.getHeaders();
		}
	   
	}

	public DataSet getDataSet() {
		return dataSet;
	} 
	 
	public ArrayList<SelectItem> getAllHeadersAsSelectItem()  
	{
	    ArrayList<SelectItem> result = new ArrayList<SelectItem>();
	    ArrayList<Header>     headersList       =dataSet.getHeaders();
		if (headersList!= null&&!headersList.isEmpty())
		 {
				for (Header header : headersList)
				{
					result.add( new SelectItem( header,header.getDisplayText()  ) )  ;
				}
		 }
		else
		{   result=null;   }
				
		return result ; 
	}

	@Override
	public void afterAttributeChange(Attribute att) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeAttributeChange(Attribute att) throws Exception {
		// TODO Auto-generated method stub
		
	}
	

}