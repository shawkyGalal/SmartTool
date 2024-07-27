package com.smartValue.database.map.auto;

import com.smartValue.database.Attribute;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;

public abstract class _ActionNeededList extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "ACTION_NEEDED_LIST"; 
 public static final String DB_TABLE_OWNER = "ICDB";

	public _ActionNeededList(){
	}

	 public DbTable getTable() 
	 { 
		return new DbTable(DB_TABLE_OWNER, DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String QUERY_FOR_LIST ="QUERY_FOR_LIST" ;

	public void setQueryForListValue(oracle.sql.CLOB   pm_queryForList){
		this.getAttribute(QUERY_FOR_LIST ).setValue( pm_queryForList );
	}
 
	public oracle.sql.CLOB getQueryForListValue(){
		return (oracle.sql.CLOB) this.getAttribute ( QUERY_FOR_LIST).getValue()  ;
	}
 
	public Attribute getQueryForList(){
		return this.getAttribute ( QUERY_FOR_LIST)  ;
	}

	public static final String LIST_DESC_ ="LIST_DESC_" ;

	public void setListDesc_Value(java.lang.String   pm_listDesc_){
		this.getAttribute(LIST_DESC_ ).setValue( pm_listDesc_ );
	}
 
	public java.lang.String getListDesc_Value(){
		return (java.lang.String) this.getAttribute ( LIST_DESC_).getValue()  ;
	}
 
	public Attribute getListDesc_(){
		return this.getAttribute ( LIST_DESC_)  ;
	}

	public static final String LIST_DESC ="LIST_DESC" ;

	public void setListDescValue(java.lang.String   pm_listDesc){
		this.getAttribute(LIST_DESC ).setValue( pm_listDesc );
	}
 
	public java.lang.String getListDescValue(){
		return (java.lang.String) this.getAttribute ( LIST_DESC).getValue()  ;
	}
 
	public Attribute getListDesc(){
		return this.getAttribute ( LIST_DESC)  ;
	}

	public static final String URL_FOR_OBJECT_EDITOR ="URL_FOR_OBJECT_EDITOR" ;

	public void setUrlForObjectEditorValue(java.lang.String   pm_urlForObjectEditor){
		this.getAttribute(URL_FOR_OBJECT_EDITOR ).setValue( pm_urlForObjectEditor );
	}
 
	public java.lang.String getUrlForObjectEditorValue(){
		return (java.lang.String) this.getAttribute ( URL_FOR_OBJECT_EDITOR).getValue()  ;
	}
 
	public Attribute getUrlForObjectEditor(){
		return this.getAttribute ( URL_FOR_OBJECT_EDITOR)  ;
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

	public static final String VAR_OBJECT_OF_MAINTFORM_PAGE ="VAR_OBJECT_OF_MAINTFORM_PAGE" ;

	public void setVarObjectOfMaintformPageValue(java.lang.String   pm_varObjectOfMaintformPage){
		this.getAttribute(VAR_OBJECT_OF_MAINTFORM_PAGE ).setValue( pm_varObjectOfMaintformPage );
	}
 
	public java.lang.String getVarObjectOfMaintformPageValue(){
		return (java.lang.String) this.getAttribute ( VAR_OBJECT_OF_MAINTFORM_PAGE).getValue()  ;
	}
 
	public Attribute getVarObjectOfMaintformPage(){
		return this.getAttribute ( VAR_OBJECT_OF_MAINTFORM_PAGE)  ;
	}

	public static final String CLASS_NAME ="CLASS_NAME" ;

	public void setClassNameValue(java.lang.String   pm_className){
		this.getAttribute(CLASS_NAME ).setValue( pm_className );
	}
 
	public java.lang.String getClassNameValue(){
		return (java.lang.String) this.getAttribute ( CLASS_NAME).getValue()  ;
	}
 
	public Attribute getClassName(){
		return this.getAttribute ( CLASS_NAME)  ;
	}

	public void setListDescAutoLang(java.lang.String   pm_listDesc){
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		this.getListDesc().setValue( pm_listDesc );
		 else 
		this.getListDesc_().setValue( pm_listDesc );
	}
 
	public Attribute getListDescAutoLang(){
		 Attribute result ;
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		 result =   this.getListDesc();
		 else 
		 result =   this.getListDesc_();
		 return result;
	}
}