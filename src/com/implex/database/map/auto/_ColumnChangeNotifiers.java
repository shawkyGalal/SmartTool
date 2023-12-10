package com.implex.database.map.auto;

import com.implex.database.Attribute;
import com.implex.database.DbTable;
import com.implex.database.PersistantObject;

public abstract class _ColumnChangeNotifiers extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "COLUMN_CHANGE_NOTIFIERS"; 
 public static final String DB_TABLE_OWNER = "ICDB";

	public _ColumnChangeNotifiers(){
	}

	 public DbTable getTable() 
	 { 
			return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
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

	public static final String COLUMN_NAME ="COLUMN_NAME" ;

	public void setColumnNameValue(java.lang.String   pm_columnName){
		this.getAttribute("COLUMN_NAME" ).setValue( pm_columnName );
	}
 
	public java.lang.String getColumnNameValue(){
		return (java.lang.String) this.getAttribute ( "COLUMN_NAME").getValue()  ;
	}
 
	public Attribute getColumnName(){
		return this.getAttribute ( "COLUMN_NAME")  ;
	}

	public static final String CHANGE_NOTIFY_IMPL_ID ="CHANGE_NOTIFY_IMPL_ID" ;

	public void setChangeNotifyImplIdValue(java.math.BigDecimal   pm_changeNotifyImplId){
		this.getAttribute("CHANGE_NOTIFY_IMPL_ID" ).setValue( pm_changeNotifyImplId );
	}
 
	public java.math.BigDecimal getChangeNotifyImplIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( "CHANGE_NOTIFY_IMPL_ID").getValue()  ;
	}
 
	public Attribute getChangeNotifyImplId(){
		return this.getAttribute ( "CHANGE_NOTIFY_IMPL_ID")  ;
	}
}