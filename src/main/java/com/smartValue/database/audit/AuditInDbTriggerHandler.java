package com.smartValue.database.audit;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.Attribute;
import com.smartValue.database.DataSet;
import com.smartValue.database.DirectJdbcServiceImpl;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.map.AuditControler;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.SysMsg;
import com.smartValue.database.map.services.SysMsgServices;
import com.smartValue.database.trigger.TriggerHandler;

/**
 * This Class Can be Used  for Audit Operation as an implementation for TriggerHandler Class 
 * @author Shawky Foda
 *
 */
public class AuditInDbTriggerHandler  implements TriggerHandler , PersistentObjectAuditor   {

	public static final String TRANSACTION_BY_COLUMN_NAME = "TRANSACTION_BY" ;
	public static final String TRANSACTION_TIME_COLUMN_NAME = "TRANSACTION_TIME";
	public static final String TRANSACTION_TYPE_COLUMN_NAME = "TRANSACTION_TYPE";
	public static final String TRANSACTION_ID_COLUMN_NAME = "TRANSACTION_ID";
	private static final String OLD_NEW_COLUMN_NAME = "OLD_NEW";
	
	private boolean auditTableCreated = false;
	
	
	public void afterDelete(SecUsrDta pm_secUser, PersistantObject pm_po,
			Connection pm_con) throws Exception {
		if (this.isAuditEnabled(pm_secUser , pm_po , DELETE_TRANSACTION ) )
		{
			this.auditTransaction(DELETE_TRANSACTION , pm_secUser, pm_po, pm_con);
		}
		
	}

	public void auditTransaction(String pm_transactionType , SecUsrDta pm_secUser , PersistantObject pm_po , Connection pm_con) throws Exception {
		pm_po.setRefreshColumns(true);
		String tableName = pm_po.getTable().getTableName();
		String auditTableName = this.getAuditTableName(tableName) ;
		if (! this.isAuditTableCreated() && ! isTableExists (auditTableName , pm_con))
		{
			this.createAuditTable(tableName, pm_con);
			this.setAuditTableCreated(true);
		}
		
		int transactionID = this.getTransactionID(pm_con).intValue();
		pm_po.getData().put(TRANSACTION_BY_COLUMN_NAME, new Attribute(TRANSACTION_BY_COLUMN_NAME , pm_secUser.getUsrNameValue() , pm_po ));
		pm_po.getData().put(TRANSACTION_TIME_COLUMN_NAME, new Attribute(TRANSACTION_TIME_COLUMN_NAME , new Date() , pm_po ));
		pm_po.getData().put(TRANSACTION_ID_COLUMN_NAME, new Attribute(TRANSACTION_ID_COLUMN_NAME , transactionID , pm_po ));

		String[] extraColumns ;
		if (pm_transactionType.equalsIgnoreCase(UPDATE_TRANSACTION))
		{

			pm_po.getData().put(TRANSACTION_TYPE_COLUMN_NAME, new Attribute(TRANSACTION_TYPE_COLUMN_NAME , UPDATE_TRANSACTION , pm_po ));
			pm_po.getData().put(OLD_NEW_COLUMN_NAME, new Attribute(OLD_NEW_COLUMN_NAME , "OLD" , pm_po ));
			extraColumns = new String[]{TRANSACTION_BY_COLUMN_NAME, TRANSACTION_TIME_COLUMN_NAME, TRANSACTION_ID_COLUMN_NAME , TRANSACTION_TYPE_COLUMN_NAME , OLD_NEW_COLUMN_NAME};
			String insertStmtForOriginalValues = pm_po.constructInsertStatementFromPO(auditTableName , false , extraColumns);
			this.executeStatement(insertStmtForOriginalValues , pm_con );
			
			pm_po.getData().put(OLD_NEW_COLUMN_NAME, new Attribute(OLD_NEW_COLUMN_NAME , "NEW" , pm_po ));
			String insertStmtForModifiedValues = pm_po.constructInsertStatementFromPO(auditTableName , true, extraColumns);
			this.executeStatement(insertStmtForModifiedValues , pm_con );
		}
		else
		{
			
			pm_po.getData().put(TRANSACTION_TYPE_COLUMN_NAME, new Attribute(TRANSACTION_TYPE_COLUMN_NAME , pm_transactionType , pm_po ));
			extraColumns = new String[]{TRANSACTION_BY_COLUMN_NAME, TRANSACTION_TIME_COLUMN_NAME, TRANSACTION_ID_COLUMN_NAME, TRANSACTION_TYPE_COLUMN_NAME};
			String insertStmtForModifiedValues = pm_po.constructInsertStatementFromPO(auditTableName , true , extraColumns );
			
			this.executeStatement(insertStmtForModifiedValues , pm_con );
			
		}
		pm_po.setRefreshColumns(false);
		
	}
	

	private boolean isTableExists(String auditTableName , Connection pm_con) {
		boolean result = false;
		ResultSet rs = null;
		String sqlSelect = "select * from all_tables t where t.table_name = '" + auditTableName.toUpperCase()+"'";
		Statement stmt = null;
		try
		{    stmt = pm_con.createStatement();
			rs  = stmt.executeQuery(sqlSelect);
			result = rs.next();
		} catch (SQLException e)
		{
			e.printStackTrace();
			DirectJdbcServiceImpl.releaseResources(rs, stmt , null);
		}
		return result;
	}

	private void executeStatement(String sqlStmt , Connection pm_con ) throws Exception {
		Statement stmt = null;
		try {
			 stmt = pm_con.createStatement();
			 stmt.execute(sqlStmt);
		} catch (SQLException e) {
			
		
			e.printStackTrace();
			throw new Exception(e);
		}
		finally
		{
			DirectJdbcServiceImpl.releaseResources(null, stmt , null);
		}
	}

	private String getAuditTableName(String  pm_tableName) {
		return pm_tableName+"_AUDIT$";
	}

	private BigDecimal getTransactionID(Connection pm_con) throws Exception {

		String selectStmt = "Select Audit_Trans_ID_Seq.NextVal from dual" ;
		BigDecimal result = null ;
		java.sql.ResultSet rs = null;
		try {
			rs = pm_con.createStatement().executeQuery(selectStmt);
		
		rs.next();
		result = rs.getBigDecimal(1);
		} catch (SQLException e) {
			if (rs != null)
				{
					try {
						rs.close();
					} catch (SQLException e1) {	}
				}
			e.printStackTrace();
		}
		return result ;
		
		
	}

	public void afterInsert(SecUsrDta pm_secUser, PersistantObject pm_po,
			Connection pm_con) throws Exception {
		if (this.isAuditEnabled(pm_secUser , pm_po , INSERT_TRANSACTION ) )
		{
			this.auditTransaction(INSERT_TRANSACTION , pm_secUser, pm_po, pm_con);
		}
		
	}

	

	public void afterUpdate(SecUsrDta pm_secUser, PersistantObject pm_po,
			Connection pm_con) throws Exception {
		if (this.isAuditEnabled(pm_secUser , pm_po , UPDATE_TRANSACTION ) )
		{
			this.auditTransaction(UPDATE_TRANSACTION , pm_secUser, pm_po, pm_con);
		}
	}

	public boolean isAuditEnabled(SecUsrDta pm_secUser, PersistantObject pm_po , String pm_transactionType) 
	{
		String query = "select t.*, t.rowid from audit_controler t where " +
				" Username = '"+pm_secUser.getUsrNameValue()+"' " +
				" and t.tablename = '"+pm_po.getTable().getTableName()+"' " +
				" and t.operation = '"+pm_transactionType+"'" ;
		DataSet ds = null;
		try
		{
			 ds = pm_po.getDbService().queryForDataSet(query, AuditControler.class);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		if (ds!=null && ! ds.getPersistantObjectList().isEmpty())
		{String enabled = ((AuditControler)ds.getPersistantObjectList().get(0)).getEnabledValue();
		return ( enabled.equals("1") || enabled.equalsIgnoreCase("Y")); }
		else return false;
	}

	public void beforeDelete(SecUsrDta pm_secUser, PersistantObject pm_po,
			Connection pm_con) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void beforeInsert(SecUsrDta pm_secUser, PersistantObject pm_po,
			Connection pm_con) throws Exception {
		
		
	}

	public void berforeUpdate(SecUsrDta pm_secUser, PersistantObject pm_po,
			Connection pm_con) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	private void createAuditTable(String tableName , Connection pm_con) throws Exception
	{
		String auditTableName = this.getAuditTableName(tableName);
		String[] dmlStatements = {"create table "+auditTableName+" as select * from "+tableName+" where 1<>1"
							,  	"alter table "+auditTableName+" add " + TRANSACTION_BY_COLUMN_NAME + " NVARCHAR2(80)"
							,  	"alter table "+auditTableName+" add " + TRANSACTION_TIME_COLUMN_NAME +" date"
							,	"alter table "+auditTableName+" add " + TRANSACTION_TYPE_COLUMN_NAME +" nvarchar2(20) "
							,	"alter table "+auditTableName+" add " + TRANSACTION_ID_COLUMN_NAME +" number "
							,	"alter table "+auditTableName+" add " + OLD_NEW_COLUMN_NAME +" nvarchar2(3)" };

		for (int i = 0 ; i< dmlStatements.length ; i++)
		{
			this.executeStatement(dmlStatements[i], pm_con);
			
		}
		
	}

	public void setAuditTableCreated(boolean auditTableCreated) {
		this.auditTableCreated = auditTableCreated;
	}

	public boolean isAuditTableCreated() {
		return auditTableCreated;
	}

	
}
