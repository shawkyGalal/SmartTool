package com.smartValue.database.map.security;
import com.sideinternational.sas.BaseException;
import com.smartValue.database.Attribute;
import com.smartValue.database.PersistantObject;
public class SqlBoundVarsSecurityControlHandlerImpl implements PersistentObjectSecurityControl
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
	return true;
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
	return null;
}
	
	public String getOnChange( Attribute pm_att) {
	return null;
}
	
	public String getHint( Attribute pm_att) {
	return null;
}

}