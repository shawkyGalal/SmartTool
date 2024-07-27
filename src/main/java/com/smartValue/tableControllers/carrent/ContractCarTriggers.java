package com.smartValue.tableControllers.carrent;

import java.sql.Connection;
import java.util.Vector;

import oracle.sql.ROWID;

import com.smartValue.database.map.SecUsrDta;
import com.smartValue.tableControllers.ItableTriggerController;

import Support.Misc;
import Support.SqlReader;
 
public class ContractCarTriggers extends ItableTriggerController {
	
	@Override
	public void afterUpdate(Connection m_con, String m_rowId, String m_columnName , Object m_newValue) throws Exception {

		if ( m_columnName.equalsIgnoreCase("CAR") )
		{
			String executableId = "21174" ; 
			Vector<String> paramNames = new Vector<String>();
			Vector<String> paramValues = new Vector<String>();
			paramNames.add("$$contractCarRowId") ;
			paramValues.add(m_rowId); 
			executeExecutable(executableId , m_con ,  m_rowId , paramNames , paramValues) ;
		}
				
		else if ( m_columnName.equalsIgnoreCase("returndate")
				|| m_columnName.equalsIgnoreCase("fmdate")
				|| m_columnName.equalsIgnoreCase("dayrate")
				|| m_columnName.equalsIgnoreCase("kmperday")
				|| m_columnName.equalsIgnoreCase("overkmrate")
				|| m_columnName.equalsIgnoreCase("fmkmreading")
				|| m_columnName.equalsIgnoreCase("hoursvalue")
				|| m_columnName.equalsIgnoreCase("returnkmreading") 
				|| m_columnName.equalsIgnoreCase("discount")
				|| m_columnName.equalsIgnoreCase("damgevalue")
				|| m_columnName.equalsIgnoreCase("penality")
				)
		{
			String executableId = "21258" ;  // Recalculate Contract Fees
			Vector<String> paramNames = new Vector<String>();
			Vector<String> paramValues = new Vector<String>();
			paramNames.add("$$contractCarRowId") ;
			paramValues.add(m_rowId); 
			executeExecutable(executableId , m_con ,  m_rowId , paramNames , paramValues) ;
		}
	}

	@Override
	public void beforeInsert(Connection con) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeUpdate(Connection con, String rowId, String columnName , Object m_newValue) throws Exception {
		System.out.println(" Before Updating  Column Name = " + columnName ) ;

	}

	public void executeExecutable(String executableId , Connection m_con, String m_rowId , Vector<String> paramNames , Vector<String> paramValues) throws Exception
	{

		SqlReader sqlReader = new Support.SqlReader ( this.getRepCon() , "LU_EXECUTABLES" , "EXEC_BODY" ,  executableId  , paramNames , paramValues  , true ,  this.getTableMaintMaster().getDbService().getSelectedEnv() );
		String[] execStmt =sqlReader.getQueryStatments();

		String updateStr = execStmt[0] ; 
		updateStr = Misc.repalceAll(updateStr, "$$contractCarRowId", m_rowId);
		System.out.println("Smart Tool Will Execute Executable ID : " + executableId + "\n" +  updateStr ) ; 
		m_con.createStatement().execute(updateStr); 

	}

	@Override
	public void afterDelete(Connection mCon, String mRowId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterInsert(Connection mCon, String mRowId) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void beforeDelete(Connection mCon, String mRowId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void upponInserCommit(Connection mCon, SecUsrDta mLoggedUser,
			String mRowId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void upponUpdateCommit(Connection mCon, SecUsrDta mLoggedUser,
			String mRowId, String mColumnName, Object mOldValue,
			Object mNewValue) throws Exception {
		// TODO Auto-generated method stub
		
	}




}
