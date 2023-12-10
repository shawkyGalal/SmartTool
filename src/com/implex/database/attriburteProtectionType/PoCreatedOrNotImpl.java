package com.implex.database.attriburteProtectionType;

import com.implex.database.Attribute;

public class PoCreatedOrNotImpl implements AttributePropertyController {

	public String getMaintPageURL(String udvColumnName) {
		String result = DUMMY_Specific_Properties;
		
		return result ; 
	}

	public boolean isDisabled(Attribute attribute) {
		
		return attribute.getParentPersistantObject().isContainsObjectKey();
	}

	public boolean isRendered(Attribute attribute) {
		
		return !attribute.getParentPersistantObject().isContainsObjectKey();
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
