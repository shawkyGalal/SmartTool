package com.smartValue.database.map.auto;

import com.smartValue.database.Attribute;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;

public abstract class _SavedSearch extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "SAVED_SEARCH"; 
 public static final String DB_TABLE_OWNER = "ICDB";

	public _SavedSearch(){
	}

	 public DbTable getTable() 
	 { 
			return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String SEARCH_NAME ="SEARCH_NAME" ;

	public void setSearchNameValue(java.lang.String   pm_searchName){
		this.getAttribute(SEARCH_NAME ).setValue( pm_searchName );
	}
 
	public java.lang.String getSearchNameValue(){
		return (java.lang.String) this.getAttribute ( SEARCH_NAME).getValue()  ;
	}
 
	public Attribute getSearchName(){
		return this.getAttribute ( SEARCH_NAME)  ;
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

	public static final String PUBLIC_SEARCH ="PUBLIC_SEARCH" ;

	public void setPublicSearchValue(java.lang.String   pm_publicSearch){
		this.getAttribute(PUBLIC_SEARCH ).setValue( pm_publicSearch );
	}
 
	public java.lang.String getPublicSearchValue(){
		return (java.lang.String) this.getAttribute ( PUBLIC_SEARCH).getValue()  ;
	}
 
	public Attribute getPublicSearch(){
		return this.getAttribute ( PUBLIC_SEARCH)  ;
	}

	public static final String COMPLEX_SEARCH ="COMPLEX_SEARCH" ;

	public void setComplexSearchValue(java.lang.String   pm_complexSearch){
		this.getAttribute(COMPLEX_SEARCH ).setValue( pm_complexSearch );
	}
 
	public java.lang.String getComplexSearchValue(){
		return (java.lang.String) this.getAttribute ( COMPLEX_SEARCH).getValue()  ;
	}
 
	public Attribute getComplexSearch(){
		return this.getAttribute ( COMPLEX_SEARCH)  ;
	}

	public static final String QUERY_BODY ="QUERY_BODY" ;

	public void setQueryBodyValue(oracle.sql.CLOB   pm_queryBody){
		this.getAttribute(QUERY_BODY ).setValue( pm_queryBody );
	}
 
	public oracle.sql.CLOB getQueryBodyValue(){
		return (oracle.sql.CLOB) this.getAttribute ( QUERY_BODY).getValue()  ;
	}
 
	public Attribute getQueryBody(){
		return this.getAttribute ( QUERY_BODY)  ;
	}
}