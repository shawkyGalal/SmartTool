package com.smartValue.database.map.auto;

import com.smartValue.database.Attribute;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;

public abstract class _SysPoAttatchGroupMetadata extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "SYS_PO_ATTATCH_GROUP_METADATA"; 
 public static final String DB_TABLE_OWNER = "ICDB";

	public _SysPoAttatchGroupMetadata(){
	}

	 public DbTable getTable() 
	 { 
			return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String VAR_NAME ="VAR_NAME" ;

	public void setVarNameValue(java.lang.String   pm_varName){
		this.getAttribute(VAR_NAME ).setValue( pm_varName );
	}
 
	public java.lang.String getVarNameValue(){
		return (java.lang.String) this.getAttribute ( VAR_NAME).getValue()  ;
	}
 
	public Attribute getVarName(){
		return this.getAttribute ( VAR_NAME)  ;
	}

	public static final String VAR_DESC ="VAR_DESC" ;

	public void setVarDescValue(java.lang.String   pm_varDesc){
		this.getAttribute(VAR_DESC ).setValue( pm_varDesc );
	}
 
	public java.lang.String getVarDescValue(){
		return (java.lang.String) this.getAttribute ( VAR_DESC).getValue()  ;
	}
 
	public Attribute getVarDesc(){
		return this.getAttribute ( VAR_DESC)  ;
	}

	public static final String VAR_DESC_ ="VAR_DESC_" ;

	public void setVarDesc_Value(java.lang.String   pm_varDesc_){
		this.getAttribute(VAR_DESC_ ).setValue( pm_varDesc_ );
	}
 
	public java.lang.String getVarDesc_Value(){
		return (java.lang.String) this.getAttribute ( VAR_DESC_).getValue()  ;
	}
 
	public Attribute getVarDesc_(){
		return this.getAttribute ( VAR_DESC_)  ;
	}

	public static final String MANDATORY ="MANDATORY" ;

	public void setMandatoryValue(java.lang.String   pm_mandatory){
		this.getAttribute(MANDATORY ).setValue( pm_mandatory );
	}
 
	public java.lang.String getMandatoryValue(){
		return (java.lang.String) this.getAttribute ( MANDATORY).getValue()  ;
	}
 
	public Attribute getMandatory(){
		return this.getAttribute ( MANDATORY)  ;
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

	public void setVarDescAutoLang(java.lang.String   pm_varDesc){
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		this.getVarDesc().setValue( pm_varDesc );
		 else 
		this.getVarDesc_().setValue( pm_varDesc );
	}
 
	public Attribute getVarDescAutoLang(){
		 Attribute result ;
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		 result =   this.getVarDesc();
		 else 
		 result =   this.getVarDesc_();
		 return result;
	}
}