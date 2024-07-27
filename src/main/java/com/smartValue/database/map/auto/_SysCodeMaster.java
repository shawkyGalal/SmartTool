package com.smartValue.database.map.auto;

import com.smartValue.database.Attribute;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;

public abstract class _SysCodeMaster extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "SYS_CODE_MASTER"; 
 public static final String DB_TABLE_OWNER = "ICDB";

	public _SysCodeMaster(){
	}

	 public DbTable getTable() 
	 { 
			return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String TBLD_ID ="TBLD_ID" ;

	public void setTbldIdValue(java.lang.String   pm_tbldId){
		this.getAttribute(TBLD_ID ).setValue( pm_tbldId );
	}
 
	public java.lang.String getTbldIdValue(){
		return (java.lang.String) this.getAttribute ( TBLD_ID).getValue()  ;
	}
 
	public Attribute getTbldId(){
		return this.getAttribute ( TBLD_ID)  ;
	}

	public static final String TABLE_DESC ="TABLE_DESC" ;

	public void setTableDescValue(java.lang.String   pm_tableDesc){
		this.getAttribute(TABLE_DESC ).setValue( pm_tableDesc );
	}
 
	public java.lang.String getTableDescValue(){
		return (java.lang.String) this.getAttribute ( TABLE_DESC).getValue()  ;
	}
 
	public Attribute getTableDesc(){
		return this.getAttribute ( TABLE_DESC)  ;
	}

	public static final String TABLE_DESC_ ="TABLE_DESC_" ;

	public void setTableDesc_Value(java.lang.String   pm_tableDesc_){
		this.getAttribute(TABLE_DESC_ ).setValue( pm_tableDesc_ );
	}
 
	public java.lang.String getTableDesc_Value(){
		return (java.lang.String) this.getAttribute ( TABLE_DESC_).getValue()  ;
	}
 
	public Attribute getTableDesc_(){
		return this.getAttribute ( TABLE_DESC_)  ;
	}

	public void setTableDescAutoLang(java.lang.String   pm_tableDesc){
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		this.getTableDesc().setValue( pm_tableDesc );
		 else 
		this.getTableDesc_().setValue( pm_tableDesc );
	}
 
	public Attribute getTableDescAutoLang(){
		 Attribute result ;
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		 result =   this.getTableDesc();
		 else 
		 result =   this.getTableDesc_();
		 return result;
	}
}