package com.smartValue.database.map.auto;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.Attribute;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;

public abstract class _AccMasAccount extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "ACC_MAS_ACCOUNT"; 
 public static final String DB_TABLE_OWNER = "ACC";

	public _AccMasAccount(){
	}

	 public DbTable getTable() 
	 { 
		return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String COMMUNITY_ID ="COMMUNITY_ID" ;

	public void setCommunityIdValue(java.math.BigDecimal   pm_communityId){
		this.getAttribute(COMMUNITY_ID ).setValue( pm_communityId );
	}
 
	public java.math.BigDecimal getCommunityIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( COMMUNITY_ID).getValue()  ;
	}
 
	public Attribute getCommunityId(){
		return this.getAttribute ( COMMUNITY_ID)  ;
	}

	public static final String ACCOUNT_ID ="ACCOUNT_ID" ;

	public void setAccountIdValue(java.lang.String   pm_accountId){
		this.getAttribute(ACCOUNT_ID ).setValue( pm_accountId );
	}
 
	public java.lang.String getAccountIdValue(){
		return (java.lang.String) this.getAttribute ( ACCOUNT_ID).getValue()  ;
	}
 
	public Attribute getAccountId(){
		return this.getAttribute ( ACCOUNT_ID)  ;
	}

	public static final String ACCOUNT_NAME ="ACCOUNT_NAME" ;

	public void setAccountNameValue(java.lang.String   pm_accountName){
		this.getAttribute(ACCOUNT_NAME ).setValue( pm_accountName );
	}
 
	public java.lang.String getAccountNameValue(){
		return (java.lang.String) this.getAttribute ( ACCOUNT_NAME).getValue()  ;
	}
 
	public Attribute getAccountName(){
		return this.getAttribute ( ACCOUNT_NAME)  ;
	}

	public static final String ACCOUNT_NAME_ ="ACCOUNT_NAME_" ;

	public void setAccountName_Value(java.lang.String   pm_accountName_){
		this.getAttribute(ACCOUNT_NAME_ ).setValue( pm_accountName_ );
	}
 
	public java.lang.String getAccountName_Value(){
		return (java.lang.String) this.getAttribute ( ACCOUNT_NAME_).getValue()  ;
	}
 
	public Attribute getAccountName_(){
		return this.getAttribute ( ACCOUNT_NAME_)  ;
	}

	public static final String ACCOUNT_DESCRIPTION ="ACCOUNT_DESCRIPTION" ;

	public void setAccountDescriptionValue(java.lang.String   pm_accountDescription){
		this.getAttribute(ACCOUNT_DESCRIPTION ).setValue( pm_accountDescription );
	}
 
	public java.lang.String getAccountDescriptionValue(){
		return (java.lang.String) this.getAttribute ( ACCOUNT_DESCRIPTION).getValue()  ;
	}
 
	public Attribute getAccountDescription(){
		return this.getAttribute ( ACCOUNT_DESCRIPTION)  ;
	}

	public static final String ACCOUNT_DESCRIPTION_ ="ACCOUNT_DESCRIPTION_" ;

	public void setAccountDescription_Value(java.lang.String   pm_accountDescription_){
		this.getAttribute(ACCOUNT_DESCRIPTION_ ).setValue( pm_accountDescription_ );
	}
 
	public java.lang.String getAccountDescription_Value(){
		return (java.lang.String) this.getAttribute ( ACCOUNT_DESCRIPTION_).getValue()  ;
	}
 
	public Attribute getAccountDescription_(){
		return this.getAttribute ( ACCOUNT_DESCRIPTION_)  ;
	}

	public static final String ACCOUNT_GROUP_ID ="ACCOUNT_GROUP_ID" ;

	public void setAccountGroupIdValue(java.lang.String   pm_accountGroupId){
		this.getAttribute(ACCOUNT_GROUP_ID ).setValue( pm_accountGroupId );
	}
 
	public java.lang.String getAccountGroupIdValue(){
		return (java.lang.String) this.getAttribute ( ACCOUNT_GROUP_ID).getValue()  ;
	}
 
	public Attribute getAccountGroupId(){
		return this.getAttribute ( ACCOUNT_GROUP_ID)  ;
	}

	public static final String ACCOUNT_BLOCK_FLAG ="ACCOUNT_BLOCK_FLAG" ;

	public void setAccountBlockFlagValue(java.lang.String   pm_accountBlockFlag){
		this.getAttribute(ACCOUNT_BLOCK_FLAG ).setValue( pm_accountBlockFlag );
	}
 
	public java.lang.String getAccountBlockFlagValue(){
		return (java.lang.String) this.getAttribute ( ACCOUNT_BLOCK_FLAG).getValue()  ;
	}
 
	public Attribute getAccountBlockFlag(){
		return this.getAttribute ( ACCOUNT_BLOCK_FLAG)  ;
	}

	public static final String ACCOUNT_BALANCE_FLAG ="ACCOUNT_BALANCE_FLAG" ;

	public void setAccountBalanceFlagValue(java.lang.String   pm_accountBalanceFlag){
		this.getAttribute(ACCOUNT_BALANCE_FLAG ).setValue( pm_accountBalanceFlag );
	}
 
	public java.lang.String getAccountBalanceFlagValue(){
		return (java.lang.String) this.getAttribute ( ACCOUNT_BALANCE_FLAG).getValue()  ;
	}
 
	public Attribute getAccountBalanceFlag(){
		return this.getAttribute ( ACCOUNT_BALANCE_FLAG)  ;
	}

	public static final String ACCOUNT_PROFIT_OR_LOSS_FLAG ="ACCOUNT_PROFIT_OR_LOSS_FLAG" ;

	public void setAccountProfitOrLossFlagValue(java.lang.String   pm_accountProfitOrLossFlag){
		this.getAttribute(ACCOUNT_PROFIT_OR_LOSS_FLAG ).setValue( pm_accountProfitOrLossFlag );
	}
 
	public java.lang.String getAccountProfitOrLossFlagValue(){
		return (java.lang.String) this.getAttribute ( ACCOUNT_PROFIT_OR_LOSS_FLAG).getValue()  ;
	}
 
	public Attribute getAccountProfitOrLossFlag(){
		return this.getAttribute ( ACCOUNT_PROFIT_OR_LOSS_FLAG)  ;
	}

	public static final String ACCOUNT_RECEIVABLE_FLAG ="ACCOUNT_RECEIVABLE_FLAG" ;

	public void setAccountReceivableFlagValue(java.lang.String   pm_accountReceivableFlag){
		this.getAttribute(ACCOUNT_RECEIVABLE_FLAG ).setValue( pm_accountReceivableFlag );
	}
 
	public java.lang.String getAccountReceivableFlagValue(){
		return (java.lang.String) this.getAttribute ( ACCOUNT_RECEIVABLE_FLAG).getValue()  ;
	}
 
	public Attribute getAccountReceivableFlag(){
		return this.getAttribute ( ACCOUNT_RECEIVABLE_FLAG)  ;
	}

	public static final String ACCOUNT_PAYABLE_FLAG ="ACCOUNT_PAYABLE_FLAG" ;

	public void setAccountPayableFlagValue(java.lang.String   pm_accountPayableFlag){
		this.getAttribute(ACCOUNT_PAYABLE_FLAG ).setValue( pm_accountPayableFlag );
	}
 
	public java.lang.String getAccountPayableFlagValue(){
		return (java.lang.String) this.getAttribute ( ACCOUNT_PAYABLE_FLAG).getValue()  ;
	}
 
	public Attribute getAccountPayableFlag(){
		return this.getAttribute ( ACCOUNT_PAYABLE_FLAG)  ;
	}

	public static final String ACCOUNT_VAT_TYPE ="ACCOUNT_VAT_TYPE" ;

	public void setAccountVatTypeValue(java.lang.String   pm_accountVatType){
		this.getAttribute(ACCOUNT_VAT_TYPE ).setValue( pm_accountVatType );
	}
 
	public java.lang.String getAccountVatTypeValue(){
		return (java.lang.String) this.getAttribute ( ACCOUNT_VAT_TYPE).getValue()  ;
	}
 
	public Attribute getAccountVatType(){
		return this.getAttribute ( ACCOUNT_VAT_TYPE)  ;
	}

	public static final String ACCOUNT_FIXED_ASSET_TYPE ="ACCOUNT_FIXED_ASSET_TYPE" ;

	public void setAccountFixedAssetTypeValue(java.lang.String   pm_accountFixedAssetType){
		this.getAttribute(ACCOUNT_FIXED_ASSET_TYPE ).setValue( pm_accountFixedAssetType );
	}
 
	public java.lang.String getAccountFixedAssetTypeValue(){
		return (java.lang.String) this.getAttribute ( ACCOUNT_FIXED_ASSET_TYPE).getValue()  ;
	}
 
	public Attribute getAccountFixedAssetType(){
		return this.getAttribute ( ACCOUNT_FIXED_ASSET_TYPE)  ;
	}

	public static final String ACCOUNT_PERIOD_FLAG ="ACCOUNT_PERIOD_FLAG" ;

	public void setAccountPeriodFlagValue(java.lang.String   pm_accountPeriodFlag){
		this.getAttribute(ACCOUNT_PERIOD_FLAG ).setValue( pm_accountPeriodFlag );
	}
 
	public java.lang.String getAccountPeriodFlagValue(){
		return (java.lang.String) this.getAttribute ( ACCOUNT_PERIOD_FLAG).getValue()  ;
	}
 
	public Attribute getAccountPeriodFlag(){
		return this.getAttribute ( ACCOUNT_PERIOD_FLAG)  ;
	}

	public static final String ACCOUNT_REEVALUATION_FLAG ="ACCOUNT_REEVALUATION_FLAG" ;

	public void setAccountReevaluationFlagValue(java.lang.String   pm_accountReevaluationFlag){
		this.getAttribute(ACCOUNT_REEVALUATION_FLAG ).setValue( pm_accountReevaluationFlag );
	}
 
	public java.lang.String getAccountReevaluationFlagValue(){
		return (java.lang.String) this.getAttribute ( ACCOUNT_REEVALUATION_FLAG).getValue()  ;
	}
 
	public Attribute getAccountReevaluationFlag(){
		return this.getAttribute ( ACCOUNT_REEVALUATION_FLAG)  ;
	}

	public static final String ACCOUNT_START_VALIDITY_DATE ="ACCOUNT_START_VALIDITY_DATE" ;

	public void setAccountStartValidityDateValue(java.sql.Timestamp   pm_accountStartValidityDate){
		this.getAttribute(ACCOUNT_START_VALIDITY_DATE ).setValue( pm_accountStartValidityDate );
	}
 
	public java.sql.Timestamp getAccountStartValidityDateValue(){
		return (java.sql.Timestamp) this.getAttribute ( ACCOUNT_START_VALIDITY_DATE).getValue()  ;
	}
 
	public Attribute getAccountStartValidityDate(){
		return this.getAttribute ( ACCOUNT_START_VALIDITY_DATE)  ;
	}

	public static final String ACCOUNT_END_VALIDITY_DATE ="ACCOUNT_END_VALIDITY_DATE" ;

	public void setAccountEndValidityDateValue(java.sql.Timestamp   pm_accountEndValidityDate){
		this.getAttribute(ACCOUNT_END_VALIDITY_DATE ).setValue( pm_accountEndValidityDate );
	}
 
	public java.sql.Timestamp getAccountEndValidityDateValue(){
		return (java.sql.Timestamp) this.getAttribute ( ACCOUNT_END_VALIDITY_DATE).getValue()  ;
	}
 
	public Attribute getAccountEndValidityDate(){
		return this.getAttribute ( ACCOUNT_END_VALIDITY_DATE)  ;
	}

	public static final String ACCOUNT_BUDGET_FLAG ="ACCOUNT_BUDGET_FLAG" ;

	public void setAccountBudgetFlagValue(java.lang.String   pm_accountBudgetFlag){
		this.getAttribute(ACCOUNT_BUDGET_FLAG ).setValue( pm_accountBudgetFlag );
	}
 
	public java.lang.String getAccountBudgetFlagValue(){
		return (java.lang.String) this.getAttribute ( ACCOUNT_BUDGET_FLAG).getValue()  ;
	}
 
	public Attribute getAccountBudgetFlag(){
		return this.getAttribute ( ACCOUNT_BUDGET_FLAG)  ;
	}

	public static final String ACCOUNT_COST_TRANSACTION_FLAG ="ACCOUNT_COST_TRANSACTION_FLAG" ;

	public void setAccountCostTransactionFlagValue(java.lang.String   pm_accountCostTransactionFlag){
		this.getAttribute(ACCOUNT_COST_TRANSACTION_FLAG ).setValue( pm_accountCostTransactionFlag );
	}
 
	public java.lang.String getAccountCostTransactionFlagValue(){
		return (java.lang.String) this.getAttribute ( ACCOUNT_COST_TRANSACTION_FLAG).getValue()  ;
	}
 
	public Attribute getAccountCostTransactionFlag(){
		return this.getAttribute ( ACCOUNT_COST_TRANSACTION_FLAG)  ;
	}

	public static final String ACCOUNT_BANK_FLAG ="ACCOUNT_BANK_FLAG" ;

	public void setAccountBankFlagValue(java.lang.String   pm_accountBankFlag){
		this.getAttribute(ACCOUNT_BANK_FLAG ).setValue( pm_accountBankFlag );
	}
 
	public java.lang.String getAccountBankFlagValue(){
		return (java.lang.String) this.getAttribute ( ACCOUNT_BANK_FLAG).getValue()  ;
	}
 
	public Attribute getAccountBankFlag(){
		return this.getAttribute ( ACCOUNT_BANK_FLAG)  ;
	}

	public static final String ACCOUNT_RECONCILIATION_FLAG ="ACCOUNT_RECONCILIATION_FLAG" ;

	public void setAccountReconciliationFlagValue(java.lang.String   pm_accountReconciliationFlag){
		this.getAttribute(ACCOUNT_RECONCILIATION_FLAG ).setValue( pm_accountReconciliationFlag );
	}
 
	public java.lang.String getAccountReconciliationFlagValue(){
		return (java.lang.String) this.getAttribute ( ACCOUNT_RECONCILIATION_FLAG).getValue()  ;
	}
 
	public Attribute getAccountReconciliationFlag(){
		return this.getAttribute ( ACCOUNT_RECONCILIATION_FLAG)  ;
	}

	public static final String ACCOUNT_SUSPENSE_FLAG ="ACCOUNT_SUSPENSE_FLAG" ;

	public void setAccountSuspenseFlagValue(java.lang.String   pm_accountSuspenseFlag){
		this.getAttribute(ACCOUNT_SUSPENSE_FLAG ).setValue( pm_accountSuspenseFlag );
	}
 
	public java.lang.String getAccountSuspenseFlagValue(){
		return (java.lang.String) this.getAttribute ( ACCOUNT_SUSPENSE_FLAG).getValue()  ;
	}
 
	public Attribute getAccountSuspenseFlag(){
		return this.getAttribute ( ACCOUNT_SUSPENSE_FLAG)  ;
	}

	public static final String ACCOUNT_PETTY_CASH_FLAG ="ACCOUNT_PETTY_CASH_FLAG" ;

	public void setAccountPettyCashFlagValue(java.lang.String   pm_accountPettyCashFlag){
		this.getAttribute(ACCOUNT_PETTY_CASH_FLAG ).setValue( pm_accountPettyCashFlag );
	}
 
	public java.lang.String getAccountPettyCashFlagValue(){
		return (java.lang.String) this.getAttribute ( ACCOUNT_PETTY_CASH_FLAG).getValue()  ;
	}
 
	public Attribute getAccountPettyCashFlag(){
		return this.getAttribute ( ACCOUNT_PETTY_CASH_FLAG)  ;
	}

	public static final String ACCOUNT_INVENTORY_TYPE ="ACCOUNT_INVENTORY_TYPE" ;

	public void setAccountInventoryTypeValue(java.lang.String   pm_accountInventoryType){
		this.getAttribute(ACCOUNT_INVENTORY_TYPE ).setValue( pm_accountInventoryType );
	}
 
	public java.lang.String getAccountInventoryTypeValue(){
		return (java.lang.String) this.getAttribute ( ACCOUNT_INVENTORY_TYPE).getValue()  ;
	}
 
	public Attribute getAccountInventoryType(){
		return this.getAttribute ( ACCOUNT_INVENTORY_TYPE)  ;
	}

	public static final String ACCOUNT_CURRENCY_CONNECT_TYPE ="ACCOUNT_CURRENCY_CONNECT_TYPE" ;

	public void setAccountCurrencyConnectTypeValue(java.lang.String   pm_accountCurrencyConnectType){
		this.getAttribute(ACCOUNT_CURRENCY_CONNECT_TYPE ).setValue( pm_accountCurrencyConnectType );
	}
 
	public java.lang.String getAccountCurrencyConnectTypeValue(){
		return (java.lang.String) this.getAttribute ( ACCOUNT_CURRENCY_CONNECT_TYPE).getValue()  ;
	}
 
	public Attribute getAccountCurrencyConnectType(){
		return this.getAttribute ( ACCOUNT_CURRENCY_CONNECT_TYPE)  ;
	}

	public static final String CURRENCY_ID ="CURRENCY_ID" ;

	public void setCurrencyIdValue(java.lang.String   pm_currencyId){
		this.getAttribute(CURRENCY_ID ).setValue( pm_currencyId );
	}
 
	public java.lang.String getCurrencyIdValue(){
		return (java.lang.String) this.getAttribute ( CURRENCY_ID).getValue()  ;
	}
 
	public Attribute getCurrencyId(){
		return this.getAttribute ( CURRENCY_ID)  ;
	}

	public static final String ACCOUNT_SIGN_TYPE ="ACCOUNT_SIGN_TYPE" ;

	public void setAccountSignTypeValue(java.lang.String   pm_accountSignType){
		this.getAttribute(ACCOUNT_SIGN_TYPE ).setValue( pm_accountSignType );
	}
 
	public java.lang.String getAccountSignTypeValue(){
		return (java.lang.String) this.getAttribute ( ACCOUNT_SIGN_TYPE).getValue()  ;
	}
 
	public Attribute getAccountSignType(){
		return this.getAttribute ( ACCOUNT_SIGN_TYPE)  ;
	}

	public static final String ACCOUNT_QTY_TYPE ="ACCOUNT_QTY_TYPE" ;

	public void setAccountQtyTypeValue(java.lang.String   pm_accountQtyType){
		this.getAttribute(ACCOUNT_QTY_TYPE ).setValue( pm_accountQtyType );
	}
 
	public java.lang.String getAccountQtyTypeValue(){
		return (java.lang.String) this.getAttribute ( ACCOUNT_QTY_TYPE).getValue()  ;
	}
 
	public Attribute getAccountQtyType(){
		return this.getAttribute ( ACCOUNT_QTY_TYPE)  ;
	}

	public static final String ACCOUNT_TEXT_MANDATORY_FLAG ="ACCOUNT_TEXT_MANDATORY_FLAG" ;

	public void setAccountTextMandatoryFlagValue(java.lang.String   pm_accountTextMandatoryFlag){
		this.getAttribute(ACCOUNT_TEXT_MANDATORY_FLAG ).setValue( pm_accountTextMandatoryFlag );
	}
 
	public java.lang.String getAccountTextMandatoryFlagValue(){
		return (java.lang.String) this.getAttribute ( ACCOUNT_TEXT_MANDATORY_FLAG).getValue()  ;
	}
 
	public Attribute getAccountTextMandatoryFlag(){
		return this.getAttribute ( ACCOUNT_TEXT_MANDATORY_FLAG)  ;
	}

	public static final String DIMENSION_1_ID ="DIMENSION_1_ID" ;

	public void setDimension1IdValue(java.math.BigDecimal   pm_dimension1Id){
		this.getAttribute(DIMENSION_1_ID ).setValue( pm_dimension1Id );
	}
 
	public java.math.BigDecimal getDimension1IdValue(){
		return (java.math.BigDecimal) this.getAttribute ( DIMENSION_1_ID).getValue()  ;
	}
 
	public Attribute getDimension1Id(){
		return this.getAttribute ( DIMENSION_1_ID)  ;
	}

	public static final String DIMENSION_1_CHECK_TYPE ="DIMENSION_1_CHECK_TYPE" ;

	public void setDimension1CheckTypeValue(java.lang.String   pm_dimension1CheckType){
		this.getAttribute(DIMENSION_1_CHECK_TYPE ).setValue( pm_dimension1CheckType );
	}
 
	public java.lang.String getDimension1CheckTypeValue(){
		return (java.lang.String) this.getAttribute ( DIMENSION_1_CHECK_TYPE).getValue()  ;
	}
 
	public Attribute getDimension1CheckType(){
		return this.getAttribute ( DIMENSION_1_CHECK_TYPE)  ;
	}

	public static final String DIMENSION_2_ID ="DIMENSION_2_ID" ;

	public void setDimension2IdValue(java.math.BigDecimal   pm_dimension2Id){
		this.getAttribute(DIMENSION_2_ID ).setValue( pm_dimension2Id );
	}
 
	public java.math.BigDecimal getDimension2IdValue(){
		return (java.math.BigDecimal) this.getAttribute ( DIMENSION_2_ID).getValue()  ;
	}
 
	public Attribute getDimension2Id(){
		return this.getAttribute ( DIMENSION_2_ID)  ;
	}

	public static final String DIMENSION_2_CHECK_TYPE ="DIMENSION_2_CHECK_TYPE" ;

	public void setDimension2CheckTypeValue(java.lang.String   pm_dimension2CheckType){
		this.getAttribute(DIMENSION_2_CHECK_TYPE ).setValue( pm_dimension2CheckType );
	}
 
	public java.lang.String getDimension2CheckTypeValue(){
		return (java.lang.String) this.getAttribute ( DIMENSION_2_CHECK_TYPE).getValue()  ;
	}
 
	public Attribute getDimension2CheckType(){
		return this.getAttribute ( DIMENSION_2_CHECK_TYPE)  ;
	}

	public static final String DIMENSION_3_ID ="DIMENSION_3_ID" ;

	public void setDimension3IdValue(java.math.BigDecimal   pm_dimension3Id){
		this.getAttribute(DIMENSION_3_ID ).setValue( pm_dimension3Id );
	}
 
	public java.math.BigDecimal getDimension3IdValue(){
		return (java.math.BigDecimal) this.getAttribute ( DIMENSION_3_ID).getValue()  ;
	}
 
	public Attribute getDimension3Id(){
		return this.getAttribute ( DIMENSION_3_ID)  ;
	}

	public static final String DIMENSION_3_CHECK_TYPE ="DIMENSION_3_CHECK_TYPE" ;

	public void setDimension3CheckTypeValue(java.lang.String   pm_dimension3CheckType){
		this.getAttribute(DIMENSION_3_CHECK_TYPE ).setValue( pm_dimension3CheckType );
	}
 
	public java.lang.String getDimension3CheckTypeValue(){
		return (java.lang.String) this.getAttribute ( DIMENSION_3_CHECK_TYPE).getValue()  ;
	}
 
	public Attribute getDimension3CheckType(){
		return this.getAttribute ( DIMENSION_3_CHECK_TYPE)  ;
	}

	public static final String DIMENSION_4_ID ="DIMENSION_4_ID" ;

	public void setDimension4IdValue(java.math.BigDecimal   pm_dimension4Id){
		this.getAttribute(DIMENSION_4_ID ).setValue( pm_dimension4Id );
	}
 
	public java.math.BigDecimal getDimension4IdValue(){
		return (java.math.BigDecimal) this.getAttribute ( DIMENSION_4_ID).getValue()  ;
	}
 
	public Attribute getDimension4Id(){
		return this.getAttribute ( DIMENSION_4_ID)  ;
	}

	public static final String DIMENSION_4_CHECK_TYPE ="DIMENSION_4_CHECK_TYPE" ;

	public void setDimension4CheckTypeValue(java.lang.String   pm_dimension4CheckType){
		this.getAttribute(DIMENSION_4_CHECK_TYPE ).setValue( pm_dimension4CheckType );
	}
 
	public java.lang.String getDimension4CheckTypeValue(){
		return (java.lang.String) this.getAttribute ( DIMENSION_4_CHECK_TYPE).getValue()  ;
	}
 
	public Attribute getDimension4CheckType(){
		return this.getAttribute ( DIMENSION_4_CHECK_TYPE)  ;
	}

	public static final String DIMENSION_5_ID ="DIMENSION_5_ID" ;

	public void setDimension5IdValue(java.math.BigDecimal   pm_dimension5Id){
		this.getAttribute(DIMENSION_5_ID ).setValue( pm_dimension5Id );
	}
 
	public java.math.BigDecimal getDimension5IdValue(){
		return (java.math.BigDecimal) this.getAttribute ( DIMENSION_5_ID).getValue()  ;
	}
 
	public Attribute getDimension5Id(){
		return this.getAttribute ( DIMENSION_5_ID)  ;
	}

	public static final String DIMENSION_5_CHECK_TYPE ="DIMENSION_5_CHECK_TYPE" ;

	public void setDimension5CheckTypeValue(java.lang.String   pm_dimension5CheckType){
		this.getAttribute(DIMENSION_5_CHECK_TYPE ).setValue( pm_dimension5CheckType );
	}
 
	public java.lang.String getDimension5CheckTypeValue(){
		return (java.lang.String) this.getAttribute ( DIMENSION_5_CHECK_TYPE).getValue()  ;
	}
 
	public Attribute getDimension5CheckType(){
		return this.getAttribute ( DIMENSION_5_CHECK_TYPE)  ;
	}

	public static final String DIMENSION_6_ID ="DIMENSION_6_ID" ;

	public void setDimension6IdValue(java.math.BigDecimal   pm_dimension6Id){
		this.getAttribute(DIMENSION_6_ID ).setValue( pm_dimension6Id );
	}
 
	public java.math.BigDecimal getDimension6IdValue(){
		return (java.math.BigDecimal) this.getAttribute ( DIMENSION_6_ID).getValue()  ;
	}
 
	public Attribute getDimension6Id(){
		return this.getAttribute ( DIMENSION_6_ID)  ;
	}

	public static final String DIMENSION_6_CHECK_TYPE ="DIMENSION_6_CHECK_TYPE" ;

	public void setDimension6CheckTypeValue(java.lang.String   pm_dimension6CheckType){
		this.getAttribute(DIMENSION_6_CHECK_TYPE ).setValue( pm_dimension6CheckType );
	}
 
	public java.lang.String getDimension6CheckTypeValue(){
		return (java.lang.String) this.getAttribute ( DIMENSION_6_CHECK_TYPE).getValue()  ;
	}
 
	public Attribute getDimension6CheckType(){
		return this.getAttribute ( DIMENSION_6_CHECK_TYPE)  ;
	}

	public void setAccountNameAutoLang(java.lang.String   pm_accountName){
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		this.getAccountName().setValue( pm_accountName );
		 else 
		this.getAccountName_().setValue( pm_accountName );
	}
 
	public Attribute getAccountNameAutoLang(){
		 Attribute result ;
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		 result =   this.getAccountName();
		 else 
		 result =   this.getAccountName_();
		 return result;
	}

	public void setAccountDescriptionAutoLang(java.lang.String   pm_accountDescription){
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		this.getAccountDescription().setValue( pm_accountDescription );
		 else 
		this.getAccountDescription_().setValue( pm_accountDescription );
	}
 
	public Attribute getAccountDescriptionAutoLang(){
		 Attribute result ;
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		 result =   this.getAccountDescription();
		 else 
		 result =   this.getAccountDescription_();
		 return result;
	}
}