package com.implex.database.map.services;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import Support.XMLConfigFileReader;

import com.implex.AbstractSession;
import com.implex.components.model.tree.TreeBean;
import com.implex.database.ApplicationContext;
import com.implex.database.Attribute;
import com.implex.database.DataSet;
import com.implex.database.DbServiceFactory;
import com.implex.database.DbServices;
import com.implex.database.DbTable;
import com.implex.database.MessagesCommnuicatorService;
import com.implex.database.PersistantObject;
import com.implex.database.ToolbarVisabilityController;
import com.implex.database.map.DataTableDisplayProperities;
import com.implex.database.map.SavedSearch;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.SysMnu;
import com.implex.database.map.SysMsg;
import com.implex.database.map.SysPoAttachments;
import com.implex.database.map.TableMaintDetails;
import com.implex.database.map.TableMaintMaster;
import com.implex.database.map.UsrDefVar;
import com.implex.database.mapGeneration.MapGenerator;
import com.implex.jsf.components.DynamicPanelFactory;
import com.implex.listeners.ApplicationContextListener;
import com.sideinternational.sas.util.Util;


public abstract class ModuleServicesContainer implements HttpSessionBindingListener , Serializable
{	
	private HashMap<String , ArrayList<ModuleServices>  > moduleServicesMap = new HashMap<String ,  ArrayList<ModuleServices>>();
	private HashMap<String , Boolean > newItemAddedByTree = new HashMap<String , Boolean>();
	private boolean showRichFaceDemo = false;

	private ModuleServices selectedModuleFormLastUsedList = null;
	private ModuleServices currentActiveModule ; 
	private DynamicPanelFactory dynamicPanelFactory = null ;
	private Stack<ModuleServices> lastVisitedModuleServicesStack = new Stack<ModuleServices>(); 
	private DataSet dataSetToBeExplored ; 
	
	private TableMaintDetails tmdToBeModified = null;
	private Boolean loggedUserIsAdmin = null;
	private HashMap<String , ModuleServices> container = new HashMap<String , ModuleServices>();
	private List<SelectItem> listOfLoggedUserProperties = null;


	protected DbServices dbs = DbServiceFactory.create();

	//-----------System Services -----------------
	private SystemMenusServices sysMenuServices;
	private SysMsgServices sysMsgServices ;
	private SecUserDataService secUserDataService ;	
	private MessagesCommnuicatorService messageCommnunicatorService ;
	private static UsrDefVarServices usrDefVarServices = null ;
	private static SysFrmServices sysFrmServices = null ; 
	private SqlBoundVarsServices sqlBoundVarsServices;
	private static SysParamsServices sysParamsServices;
	private TasksServices tasksServices ; 
	private SecUserCommunicationMsgsServices secUserCommunicationMsgsServices;
	private DataTableDisplayProperServices dataTableDeDisplayProperServices ; 
	private ActionNeededListServices actionNeededListServices ;
	private TableMaintMasterServices tableMinatServices ;
	private MasSysCodeLookupsServices masSysCodeLookups = null;
	private MainCodesService  mainCodesService = null;
	
	private SysPoAttachments sysPoAttachments ;

	private boolean debugMode = false ; 
	

	public MessagesCommnuicatorService getMessageCommnunicatorService()
	{
		return messageCommnunicatorService;
	}	

	public  ModuleServicesContainer(String SelectedEnv , int selectedLang) throws Exception
	{
		dbs.initialize(SelectedEnv , selectedLang);
		this.setDbServices(dbs);
		this.initialize();
		this.loadAllModules();
		dbs.setModuleServicesContainer(this);
	}
	
	public  ModuleServicesContainer() throws Exception
	{
		dbs.initializeForWeb();
		this.setDbServices(dbs);
		this.initialize();
		dbs.setModuleServicesContainer(this);
	}
	private HashMap<String , ArrayList<ModuleServices> > getModuleServicesMap()
	{
		return moduleServicesMap;
	}

	
	public int getUserUsedMemory()
	{
		//TODO.... loop for all modules...
		return 9;
	}


	public ArrayList<ModuleServices> getLastVisitedModules(){
		
		ArrayList<ModuleServices> modules = new ArrayList<ModuleServices>();
		for (int i = lastVisitedModuleServicesStack.size() - 1; i >= 0; i--) {
			modules.add((ModuleServices) lastVisitedModuleServicesStack.get(i));
		}
		return modules;
	}
	
	protected ArrayList<ModuleServices> getModuleServiceList(String pm_moduleKey)
	{
		if (!this.getModuleServicesMap().containsKey(pm_moduleKey))
		{
			this.getModuleServicesMap().put(pm_moduleKey, new ArrayList<ModuleServices>() );
		}
		return this.getModuleServicesMap().get(pm_moduleKey);
	}
	

	public ModuleServices getModuleServices(String moduleKey , int instanceIndex)
	{  
		return this.getModuleServiceList(moduleKey).get(instanceIndex);	
	}
	

	public ModuleServices getModuleServices(Class pm_class) throws Exception
	{
		
		ModuleServices ms = null;
		if (this.getSelectedModuleFormLastUsedList()!=null ) //  need a specific Instance...from the Last Used List
		{
			ms =  getSelectedModuleFormLastUsedList() ;
			setCurrentActiveModule(ms);
			//getTableMaintServices().setCallerModuleService(ms);
			this.selectedModuleFormLastUsedList = null;
		}
		else if ( isNewOneAddedByTreeRequest(pm_class))// create a new instance  it comes from the main Tree..
		{
			int index = this.getModuleServiceList(pm_class.getName()).size()-1;
			ms = this.getModuleServiceList(pm_class.getName()).get(index);
			setCurrentActiveModule(ms);
			//getTableMaintServices().setCallerModuleService(ms);
			newOneAlreadyUsed(pm_class);
		}
		
		if (ms == null)  // any one else request this type of Service..
		{
			ArrayList<ModuleServices> setvicePool = this.getModuleServiceList(pm_class.getName());
			if ( setvicePool.isEmpty()) 
				{
					ModuleServices newObject =  (ModuleServices) Util.instantiateClass(pm_class);
					ms = newObject;
					this.getModuleServiceList(ms.getClass().getName()).add(ms);
				}
			else  
			{
				ms = (ModuleServices) setvicePool.get(0);
			}
		}
		return ms;
	
	}
	
	

	private void newOneAlreadyUsed(Class pm_class)
	{
		this.newItemAddedByTree.put(pm_class.getName(), Boolean.valueOf(false));
	}

	/**
	 * Checks is a new instance added to the pool of the given type
	 * @param pm_class
	 * @return
	 */
	private boolean isNewOneAddedByTreeRequest(Class pm_class)
	{
		if ( !this.newItemAddedByTree.containsKey(pm_class.getName()) )
			this.newItemAddedByTree.put(pm_class.getName() , Boolean.valueOf(false));
		return this.newItemAddedByTree.get(pm_class.getName());
	}

	public ArrayList<ModuleServices> getAllModules()
	{
		ArrayList<ModuleServices> all = new ArrayList<ModuleServices>();
		Iterator<String> it = this.getModuleServicesMap().keySet().iterator();
		while(it.hasNext())
		{
			ArrayList<ModuleServices> x = this.getModuleServicesMap().get(it.next());
			Iterator<ModuleServices> it2 = x.iterator();
			while (it2.hasNext())
			{
				ModuleServices ms = (ModuleServices)it2.next();
				if (ms.getLinkedSysMnuItem()!=null)
				all.add(ms );
			}
		}
		return all;
	}
	

	public void setSelectedModuleFormLastUsedList(ModuleServices pm_moduleServicesTobeReturned)
	{
		this.selectedModuleFormLastUsedList = pm_moduleServicesTobeReturned;
		this.setCurrentActiveModule(pm_moduleServicesTobeReturned);
		int mnuType = this.selectedModuleFormLastUsedList.getLinkedSysMnuItem().getMnuTypeValue().intValue(); 
		if(mnuType == SysMnu.VIRTUAL_PAGE_TYPE)
		{
			this.getTableMaintServices().setTableName(this.selectedModuleFormLastUsedList.getLinkedSysMnuItem().getVpdatabasetableValue());			
		}
		String tableName = this.selectedModuleFormLastUsedList.getDbTable() != null?this.selectedModuleFormLastUsedList.getDbTable().getTableName() : this.getTableMaintServices().getTableName();
		this.getUsrDefVarServices().setSelectedTableName(tableName);
		if(selectedModuleFormLastUsedList.getDbTable() != null && selectedModuleFormLastUsedList.getDbTable().getTableName().equals(UsrDefVar.DB_TABLE_NAME))
			this.getUsrDefVarServices().setDisabledTablesNamesList(false);
		else
			this.getUsrDefVarServices().setDisabledTablesNamesList(true);
	}

	public ModuleServices getSelectedModuleFormLastUsedList()
	{
		return selectedModuleFormLastUsedList;
	}

	public void setCurrentActiveModule(ModuleServices currentActiveModule)
	{
		this.currentActiveModule = currentActiveModule;
	}

	public ModuleServices getCurrentActiveModule()
	{
		return currentActiveModule;
	}


	public int getLoggedUserMaxAllowedModules()
	{
		return this.getDbServices().getLoggedUser().getUsrSessionValue().intValue();
	}

	public void addNewModuleServiceByTreeRequest(ModuleServices ms)
	{
		this.getModuleServiceList(ms.getClass().getName()).add(ms);	
		newItemAddedByTree.put(ms.getClass().getName(), Boolean.valueOf(true) );
	}

	/**
	 * Checks if the pool contains any instances of given type...
	 * @param ms
	 * @return
	 */

	public boolean isPoolEmpty(ModuleServices ms)
	{
		return this.getModuleServiceList(ms.getClass().getName()).isEmpty();
	}

	public void setLastVisitedModuleServicesStack(
			Stack<ModuleServices> lastVisitedModuleServicesStack) {
		this.lastVisitedModuleServicesStack = lastVisitedModuleServicesStack;
	}

	public Stack<ModuleServices> getLastVisitedModuleServicesStack() {
		return lastVisitedModuleServicesStack;
	}
	public void markAllAsNotSelected()
	{
		Iterator<ModuleServices> it =  this.getLastVisitedModuleServicesStack().iterator();
		while (it.hasNext())
		{
			it.next().setModuleSelected(false);
		}
	}

	public void setDataSetToBeExplored(DataSet dataSetToBeExplored)
	{
		this.dataSetToBeExplored = dataSetToBeExplored;
	}

	public DataSet getDataSetToBeExplored()
	{
		return dataSetToBeExplored;
	}
	
	public ToolbarVisabilityController getFullOptionVisabilityControler()
	{
		return new ToolbarVisabilityController();
	}
	
	ToolbarVisabilityController toolBarForList ; 
	
	public ToolbarVisabilityController getToolBarForList()
	{
		
		return toolBarForList ; 
	}
	public ToolbarVisabilityController getReadOnlyVisabilityControler()
	{
		ToolbarVisabilityController readOnly = new ToolbarVisabilityController();
		readOnly.setShowDeleteControl(false);
		readOnly.setShowDsExplorer(false);
		readOnly.setShowFormMaintControl(false);
		readOnly.setShowNewControl(false);
		readOnly.setShowSaveAllControl(false);
		readOnly.setShowSaveControl(false);
		readOnly.setShowSearchControl(false);
		readOnly.setShowCancelChanges(false);
		return readOnly;
	}

	public void toggleShowRichFaceDemo()
	{
		this.setShowRichFaceDemo(!showRichFaceDemo);
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext extContext = context.getExternalContext();
		String url = extContext.encodeActionURL(extContext
				.getRequestContextPath()
				+ "/templates/include/ContentArea.jsf");
		try {
			extContext.redirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setShowRichFaceDemo(boolean showRichFaceDemo)
	{
		this.showRichFaceDemo = showRichFaceDemo;
	}


	public boolean isShowRichFaceDemo()
	{
		return showRichFaceDemo;
	}

	public void setDynamicPanelFactory(DynamicPanelFactory dynamicPanelFactory)
	{
		this.dynamicPanelFactory = dynamicPanelFactory;
	}

	public DynamicPanelFactory getDynamicPanelFactory()
	{
		if (dynamicPanelFactory == null)
		{
			dynamicPanelFactory = new DynamicPanelFactory();
		}
		return dynamicPanelFactory;
	}
	public void setDbServices(DbServices dbs)
	{
		this.dbs = dbs;
		this.loadBasicModules();
		this.loadAllModules();
	}
	public DbServices getDbServices()
	{
		return dbs;
	}

	
	public void setTmdToBeModified(TableMaintDetails tmdToBeModified)
	{
		this.tmdToBeModified = tmdToBeModified;
	}

	public abstract void loadAllModules();
	public void loadBasicModules()
	{
		sysMenuServices = new SystemMenusServices(this.getDbServices());
		this.addModule(sysMenuServices);
		
		secUserDataService = new SecUserDataService(this.getDbServices());
		this.addModule(secUserDataService);
		
		sysMsgServices = new SysMsgServices(this.getDbServices());
		this.addModule(sysMsgServices);
		
		tableMinatServices = new TableMaintMasterServices(this.getDbServices());
		this.addModule(tableMinatServices);
		
		sysFrmServices = new SysFrmServices(this.getDbServices());
		this.addModule(sysFrmServices);
		
		usrDefVarServices = new UsrDefVarServices(this.getDbServices());
		this.addModule(usrDefVarServices);
		
		sysParamsServices = new SysParamsServices(this.getDbServices());
		this.addModule(sysParamsServices);
		
		sqlBoundVarsServices = new SqlBoundVarsServices(this.getDbServices());
		this.addModule(sysParamsServices);
		
		sqlBoundVarsServices = new SqlBoundVarsServices(this.getDbServices());
		this.addModule(sqlBoundVarsServices);
		
		tasksServices = new TasksServices(this.getDbServices());
		this.addModule(tasksServices);
		
		secUserCommunicationMsgsServices = new SecUserCommunicationMsgsServices(this.getDbServices());
		this.addModule(secUserCommunicationMsgsServices);
		
		masSysCodeLookups = new MasSysCodeLookupsServices(this.getDbServices());
		this.addModule(masSysCodeLookups);
		
		
		messageCommnunicatorService = new MessagesCommnuicatorService(this.getDbServices());
		this.dataTableDeDisplayProperServices = new DataTableDisplayProperServices(this.getDbServices());
		this.addModule(dataTableDeDisplayProperServices);

		this.actionNeededListServices = new ActionNeededListServices(this.getDbServices());
		this.addModule(actionNeededListServices);
		
	     mainCodesService  = new MainCodesService(this.getDbServices());
	     this.addModule(mainCodesService);

	}
	private MapGenerator mapGenerator = null;
	public MapGenerator getMapGenerator() 
	{
		 if (mapGenerator == null)
		 {
			mapGenerator = new MapGenerator(this.getDbServices());
			String appEnvirmoment = this.getUserSession().getSelectedEnv();
			mapGenerator.setAppEnvironment(appEnvirmoment);
		 }
		return mapGenerator;
	}
	protected void addModule(ModuleServices ms)
	{
		this.addToContainer(ms);
	}
	public SystemMenusServices getSysMenuServices() 
	{ 	
		return sysMenuServices ;
	}

	public SecUserDataService getSecUserDataService()  
	{
		return secUserDataService;
	}
	
	public SysMsgServices getSysMsgServices()  
	{
		return sysMsgServices;
	}
	
	public TableMaintDetails getTmdToBeModified()
	{
		return tmdToBeModified;
	}


	public TableMaintMasterServices getTableMaintServices()  
	{
		return tableMinatServices;
	}
	
	public SysFrmServices getSysFrmServices() 
	{
		return sysFrmServices ;
	}
		
	public UsrDefVarServices getUsrDefVarServices() 
	{
		return usrDefVarServices ;
	}

	public boolean isLoggedUserIsAdmin()
	{
		if (this.loggedUserIsAdmin == null)
		{
			SecUsrDta loggedUser = this.getDbServices().getLoggedUser() ;
			if  (loggedUser != null) 
				loggedUserIsAdmin = loggedUser.isSmartToolAdmin();
			else return false ; 
		}
		return loggedUserIsAdmin;
	}
	public SecUsrDta getCurrentLoggedUser()
	{
		return this.getDbServices().getLoggedUser() ; 
	}
	public SysParamsServices getSysParamsServices() 
	{	
		return sysParamsServices ;
	}


	
	public List <SecUsrDta> getLoggedUsers()
	{
		return ApplicationContext.getListOfLoggedUsers();
	}
	
	
	public SqlBoundVarsServices getSqlBoundVarsServices() 
	{
		return sqlBoundVarsServices ;
	}
	
	protected void addToContainer(ModuleServices ms )
	{
		this.container.put(ms.getClass().getName(), ms);
	}
	
	public ModuleServices searchForServices(String className)
	{
		return container.get(className);
		
	}
	
	public HashMap<String, ModuleServices> getContainer()
	{
		return container ; 
	}
	
	private ArrayList<SelectItem> allOwnerNames = null;
	public ArrayList<SelectItem> getAllOwnerNames()
	{
		if(allOwnerNames == null)
		{
			String query = "select userName aname , username ename  from all_users" ;
			allOwnerNames = this.getDbServices().getSelectItems(query, false) ;
		}
		return allOwnerNames;
	}
	

	

	public TasksServices getTasksServices() 
	{
		return tasksServices;
	}
	
	public List<SelectItem> getListOfLoggedUserProperties() {
		if(listOfLoggedUserProperties  == null)
		{
			listOfLoggedUserProperties = new ArrayList<SelectItem>();
			TableMaintMaster tmm = this.getDbServices().getLoggedUser().getTableMaintMaster();
			for (PersistantObject po : tmm.getTableMaintDetailss().getPersistantObjectList()) {
				TableMaintDetails tmd = (TableMaintDetails) po;
				listOfLoggedUserProperties.add(new SelectItem(PersistantObject.LOGGED_USER_PROPERTY_DELIMTER + tmd.getColumnNameValue(), (String) tmd.getDisplayNameAutoLang().getValue()));				
			}			
		}
		return listOfLoggedUserProperties;
	}	

	public SecUserCommunicationMsgsServices getSecUserCommunicationMsgsServices() 
	{
		return secUserCommunicationMsgsServices;
	}

	public DataTableDisplayProperServices getDataTableDisplayProperitiesServices()
	{
		return this.dataTableDeDisplayProperServices ;
	}
	
	public MasSysCodeLookupsServices getMasSysCodeLookupsService() 
	{
		return masSysCodeLookups;
	}

	public MainCodesService getMainCodesService()
	{
		return mainCodesService;
	}


	private boolean maintainForm = false;
	public final static String DEFAULT_HOME_PAGE= "/SWAF/jsp/ModulesMaintenance.xhtml";
	public final static String DEFAULT_PAGE_TITLE= "Welcome";
	private String pageId = DEFAULT_HOME_PAGE;
	
	public void setMaintainForm(boolean maintainForm) {
		this.maintainForm = maintainForm;
		this.getSysFrmServices().setEditObjectTxt(maintainForm);
	}

	public boolean isMaintainForm() {
		return maintainForm;
	}
	String maintainFormLabel ;
	public String getMaintainFormLabel() {
		if (maintainForm == true)
			maintainFormLabel = this.getSysFrmServices().getFrmObjTxt("/templates/include/toolBar.xhtml","btnDisableMaintainForm");
		else
			maintainFormLabel = this.getSysFrmServices().getFrmObjTxt("/templates/include/toolBar.xhtml","btnEnableMaintainForm");
		return maintainFormLabel;
	}
	

	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pm_pageId) {
		String path = TreeBean.getContextPath(pm_pageId) ;
		if (path!=null)
		{
			File file = new File(path);
			if( file.exists())
				this.pageId = pm_pageId;
		}
		else
			this.pageId = DEFAULT_HOME_PAGE;
	}
	
	private String pageTitle = DEFAULT_PAGE_TITLE;
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	public String getPageTitle() {
		return pageTitle;
	}
	
	
	public void setDataTableDisplayPropertiesToBeUpdated(
			DataTableDisplayProperities dataTableDisplayPropertiesToBeUpdated) {
		this.dataTableDisplayPropertiesToBeUpdated = dataTableDisplayPropertiesToBeUpdated;
	}
	public DataTableDisplayProperities getDataTableDisplayPropertiesToBeUpdated() {
		return dataTableDisplayPropertiesToBeUpdated;
	}
	
	public ActionNeededListServices getActionNeededListServices()
	{
		return actionNeededListServices ;
	}
	
	private DataTableDisplayProperities dataTableDisplayPropertiesToBeUpdated ; 
	
	private void initialize ()
	{
			toolBarForList = new ToolbarVisabilityController();
			toolBarForList.setShowDeleteControl(false);
			toolBarForList.setShowDsExplorer(true);
			toolBarForList.setShowFormMaintControl(false);
			toolBarForList.setShowNewControl(true);
			toolBarForList.setShowSaveAllControl(true);
			toolBarForList.setShowSaveControl(false);
			toolBarForList.setShowSearchControl(false);
			toolBarForList.setShowCancelChanges(true);
			toolBarForList.setShowNavigation(false);
	}
	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}
	public boolean isDebugMode() {
		return debugMode;
	}
	
	//............................................For File Uploder................................
    
	private Attribute  attributeFileUploder;
	
	public Attribute getAttributeFileUploder() {
		return attributeFileUploder;
	}
	public void setAttributeFileUploder(Attribute pm_attributeFileUploder) {
		this.attributeFileUploder = pm_attributeFileUploder;
	}
    private String reRenderAfterClose;
       
	public String getRerenderAfterClose() {
		return reRenderAfterClose;
	}
	public void setRerenderAfterClose(String rerenderAfterClose) {
		this.reRenderAfterClose = rerenderAfterClose;
	}
	//.................................................................................

	PersistantObject  poForWelcomePage;
	
	public PersistantObject getPoForWelcomePage() {
		return poForWelcomePage;
	}
	public void setPoForWelcomePage(PersistantObject poForWelcomePage) {
		this.poForWelcomePage = poForWelcomePage;
	}
	
	private Attribute searchFormAtt ; 
	public void setSearchFormAtt(Attribute searchFormAtt) {
		this.searchFormAtt = searchFormAtt;
	}
	public Attribute getSearchFormAtt() {
		return searchFormAtt;
	}

	private   Hashtable<DbTable, TableMaintMaster> listOfUsedTables = new Hashtable<DbTable, TableMaintMaster>();

	public  Hashtable<DbTable, TableMaintMaster> getListOfUsedTables()
	{
		return listOfUsedTables;
	}
	
	
	public void valueBound(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void valueUnbound(HttpSessionBindingEvent arg0) 
	{
		List<ModuleServices> activeModules =  this.getLastVisitedModules() ;
		for (ModuleServices ms : activeModules)
		{
			ms.removeFromLastVisted();
		}

	}
	public static 		final String UserSession = "userSession";

	AbstractSession userSession ; 
	public  AbstractSession getUserSession() {

		if (userSession == null)
		{
			userSession =  (AbstractSession) ModuleServicesContainer.getHttpSession().getAttribute(UserSession) ; 
		}

		return  userSession ;
	}
	
	public static HttpSession getHttpSession()
	{
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		
		return session ; 
	}
	
	public  Connection getConnFromUsrSession() throws Exception
	{
		return getConnFromUsrSession(this.getUserSession().getSelectedEnv());
	}

	public  Connection getReposatoryConnection() throws Exception
	{
		Connection result = null ; 
		XMLConfigFileReader xmlConnectionsData = ApplicationContextListener.getXmlConnectionsData();
		 
		String repConnName = xmlConnectionsData.reposatoryConn.name ; 
		result = this.getSessionConnections().get(repConnName);
		if (result == null)
		{
			result  = xmlConnectionsData.reposatoryConn.generateConnection(); 
			
			this.getSessionConnections().put(repConnName, result);
		}
		return result ; 
	}
	
	public  Connection getConnFromUsrSession(String xx) throws Exception
	{
		Connection result = null ; 
		result = this.getSessionConnections().get(xx);
		if (result == null)
		{
			URL url = ApplicationContextListener.getConnectionsFile();
			XMLConfigFileReader connectionsData = new XMLConfigFileReader(url , false );
			Vector<Support.ConnParms> conParms = connectionsData.connParms;
			
			for (int i = 0 ; i< conParms.size() ; i++ ) 
	        {
				Support.ConnParms thisConParms = (Support.ConnParms)conParms.elementAt(i);
				if (thisConParms.name.equals(xx))
				{
					result = thisConParms.generateConnection();
					break;
				}
	        }
			this.getSessionConnections().put(xx, result);
		}
		return result ; 
	}
	
	private HashMap <String , Connection> sessionConnections = new HashMap <String , Connection>();

	public HashMap <String , Connection> getSessionConnections() {
		return sessionConnections;
	}

	public void closeAllConnections() {
		for (Connection con : this.getSessionConnections().values())
		{
			if (con != null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
	public void setSysPoAttachments(SysPoAttachments pm_sysPoAttachments) {
		this.sysPoAttachments = pm_sysPoAttachments;
	}
	public SysPoAttachments getSysPoAttachments() {
		return sysPoAttachments;
	}
	private PersistantObject poToBeExplored ;
	public void setPoToBeExplored(PersistantObject poToBeExplored) {
		this.poToBeExplored = poToBeExplored;
	}
	public PersistantObject getPoToBeExplored() {
		return poToBeExplored;
	}

	public void reset()
	{
		this.getListOfUsedTables().clear();
		for ( ModuleServices ms :  this.getAllModules())
		{
			ms.reset();
			ms.removeFromLastVisted();
			
		}
		
	}
	
}
