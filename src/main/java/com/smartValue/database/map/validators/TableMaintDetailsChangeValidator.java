package com.smartValue.database.map.validators;
import com.smartValue.HtmlTypes.IHtmlType;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.ValidationResult;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.TableMaintDetails;
import com.smartValue.database.map.validators.auto._TableMaintDetailsChangeValidator;

public class TableMaintDetailsChangeValidator extends _TableMaintDetailsChangeValidator
{ 
	 public TableMaintDetailsChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key) 
	{
		super(pm_secUserData, pm_po, pm_key); 
	}

	@Override
	public ValidationResult validateAttribute(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateColumnIncluded(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateColumnName(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateComments(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateDisplayName(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateHtmlType(Object newValue)
	{
		String htmlTypeStr = (String)newValue ;
		TableMaintDetails tmd =  (TableMaintDetails) this.po;
		IHtmlType htmlType =  TableMaintDetails.calcHtmlImpl(htmlTypeStr, tmd.getDbService());
		return htmlType.isAllowedForDataType(tmd.getDataTypeValue() , po.getDbService());
			
	}


	@Override
	public ValidationResult validateIncludedInResult(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateJavaScriptValidation(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateModifiedDt(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateOwner(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateSelectListQuery(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateTabIndex(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateTableName(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateDatalength(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateDefaultAccessType(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateNullable(Object newValue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateDisplayName_(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateEncryped(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateGetCurrentitemByme(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateIncludedInEdit(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ValidationResult validateHasInputtext(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateRendered(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateTooltipDesc(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateTooltipDesc_(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public ValidationResult validateCssStyle(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateCalenderLocale(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateDataType(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateDefaultValue(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateDisableProtectionType(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateDisabled(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateInputSliderMax(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateInputSliderMin(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateInputSliderStep(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateModifiedBy(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validatePattern(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validatePopUp(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validatePreparedBy(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validatePreparedDt(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateRenderProtectionType(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateShowApplyButton(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateShowToolTip(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateUsrDefVarIdForDisabled(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateUsrDefVarIdForRender(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateHeightricheditor(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateShowLabel(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateTheme(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateViewmode(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateWidthricheditor(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateAcceptedTypesFileUploader(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateAjaxEnabled(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateAllowFlashFileUploader(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateImmediateUploadFileUploader(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateListThreshold(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateMaxFilesUpoaldedQuantity(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult validateValueSearchForm(Object newValue) {
		// TODO Auto-generated method stub
		return null;
	} 
} 