package com.smartValue.support.map;

import com.smartValue.database.Attribute;
import com.smartValue.database.AttributeChangeValidator;
import com.smartValue.database.DbForignKeyArray;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.audit.AuditInDbTriggerHandler;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.security.PersistentObjectSecurityControl;
import com.smartValue.database.trigger.TriggerHandler;
import com.smartValue.support.map.auto._LuExecutables;

import java.util.HashMap;

public class LuExecutables extends _LuExecutables {
	 private static HashMap<String, DbForignKeyArray> childrenForignKeys;
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
	  private com.smartValue.database.PersistantObject  abc ;  
	  public com.smartValue.database.PersistantObject getAbc() 
	  { 
		 if ( this.abc== null)
		 {
			 refreshAbc() ;  
		 }
		 return abc ; 
	  } 
	  private com.smartValue.database.PersistantObject calcAbc() 
	  { 
		  com.smartValue.database.PersistantObject result = null ; 
		  //Write Down Your Code here to calculate the value of abc... 
		  return result ;  
	  } 
	 
	  public void refreshAbc() 
	  { 
		  //Important Please call this method in the afterAttributeChange() method for any attribute used to calculate this variable 
		  this.abc = calcAbc() ; 
	  } 
		@Override
		public void beforeAttributeChange(Attribute pm_att) throws Exception {
		}
		@Override
		public void afterAttributeChange(Attribute pm_att) {
		}
}