package com.implex.database.map.msgAction;

import com.implex.database.ApplicationContext;
import com.implex.database.DataSet;
import com.implex.database.PersistantObject;


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
