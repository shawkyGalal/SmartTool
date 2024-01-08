package com.implex.database.map.auto;


import com.implex.database.Attribute;
import com.implex.database.DbTable;
import com.implex.database.PersistantObject;

public abstract class _TableUserView extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "TABLE_USER_VIEW"; 
 public static final String DB_TABLE_OWNER = "ICDB";

	public _TableUserView(){
	}

	 public DbTable getTable() 
	 { 
			return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String TABEL_NAME ="TABEL_NAME" ;

	public void setTabelNameValue(java.lang.String   pm_tabelName){
		this.getAttribute(TABEL_NAME ).setValue( pm_tabelName );
	}
 
	public java.lang.String getTabelNameValue(){
		return (java.lang.String) this.getAttribute ( TABEL_NAME).getValue()  ;
	}
 
	public Attribute getTabelName(){
		return this.getAttribute ( TABEL_NAME)  ;
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

	public static final String USER_ID ="USER_ID" ;

	public void setUserIdValue(java.lang.String   pm_userId){
		this.getAttribute(USER_ID ).setValue( pm_userId );
	}
 
	public java.lang.String getUserIdValue(){
		return (java.lang.String) this.getAttribute ( USER_ID).getValue()  ;
	}
 
	public Attribute getUserId(){
		return this.getAttribute ( USER_ID)  ;
	}

	public static final String VIEW_WHERE_CONDITION ="VIEW_WHERE_CONDITION" ;

	public void setViewWhereConditionValue(java.lang.String   pm_viewWhereCondition){
		this.getAttribute(VIEW_WHERE_CONDITION ).setValue( pm_viewWhereCondition );
	}
 
	public java.lang.String getViewWhereConditionValue(){
		return (java.lang.String) this.getAttribute ( VIEW_WHERE_CONDITION).getValue()  ;
	}
 
	public Attribute getViewWhereCondition(){
		return this.getAttribute ( VIEW_WHERE_CONDITION)  ;
	}

	public static final String USR_DEFAULT_VALUES ="USR_DEFAULT_VALUES" ;

	public void setUsrDefaultValuesValue(java.lang.String   pm_usrDefaultValues){
		this.getAttribute(USR_DEFAULT_VALUES ).setValue( pm_usrDefaultValues );
	}
 
	public java.lang.String getUsrDefaultValuesValue(){
		return (java.lang.String) this.getAttribute ( USR_DEFAULT_VALUES).getValue()  ;
	}
 
	public Attribute getUsrDefaultValues(){
		return this.getAttribute ( USR_DEFAULT_VALUES)  ;
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
}