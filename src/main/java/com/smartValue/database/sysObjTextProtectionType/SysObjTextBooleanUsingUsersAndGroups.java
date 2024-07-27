package com.smartValue.database.sysObjTextProtectionType;


import com.smartValue.database.map.SysObjText;
import com.smartValue.database.map.TableMaintDetails;

public class SysObjTextBooleanUsingUsersAndGroups implements SysObjTextPropertyController {

	
	public boolean isRendered(SysObjText sysObjText) {
		boolean result= false;
		String defaultAccessType = sysObjText.getDefaultAccessTypeValue();
		if(defaultAccessType!=null)
		{
			if(defaultAccessType.equals(TableMaintDetails.ALLOW_ACCESS_TO_ALL))
				result = true;
			else if (defaultAccessType.equals(TableMaintDetails.DENY_ACCESS_TO_ALL))
				result= false;
		}
		return result;
	}
//
//	/**
//	 * checks if  that TableMainDetail is excluded for the logged user
//	 * @param tmd
//	 * @return
//	 */
//	private boolean isExcludedTMD(TableMaintDetails tmd)
//	{
//		try {
//			ArrayList<PersistantObject> pos = tmd.getExcludedRolesDS().getPersistantObjectList() ;
//			for (PersistantObject po: pos) {
//				ExcludedRolesDetails erd = (ExcludedRolesDetails) po;
//				if(erd.getTableNameValue().equals(TableMaintDetails.DB_TABLE_NAME)
//						&& erd.getColumnNameValue().equals(tmd.getColumnNameValue()) 
//						&& erd.getSecgroupIdValue().equals(((SecUsrDta)SWAF.getSession().getOperator()).getUsrNameValue()))
//					return true;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
	
	public String getMaintPageURL( String columnNameToBeChecked) {
		String result = DUMMY_Specific_Properties ; 
		if (columnNameToBeChecked.equalsIgnoreCase(SysObjText.RENDERED))
		{
			result = "/templates/include/attBooleanSpecificProperties/loggedUserSpecificProperties.xhtml?" ; 
		}
		
		return result ; 
	}
	
	
	public boolean isDisabled(SysObjText sysObjText) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
