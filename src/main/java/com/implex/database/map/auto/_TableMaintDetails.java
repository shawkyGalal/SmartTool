package com.implex.database.map.auto;

import com.implex.database.PersistantObject; 
import com.implex.database.Attribute;
import com.implex.database.DbTable;

public abstract class _TableMaintDetails extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "TABLE_MAINT_DETAILS"; 
 public static final String DB_TABLE_OWNER = "ICDB"; 

	public _TableMaintDetails(){
	}

	 public DbTable getTable() 
	 { 
		return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String COLUMN_INCLUDED ="COLUMN_INCLUDED" ;

	public void setColumnIncludedValue(java.lang.String   pm_columnIncluded){
		this.getAttribute(COLUMN_INCLUDED ).setValue( pm_columnIncluded );
	}
 
	public java.lang.String getColumnIncludedValue(){
		return (java.lang.String) this.getAttribute ( COLUMN_INCLUDED).getValue()  ;
	}
 
	public Attribute getColumnIncluded(){
		return this.getAttribute ( COLUMN_INCLUDED)  ;
	}

	public static final String SELECT_LIST_QUERY ="SELECT_LIST_QUERY" ;

	public void setSelectListQueryValue(java.lang.String   pm_selectListQuery){
		this.getAttribute(SELECT_LIST_QUERY ).setValue( pm_selectListQuery );
	}
 
	public java.lang.String getSelectListQueryValue(){
		return (java.lang.String) this.getAttribute ( SELECT_LIST_QUERY).getValue()  ;
	}
 
	public Attribute getSelectListQuery(){
		return this.getAttribute ( SELECT_LIST_QUERY)  ;
	}

	public static final String JAVA_SCRIPT_VALIDATION ="JAVA_SCRIPT_VALIDATION" ;

	public void setJavaScriptValidationValue(java.lang.String   pm_javaScriptValidation){
		this.getAttribute(JAVA_SCRIPT_VALIDATION ).setValue( pm_javaScriptValidation );
	}
 
	public java.lang.String getJavaScriptValidationValue(){
		return (java.lang.String) this.getAttribute ( JAVA_SCRIPT_VALIDATION).getValue()  ;
	}
 
	public Attribute getJavaScriptValidation(){
		return this.getAttribute ( JAVA_SCRIPT_VALIDATION)  ;
	}

	public static final String HTML_TYPE ="HTML_TYPE" ;

	public void setHtmlTypeValue(java.lang.String   pm_htmlType){
		this.getAttribute(HTML_TYPE ).setValue( pm_htmlType );
	}
 
	public java.lang.String getHtmlTypeValue(){
		return (java.lang.String) this.getAttribute ( HTML_TYPE).getValue()  ;
	}
 
	public Attribute getHtmlType(){
		return this.getAttribute ( HTML_TYPE)  ;
	}

	public static final String ATTRIBUTE ="ATTRIBUTE" ;

	public void setAttributeValue(java.lang.String   pm_attribute){
		this.getAttribute(ATTRIBUTE ).setValue( pm_attribute );
	}
 
	public java.lang.String getAttributeValue(){
		return (java.lang.String) this.getAttribute ( ATTRIBUTE).getValue()  ;
	}
 
	public Attribute getAttribute(){
		return this.getAttribute ( ATTRIBUTE)  ;
	}

	public static final String TAB_INDEX ="TAB_INDEX" ;

	public void setTabIndexValue(java.math.BigDecimal   pm_tabIndex){
		this.getAttribute(TAB_INDEX ).setValue( pm_tabIndex );
	}
 
	public java.math.BigDecimal getTabIndexValue(){
		return (java.math.BigDecimal) this.getAttribute ( TAB_INDEX).getValue()  ;
	}
 
	public Attribute getTabIndex(){
		return this.getAttribute ( TAB_INDEX)  ;
	}

	public static final String DISPLAY_NAME ="DISPLAY_NAME" ;

	public void setDisplayNameValue(java.lang.String   pm_displayName){
		this.getAttribute(DISPLAY_NAME ).setValue( pm_displayName );
	}
 
	public java.lang.String getDisplayNameValue(){
		return (java.lang.String) this.getAttribute ( DISPLAY_NAME).getValue()  ;
	}
 
	public Attribute getDisplayName(){
		return this.getAttribute ( DISPLAY_NAME)  ;
	}

	public static final String COMMENTS ="COMMENTS" ;

	public void setCommentsValue(java.lang.String   pm_comments){
		this.getAttribute(COMMENTS ).setValue( pm_comments );
	}
 
	public java.lang.String getCommentsValue(){
		return (java.lang.String) this.getAttribute ( COMMENTS).getValue()  ;
	}
 
	public Attribute getComments(){
		return this.getAttribute ( COMMENTS)  ;
	}

	public static final String COLUMN_NAME ="COLUMN_NAME" ;

	public void setColumnNameValue(java.lang.String   pm_columnName){
		this.getAttribute(COLUMN_NAME ).setValue( pm_columnName );
	}
 
	public java.lang.String getColumnNameValue(){
		return (java.lang.String) this.getAttribute ( COLUMN_NAME).getValue()  ;
	}
 
	public Attribute getColumnName(){
		return this.getAttribute ( COLUMN_NAME)  ;
	}

	public static final String OWNER ="OWNER" ;

	public void setOwnerValue(java.lang.String   pm_owner){
		this.getAttribute(OWNER ).setValue( pm_owner );
	}
 
	public java.lang.String getOwnerValue(){
		return (java.lang.String) this.getAttribute ( OWNER).getValue()  ;
	}
 
	public Attribute getOwner(){
		return this.getAttribute ( OWNER)  ;
	}

	public static final String TABLE_NAME ="TABLE_NAME" ;

	public void setTableNameValue(java.lang.String   pm_tableName){
		this.getAttribute(TABLE_NAME ).setValue( pm_tableName );
	}
 
	public java.lang.String getTableNameValue(){
		return (java.lang.String) this.getAttribute ( TABLE_NAME).getValue()  ;
	}
 
	public Attribute getTableName(){
		return this.getAttribute ( TABLE_NAME)  ;
	}

	public static final String INCLUDED_IN_RESULT ="INCLUDED_IN_RESULT" ;

	public void setIncludedInResultValue(java.lang.String   pm_includedInResult){
		this.getAttribute(INCLUDED_IN_RESULT ).setValue( pm_includedInResult );
	}
 
	public java.lang.String getIncludedInResultValue(){
		return (java.lang.String) this.getAttribute ( INCLUDED_IN_RESULT).getValue()  ;
	}
 
	public Attribute getIncludedInResult(){
		return this.getAttribute ( INCLUDED_IN_RESULT)  ;
	}

	public static final String DISPLAY_NAME_ ="DISPLAY_NAME_" ;

	public void setDisplayName_Value(java.lang.String   pm_displayName_){
		this.getAttribute(DISPLAY_NAME_ ).setValue( pm_displayName_ );
	}
 
	public java.lang.String getDisplayName_Value(){
		return (java.lang.String) this.getAttribute ( DISPLAY_NAME_).getValue()  ;
	}
 
	public Attribute getDisplayName_(){
		return this.getAttribute ( DISPLAY_NAME_)  ;
	}

	public static final String DEFAULT_ACCESS_TYPE ="DEFAULT_ACCESS_TYPE" ;

	public void setDefaultAccessTypeValue(java.lang.String   pm_defaultAccessType){
		this.getAttribute(DEFAULT_ACCESS_TYPE ).setValue( pm_defaultAccessType );
	}
 
	public java.lang.String getDefaultAccessTypeValue(){
		return (java.lang.String) this.getAttribute ( DEFAULT_ACCESS_TYPE).getValue()  ;
	}
 
	public Attribute getDefaultAccessType(){
		return this.getAttribute ( DEFAULT_ACCESS_TYPE)  ;
	}

	public static final String DATALENGTH ="DATALENGTH" ;

	public void setDatalengthValue(java.math.BigDecimal   pm_datalength){
		this.getAttribute(DATALENGTH ).setValue( pm_datalength );
	}
 
	public java.math.BigDecimal getDatalengthValue(){
		return (java.math.BigDecimal) this.getAttribute ( DATALENGTH).getValue()  ;
	}
 
	public Attribute getDatalength(){
		return this.getAttribute ( DATALENGTH)  ;
	}

	public static final String NULLABLE ="NULLABLE" ;

	public void setNullableValue(java.lang.String   pm_nullable){
		this.getAttribute(NULLABLE ).setValue( pm_nullable );
	}
 
	public java.lang.String getNullableValue(){
		return (java.lang.String) this.getAttribute ( NULLABLE).getValue()  ;
	}
 
	public Attribute getNullable(){
		return this.getAttribute ( NULLABLE)  ;
	}

	public static final String ENCRYPED ="ENCRYPED" ;

	public void setEncrypedValue(java.lang.String   pm_encryped){
		this.getAttribute(ENCRYPED ).setValue( pm_encryped );
	}
 
	public java.lang.String getEncrypedValue(){
		return (java.lang.String) this.getAttribute ( ENCRYPED).getValue()  ;
	}
 
	public Attribute getEncryped(){
		return this.getAttribute ( ENCRYPED)  ;
	}

	public static final String INCLUDED_IN_EDIT ="INCLUDED_IN_EDIT" ;

	public void setIncludedInEditValue(java.lang.String   pm_includedInEdit){
		this.getAttribute(INCLUDED_IN_EDIT ).setValue( pm_includedInEdit );
	}
 
	public java.lang.String getIncludedInEditValue(){
		return (java.lang.String) this.getAttribute ( INCLUDED_IN_EDIT).getValue()  ;
	}
 
	public Attribute getIncludedInEdit(){
		return this.getAttribute ( INCLUDED_IN_EDIT)  ;
	}

	public static final String GET_CURRENTITEM_BYME ="GET_CURRENTITEM_BYME" ;

	public void setGetCurrentitemBymeValue(java.lang.String   pm_getCurrentitemByme){
		this.getAttribute(GET_CURRENTITEM_BYME ).setValue( pm_getCurrentitemByme );
	}
 
	public java.lang.String getGetCurrentitemBymeValue(){
		return (java.lang.String) this.getAttribute ( GET_CURRENTITEM_BYME).getValue()  ;
	}
 
	public Attribute getGetCurrentitemByme(){
		return this.getAttribute ( GET_CURRENTITEM_BYME)  ;
	}

	public static final String RENDERED ="RENDERED" ;

	public void setRenderedValue(java.lang.String   pm_rendered){
		this.getAttribute(RENDERED ).setValue( pm_rendered );
	}
 
	public java.lang.String getRenderedValue(){
		return (java.lang.String) this.getAttribute ( RENDERED).getValue()  ;
	}
 
	public Attribute getRendered(){
		return this.getAttribute ( RENDERED)  ;
	}

	public static final String HAS_INPUTTEXT ="HAS_INPUTTEXT" ;

	public void setHasInputtextValue(java.lang.String   pm_hasInputtext){
		this.getAttribute(HAS_INPUTTEXT ).setValue( pm_hasInputtext );
	}
 
	public java.lang.String getHasInputtextValue(){
		return (java.lang.String) this.getAttribute ( HAS_INPUTTEXT).getValue()  ;
	}
 
	public Attribute getHasInputtext(){
		return this.getAttribute ( HAS_INPUTTEXT)  ;
	}

	public static final String TOOLTIP_DESC ="TOOLTIP_DESC" ;

	public void setTooltipDescValue(java.lang.String   pm_tooltipDesc){
		this.getAttribute(TOOLTIP_DESC ).setValue( pm_tooltipDesc );
	}
 
	public java.lang.String getTooltipDescValue(){
		return (java.lang.String) this.getAttribute ( TOOLTIP_DESC).getValue()  ;
	}
 
	public Attribute getTooltipDesc(){
		return this.getAttribute ( TOOLTIP_DESC)  ;
	}

	public static final String TOOLTIP_DESC_ ="TOOLTIP_DESC_" ;

	public void setTooltipDesc_Value(java.lang.String   pm_tooltipDesc_){
		this.getAttribute(TOOLTIP_DESC_ ).setValue( pm_tooltipDesc_ );
	}
 
	public java.lang.String getTooltipDesc_Value(){
		return (java.lang.String) this.getAttribute ( TOOLTIP_DESC_).getValue()  ;
	}
 
	public Attribute getTooltipDesc_(){
		return this.getAttribute ( TOOLTIP_DESC_)  ;
	}

	public static final String RENDER_PROTECTION_TYPE ="RENDER_PROTECTION_TYPE" ;

	public void setRenderProtectionTypeValue(java.lang.String   pm_renderProtectionType){
		this.getAttribute(RENDER_PROTECTION_TYPE ).setValue( pm_renderProtectionType );
	}
 
	public java.lang.String getRenderProtectionTypeValue(){
		return (java.lang.String) this.getAttribute ( RENDER_PROTECTION_TYPE).getValue()  ;
	}
 
	public Attribute getRenderProtectionType(){
		return this.getAttribute ( RENDER_PROTECTION_TYPE)  ;
	}

	public static final String CSS_STYLE ="CSS_STYLE" ;

	public void setCssStyleValue(java.lang.String   pm_cssStyle){
		this.getAttribute(CSS_STYLE ).setValue( pm_cssStyle );
	}
 
	public java.lang.String getCssStyleValue(){
		return (java.lang.String) this.getAttribute ( CSS_STYLE).getValue()  ;
	}
 
	public Attribute getCssStyle(){
		return this.getAttribute ( CSS_STYLE)  ;
	}

	public static final String DEFAULT_VALUE ="DEFAULT_VALUE" ;

	public void setDefaultValueValue(java.lang.String   pm_defaultValue){
		this.getAttribute(DEFAULT_VALUE ).setValue( pm_defaultValue );
	}
 
	public java.lang.String getDefaultValueValue(){
		return (java.lang.String) this.getAttribute ( DEFAULT_VALUE).getValue()  ;
	}
 
	public Attribute getDefaultValue(){
		return this.getAttribute ( DEFAULT_VALUE)  ;
	}

	public static final String PREPARED_BY ="PREPARED_BY" ;

	public void setPreparedByValue(java.lang.String   pm_preparedBy){
		this.getAttribute(PREPARED_BY ).setValue( pm_preparedBy );
	}
 
	public java.lang.String getPreparedByValue(){
		return (java.lang.String) this.getAttribute ( PREPARED_BY).getValue()  ;
	}
 
	public Attribute getPreparedBy(){
		return this.getAttribute ( PREPARED_BY)  ;
	}

	public static final String PREPARED_DT ="PREPARED_DT" ;

	public void setPreparedDtValue(java.sql.Timestamp   pm_preparedDt){
		this.getAttribute(PREPARED_DT ).setValue( pm_preparedDt );
	}
 
	public java.sql.Timestamp getPreparedDtValue(){
		return (java.sql.Timestamp) this.getAttribute ( PREPARED_DT).getValue()  ;
	}
 
	public Attribute getPreparedDt(){
		return this.getAttribute ( PREPARED_DT)  ;
	}

	public static final String MODIFIED_BY ="MODIFIED_BY" ;

	public void setModifiedByValue(java.lang.String   pm_modifiedBy){
		this.getAttribute(MODIFIED_BY ).setValue( pm_modifiedBy );
	}
 
	public java.lang.String getModifiedByValue(){
		return (java.lang.String) this.getAttribute ( MODIFIED_BY).getValue()  ;
	}
 
	public Attribute getModifiedBy(){
		return this.getAttribute ( MODIFIED_BY)  ;
	}

	public static final String MODIFIED_DT ="MODIFIED_DT" ;

	public void setModifiedDtValue(java.sql.Timestamp   pm_modifiedDt){
		this.getAttribute(MODIFIED_DT ).setValue( pm_modifiedDt );
	}
 
	public java.sql.Timestamp getModifiedDtValue(){
		return (java.sql.Timestamp) this.getAttribute ( MODIFIED_DT).getValue()  ;
	}
 
	public Attribute getModifiedDt(){
		return this.getAttribute ( MODIFIED_DT)  ;
	}

	public static final String USR_DEF_VAR_ID_FOR_RENDER ="USR_DEF_VAR_ID_FOR_RENDER" ;

	public void setUsrDefVarIdForRenderValue(java.lang.String   pm_usrDefVarIdForRender){
		this.getAttribute(USR_DEF_VAR_ID_FOR_RENDER ).setValue( pm_usrDefVarIdForRender );
	}
 
	public java.lang.String getUsrDefVarIdForRenderValue(){
		return (java.lang.String) this.getAttribute ( USR_DEF_VAR_ID_FOR_RENDER).getValue()  ;
	}
 
	public Attribute getUsrDefVarIdForRender(){
		return this.getAttribute ( USR_DEF_VAR_ID_FOR_RENDER)  ;
	}

	public static final String USR_DEF_VAR_ID_FOR_DISABLED ="USR_DEF_VAR_ID_FOR_DISABLED" ;

	public void setUsrDefVarIdForDisabledValue(java.lang.String   pm_usrDefVarIdForDisabled){
		this.getAttribute(USR_DEF_VAR_ID_FOR_DISABLED ).setValue( pm_usrDefVarIdForDisabled );
	}
 
	public java.lang.String getUsrDefVarIdForDisabledValue(){
		return (java.lang.String) this.getAttribute ( USR_DEF_VAR_ID_FOR_DISABLED).getValue()  ;
	}
 
	public Attribute getUsrDefVarIdForDisabled(){
		return this.getAttribute ( USR_DEF_VAR_ID_FOR_DISABLED)  ;
	}

	public static final String DISABLED ="DISABLED" ;

	public void setDisabledValue(java.lang.String   pm_disabled){
		this.getAttribute(DISABLED ).setValue( pm_disabled );
	}
 
	public java.lang.String getDisabledValue(){
		return (java.lang.String) this.getAttribute ( DISABLED).getValue()  ;
	}
 
	public Attribute getDisabled(){
		return this.getAttribute ( DISABLED)  ;
	}

	public static final String CALENDER_LOCALE ="CALENDER_LOCALE" ;

	public void setCalenderLocaleValue(java.lang.String   pm_calenderLocale){
		this.getAttribute(CALENDER_LOCALE ).setValue( pm_calenderLocale );
	}
 
	public java.lang.String getCalenderLocaleValue(){
		return (java.lang.String) this.getAttribute ( CALENDER_LOCALE).getValue()  ;
	}
 
	public Attribute getCalenderLocale(){
		return this.getAttribute ( CALENDER_LOCALE)  ;
	}

	public static final String POP_UP ="POP_UP" ;

	public void setPopUpValue(java.lang.String   pm_popUp){
		this.getAttribute(POP_UP ).setValue( pm_popUp );
	}
 
	public java.lang.String getPopUpValue(){
		return (java.lang.String) this.getAttribute ( POP_UP).getValue()  ;
	}
 
	public Attribute getPopUp(){
		return this.getAttribute ( POP_UP)  ;
	}

	public static final String PATTERN ="PATTERN" ;

	public void setPatternValue(java.lang.String   pm_pattern){
		this.getAttribute(PATTERN ).setValue( pm_pattern );
	}
 
	public java.lang.String getPatternValue(){
		return (java.lang.String) this.getAttribute ( PATTERN).getValue()  ;
	}
 
	public Attribute getPattern(){
		return this.getAttribute ( PATTERN)  ;
	}

	public static final String SHOW_APPLY_BUTTON ="SHOW_APPLY_BUTTON" ;

	public void setShowApplyButtonValue(java.lang.String   pm_showApplyButton){
		this.getAttribute(SHOW_APPLY_BUTTON ).setValue( pm_showApplyButton );
	}
 
	public java.lang.String getShowApplyButtonValue(){
		return (java.lang.String) this.getAttribute ( SHOW_APPLY_BUTTON).getValue()  ;
	}
 
	public Attribute getShowApplyButton(){
		return this.getAttribute ( SHOW_APPLY_BUTTON)  ;
	}

	public static final String SHOW_TOOL_TIP ="SHOW_TOOL_TIP" ;

	public void setShowToolTipValue(java.lang.String   pm_showToolTip){
		this.getAttribute(SHOW_TOOL_TIP ).setValue( pm_showToolTip );
	}
 
	public java.lang.String getShowToolTipValue(){
		return (java.lang.String) this.getAttribute ( SHOW_TOOL_TIP).getValue()  ;
	}
 
	public Attribute getShowToolTip(){
		return this.getAttribute ( SHOW_TOOL_TIP)  ;
	}

	public static final String INPUT_SLIDER_MIN ="INPUT_SLIDER_MIN" ;

	public void setInputSliderMinValue(java.lang.String   pm_inputSliderMin){
		this.getAttribute(INPUT_SLIDER_MIN ).setValue( pm_inputSliderMin );
	}
 
	public java.lang.String getInputSliderMinValue(){
		return (java.lang.String) this.getAttribute ( INPUT_SLIDER_MIN).getValue()  ;
	}
 
	public Attribute getInputSliderMin(){
		return this.getAttribute ( INPUT_SLIDER_MIN)  ;
	}

	public static final String INPUT_SLIDER_MAX ="INPUT_SLIDER_MAX" ;

	public void setInputSliderMaxValue(java.lang.String   pm_inputSliderMax){
		this.getAttribute(INPUT_SLIDER_MAX ).setValue( pm_inputSliderMax );
	}
 
	public java.lang.String getInputSliderMaxValue(){
		return (java.lang.String) this.getAttribute ( INPUT_SLIDER_MAX).getValue()  ;
	}
 
	public Attribute getInputSliderMax(){
		return this.getAttribute ( INPUT_SLIDER_MAX)  ;
	}

	public static final String INPUT_SLIDER_STEP ="INPUT_SLIDER_STEP" ;

	public void setInputSliderStepValue(java.lang.String   pm_inputSliderStep){
		this.getAttribute(INPUT_SLIDER_STEP ).setValue( pm_inputSliderStep );
	}
 
	public java.lang.String getInputSliderStepValue(){
		return (java.lang.String) this.getAttribute ( INPUT_SLIDER_STEP).getValue()  ;
	}
 
	public Attribute getInputSliderStep(){
		return this.getAttribute ( INPUT_SLIDER_STEP)  ;
	}

	public static final String DATA_TYPE ="DATA_TYPE" ;

	public void setDataTypeValue(java.lang.String   pm_dataType){
		this.getAttribute(DATA_TYPE ).setValue( pm_dataType );
	}
 
	public java.lang.String getDataTypeValue(){
		return (java.lang.String) this.getAttribute ( DATA_TYPE).getValue()  ;
	}
 
	public Attribute getDataType(){
		return this.getAttribute ( DATA_TYPE)  ;
	}

	public static final String DISABLE_PROTECTION_TYPE ="DISABLE_PROTECTION_TYPE" ;

	public void setDisableProtectionTypeValue(java.lang.String   pm_disableProtectionType){
		this.getAttribute(DISABLE_PROTECTION_TYPE ).setValue( pm_disableProtectionType );
	}
 
	public java.lang.String getDisableProtectionTypeValue(){
		return (java.lang.String) this.getAttribute ( DISABLE_PROTECTION_TYPE).getValue()  ;
	}
 
	public Attribute getDisableProtectionType(){
		return this.getAttribute ( DISABLE_PROTECTION_TYPE)  ;
	}

	public static final String THEME ="THEME" ;

	public void setThemeValue(java.lang.String   pm_theme){
		this.getAttribute(THEME ).setValue( pm_theme );
	}
 
	public java.lang.String getThemeValue(){
		return (java.lang.String) this.getAttribute ( THEME).getValue()  ;
	}
 
	public Attribute getTheme(){
		return this.getAttribute ( THEME)  ;
	}

	public static final String VIEWMODE ="VIEWMODE" ;

	public void setViewmodeValue(java.lang.String   pm_viewmode){
		this.getAttribute(VIEWMODE ).setValue( pm_viewmode );
	}
 
	public java.lang.String getViewmodeValue(){
		return (java.lang.String) this.getAttribute ( VIEWMODE).getValue()  ;
	}
 
	public Attribute getViewmode(){
		return this.getAttribute ( VIEWMODE)  ;
	}

	public static final String WIDTHRICHEDITOR ="WIDTHRICHEDITOR" ;

	public void setWidthricheditorValue(java.math.BigDecimal   pm_widthricheditor){
		this.getAttribute(WIDTHRICHEDITOR ).setValue( pm_widthricheditor );
	}
 
	public java.math.BigDecimal getWidthricheditorValue(){
		return (java.math.BigDecimal) this.getAttribute ( WIDTHRICHEDITOR).getValue()  ;
	}
 
	public Attribute getWidthricheditor(){
		return this.getAttribute ( WIDTHRICHEDITOR)  ;
	}

	public static final String HEIGHTRICHEDITOR ="HEIGHTRICHEDITOR" ;

	public void setHeightricheditorValue(java.math.BigDecimal   pm_heightricheditor){
		this.getAttribute(HEIGHTRICHEDITOR ).setValue( pm_heightricheditor );
	}
 
	public java.math.BigDecimal getHeightricheditorValue(){
		return (java.math.BigDecimal) this.getAttribute ( HEIGHTRICHEDITOR).getValue()  ;
	}
 
	public Attribute getHeightricheditor(){
		return this.getAttribute ( HEIGHTRICHEDITOR)  ;
	}

	public static final String SHOW_LABEL ="SHOW_LABEL" ;

	public void setShowLabelValue(java.lang.String   pm_showLabel){
		this.getAttribute(SHOW_LABEL ).setValue( pm_showLabel );
	}
 
	public java.lang.String getShowLabelValue(){
		return (java.lang.String) this.getAttribute ( SHOW_LABEL).getValue()  ;
	}
 
	public Attribute getShowLabel(){
		return this.getAttribute ( SHOW_LABEL)  ;
	}

	public static final String MAX_FILES_UPOALDED_QUANTITY ="MAX_FILES_UPOALDED_QUANTITY" ;

	public void setMaxFilesUpoaldedQuantityValue(java.math.BigDecimal   pm_maxFilesUpoaldedQuantity){
		this.getAttribute(MAX_FILES_UPOALDED_QUANTITY ).setValue( pm_maxFilesUpoaldedQuantity );
	}
 
	public java.math.BigDecimal getMaxFilesUpoaldedQuantityValue(){
		return (java.math.BigDecimal) this.getAttribute ( MAX_FILES_UPOALDED_QUANTITY).getValue()  ;
	}
 
	public Attribute getMaxFilesUpoaldedQuantity(){
		return this.getAttribute ( MAX_FILES_UPOALDED_QUANTITY)  ;
	}

	public static final String IMMEDIATE_UPLOAD_FILE_UPLOADER ="IMMEDIATE_UPLOAD_FILE_UPLOADER" ;

	public void setImmediateUploadFileUploaderValue(java.lang.String   pm_immediateUploadFileUploader){
		this.getAttribute(IMMEDIATE_UPLOAD_FILE_UPLOADER ).setValue( pm_immediateUploadFileUploader );
	}
 
	public java.lang.String getImmediateUploadFileUploaderValue(){
		return (java.lang.String) this.getAttribute ( IMMEDIATE_UPLOAD_FILE_UPLOADER).getValue()  ;
	}
 
	public Attribute getImmediateUploadFileUploader(){
		return this.getAttribute ( IMMEDIATE_UPLOAD_FILE_UPLOADER)  ;
	}

	public static final String ACCEPTED_TYPES_FILE_UPLOADER ="ACCEPTED_TYPES_FILE_UPLOADER" ;

	public void setAcceptedTypesFileUploaderValue(java.lang.String   pm_acceptedTypesFileUploader){
		this.getAttribute(ACCEPTED_TYPES_FILE_UPLOADER ).setValue( pm_acceptedTypesFileUploader );
	}
 
	public java.lang.String getAcceptedTypesFileUploaderValue(){
		return (java.lang.String) this.getAttribute ( ACCEPTED_TYPES_FILE_UPLOADER).getValue()  ;
	}
 
	public Attribute getAcceptedTypesFileUploader(){
		return this.getAttribute ( ACCEPTED_TYPES_FILE_UPLOADER)  ;
	}

	public static final String ALLOW_FLASH_FILE_UPLOADER ="ALLOW_FLASH_FILE_UPLOADER" ;

	public void setAllowFlashFileUploaderValue(java.lang.String   pm_allowFlashFileUploader){
		this.getAttribute(ALLOW_FLASH_FILE_UPLOADER ).setValue( pm_allowFlashFileUploader );
	}
 
	public java.lang.String getAllowFlashFileUploaderValue(){
		return (java.lang.String) this.getAttribute ( ALLOW_FLASH_FILE_UPLOADER).getValue()  ;
	}
 
	public Attribute getAllowFlashFileUploader(){
		return this.getAttribute ( ALLOW_FLASH_FILE_UPLOADER)  ;
	}

	public static final String VALUE_SEARCH_FORM ="VALUE_SEARCH_FORM" ;

	public void setValueSearchFormValue(java.lang.String   pm_valueSearchForm){
		this.getAttribute(VALUE_SEARCH_FORM ).setValue( pm_valueSearchForm );
	}
 
	public java.lang.String getValueSearchFormValue(){
		return (java.lang.String) this.getAttribute ( VALUE_SEARCH_FORM).getValue()  ;
	}
 
	public Attribute getValueSearchForm(){
		return this.getAttribute ( VALUE_SEARCH_FORM)  ;
	}

	public static final String LIST_THRESHOLD ="LIST_THRESHOLD" ;

	public void setListThresholdValue(java.math.BigDecimal   pm_listThreshold){
		this.getAttribute(LIST_THRESHOLD ).setValue( pm_listThreshold );
	}
 
	public java.math.BigDecimal getListThresholdValue(){
		return (java.math.BigDecimal) this.getAttribute ( LIST_THRESHOLD).getValue()  ;
	}
 
	public Attribute getListThreshold(){
		return this.getAttribute ( LIST_THRESHOLD)  ;
	}

	public static final String HAVE_SUB_LIST ="HAVE_SUB_LIST" ;

	public void setHaveSubListValue(java.lang.String   pm_haveSubList){
		this.getAttribute(HAVE_SUB_LIST ).setValue( pm_haveSubList );
	}
 
	public java.lang.String getHaveSubListValue(){
		return (java.lang.String) this.getAttribute ( HAVE_SUB_LIST).getValue()  ;
	}
 
	public Attribute getHaveSubList(){
		return this.getAttribute ( HAVE_SUB_LIST)  ;
	}

	public static final String PARENT_LIST_COLUMN_NAME ="PARENT_LIST_COLUMN_NAME" ;

	public void setParentListColumnNameValue(java.lang.String   pm_parentListColumnName){
		this.getAttribute(PARENT_LIST_COLUMN_NAME ).setValue( pm_parentListColumnName );
	}
 
	public java.lang.String getParentListColumnNameValue(){
		return (java.lang.String) this.getAttribute ( PARENT_LIST_COLUMN_NAME).getValue()  ;
	}
 
	public Attribute getParentListColumnName(){
		return this.getAttribute ( PARENT_LIST_COLUMN_NAME)  ;
	}

	public static final String AJAX_ENABLED ="AJAX_ENABLED" ;

	public void setAjaxEnabledValue(java.lang.String   pm_ajaxEnabled){
		this.getAttribute(AJAX_ENABLED ).setValue( pm_ajaxEnabled );
	}
 
	public java.lang.String getAjaxEnabledValue(){
		return (java.lang.String) this.getAttribute ( AJAX_ENABLED).getValue()  ;
	}
 
	public Attribute getAjaxEnabled(){
		return this.getAttribute ( AJAX_ENABLED)  ;
	}

	public static final String CLASSDATASETFRESHLISTNER ="CLASSDATASETFRESHLISTNER" ;

	public void setClassdatasetfreshlistnerValue(java.lang.String   pm_classdatasetfreshlistner){
		this.getAttribute(CLASSDATASETFRESHLISTNER ).setValue( pm_classdatasetfreshlistner );
	}
 
	public java.lang.String getClassdatasetfreshlistnerValue(){
		return (java.lang.String) this.getAttribute ( CLASSDATASETFRESHLISTNER).getValue()  ;
	}
 
	public Attribute getClassdatasetfreshlistner(){
		return this.getAttribute ( CLASSDATASETFRESHLISTNER)  ;
	}

	public static final String ONCHANGE ="ONCHANGE" ;

	public void setOnchangeValue(java.lang.String   pm_onchange){
		this.getAttribute(ONCHANGE ).setValue( pm_onchange );
	}
 
	public java.lang.String getOnchangeValue(){
		return (java.lang.String) this.getAttribute ( ONCHANGE).getValue()  ;
	}
 
	public Attribute getOnchange(){
		return this.getAttribute ( ONCHANGE)  ;
	}

	public static final String DISPLAY_TYPE_QUERY ="DISPLAY_TYPE_QUERY" ;

	public void setDisplayTypeQueryValue(java.lang.String   pm_displayTypeQuery){
		this.getAttribute(DISPLAY_TYPE_QUERY ).setValue( pm_displayTypeQuery );
	}
 
	public java.lang.String getDisplayTypeQueryValue(){
		return (java.lang.String) this.getAttribute ( DISPLAY_TYPE_QUERY).getValue()  ;
	}
 
	public Attribute getDisplayTypeQuery(){
		return this.getAttribute ( DISPLAY_TYPE_QUERY)  ;
	}

	public void setDisplayNameAutoLang(java.lang.String   pm_displayName){
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		this.getDisplayName().setValue( pm_displayName );
		 else 
		this.getDisplayName_().setValue( pm_displayName );
	}
 
	public Attribute getDisplayNameAutoLang(){
		 Attribute result ;
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		 result =   this.getDisplayName();
		 else 
		 result =   this.getDisplayName_();
		 return result;
	}

	public void setTooltipDescAutoLang(java.lang.String   pm_tooltipDesc){
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		this.getTooltipDesc().setValue( pm_tooltipDesc );
		 else 
		this.getTooltipDesc_().setValue( pm_tooltipDesc );
	}
 
	public Attribute getTooltipDescAutoLang(){
		 Attribute result ;
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		 result =   this.getTooltipDesc();
		 else 
		 result =   this.getTooltipDesc_();
		 return result;
	}

	// ********Start of Child DataSets getter methods******** 
	// *******End of Child DataSets getter methods******** 
}