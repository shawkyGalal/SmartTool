package com.smartValue.database.map;

import com.smartValue.database.Attribute;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.map.security.PersistentObjectSecurityControl;

public class SavedSearchSecurityControler implements
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
