package com.smartValue.support.map.auto;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.Attribute;
import com.smartValue.database.DataSet;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;

public abstract class _LuQueryDetails extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "LU_QUERY_DETAILS"; 
 public static final String DB_TABLE_OWNER = "SUPPORT"; 

	public _LuQueryDetails(){
	}

	 public DbTable getTable() 
	 { 
		return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String QUERY_ID ="QUERY_ID" ;

	public void setQueryIdValue(java.math.BigDecimal   pm_queryId){
		this.getAttribute(QUERY_ID ).setValue( pm_queryId );
	}
 
	public java.math.BigDecimal getQueryIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( QUERY_ID).getValue()  ;
	}
 
	public Attribute getQueryId(){
		return this.getAttribute ( QUERY_ID)  ;
	}

	public static final String QUERY_INDEX ="QUERY_INDEX" ;

	public void setQueryIndexValue(java.math.BigDecimal   pm_queryIndex){
		this.getAttribute(QUERY_INDEX ).setValue( pm_queryIndex );
	}
 
	public java.math.BigDecimal getQueryIndexValue(){
		return (java.math.BigDecimal) this.getAttribute ( QUERY_INDEX).getValue()  ;
	}
 
	public Attribute getQueryIndex(){
		return this.getAttribute ( QUERY_INDEX)  ;
	}

	public static final String QUERY_BODY ="QUERY_BODY" ;

	public void setQueryBodyValue(oracle.sql.CLOB   pm_queryBody){
		this.getAttribute(QUERY_BODY ).setValue( pm_queryBody );
	}
 
	public java.lang.String getQueryBodyValue(){
		return (java.lang.String) this.getAttribute ( QUERY_BODY).getValue()  ;
	}
 
	public Attribute getQueryBody(){
		return this.getAttribute ( QUERY_BODY)  ;
	}

	public static final String SHOW_AGGREGATE ="SHOW_AGGREGATE" ;

	public void setShowAggregateValue(java.lang.String   pm_showAggregate){
		this.getAttribute(SHOW_AGGREGATE ).setValue( pm_showAggregate );
	}
 
	public java.lang.String getShowAggregateValue(){
		return (java.lang.String) this.getAttribute ( SHOW_AGGREGATE).getValue()  ;
	}
 
	public Attribute getShowAggregate(){
		return this.getAttribute ( SHOW_AGGREGATE)  ;
	}

	public static final String TITLE ="TITLE" ;

	public void setTitleValue(java.lang.String   pm_title){
		this.getAttribute(TITLE ).setValue( pm_title );
	}
 
	public java.lang.String getTitleValue(){
		return (java.lang.String) this.getAttribute ( TITLE).getValue()  ;
	}
 
	public Attribute getTitle(){
		return this.getAttribute ( TITLE)  ;
	}

	public static final String FOOTER ="FOOTER" ;

	public void setFooterValue(java.lang.String   pm_footer){
		this.getAttribute(FOOTER ).setValue( pm_footer );
	}
 
	public java.lang.String getFooterValue(){
		return (java.lang.String) this.getAttribute ( FOOTER).getValue()  ;
	}
 
	public Attribute getFooter(){
		return this.getAttribute ( FOOTER)  ;
	}

	public static final String TABLE_CSS_CLASS ="TABLE_CSS_CLASS" ;

	public void setTableCssClassValue(java.lang.String   pm_tableCssClass){
		this.getAttribute(TABLE_CSS_CLASS ).setValue( pm_tableCssClass );
	}
 
	public java.lang.String getTableCssClassValue(){
		return (java.lang.String) this.getAttribute ( TABLE_CSS_CLASS).getValue()  ;
	}
 
	public Attribute getTableCssClass(){
		return this.getAttribute ( TABLE_CSS_CLASS)  ;
	}

	public static final String PIVOTTABLE_INIT_PARAMS ="PIVOTTABLE_INIT_PARAMS" ;

	public void setPivottableInitParamsValue(java.lang.String   pm_pivottableInitParams){
		this.getAttribute(PIVOTTABLE_INIT_PARAMS ).setValue( pm_pivottableInitParams );
	}
 
	public java.lang.String getPivottableInitParamsValue(){
		return (java.lang.String) this.getAttribute ( PIVOTTABLE_INIT_PARAMS).getValue()  ;
	}
 
	public Attribute getPivottableInitParams(){
		return this.getAttribute ( PIVOTTABLE_INIT_PARAMS)  ;
	}

	// ********Start of Child DataSets getter methods******** 
	// *******End of Child DataSets getter methods******** 
}