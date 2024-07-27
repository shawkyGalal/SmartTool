package com.smartValue.database.map.auto;

import com.smartValue.database.Attribute;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;

public abstract class _AuditControler extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "AUDIT_CONTROLER"; 
 public static final String DB_TABLE_OWNER = "ICDB";

	public _AuditControler(){
	}

	 public DbTable getTable() 
	 { 
		return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String USERNAME ="USERNAME" ;

	public void setUsernameValue(java.lang.String   pm_username){
		this.getAttribute("USERNAME" ).setValue( pm_username );
	}
 
	public java.lang.String getUsernameValue(){
		return (java.lang.String) this.getAttribute ( "USERNAME").getValue()  ;
	}
 
	public Attribute getUsername(){
		return this.getAttribute ( "USERNAME")  ;
	}

	public static final String TABLENAME ="TABLENAME" ;

	public void setTablenameValue(java.lang.String   pm_tablename){
		this.getAttribute("TABLENAME" ).setValue( pm_tablename );
	}
 
	public java.lang.String getTablenameValue(){
		return (java.lang.String) this.getAttribute ( "TABLENAME").getValue()  ;
	}
 
	public Attribute getTablename(){
		return this.getAttribute ( "TABLENAME")  ;
	}

	public static final String OPERATION ="OPERATION" ;

	public void setOperationValue(java.lang.String   pm_operation){
		this.getAttribute("OPERATION" ).setValue( pm_operation );
	}
 
	public java.lang.String getOperationValue(){
		return (java.lang.String) this.getAttribute ( "OPERATION").getValue()  ;
	}
 
	public Attribute getOperation(){
		return this.getAttribute ( "OPERATION")  ;
	}

	public static final String ENABLED ="ENABLED" ;

	public void setEnabledValue(java.lang.String   pm_enabled){
		this.getAttribute("ENABLED" ).setValue( pm_enabled );
	}
 
	public java.lang.String getEnabledValue(){
		return (java.lang.String) this.getAttribute ( "ENABLED").getValue()  ;
	}
 
	public Attribute getEnabled(){
		return this.getAttribute ( "ENABLED")  ;
	}

	public static final String MODIFIED_DT ="MODIFIED_DT" ;

	public void setModifiedDtValue(java.sql.Timestamp   pm_modifiedDt){
		this.getAttribute("MODIFIED_DT" ).setValue( pm_modifiedDt );
	}
 
	public java.sql.Timestamp getModifiedDtValue(){
		return (java.sql.Timestamp) this.getAttribute ( "MODIFIED_DT").getValue()  ;
	}
 
	public Attribute getModifiedDt(){
		return this.getAttribute ( "MODIFIED_DT")  ;
	}
}