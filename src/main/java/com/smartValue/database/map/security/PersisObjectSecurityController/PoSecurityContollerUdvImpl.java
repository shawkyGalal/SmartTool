package com.smartValue.database.map.security.PersisObjectSecurityController;

import com.smartValue.database.PersistantObject;
import com.smartValue.database.map.TableMaintMaster;
import com.smartValue.database.map.UsrDefVar;

public class PoSecurityContollerUdvImpl implements IPersisObjectController {

	
	public boolean isCreatable(PersistantObject pm_po) {
		
		return getUserDefVarBooleanValue (pm_po , TableMaintMaster.IS_LOGGED_USER_CAN_CREATE) ; 
	}

	
	public boolean isDeletable(PersistantObject pm_po) {
		return getUserDefVarBooleanValue (pm_po , TableMaintMaster.IS_LOGGED_USER_CAN_DELETE) ;
	}

	
	public boolean isUpdatable(PersistantObject pm_po) {
		// TODO Auto-generated method stub
		return this.getUserDefVarBooleanValue (pm_po , TableMaintMaster.IS_LOGGED_USER_CAN_UPDATE) ;
	}

	private boolean getUserDefVarBooleanValue(PersistantObject po, String udvUniqueName)
	{
		UsrDefVar udv = po.getUsrDefVar(udvUniqueName);
		return ( udv!= null &&  udv.calculateValue(po).equals("'Y'"));
	}

	
	public String getMaintFormUrl() {
		return "/templates/include/securityControllersMaintForms/udvSecurityControllerMaintForm.xhtml";

	}
}
