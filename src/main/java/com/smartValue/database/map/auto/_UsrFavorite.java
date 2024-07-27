package com.smartValue.database.map.auto;

import com.smartValue.database.Attribute;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;

public abstract class _UsrFavorite extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */

	 public static final String DB_TABLE_NAME = "USR_FAVORITE"; 
	 public static final String DB_TABLE_OWNER = "ICDB";
	public _UsrFavorite(){
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

	public static final String MNU_CODE ="MNU_CODE" ;

	public void setMnuCodeValue(java.lang.String   pm_mnuCode){
		this.getAttribute("MNU_CODE" ).setValue( pm_mnuCode );
	}
 
	public java.lang.String getMnuCodeValue(){
		return (java.lang.String) this.getAttribute ( "MNU_CODE").getValue()  ;
	}
 
	public Attribute getMnuCode(){
		return this.getAttribute ( "MNU_CODE")  ;
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
