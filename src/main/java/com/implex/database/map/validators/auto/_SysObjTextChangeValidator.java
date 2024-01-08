package com.implex.database.map.validators.auto;

import com.implex.database.AttributeChangeValidator;
import com.implex.database.PersistantObject;
import com.implex.database.ValidationResult;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.SysObjText;

public abstract class _SysObjTextChangeValidator extends AttributeChangeValidator {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */

	protected SecUsrDta secUser = null ; 
	protected PersistantObject po = null; 
	protected String key = null; 

	public _SysObjTextChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key){
		 this.secUser = pm_secUserData ;
		 this.po = pm_po ; 
		 this.key = pm_key;
	}
	@Override
	public ValidationResult validate(Object newValue) {
		 ValidationResult result = new ValidationResult();
		 if (this.key.equals(SysObjText.FRM_NAME)) 
		 {
			 result  = validateFrmName( newValue);
		 }
		 else if (this.key.equals(SysObjText.FRM_OBJ_NAME)) 
		 {
			 result  = validateFrmObjName( newValue);
		 }
		 else if (this.key.equals(SysObjText.FRM_OBJ_LNG)) 
		 {
			 result  = validateFrmObjLng( newValue);
		 }
		 else if (this.key.equals(SysObjText.FRM_OBJ_TXT)) 
		 {
			 result  = validateFrmObjTxt( newValue);
		 }
		 else if (this.key.equals(SysObjText.FRM_OBJ_FONT)) 
		 {
			 result  = validateFrmObjFont( newValue);
		 }
		 else if (this.key.equals(SysObjText.FRM_OBJ_FONT_SIZE)) 
		 {
			 result  = validateFrmObjFontSize( newValue);
		 }
		 else if (this.key.equals(SysObjText.FRM_OBJ_FONT_BOLD)) 
		 {
			 result  = validateFrmObjFontBold( newValue);
		 }
		 else if (this.key.equals(SysObjText.FRM_OBJ_FONT_COLOR)) 
		 {
			 result  = validateFrmObjFontColor( newValue);
		 }
		 else if (this.key.equals(SysObjText.FRM_OBJ_FONT_BCOLOR)) 
		 {
			 result  = validateFrmObjFontBcolor( newValue);
		 }
		 else if (this.key.equals(SysObjText.FRM_STYLE)) 
		 {
			 result  = validateFrmStyle( newValue);
		 }
		 else if (this.key.equals(SysObjText.FRM_COMMON_OBJ)) 
		 {
			 result  = validateFrmCommonObj( newValue);
		 }
		 else if (this.key.equals(SysObjText.SECURITY_QUERY)) 
		 {
			 result  = validateSecurityQuery( newValue);
		 }
		 else if (this.key.equals(SysObjText.DEFAULT_ACCESS_TYPE)) 
		 {
			 result  = validateDefaultAccessType( newValue);
		 }
		 else if (this.key.equals(SysObjText.ID)) 
		 {
			 result  = validateId( newValue);
		 }
		 else if (this.key.equals(SysObjText.PREPARED_BY)) 
		 {
			 result  = validatePreparedBy( newValue);
		 }
		 else if (this.key.equals(SysObjText.PREPARED_DT)) 
		 {
			 result  = validatePreparedDt( newValue);
		 }
		 else if (this.key.equals(SysObjText.MODIFIED_BY)) 
		 {
			 result  = validateModifiedBy( newValue);
		 }
		 else if (this.key.equals(SysObjText.MODIFIED_DT)) 
		 {
			 result  = validateModifiedDt( newValue);
		 }
		 else if (this.key.equals(SysObjText.RENDERED)) 
		 {
			 result  = validateRendered( newValue);
		 }
		 else if (this.key.equals(SysObjText.DISABLED)) 
		 {
			 result  = validateDisabled( newValue);
		 }
		 else if (this.key.equals(SysObjText.RENDER_PROTECTION_TYPE)) 
		 {
			 result  = validateRenderProtectionType( newValue);
		 }
		 else if (this.key.equals(SysObjText.DISABLE_PROTECTION_TYPE)) 
		 {
			 result  = validateDisableProtectionType( newValue);
		 }
		 else if (this.key.equals(SysObjText.USR_DEF_VAR_ID_FOR_RENDER)) 
		 {
			 result  = validateUsrDefVarIdForRender( newValue);
		 }
		 else if (this.key.equals(SysObjText.USR_DEF_VAR_ID_FOR_DISABLED)) 
		 {
			 result  = validateUsrDefVarIdForDisabled( newValue);
		 }
		 return result ;
 } 
	public abstract ValidationResult validateFrmName( Object newValue);
	public abstract ValidationResult validateFrmObjName( Object newValue);
	public abstract ValidationResult validateFrmObjLng( Object newValue);
	public abstract ValidationResult validateFrmObjTxt( Object newValue);
	public abstract ValidationResult validateFrmObjFont( Object newValue);
	public abstract ValidationResult validateFrmObjFontSize( Object newValue);
	public abstract ValidationResult validateFrmObjFontBold( Object newValue);
	public abstract ValidationResult validateFrmObjFontColor( Object newValue);
	public abstract ValidationResult validateFrmObjFontBcolor( Object newValue);
	public abstract ValidationResult validateFrmStyle( Object newValue);
	public abstract ValidationResult validateFrmCommonObj( Object newValue);
	public abstract ValidationResult validateSecurityQuery( Object newValue);
	public abstract ValidationResult validateDefaultAccessType( Object newValue);
	public abstract ValidationResult validateId( Object newValue);
	public abstract ValidationResult validatePreparedBy( Object newValue);
	public abstract ValidationResult validatePreparedDt( Object newValue);
	public abstract ValidationResult validateModifiedBy( Object newValue);
	public abstract ValidationResult validateModifiedDt( Object newValue);
	public abstract ValidationResult validateRendered( Object newValue);
	public abstract ValidationResult validateDisabled( Object newValue);
	public abstract ValidationResult validateRenderProtectionType( Object newValue);
	public abstract ValidationResult validateDisableProtectionType( Object newValue);
	public abstract ValidationResult validateUsrDefVarIdForRender( Object newValue);
	public abstract ValidationResult validateUsrDefVarIdForDisabled( Object newValue);
 } 
