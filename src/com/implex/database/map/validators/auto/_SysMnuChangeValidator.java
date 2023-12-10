package com.implex.database.map.validators.auto;

import com.implex.database.AttributeChangeValidator;
import com.implex.database.PersistantObject;
import com.implex.database.ValidationResult;
import com.implex.database.map.SecUsrDta;
import com.implex.database.map.SysMnu;

public abstract class _SysMnuChangeValidator extends AttributeChangeValidator {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */

	protected SecUsrDta secUser = null ; 
	protected PersistantObject po = null; 
	protected String key = null; 

	public _SysMnuChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key){
		 this.secUser = pm_secUserData ;
		 this.po = pm_po ; 
		 this.key = pm_key;
	}
	@Override
	public ValidationResult validate(Object newValue) {
		 ValidationResult result = new ValidationResult();
		 if (this.key.equals(SysMnu.MNU_CODE)) 
		 {
			 result  = validateMnuCode( newValue);
		 }
		 if (this.key.equals(SysMnu.MNU_PARENT)) 
		 {
			 result  = validateMnuParent( newValue);
		 }
		 if (this.key.equals(SysMnu.MNU_SHORTCUT)) 
		 {
			 result  = validateMnuShortcut( newValue);
		 }
		 if (this.key.equals(SysMnu.MNU_CALL_ID)) 
		 {
			 result  = validateMnuCallId( newValue);
		 }
		 if (this.key.equals(SysMnu.MNU_TYPE)) 
		 {
			 result  = validateMnuType( newValue);
		 }
		 if (this.key.equals(SysMnu.MNU_PRG_NAME)) 
		 {
			 result  = validateMnuPrgName( newValue);
		 }
		 if (this.key.equals(SysMnu.MNU_PRG_ALIAS)) 
		 {
			 result  = validateMnuPrgAlias( newValue);
		 }
		 if (this.key.equals(SysMnu.MNU_SQL)) 
		 {
			 result  = validateMnuSql( newValue);
		 }
		 if (this.key.equals(SysMnu.MNU_ARG_TYP)) 
		 {
			 result  = validateMnuArgTyp( newValue);
		 }
		 if (this.key.equals(SysMnu.MNU_ARG1)) 
		 {
			 result  = validateMnuArg1( newValue);
		 }
		 if (this.key.equals(SysMnu.MNU_ARG2)) 
		 {
			 result  = validateMnuArg2( newValue);
		 }
		 if (this.key.equals(SysMnu.MNU_ARG3)) 
		 {
			 result  = validateMnuArg3( newValue);
		 }
		 if (this.key.equals(SysMnu.MNU_ARG4)) 
		 {
			 result  = validateMnuArg4( newValue);
		 }
		 if (this.key.equals(SysMnu.MNU_FONT_NAME)) 
		 {
			 result  = validateMnuFontName( newValue);
		 }
		 if (this.key.equals(SysMnu.MNU_FONT_SIZE)) 
		 {
			 result  = validateMnuFontSize( newValue);
		 }
		 if (this.key.equals(SysMnu.MNU_FONT_BOLD)) 
		 {
			 result  = validateMnuFontBold( newValue);
		 }
		 if (this.key.equals(SysMnu.MNU_FONT_COLOR_R)) 
		 {
			 result  = validateMnuFontColorR( newValue);
		 }
		 if (this.key.equals(SysMnu.MNU_FONT_COLOR_G)) 
		 {
			 result  = validateMnuFontColorG( newValue);
		 }
		 if (this.key.equals(SysMnu.MNU_FONT_COLOR_B)) 
		 {
			 result  = validateMnuFontColorB( newValue);
		 }
		 if (this.key.equals(SysMnu.MNU_IMG)) 
		 {
			 result  = validateMnuImg( newValue);
		 }
		 if (this.key.equals(SysMnu.MNU_STYL)) 
		 {
			 result  = validateMnuStyl( newValue);
		 }
		 if (this.key.equals(SysMnu.MNU_STATUS)) 
		 {
			 result  = validateMnuStatus( newValue);
		 }
		 if (this.key.equals(SysMnu.MODIFIED_DT)) 
		 {
			 result  = validateModifiedDt( newValue);
		 }
		 if (this.key.equals(SysMnu.SERVICE_CLASS)) 
		 {
			 result  = validateServiceClass( newValue);
		 }
		 if (this.key.equals(SysMnu.DATE_CREATED)) 
		 {
			 result  = validateDateCreated( newValue);
		 }
		 if (this.key.equals(SysMnu.MNU_TXT)) 
		 {
			 result  = validateMnuTxt( newValue);
		 }
		 if (this.key.equals(SysMnu.MNU_TXT_)) 
		 {
			 result  = validateMnuTxt_( newValue);
		 }
		 if (this.key.equals(SysMnu.MNU_DESC)) 
		 {
			 result  = validateMnuDesc( newValue);
		 }
		 if (this.key.equals(SysMnu.MNU_DESC_)) 
		 {
			 result  = validateMnuDesc_( newValue);
		 }
		 if (this.key.equals(SysMnu.MNU_URL)) 
		 {
			 result  = validateMnuUrl( newValue);
		 }
		 if (this.key.equals(SysMnu.MNU_RPT)) 
		 {
			 result  = validateMnuRpt( newValue);
		 }
		 if (this.key.equals(SysMnu.VPDATABASETABLE)) 
		 {
			 result  = validateVpdatabasetable( newValue);
		 }
		 return result ;
 } 
	public abstract ValidationResult validateMnuCode( Object newValue);
	public abstract ValidationResult validateMnuParent( Object newValue);
	public abstract ValidationResult validateMnuShortcut( Object newValue);
	public abstract ValidationResult validateMnuCallId( Object newValue);
	public abstract ValidationResult validateMnuType( Object newValue);
	public abstract ValidationResult validateMnuPrgName( Object newValue);
	public abstract ValidationResult validateMnuPrgAlias( Object newValue);
	public abstract ValidationResult validateMnuSql( Object newValue);
	public abstract ValidationResult validateMnuArgTyp( Object newValue);
	public abstract ValidationResult validateMnuArg1( Object newValue);
	public abstract ValidationResult validateMnuArg2( Object newValue);
	public abstract ValidationResult validateMnuArg3( Object newValue);
	public abstract ValidationResult validateMnuArg4( Object newValue);
	public abstract ValidationResult validateMnuFontName( Object newValue);
	public abstract ValidationResult validateMnuFontSize( Object newValue);
	public abstract ValidationResult validateMnuFontBold( Object newValue);
	public abstract ValidationResult validateMnuFontColorR( Object newValue);
	public abstract ValidationResult validateMnuFontColorG( Object newValue);
	public abstract ValidationResult validateMnuFontColorB( Object newValue);
	public abstract ValidationResult validateMnuImg( Object newValue);
	public abstract ValidationResult validateMnuStyl( Object newValue);
	public abstract ValidationResult validateMnuStatus( Object newValue);
	public abstract ValidationResult validateModifiedDt( Object newValue);
	public abstract ValidationResult validateServiceClass( Object newValue);
	public abstract ValidationResult validateDateCreated( Object newValue);
	public abstract ValidationResult validateMnuTxt( Object newValue);
	public abstract ValidationResult validateMnuTxt_( Object newValue);
	public abstract ValidationResult validateMnuDesc( Object newValue);
	public abstract ValidationResult validateMnuDesc_( Object newValue);
	public abstract ValidationResult validateMnuUrl( Object newValue);
	public abstract ValidationResult validateMnuRpt( Object newValue);
	public abstract ValidationResult validateVpdatabasetable( Object newValue);
 } 