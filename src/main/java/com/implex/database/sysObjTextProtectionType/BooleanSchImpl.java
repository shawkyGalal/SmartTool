package com.implex.database.sysObjTextProtectionType;

import com.implex.database.map.SysObjText;

public class BooleanSchImpl implements SysObjTextPropertyController {

	
	public boolean isRendered(SysObjText sysObjText) {
		boolean resultFromSch = true ;
//		Jsfproperties jsfp = attribute.getJsfProperties();
//		if ( jsfp!= null)
//			resultFromSch =  jsfp.isRendered() ;
		return resultFromSch ;
	}

	
	public String getMaintPageURL( String columnNameToBeChecked) {
		String result = DUMMY_Specific_Properties;
		
		return result ; 
	}

	
	public boolean isDisabled(SysObjText sysObjText) {
		boolean resultFromSch = true ;
//		Jsfproperties jsfp = attribute.getJsfProperties();
//		if ( jsfp!= null)
		
		return resultFromSch ;
	}


	
}
