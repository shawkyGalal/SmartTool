package com.implex.database.map.security;

import java.math.BigDecimal;

import com.implex.database.Attribute;
import com.implex.database.PersistantObject;
import com.implex.database.map.SysMnu;
import com.implex.database.map.TableMaintMaster;

public class SysMnuSecurityController implements PersistentObjectSecurityControl
{


	
	public boolean isRendered( Attribute pm_att)
	{
		PersistantObject persistantObject = pm_att.getParentPersistantObject();
		String key = pm_att.getKey();
		boolean result = true; 
		SysMnu sysMnu = (SysMnu) persistantObject;
		BigDecimal mnuType =  sysMnu.getMnuTypeValue(); //getAttribute(SysMnu.MNU_TYPE).getValue();

		if (key.equalsIgnoreCase(SysMnu.MNU_SQL)) 
		{
			result =  sysMnu.isMnuSqlRendered();	
		}
	
		else if (key.equalsIgnoreCase(SysMnu.MNU_URL)) {
			if (mnuType != null)
				result =  (mnuType.intValue() == SysMnu.MENU_URL_TYPE);
		}
		else if (key.equalsIgnoreCase(SysMnu.MNU_RPT)) {
			if (mnuType != null)
				result =  (mnuType.intValue() == SysMnu.MENU_RPT_TYPE);
		}
		else if (key.equalsIgnoreCase(SysMnu.MNU_PRG_ALIAS)) {
			if (mnuType != null)
				result =   (mnuType.intValue() == SysMnu.VIRTUAL_PAGE_TYPE);
		}
		else if (key.equalsIgnoreCase(SysMnu.MNU_PRG_NAME)) {
			if (mnuType != null)
				result =  (mnuType.intValue() == SysMnu.PHYSICAL_PAGE_TYPE)
						|| (mnuType.intValue() == SysMnu.VIRTUAL_PAGE_TYPE)
						|| (mnuType.intValue() == SysMnu.Menu_SQL_TYPE)
						|| (mnuType.intValue() == SysMnu.DYNAMIC_REPORT_TYPE)
						|| (mnuType.intValue() == SysMnu.MENU_RPT_TYPE);
		}
		else if (key.equalsIgnoreCase(SysMnu.MNU_ARG1)) {
			if (mnuType != null)
				result =  (mnuType.intValue() == SysMnu.DYNAMIC_REPORT_TYPE);
		}
		
		else if (key.equalsIgnoreCase(SysMnu.VPDATABASETABLE)) {
			if (mnuType != null)
				result =  (mnuType.intValue() == SysMnu.VIRTUAL_PAGE_TYPE);
		} 
		return result;
	}
	
	
	public boolean isObjectCanBeAdded( PersistantObject pm_persistentObject)
	{
		// TODO Auto-generated method stub
		return true;
	}

	
	public boolean isObjectCanBeDeleted( PersistantObject pm_persistentObject)
	{
		//String msg = "You ("+secUserData.getUsrNameValue()+") Are Not Authorized To Delete this Object of type " + pm_persistentObject; 
		//getMessageCommnunicatorService().sendMessageToUser(msg);
		return true;
	}

	
	public boolean isObjectCanBeSaved( PersistantObject pm_persistentObject)
	{
		//String msg = "You ("+secUserData.getUsrNameValue()+") Are Not Authorized To Update this Object of type " + pm_persistentObject; 
		//getMessageCommnunicatorService().sendMessageToUser(msg);
		return true;
	}

	
	public boolean isRequired( Attribute pm_att)
	{
		return ! pm_att.getTableMaintDetail().getNullable().getBooleanValue();

//		PersistantObject persistantObject = pm_att.getParentPersistantObject();
//		String key = pm_att.getKey();
//		boolean result = false;
//		TableMaintMaster tmm = persistantObject.getTableMaintMaster();
//		if (tmm!= null)
//		{
//			TableMaintDetails tmd = tmm.getTmdByColumnName(key); 
//			if (tmd != null)
//			{
//				result =  tmd.isRequired();
//			}
//		}
		
//		return result;
	}

	
	public String getOnChange( Attribute pm_att)
	{
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getOnMouseOver( Attribute pm_att)
	{
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getOnChangeRerender( Attribute pm_att)
	{	String result = "";
		String key = pm_att.getKey();
		if (key.equalsIgnoreCase(SysMnu.MNU_SQL))
		{
			result =  "sysMnuSqlEditorpanelGrid , sysMnuPanel";
		}
		else if (key.equalsIgnoreCase(SysMnu.MNU_IMG))
		{
			result =  "mnuImgPanel";
		}
		else if(key.equals(SysMnu.MNU_TYPE))
			result = "MmuTypeDependantAttributesPanel";
		
		return result;
	}

	
	public boolean isDisabled( Attribute pm_att)
	{	
		PersistantObject persistantObject = pm_att.getParentPersistantObject();
		String key = pm_att.getKey();
		SysMnu sysMnu = (SysMnu) persistantObject;
		
		if (key.equalsIgnoreCase(SysMnu.MNU_PRG_NAME) )
		{
			return isMnuPrgNameDisabled( sysMnu );
		}
		
		if (key.equalsIgnoreCase(SysMnu.MNU_PRG_ALIAS) )
		{
				return isMnuPrgTypeDisabled( sysMnu );
		}
		
		if (key.equalsIgnoreCase(SysMnu.MNU_PARENT) )
		{
			return isMnuParentDisabled( sysMnu );
		}
		
		return false;
	}

	private boolean isMnuParentDisabled( SysMnu sysMnu)
	{
		return sysMnu.isNewChild();
	}

	private boolean isMnuPrgNameDisabled( SysMnu sysMnu)
	{
		boolean result = false;
		if (sysMnu.getMnuTypeValue() != null)
		{
		int mnuType =  sysMnu.getMnuTypeValue().intValue();
		result = ( mnuType== SysMnu.DYNAMIC_REPORT_TYPE 
				|| mnuType== SysMnu.VIRTUAL_PAGE_TYPE
				||  mnuType == SysMnu.Menu_SQL_TYPE 
				|| mnuType == SysMnu.MENU_RPT_TYPE);
		}
		return result;
	}
	
	private boolean isMnuPrgTypeDisabled( SysMnu sysMnu)
	{
		boolean result = false;
		if (sysMnu.getMnuTypeValue() != null)
		{
		int mnuType =  sysMnu.getMnuTypeValue().intValue();
		result = (mnuType== SysMnu.DYNAMIC_REPORT_TYPE 
				  ||  mnuType == SysMnu.Menu_SQL_TYPE
				  || mnuType == SysMnu.MENU_RPT_TYPE 
				  
				  );
		}
		return result;
	}
	
	
	
	public String getHint( Attribute pm_att)
	{
		PersistantObject persistantObject = pm_att.getParentPersistantObject();
		String key = pm_att.getKey();
		return persistantObject.getTable().getTableName()+"." + key;
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
		return false;
	}

}
