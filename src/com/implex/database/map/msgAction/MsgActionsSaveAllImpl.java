package com.implex.database.map.msgAction;


import com.implex.database.ApplicationContext;
import com.implex.database.DataSet;
import com.implex.database.PersistantObject;


public class MsgActionsSaveAllImpl implements MsgAction{

	public void doAction(PersistantObject pmPoForAction, String actionType) {
		
		
		if (actionType.equals(MsgAction.OK_ACTION_TYPE))
		{
			if (pmPoForAction == null)
			{
				String msgToUser = "Persistent Object to Save All Changes not defined...please set its value... no action have been taken ";
				pmPoForAction.getMessageCommnunicatorService().sendMessageToUser(msgToUser);
				return;
			}
			pmPoForAction.getParentDataSet().saveAll();
		}
		else 
		{
			
		}
		
	}

	public void doAction(DataSet pmDsForAction, String actionType) {
		if (actionType.equals(MsgAction.OK_ACTION_TYPE))
		{
			if (pmDsForAction == null)
			{
				String msgToUser = "Persistent Object to Save All Changes not defined...please set its value... no action have been taken ";
				pmDsForAction.getDbService().getLoggedUser().getModuleServiceContainer().getMessageCommnunicatorService().sendMessageToUser(msgToUser);
				return;
			}
			pmDsForAction.saveAll();
		}
		else 
		{
			
		}
		
	}

}
