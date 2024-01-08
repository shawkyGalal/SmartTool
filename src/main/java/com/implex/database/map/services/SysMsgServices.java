package com.implex.database.map.services;

import java.util.List;

import com.implex.database.ApplicationContext;
import com.implex.database.DbServices;
import com.implex.database.DbTable;
import com.implex.database.PersistantObject;
import com.implex.database.map.SysMsg;
import com.implex.database.map.TableMaintMaster;

public class SysMsgServices extends ModuleServices{
	
	public SysMsgServices(DbServices pmDbServices) {
		super(pmDbServices);
	}
	
	@Override
	public boolean isChanged() {
		return this.getDataSet().isChanged() ; 
	}
	
	private SysMsg sysMsg = new SysMsg();
	private boolean showMsgDetail = false ; 
	
	public String getName(){
		return "sysMsgServices";
	}
	
	@Override
	public Class getPersistentClass()
	{
		return  SysMsg.class;	
	}
	

	@Override
	public String getOrginalQuery() {
		return "select  t.rowid , t.* from SYS_Msg t where 1<> 1";
		
	}
	public SysMsg getDeleteMsg(){
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("25", "2" , false );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	public SysMsg getRemoveMsg(){
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("26", "2" , false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	public SysMsg getSaveMsg(){
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("7", "2", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	public SysMsg getSaveAllMsg(){
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("13", "2", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	public SysMsg getRevertMsg(){
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("1004", "2", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	public SysMsg getRevertAllMsg(){
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("1001", "2" , false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	public SysMsg getAddToFavoritesMsg(){
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("1002", "2");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	public SysMsg getSysMsg(String msgId, String msgType ) throws Exception{
		return getSysMsg( msgId,  msgType,  false);
	}
	public SysMsg getSysMsg(String msgId, String msgType, boolean freshInstance) throws Exception{
		
		SysMsg systemMsg = getSysMsg(Integer.parseInt(msgId) , Integer.parseInt(msgType) ,freshInstance );
		return systemMsg;
	}
	public SysMsg getNoDataFoundMsg() throws Exception
	{
		return this.getNoDataFoundMsg(false);
	}
	public SysMsg getNoDataFoundMsg(boolean freshInstance) throws Exception
	{
		return this.getSysMsg(1000 , 3 , freshInstance);
	}
	public SysMsg getSysMsg(int msgId, int msgType  , boolean freshInstance) throws Exception
	{
		String msgKey = msgId+"-"+msgType;
		SysMsg result = null; 
		if (ApplicationContext.getListOfUsedSysMsgs().containsKey(msgKey) && ! freshInstance)
		{
			return ApplicationContext.getListOfUsedSysMsgs().get(msgKey);
		}
		else
		{
			DbServices dbs =  this.getDbServices();
			int userlang = dbs.getLoggedUserLang();
			String query = TableMaintMaster.getAllItemsQuery(SysMsg.DB_TABLE_NAME , TableMaintMaster.CDB_SCHEMA_OWNER ) + " WHERE " +
					"t.msg_id = "+msgId+" AND t.msg_lng_id = "+userlang+" AND t.msg_type = "+msgType;
			List<PersistantObject> list = dbs.queryForList(query,	SysMsg.class);
			if(!list.isEmpty())
			{
				result =   (SysMsg)list.get(0);
				ApplicationContext.getListOfUsedSysMsgs().put(result.getMsgUniqueKey(), result);
			}
			return result;
		}
	}
	
	public void setSysMsg(SysMsg sysMsg) {
		this.sysMsg = sysMsg;
	}

	public SysMsg getSysMsg() {
		return sysMsg;
	}

	@Override
	public boolean isCanHaveMultipleInstances()
	{
		return false;
	}
	private SysMsg confirmDelSysMessage ;
	private SysMsg confirmIgnoreChanges ; 
	
	private SysMsg currentActiveMessage;
	
	public SysMsg getConfirmDelSysMessage() throws Exception
	{   if (this.confirmDelSysMessage == null)
		confirmDelSysMessage= this.getSysMsg(SysMsg.CONFIRM_DELETE_ID , 1 , false);
		return confirmDelSysMessage;
	}

	public SysMsg getConfirmIgnoreChanges() throws Exception
	{
		if (confirmIgnoreChanges == null)
			confirmIgnoreChanges=  this.getSysMsg(SysMsg.CONFIRM_IGNORE_CHANGES_ID , 1 , false);
		return confirmIgnoreChanges;
	}
	

	public void setCurrentActiveMessage(SysMsg pm_currentActiveMessage)
	{
		this.currentActiveMessage = pm_currentActiveMessage;
	}
	public SysMsg getCurrentActiveMessage()
	{
		return currentActiveMessage;
	}

	@Override
	public DbTable getDbTable() {
		return new DbTable(TableMaintMaster.CDB_SCHEMA_OWNER , SysMsg.DB_TABLE_NAME , this.getDbServices());
	}
	public SysMsg getInvalidColumnTableNameMsg()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("1005", "1", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}

	//....................................................................................
	public SysMsg getUnableToCleanTable()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("1008", "1", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	
	public SysMsg getSaveNewClassFirst()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("10114", "3", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	
	
	public SysMsg 	getClassCodeNotAllowedtobeUpdated()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("10115", "1", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	
	public SysMsg 	getSaveNewCustomerFirst()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("10116", "3", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	
	public SysMsg 	getCustomerIdNotAllowedtobeUpdated()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("10117", "1", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	
	public SysMsg 	getSaveNewRiskFirst()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("10118", "3", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	
	
	public SysMsg 	getRiskCodeNotAllowedtobeUpdated()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("10119", "1", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	
	public SysMsg 	getUnabletoDeleteHrMasWorkFlowPlan()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("10107", "1", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	
	public SysMsg 	getUnableAddNewHrMasWorkFlowPlan()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("10108", "1", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}

	
	public SysMsg 	getUnableaAdNewVacTrnTed()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("101099", "1", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	

	public SysMsg 	getUnabledeleteHrMasBenDataPercentage()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("10111", "1", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	
	
	
	public SysMsg 	getUnableDeleteHMasBenDataPercentage()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("1010", "1", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	
	
	
	public SysMsg getUnableAddNewHrMasBenDataPercentage ()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("10101", "1", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	
	
	
	public SysMsg getUnableDeleteEmployeeBenefit ()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("10102", "1", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	
	
	
	public SysMsg getUnableAddBenefit()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("10103", "1", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	

	public SysMsg getNotFindHrTrnEmpBenefit()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("10104", "3", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	
	
	
	public SysMsg getSaveNewBrokerFirst()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("10112", "2", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	

	
	
	public SysMsg getBrokerIdNotAllowedtobeUpdatedt()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("10113", "1", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	
	 
	
	
	public SysMsg getMsgUnableCalculateTotalBenefits()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("1009", "1", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	
	
	public SysMsg getMsgUnableDeleteHrMasPenaltyDetail()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("90001", "1", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	
	public SysMsg getMsgUnableDeleteTaxData()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("90002", "1", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	
	
	
	public SysMsg getMsgUnableAddNewHrMasBenDataPercentage()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("90003", "1", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	
	
	
	public SysMsg getMsgUnableAddNewHrMasBenData()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("90004", "1", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	
	
	
	
	public SysMsg getMsgSomeEntrieswithInvalidValue()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg =getSysMsg("11111", "1", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	
	
	public SysMsg getMsgUnableToAuditTransaction ()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg =getSysMsg("90005", "1", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	
	

	public SysMsg getMsgPleaseSaveOrCancelChanges()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg =getSysMsg("90006", "2", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	
	
	
	public SysMsg getMsgUnableToDeleteTreeItem()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg =getSysMsg("90007", "1", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	
	
	public SysMsg getMsgSelectListHasinvalidSQLQuery()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg =getSysMsg("90008", "1", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	
	
	public SysMsg getMsgUnableCalculateUserDefinedVariable()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg =getSysMsg("90009", "1", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	
	public SysMsg getMsgNoItemFoundWithYourSearch()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg =getSysMsg("90010", "3", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}
	
	 
	   public SysMsg getMsgUnableToExecute(boolean freshInstance)
		{
			SysMsg sysMsg = null;
			try {
				sysMsg =getSysMsg("90011", "1",  freshInstance);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return sysMsg;
		}
		
	  
	   public SysMsg getMsgAreNotAuthorizedTo(boolean freshInstance)
		{
			SysMsg sysMsg = null;
			try {
				sysMsg =getSysMsg("90012", "1", freshInstance);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return sysMsg;
		}
		
	   
	   public SysMsg getMsgItemStatus(boolean freshInstance)
		{
			SysMsg sysMsg = null;
			try {
				sysMsg =getSysMsg("90013", "0",  freshInstance);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return sysMsg;
		}
	   
	  
	   public SysMsg getMsgViewTime()
		{
			SysMsg sysMsg = null;
			try {
				sysMsg =getSysMsg("90014", "3", false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return sysMsg;
		}
		  
		  
	//.............................................................................
	//Unable to save this object   Both sides of the accounting entry is  not  equal
	   public SysMsg getMsgUnbalancedTransaction()
		{
			SysMsg sysMsg = null;
			try {
				sysMsg =getSysMsg("99011", "1", false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return sysMsg;
		}   
	
	public SysMsg getInvalidHtmlTypeMsg()
	{
		SysMsg sysMsg = null;
		try {
			sysMsg = getSysMsg("1007", "1", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysMsg;
	}

	public void setShowMsgDetail(boolean showMsgDetail) {
		this.showMsgDetail = showMsgDetail;
	}

	public boolean isShowMsgDetail() {
		return showMsgDetail;
	}

	@Override
	public void afterModuleRemoved()  {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeModuleRemoved() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
