package com.smartValue.database;

import java.util.HashMap;

import com.smartValue.database.audit.AuditInDbTriggerHandler;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.security.PersistentObjectSecurityControl;
import com.smartValue.database.map.security.SecurityControlHandlerFactory;
import com.smartValue.database.trigger.TriggerHandler;

public class TablePersistantObject extends PersistantObject
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * This Class will be used as default Persistent object, where directJDBCServiceImpl will estimate - by overriding the getTable() method-  table name from the query.
	 */
	TriggerHandler triggerHandler = new AuditInDbTriggerHandler();
	public TablePersistantObject()
	{
		
	}


	@Override
	public TriggerHandler getTriggerHandler()
	{
		return triggerHandler;
	}

	@Override
	public PersistentObjectSecurityControl getSecurityController()
	{
		return SecurityControlHandlerFactory.getTmdImpl();
	}


	@Override
	public DbTable getTable()
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

	private static HashMap<String, DbForignKeyArray> childrenForignKeys = new HashMap<String, DbForignKeyArray>() ;
	@Override
	protected HashMap<String, DbForignKeyArray> getChildrenForignKeys()
	{
		// TODO Auto-generated method stub
		return childrenForignKeys;
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
