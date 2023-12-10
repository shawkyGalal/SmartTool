
package com.implex.components.model.tree;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.richfaces.component.html.HtmlTree;
import org.richfaces.component.state.TreeState;
import org.richfaces.event.NodeSelectedEvent;
import org.richfaces.model.TreeNode;
import org.richfaces.model.TreeNodeImpl;

import com.implex.database.ApplicationContext;
import com.implex.database.DataSet;
import com.implex.database.DbTable;
import com.implex.database.DirectJdbcServiceImpl;
import com.implex.database.PersistantObject;
import com.implex.database.map.SysMnu;
import com.implex.database.map.TableMaintMaster;
import com.implex.database.map.UsrDefVar;
import com.implex.database.map.services.ModuleServices;
import com.implex.database.map.services.ModuleServicesContainer;
import com.implex.database.map.services.SysFrmServices;
import com.implex.listeners.ApplicationContextListener;
import com.implex.sysMnuTypes.SysMnuSelectionProcessor;
import com.sideinternational.clrt.GenerateReport;
import com.sideinternational.clrt.InputArgs;
import com.sideinternational.sas.BaseException;
import com.sideinternational.sas.configuration.Configuration;
import com.sideinternational.sas.event.resource.debug.EventDbug9998;
import com.sideinternational.sas.event.resource.system.EventSysm1026;
import com.sideinternational.sas.service.authorization.AuthorizationServiceFactory;
import com.sideinternational.web.swaf.SWAF;


public class TreeBean {

	private TreeNode<SysMnu> firstNode = null;
	// (set  enterMnueContext  with value When  click  on  ContextMnue  Properties   only) to know that it come from properties button
	private		String enterMnueContext="";


	public void setEnterMnueContext(String enterMnueContext) {
		this.enterMnueContext = enterMnueContext;
	}
	private SysMnu selectedSysMenu;
	private DataSet allItems;

	private ArrayList<PersistantObject> lastVisited = null;
	int lastVisisyedSize = 3;
	List<SysMnu> rootNodes = null;

	
	public void setAllItems(DataSet allItems) {
		this.allItems = allItems;
	}
	public  List<SysMnu> getLeafsOnly()
	{
		List<SysMnu> leafs = new ArrayList<SysMnu>();
		for (int i = 0 ; i< this.getAllItems().getPersistantObjectList().size() ; i++)
		{
			SysMnu item = (SysMnu) this.getAllItems().getPersistantObjectList().get(i);
			
			if ( item != null && this.getChilderns(item).size() == 0)
				{
					leafs.add(item);
				}
		}
		return leafs;
	}
	public DataSet getAllItems() {
		return allItems;
	}
	

	public SysMnu getSelectedSysMenu() {
		return selectedSysMenu;
	}
	
	public void processSelection(NodeSelectedEvent event) {
        HtmlTree tree = (HtmlTree) event.getComponent();
        SysMnu sysMnu = (SysMnu) tree.getRowData();
        if(!this.enterMnueContext.equals(""))
		{
		this.enterMnueContext="";
		}
		else
		{
		this.setSelectedSysMenu(sysMnu);
		}
        //rich:tree clear selection
        TreeState state = (TreeState) tree.getComponentState();
        state.setSelected(null);
	}

	public void setSelectedSysMenu(SysMnu selectedSysMenu) {
		if(!selectedSysMenu.isParentNode() && !selectedSysMenu.isUrlType())
		{
			if(selectedSysMenu.getModuleServiceContainer().getLastVisitedModules() != null &&
			   !selectedSysMenu.getModuleServiceContainer().getLastVisitedModules().isEmpty())
			{
				for(ModuleServices moduleService : selectedSysMenu.getModuleServiceContainer().getLastVisitedModules())
				{
					if(moduleService.getLinkedSysMnuItem().getMnuCodeValue().equals(selectedSysMenu.getMnuCodeValue()))
					{
						selectedSysMenu.getModuleServiceContainer().setSelectedModuleFormLastUsedList(moduleService);
						moduleService.applyPage();
						return;
					}
				}
			}
			
			this.selectedSysMenu = selectedSysMenu;
			this.applySelectedSysMnu( selectedSysMenu.getModuleServiceContainer() );
		}
	}
	

	public DataSet getLastVisited() {
		if (this.lastVisited == null)
		{
			this.lastVisited= new ArrayList<PersistantObject>();
			for (int i = 0 ; i< this.lastVisisyedSize ; i++)
			{
				this.lastVisited.add(null);
			}
		}
		return new DataSet( lastVisited , allItems.getDbService());
	}

	public void setLastVisited(ArrayList<PersistantObject> lastVisited) {
		this.lastVisited = lastVisited;
	}
	
	public List<PersistantObject> getLastVisted()
	{
		return this.lastVisited ;
	}

	public void loadTree() {
		firstNode = new TreeNodeImpl<SysMnu>();
		rootNodes = this.getRootNodes();
		 
		for (int i = 0 ; i < this.rootNodes.size() ; i++)
		{
			SysMnu rootNode = (SysMnu)rootNodes.get(i);
			addNodes( firstNode , rootNode  );
		}
	}
	
	private List<SysMnu> getRootNodes() {
		if (rootNodes == null)
		{
			rootNodes = new ArrayList<SysMnu>();
			if (this.getAllItems() != null)
			for (int i = 0 ; i < this.getAllItems().getPersistantObjectList().size() ; i++)
			{
				SysMnu item = (SysMnu)this.getAllItems().getPersistantObjectList().get(i);
				{
					if (item != null && ((String) item.getMnuParent().getValue()== null || ((String) item.getMnuParent().getValue()).equals("")) ) 
						rootNodes.add(item);
				}	
			}
		}
		return rootNodes;
	}

	private List<SysMnu> getChilderns(SysMnu pm_sysMenu) {
		if (pm_sysMenu==null) return new ArrayList<SysMnu>();
		return this.getChilderns((String)pm_sysMenu.getMnuCode().getValue());
	 
	}
	private List<SysMnu> getChilderns(String key) {
		if (key==null) return new ArrayList<SysMnu>();
		List<SysMnu> childernItems = new ArrayList<SysMnu>();
		for (int i = 0 ; i < this.getAllItems().getPersistantObjectList().size() ; i++)
		{
			SysMnu item = (SysMnu)this.getAllItems().getPersistantObjectList().get(i);
			
			if (item != null && item.getMnuParent().getValue() !=null && (((String)item.getMnuParent().getValue()).equalsIgnoreCase(key))) childernItems.add(item);
				
		}
		return childernItems;
	}
	private void addNodes( TreeNode<SysMnu> node  , SysMnu pm_sysMenu ) //throws BaseException
	{
			if (pm_sysMenu == null) return;
			String key = (String) pm_sysMenu.getMnuCode().getValue();
			TreeNodeImpl<SysMnu> nodeImpl = new TreeNodeImpl<SysMnu>();
			nodeImpl.setData(pm_sysMenu);
			node.addChild(key, nodeImpl);
			{
				List<SysMnu> childrens = this.getChilderns(key);
				for (int j = 0 ; j< childrens.size() ; j++)
				{
					SysMnu sysMenu = (SysMnu) childrens.get(j);				
					addNodes( nodeImpl , sysMenu );
				}
			}
	}

	public void applySelectedSysMnu(ModuleServicesContainer msc) {

		if (this.lastVisited != null && !this.lastVisited.contains(selectedSysMenu)) {
			for (int i = 0; i < this.lastVisisyedSize - 1; i++) {
				this.lastVisited.add(i + 1, lastVisited.get(i));
				this.lastVisited.remove(i);
			}
			addToLastVisited(selectedSysMenu);
		}
		BigDecimal mnuType = selectedSysMenu.getMnuTypeValue();
		String menuText =	(String) this.selectedSysMenu.getMnuTxt().getValue();
		if (!hasModuleAccess((String) this.selectedSysMenu.getMnuCode().getValue())) {
			SWAF.addErrorMessage(null, new EventDbug9998("Sorry : You do not have Access to " + 
					menuText + "(" + this.selectedSysMenu.getMnuCode() + ")" + 
					"Module. Please Check With System Admin"));
			
			new EventSysm1026(menuText);
		}		
			
			int LoggedUserMaxAllowedModules = msc.getLoggedUserMaxAllowedModules() ; 
			int activeModulesSize = msc.getLastVisitedModuleServicesStack().size();
			if (activeModulesSize > LoggedUserMaxAllowedModules ) 
			{
				msc.setPageId("/SWAF/jsp/modulesExcceedsLimit.xhtml");
				msc.setPageTitle("You Have Exceeded Your Allowed Opened Modules (" +msc.getLoggedUserMaxAllowedModules() +")");
			} 
			else 
			{
				SysMnuSelectionProcessor sysMnuSelectionProcessor = selectedSysMenu.getSelectionProcessor();
				if (sysMnuSelectionProcessor != null)
				{
					sysMnuSelectionProcessor.processSelection(selectedSysMenu);
				}
				this.creatAndAddToServiceContainer(msc);
				
			}
		
	}
	
	public static String getContextPath (String pm_pageId)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession)context.getExternalContext().getSession(true);
		return session.getServletContext().getRealPath(pm_pageId) ; 
		
	}
	
	
	
	private void creatAndAddToServiceContainer(ModuleServicesContainer msc) 
	{
		ModuleServices ms =	null ; 
		 
			String serviceClassName =  (String) this.selectedSysMenu.getServiceClass().getValue();
			String prgName = (String) this.selectedSysMenu.getMnuPrgName().getValue();
			if(prgName == null)
				prgName = ModuleServicesContainer.DEFAULT_HOME_PAGE;
			BigDecimal mnuType = selectedSysMenu.getMnuTypeValue(); 
			if( mnuType!=null && mnuType.intValue() == SysMnu.Menu_SQL_TYPE )
				prgName = SysFrmServices.SQL_EXECUTER_PAGE;

			if (serviceClassName != null ) 
			{
				  ms = msc.searchForServices(serviceClassName);
					
				  if (ms ==null)
					{
						ModuleServicesContainer hrMsc = ApplicationContext.getHrModuleServicesContainer();
						ms = hrMsc.searchForServices(serviceClassName);
					}
				  
				  	
			}
			else
			{
				ms = getDefaultModuleServices();	
			}
			
			ms.setDbService(this.selectedSysMenu.getDbService());
			
			ms.setLinkedSysMnuItem(selectedSysMenu);
			if(ms.getDbTable() != null)
				msc.getUsrDefVarServices().setSelectedTableName(ms.getDbTable().getTableName());
			if(ms.getDbTable() != null && ms.getDbTable().getTableName().equals(UsrDefVar.DB_TABLE_NAME))
				msc.getUsrDefVarServices().setDisabledTablesNamesList(false);
			else
				msc.getUsrDefVarServices().setDisabledTablesNamesList(true);
			
			if ( ms.isCanHaveMultipleInstances() ||  msc.isPoolEmpty(ms))
			{
				msc.addNewModuleServiceByTreeRequest(ms);
			}
			msc.setCurrentActiveModule(ms);
			//msc.getTableMaintServices().setCallerModuleService(ms);
			addToLastVisted(ms);
		}
	
	private void addToLastVisted(ModuleServices pm_ms)
	{
		ModuleServicesContainer msc = (ModuleServicesContainer) SWAF.getManagedBean("moduleServicesContainer");
		msc.markAllAsNotSelected();
		
		pm_ms.setModuleSelected(true);
		msc.getLastVisitedModuleServicesStack().push(pm_ms);
	}
		
	private ModuleServices getDefaultModuleServices()
	{
		ModuleServices dms = new ModuleServices(this.selectedSysMenu.getDbService()){

			@Override
			public String getOrginalQuery()
			{	ModuleServicesContainer msc = this.getDbServices().getModuleServiceContainer() ; 
				return TableMaintMaster.getAllItemsQuery(msc.getTableMaintServices().getTableName(), TableMaintMaster.CDB_SCHEMA_OWNER) ;
			}
			@Override
			public Class getPersistentClass()
			{
				return null;
			}
			@Override
			public boolean isCanHaveMultipleInstances()
			{
				return false;
			}
			@Override
			public DbTable getDbTable() {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			public void afterModuleRemoved()  {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void beforeModuleRemoved()  {
				// TODO Auto-generated method stub
				
			}
			@Override
			public boolean isChanged() {
				// TODO Auto-generated method stub
				return false;
			}};
		
		return dms;
	}
	
	
	private void addToLastVisited(SysMnu pm_selectedSysMenu)
	{
		this.lastVisited.add(0, pm_selectedSysMenu);
		SysMnu parent = this.getParent(pm_selectedSysMenu);
		if (parent != null && !lastVisited.contains(parent))
		{
			addToLastVisited(parent);
		}
	}
	
	private SysMnu getParent(SysMnu pm_sm)
	{
		SysMnu parent = null;
		for  (int i = 0; i < this.allItems.getPersistantObjectList().size() ; i++ )
		{   SysMnu smm = (SysMnu) this.allItems.getPersistantObjectList().get(i);
			if (smm.getMnuCode().equals(pm_sm.getMnuParent())) {
				parent = smm;
				break;
			}
		}
		return parent;		
	}
	
	private boolean hasModuleAccess(String pm_moduleName) 
	{
		try
		{
			return AuthorizationServiceFactory.createAuthorizationService().hasModuleAccess(pm_moduleName, SWAF.getApplicationId(), SWAF.getSession().getOperator());
		}
		catch (BaseException e)
		{
			return false;
		}
	}
	public TreeNode<SysMnu> getTreeNode() {
		if (firstNode == null) {
			this.loadTree();
		}
		return firstNode;
	}

	public static void main(String[] args) throws Exception
	{
		Configuration.initializeForWeb("ERPINS", "en-config.xml");
		ApplicationContext.setPools(ApplicationContextListener.initializeConnections(new URL ("File:\\D:\\ERPINS\\Sources\\IMPLEX_CONFIG_HOME\\ERPINS\\" +File.separator + "Connections_config.xml")));
		
		DirectJdbcServiceImpl dbs = new DirectJdbcServiceImpl();
		dbs.initialize("HRMS" , 1);
		TreeBean tb = new TreeBean();
		tb.loadTree();
	}
	
	public String clearHistory()
	{
		this.lastVisited = null;
		return "Success";
	}
}
