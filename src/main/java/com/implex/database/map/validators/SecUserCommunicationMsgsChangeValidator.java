package com.implex.database.map.validators;
import com.implex.database.PersistantObject;
import com.implex.database.ValidationResult;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.validators.auto._SecUserCommunicationMsgsChangeValidator;

public class SecUserCommunicationMsgsChangeValidator extends _SecUserCommunicationMsgsChangeValidator 
{ 
	 public SecUserCommunicationMsgsChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key) 
	{
		super(pm_secUserData, pm_po, pm_key); 
	}
	public ValidationResult validateFromUser( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateToUsers( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateMsgHeader( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateMsgBody( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateModifiedDt( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateMsgRead( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
} 
} 
