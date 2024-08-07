package com.smartValue.database.map.validators.auto;

import com.smartValue.database.AttributeChangeValidator;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.ValidationResult;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.SysTablesSecurityControler;

public abstract class _SysTablesSecurityControlerChangeValidator extends AttributeChangeValidator {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */

	protected SecUsrDta secUser = null ; 
	protected PersistantObject po = null; 
	protected String key = null; 

	public _SysTablesSecurityControlerChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key){
		 this.secUser = pm_secUserData ;
		 this.po = pm_po ; 
		 this.key = pm_key;
	}
	@Override
	public ValidationResult validate(Object newValue) {
		 ValidationResult result = new ValidationResult();
		 if (this.key.equals(SysTablesSecurityControler.TABLE_NAME)) 
		 {
			 result  = validateTableName( newValue);
		 }
		 else if (this.key.equals(SysTablesSecurityControler.OPEARTION)) 
		 {
			 result  = validateOpeartion( newValue);
		 }
		 else if (this.key.equals(SysTablesSecurityControler.EXECLUDED_USERS)) 
		 {
			 result  = validateExecludedUsers( newValue);
		 }
		 return result ;
 } 
	public abstract ValidationResult validateTableName( Object newValue);
	public abstract ValidationResult validateOpeartion( Object newValue);
	public abstract ValidationResult validateExecludedUsers( Object newValue);
 } 