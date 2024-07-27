package com.smartValue.database.map.validators.auto;

import com.smartValue.database.AttributeChangeValidator;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.ValidationResult;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.SysFrm;

public abstract class _SysFrmChangeValidator extends AttributeChangeValidator {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */

	protected SecUsrDta secUser = null ; 
	protected PersistantObject po = null; 
	protected String key = null; 

	public _SysFrmChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key){
		 this.secUser = pm_secUserData ;
		 this.po = pm_po ; 
		 this.key = pm_key;
	}
	@Override
	public ValidationResult validate(Object newValue) {
		 ValidationResult result = new ValidationResult();
		 if (this.key.equals(SysFrm.FRM_NAME)) 
		 {
			 result  = validateFrmName( newValue);
		 }
		 if (this.key.equals(SysFrm.FRM_LNG_ID)) 
		 {
			 result  = validateFrmLngId( newValue);
		 }
		 if (this.key.equals(SysFrm.FRM_ALIAS)) 
		 {
			 result  = validateFrmAlias( newValue);
		 }
		 if (this.key.equals(SysFrm.FRM_TITLE)) 
		 {
			 result  = validateFrmTitle( newValue);
		 }
		 if (this.key.equals(SysFrm.FRM_HELP)) 
		 {
			 result  = validateFrmHelp( newValue);
		 }
		 if (this.key.equals(SysFrm.FRM_VP)) 
		 {
			 result  = validateFrmVp( newValue);
		 }
		 if (this.key.equals(SysFrm.MODIFIED_DT)) 
		 {
			 result  = validateModifiedDt( newValue);
		 }
		 return result ;
 } 
	public abstract ValidationResult validateFrmName( Object newValue);
	public abstract ValidationResult validateFrmLngId( Object newValue);
	public abstract ValidationResult validateFrmAlias( Object newValue);
	public abstract ValidationResult validateFrmTitle( Object newValue);
	public abstract ValidationResult validateFrmHelp( Object newValue);
	public abstract ValidationResult validateFrmVp( Object newValue);
	public abstract ValidationResult validateModifiedDt( Object newValue);
 } 
