package com.smartValue.epm.map;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.Attribute;
import com.smartValue.database.AttributeChangeValidator;
import com.smartValue.database.DataSet;
import com.smartValue.database.DbForignKeyArray;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.audit.AuditInDbTriggerHandler;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.security.PersistentObjectSecurityControl;
import com.smartValue.database.map.services.ModuleServicesContainer;
import com.smartValue.database.trigger.TriggerHandler;
import com.smartValue.epm.map.auto._BscObjective;

import java.util.HashMap;

public class BscObjective extends _BscObjective {
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
	  private com.smartValue.database.PersistantObject  abc ;  
	  public com.smartValue.database.PersistantObject getAbc() 
	  { 
		 if ( this.abc== null)
		 {
			 refreshAbc() ;  
		 }
		 return abc ; 
	  } 
	  private com.smartValue.database.PersistantObject calcAbc() 
	  { 
		  com.smartValue.database.PersistantObject result = null ; 
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
		
		private DataSet bscObjectivesKpiDs ; 
		public DataSet getBscObjectivesKpiDs(boolean m_refresh)
		{
			if (bscObjectivesKpiDs == null || m_refresh)
			{
				String queryStr = "Select * from "+ BscObjectiveKpi.DB_TABLE_OWNER +"."+ BscObjectiveKpi.DB_TABLE_NAME  
					+" Where "+ BscObjectiveKpi.BUSINESS_UNIT + " = '" + this.getBusinessUnitValue() + "'"
					+ "And " + BscObjectiveKpi.OBJECTIVE_TYPE + " = '" + this.getObjectiveTypeValue() + "'"
					+ "And " + BscObjectiveKpi.OBJECTIVE_ID + " = " + this.getObjectiveIdValue() 
					+" Order by "+ BscObjectiveKpi.KPI_ID ;
				
				 bscObjectivesKpiDs = this.getDbService().queryForDataSet(queryStr, com.smartValue.epm.map.BscObjectiveKpi.class) ;

			}
			return bscObjectivesKpiDs ; 
		}
}