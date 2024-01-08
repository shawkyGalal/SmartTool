package com.implex.database.map.auto;

import com.implex.database.Attribute;
import com.implex.database.DbTable;
import com.implex.database.PersistantObject;

public abstract class _SecGroups extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */

	public _SecGroups(){
	}

	 public DbTable getTable() 
	 { 
		return new DbTable("ICDB" , "SEC_GROUPS" , this.getDbService()); 
	 } 

	public static final String GROUP_ID ="GROUP_ID" ;

	public void setGroupIdValue(java.math.BigDecimal   pm_groupId){
		this.getAttribute("GROUP_ID" ).setValue( pm_groupId );
	}
 
	public java.math.BigDecimal getGroupIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( "GROUP_ID").getValue()  ;
	}
 
	public Attribute getGroupId(){
		return this.getAttribute ( "GROUP_ID")  ;
	}

	public static final String GROUP_NAME ="GROUP_NAME" ;

	public void setGroupNameValue(java.lang.String   pm_groupName){
		this.getAttribute("GROUP_NAME" ).setValue( pm_groupName );
	}
 
	public java.lang.String getGroupNameValue(){
		return (java.lang.String) this.getAttribute ( "GROUP_NAME").getValue()  ;
	}
 
	public Attribute getGroupName(){
		return this.getAttribute ( "GROUP_NAME")  ;
	}

	public static final String GROUP_DESC ="GROUP_DESC" ;

	public void setGroupDescValue(java.lang.String   pm_groupDesc){
		this.getAttribute("GROUP_DESC" ).setValue( pm_groupDesc );
	}
 
	public java.lang.String getGroupDescValue(){
		return (java.lang.String) this.getAttribute ( "GROUP_DESC").getValue()  ;
	}
 
	public Attribute getGroupDesc(){
		return this.getAttribute ( "GROUP_DESC")  ;
	}
}
