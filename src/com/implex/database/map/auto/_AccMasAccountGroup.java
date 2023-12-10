package com.implex.database.map.auto;

import com.implex.database.PersistantObject; 
import com.implex.database.Attribute;
import com.implex.database.DbTable;
import com.implex.database.ApplicationContext;

public abstract class _AccMasAccountGroup extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "ACC_MAS_ACCOUNT_GROUP"; 
 public static final String DB_TABLE_OWNER = "ACC";

	public _AccMasAccountGroup(){
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

	public static final String ACCOUNT_GROUP_DESC ="ACCOUNT_GROUP_DESC" ;

	public void setAccountGroupDescValue(java.lang.String   pm_accountGroupDesc){
		this.getAttribute(ACCOUNT_GROUP_DESC ).setValue( pm_accountGroupDesc );
	}
 
	public java.lang.String getAccountGroupDescValue(){
		return (java.lang.String) this.getAttribute ( ACCOUNT_GROUP_DESC).getValue()  ;
	}
 
	public Attribute getAccountGroupDesc(){
		return this.getAttribute ( ACCOUNT_GROUP_DESC)  ;
	}

	public static final String ACCOUNT_GROUP_DESC_ ="ACCOUNT_GROUP_DESC_" ;

	public void setAccountGroupDesc_Value(java.lang.String   pm_accountGroupDesc_){
		this.getAttribute(ACCOUNT_GROUP_DESC_ ).setValue( pm_accountGroupDesc_ );
	}
 
	public java.lang.String getAccountGroupDesc_Value(){
		return (java.lang.String) this.getAttribute ( ACCOUNT_GROUP_DESC_).getValue()  ;
	}
 
	public Attribute getAccountGroupDesc_(){
		return this.getAttribute ( ACCOUNT_GROUP_DESC_)  ;
	}

	public static final String PARENT_ACCOUNT_GROUP_ID ="PARENT_ACCOUNT_GROUP_ID" ;

	public void setParentAccountGroupIdValue(java.lang.String   pm_parentAccountGroupId){
		this.getAttribute(PARENT_ACCOUNT_GROUP_ID ).setValue( pm_parentAccountGroupId );
	}
 
	public java.lang.String getParentAccountGroupIdValue(){
		return (java.lang.String) this.getAttribute ( PARENT_ACCOUNT_GROUP_ID).getValue()  ;
	}
 
	public Attribute getParentAccountGroupId(){
		return this.getAttribute ( PARENT_ACCOUNT_GROUP_ID)  ;
	}

	public static final String ACCOUNT_GROUP_STATUS ="ACCOUNT_GROUP_STATUS" ;

	public void setAccountGroupStatusValue(java.lang.String   pm_accountGroupStatus){
		this.getAttribute(ACCOUNT_GROUP_STATUS ).setValue( pm_accountGroupStatus );
	}
 
	public java.lang.String getAccountGroupStatusValue(){
		return (java.lang.String) this.getAttribute ( ACCOUNT_GROUP_STATUS).getValue()  ;
	}
 
	public Attribute getAccountGroupStatus(){
		return this.getAttribute ( ACCOUNT_GROUP_STATUS)  ;
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

	public void setAccountGroupDescAutoLang(java.lang.String   pm_accountGroupDesc){
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		this.getAccountGroupDesc().setValue( pm_accountGroupDesc );
		 else 
		this.getAccountGroupDesc_().setValue( pm_accountGroupDesc );
	}
 
	public Attribute getAccountGroupDescAutoLang(){
		 Attribute result ;
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		 result =   this.getAccountGroupDesc();
		 else 
		 result =   this.getAccountGroupDesc_();
		 return result;
	}
}