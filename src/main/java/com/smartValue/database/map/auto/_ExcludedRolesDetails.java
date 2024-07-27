package com.smartValue.database.map.auto;

import com.smartValue.database.Attribute;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;

public abstract class _ExcludedRolesDetails extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "EXCLUDED_ROLES_DETAILS"; 
 public static final String DB_TABLE_OWNER = "ICDB";

	public _ExcludedRolesDetails(){
	}

	 public DbTable getTable() 
	 { 
			return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
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

	public static final String OBJECT_ID ="OBJECT_ID" ;

	public void setObjectIdValue(java.math.BigDecimal   pm_objectId){
		this.getAttribute(OBJECT_ID ).setValue( pm_objectId );
	}
 
	public java.math.BigDecimal getObjectIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( OBJECT_ID).getValue()  ;
	}
 
	public Attribute getObjectId(){
		return this.getAttribute ( OBJECT_ID)  ;
	}

	public static final String SECGROUP_ID ="SECGROUP_ID" ;

	public void setSecgroupIdValue(java.lang.String   pm_secgroupId){
		this.getAttribute(SECGROUP_ID ).setValue( pm_secgroupId );
	}
 
	public java.lang.String getSecgroupIdValue(){
		return (java.lang.String) this.getAttribute ( SECGROUP_ID).getValue()  ;
	}
 
	public Attribute getSecgroupId(){
		return this.getAttribute ( SECGROUP_ID)  ;
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

	public static final String COLUMN_NAME ="COLUMN_NAME" ;

	public void setColumnNameValue(java.lang.String   pm_columnName){
		this.getAttribute(COLUMN_NAME ).setValue( pm_columnName );
	}
 
	public java.lang.String getColumnNameValue(){
		return (java.lang.String) this.getAttribute ( COLUMN_NAME).getValue()  ;
	}
 
	public Attribute getColumnName(){
		return this.getAttribute ( COLUMN_NAME)  ;
	}

	public static final String RENDERED_OR_DISABLED ="RENDERED_OR_DISABLED" ;

	public void setRenderedOrDisabledValue(java.lang.String   pm_renderedOrDisabled){
		this.getAttribute(RENDERED_OR_DISABLED ).setValue( pm_renderedOrDisabled );
	}
 
	public java.lang.String getRenderedOrDisabledValue(){
		return (java.lang.String) this.getAttribute ( RENDERED_OR_DISABLED).getValue()  ;
	}
 
	public Attribute getRenderedOrDisabled(){
		return this.getAttribute ( RENDERED_OR_DISABLED)  ;
	}
}