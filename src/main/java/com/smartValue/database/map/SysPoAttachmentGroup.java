package com.smartValue.database.map;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import com.smartValue.database.Attribute;
import com.smartValue.database.AttributeChangeValidator;
import com.smartValue.database.DataSet;
import com.smartValue.database.DbForignKey;
import com.smartValue.database.DbForignKeyArray;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.audit.AuditInDbTriggerHandler;
import com.smartValue.database.map.auto._SysPoAttachmentGroup;
import com.smartValue.database.map.security.PersistentObjectSecurityControl;
import com.smartValue.database.map.security.SysPoAttachmentGroupSecurity;
import com.smartValue.database.trigger.TriggerHandler;

public class SysPoAttachmentGroup extends _SysPoAttachmentGroup {
	
	private static HashMap<String, DbForignKeyArray> childrenForignKeys;
	public static final String ATTACHMENT_OWNER_KEY = "ATTACHMENT_OWNER";

	@Override
	public HashMap<String, DbForignKeyArray> getChildrenForignKeys() 
	{
		if (childrenForignKeys == null)
		{  
			childrenForignKeys = new HashMap<String, DbForignKeyArray>();
			childrenForignKeys.put(SysPoAttachments.DB_TABLE_NAME,SysPoAttachmentGroup.ForignKeyForSysPoAttachments );
			childrenForignKeys.put(SysPoAttatchGroupMetadata.DB_TABLE_NAME,SysPoAttachmentGroup.ForignKeyForSysPoAttatchGroupMetadata );
			
		}
		
	 return childrenForignKeys; //TODO... Fill Your Childern 
	}
	//.....................................................................
	private static final DbForignKeyArray ForignKeyForSysPoAttatchGroupMetadata = new DbForignKeyArray ( new DbForignKey[]{new DbForignKey(SysPoAttachmentGroup.TABLE_NAME ,SysPoAttatchGroupMetadata.TABLE_NAME )
	,new DbForignKey(SysPoAttachmentGroup.TABLE_OWNER ,SysPoAttatchGroupMetadata.TABLE_OWNER ),new DbForignKey(SysPoAttachmentGroup.GROUP_ID ,SysPoAttatchGroupMetadata.GROUP_ID )
	} , false ) ; 
	private DataSet sysPoAttatchGroupMetadataDS = null;
	
	public DataSet getSysPoAttatchGroupMetadataDS()
	{
		if(sysPoAttatchGroupMetadataDS == null);
		try
		{
			sysPoAttatchGroupMetadataDS =  this.getChildrenDataSet(SysPoAttatchGroupMetadata.DB_TABLE_NAME , SysPoAttatchGroupMetadata.class , false);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return sysPoAttatchGroupMetadataDS;
	}
	//......................................................................
	private static DbForignKeyArray ForignKeyForSysPoAttachments=
		 new DbForignKeyArray ( new DbForignKey[]{
				 new DbForignKey(SysPoAttachmentGroup.TABLE_NAME ,SysPoAttachments.TABLE_NAME )
				 ,new DbForignKey(SysPoAttachmentGroup.TABLE_OWNER ,SysPoAttachments.TABLE_OWNER )
				 ,new DbForignKey(SysPoAttachmentGroup.GROUP_ID ,SysPoAttachments.ATTACMENT_GROUP_ID )
			} , false ) ;
	
	private DataSet sysPoAttachmentsDS = null;
	public DataSet getSysPoAttachmentsDS() 
	{
		if(sysPoAttachmentsDS == null )
		{
			try
			{ 	 
				PersistantObject ownerPO = this.getOwnerPO();
				String extraWhere = (ownerPO != null )? " And " + SysPoAttachments.PO_KEY  + " = '" + ownerPO.getDisplayTxtWithDQ() +"'" : " And 1<>1 " ; 
				sysPoAttachmentsDS =  this.getChildrenDataSet( SysPoAttachments.DB_TABLE_NAME , SysPoAttachments.class , true , extraWhere );
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return sysPoAttachmentsDS ;
	}

	@Override
	public PersistentObjectSecurityControl getSecurityController() 
	{
		return  new SysPoAttachmentGroupSecurity();
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
		
	 } 
	 
	 
	 public boolean   isGroupHaveAttachment()
	 {
		 if (this.getSysPoAttachmentsDS().getPersistantObjectList().size()==0)
		 {
			 return false;
		 }
		 return true ;
	 }
	 
	 
      private  void  insertGroupIdInSysPoAttatchGroupMetadataDS()
      {
    	 List<PersistantObject> AttatchGroupMetadataList =this.getSysPoAttatchGroupMetadataDS().getPersistantObjectList();
    	  if(this.getSysPoAttatchGroupMetadataDS()!=null&&AttatchGroupMetadataList.size()!=0)
    	  {
    		  for(PersistantObject po :AttatchGroupMetadataList)
    		  {
    			  SysPoAttatchGroupMetadata sysPoAttatchGroupMetadata =(SysPoAttatchGroupMetadata)po;
    			  sysPoAttatchGroupMetadata.setGroupIdValue(this.getGroupIdValue());
    		  }
    	  }
    	 
    	  
      }
      
	 @Override
	public void beforeInsert() throws Exception {
		super.beforeInsert();
		HashMap<String, Object> xx = new HashMap<String, Object>() ;
		xx.put(SysPoAttachmentGroup.TABLE_NAME, this.getTableNameValue());
		BigDecimal nextGroupId =  this.getTableMaintMaster().getNextSequanceNumber(SysPoAttachmentGroup.GROUP_ID , xx);
		this.setGroupIdValue(nextGroupId.toString());
		/*
		String Qeury="SELECT   MAX(CAST(S.GROUP_ID AS Int)) MAXGROUPID FROM SYS_PO_ATTACHMENT_GROUP S     where ( S.TABLE_NAME='"+this.getTableNameValue()  +"'  and S.TABLE_OWNER='"+this.getTableOwnerValue()+"' )";
		List<PersistantObject> sysPoAttachmGroupList =this.getDbService().queryForList(Qeury, SysPoAttachmentGroup.class);
		String   MaxGROUPID     =(String) (sysPoAttachmGroupList.get(0).getAttribute("MAXGROUPID").getValue());
		if(MaxGROUPID == null || MaxGROUPID.equals(""))
		{
			this.setGroupIdValue("1");
			this.insertGroupIdInSysPoAttatchGroupMetadataDS();
		}
		else
		{
		int       newGroupID     =Integer.parseInt(MaxGROUPID)+1 ;
		this.setGroupIdValue(Integer.toString(newGroupID));    
		this.insertGroupIdInSysPoAttatchGroupMetadataDS();	                      
		                                              
		}
		*/
	}
	@Override
	public void afterAttributeChange(Attribute att) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void beforeAttributeChange(Attribute att) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public PersistantObject getOwnerPO()
	{
		return (PersistantObject) this.getParentDataSet().getProperties().get(SysPoAttachmentGroup.ATTACHMENT_OWNER_KEY);
	}
	
	public void setOwnerPO(PersistantObject po)
	{
		this.getParentDataSet().getProperties().put(SysPoAttachmentGroup.ATTACHMENT_OWNER_KEY , po);
		this.sysPoAttachmentsDS = null ;
	}
	 
	 
}
