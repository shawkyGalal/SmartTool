package com.smartValue.database.map;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.model.SelectItem;

import com.sideinternational.sas.BaseException;
import com.sideinternational.sas.service.Operator;
import com.sideinternational.sas.service.Zone;
import com.smartValue.database.ApplicationContext;
import com.smartValue.database.Attribute;
import com.smartValue.database.AttributeChangeValidator;
import com.smartValue.database.DataSet;
import com.smartValue.database.DataSetPickList;
import com.smartValue.database.DbForignKey;
import com.smartValue.database.DbForignKeyArray;
import com.smartValue.database.DbServices;
import com.smartValue.database.ParentPersistantObject;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.Query;
import com.smartValue.database.ValidationResult;
import com.smartValue.database.audit.AuditInDbTriggerHandler;
import com.smartValue.database.map.auto._SecUsrDta;
import com.smartValue.database.map.security.PersistentObjectSecurityControl;
import com.smartValue.database.map.security.SecUserSecurityControlHandler;
import com.smartValue.database.map.services.ModuleServicesContainer;
import com.smartValue.database.map.services.SystemMenusServices;
import com.smartValue.database.trigger.TriggerHandler;
import com.smartValue.jsf.components.MultiPageControl;
import com.smartValue.jsf.components.MultiPagesDisplayable;
import com.smartValue.jsf.components.PageControl;

public class SecUsrDta extends _SecUsrDta implements Operator ,MultiPagesDisplayable {

	private static final long serialVersionUID = 1L;
	public static final int USER = 0;
	public static final int GROUP = 1;
	private AuditControler  auditControlTobeUpdated ; 
	private PersistentObjectSecurityControl sch ; 
	public static final String ADMINISTRATOR = "1";
	private MasCompanyData userCompany;
	private HashMap<String, TableUserView> tableUserViewMap;

	private static HashMap<String, DbForignKeyArray> childrenForignKeys ;

	public AuditControler getAuditControlTobeUpdated() throws Exception
	{
		return (auditControlTobeUpdated == null) ?  (AuditControler) this.getUserAuditControl().getCurrentItem(): auditControlTobeUpdated;
	}
	
	public void setAuditControlTobeUpdated(AuditControler pm_ac) 
	{
		 auditControlTobeUpdated = pm_ac;
	}
	
	private static final String AUDIT_CONTROL_TABLE_NAME = "AUDIT_CONTROLER";
	private static final String SEC_USER_GROUPS_TABLE_NAME = "SEC_USER_GROUPS";
	private static final String USR_FAVORITE_TABLE_NAME = "USR_FAVORITE";
	private static final String SEC_USER_MNUS_TABLE_NAME = "SEC_USR_MNUS";
	
	
	private static DbForignKeyArray getForignKeyForAuditControler()
	{
		return new DbForignKeyArray ( new DbForignKey[]{new DbForignKey(SecUsrDta.USR_NAME ,AuditControler.USERNAME )} , false );
	}

	private static DbForignKeyArray getForignKeyForUserFav()
	{
		return new DbForignKeyArray ( new DbForignKey[]{new DbForignKey(SecUsrDta.USR_NAME ,UsrFavorite.USR_NAME )} , false );
	}

	private static DbForignKeyArray getForignKeyForUserGroups()
	{
		return new DbForignKeyArray (  new DbForignKey[]{new DbForignKey(SecUsrDta.USR_NAME ,SecUserGroups.USER_NAME )} , false );
	}
	private static DbForignKeyArray getForignKeyForUserMnus()
	{
		return new DbForignKeyArray ( new DbForignKey[]{new DbForignKey(SecUsrDta.USR_NAME ,SecUsrMnus.USR_NAME )} , false );
	}
	private static DbForignKeyArray getForignKeyForTableUserView()
	{
		return new DbForignKeyArray ( new DbForignKey[]{new DbForignKey(SecUsrDta.USR_NAME ,TableUserView.USER_ID)} , false );
	}
	
	private static DbForignKeyArray getForignKeyForSavedSearchs()
	{
		return new DbForignKeyArray (  new DbForignKey[]{new DbForignKey(SecUsrDta.USR_NAME ,SavedSearch.OWNER )} , false );
	}
 
	@Override
	protected HashMap<String, DbForignKeyArray> getChildrenForignKeys()
	{
		if (childrenForignKeys == null)
		{
			childrenForignKeys  = new HashMap<String, DbForignKeyArray>(); 
			childrenForignKeys.put(AUDIT_CONTROL_TABLE_NAME, SecUsrDta.getForignKeyForAuditControler());
			childrenForignKeys.put(USR_FAVORITE_TABLE_NAME, SecUsrDta.getForignKeyForUserFav());
			childrenForignKeys.put(SEC_USER_GROUPS_TABLE_NAME, SecUsrDta.getForignKeyForUserGroups());
			childrenForignKeys.put(SEC_USER_MNUS_TABLE_NAME, SecUsrDta.getForignKeyForUserMnus());
			childrenForignKeys.put(TableUserView.DB_TABLE_NAME, SecUsrDta.getForignKeyForTableUserView());
			childrenForignKeys.put(SavedSearch.DB_TABLE_NAME, SecUsrDta.getForignKeyForSavedSearchs());
		}
	
		return childrenForignKeys;
	}

	private DataSet getTableUserViewDS() 
	{
		DataSet tableUserViewDS = null;
		try
		{
			tableUserViewDS = this.getChildrenDataSet(TableMaintMaster.CDB_SCHEMA_OWNER + "." + TableUserView.DB_TABLE_NAME , TableUserView.class , false);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return tableUserViewDS;
	}
	public HashMap<String, TableUserView> getTableUserViewMap()
	{
		if(tableUserViewMap == null)
		{
			tableUserViewMap = new HashMap<String, TableUserView>();
			ArrayList<PersistantObject> tableViewsList = this.getTableUserViewDS().getPersistantObjectList() ; 
			for (PersistantObject po :tableViewsList )
			{
				TableUserView tuv = (TableUserView) po;
				tableUserViewMap.put(tuv.getTabelNameValue(), tuv);
			}
		}
		return tableUserViewMap;
	}
	public DataSet getUserFavoriteDS() throws Exception
	{
		return getChildrenDataSet(USR_FAVORITE_TABLE_NAME , UsrFavorite.class , false);
	}

	public static final String USER_GROUP_DS_NAME = "USER_GROUP_DS_NAME" ; 
	public DataSet getUserGroupsDS() throws Exception
	{
		DataSet v_result = this.getChildernDataSetMap().get(USER_GROUP_DS_NAME) ; 
		
		if (v_result == null )
		{
			String query = " Select sug.* , sug.rowid from icdb.sec_user_groups sug , icdb.sec_role sr "
							 +" where upper(sug.user_name) = upper('"+this.getUsrNameValue()+"') "
							 +" and sr.role_id = sug.role_id "
							 +" and sr.company_id  = " + this.getUsrCmpIdValue()
							 +"  order by sug.role_id asc" ; 
			v_result = this.getDbService().queryForDataSet(query, null , SecUserGroups.class ,new ParentPersistantObject(this))  ;
			this.getChildernDataSetMap().put(USER_GROUP_DS_NAME, v_result) ; 
		}
		return  v_result ; 
		
	}
	
	public void resetUserGroupDS()
	{
		this.getChildernDataSetMap().remove(USER_GROUP_DS_NAME) ; 
				
	}
	public void resetDefaultSecRole()
	{
		this.defaultSecRole = null ;
	}
	
	public DataSet getUserAuditControl() throws Exception
	{
		return getChildrenDataSet(AUDIT_CONTROL_TABLE_NAME , AuditControler.class , false);
	}
	
	public DataSet getUserMnus() throws Exception
	{
		return getChildrenDataSet(SEC_USER_MNUS_TABLE_NAME , SecUsrMnus.class , false);
	}
	
	public DataSet getMySavedSearchs() throws Exception
	{
		return getChildrenDataSet(SavedSearch.DB_TABLE_NAME , SavedSearch.class , false);
	}
	

	public void addToFavorite(SysMnu sysMnu){
		if (!isFavoriteMenu(sysMnu.getMnuCodeValue())) {
			try {
				this.getUserFavoriteDS().addNew();			
			((UsrFavorite) this.getUserFavoriteDS().getCurrentItem()).setMnuCodeValue(sysMnu.getMnuCodeValue());
			this.getUserFavoriteDS().save();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	

	public void initialize()
	{
		// refreshUserCompany();
	}
	
	public MasCompanyData getUserCompany(){
		if (userCompany == null)
		{
			refreshUserCompany() ; 
		}
		return userCompany;
	}
	
	public void refreshUserCompany() {
		if(this.getUsrCmpIdValue() != null)
		{
			try{
				String query = "SELECT t.rowid, t.* FROM "+TableMaintMaster.CDB_SCHEMA_OWNER+".mas_company_data t where t.cmp_id = "+this.getUsrCmpIdValue().toString();
				List<PersistantObject> list = this.getDbService().queryForDataSet(query, MasCompanyData.class).getPersistantObjectList();
				userCompany = (MasCompanyData) list.get(0);				
			}catch (Exception e) {
			}
		}
		
	}

	/**
	 * Checks if this user has access to the given SysMnu
	 * @param sysMnuCode
	 * @return
	 */
	private boolean isHasAccessToSysMnu(String sysMnuCode) {
		boolean result = false;
		
		SysMnu inputSm = this.getSysMnuByMnuCode(sysMnuCode);
		if (inputSm != null)
		{
			try{
				List<PersistantObject> userMnusList = this.getUserMnus().getPersistantObjectList();
				for(int i=0; i< userMnusList.size() ; i++)
				{
					SecUsrMnus sum = (SecUsrMnus)userMnusList.get(i);
					SysMnu sm = this.getSysMnuByMnuCode( sum.getSysMnuCodeValue());
					if(sm.equals(inputSm))
						{
						result =  true;
						break;
						}
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * Adds the given pm_mnuCodes to this user allowed menu list 
	 * @param pm_MnuCodes
	 */
	public void setUserSysMnuCodes(String[] pm_MnuCodes) {		
		
		try {
			this.getUserMnus().markAllToBeDeleted();
			for (int i = 0; i < pm_MnuCodes.length; i++) {
				{
					this.getUserMnus().addNew();
					SecUsrMnus newItem = ((SecUsrMnus) this.getUserMnus().getCurrentItem());
					newItem	.setSysMnuCodeValue(pm_MnuCodes[i]);
				}
			}
		} catch (Exception e) {
			 this.getModuleServiceContainer().getMessageCommnunicatorService().sendMessageToUser(
					"Unable to add to user groups due to " + e.getMessage());
		}
	}
	
	/**
	 * 
	 * @return array of String contains the menu codes this user have access to  
	 */
	public String[] getUserSysMnuCodes() {
		String[]  result  = null; 
		try {
			int size = this.getUserMnus().getPersistantObjectList().size();
			result = new String[size];
			List<PersistantObject> userMnusList = this.getUserMnus().getPersistantObjectList();
			for (int i = 0; i < size; i++) {
				result[i] = ((SecUsrMnus)userMnusList.get(i)).getSysMnuCodeValue();
			}
		} catch (Exception e) {
			this.getModuleServiceContainer().getMessageCommnunicatorService().sendMessageToUser(
					"Unable to get user mnus due to " + e.getMessage());
		}
		return result;
	}
	
	

//	private void removeFromSysMnuCodes(String MnuCode) throws Exception {
//		SecUsrMnus userMnusToBeDeleted = getSecUserMnuByMnuCode(MnuCode);
//		this.getUserMnus().markObjectToBeDeleted(userMnusToBeDeleted);		
//	}
//	
//	private void removeFromUserGroups(String groupId) throws Exception {
//		SecUserGroups userGroupsToBeDeleted = getSecUserGroupByGroupId(groupId);
//		this.getUserGroupsDS().markObjectToBeDeleted(userGroupsToBeDeleted);		
//	}

	public boolean isMemberInUpdatedUserGroups(String groupId) {
		String[] userGroupsIds = getUserGroupsIds();
		for(int i=0; i< userGroupsIds.length;i++){
			if(userGroupsIds[i]!=null && userGroupsIds[i].equals(groupId))
				return true;
		}
		return false;
	}
	public void addToUserGroupsxx(String[] userGroupsIds ) {		
		
	}
	
	public void setUserGroupsIds(String[] pm_selectedGroups) {
		try {
			this.getUserGroupsDS().markAllToBeDeleted();
			for (int i = 0; i < pm_selectedGroups.length; i++) {
				{
					this.getUserGroupsDS().addNew();
					((SecUserGroups) this.getUserGroupsDS().getCurrentItem())
							.setMemberInGroupValue(pm_selectedGroups[i]);
				}
			}
		} catch (Exception e) {
			this.getModuleServiceContainer().getMessageCommnunicatorService().sendMessageToUser(
					"Unable to modify user groups due to " + e.getMessage());
		}
	}

	public String[] getUserGroupsIds() {
		String[] userGroupsIds = null;
		try {
			int size = this.getUserGroupsDS().getPersistantObjectList().size();
			userGroupsIds = new String[size];
			for (int i = 0; i < size; i++) {
				userGroupsIds[i] = ((SecUserGroups)this.getUserGroupsDS().getPersistantObjectList().get(i)).getMemberInGroupValue();
			}
		} catch (Exception e) {
			this.getModuleServiceContainer().getMessageCommnunicatorService().sendMessageToUser(
					"Unable to get user groups due to " + e.getMessage());
		}
		return userGroupsIds;
	}
	
	public boolean isMemberInGroups(String[] pm_GroupsIds) {		
		String[] userGroupsIds = getUserGroupsIds();
		
		if (getUserGroupsIds() != null && pm_GroupsIds != null)
			for (int i = 0; i < pm_GroupsIds.length; i++) {
				for (int j = 0; j < userGroupsIds.length; j++) {
					if (pm_GroupsIds[i] != null && pm_GroupsIds[i].equals(pm_GroupsIds[j]))
						return true;
				}
			}
		return false;
		
	}
	
	private SecUserGroups getSecUserGroupByGroupId(String groupId) throws Exception{
		SecUserGroups secUserGroup = null;
		DataSet ugds = this.getUserGroupsDS();
		secUserGroup = (SecUserGroups) ugds.getFirstFilteredPO(SecUserGroups.ROLE_ID, groupId) ; 
		
		return secUserGroup;
	}
	private SecUserGroups getSecUserGroupByGroupName(String groupName) throws Exception{
		SecUserGroups result = null;

		DataSet ugds = this.getUserGroupsDS();
		SecUserGroups secUserGroup = null;
		for (PersistantObject po : ugds.getPersistantObjectList())
		{
			secUserGroup = (SecUserGroups) po; 
			SecRole secRole = secUserGroup.getSecRole(false) ; 
			if (secRole.getRoleNameValue().equalsIgnoreCase(groupName))
			{	result = secUserGroup ; 
				break ; 
			}
				
		}
		 
		return result;
	}
	
	private SysMnu getSysMnuByMnuCode(String sysMnuCode)
	{
		SystemMenusServices sms =  this.getDbService().getModuleServiceContainer().getSysMenuServices();
		return sms.getSysMnuByMnuCode(sysMnuCode);
	}

	/**
	 * Checks if this User includes a given SysMnu object in his Favourites 
	 * @param sysMnuCode
	 * @return true if This user includes the given SysMnu object in his Favourites
	 * @throws BaseException
	 */
	public boolean isFavoriteMenu(String sysMnuCode){
		SysMnu sm =  getSysMnuByMnuCode(sysMnuCode);
		boolean result = false;
		try {
			result = this.isFavoriteMenu(sm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean isFavoriteMenu(SysMnu sm) throws Exception{
		java.util.List<PersistantObject> favourateList = this.getUserFavoriteDS().getPersistantObjectList();
		
		for (int i = 0; i < favourateList.size(); i++) {
			UsrFavorite uf = (UsrFavorite) favourateList.get(i);
			SysMnu sm1 = uf.getCorespondingSysMnu();
			if (sm.equals(sm1))//(uf.getCorespondingSysMnu().getMnuCodeValue().equals(sysMnuCode))
				return true;
		}
		return false;
	}
	
	public boolean isMemberInGroup(String groupId) throws Exception{
		for (int i = 0; i < this.getUserGroupsDS().getPersistantObjectList().size(); i++) {
			if (((SecUserGroups) this.getUserGroupsDS().getPersistantObjectList()
					.get(i)).getMemberInGroupValue().equals(groupId))
				return true;
		}
		return false;
	}


	
	public String getDepartment() {
		
		return null;
	}
	
	
	public Integer getFailedLoginCount() {
		
		return null;
	}

	
	public String getFirstName() {
		
		return null;
	}

	
	public String getLastName() {
		
		return null;
	}

	
	public Date getLastSuccessfulLogin() {
		
		return null;
	}

	
	public String getLoginName() {
		// TODO Auto-generated method stub
		return this.getUsrNameValue();
	}

	
	public Zone getMasterZone() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.getUsrPasswordValue();
	}

	
	public Date getPasswordDate() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Integer getPasswordExpiration() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Date getPasswordExpirationDate() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getRemark() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean isLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean isPasswordExpirationActivated() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean isSysAdmin() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public Date getCreationDate() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Date getLastUpdateDate() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getName() {
		// TODO Auto-generated method stub
		return this.getUsrFullnameValue();
	}

	
	public boolean isDeleted() {
		// TODO Auto-generated method stub
		return false;
	}


	
	public PersistentObjectSecurityControl getSecurityController()
	{
		if (sch == null)
		{
			sch = new SecUserSecurityControlHandler();
		}
		return sch;
		 
	}

	private static TriggerHandler triggerHandler = null;
	@Override
	public TriggerHandler getTriggerHandler() 
	{
		boolean auditable = this.getTableMaintMaster().getAuditable().getBooleanValue();
		if (triggerHandler == null && auditable )
		{
			triggerHandler = new AuditInDbTriggerHandler();
		}
		
		return triggerHandler; 
	}

	public boolean isAuditEnabled() {
		return this.getUsrAuditValue().intValue()==1;
	}

	@Override
	public AttributeChangeValidator getAttributeChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po,
			String pm_key)
	{
		// TODO Auto-generated method stub
		return new AttributeChangeValidator(){

			@Override
			public ValidationResult validate(Object newValue)
			{
				// TODO Auto-generated method stub
				return new ValidationResult();
			}
		
		};
	}

	public boolean isPassWordRendered()
	{
		int userType =( (BigDecimal) this.getAttribute(SecUsrDta.USR_TYPE).getValue()). intValue();
		return  (userType == SecUsrDta.USER	);
	}
	 
	
	//public boolean isAdmin()
	//{
	//	return hasRoleId(ADMINISTRATOR); 
	//}
	public boolean hasRoleNamed(String m_roleName)
	{
		boolean result = false ; 
		SecUserGroups sug = null;
		try {sug = this.getSecUserGroupByGroupName(m_roleName) ;}
		catch (Exception e) {e.printStackTrace();}
		result =  (sug != null) ;
	return result;
		
	}
	public boolean hasRoleId(String m_roleId)
	{
		boolean result = false ;
		SecUserGroups sug = null;
		try 
		{
			 sug = this.getSecUserGroupByGroupId(m_roleId);
		} catch (Exception e) {
				e.printStackTrace();
		}
		if (sug != null) result = true;
		return result;
	}
	
	Boolean smartToolAdmin ; 
	public boolean isSmartToolAdmin()
	{
		if (smartToolAdmin == null )
		{
			smartToolAdmin = hasRoleNamed("Administrator"); //hasRoleId(ADMINISTRATOR);
		}
				
		return smartToolAdmin;
		
	}
	
	public ArrayList<SelectItem> mySavedSearchs ;
	
	private String getQueryForMySavedSearch(TableMaintMaster tmm)
	{
		String query = "Select distinct ss.search_name , ss.search_name  " +
		"from  "+ TableMaintMaster.CDB_SCHEMA_OWNER+".saved_search ss ,   "+ TableMaintMaster.CDB_SCHEMA_OWNER+".saved_search_details ssd ,  "+ TableMaintMaster.CDB_SCHEMA_OWNER+".table_maint_details tmd "
		+ " where ss.table_name = ssd.table_name " 
		+  " and  ss.search_name = ssd.search_name "
		+  " and ssd.table_name = tmd.table_name "
		+  " and ssd.column_name = tmd.column_name "
		+ " and tmd.table_name = '"+ tmm.getTableNameValue()+"'"
		+ " and ss.owner = '" + this.getUsrNameValue() + "'" ;
		
		return query;
	}
	
	public ArrayList<SelectItem> getMySavedSearchsAsSelectItems(TableMaintMaster tmm) throws Exception
	{
		{
			DbServices ds =  this.getDbService();
			String query = this.getQueryForMySavedSearch( tmm);
			mySavedSearchs =ds.getSelectItems(query , true);
		}
		return mySavedSearchs != null ? mySavedSearchs : new ArrayList<SelectItem>();
	}
	
	
	
	private HashMap<TableMaintMaster , DataSet>  mySeavedSearch = new HashMap<TableMaintMaster , DataSet>() ; 
	public DataSet getMySavedSearchsDS(TableMaintMaster tmm) throws Exception
	{
		DataSet  result ;  
		result = mySeavedSearch.get(tmm);
		if (result == null)
		{
			DbServices ds =  this.getDbService();
			String query = this.getQueryForMySavedSearch(tmm);
			result =   ds.queryForDataSet(query, SavedSearch.class);
			mySeavedSearch.put(tmm, result);
		}
		return result ; 
	}
	@Override
	public void setUsrPasswordValue(String pm_password){
		if(this.getUsrPasswordValue()!= pm_password && this.isPasswordConfirmed()== true)
		{	
			this.setPasswordConfirmed(false);
			super.setUsrPasswordValue(pm_password);
		}
	}

	private String  passwordConfirm; 
	public String  getPasswordConfirm()
	{
		if (passwordConfirm == null)
		{
			passwordConfirm = this.getUsrPasswordValue() ; 
		}
		return this.passwordConfirm;
	}
	public void setPasswordConfirm(String password)
	{
		this.setPasswordConfirmed(false);
		boolean result = this.confirmationSuccess(this.getUsrPasswordValue(), password);
		if(result){
			this.passwordConfirm = password;
			this.setPasswordConfirmed(true);
		}
	}
	public boolean confirmationSuccess(String confirmpassword , String password){
		boolean result = true;
		if(!confirmpassword.equals(password)){
			result=false;
			try{
			SysMsg sysMsg = this.getDbService().getModuleServiceContainer().getSysMsgServices().getSysMsg("1103", "3", false);
			String msg = sysMsg.toString();
			this.getModuleServiceContainer().getMessageCommnunicatorService().sendMessageToUser(msg);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public void save() throws Exception{
		if(this.isPasswordConfirmed()){
			super.save();
		}else {
			SysMsg sysMsg = this.getDbService().getModuleServiceContainer().getSysMsgServices().getSysMsg("1105", "3", false);
			String msg = sysMsg.toString();
			this.getModuleServiceContainer().getMessageCommnunicatorService().sendMessageToUser(msg);
		}
		//TODO : Sakr Please create a new folder with userName under D:\IMPLEX\Sources\App\ERPINS-ROYAL\WebContent\CrystalReports\Results 
	}
	private boolean passwordConfirmed=true;
	public boolean isPasswordConfirmed(){
		return passwordConfirmed;
	}
	public void setPasswordConfirmed (boolean pm_Confirmed){
		this.passwordConfirmed = pm_Confirmed;
	}
	
	private DataSet secUserCommMsgsDataSet = null;
	
	public DataSet getSecUserCommMsgsDataSet()
	{
		nonReadMsgCount = 0;
		String query = "select t.rowid, t.* from SEC_USER_COMMUNICATION_MSGS t where to_users like '%"+this.getUsrNameValue()+"%'";
		try {
			secUserCommMsgsDataSet = this.getDbService().queryForDataSet(query, SecUserCommunicationMsgs.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (PersistantObject po : secUserCommMsgsDataSet.getPersistantObjectList()) {
			SecUserCommunicationMsgs msg = (SecUserCommunicationMsgs) po;
			if(!msg.getMsgRead().getBooleanValue())
			{
				nonReadMsgCount  ++;
			}
		}
		return secUserCommMsgsDataSet;
	}
	
	private int nonReadMsgCount = -1;
	public int getNonReadMsgCount() {
		getSecUserCommMsgsDataSet();
		return nonReadMsgCount;
	}

	public void setNonReadMsgCount(int nonReadMsgCount) {
		this.nonReadMsgCount = nonReadMsgCount;
	}
	public DataSet getSecUserCommMsgsDS()
	{		
		if(secUserCommMsgsDataSet == null)
			getSecUserCommMsgsDataSet();
		return secUserCommMsgsDataSet;
	}
	
	public ArrayList<SelectItem> getLoggedUserSqlBoundVars() throws Exception {
		ArrayList<SelectItem> allSqlBoundVars = null;
		
			String query = "select T.BOUND_VAR_NAME, t.title, t.title from SQL_BOUND_VARS t where T.OWNER='" +
					this.getUsrNameValue().toUpperCase()+"'";
			allSqlBoundVars = this.getDbService().getSelectItems(query);
		
		return allSqlBoundVars;
	}
	//.........................................................
	DataSetPickList        datasetPickListOfListScrensOFUsres;
	public DataSetPickList getDatasetPickListOfListScrensOFUsres() throws Exception 
	{
		if(datasetPickListOfListScrensOFUsres==null)
		{
			ArrayList<PersistantObject> initialValue = this.getUserMnusAsSysMnus();
			
			datasetPickListOfListScrensOFUsres=new  DataSetPickList( "SYS_MNU_CODE"  ,"MNU_CODE"  ,this.getUserMnus() , initialValue );
			datasetPickListOfListScrensOFUsres.setConverterId("converterforPickListOfListScrensOFUsres");
			DbServices dbs = this.getDbService() ; 
			ArrayList<SelectItem> allAvailableItems = this.getDbService().getModuleServiceContainer().getSysMenuServices().getAllAvailableMunCodes();
			datasetPickListOfListScrensOFUsres.setAllAvailableItems(allAvailableItems);
		}
		return datasetPickListOfListScrensOFUsres;
	}
	
	private ArrayList<PersistantObject> getUserMnusAsSysMnus() {
		ArrayList<PersistantObject> result = new ArrayList<PersistantObject> () ; 
		String query="Select t.* , t.rowid from  "+ TableMaintMaster.CDB_SCHEMA_OWNER+".SEC_USR_MNUS  , "+ TableMaintMaster.CDB_SCHEMA_OWNER+".SYS_MNU t where SEC_USR_MNUS.SYS_MNU_CODE =t.MNU_CODE and USR_NAME='"+this.getUsrNameValue()+"'order by mnu_txt asc";
		try {
			result= (ArrayList<PersistantObject>) this.getDbService().queryForList(query,SysMnu.class);
			} 
		catch (Exception e) 
			{e.printStackTrace();}
	return result;
	}


	//.....................................................................................
	/* i found out that the object which is loaded and the object wich was selected will be
	 *  compared when setting to the backing bean. So if your object�s
	 *  Class has not overridden the equals method this error message is shown. */
	  public boolean equals(Object obj) { 
			if(this == obj)
			{
				return true; 
			} 
			if (!(obj instanceof SecUsrDta))
			{ 
				return false;  
			} 
			SecUsrDta secuserdta = (SecUsrDta)obj; 
	
			String thisUserName = this.getUsrNameValue() ; 
			String objUsrName = secuserdta.getUsrNameValue() ; 
			return thisUserName.equals(objUsrName) ;

		} 
	//.....................................................................................

	
	  private static  MultiPageControl multiPageControl ;
	
		public  MultiPageControl getMultiPageControl() {
			if (multiPageControl == null)
			{
				multiPageControl = new MultiPageControl();
				ArrayList<PageControl> pageControls = new ArrayList<PageControl>();
				DbServices dbs = this.getDbService() ;
				ModuleServicesContainer msc =  this.getDbService().getModuleServiceContainer() ;
				
				PageControl accountInfoPage =  new PageControl(this) ;
				accountInfoPage .setPageRelUrl("/SWAF/jsp/secUsrAccountInfoTab.xhtml");
				accountInfoPage .setPageId("1");
				accountInfoPage .setPageHeader( msc.getSysFrmServices().getFrmObjTxt("/SWAF/jsp/secUserDataMaintainance.xhtml","tabAccountInfo") );
				pageControls.add(accountInfoPage ) ;
				
				PageControl loginControlPage =  new PageControl(this) ;
				loginControlPage.setPageRelUrl("/SWAF/jsp/secUsrLoginControlTab.xhtml");
				loginControlPage .setPageId("2");
				loginControlPage.setPageHeader( msc.getSysFrmServices().getFrmObjTxt("/SWAF/jsp/secUserDataMaintainance.xhtml","tabLoginControl") );
				pageControls.add(loginControlPage) ;
				
				PageControl userMenus =  new PageControl(this) ;
				userMenus.setPageRelUrl("/SWAF/jsp/secUsrDtaUsrMenusTab.xhtml");
				userMenus .setPageId("3");
				userMenus.setPageHeader( msc.getSysFrmServices().getFrmObjTxt("/SWAF/jsp/secUserDataMaintainance.xhtml","tabUserMenus") );
				pageControls.add(userMenus) ;
				
				PageControl userDefinedFields =  new PageControl(this) ;
				userDefinedFields.setPageRelUrl("/SWAF/jsp/secUsrDtaUsrDefinedFieldsTab.xhtml");
				userDefinedFields .setPageId("4");
				userDefinedFields.setPageHeader(msc.getSysFrmServices().getFrmObjTxt("/SWAF/jsp/secUserDataMaintainance.xhtml","tabUserDefinedFields") );
				pageControls.add(userDefinedFields) ;
				
				
				PageControl userEnvironment =  new PageControl(this) ;
				userEnvironment.setPageRelUrl("/SWAF/jsp/secUsrDataUsrEnvironmentTab.xhtml");
				userEnvironment .setPageId("5");
				userEnvironment.setPageHeader( msc.getSysFrmServices().getFrmObjTxt("/SWAF/jsp/secUserDataMaintainance.xhtml","tabUserEnviroments") );
				pageControls.add(userEnvironment) ;
				
				PageControl auditControl =  new PageControl(this) ;
				auditControl.setPageRelUrl("/SWAF/jsp/secUsrAuditControlTab.xhtml");
				auditControl .setPageId("6");
				auditControl.setPageHeader( msc.getSysFrmServices().getFrmObjTxt("/SWAF/jsp/secUserDataMaintainance.xhtml","tabAuditControl") );
				pageControls.add(auditControl) ;
				
				PageControl userGroups =  new PageControl(this) ;
				userGroups.setPageRelUrl("/SWAF/jsp/secUsrDtaUsrGroupsTab.xhtml");
				userGroups .setPageId("7");
				userGroups.setPageHeader( msc.getSysFrmServices().getFrmObjTxt("/SWAF/jsp/secUserDataMaintainance.xhtml","tabUserGroups") );
				pageControls.add(userGroups) ;
				
				multiPageControl.setPages(pageControls);
			}
			
			return multiPageControl;
		}
		
		private PersistantObject hrMasEmployeeData ; 
		public PersistantObject calcHrMasEmployeeData()
		{
			PersistantObject result = null ; 
			String query = "Select * from HR_MAS_EMPLOYEE_DATA where EMPLOYEE_ID = '" + this.getUsrEmpIdValue() +"'" ;
			DataSet ds = this.getDbService().queryForDataSet(new Query(query) , null, null, null) ;
			ArrayList<PersistantObject> poList = ds.getPersistantObjectList() ;
			if (!poList.isEmpty())
			{
				result = poList.get(0);
			}
			return result ; 
		}
		public void refreshHrMasEmployeeData()
		{
			hrMasEmployeeData = calcHrMasEmployeeData() ; 
		}
		public PersistantObject getHrMasEmployeeData() {
			if (hrMasEmployeeData == null)
			{
				this.refreshHrMasEmployeeData();
			}
			return hrMasEmployeeData;
		}

		private HashMap usrDefaultValuesMap = null;
		public HashMap getUsrDefaultValuesMap() {
			if(usrDefaultValuesMap == null)
			{
				String query = TableMaintMaster.getAllItemsQuery(TableUserView.DB_TABLE_NAME , TableMaintMaster.CDB_SCHEMA_OWNER) +" Where t.user_id = '" + this.getUsrNameValue() + "'";
				DataSet ds = this.getDbService().queryForDataSet(query, TableUserView.class);
				usrDefaultValuesMap = new HashMap();
				TableUserView tableUsrView = null;
				for(PersistantObject po : ds.getPersistantObjectList())
				{
					tableUsrView = (TableUserView) po;
					usrDefaultValuesMap.put(tableUsrView.getTabelNameValue(), tableUsrView.getUsrDefaultValuesValue());
				}
			}
			return usrDefaultValuesMap;
		}
		
		public Object getUsrDefaultValueByTableAndColumnNames(String tableName, String columnName, String dataTaype)
		{
			Object newValue = null;
			if(this.getUsrDefaultValuesMap() != null && !this.getUsrDefaultValuesMap().isEmpty())
			{
				String condition = String.valueOf(this.getUsrDefaultValuesMap().get(tableName));
				if(condition != null && !condition.equals("null") && !condition.isEmpty())
				{
					//Calculate the value
					String [] valueSplitter1 = condition.split(columnName);
					String value = "";
					String newValueAsString = "";
				
					value = valueSplitter1[1];
					for(int i = 0; i < value.length(); i++)
					{
						if(!String.valueOf(value.charAt(i)).equalsIgnoreCase("a"))
						{
							if(!String.valueOf(value.charAt(i)).equalsIgnoreCase("o"))
							{
								if(!String.valueOf(value.charAt(i)).equals("'"))
								{
									newValueAsString += value.charAt(i);
								}
							}else{
								break;
							}
						}else{
							break;
						}
						
					}
					
					String[] valueSplitter2 = newValueAsString.split("=");
					newValue = valueSplitter2[1].trim();
					
					if(newValue!=null)
					{
						if( dataTaype!= null && dataTaype.equals("NUMBER"))
						{
							newValue = new BigDecimal( String.valueOf(newValue) );
							
						}else{
							newValue = String.valueOf(newValue);
						}
					}
				}
			}
			return newValue;
		}
		
		@Override
		public void afterAttributeChange(Attribute pm_att) {
			SecUsrDta secUsrDta = (SecUsrDta) pm_att.getParentPersistantObject();
			if(pm_att.getKey().equals(SecUsrDta.USR_CMP_ID))
			{
				secUsrDta.refreshUserCompany();
			}
			
		}

		@Override
		public void beforeAttributeChange(Attribute pm_att) throws BaseException
		{
			// TODO Auto-generated method stub
		}	
		
		public String getAppURL()
		{
			SysParams sysParam = (SysParams) this.getUserCompany().getSysParams().getFirstFilteredPO(SysParams.E_NAME, "AppURL") ; 
			return  sysParam.getValValue(); 
		}
		SecRole defaultSecRole = null ; 
		public SecRole getDefaultSecRole() throws Exception 
		{
			if (defaultSecRole == null )
			{
			 SecUserGroups  userDefaultGroup = (SecUserGroups) this.getUserGroupsDS().getFirstFilteredPO( "USER_DEFAULT_ROLE" , "Y") ;
			 defaultSecRole = userDefaultGroup.getSecRole(false);
			}
			return defaultSecRole ; 
		}
		
		static final int ENGLISH = 0 ; 
		static final int ARABIC = 1 ;
		public String getDisplayDirection()
		{
			return (this.isUsrLangEnglish())? "ltr" : "rtl" ; 
		}
		public boolean isUsrLangArabic()
		{
		 return this.getUserLangIntValue() == ARABIC ; 
		}
		
		public boolean isUsrLangEnglish()
		{
		 return this.getUserLangIntValue() == ENGLISH ; 
		}
		/**
		 * generate a random password and execute "alter user identified by " this password  
		 * @return
		 * @throws SQLException
		 */
		public String  alterUserPassword() throws SQLException
		{
			java.sql.Connection connForRest = null ; 
			String randomPassword = Support.Misc.generateRandomPassword(8); 
			connForRest = this.getDbService().getConnection() ; 
			Statement stmt = connForRest.createStatement(); 
			stmt.execute("Alter User " + this.getUsrNameValue() +" Identified By " + randomPassword ) ; 
			stmt.close() ;
			return randomPassword ; 
		}
		
		public String getDefaultResetEmailMessage() throws SQLException
		{
			String randomPassword = alterUserPassword(); 
			com.smartValue.database.DataSet sysParams = this.getUserCompany().getSysParams() ; 
			String appUrl = ((SysParams) sysParams.getFirstFilteredPO("E_NAME" , "AppURL")).getValValue();
		    appUrl = appUrl +"/SmartTool/Company/"+this.getUsrCmpIdValue()+"/loginScreen.jsp" ; 
			String message = "<div align='right' dir='rtl'> تم إنشاء مستخدم جديد لك فى نظام قياس الاداء "
								+"<br> اسم المستخدم : " +this.getUsrNameValue() 
								+"<br> البريد الالكتروني : " +this.getUsrEmailValue() 
								+"<br> كلمة المرور الجديدة  : " + randomPassword ;
		    message += "<br><a href = "+appUrl+">إضغط هنا للدخول للنظام </a> </div>" ;
		    return message ;  
		}
		public void sendResetPasswordEmail() throws Exception
		{
			String message = getDefaultResetEmailMessage();
			String subject = " تم إنشاء مستخدم جديد لك فى نظام قياس الاداء " ;
			this.sendResetPasswordEmail(subject, message);
		}
		public void sendResetPasswordEmail(String m_subject , String m_message) throws Exception
		{
			com.smartValue.database.DataSet sysParams = this.getUserCompany().getSysParams() ;
			String supportAdminMailSender = ((SysParams) sysParams.getFirstFilteredPO("E_NAME" , "Support User Email")).getValValue(); // Support.SysConfigParams.getSupport_User_Email();
		    String[] to = {this.getUsrEmailValue()};
		    Support.mail.EmailMessage em = new Support.mail.EmailMessage();
			em.setFrom(supportAdminMailSender);
		    em.setTo(to);
			em.setSubject(m_subject);
			em.setBody(m_message);
			Support.mail.MailSender ms = new Support.mail.MailSender(sysParams);
			ms.sendMail(em);
		}
		
		
		int userLangIntValue = -1 ; 
		public int getUserLangIntValue()
		{  
			if (userLangIntValue == -1 )
			{
				userLangIntValue = 0 ;
				Attribute usrLang = getUsrLang() ;  
				if ( usrLang != null)
					userLangIntValue =  ((java.math.BigDecimal)usrLang.getValue()).intValue() ;
			}
			return userLangIntValue ; 
		}

	
}