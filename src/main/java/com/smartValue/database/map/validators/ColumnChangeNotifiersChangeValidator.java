package com.smartValue.database.map.validators;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.ValidationResult;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.validators.auto._ColumnChangeNotifiersChangeValidator;

public class ColumnChangeNotifiersChangeValidator extends _ColumnChangeNotifiersChangeValidator 
{ 
	 public ColumnChangeNotifiersChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key) 
	{
		super(pm_secUserData, pm_po, pm_key); 
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
	public ValidationResult validateColumnName( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateChangeNotifyImplId( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
} 
} 