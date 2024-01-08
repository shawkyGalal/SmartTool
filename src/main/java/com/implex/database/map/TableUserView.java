package com.implex.database.map;

import java.util.HashMap;

import com.implex.database.Attribute;
import com.implex.database.AttributeChangeValidator;
import com.implex.database.DbForignKeyArray;
import com.implex.database.PersistantObject;
import com.implex.database.audit.AuditInDbTriggerHandler;
import com.implex.database.map.auto._TableUserView;
import com.implex.database.map.security.PersistentObjectSecurityControl;
import com.implex.database.map.security.TableUserViewSecurityControlHandlerImpl;
import com.implex.database.trigger.TriggerHandler;

public class TableUserView extends _TableUserView {
	private TableUserViewSecurityControlHandlerImpl securityControlHandlerImpl = new TableUserViewSecurityControlHandlerImpl();
	
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
		return null; 
	}
	
	private String substituedViewCond ; 
	public String calacSubstituedViewCond()
	{
		String result = this.subLoggedUserProperties(this.getViewWhereConditionValue())  ;
		
		return result ;
	}
	
	public void refreshSubstituedViewCond()
	{
		substituedViewCond = calacSubstituedViewCond();
	}
	public String getSubstituedViewCond() {
		if (substituedViewCond == null)
		{
			this.refreshSubstituedViewCond();
		}
		return substituedViewCond;
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