package com.smartValue.database.map.auto;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.Attribute;
import com.smartValue.database.DataSet;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;

public abstract class _SysMsg extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
 private static final long serialVersionUID = 1L ; 
 public static final String DB_TABLE_NAME = "SYS_MSG"; 
 public static final String DB_TABLE_OWNER = "ICDB"; 

	public _SysMsg(){
	}

	 public DbTable getTable() 
	 { 
		return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public static final String MSG_ID ="MSG_ID" ;

	public void setMsgIdValue(java.math.BigDecimal   pm_msgId){
		this.getAttribute(MSG_ID ).setValue( pm_msgId );
	}
 
	public java.math.BigDecimal getMsgIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( MSG_ID).getValue()  ;
	}
 
	public Attribute getMsgId(){
		return this.getAttribute ( MSG_ID)  ;
	}

	public static final String MSG_LNG_ID ="MSG_LNG_ID" ;

	public void setMsgLngIdValue(java.math.BigDecimal   pm_msgLngId){
		this.getAttribute(MSG_LNG_ID ).setValue( pm_msgLngId );
	}
 
	public java.math.BigDecimal getMsgLngIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( MSG_LNG_ID).getValue()  ;
	}
 
	public Attribute getMsgLngId(){
		return this.getAttribute ( MSG_LNG_ID)  ;
	}

	public static final String MSG_TOOLTIP ="MSG_TOOLTIP" ;

	public void setMsgTooltipValue(java.math.BigDecimal   pm_msgTooltip){
		this.getAttribute(MSG_TOOLTIP ).setValue( pm_msgTooltip );
	}
 
	public java.math.BigDecimal getMsgTooltipValue(){
		return (java.math.BigDecimal) this.getAttribute ( MSG_TOOLTIP).getValue()  ;
	}
 
	public Attribute getMsgTooltip(){
		return this.getAttribute ( MSG_TOOLTIP)  ;
	}

	public static final String MSG_TYPE ="MSG_TYPE" ;

	public void setMsgTypeValue(java.math.BigDecimal   pm_msgType){
		this.getAttribute(MSG_TYPE ).setValue( pm_msgType );
	}
 
	public java.math.BigDecimal getMsgTypeValue(){
		return (java.math.BigDecimal) this.getAttribute ( MSG_TYPE).getValue()  ;
	}
 
	public Attribute getMsgType(){
		return this.getAttribute ( MSG_TYPE)  ;
	}

	public static final String MSG_IMG_ID ="MSG_IMG_ID" ;

	public void setMsgImgIdValue(java.math.BigDecimal   pm_msgImgId){
		this.getAttribute(MSG_IMG_ID ).setValue( pm_msgImgId );
	}
 
	public java.math.BigDecimal getMsgImgIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( MSG_IMG_ID).getValue()  ;
	}
 
	public Attribute getMsgImgId(){
		return this.getAttribute ( MSG_IMG_ID)  ;
	}

	public static final String MSG_TITLE ="MSG_TITLE" ;

	public void setMsgTitleValue(java.lang.String   pm_msgTitle){
		this.getAttribute(MSG_TITLE ).setValue( pm_msgTitle );
	}
 
	public java.lang.String getMsgTitleValue(){
		return (java.lang.String) this.getAttribute ( MSG_TITLE).getValue()  ;
	}
 
	public Attribute getMsgTitle(){
		return this.getAttribute ( MSG_TITLE)  ;
	}

	public static final String MSG_DESC ="MSG_DESC" ;

	public void setMsgDescValue(java.lang.String   pm_msgDesc){
		this.getAttribute(MSG_DESC ).setValue( pm_msgDesc );
	}
 
	public java.lang.String getMsgDescValue(){
		return (java.lang.String) this.getAttribute ( MSG_DESC).getValue()  ;
	}
 
	public Attribute getMsgDesc(){
		return this.getAttribute ( MSG_DESC)  ;
	}

	public static final String IMPL_CLASS_ID ="IMPL_CLASS_ID" ;

	public void setImplClassIdValue(java.math.BigDecimal   pm_implClassId){
		this.getAttribute(IMPL_CLASS_ID ).setValue( pm_implClassId );
	}
 
	public java.math.BigDecimal getImplClassIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( IMPL_CLASS_ID).getValue()  ;
	}
 
	public Attribute getImplClassId(){
		return this.getAttribute ( IMPL_CLASS_ID)  ;
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

	// ********Start of Child DataSets getter methods******** 
	// *******End of Child DataSets getter methods******** 
}