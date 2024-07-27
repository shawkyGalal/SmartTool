package com.smartValue.database.map.msgAction;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.DataSet;
import com.smartValue.database.PersistantObject;


public class MsgActionsCancelUpdateImpl implements MsgAction{

	
	public void doAction(PersistantObject pmPoForAction, String actionType) {
		if (actionType.equals(MsgAction.OK_ACTION_TYPE))
		{
			if (pmPoForAction == null)
			{
				String msg = "Persistent Object to Cancel updates not defined...please set its value... no action have been taken " ;
				pmPoForAction.getMessageCommnunicatorService().sendMessageToUser(msg);
				return;
			}
			pmPoForAction.revert();
			
		}
		
	}

	
	public void doAction(DataSet pmDsForAction, String actionType) {
		// TODO Auto-generated method stub
		
	}

}
