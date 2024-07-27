package com.smartValue.epm.map.auto;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.Attribute;
import com.smartValue.database.DataSet;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;

public abstract class _BscObjectiveKpi extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "BSC_OBJECTIVE_KPI"; 
 public static final String DB_TABLE_OWNER = "GIHAZ"; 

	public _BscObjectiveKpi(){
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

	public static final String KPI_ID_OLD ="KPI_ID_OLD" ;

	public void setKpiIdOldValue(java.lang.String   pm_kpiIdOld){
		this.getAttribute(KPI_ID_OLD ).setValue( pm_kpiIdOld );
	}
 
	public java.lang.String getKpiIdOldValue(){
		return (java.lang.String) this.getAttribute ( KPI_ID_OLD).getValue()  ;
	}
 
	public Attribute getKpiIdOld(){
		return this.getAttribute ( KPI_ID_OLD)  ;
	}

	public static final String MEASURE_KPI ="MEASURE_KPI" ;

	public void setMeasureKpiValue(java.lang.String   pm_measureKpi){
		this.getAttribute(MEASURE_KPI ).setValue( pm_measureKpi );
	}
 
	public java.lang.String getMeasureKpiValue(){
		return (java.lang.String) this.getAttribute ( MEASURE_KPI).getValue()  ;
	}
 
	public Attribute getMeasureKpi(){
		return this.getAttribute ( MEASURE_KPI)  ;
	}

	public static final String KPI_DESC ="KPI_DESC" ;

	public void setKpiDescValue(java.lang.String   pm_kpiDesc){
		this.getAttribute(KPI_DESC ).setValue( pm_kpiDesc );
	}
 
	public java.lang.String getKpiDescValue(){
		return (java.lang.String) this.getAttribute ( KPI_DESC).getValue()  ;
	}
 
	public Attribute getKpiDesc(){
		return this.getAttribute ( KPI_DESC)  ;
	}

	public static final String MEASURE_UNIT ="MEASURE_UNIT" ;

	public void setMeasureUnitValue(java.lang.String   pm_measureUnit){
		this.getAttribute(MEASURE_UNIT ).setValue( pm_measureUnit );
	}
 
	public java.lang.String getMeasureUnitValue(){
		return (java.lang.String) this.getAttribute ( MEASURE_UNIT).getValue()  ;
	}
 
	public Attribute getMeasureUnit(){
		return this.getAttribute ( MEASURE_UNIT)  ;
	}

	public static final String TARGET ="TARGET" ;

	public void setTargetValue(java.math.BigDecimal   pm_target){
		this.getAttribute(TARGET ).setValue( pm_target );
	}
 
	public java.math.BigDecimal getTargetValue(){
		return (java.math.BigDecimal) this.getAttribute ( TARGET).getValue()  ;
	}
 
	public Attribute getTarget(){
		return this.getAttribute ( TARGET)  ;
	}

	public static final String FREQUENCY ="FREQUENCY" ;

	public void setFrequencyValue(java.lang.String   pm_frequency){
		this.getAttribute(FREQUENCY ).setValue( pm_frequency );
	}
 
	public java.lang.String getFrequencyValue(){
		return (java.lang.String) this.getAttribute ( FREQUENCY).getValue()  ;
	}
 
	public Attribute getFrequency(){
		return this.getAttribute ( FREQUENCY)  ;
	}

	public static final String RELATIVE_WIEGHT ="RELATIVE_WIEGHT" ;

	public void setRelativeWieghtValue(java.math.BigDecimal   pm_relativeWieght){
		this.getAttribute(RELATIVE_WIEGHT ).setValue( pm_relativeWieght );
	}
 
	public java.math.BigDecimal getRelativeWieghtValue(){
		return (java.math.BigDecimal) this.getAttribute ( RELATIVE_WIEGHT).getValue()  ;
	}
 
	public Attribute getRelativeWieght(){
		return this.getAttribute ( RELATIVE_WIEGHT)  ;
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

	public static final String STRATEGIC_OBJECTIVE_ID ="STRATEGIC_OBJECTIVE_ID" ;

	public void setStrategicObjectiveIdValue(java.math.BigDecimal   pm_strategicObjectiveId){
		this.getAttribute(STRATEGIC_OBJECTIVE_ID ).setValue( pm_strategicObjectiveId );
	}
 
	public java.math.BigDecimal getStrategicObjectiveIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( STRATEGIC_OBJECTIVE_ID).getValue()  ;
	}
 
	public Attribute getStrategicObjectiveId(){
		return this.getAttribute ( STRATEGIC_OBJECTIVE_ID)  ;
	}

	public static final String VALUE_TYPE ="VALUE_TYPE" ;

	public void setValueTypeValue(java.lang.String   pm_valueType){
		this.getAttribute(VALUE_TYPE ).setValue( pm_valueType );
	}
 
	public java.lang.String getValueTypeValue(){
		return (java.lang.String) this.getAttribute ( VALUE_TYPE).getValue()  ;
	}
 
	public Attribute getValueType(){
		return this.getAttribute ( VALUE_TYPE)  ;
	}

	public static final String QUERY_FOR_SELECT ="QUERY_FOR_SELECT" ;

	public void setQueryForSelectValue(java.lang.String   pm_queryForSelect){
		this.getAttribute(QUERY_FOR_SELECT ).setValue( pm_queryForSelect );
	}
 
	public java.lang.String getQueryForSelectValue(){
		return (java.lang.String) this.getAttribute ( QUERY_FOR_SELECT).getValue()  ;
	}
 
	public Attribute getQueryForSelect(){
		return this.getAttribute ( QUERY_FOR_SELECT)  ;
	}

	public static final String KPI_CLASS ="KPI_CLASS" ;

	public void setKpiClassValue(java.lang.String   pm_kpiClass){
		this.getAttribute(KPI_CLASS ).setValue( pm_kpiClass );
	}
 
	public java.lang.String getKpiClassValue(){
		return (java.lang.String) this.getAttribute ( KPI_CLASS).getValue()  ;
	}
 
	public Attribute getKpiClass(){
		return this.getAttribute ( KPI_CLASS)  ;
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

	public static final String DIRECTION ="DIRECTION" ;

	public void setDirectionValue(java.lang.String   pm_direction){
		this.getAttribute(DIRECTION ).setValue( pm_direction );
	}
 
	public java.lang.String getDirectionValue(){
		return (java.lang.String) this.getAttribute ( DIRECTION).getValue()  ;
	}
 
	public Attribute getDirection(){
		return this.getAttribute ( DIRECTION)  ;
	}

	public static final String AUTO_CALCULATED ="AUTO_CALCULATED" ;

	public void setAutoCalculatedValue(java.lang.String   pm_autoCalculated){
		this.getAttribute(AUTO_CALCULATED ).setValue( pm_autoCalculated );
	}
 
	public java.lang.String getAutoCalculatedValue(){
		return (java.lang.String) this.getAttribute ( AUTO_CALCULATED).getValue()  ;
	}
 
	public Attribute getAutoCalculated(){
		return this.getAttribute ( AUTO_CALCULATED)  ;
	}

	public static final String BONUS_THRESHOLD ="BONUS_THRESHOLD" ;

	public void setBonusThresholdValue(java.math.BigDecimal   pm_bonusThreshold){
		this.getAttribute(BONUS_THRESHOLD ).setValue( pm_bonusThreshold );
	}
 
	public java.math.BigDecimal getBonusThresholdValue(){
		return (java.math.BigDecimal) this.getAttribute ( BONUS_THRESHOLD).getValue()  ;
	}
 
	public Attribute getBonusThreshold(){
		return this.getAttribute ( BONUS_THRESHOLD)  ;
	}

	public static final String THRESHOLD_DELETE ="THRESHOLD_DELETE" ;

	public void setThresholdDeleteValue(java.math.BigDecimal   pm_thresholdDelete){
		this.getAttribute(THRESHOLD_DELETE ).setValue( pm_thresholdDelete );
	}
 
	public java.math.BigDecimal getThresholdDeleteValue(){
		return (java.math.BigDecimal) this.getAttribute ( THRESHOLD_DELETE).getValue()  ;
	}
 
	public Attribute getThresholdDelete(){
		return this.getAttribute ( THRESHOLD_DELETE)  ;
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

	public static final String ACHIVE_CALC_UDV_CODE ="ACHIVE_CALC_UDV_CODE" ;

	public void setAchiveCalcUdvCodeValue(java.math.BigDecimal   pm_achiveCalcUdvCode){
		this.getAttribute(ACHIVE_CALC_UDV_CODE ).setValue( pm_achiveCalcUdvCode );
	}
 
	public java.math.BigDecimal getAchiveCalcUdvCodeValue(){
		return (java.math.BigDecimal) this.getAttribute ( ACHIVE_CALC_UDV_CODE).getValue()  ;
	}
 
	public Attribute getAchiveCalcUdvCode(){
		return this.getAttribute ( ACHIVE_CALC_UDV_CODE)  ;
	}

	public static final String OFF_TRACK_PERC ="OFF_TRACK_PERC" ;

	public void setOffTrackPercValue(java.math.BigDecimal   pm_offTrackPerc){
		this.getAttribute(OFF_TRACK_PERC ).setValue( pm_offTrackPerc );
	}
 
	public java.math.BigDecimal getOffTrackPercValue(){
		return (java.math.BigDecimal) this.getAttribute ( OFF_TRACK_PERC).getValue()  ;
	}
 
	public Attribute getOffTrackPerc(){
		return this.getAttribute ( OFF_TRACK_PERC)  ;
	}

	// ********Start of Child DataSets getter methods******** 
	// *******End of Child DataSets getter methods******** 
}