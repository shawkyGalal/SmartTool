package com.smartValue.database.map.validators;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.ValidationResult;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.validators.auto._SqlBoundVarsChangeValidator;

public class SqlBoundVarsChangeValidator extends _SqlBoundVarsChangeValidator 
{ 
	 public SqlBoundVarsChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key) 
	{
		super(pm_secUserData, pm_po, pm_key); 
	}
	public ValidationResult validateOwner( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateTitle( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateBoundVarName( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateActive( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateDefaultVal( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateCreationDate( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateSn( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateDataType( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateQueryForList( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
} 
} 
