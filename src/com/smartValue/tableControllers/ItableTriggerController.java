package com.smartValue.tableControllers;

import java.sql.Connection;

import com.implex.database.map.SecUsrDta;
import com.implex.database.map.TableMaintMaster;

public abstract class ItableTriggerController {
	

	public abstract void beforeUpdate(Connection m_con , String m_rowId , String m_columnName , Object m_newValue) throws Exception ; 
	
	public abstract void afterUpdate(Connection m_con , String m_rowId , String m_columnName, Object m_newValue) throws Exception ;  
	
	public abstract void beforeInsert(Connection m_con ) throws Exception  ;
	
	public abstract void afterInsert(Connection m_con , String m_rowId ) throws Exception ; 
	
	public abstract void beforeDelete(Connection m_con , String m_rowId) throws Exception ;  
	
	public abstract void afterDelete(Connection m_con , String m_rowId) throws Exception ;
	
	public void setRepCon(Connection repCon) {
		this.repCon = repCon;
	}

	public Connection getRepCon() {
		return repCon;
	}

	private Connection repCon ;
	private TableMaintMaster tableMaintMaster ; 
	public void setTableMaintMaster(TableMaintMaster mTableMaintMaster) {
		this.tableMaintMaster = mTableMaintMaster ; 
		
	} 
	
	public TableMaintMaster getTableMaintMaster()
	{
		return this.tableMaintMaster ; 
	}
	public abstract void upponUpdateCommit(Connection m_con , SecUsrDta m_logged_user , String m_rowId , String m_columnName , Object m_oldValue, Object m_newValue) throws Exception ;
	public abstract void upponInserCommit(Connection m_con , SecUsrDta m_logged_user , String m_rowId ) throws Exception ;
	
	
}
