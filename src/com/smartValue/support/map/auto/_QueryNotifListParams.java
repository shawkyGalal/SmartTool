package com.smartValue.support.map.auto;

import com.implex.database.PersistantObject; 
import com.implex.database.DataSet;
import com.implex.database.Attribute;
import com.implex.database.DbTable;
import com.implex.database.ApplicationContext;

public abstract class _QueryNotifListParams extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "QUERY_NOTIF_LIST_PARAMS"; 
 public static final String DB_TABLE_OWNER = "SUPPORT"; 

	public _QueryNotifListParams(){
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

	public static final String COMPANY_ID ="COMPANY_ID" ;

	public void setCompanyIdValue(java.math.BigDecimal   pm_companyId){
		this.getAttribute(COMPANY_ID ).setValue( pm_companyId );
	}
 
	public java.math.BigDecimal getCompanyIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( COMPANY_ID).getValue()  ;
	}
 
	public Attribute getCompanyId(){
		return this.getAttribute ( COMPANY_ID)  ;
	}

	public static final String NOTIFY_ID ="NOTIFY_ID" ;

	public void setNotifyIdValue(java.math.BigDecimal   pm_notifyId){
		this.getAttribute(NOTIFY_ID ).setValue( pm_notifyId );
	}
 
	public java.math.BigDecimal getNotifyIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( NOTIFY_ID).getValue()  ;
	}
 
	public Attribute getNotifyId(){
		return this.getAttribute ( NOTIFY_ID)  ;
	}

	public static final String PARAM_NAME ="PARAM_NAME" ;

	public void setParamNameValue(java.lang.String   pm_paramName){
		this.getAttribute(PARAM_NAME ).setValue( pm_paramName );
	}
 
	public java.lang.String getParamNameValue(){
		return (java.lang.String) this.getAttribute ( PARAM_NAME).getValue()  ;
	}
 
	public Attribute getParamName(){
		return this.getAttribute ( PARAM_NAME)  ;
	}

	public static final String PARAM_VALUE ="PARAM_VALUE" ;

	public void setParamValueValue(java.lang.String   pm_paramValue){
		this.getAttribute(PARAM_VALUE ).setValue( pm_paramValue );
	}
 
	public java.lang.String getParamValueValue(){
		return (java.lang.String) this.getAttribute ( PARAM_VALUE).getValue()  ;
	}
 
	public Attribute getParamValue(){
		return this.getAttribute ( PARAM_VALUE)  ;
	}

	// ********Start of Child DataSets getter methods******** 
	// *******End of Child DataSets getter methods******** 
}