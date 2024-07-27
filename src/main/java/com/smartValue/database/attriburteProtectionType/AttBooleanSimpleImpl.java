package com.smartValue.database.attriburteProtectionType;

import com.smartValue.database.Attribute;
import com.smartValue.database.map.TableMaintDetails;

public class AttBooleanSimpleImpl implements AttributePropertyController {

	public boolean isRendered(Attribute attribute) {
		boolean result = true;
		TableMaintDetails tmd = attribute.getTableMaintDetail() ;
		result = tmd.getRendered().getBooleanValue();
		return result;
	}

	public String getMaintPageURL( String columnNameToBeChecked) {
		String result = DUMMY_Specific_Properties;
		if (columnNameToBeChecked.equalsIgnoreCase(TableMaintDetails.RENDERED))
		{
			result = "/templates/include/attBooleanSpecificProperties/simpleSpecificProperties.xhtml‬" ; 
		}
		return result ; 
	}

	public boolean isDisabled(Attribute attribute) {
		boolean result = false;
		TableMaintDetails tmd = attribute.getTableMaintDetail() ;
		result = tmd.getDisabled().getBooleanValue();
		return result;
	}

	public boolean isSecured(Attribute attribute) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isHaveSubList(Attribute attribute) {
		boolean result = false;
		TableMaintDetails tmd = attribute.getTableMaintDetail() ;
		result = (tmd != null && tmd.getHaveSubList() != null)? tmd.getHaveSubList().getBooleanValue() : result;
		return result;
	}


	
}
