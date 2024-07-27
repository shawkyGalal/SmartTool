package com.smartValue.database.map;

import java.util.HashMap;

import com.smartValue.database.Attribute;
import com.smartValue.database.AttributeChangeValidator;
import com.smartValue.database.DbForignKeyArray;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.audit.AuditInDbTriggerHandler;
import com.smartValue.database.map.auto._AuditControler;
import com.smartValue.database.map.security.PersistentObjectSecurityControl;
import com.smartValue.database.trigger.TriggerHandler;

public class AuditControler extends _AuditControler {

	private static TriggerHandler triggerHandler = null;
	
	@Override
	public PersistentObjectSecurityControl getSecurityController() 
	{
		// TODO Auto-generated method stub
	 return null; 
	}
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
		// TODO Auto-generated method stub
	 return null; 
	}
	@Override
	protected HashMap<String, DbForignKeyArray> getChildrenForignKeys()
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void afterAttributeChange(Attribute att){
		// TODO Auto-generated method stub
		
	}
	@Override
	public void beforeAttributeChange(Attribute att) throws Exception {
		// TODO Auto-generated method stub
		
	}
}