package com.smartValue.sys.map.auto;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.Attribute;
import com.smartValue.database.DataSet;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;

public abstract class _AllConsColumns extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "ALL_CONS_COLUMNS"; 
 public static final String DB_TABLE_OWNER = "SYS";
	public _AllConsColumns(){
	}

	 public DbTable getTable() 
	 { 
		return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME, this.getDbService()); 
	 } 

	public static final String OWNER ="OWNER" ;

	public void setOwnerValue(java.lang.String   pm_owner){
		this.getAttribute(OWNER ).setValue( pm_owner );
	}
 
	public java.lang.String getOwnerValue(){
		return (java.lang.String) this.getAttribute ( OWNER).getValue()  ;
	}
 
	public Attribute getOwner(){
		return this.getAttribute ( OWNER)  ;
	}

	public static final String CONSTRAINT_NAME ="CONSTRAINT_NAME" ;

	public void setConstraintNameValue(java.lang.String   pm_constraintName){
		this.getAttribute(CONSTRAINT_NAME ).setValue( pm_constraintName );
	}
 
	public java.lang.String getConstraintNameValue(){
		return (java.lang.String) this.getAttribute ( CONSTRAINT_NAME).getValue()  ;
	}
 
	public Attribute getConstraintName(){
		return this.getAttribute ( CONSTRAINT_NAME)  ;
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

	public static final String COLUMN_NAME ="COLUMN_NAME" ;

	public void setColumnNameValue(java.lang.String   pm_columnName){
		this.getAttribute(COLUMN_NAME ).setValue( pm_columnName );
	}
 
	public java.lang.String getColumnNameValue(){
		return (java.lang.String) this.getAttribute ( COLUMN_NAME).getValue()  ;
	}
 
	public Attribute getColumnName(){
		return this.getAttribute ( COLUMN_NAME)  ;
	}

	public static final String POSITION ="POSITION" ;

	public void setPositionValue(java.math.BigDecimal   pm_position){
		this.getAttribute(POSITION ).setValue( pm_position );
	}
 
	public java.math.BigDecimal getPositionValue(){
		return (java.math.BigDecimal) this.getAttribute ( POSITION).getValue()  ;
	}
 
	public Attribute getPosition(){
		return this.getAttribute ( POSITION)  ;
	}

	// ********Start of Child DataSets getter methods******** 
	// *******End of Child DataSets getter methods******** 
}