package com.smartValue.database.map.validators.auto;

import com.smartValue.database.AttributeChangeValidator;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.ValidationResult;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.SysParams;

public abstract class _SysParamsChangeValidator extends AttributeChangeValidator {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */

	protected SecUsrDta secUser = null ; 
	protected PersistantObject po = null; 
	protected String key = null; 

	public _SysParamsChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key){
		 this.secUser = pm_secUserData ;
		 this.po = pm_po ; 
		 this.key = pm_key;
	}
	@Override
	public ValidationResult validate(Object newValue) {
		 ValidationResult result = new ValidationResult();
		 if (this.key.equals(SysParams.SYS_CODE)) 
		 {
			 result  = validateSysCode( newValue);
		 }
		 if (this.key.equals(SysParams.MOD_CODE)) 
		 {
			 result  = validateModCode( newValue);
		 }
		 if (this.key.equals(SysParams.USR_ID)) 
		 {
			 result  = validateUsrId( newValue);
		 }
		 if (this.key.equals(SysParams.ID)) 
		 {
			 result  = validateId( newValue);
		 }
		 if (this.key.equals(SysParams.TYP)) 
		 {
			 result  = validateTyp( newValue);
		 }
		 if (this.key.equals(SysParams.SECT)) 
		 {
			 result  = validateSect( newValue);
		 }
		 if (this.key.equals(SysParams.E_NAME)) 
		 {
			 result  = validateEName( newValue);
		 }
		 if (this.key.equals(SysParams.A_NAME)) 
		 {
			 result  = validateAName( newValue);
		 }
		 if (this.key.equals(SysParams.VAL)) 
		 {
			 result  = validateVal( newValue);
		 }
		 if (this.key.equals(SysParams.E_DSC)) 
		 {
			 result  = validateEDsc( newValue);
		 }
		 if (this.key.equals(SysParams.A_DSC)) 
		 {
			 result  = validateADsc( newValue);
		 }
		 if (this.key.equals(SysParams.MODIFY_SEQ)) 
		 {
			 result  = validateModifySeq( newValue);
		 }
		 if (this.key.equals(SysParams.UPDATE_STATUS)) 
		 {
			 result  = validateUpdateStatus( newValue);
		 }
		 if (this.key.equals(SysParams.MODIFIED_DT)) 
		 {
			 result  = validateModifiedDt( newValue);
		 }
		 return result ;
 } 
	public abstract ValidationResult validateSysCode( Object newValue);
	public abstract ValidationResult validateModCode( Object newValue);
	public abstract ValidationResult validateUsrId( Object newValue);
	public abstract ValidationResult validateId( Object newValue);
	public abstract ValidationResult validateTyp( Object newValue);
	public abstract ValidationResult validateSect( Object newValue);
	public abstract ValidationResult validateEName( Object newValue);
	public abstract ValidationResult validateAName( Object newValue);
	public abstract ValidationResult validateVal( Object newValue);
	public abstract ValidationResult validateEDsc( Object newValue);
	public abstract ValidationResult validateADsc( Object newValue);
	public abstract ValidationResult validateModifySeq( Object newValue);
	public abstract ValidationResult validateUpdateStatus( Object newValue);
	public abstract ValidationResult validateModifiedDt( Object newValue);
 } 