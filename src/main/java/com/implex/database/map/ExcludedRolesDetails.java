package com.implex.database.map;


import java.util.HashMap;

import com.implex.database.Attribute;
import com.implex.database.AttributeChangeValidator;
import com.implex.database.DbForignKeyArray;
import com.implex.database.PersistantObject;
import com.implex.database.map.auto._ExcludedRolesDetails;
import com.implex.database.map.security.PersistentObjectSecurityControl;
import com.implex.database.trigger.TriggerHandler;

public class ExcludedRolesDetails extends _ExcludedRolesDetails {
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
		// TODO Auto-generated method stub
	 return null; 
	}
	@Override
	public TriggerHandler getTriggerHandler() 
	{
		// TODO Auto-generated method stub
	 return null; 
	}
	@Override
	public AttributeChangeValidator getAttributeChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po,
			String pm_key)
	{
		// TODO Auto-generated method stub
		return null;
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
