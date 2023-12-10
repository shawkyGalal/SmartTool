package com.implex.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.model.SelectItem;

import com.implex.database.map.TableUserView;

public class SpringDbserviceImpl extends DbServices {

	@Override
	public void delete(PersistantObject persistantObject) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BatchExecuteResult executeBatch(Batch pmBatch) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BatchExecuteResult executeCommandBatch(
			CommandBatch pm_commandBatch) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SelectItem> getSelectItems(String pmSql,
			boolean pmIncludeNull, int loggedUserLang) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialize(String pmEnvironmentName, int loggedUserLang) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DataSet queryForDataSet(Query query, Object[] mArgs, Class pmClass,
			ParentPersistantObject pmParent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void returnConnection(Connection pmCon) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(PersistantObject pmPersistantObject) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TableUserView getTableUserViewForLoggedUser(String pm_tableName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SelectItem> getSelectItems(String query, boolean b,
			int pmUserLang, Connection mCon) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConnection(Connection m_con) {
		// TODO Auto-generated method stub
		
	}
	
		

}
