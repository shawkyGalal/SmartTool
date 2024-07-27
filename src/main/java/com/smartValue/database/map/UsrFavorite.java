package com.smartValue.database.map;

import java.util.HashMap;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.Attribute;
import com.smartValue.database.AttributeChangeValidator;
import com.smartValue.database.DbForignKeyArray;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.map.auto._UsrFavorite;
import com.smartValue.database.map.security.PersistentObjectSecurityControl;
import com.smartValue.database.trigger.TriggerHandler;

public class UsrFavorite extends _UsrFavorite {
	
	private SysMnu correspondingSysMnu;

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
	
	public SysMnu getCorespondingSysMnu()
	{
		if (correspondingSysMnu == null)
		{
			//ModuleServicesContainer msc = (ModuleServicesContainer) SWAF.getManagedBean("moduleServicesContainer");
			correspondingSysMnu = this.getDbService().getModuleServiceContainer().getSysMenuServices().getSysMnuByMnuCode(this.getMnuCodeValue());
		}
		return correspondingSysMnu;

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
