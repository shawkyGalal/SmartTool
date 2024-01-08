package com.implex.database.attriburteProtectionType;

import com.implex.database.Attribute;
import com.implex.database.Jsfproperties;

public class BooleanSchImpl implements AttributePropertyController {

	public boolean isRendered(Attribute attribute) {
		boolean resultFromSch = true ;
		Jsfproperties jsfp = attribute.getJsfProperties();
		if ( jsfp!= null)
			resultFromSch =  jsfp.isRendered() ;
		return resultFromSch ;
	}

	public String getMaintPageURL( String columnNameToBeChecked) {
		String result = DUMMY_Specific_Properties;
		
		return result ; 
	}

	public boolean isDisabled(Attribute attribute) {
		boolean resultFromSch = true ;
		Jsfproperties jsfp = attribute.getJsfProperties();
		if ( jsfp!= null)
			resultFromSch =  jsfp.isDisabled() ;
		return resultFromSch ;
	}

	public boolean isSecured(Attribute attribute) {
		boolean resultFromSch = true ;
		Jsfproperties jsfp = attribute.getJsfProperties();
		if ( jsfp!= null)
			resultFromSch =  jsfp.isSecured() ;
		return resultFromSch ;
	}

	public boolean isHaveSubList(Attribute attribute) {
		// TODO Auto-generated method stub
		return false;
	}


	
}
