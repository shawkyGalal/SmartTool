package com.implex.database.map.msgAction;


import com.implex.database.ApplicationContext;
import com.implex.database.DataSet;
import com.implex.database.PersistantObject;
import com.implex.database.map.SysMnu;


public class MsgActionsAddToFavoriteImpl implements MsgAction{

	
	public void doAction(PersistantObject pmPoForAction, String actionType) {
		
		
		if (actionType.equals(MsgAction.OK_ACTION_TYPE))
		{
			if (pmPoForAction == null)
			{
				String msgToUser = "Persistent Object to Add to favorite is not defined...please set its value... no action have been taken ";
				pmPoForAction.getMessageCommnunicatorService().sendMessageToUser(msgToUser);
				return;
			}
			
			SysMnu sysMnu = (SysMnu) pmPoForAction;
			sysMnu.getDbService().getLoggedUser().addToFavorite(sysMnu);
			
		}
		
	}

	
	public void doAction(DataSet pmDsForAction, String actionType) {
		// TODO Auto-generated method stub
		
	}

}
