package com.smartValue.database.sysObjTextProtectionType;

import com.smartValue.database.map.SysObjText;

public class PoCreatedOrNotImpl implements SysObjTextPropertyController {

	
	public String getMaintPageURL(String udvColumnName) {
		String result = DUMMY_Specific_Properties;
		
		return result ; 
	}

//	@Override
//	public boolean isDisabled(Attribute attribute) {
//		
//		return attribute.getParentPersistantObject().isContainsObjectKey();
//	}
//
//	@Override
//	public boolean isRendered(Attribute attribute) {
//		
//		return !attribute.getParentPersistantObject().isContainsObjectKey();
//	}

	
	public boolean isDisabled(SysObjText sysObjText) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean isRendered(SysObjText sysObjText) {
		// TODO Auto-generated method stub
		return false;
	}

}
