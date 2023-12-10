package com.implex.database.map.security.PersisObjectSecurityController;

import com.implex.database.Attribute;
import com.implex.database.PersistantObject;
import com.implex.database.map.SysPoAttachments;
import com.implex.database.map.security.PersistentObjectSecurityControl;

public class SysPoAttachmentsSecurity implements
		PersistentObjectSecurityControl {


	
	public String getHint(Attribute att) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getOnChange(Attribute att) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getOnChangeRerender(Attribute att) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getOnMouseOver(Attribute att) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean isDisabled(Attribute att) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean isObjectCanBeAdded(PersistantObject pmPersistentObject) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean isObjectCanBeDeleted(PersistantObject pmPersistentObject) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean isObjectCanBeSaved(PersistantObject pmPersistentObject) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean isRendered(Attribute att) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean isRequired(Attribute att) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean isSecured(Attribute att) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean isStoredEncrypted(Attribute att) {
		// TODO Auto-generated method stub
		return false;
	}

}
