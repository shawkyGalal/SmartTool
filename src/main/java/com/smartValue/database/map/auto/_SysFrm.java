package com.smartValue.database.map.auto;

import com.smartValue.database.Attribute;
import com.smartValue.database.AttributeChangeValidator;
import com.smartValue.database.DbTable;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.map.SecUsrDta;
import com.smartValue.database.map.validators.SysFrmChangeValidator;

public abstract class _SysFrm extends PersistantObject {
/* Dynamically Generated Mapping Class 
 * By : Shawky Foda. Please Never Update This Class.. Only regenerate... 
 */
	public static final String DB_TABLE_NAME = "SYS_FRM";
	public static final String DB_TABLE_OWNER = "ICDB";
	public _SysFrm(){
	}

	 public DbTable getTable() 
	 { 
			return new DbTable(DB_TABLE_OWNER , DB_TABLE_NAME , this.getDbService()); 
	 } 

	public AttributeChangeValidator changeValidator ; 
	@Override 
	public AttributeChangeValidator getAttributeChangeValidator(SecUsrDta pm_secUserData, PersistantObject pm_po, String pm_key)
	{ 
		if (changeValidator == null) 
		changeValidator = new SysFrmChangeValidator(pm_secUserData , pm_po  , pm_key); 
		return changeValidator; 
	}

	public static final String FRM_NAME ="FRM_NAME" ;

	public void setFrmNameValue(java.lang.String   pm_frmName){
		this.getAttribute("FRM_NAME" ).setValue( pm_frmName );
	}
 
	public java.lang.String getFrmNameValue(){
		return (java.lang.String) this.getAttribute ( "FRM_NAME").getValue()  ;
	}
 
	public Attribute getFrmName(){
		return this.getAttribute ( "FRM_NAME")  ;
	}

	public static final String FRM_LNG_ID ="FRM_LNG_ID" ;

	public void setFrmLngIdValue(java.math.BigDecimal   pm_frmLngId){
		this.getAttribute("FRM_LNG_ID" ).setValue( pm_frmLngId );
	}
 
	public java.math.BigDecimal getFrmLngIdValue(){
		return (java.math.BigDecimal) this.getAttribute ( "FRM_LNG_ID").getValue()  ;
	}
 
	public Attribute getFrmLngId(){
		return this.getAttribute ( "FRM_LNG_ID")  ;
	}

	public static final String FRM_ALIAS ="FRM_ALIAS" ;

	public void setFrmAliasValue(java.lang.String   pm_frmAlias){
		this.getAttribute("FRM_ALIAS" ).setValue( pm_frmAlias );
	}
 
	public java.lang.String getFrmAliasValue(){
		return (java.lang.String) this.getAttribute ( "FRM_ALIAS").getValue()  ;
	}
 
	public Attribute getFrmAlias(){
		return this.getAttribute ( "FRM_ALIAS")  ;
	}

	public static final String FRM_TITLE ="FRM_TITLE" ;

	public void setFrmTitleValue(java.lang.String   pm_frmTitle){
		this.getAttribute("FRM_TITLE" ).setValue( pm_frmTitle );
	}
 
	public java.lang.String getFrmTitleValue(){
		return (java.lang.String) this.getAttribute ( "FRM_TITLE").getValue()  ;
	}
 
	public Attribute getFrmTitle(){
		return this.getAttribute ( "FRM_TITLE")  ;
	}

	public static final String FRM_HELP ="FRM_HELP" ;

	public void setFrmHelpValue(java.lang.String   pm_frmHelp){
		this.getAttribute("FRM_HELP" ).setValue( pm_frmHelp );
	}
 
	public java.lang.String getFrmHelpValue(){
		return (java.lang.String) this.getAttribute ( "FRM_HELP").getValue()  ;
	}
 
	public Attribute getFrmHelp(){
		return this.getAttribute ( "FRM_HELP")  ;
	}

	public static final String FRM_VP ="FRM_VP" ;

	public void setFrmVpValue(java.math.BigDecimal   pm_frmVp){
		this.getAttribute("FRM_VP" ).setValue( pm_frmVp );
	}
 
	public java.math.BigDecimal getFrmVpValue(){
		return (java.math.BigDecimal) this.getAttribute ( "FRM_VP").getValue()  ;
	}
 
	public Attribute getFrmVp(){
		return this.getAttribute ( "FRM_VP")  ;
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
}