package com.smartValue.database.map.validators.auto;

import com.smartValue.database.AttributeChangeValidator;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.ValidationResult;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.TableUserView;

public abstract class _TableUserViewChangeValidator extends AttributeChangeValidator {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */

	protected SecUsrDta secUser = null ; 
	protected PersistantObject po = null; 
	protected String key = null; 

	public _TableUserViewChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key){
		 this.secUser = pm_secUserData ;
		 this.po = pm_po ; 
		 this.key = pm_key;
	}
	@Override
	public ValidationResult validate(Object newValue) {
		 ValidationResult result = new ValidationResult();
		 if (this.key.equals(TableUserView.TABEL_NAME)) 
		 {
			 result  = validateTabelName( newValue);
		 }
		 else if (this.key.equals(TableUserView.TABLE_OWNER)) 
		 {
			 result  = validateTableOwner( newValue);
		 }
		 else if (this.key.equals(TableUserView.USER_ID)) 
		 {
			 result  = validateUserId( newValue);
		 }
		 else if (this.key.equals(TableUserView.VIEW_WHERE_CONDITION)) 
		 {
			 result  = validateViewWhereCondition( newValue);
		 }
		 else if (this.key.equals(TableUserView.MODIFIED_DT)) 
		 {
			 result  = validateModifiedDt( newValue);
		 }
		 return result ;
 } 
	public abstract ValidationResult validateTabelName( Object newValue);
	public abstract ValidationResult validateTableOwner( Object newValue);
	public abstract ValidationResult validateUserId( Object newValue);
	public abstract ValidationResult validateViewWhereCondition( Object newValue);
	public abstract ValidationResult validateModifiedDt( Object newValue);
 } 
