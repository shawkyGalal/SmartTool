package com.implex.database.map.auto;

import com.implex.database.Attribute;
import com.implex.database.DbTable;
import com.implex.database.PersistantObject;

public abstract class _SavedSearchDetails extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "SAVED_SEARCH_DETAILS"; 
 public static final String DB_TABLE_OWNER = "ICDB";

	public _SavedSearchDetails(){
	}

	 public DbTable getTable() 
	 { 
			return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String OPERATOR ="OPERATOR" ;

	public void setOperatorValue(java.lang.String   pm_operator){
		this.getAttribute("OPERATOR" ).setValue( pm_operator );
	}
 
	public java.lang.String getOperatorValue(){
		return (java.lang.String) this.getAttribute ( "OPERATOR").getValue()  ;
	}
 
	public Attribute getOperator(){
		return this.getAttribute ( "OPERATOR")  ;
	}

	public static final String ATTRIBUTE_VALUE ="ATTRIBUTE_VALUE" ;

	public void setAttributeValueValue(java.lang.String   pm_attributeValue){
		this.getAttribute("ATTRIBUTE_VALUE" ).setValue( pm_attributeValue );
	}
 
	public java.lang.String getAttributeValueValue(){
		return (java.lang.String) this.getAttribute ( "ATTRIBUTE_VALUE").getValue()  ;
	}
 
	public Attribute getAttributeValue(){
		return this.getAttribute ( "ATTRIBUTE_VALUE")  ;
	}

	public static final String LOGICAL_OPERATION ="LOGICAL_OPERATION" ;

	public void setLogicalOperationValue(java.lang.String   pm_logicalOperation){
		this.getAttribute("LOGICAL_OPERATION" ).setValue( pm_logicalOperation );
	}
 
	public java.lang.String getLogicalOperationValue(){
		return (java.lang.String) this.getAttribute ( "LOGICAL_OPERATION").getValue()  ;
	}
 
	public Attribute getLogicalOperation(){
		return this.getAttribute ( "LOGICAL_OPERATION")  ;
	}

	public static final String ATTRIBUTE_DATE_VALUE ="ATTRIBUTE_DATE_VALUE" ;

	public void setAttributeDateValueValue(java.sql.Timestamp   pm_attributeDateValue){
		this.getAttribute("ATTRIBUTE_DATE_VALUE" ).setValue( pm_attributeDateValue );
	}
 
	public java.sql.Timestamp getAttributeDateValueValue(){
		return (java.sql.Timestamp) this.getAttribute ( "ATTRIBUTE_DATE_VALUE").getValue()  ;
	}
 
	public Attribute getAttributeDateValue(){
		return this.getAttribute ( "ATTRIBUTE_DATE_VALUE")  ;
	}

	public static final String DATE_EXPRESSION_ID ="DATE_EXPRESSION_ID" ;

	public void setDateExpressionIdValue(java.math.BigDecimal   pm_dateExpressionId){
		this.getAttribute("DATE_EXPRESSION_ID" ).setValue( pm_dateExpressionId );
	}
 
	public java.math.BigDecimal getDateExpressionIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( "DATE_EXPRESSION_ID").getValue()  ;
	}
 
	public Attribute getDateExpressionId(){
		return this.getAttribute ( "DATE_EXPRESSION_ID")  ;
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

	public static final String SEARCH_NAME ="SEARCH_NAME" ;

	public void setSearchNameValue(java.lang.String   pm_searchName){
		this.getAttribute("SEARCH_NAME" ).setValue( pm_searchName );
	}
 
	public java.lang.String getSearchNameValue(){
		return (java.lang.String) this.getAttribute ( "SEARCH_NAME").getValue()  ;
	}
 
	public Attribute getSearchName(){
		return this.getAttribute ( "SEARCH_NAME")  ;
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

	public static final String ORDERS ="ORDERS" ;

	public void setOrdersValue(java.math.BigDecimal   pm_orders){
		this.getAttribute("ORDERS" ).setValue( pm_orders );
	}
 
	public java.math.BigDecimal getOrdersValue(){
		return (java.math.BigDecimal) this.getAttribute ( "ORDERS").getValue()  ;
	}
 
	public Attribute getOrders(){
		return this.getAttribute ( "ORDERS")  ;
	}
}