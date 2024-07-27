package com.smartValue.database.map;

import java.util.HashMap;
import java.util.List;

import com.sideinternational.sas.BaseException;
import com.smartValue.database.ApplicationContext;
import com.smartValue.database.Attribute;
import com.smartValue.database.AttributeChangeValidator;
import com.smartValue.database.ClobAttribute;
import com.smartValue.database.DataSet;
import com.smartValue.database.DbForignKeyArray;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.audit.AuditInDbTriggerHandler;
import com.smartValue.database.map.auto._ActionNeededList;
import com.smartValue.database.map.security.ActionNeededListSecurityControlHandlerImpl;
import com.smartValue.database.map.security.PersistentObjectSecurityControl;
import com.smartValue.database.trigger.TriggerHandler;
import com.smartValue.event.logging.Console;

public class ActionNeededList extends _ActionNeededList {
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
		return  new ActionNeededListSecurityControlHandlerImpl();
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
		return null; 
	}
	 public void initialize() 
	 { 
		// Write your own initialization code here this will help you greatly improve performance especially
		// apply our standard rule < Minimise code inside any getXyz() method - simply return object -  > 
		 //ds =calcDatasetFromList();
		 count=getCountofRecords();
	 } 
	 
	 int count;
	private int getCountofRecords()
	 {
		 int result = 0 ;
		 String countQuery="";
		 try {
			 ClobAttribute qfl = (ClobAttribute) this.getQueryForList() ; 
			String  query = qfl.getClobStrValue();
			countQuery="Select count(*) COUNT from ( " + getQueryAfterSubstitutewithLoggedUser(query.trim()) + ")";
			List<PersistantObject> listPo = this.getDbService().queryForList(countQuery);
			result  = Integer.parseInt( (listPo.get(0).getAttributeValue("COUNT")).toString());
		 } catch (Exception e) {
			 Console.log(" Invalid Query:\n   "+countQuery, this.getClass());
			e.printStackTrace();
			result=-1;
		}
		return result ; 
	 }
	 public int getCount()
	 {
		return count;
	 }
	 
	 public void refreshDatasetFromList()
	 {
		 this.dataSetFromList = this.calcDatasetFromList() ;
	 }
	 private String  getQueryAfterSubstitutewithLoggedUser(String	query )
	 {
		 String loggedUser = this.getDbService().getLoggedUser().getUsrNameValue();
			
		query = query.replace(PersistantObject.LOGGED_USER_PROPERTY_DELIMTER, loggedUser);
		 return query;
	 }
	 
	 DataSet dataSetFromList;
	 private DataSet calcDatasetFromList()
	 {
			DataSet ds = null;
			String query = null;
			ClobAttribute qfl = (ClobAttribute) this.getQueryForList() ; 
			query =  qfl.getClobStrValue();
			
			//............to   get count of rows.................
			count=getCountofRecords();
			//...................................................
			query=getQueryAfterSubstitutewithLoggedUser(query.trim());
			try {
				ds = this.getDbService().queryForDataSet( query ,Class.forName( this.getClassNameValue() ));
			} catch (Exception e) {
				 Console.log( " Invalid Query:\n  ds=null   "+query, this.getClass());
				e.printStackTrace();
			}
			
			return ds;
	 }
	 public DataSet getDataSetFromList()
	 {
		return dataSetFromList ;
	 }
	 
		@Override
		public void beforeAttributeChange(Attribute pm_att) throws BaseException {
	}
		@Override
		public void afterAttributeChange(Attribute att){			
			String key = att.getKey() ;
			if (key.equals(ActionNeededList.QUERY_FOR_LIST))
			{ 
				ActionNeededList actionNeededList =  (ActionNeededList) att.getParentPersistantObject();
				actionNeededList.refreshDatasetFromList();
			}		
			
	}
}