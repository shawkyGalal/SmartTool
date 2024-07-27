package com.smartValue.database.map.validators.auto;

import com.smartValue.database.AttributeChangeValidator;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.ValidationResult;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.SqlBoundVars;

public abstract class _SqlBoundVarsChangeValidator extends AttributeChangeValidator {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */

	protected SecUsrDta secUser = null ; 
	protected PersistantObject po = null; 
	protected String key = null; 

	public _SqlBoundVarsChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key){
		 this.secUser = pm_secUserData ;
		 this.po = pm_po ; 
		 this.key = pm_key;
	}
	@Override
	public ValidationResult validate(Object newValue) {
		 ValidationResult result = new ValidationResult();
		 if (this.key.equals(SqlBoundVars.OWNER)) 
		 {
			 result  = validateOwner( newValue);
		 }
		 if (this.key.equals(SqlBoundVars.TITLE)) 
		 {
			 result  = validateTitle( newValue);
		 }
		 if (this.key.equals(SqlBoundVars.BOUND_VAR_NAME)) 
		 {
			 result  = validateBoundVarName( newValue);
		 }
		 if (this.key.equals(SqlBoundVars.ACTIVE)) 
		 {
			 result  = validateActive( newValue);
		 }
		 if (this.key.equals(SqlBoundVars.DEFAULT_VAL)) 
		 {
			 result  = validateDefaultVal( newValue);
		 }
		 if (this.key.equals(SqlBoundVars.CREATION_DATE)) 
		 {
			 result  = validateCreationDate( newValue);
		 }
		 if (this.key.equals(SqlBoundVars.SN)) 
		 {
			 result  = validateSn( newValue);
		 }
		 if (this.key.equals(SqlBoundVars.DATA_TYPE)) 
		 {
			 result  = validateDataType( newValue);
		 }
		 if (this.key.equals(SqlBoundVars.QUERY_FOR_LIST)) 
		 {
			 result  = validateQueryForList( newValue);
		 }
		 return result ;
 } 
	public abstract ValidationResult validateOwner( Object newValue);
	public abstract ValidationResult validateTitle( Object newValue);
	public abstract ValidationResult validateBoundVarName( Object newValue);
	public abstract ValidationResult validateActive( Object newValue);
	public abstract ValidationResult validateDefaultVal( Object newValue);
	public abstract ValidationResult validateCreationDate( Object newValue);
	public abstract ValidationResult validateSn( Object newValue);
	public abstract ValidationResult validateDataType( Object newValue);
	public abstract ValidationResult validateQueryForList( Object newValue);
 } 
