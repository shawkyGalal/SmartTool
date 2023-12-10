package com.implex.sys.map;

import com.implex.sys.map.auto._AllConstraints; 
import com.implex.database.Attribute;
import com.implex.database.DataSet;
import com.implex.database.DbForignKey;
import com.implex.database.PersistantObject;
import com.implex.database.AttributeChangeValidator;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.security.PersistentObjectSecurityControl;
import com.implex.database.trigger.TriggerHandler;
import com.implex.database.audit.AuditInDbTriggerHandler;
import com.implex.database.DbForignKeyArray;
import java.util.HashMap;

public class AllConstraints extends _AllConstraints {
	 private static HashMap<String, DbForignKeyArray> childrenForignKeys;
	@Override
	public HashMap<String, DbForignKeyArray> getChildrenForignKeys() 
	{
		 if (childrenForignKeys == null) 
		  { 
			childrenForignKeys = new HashMap<String, DbForignKeyArray>();
			for (DbForignKeyArray fka : this.getTableMaintMaster().getAllForignKeysArrays())
		  	{
			  childrenForignKeys.put(fka.getName(), fka);
		  	}
			
			DbForignKey[] dbfk = {new DbForignKey(AllConstraints.CONSTRAINT_NAME , AllConsColumns.CONSTRAINT_NAME , AllConsColumns.DB_TABLE_NAME , "SYS")
								, new DbForignKey(AllConstraints.OWNER , AllConsColumns.OWNER , AllConsColumns.DB_TABLE_NAME , "SYS")};
			DbForignKeyArray fkaForConstrainColumns = new DbForignKeyArray(dbfk , false);
			//fkaForConstrainColumns.setChildOwner("SYS");
			childrenForignKeys.put(AllConsColumns.DB_TABLE_NAME, fkaForConstrainColumns);
			//TODO... Fill Your Childern 
			  //Example ... this.getTableMaintMaster().getDbForignKey(new DbTable(SavedSearchDetail.DB_TABLE_NAME)); 
		  } 
	 return childrenForignKeys; 
	}
	
	private DataSet constraintColumnsDS ;

	public DataSet getConstraintColumnsDS() {
		if (constraintColumnsDS == null)
		{
			try {
				constraintColumnsDS = this.getChildrenDataSet(AllConsColumns.DB_TABLE_NAME, AllConsColumns.class, false, "ORDER BY " + AllConsColumns.POSITION);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return constraintColumnsDS;
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

	 private AllConstraints refrencedConstraint ;
	 public AllConstraints getRefrencedConstraint() 
	 {
		if (this.getConstraintTypeValue().equals("R") && refrencedConstraint == null )
			{
				String query = this.getTableMaintMaster().getAllItemsQuery() + " Where t." + AllConstraints.CONSTRAINT_NAME + " = '"+this.getRConstraintNameValue()+"'" ;
				DataSet ds = this.getDbService().queryForDataSet(query, AllConstraints.class);
				if (ds != null &&  ds.getPersistantObjectList().size()> 0)
				{
					refrencedConstraint =  (AllConstraints) ds.getPersistantObjectList().get(0);
				}
			}
		return refrencedConstraint;
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