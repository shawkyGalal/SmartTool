package com.implex.database.map.msgAction;


import com.implex.database.ApplicationContext;
import com.implex.database.DataSet;
import com.implex.database.PersistantObject;


public class MsgActionsRemovePoImpl implements MsgAction{

	public void doAction(PersistantObject pmPoForAction, String actionType) {
		
		try {			
			if (actionType.equals(MsgAction.OK_ACTION_TYPE))
			{
				if (pmPoForAction == null)
				{
					String msgToUser = "Persistent Object to Remove From DS not defined...please set its value... no action have been taken ";
					pmPoForAction.getMessageCommnunicatorService().sendMessageToUser(msgToUser);
					return;
				}
				DataSet  ds =  pmPoForAction.getParentDataSet();
				ds.markObjectToBeDeleted(pmPoForAction);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public void doAction(DataSet pmDsForAction, String actionType) {
		// TODO Auto-generated method stub
		
	}

}
