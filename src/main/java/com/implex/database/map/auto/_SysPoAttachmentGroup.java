package com.implex.database.map.auto;

import com.implex.database.Attribute;
import com.implex.database.DbTable;
import com.implex.database.PersistantObject;

public abstract class _SysPoAttachmentGroup extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "SYS_PO_ATTACHMENT_GROUP";
 public static final String DB_TABLE_OWNER = "ICDB";

	public _SysPoAttachmentGroup(){
	}

	 public DbTable getTable() 
	 { 
			return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String TABLE_OWNER ="TABLE_OWNER" ;

	public void setTableOwnerValue(java.lang.String   pm_tableOwner){
		this.getAttribute(TABLE_OWNER ).setValue( pm_tableOwner );
	}
 
	public java.lang.String getTableOwnerValue(){
		return (java.lang.String) this.getAttribute ( TABLE_OWNER).getValue()  ;
	}
 
	public Attribute getTableOwner(){
		return this.getAttribute ( TABLE_OWNER)  ;
	}

	public static final String TABLE_NAME ="TABLE_NAME" ;

	public void setTableNameValue(java.lang.String   pm_tableName){
		this.getAttribute(TABLE_NAME ).setValue( pm_tableName );
	}
 
	public java.lang.String getTableNameValue(){
		return (java.lang.String) this.getAttribute ( TABLE_NAME).getValue()  ;
	}
 
	public Attribute getTableName(){
		return this.getAttribute ( TABLE_NAME)  ;
	}

	public static final String GROUP_ID ="GROUP_ID" ;

	public void setGroupIdValue(java.lang.String   pm_groupId){
		this.getAttribute(GROUP_ID ).setValue( pm_groupId );
	}
 
	public java.lang.String getGroupIdValue(){
		return (java.lang.String) this.getAttribute ( GROUP_ID).getValue()  ;
	}
 
	public Attribute getGroupId(){
		return this.getAttribute ( GROUP_ID)  ;
	}

	public static final String PO_KEY ="PO_KEY" ;

	public void setPoKeyValue(java.lang.String   pm_poKey){
		this.getAttribute(PO_KEY ).setValue( pm_poKey );
	}
 
	public java.lang.String getPoKeyValue(){
		return (java.lang.String) this.getAttribute ( PO_KEY).getValue()  ;
	}
 
	public Attribute getPoKey(){
		return this.getAttribute ( PO_KEY)  ;
	}

	public static final String GROUP_DESC ="GROUP_DESC" ;

	public void setGroupDescValue(java.lang.String   pm_groupDesc){
		this.getAttribute(GROUP_DESC ).setValue( pm_groupDesc );
	}
 
	public java.lang.String getGroupDescValue(){
		return (java.lang.String) this.getAttribute ( GROUP_DESC).getValue()  ;
	}
 
	public Attribute getGroupDesc(){
		return this.getAttribute ( GROUP_DESC)  ;
	}

	public static final String GROUP_DESC_ ="GROUP_DESC_" ;

	public void setGroupDesc_Value(java.lang.String   pm_groupDesc_){
		this.getAttribute(GROUP_DESC_ ).setValue( pm_groupDesc_ );
	}
 
	public java.lang.String getGroupDesc_Value(){
		return (java.lang.String) this.getAttribute ( GROUP_DESC_).getValue()  ;
	}
 
	public Attribute getGroupDesc_(){
		return this.getAttribute ( GROUP_DESC_)  ;
	}

	public static final String PREPARED_BY ="PREPARED_BY" ;

	public void setPreparedByValue(java.lang.String   pm_preparedBy){
		this.getAttribute(PREPARED_BY ).setValue( pm_preparedBy );
	}
 
	public java.lang.String getPreparedByValue(){
		return (java.lang.String) this.getAttribute ( PREPARED_BY).getValue()  ;
	}
 
	public Attribute getPreparedBy(){
		return this.getAttribute ( PREPARED_BY)  ;
	}

	public static final String PREPARED_DT ="PREPARED_DT" ;

	public void setPreparedDtValue(java.sql.Timestamp   pm_preparedDt){
		this.getAttribute(PREPARED_DT ).setValue( pm_preparedDt );
	}
 
	public java.sql.Timestamp getPreparedDtValue(){
		return (java.sql.Timestamp) this.getAttribute ( PREPARED_DT).getValue()  ;
	}
 
	public Attribute getPreparedDt(){
		return this.getAttribute ( PREPARED_DT)  ;
	}

	public static final String MODIFIED_BY ="MODIFIED_BY" ;

	public void setModifiedByValue(java.lang.String   pm_modifiedBy){
		this.getAttribute(MODIFIED_BY ).setValue( pm_modifiedBy );
	}
 
	public java.lang.String getModifiedByValue(){
		return (java.lang.String) this.getAttribute ( MODIFIED_BY).getValue()  ;
	}
 
	public Attribute getModifiedBy(){
		return this.getAttribute ( MODIFIED_BY)  ;
	}

	public static final String MODIFIED_DT ="MODIFIED_DT" ;

	public void setModifiedDtValue(java.sql.Timestamp   pm_modifiedDt){
		this.getAttribute(MODIFIED_DT ).setValue( pm_modifiedDt );
	}
 
	public java.sql.Timestamp getModifiedDtValue(){
		return (java.sql.Timestamp) this.getAttribute ( MODIFIED_DT).getValue()  ;
	}
 
	public Attribute getModifiedDt(){
		return this.getAttribute ( MODIFIED_DT)  ;
	}

	public void setGroupDescAutoLang(java.lang.String   pm_groupDesc){
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		this.getGroupDesc().setValue( pm_groupDesc );
		 else 
		this.getGroupDesc_().setValue( pm_groupDesc );
	}
 
	public Attribute getGroupDescAutoLang(){
		 Attribute result ;
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		 result =   this.getGroupDesc();
		 else 
		 result =   this.getGroupDesc_();
		 return result;
	}
}
