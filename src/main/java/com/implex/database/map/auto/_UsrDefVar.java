package com.implex.database.map.auto;

import com.implex.database.Attribute;
import com.implex.database.DbTable;
import com.implex.database.PersistantObject;

public abstract class _UsrDefVar extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "USR_DEF_VAR"; 
 public static final String DB_TABLE_OWNER = "ICDB";

	public _UsrDefVar(){
	}

	 public DbTable getTable() 
	 { 
			return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String UDV_CODE ="UDV_CODE" ;

	public void setUdvCodeValue(java.math.BigDecimal   pm_udvCode){
		this.getAttribute(UDV_CODE ).setValue( pm_udvCode );
	}
 
	public java.math.BigDecimal getUdvCodeValue(){
		return (java.math.BigDecimal) this.getAttribute ( UDV_CODE).getValue()  ;
	}
 
	public Attribute getUdvCode(){
		return this.getAttribute ( UDV_CODE)  ;
	}

	public static final String UDV_VAR ="UDV_VAR" ;

	public void setUdvVarValue(java.lang.String   pm_udvVar){
		this.getAttribute(UDV_VAR ).setValue( pm_udvVar );
	}
 
	public java.lang.String getUdvVarValue(){
		return (java.lang.String) this.getAttribute ( UDV_VAR).getValue()  ;
	}
 
	public Attribute getUdvVar(){
		return this.getAttribute ( UDV_VAR)  ;
	}

	public static final String UDV_VAR_FIELD ="UDV_VAR_FIELD" ;

	public void setUdvVarFieldValue(oracle.sql.CLOB   pm_udvVarField){
		this.getAttribute(UDV_VAR_FIELD ).setValue( pm_udvVarField );
	}
 
	public oracle.sql.CLOB getUdvVarFieldValue(){
		return (oracle.sql.CLOB) this.getAttribute ( UDV_VAR_FIELD).getValue()  ;
	}
 
	public Attribute getUdvVarField(){
		return this.getAttribute ( UDV_VAR_FIELD)  ;
	}

	public static final String UDV_VAR_DESC ="UDV_VAR_DESC" ;

	public void setUdvVarDescValue(java.lang.String   pm_udvVarDesc){
		this.getAttribute(UDV_VAR_DESC ).setValue( pm_udvVarDesc );
	}
 
	public java.lang.String getUdvVarDescValue(){
		return (java.lang.String) this.getAttribute ( UDV_VAR_DESC).getValue()  ;
	}
 
	public Attribute getUdvVarDesc(){
		return this.getAttribute ( UDV_VAR_DESC)  ;
	}

	public static final String UDV_VAR_DESC_ ="UDV_VAR_DESC_" ;

	public void setUdvVarDesc_Value(java.lang.String   pm_udvVarDesc_){
		this.getAttribute(UDV_VAR_DESC_ ).setValue( pm_udvVarDesc_ );
	}
 
	public java.lang.String getUdvVarDesc_Value(){
		return (java.lang.String) this.getAttribute ( UDV_VAR_DESC_).getValue()  ;
	}
 
	public Attribute getUdvVarDesc_(){
		return this.getAttribute ( UDV_VAR_DESC_)  ;
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

	public static final String VALIDITY ="VALIDITY" ;

	public void setValidityValue(java.lang.String   pm_validity){
		this.getAttribute(VALIDITY ).setValue( pm_validity );
	}
 
	public java.lang.String getValidityValue(){
		return (java.lang.String) this.getAttribute ( VALIDITY).getValue()  ;
	}
 
	public Attribute getValidity(){
		return this.getAttribute ( VALIDITY)  ;
	}

	public static final String SYSTEM_GENERATED ="SYSTEM_GENERATED" ;

	public void setSystemGeneratedValue(java.lang.String   pm_systemGenerated){
		this.getAttribute(SYSTEM_GENERATED ).setValue( pm_systemGenerated );
	}
 
	public java.lang.String getSystemGeneratedValue(){
		return (java.lang.String) this.getAttribute ( SYSTEM_GENERATED).getValue()  ;
	}
 
	public Attribute getSystemGenerated(){
		return this.getAttribute ( SYSTEM_GENERATED)  ;
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

	public static final String UDVG_CODE ="UDVG_CODE" ;

	public void setUdvgCodeValue(java.lang.String   pm_udvgCode){
		this.getAttribute(UDVG_CODE ).setValue( pm_udvgCode );
	}
 
	public java.lang.String getUdvgCodeValue(){
		return (java.lang.String) this.getAttribute ( UDVG_CODE).getValue()  ;
	}
 
	public Attribute getUdvgCode(){
		return this.getAttribute ( UDVG_CODE)  ;
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

	public void setUdvVarDescAutoLang(java.lang.String   pm_udvVarDesc){
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		this.getUdvVarDesc().setValue( pm_udvVarDesc );
		 else 
		this.getUdvVarDesc_().setValue( pm_udvVarDesc );
	}
 
	public Attribute getUdvVarDescAutoLang(){
		 Attribute result ;
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		 result =   this.getUdvVarDesc();
		 else 
		 result =   this.getUdvVarDesc_();
		 return result;
	}
}