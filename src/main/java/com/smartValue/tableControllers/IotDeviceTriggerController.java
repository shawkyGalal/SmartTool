package com.smartValue.tableControllers;

import java.sql.Connection;

import com.smartValue.database.map.SecUsrDta;

public class IotDeviceTriggerController extends ItableTriggerController {

	@Override
	public void afterDelete(Connection mCon, String mRowId) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterInsert(Connection mCon, String mRowId) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterUpdate(Connection mCon, String mRowId, String mColumnName,
			Object mNewValue) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeDelete(Connection mCon, String mRowId) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeInsert(Connection mCon) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeUpdate(Connection mCon, String mRowId,
			String mColumnName, Object mNewValue) throws Exception {
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
