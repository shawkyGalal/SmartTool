package com.smartValue.database.attriburteProtectionType;

import java.util.ArrayList;

import com.sideinternational.web.swaf.SWAF;
import com.smartValue.database.ApplicationContext;
import com.smartValue.database.Attribute;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.map.ExcludedRolesDetails;
import com.smartValue.database.map.TableMaintDetails;
import com.smartValue.database.map.auto._SecUsrDta;
import com.smartValue.database.map.services.ModuleServicesContainer;

public class AttBooleanUsingUsersAndGroups implements AttributePropertyController {

	public boolean isRendered(Attribute attribute ) {
		boolean result = false;
		TableMaintDetails  tmd = attribute.getTableMaintDetail();
		String defaultAccessType =  tmd.getDefaultAccessTypeValue();
		if (defaultAccessType != null)
		{
		if(defaultAccessType.equals(TableMaintDetails.ALLOW_ACCESS_TO_ALL))
			result = ! isExcludedTMDForRendered(tmd);
		else if(defaultAccessType.equals(TableMaintDetails.DENY_ACCESS_TO_ALL))
			result =   isExcludedTMDForRendered(tmd);
		}
		return result;
	}
	/**
	 * checks if  that TableMainDetail is excluded for the logged user
	 * @param tmd
	 * @return
	 */
	private boolean isExcludedTMDForRendered(TableMaintDetails tmd)
	{
		try {
		String userName	=(String)( (_SecUsrDta) SWAF.getSession().getOperator()).getUsrNameValue();
		ModuleServicesContainer msc= tmd.getDbService().getModuleServiceContainer() ; 
		String currentTable=msc.getCurrentActiveModule().getDbTable().getTableName();
			ArrayList<PersistantObject> pos = tmd.getExcludedRolesDSForRendered().getPersistantObjectList() ;
			for (PersistantObject po: pos) {
				ExcludedRolesDetails erd = (ExcludedRolesDetails) po;
				if(erd.getTableNameValue().equals(currentTable)
						&& erd.getColumnNameValue().equals(tmd.getColumnNameValue()) 
						&& erd.getSecgroupIdValue().equals((userName)) )
					return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public String getMaintPageURL( String columnNameToBeChecked) {
		String result = DUMMY_Specific_Properties ; 
		if (columnNameToBeChecked.equalsIgnoreCase(TableMaintDetails.RENDERED))
		{
			result = "/templates/include/attBooleanSpecificProperties/loggedUserSpecificProperties.xhtml‬" ; 
		}
		
		return result ; 
	}
	public boolean isDisabled(Attribute attribute) {
		boolean result = false;
		TableMaintDetails  tmd = attribute.getTableMaintDetail();
		String defaultAccessType =  tmd.getDefaultAccessTypeValue();
		if (defaultAccessType != null)
		{
		if(defaultAccessType.equals(TableMaintDetails.ALLOW_ACCESS_TO_ALL))
			result =  isExcludedTMDForDisabled(tmd);
		else if(defaultAccessType.equals(TableMaintDetails.DENY_ACCESS_TO_ALL))
			result = !  isExcludedTMDForDisabled(tmd);
		}
		return result;
			
	}

	private boolean isExcludedTMDForDisabled(TableMaintDetails tmd)
	{
		try {
		String userName	=(String)( (_SecUsrDta) SWAF.getSession().getOperator()).getUsrNameValue();
		ModuleServicesContainer msc= tmd.getDbService().getModuleServiceContainer() ;
		String currentTable=msc.getCurrentActiveModule().getDbTable().getTableName();
			ArrayList<PersistantObject> pos = tmd.getExcludedRolesDSForDisabled().getPersistantObjectList() ;
			for (PersistantObject po: pos) {
				ExcludedRolesDetails erd = (ExcludedRolesDetails) po;
				if(erd.getTableNameValue().equals(currentTable)
						&& erd.getColumnNameValue().equals(tmd.getColumnNameValue()) 
						&& erd.getSecgroupIdValue().equals((userName)) )
					return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean isSecured(Attribute attribute) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isHaveSubList(Attribute attribute) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
