package com.smartValue.database.map.msgAction;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.DataSet;
import com.smartValue.database.PersistantObject;

public class MsgActionsSavePoImpl implements MsgAction{

	public void doAction(PersistantObject pmPoForAction, String actionType) {
		
		
		if (actionType.equals(MsgAction.OK_ACTION_TYPE))
		{
			if (pmPoForAction == null)
			{
				String msgToUser = "Persistent Object to Save Changes not defined...please set its value... no action have been taken ";
				pmPoForAction.getMessageCommnunicatorService().sendMessageToUser(msgToUser);
				return;
			}
			try {
				pmPoForAction.save();
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
