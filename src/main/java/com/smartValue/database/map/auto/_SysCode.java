package com.smartValue.database.map.auto;

import com.smartValue.database.Attribute;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;

public abstract class _SysCode extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "SYS_CODE";
 public static final String DB_TABLE_OWNER = "ICDB";

	public _SysCode(){
	}

	 public DbTable getTable() 
	 { 
			return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String TBL_ID ="TBL_ID" ;

	public void setTblIdValue(java.lang.String   pm_tblId){
		this.getAttribute(TBL_ID ).setValue( pm_tblId );
	}
 
	public java.lang.String getTblIdValue(){
		return (java.lang.String) this.getAttribute ( TBL_ID).getValue()  ;
	}
 
	public Attribute getTblId(){
		return this.getAttribute ( TBL_ID)  ;
	}

	public static final String CODE_ID ="CODE_ID" ;

	public void setCodeIdValue(java.lang.String   pm_codeId){
		this.getAttribute(CODE_ID ).setValue( pm_codeId );
	}
 
	public java.lang.String getCodeIdValue(){
		return (java.lang.String) this.getAttribute ( CODE_ID).getValue()  ;
	}
 
	public Attribute getCodeId(){
		return this.getAttribute ( CODE_ID)  ;
	}

	public static final String CODE_NAME ="CODE_NAME" ;

	public void setCodeNameValue(java.lang.String   pm_codeName){
		this.getAttribute(CODE_NAME ).setValue( pm_codeName );
	}
 
	public java.lang.String getCodeNameValue(){
		return (java.lang.String) this.getAttribute ( CODE_NAME).getValue()  ;
	}
 
	public Attribute getCodeName(){
		return this.getAttribute ( CODE_NAME)  ;
	}

	public static final String CODE_NAME_ ="CODE_NAME_" ;

	public void setCodeName_Value(java.lang.String   pm_codeName_){
		this.getAttribute(CODE_NAME_ ).setValue( pm_codeName_ );
	}
 
	public java.lang.String getCodeName_Value(){
		return (java.lang.String) this.getAttribute ( CODE_NAME_).getValue()  ;
	}
 
	public Attribute getCodeName_(){
		return this.getAttribute ( CODE_NAME_)  ;
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

	public void setCodeNameAutoLang(java.lang.String   pm_codeName){
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		this.getCodeName().setValue( pm_codeName );
		 else 
		this.getCodeName_().setValue( pm_codeName );
	}
 
	public Attribute getCodeNameAutoLang(){
		 Attribute result ;
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		 result =   this.getCodeName();
		 else 
		 result =   this.getCodeName_();
		 return result;
	}
}