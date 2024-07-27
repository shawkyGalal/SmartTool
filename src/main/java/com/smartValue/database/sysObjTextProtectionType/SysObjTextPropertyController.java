package com.smartValue.database.sysObjTextProtectionType;


import com.smartValue.database.map.SysObjText;

public interface SysObjTextPropertyController {

	public static final String INTERFACE_NAME = SysObjTextPropertyController.class.getName();
	public static final String DUMMY_Specific_Properties = "/templates/include/attBooleanSpecificProperties/dummySpecificProperties.xhtml?" ; 
	public abstract boolean isRendered(SysObjText sysObjText );
	public abstract boolean isDisabled(SysObjText sysObjText );
	
	public abstract String getMaintPageURL( String udvColumnName);
}
