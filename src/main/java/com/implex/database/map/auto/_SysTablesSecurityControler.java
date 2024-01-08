package com.implex.database.map.auto;

import com.implex.database.Attribute;
import com.implex.database.DbTable;
import com.implex.database.PersistantObject;

public abstract class _SysTablesSecurityControler extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "SYS_TABLES_SECURITY_CONTROLER"; 
 public static final String DB_TABLE_OWNER = "ICDB";

	public _SysTablesSecurityControler(){
	}

	 public DbTable getTable() 
	 { 
			return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String TABLE_NAME ="TABLE_NAME" ;

	public void setTableNameValue(java.lang.String   pm_tableName){
		this.getAttribute("TABLE_NAME" ).setValue( pm_tableName );
	}
 
	public java.lang.String getTableNameValue(){
		return (java.lang.String) this.getAttribute ( "TABLE_NAME").getValue()  ;
	}
 
	public Attribute getTableName(){
		return this.getAttribute ( "TABLE_NAME")  ;
	}

	public static final String OPEARTION ="OPEARTION" ;

	public void setOpeartionValue(java.lang.String   pm_opeartion){
		this.getAttribute("OPEARTION" ).setValue( pm_opeartion );
	}
 
	public java.lang.String getOpeartionValue(){
		return (java.lang.String) this.getAttribute ( "OPEARTION").getValue()  ;
	}
 
	public Attribute getOpeartion(){
		return this.getAttribute ( "OPEARTION")  ;
	}

	public static final String EXECLUDED_USERS ="EXECLUDED_USERS" ;

	public void setExecludedUsersValue(java.lang.String   pm_execludedUsers){
		this.getAttribute("EXECLUDED_USERS" ).setValue( pm_execludedUsers );
	}
 
	public java.lang.String getExecludedUsersValue(){
		return (java.lang.String) this.getAttribute ( "EXECLUDED_USERS").getValue()  ;
	}
 
	public Attribute getExecludedUsers(){
		return this.getAttribute ( "EXECLUDED_USERS")  ;
	}
}