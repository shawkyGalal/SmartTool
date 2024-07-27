package com.smartValue.support.map.auto;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.Attribute;
import com.smartValue.database.DataSet;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;

public abstract class _QueryNotifier extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "QUERY_NOTIFIER"; 
 public static final String DB_TABLE_OWNER = "SUPPORT"; 

	public _QueryNotifier(){
	}

	 public DbTable getTable() 
	 { 
		return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String QUERY_ID ="QUERY_ID" ;

	public void setQueryIdValue(java.math.BigDecimal   pm_queryId){
		this.getAttribute(QUERY_ID ).setValue( pm_queryId );
	}
 
	public java.math.BigDecimal getQueryIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( QUERY_ID).getValue()  ;
	}
 
	public Attribute getQueryId(){
		return this.getAttribute ( QUERY_ID)  ;
	}

	public static final String EMAIL_TO ="EMAIL_TO" ;

	public void setEmailToValue(java.lang.String   pm_emailTo){
		this.getAttribute(EMAIL_TO ).setValue( pm_emailTo );
	}
 
	public java.lang.String getEmailToValue(){
		return (java.lang.String) this.getAttribute ( EMAIL_TO).getValue()  ;
	}
 
	public Attribute getEmailTo(){
		return this.getAttribute ( EMAIL_TO)  ;
	}

	public static final String EMAIL_CC ="EMAIL_CC" ;

	public void setEmailCcValue(java.lang.String   pm_emailCc){
		this.getAttribute(EMAIL_CC ).setValue( pm_emailCc );
	}
 
	public java.lang.String getEmailCcValue(){
		return (java.lang.String) this.getAttribute ( EMAIL_CC).getValue()  ;
	}
 
	public Attribute getEmailCc(){
		return this.getAttribute ( EMAIL_CC)  ;
	}

	public static final String NOTIFY_ID ="NOTIFY_ID" ;

	public void setNotifyIdValue(java.math.BigDecimal   pm_notifyId){
		this.getAttribute(NOTIFY_ID ).setValue( pm_notifyId );
	}
 
	public java.math.BigDecimal getNotifyIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( NOTIFY_ID).getValue()  ;
	}
 
	public Attribute getNotifyId(){
		return this.getAttribute ( NOTIFY_ID)  ;
	}

	public static final String COMPANY_ID ="COMPANY_ID" ;

	public void setCompanyIdValue(java.math.BigDecimal   pm_companyId){
		this.getAttribute(COMPANY_ID ).setValue( pm_companyId );
	}
 
	public java.math.BigDecimal getCompanyIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( COMPANY_ID).getValue()  ;
	}
 
	public Attribute getCompanyId(){
		return this.getAttribute ( COMPANY_ID)  ;
	}

	public static final String DESCRIPTION ="DESCRIPTION" ;

	public void setDescriptionValue(java.lang.String   pm_description){
		this.getAttribute(DESCRIPTION ).setValue( pm_description );
	}
 
	public java.lang.String getDescriptionValue(){
		return (java.lang.String) this.getAttribute ( DESCRIPTION).getValue()  ;
	}
 
	public Attribute getDescription(){
		return this.getAttribute ( DESCRIPTION)  ;
	}

	public static final String IS_ACTIVE ="IS_ACTIVE" ;

	public void setIsActiveValue(java.lang.String   pm_isActive){
		this.getAttribute(IS_ACTIVE ).setValue( pm_isActive );
	}
 
	public java.lang.String getIsActiveValue(){
		return (java.lang.String) this.getAttribute ( IS_ACTIVE).getValue()  ;
	}
 
	public Attribute getIsActive(){
		return this.getAttribute ( IS_ACTIVE)  ;
	}

	public static final String USER_NAME ="USER_NAME" ;

	public void setUserNameValue(java.lang.String   pm_userName){
		this.getAttribute(USER_NAME ).setValue( pm_userName );
	}
 
	public java.lang.String getUserNameValue(){
		return (java.lang.String) this.getAttribute ( USER_NAME).getValue()  ;
	}
 
	public Attribute getUserName(){
		return this.getAttribute ( USER_NAME)  ;
	}

	public static final String EMAIL_SUBJECT ="EMAIL_SUBJECT" ;

	public void setEmailSubjectValue(java.lang.String   pm_emailSubject){
		this.getAttribute(EMAIL_SUBJECT ).setValue( pm_emailSubject );
	}
 
	public java.lang.String getEmailSubjectValue(){
		return (java.lang.String) this.getAttribute ( EMAIL_SUBJECT).getValue()  ;
	}
 
	public Attribute getEmailSubject(){
		return this.getAttribute ( EMAIL_SUBJECT)  ;
	}

	public static final String GROUP_NOTIFICATION ="GROUP_NOTIFICATION" ;

	public void setGroupNotificationValue(java.lang.String   pm_groupNotification){
		this.getAttribute(GROUP_NOTIFICATION ).setValue( pm_groupNotification );
	}
 
	public java.lang.String getGroupNotificationValue(){
		return (java.lang.String) this.getAttribute ( GROUP_NOTIFICATION).getValue()  ;
	}
 
	public Attribute getGroupNotification(){
		return this.getAttribute ( GROUP_NOTIFICATION)  ;
	}

	public static final String GROUP_REFRENCE_ID ="GROUP_REFRENCE_ID" ;

	public void setGroupRefrenceIdValue(java.math.BigDecimal   pm_groupRefrenceId){
		this.getAttribute(GROUP_REFRENCE_ID ).setValue( pm_groupRefrenceId );
	}
 
	public java.math.BigDecimal getGroupRefrenceIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( GROUP_REFRENCE_ID).getValue()  ;
	}
 
	public Attribute getGroupRefrenceId(){
		return this.getAttribute ( GROUP_REFRENCE_ID)  ;
	}

	// ********Start of Child DataSets getter methods******** 

	public DataSet getDatasetQueryNotifListParamsFk1()
	{
		 return this.geChildDsByForignKeyName("QUERY_NOTIF_LIST_PARAMS_FK1");
	}
	// *******End of Child DataSets getter methods******** 
}