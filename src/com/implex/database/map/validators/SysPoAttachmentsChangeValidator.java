package com.implex.database.map.validators;
import com.implex.database.PersistantObject;
import com.implex.database.ValidationResult;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.validators.auto._SysPoAttachmentsChangeValidator;

public class SysPoAttachmentsChangeValidator extends _SysPoAttachmentsChangeValidator 
{ 
	 public SysPoAttachmentsChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key) 
	{
		super(pm_secUserData, pm_po, pm_key); 
	}
	public ValidationResult validatePoKey( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateAttachedFile( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateAttacmentGroupId( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validatePreparedBy( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validatePreparedDt( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateModifiedBy( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateModifiedDt( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateFileName( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateTableOwner( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateTableName( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
} 
} 