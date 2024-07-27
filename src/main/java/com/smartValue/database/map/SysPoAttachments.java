package com.smartValue.database.map;
 
import java.util.HashMap;

import com.smartValue.database.Attribute;
import com.smartValue.database.AttributeChangeValidator;
import com.smartValue.database.DataSet;
import com.smartValue.database.DbForignKeyArray;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.audit.AuditInDbTriggerHandler;
import com.smartValue.database.map.auto._SysPoAttachments;
import com.smartValue.database.map.security.PersistentObjectSecurityControl;
import com.smartValue.database.map.validators.SysPoAttachmentsChangeValidator;
import com.smartValue.database.trigger.TriggerHandler;

public class SysPoAttachments extends _SysPoAttachments {
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
	 return new SysPoAttachmentsChangeValidator(pm_secUserData , pm_po  , pm_key); 
	}
	 public void initialize() 
	 { 
		//Write your own initialization code here this will help you greatly improve performance especially
		// apply our standard rule < Minimise code inside any getXyz() method - simply return object -  > 
	 } 
		@Override
		public void afterAttributeChange(Attribute att) {
			String key = att.getKey() ; 
			if (key.equals(SysPoAttachments.TABLE_NAME))
			{
				SysPoAttachments spoa =  (SysPoAttachments) att.getParentPersistantObject() ;
				spoa.getAttacmentGroupId().refreshListOfValues();
			}
		}

		@Override
		public void beforeAttributeChange(Attribute att) throws Exception {
			// TODO Auto-generated method stub

		}
		private PersistantObject ownerPO ; 
		/**
		 * 
		 * @return The Owner Persistent Object of this attachment 
		 */
		public PersistantObject getOwnerPO()
		{
			if (ownerPO == null)
			{
				DataSet attchGroupDataSet = this.getParentDataSet().getParent().getPersistantObject().getParentDataSet() ; 
				ownerPO = (PersistantObject) attchGroupDataSet.getProperties().get(SysPoAttachmentGroup.ATTACHMENT_OWNER_KEY); 
			}
			return ownerPO ;
		}
		
		public DataSet getSysPoAttachMetadataValuesDS()
		{
			DataSet result = null;
			SysPoAttachmentGroup ag =  (SysPoAttachmentGroup) this.getParent().getPersistantObject() ;
			//ag.getSysPoAttatchGroupMetadataDS();
			//TODO..
			return result;
		}
}
