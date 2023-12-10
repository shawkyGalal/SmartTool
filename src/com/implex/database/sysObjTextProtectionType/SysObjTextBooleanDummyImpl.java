package com.implex.database.sysObjTextProtectionType;

import com.implex.database.map.SysObjText;

public class SysObjTextBooleanDummyImpl implements SysObjTextPropertyController {

	
	public boolean isRendered(SysObjText sysObjText ) {
		boolean result = true;
		return result;
	}

	
	public String getMaintPageURL( String columnNameToBeChecked) {
		String result = DUMMY_Specific_Properties;
		
		return result ; 
	}

	
	public boolean isDisabled(SysObjText sysObjText) {
		// TODO Auto-generated method stub
		return false;
	}


	
}
