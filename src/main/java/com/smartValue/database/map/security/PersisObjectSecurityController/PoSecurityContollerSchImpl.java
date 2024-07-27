package com.smartValue.database.map.security.PersisObjectSecurityController;

import com.smartValue.database.PersistantObject;

public class PoSecurityContollerSchImpl implements IPersisObjectController {

	
	public boolean isCreatable(PersistantObject pm_po) {
		return  (pm_po.getSecurityController() == null) || pm_po.getSecurityController().isObjectCanBeAdded( pm_po); 
	}

	
	public boolean isDeletable(PersistantObject pm_po) {
		return  (pm_po.getSecurityController() == null) || pm_po.getSecurityController().isObjectCanBeDeleted( pm_po); 
	}

	
	public boolean isUpdatable(PersistantObject pm_po) {
		return  (pm_po.getSecurityController() == null) || pm_po.getSecurityController().isObjectCanBeSaved( pm_po); 
	
	}

	
	public String getMaintFormUrl() {
		return "/templates/include/securityControllersMaintForms/schSecurityControllerMaintForm.xhtml";
	}

}

