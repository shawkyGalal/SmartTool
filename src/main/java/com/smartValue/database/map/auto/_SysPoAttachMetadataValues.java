package com.smartValue.database.map.auto;

import com.smartValue.database.Attribute;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;

public abstract class _SysPoAttachMetadataValues extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "SYS_PO_ATTACH_METADATA_VALUES";
 public static final String DB_TABLE_OWNER = "ICDB";

	public _SysPoAttachMetadataValues(){
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

	public static final String VAR_VALUE ="VAR_VALUE" ;

	public void setVarValueValue(java.lang.String   pm_varValue){
		this.getAttribute(VAR_VALUE ).setValue( pm_varValue );
	}
 
	public java.lang.String getVarValueValue(){
		return (java.lang.String) this.getAttribute ( VAR_VALUE).getValue()  ;
	}
 
	public Attribute getVarValue(){
		return this.getAttribute ( VAR_VALUE)  ;
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

	public static final String FILE_NAME ="FILE_NAME" ;

	public void setFileNameValue(java.lang.String   pm_fileName){
		this.getAttribute(FILE_NAME ).setValue( pm_fileName );
	}
 
	public java.lang.String getFileNameValue(){
		return (java.lang.String) this.getAttribute ( FILE_NAME).getValue()  ;
	}
 
	public Attribute getFileName(){
		return this.getAttribute ( FILE_NAME)  ;
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
}
