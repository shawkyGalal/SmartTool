package com.implex.database.map.validators.auto;

import com.implex.database.AttributeChangeValidator;
import com.implex.database.PersistantObject;
import com.implex.database.ValidationResult;
import com.implex.database.map.SavedSearch;
import com.implex.database.map.SecUsrDta;

public abstract class _SavedSearchChangeValidator extends AttributeChangeValidator {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */

	protected SecUsrDta secUser = null ; 
	protected PersistantObject po = null; 
	protected String key = null; 

	public _SavedSearchChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key){
		 this.secUser = pm_secUserData ;
		 this.po = pm_po ; 
		 this.key = pm_key;
	}
	@Override
	public ValidationResult validate(Object newValue) {
		 ValidationResult result = new ValidationResult();
		 if (this.key.equals(SavedSearch.SEARCH_NAME)) 
		 {
			 result  = validateSearchName( newValue);
		 }
		 else if (this.key.equals(SavedSearch.DESCRIPTION)) 
		 {
			 result  = validateDescription( newValue);
		 }
		 else if (this.key.equals(SavedSearch.OWNER)) 
		 {
			 result  = validateOwner( newValue);
		 }
		 else if (this.key.equals(SavedSearch.PREPARED_BY)) 
		 {
			 result  = validatePreparedBy( newValue);
		 }
		 else if (this.key.equals(SavedSearch.PREPARED_DT)) 
		 {
			 result  = validatePreparedDt( newValue);
		 }
		 else if (this.key.equals(SavedSearch.MODIFIED_BY)) 
		 {
			 result  = validateModifiedBy( newValue);
		 }
		 else if (this.key.equals(SavedSearch.MODIFIED_DT)) 
		 {
			 result  = validateModifiedDt( newValue);
		 }
		 else if (this.key.equals(SavedSearch.TABLE_NAME)) 
		 {
			 result  = validateTableName( newValue);
		 }
		 return result ;
 } 
	public abstract ValidationResult validateSearchName( Object newValue);
	public abstract ValidationResult validateDescription( Object newValue);
	public abstract ValidationResult validateOwner( Object newValue);
	public abstract ValidationResult validatePreparedBy( Object newValue);
	public abstract ValidationResult validatePreparedDt( Object newValue);
	public abstract ValidationResult validateModifiedBy( Object newValue);
	public abstract ValidationResult validateModifiedDt( Object newValue);
	public abstract ValidationResult validateTableName( Object newValue);
 } 