package com.implex.database.trigger;

import java.sql.Connection;

import com.implex.database.PersistantObject;
import com.implex.database.map.SecUsrDta;

public interface TriggerHandler
{
	public abstract void berforeUpdate(SecUsrDta pm_secUser ,   PersistantObject pm_po , Connection pm_con) throws Exception;
	public abstract void afterUpdate(SecUsrDta pm_secUser , PersistantObject pm_po , Connection pm_con) throws Exception;

	public abstract void beforeDelete(SecUsrDta pm_secUser , PersistantObject pm_po , Connection pm_con) throws Exception;
	public abstract void afterDelete(SecUsrDta pm_secUser , PersistantObject pm_po , Connection pm_con) throws Exception;

	public abstract void afterInsert(SecUsrDta pm_secUser , PersistantObject pm_po , Connection pm_con) throws Exception;
	public abstract void beforeInsert(SecUsrDta pm_secUser , PersistantObject pm_po , Connection pm_con) throws Exception;

}
