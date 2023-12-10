package com.implex.database.map;

import java.math.BigDecimal;
import java.util.HashMap;

import com.implex.database.Attribute;
import com.implex.database.AttributeChangeValidator;
import com.implex.database.DbForignKeyArray;
import com.implex.database.PersistantObject;
import com.implex.database.audit.AuditInDbTriggerHandler;
import com.implex.database.map.auto._SysCode;
import com.implex.database.map.security.PersistentObjectSecurityControl;
import com.implex.database.trigger.TriggerHandler;

public class SysCode extends _SysCode {
	
	private static HashMap<String, DbForignKeyArray> childrenForignKeys;
	@Override
	public HashMap<String, DbForignKeyArray> getChildrenForignKeys() 
	{
		if (childrenForignKeys == null)
		{
			childrenForignKeys = new HashMap<String, DbForignKeyArray>();
			
		}
	 return childrenForignKeys; //TODO... Fill Your Childern 
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
	 public void beforeInsert()
	 {
		 HashMap<String, Object> hm = new HashMap<String, Object>();
		 hm.put(SysCode.TBL_ID, this.getTblIdValue());
		 BigDecimal nextValue = this.getTableMaintMaster().getNextSequanceNumber(SysCode.CODE_ID , hm);
		 this.setCodeIdValue(String.valueOf(nextValue));
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