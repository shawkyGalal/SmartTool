package com.smartValue.database.map;

import java.util.HashMap;

import com.smartValue.database.Attribute;
import com.smartValue.database.AttributeChangeValidator;
import com.smartValue.database.DbForignKeyArray;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.audit.AuditInDbTriggerHandler;
import com.smartValue.database.map.auto._SecUserCommunicationMsgs;
import com.smartValue.database.map.security.PersistentObjectSecurityControl;
import com.smartValue.database.map.security.SecUserCommunicationMsgsSecurityControlHandlerImpl;
import com.smartValue.database.map.validators.SecUserCommunicationMsgsChangeValidator;
import com.smartValue.database.trigger.TriggerHandler;

public class SecUserCommunicationMsgs extends _SecUserCommunicationMsgs {
	private SecUserCommunicationMsgsSecurityControlHandlerImpl securityControlHandlerImpl = new SecUserCommunicationMsgsSecurityControlHandlerImpl();
	
	private static HashMap<String, DbForignKeyArray> childrenForignKeys;
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
		return securityControlHandlerImpl; 
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
	 return new SecUserCommunicationMsgsChangeValidator(pm_secUserData , pm_po  , pm_key); 
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
