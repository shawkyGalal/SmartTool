package com.implex.database.map.auto;

import com.implex.database.Attribute;
import com.implex.database.DbTable;
import com.implex.database.PersistantObject;

public abstract class _SysPoAttachments extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "SYS_PO_ATTACHMENTS"; 
 public static final String DB_TABLE_OWNER = "ICDB";

	public _SysPoAttachments(){
	}

	 public DbTable getTable() 
	 { 
			return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
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

	public static final String ATTACHED_FILE ="ATTACHED_FILE" ;

	public void setAttachedFileValue(oracle.sql.BLOB   pm_attachedFile){
		this.getAttribute(ATTACHED_FILE ).setValue( pm_attachedFile );
	}
 
	public oracle.sql.BLOB getAttachedFileValue(){
		return (oracle.sql.BLOB) this.getAttribute ( ATTACHED_FILE).getValue()  ;
	}
 
	public Attribute getAttachedFile(){
		return this.getAttribute ( ATTACHED_FILE)  ;
	}

	public static final String ATTACMENT_GROUP_ID ="ATTACMENT_GROUP_ID" ;

	public void setAttacmentGroupIdValue(java.lang.String   pm_attacmentGroupId){
		this.getAttribute(ATTACMENT_GROUP_ID ).setValue( pm_attacmentGroupId );
	}
 
	public java.lang.String getAttacmentGroupIdValue(){
		return (java.lang.String) this.getAttribute ( ATTACMENT_GROUP_ID).getValue()  ;
	}
 
	public Attribute getAttacmentGroupId(){
		return this.getAttribute ( ATTACMENT_GROUP_ID)  ;
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
}