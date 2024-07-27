package com.smartValue.database.map;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.faces.model.SelectItem;

import com.sideinternational.sas.BaseException;
import com.smartValue.database.ApplicationContext;
import com.smartValue.database.Attribute;
import com.smartValue.database.AttributeChangeValidator;
import com.smartValue.database.BatchExecuteResult;
import com.smartValue.database.ClobAttribute;
import com.smartValue.database.CommandBatch;
import com.smartValue.database.DBKey;
import com.smartValue.database.DataSet;
import com.smartValue.database.DataSetPickList;
import com.smartValue.database.DatasetEventListener;
import com.smartValue.database.DbForignKey;
import com.smartValue.database.DbForignKeyArray;
import com.smartValue.database.DbServices;
import com.smartValue.database.DbTable;
import com.smartValue.database.Operation;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.Query;
import com.smartValue.database.ToolbarVisabilityController;
import com.smartValue.database.audit.PersistentObjectAuditor;
import com.smartValue.database.map.auto._TableMaintMaster;
import com.smartValue.database.map.security.PersistentObjectSecurityControl;
import com.smartValue.database.map.security.TableMaintMasterSecurityControlHandlerImpl;
import com.smartValue.database.map.security.PersisObjectSecurityController.IPersisObjectController;
import com.smartValue.database.map.security.PersisObjectSecurityController.PoSecurityContollerSchImpl;
import com.smartValue.database.map.security.PersisObjectSecurityController.PoSecurityContollerSimpleImpl;
import com.smartValue.database.map.security.PersisObjectSecurityController.PoSecurityContollerUdvImpl;
import com.smartValue.database.map.security.PersisObjectSecurityController.PoSecurityContollerUserGroupImpl;
import com.smartValue.database.map.services.InterfaceImplServices;
import com.smartValue.database.map.services.SysMsgServices;
import com.smartValue.database.map.services.TableMaintMasterServices;
import com.smartValue.database.mapGeneration.MapGenerator;
import com.smartValue.database.trigger.TriggerHandler;
import com.smartValue.database.viewControllers.TableViewController;
import com.smartValue.sys.map.AllConstraints;
import com.smartValue.tableControllers.ItableTriggerController;


public class TableMaintMaster extends _TableMaintMaster {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String IS_LOGGED_USER_CAN_UPDATE = "IS_LOGGED_USER_CAN_UPDATE";
	public static final String IS_LOGGED_USER_CAN_DELETE = "IS_LOGGED_USER_CAN_DELETE";
	public static final String IS_LOGGED_USER_CAN_CREATE = "IS_LOGGED_USER_CAN_CREATE";
	private static HashMap<String, DbForignKeyArray> childrenForignKeys;
	private 	static String USR_DEF_VAR_GRP_TABLE_NAME = "USER_DEF_VAR_GROUPS";
	private 	DataSet tableContentsSample = null;
	private 	Set<TableMaintDetails> selectedTmds = new HashSet<TableMaintDetails>();
	private 	DBKey uniqueKeys ;
	public 	 	ArrayList<SelectItem> userDefienedVariables;
	public 	 	ArrayList<SelectItem> userDefinedVariablesGroups;
	private 	List<String> mandatoryFields = null;
	private 	IPersisObjectController persisObjectController ;
	public static final String USR_DEFAULTS_GRP_NAME = "USR_DEFAULTS_GRP";
	public static final String SYS_DEFAULTS_GRP_NAME = "SYS_DEFAULTS_GRP";
	private static final String SIMPLE_PROTECTION = "1";
	private PersistantObject childTobeUpdated; 

	private HashMap<String, String> extraFilterMap = new HashMap<String, String>();
	public static final String CDB_SCHEMA_OWNER = "ICDB" ;
	
	@Override
	public HashMap<String, DbForignKeyArray> getChildrenForignKeys() 
	{
		if (childrenForignKeys == null)
		{
			childrenForignKeys = new HashMap<String, DbForignKeyArray>();
			childrenForignKeys.put(TableMaintDetails.DB_TABLE_NAME, TableMaintMaster.ForignKeyForTableMaintDetails);
			childrenForignKeys.put(USR_DEF_VAR_GRP_TABLE_NAME, TableMaintMaster.ForignKeyForUserDefinedVarsGrp);
			childrenForignKeys.put(TableUserView.DB_TABLE_NAME, TableMaintMaster.ForignKeyForTableUserView);
			childrenForignKeys.put(SavedSearch.DB_TABLE_NAME, TableMaintMaster.ForignKeyForSavedSearch);
			childrenForignKeys.put(AuditControler.DB_TABLE_NAME, TableMaintMaster.ForignKeyForAuditController);
			childrenForignKeys.put(SysPoAttachmentGroup.DB_TABLE_NAME, TableMaintMaster.ForignKeyForAttachmentGroup);
			childrenForignKeys.put(SysTablesSecurityControler.DB_TABLE_NAME + PersistentObjectAuditor.DELETE_TRANSACTION, TableMaintMaster.ForignKeyForSysTableSecurityControler);
			childrenForignKeys.put(SysTablesSecurityControler.DB_TABLE_NAME + PersistentObjectAuditor.INSERT_TRANSACTION, TableMaintMaster.ForignKeyForSysTableSecurityControler);
			childrenForignKeys.put(SysTablesSecurityControler.DB_TABLE_NAME + PersistentObjectAuditor.UPDATE_TRANSACTION, TableMaintMaster.ForignKeyForSysTableSecurityControler);
			
			childrenForignKeys.put(TableNotificationRule.DB_TABLE_NAME, TableMaintMaster.ForignKeyForTableNotificationRule);
			

		}
		return childrenForignKeys;
	}
	
	private static DbForignKeyArray ForignKeyForAuditController =
		 new DbForignKeyArray ( new DbForignKey[]{ new DbForignKey(TableMaintMaster.TABLE_NAME ,AuditControler.TABLENAME  , AuditControler.DB_TABLE_NAME , TableMaintMaster.CDB_SCHEMA_OWNER )
		 } , false ) ;
	
	private static DbForignKeyArray ForignKeyForAttachmentGroup = 
		 new DbForignKeyArray ( new DbForignKey[]{ new DbForignKey(TableMaintMaster.TABLE_NAME ,SysPoAttachmentGroup.TABLE_NAME  , SysPoAttachmentGroup.DB_TABLE_NAME , TableMaintMaster.CDB_SCHEMA_OWNER )
		 											, new DbForignKey(TableMaintMaster.OWNER ,SysPoAttachmentGroup.TABLE_OWNER  , SysPoAttachmentGroup.DB_TABLE_NAME , TableMaintMaster.CDB_SCHEMA_OWNER )
		 } , false ) ;
	
	private static DbForignKeyArray ForignKeyForSysTableSecurityControler =
		 new DbForignKeyArray ( new DbForignKey[]{ new DbForignKey(TableMaintMaster.TABLE_NAME ,SysTablesSecurityControler.TABLE_NAME  , SysTablesSecurityControler.DB_TABLE_NAME , TableMaintMaster.CDB_SCHEMA_OWNER )
													} , false ) ;
	
	private static DbForignKeyArray ForignKeyForSavedSearch = 
		new DbForignKeyArray ( new DbForignKey[]{new DbForignKey(TableMaintMaster.TABLE_NAME ,SavedSearch.TABLE_NAME )
												} , false ) ;
	

	private static DbForignKeyArray ForignKeyForTableUserView =
		 new DbForignKeyArray ( new DbForignKey[]{new DbForignKey(TableMaintMaster.TABLE_NAME ,TableUserView.TABEL_NAME )
		,new DbForignKey(TableMaintMaster.OWNER ,TableUserView.TABLE_OWNER )
		} , false ) ;
	

	private static DbForignKeyArray ForignKeyForConstraints =
		 new DbForignKeyArray ( new DbForignKey[]{ new DbForignKey(TableMaintMaster.TABLE_NAME ,AllConstraints.TABLE_NAME  , AllConstraints.DB_TABLE_NAME , "SYS"  )
		 						                 , new DbForignKey(TableMaintMaster.OWNER ,AllConstraints.OWNER  , AllConstraints.DB_TABLE_NAME , "SYS")} , false ) ;

	private static DbForignKeyArray ForignKeyForTableNotificationRule =
		 new DbForignKeyArray ( new DbForignKey[]{ new DbForignKey(TableMaintMaster.TABLE_NAME , TableNotificationRule.TABLE_NAME  , TableNotificationRule.DB_TABLE_NAME , TableNotificationRule.DB_TABLE_OWNER  )
		 						                 , new DbForignKey(TableMaintMaster.OWNER ,TableNotificationRule.TABLE_OWNER  , TableNotificationRule.DB_TABLE_NAME , TableNotificationRule.DB_TABLE_OWNER )} , false ) ;

	private DataSet tableNotificationRulesDS ; 

	public DataSet getTableNotificationRuleDS(boolean refreshDS) {
		if (tableNotificationRulesDS == null || refreshDS) 
		{
			try 
			{
				tableNotificationRulesDS = this.getChildrenDataSet(TableNotificationRule.DB_TABLE_NAME, TableNotificationRule.class, false , "" ) ;
			}
			catch(Exception e ){}
		}
		return tableNotificationRulesDS;
	}
	
	private DataSet RefConstraintsDS ;

	public DataSet getRefConstraintsDS() 
	{
		//DataSet RefConstraintsDS = null ;
		if (RefConstraintsDS == null)
		{
			try {
				String query = "Select t.*  from Sys."+ AllConstraints.DB_TABLE_NAME 
				+ " t where t." + AllConstraints.TABLE_NAME +"='" + this.getTableNameValue()
				+"' and t." + AllConstraints.OWNER + "= '"+this.getOwnerValue()
				+"'  AND t.CONSTRAINT_TYPE = 'R'" ;
				RefConstraintsDS = this.getDbService().queryForDataSet(query, AllConstraints.class); 
				//getChildernDataSetMap().put("REF_CONSTRAINTS", RefConstraintsDS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return RefConstraintsDS;
	}
	
	DataSet sysTablesSecurityControlerForUpdateDS; 
	public DataSet getSysTablesSecurityControlerForUpdateDS()
	{
		return sysTablesSecurityControlerForUpdateDS ; 
		
	}
	DataSet sysTablesSecurityControlerForCreateDS ;
	public DataSet getSysTablesSecurityControlerForCreateDS()
	{
		return sysTablesSecurityControlerForCreateDS ;
	}
	
	DataSet sysTablesSecurityControlerForDeleteDS ; 
	public DataSet getSysTablesSecurityControlerForDeleteDS()
	{
		return sysTablesSecurityControlerForDeleteDS ;
	}
	
	private DataSet calcSysTablesSecurityControlerDS(String pm_operation) 
	{
		
		DataSet result = null ; 
		try {
			TreeMap<String, Object> extraWhere = new TreeMap<String, Object>();
			extraWhere.put(SysTablesSecurityControler.OPEARTION, pm_operation);//= " And " + SysTablesSecurityControler.OPEARTION + "= '" + pm_operation + "'";
			
			result =  this.getChildrenDataSet(SysTablesSecurityControler.DB_TABLE_NAME + pm_operation , SysTablesSecurityControler.class , true , extraWhere);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result ; 
	}
	
	private HashMap<String, DataSet> savedSearchDSMap = new HashMap<String, DataSet>();
	
	/**
	 * 
	 * @return a list of Saved Search associated with this table and owned by currently logged User
	 */
	public DataSet getLoggedUserSavedSearchDS() 
	{
		DataSet loggedUserSavedSearchDs = null ;
		SecUsrDta logedUser = this.getDbService().getLoggedUser();
		if (logedUser != null)
		{
			String loggedUserName = (logedUser != null)? logedUser.getUsrNameValue().toUpperCase() : null ;
			loggedUserSavedSearchDs = this.getSavedSearchDSMap().get(loggedUserName) ; 
			if (loggedUserSavedSearchDs == null )
			{
				try {
					String extraWhere = " And upper(" + SavedSearch.OWNER + ") = '" + loggedUserName + "'"+"or ("+SavedSearch.PUBLIC_SEARCH+"='Y' And "+SavedSearch.TABLE_NAME +" = '"+this.getTableNameValue()+"')";
					loggedUserSavedSearchDs =  this.getChildrenDataSet(SavedSearch.DB_TABLE_NAME , SavedSearch.class , true , extraWhere);
					if (loggedUserSavedSearchDs != null ) this.getSavedSearchDSMap().put(loggedUserName, loggedUserSavedSearchDs);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return loggedUserSavedSearchDs ; 
	}
	
	/**
	 * 
	 * @return A {@link DataSet} for all {@link TableUserView}'s  (Used To Control User Accessibility for this table) Associated with This Table.
	 */
	private DataSet tableUserViewDS = null;
	public DataSet getTableViewsDS() 
	{
		if(tableUserViewDS == null);
		try
		{
			tableUserViewDS =  this.getChildrenDataSet(TableUserView.DB_TABLE_NAME , TableUserView.class , false);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return tableUserViewDS;
	}
	
	private DataSet sysPOAttachGroupDS = null;
	public DataSet getSysPOAttachGroupDS() 
	{
		if(sysPOAttachGroupDS == null);
		try
		{
			sysPOAttachGroupDS =  this.getChildrenDataSet(SysPoAttachmentGroup.DB_TABLE_NAME , SysPoAttachmentGroup.class , false);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return sysPOAttachGroupDS;
	}

	
	public void addTableUserView()
	{
		try {
			this.getTableViewsDS().addNew();
			((TableUserView)(this.getTableViewsDS().getCurrentItem())).setTabelNameValue(this.getTableNameValue());
			((TableUserView)(this.getTableViewsDS().getCurrentItem())).setTableOwnerValue((this.getOwnerValue()));
			((TableUserView)(this.getTableViewsDS().getCurrentItem())).setTabelNameValue(this.getTableNameValue());
			((TableUserView)(this.getTableViewsDS().getCurrentItem())).setUserIdValue(this.getDbService().getLoggedUser().getUsrNameValue());
			((TableUserView)(this.getTableViewsDS().getCurrentItem())).setViewWhereConditionValue("1=1");
			this.getTableViewsDS().save();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static DbForignKeyArray  ForignKeyForUserDefinedVarsGrp = 
			 new DbForignKeyArray ( new DbForignKey[]{new DbForignKey(TableMaintMaster.TABLE_NAME ,  UserDefVarGroups.TABLE_NAME , UserDefVarGroups.DB_TABLE_NAME , TableMaintMaster.CDB_SCHEMA_OWNER )
			                        ,new DbForignKey(TableMaintMaster.OWNER , UserDefVarGroups.TABLE_OWNER ,UserDefVarGroups.DB_TABLE_NAME, TableMaintMaster.CDB_SCHEMA_OWNER )
			} , false );
	

	private static DbForignKeyArray ForignKeyForTableMaintDetails = 
	
		 new DbForignKeyArray ( new DbForignKey[]{new DbForignKey(TableMaintMaster.TABLE_NAME ,TableMaintDetails.TABLE_NAME , TableMaintDetails.DB_TABLE_NAME , TableMaintMaster.CDB_SCHEMA_OWNER )
								,new DbForignKey(TableMaintMaster.OWNER ,TableMaintDetails.OWNER , TableMaintDetails.DB_TABLE_NAME , TableMaintMaster.CDB_SCHEMA_OWNER )
		} , false ) ;
	



	
	/**
	 * 
	 * @return a DataSet for This Table data (Sample Data with a size = )
	 */
	public DataSet getTableContents() {
		if (this.tableContentsSample == null && this.getTableNameValue()!= null) 
			{
				loadTableContents();
			}
		return this.tableContentsSample;
	}
	
	private int tableContentSampleSize = 10; 
	

	private Class mapedClass =null;

	public Class calcMapedClass()
	{
		Class result = null ; 
		try
		{
			if(this.getMapClassNameValue()!=null&&!this.getMapClassNameValue().equals("") )
			{
				result =Class.forName(this.getMapClassNameValue().trim());
			}
    		else
    		{
    			result=this.getPersistantObjectClass();
    		}
					
		}
		catch (Exception e) {}
		return result ;
	}

	public Class getMapedClass()
	{
		if ( mapedClass ==null )
		{
			this.refreshMapedClass();
		}
		return mapedClass ;
	}
	
	public void refreshMapedClass()
	{
		this.mapedClass = this.calcMapedClass() ;
	}
	
	private void loadTableContents()
	{
		try {
			//TableUserView tuv =  this.getTableViewForLoggedUser() ; 
			String loggedUserView = "";
			//if (tuv != null)
			loggedUserView = this.getLoggedUserSecurityFilter() ;
			loggedUserView = loggedUserView == null || loggedUserView.equals("") ? "" : loggedUserView + " AND " ; 
			String query =  this.getAllItemsQuery()+ " Where "+loggedUserView +" rownum <=" + this.getTableContentSampleSize() ; 
			Class ClassMap = this.getMapedClass();
			Query q = new Query(query) ; 
			this.tableContentsSample = this.getDbService().queryForDataSet(q,null, ClassMap, null);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void loadDefaultColumns() throws Exception, SQLException
	{	
		
		this.getTableMaintDetailss().markAllToBeDeleted();
		ArrayList<PersistantObject> tmds = this.getTableDefaultColumns(this.getTableNameValue() , this.getOwnerValue());
		this.getTableMaintDetailss().addAllNew(tmds) ;
	}
	
	public void deleteAll() throws Exception
	{
		this.getTableMaintDetailss().markAllToBeDeleted();
	}
	/**
	 * This Method will Check for any new Column created in DB and add them to the child column. 
	 * Object should be saved to commit changes
	 * @throws Exception
	 * @throws SQLException
	 */
	public void loadUpdatesOnly() throws Exception, SQLException
	{	
		
		List<PersistantObject> tmdss = this.getTableDefaultColumns(this.getTableNameValue() , this.getOwnerValue());
		for (int i = 0 ; i< tmdss.size() ; i++)
		{
			TableMaintDetails tmds =  (TableMaintDetails) tmdss.get(i);
			if (! alreadyExists(tmds))
			{
				this.getTableMaintDetailss().getPersistantObjectList().add(tmds);
				this.getTableMaintDetailss().getNewList().add(tmds);
			}
		}
		this.getTableMaintDetailss().resetSaveAllCommandList() ;
	
	}
	public void fetchObjectMasterInfo()
	{
		String query = "select t.owner, t.object_name, t.subobject_name, t.object_id, t.data_object_id," 
			+"\n t.object_type, t.created, t.last_ddl_time, t.timestamp, " 
			+"\n t.status, t.temporary, t.generated,t.secondary, t.namespace, " 
			+"\n t.edition_name  "
			+"\n from SYS.ALL_OBJECTS t where t.owner = '"+this.getOwnerValue()+"' and t.object_name = '"+this.getTableNameValue()+"'" ;
		DbServices ds =  this.getDbService();
		PersistantObject objectInfo = ds.queryForDataSet(query, null).getPersistantObjectList().get(0);
		this.setObjectTypeValue((String) objectInfo.getAttributeValue("OBJECT_TYPE")) ;
		
	}
	public void synchronizeWithDb() throws Exception, SQLException
	{
		this.fetchObjectMasterInfo();
		this.loadUpdatesOnly();
		this.cleanNonRealCoumns();
		
	}
	public void cleanNonRealCoumns()
	{
		try 
		{
			ArrayList<PersistantObject> childTmds = this.getTableMaintDetailss().getPersistantObjectList() ;
			ArrayList<PersistantObject> tmdss = this.getTableDefaultColumns(this.getTableNameValue() , this.getOwnerValue());
			for (int i = 0 ; i< childTmds.size() ; i++)
			{
				TableMaintDetails  childTmd = (TableMaintDetails) childTmds.get(i);
				if (! isIncludedinList(tmdss , childTmd))
				{
					this.getTableMaintDetailss().markObjectToBeDeleted(childTmd);
				}
			}
		} 
		catch (Exception e) {
			  SysMsgServices sysmessservice=this.getDbService().getModuleServiceContainer().getSysMsgServices();
		      SysMsg sysMsg = sysmessservice.getUnableToCleanTable();
		      sysMsg.setMsgDescValue(sysMsg.getMsgDescValue()+e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 * Checks if this object has a corresponding column in the Db
	 * @param childTmd
	 * @return true if there is a column corresponding to this object( with the same owner , table Name and column name ) 
	 * @throws Exception 
	 */

	private boolean isIncludedinList(ArrayList<PersistantObject> pm_tmds , TableMaintDetails pm_tmd)
	{
		boolean result = false ;
		for(PersistantObject tmd : pm_tmds )
		{
			if (pm_tmd.equals(tmd))
			{
				result = true ;
				break;
			}
		}
		return result;
	}

	private boolean alreadyExists(TableMaintDetails tmds)
	{
		boolean result = false;
		for (int i = 0 ; i< this.getTableMaintDetailss().getPersistantObjectList().size() ; i++)
		{
			TableMaintDetails tmdsXX = (TableMaintDetails) this.getTableMaintDetailss().getPersistantObjectList().get(i);
			if (tmdsXX.equals(tmds))
			{
				result = true;
				break;
			}
		}
		return result;
	}

	private DataSet tmdDs = null;
	/**
	 * 
	 * @return A DataSet Contains {@link TableMaintDetails}'s  (Column details) for this Table
	 */
	public DataSet getTableMaintDetailss() 
	{
		if(tmdDs == null)
		{
			try
			{
				Class tmdClass = TableMaintDetailPOAware.class ; // TableMaintDetails.class; 
				String extraWhere="ORDER BY   TAB_INDEX";
				tmdDs           =this.getChildrenDataSet(TableMaintDetails.DB_TABLE_NAME , tmdClass , false,extraWhere);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return tmdDs;
	}
	
	public HashMap<String, TableMaintDetails > getTableMaintDetailsHashMap()
	{
		HashMap<String, TableMaintDetails > result = new HashMap<String, TableMaintDetails >();
		DataSet tmdDs = getTableMaintDetailss();
		for (PersistantObject po : tmdDs.getPersistantObjectList())
		{
			TableMaintDetails tmd = (TableMaintDetails) po ;
			result.put(tmd.getColumnNameValue(), tmd);
		}
		return result; 
	}
	
	
	private DataSet userDefinedVarGrpDS = null;	
	public DataSet getUserDefinedVarGrpsDS() {		
	if(userDefinedVarGrpDS == null)		{
			try {
				userDefinedVarGrpDS = this.getChildrenDataSet(USR_DEF_VAR_GRP_TABLE_NAME , UserDefVarGroups.class , false);
			} catch (Exception e) {
				e.printStackTrace();
				}
		}
		return userDefinedVarGrpDS;
	 
 	}
	
	public UserDefVarGroups getUserDefVarGroupByCodeName()throws Exception
	{
		return  getUserDefVarGroupByCodeName(this.getSelectedUserDefindVariableGroup());
	}
	public UserDefVarGroups getUserDefVarGroupByCodeName(String pm_UserDefVarGrpName) throws Exception
	{
		UserDefVarGroups usrDefVarGrp = null;
		for(PersistantObject po : this.getUserDefinedVarGrpsDS().getPersistantObjectList())
		{
			usrDefVarGrp = (UserDefVarGroups) po;
			if(usrDefVarGrp.getUdvgCodeValue().equals(pm_UserDefVarGrpName))
			{
				break;
			}
		}
		if(usrDefVarGrp == null && pm_UserDefVarGrpName != null && ( pm_UserDefVarGrpName.equals(TableMaintMaster.SYS_DEFAULTS_GRP_NAME)
									|| pm_UserDefVarGrpName.equals(TableMaintMaster.USR_DEFAULTS_GRP_NAME) ) )
		{
			this.getUserDefinedVarGrpsDS().addNew();
			usrDefVarGrp = (UserDefVarGroups)this.getUserDefinedVarGrpsDS().getCurrentItem();
			usrDefVarGrp.setUdvgCodeValue(pm_UserDefVarGrpName);
		}
		return usrDefVarGrp;
	}
		
	public void createSystemDefaults() {
		try 
		{
			UserDefVarGroups sysDefaultGroup = this.getUserDefVarGroupByCodeName(TableMaintMaster.SYS_DEFAULTS_GRP_NAME);
			DataSet userDefVarDS = sysDefaultGroup.getUserDefVarDS();
			
			if (searchForUserDefvarByName(userDefVarDS, IS_LOGGED_USER_CAN_UPDATE) == null) {
				
				 userDefVarDS.addNew();
				 UsrDefVar udf =  (UsrDefVar) userDefVarDS.getCurrentItem();
				 udf.setUdvVarDesc_Value(IS_LOGGED_USER_CAN_UPDATE);
				 udf.setUdvVarDescValue(IS_LOGGED_USER_CAN_UPDATE);
				 udf.getUdvVarField().setValue("Select 'Y' Result from dual");
				 udf.setSystemGeneratedValue("Y");
				 udf.setUdvVarValue(IS_LOGGED_USER_CAN_UPDATE);
				 userDefVarDS.save();
 			}
			if (searchForUserDefvarByName(userDefVarDS, IS_LOGGED_USER_CAN_DELETE) == null) {
				
				userDefVarDS.addNew();
				UsrDefVar udf =  (UsrDefVar) userDefVarDS.getCurrentItem();
				udf.setUdvVarDesc_Value(IS_LOGGED_USER_CAN_DELETE);
				udf.setUdvVarDescValue(IS_LOGGED_USER_CAN_DELETE);
				udf.getUdvVarField().setValue("Select 'Y' Result from dual");
				udf.setSystemGeneratedValue("Y");
				udf.setUdvVarValue(IS_LOGGED_USER_CAN_DELETE);
				userDefVarDS.save();
				userDefVarDS.save();
	 		}
			if (searchForUserDefvarByName(userDefVarDS, IS_LOGGED_USER_CAN_CREATE) == null) {
				userDefVarDS.addNew();
				UsrDefVar udf =  (UsrDefVar) userDefVarDS.getCurrentItem();
				udf.setUdvVarDesc_Value(IS_LOGGED_USER_CAN_CREATE);
				udf.setUdvVarDescValue(IS_LOGGED_USER_CAN_CREATE);
				udf.getUdvVarField().setValue("Select 'Y' result from dual");
				udf.setSystemGeneratedValue("Y");
				udf.setUdvVarValue(IS_LOGGED_USER_CAN_CREATE);
				userDefVarDS.save();
				userDefVarDS.save();
				userDefVarDS.save();
		 		
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public UsrDefVar searchForUserDefvarByName(DataSet dataSetToSearch, String udfUniqueName)  {
		UsrDefVar result = null;
		List<PersistantObject> pol = dataSetToSearch.getPersistantObjectList();
		for (PersistantObject po : pol)
		{
			UsrDefVar udf = (UsrDefVar)po;
			if (udf.getUdvVarValue().equalsIgnoreCase(udfUniqueName))
					{
						result = udf ;
						break;
					}
		}
		return result;
	}

	private String selectedUserDefindVariableGroup = USR_DEFAULTS_GRP_NAME;
	public void setSelectedUserDefindVariableGroup(String selectedGrp) {
		this.selectedUserDefindVariableGroup = selectedGrp;
	}

	public String getSelectedUserDefindVariableGroup() {
		return (selectedUserDefindVariableGroup==null)? USR_DEFAULTS_GRP_NAME : selectedUserDefindVariableGroup;
	}
	/**
	 * 
	 * @return an Array of {@link SelectItem} used in the JSF Layer 
	 * @throws Exception
	 */
	public ArrayList<SelectItem> getAllUserDefinedVariablesGroups()
	{
		if (userDefinedVariablesGroups == null) 
		{
			String query = "Select t." + UserDefVarGroups.UDVG_CODE + ", t." + UserDefVarGroups.UDVG_DESC_  +", t." + UserDefVarGroups.UDVG_DESC  + 
							" from "+ TableMaintMaster.CDB_SCHEMA_OWNER+"." + UserDefVarGroups.DB_TABLE_NAME + " t where " + " t." + UserDefVarGroups.TABLE_NAME + " = '" + this.getTableNameValue() + "'";  
			userDefinedVariablesGroups = this.getDbService().getSelectItems(query,true);
		}
		return userDefinedVariablesGroups;
	}

	/**
	 * 
	 * @return an Array of {@link SelectItem} used in the JSF Layer 
	 * @throws Exception
	 */
	public ArrayList<SelectItem> getUserDefienedVariables() throws Exception
	{
		if (userDefienedVariables == null) 
		{
			String delimiter = PersistantObject.USER_DEFINED_DELIMTER; 
			String query = "select '"+delimiter+"'|| "+UsrDefVar.UDV_VAR+", "+UsrDefVar.UDV_VAR+" , "+UsrDefVar.UDV_VAR+"  from "+UsrDefVar.DB_TABLE_NAME   +
					" where "+UsrDefVar.TABLE_OWNER+" = '"+this.getOwnerValue()+"' and "+UsrDefVar.TABLE_NAME+" = '"  + this.getTableNameValue() +"'" ; //+ ApplicationContext.getUserDefVarService(getDbService()).getSelectedTableName() + "'";
			userDefienedVariables = this.getDbService().getSelectItems(query,false);
		}
		return userDefienedVariables;
	}
	
	/**
	 * 
	 * @param pm_tableName
	 * @param pm_tableOwner
	 * @return A {@link List} of {@link TableMaintDetails} Containing a fresh copy from Database MetaData
	 * @throws Exception
	 * @throws SQLException
	 */
	public  ArrayList<PersistantObject> getTableDefaultColumns(String pm_tableName , String pm_tableOwner) throws Exception
	{
		ArrayList<PersistantObject> result = new ArrayList<PersistantObject>();
		DbServices ds =  this.getDbService();
		
		String query1 = "select t1.* , t2.comments  " +
				" From  all_col_comments t2,   all_tab_columns t1 " +
				" where t1.TABLE_NAME = '"+pm_tableName+"' " +
				" and t1.OWNER = '" +pm_tableOwner+"'"  +
				" and t1.TABLE_NAME = t2.table_name " +
				" and t1.OWNER = t2.owner " +
				" and t1.COLUMN_NAME = t2.column_name" ;
		DataSet dictionaryData = ds.queryForDataSet(query1, null);
		int size = dictionaryData.getPersistantObjectList().size();		
		for  ( int i = 0 ; i<size ; i++)
		{   
			PersistantObject po = dictionaryData.getPersistantObjectList().get(i);
			String columnName = (String) po.getAttributeValue("COLUMN_NAME");
			String dataType = (String) po.getAttributeValue("DATA_TYPE");
			BigDecimal datalength = (BigDecimal) po.getAttributeValue("DATA_LENGTH");
			String nullable = (String) po.getAttributeValue("NULLABLE");
			BigDecimal columnId = (BigDecimal) po.getAttributeValue("COLUMN_ID");
			String comments = (String) po.getAttributeValue("COMMENTS");
			BigDecimal dataLegth = (BigDecimal) po.getAttributeValue("DATA_LENGTH");
			
			TableMaintDetails tmd = (TableMaintDetails) this.getTableMaintDetailss().createNew(false , false);//new TableMaintDetail();
						
			String proposedHtmlType = TableMaintDetails.INPUT_TEXT;
			
			if (dataType.equalsIgnoreCase("DATE")  )
				proposedHtmlType = TableMaintDetails.INPUT_CALENDER;
			else if (dataLegth.equals(new BigDecimal(1)))
				proposedHtmlType = TableMaintDetails.SELECT_BOOLEAN;
			
			else if (dataType.equalsIgnoreCase("CLOB"))
				proposedHtmlType = TableMaintDetails.INPUT_TEXT_AREA;
			
			else 
			proposedHtmlType = TableMaintDetails.INPUT_TEXT;
			tmd.setDataTypeValue(dataType);
			tmd.setHtmlTypeValue( proposedHtmlType);
			tmd.setColumnNameValue(columnName);
			tmd.setDisplayNameValue(columnName);
			tmd.setDisplayName_Value(columnName);
			tmd.setTableNameValue( pm_tableName);
			tmd.setOwnerValue( pm_tableOwner);
			tmd.setTabIndexValue(columnId);
			tmd.setCommentsValue(comments);
			tmd.setTooltipDescValue(comments);
			tmd.setTooltipDesc_Value(comments);
			tmd.setNullableValue( this.getMandatoryFields().contains(tmd.getColumnNameValue()) ? "N" : nullable);
			tmd.setDatalengthValue(datalength);
			tmd.setIncludedInResultValue( "Y");
			tmd.setColumnIncludedValue("Y");
			tmd.setEncrypedValue("N");
			tmd.setIncludedInEditValue("Y");
			tmd.setRenderedValue("Y");
			tmd.setDefaultAccessTypeValue("A");
			tmd.setRenderProtectionTypeValue(TableMaintDetails.SIMPLE_PROTECTION);
			tmd.setDisableProtectionTypeValue(TableMaintDetails.SIMPLE_PROTECTION);
			tmd.setGetCurrentitemBymeValue("N");
			tmd.setPatternValue("d/M/yy HH:mm"); //Default date Pattern
			tmd.setPopUpValue("Y");
			tmd.setCalenderLocaleValue("en/US"); // Default date Local
			tmd.setShowLabelValue("Y");
			if (		columnName.equalsIgnoreCase("PREPARED_BY")
					|| columnName.equalsIgnoreCase("MODIFIED_BY")
					|| columnName.equalsIgnoreCase("PREPARED_DT")
					|| columnName.equalsIgnoreCase("MODIFIED_DT")
				)
			{
				tmd.setDisabledValue("Y");
				tmd.setColumnIncludedValue("N");
				tmd.setIncludedInResultValue("N");
				tmd.setIncludedInEditValue("N");
			}
			
			if (columnName.equalsIgnoreCase("PREPARED_BY") )
			{
				tmd.setDisplayNameValue("Created By");
				tmd.setDisplayName_Value("تم انشاء بواسطة");
			}
			if (columnName.equalsIgnoreCase("PREPARED_DT") )
			{
				tmd.setDisplayNameValue("Created At");
				tmd.setDisplayName_Value("وقت الانشاء");
				tmd.setCalenderLocaleValue("en/US");
				tmd.setPopUpValue("Y");
			}
			if (columnName.equalsIgnoreCase("MODIFIED_BY") )
			{
				tmd.setDisplayNameValue("Last Updated By");
				tmd.setDisplayName_Value("اخر تعديل بواسطة");
			}
			if (columnName.equalsIgnoreCase("MODIFIED_DT") )
			{
				tmd.setDisplayNameValue("Last Updated At");
				tmd.setDisplayName_Value("وقت اخر تعديل");
				tmd.setCalenderLocaleValue("en/US");
				tmd.setPopUpValue("Y");
			}
			tmd.setSelectQueryListFromFK();
			result.add(tmd);
		}
		
		return result;
	}
	
	public static final PersistentObjectSecurityControl securityController  = new TableMaintMasterSecurityControlHandlerImpl() ;
	@Override
	public PersistentObjectSecurityControl getSecurityController() 
	{
	 return securityController ;
	}
	@Override
	public TriggerHandler getTriggerHandler() 
	{
		// TODO Auto-generated method stub
	 return null; 
	}
	@Override
	public AttributeChangeValidator getAttributeChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key) 
	{
		// TODO Auto-generated method stub
	 return null; 
	}
	public TableMaintDetails getTmdByColumnName(String pm_columnName)
	{
		TableMaintDetails result = null; 
		List<PersistantObject> tmdss = this.getTableMaintDetailss().getPersistantObjectList();
		for (int i = 0 ; i < tmdss.size() ; i++)
		{
			TableMaintDetails item = (TableMaintDetails) tmdss.get(i);
			if (item.getColumnNameValue().equalsIgnoreCase(pm_columnName))
			{
				result = item ; 
				break;
			}
			
		}
		return result;
		
	}

	public void setSelectedTmds(Set<TableMaintDetails> selectedTmds)
	{
		this.selectedTmds = selectedTmds;
	}

	public Set<TableMaintDetails> getSelectedTmds()
	{
		return selectedTmds;
	}

//	public void setListForSearchAsSelectItems(List<SelectItem> listForSearchAsSelectItems) {
//		this.listForSearchAsSelectItems = listForSearchAsSelectItems;
//	}
//	int listsCalculatedForlang = -1;
	/**
	 * @return column list used in Search as a Select List Useful in JSF
	 */
	public List<SelectItem> getListForSearchAsSelectItems()
	{

			 int userlang = this.getDbService().getLoggedUserLang() ;
			String displayColumnName = (userlang == 2)? TableMaintDetails.DISPLAY_NAME : TableMaintDetails.DISPLAY_NAME_ ;
			
			ArrayList<PersistantObject> listForSearch = getSpecificChildTmd(TableMaintDetails.COLUMN_INCLUDED);
			ArrayList<SelectItem> listForSearchAsSelectItems = PersistantObject.convertListOfposToSelectItems(listForSearch , TableMaintDetails.COLUMN_NAME , displayColumnName );
			
		return listForSearchAsSelectItems;
	}
	
	private ArrayList<PersistantObject> getSpecificChildTmd(String pm_columnName)
	{
		ArrayList<PersistantObject>  result = new ArrayList<PersistantObject> ();
		Iterator<PersistantObject> itTmm = this.getTableMaintDetailss().getPersistantObjectList().iterator();
		
		while (itTmm.hasNext())
		{
			TableMaintDetails tmd = (TableMaintDetails)itTmm.next();
			if(pm_columnName ==null || tmd.getAttribute(pm_columnName).getBooleanValue()) 
				{	
				result.add(tmd);
				}
		}
		return result ;
	}
	
	
	

	/**
	 * 
	 * @return List of TableMaintDetails used as a Search parameters (User allowed to use as a Search )  
	 */
	public List<PersistantObject> getListForSearch() {
		return getSpecificChildTmd(TableMaintDetails.COLUMN_INCLUDED);
	}


	/**
	 * 
	 * @return a List of Table Columns { TableMaintDetails } Used in the Auto Generated Grid or Tables Interface
	 */
	public ArrayList<PersistantObject> getListForResult() {
		return getSpecificChildTmd(TableMaintDetails.INCLUDED_IN_RESULT);
	}


	/**
	 * 
	 * @return a comma separated column names used for display
	 */
	public String getSearchResultsDisplayNames() {
		String result = " ";
		
			int ORACLE_MAX_ALIAS_SIZE = 15; 
			Iterator<PersistantObject> it =  this.getListForResult().iterator();
			int counter = 0;
			while (it.hasNext())
			{
				TableMaintDetails tmd = (TableMaintDetails)it.next();
				if (tmd.getIncludedInResult().getBooleanValue())
				{
					String alias = (String) tmd.getDisplayNameAutoLang().getValue();
					//String aliasTrimmed = (alias.length() >= ORACLE_MAX_ALIAS_SIZE)? alias.substring(0, ORACLE_MAX_ALIAS_SIZE-1) : alias;
					result += ((counter==0)? "": ", " ) + tmd.getColumnName().getValue();// + " \"" + aliasTrimmed + "\" "; 
					counter++;
				}
			}
		return result;
	}
	
	public DBKey getUniqueKeys() throws Exception
	{
		if (this.uniqueKeys == null)
		{
			try {
				this.uniqueKeys = this.calcConstrainType(DBKey.UNIQUE_KEY);
				} 
			catch (Exception e) 
			{
			}
		}
		return this.uniqueKeys ; 
	}
	
	DBKey primaryKeys ; 
	public DBKey getPrimaryKeys() throws Exception
	{
		if (this.primaryKeys== null)
		{
			try{
			this.primaryKeys = this.calcConstrainType(DBKey.PRIMARY_KEY);
			}
			catch(Exception e){}
		}
		return this.primaryKeys ; 
		
	}
	private DBKey calcConstrainType(String constraint) throws Exception
	{
		DBKey  result = null ; 
	
			String query = "SELECT  cc.COLUMN_NAME "
			+" FROM  all_constraints uc , all_cons_columns cc "
			+" where uc.owner = '"+this.getOwnerValue()+"' "
			+" and   uc.table_name = '"+this.getTableNameValue()+"' "
			+" and   uc.owner = cc.owner "
			+" and   cc.constraint_name = uc.constraint_name "
			+" and   cc.table_name = uc.table_name "
			+" AND   uc.constraint_type = '"+constraint+"' " 
			+" Order by cc.position ";
			
			List<PersistantObject> queryResult = this.getDbService().queryForList(query);
			if(!queryResult.isEmpty())
			{
				result  = new DBKey(constraint);// String[result.size()];
				for (int i = 0 ; i< queryResult.size() ; i++)
				{
					result.addColumn((String) queryResult.get(i).getAttribute("COLUMN_NAME").getValue());
				}
			}
	
		
		return result;
		
	}

	public List<String> getMandatoryFields()
	{
		if(mandatoryFields == null)
		{
			mandatoryFields = new ArrayList<String>();
			try {
				List<PersistantObject> PoList = this.getDbService().queryForList(
						"select column_name from user_tab_cols where table_name='"+this.getTableName()+"' and nullable='N'");			
			for(PersistantObject po : PoList)
				mandatoryFields.add((String) po.getAttributeValue("column_name".toUpperCase()));
			} catch (Exception e) {
			}
		}
		return mandatoryFields;
	}
	
	/**
	 * 
	 * @param tableMaintDSSize Sample size used to retrieve data sample from this table
	 */
	public void setTableContentSampleSize(int tableMaintDSSize) {
		if (tableMaintDSSize != this.tableContentSampleSize )
		{
			this.tableContentSampleSize = tableMaintDSSize;
			this.tableContentsSample = null;
		}
	}

	/**
	 * 
	 * @return Sample size used to retrieve data sample from this table
	 */
	public int getTableContentSampleSize() {
		return tableContentSampleSize;
	}
	
	public List<SelectItem> getListOfColumnsNames() {
			
			ArrayList<PersistantObject> listForSearch = this.getSpecificChildTmd(null);
			ArrayList<SelectItem> listOfColumnNames = PersistantObject.convertListOfposToSelectItems(listForSearch , TableMaintDetails.COLUMN_NAME , TableMaintDetails.COLUMN_NAME );
			return listOfColumnNames;
	
	}

	private ArrayList<SelectItem> listOfColumnDisplayNames ;
	public List<SelectItem> getListOfColumnsDescAutoLang(){
		if(listOfColumnDisplayNames == null)
		{
			int usrLang = this.getDbService().getLoggedUserLang();
			ArrayList<PersistantObject> listForSearch = this.getSpecificChildTmd(null);
			
			if(usrLang == 1)
			{
				listOfColumnDisplayNames = PersistantObject.convertListOfposToSelectItems(listForSearch , TableMaintDetails.COLUMN_NAME , TableMaintDetails.DISPLAY_NAME_);
			}
			else
			{
				 listOfColumnDisplayNames = PersistantObject.convertListOfposToSelectItems(listForSearch , TableMaintDetails.COLUMN_NAME , TableMaintDetails.DISPLAY_NAME);
			}
		}
		return listOfColumnDisplayNames;
	}

	public void setPersisObjectController(IPersisObjectController persisObjectController) {
		this.persisObjectController = persisObjectController;
	}

	public IPersisObjectController getPersisObjectController() {
		if(persisObjectController == null)
			this.refreshSecurityControler();
		return persisObjectController;
	}
	
	public java.math.BigDecimal getSecurityControllerIdValue(){
		BigDecimal result = null;
		result = super.getSecurityControllerIdValue();
		if (result == null)
		result = new BigDecimal(TableMaintMaster.SIMPLE_PROTECTION);
		return result ;
	}
	public IPersisObjectController calcSecurityControler()
	{
		IPersisObjectController  SecurityControler =  TableMaintMaster.calcSecurityControler(this.getSecurityControllerIdValue().toString() , this.getDbService());

		if (SecurityControler instanceof PoSecurityContollerUserGroupImpl)
		{
			if (sysTablesSecurityControlerForDeleteDS==null)
			{
			sysTablesSecurityControlerForDeleteDS = this.calcSysTablesSecurityControlerDS(PersistentObjectAuditor.DELETE_TRANSACTION); 
			}
			if (sysTablesSecurityControlerForUpdateDS == null)
			{
				sysTablesSecurityControlerForUpdateDS= this.calcSysTablesSecurityControlerDS(PersistentObjectAuditor.UPDATE_TRANSACTION); 
			}
			if (sysTablesSecurityControlerForCreateDS == null)
			{
				sysTablesSecurityControlerForCreateDS = this.calcSysTablesSecurityControlerDS(PersistentObjectAuditor.INSERT_TRANSACTION); 
			}
		}
		return 	SecurityControler ;
	}
	private static IPersisObjectController calcSecurityControler(String controlerId , DbServices dbs)
	{
		InterfaceImplServices ims = new InterfaceImplServices(dbs);
		controlerId = (controlerId == null) ? TableMaintMaster.SIMPLE_PROTECTION : controlerId ;
		SysInterfaceImplementors sii =  ims.getImplementorByCode(controlerId.toString() , IPersisObjectController.class.getName());
		return  ((sii!= null)? (IPersisObjectController)sii.getImplInstance() : new PoSecurityContollerSimpleImpl());
	}

	public boolean isSimpleSecurityController()
	{
		return this.getPersisObjectController() instanceof PoSecurityContollerSimpleImpl ;
	}
	
	public boolean isUdvSecurityController()
	{
		return this.getPersisObjectController() instanceof PoSecurityContollerUdvImpl ;
	}
	
	public boolean isUsersSecurityController()
	{
		return this.getPersisObjectController() instanceof PoSecurityContollerUserGroupImpl ;
	}
	public boolean isSchSecurityController()
	{
		return this.getPersisObjectController() instanceof PoSecurityContollerSchImpl ;
	}

	public boolean containsColumn(String pm_columnName) {
		boolean result = this.getTmdByColumnName(pm_columnName) != null ;
		return result ; 
	}
	
//	public void setSecurityControllerIdValue(java.math.BigDecimal   pm_securityControllerId)
//	{	
//		super.setSecurityControllerIdValue(pm_securityControllerId);
//		this.persisObjectController = calcSecurityControler();
//	}

	public void setChildTobeUpdated(PersistantObject childTobeUpdated) {
		this.childTobeUpdated = childTobeUpdated;
	}

	public PersistantObject getChildTobeUpdated() {
		return childTobeUpdated;
	}
	
	public void refreshSecurityControler()
	{
		if (this.getTableNameValue() != null && ! this.getTableNameValue().equals( SysInterfaceImplementors.DB_TABLE_NAME))	
		{
			persisObjectController = calcSecurityControler();
		}
	}
	public void initialize() 
	{
//		boolean webApp = ApplicationContext.isWebApp();
//		if (! webApp)
//		{
//			return ; 
//		}
		
	
	}

	public HashMap<String, DataSet> getSavedSearchDSMap() {
		return savedSearchDSMap;
	}
	//....................................................................................
	private ArrayList<PersistantObject> getChangSecUsersDtaAsSysTablesecurity(String pm_operation) {
		ArrayList<PersistantObject> result = new ArrayList<PersistantObject> () ; 
	    String operation=pm_operation ; //
	    
		String query="select  t.* from   " + SecUsrDta.DB_TABLE_NAME +"   t , "+SysTablesSecurityControler.DB_TABLE_NAME+"  where SYS_TABLES_SECURITY_CONTROLER.EXECLUDED_USERS  =t.USR_NAME  and TABLE_NAME='"+this.getTableNameValue()+"' and OPEARTION='"+operation+"'";
		try {
			result= (ArrayList<PersistantObject>) this.getDbService().getLoggedUser().getDbService().queryForList(query,SecUsrDta.class);
			} 
		catch (Exception e) 
			{e.printStackTrace();}
	return result;
	}
	
	//.....................................................................ForInsert
	private ArrayList<PersistantObject> getChangSecUsersDtaAsSysTablesecurityForInsert() 
	{
     String operationInsert=PersistentObjectAuditor.INSERT_TRANSACTION;
	 return  getChangSecUsersDtaAsSysTablesecurity(operationInsert);     
	}
	DataSetPickList  dataSetForPickListCreate ; 
	public DataSetPickList getDataSetForPickListCreate() throws Exception {
		if (dataSetForPickListCreate == null)
		{
			ArrayList<PersistantObject> initialValue=getChangSecUsersDtaAsSysTablesecurityForInsert();
			dataSetForPickListCreate  = new DataSetPickList(SysTablesSecurityControler.EXECLUDED_USERS ,SecUsrDta.USR_NAME,this.getSysTablesSecurityControlerForCreateDS(), initialValue);
			ArrayList<SelectItem> allAvailableItems = this.getModuleServiceContainer().getSecUserDataService().getAllAvailableUsers();
			dataSetForPickListCreate.setAllAvailableItems(allAvailableItems);
			dataSetForPickListCreate.setConverterId("converterForSysTableSecurity");

		}
		return dataSetForPickListCreate;
	}
	
	//........................................................................ForDelete
	private ArrayList<PersistantObject> getChangSecUsersDtaAsSysTablesecurityForDelete() {
		String operationDelete = PersistentObjectAuditor.DELETE_TRANSACTION;
		return getChangSecUsersDtaAsSysTablesecurity(operationDelete);
	}
	DataSetPickList  dataSetForPickListDELETE;
	public DataSetPickList getDataSetForPickListDELETE() throws Exception 
	{ 
		if(dataSetForPickListDELETE==null)
		{
		ArrayList<PersistantObject> initialValue=getChangSecUsersDtaAsSysTablesecurityForDelete();
		dataSetForPickListDELETE  = new DataSetPickList(SysTablesSecurityControler.EXECLUDED_USERS ,SecUsrDta.USR_NAME,this.getSysTablesSecurityControlerForDeleteDS(), initialValue);
		ArrayList<SelectItem> allAvailableItems = this.getModuleServiceContainer().getSecUserDataService().getAllAvailableUsers();
		dataSetForPickListDELETE.setAllAvailableItems(allAvailableItems);
		dataSetForPickListDELETE.setConverterId("converterForSysTableSecurity");

		}
		return dataSetForPickListDELETE;
	}
   
//......................................................................................FOR Update
   private ArrayList<PersistantObject> getChangSecUsersDtaAsSysTablesecurityForUpdate()
   {
    String operationUpdate=PersistentObjectAuditor.UPDATE_TRANSACTION;
	return getChangSecUsersDtaAsSysTablesecurity(operationUpdate);
	} 
	
 DataSetPickList  dataSetForPickListUpdate;
 public DataSetPickList getDataSetForPickListUpdate() throws Exception
  {
	if(dataSetForPickListUpdate==null)
	{
	ArrayList<PersistantObject> initialValue=getChangSecUsersDtaAsSysTablesecurityForUpdate();
	dataSetForPickListUpdate  = new DataSetPickList(SysTablesSecurityControler.EXECLUDED_USERS ,SecUsrDta.USR_NAME,this.getSysTablesSecurityControlerForUpdateDS(), initialValue);
	ArrayList<SelectItem> allAvailableItems = this.getModuleServiceContainer().getSecUserDataService().getAllAvailableUsers();
	dataSetForPickListUpdate.setAllAvailableItems(allAvailableItems);
	dataSetForPickListUpdate.setConverterId("converterForSysTableSecurity");
	}
	return dataSetForPickListUpdate;
 }

 	private BatchExecuteResult createAuditColumnsExecResult ; 
	public void createAuditColumns() throws Exception
	{
		ArrayList<String> dmlStatements = new ArrayList<String> () ; 
		dmlStatements.add(TableMaintMaster.CDB_SCHEMA_OWNER + ".general_tools.generate_default_columns ('"+this.getOwnerValue() + "." + this.getTableNameValue()+"')"  ) ;	
		this.setCreateAuditColumnsExecResult(this.getDbService().executeCommandBatch( new CommandBatch( dmlStatements)));
		this.loadUpdatesOnly();	
	}

	public void setCreateAuditColumnsExecResult(
			BatchExecuteResult createAuditColumnsExecResult) {
		this.createAuditColumnsExecResult = createAuditColumnsExecResult;
	}

	public BatchExecuteResult getCreateAuditColumnsExecResult() {
		return createAuditColumnsExecResult;
	}
	
	private 	String m_query="";
	private   	List<TableMaintDetails> selectedItems = new ArrayList<TableMaintDetails>();

	public void setQuery(String pm_query) {
		this.m_query = pm_query;
	}

	
	public Query getQuery() {
		this.constructQuery();
		String originalQuery = this.getAllItemsQuery (); // this.getOwner() + "." + this.getTableName() );
		m_query = m_query == null || m_query.equals("") ? " rownum < "+tableContentSampleSize : m_query;
		Query query = new Query( originalQuery+ " Where " +   m_query) ;
		String loggedUserExtrafilter = getLoggedUserSecurityFilter();
		query.appendAndFilter(loggedUserExtrafilter) ;
		
		return query;
	}
	
	private String loggedUserFilter = null;
	public String loggedUserFilter()
	{
		if(loggedUserFilter == null || loggedUserFilter.isEmpty())
		{
			String query = " Select T.VIEW_WHERE_CONDITION from "+ TableMaintMaster.CDB_SCHEMA_OWNER +" .TABLE_USER_VIEW t where T.TABLE_OWNER = '" + this.getOwnerValue() +"' AND T.USER_ID = '" + this.getDbService().getLoggedUser().getUsrNameValue() + "' and T.TABEL_NAME = '" + this.getTableNameValue() + "'";
			List<PersistantObject> poList = this.getDbService().queryForList(query);
			if(poList == null || poList.isEmpty())
			{
				loggedUserFilter = "";
			}else{
				loggedUserFilter = poList.get(0).getAttribute("VIEW_WHERE_CONDITION").toString();
			}
		}
		return loggedUserFilter;
	}
	
	
	
	private String getLoggedUserSecurityFilter()
	{
		String userViewWhereCondition;
		TableUserView tuv =  this.getDbService().getTableUserViewForLoggedUser(this.getTableNameValue());
		if  (tuv!= null)
		{
			userViewWhereCondition = tuv.getSubstituedViewCond();
		}
		else
		{
			String compIdColumnName = "COMP_ID" ; 
			String divIdColumnName = "DIV_ID" ;
			boolean cmpIdExist = this. containsColumn(compIdColumnName) ; 
			boolean divIdExist = this. containsColumn(divIdColumnName) ; 
			String daefaultCond = ((cmpIdExist) ?  compIdColumnName + " = @@COMP_ID " : "" )
								+ ((cmpIdExist && divIdExist) ? "And" : "")
								+ ((divIdExist) ? divIdColumnName  + " = @@DIV_ID " :"" ) 
									;
			daefaultCond = this.subLoggedUserProperties(daefaultCond) ;
			userViewWhereCondition = (daefaultCond.indexOf("@@")!= -1)? null: daefaultCond ;
		}
		 return userViewWhereCondition ;
	}
	
	public SelectItem[] getOperations() {
		return Operation.getAllAvailableOperations();
	}
	
	public void  constructQuery()
	{
		m_query = "";
		List<TableMaintDetails> selectedItems = this.getSelectedItems(); 

		if (selectedItems== null || selectedItems.size() == 0)
		return;

		Iterator<TableMaintDetails> it = selectedItems.iterator();
		int i = 0;
		while (it.hasNext())
		{
			TableMaintDetails tmd = (TableMaintDetails)it.next();
			String logicalOperation =  ( tmd.getLogicalOperation() == null)? " " :  tmd.getLogicalOperation();
			if (tmd.getCondition().isActive() )
			this.m_query +=( (i==0)? " " :" " + logicalOperation + " " ) + tmd.getCondition().toString();
			i++;
		}
	}
	
	public void setSelectedItemsFromSavedSearch(SavedSearch pm_ss) throws Exception
	{
		this.setQuery("");
		List<PersistantObject> ssds =  pm_ss.getSavedSearchDetailsList();
		 

		 this.selectedItems = new ArrayList<TableMaintDetails>();
		for (int i = 0 ; i< ssds.size() ; i++)
		{
			SavedSearchDetail ssd = (SavedSearchDetail) ssds.get(i);
			TableMaintDetails tmd = ssd.getTableMaintDetail();
			if (i==0) tmd.setFirstItemInlist(true);
			this.selectedItems.add( tmd);
		}
		
	}
	
	public boolean isSelectedItemsEmpty() {
		return (this.selectedItems == null || this.selectedItems.size()==0);
	}
	public void setSelectedItems(ArrayList<TableMaintDetails> tmdL) {
		this.selectedItems = tmdL ; 
	}
	public List<TableMaintDetails> getSelectedItems() {
		
		if(this.selectedItems.isEmpty())
		{
			try {
			//	addNewCondition();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this.selectedItems;
	}
	
	public void addNewCondition(TableMaintDetails tmd) throws Exception
	{
		if (this.selectedItems.size() == 0 )
		{
			tmd.setFirstItemInlist(true);
			tmd.setLogicalOperation("");
		}
		this.selectedItems.add(tmd);
	
	}
	public void addNewCondition() throws Exception
	{
		
		TableMaintDetails tmd = (TableMaintDetails) this.getTableMaintDetailss().createNew(false , false);//new TableMaintDetail();
		this.addNewCondition(tmd) ;
	}
	
	public void deleteCondition(int deletedIndex) {
		List<TableMaintDetails> selectedItems = this.getSelectedItems(); 
		selectedItems.remove(deletedIndex);
		if (!this.getSelectedItems().isEmpty()) 
		{
			TableMaintDetails tmd = (TableMaintDetails) selectedItems.get(0);
			tmd.setFirstItemInlist(true);
			tmd.setLogicalOperation("");
			selectedItems.set(0, tmd);
		}
	}

//	private 	SavedSearch selectedSavedSearch;
//	public void setSelectedSavedSearch(SavedSearch selectedSavedSearch) {
//		this.selectedSavedSearch = selectedSavedSearch;
//	}
//
//	public SavedSearch getSelectedSavedSearch() {
//		return selectedSavedSearch;
//	}
	
	public void setSelectedItemsFromSavedSearch() throws Exception
	{
		this.setSelectedItemsFromSavedSearch((SavedSearch) this.getLoggedUserSavedSearchDS().getSelectedObject());
	}
	

	public void setSavedSearchDetailsFromSelectedItems() throws Exception
	{
		SavedSearch pm_ss =(SavedSearch) this.getLoggedUserSavedSearchDS().getSelectedObject();
		if (pm_ss == null)
		{
			this.getLoggedUserSavedSearchDS().addNew();
			pm_ss = (SavedSearch) this.getLoggedUserSavedSearchDS().getCurrentItem();
		}
	
		List <TableMaintDetails> selectItems = getSelectedItems();
		
		DataSet   dsSavedSearchDetails;
		dsSavedSearchDetails  = pm_ss.getSavedSearchDetails();
		
		dsSavedSearchDetails.markAllToBeDeleted();
		
		for(int i=0;i<selectItems.size();i++)
		{
			TableMaintDetails tmd	=(TableMaintDetails)selectItems.get(i);
		try {
			
				dsSavedSearchDetails.addNew();
				SavedSearchDetail	savedSearchDetail	  = ((SavedSearchDetail)dsSavedSearchDetails.getCurrentItem());    //.setTaxCodeValue(selectedTaxId);
			   
				 savedSearchDetail.setLogicalOperationValue(tmd.getLogicalOperation());
				 savedSearchDetail.setColumnNameValue(tmd.getColumnNameValue());
				 savedSearchDetail.setOperatorValue(tmd.getSelectedOperation());
				 savedSearchDetail.setOrdersValue(new BigDecimal(i));
				       		 
				 String value =(String) tmd.getCondition().getAttribute().getValue().toString();
				 if(value!=null)
				 {
				  ((SavedSearchDetail)savedSearchDetail).setAttributeValueValue(value);
				                 
				 }
	        
	      	
			  } catch (Exception e) {
			   e.printStackTrace();
			  }	
		  
		}
		pm_ss.save();
	}

	public void execSavedSearch(SavedSearch ss)
	{
		try {
			if (!ss.getComplexSearch().getBooleanValue())
			{
				this.setSelectedItemsFromSavedSearch(ss);
				if ( this.selectedItems.isEmpty())
				{
					this.getModuleServiceContainer().getMessageCommnunicatorService().sendMessageToUser("Your Saved Search is Empty") ;
				}
				else
				{
					this.executeSearch();
				}
			}
			else
			{
				try{
					String queryStr = ((ClobAttribute)ss.getQueryBody()).getClobStrValue() ;
					Query query = new Query(queryStr) ;
					query.setEstimatedTableName(this.getTableNameValue()) ;
				this.queryResult = this.getDbService().queryForDataSet(query , null, this.getMapedClass(), null ) ;
				this.selectedItems = new ArrayList<TableMaintDetails>() ;
				}
				catch (Exception e) 
				{
					this.getModuleServiceContainer().getMessageCommnunicatorService().sendMessageToUser("Error During Complex Search Execution") ;
					e.printStackTrace();
				} 
			}
			
			
			this.getViewControllar().setSearchOpened(true);
		} 
	catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public void execSavedSearch()
	{
		SavedSearch ss =(SavedSearch) this.getLoggedUserSavedSearchDS().getSelectedObject() ;
		if (ss != null)
		{
			execSavedSearch(ss) ;
		}
	}
	
	private    Class persistantObjectClass ;
	public void setPersistantObjectClass(Class persistantObjectClass) {
		this.persistantObjectClass = persistantObjectClass;
	}

	public Class getPersistantObjectClass() {
		return persistantObjectClass;
	}
	
	private  	DataSet  queryResult ;

	public static final String getAllItemsQuery(String pm_tableName , String SchemaOwner)
	{
		return "Select t.rowid, t.* from "+ SchemaOwner + "." + pm_tableName  + " t " ;
	}
	
	public final String getAllItemsQuery()
	{
		return "Select " +(this.isView()? "" : "t.rowid," )+" t.* from "+ this.getOwnerValue()+"." + this.getTableNameValue()  + " t " ;
	}
	
	
	public  String  executeSearch()
	{
		queryResult = new DataSet (new ArrayList<PersistantObject>() , null);
		try {
			DbServices dbs =  this.getDbService();
			String selectStatment =  this.getQuery().getQueryStr() ;
			selectStatment += this.getExtraFilterAsString();
			queryResult = dbs.queryForDataSet( selectStatment, this.getMapedClass());
		} catch (Exception ex) {
			this.getMessageCommnunicatorService().sendMessageToUser(ex.getMessage()) ;
			ex.printStackTrace();
		}
		return "Success";
	}

	public  String  retrieveSampleData(int pm_sampleSize)
	{
		queryResult = new DataSet (new ArrayList<PersistantObject>() , null);
		try {
			DbServices dbs =  this.getDbService();
			String selectStatment = this.getAllItemsQuery ()  + " Where  rownum <=  " + pm_sampleSize   ;
			selectStatment += this.getExtraFilterAsString() ;
			Query query = new Query(selectStatment) ; 
			query.appendAndFilter (this.getLoggedUserSecurityFilter()) ;
  
			queryResult = dbs.queryForDataSet( query.getQueryStr(), this.getMapedClass());
		} catch (Exception ex) {
			this.getMessageCommnunicatorService().sendExceptionToUser(ex);
			ex.printStackTrace();
		}
		return "Success";
	}
	
//******************* This section for custom search *****
	
	public  String  executeSearch(String customQuery)
	{
		queryResult = new DataSet (new ArrayList<PersistantObject>() , null);
		try {
			DbServices dbs =  this.getDbService();
			String selectStatment =  null;
			selectStatment += null;
			
			if(customQuery != null)
			{
				if(customQuery.startsWith("select") || customQuery.startsWith("SELECT")  || customQuery.startsWith("Select")  ) 
				{
					if(customQuery.contains("plus"))
					{
						String[] splitedCustomQuery = customQuery.split("plus");
						selectStatment = splitedCustomQuery[0] + this.getExtraFilterAsString() + " " +splitedCustomQuery[1];
						
					}else{
						selectStatment = customQuery  + " " +this.getExtraFilterAsString();
					}
				}else{
					selectStatment =  this.getQuery().getQueryStr() ;
					selectStatment += this.getExtraFilterAsString();
					selectStatment += customQuery;
				}
				
				
			}else{
				selectStatment =  this.getQuery().getQueryStr() ;
				selectStatment += this.getExtraFilterAsString();
				
			}
			
			
			
			queryResult = dbs.queryForDataSet( selectStatment, this.getMapedClass());
		} catch (Exception ex) {
			this.getMessageCommnunicatorService().sendMessageToUser(ex.getMessage()) ;
			ex.printStackTrace();
		}
		return "Success";
	}

	
	
	public  String  retrieveSampleData(String customQuery)
	{
		queryResult = new DataSet (new ArrayList<PersistantObject>() , null);
		try {
			DbServices dbs =  this.getDbService();
			String selectStatment = null;
			if(customQuery != null)
			{
				if(customQuery.contains("plus"))
				{
					String[] splitedCustomQuery = customQuery.split("plus");
					selectStatment = splitedCustomQuery[0] + " Where  rownum <=  " + this.getTableContentSampleSize() + " " + this.getExtraFilterAsString() + " and " + this.getLoggedUserSecurityFilter() +" " +splitedCustomQuery[1];
					
					
					
				}else{
					selectStatment = customQuery  + " Where  rownum <=  " + this.getTableContentSampleSize() + " " + this.getExtraFilterAsString() + " and " + this.getLoggedUserSecurityFilter() ;
					
				}
				
			}else{
				selectStatment = this.getAllItemsQuery (  )  + " Where  rownum <=  " + this.getTableContentSampleSize() +  " " + this.getExtraFilterAsString() + " and " +this.getLoggedUserSecurityFilter();
				
			}
			Query query = new Query(selectStatment) ; 
			queryResult = dbs.queryForDataSet( query.getQueryStr(), this.getMapedClass());
		} catch (Exception ex) {
			this.getMessageCommnunicatorService().sendExceptionToUser(ex);
			ex.printStackTrace();
		}
		return "Success";
	}
	
	
	//**********************************************************
	
	
	public  String  retrieveSampleData()
	{
		return this.retrieveSampleData(this.getTableContentSampleSize()) ;
	}


	public DataSet  getSearchResult()
	{
		if (queryResult == null)
		{
			retrieveSampleData(0) ;
		}
		return queryResult;
	}

	public void setSearchResult(DataSet ds ) {
		this.queryResult = ds ; 
		
	}
	
	public java.lang.String getObjectEditorXhtmlPageValue(){
		String result =  (java.lang.String) this.getAttribute ( OBJECT_EDITOR_XHTML_PAGE).getValue()  ;
		if (result == null)
		{
			result = "/templates/include/poDefaultEditor.xhtml" ;
		}
		return result ; 
	}
	
	private DataSet auditControlerDs ; 
//	private DataSet calcAuditControlarDataset()
//	{
//		DataSet result = null ; 
//		String q = TableMaintMaster.getAllItemsQuery("AUDIT_CONTROLER") + "Where TableName = '" + this.getTableName().getValue()+"'" ; 
//		try {
//			result =  this.getDbService().queryForDataSet(q, null);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return result ; 
//	}

	
	public DataSet getAuditControlerDs() {
		if (auditControlerDs == null)
		{
			try {
				auditControlerDs = this.getChildrenDataSet(AuditControler.DB_TABLE_NAME , AuditControler.class , false);//calcAuditControlarDataset() ;
			} catch (Exception e) {
				
				e.printStackTrace();
			} 
		}
		return auditControlerDs;
	}
	public String getExtraFilterAsString()
	{
		String filter = "";
		Iterator<String> it =  this.extraFilterMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			filter += " and " + key + " = '" + this.extraFilterMap.get(key)+"'";
		}
		return filter;
	}
	//...........For  Toolbar   Visability...........
	ToolbarVisabilityController toolbarVisabilityWOAddNew;
	public ToolbarVisabilityController getToolbarVisabilityWOAddNew() {
		if(toolbarVisabilityWOAddNew==null)
		{
			toolbarVisabilityWOAddNew= new ToolbarVisabilityController();
			toolbarVisabilityWOAddNew.setShowNewControl(false);
		}
	 return toolbarVisabilityWOAddNew;
	}

	public HashMap<String, String> getExtraFilterMap() {
		return extraFilterMap;
	}

	public void applyExtraFilterToPo(PersistantObject po) {
		Iterator<String> it = extraFilterMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			po.setAttributeValue(key, extraFilterMap.get(key));
		}
	}
	public BigDecimal getDefaultSequence()
	{
		BigDecimal value = null;
		String query = "select "+this.getTableNameValue()+"_SEQ.NEXTVAL NEXTVALUE from DUAL";
		List<PersistantObject> list = this.getDbService().queryForList(query);
		if(!list.isEmpty())
			value = (BigDecimal)list.get(0).getAttributeValue("NEXTVALUE");
		return value;
	}
	public synchronized BigDecimal getNextSequanceNumber(String columnName )
	{
		return this.getNextSequanceNumber(columnName , null) ;
	}
	
	@SuppressWarnings("unchecked")
	public synchronized BigDecimal getNextSequanceNumber(String columnName , HashMap<String, Object> map) {
		BigDecimal result = new BigDecimal(0) ; 
		String Result = "Result" ; 
		String whereClause=" WHERE ";   
		String andClause=" and ";
		String query ="";
		Object values[]=null;
		if (columnName!=null&&!columnName.equals(""))
		{
			
			query = "select max("+columnName+") + 1 "+Result +" From " + this.getTableNameValue() ;
			 if (map!=null)
			{
				values=new Object[map.size()]; 
				Set set = map.entrySet();
				Iterator i = set.iterator();
				
				int count=0;
				 while(i.hasNext())
				 {
			      Map.Entry me = (Map.Entry)i.next();
			      whereClause=whereClause+  me.getKey()+ "= ?"    ;
			      
			      values[count]=me.getValue();
			      count++;
			      if(i.hasNext())
			    	  whereClause=whereClause + andClause;
			     }
				 query=query+whereClause;
			}
			
		}
		ArrayList<PersistantObject> poList = this.getDbService().queryForDataSet(query ,values, null ,null).getPersistantObjectList() ;
		if (!poList.isEmpty())
		{
			result = (BigDecimal) poList.get(0).getAttributeValue(Result.toUpperCase()) ; 
			if(result.equals("null"))
			{
				result=new BigDecimal(1);
			}
		}
		
  return result ; 
	}
	private TableViewController viewControllar = new TableViewController() ;
	public void setViewControllar(TableViewController viewControllar) {
		this.viewControllar = viewControllar;
	}

	public TableViewController getViewControllar() {
		return viewControllar;
	}
	public DatasetEventListener getDataSetEventListner() {
		Class  classdatasetfreshlistner=null;
		DatasetEventListener datasetRefreshListener = null; 
		if(this.getClassdatasetfreshlistnerValue()!=null)
		{
			try {   
				  classdatasetfreshlistner=Class.forName(this.getClassdatasetfreshlistnerValue().trim()) ;
			      try {
					datasetRefreshListener  =(DatasetEventListener) classdatasetfreshlistner.newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} 
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}          
		}              
		return datasetRefreshListener ;
	}

	public void clear()
	{
		tableContentsSample = null;
		selectedTmds.clear();
		uniqueKeys =  null;
		primaryKeys = null;
		userDefienedVariables = null;
		userDefinedVariablesGroups = null;
		mandatoryFields = null;
		persisObjectController = null;
		childTobeUpdated = null;
		extraFilterMap.clear();
		sysTablesSecurityControlerForUpdateDS = null;
		ForignKeyForSysTableSecurityControler = null;
		ForignKeyForTableMaintDetails = null;
		ForignKeyForTableUserView = null;
		sysTablesSecurityControlerForDeleteDS = null;
		savedSearchDSMap.clear();
		userDefinedVarGrpDS = null;
		listOfColumnDisplayNames = null;
		sysTablesSecurityControlerForCreateDS = null;
		dataSetForPickListCreate = null;
		dataSetForPickListDELETE = null;
		dataSetForPickListUpdate = null;
		createAuditColumnsExecResult = null;
		selectedItems.clear();
		m_query = "";
		persistantObjectClass = null;
		queryResult = null;
		auditControlerDs = null;
		toolbarVisabilityWOAddNew = null;
		extraFilterMap.clear();
	}
	private ArrayList<DbForignKeyArray> allForignKeys ;

	public ArrayList<DbForignKeyArray>  getAllForignKeysArrays() {
		if (allForignKeys == null)
		{
			allForignKeys = new ArrayList<DbForignKeyArray> ();
			for (PersistantObject po: this.getChildTablesDataSet().getPersistantObjectList() )
			{
				String fkNam  = (String) po.getAttributeValue("CONSTRAINT_NAME"); 
				
				if (fkNam != null)
				allForignKeys.add(getDbForignKey(fkNam));
			}
		}
		return allForignKeys;
	}
	
	public DbForignKeyArray getDbForignKey(String fkName)
	{
		if (fkName == null) {return null;}
		DataSet fkDataSet; 
		String query = "select  ucc.column_name COLUMN_NAME,  ucc2.column_name REF_COL_NAME , ucc2.position , uc.delete_rule DELETE_RULE, ucc.table_name CHILD_TABLE , ucc.owner CHILD_OWNER"
							+"\n from all_constraints uc , all_cons_columns ucc  , all_cons_columns ucc2 "
							+"\n where uc.table_name = ucc.table_name "
							+"\n and uc.owner = ucc.owner "
							+"\n and uc.constraint_name = ucc.constraint_name "
							+"\n and ucc2.constraint_name = uc.r_constraint_name "
							+"\n and ucc2.position = ucc.position "
							+"\n and uc.owner = '"+this.getOwnerValue()+"'"
							+"\n and ucc2.table_name = '"+this.getTableNameValue()+"' "
							+"\n and uc.constraint_type = 'R' "
							+"\n and uc.validated = 'VALIDATED' "
							+"\n and uc.status = 'ENABLED' "
							+"\n and ucc.constraint_name = '"+fkName+"' "
							+"\n order by ucc.position" ;
			
		fkDataSet = this.getDbService().queryForDataSet(query, null) ;
		DbForignKey[] dbForignKey = new DbForignKey[fkDataSet.getPersistantObjectList().size()] ;
		int counter = 0 ;
		String deleteRule = "" ;
		String childOwner = null ;
		if (! fkDataSet.getPersistantObjectList().isEmpty())
		{
			PersistantObject firstRow = fkDataSet.getPersistantObjectList().get(0) ; 
			deleteRule = (String)firstRow.getAttribute("DELETE_RULE").getValue() ;
			childOwner = (String)firstRow.getAttribute("CHILD_OWNER").getValue() ;
			for (PersistantObject po : fkDataSet.getPersistantObjectList())
			{
				String childTableName = String.valueOf(po.getAttribute("CHILD_TABLE").getValue());
				String childTableOwner = String.valueOf(po.getAttribute("CHILD_OWNER").getValue());
				dbForignKey[counter] = new DbForignKey(String.valueOf(po.getAttribute("REF_COL_NAME").getValue())
										, String.valueOf(po.getAttribute("COLUMN_NAME").getValue()) , childTableName , childTableOwner);
				counter ++;
			}
		}
		DbForignKeyArray result = new DbForignKeyArray(dbForignKey , deleteRule.equals("CASCADE")) ; 
		result.setName(fkName);
		//result.setChildOwner(childOwner);
		return result ; 
	}
	
	

	private MapGenerator mapGenerator = null;
	public MapGenerator getMapGenerator() 
	{
		 if (mapGenerator == null)
		 {
			mapGenerator = this.getModuleServiceContainer().getMapGenerator();
			mapGenerator.setTableOwner(this.getOwnerValue());
			mapGenerator.setTableMaintMaster(this);
			String className = this.getMapClassNameValue() ;
			if (className != null)
			{
				String pakageNamee = className.substring(0,className.lastIndexOf(".")); 
				mapGenerator.setPackageName(pakageNamee);
			}
		}
		return mapGenerator;
	}

	private DataSet childTablesList ;
	public DataSet getChildTablesDataSet() {
		if (childTablesList == null)
		{
			 String query = this.getUsrDefVar("childTablesList").getUdvVarFieldAfterSubstitution(this, false);
			childTablesList = this.getDbService().queryForDataSet(query, null, null, null) ;
		}
		return childTablesList;
	}


	public void addNewObjectToSearchResult()
	{
		try {
			
			DataSet searchResult = this.getSearchResult() ;  
			searchResult.addNew();
			searchResult.setSelectedObject(searchResult.getCurrentItem());
			this.getViewControllar().setShowSearchResult(false);
			this.getViewControllar().setSearchOpened(false);
			
		} catch (Exception e) {
			this.getModuleServiceContainer().getMessageCommnunicatorService().sendExceptionToUser(e);
			e.printStackTrace();
		}
		
	}
	
	private boolean useDefaultObjectEditor ;
	public void setUseDefaultObjectEditor(boolean useDefaultObjectEditor) {
		this.useDefaultObjectEditor = useDefaultObjectEditor;
		if (useDefaultObjectEditor)
		{
			this.setObjectEditorXhtmlPageValue("/templates/include/poDefaultEditor.xhtml");
			this.setInstanceNameInXhtmlEditorValue("po");
			this.setMapClassNameValue("");
		}
		else 
		{
			this.getObjectEditorXhtmlPage().revert();
			this.getInstanceNameInXhtmlEditor().revert();
			this.getMapClassName().revert();
		}
	}
	
	public boolean isUseDefaultObjectEditor() {
		return useDefaultObjectEditor;
	}
	
	@Override
	public void beforeAttributeChange(Attribute pm_att) throws BaseException {
	}
	@Override
	public void afterAttributeChange(Attribute pm_att) {
		String key = pm_att.getKey();
		TableMaintMaster tmm =  (TableMaintMaster) pm_att.getParentPersistantObject() ;
		if (key.equals(TableMaintMaster.SECURITY_CONTROLLER_ID))
		{
			  tmm.refreshSecurityControler();
		}
		
		if (key.equals(TableMaintMaster.MAP_CLASS_NAME))
		{
			  tmm.refreshMapedClass();
		}

	}

	public boolean isView()
	{
		boolean result = this.getObjectTypeValue() != null && this.getObjectTypeValue().equals("VIEW") ; 
		return result;
	}

	private PersistantObject samplePo ;
	protected PersistantObject getSamplePo()
	{
		if (samplePo == null)
		{
		try {
				samplePo = this.getTableContents().createNew(false, false , false);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return samplePo ;
	}

 public ItableTriggerController getTableTriggers()
 {
	  ItableTriggerController result = null; 
		 try {
			result = (ItableTriggerController) Class.forName( this.getTriggerControllerClassValue()).newInstance();
			result.setTableMaintMaster(this); 
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  return result ; 
 }
 public boolean isUserCanCreate(String loggedUser , Connection repCon) throws Exception
 {
	 return this.isUserCan("Create", loggedUser, repCon);
 }
 
 public boolean isUserCanUpdate(String loggedUser , Connection repCon , String m_columnName , String m_rowid) throws Exception
 {
	 return this.isUserCan("Update." + m_columnName+"."+m_rowid, loggedUser, repCon);
 }
 
 public boolean isUserCanDelete(String loggedUser , Connection repCon , String m_rowid) throws Exception
 {
	 return this.isUserCan("Delete."+ m_rowid, loggedUser, repCon );
 }
 
 private boolean isUserCan(String operation , String loggedUser , Connection repCon) throws Exception
 
 {
		boolean userCanCreate = false ;
		Statement repStmt = null;
		ResultSet rs = null ;
		boolean updateOperation = operation.indexOf("Update.") != -1 ; 
		boolean deleteOperation = operation.indexOf("Delete.") != -1 ;
		String qs = null ; 
		if (updateOperation)
		{
		 String columnNameAndRowid = operation.substring(7) ;
		 int dotIndex = columnNameAndRowid.indexOf(".") ;
		 String columnName = columnNameAndRowid.substring(0, dotIndex); 
		 String rowid = columnNameAndRowid.substring(dotIndex+1) ; 
		 qs = "Select icdb.security.is_User_Can_Update" +"('"+loggedUser+"' , 'Table' , '"+this.getOwnerValue() + "." + this.getTableNameValue()+"' , '"+columnName+"' , '"+rowid+"') from dual  " ;
		}
		else if (deleteOperation)
		{
		 String rowid = operation.substring(7) ;
		 qs = "Select icdb.security.is_User_Can_Delete "  +"('"+loggedUser+"' , 'Table' , '"+this.getOwnerValue() + "." + this.getTableNameValue()+"' , '"+rowid+"' ) from dual  " ; 
		}
		else
		{ qs = "Select icdb.security.is_User_Can_" + operation +"('"+loggedUser+"' , 'Table' , '"+this.getOwnerValue() + "." + this.getTableNameValue()+"') from dual  " ; }
		
		try 
		{
			repStmt = repCon.createStatement() ;
			rs = repStmt.executeQuery(qs) ;
			System.out.println("Smart Tool Executed Query " + qs + "Succcessfully ") ;
			while (rs.next())
			{
				String canUpdateStr = rs.getString(1) ;  
				userCanCreate = ( canUpdateStr != null && canUpdateStr.equalsIgnoreCase("Y") )   ; 
			}
		}
		catch (Exception e)
		{
			e.printStackTrace() ; 
			if (rs != null ) rs.close();
			if (repStmt != null ) repStmt.close() ; 
			throw new Exception ("SmartTool Unable to Execute :\n"+ qs + "\n due to " + e.getMessage()) ;
		}
		finally 
		{
			if (rs != null ) rs.close();
			if (repStmt != null ) repStmt.close() ; 
		}
		return userCanCreate ;
 }

}