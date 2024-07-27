package com.smartValue.database.map.validators.auto;

import com.smartValue.database.AttributeChangeValidator;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.ValidationResult;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.UsrDefVar;

public abstract class _UsrDefVarChangeValidator extends AttributeChangeValidator {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */

	protected SecUsrDta secUser = null ; 
	protected PersistantObject po = null; 
	protected String key = null; 

	public _UsrDefVarChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key){
		 this.secUser = pm_secUserData ;
		 this.po = pm_po ; 
		 this.key = pm_key;
	}
	@Override
	public ValidationResult validate(Object newValue) {
		 ValidationResult result = new ValidationResult();
		 if (this.key.equals(UsrDefVar.UDV_CODE)) 
		 {
			 result  = validateUdvCode( newValue);
		 }
		 else if (this.key.equals(UsrDefVar.UDV_VAR)) 
		 {
			 result  = validateUdvVar( newValue);
		 }
		 else if (this.key.equals(UsrDefVar.UDV_VAR_FIELD)) 
		 {
			 result  = validateUdvVarField( newValue);
		 }
		 else if (this.key.equals(UsrDefVar.UDV_VAR_DESC)) 
		 {
			 result  = validateUdvVarDesc( newValue);
		 }
		 else if (this.key.equals(UsrDefVar.UDV_VAR_DESC_)) 
		 {
			 result  = validateUdvVarDesc_( newValue);
		 }
		 else if (this.key.equals(UsrDefVar.TABLE_OWNER)) 
		 {
			 result  = validateTableOwner( newValue);
		 }
		 else if (this.key.equals(UsrDefVar.TABLE_NAME)) 
		 {
			 result  = validateTableName( newValue);
		 }
		 else if (this.key.equals(UsrDefVar.VALIDITY)) 
		 {
			 result  = validateValidity( newValue);
		 }
		 else if (this.key.equals(UsrDefVar.SYSTEM_GENERATED)) 
		 {
			 result  = validateSystemGenerated( newValue);
		 }
		 else if (this.key.equals(UsrDefVar.PREPARED_BY)) 
		 {
			 result  = validatePreparedBy( newValue);
		 }
		 else if (this.key.equals(UsrDefVar.PREPARED_DT)) 
		 {
			 result  = validatePreparedDt( newValue);
		 }
		 else if (this.key.equals(UsrDefVar.MODIFIED_BY)) 
		 {
			 result  = validateModifiedBy( newValue);
		 }
		 else if (this.key.equals(UsrDefVar.UDVG_CODE)) 
		 {
			 result  = validateUdvgCode( newValue);
		 }
		 else if (this.key.equals(UsrDefVar.MODIFIED_DT)) 
		 {
			 result  = validateModifiedDt( newValue);
		 }
		 return result ;
 } 
	public abstract ValidationResult validateUdvCode( Object newValue);
	public abstract ValidationResult validateUdvVar( Object newValue);
	public abstract ValidationResult validateUdvVarField( Object newValue);
	public abstract ValidationResult validateUdvVarDesc( Object newValue);
	public abstract ValidationResult validateUdvVarDesc_( Object newValue);
	public abstract ValidationResult validateTableOwner( Object newValue);
	public abstract ValidationResult validateTableName( Object newValue);
	public abstract ValidationResult validateValidity( Object newValue);
	public abstract ValidationResult validateSystemGenerated( Object newValue);
	public abstract ValidationResult validatePreparedBy( Object newValue);
	public abstract ValidationResult validatePreparedDt( Object newValue);
	public abstract ValidationResult validateModifiedBy( Object newValue);
	public abstract ValidationResult validateUdvgCode( Object newValue);
	public abstract ValidationResult validateModifiedDt( Object newValue);
 } 