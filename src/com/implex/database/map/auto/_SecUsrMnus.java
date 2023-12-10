package com.implex.database.map.auto;

import com.implex.database.Attribute;
import com.implex.database.DbTable;
import com.implex.database.PersistantObject;

public abstract class _SecUsrMnus extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "SEC_USR_MNUS"; 
 public static final String DB_TABLE_OWNER = "ICDB";

	public _SecUsrMnus(){
	}

	 public DbTable getTable() 
	 { 
			return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String USR_NAME ="USR_NAME" ;

	public void setUsrNameValue(java.lang.String   pm_usrName){
		this.getAttribute("USR_NAME" ).setValue( pm_usrName );
	}
 
	public java.lang.String getUsrNameValue(){
		return (java.lang.String) this.getAttribute ( "USR_NAME").getValue()  ;
	}
 
	public Attribute getUsrName(){
		return this.getAttribute ( "USR_NAME")  ;
	}

	public static final String SYS_MNU_CODE ="SYS_MNU_CODE" ;

	public void setSysMnuCodeValue(java.lang.String   pm_sysMnuCode){
		this.getAttribute("SYS_MNU_CODE" ).setValue( pm_sysMnuCode );
	}
 
	public java.lang.String getSysMnuCodeValue(){
		return (java.lang.String) this.getAttribute ( "SYS_MNU_CODE").getValue()  ;
	}
 
	public Attribute getSysMnuCode(){
		return this.getAttribute ( "SYS_MNU_CODE")  ;
	}

	public static final String PREPARED_BY ="PREPARED_BY" ;

	public void setPreparedByValue(java.lang.String   pm_preparedBy){
		this.getAttribute("PREPARED_BY" ).setValue( pm_preparedBy );
	}
 
	public java.lang.String getPreparedByValue(){
		return (java.lang.String) this.getAttribute ( "PREPARED_BY").getValue()  ;
	}
 
	public Attribute getPreparedBy(){
		return this.getAttribute ( "PREPARED_BY")  ;
	}

	public static final String PREPARED_DT ="PREPARED_DT" ;

	public void setPreparedDtValue(java.sql.Timestamp   pm_preparedDt){
		this.getAttribute("PREPARED_DT" ).setValue( pm_preparedDt );
	}
 
	public java.sql.Timestamp getPreparedDtValue(){
		return (java.sql.Timestamp) this.getAttribute ( "PREPARED_DT").getValue()  ;
	}
 
	public Attribute getPreparedDt(){
		return this.getAttribute ( "PREPARED_DT")  ;
	}

	public static final String MODIFIED_BY ="MODIFIED_BY" ;

	public void setModifiedByValue(java.lang.String   pm_modifiedBy){
		this.getAttribute("MODIFIED_BY" ).setValue( pm_modifiedBy );
	}
 
	public java.lang.String getModifiedByValue(){
		return (java.lang.String) this.getAttribute ( "MODIFIED_BY").getValue()  ;
	}
 
	public Attribute getModifiedBy(){
		return this.getAttribute ( "MODIFIED_BY")  ;
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