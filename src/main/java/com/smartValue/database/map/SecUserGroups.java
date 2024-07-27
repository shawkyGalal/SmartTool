package com.smartValue.database.map;

import java.util.HashMap;

import com.smartValue.database.Attribute;
import com.smartValue.database.AttributeChangeValidator;
import com.smartValue.database.DataSet;
import com.smartValue.database.DbForignKeyArray;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.audit.AuditInDbTriggerHandler;
import com.smartValue.database.map.auto._SecUserGroups;
import com.smartValue.database.map.security.PersistentObjectSecurityControl;
import com.smartValue.database.trigger.TriggerHandler;

public class SecUserGroups extends _SecUserGroups {
	
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
		// TODO Auto-generated method stub
	 return null; 
	}
	@Override
	protected HashMap<String, DbForignKeyArray> getChildrenForignKeys() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PersistentObjectSecurityControl getSecurityController()
	{
		// Pleazzzzzzzzzzzzzzzzzzzzze do nou use the default SecurityControlHandlerFactory.getTmdImpl(); 
		// it will cause closed loop feedback...
		return null; //SecurityControlHandlerFactory.getTmdImpl();
	}
	@Override
	public void afterAttributeChange(Attribute att) {
		if (att.getKey().equalsIgnoreCase(SecUserGroups.USER_DEFAULT_ROLE))
		{
			PersistantObject parentPo =  this.getParent().getPersistantObject() ; 
			if ( parentPo instanceof SecUsrDta)
			{
				SecUsrDta secUsrDta = (SecUsrDta) parentPo ; 
				secUsrDta.resetDefaultSecRole();
				secUsrDta.resetUserGroupDS();
			}
		}
		
	}
	@Override
	public void beforeAttributeChange(Attribute att) throws Exception {
		// TODO Auto-generated method stub
		
	}
	SecRole secRole ; 
	public SecRole getSecRole( boolean m_refresh)
	{
		if (this.secRole == null || m_refresh) 
		{
			String query =  "Select * from " +SecRole.DB_TABLE_OWNER + "." + SecRole.DB_TABLE_NAME + " Where "+SecRole.ROLE_ID+" = " + this.getRoleIdValue() ; 
			DataSet ds = this.getDbService().queryForDataSet(query , SecRole.class) ;
			this.secRole =  (SecRole) ds.getPersistantObjectList().get(0) ;
		}
		return this.secRole ; 
	}
}
