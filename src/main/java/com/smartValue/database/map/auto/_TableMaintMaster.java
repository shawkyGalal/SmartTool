package com.smartValue.database.map.auto;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.Attribute;
import com.smartValue.database.DataSet;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;

public abstract class _TableMaintMaster extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "TABLE_MAINT_MASTER";
 public static final String DB_TABLE_OWNER = "ICDB";
 

	public _TableMaintMaster(){
	}

	 public DbTable getTable() 
	 { 
		return new DbTable(DB_TABLE_OWNER ,DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String SUBMIT_FORM_VALIDARION ="SUBMIT_FORM_VALIDARION" ;

	public void setSubmitFormValidarionValue(java.lang.String   pm_submitFormValidarion){
		this.getAttribute(SUBMIT_FORM_VALIDARION ).setValue( pm_submitFormValidarion );
	}
 
	public java.lang.String getSubmitFormValidarionValue(){
		return (java.lang.String) this.getAttribute ( SUBMIT_FORM_VALIDARION).getValue()  ;
	}
 
	public Attribute getSubmitFormValidarion(){
		return this.getAttribute ( SUBMIT_FORM_VALIDARION)  ;
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

	public static final String TABLEDESC ="TABLEDESC" ;

	public void setTabledescValue(java.lang.String   pm_tabledesc){
		this.getAttribute(TABLEDESC ).setValue( pm_tabledesc );
	}
 
	public java.lang.String getTabledescValue(){
		return (java.lang.String) this.getAttribute ( TABLEDESC).getValue()  ;
	}
 
	public Attribute getTabledesc(){
		return this.getAttribute ( TABLEDESC)  ;
	}

	public static final String AUDITABLE ="AUDITABLE" ;

	public void setAuditableValue(java.lang.String   pm_auditable){
		this.getAttribute(AUDITABLE ).setValue( pm_auditable );
	}
 
	public java.lang.String getAuditableValue(){
		return (java.lang.String) this.getAttribute ( AUDITABLE).getValue()  ;
	}
 
	public Attribute getAuditable(){
		return this.getAttribute ( AUDITABLE)  ;
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

	public static final String SECURITY_CONTROLLER_ID ="SECURITY_CONTROLLER_ID" ;

	public void setSecurityControllerIdValue(java.math.BigDecimal   pm_securityControllerId){
		this.getAttribute(SECURITY_CONTROLLER_ID ).setValue( pm_securityControllerId );
	}
 
	public java.math.BigDecimal getSecurityControllerIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( SECURITY_CONTROLLER_ID).getValue()  ;
	}
 
	public Attribute getSecurityControllerId(){
		return this.getAttribute ( SECURITY_CONTROLLER_ID)  ;
	}

	public static final String SIMPLY_UPDATABLE ="SIMPLY_UPDATABLE" ;

	public void setSimplyUpdatableValue(java.lang.String   pm_simplyUpdatable){
		this.getAttribute(SIMPLY_UPDATABLE ).setValue( pm_simplyUpdatable );
	}
 
	public java.lang.String getSimplyUpdatableValue(){
		return (java.lang.String) this.getAttribute ( SIMPLY_UPDATABLE).getValue()  ;
	}
 
	public Attribute getSimplyUpdatable(){
		return this.getAttribute ( SIMPLY_UPDATABLE)  ;
	}

	public static final String SIMPLY_CREATABLE ="SIMPLY_CREATABLE" ;

	public void setSimplyCreatableValue(java.lang.String   pm_simplyCreatable){
		this.getAttribute(SIMPLY_CREATABLE ).setValue( pm_simplyCreatable );
	}
 
	public java.lang.String getSimplyCreatableValue(){
		return (java.lang.String) this.getAttribute ( SIMPLY_CREATABLE).getValue()  ;
	}
 
	public Attribute getSimplyCreatable(){
		return this.getAttribute ( SIMPLY_CREATABLE)  ;
	}

	public static final String SIMPLY_DELETABLE ="SIMPLY_DELETABLE" ;

	public void setSimplyDeletableValue(java.lang.String   pm_simplyDeletable){
		this.getAttribute(SIMPLY_DELETABLE ).setValue( pm_simplyDeletable );
	}
 
	public java.lang.String getSimplyDeletableValue(){
		return (java.lang.String) this.getAttribute ( SIMPLY_DELETABLE).getValue()  ;
	}
 
	public Attribute getSimplyDeletable(){
		return this.getAttribute ( SIMPLY_DELETABLE)  ;
	}

	public static final String UPDATE_LIST_FOR_ALLOWED ="UPDATE_LIST_FOR_ALLOWED" ;

	public void setUpdateListForAllowedValue(java.lang.String   pm_updateListForAllowed){
		this.getAttribute(UPDATE_LIST_FOR_ALLOWED ).setValue( pm_updateListForAllowed );
	}
 
	public java.lang.String getUpdateListForAllowedValue(){
		return (java.lang.String) this.getAttribute ( UPDATE_LIST_FOR_ALLOWED).getValue()  ;
	}
 
	public Attribute getUpdateListForAllowed(){
		return this.getAttribute ( UPDATE_LIST_FOR_ALLOWED)  ;
	}

	public static final String CREATE_LIST_FOR_ALLOWED ="CREATE_LIST_FOR_ALLOWED" ;

	public void setCreateListForAllowedValue(java.lang.String   pm_createListForAllowed){
		this.getAttribute(CREATE_LIST_FOR_ALLOWED ).setValue( pm_createListForAllowed );
	}
 
	public java.lang.String getCreateListForAllowedValue(){
		return (java.lang.String) this.getAttribute ( CREATE_LIST_FOR_ALLOWED).getValue()  ;
	}
 
	public Attribute getCreateListForAllowed(){
		return this.getAttribute ( CREATE_LIST_FOR_ALLOWED)  ;
	}

	public static final String DELETE_LIST_FOR_ALLOWED ="DELETE_LIST_FOR_ALLOWED" ;

	public void setDeleteListForAllowedValue(java.lang.String   pm_deleteListForAllowed){
		this.getAttribute(DELETE_LIST_FOR_ALLOWED ).setValue( pm_deleteListForAllowed );
	}
 
	public java.lang.String getDeleteListForAllowedValue(){
		return (java.lang.String) this.getAttribute ( DELETE_LIST_FOR_ALLOWED).getValue()  ;
	}
 
	public Attribute getDeleteListForAllowed(){
		return this.getAttribute ( DELETE_LIST_FOR_ALLOWED)  ;
	}

	public static final String VIRTUAL ="VIRTUAL" ;

	public void setVirtualValue(java.lang.String   pm_virtual){
		this.getAttribute(VIRTUAL ).setValue( pm_virtual );
	}
 
	public java.lang.String getVirtualValue(){
		return (java.lang.String) this.getAttribute ( VIRTUAL).getValue()  ;
	}
 
	public Attribute getVirtual(){
		return this.getAttribute ( VIRTUAL)  ;
	}

	public static final String OBJECT_NAME ="OBJECT_NAME" ;

	public void setObjectNameValue(java.lang.String   pm_objectName){
		this.getAttribute(OBJECT_NAME ).setValue( pm_objectName );
	}
 
	public java.lang.String getObjectNameValue(){
		return (java.lang.String) this.getAttribute ( OBJECT_NAME).getValue()  ;
	}
 
	public Attribute getObjectName(){
		return this.getAttribute ( OBJECT_NAME)  ;
	}

	public static final String OBJECT_NAME_ ="OBJECT_NAME_" ;

	public void setObjectName_Value(java.lang.String   pm_objectName_){
		this.getAttribute(OBJECT_NAME_ ).setValue( pm_objectName_ );
	}
 
	public java.lang.String getObjectName_Value(){
		return (java.lang.String) this.getAttribute ( OBJECT_NAME_).getValue()  ;
	}
 
	public Attribute getObjectName_(){
		return this.getAttribute ( OBJECT_NAME_)  ;
	}

	public static final String OBJECT_EDITOR_XHTML_PAGE ="OBJECT_EDITOR_XHTML_PAGE" ;

	public void setObjectEditorXhtmlPageValue(java.lang.String   pm_objectEditorXhtmlPage){
		this.getAttribute(OBJECT_EDITOR_XHTML_PAGE ).setValue( pm_objectEditorXhtmlPage );
	}
 
	public java.lang.String getObjectEditorXhtmlPageValue(){
		return (java.lang.String) this.getAttribute ( OBJECT_EDITOR_XHTML_PAGE).getValue()  ;
	}
 
	public Attribute getObjectEditorXhtmlPage(){
		return this.getAttribute ( OBJECT_EDITOR_XHTML_PAGE)  ;
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

	public static final String MAP_CLASS_NAME ="MAP_CLASS_NAME" ;

	public void setMapClassNameValue(java.lang.String   pm_mapClassName){
		this.getAttribute(MAP_CLASS_NAME ).setValue( pm_mapClassName );
	}
 
	public java.lang.String getMapClassNameValue(){
		return (java.lang.String) this.getAttribute ( MAP_CLASS_NAME).getValue()  ;
	}
 
	public Attribute getMapClassName(){
		return this.getAttribute ( MAP_CLASS_NAME)  ;
	}

	public static final String LIST_EXTRA_COLUMN_FIRST_PATH ="LIST_EXTRA_COLUMN_FIRST_PATH" ;

	public void setListExtraColumnFirstPathValue(java.lang.String   pm_listExtraColumnFirstPath){
		this.getAttribute(LIST_EXTRA_COLUMN_FIRST_PATH ).setValue( pm_listExtraColumnFirstPath );
	}
 
	public java.lang.String getListExtraColumnFirstPathValue(){
		return (java.lang.String) this.getAttribute ( LIST_EXTRA_COLUMN_FIRST_PATH).getValue()  ;
	}
 
	public Attribute getListExtraColumnFirstPath(){
		return this.getAttribute ( LIST_EXTRA_COLUMN_FIRST_PATH)  ;
	}

	public static final String RELOAD_DATASET_AFTER_SAVEALL ="RELOAD_DATASET_AFTER_SAVEALL" ;

	public void setReloadDatasetAfterSaveallValue(java.lang.String   pm_reloadDatasetAfterSaveall){
		this.getAttribute(RELOAD_DATASET_AFTER_SAVEALL ).setValue( pm_reloadDatasetAfterSaveall );
	}
 
	public java.lang.String getReloadDatasetAfterSaveallValue(){
		return (java.lang.String) this.getAttribute ( RELOAD_DATASET_AFTER_SAVEALL).getValue()  ;
	}
 
	public Attribute getReloadDatasetAfterSaveall(){
		return this.getAttribute ( RELOAD_DATASET_AFTER_SAVEALL)  ;
	}

	public static final String INSTANCE_NAME_IN_XHTML_EDITOR ="INSTANCE_NAME_IN_XHTML_EDITOR" ;

	public void setInstanceNameInXhtmlEditorValue(java.lang.String   pm_instanceNameInXhtmlEditor){
		this.getAttribute(INSTANCE_NAME_IN_XHTML_EDITOR ).setValue( pm_instanceNameInXhtmlEditor );
	}
 
	public java.lang.String getInstanceNameInXhtmlEditorValue(){
		return (java.lang.String) this.getAttribute ( INSTANCE_NAME_IN_XHTML_EDITOR).getValue()  ;
	}
 
	public Attribute getInstanceNameInXhtmlEditor(){
		return this.getAttribute ( INSTANCE_NAME_IN_XHTML_EDITOR)  ;
	}

	public static final String OBJECT_TYPE ="OBJECT_TYPE" ;

	public void setObjectTypeValue(java.lang.String   pm_objectType){
		this.getAttribute(OBJECT_TYPE ).setValue( pm_objectType );
	}
 
	public java.lang.String getObjectTypeValue(){
		return (java.lang.String) this.getAttribute ( OBJECT_TYPE).getValue()  ;
	}
 
	public Attribute getObjectType(){
		return this.getAttribute ( OBJECT_TYPE)  ;
	}

	public static final String TRIGGER_CONTROLLER_CLASS ="TRIGGER_CONTROLLER_CLASS" ;

	public void setTriggerControllerClassValue(java.lang.String   pm_triggerControllerClass){
		this.getAttribute(TRIGGER_CONTROLLER_CLASS ).setValue( pm_triggerControllerClass );
	}
 
	public java.lang.String getTriggerControllerClassValue(){
		return (java.lang.String) this.getAttribute ( TRIGGER_CONTROLLER_CLASS).getValue()  ;
	}
 
	public Attribute getTriggerControllerClass(){
		return this.getAttribute ( TRIGGER_CONTROLLER_CLASS)  ;
	}

	public static final String OBJECT_EDITOR_JSP_PAGE ="OBJECT_EDITOR_JSP_PAGE" ;

	public void setObjectEditorJspPageValue(java.lang.String   pm_objectEditorJspPage){
		this.getAttribute(OBJECT_EDITOR_JSP_PAGE ).setValue( pm_objectEditorJspPage );
	}
 
	public java.lang.String getObjectEditorJspPageValue(){
		return (java.lang.String) this.getAttribute ( OBJECT_EDITOR_JSP_PAGE).getValue()  ;
	}
 
	public Attribute getObjectEditorJspPage(){
		return this.getAttribute ( OBJECT_EDITOR_JSP_PAGE)  ;
	}

	public static final String AFTER_DELETE_EXE_ID ="AFTER_DELETE_EXE_ID" ;

	public void setAfterDeleteExeIdValue(java.lang.String   pm_afterDeleteExeId){
		this.getAttribute(AFTER_DELETE_EXE_ID ).setValue( pm_afterDeleteExeId );
	}
 
	public java.lang.String getAfterDeleteExeIdValue(){
		return (java.lang.String) this.getAttribute ( AFTER_DELETE_EXE_ID).getValue()  ;
	}
 
	public Attribute getAfterDeleteExeId(){
		return this.getAttribute ( AFTER_DELETE_EXE_ID)  ;
	}

	public static final String BEFORE_DELETE_EXE_ID ="BEFORE_DELETE_EXE_ID" ;

	public void setBeforeDeleteExeIdValue(java.lang.String   pm_beforeDeleteExeId){
		this.getAttribute(BEFORE_DELETE_EXE_ID ).setValue( pm_beforeDeleteExeId );
	}
 
	public java.lang.String getBeforeDeleteExeIdValue(){
		return (java.lang.String) this.getAttribute ( BEFORE_DELETE_EXE_ID).getValue()  ;
	}
 
	public Attribute getBeforeDeleteExeId(){
		return this.getAttribute ( BEFORE_DELETE_EXE_ID)  ;
	}

	public static final String BEFORE_UPDATE_EXE_ID ="BEFORE_UPDATE_EXE_ID" ;

	public void setBeforeUpdateExeIdValue(java.lang.String   pm_beforeUpdateExeId){
		this.getAttribute(BEFORE_UPDATE_EXE_ID ).setValue( pm_beforeUpdateExeId );
	}
 
	public java.lang.String getBeforeUpdateExeIdValue(){
		return (java.lang.String) this.getAttribute ( BEFORE_UPDATE_EXE_ID).getValue()  ;
	}
 
	public Attribute getBeforeUpdateExeId(){
		return this.getAttribute ( BEFORE_UPDATE_EXE_ID)  ;
	}

	public static final String AFTER_UPDATE_EXE_ID ="AFTER_UPDATE_EXE_ID" ;

	public void setAfterUpdateExeIdValue(java.lang.String   pm_afterUpdateExeId){
		this.getAttribute(AFTER_UPDATE_EXE_ID ).setValue( pm_afterUpdateExeId );
	}
 
	public java.lang.String getAfterUpdateExeIdValue(){
		return (java.lang.String) this.getAttribute ( AFTER_UPDATE_EXE_ID).getValue()  ;
	}
 
	public Attribute getAfterUpdateExeId(){
		return this.getAttribute ( AFTER_UPDATE_EXE_ID)  ;
	}

	public static final String BEFORE_INSERT_EXE_ID ="BEFORE_INSERT_EXE_ID" ;

	public void setBeforeInsertExeIdValue(java.lang.String   pm_beforeInsertExeId){
		this.getAttribute(BEFORE_INSERT_EXE_ID ).setValue( pm_beforeInsertExeId );
	}
 
	public java.lang.String getBeforeInsertExeIdValue(){
		return (java.lang.String) this.getAttribute ( BEFORE_INSERT_EXE_ID).getValue()  ;
	}
 
	public Attribute getBeforeInsertExeId(){
		return this.getAttribute ( BEFORE_INSERT_EXE_ID)  ;
	}

	public static final String AFTER_INSERT_EXE_ID ="AFTER_INSERT_EXE_ID" ;

	public void setAfterInsertExeIdValue(java.lang.String   pm_afterInsertExeId){
		this.getAttribute(AFTER_INSERT_EXE_ID ).setValue( pm_afterInsertExeId );
	}
 
	public java.lang.String getAfterInsertExeIdValue(){
		return (java.lang.String) this.getAttribute ( AFTER_INSERT_EXE_ID).getValue()  ;
	}
 
	public Attribute getAfterInsertExeId(){
		return this.getAttribute ( AFTER_INSERT_EXE_ID)  ;
	}

	public void setObjectNameAutoLang(java.lang.String   pm_objectName){
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		this.getObjectName().setValue( pm_objectName );
		 else 
		this.getObjectName_().setValue( pm_objectName );
	}
 
	public Attribute getObjectNameAutoLang(){
		 Attribute result ;
		 int userlang = this.getDbService().getLoggedUserLang() ;
		 int sysDefaultLang = 2; //TODO getSysDefault
		 if (userlang == sysDefaultLang) 
		 result =   this.getObjectName();
		 else 
		 result =   this.getObjectName_();
		 return result;
	}

}