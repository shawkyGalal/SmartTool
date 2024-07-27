package com.smartValue.tableControllers;

import java.sql.Connection;
import java.util.Vector;

import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.TableMaintMaster;

import Support.Misc;
import Support.SqlReader;
import Support.SqlReaderOld;

import oracle.sql.ROWID;

public class defaultTableTrigger extends com.smartValue.tableControllers.ItableTriggerController
{

	@Override
	public void afterDelete(Connection mCon, String mRowId) throws Exception 
	{
		TableMaintMaster tmm =  this.getTableMaintMaster(); 
		String afterDeleteExeIdValue = tmm.getAfterDeleteExeIdValue();
 		this.executeExecutable(afterDeleteExeIdValue, mCon, mRowId);			
	}

	@Override
	public void afterInsert(Connection mCon, String mRowId) throws Exception{
		TableMaintMaster tmm =  this.getTableMaintMaster(); 
		String afterInsertExeIdValue = tmm.getAfterInsertExeIdValue();
		this.executeExecutable(afterInsertExeIdValue, mCon, mRowId);
	}

	@Override
	public void afterUpdate(Connection mCon, String mRowId, String mColumnName, Object m_newValue)		throws Exception 
	{
		TableMaintMaster tmm =  this.getTableMaintMaster(); 
		String afterUpdateExeIdValue = tmm.getAfterUpdateExeIdValue(); 
 		this.executeExecutable(afterUpdateExeIdValue, mCon, mRowId);			
	
	}

	@Override
	public void beforeDelete(Connection mCon, String mRowId) throws Exception {
		TableMaintMaster tmm =  this.getTableMaintMaster(); 
		String beforeDeleteExeIdValue = tmm.getBeforeDeleteExeIdValue(); 
 		this.executeExecutable(beforeDeleteExeIdValue, mCon, mRowId);			
		
	}

	@Override
	public void beforeInsert(Connection mCon) throws Exception {
		TableMaintMaster tmm =  this.getTableMaintMaster(); 
		String beforeInsertExeIdValue = tmm.getBeforeInsertExeIdValue(); 
 		this.executeExecutable(beforeInsertExeIdValue, mCon, null);			
		
	}

	@Override
	public void beforeUpdate(Connection mCon, String mRowId, String mColumnName , Object m_newValue)	throws Exception {
		TableMaintMaster tmm =  this.getTableMaintMaster(); 
		String beforeUpdateExeIdValue = tmm.getBeforeUpdateExeIdValue(); 
 		this.executeExecutable(beforeUpdateExeIdValue, mCon, mRowId);			
		
	}
	
	@Override
	public  void upponUpdateCommit(Connection m_con , SecUsrDta m_logged_user , String m_rowId , String m_columnName , Object m_oldValue , Object m_newValue) throws Exception 
	{
		
	}
	@Override
	public void upponInserCommit(Connection mCon , SecUsrDta m_logged_user , String mRowId) throws Exception 
	{
		// TODO Auto-generated method stub
		
	}
	
	private void executeExecutable(String executableId , Connection m_con, String m_rowId ) throws Exception
	{
		Vector<String> paramNames = new Vector<String>();
		Vector<String> paramValues = new Vector<String>();
		paramNames.add("$$rowIdValue") ;
		paramValues.add(m_rowId); 
		executeExecutable(executableId , m_con, paramNames , paramValues ) ; 
	}

	private void executeExecutable(String executableId , Connection m_con, Vector<String> paramNames , Vector<String> paramValues ) throws Exception
	{
		if (executableId == null || executableId.equalsIgnoreCase("")) {return; }
		SqlReaderOld sqlReader = new Support.SqlReaderOld ( this.getRepCon() , "LU_EXECUTABLES" , "EXEC_BODY" ,  executableId  , paramNames , paramValues  , true );
		sqlReader.executeExecutable(0, m_con) ; 
	}




}
