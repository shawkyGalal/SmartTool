package com.smartValue.epm.map.auto;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.Attribute;
import com.smartValue.database.DataSet;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;

public abstract class _BscObjective extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "BSC_OBJECTIVE"; 
 public static final String DB_TABLE_OWNER = "GIHAZ"; 

	public _BscObjective(){
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

	public static final String OBJECTIVE_TYPE ="OBJECTIVE_TYPE" ;

	public void setObjectiveTypeValue(java.lang.String   pm_objectiveType){
		this.getAttribute(OBJECTIVE_TYPE ).setValue( pm_objectiveType );
	}
 
	public java.lang.String getObjectiveTypeValue(){
		return (java.lang.String) this.getAttribute ( OBJECTIVE_TYPE).getValue()  ;
	}
 
	public Attribute getObjectiveType(){
		return this.getAttribute ( OBJECTIVE_TYPE)  ;
	}

	public static final String OBJECTIVE ="OBJECTIVE" ;

	public void setObjectiveValue(java.lang.String   pm_objective){
		this.getAttribute(OBJECTIVE ).setValue( pm_objective );
	}
 
	public java.lang.String getObjectiveValue(){
		return (java.lang.String) this.getAttribute ( OBJECTIVE).getValue()  ;
	}
 
	public Attribute getObjective(){
		return this.getAttribute ( OBJECTIVE)  ;
	}

	public static final String OBJECTIVE_ID ="OBJECTIVE_ID" ;

	public void setObjectiveIdValue(java.lang.String   pm_objectiveId){
		this.getAttribute(OBJECTIVE_ID ).setValue( pm_objectiveId );
	}
 
	public java.lang.String getObjectiveIdValue(){
		return (java.lang.String) this.getAttribute ( OBJECTIVE_ID).getValue()  ;
	}
 
	public Attribute getObjectiveId(){
		return this.getAttribute ( OBJECTIVE_ID)  ;
	}

	public static final String COMMENTS ="COMMENTS" ;

	public void setCommentsValue(java.lang.String   pm_comments){
		this.getAttribute(COMMENTS ).setValue( pm_comments );
	}
 
	public java.lang.String getCommentsValue(){
		return (java.lang.String) this.getAttribute ( COMMENTS).getValue()  ;
	}
 
	public Attribute getComments(){
		return this.getAttribute ( COMMENTS)  ;
	}

	public static final String BUSINESS_UNIT ="BUSINESS_UNIT" ;

	public void setBusinessUnitValue(java.lang.String   pm_businessUnit){
		this.getAttribute(BUSINESS_UNIT ).setValue( pm_businessUnit );
	}
 
	public java.lang.String getBusinessUnitValue(){
		return (java.lang.String) this.getAttribute ( BUSINESS_UNIT).getValue()  ;
	}
 
	public Attribute getBusinessUnit(){
		return this.getAttribute ( BUSINESS_UNIT)  ;
	}

	// ********Start of Child DataSets getter methods******** 
	// *******End of Child DataSets getter methods******** 
}