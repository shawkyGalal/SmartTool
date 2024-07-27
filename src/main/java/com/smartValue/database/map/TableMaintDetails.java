package com.smartValue.database.map;


//import com.implex.HtmlTypesOld;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

import javax.faces.model.SelectItem;

import com.sideinternational.web.swaf.SWAF;
import com.smartValue.HtmlTypes.IHtmlType;
import com.smartValue.HtmlTypes.InputText;
import com.smartValue.HtmlTypes.OutputLabel;
import com.smartValue.components.model.tree.PersistantTreeObject;
import com.smartValue.components.model.tree.TreeBeanGeneric;
import com.smartValue.database.ApplicationContext;
import com.smartValue.database.AttChangeListner;
import com.smartValue.database.Attribute;
import com.smartValue.database.AttributeChangeValidator;
import com.smartValue.database.Condition;
import com.smartValue.database.DataSet;
import com.smartValue.database.DataSetPickList;
import com.smartValue.database.DbForignKey;
import com.smartValue.database.DbForignKeyArray;
import com.smartValue.database.DbServices;
import com.smartValue.database.DbTable;
import com.smartValue.database.Facet;
import com.smartValue.database.MessagesCommnuicatorService;
import com.smartValue.database.Operation;
import com.smartValue.database.ParentPersistantObject;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.attriburteProtectionType.AttBooleanSimpleImpl;
import com.smartValue.database.attriburteProtectionType.AttBooleanUsingUserDefVar;
import com.smartValue.database.attriburteProtectionType.AttBooleanUsingUsersAndGroups;
import com.smartValue.database.attriburteProtectionType.AttributePropertyController;
import com.smartValue.database.map.auto._TableMaintDetails;
import com.smartValue.database.map.security.PersistentObjectSecurityControl;
import com.smartValue.database.map.security.TableMaintDetailsSecurityControlHandlerImpl;
import com.smartValue.database.map.services.InterfaceImplServices;
import com.smartValue.database.map.services.ModuleServicesContainer;
import com.smartValue.database.map.services.SysMsgServices;
import com.smartValue.database.map.services.TableMaintMasterServices;
import com.smartValue.database.map.validators.TableMaintDetailsChangeValidator;
import com.smartValue.database.notifications.AttributeChangeNotifier;
import com.smartValue.database.trigger.TriggerHandler;
import com.smartValue.event.logging.Console;
import com.smartValue.sys.map.AllConsColumns;
import com.smartValue.sys.map.AllConstraints;


public abstract class  TableMaintDetails extends _TableMaintDetails {

	private Condition m_condition = null;
	private HashMap<Integer, ArrayList<SelectItem>> m_componentValues = new HashMap<Integer, ArrayList<SelectItem>>();
	private String logicalOperation = null;
	private boolean firstItemInlist = false; 
	private String m_selectedOperation = null;
	private static HashMap<String, DbForignKeyArray> childrenForignKeys ;
	public static final String ALLOW_ACCESS_TO_ALL = "A";
	public static final String DENY_ACCESS_TO_ALL = "D";
	private static final String EXCLUDED_ROLES_TABLE_NAME = "EXCLUDED_ROLES_DETAILS";
	public static final String NUMBER_DATA_TYPE = "NUMBER";
	private static final String DATE_DATA_TYPE = "DATE";
	private static final String FLOAT = "FLOAT";
	private static final String TIMESTAMP = "TIMESTAMP";
	private String columnNameToSwitch ; 
    private static String  EXCLUDED_ROLES_DISABLED="DISABLED";
    private static String  EXCLUDED_ROLES_RENDERED="RENDERED";
	private  TreeBeanGeneric queryTreeBean;
	private TableMaintDetailsSecurityControlHandlerImpl tmdSecurityControlHandler = new TableMaintDetailsSecurityControlHandlerImpl();
	
	private boolean numeric ; 
	private boolean date ; 
	public boolean isNumric()
	{
		String dataType = this.getDataTypeValue();
		this.numeric =  this.getDataTypeValue().equalsIgnoreCase(NUMBER_DATA_TYPE) 
						|| dataType.equalsIgnoreCase(FLOAT)   ;
		return numeric ; 
		
	}
	public boolean isDate() {
		String dataType = this.getDataTypeValue() ;
		this.date = dataType.equalsIgnoreCase(DATE_DATA_TYPE) 
									|| dataType.equalsIgnoreCase(TIMESTAMP);
		return date ; 
		
	}
	public boolean isFirstItemInlist() {
		return firstItemInlist;
	}
	public void setFirstItemInlist(boolean firstItemInlist) {
		this.firstItemInlist = firstItemInlist;
	}
	
	public String getLogicalOperation() {
		return logicalOperation;
	}
	public void setLogicalOperation(String logicalOperation) {
		this.logicalOperation = logicalOperation;
	}
	
	private ArrayList<SelectItem> calcQuerySelectItems(int pm_userLang )
	{
		java.sql.Connection con = this.getDbService().getConnection() ; 
		return calcQuerySelectItems (pm_userLang , con , null) ; 
	}
	protected ArrayList<SelectItem> calcQuerySelectItems(int pm_userLang , java.sql.Connection m_con , String callerObjectRowId)
	{
		ArrayList<SelectItem> result ; 
		try{
			String query = (String) this.getSelectListQueryValue();
			boolean mandatory = ! this.getNullable().getBooleanValue(); //&&  isRadioButton ; //  this.getHtmlTypeValue().equals(HtmlTypesOld.Radio_Button_Group);
			if (query == null || query.equals("") || this.isListExccedsLimit())
			{
				result = new ArrayList<SelectItem>();
			}
			else
			{
				result = this.getDbService().getSelectItems(query , ! mandatory , pm_userLang , m_con);
			}
			
		}
		catch (Exception e)
		{
			
			String msg = "Select List has an invalid SQL Query.. <"+this.getSelectListQuery()+"> ... Due To : "+e.getMessage()+" Please Correct The corresponding SQL Select Statement to be like \n\t Select Item_ID , Item_Desc from tableName";
			//ApplicationContext.getMessageCommnunicatorService().sendMessageToUser(msg);
			SysMsgServices sysmsgservices=this.getDbService().getModuleServiceContainer().getSysMsgServices();
			SysMsg sysMsg ; 
			try
			{
				sysMsg =sysmsgservices.getMsgSelectListHasinvalidSQLQuery();
				ArrayList<String>param=new ArrayList<String>();
				String selectlistquery=this.getSelectListQueryValue();
				param.add(selectlistquery);
				param.add(e.getMessage());
				sysMsg.setParams(param);
				this.getModuleServiceContainer().getMessageCommnunicatorService().sendMessageToUser(sysMsg);
			}
			catch (Exception ex)
			{
				this.getModuleServiceContainer().getMessageCommnunicatorService().sendMessageToUser(msg);
			}
			e.printStackTrace();
			result = new ArrayList<SelectItem>();
		}
//		this.calcSubQuerySelectItems();
		return (result != null)?  result : new ArrayList<SelectItem>();
	}
	
//	private void calcSubQuerySelectItems()
//	{
//		if(this.getHaveSubListValue().equalsIgnoreCase("Y"))
//		{
//			DataSet tmds = this.getTableMaintMaster().getTableMaintDetailss();
//			TableMaintDetails currentTmd = null;
//			for(PersistantObject po : tmds.getPersistantObjectList())
//			{
//				currentTmd = (TableMaintDetails) po;
//				if(currentTmd.getSelectListQueryValue().contains("@" + .getColumnNameValue()));
//				{
//					String query = currentTmd.getSelectListQueryValue();
//					String[] querySplitter = query.split("@");
//					String subQuery = querySplitter[0] + this.getValue();
//					currentTmd.setSelectListQueryValue(subQuery);
//				}
//			}
//		}
//	}
	
	public ArrayList<SelectItem> getQuerySelectItems(Connection loggedConnection , String callerObjectRowId) {
		ArrayList<SelectItem> v_result = null ; 
		int loggedUserLang = this.getDbService().getLoggedUserLang() ;
		if (this.isSimpleQuery() || callerObjectRowId== null)
		{
			
			if (! this.m_componentValues.containsKey(loggedUserLang) || this.m_componentValues.get(loggedUserLang) == null)
			{
				v_result = calcQuerySelectItems(loggedUserLang , loggedConnection , null );
				this.m_componentValues.put(loggedUserLang, v_result) ;
			}
			v_result = this.m_componentValues.get(loggedUserLang) ; 
		}
		else 
		{	// Select Items Dependent on the callerObjectRowId
			v_result = calcQuerySelectItems(loggedUserLang , loggedConnection , callerObjectRowId);
		}
		 return v_result ; 
	}

	public ArrayList<SelectItem> getQuerySelectItems() {
		int loggedUserLang = this.getDbService().getLoggedUserLang() ;
		if (! this.m_componentValues.containsKey(loggedUserLang))
		{
			this.m_componentValues.put(loggedUserLang, calcQuerySelectItems(loggedUserLang)) ;
		}
		return this.m_componentValues.get(loggedUserLang) ; 
	}

	/**
	 * @deprecated This method is developed by m.sakr and it is no longer needed
	 * @return
	 */
	@Deprecated
	public ArrayList<SelectItem> getFilteredQuerySelectItems() 
	{
		if(this.getSelectListQueryValue() != null)
		{
//			this.getUsrDefVarValue(userDefUniqueName);
		}
		else 
			return getQuerySelectItems();
		return null;
	}
	public void refreshQuerySelectItems() {
		Set<Integer> keySet = m_componentValues.keySet();
		for (Integer lang : keySet)
		{ 
		this.m_componentValues.put(lang, calcQuerySelectItems(lang)) ;
		}
	}
	

	public void setCondition(Condition pm_condition) {
		this.m_condition = pm_condition;
	}
	
	
	Condition blankCondition = null;
	private  Condition getBlankCondition()
	{
		if (blankCondition == null)
		{
			ParentPersistantObject ppo = this.getParent() ;
			TableMaintMaster tmm ;
			if (ppo== null)
			{ 
				DbTable dbTable = new DbTable(this.getOwnerValue() , this.getTableNameValue() , this.getDbService()) ;
				dbTable.setTableOwner(this.getOwnerValue());
				tmm = this.getModuleServiceContainer().getTableMaintServices().getTableMaintMaster(dbTable);
			}
			else
			{
				tmm = (TableMaintMaster)ppo.getPersistantObject();	
			}
			PersistantObject samplePo = tmm.getSamplePo() ; 
			if (samplePo != null)
			{
				Attribute att =  samplePo.getAttribute(this.getColumnNameValue() , true);
				blankCondition =  new Condition ( Operation.EQUAL,att);
			}
		}
		return blankCondition ; 
	}
	
	public Condition getCondition() {
		
		if (m_condition == null || m_condition.getAttribute()==null )
		{
			m_condition = getBlankCondition();
			
		}

		return m_condition;
	}
	
	
	 public boolean isFileUploader()
	 {
		return   this.getHtmlTypeImpl() instanceof com.smartValue.HtmlTypes.FileUploader ; 
	 }
	 
	 public boolean isOutputLabel()
	 {
		return   this.getHtmlTypeImpl() instanceof com.smartValue.HtmlTypes.OutputLabel ; 
	 }
	 
	 public boolean isInputText()
	 {
		return   this.getHtmlTypeImpl() instanceof com.smartValue.HtmlTypes.InputText ; 
	 }
	 
	 public boolean isInputTextArea()
	 {
		 return   this.getHtmlTypeImpl() instanceof com.smartValue.HtmlTypes.InputTextArea ; 
	 }
	 
	 public boolean isCalender()
	 {
		 return   this.getHtmlTypeImpl() instanceof com.smartValue.HtmlTypes.Calender ; 
	 }
	 public boolean isCharcterEditor()
	 {
		 return   this.getHtmlTypeImpl() instanceof com.smartValue.HtmlTypes.CharacterEditor ; 
	 }
	 public boolean isComboBox()
	 {
		 return   this.getHtmlTypeImpl() instanceof com.smartValue.HtmlTypes.ComboBox ; 
	 
	 }
	 public boolean isRichComboBox()
	 {
		 return   this.getHtmlTypeImpl() instanceof com.smartValue.HtmlTypes.RichComboBox ; 
	  
	 }
	 public boolean isRadioButton()
	 {
		 return   this.getHtmlTypeImpl() instanceof com.smartValue.HtmlTypes.RadioButtonGroup ; 
	 }
	 public boolean isPickList()
	 {
		 return   this.getHtmlTypeImpl() instanceof com.smartValue.HtmlTypes.PickList ; 
	 }
	 public boolean isHasList(){
		 return this.isComboBox()||this.isPickList()||this.isRadioButton() || this.isRichComboBox() || this.isTree();
	 }
	 public boolean isIgnoreCaseAndDeleteBtnRendered(){
		 if (this.getSelectedOperation() == null)
		 {
			 return false;
		 }
		 return true;
	 }
	 
	 public boolean isInputNumberSlider(){
		 return   this.getHtmlTypeImpl() instanceof com.smartValue.HtmlTypes.InputNumberSlider ; 
	 }
	 public boolean isInputTree(){
		 return   this.getHtmlTypeImpl() instanceof com.smartValue.HtmlTypes.SelectionTree ; 
	 }
	public void setSelectedOperation(String pm_selectedOperation) {
		//Prohibit IN Selection if not valid...
		if (( this.isComboBox() || this.isRadioButton() ) 
				&& pm_selectedOperation != null && pm_selectedOperation.equals(Operation.IN) )
		{
		this.m_selectedOperation = pm_selectedOperation;
		this.getCondition().setOperation(pm_selectedOperation);
		//this.setHtmlTypeValue(HtmlTypesOld.INPUT_PICK_LIST);
		this.getQuerySelectItems();

		}
		else if (pm_selectedOperation != null )
		{
			this.m_selectedOperation = pm_selectedOperation;
			this.getCondition().setOperation(pm_selectedOperation);
		}
	}
	public String getSelectedOperation() {
		return m_selectedOperation;
	}
	
//	public void setPickListChoices(String[] pickListChoices) {
//		
//		this.pickListChoices = pickListChoices;
//		Attribute att = new Attribute(this.getColumnNameValue(), pickListChoices, null);
//		att.setTableMaintDetail(this);
//		this.getCondition().setAttribute(att);
//	}
//	
//	public String[] getPickListChoices() {
//		return pickListChoices;
//	}


	
	private PersistantObject  geDataSetCurrentItem() 
	{
		//TODO I need To know Which TableManitServices should be used...
		TableMaintMaster tmm =  (TableMaintMaster) this.getParent().getPersistantObject();	
		PersistantObject result = null;		
		if (tmm.getTableContents() != null)
		result = tmm.getTableContents().getCurrentItem();
		return result;
	}
		
	 public boolean isCheckBox()
	 {
		 return   this.getHtmlTypeImpl() instanceof com.smartValue.HtmlTypes.CheckBox ; 
	 }
	 public boolean isRichInputText()
	 {
		 return   this.getHtmlTypeImpl() instanceof com.smartValue.HtmlTypes.RichInputText ; 
	 }
	 
	 public boolean isTree()
	 {
		 return   this.getHtmlTypeImpl() instanceof com.smartValue.HtmlTypes.SelectionTree ; 
	 }
	 public boolean isRadioButtonGroup()
	 {
		 return   this.getHtmlTypeImpl() instanceof com.smartValue.HtmlTypes.RadioButtonGroup ; 
	 }
	 
	public Attribute getObjectFieldAttribute() throws Exception
	{ 
		PersistantObject currentItem = geDataSetCurrentItem();
		if (currentItem == null) return null;
		String key = this.getColumnNameValue();
		Attribute att = currentItem.getAttribute(key.toUpperCase()); 
		
		return att;
	}
	
	public Object getObjectFieldValue() throws Exception
	{
		Object result ; 
		
		Attribute att = this.getObjectFieldAttribute();
		if (att == null ) return null;
		result=  att.getValue();
		if (this.isCheckBox())
		{
			if (result instanceof BigDecimal )
			result = (( (BigDecimal) result).intValue()== 1 ) ? true : false;
			
			else if (result instanceof String )
			result = (( (String) result).equalsIgnoreCase("Y")   ) ? true : false;	
		}
		return result;
	}
		
	public void setObjectFieldValue( Object pm_Object) throws Exception
	{
		String key = this.getColumnNameValue();
		PersistantObject currentItem = geDataSetCurrentItem();
		if (currentItem != null)
		{
			Attribute att = currentItem.getAttribute(key.toUpperCase()); 
			Object OriginalValue =  att.getOrginalValue();
			if (OriginalValue instanceof String && pm_Object instanceof Boolean  )
			{
				pm_Object = ((Boolean) pm_Object)? "Y" : "N" ;
			}
			if (OriginalValue instanceof BigDecimal && pm_Object instanceof Boolean  )
			{
				pm_Object = ((Boolean) pm_Object)? new BigDecimal(1) : new BigDecimal(0) ;
			}
			currentItem.getAttribute(key.toUpperCase()).setValue(pm_Object);
		}
	}
	

	public DataSet  calcExcludedRolesDS(String pm_operation) 
	{
		DataSet result = null;
		
		TreeMap<String, Object> extraWhere = new TreeMap<String, Object>();
		extraWhere.put(ExcludedRolesDetails.RENDERED_OR_DISABLED, pm_operation);
		try {
			result=this.getChildrenDataSet(EXCLUDED_ROLES_TABLE_NAME+pm_operation, ExcludedRolesDetails.class ,true , extraWhere);
		} catch (Exception e) {
			Console.log("\n\n Error   in  calcExcludedRolesDS \n", this.getClass());
			e.printStackTrace();
		}
		
		return  result;
	}

	DataSet  excludedRolesDSForRendered;
	public DataSet getExcludedRolesDSForRendered() {
		if (excludedRolesDSForRendered == null)
		{
			excludedRolesDSForRendered=this.calcExcludedRolesDS(EXCLUDED_ROLES_RENDERED);
		}
		return excludedRolesDSForRendered;
	}
	DataSet  excludedRolesDSForDisabled;	
	public DataSet getExcludedRolesDSForDisabled() {
		if (excludedRolesDSForDisabled == null)
		{
			excludedRolesDSForDisabled=this.calcExcludedRolesDS(EXCLUDED_ROLES_DISABLED);	
		}
		return excludedRolesDSForDisabled;
	}
	
	private static DbForignKeyArray getForignKeyForExcludedRoles()
	{
		return new DbForignKeyArray ( new DbForignKey[]{
				                new DbForignKey(TableMaintDetails.TABLE_NAME, ExcludedRolesDetails.TABLE_NAME ,EXCLUDED_ROLES_TABLE_NAME , TableMaintMaster.CDB_SCHEMA_OWNER) ,
								new DbForignKey(TableMaintDetails.COLUMN_NAME, ExcludedRolesDetails.COLUMN_NAME,EXCLUDED_ROLES_TABLE_NAME , TableMaintMaster.CDB_SCHEMA_OWNER) } , false );
	}
	
	@Override
	public HashMap<String, DbForignKeyArray> getChildrenForignKeys() {
		if (childrenForignKeys == null) 
		{
			childrenForignKeys = new HashMap<String, DbForignKeyArray>();
			childrenForignKeys.put(EXCLUDED_ROLES_TABLE_NAME+EXCLUDED_ROLES_RENDERED, TableMaintDetails.getForignKeyForExcludedRoles());
			childrenForignKeys.put(EXCLUDED_ROLES_TABLE_NAME+EXCLUDED_ROLES_DISABLED, TableMaintDetails.getForignKeyForExcludedRoles());
			childrenForignKeys.put(ColumnChangeNotifiers.DB_TABLE_NAME, TableMaintDetails.getForignKeyForChangeNotifers());

		}
		return childrenForignKeys;
	}
	public PersistentObjectSecurityControl getSecurityController() 
	{
	 return tmdSecurityControlHandler;
	}
	@Override
	public TriggerHandler getTriggerHandler() 
	{
		// TODO Auto-generated method stub
	 return null; 
	}
	
	public boolean isSecured() {
		boolean result = false;
		String defaultAccessType = this.getDefaultAccessTypeValue() ;
		SecUsrDta loggedUser = (SecUsrDta) this.getDbService().getLoggedUser() ; //((UserSession)SWAF.getManagedBean("userSession")).getOperator();
		if (loggedUser != null)
		{
			boolean loggedUserInExcludedGroups = loggedUser.isMemberInGroups(getExcludedGroupsIds());
			
			if(defaultAccessType.equals(DENY_ACCESS_TO_ALL)){
				result = ! loggedUserInExcludedGroups ; 
			}
					
			else if(defaultAccessType.equals(ALLOW_ACCESS_TO_ALL)){
				result =   loggedUserInExcludedGroups ;
			}
		}
		return result;
	}
	
	public String getDefaultAccessTypeValue()
	{
		return (super.getDefaultAccessTypeValue()==null)? ALLOW_ACCESS_TO_ALL : super.getDefaultAccessTypeValue() ;
	}
	

	public void setExcludedGroupsIds(String[] pm_selectedGroups) {
		try {			
			DataSet ds = this.calcExcludedRolesDS(EXCLUDED_ROLES_RENDERED);;
			String column2BeUpdated = ExcludedRolesDetails.SECGROUP_ID ; 
			ds.setValues( column2BeUpdated, pm_selectedGroups );
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public String[] getExcludedGroupsIds() {
		String[] result = new String[0];
		try {
			DataSet ds = this.calcExcludedRolesDS(EXCLUDED_ROLES_RENDERED); ;
			String columnName = ExcludedRolesDetails.SECGROUP_ID ;
			result = ds.getValues(columnName);
		
			} catch (Exception e) {
				getMessageCommnunicatorService().sendMessageToUser(
						"Unable to get table maintainance details groups due to "+ e.getMessage());
			}
		return result;
	}
	

	//public AttributeChangeValidator changeValidator ;
	@Override 
	public AttributeChangeValidator getAttributeChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key)
	{ 
		//if (changeValidator == null) 
		return  new TableMaintDetailsChangeValidator(pm_secUserData , pm_po  , pm_key); 
		//return changeValidator; 
	}


	public void setAccessType(String pm_accessType) {
		try {
			String currentAccessType = this.getAccessType();
			if(currentAccessType != null && !currentAccessType.equals(pm_accessType))
				this.calcExcludedRolesDS(EXCLUDED_ROLES_RENDERED).markAllToBeDeleted();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setDefaultAccessTypeValue(pm_accessType);
	}
	public String getAccessType() {
		return this.getDefaultAccessTypeValue();
	}
	
		
	public static final String DEFAULT_JSF_File = "/templates/include/controls/inputText.xhtml"; 

	public static final String SIMPLE_PROTECTION = "1";
	public static final String Protection_Using_Users_And_Groups = "2";
	public static final String Protection_Using_UDV = "3";
	public static final String HTML_TYPES_INTERFACE = "HtmlTypes";
	public static final String INPUT_TEXT = "1";
	public static final String SELECT_ONE_MENU = "3";
	public static final String INPUT_CALENDER = "4";
	public static final String INPUT_TEXT_AREA = "7";
	public static final String SELECT_BOOLEAN = "9";

	public String getSpecificPropertiesPage()
	{
		
		String result = new OutputLabel().getSpecificPropertiesPage() ;
		IHtmlType htmlType = this.getHtmlTypeImpl();
		if (htmlType == null) htmlType = new InputText();
		String resultFromImpl=  htmlType.getSpecificPropertiesPage();
		result = (this.checkImplPageExist (resultFromImpl)) ? resultFromImpl : result;
		return result ;
	}
	
	private String calcJsfTemplateFile()
	{
		String result = new OutputLabel().getJsfTemplateFile() ;
		IHtmlType htmlType = this.getHtmlTypeImpl();
		if (htmlType == null) htmlType = new InputText();
		String resultFromImpl= htmlType.getJsfTemplateFile();
		result = (this.checkImplPageExist (resultFromImpl)) ? resultFromImpl : result;
		return result ;
	}
	private boolean checkImplPageExist(String pageUrl)
	{
		//TODO Sakr : Please Call the method you have did before to check if the file already exist or not 
		return true ; 
	}
	
	protected IHtmlType htmlTypeImpl ; 
	public IHtmlType getHtmlTypeImpl() {
		if(htmlTypeImpl == null)
			refreshHtmlType();
		return htmlTypeImpl ; 
	}
	
	public void setHtmlTypeImpl(IHtmlType pm_htmlType)
	{
		this.htmlTypeImpl = pm_htmlType ; 
	}
	
	
	private boolean newHtmlTypeAllowd = true ;
//	public void setHtmlTypeValue(java.lang.String   pm_htmlType){
//		String orignalValue = this.getHtmlTypeValue() ; 
//		super.setHtmlTypeValue(pm_htmlType);
//		htmlTypeImpl = calcHtmlImpl();
//		specificAttributes = this.calcSpecificAttributes(htmlTypeImpl);
//		ValidationResult validationResult = htmlTypeImpl.isAllowedForDataType(this.getDataTypeValue() , this.getDbService());
//		setNewHtmlTypeAllowd(validationResult.isValidResult());
//		if (! validationResult.isValidResult())
//		{
//			ArrayList<String> param = new ArrayList<String>();
//			param.add(TableMaintDetails.getHtmlTypeDesc(pm_htmlType , this.getDbService()));
//			param.add(this.getDataTypeValue());
//			SysMsg sysMsg = ApplicationContext.getSysMsgServices(getDbService()).getInvalidHtmlTypeMsg();
//			sysMsg.setParams(param);
//			ApplicationContext.getSysMsgServices(this.getDbService()).setCurrentActiveMessage(sysMsg);
//			String msg = sysMsg.getMsgDescWithParam();
//			validationResult.setInvalidMessage(msg);
//			ApplicationContext.getMessageCommnunicatorService().sendValidationresultToUser(validationResult);
//			//Undo Changes 
//			super.setHtmlTypeValue(orignalValue);
//			htmlTypeImpl = calcHtmlImpl();
//			specificAttributes = this.calcSpecificAttributes(htmlTypeImpl );
//		}
//	}
	public static String getHtmlTypeDesc(String pm_HtmlType , DbServices dbs)
	{
		InterfaceImplServices ims = new InterfaceImplServices(dbs);
		SysInterfaceImplementors sii =  ims.getImplementorByCode(pm_HtmlType , HTML_TYPES_INTERFACE);
		return (String) sii.getImpDescAutoLang().getValue();
	}

	private IHtmlType calcHtmlImpl()
	{
		return TableMaintDetails.calcHtmlImpl(this.getHtmlTypeValue() , this.getDbService());
	}
	public static IHtmlType calcHtmlImpl(String htmlType , DbServices dbs)
	{
		InterfaceImplServices ims = new InterfaceImplServices(dbs);
		htmlType = (htmlType == null) ? TableMaintDetails.SIMPLE_PROTECTION : htmlType ;
		SysInterfaceImplementors sii =  ims.getImplementorByCode(htmlType , TableMaintDetails.HTML_TYPES_INTERFACE);
		return (sii!= null)? (IHtmlType)sii.getImplInstance() : new InputText();
	}

	ArrayList<Attribute> specificAttributes;
	public ArrayList<Attribute> getSpecificAttributes()
	{
		if (specificAttributes== null)
		{
			specificAttributes = this.calcSpecificAttributes(this.getHtmlTypeImpl());
		}
		return specificAttributes ; 
	}
	
	public void setSpecificAttributes(ArrayList<Attribute> pm_specificAttributes)
	{
		specificAttributes = pm_specificAttributes ;
	}
	public ArrayList<Attribute> calcSpecificAttributes(IHtmlType pm_html )
	{ 
		ArrayList<Attribute> result = new ArrayList<Attribute>();
		if (pm_html != null)
		{
			ArrayList<String> xx =  pm_html.getSpecificTmdAttributes();
		
			for (String attName : xx)
			{
				result.add(this.getAttribute(attName));
			}
		}
		return result;
	}

	public TreeBeanGeneric getQueryTreeBean() throws Exception
	{
		if (queryTreeBean == null)
		{
			DbServices ds = this.getDbService() ; 
			String query = (String) this.getSelectListQueryValue();
			DataSet dataSetForTree = ds.queryForDataSet(query, PersistantTreeObject.class);
			
			queryTreeBean = new TreeBeanGeneric();
			queryTreeBean.setAllItems(dataSetForTree);
		}
		
		return queryTreeBean;
	}
	
	public boolean equals(Object otherObject)
	{
		if (! (otherObject instanceof TableMaintDetails))
			return false;
		else{
			TableMaintDetails otherTmd = (TableMaintDetails)otherObject;
			return     this.getTableNameValue().equals(otherTmd.getTableNameValue())
					&& this.getColumnNameValue().equals(otherTmd.getColumnNameValue())
					&& this.getOwnerValue().equals(otherTmd.getOwnerValue());
		}
		
	}

	

//	public Attribute getAttributeFromPo(PersistantObject po)
//	{
//		return po.getAttribute(this.getColumnNameValue());
//	}
	

	public boolean isRenderCalcuSimple()
	{
		return this.getRenderBooleanImpl() instanceof AttBooleanSimpleImpl ;
	}
	
	public boolean isRenderCalcuUdv()
	{
		return this.getRenderBooleanImpl() instanceof AttBooleanUsingUserDefVar ;
	}
	
	public boolean isRenderCalcuUsers()
	{
		return this.getRenderBooleanImpl() instanceof AttBooleanUsingUsersAndGroups ;
	}
	
	public boolean isDisabledCalcuSimple()
	{
		return this.getDisabledBooleanImpl() instanceof AttBooleanSimpleImpl ;
	}
	
	public boolean isDisabledCalcuUdv()
	{
		return this.getDisabledBooleanImpl() instanceof AttBooleanUsingUserDefVar ;
	}
	
	public boolean isDisabledCalcuUsers()
	{
		return this.getDisabledBooleanImpl() instanceof AttBooleanUsingUsersAndGroups ;
	}
	
	private AttributePropertyController disabledBooleanImpl = null ;	

	public void setDisabledBooleanImpl(AttributePropertyController pm_disabledBooleanImpl) {
		this.disabledBooleanImpl = pm_disabledBooleanImpl;
	}

	public AttributePropertyController getDisabledBooleanImpl() {
		if(disabledBooleanImpl == null)
			refreshDisableProtectionType();
		return disabledBooleanImpl; 
	}
	
	public void setDisableProtectionTypeValue(java.lang.String   pm_DisabledProtectionType){
		super.setDisableProtectionTypeValue(pm_DisabledProtectionType);
		String implenCalssCode = this.getDisableProtectionTypeValue();
		AttributePropertyController disabledBooleanImpl = this.calcAttBooleanType(implenCalssCode);
		this.setDisabledBooleanImpl(disabledBooleanImpl);
	}

	private AttributePropertyController renderBooleanImpl = null ;

	public void setRenderBooleanImpl(AttributePropertyController renderboolenImpl) {
		this.renderBooleanImpl = renderboolenImpl;
	}

	public AttributePropertyController getRenderBooleanImpl() {
	
		if(renderBooleanImpl == null)
			refreshRenderedProtectionType();
		return renderBooleanImpl; 
	}
	
	public void setRenderProtectionTypeValue(java.lang.String   pm_renderProtectionType){
		super.setRenderProtectionTypeValue(pm_renderProtectionType);
		String implenCalssCode = this.getRenderProtectionTypeValue();
		AttributePropertyController renderBooleanImpl = this.calcAttBooleanType(implenCalssCode);
		this.setRenderBooleanImpl(renderBooleanImpl);
	}
	
	private AttributePropertyController calcAttBooleanType(String implenCalssCode)
	{
		InterfaceImplServices ims = new InterfaceImplServices(this.getDbService());
		AttributePropertyController attBooleanType = new AttBooleanSimpleImpl();
		SysInterfaceImplementors sysImpl = 
		ims.getImplementorByCode(implenCalssCode,AttributePropertyController.INTERFACE_NAME);
		attBooleanType = (sysImpl!=null)? (AttributePropertyController)sysImpl.getImplInstance() : new AttBooleanSimpleImpl() ;
		return attBooleanType;
	}
	
	public ArrayList<AttributeChangeNotifier> getChangeNotifiers() {
		ArrayList<AttributeChangeNotifier> changeNotifiers = new ArrayList<AttributeChangeNotifier>(); ; 
		ArrayList<PersistantObject> cns = this.getChangeNotifiersDs().getPersistantObjectList();
		if (cns!=null && ! cns.isEmpty())
		{
			for (PersistantObject po : cns)
			{
				ColumnChangeNotifiers ccn = (ColumnChangeNotifiers) po ;
				AttributeChangeNotifier acn =  ccn.getAttChangeNotifier();
				changeNotifiers.add(acn);
			}
		}
		
		return changeNotifiers ;
	}
	public String[] getUsersNeedToByModified() {
		// TODO Auto-generated method stub
		String[] result = {"foda.sh@gmail.com"};
		return result;
	}
	
	DataSet changeNotifiersDs ; 
	public DataSet getChangeNotifiersDs() 
	{
			try
			{
				changeNotifiersDs =  this.getChildrenDataSet(ColumnChangeNotifiers.DB_TABLE_NAME , ColumnChangeNotifiers.class , false);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		
		return changeNotifiersDs;
	}
	
	
	public static DbForignKeyArray getForignKeyForChangeNotifers()
	{
		return new DbForignKeyArray( new DbForignKey[]{new DbForignKey(TableMaintDetails.TABLE_NAME ,ColumnChangeNotifiers.TABLE_NAME )
								,new DbForignKey(TableMaintDetails.OWNER ,ColumnChangeNotifiers.TABLE_OWNER)
								,new DbForignKey(TableMaintDetails.COLUMN_NAME ,ColumnChangeNotifiers.COLUMN_NAME)
		} , false );
	}
	public void setNewHtmlTypeAllowd(boolean newHtmlTypeAllowd) {
		this.newHtmlTypeAllowd = newHtmlTypeAllowd;
	}
	public boolean isNewHtmlTypeAllowd() {
		return newHtmlTypeAllowd;
	}
	
	public Object getEmptyInstanceOfColumnType() throws Exception
	{
		Object result = null ;
		if ((this.getDataTypeValue().contains("VARCHAR")))
		{
			result =  new String();
		}
		else if (this.getDataTypeValue().equalsIgnoreCase("NUMBER"))
			result =  null;// new BigDecimal(0);
		else if (this.getDataTypeValue().equalsIgnoreCase("DATE"))
			result =  new java.sql.Timestamp(new Date().getTime());
		
		return result ;
	}
	
	private Facet facet  = null;
	public void setFacet(Facet facet)
	{
		this.facet = facet;
	}
	public Facet getFacet()
	{
		if(facet == null)
			this.facet = new Facet((String) this.getDisplayNameAutoLang().getValue(), null);
		return facet;
	}

	public void initialize() 
	{
//		boolean webApp = ApplicationContext.isWebApp(); 
//		if (! webApp)
//		{
//			//return ;
//		}
//		Integer userLange = this.getDbService().getLoggedUserLang() ;
//		this.m_componentValues.put(userLange, this.calcQuerySelectItems(userLange));
//		this.refreshHtmlType() ;
//		this.facet = new Facet((String) this.getDisplayNameAutoLang().getValue(), null); 
//		this.refreshRenderedProtectionType();
//		this.refreshDisableProtectionType ();
//		this.columnNameToSwitch = this.getColumnNameValue() ;
//		String dataType = this.getDataTypeValue() ; sd
//		this.numeric =  dataType.equalsIgnoreCase(NUMBER_DATA_TYPE) 
//			|| dataType.equalsIgnoreCase(FLOAT)   ; 
		
//		this.date = ! numeric && ( dataType.equalsIgnoreCase(DATE_DATA_TYPE) 
//									|| dataType.equalsIgnoreCase(TIMESTAMP) ) ;
	
	}
	
	private String jsfTemplateFile ; 
	public String getJsfTemplateFile() {
		if(jsfTemplateFile == null)
			refreshHtmlType();
		return jsfTemplateFile;
	}
	public void refreshJsfTemplateFile()
	{
			jsfTemplateFile = this.calcJsfTemplateFile();
	}
	
	public void refreshHtmlType()
	{
		this.htmlTypeImpl = this.calcHtmlImpl();
		this.refreshJsfTemplateFile() ; 		
//		this.refreshQuerySelectItems();
		this.setSpecificAttributes(this.calcSpecificAttributes(this.htmlTypeImpl));
	}
	public void refreshDisableProtectionType()
	{
		this.disabledBooleanImpl = this.calcAttBooleanType(this.getDisableProtectionTypeValue()) ; 
	}
	
	public void refreshRenderedProtectionType()
	{
		this.renderBooleanImpl =   this.calcAttBooleanType(this.getRenderProtectionTypeValue()) ;
	}
	private boolean numberOrDate;
	
	public void setNumberOrDate(boolean numberOrDate) {
		this.numberOrDate = numberOrDate;
	}
	public boolean isNumberOrDate() {
		return numberOrDate;
	}
	private void checkOnDataType(String pm_DataType){
		this.setNumberOrDate((pm_DataType.equalsIgnoreCase("Number")||pm_DataType.equalsIgnoreCase("DATE"))) ;
	}
	public void setColumnNameToSwitch(String columnNameToSwitch) {
		this.columnNameToSwitch = columnNameToSwitch;
		if (columnNameToSwitch != null)
		{
			TableMaintDetails tableMaintDetails;
			try
			{
				TableMaintMasterServices tmms =  this.getDbService().getModuleServiceContainer().getTableMaintServices() ;
				tableMaintDetails = tmms.getTableMaintDetails(this.getTableNameValue() , columnNameToSwitch);
				this.setDataTypeValue(tableMaintDetails.getDataTypeValue());
				this.setHtmlTypeValue(tableMaintDetails.getHtmlTypeValue());
				this.setCondition(tableMaintDetails.getBlankCondition());
				this.setSelectListQueryValue(tableMaintDetails.getSelectListQueryValue());
				this.setColumnNameValue(tableMaintDetails.getColumnNameValue());
				this.setTableNameValue(tableMaintDetails.getTableNameValue());
				this.checkOnDataType(tableMaintDetails.getDataTypeValue());
				
			} catch (Exception e)
			{
				
				e.printStackTrace();
			}			
		}
	}
	public String getColumnNameToSwitch() {
		if(columnNameToSwitch == null)
			this.columnNameToSwitch = this.getColumnNameValue();
		return columnNameToSwitch;
	}
	private ArrayList<PersistantObject> getChangeNotiferAsSysInterfaceImpementor() {
		ArrayList<PersistantObject> result = new ArrayList<PersistantObject> () ; 
		String  IMP_INTERFACE  = AttributeChangeNotifier.class.getName() ; 
		String query=" select t.* from  COLUMN_CHANGE_NOTIFIERS,SYS_INTERFACE_IMPLEMENTORS t where  (COLUMN_CHANGE_NOTIFIERS.CHANGE_NOTIFY_IMPL_ID=t.IMP_CODE  and  IMP_INTERFACE='"+IMP_INTERFACE+"')and TABLE_NAME='"+this.getTableNameValue()+"' and COLUMN_NAME='" +this.getColumnNameValue()+"'";
		try {
			result= (ArrayList<PersistantObject>) this.getDbService().getLoggedUser().getDbService().queryForList(query,SysInterfaceImplementors.class);
			} 
		catch (Exception e) 
			{e.printStackTrace();}
	return result;
	}
	
	DataSetPickList  dataSetForPickList ; 
	public DataSetPickList getDataSetForPickList() {
		if (dataSetForPickList == null)
		{
			ArrayList<PersistantObject> initialValue=getChangeNotiferAsSysInterfaceImpementor();
		    dataSetForPickList  = new DataSetPickList(ColumnChangeNotifiers.CHANGE_NOTIFY_IMPL_ID  , SysInterfaceImplementors.IMP_CODE ,this.getChangeNotifiersDs(), initialValue);
		    ArrayList<SelectItem> allAvailableItems = this.getModuleServiceContainer().getTableMaintServices().getAllAvailableNotificationsTypes();
		    dataSetForPickList.setAllAvailableItems(allAvailableItems);
		    dataSetForPickList.setConverterId("SysInterfImplConvert");
		}
		return dataSetForPickList;
	}
	public void setDataSetForPickList(DataSetPickList obj) {
		this.dataSetForPickList = obj;
	}
	//..............................................................................................
	DataSetPickList        datasetPickListOfSelectedGroupsForRendered;
	
	
	public DataSetPickList getDatasetPickListOfSelectedGroupsForRendered() throws Exception 
	{
		if(datasetPickListOfSelectedGroupsForRendered==null)
		{
			ArrayList<PersistantObject> initialValue = this.getinitialGroupsForRENDERED();
			
			datasetPickListOfSelectedGroupsForRendered=new  DataSetPickList( "SECGROUP_ID"  ,"USR_NAME"  ,getExcludedRolesDSForRendered() , initialValue );
			ArrayList<SelectItem> allAvailableItems = this.getModuleServiceContainer().getSecUserDataService().getAllAvailableUsers();
			datasetPickListOfSelectedGroupsForRendered.setAllAvailableItems(allAvailableItems );
			datasetPickListOfSelectedGroupsForRendered.setConverterId("converterForSysTableSecurity");
		}
		return datasetPickListOfSelectedGroupsForRendered;
	}
	
	private ArrayList<PersistantObject> getinitialGroupsForRENDERED() {
		ArrayList<PersistantObject> result = new ArrayList<PersistantObject> () ; 
		String query="Select t.* , t.rowid from  "+ TableMaintMaster.CDB_SCHEMA_OWNER+".EXCLUDED_ROLES_DETAILS,"+ TableMaintMaster.CDB_SCHEMA_OWNER+".SEC_USR_DTA t where EXCLUDED_ROLES_DETAILS.SECGROUP_ID =t.USR_NAME and EXCLUDED_ROLES_DETAILS.COLUMN_NAME='"+this.getColumnNameValue()+"' and RENDERED_OR_DISABLED='RENDERED'  " ;
		try {
			result= (ArrayList<PersistantObject>) this.getDbService().getLoggedUser().getDbService().queryForList(query,SecUsrDta.class);
			} 
		catch (Exception e) 
			{e.printStackTrace();}
	return result;
	}
    //.................................................................................................
	DataSetPickList        datasetPickListOfSelectedGroupsForDisabled;
	
	public DataSetPickList getDatasetPickListOfSelectedGroupsForDisabled() throws Exception{
		if(datasetPickListOfSelectedGroupsForDisabled==null)
		{
			ArrayList<PersistantObject> initialValue=this.getInitialGroupsForDISABLED();
			datasetPickListOfSelectedGroupsForDisabled =new DataSetPickList( "SECGROUP_ID"  ,"USR_NAME"  ,this.getExcludedRolesDSForDisabled(), initialValue);
			ArrayList<SelectItem> allAvailableItems = this.getModuleServiceContainer().getSecUserDataService().getAllAvailableUsers();
			datasetPickListOfSelectedGroupsForDisabled.setAllAvailableItems(allAvailableItems );
			datasetPickListOfSelectedGroupsForDisabled.setConverterId("converterForSysTableSecurity");
		}
		
		return datasetPickListOfSelectedGroupsForDisabled;
	}

	private ArrayList<PersistantObject> getInitialGroupsForDISABLED() {
		ArrayList<PersistantObject> result = new ArrayList<PersistantObject> () ; 
		String query="Select t.* , t.rowid from "+ TableMaintMaster.CDB_SCHEMA_OWNER+".EXCLUDED_ROLES_DETAILS, "+ TableMaintMaster.CDB_SCHEMA_OWNER+".SEC_USR_DTA t where EXCLUDED_ROLES_DETAILS.SECGROUP_ID =t.USR_NAME and EXCLUDED_ROLES_DETAILS.COLUMN_NAME='"+this.getColumnNameValue()+"' and RENDERED_OR_DISABLED='DISABLED'  " ;
		try {
			result= (ArrayList<PersistantObject>) this.getDbService().getLoggedUser().getDbService().queryForList(query,SecUsrDta.class);
			}  
		catch (Exception e) 
			{e.printStackTrace();}
	return result;
	}
	 
	
	//..............................................................................................
	public void goToParentTableMaint()
	{
		//1- Adjust SecUserDataService ;
		String tableName = this.getTableNameValue();
		ModuleServicesContainer msc =  this.getModuleServiceContainer(); 
		TableMaintMasterServices tms =  msc.getTableMaintServices();
		tms.setTableName(tableName);
		//tms.setDataSetWithCondition(" Where " + SecUsrDta.USR_NAME +"='"+ assinedTo +"'" , false);
		
		//2- Simulate as if User clicked on Tree Node for Sec Users Maintainance 
		msc.getSysMenuServices().goToSysMnu("TableMaintMaster");
		
	}
	private Integer querySelectItemsCount  ; 
	
	
	private Integer calcQuerySelectItemsCount()
	{
		Integer result = null ;//= this.getQuerySelectItems().size();	
		
		String query =this. getSelectListQueryValue() ;
		if (query != null)
		{
			 query = "select count(*) RESULT from ( \n" + query + "\n ) " ;
			 DataSet ds = this.getDbService().queryForDataSet(query, null) ;
			
			 if (ds != null && ! ds.getPersistantObjectList().isEmpty())
			 {
				result = Integer.parseInt( String.valueOf( ds.getPersistantObjectList().get(0).getAttributeValue("RESULT") )) ;
			 }
		}
		return result ; 
	}

	public Integer getQuerySelectItemsCount() {
		if (querySelectItemsCount == null)
		{
			refreshQuerySelectItemsCount() ;
		}
		return querySelectItemsCount;
	}
	public void refreshQuerySelectItemsCount()
	{
		querySelectItemsCount = calcQuerySelectItemsCount() ;
	}
	
	public boolean  isListExccedsLimit()
	{
		boolean result = false ; 
		Integer selectItemsCount = this.getQuerySelectItemsCount();
		BigDecimal listThreshold = this.getListThresholdValue() ;
		
		if (selectItemsCount != null && listThreshold != null)
		{
			result = selectItemsCount.intValue() >  listThreshold.intValue()  ;
		}
		return result ; 
	}
	
	public void clear()
	{
		 this.m_condition = null;
		 m_componentValues.clear();
		 this.logicalOperation = null;
		 this.firstItemInlist = false; 
		 this.m_selectedOperation = null;
		 this.columnNameToSwitch =  null; 
		 this.queryTreeBean = null;
		 tmdSecurityControlHandler = null;
		 tmdSecurityControlHandler = new TableMaintDetailsSecurityControlHandlerImpl();
		 this.excludedRolesDSForRendered = null;
		 this.specificAttributes = null;
		 this.disabledBooleanImpl = null;
		 this.renderBooleanImpl = null;
		 this.changeNotifiersDs = null;
		 this.facet = null;
		 this.jsfTemplateFile = null;
		 this.dataSetForPickList = null;
		 this.datasetPickListOfSelectedGroupsForRendered = null;
		 this.datasetPickListOfSelectedGroupsForDisabled = null;
		 this.querySelectItemsCount = null;
	}
	
	@Override
	public void beforeAttributeChange(Attribute pm_att) throws Exception {
		if (pm_att.getKey().equals(TableMaintDetails.HTML_TYPE))
		{
		}
	}

	@Override
	public void afterAttributeChange(Attribute pm_att) {
		if (pm_att.getKey().equals(TableMaintDetails.HTML_TYPE))
		{
			this.afterHtmlTypeChange(pm_att);
		}
		else if (pm_att.getKey().equals(TableMaintDetails.SELECT_LIST_QUERY))
		{
			this.afterSelectListQueryChange(pm_att);
		}
		else if (pm_att.getKey().equals(TableMaintDetails.NULLABLE))
		{
			this.afterNullableChange(pm_att);
		}
		else if (pm_att.getKey().equals(TableMaintDetails.DISABLE_PROTECTION_TYPE))
		{
			this.disableProtectionTypeChange(pm_att);
		}
		
		else if (pm_att.getKey().equals(TableMaintDetails.RENDER_PROTECTION_TYPE))
		{
			this.renderedProtectionTypeChange(pm_att);
		}
	}
	Attribute listThreshold = null;
	public Attribute getListThreshold()
	{
		if (listThreshold == null )
		{
			listThreshold = super.getListThreshold();
			listThreshold.addChangeListner(new AttChangeListner() 
				{
					
					public void beforeChange(Attribute att) throws Exception {
					}
					
					
					public void afterChange(Attribute att) {
						refreshQuerySelectItems();
					}
				}) ;
		}
		
		return listThreshold; 
	}
	private void disableProtectionTypeChange(Attribute pmAtt) {
		this.refreshDisableProtectionType() ; 
		
	}
	
	private void renderedProtectionTypeChange(Attribute pmAtt) {
		this.refreshRenderedProtectionType() ; 
		
	} 
	private void afterNullableChange(Attribute pmAtt) {
		this.refreshQuerySelectItems();
	}
	private void afterSelectListQueryChange(Attribute pmAtt) {
		this.refreshQuerySelectItems();
		
	}
	private void afterHtmlTypeChange(Attribute pmAtt) {
		this.refreshHtmlType();

		
	}
	private AllConstraints  refrancedConstraint ;
	private int refrancedConstraintPosition = -1;
	/**
	 * 
	 * @return the associated refrational Constrain if exist 
	 */
	private AllConstraints getRefrancedConstraint()
	{
		if (refrancedConstraint == null)
		{
			TableMaintMaster tmm =  (TableMaintMaster) this.getParent().getPersistantObject() ;
			for ( PersistantObject po :  tmm.getRefConstraintsDS().getPersistantObjectList())
			{
				AllConstraints ac = (AllConstraints) po;
				int counter = -1 ;
				DataSet cc = ac.getConstraintColumnsDS(); 
				if (cc != null)
				{
					for ( PersistantObject po2 : ac.getConstraintColumnsDS().getPersistantObjectList())
					{
						counter++;
						AllConsColumns acc = (AllConsColumns) po2 ;
						if ( 	acc.getOwnerValue().equals(this.getOwnerValue()) 
								&& acc.getTableNameValue().equals(this.getTableNameValue()) 
								&& acc.getColumnNameValue().equals(this.getColumnNameValue())) 
						{
							refrancedConstraint = ac.getRefrencedConstraint() ;
							refrancedConstraintPosition = counter;
						}
					}
				}
			}
		}
		return refrancedConstraint ;
	}
	
	public void setSelectQueryListFromFK()
	{
		AllConstraints rc = this.getRefrancedConstraint();
		if (rc != null && refrancedConstraintPosition >= 0 )
		{
			String refrencedColumn = ((AllConsColumns)rc.getConstraintColumnsDS().getPersistantObjectList().get(refrancedConstraintPosition)).getColumnNameValue(); 
			String query = "Select " + refrencedColumn +" ID"+  "," + refrencedColumn +" ANAME"+ "," + refrencedColumn +" ENAME"+ " From " +rc.getOwnerValue()+"."+ rc.getTableNameValue() ;
			this.setHtmlTypeValue(SELECT_ONE_MENU);
			this.setListThresholdValue(new BigDecimal(100));
			this.setSelectListQueryValue(query);
		}
	}
	
	 public DbTable getTable() 
	 { 
		 DbTable result = new DbTable(TableMaintMaster.CDB_SCHEMA_OWNER  ,DB_TABLE_NAME , this.getDbService()); ;
		return  result ; 
	 } 
	 
	 protected static String boundVarInit = "$$";
	 protected static String refQueryInit = "@@";
	 private Boolean simpleQuery = null;
 
	protected boolean isSimpleQuery()
	{
		if (simpleQuery == null)
		{
			String query = (String) this.getSelectListQueryValue();
			simpleQuery = (query == null ) || ( query.indexOf(boundVarInit) == -1 && query.indexOf(refQueryInit) == -1) ; 
		}
		return  simpleQuery ;
	}
}