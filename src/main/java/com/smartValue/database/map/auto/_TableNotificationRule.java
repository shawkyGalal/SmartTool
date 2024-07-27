package com.smartValue.database.map.auto;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.Attribute;
import com.smartValue.database.DataSet;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;

public abstract class _TableNotificationRule extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "TABLE_NOTIFICATION_RULE"; 
 public static final String DB_TABLE_OWNER = "SUPPORT"; 

	public _TableNotificationRule(){
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

	public static final String OPERATION ="OPERATION" ;

	public void setOperationValue(java.math.BigDecimal   pm_operation){
		this.getAttribute(OPERATION ).setValue( pm_operation );
	}
 
	public java.math.BigDecimal getOperationValue(){
		return (java.math.BigDecimal) this.getAttribute ( OPERATION).getValue()  ;
	}
 
	public Attribute getOperation(){
		return this.getAttribute ( OPERATION)  ;
	}

	public static final String UPDATE_QUERY_ID ="UPDATE_QUERY_ID" ;

	public void setUpdateQueryIdValue(java.math.BigDecimal   pm_updateQueryId){
		this.getAttribute(UPDATE_QUERY_ID ).setValue( pm_updateQueryId );
	}
 
	public java.math.BigDecimal getUpdateQueryIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( UPDATE_QUERY_ID).getValue()  ;
	}
 
	public Attribute getUpdateQueryId(){
		return this.getAttribute ( UPDATE_QUERY_ID)  ;
	}

	public static final String INSERT_QUERY_ID ="INSERT_QUERY_ID" ;

	public void setInsertQueryIdValue(java.math.BigDecimal   pm_insertQueryId){
		this.getAttribute(INSERT_QUERY_ID ).setValue( pm_insertQueryId );
	}
 
	public java.math.BigDecimal getInsertQueryIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( INSERT_QUERY_ID).getValue()  ;
	}
 
	public Attribute getInsertQueryId(){
		return this.getAttribute ( INSERT_QUERY_ID)  ;
	}

	public static final String EMAILTO_QUERY_LIST ="EMAILTO_QUERY_LIST" ;

	public void setEmailtoQueryListValue(java.lang.String   pm_emailtoQueryList){
		this.getAttribute(EMAILTO_QUERY_LIST ).setValue( pm_emailtoQueryList );
	}
 
	public java.lang.String getEmailtoQueryListValue(){
		return (java.lang.String) this.getAttribute ( EMAILTO_QUERY_LIST).getValue()  ;
	}
 
	public Attribute getEmailtoQueryList(){
		return this.getAttribute ( EMAILTO_QUERY_LIST)  ;
	}

	public static final String EMAILCC_QUERY_LIST ="EMAILCC_QUERY_LIST" ;

	public void setEmailccQueryListValue(java.lang.String   pm_emailccQueryList){
		this.getAttribute(EMAILCC_QUERY_LIST ).setValue( pm_emailccQueryList );
	}
 
	public java.lang.String getEmailccQueryListValue(){
		return (java.lang.String) this.getAttribute ( EMAILCC_QUERY_LIST).getValue()  ;
	}
 
	public Attribute getEmailccQueryList(){
		return this.getAttribute ( EMAILCC_QUERY_LIST)  ;
	}

	public static final String EMAIL_SUBJECT ="EMAIL_SUBJECT" ;

	public void setEmailSubjectValue(java.lang.String   pm_emailSubject){
		this.getAttribute(EMAIL_SUBJECT ).setValue( pm_emailSubject );
	}
 
	public java.lang.String getEmailSubjectValue(){
		return (java.lang.String) this.getAttribute ( EMAIL_SUBJECT).getValue()  ;
	}
 
	public Attribute getEmailSubject(){
		return this.getAttribute ( EMAIL_SUBJECT)  ;
	}

	public static final String EMAIL_HEADER ="EMAIL_HEADER" ;

	public void setEmailHeaderValue(java.lang.String   pm_emailHeader){
		this.getAttribute(EMAIL_HEADER ).setValue( pm_emailHeader );
	}
 
	public java.lang.String getEmailHeaderValue(){
		return (java.lang.String) this.getAttribute ( EMAIL_HEADER).getValue()  ;
	}
 
	public Attribute getEmailHeader(){
		return this.getAttribute ( EMAIL_HEADER)  ;
	}

	public static final String IS_MANDATORY ="IS_MANDATORY" ;

	public void setIsMandatoryValue(java.lang.String   pm_isMandatory){
		this.getAttribute(IS_MANDATORY ).setValue( pm_isMandatory );
	}
 
	public java.lang.String getIsMandatoryValue(){
		return (java.lang.String) this.getAttribute ( IS_MANDATORY).getValue()  ;
	}
 
	public Attribute getIsMandatory(){
		return this.getAttribute ( IS_MANDATORY)  ;
	}

	public static final String DESCRIPTION ="DESCRIPTION" ;

	public void setDescriptionValue(java.lang.String   pm_description){
		this.getAttribute(DESCRIPTION ).setValue( pm_description );
	}
 
	public java.lang.String getDescriptionValue(){
		return (java.lang.String) this.getAttribute ( DESCRIPTION).getValue()  ;
	}
 
	public Attribute getDescription(){
		return this.getAttribute ( DESCRIPTION)  ;
	}

	public static final String ACTIVE ="ACTIVE" ;

	public void setActiveValue(java.lang.String   pm_active){
		this.getAttribute(ACTIVE ).setValue( pm_active );
	}
 
	public java.lang.String getActiveValue(){
		return (java.lang.String) this.getAttribute ( ACTIVE).getValue()  ;
	}
 
	public Attribute getActive(){
		return this.getAttribute ( ACTIVE)  ;
	}

	public static final String APPLY_SCOPE ="APPLY_SCOPE" ;

	public void setApplyScopeValue(java.lang.String   pm_applyScope){
		this.getAttribute(APPLY_SCOPE ).setValue( pm_applyScope );
	}
 
	public java.lang.String getApplyScopeValue(){
		return (java.lang.String) this.getAttribute ( APPLY_SCOPE).getValue()  ;
	}
 
	public Attribute getApplyScope(){
		return this.getAttribute ( APPLY_SCOPE)  ;
	}

	// ********Start of Child DataSets getter methods******** 
	// *******End of Child DataSets getter methods******** 
}