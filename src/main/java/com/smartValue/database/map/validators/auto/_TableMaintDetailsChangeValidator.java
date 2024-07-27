package com.smartValue.database.map.validators.auto;

import com.smartValue.database.AttributeChangeValidator;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.ValidationResult;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.TableMaintDetails;

public abstract class _TableMaintDetailsChangeValidator extends AttributeChangeValidator {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */

	protected SecUsrDta secUser = null ; 
	protected PersistantObject po = null; 
	protected String key = null; 

	public _TableMaintDetailsChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key){
		 this.secUser = pm_secUserData ;
		 this.po = pm_po ; 
		 this.key = pm_key;
	}
	@Override
	public ValidationResult validate(Object newValue) {
		 ValidationResult result = new ValidationResult();
		 if (this.key.equals(TableMaintDetails.COLUMN_INCLUDED)) 
		 {
			 result  = validateColumnIncluded( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.SELECT_LIST_QUERY)) 
		 {
			 result  = validateSelectListQuery( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.JAVA_SCRIPT_VALIDATION)) 
		 {
			 result  = validateJavaScriptValidation( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.HTML_TYPE)) 
		 {
			 result  = validateHtmlType( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.ATTRIBUTE)) 
		 {
			 result  = validateAttribute( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.TAB_INDEX)) 
		 {
			 result  = validateTabIndex( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.DISPLAY_NAME)) 
		 {
			 result  = validateDisplayName( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.COMMENTS)) 
		 {
			 result  = validateComments( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.COLUMN_NAME)) 
		 {
			 result  = validateColumnName( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.OWNER)) 
		 {
			 result  = validateOwner( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.TABLE_NAME)) 
		 {
			 result  = validateTableName( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.INCLUDED_IN_RESULT)) 
		 {
			 result  = validateIncludedInResult( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.DISPLAY_NAME_)) 
		 {
			 result  = validateDisplayName_( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.DEFAULT_ACCESS_TYPE)) 
		 {
			 result  = validateDefaultAccessType( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.DATALENGTH)) 
		 {
			 result  = validateDatalength( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.NULLABLE)) 
		 {
			 result  = validateNullable( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.ENCRYPED)) 
		 {
			 result  = validateEncryped( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.INCLUDED_IN_EDIT)) 
		 {
			 result  = validateIncludedInEdit( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.GET_CURRENTITEM_BYME)) 
		 {
			 result  = validateGetCurrentitemByme( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.RENDERED)) 
		 {
			 result  = validateRendered( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.HAS_INPUTTEXT)) 
		 {
			 result  = validateHasInputtext( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.TOOLTIP_DESC)) 
		 {
			 result  = validateTooltipDesc( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.TOOLTIP_DESC_)) 
		 {
			 result  = validateTooltipDesc_( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.RENDER_PROTECTION_TYPE)) 
		 {
			 result  = validateRenderProtectionType( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.CSS_STYLE)) 
		 {
			 result  = validateCssStyle( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.DEFAULT_VALUE)) 
		 {
			 result  = validateDefaultValue( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.PREPARED_BY)) 
		 {
			 result  = validatePreparedBy( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.PREPARED_DT)) 
		 {
			 result  = validatePreparedDt( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.MODIFIED_BY)) 
		 {
			 result  = validateModifiedBy( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.MODIFIED_DT)) 
		 {
			 result  = validateModifiedDt( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.USR_DEF_VAR_ID_FOR_RENDER)) 
		 {
			 result  = validateUsrDefVarIdForRender( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.USR_DEF_VAR_ID_FOR_DISABLED)) 
		 {
			 result  = validateUsrDefVarIdForDisabled( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.DISABLED)) 
		 {
			 result  = validateDisabled( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.CALENDER_LOCALE)) 
		 {
			 result  = validateCalenderLocale( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.POP_UP)) 
		 {
			 result  = validatePopUp( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.PATTERN)) 
		 {
			 result  = validatePattern( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.SHOW_APPLY_BUTTON)) 
		 {
			 result  = validateShowApplyButton( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.SHOW_TOOL_TIP)) 
		 {
			 result  = validateShowToolTip( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.INPUT_SLIDER_MIN)) 
		 {
			 result  = validateInputSliderMin( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.INPUT_SLIDER_MAX)) 
		 {
			 result  = validateInputSliderMax( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.INPUT_SLIDER_STEP)) 
		 {
			 result  = validateInputSliderStep( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.DATA_TYPE)) 
		 {
			 result  = validateDataType( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.DISABLE_PROTECTION_TYPE)) 
		 {
			 result  = validateDisableProtectionType( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.THEME)) 
		 {
			 result  = validateTheme( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.VIEWMODE)) 
		 {
			 result  = validateViewmode( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.WIDTHRICHEDITOR)) 
		 {
			 result  = validateWidthricheditor( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.HEIGHTRICHEDITOR)) 
		 {
			 result  = validateHeightricheditor( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.SHOW_LABEL)) 
		 {
			 result  = validateShowLabel( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.MAX_FILES_UPOALDED_QUANTITY)) 
		 {
			 result  = validateMaxFilesUpoaldedQuantity( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.IMMEDIATE_UPLOAD_FILE_UPLOADER)) 
		 {
			 result  = validateImmediateUploadFileUploader( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.ACCEPTED_TYPES_FILE_UPLOADER)) 
		 {
			 result  = validateAcceptedTypesFileUploader( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.ALLOW_FLASH_FILE_UPLOADER)) 
		 {
			 result  = validateAllowFlashFileUploader( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.VALUE_SEARCH_FORM)) 
		 {
			 result  = validateValueSearchForm( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.LIST_THRESHOLD)) 
		 {
			 result  = validateListThreshold( newValue);
		 }
		 else if (this.key.equals(TableMaintDetails.AJAX_ENABLED)) 
		 {
			 result  = validateAjaxEnabled( newValue);
		 }
		 return result ;
 } 
	public abstract ValidationResult validateColumnIncluded( Object newValue);
	public abstract ValidationResult validateSelectListQuery( Object newValue);
	public abstract ValidationResult validateJavaScriptValidation( Object newValue);
	public abstract ValidationResult validateHtmlType( Object newValue);
	public abstract ValidationResult validateAttribute( Object newValue);
	public abstract ValidationResult validateTabIndex( Object newValue);
	public abstract ValidationResult validateDisplayName( Object newValue);
	public abstract ValidationResult validateComments( Object newValue);
	public abstract ValidationResult validateColumnName( Object newValue);
	public abstract ValidationResult validateOwner( Object newValue);
	public abstract ValidationResult validateTableName( Object newValue);
	public abstract ValidationResult validateIncludedInResult( Object newValue);
	public abstract ValidationResult validateDisplayName_( Object newValue);
	public abstract ValidationResult validateDefaultAccessType( Object newValue);
	public abstract ValidationResult validateDatalength( Object newValue);
	public abstract ValidationResult validateNullable( Object newValue);
	public abstract ValidationResult validateEncryped( Object newValue);
	public abstract ValidationResult validateIncludedInEdit( Object newValue);
	public abstract ValidationResult validateGetCurrentitemByme( Object newValue);
	public abstract ValidationResult validateRendered( Object newValue);
	public abstract ValidationResult validateHasInputtext( Object newValue);
	public abstract ValidationResult validateTooltipDesc( Object newValue);
	public abstract ValidationResult validateTooltipDesc_( Object newValue);
	public abstract ValidationResult validateRenderProtectionType( Object newValue);
	public abstract ValidationResult validateCssStyle( Object newValue);
	public abstract ValidationResult validateDefaultValue( Object newValue);
	public abstract ValidationResult validatePreparedBy( Object newValue);
	public abstract ValidationResult validatePreparedDt( Object newValue);
	public abstract ValidationResult validateModifiedBy( Object newValue);
	public abstract ValidationResult validateModifiedDt( Object newValue);
	public abstract ValidationResult validateUsrDefVarIdForRender( Object newValue);
	public abstract ValidationResult validateUsrDefVarIdForDisabled( Object newValue);
	public abstract ValidationResult validateDisabled( Object newValue);
	public abstract ValidationResult validateCalenderLocale( Object newValue);
	public abstract ValidationResult validatePopUp( Object newValue);
	public abstract ValidationResult validatePattern( Object newValue);
	public abstract ValidationResult validateShowApplyButton( Object newValue);
	public abstract ValidationResult validateShowToolTip( Object newValue);
	public abstract ValidationResult validateInputSliderMin( Object newValue);
	public abstract ValidationResult validateInputSliderMax( Object newValue);
	public abstract ValidationResult validateInputSliderStep( Object newValue);
	public abstract ValidationResult validateDataType( Object newValue);
	public abstract ValidationResult validateDisableProtectionType( Object newValue);
	public abstract ValidationResult validateTheme( Object newValue);
	public abstract ValidationResult validateViewmode( Object newValue);
	public abstract ValidationResult validateWidthricheditor( Object newValue);
	public abstract ValidationResult validateHeightricheditor( Object newValue);
	public abstract ValidationResult validateShowLabel( Object newValue);
	public abstract ValidationResult validateMaxFilesUpoaldedQuantity( Object newValue);
	public abstract ValidationResult validateImmediateUploadFileUploader( Object newValue);
	public abstract ValidationResult validateAcceptedTypesFileUploader( Object newValue);
	public abstract ValidationResult validateAllowFlashFileUploader( Object newValue);
	public abstract ValidationResult validateValueSearchForm( Object newValue);
	public abstract ValidationResult validateListThreshold( Object newValue);
	public abstract ValidationResult validateAjaxEnabled( Object newValue);
 } 