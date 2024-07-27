package com.smartValue.database.map.services;
import com.smartValue.database.DbServices;
import com.smartValue.database.DbTable;
import com.smartValue.database.map.SecUserCommunicationMsgs;
import com.smartValue.database.map.TableMaintMaster;
public class SecUserCommunicationMsgsServices extends ModuleServices{
 
	public SecUserCommunicationMsgsServices(DbServices pmDbServices) {
		super(pmDbServices);
	}
	
	@Override
	public boolean isChanged() {
		return this.getDataSet().isChanged() ; 
	}
	
	@Override 
	public String getOrginalQuery()	
	{
		return  "select t.rowid , t.* from SEC_USER_COMMUNICATION_MSGS t " ;
	}
	@Override
	public Class getPersistentClass()
	{
		return SecUserCommunicationMsgs.class;
	}

	@Override
	public boolean isCanHaveMultipleInstances()
	{
		return false;
	}
	@Override
	public DbTable getDbTable() {
		return new DbTable(TableMaintMaster.CDB_SCHEMA_OWNER , SecUserCommunicationMsgs.DB_TABLE_NAME , this.getDbServices());
 }
	
	private SecUserCommunicationMsgs secUserCommMsgsTObeModified;
	
	public SecUserCommunicationMsgs getSecUserCommMsgsTObeModified() {
		return secUserCommMsgsTObeModified;
	}
	public void setSecUserCommMsgsTObeModified(SecUserCommunicationMsgs secUserCommMsgsTObeModified) {
		this.secUserCommMsgsTObeModified = secUserCommMsgsTObeModified;
	}
	
	public SecUserCommunicationMsgs getNewSecUserComMsg() {		
		try {
			this.getDbServices().getLoggedUser().getSecUserCommMsgsDS().addNew();
			SecUserCommunicationMsgs secComMsg = (SecUserCommunicationMsgs)this.getDbServices().getLoggedUser().getSecUserCommMsgsDS().getCurrentItem();
			secComMsg.setFromUserValue(this.getDbServices().getLoggedUser().getLoginName());
			secComMsg.setToUsersValue(" ");
			secComMsg.setMsgHeaderValue(" ");
			secComMsg.setMsgBodyValue(" ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (SecUserCommunicationMsgs)this.getDbServices().getLoggedUser().getSecUserCommMsgsDS().getPersistantObjectList().get(0);
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
