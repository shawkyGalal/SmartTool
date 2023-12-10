package com.implex.database.sysObjTextProtectionType;


import com.implex.database.ApplicationContext;
import com.implex.database.Attribute;
import com.implex.database.PersistantObject;
import com.implex.database.map.SysObjText;
import com.implex.database.map.TableMaintMaster;
import com.implex.database.map.UsrDefVar;

public class SysObjTextBooleanUsingUserDefVar implements SysObjTextPropertyController{

	
	public boolean isRendered(SysObjText sysObjText) {
		String udvName = (String)sysObjText.getUsrDefVarIdForDisabledValue();
		return this.isUdvValueTrue(sysObjText,udvName);
	}


	
	public String getMaintPageURL( String columnNameToBeChecked) {
		String result = DUMMY_Specific_Properties ; 
		if (columnNameToBeChecked.equalsIgnoreCase(SysObjText.RENDERED))
		{
			result = "/templates/include/attBooleanSpecificProperties/userDefVarSpecificProperties.xhtml" ; 
		}
		return result ; 
	}


	
	
	public boolean isDisabled(SysObjText sysObjText) {
		String udvName = (String)sysObjText.getUsrDefVarIdForDisabledValue();
		return this.isUdvValueTrue(sysObjText,udvName);
	}


	private boolean isUdvValueTrue(SysObjText sysObjText, String pm_usrDefVarName )
	{	// TODO: NOT YET FINISHED PLZ REVIEW. HAITHAM OR SAKR 
		boolean result = true;
		try{ 
		String varNameWithoutDelimiter = pm_usrDefVarName.substring(PersistantObject.USER_DEFINED_DELIMTER.length());
		UsrDefVar udv =  sysObjText.getUsrDefVar(varNameWithoutDelimiter , TableMaintMaster.USR_DEFAULTS_GRP_NAME);
		String udvValue = udv.calculateValue(sysObjText);
		result =  Attribute.getBooleanvalue(udvValue);
		}catch(Exception e)
		{
			sysObjText.getMessageCommnunicatorService().sendExceptionToUser(e);
		}

		return result;
	}

}
