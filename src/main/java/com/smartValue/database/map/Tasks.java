package com.smartValue.database.map;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import com.smartValue.components.model.DateInterval;
import com.smartValue.database.ApplicationContext;
import com.smartValue.database.AttChangeListner;
import com.smartValue.database.Attribute;
import com.smartValue.database.AttributeChangeValidator;
import com.smartValue.database.ClobAttribute;
import com.smartValue.database.DbForignKeyArray;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.audit.AuditInDbTriggerHandler;
import com.smartValue.database.map.auto._Tasks;
import com.smartValue.database.map.security.PersistentObjectSecurityControl;
import com.smartValue.database.map.services.ModuleServicesContainer;
import com.smartValue.database.map.services.SecUserDataService;
import com.smartValue.database.map.validators.TasksChangeValidator;
import com.smartValue.database.trigger.TriggerHandler;
import com.smartValue.event.logging.Console;

public class Tasks extends _Tasks {
	private static HashMap<String, DbForignKeyArray> childrenForignKeys;
	
	@Override
	public HashMap<String, DbForignKeyArray> getChildrenForignKeys() 
	{
		 if (childrenForignKeys == null) 
		  { childrenForignKeys = new HashMap<String, DbForignKeyArray>(); 
			  //TODO... Fill Your Childern 
			  //Example ... this.getTableMaintMaster().getDbForignKey(new DbTable(SavedSearchDetail.DB_TABLE_NAME)); 
		  } 
		 return childrenForignKeys; 
	}
	
	@Override
	public PersistentObjectSecurityControl getSecurityController() 
	{
		return null;
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
	@Override 
	public AttributeChangeValidator getAttributeChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key)
	{ 
	 return new TasksChangeValidator(pm_secUserData , pm_po  , pm_key); 
	}
	
	public void save() throws Exception
	{
	
		ClobAttribute desc = (ClobAttribute) this.getDescription() ; 
		if (desc.getClobStrValue()== null  )
		{
			this.getDescription().setValue(this.getBrefDescValue());
		}
		BigDecimal comp = new BigDecimal(100);
		if (this.getTaskStateValue().equals(comp))
		{
			this.setPercCompletedValue(comp);
		}
		this.getAssignedBy().setValue(this.getDbService().getLoggedUser().getUsrNameValue());
		if (this.isNeedSave())
		{
			super.save();
			
			String loginName = this.getDbService().getLoggedUser().getLoginName();
			String to = loginName.equals(this.getAssignedByValue()) ? this.getAssignedToValue() : this.getAssignedByValue();	
			this.getDbService().getLoggedUser().getSecUserCommMsgsDataSet().addNew();
			SecUserCommunicationMsgs secComMsg = (SecUserCommunicationMsgs)this.getDbService().getLoggedUser().getSecUserCommMsgsDS().getCurrentItem();
			secComMsg.setFromUserValue(loginName);
			secComMsg.setToUsersValue(to);
			secComMsg.setMsgHeaderValue("tasks updates");
			secComMsg.setMsgBodyValue("Dear "+to+",\n "+
					loginName+" has updated your task number: "+this.getTaskIdValue()+",\n " +
							"the current stauts of the task is: "+this.getTaskStateValue()+",\nyou can check it now");
			secComMsg.save();
		}
	}
	
	public void goToAssignedToDetails()
	{
		//1- Adjust SecUserDataService ;
		String assinedTo = this.getAssignedToValue();
		ModuleServicesContainer msc =  this.getModuleServiceContainer(); 
		SecUserDataService sus =  msc.getSecUserDataService();
		sus.setDataSetWithCondition(" Where " + SecUsrDta.USR_NAME +"='"+ assinedTo +"'" , false);
		
		//2- Simulate as if User clicked on Tree Node for Sec Users Maintainance 
		msc.getSysMenuServices().goToSysMnu("A0001");
		
	}
	private DateInterval   dateInter;
	public DateInterval getDateInter() {
		return dateInter;
	}
	public void initialize()
	{

		// in after attributeChange for the defendant att Call 
		this.refreshListOfValues() ;
		// in AfterAttributeChange() method ... call assignTo.setQueryForLOV(queryForLOV) ;
		
		
		
		dateInter=new  DateInterval(this.getAssignDate(),this.getActualStartDate() , getEgyptWeekEnds());
	}
	private ArrayList<Integer> getEgyptWeekEnds()
	{
		ArrayList egyptWeekEndDays = new ArrayList<Integer>();
		egyptWeekEndDays.add(Calendar.SATURDAY) ; 
		egyptWeekEndDays.add(Calendar.FRIDAY );
		return egyptWeekEndDays ;  
	}
	
	private ArrayList<Integer> getSaudiWeekEnds() 
	{
		ArrayList saudiWeekEndDays = new ArrayList<Integer>();
		saudiWeekEndDays.add(Calendar.THURSDAY) ; 
		saudiWeekEndDays.add(Calendar.FRIDAY );
		return saudiWeekEndDays ;
	}
	public void  refreshListOfValues()
	{
		//Sample Of How To Dynamically associate a LOV for an Attribuet

		Attribute assignTo  = this.getAssignedTo() ; 
		String queryForLOV = assignTo.getTableMaintDetail().getSelectListQueryValue() ; 
		assignTo.setQueryForLOV(queryForLOV) ;
	}
	 
	Attribute assignedTo ;
	public Attribute getAssignedTo()
	{
		if ( assignedTo == null )
		{
			assignedTo = super.getAssignedTo() ;
			AttChangeListner attChLis = new AttChangeListner()
			{
				
				public void afterChange(Attribute att) {
					Console.log("\nAfter Change ... ", this.getClass());
				}
	
				
				public void beforeChange(Attribute att) throws Exception {
					Console.log("\nBefore Change ... ", this.getClass());
				}
			} ;
			assignedTo.addChangeListner(attChLis) ;
		}
		return  assignedTo ;
	}
	
	public void beforeInsert()
	{
		
		BigDecimal nextSeqNumber = this.getTableMaintMaster().getNextSequanceNumber(Tasks.TASK_ID) ; 
		this.setTaskIdValue(nextSeqNumber) ;
	}

	@Override
	public void afterAttributeChange(Attribute att) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeAttributeChange(Attribute att) throws Exception {
		// TODO Auto-generated method stub
		
	}
}