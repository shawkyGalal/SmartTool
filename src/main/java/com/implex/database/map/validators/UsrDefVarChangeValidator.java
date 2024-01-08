package com.implex.database.map.validators;
import com.implex.database.PersistantObject;
import com.implex.database.ValidationResult;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.validators.auto._UsrDefVarChangeValidator;

public class UsrDefVarChangeValidator extends _UsrDefVarChangeValidator 
{ 
	 public UsrDefVarChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key) 
	{
		super(pm_secUserData, pm_po, pm_key); 
	}
	public ValidationResult validateBenCmpId( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateBenVar( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateBenVarField( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateBenVarDesc( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateBenVarDesc_( Object newValue)
{
	// TODO Return Your ValidationResult (Status + Message) ... 
	return null;
}
	public ValidationResult validateModifiedDt( Object newValue)
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
	@Override
	public ValidationResult validateValidity(Object newValue) {
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
	public ValidationResult validateSystemGenerated(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ValidationResult validateUdvCode(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ValidationResult validateUdvVar(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ValidationResult validateUdvVarDesc(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ValidationResult validateUdvVarDesc_(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ValidationResult validateUdvVarField(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ValidationResult validateUdvgCode(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	} 
} 
