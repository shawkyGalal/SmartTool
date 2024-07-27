package com.smartValue.database.map.auto;

import com.smartValue.database.Attribute;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;

public abstract class _UserDefVarGroups extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "USER_DEF_VAR_GROUPS";
 public static final String DB_TABLE_OWNER = "ICDB";

	public _UserDefVarGroups(){
	}

	 public DbTable getTable() 
	 { 
			return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String UDVG_CODE ="UDVG_CODE" ;

	public void setUdvgCodeValue(java.lang.String   pm_udvgCode){
		this.getAttribute("UDVG_CODE" ).setValue( pm_udvgCode );
	}
 
	public java.lang.String getUdvgCodeValue(){
		return (java.lang.String) this.getAttribute ( "UDVG_CODE").getValue()  ;
	}
 
	public Attribute getUdvgCode(){
		return this.getAttribute ( "UDVG_CODE")  ;
	}

	public static final String TABLE_OWNER ="TABLE_OWNER" ;

	public void setTableOwnerValue(java.lang.String   pm_tableOwner){
		this.getAttribute("TABLE_OWNER" ).setValue( pm_tableOwner );
	}
 
	public java.lang.String getTableOwnerValue(){
		return (java.lang.String) this.getAttribute ( "TABLE_OWNER").getValue()  ;
	}
 
	public Attribute getTableOwner(){
		return this.getAttribute ( "TABLE_OWNER")  ;
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

	public static final String UDVG_DESC_ ="UDVG_DESC_" ;

	public void setUdvgDesc_Value(java.lang.String   pm_udvgDesc_){
		this.getAttribute("UDVG_DESC_" ).setValue( pm_udvgDesc_ );
	}
 
	public java.lang.String getUdvgDesc_Value(){
		return (java.lang.String) this.getAttribute ( "UDVG_DESC_").getValue()  ;
	}
 
	public Attribute getUdvgDesc_(){
		return this.getAttribute ( "UDVG_DESC_")  ;
	}

	public static final String UDVG_DESC ="UDVG_DESC" ;

	public void setUdvgDescValue(java.lang.String   pm_udvgDesc){
		this.getAttribute("UDVG_DESC" ).setValue( pm_udvgDesc );
	}
 
	public java.lang.String getUdvgDescValue(){
		return (java.lang.String) this.getAttribute ( "UDVG_DESC").getValue()  ;
	}
 
	public Attribute getUdvgDesc(){
		return this.getAttribute ( "UDVG_DESC")  ;
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

	public void setUdvgDescAutoLang(java.lang.String   pm_udvgDesc){
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		this.getUdvgDesc().setValue( pm_udvgDesc );
		 else 
		this.getUdvgDesc_().setValue( pm_udvgDesc );
	}
 
	public Attribute getUdvgDescAutoLang(){
		 Attribute result ;
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		 result =   this.getUdvgDesc();
		 else 
		 result =   this.getUdvgDesc_();
		 return result;
	}
}
