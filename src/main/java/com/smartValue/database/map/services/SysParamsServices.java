package com.smartValue.database.map.services;
import Support.mail.MailSender;

import com.smartValue.database.DataSet;
import com.smartValue.database.DbServices;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.SysParams;
import com.smartValue.database.map.TableMaintMaster;
public class SysParamsServices extends ModuleServices{
	
	public SysParamsServices(DbServices pmDbServices) {
		super(pmDbServices);
	}

	private SysParams  smtpServer ;
	private SysParams  mailSenderUserName; 
	private SysParams  mailSenderPassword; 
	private SysParams  consoleLoggingLevel;
	private static final String DEFAULT_USER_NAME = "admin";
 
	@Override
	public boolean isChanged() {
		return this.getDataSet().isChanged() ; 
	}
	@Override 
	public String getOrginalQuery()	
	{
		return  TableMaintMaster.getAllItemsQuery(SysParams.DB_TABLE_NAME, TableMaintMaster.CDB_SCHEMA_OWNER) ; 
	}
	
 	public DataSet getDataSet()
 	{	
 		this.getDbServices().setLogRequestToConsole(false);
 		DataSet ds = super.getDataSet();
 		this.getDbServices().setLogRequestToConsole(true);
 		return ds ; 
 	}
 
 @Override
	public Class getPersistentClass()
	{
		return SysParams.class;
	}

	@Override
	public boolean isCanHaveMultipleInstances()
	{
		return false;
	}
	
	
	private SysParams getSysParam( String pm_paramID)
	{
		SysParams result = null ; 
		SecUsrDta loggedUser = this.getDbServices().getLoggedUser();
		
		if (loggedUser != null)
		{
			result = this.getSysParamForUser(pm_paramID, loggedUser.getUsrNameValue());
		}
		
		if (result == null)
		{
			String defaultUser = DEFAULT_USER_NAME;
			result = this.getSysParamForUser(pm_paramID, defaultUser);
		}
		return result ;  
	}
	
	public SysParams getSysParamForUser(String pm_paramID , String pm_userName) {
		
		SysParams result = null;
		DataSet ds = this.getDataSet() ;
		if ( ds!= null)
		{
			for (PersistantObject po : ds.getPersistantObjectList())
			{
				SysParams sp = (SysParams) po ; 
				if (sp.getIdValue().toString().equals(pm_paramID)  && sp.getUsrIdValue().equalsIgnoreCase(pm_userName))
				{
					result = sp ; 
					break;
				}
			}
		}
		return result; 
	}
	
	
	public SysParams getEmailServer() 
	{
		if (smtpServer == null )
		{
			smtpServer =  this.getSysParam("2" );
		}
		return smtpServer ; 
	}
	
	public SysParams getMailSenderUserName() 
	{
		if (mailSenderUserName == null )
		{
			mailSenderUserName =  this.getSysParam("14" );
		}
		return mailSenderUserName ; 
	}
	
	public SysParams getConsoleLoggingLevel() 
	{
		if (consoleLoggingLevel == null )
		{
			consoleLoggingLevel =  this.getSysParam("17");			
		}
		return consoleLoggingLevel ; 
	}
	
	public SysParams getMailSenderPassword() 
	{
		if (mailSenderPassword == null )
		{
			mailSenderPassword =  this.getSysParam("15");			
		}
		return mailSenderPassword ; 
	}

	SysParams senderEmailAddress;
	public SysParams getSenderEmailAddress() 
	{
		if (senderEmailAddress == null )
		{
			senderEmailAddress =  this.getSysParam("3");			
		}
		return senderEmailAddress ; 
	}
	@Override
	public DbTable getDbTable() {
		return new DbTable(TableMaintMaster.CDB_SCHEMA_OWNER , SysParams.DB_TABLE_NAME , this.getDbServices());
	}

	public MailSender getMailSender() {
		MailSender ms = null;
		String userName = this.getMailSenderUserName().getValValue();
		String password = this.getMailSenderPassword().getValValue();
		String emailServer = this.getEmailServer().getValValue();
		try {
			 ms =  new MailSender(emailServer, userName, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ms;
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