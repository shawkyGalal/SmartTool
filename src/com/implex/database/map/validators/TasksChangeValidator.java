package com.implex.database.map.validators;
import com.implex.database.PersistantObject;
import com.implex.database.ValidationResult;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.validators.auto._TasksChangeValidator;

public class TasksChangeValidator extends _TasksChangeValidator 
{ 
	 public TasksChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key) 
	{
		super(pm_secUserData, pm_po, pm_key); 
	}
	public ValidationResult validateTaskId( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateParentTsskId( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateBrefDesc( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateDescription( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateAssignedTo( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateAssignedBy( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateAssignDate( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateEstimatedStartDate( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateActualStartDate( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateUrgent( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validatePeriority( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateEstimatedDurationInHour( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateTaskState( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	@Override
	public ValidationResult validateModifiedDt(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ValidationResult validatePercCompleted(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ValidationResult validateModifiedBy(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ValidationResult validatePreparedBy(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ValidationResult validatePreparedDt(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ValidationResult validateTestAssignedTo(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	} 
} 