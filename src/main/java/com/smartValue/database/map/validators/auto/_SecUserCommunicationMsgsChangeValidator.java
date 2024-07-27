package com.smartValue.database.map.validators.auto;

import com.smartValue.database.AttributeChangeValidator;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.ValidationResult;
import com.smartValue.database.map.SecUserCommunicationMsgs;
import com.smartValue.database.map.SecUsrDta;

public abstract class _SecUserCommunicationMsgsChangeValidator extends AttributeChangeValidator {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */

	protected SecUsrDta secUser = null ; 
	protected PersistantObject po = null; 
	protected String key = null; 

	public _SecUserCommunicationMsgsChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key){
		 this.secUser = pm_secUserData ;
		 this.po = pm_po ; 
		 this.key = pm_key;
	}
	@Override
	public ValidationResult validate(Object newValue) {
		 ValidationResult result = new ValidationResult();
		 if (this.key.equals(SecUserCommunicationMsgs.FROM_USER)) 
		 {
			 result  = validateFromUser( newValue);
		 }
		 else if (this.key.equals(SecUserCommunicationMsgs.TO_USERS)) 
		 {
			 result  = validateToUsers( newValue);
		 }
		 else if (this.key.equals(SecUserCommunicationMsgs.MSG_HEADER)) 
		 {
			 result  = validateMsgHeader( newValue);
		 }
		 else if (this.key.equals(SecUserCommunicationMsgs.MODIFIED_DT)) 
		 {
			 result  = validateModifiedDt( newValue);
		 }
		 else if (this.key.equals(SecUserCommunicationMsgs.MSG_READ)) 
		 {
			 result  = validateMsgRead( newValue);
		 }
		 else if (this.key.equals(SecUserCommunicationMsgs.MSG_BODY)) 
		 {
			 result  = validateMsgBody( newValue);
		 }
		 return result ;
 } 
	public abstract ValidationResult validateFromUser( Object newValue);
	public abstract ValidationResult validateToUsers( Object newValue);
	public abstract ValidationResult validateMsgHeader( Object newValue);
	public abstract ValidationResult validateModifiedDt( Object newValue);
	public abstract ValidationResult validateMsgRead( Object newValue);
	public abstract ValidationResult validateMsgBody( Object newValue);
 } 
