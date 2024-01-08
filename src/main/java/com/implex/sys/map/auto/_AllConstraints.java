package com.implex.sys.map.auto;

import com.implex.database.PersistantObject; 
import com.implex.database.DataSet;
import com.implex.database.Attribute;
import com.implex.database.DbTable;
import com.implex.database.ApplicationContext;

public abstract class _AllConstraints extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "ALL_CONSTRAINTS"; 
 public static final String DB_TABLE_OWNER = "SYS";

	public _AllConstraints(){
	}

	 public DbTable getTable() 
	 { 
		return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String OWNER ="OWNER" ;

	public void setOwnerValue(java.lang.String   pm_owner){
		this.getAttribute(OWNER ).setValue( pm_owner );
	}
 
	public java.lang.String getOwnerValue(){
		return (java.lang.String) this.getAttribute ( OWNER).getValue()  ;
	}
 
	public Attribute getOwner(){
		return this.getAttribute ( OWNER)  ;
	}

	public static final String CONSTRAINT_NAME ="CONSTRAINT_NAME" ;

	public void setConstraintNameValue(java.lang.String   pm_constraintName){
		this.getAttribute(CONSTRAINT_NAME ).setValue( pm_constraintName );
	}
 
	public java.lang.String getConstraintNameValue(){
		return (java.lang.String) this.getAttribute ( CONSTRAINT_NAME).getValue()  ;
	}
 
	public Attribute getConstraintName(){
		return this.getAttribute ( CONSTRAINT_NAME)  ;
	}

	public static final String CONSTRAINT_TYPE ="CONSTRAINT_TYPE" ;

	public void setConstraintTypeValue(java.lang.String   pm_constraintType){
		this.getAttribute(CONSTRAINT_TYPE ).setValue( pm_constraintType );
	}
 
	public java.lang.String getConstraintTypeValue(){
		return (java.lang.String) this.getAttribute ( CONSTRAINT_TYPE).getValue()  ;
	}
 
	public Attribute getConstraintType(){
		return this.getAttribute ( CONSTRAINT_TYPE)  ;
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

	public static final String SEARCH_CONDITION ="SEARCH_CONDITION" ;

	public void setSearchConditionValue(java.lang.String   pm_searchCondition){
		this.getAttribute(SEARCH_CONDITION ).setValue( pm_searchCondition );
	}
 
	public java.lang.String getSearchConditionValue(){
		return (java.lang.String) this.getAttribute ( SEARCH_CONDITION).getValue()  ;
	}
 
	public Attribute getSearchCondition(){
		return this.getAttribute ( SEARCH_CONDITION)  ;
	}

	public static final String R_OWNER ="R_OWNER" ;

	public void setROwnerValue(java.lang.String   pm_rOwner){
		this.getAttribute(R_OWNER ).setValue( pm_rOwner );
	}
 
	public java.lang.String getROwnerValue(){
		return (java.lang.String) this.getAttribute ( R_OWNER).getValue()  ;
	}
 
	public Attribute getROwner(){
		return this.getAttribute ( R_OWNER)  ;
	}

	public static final String R_CONSTRAINT_NAME ="R_CONSTRAINT_NAME" ;

	public void setRConstraintNameValue(java.lang.String   pm_rConstraintName){
		this.getAttribute(R_CONSTRAINT_NAME ).setValue( pm_rConstraintName );
	}
 
	public java.lang.String getRConstraintNameValue(){
		return (java.lang.String) this.getAttribute ( R_CONSTRAINT_NAME).getValue()  ;
	}
 
	public Attribute getRConstraintName(){
		return this.getAttribute ( R_CONSTRAINT_NAME)  ;
	}

	public static final String DELETE_RULE ="DELETE_RULE" ;

	public void setDeleteRuleValue(java.lang.String   pm_deleteRule){
		this.getAttribute(DELETE_RULE ).setValue( pm_deleteRule );
	}
 
	public java.lang.String getDeleteRuleValue(){
		return (java.lang.String) this.getAttribute ( DELETE_RULE).getValue()  ;
	}
 
	public Attribute getDeleteRule(){
		return this.getAttribute ( DELETE_RULE)  ;
	}

	public static final String STATUS ="STATUS" ;

	public void setStatusValue(java.lang.String   pm_status){
		this.getAttribute(STATUS ).setValue( pm_status );
	}
 
	public java.lang.String getStatusValue(){
		return (java.lang.String) this.getAttribute ( STATUS).getValue()  ;
	}
 
	public Attribute getStatus(){
		return this.getAttribute ( STATUS)  ;
	}

	public static final String DEFERRABLE ="DEFERRABLE" ;

	public void setDeferrableValue(java.lang.String   pm_deferrable){
		this.getAttribute(DEFERRABLE ).setValue( pm_deferrable );
	}
 
	public java.lang.String getDeferrableValue(){
		return (java.lang.String) this.getAttribute ( DEFERRABLE).getValue()  ;
	}
 
	public Attribute getDeferrable(){
		return this.getAttribute ( DEFERRABLE)  ;
	}

	public static final String DEFERRED ="DEFERRED" ;

	public void setDeferredValue(java.lang.String   pm_deferred){
		this.getAttribute(DEFERRED ).setValue( pm_deferred );
	}
 
	public java.lang.String getDeferredValue(){
		return (java.lang.String) this.getAttribute ( DEFERRED).getValue()  ;
	}
 
	public Attribute getDeferred(){
		return this.getAttribute ( DEFERRED)  ;
	}

	public static final String VALIDATED ="VALIDATED" ;

	public void setValidatedValue(java.lang.String   pm_validated){
		this.getAttribute(VALIDATED ).setValue( pm_validated );
	}
 
	public java.lang.String getValidatedValue(){
		return (java.lang.String) this.getAttribute ( VALIDATED).getValue()  ;
	}
 
	public Attribute getValidated(){
		return this.getAttribute ( VALIDATED)  ;
	}

	public static final String GENERATED ="GENERATED" ;

	public void setGeneratedValue(java.lang.String   pm_generated){
		this.getAttribute(GENERATED ).setValue( pm_generated );
	}
 
	public java.lang.String getGeneratedValue(){
		return (java.lang.String) this.getAttribute ( GENERATED).getValue()  ;
	}
 
	public Attribute getGenerated(){
		return this.getAttribute ( GENERATED)  ;
	}

	public static final String BAD ="BAD" ;

	public void setBadValue(java.lang.String   pm_bad){
		this.getAttribute(BAD ).setValue( pm_bad );
	}
 
	public java.lang.String getBadValue(){
		return (java.lang.String) this.getAttribute ( BAD).getValue()  ;
	}
 
	public Attribute getBad(){
		return this.getAttribute ( BAD)  ;
	}

	public static final String RELY ="RELY" ;

	public void setRelyValue(java.lang.String   pm_rely){
		this.getAttribute(RELY ).setValue( pm_rely );
	}
 
	public java.lang.String getRelyValue(){
		return (java.lang.String) this.getAttribute ( RELY).getValue()  ;
	}
 
	public Attribute getRely(){
		return this.getAttribute ( RELY)  ;
	}

	public static final String LAST_CHANGE ="LAST_CHANGE" ;

	public void setLastChangeValue(java.sql.Timestamp   pm_lastChange){
		this.getAttribute(LAST_CHANGE ).setValue( pm_lastChange );
	}
 
	public java.sql.Timestamp getLastChangeValue(){
		return (java.sql.Timestamp) this.getAttribute ( LAST_CHANGE).getValue()  ;
	}
 
	public Attribute getLastChange(){
		return this.getAttribute ( LAST_CHANGE)  ;
	}

	public static final String INDEX_OWNER ="INDEX_OWNER" ;

	public void setIndexOwnerValue(java.lang.String   pm_indexOwner){
		this.getAttribute(INDEX_OWNER ).setValue( pm_indexOwner );
	}
 
	public java.lang.String getIndexOwnerValue(){
		return (java.lang.String) this.getAttribute ( INDEX_OWNER).getValue()  ;
	}
 
	public Attribute getIndexOwner(){
		return this.getAttribute ( INDEX_OWNER)  ;
	}

	public static final String INDEX_NAME ="INDEX_NAME" ;

	public void setIndexNameValue(java.lang.String   pm_indexName){
		this.getAttribute(INDEX_NAME ).setValue( pm_indexName );
	}
 
	public java.lang.String getIndexNameValue(){
		return (java.lang.String) this.getAttribute ( INDEX_NAME).getValue()  ;
	}
 
	public Attribute getIndexName(){
		return this.getAttribute ( INDEX_NAME)  ;
	}

	public static final String INVALID ="INVALID" ;

	public void setInvalidValue(java.lang.String   pm_invalid){
		this.getAttribute(INVALID ).setValue( pm_invalid );
	}
 
	public java.lang.String getInvalidValue(){
		return (java.lang.String) this.getAttribute ( INVALID).getValue()  ;
	}
 
	public Attribute getInvalid(){
		return this.getAttribute ( INVALID)  ;
	}

	public static final String VIEW_RELATED ="VIEW_RELATED" ;

	public void setViewRelatedValue(java.lang.String   pm_viewRelated){
		this.getAttribute(VIEW_RELATED ).setValue( pm_viewRelated );
	}
 
	public java.lang.String getViewRelatedValue(){
		return (java.lang.String) this.getAttribute ( VIEW_RELATED).getValue()  ;
	}
 
	public Attribute getViewRelated(){
		return this.getAttribute ( VIEW_RELATED)  ;
	}

	// ********Start of Child DataSets getter methods******** 
	// *******End of Child DataSets getter methods******** 
}