package com.smartValue.database.map.auto;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.Attribute;
import com.smartValue.database.DataSet;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;

public abstract class _SecUserGroups extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "SEC_USER_GROUPS"; 
 public static final String DB_TABLE_OWNER = "ICDB"; 

	public _SecUserGroups(){
	}

	 public DbTable getTable() 
	 { 
		return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
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

	public static final String MEMBER_IN_GROUP ="MEMBER_IN_GROUP" ;

	public void setMemberInGroupValue(java.lang.String   pm_memberInGroup){
		this.getAttribute(MEMBER_IN_GROUP ).setValue( pm_memberInGroup );
	}
 
	public java.lang.String getMemberInGroupValue(){
		return (java.lang.String) this.getAttribute ( MEMBER_IN_GROUP).getValue()  ;
	}
 
	public Attribute getMemberInGroup(){
		return this.getAttribute ( MEMBER_IN_GROUP)  ;
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

	public static final String USER_DEFAULT_ROLE ="USER_DEFAULT_ROLE" ;

	public void setUserDefaultRoleValue(java.lang.String   pm_userDefaultRole){
		this.getAttribute(USER_DEFAULT_ROLE ).setValue( pm_userDefaultRole );
	}
 
	public java.lang.String getUserDefaultRoleValue(){
		return (java.lang.String) this.getAttribute ( USER_DEFAULT_ROLE).getValue()  ;
	}
 
	public Attribute getUserDefaultRole(){
		return this.getAttribute ( USER_DEFAULT_ROLE)  ;
	}

	// ********Start of Child DataSets getter methods******** 
	// *******End of Child DataSets getter methods******** 
}