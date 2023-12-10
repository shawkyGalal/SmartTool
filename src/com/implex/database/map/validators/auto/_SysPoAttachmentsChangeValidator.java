package com.implex.database.map.validators.auto;

import com.implex.database.AttributeChangeValidator;
import com.implex.database.PersistantObject;
import com.implex.database.ValidationResult;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.SysPoAttachments;

public abstract class _SysPoAttachmentsChangeValidator extends AttributeChangeValidator {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */

	protected SecUsrDta secUser = null ; 
	protected PersistantObject po = null; 
	protected String key = null; 

	public _SysPoAttachmentsChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key){
		 this.secUser = pm_secUserData ;
		 this.po = pm_po ; 
		 this.key = pm_key;
	}
	@Override
	public ValidationResult validate(Object newValue) {
		 ValidationResult result = new ValidationResult();
		 if (this.key.equals(SysPoAttachments.PO_KEY)) 
		 {
			 result  = validatePoKey( newValue);
		 }
		 else if (this.key.equals(SysPoAttachments.ATTACHED_FILE)) 
		 {
			 result  = validateAttachedFile( newValue);
		 }
		 else if (this.key.equals(SysPoAttachments.ATTACMENT_GROUP_ID)) 
		 {
			 result  = validateAttacmentGroupId( newValue);
		 }
		 else if (this.key.equals(SysPoAttachments.PREPARED_BY)) 
		 {
			 result  = validatePreparedBy( newValue);
		 }
		 else if (this.key.equals(SysPoAttachments.PREPARED_DT)) 
		 {
			 result  = validatePreparedDt( newValue);
		 }
		 else if (this.key.equals(SysPoAttachments.MODIFIED_BY)) 
		 {
			 result  = validateModifiedBy( newValue);
		 }
		 else if (this.key.equals(SysPoAttachments.MODIFIED_DT)) 
		 {
			 result  = validateModifiedDt( newValue);
		 }
		 else if (this.key.equals(SysPoAttachments.FILE_NAME)) 
		 {
			 result  = validateFileName( newValue);
		 }
		 else if (this.key.equals(SysPoAttachments.TABLE_OWNER)) 
		 {
			 result  = validateTableOwner( newValue);
		 }
		 else if (this.key.equals(SysPoAttachments.TABLE_NAME)) 
		 {
			 result  = validateTableName( newValue);
		 }
		 return result ;
 } 
	public abstract ValidationResult validatePoKey( Object newValue);
	public abstract ValidationResult validateAttachedFile( Object newValue);
	public abstract ValidationResult validateAttacmentGroupId( Object newValue);
	public abstract ValidationResult validatePreparedBy( Object newValue);
	public abstract ValidationResult validatePreparedDt( Object newValue);
	public abstract ValidationResult validateModifiedBy( Object newValue);
	public abstract ValidationResult validateModifiedDt( Object newValue);
	public abstract ValidationResult validateFileName( Object newValue);
	public abstract ValidationResult validateTableOwner( Object newValue);
	public abstract ValidationResult validateTableName( Object newValue);
 } 