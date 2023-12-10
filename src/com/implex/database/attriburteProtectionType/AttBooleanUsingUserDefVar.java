package com.implex.database.attriburteProtectionType;


import com.implex.database.ApplicationContext;
import com.implex.database.Attribute;
import com.implex.database.PersistantObject;
import com.implex.database.map.TableMaintDetails;
import com.implex.database.map.TableMaintMaster;
import com.implex.database.map.UsrDefVar;

public class AttBooleanUsingUserDefVar implements AttributePropertyController{

	public boolean isRendered(Attribute attribute  ) {
		TableMaintDetails  tmd = attribute.getTableMaintDetail();
		String udvName = (String) tmd.getUsrDefVarIdForRenderValue();
		return this.isUdvValueTrue(attribute, udvName);
		
	}



	public String getMaintPageURL( String columnNameToBeChecked) {
		String result = DUMMY_Specific_Properties ; 
		if (columnNameToBeChecked.equalsIgnoreCase(TableMaintDetails.RENDERED))
		{
			result = "/templates/include/attBooleanSpecificProperties/userDefVarSpecificProperties.xhtml‬" ; 
		}
		return result ; 
	}


	public boolean isDisabled(Attribute attribute) {
		TableMaintDetails  tmd = attribute.getTableMaintDetail();
		String udvName = (String) tmd.getUsrDefVarIdForDisabledValue();
		return this.isUdvValueTrue(attribute, udvName);
	}

	private boolean isUdvValueTrue(Attribute attribute  , String pm_usrDefVarName )
	{
		boolean result = true;
		try{
		PersistantObject po = attribute.getParentPersistantObject() ; 
		String varNameWithoutDelimiter = pm_usrDefVarName.substring(PersistantObject.USER_DEFINED_DELIMTER.length());
		UsrDefVar udv =  po.getUsrDefVar(varNameWithoutDelimiter , TableMaintMaster.USR_DEFAULTS_GRP_NAME);
		String udvValue = udv.calculateValue(po);
		result =  Attribute.getBooleanvalue(udvValue);
		}catch(Exception e)
		{
			attribute.getMessageCommnunicatorService().sendExceptionToUser(e);
		}

		return result;
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
