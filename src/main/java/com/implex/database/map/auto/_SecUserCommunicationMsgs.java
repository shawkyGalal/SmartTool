package com.implex.database.map.auto;

import com.implex.database.Attribute;
import com.implex.database.DbTable;
import com.implex.database.PersistantObject;

public abstract class _SecUserCommunicationMsgs extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "SEC_USER_COMMUNICATION_MSGS"; 
 public static final String DB_TABLE_OWNER = "ICDB"; 
 

	public _SecUserCommunicationMsgs(){
	}

	 public DbTable getTable() 
	 { 
			return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String FROM_USER ="FROM_USER" ;

	public void setFromUserValue(java.lang.String   pm_fromUser){
		this.getAttribute("FROM_USER" ).setValue( pm_fromUser );
	}
 
	public java.lang.String getFromUserValue(){
		return (java.lang.String) this.getAttribute ( "FROM_USER").getValue()  ;
	}
 
	public Attribute getFromUser(){
		return this.getAttribute ( "FROM_USER")  ;
	}

	public static final String TO_USERS ="TO_USERS" ;

	public void setToUsersValue(java.lang.String   pm_toUsers){
		this.getAttribute("TO_USERS" ).setValue( pm_toUsers );
	}
 
	public java.lang.String getToUsersValue(){
		return (java.lang.String) this.getAttribute ( "TO_USERS").getValue()  ;
	}
 
	public Attribute getToUsers(){
		return this.getAttribute ( "TO_USERS")  ;
	}

	public static final String MSG_HEADER ="MSG_HEADER" ;

	public void setMsgHeaderValue(java.lang.String   pm_msgHeader){
		this.getAttribute("MSG_HEADER" ).setValue( pm_msgHeader );
	}
 
	public java.lang.String getMsgHeaderValue(){
		return (java.lang.String) this.getAttribute ( "MSG_HEADER").getValue()  ;
	}
 
	public Attribute getMsgHeader(){
		return this.getAttribute ( "MSG_HEADER")  ;
	}

	public static final String MODIFIED_DT ="MODIFIED_DT" ;

	public void setModifiedDtValue(java.sql.Timestamp   pm_modifiedDt){
		this.getAttribute("MODIFIED_DT" ).setValue( pm_modifiedDt );
	}
 
	public java.sql.Timestamp getModifiedDtValue(){
		return (java.sql.Timestamp) this.getAttribute ( "MODIFIED_DT").getValue()  ;
	}
 
	public Attribute getModifiedDt(){
		return this.getAttribute ( "MODIFIED_DT")  ;
	}

	public static final String MSG_READ ="MSG_READ" ;

	public void setMsgReadValue(java.lang.String   pm_msgRead){
		this.getAttribute("MSG_READ" ).setValue( pm_msgRead );
	}
 
	public java.lang.String getMsgReadValue(){
		return (java.lang.String) this.getAttribute ( "MSG_READ").getValue()  ;
	}
 
	public Attribute getMsgRead(){
		return this.getAttribute ( "MSG_READ")  ;
	}

	public static final String MSG_BODY ="MSG_BODY" ;

	public void setMsgBodyValue(java.lang.String   pm_msgBody){
		this.getAttribute("MSG_BODY" ).setValue( pm_msgBody );
	}
 
	public java.lang.String getMsgBodyValue(){
		return (java.lang.String) this.getAttribute ( "MSG_BODY").getValue()  ;
	}
 
	public Attribute getMsgBody(){
		return this.getAttribute ( "MSG_BODY")  ;
	}
}
