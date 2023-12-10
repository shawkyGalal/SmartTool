package com.implex.database.map.auto;

import com.implex.database.PersistantObject; 
import com.implex.database.DataSet;
import com.implex.database.Attribute;
import com.implex.database.DbTable;
import com.implex.database.ApplicationContext;

public abstract class _Tasks extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "TASKS"; 
 public static final String DB_TABLE_OWNER = "ICDB";

	public _Tasks(){
	}

	 public DbTable getTable() 
	 { 
			return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String TASK_ID ="TASK_ID" ;

	public void setTaskIdValue(java.math.BigDecimal   pm_taskId){
		this.getAttribute(TASK_ID ).setValue( pm_taskId );
	}
 
	public java.math.BigDecimal getTaskIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( TASK_ID).getValue()  ;
	}
 
	public Attribute getTaskId(){
		return this.getAttribute ( TASK_ID)  ;
	}

	public static final String PARENT_TSSK_ID ="PARENT_TSSK_ID" ;

	public void setParentTsskIdValue(java.math.BigDecimal   pm_parentTsskId){
		this.getAttribute(PARENT_TSSK_ID ).setValue( pm_parentTsskId );
	}
 
	public java.math.BigDecimal getParentTsskIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( PARENT_TSSK_ID).getValue()  ;
	}
 
	public Attribute getParentTsskId(){
		return this.getAttribute ( PARENT_TSSK_ID)  ;
	}

	public static final String BREF_DESC ="BREF_DESC" ;

	public void setBrefDescValue(java.lang.String   pm_brefDesc){
		this.getAttribute(BREF_DESC ).setValue( pm_brefDesc );
	}
 
	public java.lang.String getBrefDescValue(){
		return (java.lang.String) this.getAttribute ( BREF_DESC).getValue()  ;
	}
 
	public Attribute getBrefDesc(){
		return this.getAttribute ( BREF_DESC)  ;
	}

	public static final String DESCRIPTION ="DESCRIPTION" ;

	public void setDescriptionValue(oracle.sql.CLOB   pm_description){
		this.getAttribute(DESCRIPTION ).setValue( pm_description );
	}
 
	public oracle.sql.CLOB getDescriptionValue(){
		return (oracle.sql.CLOB) this.getAttribute ( DESCRIPTION).getValue()  ;
	}
 
	public Attribute getDescription(){
		return this.getAttribute ( DESCRIPTION)  ;
	}

	public static final String ASSIGNED_TO ="ASSIGNED_TO" ;

	public void setAssignedToValue(java.lang.String   pm_assignedTo){
		this.getAttribute(ASSIGNED_TO ).setValue( pm_assignedTo );
	}
 
	public java.lang.String getAssignedToValue(){
		return (java.lang.String) this.getAttribute ( ASSIGNED_TO).getValue()  ;
	}
 
	public Attribute getAssignedTo(){
		return this.getAttribute ( ASSIGNED_TO)  ;
	}

	public static final String ASSIGNED_BY ="ASSIGNED_BY" ;

	public void setAssignedByValue(java.lang.String   pm_assignedBy){
		this.getAttribute(ASSIGNED_BY ).setValue( pm_assignedBy );
	}
 
	public java.lang.String getAssignedByValue(){
		return (java.lang.String) this.getAttribute ( ASSIGNED_BY).getValue()  ;
	}
 
	public Attribute getAssignedBy(){
		return this.getAttribute ( ASSIGNED_BY)  ;
	}

	public static final String ASSIGN_DATE ="ASSIGN_DATE" ;

	public void setAssignDateValue(java.sql.Timestamp   pm_assignDate){
		this.getAttribute(ASSIGN_DATE ).setValue( pm_assignDate );
	}
 
	public java.sql.Timestamp getAssignDateValue(){
		return (java.sql.Timestamp) this.getAttribute ( ASSIGN_DATE).getValue()  ;
	}
 
	public Attribute getAssignDate(){
		return this.getAttribute ( ASSIGN_DATE)  ;
	}

	public static final String ESTIMATED_START_DATE ="ESTIMATED_START_DATE" ;

	public void setEstimatedStartDateValue(java.sql.Timestamp   pm_estimatedStartDate){
		this.getAttribute(ESTIMATED_START_DATE ).setValue( pm_estimatedStartDate );
	}
 
	public java.sql.Timestamp getEstimatedStartDateValue(){
		return (java.sql.Timestamp) this.getAttribute ( ESTIMATED_START_DATE).getValue()  ;
	}
 
	public Attribute getEstimatedStartDate(){
		return this.getAttribute ( ESTIMATED_START_DATE)  ;
	}

	public static final String ACTUAL_START_DATE ="ACTUAL_START_DATE" ;

	public void setActualStartDateValue(java.sql.Timestamp   pm_actualStartDate){
		this.getAttribute(ACTUAL_START_DATE ).setValue( pm_actualStartDate );
	}
 
	public java.sql.Timestamp getActualStartDateValue(){
		return (java.sql.Timestamp) this.getAttribute ( ACTUAL_START_DATE).getValue()  ;
	}
 
	public Attribute getActualStartDate(){
		return this.getAttribute ( ACTUAL_START_DATE)  ;
	}

	public static final String URGENT ="URGENT" ;

	public void setUrgentValue(java.math.BigDecimal   pm_urgent){
		this.getAttribute(URGENT ).setValue( pm_urgent );
	}
 
	public java.math.BigDecimal getUrgentValue(){
		return (java.math.BigDecimal) this.getAttribute ( URGENT).getValue()  ;
	}
 
	public Attribute getUrgent(){
		return this.getAttribute ( URGENT)  ;
	}

	public static final String PERIORITY ="PERIORITY" ;

	public void setPeriorityValue(java.math.BigDecimal   pm_periority){
		this.getAttribute(PERIORITY ).setValue( pm_periority );
	}
 
	public java.math.BigDecimal getPeriorityValue(){
		return (java.math.BigDecimal) this.getAttribute ( PERIORITY).getValue()  ;
	}
 
	public Attribute getPeriority(){
		return this.getAttribute ( PERIORITY)  ;
	}

	public static final String ESTIMATED_DURATION_IN_HOUR ="ESTIMATED_DURATION_IN_HOUR" ;

	public void setEstimatedDurationInHourValue(java.math.BigDecimal   pm_estimatedDurationInHour){
		this.getAttribute(ESTIMATED_DURATION_IN_HOUR ).setValue( pm_estimatedDurationInHour );
	}
 
	public java.math.BigDecimal getEstimatedDurationInHourValue(){
		return (java.math.BigDecimal) this.getAttribute ( ESTIMATED_DURATION_IN_HOUR).getValue()  ;
	}
 
	public Attribute getEstimatedDurationInHour(){
		return this.getAttribute ( ESTIMATED_DURATION_IN_HOUR)  ;
	}

	public static final String TASK_STATE ="TASK_STATE" ;

	public void setTaskStateValue(java.math.BigDecimal   pm_taskState){
		this.getAttribute(TASK_STATE ).setValue( pm_taskState );
	}
 
	public java.math.BigDecimal getTaskStateValue(){
		return (java.math.BigDecimal) this.getAttribute ( TASK_STATE).getValue()  ;
	}
 
	public Attribute getTaskState(){
		return this.getAttribute ( TASK_STATE)  ;
	}

	public static final String PERC_COMPLETED ="PERC_COMPLETED" ;

	public void setPercCompletedValue(java.math.BigDecimal   pm_percCompleted){
		this.getAttribute(PERC_COMPLETED ).setValue( pm_percCompleted );
	}
 
	public java.math.BigDecimal getPercCompletedValue(){
		return (java.math.BigDecimal) this.getAttribute ( PERC_COMPLETED).getValue()  ;
	}
 
	public Attribute getPercCompleted(){
		return this.getAttribute ( PERC_COMPLETED)  ;
	}

	public static final String PREPARED_BY ="PREPARED_BY" ;

	public void setPreparedByValue(java.lang.String   pm_preparedBy){
		this.getAttribute(PREPARED_BY ).setValue( pm_preparedBy );
	}
 
	public java.lang.String getPreparedByValue(){
		return (java.lang.String) this.getAttribute ( PREPARED_BY).getValue()  ;
	}
 
	public Attribute getPreparedBy(){
		return this.getAttribute ( PREPARED_BY)  ;
	}

	public static final String PREPARED_DT ="PREPARED_DT" ;

	public void setPreparedDtValue(java.sql.Timestamp   pm_preparedDt){
		this.getAttribute(PREPARED_DT ).setValue( pm_preparedDt );
	}
 
	public java.sql.Timestamp getPreparedDtValue(){
		return (java.sql.Timestamp) this.getAttribute ( PREPARED_DT).getValue()  ;
	}
 
	public Attribute getPreparedDt(){
		return this.getAttribute ( PREPARED_DT)  ;
	}

	public static final String MODIFIED_BY ="MODIFIED_BY" ;

	public void setModifiedByValue(java.lang.String   pm_modifiedBy){
		this.getAttribute(MODIFIED_BY ).setValue( pm_modifiedBy );
	}
 
	public java.lang.String getModifiedByValue(){
		return (java.lang.String) this.getAttribute ( MODIFIED_BY).getValue()  ;
	}
 
	public Attribute getModifiedBy(){
		return this.getAttribute ( MODIFIED_BY)  ;
	}

	public static final String MODIFIED_DT ="MODIFIED_DT" ;

	public void setModifiedDtValue(java.sql.Timestamp   pm_modifiedDt){
		this.getAttribute(MODIFIED_DT ).setValue( pm_modifiedDt );
	}
 
	public java.sql.Timestamp getModifiedDtValue(){
		return (java.sql.Timestamp) this.getAttribute ( MODIFIED_DT).getValue()  ;
	}
 
	public Attribute getModifiedDt(){
		return this.getAttribute ( MODIFIED_DT)  ;
	}

	public static final String TEST_ASSIGNED_TO ="TEST_ASSIGNED_TO" ;

	public void setTestAssignedToValue(java.lang.String   pm_testAssignedTo){
		this.getAttribute(TEST_ASSIGNED_TO ).setValue( pm_testAssignedTo );
	}
 
	public java.lang.String getTestAssignedToValue(){
		return (java.lang.String) this.getAttribute ( TEST_ASSIGNED_TO).getValue()  ;
	}
 
	public Attribute getTestAssignedTo(){
		return this.getAttribute ( TEST_ASSIGNED_TO)  ;
	}

	public static final String FILE_ATTACHED ="FILE_ATTACHED" ;

	public void setFileAttachedValue(oracle.sql.BLOB   pm_fileAttached){
		this.getAttribute(FILE_ATTACHED ).setValue( pm_fileAttached );
	}
 
	public oracle.sql.BLOB getFileAttachedValue(){
		return (oracle.sql.BLOB) this.getAttribute ( FILE_ATTACHED).getValue()  ;
	}
 
	public Attribute getFileAttached(){
		return this.getAttribute ( FILE_ATTACHED)  ;
	}

	public static final String FILE_NAME ="FILE_NAME" ;

	public void setFileNameValue(java.lang.String   pm_fileName){
		this.getAttribute(FILE_NAME ).setValue( pm_fileName );
	}
 
	public java.lang.String getFileNameValue(){
		return (java.lang.String) this.getAttribute ( FILE_NAME).getValue()  ;
	}
 
	public Attribute getFileName(){
		return this.getAttribute ( FILE_NAME)  ;
	}

	// ********Start of Child DataSets getter methods******** 
	// *******End of Child DataSets getter methods******** 
}