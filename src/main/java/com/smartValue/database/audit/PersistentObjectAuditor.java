package com.smartValue.database.audit;

import java.sql.Connection;

import com.smartValue.database.PersistantObject;
import com.smartValue.database.map.SecUsrDta;

public interface  PersistentObjectAuditor
{
	public static final String DELETE_TRANSACTION = "DELETE";
	public static final String UPDATE_TRANSACTION = "UPDATE";
	public static final String INSERT_TRANSACTION = "INSERT";
	
	abstract boolean isAuditEnabled(SecUsrDta pm_secUser, PersistantObject pm_po , String pm_transactionType);
	abstract void auditTransaction(String pm_transactionType , SecUsrDta pm_secUser , PersistantObject pm_po , Connection pm_con) throws Exception;

}
