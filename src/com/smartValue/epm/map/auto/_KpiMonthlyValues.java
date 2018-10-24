package com.smartValue.epm.map.auto;

import com.implex.database.PersistantObject; 
import com.implex.database.DataSet;
import com.implex.database.Attribute;
import com.implex.database.DbTable;
import com.implex.database.ApplicationContext;

public abstract class _KpiMonthlyValues extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "KPI_MONTHLY_VALUES"; 
 public static final String DB_TABLE_OWNER = "GIHAZ"; 

	public _KpiMonthlyValues(){
	}

	 public DbTable getTable() 
	 { 
		return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
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

	public static final String OBJECTIVE_ID ="OBJECTIVE_ID" ;

	public void setObjectiveIdValue(java.math.BigDecimal   pm_objectiveId){
		this.getAttribute(OBJECTIVE_ID ).setValue( pm_objectiveId );
	}
 
	public java.math.BigDecimal getObjectiveIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( OBJECTIVE_ID).getValue()  ;
	}
 
	public Attribute getObjectiveId(){
		return this.getAttribute ( OBJECTIVE_ID)  ;
	}

	public static final String KPI_ID ="KPI_ID" ;

	public void setKpiIdValue(java.math.BigDecimal   pm_kpiId){
		this.getAttribute(KPI_ID ).setValue( pm_kpiId );
	}
 
	public java.math.BigDecimal getKpiIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( KPI_ID).getValue()  ;
	}
 
	public Attribute getKpiId(){
		return this.getAttribute ( KPI_ID)  ;
	}

	public static final String MONTH ="MONTH" ;

	public void setMonthValue(java.sql.Timestamp   pm_month){
		this.getAttribute(MONTH ).setValue( pm_month );
	}
 
	public java.sql.Timestamp getMonthValue(){
		return (java.sql.Timestamp) this.getAttribute ( MONTH).getValue()  ;
	}
 
	public Attribute getMonth(){
		return this.getAttribute ( MONTH)  ;
	}

	public static final String TARGET_PLANNED_YTD ="TARGET_PLANNED_YTD" ;

	public void setTargetPlannedYtdValue(java.math.BigDecimal   pm_targetPlannedYtd){
		this.getAttribute(TARGET_PLANNED_YTD ).setValue( pm_targetPlannedYtd );
	}
 
	public java.math.BigDecimal getTargetPlannedYtdValue(){
		return (java.math.BigDecimal) this.getAttribute ( TARGET_PLANNED_YTD).getValue()  ;
	}
 
	public Attribute getTargetPlannedYtd(){
		return this.getAttribute ( TARGET_PLANNED_YTD)  ;
	}

	public static final String TARGET_ACT_YTD ="TARGET_ACT_YTD" ;

	public void setTargetActYtdValue(java.math.BigDecimal   pm_targetActYtd){
		this.getAttribute(TARGET_ACT_YTD ).setValue( pm_targetActYtd );
	}
 
	public java.math.BigDecimal getTargetActYtdValue(){
		return (java.math.BigDecimal) this.getAttribute ( TARGET_ACT_YTD).getValue()  ;
	}
 
	public Attribute getTargetActYtd(){
		return this.getAttribute ( TARGET_ACT_YTD)  ;
	}

	public static final String TARGET_MONTHLY_ACT ="TARGET_MONTHLY_ACT" ;

	public void setTargetMonthlyActValue(java.math.BigDecimal   pm_targetMonthlyAct){
		this.getAttribute(TARGET_MONTHLY_ACT ).setValue( pm_targetMonthlyAct );
	}
 
	public java.math.BigDecimal getTargetMonthlyActValue(){
		return (java.math.BigDecimal) this.getAttribute ( TARGET_MONTHLY_ACT).getValue()  ;
	}
 
	public Attribute getTargetMonthlyAct(){
		return this.getAttribute ( TARGET_MONTHLY_ACT)  ;
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

	public static final String MEASURE_SOURCE ="MEASURE_SOURCE" ;

	public void setMeasureSourceValue(java.lang.String   pm_measureSource){
		this.getAttribute(MEASURE_SOURCE ).setValue( pm_measureSource );
	}
 
	public java.lang.String getMeasureSourceValue(){
		return (java.lang.String) this.getAttribute ( MEASURE_SOURCE).getValue()  ;
	}
 
	public Attribute getMeasureSource(){
		return this.getAttribute ( MEASURE_SOURCE)  ;
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

	public static final String EMP_ID ="EMP_ID" ;

	public void setEmpIdValue(java.lang.String   pm_empId){
		this.getAttribute(EMP_ID ).setValue( pm_empId );
	}
 
	public java.lang.String getEmpIdValue(){
		return (java.lang.String) this.getAttribute ( EMP_ID).getValue()  ;
	}
 
	public Attribute getEmpId(){
		return this.getAttribute ( EMP_ID)  ;
	}

	public static final String POSITION_ID ="POSITION_ID" ;

	public void setPositionIdValue(java.lang.String   pm_positionId){
		this.getAttribute(POSITION_ID ).setValue( pm_positionId );
	}
 
	public java.lang.String getPositionIdValue(){
		return (java.lang.String) this.getAttribute ( POSITION_ID).getValue()  ;
	}
 
	public Attribute getPositionId(){
		return this.getAttribute ( POSITION_ID)  ;
	}

	public static final String THRESHOLD ="THRESHOLD" ;

	public void setThresholdValue(java.math.BigDecimal   pm_threshold){
		this.getAttribute(THRESHOLD ).setValue( pm_threshold );
	}
 
	public java.math.BigDecimal getThresholdValue(){
		return (java.math.BigDecimal) this.getAttribute ( THRESHOLD).getValue()  ;
	}
 
	public Attribute getThreshold(){
		return this.getAttribute ( THRESHOLD)  ;
	}

	// ********Start of Child DataSets getter methods******** 
	// *******End of Child DataSets getter methods******** 
}