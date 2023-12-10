package com.implex.database.map.msgAction;

import com.implex.database.ApplicationContext;
import com.implex.database.DataSet;
import com.implex.database.PersistantObject;
import com.implex.database.map.SysMnu;
import com.implex.database.map.services.ModuleServicesContainer;

public class MsgActionsDelPoImpl implements MsgAction{

	public static final String RemoveOnlyFromDataSet = "RemoveOnlyFromDataSet";
	public void doAction(PersistantObject pmPoForAction, String actionType) {
		
		if (actionType.equals( MsgAction.OK_ACTION_TYPE))
		{
			if (pmPoForAction == null)
			{
				pmPoForAction.getMessageCommnunicatorService().sendMessageToUser("Persistent Object to Delete not defined...please set its value... no action have been taken ");
				return;
			}
			DataSet ds = pmPoForAction.getParentDataSet();
			try {
				pmPoForAction.getDbService().delete(pmPoForAction);
				if(ds != null)
					ds.getPersistantObjectList().remove(pmPoForAction);
				if (pmPoForAction instanceof SysMnu)
				{
					ModuleServicesContainer msc = pmPoForAction.getDbService().getModuleServiceContainer(); 
					msc.getSysMenuServices().setAllSysMenuTree(null);
				}
			} catch (Exception e) {
				pmPoForAction.getMessageCommnunicatorService().sendExceptionToUser(e);
				e.printStackTrace();
			}
		
		}
		else 
		{
			
			
		}
		
	}
	public void doAction(DataSet pmDsForAction, String actionType) {
		// TODO Auto-generated method stub
		
	}

}
