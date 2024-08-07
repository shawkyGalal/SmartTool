package com.smartValue.database.map.msgAction;

import com.smartValue.database.DataSet;
import com.smartValue.database.PersistantObject;

public interface MsgAction {
	
	public static final String CANCEL_ACTION_TYPE = "CANCEL_ACTION" ;
	public static final String OK_ACTION_TYPE = "OK_ACTION" ;
	
	
	public void doAction(PersistantObject pm_poForAction , String actionType);
	public void doAction(DataSet pm_DsForAction , String actionType);
	
}
