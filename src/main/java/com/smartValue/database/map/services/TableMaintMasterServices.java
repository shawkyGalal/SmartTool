package com.smartValue.database.map.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.DataSet;
import com.smartValue.database.DbServices;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.attriburteProtectionType.AttributePropertyController;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.TableMaintDetails;
import com.smartValue.database.map.TableMaintMaster;
import com.smartValue.database.map.TableUserView;
import com.smartValue.database.map.security.PersisObjectSecurityController.IPersisObjectController;
import com.smartValue.database.notifications.AttributeChangeNotifier;

public class TableMaintMasterServices extends ModuleServices
{

	private 	String tableName =null;
	private 	String tableOwner ;
	private   	TableMaintDetails currentCondition;
	private 	boolean includeAllInSearch = false;
	private 	boolean includeAllInResult = false;
	private  	DataSet  queryResult ;
	protected 	ArrayList<SelectItem> allAvailableDateExpressions;
	private 	boolean enableModuleChange;
	//private 	DataSet callerDataSet;	 
	//private     ModuleServices callerModuleService;

  
	
	@Override
	public boolean isChanged() {
		return this.getDataSet().isChanged() ; 
	}
	
	 public boolean isIncludeAllInResult() {
		  return includeAllInResult;
		 }
	 
	 public TableMaintDetails getCurrentCondition() {
	  return currentCondition;
	 }
	 public void setCurrentCondition(TableMaintDetails currentCondition) {
	  this.currentCondition = currentCondition;
	 }
	

	public TableMaintMasterServices(DbServices pm_dbsDbServices)
	{
		super (pm_dbsDbServices);
		//SortField[] fields = {new SortField("xxx", true)};
		//this.getOrder().setFields(fields);
	}


	public String getTableName() {
		//if(tableName == null)
			//tableName = "USR_DEF_VAR";
		return tableName;
	}

	public void setTableName(String pm_tableName) {
		if (pm_tableName == null || pm_tableName.equals(""))
		{
			return ;
		}
		String orignalTableName = this.getTableName();
		if (   orignalTableName == null 
				||	! orignalTableName.equalsIgnoreCase(pm_tableName)
				
				) 
		{
			this.tableName = pm_tableName.toUpperCase();
			this.loadDataSet();
		}
	}

	public String getTableOwner() {
		if (tableOwner == null)
		{
			DbServices ds = this.getDbServices() != null? this.getDbServices() : this.getDbServices();
			try {
				tableOwner = ds.queryForDataSet("Select user Result from dual", null).getPersistantObjectList().get(0).getAttribute("RESULT").toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tableOwner;
	}

	public void setTableOwner(String pm_tableOwner) {
		if (pm_tableOwner != null && ! pm_tableOwner.equalsIgnoreCase(this.tableOwner))
		{
			this.tableOwner = pm_tableOwner;
			ApplicationContext.getEnvironmentTableNames().put(this.getDbServices().getSelectedEnv(), this.calcAllUserTableNames());
		}
	}



	@Override
	public void loadDataSet()
	{

		try {
				String searchForTableQuery = TableMaintMaster.getAllItemsQuery(TableMaintMaster.DB_TABLE_NAME, TableMaintMaster.CDB_SCHEMA_OWNER) + " Where table_name = '"+this.getTableName()+"'" ;//this.getOrginalQuery() ; 
				DbServices ds = this.getDbServices() ;
				//1 Search in logged User chema
				this.setDataSet( ds
					.queryForDataSet(searchForTableQuery + "  and  owner = '"+ this.getTableOwner() + "' " 
								, TableMaintMaster.class) );
				
				if (this.getTableName() != null && this.dataSet.getPersistantObjectList().isEmpty())
				{
				// 2- if not found, Search in ICDB schema 
					this.setDataSet( ds
							.queryForDataSet(searchForTableQuery + "  and  owner =  'ICDB' " 
										, TableMaintMaster.class) );
					if (this.dataSet.getPersistantObjectList().isEmpty())
					{
						//3- if not Found Cteate a new one.. 
						this.getDataSet().addNew(false);
						TableMaintMaster tmm = (TableMaintMaster)this.getDataSet().getCurrentItem();
						tmm.setTableNameValue(this.getTableName());
						tmm.setOwnerValue(this.getTableOwner());
						tmm.setObjectName_Value(this.getTableName());
						tmm.setObjectNameValue(this.getTableName());						
						tmm.initialize();
						tmm.fetchObjectMasterInfo();
						tmm.loadDefaultColumns();
						//tmm.save();
					}
				}
				
				queryResult = new DataSet(new ArrayList<PersistantObject>() , null);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	ArrayList<SelectItem> allAvailableHtmlTypes ; 
	public ArrayList<SelectItem> getAllAvailableHtmlTypes()
	{
		if (allAvailableHtmlTypes==null)
		{
		InterfaceImplServices ims = new InterfaceImplServices(this.getDbServices());
		allAvailableHtmlTypes =  ims.getAllAvailableImpls(TableMaintDetails.HTML_TYPES_INTERFACE);
		}
		return  allAvailableHtmlTypes ;//HtmlTypes.AllAvailableHtmlTypes ;
	}
	
	ArrayList<SelectItem> allAvailableProtectTypes ;
	public ArrayList<SelectItem> getAllAvailableProtectTypes()
	{
		if (allAvailableProtectTypes==null)
		{
		InterfaceImplServices ims = new InterfaceImplServices(this.getDbServices());
		allAvailableProtectTypes =  ims.getAllAvailableImpls(AttributePropertyController.INTERFACE_NAME);
		}
		return  allAvailableProtectTypes ;//HtmlTypes.AllAvailableHtmlTypes ;
	}
		
	

	public TableMaintDetails getTableMaintDetails(BigDecimal pm_tableMaintDetailId) throws Exception
	{
		DbServices ds =  this.getDbServices();
		return  (TableMaintDetails) ds.queryForList("select t.* , t.rowid from Table_Maint_Details t where id = " + pm_tableMaintDetailId.intValue() , TableMaintDetails.class).get(0);

	}
	public  ArrayList<SelectItem> getAllAvailableDateExpressions() throws Exception
	{
		if ( this.allAvailableDateExpressions == null)
		{
		DbServices ds =  this.getDbServices();
		allAvailableDateExpressions = ds.getSelectItems("select id , description  from Date_Expressions" , true);
		}
		return allAvailableDateExpressions;
	}
	ArrayList<SelectItem>	allAvailableNotificationsTypes;
	
	public ArrayList<SelectItem> getAllAvailableNotificationsTypes() 
	{
		if (allAvailableNotificationsTypes==null)
		{
			InterfaceImplServices ims = new InterfaceImplServices(this.getDbServices());
			allAvailableNotificationsTypes =  ims.getAllAvailableImplsForPickList(AttributeChangeNotifier.class.getName());
 		}
		
		return allAvailableNotificationsTypes;
	}
	
		//TODO... Sakr...Please Rview the below functionality...
	 	public void setIncludeAllInSearch(boolean pm_includeAllInSearch) {

		  
		 }
		 public boolean isIncludeAllInSearch() {
		  return includeAllInSearch;
		 }
		 //TODO... Sakr...Please Rview the below functionality...
		 public void setIncludeAllInResult(boolean pm_includeAllInResult) {

		 }
		 
		public void setEnableModuleChange(boolean pm_enableModuleChange)
		{
			this.enableModuleChange = pm_enableModuleChange;	
		}
		
		public boolean isEnableModuleChange()
		{
			return this.enableModuleChange;
		}
		
//		public void setCallerDataSet(DataSet pm_dataSet)
//		{
//			this.callerDataSet = pm_dataSet;
//			String tableName = "";
//			
//			{	
//				tableName = pm_dataSet.getPersistantObjectList().get(0).getTable().getTableName();
//			}
//			this.setTableName(tableName);
//		}
		
//		public void informCallerDataSet() throws Exception
//		{
//			this.callerDataSet.applyFilter(this);
//			
//		}
		
		@Override
		public String getOrginalQuery()
		{
			return "select t.rowid, t.*  from "+TableMaintMaster.DB_TABLE_NAME+" t " 
			+"  where "+TableMaintMaster.OWNER+" = '"+this.getTableOwner()+"' And table_name = '" + this.getTableName() + "' " ;
			
		}
		@Override
		public boolean isCanHaveMultipleInstances()
		{
			return false;
		}
		@Override
		public Class getPersistentClass()
		{
			return TableMaintMaster.class;
		}

		private TableMaintMaster currentTable;
	
		public void setCurrentTable(TableMaintMaster currentTable) 
		{
			this.currentTable = currentTable;
			this.tableName = currentTable.getTableNameValue();
			this.tableOwner = currentTable.getOwnerValue();
		}
		public TableMaintMaster getCurrentTable() 
		{
			if (this.currentTable != null) 
			{
				return currentTable;
			}	
			DbTable dbt = new DbTable(this.getTableOwner() , this.getTableName() , this.getDbServices());
			ModuleServicesContainer msc= this.getDbServices().getModuleServiceContainer(); 
			TableMaintMaster result = msc.getListOfUsedTables().get(dbt) ;
			if (result != null)
			{
				return result;
			}
			else
			{
				DataSet ds = this.getDataSet() ;  
				if ( ds != null)
					{
					result = (TableMaintMaster) ds.getCurrentItem();
					if (result != null)
						{
							msc.getListOfUsedTables().put(dbt, result);
						}
					
					}
	
			}
			return result ; 
		}
		public TableMaintMaster getTableMaintMasterNew(DbTable dbTable)
		{			
			TableMaintMaster tableMaintMaster = null;
			if (dbTable != null && dbTable.getTableName() != null)
			{
				String query = TableMaintMaster.getAllItemsQuery("TABLE_MAINT_MASTER" , TableMaintMaster.CDB_SCHEMA_OWNER)+ " Where "+TableMaintMaster.TABLE_NAME + " = '" + dbTable.getTableName() +"'" ; 
				query+= (dbTable.getTableOwner() != null ) ?  " And " + TableMaintMaster.OWNER + "= '"+dbTable.getTableOwner()+"' " : "" ;
				List<PersistantObject> result = null;
				try {
					result = this.getDbServices().queryForDataSet(query, TableMaintMaster.class).getPersistantObjectList();
					if (result != null && !result.isEmpty() )
					 {
						tableMaintMaster = (TableMaintMaster) result.get(0);
					 }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return tableMaintMaster ; 
		}
		public TableMaintMaster getTableMaintMaster(DbTable dbTable) throws IllegalArgumentException
		{
			TableMaintMaster tableMaintMaster = null;
			if (dbTable == null)
			{
				throw new IllegalArgumentException ("dbTable Should not be null") ; 
			}
	
				ModuleServicesContainer msc = this.getDbServices().getModuleServiceContainer() ;  
				tableMaintMaster = msc.getListOfUsedTables().get(dbTable) ;
				if (tableMaintMaster == null)
				{
					tableMaintMaster = this.getTableMaintMasterNew(dbTable);
					if (tableMaintMaster == null)
					{
						// -- if Table is new and have no entry in the Table_Maint_Master 
						this.setTableOwner(dbTable.getTableOwner()) ; 
						this.setTableName(dbTable.getTableName()) ;
 
						 tableMaintMaster = this.getCurrentTable() ; 
						 try
						 {
							 tableMaintMaster.save();
							 tableMaintMaster.getTableMaintDetailss().restore();
						 }
						 catch (Exception e)
						 {
							 System.out.println("Unable to Generate Table Metadata for " + dbTable.getTableOwner() + "." + dbTable.getTableName() +  "Due To :") ; 
							e.printStackTrace() ; 
						 }
					}
		
					if ( tableMaintMaster != null && !tableMaintMaster.isNeedSave())
					{
						msc.getListOfUsedTables().put(dbTable, tableMaintMaster);
					}
				}
				tableMaintMaster.setDbService(this.getDbServices());
				return tableMaintMaster;
			
		}
		
		@Override
		public DbTable getDbTable() {
			return new DbTable(TableMaintMaster.CDB_SCHEMA_OWNER , "TABLE_MAINT_MASTER" , this.getDbServices());
		}

//		public void setCallerModuleService(ModuleServices callerModuleService) {
//			this.callerModuleService = callerModuleService;
//		}
//
//		public ModuleServices getCallerModuleService() {
//			return callerModuleService;
//		}
		
		public ArrayList<SelectItem> getLoggedUserSavedSearchsAsSelectItems() throws Exception
		{
			TableMaintMaster currentTab = this.getCurrentTable() ; 
			if (currentTab==null)
			{
				return new ArrayList<SelectItem>();
			}
			SecUsrDta loogedUser = this.getDbServices().getLoggedUser() ; //(SecUsrDta) SWAF.getSession().getOperator();
			return loogedUser.getMySavedSearchsAsSelectItems(currentTab);
		}
		
		public DataSet getLoggedUserSavedSearchsDS() throws Exception
		{
			DataSet result = null ;
		
			TableMaintMaster currentTab = this.getCurrentTable();
			if (currentTab != null)
			{
				SecUsrDta loogedUser = this.getDbServices().getLoggedUser() ;//(SecUsrDta) SWAF.getSession().getOperator();
				result =  loogedUser.getMySavedSearchsDS(this.getCurrentTable());
			}
			return result ;
		}




		
	

	

		private TableUserView tableUserViewTObeModified;
		
		public void setTableUserViewTObeModified(
				TableUserView tableUserViewTObeModified) {
			this.tableUserViewTObeModified = tableUserViewTObeModified;
		}

		public TableUserView getTableUserViewTObeModified() {
			return tableUserViewTObeModified;
		}

		public TableMaintDetails getTableMaintDetails(String pm_tableName, String pm_ColumnName) {
			DbServices ds =  this.getDbServices();
			TableMaintDetails result = null ; 
			String q= "select t.* , t.rowid from Table_Maint_Details t where " + TableMaintDetails.TABLE_NAME +" = '" + pm_tableName +"' And "
			+ TableMaintDetails.COLUMN_NAME + " = '"+pm_ColumnName+"'";
			try {
				result =   (TableMaintDetails) ds.queryForList( q, TableMaintDetails.class).get(0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result ;
		}

		ArrayList<SelectItem> allAvailablePoSecurityControllers ; 
		public ArrayList<SelectItem> getallAvailablePoSecurityControllers()
		{
			if (allAvailablePoSecurityControllers==null)
			{
			InterfaceImplServices ims = new InterfaceImplServices(this.getDbServices());
			allAvailablePoSecurityControllers =  ims.getAllAvailableImpls(IPersisObjectController.class.getName());
			}
			return  allAvailablePoSecurityControllers ;
		}

		  private ArrayList<SelectItem> calcAllUserTableNames()
		  {
		   return calcAllUserTableNames(false); 
		  }
		  public ArrayList<SelectItem> calcAllUserTableNames(boolean isNullable)
		  {
		   DbServices ds = this.getDbServices();
		   
		   String query =  "select OBJECT_name  t1, OBJECT_name  t2 "   
           +"\nfrom all_objects ao" 
           +"\n where owner = '"+ this.getTableOwner()+"' "  
           +"\n and ao.object_type in ('TABLE', 'VIEW') " 
           +"\n order by ao.object_type , t1 " ;
			   		   
		   return ds.getSelectItems(query, isNullable); 
		  }
		public ArrayList<SelectItem> getAllUserTableNames() 
		{
			if (ApplicationContext.getEnvironmentTableNames().get(this.getDbServices().getSelectedEnv()) == null) {
				ApplicationContext.getEnvironmentTableNames().put(this.getDbServices().getSelectedEnv(), calcAllUserTableNames());
			}
			return ApplicationContext.getEnvironmentTableNames().get(this.getDbServices().getSelectedEnv());
		}
		
	

		@Override
		public void afterModuleRemoved()  {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void beforeModuleRemoved() throws Exception {
			// TODO Auto-generated method stub
			
		}

	

		
		
		public void addNew()
		{
			DataSet searchResult = this.getCurrentTable().getSearchResult() ; 
			searchResult.addNewWithDefaultCompId();
			
			searchResult.setSelectedObject(searchResult.getCurrentItem());
			this.getCurrentTable().getViewControllar().setShowSearchResult(false);
			this.getCurrentTable().getViewControllar().setSearchOpened(false) ;
		}
}
