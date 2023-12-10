package com.implex.database.map;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.faces.model.SelectItem;

import com.implex.database.ApplicationContext;
import com.implex.database.Attribute;
import com.implex.database.AttributeChangeValidator;
import com.implex.database.DataSet;
import com.implex.database.DataSetPickList;
import com.implex.database.DbForignKey;
import com.implex.database.DbForignKeyArray;
import com.implex.database.DbServices;
import com.implex.database.PersistantObject;
import com.implex.database.map.auto._SysMnu;
import com.implex.database.map.security.PersistentObjectSecurityControl;
import com.implex.database.map.security.SysMnuSecurityController;
import com.implex.database.map.services.InterfaceImplServices;
import com.implex.database.map.validators.SysMnuChangeValidator;
import com.implex.database.trigger.SysMnuTriggerHandler;
import com.implex.database.trigger.TriggerHandler;
import com.implex.sysMnuTypes.SysMnuSelectionProcessor;
import com.sideinternational.sas.BaseException;


public class SysMnu extends _SysMnu {
	
	public static final int DYNAMIC_REPORT_TYPE = 0;
	public static final String DYNAMIC_REPORT = "/SWAF/jsp/reports.xhtml";
	//public static final String DYNAMIC_REPORT_DESC = "Dynamic report";
	
	public static final int VIRTUAL_PAGE_TYPE = 1;
	//public static final String VIRTUAL_PAGE_DESC = "Virtual Page";
	public static final String VIRTUAL_PAGE= "/SWAF/jsp/globalPage.xhtml";
	
	public static final int PHYSICAL_PAGE_TYPE = 2;
	//public static final String  PHYSICAL_PAGE_DESC = "Physical Page";
	
	public static final int Menu_SQL_TYPE = 3;
	public static final String Menu_SQL_PAGE = "/SWAF/jsp/sqlExecuter.xhtml";
	//public static final String Menu_SQL_DESC = "Execute SQL";

	public static final int MENU_URL_TYPE = 4;
	//public static final String MENU_URL_DESC = "Open URL";
	
	public static final int MENU_RPT_TYPE = 5;
	public static final String MENU_CRYSTAL_RPT_PAGE="/SWAF/jsp/crystalReportViewer.xhtml" ;
	//public static final String MENU_RPT_DESC = "Open Crystal Report";

	public static final int MENU_PARENT_NODE_TYPE = 6;
		
	private static final String UNIQUE_KEY = "MNU_CODE";
	
	private static final String SYS_MNU_DESC = "SYS_MNU_DESC";

	private SysMnuSecurityController securityController = new SysMnuSecurityController(); 

	private static TriggerHandler auditTriger = new SysMnuTriggerHandler();
	private boolean newChild = false;
	private QueryExecuter queryExecuter ; 
	
		
	public SysMnu(){ 
		super(); 
	}
	
	private static DbForignKeyArray getForignKeyForSysMnus()
	{
		return new DbForignKeyArray (new DbForignKey[]{new DbForignKey(SysMnu.MNU_CODE ,SecUsrMnus.SYS_MNU_CODE )} , false );
	}

	private DataSet smd ;
	
	public DataSet getSmd()
	{
		return smd;
	}
	private static HashMap<String, DbForignKeyArray> childrenForignKeys;
	@Override
	protected HashMap<String, DbForignKeyArray> getChildrenForignKeys()
	{
		if (childrenForignKeys == null)
		{
		childrenForignKeys = new HashMap<String, DbForignKeyArray>();
		childrenForignKeys.put(SYS_MNU_DESC ,   new DbForignKeyArray( new DbForignKey[]{new DbForignKey( SysMnu.UNIQUE_KEY ,"MNU_CODE" )} , false )) ;
		childrenForignKeys.put(SecUsrMnus.DB_TABLE_NAME , SysMnu.getForignKeyForSysMnus());
		}
		return childrenForignKeys;
	}

	public DataSet getMnuUsers() throws Exception
	{
		return getChildrenDataSet(SecUsrMnus.DB_TABLE_NAME  , SecUsrMnus.class , false);
	}
	@Override 
	public AttributeChangeValidator getAttributeChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key)
	{ 
		return new SysMnuChangeValidator(pm_secUserData , pm_po  , pm_key);
	}

	public boolean hasParentSysMnu()
	{
		return (this.getMnuParentValue()!= null);
	}
	
	public SysMnu getParentSysMnu() throws Exception
	{
		String q = "select * from sys_mnu where mnu_code = '" + this.getMnuParentValue() + "'";
		DbServices dbs =  this.getDbService();
		DataSet ds = dbs.queryForDataSet(q, SysMnu.class);
		if (ds.getPersistantObjectList().isEmpty()) return null;
		return (SysMnu) ds.getPersistantObjectList().get(0);
	}
	
	@Override
	public PersistentObjectSecurityControl getSecurityController()
	{
		return securityController;
	}

	@Override
	public TriggerHandler getTriggerHandler()
	{
		return auditTriger ;
	}

	String mnuImagePath = null;
	public void setMnuImagePath(String mnuImagePath) {
		this.mnuImagePath = mnuImagePath;
	}

	public String getMnuImagePath() {
		if(mnuImagePath == null)
		{
			try {
				if (this.getMnuImgValue() == null || this.getMnuImgValue().equals("") )
				{
					mnuImagePath = "";
					return mnuImagePath;
				}
				SysImg sysImg = (SysImg) (this.getDbService().queryForList(
						"SELECT I.IMG_PATH FROM sys_img I where I.IMG_ID= "	+ this.getMnuImgValue(), SysImg.class)).get(0);
				mnuImagePath = sysImg.getImgPathValue();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mnuImagePath;
	}
	 	
	


	public void setNewChild(boolean newChild)
	{
		this.newChild = newChild;
	}

	public boolean isNewChild()
	{
		return newChild;
	}
	
	
	/**
	 * 
	 * @return trur if this item is modified within the last week
	 */
	public boolean isNew()
	{
		boolean result = false;
		Date now = new Date();
		Date weekBeforeNow = new Date(now.getTime()-7*24*60*60*1000);
		Date dateCreated = this.getDateCreatedValue();
		result = dateCreated.after(weekBeforeNow);
		return result;
		
	}
	
	public boolean isMnuSqlRendered ()
	{	boolean result = true;
		if (this.getMnuTypeValue() != null)
			result =(getMnuTypeValue().intValue() == SysMnu.Menu_SQL_TYPE);
		return result;
	}
	
	public boolean isUrlType()
	{	
		
		return this.getMnuTypeValue() != null && this.getMnuTypeValue().intValue()==SysMnu.MENU_URL_TYPE;
	}
	public boolean isParentNode()
	{
		return this.getMnuTypeValue() != null && this.getMnuTypeValue().intValue()==SysMnu.MENU_PARENT_NODE_TYPE;
	}
	
	
	public void beforeUpdate() throws Exception
	{
		if ( this.getMnuTxtValue() == null || this.getMnuTxtValue().equals(""))
		{
			this.setMnuTxtValue(this.getMnuTxt_Value());
		}
		else if( this.getMnuTxt_Value() == null || this.getMnuTxt_Value().equals(""))
		{
			this.setMnuTxt_Value(this.getMnuTxtValue());
		}
	}
	
	public void afterSave()
	{
		this.getDbService().getModuleServiceContainer().getSysMenuServices().setAllSysMenuTree(null);
	}
	private String pdfReportPath = "";
	public void setPdfReportPath(String pdfReportPath) {
		this.pdfReportPath = pdfReportPath;
	}
	public String getPdfReportPath() {
		return pdfReportPath;
	}

	/* i found out that the object which is loaded and the object wich was selected will be
	 *  compared when setting to the backing bean. So if your object’s
	 *  Class has not overridden the equals method this error message is shown. */
	  public boolean equals(Object obj) 
	{ 
			if(this == obj) { 
			return true; 
			} 
			if (!(obj instanceof SysMnu)) { 
			return false;  
			} 
			SysMnu sysmnu = (SysMnu)obj; 
			return this.getMnuCodeValue().equals(sysmnu.getMnuCodeValue()) ;
	}
	SysMnuSelectionProcessor selectionProcessor;
	public SysMnuSelectionProcessor getSelectionProcessor() {
		if (selectionProcessor == null)
		{
			this.refreshSelectionProcessor();
		}
		return selectionProcessor ;
	}
	
	public void refreshSelectionProcessor() {
		selectionProcessor = this.calcSelectionProcessor();
	}
	public SysMnuSelectionProcessor calcSelectionProcessor() {

		InterfaceImplServices ims = new InterfaceImplServices(this.getDbService());
		
		SysInterfaceImplementors sii =  ims.getImplementorByCode(String.valueOf(this.getMnuTypeValue()) , SysMnuSelectionProcessor.class.getName());
		return (SysMnuSelectionProcessor)sii.getImplInstance();
	}
	
	  DataSetPickList dataSetPickListOfUsers;
		public DataSetPickList getDataSetPickListOfUsers() throws Exception 
		{
			if(dataSetPickListOfUsers==null)
			{
				ArrayList<PersistantObject> initialValue = this.getUsersOfSysMnu();
				
				dataSetPickListOfUsers = new  DataSetPickList( "USR_NAME"  ,"USR_NAME"  ,this.getMnuUsers() , initialValue );
				DbServices dbs = this.getDbService() ; 
				ArrayList<SelectItem> allAvailableItems = this.getDbService().getModuleServiceContainer().getSecUserDataService().getAllAvailableUsers();
				dataSetPickListOfUsers.setAllAvailableItems(allAvailableItems);
				dataSetPickListOfUsers.setConverterId("converterForSysTableSecurity");
			}
			return dataSetPickListOfUsers;
		}
		
		private ArrayList<PersistantObject> getUsersOfSysMnu() {
			ArrayList<PersistantObject> result = new ArrayList<PersistantObject> () ; 
			String query = "Select t.* , t.rowid from "+ TableMaintMaster.CDB_SCHEMA_OWNER+".SEC_USR_MNUS  ,"+ TableMaintMaster.CDB_SCHEMA_OWNER+".SEC_USR_DTA t where SEC_USR_MNUS.USR_NAME = t.USR_NAME and SEC_USR_MNUS.SYS_MNU_CODE='"+this.getMnuCodeValue()+"'order by t.USR_NAME asc";
			try {
				result = (ArrayList<PersistantObject>) this.getDbService().getLoggedUser().getDbService().queryForList(query,SecUsrDta.class);
				} 
			catch (Exception e) 
				{e.printStackTrace();}
		return result;
		}
		
		public QueryExecuter getQueryExecuter() {
			
			return queryExecuter;
		}
		
		public void initialize()
		{
			queryExecuter = new QueryExecuter(this, this.getDbService());
		}
		
		@Override
		public void beforeInsert() throws BaseException {
			
			
			boolean noArabic =  this.getMnuTxt_Value()==null || this.getMnuTxt_Value().trim().equals("") ;
			boolean noEnglish = this.getMnuTxtValue()==null || this.getMnuTxtValue().trim().equals("")  ; 
			
			if (noArabic && noEnglish) 
			{
				this.getMnuTxt().setValue("New Tree Node");
				this.getMnuTxt_().setValue("New Tree Node");
			}
			else if (noArabic )
			{
				this.getMnuTxt_().setValue(this.getMnuTxtValue());
			}
			
			else if (noEnglish)
			{
				this.getMnuTxt().setValue(this.getMnuTxt_Value());
			}
			
			
			
		}
		
		public void clearQueryExeuterResults()
		{
			if (this.getQueryExecuter() != null)
			{
				this.getQueryExecuter().clearResults() ;
			}
		}
		@Override
		public void afterAttributeChange(Attribute pm_att)
		{
			String key = pm_att.getKey();
			if(key.equals(SysMnu.MNU_TYPE))
			{
				doAfterMnuTypeChanged();
			}		
			else if(key.equals(SysMnu.MNU_IMG))
			{
				this.setMnuImagePath(null);
			}
			else if(key.equals(SysMnu.MNU_SQL))
			{
				this.initialize();
			}
		}

		private void doAfterMnuTypeChanged() {
			if (this.getMnuTypeValue() != null)
			{
				this.refreshSelectionProcessor();
				this.getSelectionProcessor().applyDefaults(this);
				
				int mnuType = this.getMnuTypeValue().intValue();

				if (mnuType == SysMnu.DYNAMIC_REPORT_TYPE)
				{
					this.getMnuPrgName().setValue(SysMnu.DYNAMIC_REPORT);
				}
				
				
			}
			
		}
		
		@Override
		public void beforeAttributeChange(Attribute pm_att)
		{
		}

}


