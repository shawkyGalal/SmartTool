package com.implex.database.map.auto;

import com.implex.database.PersistantObject; 
import com.implex.database.Attribute;
import com.implex.database.DbTable;

public abstract class _OrgUnit extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "ORG_UNIT"; 
 public static final String DB_TABLE_OWNER = "JCCS"; 

	public _OrgUnit(){
	}

	 public DbTable getTable() 
	 { 
		return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String DESCRIPTION ="DESCRIPTION" ;

	public void setDescriptionValue(java.lang.String   pm_description){
		this.getAttribute(DESCRIPTION ).setValue( pm_description );
	}
 
	public java.lang.String getDescriptionValue(){
		return (java.lang.String) this.getAttribute ( DESCRIPTION).getValue()  ;
	}
 
	public Attribute getDescription(){
		return this.getAttribute ( DESCRIPTION)  ;
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

	public static final String ORG_UNIT_ID ="ORG_UNIT_ID" ;

	public void setOrgUnitIdValue(java.lang.String   pm_orgUnitId){
		this.getAttribute(ORG_UNIT_ID ).setValue( pm_orgUnitId );
	}
 
	public java.lang.String getOrgUnitIdValue(){
		return (java.lang.String) this.getAttribute ( ORG_UNIT_ID).getValue()  ;
	}
 
	public Attribute getOrgUnitId(){
		return this.getAttribute ( ORG_UNIT_ID)  ;
	}

	public static final String PARENT_ORG_UNIT_ID ="PARENT_ORG_UNIT_ID" ;

	public void setParentOrgUnitIdValue(java.lang.String   pm_parentOrgUnitId){
		this.getAttribute(PARENT_ORG_UNIT_ID ).setValue( pm_parentOrgUnitId );
	}
 
	public java.lang.String getParentOrgUnitIdValue(){
		return (java.lang.String) this.getAttribute ( PARENT_ORG_UNIT_ID).getValue()  ;
	}
 
	public Attribute getParentOrgUnitId(){
		return this.getAttribute ( PARENT_ORG_UNIT_ID)  ;
	}

	public static final String IS_HEADER ="IS_HEADER" ;

	public void setIsHeaderValue(java.lang.String   pm_isHeader){
		this.getAttribute(IS_HEADER ).setValue( pm_isHeader );
	}
 
	public java.lang.String getIsHeaderValue(){
		return (java.lang.String) this.getAttribute ( IS_HEADER).getValue()  ;
	}
 
	public Attribute getIsHeader(){
		return this.getAttribute ( IS_HEADER)  ;
	}

	public static final String ORG_UNIT_TYPE_ID ="ORG_UNIT_TYPE_ID" ;

	public void setOrgUnitTypeIdValue(java.math.BigDecimal   pm_orgUnitTypeId){
		this.getAttribute(ORG_UNIT_TYPE_ID ).setValue( pm_orgUnitTypeId );
	}
 
	public java.math.BigDecimal getOrgUnitTypeIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( ORG_UNIT_TYPE_ID).getValue()  ;
	}
 
	public Attribute getOrgUnitTypeId(){
		return this.getAttribute ( ORG_UNIT_TYPE_ID)  ;
	}

	public static final String ACTIVE ="ACTIVE" ;

	public void setActiveValue(java.lang.String   pm_active){
		this.getAttribute(ACTIVE ).setValue( pm_active );
	}
 
	public java.lang.String getActiveValue(){
		return (java.lang.String) this.getAttribute ( ACTIVE).getValue()  ;
	}
 
	public Attribute getActive(){
		return this.getAttribute ( ACTIVE)  ;
	}

	// ********Start of Child DataSets getter methods******** 
	// *******End of Child DataSets getter methods******** 
}