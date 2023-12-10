package com.implex.database.map.validators.auto;

import com.implex.database.AttributeChangeValidator;
import com.implex.database.PersistantObject;
import com.implex.database.ValidationResult;
import com.implex.database.map.ColumnChangeNotifiers;
import com.implex.database.map.SecUsrDta;

public abstract class _ColumnChangeNotifiersChangeValidator extends AttributeChangeValidator {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */

	protected SecUsrDta secUser = null ; 
	protected PersistantObject po = null; 
	protected String key = null; 

	public _ColumnChangeNotifiersChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key){
		 this.secUser = pm_secUserData ;
		 this.po = pm_po ; 
		 this.key = pm_key;
	}
	@Override
	public ValidationResult validate(Object newValue) {
		 ValidationResult result = new ValidationResult();
		 if (this.key.equals(ColumnChangeNotifiers.TABLE_OWNER)) 
		 {
			 result  = validateTableOwner( newValue);
		 }
		 else if (this.key.equals(ColumnChangeNotifiers.TABLE_NAME)) 
		 {
			 result  = validateTableName( newValue);
		 }
		 else if (this.key.equals(ColumnChangeNotifiers.COLUMN_NAME)) 
		 {
			 result  = validateColumnName( newValue);
		 }
		 else if (this.key.equals(ColumnChangeNotifiers.CHANGE_NOTIFY_IMPL_ID)) 
		 {
			 result  = validateChangeNotifyImplId( newValue);
		 }
		 return result ;
 } 
	public abstract ValidationResult validateTableOwner( Object newValue);
	public abstract ValidationResult validateTableName( Object newValue);
	public abstract ValidationResult validateColumnName( Object newValue);
	public abstract ValidationResult validateChangeNotifyImplId( Object newValue);
 } 