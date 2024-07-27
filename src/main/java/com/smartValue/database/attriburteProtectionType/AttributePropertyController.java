package com.smartValue.database.attriburteProtectionType;

import com.smartValue.database.Attribute;

public interface AttributePropertyController {

	public static final String INTERFACE_NAME = AttributePropertyController.class.getName();
	public static final String DUMMY_Specific_Properties = "/templates/include/attBooleanSpecificProperties/dummySpecificProperties.xhtml‬" ; 
	public abstract boolean isRendered(Attribute attribute );
	public abstract boolean isDisabled(Attribute attribute );
	public abstract boolean isSecured(Attribute attribute );
	public abstract boolean isHaveSubList(Attribute attribute );
	public abstract String getMaintPageURL( String udvColumnName);
}
