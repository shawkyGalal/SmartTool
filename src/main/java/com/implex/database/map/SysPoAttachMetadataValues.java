package com.implex.database.map;

import java.util.ArrayList;
import java.util.HashMap;

import com.implex.database.Attribute;
import com.implex.database.AttributeChangeValidator;
import com.implex.database.DbForignKeyArray;
import com.implex.database.PersistantObject;
import com.implex.database.audit.AuditInDbTriggerHandler;
import com.implex.database.map.auto._SysPoAttachMetadataValues;
import com.implex.database.map.security.PersistentObjectSecurityControl;
import com.implex.database.trigger.TriggerHandler;

public class SysPoAttachMetadataValues extends _SysPoAttachMetadataValues {
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
		return null; 
	}
	 public boolean  checkValidationOfVarNameValue()
	 {
		 if (this.getMandatoryValue().equals("Y"))
		 {
			 if(this.getVarValueValue()==null||this.getVarValueValue().equals(""))
			 {
				 return true;
			 }
		 }
		 
		 return false;
	 }
	
	 public void initialize() 
	 { 
		 this.refreshSysPoAttatchGroupMetadata();
		//Write your own initialization code here this will help you greatly improve performance especially
		// apply our standard rule < Minimise code inside any getXyz() method - simply return object -  > 
	 } 
	 
	 private SysPoAttatchGroupMetadata  sysPoAttatchGroupMetadata ; 
	 public SysPoAttatchGroupMetadata getSysPoAttatchGroupMetadata()
	 {
		 return sysPoAttatchGroupMetadata ; 
	 }
	 
	 private SysPoAttatchGroupMetadata calcSysPoAttatchGroupMetadata()
	 {
		 SysPoAttatchGroupMetadata result = null ; 
		 String query = TableMaintMaster.getAllItemsQuery(SysPoAttatchGroupMetadata.DB_TABLE_NAME , TableMaintMaster.CDB_SCHEMA_OWNER) + "Where t." + SysPoAttatchGroupMetadata.TABLE_NAME + "= '"+this.getTableNameValue()+"'" 
		 				+ "And t." + SysPoAttatchGroupMetadata.TABLE_OWNER +" = '"+this.getTableOwnerValue()+"'"
		 				+ "And t." + SysPoAttatchGroupMetadata.GROUP_ID +" = '"+this.getGroupIdValue()+"'" 
		 				+ "And t." + SysPoAttatchGroupMetadata.VAR_NAME +" = '"+this.getVarNameValue()+"'" ; 
		 ArrayList<PersistantObject> al = this.getDbService().queryForDataSet(query, SysPoAttatchGroupMetadata.class).getPersistantObjectList() ;
		 if (al.size() > 0 )
		 {
			 result = (SysPoAttatchGroupMetadata) al.get(0);
		 }
		 return result ; 
	 }
	 
	 public void refreshSysPoAttatchGroupMetadata()
	 {
		 this.sysPoAttatchGroupMetadata = calcSysPoAttatchGroupMetadata() ; 
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
