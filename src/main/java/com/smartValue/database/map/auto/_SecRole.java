package com.smartValue.database.map.auto;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.Attribute;
import com.smartValue.database.DataSet;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;

public abstract class _SecRole extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "SEC_ROLE"; 
 public static final String DB_TABLE_OWNER = "ICDB"; 

	public _SecRole(){
	}

	 public DbTable getTable() 
	 { 
		return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String ROLE_ID ="ROLE_ID" ;

	public void setRoleIdValue(java.math.BigDecimal   pm_roleId){
		this.getAttribute(ROLE_ID ).setValue( pm_roleId );
	}
 
	public java.math.BigDecimal getRoleIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( ROLE_ID).getValue()  ;
	}
 
	public Attribute getRoleId(){
		return this.getAttribute ( ROLE_ID)  ;
	}

	public static final String ROLE_NAME ="ROLE_NAME" ;

	public void setRoleNameValue(java.lang.String   pm_roleName){
		this.getAttribute(ROLE_NAME ).setValue( pm_roleName );
	}
 
	public java.lang.String getRoleNameValue(){
		return (java.lang.String) this.getAttribute ( ROLE_NAME).getValue()  ;
	}
 
	public Attribute getRoleName(){
		return this.getAttribute ( ROLE_NAME)  ;
	}

	public static final String DESCRIPTIONS ="DESCRIPTIONS" ;

	public void setDescriptionsValue(java.lang.String   pm_descriptions){
		this.getAttribute(DESCRIPTIONS ).setValue( pm_descriptions );
	}
 
	public java.lang.String getDescriptionsValue(){
		return (java.lang.String) this.getAttribute ( DESCRIPTIONS).getValue()  ;
	}
 
	public Attribute getDescriptions(){
		return this.getAttribute ( DESCRIPTIONS)  ;
	}

	public static final String DEFAULT_READ_ACCESS ="DEFAULT_READ_ACCESS" ;

	public void setDefaultReadAccessValue(java.lang.String   pm_defaultReadAccess){
		this.getAttribute(DEFAULT_READ_ACCESS ).setValue( pm_defaultReadAccess );
	}
 
	public java.lang.String getDefaultReadAccessValue(){
		return (java.lang.String) this.getAttribute ( DEFAULT_READ_ACCESS).getValue()  ;
	}
 
	public Attribute getDefaultReadAccess(){
		return this.getAttribute ( DEFAULT_READ_ACCESS)  ;
	}

	public static final String DEFAULT_UPDATE_ACCESS ="DEFAULT_UPDATE_ACCESS" ;

	public void setDefaultUpdateAccessValue(java.lang.String   pm_defaultUpdateAccess){
		this.getAttribute(DEFAULT_UPDATE_ACCESS ).setValue( pm_defaultUpdateAccess );
	}
 
	public java.lang.String getDefaultUpdateAccessValue(){
		return (java.lang.String) this.getAttribute ( DEFAULT_UPDATE_ACCESS).getValue()  ;
	}
 
	public Attribute getDefaultUpdateAccess(){
		return this.getAttribute ( DEFAULT_UPDATE_ACCESS)  ;
	}

	public static final String DEFAULT_EXEC_ACCESS ="DEFAULT_EXEC_ACCESS" ;

	public void setDefaultExecAccessValue(java.lang.String   pm_defaultExecAccess){
		this.getAttribute(DEFAULT_EXEC_ACCESS ).setValue( pm_defaultExecAccess );
	}
 
	public java.lang.String getDefaultExecAccessValue(){
		return (java.lang.String) this.getAttribute ( DEFAULT_EXEC_ACCESS).getValue()  ;
	}
 
	public Attribute getDefaultExecAccess(){
		return this.getAttribute ( DEFAULT_EXEC_ACCESS)  ;
	}

	public static final String DEFAULT_CREATE_ACCESS ="DEFAULT_CREATE_ACCESS" ;

	public void setDefaultCreateAccessValue(java.lang.String   pm_defaultCreateAccess){
		this.getAttribute(DEFAULT_CREATE_ACCESS ).setValue( pm_defaultCreateAccess );
	}
 
	public java.lang.String getDefaultCreateAccessValue(){
		return (java.lang.String) this.getAttribute ( DEFAULT_CREATE_ACCESS).getValue()  ;
	}
 
	public Attribute getDefaultCreateAccess(){
		return this.getAttribute ( DEFAULT_CREATE_ACCESS)  ;
	}

	public static final String DEFAULT_DELETE_ACCESS ="DEFAULT_DELETE_ACCESS" ;

	public void setDefaultDeleteAccessValue(java.lang.String   pm_defaultDeleteAccess){
		this.getAttribute(DEFAULT_DELETE_ACCESS ).setValue( pm_defaultDeleteAccess );
	}
 
	public java.lang.String getDefaultDeleteAccessValue(){
		return (java.lang.String) this.getAttribute ( DEFAULT_DELETE_ACCESS).getValue()  ;
	}
 
	public Attribute getDefaultDeleteAccess(){
		return this.getAttribute ( DEFAULT_DELETE_ACCESS)  ;
	}

	public static final String SYS_OWNER ="SYS_OWNER" ;

	public void setSysOwnerValue(java.lang.String   pm_sysOwner){
		this.getAttribute(SYS_OWNER ).setValue( pm_sysOwner );
	}
 
	public java.lang.String getSysOwnerValue(){
		return (java.lang.String) this.getAttribute ( SYS_OWNER).getValue()  ;
	}
 
	public Attribute getSysOwner(){
		return this.getAttribute ( SYS_OWNER)  ;
	}

	public static final String GRANTABLE ="GRANTABLE" ;

	public void setGrantableValue(java.lang.String   pm_grantable){
		this.getAttribute(GRANTABLE ).setValue( pm_grantable );
	}
 
	public java.lang.String getGrantableValue(){
		return (java.lang.String) this.getAttribute ( GRANTABLE).getValue()  ;
	}
 
	public Attribute getGrantable(){
		return this.getAttribute ( GRANTABLE)  ;
	}

	public static final String COMPANY_ID ="COMPANY_ID" ;

	public void setCompanyIdValue(java.lang.String   pm_companyId){
		this.getAttribute(COMPANY_ID ).setValue( pm_companyId );
	}
 
	public java.lang.String getCompanyIdValue(){
		return (java.lang.String) this.getAttribute ( COMPANY_ID).getValue()  ;
	}
 
	public Attribute getCompanyId(){
		return this.getAttribute ( COMPANY_ID)  ;
	}

	public static final String HIDE_TREE_NODES ="HIDE_TREE_NODES" ;

	public void setHideTreeNodesValue(java.lang.String   pm_hideTreeNodes){
		this.getAttribute(HIDE_TREE_NODES ).setValue( pm_hideTreeNodes );
	}
 
	public java.lang.String getHideTreeNodesValue(){
		return (java.lang.String) this.getAttribute ( HIDE_TREE_NODES).getValue()  ;
	}
 
	public Attribute getHideTreeNodes(){
		return this.getAttribute ( HIDE_TREE_NODES)  ;
	}

	// ********Start of Child DataSets getter methods******** 
	public DataSet getDatasetFkSecRoleAuthority()
	{
		 return this.geChildDsByForignKeyName("FK_SEC_ROLE_AUTHORITY");
	}
	// *******End of Child DataSets getter methods******** 
}