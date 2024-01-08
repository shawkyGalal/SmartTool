package com.implex.database.sysObjTextProtectionType;

import com.implex.database.map.SysObjText;


public class SysObjTextBooleanSimpleImpl implements SysObjTextPropertyController {
	
	public boolean isRendered(SysObjText sysObjText) {
		boolean result = false;
		result = sysObjText.getRendered().getBooleanValue();
		return result;
	}

	
	public String getMaintPageURL( String columnNameToBeChecked) {
		String result = DUMMY_Specific_Properties;
		if (columnNameToBeChecked.equalsIgnoreCase(SysObjText.RENDERED))
		{
			result = "/templates/include/attBooleanSpecificProperties/simpleSpecificProperties.xhtml?" ; 
		}
		return result ; 
	}

	
	public boolean isDisabled(SysObjText sysObjText) {
		boolean result = false;
		result = sysObjText.getRendered().getBooleanValue();
		return result;
		}
	
}
