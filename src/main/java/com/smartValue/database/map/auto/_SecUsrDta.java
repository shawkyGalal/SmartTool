package com.smartValue.database.map.auto;

import com.smartValue.database.Attribute;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;

public abstract class _SecUsrDta extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "SEC_USR_DTA"; 
 public static final String DB_TABLE_OWNER = "ICDB"; 

	public _SecUsrDta(){
	}

	 public DbTable getTable() 
	 { 
		return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String USR_NAME ="USR_NAME" ;

	public void setUsrNameValue(java.lang.String   pm_usrName){
		this.getAttribute(USR_NAME ).setValue( pm_usrName );
	}
 
	public java.lang.String getUsrNameValue(){
		return (java.lang.String) this.getAttribute ( USR_NAME).getValue()  ;
	}
 
	public Attribute getUsrName(){
		return this.getAttribute ( USR_NAME)  ;
	}

	public static final String USR_TRN_TYPE ="USR_TRN_TYPE" ;

	public void setUsrTrnTypeValue(java.math.BigDecimal   pm_usrTrnType){
		this.getAttribute(USR_TRN_TYPE ).setValue( pm_usrTrnType );
	}
 
	public java.math.BigDecimal getUsrTrnTypeValue(){
		return (java.math.BigDecimal) this.getAttribute ( USR_TRN_TYPE).getValue()  ;
	}
 
	public Attribute getUsrTrnType(){
		return this.getAttribute ( USR_TRN_TYPE)  ;
	}

	public static final String USR_SEC_TYPE ="USR_SEC_TYPE" ;

	public void setUsrSecTypeValue(java.math.BigDecimal   pm_usrSecType){
		this.getAttribute(USR_SEC_TYPE ).setValue( pm_usrSecType );
	}
 
	public java.math.BigDecimal getUsrSecTypeValue(){
		return (java.math.BigDecimal) this.getAttribute ( USR_SEC_TYPE).getValue()  ;
	}
 
	public Attribute getUsrSecType(){
		return this.getAttribute ( USR_SEC_TYPE)  ;
	}

	public static final String USR_TYPE ="USR_TYPE" ;

	public void setUsrTypeValue(java.math.BigDecimal   pm_usrType){
		this.getAttribute(USR_TYPE ).setValue( pm_usrType );
	}
 
	public java.math.BigDecimal getUsrTypeValue(){
		return (java.math.BigDecimal) this.getAttribute ( USR_TYPE).getValue()  ;
	}
 
	public Attribute getUsrType(){
		return this.getAttribute ( USR_TYPE)  ;
	}

	public static final String USR_FULLNAME ="USR_FULLNAME" ;

	public void setUsrFullnameValue(java.lang.String   pm_usrFullname){
		this.getAttribute(USR_FULLNAME ).setValue( pm_usrFullname );
	}
 
	public java.lang.String getUsrFullnameValue(){
		return (java.lang.String) this.getAttribute ( USR_FULLNAME).getValue()  ;
	}
 
	public Attribute getUsrFullname(){
		return this.getAttribute ( USR_FULLNAME)  ;
	}

	public static final String USR_PASSWORD ="USR_PASSWORD" ;

	public void setUsrPasswordValue(java.lang.String   pm_usrPassword){
		this.getAttribute(USR_PASSWORD ).setValue( pm_usrPassword );
	}
 
	public java.lang.String getUsrPasswordValue(){
		return (java.lang.String) this.getAttribute ( USR_PASSWORD).getValue()  ;
	}
 
	public Attribute getUsrPassword(){
		return this.getAttribute ( USR_PASSWORD)  ;
	}

	public static final String USR_EMP_ID ="USR_EMP_ID" ;

	public void setUsrEmpIdValue(java.lang.String   pm_usrEmpId){
		this.getAttribute(USR_EMP_ID ).setValue( pm_usrEmpId );
	}
 
	public java.lang.String getUsrEmpIdValue(){
		return (java.lang.String) this.getAttribute ( USR_EMP_ID).getValue()  ;
	}
 
	public Attribute getUsrEmpId(){
		return this.getAttribute ( USR_EMP_ID)  ;
	}

	public static final String USR_SEC_ALL_DATA ="USR_SEC_ALL_DATA" ;

	public void setUsrSecAllDataValue(java.math.BigDecimal   pm_usrSecAllData){
		this.getAttribute(USR_SEC_ALL_DATA ).setValue( pm_usrSecAllData );
	}
 
	public java.math.BigDecimal getUsrSecAllDataValue(){
		return (java.math.BigDecimal) this.getAttribute ( USR_SEC_ALL_DATA).getValue()  ;
	}
 
	public Attribute getUsrSecAllData(){
		return this.getAttribute ( USR_SEC_ALL_DATA)  ;
	}

	public static final String USR_START_VALIDITY ="USR_START_VALIDITY" ;

	public void setUsrStartValidityValue(java.sql.Timestamp   pm_usrStartValidity){
		this.getAttribute(USR_START_VALIDITY ).setValue( pm_usrStartValidity );
	}
 
	public java.sql.Timestamp getUsrStartValidityValue(){
		return (java.sql.Timestamp) this.getAttribute ( USR_START_VALIDITY).getValue()  ;
	}
 
	public Attribute getUsrStartValidity(){
		return this.getAttribute ( USR_START_VALIDITY)  ;
	}

	public static final String USR_EXPIRY_VALIDITY ="USR_EXPIRY_VALIDITY" ;

	public void setUsrExpiryValidityValue(java.sql.Timestamp   pm_usrExpiryValidity){
		this.getAttribute(USR_EXPIRY_VALIDITY ).setValue( pm_usrExpiryValidity );
	}
 
	public java.sql.Timestamp getUsrExpiryValidityValue(){
		return (java.sql.Timestamp) this.getAttribute ( USR_EXPIRY_VALIDITY).getValue()  ;
	}
 
	public Attribute getUsrExpiryValidity(){
		return this.getAttribute ( USR_EXPIRY_VALIDITY)  ;
	}

	public static final String USR_ENABLE_TIME ="USR_ENABLE_TIME" ;

	public void setUsrEnableTimeValue(java.math.BigDecimal   pm_usrEnableTime){
		this.getAttribute(USR_ENABLE_TIME ).setValue( pm_usrEnableTime );
	}
 
	public java.math.BigDecimal getUsrEnableTimeValue(){
		return (java.math.BigDecimal) this.getAttribute ( USR_ENABLE_TIME).getValue()  ;
	}
 
	public Attribute getUsrEnableTime(){
		return this.getAttribute ( USR_ENABLE_TIME)  ;
	}

	public static final String USR_DAYS ="USR_DAYS" ;

	public void setUsrDaysValue(java.lang.String   pm_usrDays){
		this.getAttribute(USR_DAYS ).setValue( pm_usrDays );
	}
 
	public java.lang.String getUsrDaysValue(){
		return (java.lang.String) this.getAttribute ( USR_DAYS).getValue()  ;
	}
 
	public Attribute getUsrDays(){
		return this.getAttribute ( USR_DAYS)  ;
	}

	public static final String USR_CHNG_PWD ="USR_CHNG_PWD" ;

	public void setUsrChngPwdValue(java.math.BigDecimal   pm_usrChngPwd){
		this.getAttribute(USR_CHNG_PWD ).setValue( pm_usrChngPwd );
	}
 
	public java.math.BigDecimal getUsrChngPwdValue(){
		return (java.math.BigDecimal) this.getAttribute ( USR_CHNG_PWD).getValue()  ;
	}
 
	public Attribute getUsrChngPwd(){
		return this.getAttribute ( USR_CHNG_PWD)  ;
	}

	public static final String USR_CHNG_PWD_TIME ="USR_CHNG_PWD_TIME" ;

	public void setUsrChngPwdTimeValue(java.math.BigDecimal   pm_usrChngPwdTime){
		this.getAttribute(USR_CHNG_PWD_TIME ).setValue( pm_usrChngPwdTime );
	}
 
	public java.math.BigDecimal getUsrChngPwdTimeValue(){
		return (java.math.BigDecimal) this.getAttribute ( USR_CHNG_PWD_TIME).getValue()  ;
	}
 
	public Attribute getUsrChngPwdTime(){
		return this.getAttribute ( USR_CHNG_PWD_TIME)  ;
	}

	public static final String USR_CHNG_PWD_DATE ="USR_CHNG_PWD_DATE" ;

	public void setUsrChngPwdDateValue(java.sql.Timestamp   pm_usrChngPwdDate){
		this.getAttribute(USR_CHNG_PWD_DATE ).setValue( pm_usrChngPwdDate );
	}
 
	public java.sql.Timestamp getUsrChngPwdDateValue(){
		return (java.sql.Timestamp) this.getAttribute ( USR_CHNG_PWD_DATE).getValue()  ;
	}
 
	public Attribute getUsrChngPwdDate(){
		return this.getAttribute ( USR_CHNG_PWD_DATE)  ;
	}

	public static final String USR_SESSION ="USR_SESSION" ;

	public void setUsrSessionValue(java.math.BigDecimal   pm_usrSession){
		this.getAttribute(USR_SESSION ).setValue( pm_usrSession );
	}
 
	public java.math.BigDecimal getUsrSessionValue(){
		return (java.math.BigDecimal) this.getAttribute ( USR_SESSION).getValue()  ;
	}
 
	public Attribute getUsrSession(){
		return this.getAttribute ( USR_SESSION)  ;
	}

	public static final String USR_AUDIT ="USR_AUDIT" ;

	public void setUsrAuditValue(java.math.BigDecimal   pm_usrAudit){
		this.getAttribute(USR_AUDIT ).setValue( pm_usrAudit );
	}
 
	public java.math.BigDecimal getUsrAuditValue(){
		return (java.math.BigDecimal) this.getAttribute ( USR_AUDIT).getValue()  ;
	}
 
	public Attribute getUsrAudit(){
		return this.getAttribute ( USR_AUDIT)  ;
	}

	public static final String USR_UD1 ="USR_UD1" ;

	public void setUsrUd1Value(java.lang.String   pm_usrUd1){
		this.getAttribute(USR_UD1 ).setValue( pm_usrUd1 );
	}
 
	public java.lang.String getUsrUd1Value(){
		return (java.lang.String) this.getAttribute ( USR_UD1).getValue()  ;
	}
 
	public Attribute getUsrUd1(){
		return this.getAttribute ( USR_UD1)  ;
	}

	public static final String USR_UD2 ="USR_UD2" ;

	public void setUsrUd2Value(java.lang.String   pm_usrUd2){
		this.getAttribute(USR_UD2 ).setValue( pm_usrUd2 );
	}
 
	public java.lang.String getUsrUd2Value(){
		return (java.lang.String) this.getAttribute ( USR_UD2).getValue()  ;
	}
 
	public Attribute getUsrUd2(){
		return this.getAttribute ( USR_UD2)  ;
	}

	public static final String USR_UD3 ="USR_UD3" ;

	public void setUsrUd3Value(java.lang.String   pm_usrUd3){
		this.getAttribute(USR_UD3 ).setValue( pm_usrUd3 );
	}
 
	public java.lang.String getUsrUd3Value(){
		return (java.lang.String) this.getAttribute ( USR_UD3).getValue()  ;
	}
 
	public Attribute getUsrUd3(){
		return this.getAttribute ( USR_UD3)  ;
	}

	public static final String USR_UD4 ="USR_UD4" ;

	public void setUsrUd4Value(java.lang.String   pm_usrUd4){
		this.getAttribute(USR_UD4 ).setValue( pm_usrUd4 );
	}
 
	public java.lang.String getUsrUd4Value(){
		return (java.lang.String) this.getAttribute ( USR_UD4).getValue()  ;
	}
 
	public Attribute getUsrUd4(){
		return this.getAttribute ( USR_UD4)  ;
	}

	public static final String CRT_DATE ="CRT_DATE" ;

	public void setCrtDateValue(java.sql.Timestamp   pm_crtDate){
		this.getAttribute(CRT_DATE ).setValue( pm_crtDate );
	}
 
	public java.sql.Timestamp getCrtDateValue(){
		return (java.sql.Timestamp) this.getAttribute ( CRT_DATE).getValue()  ;
	}
 
	public Attribute getCrtDate(){
		return this.getAttribute ( CRT_DATE)  ;
	}

	public static final String CRT_USR_NAME ="CRT_USR_NAME" ;

	public void setCrtUsrNameValue(java.lang.String   pm_crtUsrName){
		this.getAttribute(CRT_USR_NAME ).setValue( pm_crtUsrName );
	}
 
	public java.lang.String getCrtUsrNameValue(){
		return (java.lang.String) this.getAttribute ( CRT_USR_NAME).getValue()  ;
	}
 
	public Attribute getCrtUsrName(){
		return this.getAttribute ( CRT_USR_NAME)  ;
	}

	public static final String CRT_MACHINE ="CRT_MACHINE" ;

	public void setCrtMachineValue(java.lang.String   pm_crtMachine){
		this.getAttribute(CRT_MACHINE ).setValue( pm_crtMachine );
	}
 
	public java.lang.String getCrtMachineValue(){
		return (java.lang.String) this.getAttribute ( CRT_MACHINE).getValue()  ;
	}
 
	public Attribute getCrtMachine(){
		return this.getAttribute ( CRT_MACHINE)  ;
	}

	public static final String MOD_DATE ="MOD_DATE" ;

	public void setModDateValue(java.sql.Timestamp   pm_modDate){
		this.getAttribute(MOD_DATE ).setValue( pm_modDate );
	}
 
	public java.sql.Timestamp getModDateValue(){
		return (java.sql.Timestamp) this.getAttribute ( MOD_DATE).getValue()  ;
	}
 
	public Attribute getModDate(){
		return this.getAttribute ( MOD_DATE)  ;
	}

	public static final String MOD_USR_NAME ="MOD_USR_NAME" ;

	public void setModUsrNameValue(java.lang.String   pm_modUsrName){
		this.getAttribute(MOD_USR_NAME ).setValue( pm_modUsrName );
	}
 
	public java.lang.String getModUsrNameValue(){
		return (java.lang.String) this.getAttribute ( MOD_USR_NAME).getValue()  ;
	}
 
	public Attribute getModUsrName(){
		return this.getAttribute ( MOD_USR_NAME)  ;
	}

	public static final String MOD_MACHINE ="MOD_MACHINE" ;

	public void setModMachineValue(java.lang.String   pm_modMachine){
		this.getAttribute(MOD_MACHINE ).setValue( pm_modMachine );
	}
 
	public java.lang.String getModMachineValue(){
		return (java.lang.String) this.getAttribute ( MOD_MACHINE).getValue()  ;
	}
 
	public Attribute getModMachine(){
		return this.getAttribute ( MOD_MACHINE)  ;
	}

	public static final String USR_STATUS ="USR_STATUS" ;

	public void setUsrStatusValue(java.math.BigDecimal   pm_usrStatus){
		this.getAttribute(USR_STATUS ).setValue( pm_usrStatus );
	}
 
	public java.math.BigDecimal getUsrStatusValue(){
		return (java.math.BigDecimal) this.getAttribute ( USR_STATUS).getValue()  ;
	}
 
	public Attribute getUsrStatus(){
		return this.getAttribute ( USR_STATUS)  ;
	}

	public static final String USR_GRP_ID ="USR_GRP_ID" ;

	public void setUsrGrpIdValue(java.lang.String   pm_usrGrpId){
		this.getAttribute(USR_GRP_ID ).setValue( pm_usrGrpId );
	}
 
	public java.lang.String getUsrGrpIdValue(){
		return (java.lang.String) this.getAttribute ( USR_GRP_ID).getValue()  ;
	}
 
	public Attribute getUsrGrpId(){
		return this.getAttribute ( USR_GRP_ID)  ;
	}

	public static final String USR_CMP_ID ="USR_CMP_ID" ;

	public void setUsrCmpIdValue(java.lang.String   pm_usrCmpId){
		this.getAttribute(USR_CMP_ID ).setValue( pm_usrCmpId );
	}
 
	public java.lang.String getUsrCmpIdValue(){
		return (java.lang.String) this.getAttribute ( USR_CMP_ID).getValue()  ;
	}
 
	public Attribute getUsrCmpId(){
		return this.getAttribute ( USR_CMP_ID)  ;
	}

	public static final String USR_APL ="USR_APL" ;

	public void setUsrAplValue(java.math.BigDecimal   pm_usrApl){
		this.getAttribute(USR_APL ).setValue( pm_usrApl );
	}
 
	public java.math.BigDecimal getUsrAplValue(){
		return (java.math.BigDecimal) this.getAttribute ( USR_APL).getValue()  ;
	}
 
	public Attribute getUsrApl(){
		return this.getAttribute ( USR_APL)  ;
	}

	public static final String USR_CHNG_TIME ="USR_CHNG_TIME" ;

	public void setUsrChngTimeValue(java.sql.Timestamp   pm_usrChngTime){
		this.getAttribute(USR_CHNG_TIME ).setValue( pm_usrChngTime );
	}
 
	public java.sql.Timestamp getUsrChngTimeValue(){
		return (java.sql.Timestamp) this.getAttribute ( USR_CHNG_TIME).getValue()  ;
	}
 
	public Attribute getUsrChngTime(){
		return this.getAttribute ( USR_CHNG_TIME)  ;
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

	public static final String DELETED ="DELETED" ;

	public void setDeletedValue(java.lang.String   pm_deleted){
		this.getAttribute(DELETED ).setValue( pm_deleted );
	}
 
	public java.lang.String getDeletedValue(){
		return (java.lang.String) this.getAttribute ( DELETED).getValue()  ;
	}
 
	public Attribute getDeleted(){
		return this.getAttribute ( DELETED)  ;
	}

	public static final String SUB_GROUP_ID ="SUB_GROUP_ID" ;

	public void setSubGroupIdValue(java.lang.String   pm_subGroupId){
		this.getAttribute(SUB_GROUP_ID ).setValue( pm_subGroupId );
	}
 
	public java.lang.String getSubGroupIdValue(){
		return (java.lang.String) this.getAttribute ( SUB_GROUP_ID).getValue()  ;
	}
 
	public Attribute getSubGroupId(){
		return this.getAttribute ( SUB_GROUP_ID)  ;
	}

	public static final String USR_EMAIL ="USR_EMAIL" ;

	public void setUsrEmailValue(java.lang.String   pm_usrEmail){
		this.getAttribute(USR_EMAIL ).setValue( pm_usrEmail );
	}
 
	public java.lang.String getUsrEmailValue(){
		return (java.lang.String) this.getAttribute ( USR_EMAIL).getValue()  ;
	}
 
	public Attribute getUsrEmail(){
		return this.getAttribute ( USR_EMAIL)  ;
	}

	public static final String USR_LANG ="USR_LANG" ;

	public void setUsrLangValue(java.math.BigDecimal   pm_usrAudit){
		this.getAttribute(USR_LANG ).setValue( pm_usrAudit );
	}
 
	public java.math.BigDecimal getUsrLangValue(){
		return (java.math.BigDecimal) this.getAttribute ( USR_LANG).getValue()  ;
	}
 
	public Attribute getUsrLang(){
		return this.getAttribute ( USR_LANG)  ;
	}
	
	int userLangIntValue = -1 ; 
	public int getUserLangIntValue()
	{  
		if (userLangIntValue == -1 )
		{
			userLangIntValue = 0 ;
			Attribute usrLang = getUsrLang() ;  
			if ( usrLang != null)
				userLangIntValue =  ((java.math.BigDecimal)usrLang.getValue()).intValue() ;
		}
		return userLangIntValue ; 
	}

	// ********Start of Child DataSets getter methods******** 
	// *******End of Child DataSets getter methods******** 
}