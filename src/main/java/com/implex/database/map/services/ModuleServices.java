package com.implex.database.map.services;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import oracle.sql.ROWID;

//import org.richfaces.component.UIScrollableDataTable;
//import org.richfaces.model.SortOrder;

import com.implex.database.ApplicationContext;
import com.implex.database.DataSet;
import com.implex.database.DbServices;
import com.implex.database.DbTable;
import com.implex.database.MessagesCommnuicatorService;
import com.implex.database.PersistantObject;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.SysMnu;
import com.implex.database.map.SysMsg;
import com.implex.database.map.TableMaintDetails;
import com.implex.database.map.TableMaintMaster;
import com.implex.database.map.TableUserView;
import com.sideinternational.sas.event.logging.Console;
import com.sideinternational.web.swaf.SWAF;

public abstract class ModuleServices implements Serializable{

	
	protected	DataSet dataSet; 
	private 	PersistantObject queryResultSelectedItem ;
//	private 	UIScrollableDataTable table;
//	private 	SortOrder order = new SortOrder();
	private 	SysMnu linkedSysMnuItem;
	
	private 	boolean moduleSelected;
	private 	DbServices dbServices ;
	private 	TableMaintMasterServices tableMaintMasterServices ;
	protected static final String SAVE_OR_CANCEL_CHANGES = "Please Save or Cancel Changes";
	ArrayList<LogRecord> logRecordsList = new ArrayList<LogRecord>();
	//private 	ModuleServicesContainer moduleServicesContainer ; 
	private String rowIdTobeUpdated = "" ; 
	
	
	public void setDbService(DbServices pm_dbServices)
	{
		dbServices = pm_dbServices ;
	}
	public DbServices getDbServices()
	{
		return this.dbServices;
	}
	
	public  ModuleServices(DbServices pm_dbServices)
	{
		this.dbServices = pm_dbServices ; 
	}
	
	
	/**
	 * 
	 * @param pm_rowId
	 * @return
	 */
	public PersistantObject getObjectByRowId(ROWID pm_rowId)
	{
		PersistantObject result = null;
		String query = TableMaintMaster.getAllItemsQuery(this.getDbTable().getTableName(), this.getDbTable().getTableOwner()) + " Where rowid = ?"; 
		List<PersistantObject> list = null ; 
		try {
			list = this.getDbServices().queryForDataSet(query, new Object[]{pm_rowId}, this.getPersistentClass(), null).getPersistantObjectList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list != null && ! list.isEmpty())
		{
			result = list.get(0);
		}
		return result;
	}
	
	public void setQueryResultSelectedItem(PersistantObject selectedItem) {
		this.queryResultSelectedItem = selectedItem;
		SWAF.addErrorMessage(null, "You Have Selected "+ queryResultSelectedItem);
	}

	public PersistantObject getQueryResultSelectedItem() {
		
		return queryResultSelectedItem;
	}

//	public void setTable(UIScrollableDataTable table) {
//		this.table = table;
//	}
//
//	public UIScrollableDataTable getTable() {
//		return table;
//	}

//	public void setOrder(SortOrder order) {
//		this.order = order;
//	}

//	public SortOrder getOrder() {
//		return order;
//	}
	 
	public void setDataSet(DataSet dataSet)
	{
		this.dataSet = dataSet;
	}

	public DataSet getDataSet()
	{
		if (this.dataSet == null) loadDataSet();
		return this.dataSet;
	}
/**
 * Load the default DataSet based on the given original query of this Module Service
 */
	public  void loadDataSet()
	{
		TableUserView  tableUserView = this.getDbServices().getTableUserViewForLoggedUser(this.getDbTable().getTableName()) ;
		String userViewWhereCondition = "" ;
		if ((tableUserView!= null))
		{
			userViewWhereCondition  = ( this.getOrginalQuery().toUpperCase().indexOf(" WHERE ")!=-1 ? " And " : " Where " ) + tableUserView.getViewWhereConditionValue();
		}
		try{
			setDataSet(this.getDbServices().queryForDataSet(this.getOrginalQuery()   +  userViewWhereCondition, this.getPersistentClass()));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
	}
	public abstract Class getPersistentClass();
	
	public abstract String getOrginalQuery();
	public abstract boolean isCanHaveMultipleInstances();

	public void setLinkedSysMnuItem(SysMnu sysMenuItem)
	{
		ModuleServicesContainer msc = (ModuleServicesContainer) SWAF.getManagedBean("moduleServicesContainer");
		SysMnu updetableSysMnu = msc.getSysMenuServices().getSysMnuByMnuCode((String) sysMenuItem.getMnuCode().getValue());
		this.linkedSysMnuItem = (updetableSysMnu!=null)? updetableSysMnu : sysMenuItem;
		
	}
	
	public SysMnu getLinkedSysMnuItem()
	{
		return this.linkedSysMnuItem;
	}
	
	public void setModuleSelected(boolean moduleSelected)
	{
		this.moduleSelected = moduleSelected;
	}
	public boolean isModuleSelected()
	{
		return moduleSelected;
	}

	/**
	 * This Method is Called when user click item from the List Of Active Modules
	 */
	public void applyPage()
	{   
		ModuleServicesContainer msc = (ModuleServicesContainer) SWAF.getManagedBean("moduleServicesContainer");
		SysFrmServices pis = msc.getSysFrmServices();
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession)context.getExternalContext().getSession(true);
		String pageName =  this.getLinkedSysMnuItem().getMnuPrgNameValue();
		if(pageName == null)
			pageName = ModuleServicesContainer.DEFAULT_HOME_PAGE;
		BigDecimal mnuType = this.getLinkedSysMnuItem().getMnuTypeValue(); 
		if( mnuType!=null && mnuType.intValue() == SysMnu.Menu_SQL_TYPE )
			pageName = SysFrmServices.SQL_EXECUTER_PAGE;
		String path =session.getServletContext().getRealPath(pageName) ;
		File file=new File(path);
		String pageId = ModuleServicesContainer.DEFAULT_HOME_PAGE;
		String pageTitle = ModuleServicesContainer.DEFAULT_PAGE_TITLE ;
	   
		if( file.exists())
			{
				pageId = pageName;
				pageTitle = (String) this.getLinkedSysMnuItem().getMnuTxt().getValue();
		   	}	

		try
		{
			msc.setPageId(pageId);
			msc.setPageTitle(pageTitle);
		} 
	    catch (Exception e)
		{
			e.printStackTrace();
		}
	    msc.markAllAsNotSelected();
	    this.setModuleSelected(true);
	    if ( pageId.equalsIgnoreCase(SysFrmServices.GLOBAL_PAGE_PATH)  ) 
	    	{
	    		this.loadDataSet();
	    	}
	}
	
	
	public abstract  boolean isChanged() ; 
		
	
	public void setAllFieldsToNull()
	{
		
		this.dataSet = null;
		Field[] fields =  this.getClass().getDeclaredFields();
		Field[] parentFields =  super.getClass().getDeclaredFields();
		this.setToNull(fields);
		this.setToNull(parentFields);
		
		
	}
	private void setToNull(Field[] fields)
	{
		for (int i = 0; i<fields.length ; i++)
		{
			 try {
				 fields[i].set(this, null);
				} catch (IllegalArgumentException e) {
				
				e.printStackTrace();
				} catch (IllegalAccessException e) {
					
					Console.log(e.getMessage(), this.getClass());
				}
		}
	}
	
	public ArrayList< DataSet> getAllDataSets() throws SecurityException
	{
		ArrayList< DataSet> result = new ArrayList< DataSet>();
		
		java.lang.reflect.Method[] methods =  this.getClass().getMethods();
		for (int i = 0; i<methods.length ; i++)
		{
			String returnTypeClassName = methods[i].getReturnType().getName();
			String dataSetClassName = DataSet.class.getName();
			if (returnTypeClassName.equalsIgnoreCase(dataSetClassName))	
			{
				try{
				result.add ( (DataSet) methods[i].invoke(this) );
				}
				catch (Exception e) {
					
				}
			}
		}
		return result;
	}
	
	

	/** 
	 * 
	 * @return No. of All Persistent Objects included in this ModulesServices in addition to all children
	 */
	public int getAllocatedSize()
	{
		int result = 0;
		ArrayList<DataSet> dss = this.getAllDataSets();
		for (int i=0; i< dss.size(); i++)
		{
			DataSet ds = dss.get(i);
			if (ds != null && ds.getPersistantObjectList() != null)
			{
				result += ds.getPersistantObjectList().size();
				for (int j=0 ; j< ds.getPersistantObjectList().size() ; j++)
				{
					PersistantObject po = ds.getPersistantObjectList().get(j);
					result += (po != null)? po.getAllChildrenCount() : 0 ;
				}
			}
		}
		return result;
	}
	
	public abstract  DbTable getDbTable();
	
	public TableMaintMasterServices getTableMaintMasterServices()
	{
		if (tableMaintMasterServices == null)
		{
			tableMaintMasterServices = new TableMaintMasterServices(this.dbServices);
			tableMaintMasterServices.setDbService(this.getDbServices());
			String tabName = this.getDbTable() != null ? this.getDbTable().getTableName() : this.getLinkedSysMnuItem().getVpdatabasetableValue();
			tableMaintMasterServices.setTableName(tabName);
			tableMaintMasterServices.setTableOwner((this.getDbTable() != null)? this.getDbTable().getTableOwner(): null);
			tableMaintMasterServices.getCurrentTable().setPersistantObjectClass(this.getPersistentClass());
			//tableMaintMasterServices.setCallerModuleService(this);
		}
		return tableMaintMasterServices ;
	}
	
	/**
	 * 
	 * @param pm_rowId
	 * @return
	 */

	public PersistantObject getObjectByRowId(String pm_rowId) 
	{
		PersistantObject result = null;
		List<PersistantObject> list = this.getDataSetWithCondition().getPersistantObjectList();
		if (list != null && !list.isEmpty()) 
		{
			result = list.get(0);
		}
		return result;
	}

	private DataSet dataSetWithCondition = null;
	public DataSet getDataSetWithCondition() 
	{
		return dataSetWithCondition;
	}
	public void setDataSetWithCondition(DataSet ds)
	{
		if(!this.isDataSetHasChanges())
		{	
			dataSetWithCondition = ds;
			this.getTableMaintMasterServices().getCurrentTable().getSearchResult().clear();
		}
	}
	private void setDataSetWithRowIdCondition(String pm_rowId) 
	{
		setDataSetWithCondition( pm_rowId, true);
	}
	public void setDataSetWithCondition(String pm_whereClause, boolean isRowId) 
	{
		String whereClause = (isRowId) ? " Where rowid = '"	+ pm_whereClause + "'"  : pm_whereClause;
		String extraFilter = this.getTableMaintMasterServices().getCurrentTable().getExtraFilterAsString();
		whereClause += extraFilter;
		dataSetWithCondition = this.getDataSetWithCondition(whereClause);
	}
	public DataSet getDataSetWithCondition(String whereCaluse)
	{
		DataSet result = null ; 
		String query = TableMaintMaster.getAllItemsQuery(this.getTableName() , this.getDbTable().getTableOwner()) + whereCaluse ;
		try {
			result = this.getDbServices().queryForDataSet(query,this.getPersistentClass());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result ; 
	}
	
	private String getTableName()
	{
		return this.getDbTable() != null? this.getDbTable().getTableName() : this.getDbServices().getModuleServiceContainer().getTableMaintServices().getTableName();
	}

	public void addNewObject()
	{
		if(!isDataSetHasChanges())
		{
			setDataSetWithCondition( "", true);
			this.getDataSetWithCondition().addNewWithDefaultCompId();
			this.setDefaultValues();
		}
	}
	private void setDefaultValues()
	{
		
		SecUsrDta secUsrData = this.getDbServices().getLoggedUser();
			
		PersistantObject newPo = this.getDataSetWithCondition().getCurrentItem();
		
	    HashMap usrDefvalueMap = secUsrData.getUsrDefaultValuesMap();
		if(usrDefvalueMap != null  && !usrDefvalueMap.isEmpty())
		{
			TableMaintDetails tmd = null;
			Object defaultValue = null;
			
			for(PersistantObject po : newPo.getTableMaintMaster().getTableMaintDetailss().getPersistantObjectList())
			{
				tmd = (TableMaintDetails) po;
				if(usrDefvalueMap.get(tmd.getTableNameValue()) != null && usrDefvalueMap.get(tmd.getTableNameValue()).toString().contains(tmd.getColumnNameValue()))
				{
					defaultValue = secUsrData.getUsrDefaultValueByTableAndColumnNames(tmd.getTableNameValue(), tmd.getColumnNameValue(), tmd.getDataTypeValue());
					if(defaultValue != null && !defaultValue.toString().equals("null"))
					{
						newPo.getAttribute(tmd.getColumnNameValue()).setValue(defaultValue);
					}
				}
			}
		}
	}
	public void setRowIdTobeUpdated(String pm_rowIdTobeUpdated) {
		if(!this.isDataSetHasChanges())
		{	
			this.rowIdTobeUpdated = pm_rowIdTobeUpdated;
			this.setDataSetWithRowIdCondition(this.rowIdTobeUpdated);
			this.getTableMaintMasterServices().getCurrentTable().getSearchResult().clear();
		}
	}
	protected MessagesCommnuicatorService getMessageCommnunicatorService()
	{
		return this.getDbServices().getModuleServiceContainer().getMessageCommnunicatorService();
	}
	private boolean isDataSetHasChanges()
	{
		boolean result = false;
		if (dataSetWithCondition != null && dataSetWithCondition.isChanged())
		{			
			SysMsgServices sysmsgservices=this.getDbServices().getModuleServiceContainer().getSysMsgServices();
			SysMsg sysMsg = sysmsgservices.getMsgPleaseSaveOrCancelChanges();
			 
			this.getMessageCommnunicatorService().clear();
			this.getMessageCommnunicatorService().sendMessageToUser(sysMsg);
			result = true;
		}
		TableMaintMaster tmm = this.getTableMaintMasterServices().getCurrentTable() ; 
		tmm.getViewControllar().setShowSearchResult(false);
		tmm.getViewControllar().setSearchOpened(false) ;
		return result;
	}
	
	public String getRowIdTobeUpdated() {
		return rowIdTobeUpdated;
	}
	public void createLike(PersistantObject po)
	{
		String rowid = po.createLikeMe();
		this.setRowIdTobeUpdated(rowid);
	}
	public void createNewLike(String pm_rowIdTobeUpdated)
	{
		this.setRowIdTobeUpdated(pm_rowIdTobeUpdated);
		try {
			this.getDataSetWithCondition().addNew(true);
		} catch (Exception e) {
			this.getMessageCommnunicatorService().sendMessageToUser("Couldn't create new object like current due to "+e.getMessage());
			e.printStackTrace();
		}
	}
	public void createNewLikeWithDetails(String pm_rowIdTobeUpdated)
	{
		this.setRowIdTobeUpdated(pm_rowIdTobeUpdated);
		try {
			this.getDataSetWithCondition().addNewWithDetails();
		} catch (Exception e) {
			this.getMessageCommnunicatorService().sendMessageToUser("Couldn't create new object like current with details due to "+e.getMessage());
			e.printStackTrace();
		}
	}
	public ArrayList<LogRecord> getLogRecordsList() {
		return logRecordsList;
	}
	  
	public void addNotifactionLogRecord(String msg)
	{
		LogRecord lr = new LogRecord(Level.INFO , msg);
	    logRecordsList.add(lr);
	}
	  
	public void addSevereLogRecord(String msg)
	{
		LogRecord lr = new LogRecord(Level.SEVERE , msg);
	    logRecordsList.add(lr);
	}
	  
	public void clearLogRecordsList()
	{
	   this.logRecordsList = new ArrayList<LogRecord>();
	}
	public Level getSeverLevelValue()
	{
		return Level.SEVERE;
	}
	
	
	public String getLevelStyle (Level pm_level)
	{ 
		String levelColor = "green";
		if(pm_level == Level.WARNING){
			levelColor = "yellow";
		}else if(pm_level == Level.SEVERE){
			levelColor = "red";
		}
		return levelColor;
	}
	
	
	public abstract void afterModuleRemoved()  ;
	public abstract void beforeModuleRemoved() throws Exception ;
	public void  removeFromLastVisted( ) 
	{
		//************ Start Free memory ******
		//Free the attributes values
//		if ( this.getDataSetWithCondition() != null )
//		{
//			ArrayList<Attribute> attList = this.getDataSetWithCondition().getCurrentItem().getAttributesList();
//			for(Attribute att : attList)
//			{
//				att.clear();
//			}
//			
//			//Free the po values
//			this.getDataSetWithCondition().getCurrentItem().clear();//PersistentObject clearness
//			
////			//Free the dsWithCondition
//			this.getDataSetWithCondition().clear();
//		}
//		//************ End Free memory ********
		
		ModuleServicesContainer msc =  this.getDbServices().getModuleServiceContainer();  //this.getModuleServicesContainer() ; 
		this.getLinkedSysMnuItem().clearQueryExeuterResults();
		try{
		this.beforeModuleRemoved();
		}
		catch (Exception e ) 
		{
			this.applyPage();
			msc.getMessageCommnunicatorService().sendMessageToUser("Unable To Close Module Due To " + e.getMessage());		
			return ;
		}
		
		String modulesID = this.getClass().getName();		
		if (this.isChanged())
		{
			//Go to  the Module You need to Close before print message 
			this.applyPage();
			SysMsgServices sysmsgservices=this.getDbServices().getModuleServiceContainer().getSysMsgServices();
			SysMsg sysMsg = sysmsgservices.getMsgPleaseSaveOrCancelChanges();
			msc.getMessageCommnunicatorService().sendMessageToUser(sysMsg);		
			return ;
		}
		//this.setAllFieldsToNull();
		
		msc.getModuleServiceList(modulesID).remove(this);
		if (! (this instanceof TableMaintMasterServices))
		{
			try 
			{ 
				this.getTableMaintMasterServices().getCurrentTable().setSearchResult(null);
			}
			catch (Exception e) {}
		}
		Stack<ModuleServices> lastVistedModuleservicesStak = msc.getLastVisitedModuleServicesStack() ; 
		lastVistedModuleservicesStak.remove(this);
		String pageId = 	ModuleServicesContainer.DEFAULT_HOME_PAGE;
		String pageTitle =  ModuleServicesContainer.DEFAULT_PAGE_TITLE ;
		
		if(!lastVistedModuleservicesStak.isEmpty()){
			try{
				SysMnu linkedSysMnu = (lastVistedModuleservicesStak.peek()).getLinkedSysMnuItem() ;
				pageId = 	((String)linkedSysMnu.getMnuPrgName().getValue());
				pageTitle = ((String)linkedSysMnu.getMnuTxt().getValue());
				 
				
				if (linkedSysMnu.getMnuTypeValue().equals(SysMnu.VIRTUAL_PAGE_TYPE) ) //if(tableName != null) // if coming opened module service is virtual page
				{ 
					String tableName = (String) linkedSysMnu.getVpdatabasetable().getValue() ;
					msc.getTableMaintServices().setTableName(tableName);
				}
			}
			
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		msc.setPageId(pageId);
		msc.setPageTitle(pageTitle);
		this.reset();
		
		
		this.afterModuleRemoved();
//		String className = this.getClass().getName();
//		if(className.equals("com.implex.erpins.underwriting.map.services.UwTrnPolicyServices"))
//		{
//			ApplicationContext.getModuleServicesContainer().getContainer().remove(className);
//		}
		//Free the TMM
//		this.getTableMaintMasterServices().getCurrentTable().clear();//TMM clearness
		
	}

	
	protected void reset()
	{
		this.queryResultSelectedItem = null;
		this.dataSet = null; 
		this.dataSetWithCondition = null ; 
		this.logRecordsList.clear();
		this.moduleSelected = false ;
		this.rowIdTobeUpdated = "" ;
		this.tableMaintMasterServices = null;
	}
	
	public void setShowSearchResult(boolean pm_showSearchResult) {
		this.getTableMaintMasterServices().getCurrentTable().getViewControllar().setShowSearchResult(pm_showSearchResult);
	}
	public boolean isShowSearchResult() {
		return this.getTableMaintMasterServices().getCurrentTable().getViewControllar().isShowSearchResult();
	}

	
}
