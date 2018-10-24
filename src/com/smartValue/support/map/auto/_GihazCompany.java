package com.smartValue.support.map.auto;

import com.implex.database.PersistantObject; 
import com.implex.database.DataSet;
import com.implex.database.Attribute;
import com.implex.database.DbTable;
import com.implex.database.ApplicationContext;

public abstract class _GihazCompany extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "GIHAZ_COMPANY"; 
 public static final String DB_TABLE_OWNER = "GIHAZ"; 

	public _GihazCompany(){
	}

	 public DbTable getTable() 
	 { 
		return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
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

	public static final String COMPANY_NAME ="COMPANY_NAME" ;

	public void setCompanyNameValue(java.lang.String   pm_companyName){
		this.getAttribute(COMPANY_NAME ).setValue( pm_companyName );
	}
 
	public java.lang.String getCompanyNameValue(){
		return (java.lang.String) this.getAttribute ( COMPANY_NAME).getValue()  ;
	}
 
	public Attribute getCompanyName(){
		return this.getAttribute ( COMPANY_NAME)  ;
	}

	public static final String APPROVAL_TEMPLATE_ID ="APPROVAL_TEMPLATE_ID" ;

	public void setApprovalTemplateIdValue(java.math.BigDecimal   pm_approvalTemplateId){
		this.getAttribute(APPROVAL_TEMPLATE_ID ).setValue( pm_approvalTemplateId );
	}
 
	public java.math.BigDecimal getApprovalTemplateIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( APPROVAL_TEMPLATE_ID).getValue()  ;
	}
 
	public Attribute getApprovalTemplateId(){
		return this.getAttribute ( APPROVAL_TEMPLATE_ID)  ;
	}

	// ********Start of Child DataSets getter methods******** 
	// *******End of Child DataSets getter methods******** 
}