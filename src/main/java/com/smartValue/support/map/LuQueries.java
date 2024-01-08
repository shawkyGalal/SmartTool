package com.smartValue.support.map;

import com.smartValue.support.map.auto._LuQueries; 
import com.implex.database.ApplicationContext;
import com.implex.database.DataSet;
import com.implex.database.PersistantObject;
import com.implex.database.Attribute;
import com.implex.database.AttributeChangeValidator;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.security.PersistentObjectSecurityControl;
import com.implex.database.map.services.ModuleServicesContainer;
import com.implex.database.trigger.TriggerHandler;
import com.implex.database.audit.AuditInDbTriggerHandler;
import com.implex.database.DbForignKeyArray;
import java.util.HashMap;

public class LuQueries extends _LuQueries {
	 private static HashMap<String, DbForignKeyArray> childrenForignKeys;
	@Override
	public HashMap<String, DbForignKeyArray> getChildrenForignKeys() 
	{
		 if (childrenForignKeys == null) 
		  { childrenForignKeys = new HashMap<String, DbForignKeyArray>(); 
			  for (DbForignKeyArray fka : this.getTableMaintMaster().getAllForignKeysArrays()) 
			  { 
			  childrenForignKeys.put(fka.getName(), fka); 
			  } 
			  //TODO... Fill Your Childern 
			  //Example ... this.getTableMaintMaster().getDbForignKey(new DbTable("ICDB" , SavedSearchDetail.DB_TABLE_NAME)); 
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
		return null; 
	}
	 public void initialize() 
	 { 
		//Write your own initialization code here this will help you greatly improve performance especially
		// apply our standard rule < Minimise code inside any getXyz() method - simply return object -  > 
	 } 
	  private com.implex.database.PersistantObject  abc ;  
	  public com.implex.database.PersistantObject getAbc() 
	  { 
		 if ( this.abc== null)
		 {
			 refreshAbc() ;  
		 }
		 return abc ; 
	  } 
	  private com.implex.database.PersistantObject calcAbc() 
	  { 
		  com.implex.database.PersistantObject result = null ; 
		  //Write Down Your Code here to calculate the value of abc... 
		  return result ;  
	  } 
	 
	  public void refreshAbc() 
	  { 
		  //Important Please call this method in the afterAttributeChange() method for any attribute used to calculate this variable 
		  this.abc = calcAbc() ; 
	  } 
		@Override
		public void beforeAttributeChange(Attribute pm_att) throws Exception {
		}
		@Override
		public void afterAttributeChange(Attribute pm_att) {
		}
		
		private DataSet queryDetailDs ;
		public DataSet getQueryDetailDs( )
		{
			if (queryDetailDs == null )
			{
				String queryStr = "Select qn.* , qn.rowid from Support.LU_QUERY_DETAILS qn where qn.query_id = " + this.getIdValue()
			 + " order by qn.query_index " ;
			queryDetailDs = this.getDbService().queryForDataSet(queryStr, com.smartValue.support.map.LuQueryDetails.class) ;
			}
			return queryDetailDs ; 
			
		}
		
}