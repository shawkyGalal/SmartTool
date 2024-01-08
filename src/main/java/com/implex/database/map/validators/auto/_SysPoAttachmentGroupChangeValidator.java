package com.implex.database.map.validators.auto;

import com.implex.database.AttributeChangeValidator;
import com.implex.database.PersistantObject;
import com.implex.database.ValidationResult;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.SysPoAttachmentGroup;

public abstract class _SysPoAttachmentGroupChangeValidator extends AttributeChangeValidator {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */

	protected SecUsrDta secUser = null ; 
	protected PersistantObject po = null; 
	protected String key = null; 

	public _SysPoAttachmentGroupChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key){
		 this.secUser = pm_secUserData ;
		 this.po = pm_po ; 
		 this.key = pm_key;
	}
	@Override
	public ValidationResult validate(Object newValue) {
		 ValidationResult result = new ValidationResult();
		 if (this.key.equals(SysPoAttachmentGroup.TABLE_OWNER)) 
		 {
			 result  = validateTableOwner( newValue);
		 }
		 else if (this.key.equals(SysPoAttachmentGroup.TABLE_NAME)) 
		 {
			 result  = validateTableName( newValue);
		 }
		 else if (this.key.equals(SysPoAttachmentGroup.GROUP_ID)) 
		 {
			 result  = validateGroupId( newValue);
		 }
		 else if (this.key.equals(SysPoAttachmentGroup.PO_KEY)) 
		 {
			 result  = validatePoKey( newValue);
		 }
		 else if (this.key.equals(SysPoAttachmentGroup.GROUP_DESC)) 
		 {
			 result  = validateGroupDesc( newValue);
		 }
		 else if (this.key.equals(SysPoAttachmentGroup.GROUP_DESC_)) 
		 {
			 result  = validateGroupDesc_( newValue);
		 }
		 else if (this.key.equals(SysPoAttachmentGroup.PREPARED_BY)) 
		 {
			 result  = validatePreparedBy( newValue);
		 }
		 else if (this.key.equals(SysPoAttachmentGroup.PREPARED_DT)) 
		 {
			 result  = validatePreparedDt( newValue);
		 }
		 else if (this.key.equals(SysPoAttachmentGroup.MODIFIED_BY)) 
		 {
			 result  = validateModifiedBy( newValue);
		 }
		 else if (this.key.equals(SysPoAttachmentGroup.MODIFIED_DT)) 
		 {
			 result  = validateModifiedDt( newValue);
		 }
		 return result ;
 } 
	public abstract ValidationResult validateTableOwner( Object newValue);
	public abstract ValidationResult validateTableName( Object newValue);
	public abstract ValidationResult validateGroupId( Object newValue);
	public abstract ValidationResult validatePoKey( Object newValue);
	public abstract ValidationResult validateGroupDesc( Object newValue);
	public abstract ValidationResult validateGroupDesc_( Object newValue);
	public abstract ValidationResult validatePreparedBy( Object newValue);
	public abstract ValidationResult validatePreparedDt( Object newValue);
	public abstract ValidationResult validateModifiedBy( Object newValue);
	public abstract ValidationResult validateModifiedDt( Object newValue);
 } 
