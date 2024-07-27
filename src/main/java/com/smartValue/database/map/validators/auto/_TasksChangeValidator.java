package com.smartValue.database.map.validators.auto;

import com.smartValue.database.AttributeChangeValidator;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.ValidationResult;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.Tasks;

public abstract class _TasksChangeValidator extends AttributeChangeValidator {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */

	protected SecUsrDta secUser = null ; 
	protected PersistantObject po = null; 
	protected String key = null; 

	public _TasksChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key){
		 this.secUser = pm_secUserData ;
		 this.po = pm_po ; 
		 this.key = pm_key;
	}
	@Override
	public ValidationResult validate(Object newValue) {
		 ValidationResult result = new ValidationResult();
		 if (this.key.equals(Tasks.TASK_ID)) 
		 {
			 result  = validateTaskId( newValue);
		 }
		 else if (this.key.equals(Tasks.PARENT_TSSK_ID)) 
		 {
			 result  = validateParentTsskId( newValue);
		 }
		 else if (this.key.equals(Tasks.BREF_DESC)) 
		 {
			 result  = validateBrefDesc( newValue);
		 }
		 else if (this.key.equals(Tasks.DESCRIPTION)) 
		 {
			 result  = validateDescription( newValue);
		 }
		 else if (this.key.equals(Tasks.ASSIGNED_TO)) 
		 {
			 result  = validateAssignedTo( newValue);
		 }
		 else if (this.key.equals(Tasks.ASSIGNED_BY)) 
		 {
			 result  = validateAssignedBy( newValue);
		 }
		 else if (this.key.equals(Tasks.ASSIGN_DATE)) 
		 {
			 result  = validateAssignDate( newValue);
		 }
		 else if (this.key.equals(Tasks.ESTIMATED_START_DATE)) 
		 {
			 result  = validateEstimatedStartDate( newValue);
		 }
		 else if (this.key.equals(Tasks.ACTUAL_START_DATE)) 
		 {
			 result  = validateActualStartDate( newValue);
		 }
		 else if (this.key.equals(Tasks.URGENT)) 
		 {
			 result  = validateUrgent( newValue);
		 }
		 else if (this.key.equals(Tasks.PERIORITY)) 
		 {
			 result  = validatePeriority( newValue);
		 }
		 else if (this.key.equals(Tasks.ESTIMATED_DURATION_IN_HOUR)) 
		 {
			 result  = validateEstimatedDurationInHour( newValue);
		 }
		 else if (this.key.equals(Tasks.TASK_STATE)) 
		 {
			 result  = validateTaskState( newValue);
		 }
		 else if (this.key.equals(Tasks.PERC_COMPLETED)) 
		 {
			 result  = validatePercCompleted( newValue);
		 }
		 else if (this.key.equals(Tasks.PREPARED_BY)) 
		 {
			 result  = validatePreparedBy( newValue);
		 }
		 else if (this.key.equals(Tasks.PREPARED_DT)) 
		 {
			 result  = validatePreparedDt( newValue);
		 }
		 else if (this.key.equals(Tasks.MODIFIED_BY)) 
		 {
			 result  = validateModifiedBy( newValue);
		 }
		 else if (this.key.equals(Tasks.MODIFIED_DT)) 
		 {
			 result  = validateModifiedDt( newValue);
		 }
		 else if (this.key.equals(Tasks.TEST_ASSIGNED_TO)) 
		 {
			 result  = validateTestAssignedTo( newValue);
		 }
		 return result ;
 } 
	public abstract ValidationResult validateTaskId( Object newValue);
	public abstract ValidationResult validateParentTsskId( Object newValue);
	public abstract ValidationResult validateBrefDesc( Object newValue);
	public abstract ValidationResult validateDescription( Object newValue);
	public abstract ValidationResult validateAssignedTo( Object newValue);
	public abstract ValidationResult validateAssignedBy( Object newValue);
	public abstract ValidationResult validateAssignDate( Object newValue);
	public abstract ValidationResult validateEstimatedStartDate( Object newValue);
	public abstract ValidationResult validateActualStartDate( Object newValue);
	public abstract ValidationResult validateUrgent( Object newValue);
	public abstract ValidationResult validatePeriority( Object newValue);
	public abstract ValidationResult validateEstimatedDurationInHour( Object newValue);
	public abstract ValidationResult validateTaskState( Object newValue);
	public abstract ValidationResult validatePercCompleted( Object newValue);
	public abstract ValidationResult validatePreparedBy( Object newValue);
	public abstract ValidationResult validatePreparedDt( Object newValue);
	public abstract ValidationResult validateModifiedBy( Object newValue);
	public abstract ValidationResult validateModifiedDt( Object newValue);
	public abstract ValidationResult validateTestAssignedTo( Object newValue);
 } 