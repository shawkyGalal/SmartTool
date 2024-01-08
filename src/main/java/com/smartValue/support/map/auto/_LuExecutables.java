package com.smartValue.support.map.auto;

import com.implex.database.PersistantObject; 
import com.implex.database.DataSet;
import com.implex.database.Attribute;
import com.implex.database.DbTable;
import com.implex.database.ApplicationContext;

public abstract class _LuExecutables extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "LU_EXECUTABLES"; 
 public static final String DB_TABLE_OWNER = "SUPPORT"; 

	public _LuExecutables(){
	}

	 public DbTable getTable() 
	 { 
		return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String ID ="ID" ;

	public void setIdValue(java.math.BigDecimal   pm_id){
		this.getAttribute(ID ).setValue( pm_id );
	}
 
	public java.math.BigDecimal getIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( ID).getValue()  ;
	}
 
	public Attribute getId(){
		return this.getAttribute ( ID)  ;
	}

	public static final String CODE ="CODE" ;

	public void setCodeValue(java.lang.String   pm_code){
		this.getAttribute(CODE ).setValue( pm_code );
	}
 
	public java.lang.String getCodeValue(){
		return (java.lang.String) this.getAttribute ( CODE).getValue()  ;
	}
 
	public Attribute getCode(){
		return this.getAttribute ( CODE)  ;
	}

	public static final String PARENT_ID ="PARENT_ID" ;

	public void setParentIdValue(java.math.BigDecimal   pm_parentId){
		this.getAttribute(PARENT_ID ).setValue( pm_parentId );
	}
 
	public java.math.BigDecimal getParentIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( PARENT_ID).getValue()  ;
	}
 
	public Attribute getParentId(){
		return this.getAttribute ( PARENT_ID)  ;
	}

	public static final String PREV_PARENT_ID ="PREV_PARENT_ID" ;

	public void setPrevParentIdValue(java.math.BigDecimal   pm_prevParentId){
		this.getAttribute(PREV_PARENT_ID ).setValue( pm_prevParentId );
	}
 
	public java.math.BigDecimal getPrevParentIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( PREV_PARENT_ID).getValue()  ;
	}
 
	public Attribute getPrevParentId(){
		return this.getAttribute ( PREV_PARENT_ID)  ;
	}

	public static final String HEADER_ID ="HEADER_ID" ;

	public void setHeaderIdValue(java.math.BigDecimal   pm_headerId){
		this.getAttribute(HEADER_ID ).setValue( pm_headerId );
	}
 
	public java.math.BigDecimal getHeaderIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( HEADER_ID).getValue()  ;
	}
 
	public Attribute getHeaderId(){
		return this.getAttribute ( HEADER_ID)  ;
	}

	public static final String PREV_HEADER_ID ="PREV_HEADER_ID" ;

	public void setPrevHeaderIdValue(java.math.BigDecimal   pm_prevHeaderId){
		this.getAttribute(PREV_HEADER_ID ).setValue( pm_prevHeaderId );
	}
 
	public java.math.BigDecimal getPrevHeaderIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( PREV_HEADER_ID).getValue()  ;
	}
 
	public Attribute getPrevHeaderId(){
		return this.getAttribute ( PREV_HEADER_ID)  ;
	}

	public static final String A_DSC ="A_DSC" ;

	public void setADscValue(java.lang.String   pm_aDsc){
		this.getAttribute(A_DSC ).setValue( pm_aDsc );
	}
 
	public java.lang.String getADscValue(){
		return (java.lang.String) this.getAttribute ( A_DSC).getValue()  ;
	}
 
	public Attribute getADsc(){
		return this.getAttribute ( A_DSC)  ;
	}

	public static final String E_DSC ="E_DSC" ;

	public void setEDscValue(java.lang.String   pm_eDsc){
		this.getAttribute(E_DSC ).setValue( pm_eDsc );
	}
 
	public java.lang.String getEDscValue(){
		return (java.lang.String) this.getAttribute ( E_DSC).getValue()  ;
	}
 
	public Attribute getEDsc(){
		return this.getAttribute ( E_DSC)  ;
	}

	public static final String NOTE_NOTES_ID ="NOTE_NOTES_ID" ;

	public void setNoteNotesIdValue(java.math.BigDecimal   pm_noteNotesId){
		this.getAttribute(NOTE_NOTES_ID ).setValue( pm_noteNotesId );
	}
 
	public java.math.BigDecimal getNoteNotesIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( NOTE_NOTES_ID).getValue()  ;
	}
 
	public Attribute getNoteNotesId(){
		return this.getAttribute ( NOTE_NOTES_ID)  ;
	}

	public static final String ACTIVE ="ACTIVE" ;

	public void setActiveValue(java.lang.String   pm_active){
		this.getAttribute(ACTIVE ).setValue( pm_active );
	}
 
	public java.lang.String getActiveValue(){
		return (java.lang.String) this.getAttribute ( ACTIVE).getValue()  ;
	}
 
	public Attribute getActive(){
		return this.getAttribute ( ACTIVE)  ;
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

	public static final String MODIFY_SEQ ="MODIFY_SEQ" ;

	public void setModifySeqValue(java.math.BigDecimal   pm_modifySeq){
		this.getAttribute(MODIFY_SEQ ).setValue( pm_modifySeq );
	}
 
	public java.math.BigDecimal getModifySeqValue(){
		return (java.math.BigDecimal) this.getAttribute ( MODIFY_SEQ).getValue()  ;
	}
 
	public Attribute getModifySeq(){
		return this.getAttribute ( MODIFY_SEQ)  ;
	}

	public static final String UPDATE_STATUS ="UPDATE_STATUS" ;

	public void setUpdateStatusValue(java.math.BigDecimal   pm_updateStatus){
		this.getAttribute(UPDATE_STATUS ).setValue( pm_updateStatus );
	}
 
	public java.math.BigDecimal getUpdateStatusValue(){
		return (java.math.BigDecimal) this.getAttribute ( UPDATE_STATUS).getValue()  ;
	}
 
	public Attribute getUpdateStatus(){
		return this.getAttribute ( UPDATE_STATUS)  ;
	}

	public static final String EXEC_BODY ="EXEC_BODY" ;

	public void setExecBodyValue(java.lang.String   pm_execBody){
		this.getAttribute(EXEC_BODY ).setValue( pm_execBody );
	}
 
	public java.lang.String getExecBodyValue(){
		return (java.lang.String) this.getAttribute ( EXEC_BODY).getValue()  ;
	}
 
	public Attribute getExecBody(){
		return this.getAttribute ( EXEC_BODY)  ;
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

	public static final String MSG ="MSG" ;

	public void setMsgValue(java.lang.String   pm_msg){
		this.getAttribute(MSG ).setValue( pm_msg );
	}
 
	public java.lang.String getMsgValue(){
		return (java.lang.String) this.getAttribute ( MSG).getValue()  ;
	}
 
	public Attribute getMsg(){
		return this.getAttribute ( MSG)  ;
	}

	public static final String HYPERLINK_TITLE ="HYPERLINK_TITLE" ;

	public void setHyperlinkTitleValue(java.lang.String   pm_hyperlinkTitle){
		this.getAttribute(HYPERLINK_TITLE ).setValue( pm_hyperlinkTitle );
	}
 
	public java.lang.String getHyperlinkTitleValue(){
		return (java.lang.String) this.getAttribute ( HYPERLINK_TITLE).getValue()  ;
	}
 
	public Attribute getHyperlinkTitle(){
		return this.getAttribute ( HYPERLINK_TITLE)  ;
	}

	public static final String EMAIL_NOTIFY_CC ="EMAIL_NOTIFY_CC" ;

	public void setEmailNotifyCcValue(java.lang.String   pm_emailNotifyCc){
		this.getAttribute(EMAIL_NOTIFY_CC ).setValue( pm_emailNotifyCc );
	}
 
	public java.lang.String getEmailNotifyCcValue(){
		return (java.lang.String) this.getAttribute ( EMAIL_NOTIFY_CC).getValue()  ;
	}
 
	public Attribute getEmailNotifyCc(){
		return this.getAttribute ( EMAIL_NOTIFY_CC)  ;
	}

	public static final String EMAIL_NOTIFY_TO ="EMAIL_NOTIFY_TO" ;

	public void setEmailNotifyToValue(java.lang.String   pm_emailNotifyTo){
		this.getAttribute(EMAIL_NOTIFY_TO ).setValue( pm_emailNotifyTo );
	}
 
	public java.lang.String getEmailNotifyToValue(){
		return (java.lang.String) this.getAttribute ( EMAIL_NOTIFY_TO).getValue()  ;
	}
 
	public Attribute getEmailNotifyTo(){
		return this.getAttribute ( EMAIL_NOTIFY_TO)  ;
	}

	public static final String LOG_EXECUTION ="LOG_EXECUTION" ;

	public void setLogExecutionValue(java.lang.String   pm_logExecution){
		this.getAttribute(LOG_EXECUTION ).setValue( pm_logExecution );
	}
 
	public java.lang.String getLogExecutionValue(){
		return (java.lang.String) this.getAttribute ( LOG_EXECUTION).getValue()  ;
	}
 
	public Attribute getLogExecution(){
		return this.getAttribute ( LOG_EXECUTION)  ;
	}

	public static final String TARGET_URL ="TARGET_URL" ;

	public void setTargetUrlValue(java.lang.String   pm_targetUrl){
		this.getAttribute(TARGET_URL ).setValue( pm_targetUrl );
	}
 
	public java.lang.String getTargetUrlValue(){
		return (java.lang.String) this.getAttribute ( TARGET_URL).getValue()  ;
	}
 
	public Attribute getTargetUrl(){
		return this.getAttribute ( TARGET_URL)  ;
	}

	public static final String EXEC_ON_REPOS_CON ="EXEC_ON_REPOS_CON" ;

	public void setExecOnReposConValue(java.lang.String   pm_execOnReposCon){
		this.getAttribute(EXEC_ON_REPOS_CON ).setValue( pm_execOnReposCon );
	}
 
	public java.lang.String getExecOnReposConValue(){
		return (java.lang.String) this.getAttribute ( EXEC_ON_REPOS_CON).getValue()  ;
	}
 
	public Attribute getExecOnReposCon(){
		return this.getAttribute ( EXEC_ON_REPOS_CON)  ;
	}

	// ********Start of Child DataSets getter methods******** 
	public DataSet getDatasetFkLuExecutables()
	{
		 return this.geChildDsByForignKeyName("FK_LU_EXECUTABLES");
	}
	// *******End of Child DataSets getter methods******** 
}