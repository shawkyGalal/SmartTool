package com.smartValue.database.map.auto;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.Attribute;
import com.smartValue.database.DataSet;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;

public abstract class _SysMnu extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "SYS_MNU"; 
 public static final String DB_TABLE_OWNER = "ICDB"; 

	public _SysMnu(){
	}

	 public DbTable getTable() 
	 { 
		return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String MNU_CODE ="MNU_CODE" ;

	public void setMnuCodeValue(java.lang.String   pm_mnuCode){
		this.getAttribute(MNU_CODE ).setValue( pm_mnuCode );
	}
 
	public java.lang.String getMnuCodeValue(){
		return (java.lang.String) this.getAttribute ( MNU_CODE).getValue()  ;
	}
 
	public Attribute getMnuCode(){
		return this.getAttribute ( MNU_CODE)  ;
	}

	public static final String MNU_PARENT ="MNU_PARENT" ;

	public void setMnuParentValue(java.lang.String   pm_mnuParent){
		this.getAttribute(MNU_PARENT ).setValue( pm_mnuParent );
	}
 
	public java.lang.String getMnuParentValue(){
		return (java.lang.String) this.getAttribute ( MNU_PARENT).getValue()  ;
	}
 
	public Attribute getMnuParent(){
		return this.getAttribute ( MNU_PARENT)  ;
	}

	public static final String MNU_SHORTCUT ="MNU_SHORTCUT" ;

	public void setMnuShortcutValue(java.lang.String   pm_mnuShortcut){
		this.getAttribute(MNU_SHORTCUT ).setValue( pm_mnuShortcut );
	}
 
	public java.lang.String getMnuShortcutValue(){
		return (java.lang.String) this.getAttribute ( MNU_SHORTCUT).getValue()  ;
	}
 
	public Attribute getMnuShortcut(){
		return this.getAttribute ( MNU_SHORTCUT)  ;
	}

	public static final String MNU_CALL_ID ="MNU_CALL_ID" ;

	public void setMnuCallIdValue(java.lang.String   pm_mnuCallId){
		this.getAttribute(MNU_CALL_ID ).setValue( pm_mnuCallId );
	}
 
	public java.lang.String getMnuCallIdValue(){
		return (java.lang.String) this.getAttribute ( MNU_CALL_ID).getValue()  ;
	}
 
	public Attribute getMnuCallId(){
		return this.getAttribute ( MNU_CALL_ID)  ;
	}

	public static final String MNU_TYPE ="MNU_TYPE" ;

	public void setMnuTypeValue(java.math.BigDecimal   pm_mnuType){
		this.getAttribute(MNU_TYPE ).setValue( pm_mnuType );
	}
 
	public java.math.BigDecimal getMnuTypeValue(){
		return (java.math.BigDecimal) this.getAttribute ( MNU_TYPE).getValue()  ;
	}
 
	public Attribute getMnuType(){
		return this.getAttribute ( MNU_TYPE)  ;
	}

	public static final String MNU_PRG_NAME ="MNU_PRG_NAME" ;

	public void setMnuPrgNameValue(java.lang.String   pm_mnuPrgName){
		this.getAttribute(MNU_PRG_NAME ).setValue( pm_mnuPrgName );
	}
 
	public java.lang.String getMnuPrgNameValue(){
		return (java.lang.String) this.getAttribute ( MNU_PRG_NAME).getValue()  ;
	}
 
	public Attribute getMnuPrgName(){
		return this.getAttribute ( MNU_PRG_NAME)  ;
	}

	public static final String MNU_PRG_ALIAS ="MNU_PRG_ALIAS" ;

	public void setMnuPrgAliasValue(java.lang.String   pm_mnuPrgAlias){
		this.getAttribute(MNU_PRG_ALIAS ).setValue( pm_mnuPrgAlias );
	}
 
	public java.lang.String getMnuPrgAliasValue(){
		return (java.lang.String) this.getAttribute ( MNU_PRG_ALIAS).getValue()  ;
	}
 
	public Attribute getMnuPrgAlias(){
		return this.getAttribute ( MNU_PRG_ALIAS)  ;
	}

	public static final String MNU_SQL ="MNU_SQL" ;

	public void setMnuSqlValue(java.lang.String   pm_mnuSql){
		this.getAttribute(MNU_SQL ).setValue( pm_mnuSql );
	}
 
	public java.lang.String getMnuSqlValue(){
		return (java.lang.String) this.getAttribute ( MNU_SQL).getValue()  ;
	}
 
	public Attribute getMnuSql(){
		return this.getAttribute ( MNU_SQL)  ;
	}

	public static final String MNU_ARG_TYP ="MNU_ARG_TYP" ;

	public void setMnuArgTypValue(java.lang.String   pm_mnuArgTyp){
		this.getAttribute(MNU_ARG_TYP ).setValue( pm_mnuArgTyp );
	}
 
	public java.lang.String getMnuArgTypValue(){
		return (java.lang.String) this.getAttribute ( MNU_ARG_TYP).getValue()  ;
	}
 
	public Attribute getMnuArgTyp(){
		return this.getAttribute ( MNU_ARG_TYP)  ;
	}

	public static final String MNU_ARG1 ="MNU_ARG1" ;

	public void setMnuArg1Value(java.lang.String   pm_mnuArg1){
		this.getAttribute(MNU_ARG1 ).setValue( pm_mnuArg1 );
	}
 
	public java.lang.String getMnuArg1Value(){
		return (java.lang.String) this.getAttribute ( MNU_ARG1).getValue()  ;
	}
 
	public Attribute getMnuArg1(){
		return this.getAttribute ( MNU_ARG1)  ;
	}

	public static final String MNU_ARG2 ="MNU_ARG2" ;

	public void setMnuArg2Value(java.lang.String   pm_mnuArg2){
		this.getAttribute(MNU_ARG2 ).setValue( pm_mnuArg2 );
	}
 
	public java.lang.String getMnuArg2Value(){
		return (java.lang.String) this.getAttribute ( MNU_ARG2).getValue()  ;
	}
 
	public Attribute getMnuArg2(){
		return this.getAttribute ( MNU_ARG2)  ;
	}

	public static final String MNU_ARG3 ="MNU_ARG3" ;

	public void setMnuArg3Value(java.lang.String   pm_mnuArg3){
		this.getAttribute(MNU_ARG3 ).setValue( pm_mnuArg3 );
	}
 
	public java.lang.String getMnuArg3Value(){
		return (java.lang.String) this.getAttribute ( MNU_ARG3).getValue()  ;
	}
 
	public Attribute getMnuArg3(){
		return this.getAttribute ( MNU_ARG3)  ;
	}

	public static final String MNU_ARG4 ="MNU_ARG4" ;

	public void setMnuArg4Value(java.lang.String   pm_mnuArg4){
		this.getAttribute(MNU_ARG4 ).setValue( pm_mnuArg4 );
	}
 
	public java.lang.String getMnuArg4Value(){
		return (java.lang.String) this.getAttribute ( MNU_ARG4).getValue()  ;
	}
 
	public Attribute getMnuArg4(){
		return this.getAttribute ( MNU_ARG4)  ;
	}

	public static final String MNU_FONT_NAME ="MNU_FONT_NAME" ;

	public void setMnuFontNameValue(java.lang.String   pm_mnuFontName){
		this.getAttribute(MNU_FONT_NAME ).setValue( pm_mnuFontName );
	}
 
	public java.lang.String getMnuFontNameValue(){
		return (java.lang.String) this.getAttribute ( MNU_FONT_NAME).getValue()  ;
	}
 
	public Attribute getMnuFontName(){
		return this.getAttribute ( MNU_FONT_NAME)  ;
	}

	public static final String MNU_FONT_SIZE ="MNU_FONT_SIZE" ;

	public void setMnuFontSizeValue(java.math.BigDecimal   pm_mnuFontSize){
		this.getAttribute(MNU_FONT_SIZE ).setValue( pm_mnuFontSize );
	}
 
	public java.math.BigDecimal getMnuFontSizeValue(){
		return (java.math.BigDecimal) this.getAttribute ( MNU_FONT_SIZE).getValue()  ;
	}
 
	public Attribute getMnuFontSize(){
		return this.getAttribute ( MNU_FONT_SIZE)  ;
	}

	public static final String MNU_FONT_BOLD ="MNU_FONT_BOLD" ;

	public void setMnuFontBoldValue(java.math.BigDecimal   pm_mnuFontBold){
		this.getAttribute(MNU_FONT_BOLD ).setValue( pm_mnuFontBold );
	}
 
	public java.math.BigDecimal getMnuFontBoldValue(){
		return (java.math.BigDecimal) this.getAttribute ( MNU_FONT_BOLD).getValue()  ;
	}
 
	public Attribute getMnuFontBold(){
		return this.getAttribute ( MNU_FONT_BOLD)  ;
	}

	public static final String MNU_FONT_COLOR_R ="MNU_FONT_COLOR_R" ;

	public void setMnuFontColorRValue(java.math.BigDecimal   pm_mnuFontColorR){
		this.getAttribute(MNU_FONT_COLOR_R ).setValue( pm_mnuFontColorR );
	}
 
	public java.math.BigDecimal getMnuFontColorRValue(){
		return (java.math.BigDecimal) this.getAttribute ( MNU_FONT_COLOR_R).getValue()  ;
	}
 
	public Attribute getMnuFontColorR(){
		return this.getAttribute ( MNU_FONT_COLOR_R)  ;
	}

	public static final String MNU_FONT_COLOR_G ="MNU_FONT_COLOR_G" ;

	public void setMnuFontColorGValue(java.math.BigDecimal   pm_mnuFontColorG){
		this.getAttribute(MNU_FONT_COLOR_G ).setValue( pm_mnuFontColorG );
	}
 
	public java.math.BigDecimal getMnuFontColorGValue(){
		return (java.math.BigDecimal) this.getAttribute ( MNU_FONT_COLOR_G).getValue()  ;
	}
 
	public Attribute getMnuFontColorG(){
		return this.getAttribute ( MNU_FONT_COLOR_G)  ;
	}

	public static final String MNU_FONT_COLOR_B ="MNU_FONT_COLOR_B" ;

	public void setMnuFontColorBValue(java.math.BigDecimal   pm_mnuFontColorB){
		this.getAttribute(MNU_FONT_COLOR_B ).setValue( pm_mnuFontColorB );
	}
 
	public java.math.BigDecimal getMnuFontColorBValue(){
		return (java.math.BigDecimal) this.getAttribute ( MNU_FONT_COLOR_B).getValue()  ;
	}
 
	public Attribute getMnuFontColorB(){
		return this.getAttribute ( MNU_FONT_COLOR_B)  ;
	}

	public static final String MNU_IMG ="MNU_IMG" ;

	public void setMnuImgValue(java.lang.String   pm_mnuImg){
		this.getAttribute(MNU_IMG ).setValue( pm_mnuImg );
	}
 
	public java.lang.String getMnuImgValue(){
		return (java.lang.String) this.getAttribute ( MNU_IMG).getValue()  ;
	}
 
	public Attribute getMnuImg(){
		return this.getAttribute ( MNU_IMG)  ;
	}

	public static final String MNU_STYL ="MNU_STYL" ;

	public void setMnuStylValue(java.lang.String   pm_mnuStyl){
		this.getAttribute(MNU_STYL ).setValue( pm_mnuStyl );
	}
 
	public java.lang.String getMnuStylValue(){
		return (java.lang.String) this.getAttribute ( MNU_STYL).getValue()  ;
	}
 
	public Attribute getMnuStyl(){
		return this.getAttribute ( MNU_STYL)  ;
	}

	public static final String MNU_STATUS ="MNU_STATUS" ;

	public void setMnuStatusValue(java.math.BigDecimal   pm_mnuStatus){
		this.getAttribute(MNU_STATUS ).setValue( pm_mnuStatus );
	}
 
	public java.math.BigDecimal getMnuStatusValue(){
		return (java.math.BigDecimal) this.getAttribute ( MNU_STATUS).getValue()  ;
	}
 
	public Attribute getMnuStatus(){
		return this.getAttribute ( MNU_STATUS)  ;
	}

	public static final String SERVICE_CLASS ="SERVICE_CLASS" ;

	public void setServiceClassValue(java.lang.String   pm_serviceClass){
		this.getAttribute(SERVICE_CLASS ).setValue( pm_serviceClass );
	}
 
	public java.lang.String getServiceClassValue(){
		return (java.lang.String) this.getAttribute ( SERVICE_CLASS).getValue()  ;
	}
 
	public Attribute getServiceClass(){
		return this.getAttribute ( SERVICE_CLASS)  ;
	}

	public static final String DATE_CREATED ="DATE_CREATED" ;

	public void setDateCreatedValue(java.sql.Timestamp   pm_dateCreated){
		this.getAttribute(DATE_CREATED ).setValue( pm_dateCreated );
	}
 
	public java.sql.Timestamp getDateCreatedValue(){
		return (java.sql.Timestamp) this.getAttribute ( DATE_CREATED).getValue()  ;
	}
 
	public Attribute getDateCreated(){
		return this.getAttribute ( DATE_CREATED)  ;
	}

	public static final String MNU_TXT ="MNU_TXT" ;

	public void setMnuTxtValue(java.lang.String   pm_mnuTxt){
		this.getAttribute(MNU_TXT ).setValue( pm_mnuTxt );
	}
 
	public java.lang.String getMnuTxtValue(){
		return (java.lang.String) this.getAttribute ( MNU_TXT).getValue()  ;
	}
 
	public Attribute getMnuTxt(){
		return this.getAttribute ( MNU_TXT)  ;
	}

	public static final String MNU_TXT_ ="MNU_TXT_" ;

	public void setMnuTxt_Value(java.lang.String   pm_mnuTxt_){
		this.getAttribute(MNU_TXT_ ).setValue( pm_mnuTxt_ );
	}
 
	public java.lang.String getMnuTxt_Value(){
		return (java.lang.String) this.getAttribute ( MNU_TXT_).getValue()  ;
	}
 
	public Attribute getMnuTxt_(){
		return this.getAttribute ( MNU_TXT_)  ;
	}

	public static final String MNU_DESC ="MNU_DESC" ;

	public void setMnuDescValue(java.lang.String   pm_mnuDesc){
		this.getAttribute(MNU_DESC ).setValue( pm_mnuDesc );
	}
 
	public java.lang.String getMnuDescValue(){
		return (java.lang.String) this.getAttribute ( MNU_DESC).getValue()  ;
	}
 
	public Attribute getMnuDesc(){
		return this.getAttribute ( MNU_DESC)  ;
	}

	public static final String MNU_DESC_ ="MNU_DESC_" ;

	public void setMnuDesc_Value(java.lang.String   pm_mnuDesc_){
		this.getAttribute(MNU_DESC_ ).setValue( pm_mnuDesc_ );
	}
 
	public java.lang.String getMnuDesc_Value(){
		return (java.lang.String) this.getAttribute ( MNU_DESC_).getValue()  ;
	}
 
	public Attribute getMnuDesc_(){
		return this.getAttribute ( MNU_DESC_)  ;
	}

	public static final String MNU_URL ="MNU_URL" ;

	public void setMnuUrlValue(java.lang.String   pm_mnuUrl){
		this.getAttribute(MNU_URL ).setValue( pm_mnuUrl );
	}
 
	public java.lang.String getMnuUrlValue(){
		return (java.lang.String) this.getAttribute ( MNU_URL).getValue()  ;
	}
 
	public Attribute getMnuUrl(){
		return this.getAttribute ( MNU_URL)  ;
	}

	public static final String MNU_RPT ="MNU_RPT" ;

	public void setMnuRptValue(java.lang.String   pm_mnuRpt){
		this.getAttribute(MNU_RPT ).setValue( pm_mnuRpt );
	}
 
	public java.lang.String getMnuRptValue(){
		return (java.lang.String) this.getAttribute ( MNU_RPT).getValue()  ;
	}
 
	public Attribute getMnuRpt(){
		return this.getAttribute ( MNU_RPT)  ;
	}

	public static final String VPDATABASETABLE ="VPDATABASETABLE" ;

	public void setVpdatabasetableValue(java.lang.String   pm_vpdatabasetable){
		this.getAttribute(VPDATABASETABLE ).setValue( pm_vpdatabasetable );
	}
 
	public java.lang.String getVpdatabasetableValue(){
		return (java.lang.String) this.getAttribute ( VPDATABASETABLE).getValue()  ;
	}
 
	public Attribute getVpdatabasetable(){
		return this.getAttribute ( VPDATABASETABLE)  ;
	}

	public static final String NEEDTOBEDELETEDLATER ="NEEDTOBEDELETEDLATER" ;

	public void setNeedtobedeletedlaterValue(java.lang.String   pm_needtobedeletedlater){
		this.getAttribute(NEEDTOBEDELETEDLATER ).setValue( pm_needtobedeletedlater );
	}
 
	public java.lang.String getNeedtobedeletedlaterValue(){
		return (java.lang.String) this.getAttribute ( NEEDTOBEDELETEDLATER).getValue()  ;
	}
 
	public Attribute getNeedtobedeletedlater(){
		return this.getAttribute ( NEEDTOBEDELETEDLATER)  ;
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

	public void setMnuTxtAutoLang(java.lang.String   pm_mnuTxt){
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		this.getMnuTxt().setValue( pm_mnuTxt );
		 else 
		this.getMnuTxt_().setValue( pm_mnuTxt );
	}
 
	public Attribute getMnuTxtAutoLang(){
		 Attribute result ;
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		 result =   this.getMnuTxt();
		 else 
		 result =   this.getMnuTxt_();
		 return result;
	}

	public void setMnuDescAutoLang(java.lang.String   pm_mnuDesc){
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		this.getMnuDesc().setValue( pm_mnuDesc );
		 else 
		this.getMnuDesc_().setValue( pm_mnuDesc );
	}
 
	public Attribute getMnuDescAutoLang(){
		 Attribute result ;
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		 result =   this.getMnuDesc();
		 else 
		 result =   this.getMnuDesc_();
		 return result;
	}

	// ********Start of Child DataSets getter methods******** 
	// *******End of Child DataSets getter methods******** 
}