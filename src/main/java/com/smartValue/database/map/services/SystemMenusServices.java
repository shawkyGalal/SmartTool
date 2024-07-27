package com.smartValue.database.map.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.richfaces.model.TreeNode;

import com.smartValue.components.model.tree.TreeBean;
import com.smartValue.database.ApplicationContext;
import com.smartValue.database.DataSet;
import com.smartValue.database.DbServices;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.SysMnu;
import com.smartValue.database.map.SysMsg;
import com.smartValue.database.map.TableMaintMaster;



public class SystemMenusServices extends ModuleServices{
	
	public SystemMenusServices(DbServices pmDbServices) {
		super(pmDbServices);
	}

	private ArrayList<SysMnu> userFavoriteSystemMenus;
	private TreeBean  	allSysMenuTree;
	private TreeBean  	lastUsedTree;
	private boolean 	displayLastVisitedAsTree = true;
	private ArrayList<SelectItem> allAvailableMenuTypes ; 
	private List<SelectItem> pages = null;
	private ArrayList<SelectItem> sysImages = null;
	private ArrayList<SelectItem> sysReports = null;
	private ArrayList<SelectItem> mnuPrgAlias = null;
	
	private int userLang =1 ;
	private SelectItem[] allAvailableMunCodes = null;
	private SysMnu sysMnuToBeUpdated; 
	
	@Override
	public String getOrginalQuery()
	{
		SecUsrDta loggedUser = this.getDbServices().getLoggedUser();
		String[] allowedSysMnusCodes =  loggedUser.getUserSysMnuCodes();
		boolean isAdmin = this.getDbServices().getLoggedUser().isSmartToolAdmin();
		return "select t.* , t.rowid from sys_mnu t "+
		 		( (isAdmin)? "" : " where mnu_code in ("+getAllowedMnuCodes(allowedSysMnusCodes)+" )" ) ; 
			
	}
	@Override
	public boolean isChanged() {
		return this.getDataSet().isChanged() ; 
	}
	
	private String getAllowedMnuCodes(String[] allowedSysMnusCodes)
	{
		StringBuffer result = new StringBuffer("");
		for (int i = 0 ; i< allowedSysMnusCodes.length ; i++)
		{
			result.append(((i==0)? "":",") + "'" +allowedSysMnusCodes[i]+"'"); 
		}
		return result.toString();
	}
	@Override
	public Class getPersistentClass()
	{
		return  SysMnu.class;
			
	}
	
	public String getMnuTxt(String key) {
		String mnuTxt = null;
		Iterator<PersistantObject> it = this.getDataSet().getPersistantObjectList().iterator();
		while(it.hasNext())
		{
			SysMnu menuItem =  (SysMnu)it.next();
			String x = (String) menuItem.getMnuCode().getValue();
			if (x.equalsIgnoreCase(key)) mnuTxt = (String) menuItem.getAttribute("mnu_txt").getValue(); 
		}
		return mnuTxt;
	}

	public List<SysMnu> getRootNodes() 
	{
		List<SysMnu> rootNodes = new ArrayList<SysMnu>();
		Iterator<PersistantObject> it =  this.getDataSet().getPersistantObjectList().iterator();
		while (it.hasNext())
		{
			SysMnu sysMenu = (SysMnu)it.next();
			String mnuParent = (String) sysMenu.getMnuParent().getValue();
			if ( mnuParent == null || mnuParent.equals(""))
			{
				rootNodes.add(sysMenu);
			}			
		}
		return rootNodes;
			
	}
	public ArrayList<SysMnu> getChilderns(String key) {
		
		ArrayList<SysMnu> childerNodes = new ArrayList<SysMnu>();
		Iterator<PersistantObject> it =  this.getDataSet().getPersistantObjectList().iterator();
		while (it.hasNext())
		{
			SysMnu sysMenu = (SysMnu)it.next();
			String mnuParent = (String) sysMenu.getMnuParent().getValue();;
			if ( mnuParent != null && mnuParent.equals(key))
			{
				childerNodes.add(sysMenu);
			}
			
		}
		return childerNodes;
	}

	public void setUserFavoriteSystemMenus(ArrayList<SysMnu> userFavoriteSystemMenus) {
		this.userFavoriteSystemMenus = userFavoriteSystemMenus;
	}

	public ArrayList<SysMnu> getUserFavoriteSystemMenus() {
		return userFavoriteSystemMenus;
	}

	public TreeBean getAllSysMenuTree() {
		if (this.allSysMenuTree == null) this.setAllSysMenuTree();
		return this.allSysMenuTree ;
	}
	public void setAllSysMenuTree(TreeBean allSysMenuTree) {
		this.allSysMenuTree = allSysMenuTree;
	}
	public void setAllSysMenuTree() {
		TreeBean  all = new TreeBean();
		all.setAllItems(this.getDataSet());
		this.allSysMenuTree =  all;
	}
	public  TreeNode<SysMnu> getAllSysMenuTreeNode()
	{
		if (this.allSysMenuTree == null) this.setAllSysMenuTree();
		return this.allSysMenuTree.getTreeNode();
	}

	public TreeBean getLastUsedSysMenuTree() {
		this.setLastUsedSysMenuTree();
		return this.lastUsedTree;
		
	}
	private void setLastUsedSysMenuTree() {
		
			TreeBean  lastUsedTree = new TreeBean();
			DataSet allItems =	null;
			
			if (this.allSysMenuTree != null) allItems = this.allSysMenuTree.getLastVisited();
			lastUsedTree.setAllItems(allItems);
			this.lastUsedTree =  lastUsedTree;
	}
	
	public  TreeNode<SysMnu> getLastUsedSysMenuTreeNode()
	{   this.setLastUsedSysMenuTree();
		return this.lastUsedTree.getTreeNode();
	}
	
//	public HtmlPanelGrid getToolbarPanelGridx()
//	{
//		if ( getToolbarPanelGrid() == null)
//			 setToolbarPanelGrid("tableMaintDetailServices.dataSet");	
//		 return super.getToolbarPanelGrid();
//			
//	}

	public void setDisplayLastVisitedAsTree(boolean displayLastVisitedAsTree)
	{
		this.displayLastVisitedAsTree = displayLastVisitedAsTree;
	}

	public boolean isDisplayLastVisitedAsTree()
	{
		return displayLastVisitedAsTree;
	}
	
	public ArrayList<SelectItem> getAllAvailableMenuTypes() throws Exception
	{
		if (this.allAvailableMenuTypes == null)
		{
		DbServices dbs =  this.getDbServices();
		setAllAvailableMenuTypes( dbs.getSelectItems(" Select 1, 'Execute SQL' from dual " +
				" union Select 2, 'Run a Program'  from dual " +
				" union Select 5, 'Navigate to URL'  from dual " , false));

		}
		return this.allAvailableMenuTypes;
	}

	public void setAllAvailableMenuTypes(ArrayList<SelectItem> allAvailableMenuTypes)
	{
		this.allAvailableMenuTypes = allAvailableMenuTypes;
	}
	public  boolean isCanHaveMultipleInstances()
	{
		// TODO Auto-generated method stub
		return true;
	}
	
	public List<SelectItem> getPages() {
		if(pages == null){
			pages = new ArrayList<SelectItem>(); 
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession)context.getExternalContext().getSession(true);

			pages.add(new SelectItem(null,"Select.."));
		
			String path = session.getServletContext().getRealPath("") ;
			File file  = new File(path);
			includeFileUnderDir(file , pages);
			
		}
		return pages;
	}
	
    public void includeFileUnderDir(File dir , List<SelectItem> pm_pages) {
        if (dir.isDirectory() ) 
        {
        	boolean excludedFolder = dir.getName().equalsIgnoreCase(".svn") 
        							||  dir.getName().equalsIgnoreCase("WEB-INF") 
        							|| dir.getName().equalsIgnoreCase("META-INF") ; 
        	if ( !excludedFolder)
        	{
	            String[] children = dir.list();
	            for (int i=0; i<children.length; i++) {
	            	includeFileUnderDir(new File(dir, children[i]), pm_pages);
	        }
        }
        } else {
        	String fileNameDisplay = dir.toString();
        	FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession)context.getExternalContext().getSession(true);
        	String contextPath = session.getServletContext().getRealPath("") ;
        	if (fileNameDisplay.indexOf(contextPath) >= 0 )
        	fileNameDisplay = fileNameDisplay.substring(contextPath.length());
        	String filNameValue = fileNameDisplay.replace("\\", "/");
            pages.add(new SelectItem(filNameValue,fileNameDisplay));
        }
    }

	public ArrayList<SelectItem> getSysImages() {
		if (sysImages == null) {
			String fieldName="";
			if(this.userLang == 1)
				fieldName = "img_name_";
			else if(this.userLang == 2)
				fieldName = "img_name";
			try {
				DbServices dbs =  this.getDbServices();
				sysImages = dbs.getSelectItems("SELECT trim(img_id), trim("+fieldName+") FROM sys_img order by "+fieldName , false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sysImages;
	}

	public static ArrayList<SelectItem> allAvailableMnuTypes ;
	public  ArrayList<SelectItem> getAllAvailableMnuTypes() throws Exception
	{
		if (allAvailableMnuTypes == null)
		{
			String query = "select t.mnu_type_code , t.mnu_type_desc from sys_mnu_types t";
			allAvailableMnuTypes = this.getDbServices().getSelectItems(query);
		}
		return allAvailableMnuTypes;
	}
	
	public ArrayList<SelectItem> getSysReports() {
		if (sysReports == null) {
			String fieldName="";
			if(this.userLang == 1)
				fieldName = "RPT_DESC_";
			else if(this.userLang == 2)
				fieldName = "RPT_DESC";
			try {
				DbServices dbs =  this.getDbServices();
				sysReports = dbs.getSelectItems("select trim(rpt_id) , trim("+fieldName+") from SYS_REPORT_DATA  order by "+fieldName , false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sysReports;
	}


	public ArrayList<SelectItem> getMnuPrgAlias() {		
		if (mnuPrgAlias == null) {
			try {
				DbServices dbs =  this.getDbServices();		
				mnuPrgAlias = dbs.getSelectItems("select trim(FRM_NAME),trim(FRM_NAME) from sys_frm order by FRM_NAME" , false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mnuPrgAlias;
	}
	
	/**
	 * Search in the Updatable SysMnu DataSet for object with the given mnuCode 
	 * @param mnuCode a unique mnuCode 
	 * @return
	 */
	public  SysMnu getSysMnuByMnuCode(String mnuCode)
	{
		
		SysMnu result =	null ;
		
		for (int i=0 ; i< this.getDataSet().getPersistantObjectList().size() ; i++)
		{
			SysMnu sm = (SysMnu) this.getDataSet().getPersistantObjectList().get(i) ;
			if (sm.getMnuCode().getValue().equals(mnuCode))
			{
				result = sm;
				break;
			}
		}
		
		return result;
	}
	
	public void setSysMnuToBeUpdated(SysMnu sm)
	{
		this.sysMnuToBeUpdated = sm ; 
	}
	
	public SysMnu getSysMnuToBeUpdated()
	{
		//if (sysMnuToBeUpdated == null)
		//	sysMnuToBeUpdated = (SysMnu) this.getDataSet().getCurrentItem();
		return this.sysMnuToBeUpdated  ; 
	}

	public void deleteSelectedTreeMenu() {
		try{
		this.getDataSet().getDbService().delete(this.getSysMnuToBeUpdated());
		this.setDataSet(null);
		this.setAllSysMenuTree();
		}
		catch (Exception e) {
		//	String msg = "Unable To Delete Tree Item Due to " + e.getMessage();
		//ApplicationContext.getMessageCommnunicatorService().sendMessageToUser(msg);
		 ModuleServicesContainer msc =  this.getDbServices().getModuleServiceContainer();
		 SysMsgServices sysmsgservices=msc.getSysMsgServices();
		 SysMsg sysMsg =sysmsgservices.getMsgUnableToDeleteTreeItem();	
		 sysMsg.setMsgDescValue(sysMsg.getMsgDescValue()+e.getMessage());
		 this.getMessageCommnunicatorService().sendMessageToUser(sysMsg);
		}
		
	}

	public void addChild(String parentCode) throws Exception {
		this.getDataSet().addNew();
		((SysMnu)this.getDataSet().getCurrentItem()).setMnuParentValue(parentCode);
		((SysMnu)this.getDataSet().getCurrentItem()).setNewChild(true);
		this.sysMnuToBeUpdated =  ((SysMnu)this.getDataSet().getCurrentItem()) ; 
	}
	
	public void ignoreChanges()   {
		if(!this.getDataSet().getCurrentItem().isContainsObjectKey())
			this.getDataSet().getPersistantObjectList().remove(this.getDataSet().getCurrentItem());
	}
	
	public ArrayList<SelectItem> getAllAvailableMunCodes() throws Exception {
		ArrayList<SelectItem> result = new ArrayList<SelectItem>();
		
			List<PersistantObject> sysMnuListOfAllAvailableItems = new ArrayList<PersistantObject>();
			//TODO Mfayek...check for lang 
			String query = "Select  t.*  from  "+ TableMaintMaster.CDB_SCHEMA_OWNER+".sys_mnu t where mnu_txt is not null order by mnu_txt asc" ; 
			sysMnuListOfAllAvailableItems=  this.getDbServices().queryForList(query,SysMnu.class);  
			for (PersistantObject po : sysMnuListOfAllAvailableItems)
			{
				SysMnu sysmnu = (SysMnu)  po;
				result.add(new SelectItem(sysmnu ,(sysmnu.getMnuTxtAutoLang().getValue()).toString() ) );
			}
		
		return result ; 
	}
	//...............................................................................
	@Override
	public DbTable getDbTable() {
		return new DbTable(TableMaintMaster.CDB_SCHEMA_OWNER , SysMnu.DB_TABLE_NAME , this.getDbServices());
	}
	
	public void goToSysMnu(String pm_sysMnuCode)
	{
		SysMnu sysMnu = this.getSysMnuByMnuCode(pm_sysMnuCode);
		if (sysMnu != null)
		{
			this.getAllSysMenuTree().setSelectedSysMenu(sysMnu);
		}
		else
		{
			this.getMessageCommnunicatorService().sendMessageToUser("Menu Item" + pm_sysMnuCode + "Not Found In the System");
		}
		
	}
	@Override
	public void afterModuleRemoved()  {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void beforeModuleRemoved() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	ArrayList<SelectItem> allSqlMnus ; 
	public ArrayList<SelectItem> getAllSqlMnus()
	{
		if ( allSqlMnus == null )
			{
				allSqlMnus = new ArrayList<SelectItem>() ;
					
				String query = "select t.* from " + SysMnu.DB_TABLE_NAME + " t where t." + SysMnu.MNU_TYPE + "= '3' " ; 
				try 
				{
					DataSet ds = this.getDbServices().queryForDataSet(query, SysMnu.class) ; 
					for (PersistantObject po : ds.getPersistantObjectList())
					{
						SysMnu sm = (SysMnu) po ; 
						allSqlMnus.add( new SelectItem ( sm , String.valueOf(sm.getMnuDescAutoLang().getValue()) ) ) ;
					}
		
				} 
				catch (Exception e) {
					
					e.printStackTrace();
				}
		
			}
		return allSqlMnus ; 
		
		
	}
	public String[] getClassPath()
	{
		return System.getProperty("java.class.path").split(";") ;
	}
	
	List<SelectItem> allClasses ;
	public List<SelectItem> getAllClasses() {
		if(allClasses == null)
		{
			allClasses = new ArrayList<SelectItem>(); 
			allClasses.add(new SelectItem(null,"Select.."));
		
			for (String path : this.getClassPath())
			{
				File file  = new File(path);
				includeFileUnderDir(file , allClasses);
			}
			
		}
		return allClasses;
	}

}
