package com.implex.database.map.validators;
import com.implex.database.PersistantObject;
import com.implex.database.ValidationResult;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.validators.auto._SavedSearchChangeValidator;

public class SavedSearchChangeValidator extends _SavedSearchChangeValidator 
{ 
	 public SavedSearchChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key) 
	{
		super(pm_secUserData, pm_po, pm_key); 
	}
	public ValidationResult validateId( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateSearchName( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateDescription( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateOwner( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateModifiedDt( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
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
	public ValidationResult validateTableName(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	} 
} 