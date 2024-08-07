package com.smartValue.database.map;

import java.util.HashMap;

import com.smartValue.database.Attribute;
import com.smartValue.database.AttributeChangeValidator;
import com.smartValue.database.DataSet;
import com.smartValue.database.DbForignKey;
import com.smartValue.database.DbForignKeyArray;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.audit.AuditInDbTriggerHandler;
import com.smartValue.database.map.auto._SysCodeMaster;
import com.smartValue.database.map.security.PersistentObjectSecurityControl;
import com.smartValue.database.trigger.TriggerHandler;

public class SysCodeMaster extends _SysCodeMaster {
	private static  HashMap<String, DbForignKeyArray> childrenForignKeys;
	
	@Override
	public HashMap<String, DbForignKeyArray> getChildrenForignKeys() 
	{
		if (childrenForignKeys == null) 
		{
			childrenForignKeys = new HashMap<String, DbForignKeyArray>();
			childrenForignKeys.put(SysCode.DB_TABLE_NAME, SysCodeMaster.getForignKeyForSysCodes());
			
		}
		return childrenForignKeys; 
	}
	private static DbForignKeyArray getForignKeyForSysCodes() {
		return new DbForignKeyArray ( new DbForignKey[]{
				new DbForignKey(SysCodeMaster.TBLD_ID, SysCode.TBL_ID)}, false);	}
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
	 
	 public DataSet getSysCodesDS() throws Exception {
			return getChildrenDataSet(SysCode.DB_TABLE_NAME, SysCode.class, false);
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