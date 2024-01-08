package com.implex.database.map;

import java.util.HashMap;

import com.implex.database.Attribute;
import com.implex.database.AttributeChangeValidator;
import com.implex.database.DbForignKeyArray;
import com.implex.database.PersistantObject;
import com.implex.database.audit.AuditInDbTriggerHandler;
import com.implex.database.map.auto._ColumnChangeNotifiers;
import com.implex.database.map.security.PersistentObjectSecurityControl;
import com.implex.database.map.services.InterfaceImplServices;
import com.implex.database.map.validators.ColumnChangeNotifiersChangeValidator;
import com.implex.database.notifications.AttributeChangeNotifier;
import com.implex.database.trigger.TriggerHandler;

public class ColumnChangeNotifiers extends _ColumnChangeNotifiers {
	@Override
	public HashMap<String, DbForignKeyArray> getChildrenForignKeys() 
	{
	 return null; //TODO... Fill Your Childern 
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
	 return new ColumnChangeNotifiersChangeValidator(pm_secUserData , pm_po  , pm_key); 
	}
	@Override
	public PersistentObjectSecurityControl getSecurityController() {
		// TODO Auto-generated method stub
		return null;
	}
	AttributeChangeNotifier attChangeNotifier ;
	
	public AttributeChangeNotifier getAttChangeNotifier()
	{
		if (attChangeNotifier == null)
		{
			attChangeNotifier = calcAttChangeNotifier();
		}
		return attChangeNotifier ;
	}
	private AttributeChangeNotifier calcAttChangeNotifier() {
		AttributeChangeNotifier result ;
		InterfaceImplServices ims = new InterfaceImplServices(this.getDbService());
		SysInterfaceImplementors  sii =ims.getImplementorByCode(this.getChangeNotifyImplIdValue().toString(), AttributeChangeNotifier.class.getName()) ;
		result = (AttributeChangeNotifier)sii.getImplInstance();
		
		return result ;
	}
	
	public void setChangeNotifyImplIdValue(java.math.BigDecimal pm_changeNotifyImplId)
	{
		super.setChangeNotifyImplIdValue(pm_changeNotifyImplId);
		attChangeNotifier = calcAttChangeNotifier();
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