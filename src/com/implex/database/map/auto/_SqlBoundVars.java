package com.implex.database.map.auto;

import com.implex.database.Attribute;
import com.implex.database.AttributeChangeValidator;
import com.implex.database.DbTable;
import com.implex.database.PersistantObject;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.validators.SqlBoundVarsChangeValidator;

public abstract class _SqlBoundVars extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "SQL_BOUND_VARS";
 public static final String DB_TABLE_OWNER = "ICDB";
	public _SqlBoundVars(){
	}

	 public DbTable getTable() 
	 { 
			return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public AttributeChangeValidator changeValidator ; 
	@Override 
	public AttributeChangeValidator getAttributeChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key)
	{ 
		if (changeValidator == null) 
		changeValidator = new SqlBoundVarsChangeValidator(pm_secUserData , pm_po  , pm_key); 
		return changeValidator; 
	}

	public static final String OWNER ="OWNER" ;

	public void setOwnerValue(java.lang.String   pm_owner){
		this.getAttribute("OWNER" ).setValue( pm_owner );
	}
 
	public java.lang.String getOwnerValue(){
		return (java.lang.String) this.getAttribute ( "OWNER").getValue()  ;
	}
 
	public Attribute getOwner(){
		return this.getAttribute ( "OWNER")  ;
	}

	public static final String TITLE ="TITLE" ;

	public void setTitleValue(java.lang.String   pm_title){
		this.getAttribute("TITLE" ).setValue( pm_title );
	}
 
	public java.lang.String getTitleValue(){
		return (java.lang.String) this.getAttribute ( "TITLE").getValue()  ;
	}
 
	public Attribute getTitle(){
		return this.getAttribute ( "TITLE")  ;
	}

	public static final String BOUND_VAR_NAME ="BOUND_VAR_NAME" ;

	public void setBoundVarNameValue(java.lang.String   pm_boundVarName){
		this.getAttribute("BOUND_VAR_NAME" ).setValue( pm_boundVarName );
	}
 
	public java.lang.String getBoundVarNameValue(){
		return (java.lang.String) this.getAttribute ( "BOUND_VAR_NAME").getValue()  ;
	}
 
	public Attribute getBoundVarName(){
		return this.getAttribute ( "BOUND_VAR_NAME")  ;
	}

	public static final String ACTIVE ="ACTIVE" ;

	public void setActiveValue(java.lang.String   pm_active){
		this.getAttribute("ACTIVE" ).setValue( pm_active );
	}
 
	public java.lang.String getActiveValue(){
		return (java.lang.String) this.getAttribute ( "ACTIVE").getValue()  ;
	}
 
	public Attribute getActive(){
		return this.getAttribute ( "ACTIVE")  ;
	}

	public static final String DEFAULT_VAL ="DEFAULT_VAL" ;

	public void setDefaultValValue(java.lang.String   pm_defaultVal){
		this.getAttribute("DEFAULT_VAL" ).setValue( pm_defaultVal );
	}
 
	public java.lang.String getDefaultValValue(){
		return (java.lang.String) this.getAttribute ( "DEFAULT_VAL").getValue()  ;
	}
 
	public Attribute getDefaultVal(){
		return this.getAttribute ( "DEFAULT_VAL")  ;
	}

	public static final String CREATION_DATE ="CREATION_DATE" ;

	public void setCreationDateValue(java.sql.Timestamp   pm_creationDate){
		this.getAttribute("CREATION_DATE" ).setValue( pm_creationDate );
	}
 
	public java.sql.Timestamp getCreationDateValue(){
		return (java.sql.Timestamp) this.getAttribute ( "CREATION_DATE").getValue()  ;
	}
 
	public Attribute getCreationDate(){
		return this.getAttribute ( "CREATION_DATE")  ;
	}

	public static final String SN ="SN" ;

	public void setSnValue(java.math.BigDecimal   pm_sn){
		this.getAttribute("SN" ).setValue( pm_sn );
	}
 
	public java.math.BigDecimal getSnValue(){
		return (java.math.BigDecimal) this.getAttribute ( "SN").getValue()  ;
	}
 
	public Attribute getSn(){
		return this.getAttribute ( "SN")  ;
	}

	public static final String DATA_TYPE ="DATA_TYPE" ;

	public void setDataTypeValue(java.lang.String   pm_dataType){
		this.getAttribute("DATA_TYPE" ).setValue( pm_dataType );
	}
 
	public java.lang.String getDataTypeValue(){
		return (java.lang.String) this.getAttribute ( "DATA_TYPE").getValue()  ;
	}
 
	public Attribute getDataType(){
		return this.getAttribute ( "DATA_TYPE")  ;
	}

	public static final String QUERY_FOR_LIST ="QUERY_FOR_LIST" ;

	public void setQueryForListValue(java.lang.String   pm_queryForList){
		this.getAttribute("QUERY_FOR_LIST" ).setValue( pm_queryForList );
	}
 
	public java.lang.String getQueryForListValue(){
		return (java.lang.String) this.getAttribute ( "QUERY_FOR_LIST").getValue()  ;
	}
 
	public Attribute getQueryForList(){
		return this.getAttribute ( "QUERY_FOR_LIST")  ;
	}
}