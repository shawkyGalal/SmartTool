package com.implex.database.map.auto;

import com.implex.database.Attribute;
import com.implex.database.DbTable;
import com.implex.database.PersistantObject;


public abstract class _SysPages extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */

	public _SysPages(){
	}

	 public DbTable getTable() 

	 { 

			return new DbTable("ICDB" , "SYS_PAGES" , this.getDbService()); 

	 } 

	public static final String PG_NAME ="PG_NAME" ;

	public void setPgNameValue(java.lang.String   pm_pgName){
		this.getAttribute("PG_NAME" ).setValue( pm_pgName );
	}
 
	public java.lang.String getPgNameValue(){
		return (java.lang.String) this.getAttribute ( "PG_NAME").getValue()  ;
	}
 
	public Attribute getPgName(){
		return this.getAttribute ( "PG_NAME")  ;
	}

	public static final String PG_TABLE ="PG_TABLE" ;

	public void setPgTableValue(java.lang.String   pm_pgTable){
		this.getAttribute("PG_TABLE" ).setValue( pm_pgTable );
	}
 
	public java.lang.String getPgTableValue(){
		return (java.lang.String) this.getAttribute ( "PG_TABLE").getValue()  ;
	}
 
	public Attribute getPgTable(){
		return this.getAttribute ( "PG_TABLE")  ;
	}

	public static final String PG_TABLE_PK ="PG_TABLE_PK" ;

	public void setPgTablePkValue(java.lang.String   pm_pgTablePk){
		this.getAttribute("PG_TABLE_PK" ).setValue( pm_pgTablePk );
	}
 
	public java.lang.String getPgTablePkValue(){
		return (java.lang.String) this.getAttribute ( "PG_TABLE_PK").getValue()  ;
	}
 
	public Attribute getPgTablePk(){
		return this.getAttribute ( "PG_TABLE_PK")  ;
	}

	public static final String PG_TABLE_PK2 ="PG_TABLE_PK2" ;

	public void setPgTablePk2Value(java.lang.String   pm_pgTablePk2){
		this.getAttribute("PG_TABLE_PK2" ).setValue( pm_pgTablePk2 );
	}
 
	public java.lang.String getPgTablePk2Value(){
		return (java.lang.String) this.getAttribute ( "PG_TABLE_PK2").getValue()  ;
	}
 
	public Attribute getPgTablePk2(){
		return this.getAttribute ( "PG_TABLE_PK2")  ;
	}

	public static final String PG_TABLE_PK3 ="PG_TABLE_PK3" ;

	public void setPgTablePk3Value(java.lang.String   pm_pgTablePk3){
		this.getAttribute("PG_TABLE_PK3" ).setValue( pm_pgTablePk3 );
	}
 
	public java.lang.String getPgTablePk3Value(){
		return (java.lang.String) this.getAttribute ( "PG_TABLE_PK3").getValue()  ;
	}
 
	public Attribute getPgTablePk3(){
		return this.getAttribute ( "PG_TABLE_PK3")  ;
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

	public static final String PG_TABLE_CMP_ID ="PG_TABLE_CMP_ID" ;

	public void setPgTableCmpIdValue(java.lang.String   pm_pgTableCmpId){
		this.getAttribute("PG_TABLE_CMP_ID" ).setValue( pm_pgTableCmpId );
	}
 
	public java.lang.String getPgTableCmpIdValue(){
		return (java.lang.String) this.getAttribute ( "PG_TABLE_CMP_ID").getValue()  ;
	}
 
	public Attribute getPgTableCmpId(){
		return this.getAttribute ( "PG_TABLE_CMP_ID")  ;
	}
}