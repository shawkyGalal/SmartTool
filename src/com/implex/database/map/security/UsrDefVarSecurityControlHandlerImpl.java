package com.implex.database.map.security;

import com.implex.database.Attribute;
import com.implex.database.PersistantObject;
import com.implex.database.map.UsrDefVar;
import com.sideinternational.sas.BaseException;
public class UsrDefVarSecurityControlHandlerImpl implements PersistentObjectSecurityControl
{
	
	public boolean isStoredEncrypted(Attribute pm_att) {
	return false;
}
	
	public boolean isSecured( Attribute pm_att) {
	return false;
	}
	
	public boolean isRequired( Attribute pm_att) {
	return false;
}
	
	public boolean isRendered( Attribute pm_att) {
	return true;
}
	
	public boolean isObjectCanBeSaved(	PersistantObject pmPersistentObject) {
		UsrDefVar udv = (UsrDefVar) pmPersistentObject;
		return ! (udv.isSystemGenerated() && udv.getUdvVar().isChanged());
	}
	
	public boolean isObjectCanBeDeleted( PersistantObject pmPersistentObject) {
		return true;
	}
	
	public boolean isObjectCanBeAdded( PersistantObject pmPersistentObject) {
	return true;
}
	
	public boolean isDisabled( Attribute pm_att) {
	return false;
}
	
	public String getOnMouseOver( Attribute pm_att) {
	return null;
	}
	
 public String getOnChangeRerender( Attribute pm_att) {
	
		if(pm_att.getKey().equalsIgnoreCase(UsrDefVar.UDV_VAR_FIELD))
			return "formulaResult";
		return null;
}
	
	public String getOnChange( Attribute pm_att) {
	return null;
}
	
	public String getHint( Attribute pm_att) {
	return null;
}
}

