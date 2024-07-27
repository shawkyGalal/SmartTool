package com.smartValue.database.map.security.PersisObjectSecurityController;

import com.smartValue.database.PersistantObject;

public class PoSecurityContollerSimpleImpl implements IPersisObjectController {

	
	public boolean isCreatable(PersistantObject pm_po) {
		
		return  pm_po.getTableMaintMaster().getSimplyCreatable().getBooleanValue(); 
	}

	
	public boolean isDeletable(PersistantObject pm_po) {
		
		return  pm_po.getTableMaintMaster().getSimplyDeletable().getBooleanValue(); 
	}

	
	public boolean isUpdatable(PersistantObject pm_po) {
		
		return  pm_po.getTableMaintMaster().getSimplyUpdatable().getBooleanValue(); 

	}

	
	public String getMaintFormUrl() {
		return "/templates/include/securityControllersMaintForms/simpleSecurityControllerMaintForm.xhtml";
	}


}
