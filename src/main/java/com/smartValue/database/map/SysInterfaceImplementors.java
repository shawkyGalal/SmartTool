package com.smartValue.database.map;

import java.util.HashMap;

import com.smartValue.database.Attribute;
import com.smartValue.database.AttributeChangeValidator;
import com.smartValue.database.DbForignKeyArray;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.audit.AuditInDbTriggerHandler;
import com.smartValue.database.map.auto._SysInterfaceImplementors;
import com.smartValue.database.map.security.PersistentObjectSecurityControl;
import com.smartValue.database.trigger.TriggerHandler;

public class SysInterfaceImplementors extends _SysInterfaceImplementors {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	public Object getImplInstance()
	{
		Object result = null ; 
		 try {
			result = Class.forName( this.getImpClassValue()).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return result ; 
	}
	//.....................................................................................
	/* i found out that the object which is loaded and the object wich was selected will be
	 *  compared when setting to the backing bean. So if your object�s
	 *  Class has not overridden the equals method this error message is shown. */
	  public boolean equals(Object obj) { 
			if(this == obj) { 
			return true; 
			} 
			if (!(obj instanceof SysInterfaceImplementors)) { 
			return false;  
			} 
			SysInterfaceImplementors sysInterImpl = (SysInterfaceImplementors)obj; 
			return this.getImpCodeValue().equals(sysInterImpl.getImpCodeValue()) 
			&& this.getImpInterfaceValue().equals(sysInterImpl.getImpInterfaceValue())
			&& this.getImpClassValue().equals(sysInterImpl.getImpClassValue())     ; 
	
		} 
	//.....................................................................................
	@Override
	public void afterAttributeChange(Attribute att) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void beforeAttributeChange(Attribute att) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
