package com.implex.database.map.validators;
import com.implex.database.PersistantObject;
import com.implex.database.ValidationResult;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.validators.auto._TableUserViewChangeValidator;

public class TableUserViewChangeValidator extends _TableUserViewChangeValidator 
{ 
	 public TableUserViewChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key) 
	{
		super(pm_secUserData, pm_po, pm_key); 
	}
	public ValidationResult validateTabelName( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateTableOwner( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateUserId( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateViewWhereCondition( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateModifiedDt( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
} 
} 
