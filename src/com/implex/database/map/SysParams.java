package com.implex.database.map;


import java.util.HashMap;

import com.implex.database.Attribute;
import com.implex.database.DbForignKeyArray;
import com.implex.database.map.auto._SysParams;
import com.implex.database.map.security.PersistentObjectSecurityControl;
import com.implex.database.map.security.SysParamsSecurityControlHandlerImpl;
import com.implex.database.trigger.TriggerHandler;

public class SysParams extends _SysParams {
	private SysParamsSecurityControlHandlerImpl securityControlHandlerImpl = new SysParamsSecurityControlHandlerImpl();
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
	@Override
	public TriggerHandler getTriggerHandler() 
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