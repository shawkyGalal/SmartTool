package com.smartValue.database.map.security;

import com.smartValue.database.Attribute;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.map.TableMaintDetails;
import com.smartValue.database.map.TableMaintMaster;

public class SecurityControlHandlerTmdImpl implements PersistentObjectSecurityControl
{

	
	public String getOnChange( Attribute pm_att)
	{return null;}

	
	public String getOnChangeRerender (Attribute pm_att)
	{	return null;	}

	
	public String getOnMouseOver( Attribute pm_att)
	{	return null;	}

	
	public String getHint( Attribute pm_att)
	{return pm_att.getKey();		}

	
	public boolean isRendered( Attribute pm_att)
	{	return true;	}

	
	public boolean isDisabled( Attribute pm_att)
	{	return false;	}

	
	public boolean isObjectCanBeAdded( PersistantObject pm_persistentObject)
	{	return true;	}

	
	public boolean isObjectCanBeDeleted( PersistantObject pm_persistentObject)
	{	return true;	}

	
	public boolean isObjectCanBeSaved( PersistantObject pm_persistentObject)
	{	return true;	}

	
	public boolean isRequired( Attribute pm_att)
	{	TableMaintDetails tmd = pm_att.getTableMaintDetail();
		return tmd != null && tmd.getNullable() != null && ! tmd.getNullable().getBooleanValue();
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


	
	public boolean isStoredEncrypted( Attribute pm_att)
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
