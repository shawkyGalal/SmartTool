package com.smartValue.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.sideinternational.web.swaf.SWAF;
import com.smartValue.UserSession;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.TableUserView;
import com.smartValue.database.map.services.ModuleServicesContainer;

public abstract class DbServices {
		
		
	private SecUsrDta LoggedUser;
	private String selectedEnv ;
	protected int loggedUserLang ;
	protected boolean logRequestToConsole = true;

	ModuleServicesContainer moduleServicesContainer ; 
	public void setModuleServicesContainer(ModuleServicesContainer m_moduleServicesContainer)
	{
		moduleServicesContainer = m_moduleServicesContainer ; 
	}
	public ModuleServicesContainer getModuleServiceContainer()
	{
		return moduleServicesContainer ;  
	}

//	public void setLoggedUserLang(int loggedUserLang)
//	{
//		this.loggedUserLang = loggedUserLang;
//	}
	public int getLoggedUserLang()
	{
		if (loggedUserLang == 0 )
		{
			UserSession loggedUserSession = (UserSession) SWAF.getManagedBean("userSession") ; 
			loggedUserLang = loggedUserSession.getUserLang();
		}
		return loggedUserLang;
	}
	public void setLoggedUser(SecUsrDta user)
	{
		this.LoggedUser = user;
		//ApplicationContext.setLoggedUser(this.LoggedUser);
	}

	public SecUsrDta getLoggedUser()
	{
		return LoggedUser;
	}
	
	public abstract TableUserView getTableUserViewForLoggedUser(String pm_tableName);


	public abstract DataSet queryForDataSet(Query query , Object[] m_args , Class pm_class , ParentPersistantObject pm_parent);   
	
	public DataSet queryForDataSet(String pm_sql, Object[] m_args , Class pm_class , ParentPersistantObject pm_parent) {
		Query query = new Query(pm_sql) ;
		return queryForDataSet(query , m_args , pm_class, pm_parent);
	}
	
	public DataSet queryForDataSet(String m_sql , Class pm_class ) 
	{
		return this.queryForDataSet(new Query(m_sql), null, pm_class , null);
	}
	
	public List<PersistantObject> queryForList(String m_sql ,Object[] m_args , Class pm_class) 
	{
		return this.queryForList(m_sql,  m_args , pm_class , null);
	}
	public List<PersistantObject> queryForList(String m_sql , Class pm_class) 
	{
		return this.queryForList(m_sql, null , pm_class , null);
	}
	
	public List<PersistantObject> queryForList(String m_sql ) 
	{
		return this.queryForList(m_sql, null , null , null);
	}
	

	public ArrayList<PersistantObject> queryForList(String pm_sql, Object[] pm_args, Class pm_class, ParentPersistantObject pm_parent) 
	{
		return this.queryForDataSet(pm_sql, pm_args, pm_class, pm_parent).getPersistantObjectList();
	}
	
	public abstract void update(PersistantObject pm_persistantObject) throws Exception;
	
	public abstract void delete(PersistantObject persistantObject)throws  Exception;

	/**
	 * Executes the given SQL statement, which may be an INSERT, UPDATE, or DELETE statement 
	 * or an SQL statement that returns nothing, such as an SQL DDL statement. 
	 * @param sqlStatement
	 * @return
	 * @throws SQLException 
	 */
	public abstract BatchExecuteResult executeCommandBatch(CommandBatch pm_commandBatch) throws SQLException;

	public abstract void initialize(String pm_environmentName , int loggedUserLang);

	public abstract BatchExecuteResult executeBatch(Batch pm_batch) throws  Exception;;

	/**
	 * return an array of SelectItems for the first and second columns in case of English or third column in case of Arabic in the given list of persistant objects
	 */
	
	public abstract ArrayList<SelectItem> getSelectItems(String pm_sql ,  boolean pm_includeNull , int loggedUserLang);

	public  ArrayList<SelectItem> getSelectItems(String pm_sql ,  boolean pm_includeNull )
	{
		
		return this.getSelectItems(pm_sql, pm_includeNull, this.getLoggedUserLang()) ; 
	}

	public abstract Connection  getConnection();
	public abstract void  setConnection(Connection m_con);

	protected abstract void  returnConnection(Connection pm_con);
	
	//protected  abstract TableMaintMaster  getCorresponingTableMaintMaster(DbTable pm_dbTable ) throws Exception ;
	

	public void initializeForWeb()  {
		UserSession loggedUserSession = (UserSession) SWAF.getManagedBean("userSession") ; 
		this.initialize(loggedUserSession.getSelectedEnv(), loggedUserSession.getUserLang());
		this.setLoggedUser((SecUsrDta)(loggedUserSession).getOperator());
		//this.setLoggedUserLang(loggedUserSession.getUserLang());
	}

	public ArrayList<SelectItem> getSelectItems(String pm_sql ) {
		int loggedUserLang = this.getLoggedUserLang();
		return getSelectItems (pm_sql , true , loggedUserLang);
	}
	public void setSelectedEnv(String selectedEnv)
	{
		this.selectedEnv = selectedEnv;
	}
	public String getSelectedEnv()
	{
		return selectedEnv;
	}
	public void setLogRequestToConsole(boolean b) {
		logRequestToConsole = b;
		
	}
	public boolean isLogRequestToConsole() {
		return logRequestToConsole ;
		
	}
	
	public  PersistantObject getObjectByRowId(String pm_rowId , String tableName , Class persistentClass  )
	{
		PersistantObject result = null;
		String queryStr = "Select t.* , t.rowId from " + tableName + " Where rowid = '"+pm_rowId+"'"; 
		Query query = new Query(queryStr);
		List<PersistantObject> list = null ; 
		try {
			list = this.queryForDataSet(query,null,  persistentClass , null).getPersistantObjectList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list != null && ! list.isEmpty())
		{
			result = list.get(0);
		}
		return result;
	}
	public abstract ArrayList<SelectItem> getSelectItems(String query, boolean b, int pmUserLang, Connection mCon) ;

	public MessagesCommnuicatorService getMessageCommnunicatorService() {
		return this.getLoggedUser().getModuleServiceContainer().getMessageCommnunicatorService();
	} 
	

}
