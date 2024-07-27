package com.smartValue.database;


import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import oracle.sql.BLOB;

import com.sideinternational.sas.BaseException;
import com.sideinternational.sas.configuration.Configuration;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.SysMsg;
import com.smartValue.database.map.TableMaintMaster;
import com.smartValue.database.map.TableUserView;
import com.smartValue.database.map.security.PersisObjectSecurityController.IPersisObjectController;
import com.smartValue.database.map.services.ModuleServicesContainer;
import com.smartValue.database.map.services.SysMsgServices;
import com.smartValue.database.notifications.AttributeChangeNotifier;
import com.smartValue.database.trigger.TriggerHandler;
import com.smartValue.event.logging.Console;
import com.smartValue.listeners.ApplicationContextListener;



public class DirectJdbcServiceImpl extends DbServices implements Serializable{

//	private Query query;
	private boolean usePooling = false; 
	
	private synchronized ResultSet getResultSet(PreparedStatement ps  , Object[] pm_args ) throws SQLException
	{
		ResultSet rs  = null;
			if (pm_args != null) {
				for (int i = 0; i < pm_args.length; i++) {
					ps.setObject(i+1, pm_args[i]);
				}
			}
			try{
			rs = ps.executeQuery();}
			catch (SQLException e ) {throw e; }
		return rs ; 
		
	}
	public MessagesCommnuicatorService getMessageCommnunicatorService()
	{
		return this.getLoggedUser().getModuleServiceContainer().getMessageCommnunicatorService() ; 
	}
	@Override
	public  DataSet queryForDataSet(Query query , Object[] m_args , Class pm_class , ParentPersistantObject pm_parent)  
	{
		String pm_sql = query.getQueryStr() ; 
		DataSet result = new DataSet(new ArrayList<PersistantObject>() , this );
		if (pm_sql==null || pm_sql.equals("null") || pm_sql.equals(""))
			return result;
		
		Connection con =  this.getConnection();
		if (con == null)
		{
			String msg = "System may be busy ... Please try again" ;
			this.getMessageCommnunicatorService().sendMessageToUser(msg);
			return result ; 
		}
		ResultSet rs = null ;
		PreparedStatement ps = null ;
		try{
			 
			query.setArgsParameter(m_args);
			Date startDate = new  Date();
			ps = con.prepareStatement(pm_sql);
			if (this.isLogRequestToConsole())
			{
				Console.log("DataBase Service : Will Execute " + pm_sql, this .getClass() , Console.NOTIFICATION_LOGGING_LEVEL);
			}
			rs = this.getResultSet(ps , m_args );
			result = this.getDataSetFromResultSet(rs ,  pm_class, pm_parent , query);
			result.setQuery(query);
			Date endDate = new  Date();
			String cost = endDate.getTime()-startDate.getTime() + "ms " ; 
			if (this.logRequestToConsole)
			{
			Console.log("Query Cost :" + cost + "   ["+result.getPersistantObjectList().size()+"] Records Found", this.getClass()  , Console.ALL_LOGGING_LEVEL);
			}
			releaseResources(rs , ps , null);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			releaseResources(rs , ps , null);
			  
			  SysMsgServices sysmsgservices=this.getModuleServiceContainer().getSysMsgServices();
			  SysMsg sysMsg= null ; 
			  // sysMsg = sysmsgservices.getMsgUnableToExecute(true);
			  if (sysMsg != null)
			  {
				  ArrayList<String>param=new ArrayList<String>();
				  param.add(pm_sql);
				  param.add(ex.toString());
				  sysMsg.setParams(param);
				  this.getMessageCommnunicatorService().sendMessageToUser(sysMsg);
			  }
			  
			  {
				String msg = "Unable To Execute :" + pm_sql + "Due To " + ex ;
				this.getMessageCommnunicatorService().sendMessageToUser( msg);
			  }
			  this.returnConnection(con);
			  //ApplicationContext.pushConnection(con, this.getSelectedEnv());
					
		}
		finally
		{ 
			releaseResources(rs , ps , null);
			this.returnConnection(con);
			//ApplicationContext.pushConnection(con, this.getSelectedEnv());			
		}
		return result;
	}
	

	
	public static  PersistantObject instantiatePersistentObject(Class pm_class , final Query pm_query) 
	{
		Object persistentObjectInstance =	null ;
		String className = DirectJdbcServiceImpl.class.getName();
		if (pm_class == null || pm_class.getName().equals(className+"$1")) 
			{
			persistentObjectInstance = new TablePersistantObject()
				{
					private static final long serialVersionUID = 1L;

					@Override
					public DbTable getTable()
					{
						DbTable result = null ; 
						if (pm_query != null)
						{
							result = new DbTable(pm_query.getEstimatedTableOwner()  , pm_query.getEstimatedTableName() ,this.getDbService());
						}
						return result ;  
					}
				};
			}
		else
		{
			try
			{
				persistentObjectInstance = pm_class.newInstance();
			} catch (Exception e)
			{
				e.printStackTrace();
			} 
		}
		return ((PersistantObject) persistentObjectInstance);
	}
	
	

	//TableMaintMaster  corespondingTableMaintMaster = null ;
	private  boolean  isCalcTableMaintMaster(Class pm_class) 
	{
		return  this.getLoggedUser() != null 
				&& !  TableMaintMaster.class.equals(pm_class)  ; 
	}
	
	private DataSet getDataSetFromResultSet(ResultSet pm_rs , Class pm_class , ParentPersistantObject pm_parent , Query pm_Query ) throws SQLException
	{
		ArrayList<PersistantObject> poList = new ArrayList<PersistantObject>();

		 
		DbTable dbt ;
		TableMaintMaster corespondingTableMaintMaster = null;
		if (this.isCalcTableMaintMaster (pm_class)) // To Prevent Recursion..
		{
			//boolean calcTableMaintMaster = ApplicationContext.getCurrentLoggedUser() != null ;
			//if (calcTableMaintMaster)
			//{
				if (pm_class != null  ) 
				{
					dbt = DirectJdbcServiceImpl.instantiatePersistentObject(pm_class , pm_Query).getTable();
					dbt.setDbServices(this);
					corespondingTableMaintMaster = (dbt==null)? null: dbt.getTableMaintMaster ( );
				}
				else if (pm_Query != null && ! pm_Query.isAliasProvided())
				{	
						String estimatedTableName = pm_Query.getEstimatedTableName();
						String estimatedTableOwner = pm_Query.getEstimatedTableOwner();
						if ( estimatedTableName != null && ! estimatedTableName.contains(Query.DUAL))
						{
							dbt = new DbTable(estimatedTableOwner , estimatedTableName , this);
							corespondingTableMaintMaster = dbt.getTableMaintMaster ( );
							if (corespondingTableMaintMaster != null) pm_class = corespondingTableMaintMaster.getPersistantObjectClass() ;
						}
				}
			//}
		
		}
	
		ResultSetMetaData m_resultSetMetaData = pm_rs.getMetaData();
		int columnCount =m_resultSetMetaData.getColumnCount();

		ArrayList<Header> headersFromRs = new ArrayList<Header>();
		int rowCounter = 0 ; 
		while (pm_rs.next())
		{
			rowCounter++;
			Map<String, Attribute> rowData = new LinkedHashMap<String, Attribute>();
			PersistantObject persistantObject = DirectJdbcServiceImpl.instantiatePersistentObject(pm_class , pm_Query);
			
			for (int i = 1 ; i <= columnCount ; i++ )
			{
				String columnName = pm_rs.getMetaData().getColumnName(i);
				String columnTypeName = pm_rs.getMetaData().getColumnTypeName(i);
				
				Object attValue = pm_rs.getObject(i) ; 
				Attribute att ;	
				if (columnTypeName.equals("CLOB"))
				{
					att = new ClobAttribute(columnName, attValue ,persistantObject) ;
				}
				
				
				else if (columnTypeName.equals("BLOB"))
				{	
					BlobAttribute blobAtt = new BlobAttribute(columnName, attValue, persistantObject) ; 
					BLOB blob = ((OracleResultSet)pm_rs).getBLOB(i);
		
					if (blob != null)
					{
						long length  = blob.length();
						blob.open(BLOB.MODE_READONLY);
						byte[]  data = blob.getBytes(1, (int) length);
						blob.close();
						BytesFile bf = new BytesFile() ;
						bf.setData(data);
						bf.setLength(length);
						try{
						bf.setName(pm_rs.getString(AttributeFileUploader.COLUMN_FOR_FILE_NAME));
						}
						catch (Exception e )
						{						
						}
						 blobAtt.getFileUploadBean().getFiles().add(0, bf);
						  
					}
					att = blobAtt ;
				}
				else
				{
					att = new Attribute(columnName, attValue ,persistantObject) ;
				}
				rowData.put(columnName, att );
			
				if (rowCounter==1)
				{
					if (!(columnName.equals(PersistantObject.OBJECT_KEY_NAME)
								 || columnName.equals(PersistantObject.LAST_UPDATE_AT_COLUMN_NAME) )
								)
					{
						headersFromRs.add(new Header(columnName , columnName )  );
					}
				}
			}			
			rowData.put(PersistantObject.VIEW_TIMESTAMP_KEY, new Attribute(PersistantObject.VIEW_TIMESTAMP_KEY, new Date() , persistantObject));
			persistantObject.setDbService(this);
			persistantObject.setData(rowData);
			persistantObject.setParent(pm_parent);
			persistantObject.setTableMaintMaster(corespondingTableMaintMaster);
			persistantObject.initialize();
			poList.add(persistantObject);
		}
	
		if (rowCounter == 0)
		{
			for (int i = 1 ; i <= columnCount ; i++ )
			{
				String cn = m_resultSetMetaData.getColumnName(i); 			
				if (!(cn.equals(PersistantObject.OBJECT_KEY_NAME)
						 || cn.equals(PersistantObject.LAST_UPDATE_AT_COLUMN_NAME) )
						)
				{
					headersFromRs.add(new Header(cn , cn )  );
				}
			}
		}
		
		DataSet ds = null ; 
		ds = new DataSet(poList  , this);
		ds.setPersistantClass(pm_class);
		ds.setTableMaintMaster(corespondingTableMaintMaster);
		ds.setHeadersFromRs(headersFromRs);
		ds.setParent(pm_parent);
		
	return ds;
		
	}
	private DbColumn[] getResultSetDbColumns(ResultSet pm_rs) throws SQLException
	{
		ResultSetMetaData m_resultSetMetaData =  pm_rs.getMetaData() ; 
		int columnCount = m_resultSetMetaData.getColumnCount();
		DbColumn[] dbColumns = new DbColumn[columnCount];
		for (int i = 1 ; i <= columnCount ; i++ )
		{
			String cn = m_resultSetMetaData.getColumnName(i); 
			DbColumn dbColumn =  new DbColumn(cn, m_resultSetMetaData.getColumnTypeName(i) , m_resultSetMetaData.getColumnClassName(i));
			dbColumns[i-1] = dbColumn ; 
		}
		return dbColumns;
	}


	public List<PersistantObject> queryForList(String pm_sql, Object[] pm_args , Class pm_class){
		return this.queryForList(pm_sql, pm_args, pm_class , null);
	}

	
	public static void releaseResources(ResultSet pm_rs  , Statement pm_psForQuery , PreparedStatement psForUpdate)
	{
		if(pm_rs!= null)
			try {
				pm_rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (pm_psForQuery != null)
		{
			try
			{
				pm_psForQuery.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		if (psForUpdate != null)
		{
			try
			{
				psForUpdate.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		
	}
	
	private void insert(PersistantObject pm_po, Connection pm_con) throws Exception {
		
		SecUsrDta loggedUser =  this.getLoggedUser();
		IPersisObjectController  persisObjectController  = pm_po.getTableMaintMaster().getPersisObjectController() ; 
		boolean userAllowed = persisObjectController!= null && persisObjectController.isCreatable(pm_po);
		if (! userAllowed)
		{
			String msg = "You ("+loggedUser.getUsrNameValue()+") Are Not Authorized To Creat new Object " + pm_po ; 
			throw new Exception((msg));
		}
		
		pm_po.beforeInsert() ;
		 
		CallableStatement stmt = null;
		String help ="";
		int rowIdOutParamIndex = 1;
		try {
			TriggerHandler th = pm_po.getTriggerHandler();
			SecUsrDta secUserData = this.getLoggedUser() ;

			if (th!=null) th.beforeInsert(secUserData , pm_po, pm_con);
			
			try{
				stmt = pm_po.constructInsertCallableStatement(pm_con);
				help = pm_po.getParametrizedInsertString();
				rowIdOutParamIndex = pm_po.getRowIdOutParamIndex();}
			catch(SQLException sql){
				stmt = null;
				String pm_sql = pm_po.constructInsertStatement(pm_po.getTable().getTableName() , true);
				stmt = pm_con.prepareCall("begin " + pm_sql + " RETURNING ROWID INTO ? ; end;");
				stmt.registerOutParameter(1, OracleTypes.ROWID);
				help = pm_sql;
				rowIdOutParamIndex = 1;
			}
			Console.log("DB Service Will Execute : "+ help , this.getClass() );
			stmt.executeUpdate();
			pm_po.freeTempBlobs() ;
			oracle.sql.ROWID rowId = (oracle.sql.ROWID) stmt.getObject(rowIdOutParamIndex);
			String rowIdString = rowId.stringValue();
			Console.log("Oracle Generated ROWID: "+ rowIdString , this.getClass() );
			pm_po = this.retriveObjectAfterUpdate(pm_po , pm_con , rowIdString);
						
			if (th!=null) th.afterInsert(secUserData , pm_po, pm_con);
			DirectJdbcServiceImpl.releaseResources(null, null, stmt);
			pm_po.afterInsert() ;
			//this.sendUpdateSuccessfullToUser(pm_po , true );
		} catch (Exception sqle) {
			sqle.printStackTrace();
			String msg = "Unable to execute : "+help +" due To : "+sqle.getMessage() ;
			//ApplicationContext.getMessageCommnunicatorService().sendMessageToUser(msg);
			SysMsgServices sysmsgservices=this.getModuleServiceContainer().getSysMsgServices();
			SysMsg sysMsg=sysmsgservices.getMsgUnableToExecute(true);
			ArrayList<String>param=new ArrayList<String>();
			param.add(help);
			param.add(sqle.getMessage());
			sysMsg.setParams(param);
			this.getMessageCommnunicatorService().sendMessageToUser(sysMsg);
			throw new Exception(msg );	
		}
		finally{
			DirectJdbcServiceImpl.releaseResources(null, null, stmt);
		}
		
	}
	
	/**
	 * This Method is created to reflect any DB updates in this Object due to triggers, default values , rowid generated in case of insert or any others  
	 * @param pm_po
	 * @param pm_con
	 * @param pm_rowIdString
	 * @return
	 * @throws SQLException
	 * @throws BaseException
	 */
	private PersistantObject retriveObjectAfterUpdate(PersistantObject pm_po, Connection pm_con , String pm_rowIdString) throws Exception
	{
		String retriveQuery = pm_po.getTableMaintMaster().getAllItemsQuery() + " Where t." + PersistantObject.OBJECT_KEY_NAME + " = '" +  pm_rowIdString +"'" ;
		Statement retriveStmt = pm_con.createStatement();
		ResultSet retriveRs = retriveStmt.executeQuery(retriveQuery);
		Query retriveQueryO = new Query(retriveQuery);
		PersistantObject afterUpdateObject = this.getDataSetFromResultSet(retriveRs, pm_po.getClass(), pm_po.getParent(), retriveQueryO).getPersistantObjectList().get(0);
		DirectJdbcServiceImpl.releaseResources(retriveRs  ,retriveStmt  , null );
		pm_po.copyDataFrom(afterUpdateObject , false);
		pm_po.reset(); 

		
		return pm_po;
	}

	protected synchronized void update(PersistantObject pm_persObject , Connection pm_con) throws Exception{
				
		SecUsrDta loggedUser =  this.getLoggedUser();
	
		
		if ( ! pm_persObject.isContainsObjectKey())
		{
			boolean creatable = false;
			IPersisObjectController poc =  pm_persObject.getTableMaintMaster().getPersisObjectController() ; 
			if(poc != null)
				creatable = pm_persObject.getTableMaintMaster().getPersisObjectController().isCreatable(pm_persObject);			
			if (! creatable ) 
			{
				//String msg = "You ("+loggedUser.getUsrNameValue()+") Are Not Authorized To Create this Object of type " + pm_persObject; 
			    //ApplicationContext.getMessageCommnunicatorService().sendMessageToUser(msg);
				SysMsgServices sysmsgservices=this.getModuleServiceContainer().getSysMsgServices();
				String valueofcreate="";
				int userlang =  this.getLoggedUserLang() ;
				if(userlang==1)
				{
					valueofcreate="Ø¥Ù†Ø´Ø§Ø¡ ";
				}
				else
				{ valueofcreate="Create";}
				 SysMsg sysMsg=sysmsgservices.getMsgAreNotAuthorizedTo(true);
				 ArrayList<String>param=new ArrayList<String>();
				 param.add(loggedUser.getUsrNameValue());
				 param.add(valueofcreate);
				 param.add(pm_persObject.getClass().getName() + ":" + pm_persObject.toString());
				 sysMsg.setParams(param);
				 this.getMessageCommnunicatorService().sendMessageToUser(sysMsg);
				throw new Exception(sysMsg.getMsgDescWithParam());
			}
			
				this.insert(pm_persObject, pm_con);
				return ;
			
		}
		boolean updatable = pm_persObject.getTableMaintMaster().getPersisObjectController().isUpdatable(pm_persObject);
		if (! updatable ) 
		{
			//String msg = "You ("+loggedUser.getUsrNameValue()+") Are Not Authorized To Update this Object of type " + pm_persObject; 
		    //ApplicationContext.getMessageCommnunicatorService().sendMessageToUser(msg);
			SysMsgServices sysmsgservices=this.getModuleServiceContainer().getSysMsgServices();
			String valueofupdate="";
			if(this.getLoggedUserLang()==1)
			{
				valueofupdate="ØªØ¹Ø¯ÙŠÙ„";
			}
			else
			{ valueofupdate="Update";}
			
			 SysMsg sysMsg=sysmsgservices.getMsgAreNotAuthorizedTo(true);
			 ArrayList<String>param=new ArrayList<String>();
			 param.add(loggedUser.getUsrNameValue());
			 param.add(valueofupdate);
			 param.add(pm_persObject.getClass().getName() + ":" +  pm_persObject.toString());
			 sysMsg.setParams(param);
			 this.getMessageCommnunicatorService().sendMessageToUser(sysMsg);
			
			throw new Exception(sysMsg.getMsgDescWithParam());
		}
		ArrayList<Attribute> modifiedData =  pm_persObject.getModifiedAttributes();

		if (modifiedData == null || modifiedData.isEmpty())
		{
			//Console.log("Info: No Updates Found ", this.getClass());
			pm_persObject.beforeUpdate();
			pm_persObject.afterUpdate();
			return;
		}
		
//		pm_persObject.checkIfObjectIsOld(this.getSelectedEnv());
		pm_persObject.beforeUpdate();
		String updateStmtString ="";
		PreparedStatement prepStmtForUpdate = null ;
		try {
			TriggerHandler th = pm_persObject.getTriggerHandler();
			if (th!=null) th.berforeUpdate(loggedUser ,  pm_persObject, pm_con);
			
			try {
				prepStmtForUpdate = pm_persObject.constructUpdatePreparedStatment(pm_con);
				updateStmtString = pm_persObject.getParametrizedUpdateSting() ; 
				}
			catch(SQLException qle) {
				//Try Conventional way...
				qle.printStackTrace();
				updateStmtString = pm_persObject.constructUpdateStatment();
				prepStmtForUpdate = pm_con.prepareCall(updateStmtString); 
			}
			Console.log("DB Service Will Execute : "+ updateStmtString , this.getClass());
			prepareUpdateNotifications(pm_persObject);
			prepStmtForUpdate.execute();
			pm_persObject.freeTempBlobs() ;
			DirectJdbcServiceImpl.releaseResources(null , null ,  prepStmtForUpdate);
			
			try{executeUpdateNotifications(pm_persObject);}
			catch (Exception e) {e.printStackTrace();}
			
			if (th!=null) th.afterUpdate(loggedUser , pm_persObject, pm_con);
			
			if (pm_persObject.isReloadAfterUpdate()) 
			{
				String rowIdString =  pm_persObject.getRowIdString();
				pm_persObject = retriveObjectAfterUpdate(pm_persObject ,pm_con , rowIdString);
			}
			else
			{
				pm_persObject.reset() ;
				pm_persObject.onSuccessfulUpdate() ;
			}
			pm_persObject.afterUpdate() ;
			//this.sendUpdateSuccessfullToUser(pm_persObject , false );
		
			//pm_persObject.setAnyAttChanged(false);
		} 
		catch (Exception sqle) {
			sqle.printStackTrace();
			//String msg = "Unable to execute : "+updateStmtString +" due To : "+sqle.getMessage() ;
			//ApplicationContext.getMessageCommnunicatorService().sendMessageToUser(msg);
			SysMsgServices sysmsgservices=this.getModuleServiceContainer().getSysMsgServices();
			SysMsg sysMsg=sysmsgservices.getMsgUnableToExecute(true);
			ArrayList<String>param=new ArrayList<String>();
			param.add(updateStmtString);
			param.add(sqle.getMessage());
			sysMsg.setParams(param);
			this.getMessageCommnunicatorService().sendMessageToUser(sysMsg);
			throw new Exception(sysMsg.getMsgDescWithParam() );
		}
		finally	{
			DirectJdbcServiceImpl.releaseResources(null , null ,  prepStmtForUpdate);
		}
	}
	
	private void sendUpdateSuccessfullToUser(PersistantObject pm_persObject  , boolean forInsert)
	{
		String valueofupdate ; 
		if (forInsert)
		{
			valueofupdate =(this.getLoggedUserLang()==1 )? "Ø¥Ø¶Ø§Ù�Ø©"  : "Created" ;
		}
		else
		{
			valueofupdate =(this.getLoggedUserLang()==1 )? "ØªØ¹Ø¯ÙŠÙ„"  : "Updated" ;
		}
		String displayTxt = (String) pm_persObject.getTableMaintMaster().getObjectNameAutoLang().getValue();
		try
		{
			SysMsgServices sysmsgservices=this.getModuleServiceContainer().getSysMsgServices();
			SysMsg updatedSuccessfullyMsg=sysmsgservices.getMsgItemStatus(true);
			ArrayList<String>param=new ArrayList<String>();
			param.add(valueofupdate);
			param.add(displayTxt);
			updatedSuccessfullyMsg.setParams(param);
			this.getMessageCommnunicatorService().sendMessageToUser(updatedSuccessfullyMsg);
		}
		catch(Exception e)
		{
			this.getMessageCommnunicatorService().sendMessageToUser(displayTxt+" Updated Successfully");
		}
	}
	private void executeUpdateNotifications(PersistantObject pm_PersObject) {
		ArrayList<Attribute> attsNeedNotifications =  pm_PersObject.getModifiedAttNeedNotification();
		for (Attribute att : attsNeedNotifications )
		{
			ArrayList<AttributeChangeNotifier>  changeNotifiers = att.getChangeNotifiers();
			for ( AttributeChangeNotifier changeNotifier: changeNotifiers)
			{
				if (changeNotifier != null) changeNotifier.executeNotification();
			}
		}
		
	}
	
	private void prepareUpdateNotifications(PersistantObject pm_PersObject) {
		
		ArrayList<Attribute> attsNeedNotifications =  pm_PersObject.getModifiedAttNeedNotification();
		for (Attribute att : attsNeedNotifications )
		{
			ArrayList<AttributeChangeNotifier>  changeNotifiers = att.getChangeNotifiers();
			for ( AttributeChangeNotifier changeNotifier: changeNotifiers)
			{
				if (changeNotifier != null) changeNotifier.prepareNotification(att);
			}
		}
	}
	
	
//	public static void closeStatment(Statement pm_stmt)
//	{
//		if (pm_stmt != null)
//		{
//			try {
//				 pm_stmt.close();
//			} 
//			catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	@Override
	public void update(PersistantObject pm_persObject) {
		
		Connection con =  this.getConnection(); 
		//ApplicationContext.popConnection(this.getSelectedEnv());
		try{
			this.update(pm_persObject, con);
		}
		catch (Exception sqle){
			
		}
		finally	{
			this.returnConnection(con);
			//ApplicationContext.pushConnection( con , this.getSelectedEnv());
		}
	}

	@Override
	public void initialize(String pm_selectedEnv , int pm_LoggedUserLang)  {
		this.setSelectedEnv(  pm_selectedEnv );
		this.loggedUserLang = pm_LoggedUserLang;
		//this.setLoggedUserLang(pm_LoggedUserLang);

	}

	protected void delete(PersistantObject pm_po , Connection pm_con) throws Exception
	{
		if (pm_po == null || ! pm_po.isContainsObjectKey())
		{
			// Object is not persistent
			return;
		}
		
		SecUsrDta loggedUser =  this.getLoggedUser();
		//boolean isLoggedUserCanDeleteUDFResult = true ; 
		//isLoggedUserCanDeleteUDFResult =  getUserDefVarBooleanValue (pm_po , TableMaintMaster.IS_LOGGED_USER_CAN_DELETE) ;
		//boolean userAllowedFromSch = (pm_po.getSecurityController() == null) || pm_po.getSecurityController().isObjectCanBeDeleted( pm_po);
		boolean deletable = pm_po.getTableMaintMaster().getPersisObjectController().isDeletable(pm_po);
		if (! deletable ) //userAllowedFromSch || ! isLoggedUserCanDeleteUDFResult)
		{
			//String msg = "You ("+loggedUser.getUsrNameValue()+") Are Not Authorized To Delete this Object of type " + pm_po; 
			//ApplicationContext.getMessageCommnunicatorService().sendMessageToUser(msg);
			 SysMsgServices sysmsgservices=this.getModuleServiceContainer().getSysMsgServices();
			String valueofdelete="";
			if(this.getLoggedUserLang()==1)
			{
				valueofdelete="Ø­Ø°Ù�";
			}
			else
			{ valueofdelete="Delete";}
			 SysMsg sysMsg=sysmsgservices.getMsgAreNotAuthorizedTo(true);
			 ArrayList<String>param=new ArrayList<String>();
			 param.add(loggedUser.getUsrNameValue());
			 param.add(valueofdelete);
			 param.add(pm_po.toString());
			 sysMsg.setParams(param);
			 this.getMessageCommnunicatorService().sendMessageToUser(sysMsg);
			throw new Exception(sysMsg.getMsgDescWithParam());
		}
				
		pm_po.checkObjectKey();
		
//		pm_po.checkIfObjectIsOld(this.getSelectedEnv());
		String delStatement = "Delete from " + pm_po.getTableMaintMaster().getOwnerValue()+"."+pm_po.getTableMaintMaster().getTableNameValue()+ " Where " + PersistantObject.OBJECT_KEY_NAME  +" = '" + ((oracle.sql.ROWID)pm_po.getAttribute(PersistantObject.OBJECT_KEY_NAME ).getValue()).stringValue()+ "'";  		
		Statement stmt = null;
		try {
			TriggerHandler th = pm_po.getTriggerHandler();
			SecUsrDta secUserData = this.getLoggedUser();
			
			if (th!=null)th.beforeDelete(secUserData ,  pm_po, pm_con);
			pm_po.beforeDelete();
			Console.log("Executing Delete Operation :" + delStatement , this.getClass() );
			stmt = pm_con.createStatement();
			int deleteCount = stmt.executeUpdate(delStatement);
			pm_po.afterDelete();	
			Console.log("("+deleteCount+") Record(s) Deleted... ", this.getClass() );
			
			if (th!=null)th.afterDelete(secUserData ,  pm_po, pm_con);
			
			String displayTxt = (String) pm_po.getTableMaintMaster().getObjectNameAutoLang().getValue();
			String msg=displayTxt+" Deleted Successfully";
			String valueofDelete=(this.getLoggedUserLang()==1) ? "Ø­Ø°Ù�" : "Deleted";
			try{
			SysMsgServices sysmsgservices=this.getModuleServiceContainer().getSysMsgServices();
			SysMsg sysMsg=sysmsgservices.getMsgItemStatus(true);
			 ArrayList<String>param=new ArrayList<String>();
			 param.add(valueofDelete);
			 param.add(displayTxt);
			 sysMsg.setParams(param);
			 
			 this.getMessageCommnunicatorService().sendMessageToUser(sysMsg);
			}
			catch (Exception e) 
			{
				this.getMessageCommnunicatorService().sendMessageToUser(displayTxt+" Deleted Successfully");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			String msg = "Unable to Execute "+ delStatement + " Due to : " + e.getMessage(); 
			throw new Exception(( msg ));
		}
		finally 
		{
			DirectJdbcServiceImpl.releaseResources(null, stmt, null);
		}
	}
	@Override
	public void delete(PersistantObject pm_po) throws Exception {
		
		Connection con = this.getConnection(); 
		//ApplicationContext.popConnection(this.getSelectedEnv());
		try{
			if(pm_po.isDeletedByStoredProcedure())
				deletePoByStoredProcedure(pm_po, con);
			else
				this.delete(pm_po , con);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		finally
		{
			this.returnConnection(con);
			//ApplicationContext.pushConnection( con , this.getSelectedEnv());
		}
	}

	private void deletePoByStoredProcedure(PersistantObject pm_po, Connection con) {
		try
		{
		    String statement= "BEGIN " +
		    		 "	DELETE_OBJECTS."+pm_po.getTable().getTableName()+"(?);" +
		    		 "END;";
		    CallableStatement callStat = con.prepareCall(statement); 
		    callStat.setString(1, pm_po.getRowIdString());
		    callStat.execute();
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
		
	}

	public void deletePoWithChildren(PersistantObject pm_po) throws Exception 
	{
		//TODO : Sakr...please loop for all children , delete them then this delete the  pm_po object itself.
		// Using batch concept.....
		
		
	}
	@Override
	public synchronized BatchExecuteResult executeBatch(Batch pm_batch ) throws  Exception{
		
		return pm_batch.executeForDirectJdbc(this);
	}

	public static void main(String[] args) throws Exception
	{

		Configuration.initializeForWeb("ERPINS", "en-config.xml");
		ApplicationContext.setPools(ApplicationContextListener.initializeConnections(new URL ("File:\\D:\\ERPINS\\Sources\\IMPLEX_CONFIG_HOME\\ERPINS\\" +File.separator + "Connections_config.xml")));
		
		DirectJdbcServiceImpl dbs = new DirectJdbcServiceImpl();
		dbs.initialize("ERPINS" , 1);
		dbs.setLoggedUser(new SecUsrDta());
		
		try {
			
			DataSet result = dbs.queryForDataSet("select t.* , t.rowid from ICDB.Sys_Msg t" , SysMsg.class);
			SysMsg sysMsg =  (SysMsg) result.getCurrentItem() ; 
			Attribute msgIdAtt = sysMsg.getMsgId();
			msgIdAtt.setValue(new BigDecimal(5));
			Console.log("Change Message :" + sysMsg.getMsgId().getValidationResult().getInvalidMessage() ,SysMsg.class  ) ;
			Console.log("RowId : " + sysMsg.getRowIdString() , result.getClass());
		}
		
		catch (Exception e){e.printStackTrace();}
		
	}
	Connection con = null ;
	public void setConnection( Connection m_con )
	{
		this.con = m_con ;
	}
	
	public Connection  getConnection()
	{	Connection result = null ;
		boolean nullOrClosed = true ; 
		try { nullOrClosed = con == null || con.isClosed() ; } catch (Exception e){}
		  
		if (nullOrClosed)
		{
			if (usePooling)
			{result = ApplicationContext.popConnection(this.getSelectedEnv());}
			else
			{
				try {
				ModuleServicesContainer msc = this.getModuleServiceContainer(); //ModuleServicesContainer(); //((ModuleServicesContainer) SWAF.getModuleServiceContatiner()) ;
				result = msc.getConnFromUsrSession(this.getSelectedEnv());
				} catch (Exception e) {
				e.printStackTrace();
				}
			}
		}
		else {return con ;  }
		return 	result ;
	}
	public Connection getReposatoryConnection()
	{
		Connection result = null ; 
		try {
			ModuleServicesContainer msc = this.getModuleServiceContainer(); //((ModuleServicesContainer) SWAF.getModuleServiceContatiner()) ;
			result = msc.getReposatoryConnection();
			} catch (Exception e) {
			e.printStackTrace();
			}
		return 	result ;
	}
	
	protected void  returnConnection(Connection pm_con)
	{
		if (usePooling)
		{
		ApplicationContext.pushConnection(pm_con, this.getSelectedEnv());	
		}
		else
		{
			
		}
	}
	
	@Override
	public ArrayList<SelectItem> getSelectItems(String pm_sql , boolean pm_includeNull , int loggedUserLang)
	{
		Connection con =  this.getConnection(); 
		return getSelectItems ( pm_sql , pm_includeNull ,  loggedUserLang , con) ; 
	}
	
	public ArrayList<SelectItem> getSelectItems(String pm_sql , boolean pm_includeNull , int loggedUserLang , Connection con) 
	{
		ArrayList<SelectItem> result = new ArrayList<SelectItem>();
			
		ResultSet rsx = null;
		Statement stmtx = null;
		try{
			if (this.isLogRequestToConsole())
			{
				Console.log("DataBase Service : Will Execute " + pm_sql, this .getClass() , Console.NOTIFICATION_LOGGING_LEVEL);
			}
			Date startDate = new Date();
			stmtx =  con.createStatement() ; 
			pm_sql = this.appendTableUserView(pm_sql);
			rsx =    stmtx.executeQuery(pm_sql);
			int columnCount = rsx.getMetaData().getColumnCount();
			boolean has3columns = (columnCount == 3 ) ;
			
			
			if (pm_includeNull)
			result.add(new SelectItem("" , "<Select...>"));
			while (rsx.next())
			{
				String desc = rsx.getString(2); 
				if (has3columns)
				{
				 desc = (loggedUserLang == 1 )? rsx.getString(2) : rsx.getString(3) ; 
				}
				result.add(new SelectItem(rsx.getObject(1) , desc));
			}
			String cost = new Date().getTime()-startDate.getTime() + "ms " ; 
			if (this.logRequestToConsole)
			{
			Console.log("Query Cost :" + cost + "   ["+result.size()+"] Records Found", this.getClass()  , Console.ALL_LOGGING_LEVEL);
			}
			DirectJdbcServiceImpl.releaseResources(rsx, stmtx , null);
			//result = new SelectItem[x.size()];
			//result = x.toArray(result);
		}
		catch (Exception e)
		{	
			e.printStackTrace();
			DirectJdbcServiceImpl.releaseResources(rsx, stmtx , null);
			String msg = "Unable to execute : " + pm_sql ; 
			this.getMessageCommnunicatorService().sendMessageToUser(msg);
			
		}
		finally
		{
			this.returnConnection(con);
			//ApplicationContext.pushConnection(con, this.getSelectedEnv());			
		}
		return result;
	}
	private String appendTableUserView(String pm_sql) {
		try{
			int fromIndex = pm_sql.toUpperCase().indexOf("FROM");
			String fromPart = pm_sql.substring(0, fromIndex+4);
			String str = pm_sql.substring(fromIndex+4).trim();
			String orderBy = "";
			if(str.toUpperCase().contains(" ORDER "))
				orderBy = str.substring(str.toUpperCase().indexOf(" ORDER "));
			String tableName = (str.indexOf(" ") != -1) ? str.substring(0,str.indexOf(" ")) : str;
			TableUserView tableUserView = this.getTableUserViewForLoggedUser(tableName.toUpperCase());
			if(tableUserView != null)
			{
				String cond = "";
				int whereIndex = str.toUpperCase().indexOf(" WHERE ");
				int orderbyIndex = str.toUpperCase().indexOf(" ORDER ");
				if(whereIndex !=-1 && orderbyIndex != -1)
					cond = str.substring(whereIndex, orderbyIndex) + " And ";
				else if(whereIndex !=-1 && orderbyIndex == -1)
					cond = str.substring(whereIndex) + " And ";
				else 
					cond = " Where ";
				
				cond += tableUserView.getViewWhereConditionValue();
				pm_sql = fromPart + " " + tableName + " " + cond +" "+ orderBy;
			}
		}
		catch (Exception e) {
		}
		return pm_sql;
	}

	/**
	 * 
	 * @return a TableUserView Object for Logged user.. this view should be automatically 
	 * appended to any search query run by this user. Also it is applied in the getTableContentSampleSize() method
	 */
	@Override
	public TableUserView getTableUserViewForLoggedUser(String pm_tableName)
	{
		if(this.getLoggedUser() == null)
			return null;
		return this.getLoggedUser().getTableUserViewMap().get(pm_tableName);
	}
	/**
	 * 
	 */
	@Override
	public BatchExecuteResult executeCommandBatch(CommandBatch pm_commandBatch) 
	{
		BatchExecuteResult result = new BatchExecuteResult() ; 
		Connection con =  this.getConnection(); //ApplicationContext.popConnection(this.getSelectedEnv());	
		
		java.sql.CallableStatement callStmt = null;
		
			try {
				con.setAutoCommit(false);
			} catch (SQLException e1) {
				
				result.getExceptions().add(e1);
				return result ;
			}
			
			
			ResultSet execResultSet ;
			for (String command : pm_commandBatch.getCommands() )
			{
				try
				{
					if (this.isLogRequestToConsole())
					{
						Console.log("DataBase Service : Will Execute " + command, this .getClass() , Console.NOTIFICATION_LOGGING_LEVEL);
					}
					 callStmt = con.prepareCall("Call "  + command);
					 callStmt.execute();
					if (callStmt.getMoreResults() )
					{
						execResultSet = callStmt.getResultSet() ;
						if (execResultSet != null)
						{
							DataSet ds = this.getDataSetFromResultSet(execResultSet, null, null , null);
							result.getDataSets().add(ds);
						}
					}
				} catch (SQLException e)
				{
					result.getExceptions().add(e);
				}
				finally	{
					this.returnConnection(con);
					//ApplicationContext.pushConnection(con, this.getSelectedEnv());
				}
			}
			
			if (result.getExceptions().isEmpty())
			{
				try {
					con.commit();
					} 
				catch (SQLException e) 
					{
					e.printStackTrace();
					}
			}
			else
			{
				try {
					con.rollback();
					} 
				catch (SQLException e) 
					{
					e.printStackTrace();
					}
				
			}
			
			DirectJdbcServiceImpl.releaseResources(null, callStmt, null);
		
		return result;
	}

}
