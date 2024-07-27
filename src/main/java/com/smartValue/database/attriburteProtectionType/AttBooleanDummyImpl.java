package com.smartValue.database.attriburteProtectionType;

import com.smartValue.database.Attribute;

public class AttBooleanDummyImpl implements AttributePropertyController {

	public boolean isRendered(Attribute attribute ) {
		boolean result = true;
		return result;
	}

	public String getMaintPageURL( String columnNameToBeChecked) {
		String result = DUMMY_Specific_Properties;
		
		return result ; 
	}

	public boolean isDisabled(Attribute attribute) {
		// TODO Auto-generated method stub
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
