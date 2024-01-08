package com.implex.database.audit;

import java.sql.Connection;

import com.implex.database.PersistantObject;
import com.implex.database.map.SecUsrDta;

public interface  PersistentObjectAuditor
{
	public static final String DELETE_TRANSACTION = "DELETE";
	public static final String UPDATE_TRANSACTION = "UPDATE";
	public static final String INSERT_TRANSACTION = "INSERT";
	
	abstract boolean isAuditEnabled(SecUsrDta pm_secUser, PersistantObject pm_po , String pm_transactionType);
	abstract void auditTransaction(String pm_transactionType , SecUsrDta pm_secUser , PersistantObject pm_po , Connection pm_con) throws Exception;

}
