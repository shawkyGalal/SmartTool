package com.implex.database.map.validators.auto;

import com.implex.database.AttributeChangeValidator;
import com.implex.database.PersistantObject;
import com.implex.database.ValidationResult;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.SysMsg;

public abstract class _SysMsgChangeValidator extends AttributeChangeValidator {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */

	protected SecUsrDta secUser = null ; 
	protected PersistantObject po = null; 
	protected String key = null; 

	public _SysMsgChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key){
		 this.secUser = pm_secUserData ;
		 this.po = pm_po ; 
		 this.key = pm_key;
	}
	@Override
	public ValidationResult validate(Object newValue) {
		 ValidationResult result = new ValidationResult();
		 if (this.key.equals(SysMsg.MSG_ID)) 
		 {
			 result  = validateMsgId( newValue);
		 }
		 else if (this.key.equals(SysMsg.MSG_LNG_ID)) 
		 {
			 result  = validateMsgLngId( newValue);
		 }
		 else if (this.key.equals(SysMsg.MSG_TOOLTIP)) 
		 {
			 result  = validateMsgTooltip( newValue);
		 }
		 else if (this.key.equals(SysMsg.MSG_TYPE)) 
		 {
			 result  = validateMsgType( newValue);
		 }
		 else if (this.key.equals(SysMsg.MSG_IMG_ID)) 
		 {
			 result  = validateMsgImgId( newValue);
		 }
		 else if (this.key.equals(SysMsg.MSG_TITLE)) 
		 {
			 result  = validateMsgTitle( newValue);
		 }
		 else if (this.key.equals(SysMsg.MSG_DESC)) 
		 {
			 result  = validateMsgDesc( newValue);
		 }
		 else if (this.key.equals(SysMsg.MODIFIED_DT)) 
		 {
			 result  = validateModifiedDt( newValue);
		 }
		 return result ;
 } 
	public abstract ValidationResult validateMsgId( Object newValue);
	public abstract ValidationResult validateMsgLngId( Object newValue);
	public abstract ValidationResult validateMsgTooltip( Object newValue);
	public abstract ValidationResult validateMsgType( Object newValue);
	public abstract ValidationResult validateMsgImgId( Object newValue);
	public abstract ValidationResult validateMsgTitle( Object newValue);
	public abstract ValidationResult validateMsgDesc( Object newValue);
	public abstract ValidationResult validateModifiedDt( Object newValue);
 } 