package com.implex.database.map.security;

import com.implex.database.ApplicationContext;
import com.implex.database.Attribute;
import com.implex.database.PersistantObject;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.TableMaintMaster;
import com.sideinternational.sas.BaseException;


 //SecUserSecurityControlHandler
 public class SecUserSecurityControlHandler implements PersistentObjectSecurityControl{



	
	public String getHint( Attribute pm_att)
	{
		return pm_att.getParentPersistantObject().getTable().getTableName() +"." + pm_att.getKey() ; 
	}

	
	public String getOnChange( Attribute pm_att)
	{
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getOnChangeRerender( Attribute pm_att)
	{
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getOnMouseOver( Attribute pm_att)
	{
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean isDisabled( Attribute pm_att)
	{
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean isObjectCanBeAdded( PersistantObject pm_persistentObject)
	{
		// TODO Auto-generated method stub
		return true;
	}

	
	public boolean isObjectCanBeDeleted( PersistantObject pm_persistentObject)
	{
		boolean result = false;
		SecUsrDta sud = (SecUsrDta) pm_persistentObject ; 
		if (sud.getUsrNameValue().equalsIgnoreCase(SecUsrDta.ADMINISTRATOR))
		{
			result = false;
			pm_persistentObject.getMessageCommnunicatorService().sendMessageToUser("This is a Bulit In Group ... You can Not Delete It");
		}
		else
		{
			result = true;
		}
		return result;
	}

	
	public boolean isObjectCanBeSaved( PersistantObject pm_persistentObject)
	{
		// TODO Auto-generated method stub
		return true;
	}

	
	public boolean isRendered( Attribute pm_att)
	{
		PersistantObject persistantObject = pm_att.getParentPersistantObject();
		String key = pm_att.getKey();
		SecUsrDta sud = (SecUsrDta) persistantObject;
		boolean result = true;
		
		if (key.equalsIgnoreCase(SecUsrDta.USR_PASSWORD ))
		{	
			result =  sud.isPassWordRendered() ;
		}
		return result;
	}


	
	public boolean isRequired( Attribute pm_att)
	{
		return false ; 
//		return ! pm_att.getTableMaintDetail().getNullable().getBooleanValue();
//		PersistantObject persistantObject = pm_att.getParentPersistantObject();
//		String key = pm_att.getKey();
//		boolean result = false;
//		TableMaintMaster tmm = persistantObject.getTableMaintMaster();
//		if (tmm!= null && tmm.getTmdByColumnName(key) != null)
//		result =  tmm.getTmdByColumnName(key).getNullable().getBooleanValue();
//		return result;
	}

	
	public boolean isSecured( Attribute pm_att)
	{
		PersistantObject persistantObject = pm_att.getParentPersistantObject();
		String key = pm_att.getKey();
		boolean result = false;
		TableMaintMaster tmm = persistantObject.getTableMaintMaster();
		if (tmm!= null && tmm.getTmdByColumnName(key) != null)
		result =  tmm.getTmdByColumnName(key).isSecured();
		return result;
		
	}

	
	public boolean isStoredEncrypted(Attribute pm_att)
	{
		PersistantObject persistantObject = pm_att.getParentPersistantObject();
		String key = pm_att.getKey();
		boolean result = false;
		TableMaintMaster tmm = persistantObject.getTableMaintMaster();
		if (tmm!= null && tmm.getTmdByColumnName(key) != null)
		result =  tmm.getTmdByColumnName(key).getEncryped().getBooleanValue();
		return result;
		
	}

	}
