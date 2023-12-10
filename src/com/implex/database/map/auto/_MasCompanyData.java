package com.implex.database.map.auto;

import com.implex.database.PersistantObject; 
import com.implex.database.DataSet;
import com.implex.database.Attribute;
import com.implex.database.DbTable;
import com.implex.database.ApplicationContext;

public abstract class _MasCompanyData extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "MAS_COMPANY_DATA"; 
 public static final String DB_TABLE_OWNER = "ICDB"; 

	public _MasCompanyData(){
	}

	 public DbTable getTable() 
	 { 
		return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String CMP_ID ="CMP_ID" ;

	public void setCmpIdValue(java.math.BigDecimal   pm_cmpId){
		this.getAttribute(CMP_ID ).setValue( pm_cmpId );
	}
 
	public String getCmpIdValue(){
		return  this.getAttribute ( CMP_ID).getValue().toString()  ;
	}
	
 
	public Attribute getCmpId(){
		return this.getAttribute ( CMP_ID)  ;
	}

	public static final String CMP_DESC ="CMP_DESC" ;

	public void setCmpDescValue(java.lang.String   pm_cmpDesc){
		this.getAttribute(CMP_DESC ).setValue( pm_cmpDesc );
	}
 
	public java.lang.String getCmpDescValue(){
		return (java.lang.String) this.getAttribute ( CMP_DESC).getValue()  ;
	}
 
	public Attribute getCmpDesc(){
		return this.getAttribute ( CMP_DESC)  ;
	}

	public static final String CMP_DESC_ ="CMP_DESC_" ;

	public void setCmpDesc_Value(java.lang.String   pm_cmpDesc_){
		this.getAttribute(CMP_DESC_ ).setValue( pm_cmpDesc_ );
	}
 
	public java.lang.String getCmpDesc_Value(){
		return (java.lang.String) this.getAttribute ( CMP_DESC_).getValue()  ;
	}
 
	public Attribute getCmpDesc_(){
		return this.getAttribute ( CMP_DESC_)  ;
	}

	public static final String CMP_PARENT ="CMP_PARENT" ;

	public void setCmpParentValue(java.lang.String   pm_cmpParent){
		this.getAttribute(CMP_PARENT ).setValue( pm_cmpParent );
	}
 
	public java.lang.String getCmpParentValue(){
		return (java.lang.String) this.getAttribute ( CMP_PARENT).getValue()  ;
	}
 
	public Attribute getCmpParent(){
		return this.getAttribute ( CMP_PARENT)  ;
	}

	public static final String CMP_DATE ="CMP_DATE" ;

	public void setCmpDateValue(java.sql.Timestamp   pm_cmpDate){
		this.getAttribute(CMP_DATE ).setValue( pm_cmpDate );
	}
 
	public java.sql.Timestamp getCmpDateValue(){
		return (java.sql.Timestamp) this.getAttribute ( CMP_DATE).getValue()  ;
	}
 
	public Attribute getCmpDate(){
		return this.getAttribute ( CMP_DATE)  ;
	}

	public static final String CMP_ADDRESS ="CMP_ADDRESS" ;

	public void setCmpAddressValue(java.lang.String   pm_cmpAddress){
		this.getAttribute(CMP_ADDRESS ).setValue( pm_cmpAddress );
	}
 
	public java.lang.String getCmpAddressValue(){
		return (java.lang.String) this.getAttribute ( CMP_ADDRESS).getValue()  ;
	}
 
	public Attribute getCmpAddress(){
		return this.getAttribute ( CMP_ADDRESS)  ;
	}

	public static final String CMP_ADDRESS_ ="CMP_ADDRESS_" ;

	public void setCmpAddress_Value(java.lang.String   pm_cmpAddress_){
		this.getAttribute(CMP_ADDRESS_ ).setValue( pm_cmpAddress_ );
	}
 
	public java.lang.String getCmpAddress_Value(){
		return (java.lang.String) this.getAttribute ( CMP_ADDRESS_).getValue()  ;
	}
 
	public Attribute getCmpAddress_(){
		return this.getAttribute ( CMP_ADDRESS_)  ;
	}

	public static final String REG_ID ="REG_ID" ;

	public void setRegIdValue(java.lang.String   pm_regId){
		this.getAttribute(REG_ID ).setValue( pm_regId );
	}
 
	public java.lang.String getRegIdValue(){
		return (java.lang.String) this.getAttribute ( REG_ID).getValue()  ;
	}
 
	public Attribute getRegId(){
		return this.getAttribute ( REG_ID)  ;
	}

	public static final String CITY_ID ="CITY_ID" ;

	public void setCityIdValue(java.lang.String   pm_cityId){
		this.getAttribute(CITY_ID ).setValue( pm_cityId );
	}
 
	public java.lang.String getCityIdValue(){
		return (java.lang.String) this.getAttribute ( CITY_ID).getValue()  ;
	}
 
	public Attribute getCityId(){
		return this.getAttribute ( CITY_ID)  ;
	}

	public static final String TER_ID ="TER_ID" ;

	public void setTerIdValue(java.lang.String   pm_terId){
		this.getAttribute(TER_ID ).setValue( pm_terId );
	}
 
	public java.lang.String getTerIdValue(){
		return (java.lang.String) this.getAttribute ( TER_ID).getValue()  ;
	}
 
	public Attribute getTerId(){
		return this.getAttribute ( TER_ID)  ;
	}

	public static final String POLICE_ID ="POLICE_ID" ;

	public void setPoliceIdValue(java.lang.String   pm_policeId){
		this.getAttribute(POLICE_ID ).setValue( pm_policeId );
	}
 
	public java.lang.String getPoliceIdValue(){
		return (java.lang.String) this.getAttribute ( POLICE_ID).getValue()  ;
	}
 
	public Attribute getPoliceId(){
		return this.getAttribute ( POLICE_ID)  ;
	}

	public static final String CMP_PBOX ="CMP_PBOX" ;

	public void setCmpPboxValue(java.lang.String   pm_cmpPbox){
		this.getAttribute(CMP_PBOX ).setValue( pm_cmpPbox );
	}
 
	public java.lang.String getCmpPboxValue(){
		return (java.lang.String) this.getAttribute ( CMP_PBOX).getValue()  ;
	}
 
	public Attribute getCmpPbox(){
		return this.getAttribute ( CMP_PBOX)  ;
	}

	public static final String CMP_ZIP_CODE ="CMP_ZIP_CODE" ;

	public void setCmpZipCodeValue(java.lang.String   pm_cmpZipCode){
		this.getAttribute(CMP_ZIP_CODE ).setValue( pm_cmpZipCode );
	}
 
	public java.lang.String getCmpZipCodeValue(){
		return (java.lang.String) this.getAttribute ( CMP_ZIP_CODE).getValue()  ;
	}
 
	public Attribute getCmpZipCode(){
		return this.getAttribute ( CMP_ZIP_CODE)  ;
	}

	public static final String CMP_WEB ="CMP_WEB" ;

	public void setCmpWebValue(java.lang.String   pm_cmpWeb){
		this.getAttribute(CMP_WEB ).setValue( pm_cmpWeb );
	}
 
	public java.lang.String getCmpWebValue(){
		return (java.lang.String) this.getAttribute ( CMP_WEB).getValue()  ;
	}
 
	public Attribute getCmpWeb(){
		return this.getAttribute ( CMP_WEB)  ;
	}

	public static final String CMP_CAPITALS ="CMP_CAPITALS" ;

	public void setCmpCapitalsValue(java.math.BigDecimal   pm_cmpCapitals){
		this.getAttribute(CMP_CAPITALS ).setValue( pm_cmpCapitals );
	}
 
	public java.math.BigDecimal getCmpCapitalsValue(){
		return (java.math.BigDecimal) this.getAttribute ( CMP_CAPITALS).getValue()  ;
	}
 
	public Attribute getCmpCapitals(){
		return this.getAttribute ( CMP_CAPITALS)  ;
	}

	public static final String CMP_SHARES_VAL ="CMP_SHARES_VAL" ;

	public void setCmpSharesValValue(java.math.BigDecimal   pm_cmpSharesVal){
		this.getAttribute(CMP_SHARES_VAL ).setValue( pm_cmpSharesVal );
	}
 
	public java.math.BigDecimal getCmpSharesValValue(){
		return (java.math.BigDecimal) this.getAttribute ( CMP_SHARES_VAL).getValue()  ;
	}
 
	public Attribute getCmpSharesVal(){
		return this.getAttribute ( CMP_SHARES_VAL)  ;
	}

	public static final String CMP_SHARES ="CMP_SHARES" ;

	public void setCmpSharesValue(java.math.BigDecimal   pm_cmpShares){
		this.getAttribute(CMP_SHARES ).setValue( pm_cmpShares );
	}
 
	public java.math.BigDecimal getCmpSharesValue(){
		return (java.math.BigDecimal) this.getAttribute ( CMP_SHARES).getValue()  ;
	}
 
	public Attribute getCmpShares(){
		return this.getAttribute ( CMP_SHARES)  ;
	}

	public static final String CMP_TYPE ="CMP_TYPE" ;

	public void setCmpTypeValue(java.lang.String   pm_cmpType){
		this.getAttribute(CMP_TYPE ).setValue( pm_cmpType );
	}
 
	public java.lang.String getCmpTypeValue(){
		return (java.lang.String) this.getAttribute ( CMP_TYPE).getValue()  ;
	}
 
	public Attribute getCmpType(){
		return this.getAttribute ( CMP_TYPE)  ;
	}

	public static final String CMP_LOGO ="CMP_LOGO" ;


	public Attribute getCmpLogo(){
		return this.getAttribute ( CMP_LOGO)  ;
	}

	public static final String CUR_ID ="CUR_ID" ;

	public void setCurIdValue(java.lang.String   pm_curId){
		this.getAttribute(CUR_ID ).setValue( pm_curId );
	}
 
	public java.lang.String getCurIdValue(){
		return (java.lang.String) this.getAttribute ( CUR_ID).getValue()  ;
	}
 
	public Attribute getCurId(){
		return this.getAttribute ( CUR_ID)  ;
	}

	public static final String CMP_CONTACT ="CMP_CONTACT" ;

	public void setCmpContactValue(java.lang.String   pm_cmpContact){
		this.getAttribute(CMP_CONTACT ).setValue( pm_cmpContact );
	}
 
	public java.lang.String getCmpContactValue(){
		return (java.lang.String) this.getAttribute ( CMP_CONTACT).getValue()  ;
	}
 
	public Attribute getCmpContact(){
		return this.getAttribute ( CMP_CONTACT)  ;
	}

	public static final String CMP_FAX ="CMP_FAX" ;

	public void setCmpFaxValue(java.lang.String   pm_cmpFax){
		this.getAttribute(CMP_FAX ).setValue( pm_cmpFax );
	}
 
	public java.lang.String getCmpFaxValue(){
		return (java.lang.String) this.getAttribute ( CMP_FAX).getValue()  ;
	}
 
	public Attribute getCmpFax(){
		return this.getAttribute ( CMP_FAX)  ;
	}

	public static final String CMP_EMAIL ="CMP_EMAIL" ;

	public void setCmpEmailValue(java.lang.String   pm_cmpEmail){
		this.getAttribute(CMP_EMAIL ).setValue( pm_cmpEmail );
	}
 
	public java.lang.String getCmpEmailValue(){
		return (java.lang.String) this.getAttribute ( CMP_EMAIL).getValue()  ;
	}
 
	public Attribute getCmpEmail(){
		return this.getAttribute ( CMP_EMAIL)  ;
	}

	public static final String CMP_STATUS ="CMP_STATUS" ;

	public void setCmpStatusValue(java.lang.String   pm_cmpStatus){
		this.getAttribute(CMP_STATUS ).setValue( pm_cmpStatus );
	}
 
	public java.lang.String getCmpStatusValue(){
		return (java.lang.String) this.getAttribute ( CMP_STATUS).getValue()  ;
	}
 
	public Attribute getCmpStatus(){
		return this.getAttribute ( CMP_STATUS)  ;
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

	public static final String SYSTEM_ID ="SYSTEM_ID" ;

	public void setSystemIdValue(java.math.BigDecimal   pm_systemId){
		this.getAttribute(SYSTEM_ID ).setValue( pm_systemId );
	}
 
	public java.math.BigDecimal getSystemIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( SYSTEM_ID).getValue()  ;
	}
 
	public Attribute getSystemId(){
		return this.getAttribute ( SYSTEM_ID)  ;
	}

	public static final String MAX_ALLOWED_USERS ="MAX_ALLOWED_USERS" ;

	public void setMaxAllowedUsersValue(java.math.BigDecimal   pm_maxAllowedUsers){
		this.getAttribute(MAX_ALLOWED_USERS ).setValue( pm_maxAllowedUsers );
	}
 
	public java.math.BigDecimal getMaxAllowedUsersValue(){
		return (java.math.BigDecimal) this.getAttribute ( MAX_ALLOWED_USERS).getValue()  ;
	}
 
	public Attribute getMaxAllowedUsers(){
		return this.getAttribute ( MAX_ALLOWED_USERS)  ;
	}

	public static final String LOGO_LINK ="LOGO_LINK" ;

	public void setLogoLinkValue(java.lang.String   pm_logoLink){
		this.getAttribute(LOGO_LINK ).setValue( pm_logoLink );
	}
 
	public java.lang.String getLogoLinkValue(){
		return (java.lang.String) this.getAttribute ( LOGO_LINK).getValue()  ;
	}
 
	public Attribute getLogoLink(){
		return this.getAttribute ( LOGO_LINK)  ;
	}

	public static final String ORG_UNITS_START_NODE ="ORG_UNITS_START_NODE" ;

	public void setOrgUnitsStartNodeValue(java.lang.String   pm_orgUnitsStartNode){
		this.getAttribute(ORG_UNITS_START_NODE ).setValue( pm_orgUnitsStartNode );
	}
 
	public java.lang.String getOrgUnitsStartNodeValue(){
		return (java.lang.String) this.getAttribute ( ORG_UNITS_START_NODE).getValue()  ;
	}
 
	public Attribute getOrgUnitsStartNode(){
		return this.getAttribute ( ORG_UNITS_START_NODE)  ;
	}

	public static final String MISSION_VISION_STMT ="MISSION_VISION_STMT" ;

	public void setMissionVisionStmtValue(java.lang.String   pm_missionVisionStmt){
		this.getAttribute(MISSION_VISION_STMT ).setValue( pm_missionVisionStmt );
	}
 
	public java.lang.String getMissionVisionStmtValue(){
		return (java.lang.String) this.getAttribute ( MISSION_VISION_STMT).getValue()  ;
	}
 
	public Attribute getMissionVisionStmt(){
		return this.getAttribute ( MISSION_VISION_STMT)  ;
	}

	public void setCmpDescAutoLang(java.lang.String   pm_cmpDesc){
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		this.getCmpDesc().setValue( pm_cmpDesc );
		 else 
		this.getCmpDesc_().setValue( pm_cmpDesc );
	}
 
	public Attribute getCmpDescAutoLang(){
		 Attribute result ;
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		 result =   this.getCmpDesc();
		 else 
		 result =   this.getCmpDesc_();
		 return result;
	}

	public void setCmpAddressAutoLang(java.lang.String   pm_cmpAddress){
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		this.getCmpAddress().setValue( pm_cmpAddress );
		 else 
		this.getCmpAddress_().setValue( pm_cmpAddress );
	}
 
	public Attribute getCmpAddressAutoLang(){
		 Attribute result ;
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		 result =   this.getCmpAddress();
		 else 
		 result =   this.getCmpAddress_();
		 return result;
	}

	// ********Start of Child DataSets getter methods******** 
	// *******End of Child DataSets getter methods******** 
}