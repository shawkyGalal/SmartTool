package com.smartValue.database;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Stack;

import com.smartValue.database.map.SysMsg;
import com.smartValue.database.map.services.ModuleServices;
import com.smartValue.database.map.services.SysMsgServices;
import com.smartValue.event.logging.Console;

public class MessagesCommnuicatorService extends ModuleServices
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MessagesCommnuicatorService(DbServices pm_dbServices) {
		super(pm_dbServices);
		// TODO Auto-generated constructor stub
	}

	private Stack<Object> sysMessagesToUser = new Stack<Object>();
	
	public boolean isThereSysMessgesToUser()
	{
		return ! this.sysMessagesToUser.isEmpty();
	}


	public void sendMessageToUser(String pm_sysMsg)
	{
		this.sysMessagesToUser.push(pm_sysMsg);
		Console.log(pm_sysMsg, this.getClass());
	}
	
	public void sendValidationresultToUser(ValidationResult pm_vrs)
	{
		for (String msg : pm_vrs.getInvalidMessages())
		{
			this.sysMessagesToUser.push(msg);
			Console.log(msg, this.getClass());
			
		}
	}
	
	public void sendExceptionToUser(Exception e)
	{
		this.sysMessagesToUser.push(e.getMessage());
		Console.log(e.getMessage(), this.getClass());
	}
	
	public void sendMessageToUser(SysMsg pm_sysMsg)
	{
		this.sysMessagesToUser.push(pm_sysMsg);
	}
	
	public Stack<Object> getSysMessagesToUser()
	{
		return this.sysMessagesToUser ; 
		/*
		ArrayList<Object> smal = new ArrayList<Object>();
		if(!sysMessagesToUser.isEmpty())
			for (int i = 0 ; i< this.sysMessagesToUser.size() ; i++ )
			{
				smal.add(sysMessagesToUser.get(i));
			}
		//sysMessagesToUser = new Stack<Object>();
		return smal;
		*/
	}
	
	public void clear()
	{
		sysMessagesToUser = new Stack<Object>();
	}
	public boolean isEmptyMessages()
	{
		return sysMessagesToUser.isEmpty();
	}


	public void sendValidationResultsToUser(ArrayList<ValidationResult> allValidationResults)
	{
		//this.sendMessageToUser("Some Entries with Invalid Value(s)...Please Correct The Invalid Entry(ies) First...");
		if (allValidationResults.size() > 0 )
		{
		DbServices dbs = this.getDbServices();
		SysMsgServices sysmsgservices=dbs.getModuleServiceContainer().getSysMsgServices();
		SysMsg 	sysMsg = sysmsgservices.getMsgSomeEntrieswithInvalidValue();
		this.sendMessageToUser(sysMsg);
		}
		for (int i = 0 ; i< allValidationResults.size() ; i++)
		{
			String msg = ((ValidationResult) allValidationResults.get(i)).getInvalidMessage();
			this.sendMessageToUser(msg);
		}
		
	}

	public String  CascadeStyle(Object pm_obj)
	{
		String sysMsgColor = "";
		if (pm_obj instanceof SysMsg)
		{
			SysMsg sysMsg = (SysMsg) pm_obj ; 
			if(	sysMsg.getMsgTypeValue().compareTo(new BigDecimal(0))==0)// o  For Successful  
			{
				sysMsgColor = "color: green;";
			}
			
			if(	sysMsg.getMsgTypeValue().compareTo(new BigDecimal(1))==0)  // 1 For Error 
			{
				sysMsgColor = "color: red;";
			}
			
			if(	sysMsg.getMsgTypeValue().compareTo(new BigDecimal(2))==0)// 2 FOR Confirmation
			{
				sysMsgColor = "color: blue;";
			}
		}
		else{
			sysMsgColor = "color: #FF8C00;";
		}
		
		return sysMsgColor;
	}
	
	public String convertToString(Object pm_obj)
	{
		String result ; 
		if(pm_obj==null)
		{
			return "";
		}
		if (pm_obj instanceof SysMsg)
		{
			SysMsg sysMsg = (SysMsg) pm_obj ;
			result = sysMsg.getMsgDescWithParam();
			
		}
		else
		{
			result = pm_obj.toString() ;
		}
		return result;
		
		
	}
	
	public void startNewTransaction()
	{
		this.clear() ;
	//	this.sendMessageToUser("----Start System Transaction-----"); 
		
	}


	@Override
	public Class getPersistentClass() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getOrginalQuery() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean isCanHaveMultipleInstances() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isChanged() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public DbTable getDbTable() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void afterModuleRemoved() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void beforeModuleRemoved() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
